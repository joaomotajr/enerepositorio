
app.controller('CompanyDetectorMaintenanceHistoricController', function ($scope, $timeout, $filter, ViewService, 
		CompanyService, CompanyDetectorMaintenanceHistoricService) {
		
	$scope.changeCompanyDetector = function() {
		
		if($scope.selectedCompanyDetector == null) return;
		$scope.getCompanyDetectorMaintenanceHistoric();
				 
	}
	
	$scope.getCompanyDetectorMaintenanceHistoric = function() {		 
		angular.element('body').addClass('loading');		
		$scope.companyDetectorMaintenanceHistoric = new CompanyDetectorMaintenanceHistoricService.listPorCompanyDetector();		
		$scope.companyDetectorMaintenanceHistoric.$companyDetectorMaintenanceHistoric(
				{_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.companyDetectorId}, function(){
						
			angular.element('body').removeClass('loading');	                 	         	
		});		 
	}
	
	$scope.changeCompany = function() { 
			
		if($scope.selectedCompany == null) return;
		
		$scope.search = {company: $scope.selectedCompany};
	}
	
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.selectedCompany = $scope.listOne.t;
			 
			 $scope.companyUid = $scope.selectedCompany.uid;
			 $scope.companyName = $scope.selectedCompany.name;
			 $scope.companyDescription = $scope.selectedCompany.description;
			 
			 $scope.search = {company: $scope.selectedCompany};
					 			 
	    });		 
	}
	
	$scope.getCompanyDetectors = function() {
				 
		 $scope.listAllDashCompany = new ViewService.listAllDashCompany();		 
		 $scope.listAllDashCompany.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.companyDetectors = $scope.listAllDashCompany.list; 				         	         	
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
				 $scope.getCompanyDetectors();
	        });		 
		 }
	 }
	
	$scope.historicMaintenaceTypes = 
		 [
		  	{ name : 'DESCONHECIDO', uid : 0 },
		  	{ name : 'MAINTENANCE', uid :  1 },
		  	{ name : 'CALIBRATION', uid : 2 },		  			  	
		 ]; 
	
	$scope.getHistoricMaintenaceTypes = function (name) {
		 
		 for (var i = 0; i < $scope.historicMaintenaceTypes.length; i++) {
            if ($scope.historicMaintenaceTypes[i].name == name) {
                
           	 return $scope.historicMaintenaceTypes[i];
            }
        } 		 
	 }
	
	
	$scope.getCompanys();	
	$scope.getCompanyDetectors();
		
	angular.element('body').removeClass('loading');	
});