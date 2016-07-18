
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
		 
		 $scope.result = new ControllerService.listAll();		 
		 $scope.result.$controller({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.controllers = $scope.result.list; 		 			 
         });		 
	 }
	 
	 $scope.saveManufacturer = function() {
		 $(".popover").popover('hide');
	 }
	 
	 $scope.editController = function (index) {
	        $scope.controllerUid = $scope.controllers[index].uid;
	        
		    $scope.controllerName = $scope.controllers[index].name;
		    $scope.controllerModel = $scope.controllers[index].model;
		    $scope.controllerManufacturer = $scope.controllers[index].manufacturer;	
	        
	        $('#idControllerNome').focus();
	    }
	 
	 $scope.deleteController = function(index) {
		 
		 var uid = $scope.controllers[index].uid;		  
		 
		 $scope.deletar = new ControllerService.deletar();		 
		 $scope.deletar.$controller({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 
			 $scope.controllers.splice(index, 1);
         	         	
         });
		 
	 }	
	 
	 $scope.getControllers();
	 $(".select2").select2();
	
});