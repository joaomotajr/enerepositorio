app.filter('manufacturerFilter2', function () {
    return function (objects, criteria) {
        
    	if (!criteria || criteria.manufacturer == null)
            return null;
        
        var filterResult = new Array();

        for (index in objects) {
        
   			 if (objects[index].manufacturerDto.uid == criteria.manufacturer.uid && 
   					 criteria.sensors.findIndex( function(item) { return item.uid === objects[index].uid }) < 0 ) {

                 filterResult.push(objects[index]);
             }
        }

        return filterResult;
    }
});

app.filter('manufacturerFilter', function () {
    return function (objects, criteria) {
        
    	if (!criteria || criteria.manufacturer == null)
            return null;
        
        var filterResult = new Array();

        for (index in objects) {
        
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
		
		for (var i = 0; i < $scope.detectorSensors.length; i++) {
			$scope.detectorSensors[i].index = undefined;	            	             
        } 		 
				
		var detector = {
			uid: $scope.detectorUid != undefined ? $scope.detectorUid : 0,
			name: $scope.detectorName,
			manufacturerDto: $scope.detectorManufacturer,
			transmitterDto: $scope.detectorTransmitter,
			model: $scope.detectorModel,
			image: $scope.detectorImage,
			sensorsDto: $scope.detectorSensors
    	}; 
		 
		$scope.inclusaoDetector = new DetectorService.save(detector);		 
		$scope.inclusaoDetector.$detector({_csrf : angular.element('#_csrf').val()}, function()
		{         	         	
			
			$timeout(function () {                    
				$scope.clearFormDetector();
	            $scope.getDetectors();
	                     	
	            angular.element('body').removeClass('loading');				 
	         }, 500);			           

		});			 
	 }
	 
	$scope.clearFormDetector = function () {
		
		$scope.sensors = angular.copy($scope.resultSensors.list);
		
	    $scope.detectorUid = undefined;  
	    $scope.detectorName = '';
	    $scope.detectorModel = '';
	    $scope.detectorManufacturer = '';
	    $scope.detectorTransmitter = '';
	    $scope.detectorImage = "/assets/img/cover.jpg";
	    $scope.detectorSensors = [];
		$scope.detectorNameExist = "false";
		$scope.existSensor = undefined;
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
			$scope.detectorSensors = $scope.detectors[index].sensorsDto;
				
			$scope.getExistSensorInPosition($scope.detectorSensors) ;
		    		    
		    $timeout(function () {
		    	$scope.search = {manufacturer: $scope.detectorManufacturer};
		    	$scope.searchSensor = {manufacturer: $scope.detectorManufacturer , sensors: $scope.detectorSensors};
		    	
	            $('#modalEditDetector').modal({ show: 'false' });                        
	        }, 200);
	    }
	 
	 $scope.deleteDetector = function(index) {
		 
		 var uid = $scope.detectors[index].uid;		  
		 
		 $scope.deletar = new DetectorService.deletar();		 
		 $scope.deletar.$detector({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 
			 if (!$scope.deletar.isError)
				 $scope.detectors.splice(index, 1);
			 else {
				 $scope.msgErroDetector = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }         	         	
		});		 
	 }	 
	 
	 $scope.getSensors = function() {
		 
		 $scope.resultSensors = new SensorService.listAll();		 
		 $scope.resultSensors.$sensor({_csrf : angular.element('#_csrf').val()}, function(){
			 
			 for (index in $scope.resultSensors.list) {
				 $scope.resultSensors.list[index].index = index;
			 }
			 
			 $scope.sensors = angular.copy($scope.resultSensors.list);	 
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
	 
	$scope.loadEvents = function() {
		
		$('#idInputImageDetector').change( encodeImageFileAsURL( function(base64Img) {		    
		    $scope.detectorImage =  base64Img;
			$scope.$apply();		    
		}));
		
		 $('#idChooseFileDetector').click(function(event) {
		    event.preventDefault();	    
		    $('#idInputImageDetector').trigger('click');	    
		 });		 
		  
		 $scope.addPhoto = function() {
			 event.preventDefault();
			$('#idInputImageDetector').trigger('click');
		 }		
	}
	
	$scope.changeManufacturer = function() { 
				
		$scope.search = {manufacturer: $scope.detectorManufacturer};
		$scope.searchSensor = {manufacturer: $scope.detectorManufacturer , sensors: $scope.detectorSensors};
		$scope.detectorSensors = [];
				
	}
	
	 $scope.addSensorDetector = function (noSortIndexindex) {
		 
		 $scope.detectorSensors.push($scope.sensors[noSortIndexindex]);
		 $scope.searchSensor = {manufacturer: $scope.detectorManufacturer , sensors: $scope.detectorSensors};
        
	 }
	 
	 $scope.deleteSensor = function (index) {
		 $scope.detectorSensors.splice(index, 1);	 
		 $scope.searchSensor = {manufacturer: $scope.detectorManufacturer , sensors: $scope.detectorSensors};
	 }	 

	 $scope.getExistSensorInPosition = function(sensors) {
		$scope.isExistsSensor = undefined;			

		if(sensors.length > 1 ) {
			$scope.isExistsSensor = new View.existsSensors();	
			$scope.isExistsSensor.$view({_csrf : angular.element('#_csrf').val(), id1:sensors[0].uid, id2:sensors[1].uid}, function() {	 			 
				$scope.existSensor = ($scope.isExistsSensor.resultType == "YES_DATA");	
			});
		}	
		else {
			$scope.isExistsSensor = new View.existsSensor();
			$scope.isExistsSensor.$view({_csrf : angular.element('#_csrf').val(), id:sensors[0].uid }, function() {	 			 
				$scope.existSensor = ($scope.isExistsSensor.resultType == "YES_DATA");	
			});
		}
	}
 
	 $scope.refreshDetectors = function() {
		 $scope.getManufacturers();
		 $scope.getDetectors();		 
		 $scope.getTransmitters();
		 $scope.getSensors();
		 $scope.detectorImage = "/assets/img/cover.jpg";
	 }
	 
	 $scope.keypress = function($event) {
	    $scope.lastKey = $event.keyCode
	    $scope.detectorNameExist = "false";
	  };
	 
	 $scope.refreshDetectors();
	 angular.element('body').removeClass('loading');
	 
	 $timeout(function () {                    
		 $scope.loadEvents();
	 }, 500);
});