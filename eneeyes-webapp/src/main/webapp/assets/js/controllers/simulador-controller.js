app.controller('simuladorController', function ($scope, $timeout, $filter, CompanyService, HistoricService, ViewService, CompanyDeviceService) {
	
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
				companyDeviceId: $scope.selectedCompanyDetector.companyDeviceId,
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
		
		if($scope.selectedCompanyDetector == null) return;
		$scope.getCompanyDevice($scope.selectedCompanyDetector.companyDeviceId);
	};	
	
	$scope.getCompanys = function() {
	
		$scope.resultCompanies = new CompanyService.listAllView();		 
			$scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.companies = $scope.resultCompanies.list;
		});
   	};

	$scope.getCompanyDevice = function(uid) {
		$scope.resultCompanyDevice = new CompanyDeviceService.listOne();
		$scope.resultCompanyDevice.$companyDevice({_csrf : angular.element('#_csrf').val(), id : uid }, function(){						
			if (!$scope.resultCompanyDevice.isError) {
				$scope.findedCompanyDevice = $scope.resultCompanyDevice.t;			
			}			
		});
	};

	$scope.changeCompany = function() { 		
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