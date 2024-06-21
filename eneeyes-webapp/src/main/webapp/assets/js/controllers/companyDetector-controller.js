app.controller('companyDetectorController', function ($scope, $interval, $rootScope, $timeout, AlarmService,
		CompanyDetectorService, CompanyDeviceService, DetectorService, PositionService, CompanyService, 
		CompanyDetectorMaintenanceHistoricService, ViewService) {

	var loadGoogleCharts = false;
	
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.$root.selectedCompany = $scope.listOne.t;
			 $scope.itens = getTree();
			 $scope.loadTreview($scope.itens);			 
	    });		 
	};
	
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
			deliveryDate : new Date($('#datePicker1').data("DateTimePicker").getDate()), 
			garantyDays: $scope.selectedCompanyDetector.garantyDays,
			descriptionDelivery: $scope.selectedCompanyDetector.descriptionDelivery,			
			installDate : new Date($('#datePicker2').data("DateTimePicker").getDate()),
			descriptionInstall : $scope.selectedCompanyDetector.descriptionInstall,
			maintenanceInterval : $scope.selectedCompanyDetector.maintenanceInterval
		 };
		 
		$scope.inclusaoCompanyDetector = new CompanyDetectorService.save(companyDetector);
		$scope.inclusaoCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val()}, function(){		
			
			//Se for um Company Detector novo ou nao associado a um Detector
			if($scope.selectedCompanyDetector.uid == undefined) {
				$scope.selectedCompanyDetector = $scope.inclusaoCompanyDetector.t;				
				$scope.getOneCompany($scope.companyUid);
			}		
			
			angular.element('body').removeClass('loading');			
			$rootScope.showGeneralMessage($scope.inclusaoCompanyDetector.message, 'SUCCESS');	

		});			 
	};
	
	$scope.clearCompanyDetector = function () {
		$scope.selectedCompanyDetector.detectorDto.image = "/assets/img/cover.jpg";
		$timeout(function () {						
		    $scope.selectedCompanyDetector.uid = undefined;
		    $scope.selectedCompanyDetector.name = '';
		    $scope.selectedCompanyDevice.uid = undefined;	    	    
		    $scope.selectedCompanyDetector.detectorDto = '';	    
		    $scope.selectedCompanyDetector.local = '';
		    $scope.selectedCompanyDetector.description = '';
		    $scope.selectedCompanyDetector.serialNumber = 0;			
		    $('#datePicker1').val('');
			$scope.selectedCompanyDetector.garantyDays = 0;
			$scope.selectedCompanyDetector.descriptionDelivery = '';;			
			$('#datePicker2').val('');
			$scope.selectedCompanyDetector.descriptionInstall = '';;
			$scope.selectedCompanyDetector.maintenanceInterval = 0;
		    
		}, 100);
	};
	
	$scope.deleteCompanyDetector = function() {		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new CompanyDetectorService.deletar();	
		
		$scope.deletar.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid}, function(){
			$rootScope.showGeneralMessage($scope.deletar.message, 'DANGER');		

			$scope.clearCompanyDetector();
			$scope.getOneCompany($scope.companyUid);
			
			angular.element('body').removeClass('loading');
	                 	         	
		});		 
	};
	
	$scope.getDetectors = function() {		 
		 $scope.resultDetectors = new DetectorService.listAll();		 
		 $scope.resultDetectors.$detector({_csrf : angular.element('#_csrf').val()}, function() {			
			 $scope.detectors = $scope.resultDetectors.list; 
			 
			 if ($scope.selectedCompanyDevice != null)
			 	$scope.getOneCompanyDetector();			
        });		 
	};	
	 
	$scope.getOneCompanyDetector = function() {
		$scope.search = { unitMeter: null, gas : null };
				
		$scope.resultCompanyDetector = new CompanyDetectorService.listPorCompanyDevice();		 
		$scope.resultCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val(), 
			id : $scope.selectedCompanyDevice.uid }, function(){
			
			$scope.selectedCompanyDetector = $scope.resultCompanyDetector.t;									
			//* Detector ja foi associado a dispositivo checa alarmes *//
			if($scope.selectedCompanyDetector != null) {
				reloadDates();
				$scope.getCompanyDetectorMaintenanceHistoric();
				$scope.getCompanyDeviceAlarm($scope.selectedCompanyDetector.companyDeviceDto.uid);
				$scope.getPositionsAndIds($scope.selectedCompanyDetector.companyDeviceDto.uid);											
			}
        });		 
	};

	$scope.getPositionsAndIds = function(companyDeviceId) {		
		$scope.listOnePosition = new PositionService.listOneByCompanyDevice();		 
		$scope.listOnePosition.$position({_csrf : angular.element('#_csrf').val(), id : companyDeviceId}, function() {
			
			$scope.selectedCompanyDetectorPosition = $scope.listOnePosition.t;		
		});
	};

	$scope.selecionarDetector = function(item) {
		if($scope.selectedCompanyDetector == undefined) {
			$scope.selectedCompanyDetector = [];
		}
		$scope.selectedCompanyDetector.detectorDto = item;
	};

	$scope.getCompanyDeviceAlarm = function(companyDeviceId) {

		$scope.resultDevices = new ViewService.listCompanyDeviceAlarms();		 
		$scope.resultDevices.$view({_csrf : angular.element('#_csrf').val(), companyDeviceId : companyDeviceId}, function(){						
			
			$scope.selectedCompanyDeviceAlarms = [];
			$scope.selectedCompanyDeviceAlarms.push($scope.resultDevices.t);
			$scope.selectedCompanyDeviceAlarm = $scope.resultDevices.t;		
			$scope.selectedCompanyDeviceAlarm.dataSource = getGaugeInfo($scope.selectedCompanyDeviceAlarm);			 
		});
	};

	getGaugeInfo = function(sensor) {

		var red =    sensor.alarmOn == null ? 0 : sensor.alarm3;
		var yellow = sensor.alarmOn == null ? 0 : sensor.alarm2;
		var orange = sensor.alarmOn == null ? 0 : sensor.alarm1;
		
		properties =  {			
			theme: "fint",
			// caption: sensor.sensorName,
			lowerLimit: sensor.rangeMin,
			upperLimit: sensor.rangeMax,
			lowerLimitDisplay: sensor.rangeMin + " Min",
			upperLimitDisplay: sensor.rangeMax + " Max",
			numberSuffix: " " + (sensor.unitMeter.symbol),			
			
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
			point: [{
					startValue: orange,					
					dashed: "1",
					dashlen: "3",
					dashgap: "3",
					thickness: "2"
				}, {
					startValue: yellow,					
					dashed: "1",
					dashlen: "3",
					dashgap: "3",
					thickness: "2"
				}, {
					startValue: red,					
					dashed: "1",
					dashlen: "3",
					dashgap: "3",
					thickness: "2"
				}]
			};

		colors = {				
			color: [
			{
				minValue: sensor.rangeMin,
				maxValue: orange,
				code: "#6baa01"			
			}, {
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
		};

		colors2 = {				
			color: [{
				minValue: sensor.rangeMin,
				maxValue: sensor.rangeMax,
				label : "Sem Limites de Alarmes",	
				code: "#6baa01"
			}]
		};

		var value = sensor.lastValue > sensor.rangeMax ? sensor.rangeMax : sensor.lastValue;
		values = {		  		  			
			dial: [{
				id: "crntYr",
				value: value,
				showValue: "1",
				tooltext: "Status : $value",
				rearExtension: "5"
			}]			
		};

		dataSource = {
			chart: null,
			colorRange: null,
			dials: null,
			trendPoints: null
		};		

		dataSource.chart = properties;
		dataSource.colorRange = (yellow == 0 || orange == 0) ? colors2 : colors;
		dataSource.dials = values;
		dataSource.trendPoints = trendPoints;

		return dataSource;
	};
	
	function reloadDates() {

		$('#datePicker1').datetimepicker(
			{ 	defaultDate: $scope.selectedCompanyDetector.deliveryDate != null ?
					new Date($scope.selectedCompanyDetector.deliveryDate): '', 
				format: "DD/MM/YYYY",
				autoclose: true,
				language: 'br'
			}
		);
		
		$('#datePicker2').datetimepicker(
			{ 	defaultDate: $scope.selectedCompanyDetector.installDate != null ? 
					new Date($scope.selectedCompanyDetector.installDate) : '', 
				format: "DD/MM/YYYY",
				autoclose: false,
				language: 'br',
				pickTime: false
			}
		);
	}

	$scope.initializeDetector =  function()  {				
		$timeout(function () { initDatatable(); }, 500);
		$("#stepTabDetector_1").trigger("click");
	};	 
	 
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
					row.child( format(row[0]) ).show();
					tr.addClass("shown");
				}
		});
	 }
	 function format ( d ) {
		var item = $scope.detectors[d];

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
				'<td>'+item.sensorDto.name+'</td>'+	                
				'<td>'+item.sensorDto.gasDto.name+'</td>'+
				'<td>'+item.sensorDto.rangeMin + '-' + item.sensorDto.rangeMax +'</td>'+
				'<td>'+item.sensorDto.unitMeter.symbol + '</td>'+
			'</tr>'+            
			'</table>'+
		'</div>';
	}	
	
	$scope.getAlarms = function() {
		
		$scope.resultAlarms = new AlarmService.listByCompanyId();
		 $scope.resultAlarms.$alarm({_csrf : angular.element('#_csrf').val(), companyId : $scope.$root.selectedCompany.uid }, function(){			 
			 $scope.alarms = $scope.resultAlarms.list;			 
        });		 
	};
	
	$scope.configAlarm = function(index) {
		
		$scope.search = { unitMeter: $scope.selectedCompanyDeviceAlarm.unitMeter.symbol, gas : $scope.selectedCompanyDeviceAlarm.gasName};
		$timeout(function () {
			$('#modalAlarm').modal({ show: 'false', backdrop: 'static', keyboard:'false' });						 
		}, 300);
	};

	$scope.toggleAlarm = function(alarm) {

		if(alarm != null && $scope.selectedCompanyDeviceAlarm.rangeMax < alarm.alarm3) {
			$scope.msgErroAlarm = "Alarm Selecionado excede Range Max do Sensor, Verifique";
			return;
		}

		if(alarm != null) {
			$scope.updateAlarm = new CompanyDeviceService.updateAlarm();
			$scope.updateAlarm.$companyDevice({_csrf : angular.element('#_csrf').val(), alarmId: alarm.uid, id : $scope.selectedCompanyDevice.uid }, function(){						
				if (!$scope.updateAlarm.isError) {
					$scope.selectedCompanyDeviceAlarm.alarmId = alarm.uid;
					$scope.getCompanyDeviceAlarm($scope.selectedCompanyDetector.companyDeviceDto.uid);	
				}			
			});	
		}
		else {

			$scope.removeAlarm = new CompanyDeviceService.removeAlarm();
			$scope.removeAlarm.$companyDevice({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDevice.uid }, function(){						
				if (!$scope.removeAlarm.isError) {
					$scope.selectedCompanyDeviceAlarm.alarmId = null;			 
					$scope.getCompanyDeviceAlarm($scope.selectedCompanyDetector.companyDeviceDto.uid);	
				}			
			});
		}
	};
	
	$scope.validDeliveryDate = function ($event) {

        if ($('#datePicker2').val().match(/[^0-9\/]/g) != null) {
            $scope.deliveryDateValid = true;
        }	        
        else if ($('#datePicker2').val() == '') {
            $scope.deliveryDateValid = true;
        }
        else {
            $scope.deliveryDateValid = false;
        }
    };

	$timeout(function(){
		angular.element('body').removeClass('loading');		
	}, 500);
	
	$scope.getCompanyDetectorMaintenanceHistoric = function() {		 
		
		$scope.companyDetectorMaintenanceHistoric = new CompanyDetectorMaintenanceHistoricService.listPorCompanyDetector();		
		$scope.companyDetectorMaintenanceHistoric.$companyDetectorMaintenanceHistoric({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid});		 
	}
	
	/* ------------------------------------- Inicio Processamento --------------------------------------------*/
	
	if($scope.$root.selecteds.unitIndex != undefined) {
		
		$scope.selectedUnit = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex]);
		$scope.selectedArea = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex]);	
		$scope.selectedCompanyDevice = angular.copy($scope.selectedArea.companyDevicesDto[$scope.$root.selecteds.CompanyDeviceIndex]);
		
		$scope.getDetectors();
		$scope.getAlarms();

		f(!$scope.selectedCompanyDevice.name) {
			$('#datePicker1').datetimepicker(
			   {
				   format: "DD/MM/YYYY",
				   autoclose: true,
				   language: 'br',
				   pickTime: false
			   }
		   )
			$('#datePicker2').datetimepicker(
			   {
				   format: "DD/MM/YYYY",
				   autoclose: true,
				   language: 'br',
				   pickTime: false
			   }
		   )
	   }
	}
	
	$scope.initializeDetector();		
});