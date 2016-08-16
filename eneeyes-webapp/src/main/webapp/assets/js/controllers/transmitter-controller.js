
app.controller('transmitterController', function ($scope, $timeout, $filter, TransmitterService, ManufacturerService) {
	    
	
	$scope.saveTransmitter = function() {
		
		var transmitter = {
			uid: $scope.transmitterUid != undefined ? $scope.transmitterUid : 0,
			name: $scope.transmitterName,
			manufacturer: $scope.transmitterManufacturer,
			model: $scope.transmitterModel,
			commProtocol : $scope.transmitterCommProtocol.uid
    	}; 
		 
		$scope.inclusaoTransmitter = new TransmitterService.save(transmitter);		 
		$scope.inclusaoTransmitter.$transmitter({_csrf : angular.element('#_csrf').val()}, function()
		{         	
         	$scope.clearFormTransmitter();
            $scope.getTransmitters();
                     	
         });		 
	 }
	
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
	 
	$scope.clearFormTransmitter = function () {
	
	    $scope.transmitterUid = undefined;
	    $scope.transmitterName = '';
	    $scope.transmitterModel = '';
	    $scope.transmitterManufacturer = '';
	    $scope.transmitterCommProtocol = '';
	}

	 
	$scope.getTransmitters = function() {
		 
		 $scope.resultTransmitters = new TransmitterService.listAll();		 
		 $scope.resultTransmitters.$transmitter({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.transmitters = $scope.resultTransmitters.list; 		 			 
         });		 
	 }
	
	$scope.getManufacturers = function() {
		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 }	 
 
	 $scope.editTransmitter = function (index) {
	        $scope.transmitterUid = $scope.transmitters[index].uid;
	        
		    $scope.transmitterName = $scope.transmitters[index].name;
		    $scope.transmitterModel = $scope.transmitters[index].model;
		    $scope.transmitterManufacturer = $scope.transmitters[index].manufacturer;	
		    $scope.transmitterCommProtocol = $scope.getCommProtocols($scope.transmitters[index].commProtocol);
		    
		    	        
	        $('#idTransmitterName').focus();
	    }
	 
	 $scope.deleteTransmitter = function(index) {
		 
		 var uid = $scope.transmitters[index].uid;		  
		 
		 $scope.deletar = new TransmitterService.deletar();		 
		 $scope.deletar.$transmitter({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 
			 $scope.transmitters.splice(index, 1);
         	         	
         });		 
	 }	 
	 
	 $scope.getCommProtocols = function (name) {
		 
		 for (var i = 0; i < $scope.commProtocols.length; i++) {
             if ($scope.commProtocols[i].name == name) {
                 
            	 return $scope.commProtocols[i];
             }
         } 		 
	 }
	 
	 $scope.commProtocols = 
		 [
		  	{ name : 'Selecione', uid : '' },
		  	{ name : 'DESCONHECIDO', uid : 0 },
		  	{ name : 'PROFIBUS', uid :  1 },
		  	{ name : 'ETHERNET_IP', uid : 2 },
		  	{ name : 'ETHERCAT', uid : 3 },
		  	{ name : 'ASI', uid : 4 },
		  	{ name : 'MODBUS', uid : 5 },
		  	{ name : 'HART', uid : 6 },
		  	{ name : 'HONEYWELL', uid : 7 },
		  	{ name : 'DEVICENET', uid : 8 }
		 ]; 
	 
	 $scope.getTransmitters();
	 $scope.getManufacturers();	 
	
});