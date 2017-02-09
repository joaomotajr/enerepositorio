app.filter('manufacturerFilter', function () {
    return function (objects, criteria) {
        
        if (!criteria)
            return objects;
        
        var filterResult = new Array();

        for (index in objects) {
        
   			 if (objects[index].manufacturerDto.uid == criteria.manufacturer.uid  ) {

                 filterResult.push(objects[index]);
             }
        }

        return filterResult;
    }
});

app.controller('detectorController', function ($scope, $timeout, $filter, DetectorService, ManufacturerService, SensorService, TransmitterService) {
		
	$scope.saveDetector = function() {		
		
		var exists =  $scope.detectors.findIndex(function (i) { return i.name.toLowerCase() === $scope.detectorName.toLowerCase() });
		
		if ( exists >= 0 && $scope.detectorUid == undefined) {
			 $scope.detectorNameExist = "true";
			 return;
		}
		
		angular.element('body').addClass('loading');
		
		for (var i = 0; i < $scope.newSensors.length; i++) {
			$scope.detectorSensors.push($scope.newSensors[i]);	            	             
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
			           
//		}, function(data) {
//			angular.element('body').removeClass('loading');
//			$scope.msgErro = "Erro: " + data.statusText;
		});			 
	 }
	 
	$scope.clearFormDetector = function () {
	
	    $scope.detectorUid = undefined;  
	    $scope.detectorName = '';
	    $scope.detectorModel = '';
	    $scope.detectorManufacturer = '';
	    $scope.detectorTransmitter = ''
	    $scope.detectorImage = "/assets/img/cover.jpg"
	    $scope.detectorSensors = [];
	    $scope.newSensors = [];
	    
	    $scope.msgSens1 = false;
	    $scope.msgSens2 = false;
	    $scope.detectorNameExist = "false";
	    
	    $('.sort .ui-draggable').remove();
		
		$scope.inicializaLDragDrop();
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
	        
	        $scope.detectorManufacturer = $scope.detectors[index].manufacturerDto;
		    $scope.detectorName = $scope.detectors[index].name;
		    $scope.detectorModel = $scope.detectors[index].model;		    		    
		    $scope.detectorImage = ($scope.detectors[index].image == null ? "/assets/img/cover.jpg" :  $scope.detectors[index].image);		    
		    $scope.detectorTransmitter = $scope.detectors[index].transmitterDto;
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
			 
			 if (!$scope.deletar.isError)
				 $scope.detectors.splice(index, 1);
			 else {
				 $scope.msgErroDetector = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }         	         	
//		 }, function(data) {		
//			 $scope.msgErro = "Erro: " + data.statusText;
		});		 
	 }	 
	 
	 $scope.getSensors = function() {
		 
		 $scope.resultSensors = new SensorService.listAll();		 
		 $scope.resultSensors.$sensor({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.sensors = angular.copy($scope.resultSensors.list);	 
         });		 
	 }	
	 
	 $scope.inicializaLDragDrop = function () {
		 
		 $scope.refreshDragDrop = function()
		 {
			 $scope.sensors = angular.copy($scope.resultSensors.list);
			 $scope.$apply();
			 $scope.inicializaLDragDrop();
		 }

		$(".sort").sortable({
		    items: 'li',
		    revert: 'valid',            
		    receive: function (event, ui) {                
		        
		        ui.item.removeAttr("style");
		
		        var clazz = getClassNameWithNumberSuffix(ui.item);
		
		        $('.drag .' + clazz).draggable("option", "revert", true)
		
		        if($scope.newSensors.length > 1 || $scope.detectorSensors.length > 1) {
					$('.sort .' + clazz).remove();	        		
					$scope.msgSens1 = true;		
					$scope.refreshDragDrop();
	        	}
		        if ($('.sort .' + clazz).length > 1) {
		            $('.sort .' + clazz + ':not(:first)').remove();
		            $scope.msgSens2 = true;
		            $scope.refreshDragDrop();
		        }
		        else {
		            
		            //Insere Tag Novo se elemento nÃo foi movido dentro da própria lista
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
	}
	
	 $scope.addSensorDetector = function (idSensor) {

        sensorDto = {
            uid: idSensor	            
        }
        $scope.newSensors.push(sensorDto);
        $scope.$apply();
	 }
	 
	 $scope.deleteSensor = function (index) {
		 $scope.detectorSensors.splice(index, 1);		 
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