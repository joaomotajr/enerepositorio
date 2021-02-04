app.filter('dashCompaniesPositionFilter', function () {
    return function (objects, criteria) {
        var filterResult = [];

        if (!criteria || criteria == 0 || criteria == 'ALL' )
            return objects;

        for (var index in objects) {
            
        	if (objects[index].alarmType == criteria) {
            	filterResult.push(objects[index]);                
            }            
        }                
        return filterResult;
    }
});

app.controller('dashController', function ($scope, $timeout, $interval, $filter, ViewService, UserService) {

	firstTime = true;
	$scope.loading = undefined;
	
	$scope.orderOptions = "none";
	$scope.row = "ASC";

	$scope.toggleQuestao = function(field) {
		if ($scope.row == "ASC") {
			$scope.orderOptions = "-" + field;
			$scope.row = "DESC";
		}
		else if ($scope.row == "DESC") {
			$scope.orderOptions = field;
			$scope.row = "ASC";
		}
	};

	chart = {
		caption: "Detectores Monitorados por Status",
		subCaption: "...",					
		numberSuffix: " Det",
		showPercentValues: "0",
		showBorder: "0",
		use3DLighting: "1",
		enableSmartLabels: "1",
		startingAngle: "310",
		showLabels: "0",					
		showLegend: "1",
		defaultCenterLabel: "Total Monitorado: 0" ,
		centerLabel: "Detectores em $label: $value",
		centerLabelBold: "1",
		showTooltip: "0",
		decimals: "0",
		centerFontSize: "10",
		useDataPlotColorForLabels: "1",
		theme: "fint"
	}

	$scope.dataSource = {
		chart: chart,			
		data: null
	}

	function populateDonut(time) {
		$scope.dataSource.chart.defaultCenterLabel = "Total Monitorado: " + $scope.sumary.devices;
		$scope.dataSource.chart.subCaption = "Atualizado " + time;

		$scope.dataSource.data = 
		 		[{
					value: $scope.sumary.normal.value,
			    color: "#00a65a",
			    highlight: "#00a65a",
			    label: "NORMAL"
			  }, {
					value: $scope.sumary.offLine.value,
		      color: "#333",
		      highlight: "#333",
		      label: "Off Line"
		    }, {
					value: $scope.sumary.alarm3.value,
		      color: "#dd4b39",
		      highlight: "#dd4b39",
		      label: "ALARM III"
		    }, {
					value: $scope.sumary.alarm2.value,
		      color: "#f39c12",
		      highlight: "#f39c12",
		      label: "ALARM II"
		    }, {
					value: $scope.sumary.alarm1.value,
		      color: "#d2d6de",
		      highlight: "#d2d6de",
		      label: "ALARM I"
		    }, {
					value: $scope.sumary.turnOff.value,
		      highlight: "#72afd2",
		      label: "S/Alarme"
		    }];
	}
	
	$scope.dashCompaniesPosition = [];
	$scope.dashDetectorsMaintenance = [];
	
	$scope.getDetectorsMaintenance = function() {	
		$scope.loading = true;		
		$scope.listAllDashDetectorsMaintenance = new ViewService.listAllDashDetectorsMaintenance();		 
		$scope.listAllDashDetectorsMaintenance.$view({_csrf : angular.element('#_csrf').val()}, function(){
			
			if($scope.listAllDashDetectorsMaintenance != null) {
				$scope.listAllDashDetectorsMaintenance.list.forEach(
					 function(e) {
						 if(e.last_date != null) { 						 
							 e.next = Math.ceil(Math.abs(new Date($scope.listAllDashDetectorsMaintenance.serverDate) - 
									 new Date(e.last_date + e.maintenance_interval*24*60*60*1000)) / (1000 * 3600 * 24));
							 e.final_date = new Date(e.last_date + e.maintenance_interval*24*60*60*1000);
						 } else {
							 e.next = Math.ceil(Math.abs(new Date($scope.listAllDashDetectorsMaintenance.serverDate) - 
									new Date(e.install_date + e.maintenance_interval*24*60*60*1000)) / (1000 * 3600 * 24));
							 e.final_date = new Date(e.install_date + e.maintenance_interval*24*60*60*1000); 
						 }
					}			
			 	);	
			}			
			$scope.loading = undefined;         	         	
    });		 
	};
	
	$scope.getCompaniesPosition = function() {		
		$scope.loading = true;
		if (firstTime) {
			angular.element('body').addClass('loading');		
			firstTime = false;
		}

		$scope.listAllDashCompaniesPosition = new ViewService.listAllDashCompaniesPosition();		 
		$scope.listAllDashCompaniesPosition.$view({_csrf : angular.element('#_csrf').val()}, function(){
		
			if($scope.listAllDashCompaniesPosition != null && $scope.listAllDashCompaniesPosition.list != null) {
				for(var i = 0; i < $scope.listAllDashCompaniesPosition.list.length; i++) {				 
					 
					 $scope.dashCompaniesPosition[i] = $scope.listAllDashCompaniesPosition.list[i];
					 $scope.dashCompaniesPosition[i].lastUpdateFull = $scope.dashCompaniesPosition[i].lastUpdate;
					 $scope.dashCompaniesPosition[i].lastUpdate = timeSinceAbrev($scope.listAllDashCompaniesPosition.serverDate, $scope.dashCompaniesPosition[i].lastUpdate);				 
					 $scope.dashCompaniesPosition[i].lastValue	= Math.round($scope.dashCompaniesPosition[i].lastValue * 100) / 100 ;
					 $scope.dashCompaniesPosition[i].arrayValues = $scope.dashCompaniesPosition[i].arrayValues.substring(0, $scope.dashCompaniesPosition[i].arrayValues.lastIndexOf(","));				
					 if($scope.dashCompaniesPosition[i].alarmType == 'ANALISE') {
						$scope.dashCompaniesPosition[i].alarmType = 'NORMAL';
						$scope.dashCompaniesPosition[i].obs = '*';
					}
				 }

				$scope.sumary = 
					{
						alarm1: {value: 0, percent: 0}, 
						alarm2: {value: 0, percent: 0}, 
						alarm3: {value: 0, percent: 0},
						normal: {value: 0, percent: 0},
						devices: {value: 0, percent: 0},
						offLine: {value: 0, percent: 0},
						turnOff: {value: 0, percent: 0} 
				};

				if ($scope.listAllDashCompaniesPosition.list)
					$scope.sumary.devices = $scope.listAllDashCompaniesPosition.list.length;
				 				
				$scope.listAllDashCompaniesPosition.list.forEach(
					 function(e) {													
						if ( e.alarmType == "OFF" || e.alarmType == "WITHOUT" ) {
							$scope.sumary.turnOff.value ++;
							$scope.sumary.turnOff.percent = ($scope.sumary.turnOff.value * 100) / $scope.sumary.devices;
						} else if ( e.alarmType == "OFFLINE" ) {							 
							$scope.sumary.offLine.value ++;
							$scope.sumary.offLine.percent = ($scope.sumary.offLine.value * 100) / $scope.sumary.devices;
						}	else if ( e.alarmType == "NORMAL" || e.alarmType == "ANALISE" ) { 
							$scope.sumary.normal.value ++;
							$scope.sumary.normal.percent = ($scope.sumary.normal.value * 100) / $scope.sumary.devices;
						} else if ( e.alarmType == "DETECCAO") {
							$scope.sumary.alarm1.value ++;
							$scope.sumary.alarm1.percent = ($scope.sumary.alarm1.value * 100) / $scope.sumary.devices;
						} else if ( e.alarmType == "ALERTA") {
							$scope.sumary.alarm2.value ++;
							$scope.sumary.alarm2.percent = ($scope.sumary.alarm2.value * 100) / $scope.sumary.devices;
						} else if ( e.alarmType == "EVACUACAO") {
							$scope.sumary.alarm3.value ++;
							$scope.sumary.alarm3.percent = ($scope.sumary.alarm3.value * 100) / $scope.sumary.devices;
						}
					}			
			 	);			 			 
				
				populateDonut(new Date($scope.listAllDashCompaniesPosition.serverDate).toLocaleTimeString());				
				$timeout(function() {	
					angular.element('body').removeClass('loading');						
				   $('.sparkbar').each(function () {
					   var $this = $(this);
					   $this.sparkline('html', {					   
					   normalRangeMin: 0, normalRangeMax: 10
					   });
				   }); 	
			   }, 10);
			}			
			$scope.loading = undefined;         	         	
       });		 
	 }
	
	$scope.pesquisaUser = function() {
		var idUsuario = $('#idUsuario').val();
		
    	$scope.userImage = new UserService.listOne();
        $scope.userImage.$user({_csrf : angular.element('#_csrf').val(), id : idUsuario }, function(){       	
        	$timeout(function () {
        		if($scope.userImage.t.image != null)
        			$scope.$root.userImage = $scope.userImage.t.image;	            
	        }, 500);
		});
    }
	
	$scope.filterStatus = function (filter) {
		$scope.selectedStatusDashCompaniesPosition = filter;
		$scope.getCompaniesPosition();
    };
		
	$scope.refreshDashboard = function() { 
		$scope.getCompaniesPosition();		
		$scope.pesquisaUser();
	};

	$interval(function() {
		if($scope.$root == null) return;
		
		if($scope.$root.currentPage == "Dashboard" && $scope.$root.errorTimes <= 5 && !$scope.loading) {
			$scope.getCompaniesPosition();  		
		}
	}, 10000);
	
	$scope.refreshDashboard();
	$scope.getDetectorsMaintenance();	
});