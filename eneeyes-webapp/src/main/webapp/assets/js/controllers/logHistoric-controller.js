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
	
	$scope.countHistoric = 0;
	
	function printData()
	{
	
	   var divToPrint = document.getElementById("printTable");
	   	   
	   divToPrint.style.visibility = "visible";
	   
	   newWin= window.open("");
	   newWin.document.write(divToPrint.outerHTML);
	   newWin.print();
	   newWin.close();
	   
	   divToPrint.style.visibility = "hidden";
	}

	$('#exportRel').on('click',function(){
		printData();
	})	
	
	$("#exportExcel").click(function(e) {
	    window.open('data:application/vnd.ms-excel,' + encodeURIComponent( $('div[id$=dvData]').html()));
	    e.preventDefault();
	});
	
	 	
	$scope.buttonClick = function (s) { 
		$scope.selectedButton = s 
	}
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.msg = msg;
        $('#result').hide().show('slow').delay(500).hide('slow');
	}
	
	$scope.clearTipoGrupo = function(grupo) {
		$scope.listHistoricInterval = undefined;
	}
	
	$scope.clearHistoric = function() {
		
		$scope.selectedCompany = '';
        $scope.selectedCompanyDetector = '';
        $scope.findedCompanyDetector = '';
        $scope.selectedCompanySensor = undefined;        
        $scope.listHistoricInterval = undefined;
        $scope.dateIn = undefined;
        $scope.dateOut = undefined;
        $scope.tipoGrupo = 1;
        			
	}		
	
	$scope.getHistorics = function(interval) {
		
		$scope.loading = true;
		
		$scope.selectedPeriodo = setInterval(interval);
		$scope.selectedButton = interval; 
		
		if($scope.tipoGrupo == 1)
			$scope.listHistoricInterval = new ViewService.listInterval();
		else if($scope.tipoGrupo == 2)
			$scope.listHistoricInterval = new ViewService.listIntervalGroupHours();
		else if($scope.tipoGrupo == 3)
			$scope.listHistoricInterval = new ViewService.listIntervalGroupDays();		
				
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, sensorId: $scope.selectedCompanySensor.uid, interval: interval }, function(){
			
			$scope.loading = false;
			$scope.countHistoric = padZeros($scope.listHistoricInterval.list.length,5);
			
			if($scope.listHistoricInterval != null && $scope.listHistoricInterval.list.length > 0 && ! $('#btnSelDevice').children('i').hasClass('fa-plus')) 
				$(function() { $('#btnSelDevice').click(); })  	
       	
       });		
	}	
	
	$scope.getLastMonth = function() {
		
		$scope.loading = true;
		$scope.selectedPeriodo = setInterval('mes');
		$scope.selectedButton = 30; 
		
		if($scope.tipoGrupo == 1) {
			//$scope.listHistoricInterval = new ViewService.listLastMonth();
			
			$scope.daysDiff ="ATENÇÃO: Esta Pesquisa NÃO Pode Exceder 15 dias " ;
			
			$("#snoAlertBox").fadeIn();
			window.setTimeout(function () { $("#snoAlertBox").fadeOut(300) }, 3000);
			
			return;
		}	
		else if($scope.tipoGrupo == 2)
			$scope.listHistoricInterval = new ViewService.listLastMonthGroupHours();
		else if($scope.tipoGrupo == 3)
			$scope.listHistoricInterval = new ViewService.listLastMonthGroupDays();
		
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, sensorId: $scope.selectedCompanySensor.uid }, function(){
			
			$scope.loading = false;
			$scope.countHistoric = padZeros($scope.listHistoricInterval.list.length,5);
			
			if($scope.listHistoricInterval != null && $scope.listHistoricInterval.list.length > 0 && ! $('#btnSelDevice').children('i').hasClass('fa-plus'))
				$(function() { $('#btnSelDevice').click(); })     	
       	
       });		
	}
	
	function checkInterval (dataInicio, dataFim ) {
		
		daysExceed = false;
		
		if($scope.tipoGrupo == 1 && dayDiff(dataInicio, dataFim) > 15 ) {
			
			$scope.daysDiff ="ATENÇÃO: Esta Pesquisa NÃO Pode Exceder 15 dias " ;
			
			$("#snoAlertBox").fadeIn();
			window.setTimeout(function () { $("#snoAlertBox").fadeOut(300)}, 3000);
			
			daysExceed = true;
		}		
		else if($scope.tipoGrupo == 2 && dayDiff(dataInicio, dataFim) > 360 ) {
			
			$scope.daysDiff ="ATENÇÃO: Esta Pesquisa NÃO Pode Exceder 360 dias " ;
			
			$("#snoAlertBox").fadeIn();
			
			window.setTimeout(function () { $("#snoAlertBox").fadeOut(300)}, 3000);
			
			daysExceed = true;
		}	
		
		return daysExceed;
	}
		
	$scope.getHistoricInterval = function() {
		
		var dataInicio = new Date($('#dateIn').data().DateTimePicker.date._d);
		var dataFim = new Date($('#dateOut').data().DateTimePicker.date._d);
				
		if(checkInterval(dataInicio, dataFim)) return;			 

		$scope.loading = true;
		
		$scope.selectedPeriodo = dataInicio.toLocaleString() + ' à ' + dataFim.toLocaleString();
		
		$scope.selectedButton = 100; 		
		
		if($scope.tipoGrupo == 1)			
			$scope.listHistoricInterval = new ViewService.listIntervalDays();
		else if($scope.tipoGrupo == 2)
			$scope.listHistoricInterval = new ViewService.listIntervalDaysGroupHours();
		else if($scope.tipoGrupo == 3)
			$scope.listHistoricInterval = new ViewService.listIntervalDaysGroupDays();
						
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(),			
			companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, 
			sensorId: $scope.selectedCompanySensor.uid,
			dateIn: dataInicio,
			dateOut: dataFim
		}, function(){
			
			$scope.loading = false;
			$scope.countHistoric = padZeros($scope.listHistoricInterval.list.length,5);
			
			if($scope.listHistoricInterval != null && $scope.listHistoricInterval.list.length > 0 && ! $('#btnSelDevice').children('i').hasClass('fa-plus')) 
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
			return "Últimas Dois Dias";
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
			 $scope.companyDetectors = $scope.listAllDashCompany.list; 				         	         	
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
		 
		 $scope.resultCompanies = new CompanyService.listAllView();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companies = $scope.resultCompanies.list;
        });		 
	}
	
	 
	 $scope.getCompanys = function() {
		 
		 if($scope.$root.isFrom != "MASTER")	{
		 
			 $scope.getOneCompany($scope.$root.isFrom);
		 }
		 else {		
			 $scope.resultCompanies = new CompanyService.listAllView();		 
			 	$scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
				 $scope.companies = $scope.resultCompanies.list;
	        });		 
		 }
	 } 
	 
	 $scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.selectedCompany = $scope.listOne.t;
			 $scope.changeCompany();
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
	
	$scope.changedGraphic = function() {
		$scope.count=0;
	}
	
	$scope.showGrafico = function() {
		
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
	      
		if($scope.tipoGrupo == 1) {
			data.addColumn('string', 'Data');
			data.addColumn('number', 'Valor');
		}
		else {
			data.addColumn('string', 'Data');
			data.addColumn('number', 'Mï¿½ximo');			
			data.addColumn('number', 'Minimo');
		}

	    var itens = new Array();
	    	    
	    for(var i in value) {
	    	var itemDate = new Date( value[i].last_update );

	    	changeDate = weekday[itemDate.getDay()] + ' ' + itemDate.toLocaleDateString() + ' as ' + itemDate.toLocaleTimeString();
	    	
	    	if($scope.tipoGrupo == 1) {
	    		itens.push([changeDate, value[i].value]);
	    	}
	    	else {
	    		itens.push([changeDate, value[i].max_value, value[i].min_value]);
	    	}
		}
	      
	    data.addRows(itens);
	    
	    if ($scope.changeGraphic) {
		    var options = {
	          
		          legend: {position: 'none'},
		          width: 900,
		          height: 450,
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
	                      'fontStyle' : 'bold',
	                      'fontSize': 9,
	                  },
		    		  ticks: [
		    		          {v:0, f: 'Range Minimo: 0' }, 
		    		          {v: orange, f: 'Detecï¿½ï¿½o: ' + orange}, 
		    		          {v: yellow, f: 'Alerta: ' + yellow}, 
		    		          {v: red, f: 'Evacuaï¿½ï¿½o: ' + red}, 
		    		          {v: $scope.selectedCompanySensor.rangeMax, f: 'Range Mï¿½ximo: ' + $scope.selectedCompanySensor.rangeMax}
		    		        ],
		    	  },
		    	  //curveType: 'function',
		          pointSize:1
		      };
	    }    
		else
			{
			   var options = {
				          
		          legend: {position: 'none'},
		          width: 900,
		          height: 450,
		    	  hAxis: {
		    		  gridlines: {color: '#333', count: 4},
		    		  baselineColor: '#fbf6a7',
		    		  textPosition: 'none'	    	  
		    	  },
		    	  vAxis: {	    		  
		    		  
		    		  textStyle: {
	                      'color': '#8C8C8C',
	                      'fontName': 'Calibri',
	                      'fontStyle' : 'bold',
	                      'fontSize': 9,
	                  },
		    	  },
		    	  //curveType: 'function',
		          pointSize:1
		      };			
		}
	    
	    objChart = document.getElementById("graficoHistorico");
	    var chart = new google.visualization.LineChart(objChart);
	    chart.draw(data, options);
	
	}	
	
	$('#dateIn').on('changeDate', function(ev){
	    $(this).datepicker('hide');
	});
	
	$scope.changeToValue = function(valor) {
	  return valor.toString().replace(".", ",")
	}

	$scope.clearHistoric();
	$scope.getCompanys();
	$scope.getCompanyDetectors();
	$scope.tipoGrupo = 1;
	
	$timeout(function(){
		angular.element('body').removeClass('loading');
		
		$('#dateIn').datetimepicker(
			{ defaultDate: new Date(), format:'DD/MM/YYYY HH:mm:ss' }
		);
		$('#dateOut').datetimepicker(
			{ defaultDate: new Date(), format:'DD/MM/YYYY HH:mm:ss' }
		);
		
		$("#dateIn").on("dp.change",function (e) {
	        //jQuery('#dateOut').data("DateTimePicker").setMinDate(e.date);
		});
		$("#dateOut").on("dp.change",function (e) {
	        //jQuery('#dateIn').data("DateTimePicker").setMaxDate(e.date);
		});
		
	}, 1000);
	
	
	
});