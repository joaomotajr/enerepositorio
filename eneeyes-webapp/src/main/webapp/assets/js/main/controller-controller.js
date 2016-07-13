
app.factory('ControllerService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/controller/delete/:id', {id: '@id'},{
    		controller : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/controller/all',{},{
        	controller : {method : 'GET'}
        }),
        listOne : $resource('/security/api/controller/obtemPorId/:id', {id: '@id'},{
        	controller : {method : 'GET'}
        }),
        save : $resource('/security/api/controller/save',{},{
        	controller : {method : 'POST'}
        }),
     };
});


app.controller('controllerController', function ($scope, $timeout, $filter, ControllerService) {
	
	
	$scope.saveController = function() {
		
		var controller = {
			uid: $scope.controllerUid != undefined ? $scope.controllerUid : 0,
			name: $scope.controllerName,
			manufacturer: $scope.controllerManufacturer,
			model: $scope.controllerModel
    	}; 
		 
		$scope.inclusao = new ControllerService.save(controller);		 
		$scope.inclusao.$controller({_csrf : angular.element('#_csrf').val()}, function()
		{         	
         	console.log($scope.inclusao);   
         	
         	$scope.clearFormController();
            $scope.getControllers();
                     	
         });		 
	 }
	 
	$scope.clearFormController = function () {
	
	    $scope.controllerUid = undefined;
	    $scope.controllerName = '';
	    $scope.controllerModel = '';
	    $scope.controllerManufacturer = '';	
	}

	 
	 $scope.getControllers = function() {
		 
		 $scope.controllers = new ControllerService.listAll();		 
		 $scope.controllers.$controller({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		 			 
         });		 
	 }
	 
	 $scope.getOneController = function() {
		 
		 $scope.listOne = new ControllerService.listOne();		 
		 $scope.listOne.$controller({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.listOne);
         	         	
         });
		 
	 }
	 
	 $scope.deletarController = function() {
		 
		 $scope.deletar = new ControllerService.deletar();		 
		 $scope.deletar.$controller({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.deletar);
         	         	
         });
		 
	 }
	
	 
	 $scope.getControllers();
	
});