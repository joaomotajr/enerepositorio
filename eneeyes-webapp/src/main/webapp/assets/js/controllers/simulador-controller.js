app.controller('simuladorController', function ($scope, $timeout, $filter, CompanyService, CompanyDetectorService, DetectorService, HistoricService, CompanyDetectorAlarmService, ViewService) {
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.msg = msg;
        $('#result').hide().show('fast').delay(500).hide('fast');
	};
	
	$scope.clearHistoric = function() {
			
		$scope.valor = '';
		$scope.selectedCompany = '';
        $scope.selectedCompanyDetector = '';
        $scope.selectedSensorAlarm = ''			
	};
	
	$scope.saveHistoric = function() {
		
		$scope.historic = {
				uid: 0,	
				value: $scope.valor,
				lastUpdate: null,
				companyDetectorId: $scope.selectedCompanyDetector.companyDetectorId,
				logOrigem: 'MANUAL'
			 }	
		 
		 $scope.inclusao = new HistoricService.save($scope.historic);		 
		 $scope.inclusao.$historic({_csrf : angular.element('#_csrf').val()}, function(){         	
        	        	
        	$scope.showInfo('Salvo');        	
        	$scope.valor = '';

        });		 
	 };	
	
	$scope.getCompanyDetectors = function() {
		 
		 $scope.listAllDashCompany = new ViewService.listAllDashCompany();		 
		 $scope.listAllDashCompany.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.CompanyDetectors = $scope.listAllDashCompany.list;        	         	
       });		 
	 };
	
	$scope.changeCompanyDetector = function() {
		
		$scope.selectedSensorAlarm = undefined;
		
		if($scope.selectedCompanyDetector == null) return;
		
		$scope.getOneCompanyDetector($scope.selectedCompanyDetector.companyDetectorId);
	};	
	
	$scope.getCompanys = function() {
		 
		 $scope.resultCompanies = new CompanyService.listAll();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companies = $scope.resultCompanies.list;
       });		 
	};

	$scope.getOneCompanyDetector = function(uid) {
						
		$scope.resultCompanyDetector = new CompanyDetectorService.listOne();		 
		$scope.resultCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val(), id : uid }, function(){			
			
			$scope.findedCompanyDetector = $scope.resultCompanyDetector.t;						
		});		 
	};

	$scope.changeCompany = function() { 
		$scope.selectedSensorAlarm = undefined;
		
		if($scope.selectedCompany == null) return;
		
		$scope.search = {company: $scope.selectedCompany};
	};
	
	$scope.clearHistoric();
	$scope.getCompanyDetectors();
	$scope.getCompanys();
	
	$timeout(function(){
		angular.element('body').removeClass('loading');
	},1500);
	  	
});