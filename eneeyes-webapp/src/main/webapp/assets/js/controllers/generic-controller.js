app.controller('genericController', function ($scope, $timeout, $filter, GenericService, ManufacturerService) {
	
	$scope.saveGeneric = function() {
		
		angular.element('body').addClass('loading');
		
		var controller = {
			uid: $scope.controllerUid != undefined ? $scope.controllerUid : 0,
			name: $scope.controllerName,
			manufacturerDto: $scope.controllerManufacturer,
			model: $scope.controllerModel
    	}; 
		 
		$scope.inclusaoGeneric = new GenericService.save(controller);		 
		$scope.inclusaoGeneric.$controller({_csrf : angular.element('#_csrf').val()}, function()
		{         	
            $timeout(function () {                    
            	$scope.clearFormGeneric();
                $scope.getGenerics();
	                     	
	            angular.element('body').removeClass('loading');				 
	         }, 500);			
		});		 
	}
	 
	$scope.clearFormGeneric = function () {
	
	    $scope.controllerUid = undefined;
	    $scope.controllerName = '';
	    $scope.controllerModel = '';
	    $scope.controllerManufacturer = '';	
	}
	 
	$scope.getGenerics = function() {
		 
		 $scope.resultGenerics = new GenericService.listAll();		 
		 $scope.resultGenerics.$generic({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.controllers = $scope.resultGenerics.list; 		 			 
         });		 
	 }
	
	$scope.getManufacturers = function() {
		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 }	 
 
	 $scope.editGeneric = function (index) {
	        $scope.controllerUid = $scope.controllers[index].uid;
	        
		    $scope.controllerName = $scope.controllers[index].name;
		    $scope.controllerModel = $scope.controllers[index].model;
		    $scope.controllerManufacturer = $scope.controllers[index].manufacturerDto;
		    	        
	        $('#idGenericName').focus();
	    }
	 
	 $scope.deleteGeneric = function(index) {
		 
		 var uid = $scope.controllers[index].uid;		  
		 
		 $scope.deletar = new GenericService.deletar();		 
		 $scope.deletar.$controller({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
		 
			 if (!$scope.deletar.isError)
				 $scope.controllers.splice(index, 1);
			 else {
				 $scope.msgErro = "Erro: " + $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }
		});
		 
	 }

	 	 
	 $scope.unitMetersGases = 
		 [
		  	{ name : 'DESCONHECIDO', uid : 0 },
		  	{ name : 'PPM', uid :  1 },
		  	{ name : 'PPB', uid : 2 },
		  	{ name : 'LEL_PERCENT', uid : 3 },
		  	{ name : 'LEL_PERCENT_METRO', uid : 4 },
			{ name : 'GRAUS_CELSIUS', uid : 5 },
			{ name : 'VOLT', uid : 6 },
			{ name : 'AMPERE', uid : 7 },
			{ name : 'MINUTE', uid : 8 },
			{ name : 'SECOND', uid : 9 },
			{ name : 'OPEN/CLOSE', uid : 10 }
		 ]; 

	 $scope.deviceTypes = 
	 [		  
		  { name : 'PLC', uid :  2, disabled : false },
		  { name : 'CONTROLADORA', uid :  3, disabled : false },
		  { name : 'ELETRICIDADE', uid :  6, disabled : false },
		  { name : 'TEMPO', uid :  7, disabled : false },
		  { name : 'TEMPERATURA', uid :  8, disabled : false },
		  { name : 'DIGITAL', uid :  9, disabled : false },
	 ];	
	 
	 $scope.refreshGenerics = function() {
		 $scope.getManufacturers();
	 }
	 
	 $scope.getGenerics();
	 $scope.getManufacturers();
	 angular.element('body').removeClass('loading');
	
});