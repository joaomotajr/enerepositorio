app.filter('alarmFilter', function () {
    return function (objects, criteria) {
        var filterResult = new Array();

        if (!criteria)
            return null;            
        else if (criteria.uid == 0)
        	return objects;
        else if (criteria.uid == 5) {        	
        	for (index in objects) {
           		 if(objects[index].value >= criteria.alarm1) {
                    filterResult.push(objects[index]);
           		 }
        	}
        }
        else if (criteria.uid == 3) {            	
        	for (index in objects) {
           		 if(objects[index].value >= criteria.alarm3) {
                    filterResult.push(objects[index]);
           		 }
        	}
        }
        else if (criteria.uid == 2) {            	
        	for (index in objects) {
           		 if(objects[index].value >= criteria.alarm2 && objects[index].value < criteria.alarm3 ) {
                    filterResult.push(objects[index]);
           		 }
        	}
        }
        else if (criteria.uid == 1) {            	
        	for (index in objects) {
           		 if(objects[index].value >= criteria.alarm1 && objects[index].value < criteria.alarm2) {
                    filterResult.push(objects[index]);
           		 }
        	}
        }
        return filterResult;
    }
});

app.controller('logHistoricController', function ($scope, $timeout, $filter, CompanyService, DetectorService, CompanyDetectorService, HistoricService, CompanyDetectorAlarmService, ViewService) {

	var loadGoogleCharts = false;
	
	$scope.buttonClick = function (s) { 
		$scope.selectedButton = s 
	}
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.msg = msg;
        $('#result').hide().show('slow').delay(500).hide('slow');
	}
	
	$scope.clearHistoric = function() {
		
		$scope.selectedCompany = '';
        $scope.selectedCompanyDetector = '';
        $scope.findedCompanyDetector = '';
        $scope.selectedCompanySensor = undefined;        
        $scope.listHistoricInterval = undefined;
        $scope.dateIn = undefined;
        $scope.dateOut = undefined;
        			
	}		
	
	$scope.getHistorics = function(interval) {
		
		$scope.loading = true;
		
		$scope.selectedPeriodo = setInterval(interval);
		$scope.selectedButton = interval; 
		
		
		$scope.listHistoricInterval = new ViewService.listInterval();		
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, sensorId: $scope.selectedCompanySensor.uid, interval: interval }, function(){
			
			$scope.loading = false;
			
			if($scope.listHistoricInterval != null && $scope.listHistoricInterval.list.length > 0) 
				$(function() { $('#btnSelDevice').click(); })  	
       	
       });		
	}	
	
	$scope.getLastMonth = function() {
		
		$scope.loading = true;
		$scope.selectedPeriodo = setInterval('mes');
		$scope.selectedButton = 30; 
		
		$scope.listHistoricInterval = new ViewService.listLastMonth();		
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, sensorId: $scope.selectedCompanySensor.uid }, function(){
			
			$scope.loading = false;
			
			if($scope.listHistoricInterval != null && $scope.listHistoricInterval.list.length > 0) 
				$(function() { $('#btnSelDevice').click(); })     	
       	
       });		
	}
	
	$scope.getHistoricInterval = function() {
		
		$scope.loading = true;
		
		var dataInicio = new Date($('#dateIn').data().date);
		var dataFim = new Date($('#dateOut').data().date);

		$scope.selectedPeriodo = dataInicio.toLocaleString() + ' à ' + dataFim.toLocaleString();
		
		$scope.selectedButton = 100; 
		
		$scope.listHistoricInterval = new ViewService.listIntervalDays();		
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(),			
			companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, 
			sensorId: $scope.selectedCompanySensor.uid,
			dateIn: dataInicio,
			dateOut: dataFim
		}, function(){
			
			$scope.loading = false;
			
			if($scope.listHistoricInterval != null && $scope.listHistoricInterval.list.length > 0) 
				$(function() { $('#btnSelDevice').click(); })     	
       });		
	}		
		
	function setInterval(interval) {
		
		if ( interval == 1 )
			return "Última Hora";
		else if ( interval == 6 )
			return "Últimas Seis Horas";
		else if ( interval == 12 )
			return "Últimas Doze Horas";
		else if ( interval == 48 )
			return "Últimos Dois Dias";
		else if ( interval == 96 )
			return "Últimos Quatro Dias";
		else if ( interval == 'mes' )
			return "Último Mês";
		else 
			return 'Desconhecido';
				
	}
	
	$scope.getCompanyDetectors = function() {
		 
		 $scope.listAllDashCompany = new ViewService.listAllDashCompany();		 
		 $scope.listAllDashCompany.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.CompanyDetectors = $scope.listAllDashCompany.list; 
			 console.log($scope.listAllDashCompany);		         	         	
        });		 
	 }
	
	$scope.changeCompanyDetector = function() {
		
		$scope.selectedSensorAlarm = undefined;
		$scope.selectedCompanySensor = undefined;
		
		if($scope.selectedCompanyDetector == null) return;
		
		$scope.getOneDetector($scope.selectedCompanyDetector.detector_id);
		
		$scope.resultCompanyDetectorAlarm = new CompanyDetectorAlarmService.listPorCompanyDetectorAlarm();		 
		$scope.resultCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.companyDetectorId}, function(){			
			$scope.selectedCompanyDetectorAlarms = $scope.resultCompanyDetectorAlarm.list;
        });		 
	}
	
	$scope.changeSensor = function() {
		
		if($scope.selectedCompanySensor == null) return;
		
		var detectorAlarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(function (i) { return i.sensorId === $scope.selectedCompanySensor.uid});				
		if (detectorAlarmIndex >= 0) {			
			$scope.selectedSensorAlarm = $scope.selectedCompanyDetectorAlarms[detectorAlarmIndex].alarmDto ;
						
			$scope.filterAlarm = 
				 [
				  	{ name : 'TODO HISTORICO', alarm1: null, alarm2: null, alarm3: null, uid : 0 },				  	
				  	{ name : 'DETECCAO', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: $scope.selectedSensorAlarm.alarm2, alarm3: $scope.selectedSensorAlarm.alarm3, uid : 1 },
				  	{ name : 'ALERTA', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: $scope.selectedSensorAlarm.alarm2, alarm3: $scope.selectedSensorAlarm.alarm3, uid : 2 },
				  	{ name : 'EVACUACAO', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: $scope.selectedSensorAlarm.alarm2, alarm3: $scope.selectedSensorAlarm.alarm3, uid : 3 },
				  	{ name : 'TODOS ALARMES', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: null, alarm3: null,  uid :  5 },
				 ];
			
			 $scope.selectedfilterAlarm = $scope.filterAlarm[0];			
		}
		else {
			$scope.selectedSensorAlarm = undefined;
	     	$scope.listHistoricInterval = undefined;
		}	
	}

	$scope.getCompanys = function() {
		 
		 $scope.resultCompanies = new CompanyService.listAll();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companies = $scope.resultCompanies.list;
        });		 
	}
	
	$scope.changeCompany = function() { 
		$scope.selectedSensorAlarm = undefined;
		$scope.selectedCompanySensor = undefined;
		
		if($scope.selectedCompany == null) return;
		
		$scope.search = {company: $scope.selectedCompany};
	}
	
	$scope.getOneDetector = function(detectorId) {
		 
		 $scope.listOne = new DetectorService.listOne();		 
		 $scope.listOne.$detector({_csrf : angular.element('#_csrf').val(), id : detectorId}, function(){			
			 $scope.findedCompanyDetector = $scope.listOne.t;
        	         	
        });			 
	}
	
	$scope.showGrafico = function(detectorId) {
		
		if(! loadGoogleCharts) {				
			google.charts.load('current', { 'packages': ['corechart', 'line'] });				
			loadGoogleCharts = true;
		}
		
		google.charts.setOnLoadCallback(formatLineSensor);	
		
		$timeout(function () {
			$('#modalGraficoHistorico').modal({ show: 'false' });
		}, 500);
	}
	
	function formatLineSensor() {
		var value = $scope.listHistoricInterval.list;
		
		var red =     $scope.selectedSensorAlarm == null  ? 0 :  $scope.selectedSensorAlarm.alarm3;
		var yellow =  $scope.selectedSensorAlarm == null  ? 0 :  $scope.selectedSensorAlarm.alarm2;
		var orange =  $scope.selectedSensorAlarm == null  ? 0 :  $scope.selectedSensorAlarm.alarm1;
		
		var data = new google.visualization.DataTable();
	      
	    data.addColumn('string', 'Data');
	    data.addColumn('number', 'Valor');    

	    var itens = new Array();
	    	    
	    for(var i in value) {
	    	var itemDate = new Date( value[i].last_update );

	    	changeDate = weekday[itemDate.getDay()] + ' ' + itemDate.toLocaleDateString() + ' as ' + itemDate.toLocaleTimeString();
	    	valor = value[i].value;
	    	
	    	itens.push([changeDate, valor]);
		}
	      
	    data.addRows(itens);

	    var options = {
          
	          legend: {position: 'none'},
	          width: 900,
	          height: 400,
	    	  hAxis: {
	    		  gridlines: {color: '#333', count: 4},
	    		  baselineColor: '#fbf6a7',
	    		  textPosition: 'none'	    	  
	    	  },
	    	  vAxis: {	    		  
	    		  maxValue: $scope.selectedCompanySensor.rangeMax,
	              minValue: 0,
	    		  textStyle: {
                      'color': '#8C8C8C',
                      'fontName': 'Calibri',
                      'fontSize': 12,
                  },
	    		  ticks: [{v:0, f: 'Range Minimo: 0' }, {v: orange, f: 'Detecção: ' + orange}, {v: yellow, f: 'Alerta: ' + yellow}, {v: red, f: 'Evacuação: ' + red}, {v: $scope.selectedCompanySensor.rangeMax, f: 'Range Máximo: ' + $scope.selectedCompanySensor.rangeMax} ]
	    	  },
	    	  curveType: 'function',
	          pointSize:1
	      };
	    objChart = document.getElementById("graficoHistorico");
	    var chart = new google.visualization.LineChart(objChart);
	    chart.draw(data, options);
	
	}
	
	$('dateIn').on('changeDate', function(ev){
	    $(this).datepicker('hide');
	});
		
	$scope.clearHistoric();
	$scope.getCompanys();
	$scope.getCompanyDetectors();
	$scope.tipoGrupo = 1;
	
	$timeout(function(){
		angular.element('body').removeClass('loading');
		
		$('#dateIn').datetimepicker(
			{
				defaultDate: new Date(),
				format:'DD/MM/YYYY HH:mm:ss'
			}
		);
		$('#dateOut').datetimepicker(
			{
				defaultDate: new Date(),
				format:'DD/MM/YYYY HH:mm:ss'
			}
		);
		
		$("#dateIn").on("dp.change",function (e) {
	        //jQuery('#dateOut').data("DateTimePicker").setMinDate(e.date);
		});
		$("#dateOut").on("dp.change",function (e) {
	        //jQuery('#dateIn').data("DateTimePicker").setMaxDate(e.date);
		});
		
	}, 1000);
	
	
	
});