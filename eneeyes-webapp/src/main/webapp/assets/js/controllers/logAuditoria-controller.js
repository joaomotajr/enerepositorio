
app.controller('logAuditoriaController', function ($scope, $timeout, $filter, $cookieStore, LogAuditoriaService) {

	$scope.lenPage =  $cookieStore.get('lenPageAuditoria') == null ? 15 : $cookieStore.get('lenPageAuditoria');
	
	$scope.countAuditoria = 0;

	$scope.lenPageValid = true;	
	$scope.currentPage = 0;
	$scope.listPages = [];	
	$scope.countPages = 0;

	var lastPage = -1;
	var listOfPagination = 6;

	$scope.enumInterval = ({
        UMA_HORA: "UMA_HORA",
        SEIS_HORAS: "SEIS_HORAS",
        DOZE_HORAS: "DOZE_HORAS",
        UM_DIA: "UM_DIA",
        DOIS_DIAS: "DOIS_DIAS",
        SETE_DIAS: "SETE_DIAS",
		UM_MES: "UM_MES",
		CUSTOM: "CUSTOM"
	});	

	$scope.interval = $scope.enumInterval.UMA_MES;

	function range(total) {
		if(total===1) {
			return [];
		}

		var input = 0;
		if(total > listOfPagination) {
			if(lastPage > Math.floor(listOfPagination/2)) {
				input = lastPage - Math.floor(listOfPagination/2);
			}
			if(input + listOfPagination <= total) {
				total = input + listOfPagination;
			}
			if(total - input < listOfPagination) {
				input = total - listOfPagination;
			}
		}
		return Array.apply(null, {length: total-input}).map(function(v, i){return i + input ;});
	}

	$scope.getLogAuditoria = function(n) {
		
		if (!$scope.lenPageValid) {
			
			$scope.daysDiff ="ATENÇÃO! Quantidade de registros por página Inválido." ;			
			$("#snoAlertBox").fadeIn();			
			window.setTimeout(function () { $("#snoAlertBox").fadeOut(300)}, 3000);			
			daysExceed = true;
			return;
		}

		if (n < 0 || n > $scope.countPages) return;			

		$scope.currentPage = n;
		$scope.loading = true;

		if($scope.interval == $scope.enumInterval.CUSTOM) {
			
			$scope.getLogAuditoriaInterval(n);
		}
		else {
			
			$scope.getLogAuditoriaPreDefined(n);
		}
	}
	
	$scope.getLogAuditoriaPreDefined = function(n) {

		$cookieStore.put("lenPageAuditoria", $scope.lenPage);
		                                                    
		$scope.listPreDefLogs = new LogAuditoriaService.listPreDef();		 
		$scope.listPreDefLogs.$logAuditoria({_csrf : angular.element('#_csrf').val(),
			interval: $scope.interval,
			currentPage: $scope.currentPage,
			lenPage: $scope.lenPage
		}, function(){
					  
			$timeout(function () {
				if($scope.listPreDefLogs != null && $scope.listPreDefLogs.list.length > 0 )
				{
					lastPage = $scope.currentPage;					
					$scope.listPages = range(Math.ceil($scope.listPreDefLogs.totalList / $scope.lenPage));
					$scope.countPages = Math.ceil($scope.listPreDefLogs.totalList / $scope.lenPage);
					$scope.countAuditoria = padZeros($scope.listPreDefLogs.totalList, 5);

					$scope.logsAuditoria = $scope.listPreDefLogs.list;					
				}
				$scope.loading = false;
			}, 500);
       });		 
	}

	$scope.getHistoricInterval = function(n) {		
		
		var dataInicio = new Date($('#dateInAuditoria').data().DateTimePicker.date._d);
		var dataFim = new Date($('#dateOutAuditoria').data().DateTimePicker.date._d);		

		if (dataFim < dataInicio) {
			
			$scope.daysDiff ="ATENÇÃO! Data Final Precisa ser Maior que Inicial." ;			
			$("#snoAlertBox").fadeIn();			
			window.setTimeout(function () { $("#snoAlertBox").fadeOut(300)}, 3000);			
			daysExceed = true;
			return;
		}
		
		$cookieStore.put("lenPageAuditoria", $scope.lenPage);

		$scope.selectedPeriodo = dataInicio.toLocaleString() + ' & ' + dataFim.toLocaleString();		
				
		$scope.listIntervalLogs = new LogAuditoriaService.listInterval();		 
		$scope.listIntervalLogs.$logAuditoria({_csrf : angular.element('#_csrf').val(),
			dateIn: dataInicio,
			dateOut: dataFim,
			currentPage: $scope.currentPage,
			lenPage: $scope.lenPage
		}, function(){
					  
			$timeout(function () {
				if($scope.listIntervalLogs != null && $scope.listIntervalLogs.list.length > 0 )
				{
					lastPage = $scope.currentPage;
					
					$scope.listPages = range(Math.ceil($scope.listIntervalLogs.totalList / $scope.lenPage));
					$scope.countPages = Math.ceil($scope.listIntervalLogs.totalList / $scope.lenPage);
					$scope.countAuditoria = padZeros($scope.listIntervalLogs.totalList, 5);

					$scope.logsAuditoria = $scope.listIntervalLogs.list					
				}

				$scope.loading = false;					

			}, 500);
       });
		
	}		

	$scope.changeLenPage = function() {
		
		if($scope.lenPage < 0 || $scope.lenPage > 2000)
			$scope.lenPageValid = false;
		else
			$scope.lenPageValid = true;
		
	}
	
	$scope.validLenPage = function(e) {
		$scope.lenPage.replace(/[^\d].+/, "");
		
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			e.preventDefault();
	   }
	}	

	$scope.details = function(i) {
				
		$scope.logAuditoriaDetails = JSON.parse($scope.logsAuditoria[i].detail);
		$scope.logAuditoriaDetails.entity = $scope.logsAuditoria[i].entity;
		
		$timeout(function () {
			$('#modalShowMessages').modal({ show: 'false' });                        
		}, 200);
	}

	$timeout(function(){
		angular.element('body').removeClass('loading');
		
		$('#dateInAuditoria').datetimepicker(
				{ 	defaultDate: new Date(), 
					format:'DD/MM/YYYY HH:mm:ss',
					autoclose: true,
		            language: 'br'
				}
		);
		$('#dateOutAuditoria').datetimepicker(
			{ 	defaultDate: new Date(), 
				format:'DD/MM/YYYY HH:mm:ss',
				autoclose: true,
	            language: 'br'
			}
		);
		
		$("#dateInAuditoria").on("dp.change",function (e) {
	        $('#dateOutAuditoria').data("DateTimePicker").setMinDate(e.date);
	        $(this).data('DateTimePicker').hide();
		});
		$("#dateOutAuditoria").on("dp.change",function (e) {
	        $('#dateInAuditoria').data("DateTimePicker").setMaxDate(e.date);
	        $(this).data('DateTimePicker').hide();
		});
		
	}, 500);

   	angular.element('body').removeClass('loading');
    		
});