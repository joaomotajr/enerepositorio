app.controller('companyController', function ($scope, $timeout, $filter, CompanyService) {
	    
	
	$scope.saveCompany = function() {
		
		var company = {
			uid: $scope.companyUid != undefined ? $scope.companyUid : 0,
			name: $scope.companyName,
			description: $scope.companyDescription		
    	}; 
		 
		$scope.inclusaoCompany = new CompanyService.save(company);		 
		$scope.inclusaoCompany.$company({_csrf : angular.element('#_csrf').val()}, function()
		{
         	
         	$scope.clearFormCompany();
            $scope.getCompanys();
                     	
         });		 
	 }
	 
	$scope.clearFormCompany = function () {
	
	    $scope.companyUid = undefined;
	    $scope.companyName = '';
	    $scope.companyDescription = '';	    
	}
	 
	$scope.getCompanys = function() {
		 
		 $scope.resultCompanies = new CompanyService.listAll();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companies = $scope.resultCompanies.list; 		 			 
         });		 
	 }
 
	 $scope.editCompany = function (index) {
	        $scope.companyUid = $scope.companys[index].uid;
	        
		    $scope.companyName = $scope.companys[index].name;
		    $scope.companyDescription = $scope.companys[index].Description;		    
		    	        
	        $('#idCompanyName').focus();
	    }
	 
	 $scope.deleteCompany = function(index) {
		 
		 var uid = $scope.companys[index].uid;		  
		 
		 $scope.deletar = new CompanyService.deletar();		 
		 $scope.deletar.$company({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 
			 $scope.companys.splice(index, 1);
         	         	
         });
		 
	 }	
	 
	 $scope.getCompanys();
	 $(".select2").select2();

});