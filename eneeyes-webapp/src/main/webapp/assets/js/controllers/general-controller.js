app.controller('generalController', function ($scope, $timeout, $filter, ViewService) {

	$scope.getCompanyDetectors = function() {
		 
		 $scope.listAllDashCompany = new ViewService.listAllDashCompany();		 
		 $scope.listAllDashCompany.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.companyDetectors = $scope.listAllDashCompany.list; 				         	         	
        });		 
	}		
		
	$scope.getCompanyDetectors();
	
	
});