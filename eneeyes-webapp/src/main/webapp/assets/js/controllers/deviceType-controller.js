
app.controller('deviceTypeController', function ($scope, $timeout, DeviceTypeService) {
			
	$scope.saveDeviceType = function() {
		
		var exists =  $scope.deviceTypes.findIndex(function (i) { return i.type.toLowerCase() === $scope.deviceTypeType.toLowerCase(); });		
		if ( exists >= 0  && $scope.deviceTypeUid == undefined ) {
			$scope.deviceTypeTypeExist = "true";
			return;
		}	
		
		angular.element('body').addClass('loading');		
		var deviceType = {
			uid: $scope.deviceTypeUid != undefined ? $scope.deviceTypeUid : 0,
			type: $scope.deviceTypeType,
			description: $scope.deviceTypeDescription,
			symbol: $scope.deviceTypeSymbol,
			graphicType: $scope.deviceTypeGraphic.type
    	}; 
		 
		$scope.inclusaoDeviceType = new DeviceTypeService.saveDeviceType(deviceType);		 
		$scope.inclusaoDeviceType.$deviceType({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$timeout(function () {				
            	$scope.clearFormDeviceType();
                $scope.getDeviceTypes();	            
	            angular.element('body').removeClass('loading');				 
	         }, 500);
		});	
	 };
	 
	$scope.clearFormDeviceType = function () {	
	    $scope.deviceTypeUid = undefined;
		$scope.deviceTypeType = '';
		$scope.deviceTypeDescription = '';
		$scope.deviceTypeSymbol = '';
		$scope.deviceTypeGraphic = '';
	}

	$scope.getDeviceTypes = function() {		 
		$scope.listAllDeviceType = new DeviceTypeService.listAllDeviceType();		 
		$scope.listAllDeviceType.$deviceType({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.deviceTypes = $scope.listAllDeviceType.list;
	   });		 
	};
 
	$scope.editDeviceType = function (index) {
	    $scope.deviceTypeUid = $scope.deviceTypes[index].uid;
	        
		$scope.deviceTypeType = $scope.deviceTypes[index].type;
		$scope.deviceTypeDescription = $scope.deviceTypes[index].description;		    	    
		$scope.deviceTypeSymbol = $scope.deviceTypes[index].symbol;		    	    
		
		$scope.deviceTypeGraphic = $scope.getGraphiType($scope.deviceTypes[index].graphicType);

	    $('#idDeviceTypeType').focus();
	};

	$scope.changeSymbol = function(s) {
		$scope.deviceTypeSymbol = s;
	};

	$scope.changeGraphic = function(s) {
		$scope.deviceTypeGraphic = s;
	};
	 
	$scope.deleteDeviceType = function(index) {		 
		 var uid = $scope.deviceTypes[index].uid;		 
		 $scope.deletar = new DeviceTypeService.deletaDeviceType();		 
		 $scope.deletar.$deviceType({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 if (!$scope.deletar.isError)
				 $scope.deviceTypes.splice(index, 1);
			 else {
				 $scope.msgErroDeviceType = $scope.deletar.message;
			 }
		});		 
	 };
	 
	 $scope.refreshDeviceTypes = function() {
		$scope.symbols = faSymbols;
		$scope.clearFormDeviceType();
		$scope.getDeviceTypes();	
	 };

	 $scope.getGraphiType = function (type) {		 
		for (var i = 0; i < $scope.images.length; i++) {
			if ($scope.images[i].type == type) {                 
				return $scope.images[i];
			}
		} 		 
	};
	 
	$scope.images = [
		{
        	img: '/assets/img/graphic_types/Bulb_Indicator.png',
			graphicType: 'Bulbo',
			type :'BULBINDICATOR'
		}, {
			img: '/assets/img/graphic_types/Cylinder_fill.png',
			graphicType: 'Cilindro',
			type:'CYLINDERFILL'
		}, {
			img: '/assets/img/graphic_types/Linear_Scale.png',
			graphicType: 'Escala Linear',
			type:'LINEARSCALE'
		}, {
			img: '/assets/img/graphic_types/Quarter_gauge.png',
			graphicType: 'Gauge 1',
			type:'QUARTERGAUGE'
		}, {
			img: '/assets/img/graphic_types/Rating_Meter.png',
			graphicType: 'Gauge 2',
			type:'RATINGMETER'
		}, {
			img: '/assets/img/graphic_types/Speedometer.png',
			graphicType: 'Velocimetro',
			type:'SPEEDOMETER'
		}, {
			img: '/assets/img/graphic_types/Thermometer_display.png',
			graphicType: 'Termometro',
			type:'THERMOMETERDISPLAY'
		}, {
			img: '/assets/img/graphic_types/Vertical_Bullet_Graph.png',
			graphicType: 'Bala Vertical',
			type:'VBULLET'
		}
	];

	$scope.refreshDeviceTypes();	 
	angular.element('body').removeClass('loading');	 
});