app.controller('sensorController', function ($scope, $timeout, SensorService, ManufacturerService, GasService, ViewService, UnitMeterService) {
		
	$scope.saveSensor = function() {
		
		var exists =  $scope.sensors.findIndex(function (i) { return i.name.toLowerCase() === $scope.sensorName.toLowerCase(); });		
		if ( exists >= 0 && $scope.sensorUid == undefined) {
			 $scope.sensorNameExist = "true";
			 return;
		}
		
		angular.element('body').addClass('loading');				
		var sensor = {
			uid: $scope.sensorUid != undefined ? $scope.sensorUid : 0,
			name: $scope.sensorName,
			manufacturerDto: $scope.sensorManufacturer,
			model: $scope.sensorModel,
			detectionType: $scope.sensorDetectionType.uid,
			gasDto: $scope.sensorGas,			
			unitMeter: {uid: $scope.unitMeter.uid}, 
			rangeMax : $scope.sensorRangeMax,
			rangeMin : $scope.sensorRangeMin,
			rangeUnit : $scope.sensorRangeUnit	    
    	}; 
		 
		$scope.inclusaoSensor = new SensorService.save(sensor);		 
		$scope.inclusaoSensor.$sensor({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$timeout(function () {				
				$scope.clearFormSensor();
				$scope.getSensors();
				angular.element('#modalEditSensor').modal('toggle');
	            angular.element('body').removeClass('loading');				 
	         }, 500);
		});
	 };
	 
	$scope.clearFormSensor = function () {	
		$scope.gases = angular.copy($scope.resultGases.list);				
	    $scope.sensorUid = undefined;  
	    $scope.sensorName = '';
	    $scope.sensorModel = '';
	    $scope.sensorManufacturer = '';
	    $scope.sensorDetectionType = '';	    
		$scope.sensorGas = '';
	    $scope.gasUnitMeter = '';
		$scope.sensorRangeMax = '';
		$scope.sensorRangeMin = '';
		$scope.sensorRangeUnit = '';
		$scope.sensorNameExist = "false";	
		$scope.unitMeter = '';
		$scope.existSensor = undefined;		
	};
	 
	$scope.getSensors = function() {		 
		 $scope.resultSensors = new SensorService.listAll();		 
		 $scope.resultSensors.$sensor({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.sensors = $scope.resultSensors.list;			 			 
			 $scope.clearFormSensor();			 
         });		 
	 };
	
	$scope.getManufacturers = function() {		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 };
 
	 $scope.editSensor = function (index) {
        $scope.sensorUid = $scope.sensors[index].uid;        
        $scope.sensorManufacturer = $scope.sensors[index].manufacturerDto;
	    $scope.sensorName = $scope.sensors[index].name;
	    $scope.sensorModel = $scope.sensors[index].model;
	    $scope.sensorDetectionType = $scope.getDetectionTypes($scope.sensors[index].detectionType);
		$scope.sensorGas = $scope.sensors[index].gasDto;
	    $scope.sensorRangeMax = $scope.sensors[index].rangeMax;
	    $scope.sensorRangeMin = $scope.sensors[index].rangeMin;
	    $scope.sensorRangeUnit = $scope.sensors[index].rangeUnit;
		$scope.deviceType = $scope.sensors[index].deviceType;
		$scope.unitMeter = $scope.sensors[index].unitMeter;
    
	    $scope.getExistSensor($scope.sensorUid) ;
	    
        $timeout(function () {
        	$scope.searchGas = {gases: $scope.sensorGases};
            $('#modalEditSensor').modal({ show: 'false' });                        
        }, 200);
	 };
	 
	$scope.getExistSensor = function(sensorId) {

		$scope.existSensor = undefined;
		$scope.isExistsSensor = new ViewService.existsSensor();
		$scope.isExistsSensor.$view({_csrf : angular.element('#_csrf').val(), id:sensorId }, function() {	 			 
			$scope.existSensor = ($scope.isExistsSensor.resultType == "YES_DATA");	
		});
	};		
	 
	$scope.deleteSensor = function(index) {		 
		var uid = $scope.sensors[index].uid;		 
		$scope.deletar = new SensorService.deletar();		 
		$scope.deletar.$sensor({_csrf : angular.element('#_csrf').val(), id : uid}, function() {			 			 
			if (!$scope.deletar.isError)
				$scope.sensors.splice(index, 1);
			 else {
				$scope.msgErroSensor = $scope.deletar.message;
				console.log($scope.deletar.systemMessage); 
			}
		});	 
	};
	 
	$scope.getDetectionTypes = function (name) {		 
		for (var i = 0; i < $scope.detectionTypes.length; i++) {
            if ($scope.detectionTypes[i].name == name) {
            	return $scope.detectionTypes[i];
            }
        } 		 
	};	

	$scope.getGases = function() {		
		$scope.resultGases = new GasService.listAll();	
		$scope.resultGases.$gas({_csrf : angular.element('#_csrf').val()}, function(){		
			$scope.gases = $scope.resultGases.list;
	   });		 
	};

	$scope.getUnitMeters = function() {		 
		$scope.listAllUnitMeter = new UnitMeterService.listAllUnitMeter();		 
		$scope.listAllUnitMeter.$unitMeter({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.unitMeters = $scope.listAllUnitMeter.list;
	   });		 
	};	 
	 
	$scope.detectionTypes = 
	[
		{ name : 'OUTROS', uid : 0 },
		{ name : 'CAT', uid :  1 },
		{ name : 'FTA', uid : 2 },
		{ name : 'PID', uid : 3 },
		{ name : 'EC', uid : 4 },
		{ name : 'IR', uid : 5 },	  	
		{ name : 'GALVANCIA', uid : 7}
	]; 
	 
	 $scope.getUnitMetersGases = function (name) {		 
		 for (var i = 0; i < $scope.unitMetersGases.length; i++) {
             	if ($scope.unitMetersGases[i].name == name) {                 
           		return $scope.unitMetersGases[i];
            }
        } 		 
	};
	 
	$scope.keypress = function($event) {
		$scope.lastKey = $event.keyCode;
		$scope.sensorNameExist = "false";
	};
	 
	$scope.refreshSensors = function() {
		$scope.getSensors();
		$scope.getManufacturers();
		$scope.getGases();
		$scope.getUnitMeters();
	};

	$scope.refreshSensors();	 
	angular.element('body').removeClass('loading');
});