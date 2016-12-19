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


app.controller('companyDetectorController', function ($scope, $interval, $timeout, $filter, CompanyDeviceService, CompanyDetectorService, DetectorService, AlarmService, CompanyDetectorAlarmService, CompanyService, PositionService, HistoricService) {

	var loadGoogleCharts = false;
	
	angular.element('body').addClass('loading');
	
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
			
			//Se for um Company Detector novo ou n�o associado a um Detector
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
			
			$scope.showDanger("Detector Exclu�do!");
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
			
			//* Detector j� foi associado a dispositivo checa alarmes *//
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
				 
		if(! loadGoogleCharts) {				
			google.charts.load('current', { 'packages': ['gauge', 'corechart'] });				
			loadGoogleCharts = true;
		}
				 		 
		$timeout(function () {
			
			$('.tabDetector a').on('click', function (event) {
			    event.preventDefault();
			    
			    //Limpa Timers
			    while ($scope.$root.timer.length) {				        	
		            $interval.cancel($scope.$root.timer.pop());				            
		         }
			    
			    if ($(event.target).attr('href') == "#tabCompanyDetector_2") {			    	
			    	
			    	google.charts.setOnLoadCallback(initDrawGaugesDetector);
			    	initGaugeTimer();
				}
			    else if ($(event.target).attr('href') == "#tabCompanyDetector_3") {
			    	
					google.charts.setOnLoadCallback(initChartLinesDetector);					
			    }			
			});
											
			initGaugeTimer = function() {
													
				var current = angular.copy($scope.selectedCompanyDetector);
				
				if(current != null) {					
					$scope.$root.timer.push($interval(function(){
						$scope.getPositions(current);					        
				    }, 13000));						
				}						
			}			
	
			initDatatable();
															
		}, 300);
				
		$("#stepTabDetector_1").trigger("click");
		
		$timeout(function () {
			angular.element('body').removeClass('loading');				
		}, 200);
	 }
	 
	function initDatatable() {
	
		var table = $('#example').DataTable({
            "bLengthChange": false,
            "filter": false,
            "info": false,
            "iDisplayLength": 6,
            "paging": false,            
            "dom": '<"top"fl<"clear">>rt<"bottom"ip<"clear">>'
        });		
		
		$("#example tbody").on("click", "td.details-control", 
			function() {
				var tr = $(this).closest("tr");
				var row = table.row(tr);
	
				if (row.child.isShown()) {
					row.child.hide();
					tr.removeClass("shown");
				}
				else {
					row.child( format(row.data()[1]) ).show();
					tr.addClass("shown");
				}
		});
	 }
	
	function format ( d ) {
		
		//var index = $scope.detectors.findIndex(item => item.name === d);
		var index = $scope.detectors.findIndex(function(item) {return item.name === d});
		var detector = $scope.detectors[index];
	
		if(detector.sensorsDto.length == 1) {
			return '<div class="slider">'+
	        '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	            '<tr>'+ 
            		'<th>Sensor</th>'+
            		'<th>Nome</th>'+
            		'<th>Gás</th>'+
            	'</tr>'+
	        	'<tr>'+ 
	        		'<td>01</td>'+
	                '<td>'+detector.sensorsDto[0].name+'</td>'+	                
	                '<td>'+detector.sensorsDto[0].gasesDto[0].name+'</td>'+
	            '</tr>'+            
	           '</table>'+
	       '</div>';		
		}
		else {			
			return '<div class="slider">'+
	        '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	            '<tr>'+ 
            		'<th>Sensor</th>'+
            		'<th>Nome</th>'+
            		'<th>Gás</th>'+
            	'</tr>'+
	        	'<tr>'+ 
	        		'<td>01</td>'+
	                '<td>'+detector.sensorsDto[0].name+'</td>'+	                
	                '<td>'+detector.sensorsDto[0].gasesDto[0].name+'</td>'+
	            '</tr>'+
	            '<tr>'+
	                '<td>02</td>'+
	                '<td>'+detector.sensorsDto[1].name+'</td>'+
	                '<td>'+detector.sensorsDto[1].gasesDto[0].name+'</td>'+
	            '</tr>'+
	           '</table>'+
	       '</div>';
		}
	}
		
	function initDrawGaugesDetector() {
		var current = angular.copy($scope.selectedCompanyDetector);
		
		if(current != null) 												
			$scope.getPositions(current);
					    		
	}
	
	function initChartLinesDetector() {
		var current = angular.copy($scope.selectedCompanyDetector);
		
		if(current != null) 												
			$scope.getHistorics(current, 3);
					    		
	}
	
	function formatGaugeSensor(sensor, value, id) {
		var selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == sensor.uid ; });

		var red =    selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm3;
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
			console.log('Objeto:: ' + id + "N�o localizado:: " + new Date())
		}
		else {
			gauge = new google.visualization.Gauge(objGauge);
							
		    gauge.draw(gaugeData, gaugeOptions);
		    gaugeData.setValue(0, 1 , value);
		    gauge.draw(gaugeData, gaugeOptions);
		}
	}	
	 
	function formatLineSensor(sensor, value, id) {
		
		var selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == sensor.uid ; });
		
		var red =    selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm3;
		var yellow = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm2;
		var orange = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm1;
		
		var data = new google.visualization.DataTable();
	      
	    data.addColumn('string', 'Date');
	    data.addColumn('number', 'Medições');
    
	    var changeDate = '';  
	    for(var i in value) {
	    	var itemDate = new Date(value[i][0]);

	    	if (changeDate != itemDate.toLocaleDateString())
	    		value[i][0] = itemDate.toLocaleTimeString() + '\r'  + itemDate.toLocaleDateString();
	    	else
	    		value[i][0] = itemDate.toLocaleTimeString()

	    	changeDate = itemDate.toLocaleDateString();

		}
	      
	    data.addRows(value);

	    var options = {
	          title: "Medições no Período",
	          titleTextStyle: { color: '#FF0000' },
	          legend: { position: 'none' },	          
	    	  width: 800,
	    	  height: 500,
	    	  hAxis: {
	    		  title: 'Data',
	    		  color: '#333', count: 5,
	    		  logscale: true
	    	  },
	    	  vAxis: {
	    		  title: 'Alarmes',
	    		  titleTextStyle: { color: '#FF0000' },
	    		  maxValue:sensor.rangeMax,
	              minValue:0,
	    		  ticks: [0, orange, yellow, red, sensor.rangeMax]
	    	  },
	    	  curveType: 'function',
	          explorer: {},
	          pointSize:3	        
	      };
	    
	    var chart = new google.visualization.LineChart(document.getElementById(id));
	    chart.draw(data, options);
	
	}	
		
	$scope.selecionarDetector = function(item) {
		if ($scope.selectedCompanyDetector ==  undefined)
			$scope.selectedCompanyDetector = [];
		
		$scope.selectedCompanyDetector.detectorDto = item;
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
		//var detectorAlarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(img => img.sensorId === $scope.selectedSensor.uid);
		var detectorAlarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(function (i) { return i.sensorId === $scope.selectedSensor.uid});
				
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
				
		//var alarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(item => item.alarmDto.uid === alarm.uid);
		var alarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex( function(item) { return item.alarmDto.uid === alarm.uid });
		
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
		 $scope.listOnePosition.$position({_csrf : angular.element('#_csrf').val(), id : currentCompanyDetector.uid}, function() {		
			 
			for (var j = 0; j < currentCompanyDetector.detectorDto.sensorsDto.length; j++) {
				
				var item = 0;					
				if($scope.listOnePosition.list != null && $scope.listOnePosition.list.length != 0) {									
					item = $.grep($scope.listOnePosition.list, function (e) { return e.sensorDto.uid == currentCompanyDetector.detectorDto.sensorsDto[j].uid ; });
				}
				
				var id = 'gauge_companyDetector_' + currentCompanyDetector.uid + '-sensor_' + currentCompanyDetector.detectorDto.sensorsDto[j].uid;					
				formatGaugeSensor(currentCompanyDetector.detectorDto.sensorsDto[j], item == 0 ? 0 : item[0].lastValue, id);					
			}				
			
	    });			
	}
	
	$scope.getHistorics = function(currentCompanyDetector, interval) {
		
		$scope.listInterval = new HistoricService.listInterval();		
		$scope.listInterval.$historic({_csrf : angular.element('#_csrf').val(),  
			companyDetectorId: currentCompanyDetector.uid,			 
			interval: interval }, function(){
			var itens = [];	
			for (var j = 0; j < currentCompanyDetector.detectorDto.sensorsDto.length; j++) {
				
				var item = 0;					
				if($scope.listInterval.list != null && $scope.listInterval.list.length != 0) {									
					
					var itens = new Array();
					for (var k = 0; k < $scope.listInterval.list.length; k++) {
						
						if( $scope.listInterval.list[k].sensorDto.uid == currentCompanyDetector.detectorDto.sensorsDto[j].uid)
							itens.push([$scope.listInterval.list[k].lastUpdate, $scope.listInterval.list[k].value ] );
					}
				}
				
				var id = 'line_companyDetector_' + currentCompanyDetector.uid + '-sensor_' + currentCompanyDetector.detectorDto.sensorsDto[j].uid;					
				formatLineSensor(currentCompanyDetector.detectorDto.sensorsDto[j], itens, id);					
			}      	
       });		
	}
		
	$scope.selectedUnit = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex]);
	$scope.selectedArea = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex]);	
	$scope.selectedCompanyDevice = angular.copy($scope.selectedArea.companyDevicesDto[$scope.$root.selecteds.CompanyDeviceIndex]);
	
	$scope.getAlarms();
	
	if ($scope.selectedCompanyDevice != null)
		$scope.getOneCompanyDetector();
	else {		
		$scope.getDetectors();
	}	
	
	$scope.initializeDetector();	
	
});