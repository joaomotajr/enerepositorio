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
        $scope.listHistoric = undefined;
        $scope.listHistoricInterval = undefined;
        			
	}		
	
	$scope.getHistorics2 = function(interval) {
		
		$scope.listHistoric = new HistoricService.listInterval2();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, sensorId: $scope.selectedCompanySensor.uid, interval: interval }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getHistorics = function(interval) {
		
		$scope.listHistoric = new HistoricService.listInterval();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, interval: interval }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getLastMonth = function() {
		$scope.listHistoric = new HistoricService.listLastMonth();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
		
	$scope.getLastMonth2 = function() {
		$scope.listHistoric = new HistoricService.listLastMonth2();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, sensorId: $scope.selectedCompanySensor.uid }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getHistoricInterval = function() {
		
		var dataInicio = new Date(getDate($scope.dateIn));
		var dataFim = new Date(getDate($scope.dateOut, true));
		
		$scope.listHistoricInterval = new HistoricService.listIntervalDays();		
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(),			
			companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, 
			sensorId: $scope.selectedCompanySensor.uid,
			dateIn: dataInicio,
			dateOut: dataFim
		}, function(){
			       	
			console.log($scope.listHistoricInterval);      	
       });		
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
				  	{ name : 'ALARME 1', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: $scope.selectedSensorAlarm.alarm2, alarm3: $scope.selectedSensorAlarm.alarm3, uid : 1 },
				  	{ name : 'ALARME 2', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: $scope.selectedSensorAlarm.alarm2, alarm3: $scope.selectedSensorAlarm.alarm3, uid : 2 },
				  	{ name : 'ALARME 3', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: $scope.selectedSensorAlarm.alarm2, alarm3: $scope.selectedSensorAlarm.alarm3, uid : 3 },
				  	{ name : 'TODOS ALARMES', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: null, alarm3: null,  uid :  5 },
				 ];
			
			 $scope.selectedfilterAlarm = $scope.filterAlarm[0];			
		}
		else {
			$scope.selectedSensorAlarm = undefined;
			$scope.listHistoric = undefined;
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
	    data.addColumn('number', 'Valores');    

	    var itens = new Array();
	    	    
	    for(var i in value) {
	    	var itemDate = new Date(value[i].lastUpdate);

	    	changeDate = itemDate.toLocaleDateString();
	    	valor = value[i].value;
	    	
	    	itens.push([changeDate, valor]);
		}
	      
	    data.addRows(itens);

	    var options = {
	          title: "Dados Recentes dos Detectores.",
	          titleTextStyle: { color: '#FF0000' },
	          //legend: { position: 'none' },
	          width: 800,
	          height: 400,
	    	  hAxis: {
	    		  title: 'Valores',
	    		  gridlines: {color: '#333', count: 4},
	    		  baselineColor: '#fbf6a7',
	    		  textPosition: 'none'	    	  },
	    	  vAxis: {
	    		  title: 'Alarmes',
	    		  count: 5,
	    		  titleTextStyle: { color: '#FF0000' },
	    		  maxValue:$scope.selectedCompanySensor.rangeMax,
	              minValue:0,
	    		  ticks: [0, orange, yellow, red, $scope.selectedCompanySensor.rangeMax],	    		  
	    	  },        
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
	
});