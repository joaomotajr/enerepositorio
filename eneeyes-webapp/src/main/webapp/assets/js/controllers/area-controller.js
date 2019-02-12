app.controller('areaController', function ($scope, $rootScope, $interval, $timeout, $filter, AreaService, CompanyDetectorService, 
		CompanyDeviceService, CompanyService, DeviceTypeService) {

	var loadGoogleCharts = false;
	
	$scope.isLock = true;
    $scope.btnLockUnlock = 'Unlock';    

	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.$root.selectedCompany = $scope.listOne.t;
			 $scope.itens = getTree();
			 $scope.loadTreview($scope.itens);			 
	    });		 
	}
		
	$scope.lockImageArea = function() {		
		
		$("#idImageArea").toggleClass("disableDiv");
		
		 $scope.isLock = !$scope.isLock;
	     $scope.btnLockUnlock = $scope.isLock ? ' Lock ' : 'Unlock';		
	}
	
	$scope.saveCompanyDeviceInit = function() {
		
		angular.element('body').addClass('loading');
		
		var companyDevice = {
			uid: 0,
			deviceType : {uid: $scope.selectedDeviceType.uid},
			areaDto: {uid : $scope.selectedArea.uid}				
		};
		
		$scope.inclusaoCompanyDevice = new CompanyDeviceService.save(companyDevice);
		$scope.inclusaoCompanyDevice.$companyDevice({_csrf : angular.element('#_csrf').val()}, function(){         	
		        	           
			$scope.getOneCompany($scope.companyUid);			
			$scope.selectedDeviceType = '';			

			$rootScope.showGeneralMessage($scope.inclusaoCompanyDevice.message, 'SUCCESS') ;
		
		});		 
	 }
	
	$scope.saveArea = function() {
		angular.element('body').addClass('loading');
		
		$scope.saveCompanyDetectorCoords();
		
		var area = {
			uid: $scope.selectedArea.uid == undefined ? 0 : $scope.selectedArea.uid,
			name: $scope.selectedArea.name,
			date: $scope.date = null,
			description: $scope.selectedArea.description,
			local: $scope.selectedArea.local,
			latitude: $scope.selectedArea.latitude,
			longitude: $scope.selectedArea.longitude,
			image: $scope.selectedArea.image,
			classified: $scope.selectedArea.classified == 1 ? true : false,	
			unitDto: {uid : $scope.selectedUnit.uid}				
		};
		 
		$scope.inclusaoArea = new AreaService.save(area);
		$scope.inclusaoArea.$area({_csrf : angular.element('#_csrf').val()}, function(){
			
			if($scope.selectedArea.uid == undefined) {
				$scope.clearFormArea();
				$scope.getOneCompany($scope.companyUid);
			}
			else {
				$scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex] = 
					$scope.selectedArea;
			}						
			$rootScope.showGeneralMessage($scope.inclusaoArea.message, 'SUCCESS');
		
		});			 
	}
	
	$scope.saveCompanyDetectorCoords = function() {
		
		var detectorsCoords = JSON.parse(localStorage.getItem('easypin'));		
		var item = $scope.selectedCompanyDetectorsArea;
		
		if(item == null || detectorsCoords == null) return;
		
		for (var i = 0; i < item.length; i++) {
	    	
			if($scope.selectedCompanyDetectorsArea[i].latitude != detectorsCoords.imgDipositivosArea[i].coords.lat ||
					$scope.selectedCompanyDetectorsArea[i].longitude != detectorsCoords.imgDipositivosArea[i].coords.long) {
			 
				$scope.selectedCompanyDetectorsArea[i].latitude = detectorsCoords.imgDipositivosArea[i].coords.lat;
			 	$scope.selectedCompanyDetectorsArea[i].longitude = detectorsCoords.imgDipositivosArea[i].coords.long;
			 			 
			 $scope.updateCompanyDetectorLatitudeLongitude($scope.selectedCompanyDetectorsArea[i].latitude, 
					 $scope.selectedCompanyDetectorsArea[i].longitude, 
					 $scope.selectedCompanyDetectorsArea[i].uid);
			}
	    }
	}
	
	$scope.updateCompanyDetectorLatitudeLongitude = function(latitude, longitude, id) {
		angular.element('body').addClass('loading');
						 
		$scope.updateLatitudeLongitude = new CompanyDetectorService.updateLatitudeLongitude();
		$scope.updateLatitudeLongitude.$companyDetector({_csrf : angular.element('#_csrf').val(), latitude: latitude, longitude: longitude, id : id }, function(){		
			
			$rootScope.showGeneralMessage($scope.updateLatitudeLongitude.message, 'SUCCESS');						
		
		});			 
	}
	
	$scope.newArea = function () {
		$scope.btnNewArea = false;
		$scope.clearFormArea();
	}
	
	$scope.clearFormArea = function () {		
		$scope.selectedArea.uid = undefined;
		$scope.selectedArea.name = '';		
		$scope.selectedArea.description = '';
		$scope.selectedArea.local = '';
		$scope.selectedArea.latitude = '';
		$scope.selectedArea.longitude = '';
		$scope.selectedArea.classified = false;		
		$scope.areaNameInit = undefined;
		$scope.sensorDetectionType = ''; 
		$('#idAreaName').select();
	};
	
	$scope.deleteArea = function() 
	{		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new AreaService.deletar();	
		
		$scope.deletar.$area({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid}, function(){
			
			$scope.clearFormArea();
			$scope.getOneCompany($scope.companyUid);
			
			$rootScope.showGeneralMessage($scope.deletar.message, 'DANGER');			
		});		 
	};	
	
	$scope.getCompanyDetectorArea = function() {
		
		$scope.resultCompanyDetectorsArea = new CompanyDetectorService.listPorAreaId();		 
		$scope.resultCompanyDetectorsArea.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid }, function(){			
			$scope.selectedCompanyDetectorsArea = $scope.resultCompanyDetectorsArea.list;					
        });		 
	};
	 
	 /*--------------------------------------------------------------------------   M A P S  &  E V E N T S -----------------------------------------------------------------------*/
	 
	 $scope.initializeArea =  function()
	 {		 
		 
		 $timeout(function () {
			 
			$('.tabArea a').on('click', function (event) {
			    event.preventDefault();  
			    
			    if ($(event.target).attr('href') == "#tabArea_2") {			    	
			    	initializeEasyPin();			    	
				}
			    else if ($(event.target).attr('href') == "#tabArea_3") {
			    
			    }
			});
			
			$('#idBtnChooseFileArea').click(function(event) {
			    event.preventDefault();	    
			    $('#idInputImageArea').trigger('click');			    
			});
			 
			$('#idInputImageArea').change( encodeImageFileAsURL( function(base64Img) {				
				$scope.selectedArea.image =  base64Img;
				$scope.$apply();					    
			}));					
						
		}, 500);
		
		$("#stepTabArea_1").trigger("click");
		
		$timeout(function () {
			angular.element('body').removeClass('loading');				
		}, 200);
	 }		
			
	initializeEasyPin = function() {
    	
		var itens;
		var limit = 0;
		$('.easy-marker').remove();
		
		if($scope.selectedArea.companyDevicesDto.length > 0) {
			
	    	itens = getDetectorsCoordinates();
	    	
	    	$timeout(function () {	    		
	    		limit = $scope.selectedCompanyDetectorsArea.length;
	    	}, 300);
	    	
	    	$timeout(function () {           
	    		easyPin(itens, limit);			    		
	    		$('.pin').trigger("click")
	    	}, 600);	    	
		}	
    	    	
    	$scope.lockImageArea();		
	}
	
	easyPin = function(itens, limit) {			
		
		var imgDipositivosArea = itens;
		
		var $easyInstance = $('.pin').easypin({
			 init: { imgDipositivosArea: itens },           
		    responsive: true,
            limit: limit
		});	
    }	
	
	getDetectorsCoordinates = function() {
				
		var pinItensString = "";
				
		var item = $scope.selectedCompanyDetectorsArea;
		for (var i = 0; i < item.length; i++) {
	    				
			var pinItem = ({
					content: item[i].name,
					uid: item[i].uid,
					coords: {
		                lat: item[i].latitude == null ? 0 :  item[i].latitude,
		                long: item[i].longitude == null ? 0 : item[i].longitude
		            }
				})				
							
			pinItensString += (String.fromCharCode(34) + i + String.fromCharCode(34) + ":" + JSON.stringify(pinItem) + (i == item.length -1 ? ', "canvas":{}' : ', '));				
			 
	    }
		
		return JSON.parse('{' + pinItensString + '}');;
	}

	$scope.getDeviceTypes = function() {		 
		$scope.listAllDeviceType = new DeviceTypeService.listAllDeviceType();		 
		$scope.listAllDeviceType.$deviceType({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.deviceTypes = $scope.listAllDeviceType.list;
	   });		 
	};

	/* ------------------------------------- Inicio Processamento --------------------------------------------*/
	
	if($scope.$root.selecteds.unitIndex != undefined) {
		
		$scope.selectedUnit = {};
		$scope.selectedArea = {};
	
		angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex], $scope.selectedUnit);
		angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex], $scope.selectedArea);
	
		$scope.getCompanyDetectorArea();		

		$scope.btnNewArea = true;
		$scope.getDeviceTypes();
		$scope.initializeArea();
	}
		
});