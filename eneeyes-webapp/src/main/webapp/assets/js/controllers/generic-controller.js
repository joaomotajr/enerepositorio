app.controller('genericController', function ($scope, $timeout, $filter, GenericService, ManufacturerService) {
	
	$scope.saveGeneric = function() {
		
		angular.element('body').addClass('loading');
		
		var generic = {
			uid: $scope.genericUid != undefined ? $scope.genericUid : 0,			
			manufacturerDto: {uid: $scope.genericManufacturer.uid},
			deviceType: $scope.deviceType.uid,						
			unitMeterGases : $scope.gasUnitMeterGases.uid,
			name: $scope.genericName,
			model: $scope.genericModel
    	}; 
		 
		$scope.inclusaoGeneric = new GenericService.save(generic);		 
		$scope.inclusaoGeneric.$generic({_csrf : angular.element('#_csrf').val()}, function()
		{         	
            $timeout(function () {                    
            	$scope.clearFormGeneric();
                $scope.getGenerics();
	                     	
	            angular.element('body').removeClass('loading');				 
	         }, 500);			
		});		 
	}
	 
	$scope.clearFormGeneric = function () {
	
		$scope.genericUid = undefined;
		
		$scope.genericManufacturer = '';	
		$scope.deviceType = '';
		$scope.gasUnitMeterGases = '';
		$scope.genericName = '';
		$scope.genericModel = '';
	}
	 
	$scope.getGenerics = function() {
		 
		 $scope.resultGenerics = new GenericService.listAll();		 
		 $scope.resultGenerics.$generic({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.generics = $scope.resultGenerics.list; 		 			 
         });		 
	 }
	
	$scope.getManufacturers = function() {
		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 }	 
 
	 $scope.editGeneric = function (index) {
	        $scope.genericUid = $scope.generics[index].uid;
	        
		    $scope.genericName = $scope.generics[index].name;
		    $scope.genericModel = $scope.generics[index].model;
			$scope.genericManufacturer = $scope.generics[index].manufacturerDto;			
			$scope.gasUnitMeterGases = $scope.getUnitMetersGases($scope.generics[index].unitMeterGases);
			$scope.deviceType = $scope.getDeviceType($scope.generics[index].deviceType);
					    	        
	        $('#idGenericName').focus();
	    }
	 
	 $scope.deleteGeneric = function(index) {
		 
		 var uid = $scope.generics[index].uid;		  
		 
		 $scope.deletar = new GenericService.deletar();		 
		 $scope.deletar.$generic({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
		 
			 if (!$scope.deletar.isError)
				 $scope.generics.splice(index, 1);
			 else {
				 $scope.msgErro = "Erro: " + $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }
		});		 
	 }

	 $scope.getDeviceType = function (name) {
		
		for (var i = 0; i < $scope.deviceTypes.length; i++) {
			if ($scope.deviceTypes[i].name == name) {
				
				return $scope.deviceTypes[i];
			}
		} 		 
	}

	 $scope.getUnitMetersGases = function (name) {		 
		for (var i = 0; i < $scope.unitMetersGases.length; i++) {
			if ($scope.unitMetersGases[i].name == name) {
				
				return $scope.unitMetersGases[i];
			}
		} 		 
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
			{ name : 'OPEN/CLOSE', uid : 10 },
			{ name : 'KWH', uid : 11 }
		 ]; 

	 $scope.deviceTypes = 
	 [		  
		  { name : 'PLC', uid :  2, disabled : false },
		  { name : 'CONTROLLER', uid :  3, disabled : false },
		  { name : 'ELETRICITY', uid :  6, disabled : false },
		  { name : 'TEMPO', uid :  7, disabled : false },
		  { name : 'TEMPERATURE', uid :  8, disabled : false },
		  { name : 'DIGITAL', uid :  9, disabled : false },
	 ];	
	 
	 $scope.refreshGenerics = function() {
		 $scope.getManufacturers();
	 }
	 
	 $scope.getGenerics();
	 $scope.getManufacturers();
	 angular.element('body').removeClass('loading');
	
});