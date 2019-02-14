app.controller('generalController', function ($scope, $timeout, $interval, $rootScope, $filter, CompanyService, UnitService, ViewService) {

	var intervals = []

	$scope.selectCompany = function(index) {		
		$scope.selectedCompany = $scope.companiesSumary[index];
		$scope.selectedCompany.selected = index;

		 $scope.listOne = new UnitService.listOneByCompanyId();		 
		 $scope.listOne.$unit({_csrf : angular.element('#_csrf').val(), companyId : $scope.selectedCompany.uid}, function(){			
			$scope.showUnits = true; 
			$scope.selectedUnit = undefined; 
			$scope.closeSelectedArea();
			$scope.companyComplete = $scope.listOne.list; 				         	         	
        });		 
	};		

	$scope.getCompaniesSumary = function() {		 
		 $scope.resultCompanies = new CompanyService.listAllSumaryView();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companiesSumary = $scope.resultCompanies.list;
        });		 
	};

	$scope.selectUnit = function(index) {
		$scope.showUnits = undefined; 
		$scope.showAreas = true;
		$scope.selectedUnit = $scope.companyComplete[index];	
	};

	$scope.closeSelectedUnit = function() {
		$scope.showUnits = true;  
		$scope.selectedUnit = undefined; 
	};

	$scope.selectArea = function(index) {
		$scope.showAreas = undefined;
		
		$scope.selectedArea = [];
		$scope.selectedArea.uid = $scope.selectedUnit.areasDto[index].uid;
		$scope.selectedArea.name = $scope.selectedUnit.areasDto[index].name;		 
		$scope.getDevices($scope.selectedArea.uid);		
	};

	$scope.closeSelectedArea = function() {				
		$scope.selectedArea = [];
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
					e.dataType = selectGraphicType(e.deviceType.graphicType);
				});

			 initGaugeTimer();
		});
	};

	function selectGraphicType(gp) {
		if (gp == "THERMOMETERDISPLAY") {
			return "thermometer";			
		} else if(gp == "BULBINDICATOR") {
			return "bulb";			
		} else if(gp == "LINEARSCALE") {
			return "hlineargauge";						
		} else if(gp == "CYLINDERFILL") {
			return "cylinder";			
		} else if(gp == "VBULLET") {
			return "vbullet";			
		} else if(gp == "RATINGMETER" || gp == "SPEEDOMETER" || gp == "QUARTERGAUGE" || gp == "LINEARSCALE") {
			return "angulargauge";
		}
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
		$scope.resultDevices.$view({_csrf : angular.element('#_csrf').val(), areaId : areaId}, function() {
			for (var i = 0; i < $scope.resultDevices.list.length; i++) {
				value = $.grep($scope.resultDevices.list, function (e) { return e.companyDeviceId == $scope.selectedArea.list[i].companyDeviceId; })[0];
				$scope.updateGaugeInfo($scope.selectedArea.list[i], value, $scope.resultDevices.serverDate);
			}						
		});
	}

	$scope.updateGaugeInfo = function(sensor, values, serverDate) {

		 if(sensor.rangeMax != values.rangeMax || sensor.rangeMin != values.rangeMin || sensor.alarmName != values.alarmName) {
		 	sensor.dataSource = $scope.getGaugeInfo(values);			
		 } else {
			var value = values.lastValue > sensor.rangeMax ? sensor.rangeMax : values.lastValue;
			sensor.alarmType = values.alarmType;
			sensor.latencia = timeSince(serverDate, values.lastUpdate);

			if (sensor.deviceType.graphicType == "THERMOMETERDISPLAY")
				sensor.dataSource.value = value;
			else if	(sensor.deviceType.graphicType == "CYLINDERFILL" || sensor.deviceType.graphicType == "VBULLET" ) {
				sensor.dataSource.value = value;
			} else if	(sensor.deviceType.graphicType == "BULBINDICATOR") {				
				if (values.lastValue == null) {
					sensor.dataSource.value = values.lastValue;		
					sensor.dataSource.chart.subcaption = "OFFLINE";	
				} else {
					sensor.dataSource.value = values.lastValue;		
					sensor.dataSource.chart.subcaption = value > 0 ? "PORTA ABERTA " : "PORTA FECHADA";	
				}
			} else if(sensor.deviceType.graphicType == "LINEARSCALE")				
				sensor.dataSource.pointers = {pointer: [{value: value}]};
			else
			 	sensor.dataSource.dials.dial[0].value = value;
		 }
	};

	$scope.getGaugeInfo = function(e) {
		var red =    e.alarmOn == null ? 0 : e.alarm3;
		var yellow = e.alarmOn == null ? 0 : e.alarm2;
		var orange = e.alarmOn == null ? 0 : e.alarm1;

		properties =  {
			caption: "Medição de " + e.deviceType.description,
			subcaption: "",
			lowerLimit: e.rangeMin,
			upperLimit: e.rangeMax,
			editMode: "1",
			showValue: "1",			
			valueBelowPivot: "1",
			tickValueDistance: "5",
			valueFontSize: "14",
			theme: "ocean"	
		};

		colors = {				
			color: 
				[{
					minValue: e.rangeMin, maxValue: orange, code: "##6baa01", label: (e.deviceType.type == "TIME" ? "Fechada" : "")
				}, {
					minValue: orange, maxValue: yellow, code: "#D8D8D8", label: (e.deviceType.type == "TIME" ? "Aberta" : "")
				}, {
					minValue: yellow, maxValue: red, code: "#f8bd19", label: (e.deviceType.type == "TIME" ? "Aberta" : "")
				}, { 
					minValue: red, maxValue: e.rangeMax, code: "#e44a00", label: (e.deviceType.type == "TIME" ? "Aberta" : "")
				}]		
		};

		var value = e.lastValue > e.rangeMax ? e.rangeMax : e.lastValue;
		values = { dial: [{ id: "crntYr", value: value, showValue: "1", tooltext: "Status : $value", rearExtension: "5" }]};

		pointers = {pointer: [{value: value	}]};
		dataSource = { chart: null, colorRange: null, dials: null};		
		dataSource.chart = properties;
		dataSource.colorRange = colors;
		dataSource.dials = values;

		if(e.deviceType.graphicType == "THERMOMETERDISPLAY") {
			dataSource.chart.gaugeFillMix = "{dark-30},{light-60},{dark-10}";
			dataSource.chart.width=300;
			dataSource.chart.height=200;
			dataSource.value = value;
			dataSource.annotations = {showbelow: 0};
		} else if(e.deviceType.graphicType == "BULBINDICATOR") {
			dataSource.chart.width=300;
			dataSource.chart.height=200;
			dataSource.chart.placeValuesInside=1;
			dataSource.value = value;
			dataSource.annotations = {showbelow: 1};			
		} else if(e.deviceType.graphicType == "LINEARSCALE") {			
			 dataSource.annotations = {showbelow: 1};			 
			 dataSource.pointers = {pointer: [{value: value	}]};
		} else if(e.deviceType.graphicType == "CYLINDERFILL") {		
			dataSource.chart.gaugeFillMix = "{dark-30},{light-60},{dark-10}";
			dataSource.chart.width =200;
			dataSource.chart.height = 350;	
		} else if(e.deviceType.graphicType == "VBULLET") {				
	   	} else if(e.deviceType.graphicType == "RATINGMETER") {			
			dataSource.chart.gaugeFillMix = "{dark-30},{light-60},{dark-10}";
			dataSource.chart.origw = "300";
			dataSource.chart.origh =  "200";
			dataSource.chart.gaugeFillRatio = "15";
			dataSource.chart.gaugeouterradius = "120";
			dataSource.chart.gaugeinnerradius = "65%";
		} else if(e.deviceType.graphicType == "SPEEDOMETER") {
			dataSource.chart.gaugeFillMix = "{dark-30},{light-60},{dark-10}";
			dataSource.chart.captionpadding = "35";
			dataSource.chart.origw = "335";
			dataSource.chart.origh =  "335";
			dataSource.chart.gaugeinnerradius = "65%";
			dataSource.chart.gaugeouterradius = "120";
			dataSource.chart.gaugestartangle = "270";
			dataSource.chart.gaugeendangle = "-25";			
			dataSource.chart.valuefontsize = "14";			
			dataSource.chart.majortmnumber = "13";							
			dataSource.chart.minortmnumber = "1";
		} else if(e.deviceType.graphicType == "QUARTERGAUGE") {
			dataSource.chart.gaugeFillMix = "{dark-30},{light-60},{dark-10}";
			dataSource.chart.captionontop = "0";
			dataSource.chart.origw = "380";
			dataSource.chart.origh =  "250";			
			dataSource.chart.gaugeinnerradius = "70%";
			dataSource.chart.gaugeouterradius = "190";
			dataSource.chart.gaugestartangle = "135";
			dataSource.chart.gaugeoriginx = "190";
			dataSource.chart.gaugeoriginy = "220";
			dataSource.chart.gaugeendangle = "45";			
			dataSource.chart.valuefontsize = "12";			
		}

		dataSource.chart.numberSuffix = $scope.getMeters(e.unitMeterGases);
		return dataSource;
	};		

	 $scope.getMeters = function(unit) {
		if (unit == 'VOLT') { 
			return ' V';
		} else if(unit == 'GRAUS_CELSIUS') {
			return ' °C';
		} else if(unit == 'WATTS') {
			return ' W';
		} else if(unit == 'KWATTS') {
			return ' kW';
		} else if(unit == 'FAHRENHEIT') {
			return ' °F';
		} else if(unit == 'M3_HOUR') {
			return ' m³/hora';
		} else if(unit == 'SECOND') {
			return " Segundos";
		} else if(unit == 'M3') {
			return ' m³';
		} else if(unit == 'MINUTE') {
			return " Minutos";
		} else if(unit == 'AMPERE') {
			return " Amps";
		} else if(unit == 'KWT') {
			return " KWT";
		} else if(unit == 'METERS') {
			return " ms";
		} else if(unit == 'LEL_PERCENT_METRO') {
			return " lel% m";
		} else if(unit == 'LEL_PERCENT') {
			return " lel%";
		} else if(unit == 'PERCENT_VOLUME') {
			return " vol%";
		} else if(unit == 'METERS') {
			return " ms";
		} else {
			return unit;
		}
	}

	$scope.getCompaniesSumary();	
});