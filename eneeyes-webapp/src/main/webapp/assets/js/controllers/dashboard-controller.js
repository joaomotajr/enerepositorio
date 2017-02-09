
app.controller('dashController', function ($scope, $timeout, $filter, CompanyDetectorService, HistoricService, CompanyDetectorAlarmService) {
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.msg = msg;
        $('#result').hide().show('slow').delay(500).hide('slow');
	}
	
	$scope.clearHistoric = function() {
			
		$scope.companyValor = '';
        $scope.selectedCompanyDetector = '';
        $scope.selectedCompanySensor = '';
			
	}
	
	$scope.saveHistoric = function() {
		
		$scope.historic = {
				uid: 0,	
				value: $scope.companyValor,
				lastUpdate: null,
				companyDetectorDto: {uid: $scope.selectedCompanyDetector.uid},
				sensorDto: {uid: $scope.selectedCompanySensor.uid}
			 }	
		 
		 $scope.inclusao = new HistoricService.save($scope.historic);		 
		 $scope.inclusao.$historic({_csrf : angular.element('#_csrf').val()}, function(){         	
        	        	
        	$scope.showInfo('Salvo');        	
        	$scope.companyValor = '';

        });		 
	 }
	
	$scope.getHistoric = function() {
		$scope.listHistoric = new HistoricService.listAll();		 
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val()}, function(){         	
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getHistorics2 = function(interval) {
		
		$scope.listHistoric = new HistoricService.listInterval2();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid, sensorId: $scope.selectedCompanySensor.uid, interval: interval }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getHistorics = function(interval) {
		
		$scope.listHistoric = new HistoricService.listInterval();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid, interval: interval }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getLastMonth = function() {
		$scope.listHistoric = new HistoricService.listLastMonth();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
		
	$scope.getLastMonth2 = function() {
		$scope.listHistoric = new HistoricService.listLastMonth2();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid, sensorId: $scope.selectedCompanySensor.uid }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getHistoricInterval = function() {
		
		var dataInicio = new Date(getDate($scope.dateIn));
		var dataFim = new Date(getDate($scope.dateOut, true));
		
		$scope.listHistoric = new HistoricService.listIntervalDays();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(),			
			companyDetectorId: $scope.selectedCompanyDetector.uid, 
			sensorId: $scope.selectedCompanySensor.uid,
			dateIn: dataInicio,
			dateOut: dataFim
		}, function(){
			       	
			console.log($scope.listHistoric);      	
       });		
	}		
		
	$scope.getCompanyDetectors = function() {
		 
		 $scope.listAll = new CompanyDetectorService.listAll();		 
		 $scope.listAll.$companyDetector({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.CompanyDetectors = $scope.listAll.list; 
			 console.log($scope.listAll);		         	         	
        });		 
	 }
	
	$scope.listCompanyDetectors = function() {
		$scope.getCompanyDetectors();
		$scope.listCompanyDetectors = angular.copy($scope.CompanyDetectors);     		 
	}	
	
	$scope.changeCompanyDetector = function() {
		
		$scope.selectedSensorAlarm = undefined;
		$scope.selectedCompanySensor = undefined;
		
		if($scope.selectedCompanyDetector == null) return;
		
		$scope.resultCompanyDetectorAlarm = new CompanyDetectorAlarmService.listPorCompanyDetectorAlarm();		 
		$scope.resultCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid}, function(){			
			$scope.selectedCompanyDetectorAlarms = $scope.resultCompanyDetectorAlarm.list;
        });		 
	}
	
	$scope.changeSensor = function() {
		
		if($scope.selectedCompanySensor == null) return;
		
		var detectorAlarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(function (i) { return i.sensorId === $scope.selectedCompanySensor.uid});				
		if (detectorAlarmIndex >= 0) {			
			$scope.selectedSensorAlarm = $scope.selectedCompanyDetectorAlarms[detectorAlarmIndex].alarmDto ;
		}
		
	}
		
	$scope.getCompanyDetectors();
	
	angular.element('body').removeClass('loading');		
	
});