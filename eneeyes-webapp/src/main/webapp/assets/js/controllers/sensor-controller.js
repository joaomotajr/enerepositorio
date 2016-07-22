
app.controller('sensorController', function ($scope, $timeout, $filter, SensorService, ManufacturerService, GasService) {
	    
	
	$scope.saveSensor = function() {
		
		
		for (var i = 0; i < $scope.newGases.length; i++) {
			$scope.sensorGases.push($scope.newGases[i]);	            	             
         } 		 
				
		var sensor = {
			uid: $scope.sensorUid != undefined ? $scope.sensorUid : 0,
			name: $scope.sensorName,
			manufacturer: $scope.sensorManufacturer,
			model: $scope.sensorModel,
			detectionType: $scope.sensorDetectionType.uid,
			gasesDto: $scope.sensorGases
    	}; 
		 
		$scope.inclusaoSensor = new SensorService.save(sensor);		 
		$scope.inclusaoSensor.$sensor({_csrf : angular.element('#_csrf').val()}, function()
		{         	
         	
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
	    $scope.sensorDetectionType = '';
	    
	    $scope.sensorGases = [];
	    $scope.newGases = [];
	}

	 
	$scope.getSensors = function() {
		 
		 $scope.resultSensors = new SensorService.listAll();		 
		 $scope.resultSensors.$sensor({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.sensors = $scope.resultSensors.list;
			 
			 
			 $scope.clearFormSensor();			 
			 
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
 
	 $scope.editSensor = function (index) {
	        $scope.sensorUid = $scope.sensors[index].uid;
	        
	        $scope.sensorManufacturer = $scope.sensors[index].manufacturer;
		    $scope.sensorName = $scope.sensors[index].name;
		    $scope.sensorModel = $scope.sensors[index].model;
		    $scope.sensorDetectionType = $scope.getDetectionTypes($scope.sensors[index].detectionType);
		    $scope.sensorGases = $scope.sensors[index].gasesDto;
		    		    		    
		    $timeout(function () {                    
                $scope.inicializaLDragDrop();
            }, 1000);
		    
		    $scope.newGases = [];
		    		    
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
	 
	 $scope.getGases = function() {
		 
		 $scope.resultGases = new GasService.listAll();		 
		 $scope.resultGases.$gas({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.gases = $scope.resultGases.list; 		 			 
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
		            alert("ATENÇÃO! Gás Já Existe.");
		        }
		        else {
		            
		            //Insere Tag Novo se elemento não foi movido dentro da própria lista
		            if (ui.item.find('small').length < 1) {
		                ui.item.append('<small class="label label-success"><i class="fa fa-bolt"></i> novo</small>');
		                $scope.addGasSensor(ui.item[0].id);
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
	 
	 $scope.addGasSensor = function (idGas) {

	        gas = {
	            uid: idGas	            
	        }

	        $scope.newGases.push(gas);

	        $scope.$apply();
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
	 $scope.getGases();

});