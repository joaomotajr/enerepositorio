
app.controller('detectorController', function ($scope, $timeout, $filter, DetectorService, ManufacturerService, SensorService, TransmitterService) {
	
	$scope.saveDetector = function() {
		
		
		for (var i = 0; i < $scope.newSensors.length; i++) {
			$scope.detectorSensors.push($scope.newSensors[i]);	            	             
         } 		 
				
		var detector = {
			uid: $scope.detectorUid != undefined ? $scope.detectorUid : 0,
			name: $scope.detectorName,
			manufacturer: $scope.detectorManufacturer,
			transmitter: $scope.detectorTransmitter,
			model: $scope.detectorModel,
			image: $scope.detectorImage,
			//detectionType: $scope.detectorDetectionType.uid,
			sensorsDto: $scope.detectorSensors
    	}; 
		 
		$scope.inclusaoDetector = new DetectorService.save(detector);		 
		$scope.inclusaoDetector.$detector({_csrf : angular.element('#_csrf').val()}, function()
		{         	
         	
         	$scope.clearFormDetector();
            $scope.getDetectors();
                     	
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
	 
	$scope.clearFormDetector = function () {
	
	    $scope.detectorUid = undefined;  
	    $scope.detectorName = '';
	    $scope.detectorModel = '';
	    $scope.detectorManufacturer = '';
	    $scope.detectorTransmitter = ''
	    //$scope.detectorDetectionType = '';
	    $scope.detectorImage = "/assets/img/cover.jpg"
	    $scope.detectorSensors = [];
	    $scope.newSensors = [];
	}

	 
	$scope.getDetectors = function() {
		 
		 $scope.resultDetectors = new DetectorService.listAll();		 
		 $scope.resultDetectors.$detector({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.detectors = $scope.resultDetectors.list;
			 
			 
			 $scope.clearFormDetector();			 
			 
			 $timeout(function () {                    
				 $scope.inicializaLDragDrop();				 
	         }, 1000);
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
	        
	        $scope.detectorManufacturer = $scope.detectors[index].manufacturer;
		    $scope.detectorName = $scope.detectors[index].name;
		    $scope.detectorModel = $scope.detectors[index].model;
		    //$scope.detectorDetectionType = $scope.getDetectionTypes($scope.detectors[index].detectionType);
		    $scope.detectorImage = $scope.detectors[index].image;
		    $scope.detectorTransmitter = $scope.detectors[index].transmitter;
		    $scope.detectorSensors = $scope.detectors[index].sensorsDto;
		    		    		    
		    $timeout(function () {                    
                $scope.inicializaLDragDrop();
            }, 1000);
		    
		    $scope.newSensors = [];
		    		    
	        $('#idDetectorName').focus();
	    }
	 
	 $scope.deleteDetector = function(index) {
		 
		 var uid = $scope.detectors[index].uid;		  
		 
		 $scope.deletar = new DetectorService.deletar();		 
		 $scope.deletar.$detector({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 
			 $scope.detectors.splice(index, 1);
         	         	
         });		 
	 }	 
	 
//	 $scope.getDetectionTypes = function (name) {
//		 
//		 for (var i = 0; i < $scope.detectionTypes.length; i++) {
//             if ($scope.detectionTypes[i].name == name) {
//                 
//            	 return $scope.detectionTypes[i];
//             }
//         } 		 
//	 }
	 
	 $scope.getSensors = function() {
		 
		 $scope.resultSensors = new SensorService.listAll();		 
		 $scope.resultSensors.$sensor({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.sensors = $scope.resultSensors.list; 		 			 
         });		 
	 }	
	 
	 $scope.inicializaLDragDrop = function () {

		$(".sort").sortable({
		    items: 'li',
		    revert: 'valid',            
		    receive: function (event, ui) {                
		        
		        ui.item.removeAttr("style");
		
		        var clazz = getClassNameWithNumberSuffix(ui.item);
		
		        $('.drag .' + clazz).draggable("option", "revert", true)
		
		        if ($('.sort .' + clazz).length > 1) {
		            $('.sort .' + clazz + ':not(:first)').remove();
		            alert("ATENÇÃO! Item Já Existe.");
		        }
		        else {
		            
		            //Insere Tag Novo se elemento não foi movido dentro da própria lista
		            if (ui.item.find('small').length < 1) {
		                ui.item.append('<small class="label label-success"><i class="fa fa-bolt"></i> novo</small>');
		                $scope.addSensorDetector(ui.item[0].id);
		            }
		        }
		    }
		});
		
		$(".drag li").draggable({            
		    revert : 'invalid',
		    connectToSortable: $('ul.sort')            
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
	 
		 	 
	function encodeImageFileAsURL(cb) {
	    return function(){
	        var file = this.files[0];
	        var reader  = new FileReader();
	        reader.onloadend = function () {
	            cb(reader.result);
	        }
	        reader.readAsDataURL(file);
	    }		    
	}
	 
	$('#inputFileToLoad').change( encodeImageFileAsURL( function(base64Img) {			
		    
	    $scope.detectorImage =  base64Img;
		$scope.$apply();
		    
	}));

	 
	 $scope.addSensorDetector = function (idSensor) {

	        sensor = {
	            uid: idSensor	            
	        }
	        $scope.newSensors.push(sensor);
	        $scope.$apply();
	 }	 
	 
	 
//	 $scope.detectionTypes = 
//		 [
//		  	{ name : 'OUTROS', uid : 0 },
//		  	{ name : 'CAT', uid :  1 },
//		  	{ name : 'FTA', uid : 2 },
//		  	{ name : 'FID', uid : 3 },
//		  	{ name : 'ECM', uid : 4 },
//		  	{ name : 'IR', uid : 5 },
//		  	{ name : 'BUT', uid : 6 }		  	
//		 ]; 
	 
	 $scope.getDetectors();
	 $scope.getManufacturers();
	 $scope.getTransmitters();
	 $scope.getSensors();
	 $scope.detectorImage = "/assets/img/cover.jpg";

});