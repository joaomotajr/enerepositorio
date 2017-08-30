
app.controller('generalController', function ($scope, $timeout, $filter, CompanyService, UnitService, ViewService) {

	$scope.selectCompany = function(index) {
		
		$scope.selectedCompany = $scope.companiesSumary[index];
		$scope.selectedCompany.selected = index;

		 $scope.listOne = new UnitService.listOneByCompanyId();		 
		 $scope.listOne.$unit({_csrf : angular.element('#_csrf').val(), companyId : $scope.selectedCompany.uid}, function(){			
			$scope.selectedUnits = true; 
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
		$scope.selectedUnits = undefined; 
		$scope.selectedAreas = true;
		$scope.selectedUnit = $scope.companyComplete[index];	
	}

	$scope.closeSelectedUnit = function() {
		$scope.selectedUnits = true;  
		$scope.selectedUnit = undefined; 
	}

	$scope.selectArea = function(index) {
		$scope.selectedAreas = undefined;
		$scope.getDetectors( $scope.selectedUnit.areasDto[index].uid);
	}

	$scope.closeSelectedArea = function() {		
		$scope.selectedArea = undefined; 
		$scope.selectedAreas = true;
	}

	$scope.getDetectors = function(areaId) {

		$scope.resultDetectors = new ViewService.listAreaCompanyDetectorsAlarms();		 
		$scope.resultDetectors.$view({_csrf : angular.element('#_csrf').val(), areaId : areaId}, function(){						
			$scope.selectedArea = $scope.resultDetectors.list;

			$scope.selectedArea.forEach(
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
			caption: sensor.sensorName,
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

		values = {		  		  			
			dial: [{
				id: "crntYr",
				value: sensor.lastValue,
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