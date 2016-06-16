
app.factory('AreaService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/area/delete',{},{
            area : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/area/all',{},{
            area : {method : 'GET'}
        }),
        listOne : $resource('/security/api/area/obtemPorId/:id', {id: '@id'},{
            area : {method : 'GET'}
        }),
        save : $resource('/security/api/area/save',{},{
            area : {method : 'POST'}
        }),
     };
});



app.factory('UnitService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/unit/delete',{},{
    		unit : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/unit/all',{},{
        	unit : {method : 'GET'}
        }),
        listOne : $resource('/security/api/unit/obtemPorId',{},{
        	unit : {method : 'GET'}
        }),
        save : $resource('/security/api/unit/save',{},{
        	unit : {method : 'POST'}
        }),
        setParent : $resource('/security/api/unit/setparent/:id/:parentid/', {id: '@id', parentid: '@parentid'}, {
        	unit : {method : 'GET'}
        })
        
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
        listOne : $resource('/security/api/company/obtemPorId/:id', {id: '@id'},{
        	company : {method : 'GET'}
        }),
        save : $resource('/security/api/company/save',{},{
        	company : {method : 'POST'}
        }),
     };
});


//app.factory('SetParentService', function($resource){
//    return {	        
//    setParent :  $resource('/security/api/unit/setparent/:id/:parentid/',  {id: '@id', parentid: '@parentid'}, {},
//    		{query: {method: 'GET', isArray:true}})	    
//    };
//});

app.controller('navegacaoController', function ($scope, $timeout, $filter, AreaService, UnitService, CompanyService) {
		
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
		unitDto: {uid: 1}
	 } 
	 
	 $scope.saveArea = function() {
		 
		 $scope.inclusao = new AreaService.save($scope.area);		 
		 $scope.inclusao.$area({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusao);         	
         });
		 
	 }
	 
	 $scope.getArea = function() {
		 
		 $scope.listAll = new AreaService.listAll();		 
		 $scope.listAll.$area({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneArea = function() {
		 
		 $scope.listOne = new AreaService.listOne();		 
		 $scope.listOne.$area({_csrf : angular.element('#_csrf').val()}, {id : 1}, function(){			
			 console.log($scope.listAll);
         	         	
         });
		 
	 }
	 /*-----------------------------------------------------------------------------------------------------------------*/
	 
	 $scope.unit = {
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
		companyDto: {uid : 1}				
	 }	 
	 

	 $scope.saveUnit = function() {
		 
		 $scope.inclusaoUnit = new UnitService.save($scope.unit);
		 $scope.inclusaoUnit.$unit({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusaoUnit);         	
         });
		 
	 }
	 
	 $scope.getUnit = function() {
		 
		 $scope.listAll = new UnitService.listAll();		 
		 $scope.listAll.$unit({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneUnit = function() {
		 
		 $scope.listOne = new UnitService.listOne();		 
		 $scope.listOne.$unit({_csrf : angular.element('#_csrf').val()}, {id : 1}, function(){			
			 console.log($scope.listAll);
         	         	
         });		 
	 }
	 
	 $scope.setUnit = function() {
		 
		 $scope.setParent = new UnitService.setParent($scope.unit);
		 $scope.setParent.$unit({_csrf : angular.element('#_csrf').val(), id : 2, parentid: 1} , function(){         	
         	console.log($scope.setParent);         	
         });
		 
	 }
	 
	 $scope.company = {
		uid: 0,
		name: "Teste"						
	 }	 
	 

	 $scope.saveCompany = function() {
		 
		 $scope.inclusaoUnit = new CompanyService.save($scope.company);
		 $scope.inclusaoUnit.$company({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusaoUnit);         	
         });
		 
	 }
	 
	 $scope.getCompany = function() {
		 
		 $scope.listAll = new CompanyService.listAll();		 
		 $scope.listAll.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneCompany = function() {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val()}, {id : 1}, function(){			
				 console.log($scope.listAll);
	         	         	
	         });		 
		 }
 
//	 $scope.setUnit = function () {
//		 
//		$scope.val = SetParentService.setParent.query({_csrf : angular.element('#_csrf').val()}, {id : 2, parentid: 1}, function(result) {
//			if(result.resultType == "SUCCESS"){
//				window.location.href='/';
//			}
//			console.log(result);
//	    }, function(data) {
//			 if (data.status >= 400 && data.status <= 505 ) {
//				 
//			 }
//		 });
//	}	 
	
});