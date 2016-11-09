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


app.controller('companyDetectorController', function ($scope, $interval, $timeout, $filter, CompanyDeviceService, CompanyDetectorService, DetectorService, AlarmService, CompanyDetectorAlarmService, CompanyService, PositionService ) {

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
			$scope.showErro("Ops!! " + data.statusText);
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
			
			//Se for um Company Detector novo ou nÃ£o associado a um Detector
			if($scope.selectedCompanyDetector.uid == undefined) {
				$scope.selectedCompanyDetector = $scope.inclusaoCompanyDetector.t;
				$scope.getCompanyDetectorAlarms();
				initGaugeDetector();
			}		
			
			$scope.showInfo("Detector Gravado!") ;
					
		}, function(data) {
			$scope.showErro("Ops!! " + data.statusText);
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
        	$scope.showErro("Ops!! " + data.statusText);
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
			
			//* Detector jï¿½ foi associado a dispositivo checa alarmes *//
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
					
					google.charts.load('current', { 'packages': ['gauge', 'corechart'] });
					
					loadGauge = true;
					
					var current = angular.copy($scope.selectedCompanyDetector);
					
					if(current != null) {					
						$scope.$root.timer.push($interval(function(){
							$scope.getPositions(current);					        
					    }, 13000));						
					}
				}
				
				$timeout(function () {	
					
					google.charts.setOnLoadCallback(initDrawGaugesDetector);
					google.charts.setOnLoadCallback(drawBackgroundColor);
					
				}, 100);				
			}				
						
		}, 300);
		
		$("#stepTabDetector_1").trigger("click");	
	 }
	 
	 var data_json = "[[1443226500000, 60], [1443233700000, 40], [1443244500000, 50], [1443255300000, 60], [1443267900000, 60], [1443278700000, 60], [1443287700000, 60], [1443298500000, 55], [1443309300000, 60], [1443317400000, 60], [1443321900000, 28], [1443325500000, 25], [1443326400000, 60], [1443338100000, 40], [1443349800000, 60], [1443349800000, 60], [1443359700000, 60], [1443359700000, 60], [1443369600000, 60], [1443382200000, 80], [1443393000000, 85], [1443400200000, 40], [1443409200000, 75], [1443420000000, 60]]";
	 
	 function drawBackgroundColor() {
	      var data = new google.visualization.DataTable();
	      
	      data.addColumn('date', 'Date');
	      data.addColumn('number', 'Formula');
		
	      var idata = JSON.parse(data_json);
	      
	      for(var i in idata){
	          idata[i][0] = new Date(idata[i][0]);
	          
	      }
	      data.addRows(idata);

	      var options = {
	    	  width: 800,    	 
	    	  hAxis: {
	          title: 'Data'
	        },
	        vAxis: {
	          title: 'Medições'
	        },
	        backgroundColor: '#f1f8e9',
	        viewWindow:{
                max:100,
                min:99.8
              }
	      };

	      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
	      chart.draw(data, options);
	    }
	
	initDrawGaugesDetector = function() {
		var current = angular.copy($scope.selectedCompanyDetector);
		
		if(current != null) 												
			$scope.getPositions(current);
					    		
	}
	
	formatGaugeSensor = function(sensor, value, id) {
		var selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == sensor.uid ; });

		var red = selectedAlarm == null  || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm3;
		var yellow = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm2;
		var orange = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm1;
		
		var gaugeOptions = {

			 min: sensor.rangeMin, max: sensor.rangeMax,			     
		     redFrom: red, redTo: red == 0 ? 0 : sensor.rangeMax,
		     yellowFrom: yellow, yellowTo: red,
		     greenFrom: orange, greenTo: yellow, 
		     minorTicks: 5
		};
					
		var gaugeData = google.visualization.arrayToDataTable([
          	['Label', 'Value'],
          	['Sensor ', 0],
          ]);
	    		
		objGauge = document.getElementById(id);
		
		if (objGauge == undefined) {
			console.log('Objeto:: ' + id + "Não localizado:: " + new Date())
		}
		else {
			gauge = new google.visualization.Gauge(objGauge);
							
		    gauge.draw(gaugeData, gaugeOptions);
		    gaugeData.setValue(0, 1 , value);
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
		
		initDrawGaugesDetector();
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
			$scope.showErro("Ops!! " + data.statusText);
		});
		
		$scope.removerAlarmTela(selectedAlarm);		
	}
	
	$scope.removerAlarmTela = function(alarm) {
				
		var alarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(item => item.alarmDto.uid === alarm.uid);		 
		$scope.selectedCompanyDetectorAlarms.splice( alarmIndex, 1);	
		$scope.selectedAlarm = undefined;
		initDrawGaugesDetector();
		
	}
	
	$scope.saveCompanyDetectorAlarm = function(alarm) {
		angular.element('body').addClass('loading');
 
		$scope.inclusaoCompanyDetectorAlarm = new CompanyDetectorAlarmService.save(alarm);
		$scope.inclusaoCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val()}, function(){		
						
			$scope.showInfo("Alarme Gravado!") ;
					
		}, function(data) {
			$scope.showErro("Ops!! " + data.statusText);
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
	}
	
	$scope.getPositions = function(currentCompanyDetector) {
		
		 $scope.listOnePosition = new PositionService.listOneByCompanyDetector();		 
		 $scope.listOnePosition.$position({_csrf : angular.element('#_csrf').val(), id : currentCompanyDetector.uid}, function(){		

			 if($scope.listOnePosition.list != null && $scope.listOnePosition.list.length != 0) {
		
				for (var j = 0; j < currentCompanyDetector.detectorDto.sensorsDto.length; j++) {
											
					var item = $.grep($scope.listOnePosition.list, function (e) { return e.sensorDto.uid == currentCompanyDetector.detectorDto.sensorsDto[j].uid ; });
					var id = 'companyDetector_' + currentCompanyDetector.uid + '-sensor_' + currentCompanyDetector.detectorDto.sensorsDto[j].uid;
					
					formatGaugeSensor(currentCompanyDetector.detectorDto.sensorsDto[j], item[0].lastValue, id);
					
				}
			}
			else {
				for (var j = 0; j < currentCompanyDetector.detectorDto.sensorsDto.length; j++) {
					var id = 'companyDetector_' + currentCompanyDetector.uid + '-sensor_' + currentCompanyDetector.detectorDto.sensorsDto[j].uid;
					formatGaugeSensor(currentCompanyDetector.detectorDto.sensorsDto[j], 0, id);
				}
			}	
	    });			
	}
		
	$scope.selectedUnit = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex]);
	$scope.selectedArea = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex]);	
	$scope.selectedCompanyDevice = angular.copy($scope.selectedArea.companyDevicesDto[$scope.$root.selecteds.CompanyDeviceIndex]);
	
	$scope.getAlarms();
	
	if ($scope.selectedCompanyDevice != null)
		$scope.getOneCompanyDetector();
	else 
		$scope.getDetectors();
	
	$scope.initializeDetector();		
});