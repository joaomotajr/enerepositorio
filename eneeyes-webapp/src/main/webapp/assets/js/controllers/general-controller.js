
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
		 
		$scope.getDetectors($scope.selectedArea.uid);		
	}

	$scope.closeSelectedArea = function() {		
		$scope.selectedArea = undefined; 
		$scope.showAreas = true;
		
		while(intervals.length){
			$interval.cancel(intervals.pop());
		}
	}

	$scope.getDetectors = function(areaId) {

		$scope.resultDetectors = new ViewService.listAreaCompanyDetectorsAlarms();		 
		$scope.resultDetectors.$view({_csrf : angular.element('#_csrf').val(), areaId : areaId}, function(){						
			$scope.selectedArea.list = $scope.resultDetectors.list;

			$scope.selectedArea.list.forEach(
					 function(e) {						
						e.dataSource = $scope.getGaugeInfo(e);
					}			
			);

			 initGaugeTimer();
		});
	}	
			
	initGaugeTimer = function() {
		intervals.push( $interval(function(){

			if($rootScope == null) return;
			if($rootScope.currentPage == "Over-View")
				$scope.timertDetectors($scope.selectedArea.uid);		
		}, 5000));	
	}

	$scope.timertDetectors = function(areaId) {

		$scope.resultDetectors = new ViewService.listAreaCompanyDetectorsAlarms();		 
		$scope.resultDetectors.$view({_csrf : angular.element('#_csrf').val(), areaId : areaId}, function(){	
			
			for (var i = 0; i < $scope.resultDetectors.list.length; i++) {
				if($scope.selectedArea.list[i].sensorId == $scope.resultDetectors.list[i].sensorId) {		
					$scope.updateGaugeInfo($scope.selectedArea.list[i], $scope.resultDetectors.list[i]);
				}
			}			
						
		});
	}

	$scope.updateGaugeInfo = function(sensor, values) {

		if(sensor.rangeMax != values.rangeMax || sensor.rangeMin != values.rangeMin || sensor.alarmName != values.alarmName) {
			sensor.dataSource = $scope.getGaugeInfo(values);
			console.log("houve modificação em Configurações")
		}
		else {
			var value = values.lastValue > sensor.rangeMax ? sensor.rangeMax : values.lastValue;
			sensor.dataSource.dials.dial[0].value = value;
			sensor.alarmType = values.alarmType;
		}
	}	

	$scope.getGaugeInfo = function(sensor) {

		var red =    sensor.alarmOn == null ? 0 : sensor.alarm3;
		var yellow = sensor.alarmOn == null ? 0 : sensor.alarm2;
		var orange = sensor.alarmOn == null ? 0 : sensor.alarm1;

		properties =  {
			// caption: sensor.sensorName,
			subcaption: "",
			lowerLimit: sensor.rangeMin,
			upperLimit: sensor.rangeMax,
			editMode: "1",
			showValue: "1",
			valueBelowPivot: "1",
			tickValueDistance: "5",
			gaugeFillMix: "{dark-30},{light-60},{dark-10}",
			gaugeFillRatio: "15",
			theme: "fint",	
			gaugeouterradius: "120",
			gaugeinnerradius: "70%",					
			valueFontSize: "14"
		};

		colors = {				
			color: [
			{
				minValue: sensor.rangeMin,
				maxValue: orange,
				code: "##6baa01"
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
			dials: null
		}

		dataSource.chart = properties;
		dataSource.colorRange = colors;
		dataSource.dials = values;

		return dataSource;
	}
		
	$scope.getCompaniesSumary();	
	
});