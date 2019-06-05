
app.controller('transmitterController', function ($scope, $timeout, $filter, TransmitterService, ManufacturerService) {	    
	
	$scope.saveTransmitter = function() {		
		
		var exists =  $scope.transmitters.findIndex(function (i) { return i.name.toLowerCase() === $scope.transmitterName.toLowerCase() });
		
		if ( exists >= 0 && $scope.transmitterUid == undefined) {
			  $scope.transmitterNameExist = "true";
			  return;
		}
		
		angular.element('body').addClass('loading');		
		var transmitter = {
			uid: $scope.transmitterUid != undefined ? $scope.transmitterUid : 0,
			name: $scope.transmitterName,
			manufacturerDto: $scope.transmitterManufacturer,
			model: $scope.transmitterModel,
			commProtocol : $scope.transmitterCommProtocol.uid
    	}; 
		 
		$scope.inclusaoTransmitter = new TransmitterService.save(transmitter);		 
		$scope.inclusaoTransmitter.$transmitter({_csrf : angular.element('#_csrf').val()}, function()
		{         	            
            $timeout(function () {				
            	$scope.clearFormTransmitter();
                $scope.getTransmitters();
	            
	            angular.element('body').removeClass('loading');				 
	         }, 500);
		});			 
	 };
	 
	$scope.clearFormTransmitter = function () {	
	    $scope.transmitterUid = undefined;
	    $scope.transmitterName = '';
	    $scope.transmitterModel = '';
	    $scope.transmitterManufacturer = '';
	    $scope.transmitterCommProtocol = '';
	};

	 
	$scope.getTransmitters = function() {		 
		 $scope.resultTransmitters = new TransmitterService.listAll();		 
		 $scope.resultTransmitters.$transmitter({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.transmitters = $scope.resultTransmitters.list; 		 			 
         });		 
	 };
	
	$scope.getManufacturers = function() {		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 };	 
 
	 $scope.editTransmitter = function (index) {
		$scope.transmitterUid = $scope.transmitters[index].uid;		
		$scope.transmitterName = $scope.transmitters[index].name;
		$scope.transmitterModel = $scope.transmitters[index].model;
		$scope.transmitterManufacturer = $scope.transmitters[index].manufacturerDto;	
		$scope.transmitterCommProtocol = $scope.getCommProtocols($scope.transmitters[index].commProtocol);	    	        
	    $('#idTransmitterName').focus();
	};
	 
	 $scope.deleteTransmitter = function(index) {		 
		 var uid = $scope.transmitters[index].uid;		  		 
		 $scope.deletar = new TransmitterService.deletar();		 
		 $scope.deletar.$transmitter({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 if (!$scope.deletar.isError)
				 $scope.transmitters.splice(index, 1);
			 else {
				 $scope.msgErroTransmitter = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage);
			 }
		});		 
	 } ;
	 
	 $scope.getCommProtocols = function (name) {		 
		 for (var i = 0; i < $scope.commProtocols.length; i++) {
             if ($scope.commProtocols[i].name == name) {                 
            	 return $scope.commProtocols[i];
             }
         } 		 
	 };
	 
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
		  	{ name : 'DEVICENET', uid : 8 },
		  	{ name : '_04_20mA', uid : 9 }
		 ]; 
	 
	 $scope.keypress = function($event) {
	    $scope.lastKey = $event.keyCode;
	    $scope.transmitterNameExist = "false";
	  };
		  	 
	 $scope.refreshTransmitters = function() {
		$scope.getTransmitters();
		 $scope.getManufacturers();	
	 }
	 
	 $scope.refreshTransmitters();	
	 angular.element('body').removeClass('loading');
	
});