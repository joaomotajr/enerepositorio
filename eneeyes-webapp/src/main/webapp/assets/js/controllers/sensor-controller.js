
app.controller('sensorController', function ($scope, $timeout, $filter, SensorService, ManufacturerService) {
	    
	
	$scope.saveSensor = function() {
		
		var sensor = {
			uid: $scope.sensorUid != undefined ? $scope.sensorUid : 0,
			name: $scope.sensorName,
			manufacturer: $scope.sensorManufacturer,
			model: $scope.sensorModel
    	}; 
		 
		$scope.inclusaoSensor = new SensorService.save(sensor);		 
		$scope.inclusaoSensor.$sensor({_csrf : angular.element('#_csrf').val()}, function()
		{         	
         	console.log($scope.inclusao);   
         	
         	$scope.clearFormSensor();
            $scope.getSensors();
                     	
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
	 
	$scope.clearFormSensor = function () {
	
	    $scope.sensorUid = undefined;  
	    $scope.sensorName = '';
	    $scope.sensorModel = '';
	    $scope.sensorManufacturer = '';	
	}

	 
	$scope.getSensors = function() {
		 
		 $scope.resultSensors = new SensorService.listAll();		 
		 $scope.resultSensors.$sensor({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.sensors = $scope.resultSensors.list; 		 			 
         });		 
	 }
	
	$scope.getManufacturers = function() {
		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 }	 
 
	 $scope.editSensor = function (index) {
	        $scope.sensorUid = $scope.sensors[index].uid;
	        
		    $scope.sensorName = $scope.sensors[index].name;
		    $scope.sensorModel = $scope.sensors[index].model;
		    $scope.sensorDetectionType = $scope.getDetectionTypes($scope.sensors[index].detectionType);
		    
		    	        
	        $('#idSensorName').focus();
	    }
	 
	 $scope.deleteSensor = function(index) {
		 
		 var uid = $scope.sensors[index].uid;		  
		 
		 $scope.deletar = new SensorService.deletar();		 
		 $scope.deletar.$sensor({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 
			 $scope.sensors.splice(index, 1);
         	         	
         });		 
	 }	 
	 
	 $scope.getDetectionTypes = function (name) {
		 
		 for (var i = 0; i < $scope.detectionTypes.length; i++) {
             if ($scope.detectionTypes[i].name == name) {
                 
            	 return $scope.detectionTypes[i];
             }
         } 		 
	 }
	 
	 $scope.detectionTypes = 
		 [
		  	{ name : 'OUTROS', uid : 0 },
		  	{ name : 'CAT', uid :  1 },
		  	{ name : 'FTA', uid : 2 },
		  	{ name : 'FID', uid : 3 },
		  	{ name : 'ECM', uid : 4 },
		  	{ name : 'IR', uid : 5 },
		  	{ name : 'BUT', uid : 6 }		  	
		 ]; 
	 
	 $scope.getSensors();
	 $scope.getManufacturers();	 
	
});