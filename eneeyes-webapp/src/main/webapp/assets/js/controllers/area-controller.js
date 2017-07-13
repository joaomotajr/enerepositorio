app.controller('areaController', function ($scope, $rootScope, $interval, $timeout, $filter, AreaService, CompanyDetectorService, 
		DetectorService, CompanyDeviceService, CompanyService, PositionService, CompanyDetectorAlarmService) {

	var loadGoogleCharts = false;
	
	$scope.isLock = true;
    $scope.btnLockUnlock = 'Unlock';    
		
	$scope.showDanger = function(msg) {		
		angular.element('body').removeClass('loading');
		 $scope.$root.msgDanger = msg ;
        $('#resultDanger').hide().show('slow').delay(1000).hide('slow');	
	}
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.$root.msgInfo = msg;
        $('#resultInfo').hide().show('slow').delay(1000).hide('slow');
	}

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
			deviceType: $scope.sensorDetectionType.uid,
			areaDto: {uid : $scope.selectedArea.uid}				
		};
		
		$scope.inclusaoCompanyDevice = new CompanyDeviceService.save(companyDevice);
		$scope.inclusaoCompanyDevice.$companyDevice({_csrf : angular.element('#_csrf').val()}, function(){         	
		        	           
			$scope.getOneCompany($scope.companyUid);			
			$scope.sensorDetectionType = '';			
			$scope.showInfo($scope.inclusaoCompanyDevice.message);
		
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
			
			$scope.showInfo($scope.inclusaoArea.message);		
		
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
			
			$scope.showInfo($scope.updateLatitudeLongitude.message);		
		
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
	}
	
	$scope.deleteArea = function() 
	{		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new AreaService.deletar();	
		
		$scope.deletar.$area({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid}, function(){
			
			$scope.clearFormArea();
			$scope.getOneCompany($scope.companyUid);
			
			$scope.showDanger($scope.deletar.message);	                 	         	        
		});		 
	}	
	
	$scope.getCompanyDetectorArea = function() {
		
		$scope.resultCompanyDetectorsArea = new CompanyDetectorService.listPorAreaId();		 
		$scope.resultCompanyDetectorsArea.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid }, function(){			
			$scope.selectedCompanyDetectorsArea = $scope.resultCompanyDetectorsArea.list;    
			
			// TODO Precisa rever a busca de alarms pela área, pois não retorna o detector
			if($scope.selectedCompanyDetectorsArea != null)
				$scope.getCompanyDetectorAlarmsArea();
			
        });		 
	}
	
	$scope.getCompanyDetectorAlarmsArea = function() {
		
		$scope.resultCompanyDetectorAlarmArea = new CompanyDetectorAlarmService.listPorAreaId();		 
		$scope.resultCompanyDetectorAlarmArea.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid}, function(){			
			$scope.selectedCompanyDetectorAlarmsArea = $scope.resultCompanyDetectorAlarmArea.list;
        });		 
	}
	 
	 /*--------------------------------------------------------------------------   M A P S  &  E V E N T S -----------------------------------------------------------------------*/
	 
	 $scope.initializeArea =  function()
	 {		 
		 if(! loadGoogleCharts) {
			google.charts.load('current', { 'packages': ['gauge'] });
			loadGauge = true;
		 }
		 
		 $timeout(function () {
			 
			google.charts.setOnLoadCallback(initDrawGaugesArea);
			 
			$('.tabArea a').on('click', function (event) {
			    event.preventDefault();  
			    
			    if ($(event.target).attr('href') == "#tabArea_2") {			    	
			    	initializeEasyPin();			    	
				}
			    else if ($(event.target).attr('href') == "#tabArea_3") {
			    	initGaugeTimerAreas();
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
			
			initGaugeTimerAreas = function() {
				
				var current = angular.copy($scope.selectedCompanyDetectorsArea);
				
				if(current != null) {					
					$scope.$root.timer.push($interval(function(){
						if($rootScope == null) return;
						if($rootScope.currentPage == "Empresas")
							$scope.getPositions(current);     						
				    }, 5000));						
				}						
			}
						
		}, 500);
		
		$("#stepTabArea_1").trigger("click");
		
		$timeout(function () {
			angular.element('body').removeClass('loading');				
		}, 200);
	 }	  
	 
	function initDrawGaugesArea() {
		var current = angular.copy($scope.selectedCompanyDetectorsArea);
		
		if(current != null) 												
			$scope.getPositions(current);
					    		
	}
	
	$scope.getPositions = function() {
		
		$scope.listPositions = new PositionService.listByAreaId();		 
		$scope.listPositions.$position({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid }, function(){
			 
			 for (var i = 0; i < $scope.selectedCompanyDetectorsArea.length; i++) {
				 
				currentCompanyDetector = $scope.selectedCompanyDetectorsArea[i]; 
				
				for (var j = 0; j < currentCompanyDetector.detectorDto.sensorsDto.length; j++) {
					
					var item = [];	
					
					if($scope.listPositions.list != null && $scope.listPositions.list.length != 0) {									
						
						$scope.listPositions.list.forEach(
							function(e) {
								if (e.companyDetectorDto.uid == currentCompanyDetector.uid &&
									e.sensorDto.uid == currentCompanyDetector.detectorDto.sensorsDto[j].uid) {										
									item.push(e);
								}
							}
						);						
					}

					var id = 'sensor_' + currentCompanyDetector.detectorDto.sensorsDto[j].$$hashKey;				
					formatGaugeSensor(currentCompanyDetector, currentCompanyDetector.detectorDto.sensorsDto[j], item == 0 ? 0 : item[0], id);					
				}				
			 }				
			
	    });			
	}
	
	function formatGaugeSensor(detector, sensor, item, id) {

		var selectedAlarm = [] ;
		
		$scope.selectedCompanyDetectorAlarmsArea.forEach(
				function(e) {
					if ( e.sensorId == sensor.uid &&
						 e.companyDetectorDto.uid == detector.uid) {										
						 selectedAlarm.push(e);
					}
				}
			);		

		var red =    selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm3;
		var yellow = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm2;
		var orange = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm1;
		
		var gaugeOptions = {
			 width: 300, height: 150,
			 min: sensor.rangeMin, max: sensor.rangeMax,			     
		     redFrom: red, redTo: red == 0 ? 0 : sensor.rangeMax,
		     yellowFrom: yellow, yellowTo: red,
		     greenFrom: orange, 
		     greenColor: "gray",
		     greenTo: yellow, 
		     minorTicks: 5
		};
							
		var gaugeData = google.visualization.arrayToDataTable([
          	['Label', 'Value'],
          	['Id: ' + item.uid, 0],
          ]);
	    		
		objGauge = document.getElementById(id);
		
		if (objGauge == undefined) {
			console.log('Objeto:: ' + id + "Não localizado:: " + new Date())
		}
		else {
			gauge = new google.visualization.Gauge(objGauge);

		    gaugeData.setValue(0, 1 , item.lastValue);
		    gauge.draw(gaugeData, gaugeOptions);
		}
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
			 init: { imgDipositivosArea },           
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
	
	$scope.deviceTypes = 
	[
	 	{ name : 'OUTROS', uid : 0, disabled : true },	 	
	 	{ name : 'PLC', uid : 2, disabled : true },
	 	{ name : 'CONTROLADORA', uid : 3, disabled : true },
	 	{ name : 'ALARME', uid : 4, disabled : true },
	 	{ name : 'DETECTOR', uid :  1, disabled : false }
	];

	/* ------------------------------------- Inicio Processamento --------------------------------------------*/
	
	if($scope.$root.selecteds.unitIndex != undefined) {
		
		$scope.selectedUnit = {};
		$scope.selectedArea = {};
	
		angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex], $scope.selectedUnit);
		angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex], $scope.selectedArea);
	
		$scope.getCompanyDetectorArea();		

		$scope.btnNewArea = true;
		$scope.initializeArea();
	}
	
	
	/* ------------------------------------------------------------------------------------------------------- */
		
});