app.controller('logHistoricController', function ($scope, $timeout, $filter, CompanyService, DetectorService, CompanyDetectorService, HistoricService, CompanyDetectorAlarmService, ViewService) {
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.msg = msg;
        $('#result').hide().show('slow').delay(500).hide('slow');
	}
	
	$scope.clearHistoric = function() {
		
		$scope.selectedCompany = '';
        $scope.selectedCompanyDetector = '';
        $scope.findedCompanyDetector = '';
        $scope.selectedCompanySensor = '';
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
	
	$scope.getCompanys();
	$scope.getCompanyDetectors();	
	
});