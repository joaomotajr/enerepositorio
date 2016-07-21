app.controller('controllerController', function ($scope, $timeout, $filter, ControllerService, ManufacturerService) {
	    
	
	$scope.saveController = function() {
		
		var controller = {
			uid: $scope.controllerUid != undefined ? $scope.controllerUid : 0,
			name: $scope.controllerName,
			manufacturer: $scope.controllerManufacturer,
			model: $scope.controllerModel
    	}; 
		 
		$scope.inclusaoController = new ControllerService.save(controller);		 
		$scope.inclusaoController.$controller({_csrf : angular.element('#_csrf').val()}, function()
		{         	
         	console.log($scope.inclusao);   
         	
         	$scope.clearFormController();
            $scope.getControllers();
                     	
         });		 
	 }
	
<<<<<<< HEAD
	
=======
>>>>>>> branch 'master' of https://mota_junior@bitbucket.org/enesens/enerepositorio.git
	$scope.saveManufacturer = function() {
		
		var manufacturer = {
			uid: 0,
			name: $scope.newManufacturer			
    	}; 
		 
		$scope.inclusaoManufacturer = new ManufacturerService.save(manufacturer);		 
		$scope.inclusaoManufacturer.$manufacturer({_csrf : angular.element('#_csrf').val()}, function()
		{         	
         	
			$scope.manufacturers.push($scope.inclusaoManufacturer.t);
                     	
        });
		
		$(".popover").popover('hide');
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
		    $scope.controllerManufacturer = $scope.controllers[index].manufacturer;	
		    
		    	        
	        $('#idControllerName').focus();
	    }
	 
	 $scope.deleteController = function(index) {
		 
		 var uid = $scope.controllers[index].uid;		  
		 
		 $scope.deletar = new ControllerService.deletar();		 
		 $scope.deletar.$controller({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 
			 $scope.controllers.splice(index, 1);
         	         	
         });
		 
	 }	
	 
	 $scope.getControllers();
	 $scope.getManufacturers();
	 
	// $(".select2").select2();
	
});