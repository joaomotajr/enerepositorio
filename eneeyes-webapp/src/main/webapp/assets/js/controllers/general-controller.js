
app.controller('generalController', function ($scope, $timeout, $filter, CompanyService, UnitService) {

	$scope.selectCompany = function(index) {
		
		$scope.selectedCompany = $scope.companiesSumary[index];
		$scope.selectedCompany.selected = index;

		 $scope.listOne = new UnitService.listOneByCompanyId();		 
		 $scope.listOne.$unit({_csrf : angular.element('#_csrf').val(), companyId : $scope.selectedCompany.uid}, function(){			
			$scope.selectedUnits = true; 
			$scope.selectedUnit = undefined; 
			$scope.companyComplete = $scope.listOne.list; 				         	         	
        });		 
	}		

	$scope.getCompaniesSumary = function() {
		 
		 $scope.resultCompanies = new CompanyService.listAllSumaryView();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companiesSumary = $scope.resultCompanies.list;
        });		 
	}

	$scope.selectUnit = function(index) {
		$scope.selectedUnits = undefined; 
		$scope.selectedAreas = true;
		$scope.selectedUnit = $scope.companyComplete[index];	
	}

	$scope.closeSelectedUnit = function() {
		$scope.selectedUnits = true;  
		$scope.selectedUnit = undefined; 
	}

	$scope.selectArea = function(index) {
		$scope.selectedAreas = undefined;
		$scope.selectedArea = $scope.selectedUnit.areasDto[index];	
	}

	$scope.closeSelectedArea = function() {		
		$scope.selectedArea = undefined; 
		$scope.selectedAreas = true;
	}
		
	$scope.getCompaniesSumary();	
	
});