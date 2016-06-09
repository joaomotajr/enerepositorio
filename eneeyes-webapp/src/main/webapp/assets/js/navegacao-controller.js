
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


app.controller('navegacaoController', function ($scope, $timeout, $filter, AreaService) {
		
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
	 	
	 $scope.area2 = {		
		uid: 1
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
	
});