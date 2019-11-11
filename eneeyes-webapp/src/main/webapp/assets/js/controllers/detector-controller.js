
app.filter('manufacturerFilter', function () {
    return function (objects, criteria) {
        
    	if (!criteria || criteria.manufacturer == null)
            return null;
        
        var filterResult = new Array();
        for (var index in objects) {
        
   			 if (objects[index].manufacturerDto.uid == criteria.manufacturer.uid) {
                 filterResult.push(objects[index]);
             }
        }
        return filterResult;
    }
});

app.controller('detectorController', function ($scope, $timeout, $filter, DetectorService, ManufacturerService, SensorService, TransmitterService, ViewService) {
		
	$scope.saveDetector = function() {		
		
		var exists =  $scope.detectors.findIndex(function (i) { return i.name.toLowerCase() === $scope.detectorName.toLowerCase() });
		
		if ( exists >= 0 && $scope.detectorUid == undefined) {
			 $scope.detectorNameExist = "true";
			 return;
		}
		
		angular.element('body').addClass('loading');				
		var detector = {
			uid: $scope.detectorUid != undefined ? $scope.detectorUid : 0,
			name: $scope.detectorName,
			manufacturerDto: $scope.detectorManufacturer,
			transmitterDto: $scope.detectorTransmitter,
			model: $scope.detectorModel,
			image: $scope.detectorImage,
			sensorDto: $scope.detectorSensor,
    	}; 
		 
		$scope.inclusaoDetector = new DetectorService.save(detector);		 
		$scope.inclusaoDetector.$detector({_csrf : angular.element('#_csrf').val()}, function() {			
			$timeout(function () {                    
				$scope.clearFormDetector();
	            $scope.getDetectors();
	                     	
	            angular.element('body').removeClass('loading');				 
	         }, 500);
		});			 
	 };
	 
	$scope.clearFormDetector = function () {
		
		$scope.sensors = angular.copy($scope.resultSensors.list);
		
	    $scope.detectorUid = undefined;  
	    $scope.detectorName = '';
	    $scope.detectorModel = '';
	    $scope.detectorManufacturer = '';
	    $scope.detectorTransmitter = '';
	    $scope.detectorImage = "/assets/img/cover.jpg";
		$scope.detectorSensor = '';
		$scope.detectorNameExist = "false";
		$scope.existDetector = undefined;
	}
	 
	$scope.getDetectors = function() {		 
		 $scope.resultDetectors = new DetectorService.listAll();		 
		 $scope.resultDetectors.$detector({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.detectors = $scope.resultDetectors.list;			 
			 $scope.clearFormDetector();			 
         });		 
	 }
	
	$scope.getManufacturers = function() {		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 }

	$scope.getTransmitters = function() {		 
		 $scope.resultTransmitters = new TransmitterService.listAll();		 
		 $scope.resultTransmitters.$transmitter({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.transmitters = $scope.resultTransmitters.list; 		 			 
        });		 
	 }
	
	 $scope.editDetector = function (index) {
	        $scope.detectorUid = $scope.detectors[index].uid;
	        
	        $scope.detectorManufacturer = $scope.detectors[index].manufacturerDto;
		    $scope.detectorName = $scope.detectors[index].name;
		    $scope.detectorModel = $scope.detectors[index].model;		    		    
		    $scope.detectorImage = ($scope.detectors[index].image == null ? "/assets/img/cover.jpg" :  $scope.detectors[index].image);
		    $scope.detectorTransmitter = $scope.detectors[index].transmitterDto;
			$scope.detectorSensor = $scope.detectors[index].sensorDto;				
			$scope.getExistDetector($scope.detectorUid);
		    $timeout(function () {
		    	$scope.search = {manufacturer: $scope.detectorManufacturer};
		    	$scope.searchSensor = {manufacturer: $scope.detectorManufacturer , sensors: $scope.detectorSensors};		    	
	            $('#modalEditDetector').modal({ show: 'false' });                        
	        }, 200);
	    }
	 
	 $scope.deleteDetector = function(index) {
		 
		 var uid = $scope.detectors[index].uid;		  
		 
		 $scope.deletar = new DetectorService.deletar();		 
		 $scope.deletar.$detector({_csrf : angular.element('#_csrf').val(), id : uid}, function() {
			 
			 if (!$scope.deletar.isError)
				 $scope.detectors.splice(index, 1);
			 else {
				 $scope.msgErroDetector = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }         	         	
		});		 
	 };	 


	 $scope.getSensors = function() {		
		$scope.resultSensors = new SensorService.listAll();	
		$scope.resultSensors.$sensor({_csrf : angular.element('#_csrf').val()}, function(){		
			$scope.sensors = $scope.resultSensors.list;
	   });		 
	}; 
	 
	$scope.loadEvents = function() {		
		$('#idInputImageDetector').change( encodeImageFileAsURL( function(base64Img) {		    
		    $scope.detectorImage =  base64Img;
			$scope.$apply();		    
		}));
		 $scope.addPhoto = function() {
			 event.preventDefault();
			$('#idInputImageDetector').trigger('click');
		 }		
	};
	
	 $scope.changeManufacturer = function() {				
	 	$scope.search = {manufacturer: $scope.detectorManufacturer};				
	 };
	 

	$scope.getExistDetector = function(detectorId) {
		$scope.existDetector = undefined;			
		$scope.isDetectorSensor = new ViewService.existsDetector();	
		$scope.isDetectorSensor.$view({_csrf : angular.element('#_csrf').val(), id:detectorId}, function() {	 			 
				$scope.existDetector = ($scope.isDetectorSensor.resultType == "YES_DATA");	
		});
	}
 
	 $scope.refreshDetectors = function() {
		 $scope.getManufacturers();
		 $scope.getDetectors();		 
		 $scope.getTransmitters();
		 $scope.getSensors();
		 $scope.detectorImage = "/assets/img/cover.jpg";
	 }
	 
	 $scope.keypress = function($event) {
	    $scope.lastKey = $event.keyCode;
	    $scope.detectorNameExist = "false";
	  };
	 
	 $scope.refreshDetectors();
	 angular.element('body').removeClass('loading');
	 
	 $timeout(function () {
		 $scope.loadEvents();
	 }, 500);
});