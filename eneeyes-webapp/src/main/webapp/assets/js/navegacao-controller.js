
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

app.factory('SetParentService', function($resource){
    return {	        
    setParent :  $resource('/security/api/unit/setparent/:id/:parentid/',  {id: '@id', parentid: '@parentid'}, {},
    		{query: {method: 'GET', isArray:true}})	    
    };
});

app.controller('navegacaoController', function ($scope, $timeout, $filter, AreaService, UnitService, SetParentService) {
		
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
		longitude: 9.232323
		//, parent : {uid: 1, name: "Teste" }				
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
	 
	 $scope.saveUnit = function() {
		 
		 $scope.inclusaoUnit = new UnitService.save($scope.unit);
		 $scope.inclusaoUnit.$unit({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusaoUnit);         	
         });
		 
	 }
	 
	 $scope.setUnit2 = function() {
		 
		 $scope.setParent = new UnitService.setParent($scope.unit);
		 $scope.setParent.$unit({_csrf : angular.element('#_csrf').val(), id : 2, parentid: 1} , function(){         	
         	console.log($scope.setParent);         	
         });
		 
	 }
	 
	 $scope.setUnit = function () {
		 
		$scope.val = SetParentService.setParent.query({_csrf : angular.element('#_csrf').val()}, {id : 2, parentid: 1}, function(result) {
			if(result.resultType == "SUCCESS"){
				window.location.href='/';
			}
			console.log(result);
	    }, function(data) {
			 if (data.status >= 400 && data.status <= 505 ) {
				 
			 }
		 });
	}	 
	
});