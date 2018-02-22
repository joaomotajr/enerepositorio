app.controller('sensorController', function ($scope, $timeout, $filter, SensorService, ManufacturerService, GasService, ViewService) {
		
	$scope.saveSensor = function() {
		
		var exists =  $scope.sensors.findIndex(function (i) { return i.name.toLowerCase() === $scope.sensorName.toLowerCase() });
		
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
			unitMeterGases : $scope.gasUnitMeterGases.uid,
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
	            angular.element('body').removeClass('loading');				 
	         }, 500);          

		});		 
	 }
	 
	$scope.clearFormSensor = function () {
	
		$scope.gases = angular.copy($scope.resultGases.list);
		$scope.searchGas = {gases: $scope.sensorGases};
		
	    $scope.sensorUid = undefined;  
	    $scope.sensorName = '';
	    $scope.sensorModel = '';
	    $scope.sensorManufacturer = '';
	    $scope.sensorDetectionType = '';	    
		$scope.sensorGas = '';
	    $scope.gasUnitMeterGases = '';
		$scope.sensorRangeMax = '';
		$scope.sensorRangeMin = '';
		$scope.sensorRangeUnit = '';
		$scope.sensorNameExist = "false";	
		$scope.existSensor = undefined;		
	}
	 
	$scope.getSensors = function() {
		 
		 $scope.resultSensors = new SensorService.listAll();		 
		 $scope.resultSensors.$sensor({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.sensors = $scope.resultSensors.list;
			 			 
			 $scope.clearFormSensor();			 
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
        
        $scope.sensorManufacturer = $scope.sensors[index].manufacturerDto;
	    $scope.sensorName = $scope.sensors[index].name;
	    $scope.sensorModel = $scope.sensors[index].model;
	    $scope.sensorDetectionType = $scope.getDetectionTypes($scope.sensors[index].detectionType);
		$scope.sensorGas = $scope.sensors[index].gasDto;
	    $scope.sensorRangeMax = $scope.sensors[index].rangeMax;
	    $scope.sensorRangeMin = $scope.sensors[index].rangeMin;
	    $scope.sensorRangeUnit = $scope.sensors[index].rangeUnit;
	    $scope.gasUnitMeterGases = $scope.getUnitMetersGases($scope.sensors[index].unitMeterGases);
    
	    $scope.getExistSensor($scope.sensorUid) ;
	    
        $timeout(function () {
        	$scope.searchGas = {gases: $scope.sensorGases};
            $('#modalEditSensor').modal({ show: 'false' });                        
        }, 200);
	 }
	 
	 $scope.getExistSensor = function(sensorId) {

		$scope.existSensor = undefined;

		$scope.isExistsSensor = new ViewService.existsSensor();
		$scope.isExistsSensor.$view({_csrf : angular.element('#_csrf').val(), id:sensorId }, function() {	 			 
			$scope.existSensor = ($scope.isExistsSensor.resultType == "YES_DATA");	
		});
	}
		
	 
	 $scope.deleteSensor = function(index) {
		 
		 var uid = $scope.sensors[index].uid;		  
		 
		 $scope.deletar = new SensorService.deletar();		 
		 $scope.deletar.$sensor({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 			 
			 if (!$scope.deletar.isError)
				 $scope.sensors.splice(index, 1);
			 else {
				 $scope.msgErroSensor = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }
		});	 
	 }	 
	 
	 $scope.getDetectionTypes = function (name) {
		 
		 for (var i = 0; i < $scope.detectionTypes.length; i++) {
             if ($scope.detectionTypes[i].name == name) {
                 
            	 return $scope.detectionTypes[i];
             }
         } 		 
	 }	

	 $scope.getGases = function() {
		
		$scope.resultGases = new GasService.listAll();	
		$scope.resultGases.$gas({_csrf : angular.element('#_csrf').val()}, function(){		
			$scope.gases = $scope.resultGases.list
	   });		 
	}	 
	 
	 function getClassNameWithNumberSuffix(el) {
        var className = null;
        var regexp = /\w+\d+/;
        $($(el).attr('class').split(' ')).each(function () {
            if (regexp.test(this)) {
                className = this;
                return false;
            }
        });

        return className;
	 }	 
	 
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
	 }
	 
	 $scope.unitMetersGases = 
		 [
		  	{ name : 'DESCONHECIDO', uid : 0 },
		  	{ name : 'PPM', uid :  1 },
		  	{ name : 'PPB', uid : 2 },
		  	{ name : 'LEL_PERCENT', uid : 3 },
		  	{ name : 'LEL_PERCENT_METRO', uid : 4 },
		  	{ name : 'PERCENT_VOLUME', uid : 5 }		  	
		 ]; 
	 
	 $scope.keypress = function($event) {
		 $scope.lastKey = $event.keyCode
		 $scope.sensorNameExist = "false";
	  };
	 
	 $scope.refreshSensors = function() {
		 $scope.getSensors();
		 $scope.getManufacturers();
		 $scope.getGases();
	 } 
	 
	 $scope.refreshSensors();	 
	 angular.element('body').removeClass('loading');

});