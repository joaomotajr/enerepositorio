
app.factory('AreaService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/area/delete',{},{
            area : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/area/all',{},{
            area : {method : 'GET'}
        }),
        listOne : $resource('/security/api/area/obtemPorId',{},{
            area : {method : 'GET'}
        }),
        save : $resource('/security/api/area/save',{},{
            area : {method : 'POST'}
        }),
     };
});



app.factory('CompanyService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/company/delete',{},{
    		company : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/company/all',{},{
        	company : {method : 'GET'}
        }),
        listOne : $resource('/security/api/company/obtemPorId',{},{
        	company : {method : 'GET'}
        }),
        save : $resource('/security/api/company/save',{},{
        	company : {method : 'POST'}
        }),
     };
});

app.controller('navegacaoController', function ($scope, $timeout, $filter, AreaService, CompanyService) {
		
	$('#treeview-searchable').treeview({
	     data: getTree(),
	 });
	
	 search = function (e) {
	     var pattern = $('#input-search').val();
	     var options = {
	         ignoreCase: true,
	         exactMatch: false,
	         revealResults: true
	     };

	     var results = $searchableTree.treeview('search', [pattern, options]);
	    
	 }
	 
	 $scope.area = {
		uid: 0,
		name: "Teste",
		description: "Teste",
		local: "Teste",	
		latitude: 12.2345545,		
		longitude: 9.232323,
		classified: true,
		date: null,
		companyDto: {uid: 1}
	 }
	 
	 $scope.company = {
		uid: 0,
		name: "Teste",
		email: null,
		url: null,
		phone: null,
		mobile: null,
		address: null,
		city: null,
		state: null,
		zip: null,
		unitType: 1,
		date: null,
		latitude: 12.2345545,		
		longitude: 9.232323,				
		parent : {uid: 1, name: "Teste" }				
	 }	 
	 
	 $scope.save = function() {
		 
		 $scope.inclusao = new AreaService.save($scope.area);		 
		 $scope.inclusao.$area({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusao);         	
         });
		 
	 }
	 
	 $scope.get = function() {
		 
		 $scope.listAll = new AreaService.listAll();		 
		 $scope.listAll.$area({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOne = function() {
		 
		 $scope.listOne = new AreaService.listOne("1");		 
		 $scope.listOne.$area({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);
         	         	
         });
		 
	 }
	 
	 $scope.saveCompany = function() {
		 
		 $scope.inclusaoCompany = new CompanyService.save($scope.company);		 
		 $scope.inclusaoCompany.$company({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusaoCompany);         	
         });
		 
	 }
	
});