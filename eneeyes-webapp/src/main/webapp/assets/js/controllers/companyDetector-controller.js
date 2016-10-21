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


app.controller('companyDetectorController', function ($scope, $timeout, $filter, CompanyDeviceService, CompanyDetectorService, DetectorService, AlarmService, CompanyDetectorAlarmService, CompanyService ) {

	var loadGauge = false;
	
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.$root.selectedCompany = $scope.listOne.t;
			 $scope.itens = getTree();
			 $scope.loadTreview($scope.itens);			 
	    });		 
	}
	
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
	
	$scope.deleteCompanyDetector = function() 
	{		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new CompanyDetectorService.deletar();	
		
		$scope.deletar.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid}, function(){
			
			angular.element('body').removeClass('loading');
			
			$scope.getOneCompany($scope.companyUid);
									
			$scope.msgDanger = "Detector Excluída!!" ;
	        $('#resultDanger').hide().show('slow').delay(1000).hide('slow');         	         	
        }, function(data) {
        	$scope.msgErro = "Erro: " + statusText;
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
			$scope.getCompanyDetectorAlarms();
        });		 
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
				
				$timeout(function () {	
					google.charts.setOnLoadCallback(drawGaugesDetector);
				}, 100);
				
			}				
						
		}, 300);
		
		$("#stepTabDetector_1").trigger("click");	
	 }
	
	drawGaugesDetector = function() {
					
		var sensors = $scope.selectedCompanyDetector.detectorDto.sensorsDto;		
		
		for (var j = 0; j < sensors.length; j++) {
			
			var selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == sensors[j].uid ; });

			var red = selectedAlarm.length == 0 || selectedAlarm == null ? sensors[j].rangeMax : selectedAlarm[0].alarmDto.alarm3 ;
			var yellow = selectedAlarm.length == 0 || selectedAlarm == null ? sensors[j].rangeMax : selectedAlarm[0].alarmDto.alarm2 ;
			var orange = selectedAlarm.length == 0 || selectedAlarm == null ? sensors[j].rangeMax : selectedAlarm[0].alarmDto.alarm1 ;
			
			var gaugeOptions = {
			     //width: 350, height: 140,
			     min: sensors[j].rangeMin, max: sensors[j].rangeMax,			     
			     redFrom: red, redTo: sensors[j].rangeMax,
			     yellowFrom: yellow, yellowTo: red,
			     greenFrom: orange, greenTo: yellow, 
			     minorTicks: 5
			};
						
			var gaugeData = google.visualization.arrayToDataTable([
              	['Label', 'Value'],
              	['Sensor ' + (j + 1), 0],
              ]);
		    
		    console.log('sensor_' + sensors[j].$$hashKey);			    
		    gauge = new google.visualization.Gauge(document.getElementById('sensor_' + sensors[j].$$hashKey));
		    gauge.draw(gaugeData, gaugeOptions);			
		}	    		
	}
	
	$scope.getCompanyDetectorAlarms = function() {
		
		$scope.resultCompanyDetectorAlarm = new CompanyDetectorAlarmService.listPorCompanyDetectorAlarm();		 
		$scope.resultCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid}, function(){			
			$scope.selectedCompanyDetectorAlarms = $scope.resultCompanyDetectorAlarm.list;
        });		 
	}
	
	$scope.getAlarms = function() {
		 
		 $scope.resultAlarms = new AlarmService.listAll();		 
		 $scope.resultAlarms.$alarm({_csrf : angular.element('#_csrf').val()}, function(){			 
			 $scope.alarms = $scope.resultAlarms.list;			 
        });		 
	}
	
	$scope.configAlarm = function(index) {
		
		$scope.selectedSensor = $scope.selectedCompanyDetector.detectorDto.sensorsDto[index];
		
		if($.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == $scope.selectedSensor.uid ; }).length != 0 )			
			$scope.selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == $scope.selectedSensor.uid ; })[0].alarmDto ;		
		
		$scope.search = { unitMeterGases: $scope.selectedSensor.unitMeterGases, gas : $scope.selectedCompanyDetector.detectorDto.sensorsDto[index].gasesDto[0].name };
		
		$timeout(function () {
			$('#modalAlarm').modal({ show: 'false' });
		}, 300);
	}
	
	$scope.selecionarAlarm = function(uid) {
		
		/* Obtem Alarm selecionado */
		var selectedAlarm = $.grep($scope.alarms, function (e) { return e.uid == uid ; })[0];		

	
		alarm = {
		 		alarmDto : selectedAlarm, 
		 		companyDetectorDto: $scope.selectedCompanyDetector, 
		 		sensorId : $scope.selectedSensor.uid
		 };
		$scope.saveCompanyDetectorAlarm(alarm);		
		$scope.mostrarAlarmTela(selectedAlarm);		

		 alarm = {
		 		alarmDto : selectedAlarm, 
		 		companyDetectorDto: $scope.selectedCompanyDetector, 
		 		sensorId : $scope.selectedSensor.uid
		 };
		
		$scope.saveCompanyDetectorAlarm(alarm);		
		$scope.mostrarAlarmTela(selectedAlarm);	
		

	}
	
	$scope.mostrarAlarmTela = function(selectedAlarm) {
				
		/* Verifica se o Sensor possui Alarm */
		var detectorAlarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(img => img.sensorId === $scope.selectedSensor.uid);
	
		if (detectorAlarmIndex < 0)  /* Add - TELA  */		
			$scope.selectedCompanyDetectorAlarms.push({alarmDto :  selectedAlarm, sensorId : $scope.selectedSensor.uid});		
		else /* UPD TELA */
			$scope.selectedCompanyDetectorAlarms[detectorAlarmIndex] = {alarmDto :  selectedAlarm, sensorId : $scope.selectedSensor.uid};
			
	}
		
	$scope.removerAlarm = function(uid) {		

		var selectedAlarm = $.grep($scope.alarms, function (e) { return e.uid == uid ; })[0];
				
		alarm = {
		 		alarmDto : selectedAlarm, 
		 		companyDetectorDto: $scope.selectedCompanyDetector, 
		 		sensorId : $scope.selectedSensor.uid
		 };
		
		$scope.deleteCompanyDetectorAlarm = new CompanyDetectorAlarmService.deletar(alarm);
		$scope.deleteCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val()}, function(){		
			
			angular.element('body').removeClass('loading');
			$scope.msgInfo = "Alarm Excluído!" ;
			$('#resultInfo').hide().show('slow').delay(1000).hide('slow');
		
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
		});
		
		$scope.removerAlarmTela(selectedAlarm);		
	}
	
	$scope.removerAlarmTela = function(alarm) {
				
		var alarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(img => img.alarmDto.uid === alarm.uid);		 
		$scope.selectedCompanyDetectorAlarms.splice( alarmIndex, 1);	
		$scope.selectedAlarm = undefined;
		
	}
	
	$scope.saveCompanyDetectorAlarm = function(alarm) {
		angular.element('body').addClass('loading');
 
		$scope.inclusaoCompanyDetectorAlarm = new CompanyDetectorAlarmService.save(alarm);
		$scope.inclusaoCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val()}, function(){		
			
			angular.element('body').removeClass('loading');
			$scope.msgInfo = "Alarm Gravado!" ;
			$('#resultInfo').hide().show('slow').delay(1000).hide('slow');
		
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
		});			 
	}
	
	
	$scope.selectedUnit = $scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex];
	$scope.selectedArea = $scope.selectedUnit.areasDto[$scope.$root.selecteds.areaIndex];
	$scope.selectedCompanyDevice = $scope.selectedArea.companyDevicesDto[$scope.$root.selecteds.CompanyDeviceIndex]
	
	$scope.getAlarms();
	
	if ($scope.selectedCompanyDevice != null)
		$scope.getOneCompanyDetector();
	else 
		$scope.getDetectors();
	
	$scope.initializeDetector();
		
		
});