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
	}

	$scope.closeSelectedArea = function() {		
		$scope.selectedArea = undefined; 
		$scope.showAreas = true;
		
		while(intervals.length){
			$interval.cancel(intervals.pop());
		}
	}

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
					else if(e.artefact == "TIME") {
						e.dataType = "bulb";
						e.width=300;
						e.height=200;
					}
					else if(e.artefact == "ELETRICITY") {
						e.dataType = "hlineargauge";
						e.width=300;
						e.height=200;
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
				if($scope.selectedArea.list[i].sensorId == $scope.resultDevices.list[i].sensorId) {		
					$scope.updateGaugeInfo($scope.selectedArea.list[i], $scope.resultDevices.list[i]);
				}
			}			
						
		});
	}

	$scope.updateGaugeInfo = function(sensor, values) {

		if(sensor.rangeMax != values.rangeMax || sensor.rangeMin != values.rangeMin || sensor.alarmName != values.alarmName) {
			sensor.dataSource = $scope.getGaugeInfo(values);			
		}
		else {
			var value = values.lastValue > sensor.rangeMax ? sensor.rangeMax : values.lastValue;
			sensor.alarmType = values.alarmType;

			if(sensor.artefact == "TEMPERATURE" || sensor.artefact == "TIME")				
				dataSource.value = value;			
			else
				sensor.dataSource.dials.dial[0].value = value;
		}
	}	

	$scope.getGaugeInfo = function(e) {

		var red =    e.alarmOn == null ? 0 : e.alarm3;
		var yellow = e.alarmOn == null ? 0 : e.alarm2;
		var orange = e.alarmOn == null ? 0 : e.alarm1;

		properties =  {
			caption: e.sensorName,
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
				label: (e.artefact == "TIME" ? "Porta Fechada" : "Normal")
			},
			{
				minValue: orange,
				maxValue: yellow,
				code: "#D8D8D8",
				label: (e.artefact == "TIME" ? "Porta Aberta" : "Detec��o")
			}, {
				minValue: yellow,
				maxValue: red,
				code: "#f8bd19",
				label: (e.artefact == "TIME" ? "Porta Aberta" : "Alerta")
			}, {
				minValue: red,
				maxValue: e.rangeMax,
				code: "#e44a00",
				label: (e.artefact == "TIME" ? "Porta Aberta" : "Evacua��o")
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

		dataSource = {
			chart: null,
			colorRange: null,
			dials: null
		};
		
		dataSource.chart = properties;
		dataSource.colorRange = colors;
		dataSource.dials = values;

		if(e.artefact == "TEMPERATURE") {
			dataSource.chart.numberSuffix = '�C';
			dataSource.value = value;
			dataSource.annotations = {showbelow: 0};
		}
		else if(e.artefact == "TIME") {
			dataSource.chart.placeValuesInside=1;
			dataSource.chart.numberSuffix = ' Mins';
			dataSource.value = value;
			dataSource.annotations = {showbelow: 1};
			dataSource.chart.subcaption = value > 0 ? "PORTA ABERTA �" : "PORTA FECHADA";
		}
		else if(e.artefact == "ELETRICITY") {
			 dataSource.chart.lowerLimitDisplay = e.rangeMin + " Min";
			 dataSource.chart.upperLimitDisplay = e.rangeMax + " Max";			
			// dataSource.chart.gaugeouterradius="120";
			// dataSource.chart.gaugeinnerradius="70%";
			// dataSource.chart.gaugeFillRatio="15";
			// dataSource.chart.gaugeStartAngle=90;
			// dataSource.chart.gaugeEndAngle=90;
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