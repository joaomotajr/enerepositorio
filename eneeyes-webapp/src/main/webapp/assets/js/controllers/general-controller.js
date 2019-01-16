app.controller('generalController', function ($scope, $timeout, $interval, $rootScope, $filter, CompanyService, UnitService, ViewService) {

	var intervals = []

	$scope.selectCompany = function(index) {		
		$scope.selectedCompany = $scope.companiesSumary[index];
		$scope.selectedCompany.selected = index;

		 $scope.listOne = new UnitService.listOneByCompanyId();		 
		 $scope.listOne.$unit({_csrf : angular.element('#_csrf').val(), companyId : $scope.selectedCompany.uid}, function(){			
			$scope.showUnits = true; 
			$scope.selectedUnit = undefined; 
			$scope.companyComplete = $scope.listOne.list; 				         	         	
        });		 
	}		

	$scope.getCompaniesSumary = function() {		 
		 $scope.resultCompanies = new CompanyService.listAllSumaryView();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companiesSumary = $scope.resultCompanies.list;
        });		 
	}

	$scope.selectUnit = function(index) {
		$scope.showUnits = undefined; 
		$scope.showAreas = true;
		$scope.selectedUnit = $scope.companyComplete[index];	
	}

	$scope.closeSelectedUnit = function() {
		$scope.showUnits = true;  
		$scope.selectedUnit = undefined; 
		$scope.closeSelectedArea();
	}

	$scope.selectArea = function(index) {
		$scope.showAreas = undefined;
		
		$scope.selectedArea = [];
		$scope.selectedArea.uid = $scope.selectedUnit.areasDto[index].uid;
		$scope.selectedArea.name = $scope.selectedUnit.areasDto[index].name;		 
		$scope.getDevices($scope.selectedArea.uid);		
	};

	$scope.closeSelectedArea = function() {		
		$scope.selectedArea = undefined; 
		$scope.showAreas = true;
		
		while(intervals.length){
			$interval.cancel(intervals.pop());
		}
	};

	$scope.getDevices = function(areaId) {

		$scope.resultDevices = new ViewService.listAreaCompanyDeviceAlarms();		 
		$scope.resultDevices.$view({_csrf : angular.element('#_csrf').val(), areaId : areaId}, function(){						
			$scope.selectedArea.list = $scope.resultDevices.list;

			$scope.selectedArea.list.forEach(
					function(e) {						
					e.dataSource = $scope.getGaugeInfo(e);
					if(e.artefact == "TEMPERATURE") {
						e.dataType = "thermometer";
						e.width=300;
						e.height=200;
					}	
					else if(e.artefact == "TIME" || e.artefact == "DIGITAL") {
						e.dataType = "bulb";
						e.width=300;
						e.height=200;
					}
					else if(e.artefact == "ELETRICITY") {
						e.dataType = "hlineargauge";
						e.width=300;
						e.height=200;
					}
					else if(e.artefact == "FLOW") {
						e.dataType = "cylinder";						
						e.width=200;
						e.height=350;
					}
					else
						e.dataType = "angulargauge";
						e.width=300;
						e.height=200;
					}			
			);

			 initGaugeTimer();
		});
	}	
			
	initGaugeTimer = function() {
		intervals.push( $interval(function(){

			if($rootScope == null) return;
			if($rootScope.currentPage == "Over-View")
				$scope.timertDevices($scope.selectedArea.uid);		
		}, 5000));	
	}

	$scope.timertDevices = function(areaId) {
		
		$scope.resultDevices = new ViewService.listAreaCompanyDeviceAlarms();	
		$scope.resultDevices.$view({_csrf : angular.element('#_csrf').val(), areaId : areaId}, function(){

			for (var i = 0; i < $scope.resultDevices.list.length; i++) {
				value = $.grep($scope.resultDevices.list, function (e) { return e.companyDeviceId == $scope.selectedArea.list[i].companyDeviceId; })[0];
				$scope.updateGaugeInfo($scope.selectedArea.list[i], value, $scope.resultDevices.serverDate);
			}			
						
		});
	}

	$scope.updateGaugeInfo = function(sensor, values, serverDate) {

		 if(sensor.rangeMax != values.rangeMax || sensor.rangeMin != values.rangeMin || sensor.alarmName != values.alarmName) {
		 	sensor.dataSource = $scope.getGaugeInfo(values);			
		 }
		 else {
			var value = values.lastValue > sensor.rangeMax ? sensor.rangeMax : values.lastValue;
			sensor.alarmType = values.alarmType;
			sensor.latencia = timeSince(serverDate, values.lastUpdate);

			if(sensor.artefact == "TEMPERATURE")
				sensor.dataSource.value = value;
			else if	(sensor.artefact == "FLOW") {
				sensor.dataSource.value = value;
			}
			else if	(sensor.artefact == "TIME") {				
				if (values.lastValue < 0) {
					sensor.dataSource.value = values.lastValue;		
					sensor.dataSource.chart.numberSuffix = "";
					sensor.dataSource.chart.subcaption = "OFFLINE";	
				}
				else {
					sensor.dataSource.value = values.lastValue;		
					sensor.dataSource.chart.numberSuffix = transformMedition(sensor.unitMeterGases);
					sensor.dataSource.chart.subcaption = value > 0 ? "PORTA ABERTA ï¿½" : "PORTA FECHADA";	
				}
			}
			else if(sensor.artefact == "ELETRICITY")				
				sensor.dataSource.pointers = {pointer: [{value: value}]};
			else
			 	sensor.dataSource.dials.dial[0].value = value;
		 }
	};
	
	function transformMedition(unitMeterGases) {
		if(unitMeterGases == 'SECOND')
			return " Segundos";

	}

	$scope.getGaugeInfo = function(e) {

		var red =    e.alarmOn == null ? 0 : e.alarm3;
		var yellow = e.alarmOn == null ? 0 : e.alarm2;
		var orange = e.alarmOn == null ? 0 : e.alarm1;

		properties =  {
			caption: e.sensorName + "- ID " + e.uid,
			subcaption: "",
			lowerLimit: e.rangeMin,
			upperLimit: e.rangeMax,
			editMode: "1",
			showValue: "1",			
			valueBelowPivot: "1",
			tickValueDistance: "5",			
			theme: "fint",										
			valueFontSize: "14"
		};

		colors = {				
			color: [
			{
				minValue: e.rangeMin,
				maxValue: orange,
				code: "##6baa01",
				label: (e.artefact == "TIME" ? "Fechada" : "")
			},
			{
				minValue: orange,
				maxValue: yellow,
				code: "#D8D8D8",
				label: (e.artefact == "TIME" ? "Aberta" : "")
			}, {
				minValue: yellow,
				maxValue: red,
				code: "#f8bd19",
				label: (e.artefact == "TIME" ? "Aberta" : "")
			}, {
				minValue: red,
				maxValue: e.rangeMax,
				code: "#e44a00",
				label: (e.artefact == "TIME" ? "Aberta" : "")
			}]		
		};

		var value = e.lastValue > e.rangeMax ? e.rangeMax : e.lastValue;
		values = {		  		  			
			dial: [{
				id: "crntYr",
				value: value,
				showValue: "1",
				tooltext: "Status : $value",
				rearExtension: "5"
			}]			
		};

		pointers = {pointer: [{value: value	}]};

		dataSource = {
			chart: null,
			colorRange: null,
			dials: null
		};
		
		dataSource.chart = properties;
		dataSource.colorRange = colors;
		dataSource.dials = values;

		if(e.artefact == "TEMPERATURE") {
			dataSource.chart.numberSuffix = '°C';
			dataSource.value = value;
			dataSource.annotations = {showbelow: 0};
		}
		else if(e.artefact == "TIME") {
			dataSource.chart.placeValuesInside=1;
			dataSource.value = value;
			dataSource.annotations = {showbelow: 1};			
		}
		else if(e.artefact == "ELETRICITY") {
			 dataSource.chart.lowerLimitDisplay = e.rangeMin + " Min";
			 dataSource.chart.upperLimitDisplay = e.rangeMax + " Max";			
			 dataSource.annotations = {showbelow: 1};			 
			 dataSource.pointers = {pointer: [{value: value	}]};
		}
		else if(e.artefact == "FLOW") {
			dataSource.chart.lowerLimitDisplay = e.rangeMin + " Min";
			dataSource.chart.upperLimitDisplay = e.rangeMax + " Max";			
			dataSource.chart.numberSuffix = " m³/hour";				
	   }
		else {
			dataSource.chart.gaugeFillMix="{dark-30},{light-60},{dark-10}";
			dataSource.chart.gaugeFillRatio="15";
			dataSource.chart.gaugeouterradius="120";
			dataSource.chart.gaugeinnerradius="70%";		
		}
		return dataSource;
	};		
	$scope.getCompaniesSumary();	
});