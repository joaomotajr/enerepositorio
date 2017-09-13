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
		CompanyDetectorMaintenanceHistoricService, ViewService) {

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
				
				$scope.getCompanyDetectorAlarms($scope.selectedCompanyDetector.uid);
				
				reloadDates();
				
				$scope.getCompanyDetectorMaintenanceHistoric();
				
			}
        });		 
	}

	$scope.getCompanyDetectorAlarms = function(companyDetectorId) {

		$scope.resultDetectors = new ViewService.listCompanyDetectorsAlarms();		 
		$scope.resultDetectors.$view({_csrf : angular.element('#_csrf').val(), companyDetectorId : companyDetectorId}, function(){						
			$scope.selectedCompanyDetectorAlarms = $scope.resultDetectors.list;

			$scope.selectedCompanyDetectorAlarms.forEach(
					 function(e) {						
						e.dataSource = $scope.getGaugeInfo(e);						
					}			
			);
			 
		});
	}

	$scope.getGaugeInfo = function(sensor) {

		var red =    sensor.alarmOn == null ? 0 : sensor.alarm3;
		var yellow = sensor.alarmOn == null ? 0 : sensor.alarm2;
		var orange = sensor.alarmOn == null ? 0 : sensor.alarm1;

		properties =  {
			
			theme: "fint",
			caption: sensor.sensorName,
			lowerLimit: sensor.rangeMin,
			upperLimit: sensor.rangeMax,
			lowerLimitDisplay: sensor.rangeMin + " Min",
			upperLimitDisplay: sensor.rangeMax + " Max",
			numberSuffix: " " + (sensor.unitMeterGases == "LEL_PERCENT" ? "LEL %" : sensor.unitMeterGases),			
			
			showValue: "1",
			valueFontSize: "12",
			origW: "400",
			origH: "150",
			ledSize: "1",
			ledGap: "1",
			manageResize: "1",			
			showhovereffect: "1",
			showValues: "0",                
			showXAxisLine: "0",
			showYAxisLine: "0",
            showSecondaryLimits : "0",		
		};

		trendPoints =  {
			point: [
				{
					startValue: orange,					
					dashed: "1",
					dashlen: "3",
					dashgap: "3",
					thickness: "2"
				},
				{
					startValue: yellow,					
					dashed: "1",
					dashlen: "3",
					dashgap: "3",
					thickness: "2"
				},
				{
					startValue: red,					
					dashed: "1",
					dashlen: "3",
					dashgap: "3",
					thickness: "2"
				}
			]
		};

		colors = {				
			color: [
			{
				minValue: sensor.rangeMin,
				maxValue: orange,
				code: "#6baa01"			
			},
			{
				minValue: orange,
				maxValue: yellow,
				code: "#D8D8D8"		
			}, {
				minValue: yellow,
				maxValue: red,
				code: "#f8bd19"		
			}, {
				minValue: red,
				maxValue: sensor.rangeMax,
				code: "#e44a00"					 	
			}]		
		}

		colors2 = {				
			color: [
			{
				minValue: sensor.rangeMin,
				maxValue: sensor.rangeMax,
				label : "Sem Limites de Alarmes",	
				code: "#6baa01"
			}]		
		}

		var value = sensor.lastValue > sensor.rangeMax ? sensor.rangeMax : sensor.lastValue;
		values = {		  		  			
			dial: [{
				id: "crntYr",
				value: value,
				showValue: "1",
				tooltext: "Status : $value",
				rearExtension: "5"
			}]			
		}

		dataSource = {
			chart: null,
			colorRange: null,
			dials: null,
			trendPoints: null
		}
		

		dataSource.chart = properties;
		dataSource.colorRange = (yellow == 0 || orange == 0) ? colors2 : colors;
		dataSource.dials = values;
		dataSource.trendPoints = trendPoints;

		return dataSource;
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
				
		$("#datemask").inputmask("dd/mm/yyyy", { "placeholder": "dd/mm/yyyy" });
	    $("#datemask2").inputmask("mm/dd/yyyy", { "placeholder": "mm/dd/yyyy" });
	    $("[data-mask]").inputmask();
	    				 		 
		$timeout(function () {
	
			initDatatable();
															
		}, 500);
		   
		$("#stepTabDetector_1").trigger("click");
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
			
	
	$scope.alarmesFired = [];
		
		
	// $scope.selecionarDetector = function(item) {
	// 	if ($scope.selectedCompanyDetector ==  undefined)
	// 		$scope.selectedCompanyDetector = [];
		
	// 	$scope.selectedCompanyDetector.detectorDto = item;
	// }
	
	
	$scope.getAlarms = function() {
		
		$scope.resultAlarms = new AlarmService.listByCompanyId();
		 $scope.resultAlarms.$alarm({_csrf : angular.element('#_csrf').val(), companyId : $scope.$root.selectedCompany.uid }, function(){			 
			 $scope.alarms = $scope.resultAlarms.list;			 
        });		 
	}
	
	$scope.configAlarm = function(index) {
		
		// $scope.sensorIndex = index; 
		// $scope.selectedSensor = $scope.selectedCompanyDetector.detector.sensors[index];
		
		// if($.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == $scope.selectedSensor.uid ; }).length != 0 )			
		// 	$scope.selectedAlarm = $.grep($scope.selectedCompanyDetectorAlarms, function (e) { return e.sensorId == $scope.selectedSensor.uid ; })[0].alarmDto;		
		// else
		// 	$scope.selectedAlarm = undefined;
		// $scope.search = { unitMeterGases: $scope.selectedSensor.unitMeterGases, gas : $scope.selectedCompanyDetector.detector.sensors[index].gases[0].name };

		$scope.selectedCompanyDetectorAlarm = $scope.selectedCompanyDetectorAlarms[index];
		$scope.search = { unitMeterGases: $scope.selectedCompanyDetectorAlarm.unitMeterGases, gas : $scope.selectedCompanyDetectorAlarm.gasName};

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
		 		companyDetectorDto: {uid : $scope.selectedCompanyDetector.uid}, 
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
	}
		
	$scope.removerAlarm = function(uid) {		

		angular.element('body').addClass('loading');
		
		var selectedAlarm = $.grep($scope.alarms, function (e) { return e.uid == uid ; })[0];
				
		alarm = {
		 		alarmDto : selectedAlarm, 
		 		companyDetectorDto: {uid : $scope.selectedCompanyDetector.uid}, 
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