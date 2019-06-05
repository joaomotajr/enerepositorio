app.controller('genericController', function ($scope, $timeout, DeviceTypeService, UnitMeterService, GenericService, ManufacturerService) {
	
	$scope.saveGeneric = function() {

		var exists =  $scope.generics.findIndex(function (i) { return i.name.toLowerCase() === $scope.genericName.toLowerCase() });
		
		if ( exists >= 0 && $scope.genericUid == undefined) {
			 $scope.genericNameExist = "true";
			 return;
		}
		
		angular.element('body').addClass('loading');		
		var generic = {
			uid: $scope.genericUid != undefined ? $scope.genericUid : 0,			
			manufacturerDto: {uid: $scope.genericManufacturer.uid},					
			deviceType: {uid: $scope.deviceType.uid},			
			unitMeter: {uid: $scope.unitMeter.uid},
			name: $scope.genericName,
			model: $scope.genericModel,
			image: $scope.genericImage,
			rangeMax: $scope.genericRangeMax,
			rangeMin: $scope.genericRangeMin,
    	}; 
		 
		$scope.inclusaoGeneric = new GenericService.save(generic);		 
		$scope.inclusaoGeneric.$generic({_csrf : angular.element('#_csrf').val()}, function()
		{         	
            $timeout(function () {                    
            	$scope.clearFormGeneric();
                $scope.getGenerics();
	                     	
	            angular.element('body').removeClass('loading');				 
	         }, 500);			
		});		 
	};
	 
	$scope.clearFormGeneric = function () {	
		$scope.genericUid = undefined;		
		$scope.genericManufacturer = '';	
		$scope.deviceType = '';
		$scope.unitMeter = '';
		$scope.genericName = '';
		$scope.genericModel = '';
		$scope.genericRangeMin = '';
		$scope.genericRangeMax = '';
		$scope.genericImage = "/assets/img/cover.jpg";
	}
	 
	$scope.getGenerics = function() {		 
		 $scope.resultGenerics = new GenericService.listAll();		 
		 $scope.resultGenerics.$generic({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.generics = $scope.resultGenerics.list; 		 			 
         });		 
	 };

	 $scope.loadEvents = function() {
		
		$('#idInputImageGeneric').change( encodeImageFileAsURL( function(base64Img) {		    
		    $scope.genericImage =  base64Img;
			$scope.$apply();		    
		}));
		
		 $('#idChooseFileGeneric').click(function(event) {
		    event.preventDefault();	    
		    $('#idInputImageGeneric').trigger('click');	    
		 });		 
		  
		 $scope.addPhoto = function() {
			 event.preventDefault();
			$('#idInputImageGeneric').trigger('click');
		 }		
	};
	
	$scope.getManufacturers = function() {		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 };	 
 
	$scope.editGeneric = function (index) {
	    $scope.genericUid = $scope.generics[index].uid;	        
		$scope.genericName = $scope.generics[index].name;
		$scope.genericModel = $scope.generics[index].model;
		$scope.genericImage = ($scope.generics[index].image == null ? "/assets/img/cover.jpg" :  $scope.generics[index].image);
		$scope.genericManufacturer = $scope.generics[index].manufacturerDto;			
		$scope.genericRangeMin = $scope.generics[index].rangeMin;
		$scope.genericRangeMax = $scope.generics[index].rangeMax;
		$scope.deviceType = $scope.generics[index].deviceType;
		$scope.unitMeter = $scope.generics[index].unitMeter;
	    $('#idGenericName').focus();
	};
	 
	$scope.deleteGeneric = function(index) {
		 
		 var uid = $scope.generics[index].uid;		 
		 $scope.deletar = new GenericService.deletar();		 
		 $scope.deletar.$generic({_csrf : angular.element('#_csrf').val(), id : uid}, function(){
			 if (!$scope.deletar.isError)
				 $scope.generics.splice(index, 1);
			 else {
				 $scope.msgErro = "Erro: " + $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }
		});		 
	 }; 

	$scope.getDeviceTypes = function() {		 
		$scope.listAllDeviceType = new DeviceTypeService.listAllDeviceType();		 
		$scope.listAllDeviceType.$deviceType({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.deviceTypes = $scope.listAllDeviceType.list;
	   });		 
	};
	
	$scope.getUnitMeters = function() {		 
		$scope.listAllUnitMeter = new UnitMeterService.listAllUnitMeter();		 
		$scope.listAllUnitMeter.$unitMeter({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.unitMeters = $scope.listAllUnitMeter.list;
	   });		 
	};
	
	$scope.refreshGenerics = function() {
		$scope.getGenerics();
		$scope.getManufacturers();
		$scope.getDeviceTypes();
		$scope.getUnitMeters();
		$scope.genericImage = "/assets/img/cover.jpg";
	};
	
	$scope.unitMeterChange = function() {
		if($scope.unitMeter.uid==0) {
			$scope.genericRangeMin = 0;
			$scope.genericRangeMax = 1;
		}
	};
	 
	$scope.refreshGenerics();
	angular.element('body').removeClass('loading');	

	$timeout(function () {                    
		$scope.loadEvents();
	}, 500);
});