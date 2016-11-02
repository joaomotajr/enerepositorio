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


app.controller('companyDetectorController', function ($scope, $timeout, $filter, CompanyDeviceService, CompanyDetectorService, DetectorService, AlarmService, CompanyDetectorAlarmService, CompanyService, PositionService ) {

	var loadGauge = false;
	
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
	
	$scope.showErro = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.$root.msgErro = msg;        
	}
	
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
			
			$scope.showInfo("Detector Gravado!");
					
		}, function(data) {			
			$scope.showErro("Erro: " + data.statusText);
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
								
			$scope.showInfo("Detector Gravado!") ;
					
		}, function(data) {
			$scope.showErro("Erro: " + data.statusText);
		});			 
	}
	
	$scope.clearCompanyDetector = function () {
		$scope.selectedCompanyDetector.detectorDto.image = "/assets/img/cover.jpg"
		$timeout(function () {						
		    $scope.selectedCompanyDetector.uid = undefined;
		    $scope.selectedCompanyDetector.name = '';
		    $scope.selectedCompanyDevice.uid = undefined;	    	    
		    $scope.selectedCompanyDetector.detectorDto = '';	    
		    $scope.selectedCompanyDetector.local = '';
		    $scope.selectedCompanyDetector.description = '';
		}, 100);
	}
	
	
	$scope.deleteCompanyDetector = function() 
	{		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new CompanyDetectorService.deletar();	
		
		$scope.deletar.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid}, function(){
			
			$scope.showDanger("Detector Excluído!");
			$scope.clearCompanyDetector();
			$scope.getOneCompany($scope.companyUid);			
	                 	         	
        }, function(data) {
        	$scope.showErro("Erro: " + data.statusText);
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
			
			//* Detector já foi associado a dispositivo checa alarmes *//
			if($scope.selectedCompanyDetector != null)
				$scope.getCompanyDetectorAlarms();
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
					
					setInterval(function() {
						
						if($scope.selectedCompanyDetector != null) 												
							$scope.getPositions($scope.selectedCompanyDetector);
																	
				    }, 13000);
				}
				
				$timeout(function () {	
					google.charts.setOnLoadCallback(drawGaugesDetector);										
				}, 100);				
			}				
						
		}, 300);
		
		$("#stepTabDetector_1").trigger("click");	
	 }
	
	drawGaugesDetector = function() {
				
		if($scope.selectedCompanyDetector == null) return;
		
		var sensors = $scope.selectedCompanyDetector.detectorDto.sensorsDto;		
		for (var j = 0; j < sensors.length; j++) {			
			formatGaugeSensor(sensors[j], 0); 			
		}	    		
	}
	
	formatGaugeSensor = function(sensor, value) {
		var selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == sensor.uid ; });

		var red = selectedAlarm == null  || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm3;
		var yellow = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm2;
		var orange = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm1;
		
		var gaugeOptions = {
		     min: sensor.rangeMin, max: sensor.rangeMax,			     
		     redFrom: red, redTo: sensor.rangeMax,
		     yellowFrom: yellow, yellowTo: red,
		     greenFrom: orange, greenTo: yellow, 
		     minorTicks: 5
		};
					
		var gaugeData = google.visualization.arrayToDataTable([
          	['Label', 'Value'],
          	['Sensor ', 0],
          ]);
	    
	    console.log('sensor_' + sensor.$$hashKey);			    
	    gauge = new google.visualization.Gauge(document.getElementById('sensor_' + sensor.$$hashKey));		    
	    
	    gauge.draw(gaugeData, gaugeOptions);
	    gaugeData.setValue(0, 1 , value);
	    gauge.draw(gaugeData, gaugeOptions);
		
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
	}
	
	$scope.mostrarAlarmTela = function(selectedAlarm) {
				
		/* Verifica se o Sensor possui Alarm */
		var detectorAlarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(img => img.sensorId === $scope.selectedSensor.uid);
				
		if (detectorAlarmIndex < 0) {
			/* Add - TELA  */
			$scope.selectedCompanyDetectorAlarms.push({alarmDto :  selectedAlarm, sensorId : $scope.selectedSensor.uid});
		}		
		else {
			/* Upd TELA */
			$scope.selectedCompanyDetectorAlarms[detectorAlarmIndex] = {alarmDto :  selectedAlarm, sensorId : $scope.selectedSensor.uid};
		}
		
		drawGaugesDetector();
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
						
			$scope.showDanger("Alarme Excluído!");			
		
		}, function(data) {
			$scope.showErro("Erro: " + data.statusText);
		});
		
		$scope.removerAlarmTela(selectedAlarm);		
	}
	
	$scope.removerAlarmTela = function(alarm) {
				
		var alarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(item => item.alarmDto.uid === alarm.uid);		 
		$scope.selectedCompanyDetectorAlarms.splice( alarmIndex, 1);	
		$scope.selectedAlarm = undefined;
		drawGaugesDetector();
		
	}
	
	$scope.saveCompanyDetectorAlarm = function(alarm) {
		angular.element('body').addClass('loading');
 
		$scope.inclusaoCompanyDetectorAlarm = new CompanyDetectorAlarmService.save(alarm);
		$scope.inclusaoCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val()}, function(){		
						
			$scope.showInfo = "Alarme Gravado!" ;
					
		}, function(data) {
			$scope.showErro("Erro: " + data.statusText);
		});			 
	}
	
	$scope.getPosition = function(currentSensor) {
		
		 $scope.listOnePosition = new PositionService.listOneBySensor();		 
		 $scope.listOnePosition.$position({_csrf : angular.element('#_csrf').val(), id : currentSensor.uid}, function(){		

			 if($scope.listOnePosition.t != null) {
				 var currentPosition = $scope.listOnePosition.t.lastValue;			 
				 formatGaugeSensor(currentSensor, currentPosition);			  
			 }
			 			 
	    });		 
		
		;
	}
	
	$scope.getPositions = function(currentCompanyDetector) {
		
		 $scope.listOnePosition = new PositionService.listOneByCompanyDetector();		 
		 $scope.listOnePosition.$position({_csrf : angular.element('#_csrf').val(), id : currentCompanyDetector.uid}, function(){		


			 if($scope.listOnePosition.list != null && $scope.listOnePosition.list.length != 0) {			 
				var sensors = currentCompanyDetector.detectorDto.sensorsDto;				 
				for (var j = 0; j < sensors.length; j++) {
											
					var item = $.grep($scope.listOnePosition.list, function (e) { return e.sensorDto.uid == sensors[j].uid ; });
					if (item[0].lastValue > 0)
					formatGaugeSensor(sensors[j], item[0].lastValue );										
				}}			 			 
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