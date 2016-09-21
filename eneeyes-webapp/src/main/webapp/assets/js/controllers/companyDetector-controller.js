app.filter('gasFilter', function () {
    return function (objects, criteria) {
        var filterResult = new Array();

        if (!criteria.unitMeterGases || !criteria.gas)
            return objects;

        for (index in objects) {
            
        	 if (objects[index].unitMeterGases == criteria.unitMeterGases && objects[index].gasDto.name == criteria.gas  ) {

                 filterResult.push(objects[index]);
             }   
        }

        return filterResult;
    }
});


app.controller('companyDetectorController', function ($scope, $timeout, $filter, CompanyDeviceService, CompanyDetectorService, DetectorService, AlarmService) {

	var loadGauge = false;
	
	$scope.saveCompanyDetectorCoords = function() {
		
		var detectorsCoords = JSON.parse(localStorage.getItem('easypin'));		
		var item = $scope.selectedCompanyDetectorsArea;
		
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
			
			angular.element('body').removeClass('loading');
			$scope.msgInfo = "Detector Gravado!" ;
			$('#resultInfo').hide().show('slow').delay(1000).hide('slow');
		
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
		});			 
	}
	
	
	$scope.saveCompanyDetector = function() {
		angular.element('body').addClass('loading');
		
		var companyDetector = {
			uid: $scope.selectedCompanyDetector.uid == undefined ? 0 : $scope.selectedCompanyDetector.uid,
			name: $scope.selectedCompanyDetector.name,
			companyDeviceDto: {uid : $scope.selectedCompanyDevice.uid},
			detectorDto: {uid: $scope.selectedCompanyDetector.detectorDto.uid},
			local: $scope.selectedCompanyDetector.local,
			description: $scope.selectedCompanyDetector.description
		 }
		 
		$scope.inclusaoCompanyDetector = new CompanyDetectorService.save(companyDetector);
		$scope.inclusaoCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val()}, function(){		
			
			angular.element('body').removeClass('loading');
			$scope.msgInfo = "Detector Gravado!" ;
			$('#resultInfo').hide().show('slow').delay(1000).hide('slow');
		
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
		});			 
	}
	
	$scope.getDetectors = function() {
		 
		 $scope.resultDetectors = new DetectorService.listAll();		 
		 $scope.resultDetectors.$detector({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.detectors = $scope.resultDetectors.list; 			 
        });		 
	 }
	
			
	$scope.getOneCompanyDetector = function() {
		
		$scope.getDetectors();
		$scope.search = { unitMeterGases: null, gas : null };
				
		$scope.resultCompanyDetector = new CompanyDetectorService.listPorCompanyDevice();		 
		$scope.resultCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDevice.uid }, function(){			
			$scope.selectedCompanyDetector = $scope.resultCompanyDetector.t;
        });		 
	}
	
//	$scope.getCompanyDetectorArea = function() {
//				
//		$scope.resultCompanyDetectorsArea = new CompanyDetectorService.listPorIdArea();		 
//		$scope.resultCompanyDetectorsArea.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid }, function(){			
//			$scope.selectedCompanyDetectorsArea = $scope.resultCompanyDetectorsArea.list;          	         	
//        });		 
//	}
		
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
			angular.element('body').removeClass('loading');
			
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
		});		 
	 }	
	
	$scope.deviceTypes = 
		[
		 	{ name : 'OUTROS', uid : 0 },
		 	{ name : 'DETECTOR', uid :  1 },
		 	{ name : 'PLC', uid : 2 },
		 	{ name : 'CONTROLADORA', uid : 3 },
		 	{ name : 'ALARME', uid : 4 } 			  	
		];
	

	 $scope.initializeDetector =  function()  {
		 
		$timeout(function () {
			
			$('.tabDetector a').on('click', function (event) {
			    event.preventDefault();	
			    
			    if ($(event.target).attr('href') == "#tabCompanyDetector_2") {			    	
			    	initGaugeDetector();
				}
			    else if ($(event.target).attr('href') == "#tabCompanyDetector_3") {
			    	
			    }			
			});
											
			initGaugeDetector = function() {				
				if(! loadGauge) {
					google.charts.load('current', { 'packages': ['gauge'] });
					loadGauge = true;
				}				
				google.charts.setOnLoadCallback(drawGaugesDetector);
			}				
						
		}, 500);
		
		$("#stepTabDetector_1").trigger("click");	
	 }
	
	drawGaugesDetector = function() {
					
		var sensors = $scope.selectedCompanyDetector.detectorDto.sensorsDto;		
		
		for (var j = 0; j < sensors.length; j++) {
			
			var gaugeOptions = {
			     //width: 350, height: 140,
			     redFrom: 90, redTo: sensors[j].rangeMax,
			     yellowFrom: 75, yellowTo: 90,
			     minorTicks: 5
			};
			
			var gaugeData = new google.visualization.DataTable();
			
			gaugeData.addColumn('number', 'Sensor ' + (j + 1) );
		    gaugeData.addRows(1);
		    gaugeData.setCell(0, 0, 0);
		    
		    console.log('sensor_' + sensors[j].$$hashKey);			    
		    gauge = new google.visualization.Gauge(document.getElementById('sensor_' + sensors[j].$$hashKey));
		    gauge.draw(gaugeData, gaugeOptions);			
		}	    		
	}
	
	$scope.getAlarms = function() {
		 
		 $scope.resultAlarms = new AlarmService.listAll();		 
		 $scope.resultAlarms.$alarm({_csrf : angular.element('#_csrf').val()}, function(){			 
			 $scope.alarms = $scope.resultAlarms.list;			 
        });		 
	}
	
	$scope.configAlarm = function(index) {
		
		$scope.search = { unitMeterGases: $scope.selectedCompanyDetector.detectorDto.sensorsDto[index].unitMeterGases, gas : $scope.selectedCompanyDetector.detectorDto.sensorsDto[index].gasesDto[0].name };
		$('#modalAlarm').modal({ show: 'false' });
	}
	
	$scope.selecionarAlarm = function(index) {
	
	}
	
	$scope.selectedUnit = $scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex];
	$scope.selectedArea = $scope.selectedUnit.areasDto[$scope.$root.selecteds.areaIndex];
	$scope.selectedCompanyDevice = $scope.selectedArea.companyDevicesDto[$scope.$root.selecteds.CompanyDeviceIndex]
	$scope.$root.masterName = "Junior";
	
	$scope.getAlarms();
	$scope.getOneCompanyDetector();
	$scope.initializeDetector();
		
		
});