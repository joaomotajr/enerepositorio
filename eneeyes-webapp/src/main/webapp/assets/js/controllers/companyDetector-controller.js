app.filter('gasFilter', function () {
    return function (objects, criteria) {
        var filterResult = new Array();

        if (criteria && !criteria.unitMeterGases != null && !criteria.gas)
            return objects;

        for (index in objects) {
        	
        	if (objects[index].unitMeterGases == criteria.unitMeterGases && objects[index].gasDto.name == criteria.gas  ) {

        		filterResult.push(objects[index]);
            }   
        }

        return filterResult;
    }
});


app.controller('companyDetectorController', function ($scope, $interval, $rootScope, $timeout, $filter, CompanyDeviceService, 
		CompanyDetectorService, DetectorService, AlarmService, CompanyDetectorAlarmService, CompanyService, 
		PositionService, HistoricService, CompanyDetectorMaintenanceHistoricService) {

	var loadGoogleCharts = false;
	
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.$root.selectedCompany = $scope.listOne.t;
			 $scope.itens = getTree();
			 $scope.loadTreview($scope.itens);			 
	    });		 
	}
	
	$scope.saveCompanyDetector = function() {
		angular.element('body').addClass('loading');
						
		var companyDetector = {
			uid: $scope.selectedCompanyDetector.uid == undefined ? 0 : $scope.selectedCompanyDetector.uid,
			name: $scope.selectedCompanyDetector.name.toUpperCase(),
			companyDeviceDto: {uid : $scope.selectedCompanyDevice.uid},
			detectorDto: {uid: $scope.selectedCompanyDetector.detectorDto.uid},
			local: $scope.selectedCompanyDetector.local,
			serialNumber: $scope.selectedCompanyDetector.serialNumber,
			description: $scope.selectedCompanyDetector.description,
			deliveryDate : getDate($('#deliveryDate').val()), 
			garantyDays: $scope.selectedCompanyDetector.garantyDays,
			descriptionDelivery: $scope.selectedCompanyDetector.descriptionDelivery,			
			installDate : getDate($('#installDate').val()),
			descriptionInstall : $scope.selectedCompanyDetector.descriptionInstall,
			maintenanceInterval : $scope.selectedCompanyDetector.maintenanceInterval
		 }
		 
		$scope.inclusaoCompanyDetector = new CompanyDetectorService.save(companyDetector);
		$scope.inclusaoCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val()}, function(){		
			
			//Se for um Company Detector novo ou nao associado a um Detector
			if($scope.selectedCompanyDetector.uid == undefined) {
				$scope.selectedCompanyDetector = $scope.inclusaoCompanyDetector.t;				
				//$scope.getCompanyDetectorAlarms();

				$scope.getOneCompany($scope.companyUid);
			}		
			
			angular.element('body').removeClass('loading');			
			$rootScope.showGeneralMessage($scope.inclusaoCompanyDetector.message, 'SUCCESS');	

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
		    $scope.selectedCompanyDetector.serialNumber = 0;			
		    $('#deliveryDate').val('');
			$scope.selectedCompanyDetector.garantyDays = 0;
			$scope.selectedCompanyDetector.descriptionDelivery = '';;			
			$('#installDate').val('');
			$scope.selectedCompanyDetector.descriptionInstall = '';;
			$scope.selectedCompanyDetector.maintenanceInterval = 0;
		    
		}, 100);
	}	
	
	$scope.deleteCompanyDetector = function() {		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new CompanyDetectorService.deletar();	
		
		$scope.deletar.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid}, function(){
			$rootScope.showGeneralMessage($scope.deletar.message, 'DANGER');		

			$scope.clearCompanyDetector();
			$scope.getOneCompany($scope.companyUid);
			
			angular.element('body').removeClass('loading');
	                 	         	
		});		 
	}
	
	$scope.getDetectors = function() {
		 
		 $scope.resultDetectors = new DetectorService.listAll();		 
		 $scope.resultDetectors.$detector({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.detectors = $scope.resultDetectors.list; 			 
        });		 
	 }	
			
	$scope.getOneCompanyDetector = function() {

		$scope.search = { unitMeterGases: null, gas : null };
				
		$scope.resultCompanyDetector = new CompanyDetectorService.listPorCompanyDevice();		 
		$scope.resultCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDevice.uid }, function(){			
			
			$scope.selectedCompanyDetector = $scope.resultCompanyDetector.t;			
						
			//* Detector ja foi associado a dispositivo checa alarmes *//
			if($scope.selectedCompanyDetector != null) {
				$scope.getCompanyDetectorAlarms();
				reloadDates();
				$scope.getCompanyDetectorMaintenanceHistoric();
				$scope.getPositionsNoTimer($scope.selectedCompanyDetector);
			}
        });		 
	}
	
	function reloadDates() {
		
		if($scope.selectedCompanyDetector.deliveryDate != null) {
			var dataAdm = new Date($scope.selectedCompanyDetector.deliveryDate);	
	        $('#deliveryDate').val(dataAdm.getUTCDate() + "/" + (dataAdm.getUTCMonth() + 1) + "/" + dataAdm.getUTCFullYear());	
		}
		
		if($scope.selectedCompanyDetector.installDate != null) {
			var dataAdm = new Date($scope.selectedCompanyDetector.installDate);	
	        $('#installDate').val(dataAdm.getUTCDate() + "/" + (dataAdm.getUTCMonth() + 1) + "/" + dataAdm.getUTCFullYear());	        
		}	
	}
		
	$scope.deviceTypes = 
		[
		 	// { name : 'OUTROS', uid : 0 },
		 	{ name : 'DETECTOR', uid :  1 },
		 	// { name : 'PLC', uid : 2 },
		 	// { name : 'CONTROLADORA', uid : 3 },
		 	// { name : 'ALARME', uid : 4 } 			  	
		];	

	 $scope.initializeDetector =  function()  {
				 
		if(! loadGoogleCharts) {				
			google.charts.load( 'visualization', '1', { 'packages': ['gauge', 'corechart'] });				
			loadGoogleCharts = true;
		}
		
		$("#datemask").inputmask("dd/mm/yyyy", { "placeholder": "dd/mm/yyyy" });
	    $("#datemask2").inputmask("mm/dd/yyyy", { "placeholder": "mm/dd/yyyy" });
	    $("[data-mask]").inputmask();
	    
	    initControlEvents();
				 		 
		$timeout(function () {
														
			initGaugeTimer = function() {
													
				var current = angular.copy($scope.selectedCompanyDetector);
				
				if(current != null) {					
					$scope.$root.timer.push($interval(function(){
						if($rootScope == null) return;
						if($rootScope.currentPage == "Empresas")
							$scope.getPositions(current);     
						
				    }, 5000));						
				}						
			}
			
			initChartTimer = function() {
				
				var current = angular.copy($scope.selectedCompanyDetector);
				
				if(current != null) {					
					$scope.$root.timer.push($interval(function(){
						if($scope.$root.currentPage == "Empresas")
							$scope.getHistorics(current, 1);						
				    }, 5000));						
				}						
			}
	
			initDatatable();
															
		}, 500);
		   
		$("#stepTabDetector_1").trigger("click");
	 }	 
	 
	 initControlEvents = function() {
		
		$('.tabDetector a').on('click', function (event) {
		    event.preventDefault();
			    
		    //Limpa Timers
		    while ($scope.$root.timer.length) {				        	
		    	$interval.cancel($scope.$root.timer.pop());				            
		    }
			    
			google.charts.setOnLoadCallback(initDrawGaugesDetector);
			google.charts.setOnLoadCallback(initChartLinesDetector);
			    
			if ($(event.target).attr('href') == "#tabCompanyDetector_2") {			    	
				initGaugeTimer();
			}
			else if ($(event.target).attr('href') == "#tabCompanyDetector_3") {
			
				initChartTimer();
			}			
		});
	 }
	 
	 $scope.validDeliveryDate = function ($event) {

        if ($('#deliveryDate').val().match(/[^0-9\/]/g) != null) {
            $scope.deliveryDateValid = true;
        }	        
        else if ($('#deliveryDate').val() == '') {
            $scope.deliveryDateValid = true;
        }
        else {
            $scope.deliveryDateValid = false;
        }
    }
	 
	 $scope.validInstallDate = function ($event) {

        if ($('#installDate').val().match(/[^0-9\/]/g) != null) {
            $scope.installDateValid = true;
        }
        else if ($('#installDate').val() == '') {
            $scope.installDateValid = true;
        }
        else {
            $scope.installDateValid = false;
        }
    }
	 
	function initDatatable() {
	
		var table = $('#sensorDetails').DataTable({
            "bLengthChange": false,
            "filter": false,
            "info": false,
            "iDisplayLength": 6,
            "paging": false,            
            "dom": '<"top"fl<"clear">>rt<"bottom"ip<"clear">>'
        });		
		
		$("#sensorDetails tbody").on("click", "td.details-control", 
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
		
		var index = $scope.detectors.findIndex(function(item) {return item.name === d});
		var detector = $scope.detectors[index];
	
		if(detector.sensorsDto.length == 1) {
			return '<div class="slider">'+
	        '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	            '<tr>'+ 
            		'<th>Sensor</th>'+
            		'<th>Nome</th>'+
            		'<th>G&aacutes</th>'+
            		'<th>Range</th>'+
            		'<th>Medi&ccedil;&atilde;o </th>'+
            	'</tr>'+
	        	'<tr>'+ 
	        		'<td>1o.</td>'+
	                '<td>'+detector.sensorsDto[0].name+'</td>'+	                
	                '<td>'+detector.sensorsDto[0].gasesDto[0].name+'</td>'+
	                '<td>'+detector.sensorsDto[0].rangeMin + '-' + +detector.sensorsDto[0].rangeMax +'</td>'+
	                '<td>'+detector.sensorsDto[0].unitMeterGases + '</td>'+
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
            		'<th>G&aacute;s</th>'+
            		'<th>Range</th>'+
            		'<th>Medi&ccedil;&atilde;o </th>'+          		
            	'</tr>'+
	        	'<tr>'+ 
	        		'<td>1o.</td>'+
	                '<td>'+detector.sensorsDto[0].name + '</td>' +	                
	                '<td>'+detector.sensorsDto[0].gasesDto[0].name+'</td>'+
	                '<td>'+detector.sensorsDto[0].rangeMin + '-' + +detector.sensorsDto[0].rangeMax +'</td>'+
	                '<td>'+detector.sensorsDto[0].unitMeterGases + '</td>'+
	            '</tr>'+
	            '<tr>'+
	                '<td>2o.</td>'+
	                '<td>'+detector.sensorsDto[1].name+'</td>'+
	                '<td>'+detector.sensorsDto[1].gasesDto[0].name+'</td>'+
	                '<td>'+detector.sensorsDto[1].rangeMin + '-' + +detector.sensorsDto[0].rangeMax +'</td>'+
	                '<td>'+detector.sensorsDto[1].unitMeterGases + '</td>'+
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
	
	$scope.getPositionsNoTimer = function(currentCompanyDetector) {
		
		$scope.listOnePositionNoTimer = new PositionService.listOneByCompanyDetector();		 
		$scope.listOnePositionNoTimer.$position({_csrf : angular.element('#_csrf').val(), id : currentCompanyDetector.uid}, function() {
			 
			if ($scope.listOnePositionNoTimer.list == undefined || $scope.listOnePositionNoTimer.list.length == 0) return;

			for (var j = 0; j < currentCompanyDetector.detector.sensors.length; j++) {				
					
				var offDate = ((new Date() - new Date($scope.listOnePositionNoTimer.list[j].lastUpdate)) / 1000) > 300;
				$scope.alarmesFired[j] = offDate ? "OFFLINE" : $scope.listOnePositionNoTimer.list[j].alarmType;
			}
			 
		});
	}
	
	$scope.alarmesFired = [];
	
	$scope.getPositions = function(currentCompanyDetector) {
		
		$scope.listOnePosition = new PositionService.listOneByCompanyDetector();		 
		$scope.listOnePosition.$position({_csrf : angular.element('#_csrf').val(), id : currentCompanyDetector.uid}, function() {		

			if ($scope.listOnePosition.list == undefined || $scope.listOnePosition.list.length == 0) return;
			 
			for (var j = 0; j < currentCompanyDetector.detector.sensors.length; j++) {
				
				var offDate = ((new Date() - new Date($scope.listOnePosition.list[j].lastUpdate)) / 1000) > 300;
				$scope.alarmesFired[j] = offDate ? "OFFLINE" : $scope.listOnePosition.list[j].alarmType;
				
				var item = 0;					
				if($scope.listOnePosition.list != null && $scope.listOnePosition.list.length != 0) {									
					item = $.grep($scope.listOnePosition.list, function (e) { return e.sensorDto.uid == currentCompanyDetector.detector.sensors[j].uid ; });
				}
				
				var id = 'gauge_companyDetector_' + currentCompanyDetector.uid + '-sensor_' + currentCompanyDetector.detector.sensors[j].uid;					
				formatGaugeSensor(currentCompanyDetector.detector.sensors[j], item == 0 ? 0 : item[0], id);					
			}				
			
	    });			
	}
	
	function formatGaugeSensor(sensor, item, id) {
		var selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == sensor.uid ; });

		var red =    selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm3;
		var yellow = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm2;
		var orange = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm1;
		
		var gaugeOptions = {
			 min: sensor.rangeMin, max: sensor.rangeMax,			     
		     redFrom: red, redTo: red == 0 ? 0 : sensor.rangeMax,
		     yellowFrom: yellow, yellowTo: red,
		     greenFrom: orange, 
		     greenColor: "gray",
		     greenTo: yellow, 
		     minorTicks: 5
		};
					
		var gaugeData = google.visualization.arrayToDataTable([ ['Label', 'Value'], ['Id: ' + item.uid, 0],]);
	    		
		objGauge = document.getElementById(id);
		
		if (objGauge == undefined) {
			console.log('Objeto:: ' + id + "Nao localizado:: " + new Date())
		}
		else {
			gauge = new google.visualization.Gauge(objGauge);
							
		    gaugeData.setValue(0, 1 , item.lastValue);
		    gauge.draw(gaugeData, gaugeOptions);
		}
	}	
	
	function initChartLinesDetector() {
		var current = angular.copy($scope.selectedCompanyDetector);
		
		if(current != null) 												
			$scope.getHistorics(current, 1);
					    		
	}
	
	$scope.getHistorics = function(currentCompanyDetector, interval) {
		
		$scope.listInterval = new HistoricService.listIntervalDetector();		
		$scope.listInterval.$historic({_csrf : angular.element('#_csrf').val(),  
			companyDetectorId: currentCompanyDetector.uid,			 
			interval: interval }, function(){
			
			var itens = [];	
			for (var j = 0; j < currentCompanyDetector.detectorDto.sensorsDto.length; j++) {
				
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
	 
	function formatLineSensor(sensor, value, id) {
		
		var selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == sensor.uid ; });
		
		var red =    selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm3;
		var yellow = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm2;
		var orange = selectedAlarm == null || selectedAlarm.length <= 0 ? 0 : selectedAlarm[0].alarmDto.alarm1;
		
		var data = new google.visualization.DataTable();
	      
	    data.addColumn('string', 'Date');
	    data.addColumn('number', 'Valor');
    
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
	    
	    if ($scope.changeGraphic) {
		    var options = {
		          title: "Dados do Sensor na �ltima Hora.",
		          legend: {position: 'none'},
		          'lineWidth': 0.75,
		    	  width: 850,
		    	  height: 400,
		    	  hAxis: {
		    		  title: 'Data', 
		    		  textPosition: 'none',
		    		  textStyle: {
	                          'color': '#8C8C8C',
	                              'fontName': 'Calibri',
	                              'fontSize': 9,
	                      },
		    	  },
		    	  vAxis: {
		    		  maxValue:sensor.rangeMax,
		              minValue:0,
		              textStyle: {
	                      'color': '#8C8C8C',
	                          'fontName': 'Calibri',
	                          'fontSize': 9,
	                          'fontStyle' : 'bold',
	                  },
		    		  ticks: [ 
		    		           {v:0, f: 'Range Minimo: 0' }, 
		    		           {v: orange, f: 'Dete��o: ' + orange}, 
		    		           {v: yellow, f: 'Alerta: ' + yellow}, 
		    		           {v: red, f: 'Evacua��o: ' + red}, 
		    		           {v: sensor.rangeMax, f: 'Range M�ximo: ' + sensor.rangeMax} 
		    		        ]
		    	  },
		    	  //curveType: 'function',
		          pointSize:1	        
		      };
	    }
		else {
			var options = {
		          title: "Dados do Sensor na �ltima Hora.",
		          legend: {position: 'none'},
		          'lineWidth': 0.75,
		    	  width: 850,
		    	  height: 400,
		    	  hAxis: {
		    		  title: 'Data', 
		    		  textPosition: 'none',
		    		  textStyle: {
	                          'color': '#8C8C8C',
	                              'fontName': 'Calibri',
	                              'fontSize': 9,
	                      },
		    	  },
		    	  vAxis: {
		              textStyle: {
	                      'color': '#8C8C8C',
	                          'fontName': 'Calibri',
	                          'fontSize': 9,
	                          'fontStyle' : 'bold',
	                  },
		    	  },
		    	  //curveType: 'function',
		          pointSize:1	        
		      };
		    	
		}
	    
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
		
		$scope.resultAlarms = new AlarmService.listByCompanyId();
		 $scope.resultAlarms.$alarm({_csrf : angular.element('#_csrf').val(), companyId : $scope.$root.selectedCompany.uid }, function(){			 
			 $scope.alarms = $scope.resultAlarms.list;			 
        });		 
	}
	
	$scope.configAlarm = function(index) {
		
		$scope.sensorIndex = index; 
		$scope.selectedSensor = $scope.selectedCompanyDetector.detectorDto.sensorsDto[index];
		
		if($.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == $scope.selectedSensor.uid ; }).length != 0 )			
			$scope.selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == $scope.selectedSensor.uid ; })[0].alarmDto;		
		else
			$scope.selectedAlarm = undefined;
		
		$scope.search = { unitMeterGases: $scope.selectedSensor.unitMeterGases, gas : $scope.selectedCompanyDetector.detectorDto.sensorsDto[index].gasesDto[0].name };
		
		$timeout(function () {
			$('#modalAlarm').modal({ show: 'false', backdrop: 'static', keyboard:'false' });
						 
		}, 300);
	}
	 
	$scope.selecionarAlarm = function(uid, index) {
		
		/* Obtem Alarm selecionado */
		var selectedAlarm = $.grep($scope.alarms, function (e) { return e.uid == uid ; })[0];
		
		if($scope.selectedSensor.rangeMax < selectedAlarm.alarm3) {
			$scope.msgErroAlarm = "Alarm Selecionado excede Range Max do Sensor, Verifique";
			return;
		}
		
		$scope.msgErroAlarm = undefined;
		
		alarm = {
		 		alarmDto : selectedAlarm, 
		 		companyDetectorDto: $scope.selectedCompanyDetector, 
		 		sensorId : $scope.selectedSensor.uid
		 };
		
		$scope.saveCompanyDetectorAlarm(alarm);		
		$scope.mostrarAlarmTela(selectedAlarm);
		
		$scope.configAlarm($scope.sensorIndex); 
	}
	
	$scope.mostrarAlarmTela = function(selectedAlarm) {
				
		/* Verifica se o Sensor possui Alarm */
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

		angular.element('body').addClass('loading');
		
		var selectedAlarm = $.grep($scope.alarms, function (e) { return e.uid == uid ; })[0];
				
		alarm = {
		 		alarmDto : selectedAlarm, 
		 		companyDetectorDto: $scope.selectedCompanyDetector, 
		 		sensorId : $scope.selectedSensor.uid
		 };
		
		$scope.deleteCompanyDetectorAlarm = new CompanyDetectorAlarmService.deletar(alarm);
		$scope.deleteCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val()}, function(){		
		
			angular.element('body').removeClass('loading');
				
		});
		
		$scope.removerAlarmTela(selectedAlarm);		
	}
	
	$scope.removerAlarmTela = function(alarm) {
				
		var alarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex( function(item) { return item.alarmDto.uid === alarm.uid });
		
		$scope.selectedCompanyDetectorAlarms.splice( alarmIndex, 1);	
		$scope.selectedAlarm = undefined;
		initDrawGaugesDetector();
		
	}
	
	$scope.saveCompanyDetectorAlarm = function(alarm) {
		angular.element('body').addClass('loading');
 
		$scope.inclusaoCompanyDetectorAlarm = new CompanyDetectorAlarmService.save(alarm);
		$scope.inclusaoCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val()}, function(){		

			angular.element('body').removeClass('loading');
		});			 
	}

	$scope.getCompanyDetectorMaintenanceHistoric = function() {		 
		
		$scope.companyDetectorMaintenanceHistoric = new CompanyDetectorMaintenanceHistoricService.listPorCompanyDetector();			
		$scope.companyDetectorMaintenanceHistoric.$companyDetectorMaintenanceHistoric({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid}, function(){});		 
	}
	
	/* ------------------------------------- Inicio Processamento --------------------------------------------*/
	
	if($scope.$root.selecteds.unitIndex != undefined) {
		
		$scope.selectedUnit = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex]);
		$scope.selectedArea = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex]);	
		$scope.selectedCompanyDevice = angular.copy($scope.selectedArea.companyDevicesDto[$scope.$root.selecteds.CompanyDeviceIndex]);
		
		$scope.getDetectors();
		$scope.getAlarms();
		
		if ($scope.selectedCompanyDevice != null)
			$scope.getOneCompanyDetector();			
	}
	
	$scope.initializeDetector();
		
	/* ------------------------------------------------------------------------------------------------------- */
});