app.controller('controllerController', function ($scope, $timeout, $filter, ControllerService, ManufacturerService) {
	    
	
	$scope.saveController = function() {
		
		angular.element('body').addClass('loading');
		
		var controller = {
			uid: $scope.controllerUid != undefined ? $scope.controllerUid : 0,
			name: $scope.controllerName,
			manufacturerDto: $scope.controllerManufacturer,
			model: $scope.controllerModel
    	}; 
		 
		$scope.inclusaoController = new ControllerService.save(controller);		 
		$scope.inclusaoController.$controller({_csrf : angular.element('#_csrf').val()}, function()
		{         	
            $timeout(function () {                    
            	$scope.clearFormController();
                $scope.getControllers();
	                     	
	            angular.element('body').removeClass('loading');				 
	         }, 500);			
		});		 
	}
	 
	$scope.clearFormController = function () {
	
	    $scope.controllerUid = undefined;
	    $scope.controllerName = '';
	    $scope.controllerModel = '';
	    $scope.controllerManufacturer = '';	
	}
	 
	$scope.getControllers = function() {
		 
		 $scope.resultControllers = new ControllerService.listAll();		 
		 $scope.resultControllers.$controller({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.controllers = $scope.resultControllers.list; 		 			 
         });		 
	 }
	
	$scope.getManufacturers = function() {
		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 }	 
 
	 $scope.editController = function (index) {
	        $scope.controllerUid = $scope.controllers[index].uid;
	        
		    $scope.controllerName = $scope.controllers[index].name;
		    $scope.controllerModel = $scope.controllers[index].model;
		    $scope.controllerManufacturer = $scope.controllers[index].manufacturerDto;
		    	        
	        $('#idControllerName').focus();
	    }
	 
	 $scope.deleteController = function(index) {
		 
		 var uid = $scope.controllers[index].uid;		  
		 
		 $scope.deletar = new ControllerService.deletar();		 
		 $scope.deletar.$controller({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
		 
			 if (!$scope.deletar.isError)
				 $scope.controllers.splice(index, 1);
			 else {
				 $scope.msgErro = "Erro: " + $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }
         	         	
//		 }, function(data) {		
//			 $scope.msgErro = "Erro: " + data.statusText;
		});
		 
	 }	
	 
	 $scope.refreshControllers = function() {
		 $scope.getManufacturers();
	 }
	 
	 $scope.getControllers();
	 $scope.getManufacturers();
	
});