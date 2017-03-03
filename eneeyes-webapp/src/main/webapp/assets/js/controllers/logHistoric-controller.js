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
        $scope.dateIn = undefined;
        $scope.dateOut = undefined;
        			
	}		
	
	$scope.getHistorics = function(interval) {
		
		$scope.loading = true;
		
		$scope.listHistoricInterval = new ViewService.listInterval();		
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, sensorId: $scope.selectedCompanySensor.uid, interval: interval }, function(){
			
			$scope.loading = false;
			console.log($scope.listHistoric);      	
       	
       });		
	}	
	
	$scope.getLastMonth = function() {
		
		$scope.loading = true;
		
		$scope.listHistoricInterval = new ViewService.listLastMonth();		
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, sensorId: $scope.selectedCompanySensor.uid }, function(){
			
			$scope.loading = false;
			console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getHistoricInterval = function() {
		
		$scope.loading = true;
		
		var dataInicio = new Date(getDate($scope.dateIn));
		var dataFim = new Date(getDate($scope.dateOut, true));
		
		$scope.listHistoricInterval = new ViewService.listIntervalDays();		
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(),			
			companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId, 
			sensorId: $scope.selectedCompanySensor.uid,
			dateIn: dataInicio,
			dateOut: dataFim
		}, function(){
			
			$scope.loading = false;
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
				  	{ name : 'DETECCAO', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: $scope.selectedSensorAlarm.alarm2, alarm3: $scope.selectedSensorAlarm.alarm3, uid : 1 },
				  	{ name : 'ALERTA', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: $scope.selectedSensorAlarm.alarm2, alarm3: $scope.selectedSensorAlarm.alarm3, uid : 2 },
				  	{ name : 'EVACUACAO', alarm1: $scope.selectedSensorAlarm.alarm1, alarm2: $scope.selectedSensorAlarm.alarm2, alarm3: $scope.selectedSensorAlarm.alarm3, uid : 3 },
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
          
	          legend: {position: 'none'},
	          width: 800,
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
	
	$timeout(function(){
		angular.element('body').removeClass('loading');
	}, 1000);
	
	
	
});