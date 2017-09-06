app.filter('dashCompaniesPositionFilter', function () {
    return function (objects, criteria) {
        var filterResult = new Array();

        if (!criteria || criteria == 0 || criteria == 'ALL' )
            return objects;

        for (index in objects) {
            
        	if (criteria == 'OFF') {
                if (objects[index].offLine) {
                    filterResult.push(objects[index]);
                }
            }
            else if (objects[index].alarmType == criteria) {
                    filterResult.push(objects[index]);                
            }            
        }
                
        return filterResult;
    }
});

app.controller('dashController', function ($scope, $timeout, $interval, $filter, ViewService, UserService) {

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
		  [
			{
			    value: $scope.sumary.normal,
			    color: "#00a65a",
			    highlight: "#00a65a",
			    label: "Operacional(is)"
			  },
		    {
		      value: $scope.sumary.offLine,
		      color: "#333",
		      highlight: "#333",
		      label: "Off Line"
		    },	    
		    {
		      value: $scope.sumary.alarm3,
		      color: "#dd4b39",
		      highlight: "#dd4b39",
		      label: "Evacucao"
		    },
		    {
		      value: $scope.sumary.alarm2,
		      color: "#f39c12",
		      highlight: "#f39c12",
		      label: "Alerta"
		    },
		    {
		      value: $scope.sumary.alarm1,
		      color: "#d2d6de",
		      highlight: "#d2d6de",
		      label: "Deteccao"
		    },
		    {
		      value: $scope.sumary.turnOff,
		      color: "#72afd2",
		      highlight: "#72afd2",
		      label: "Turn Off"
		    }
		  ];
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
						 }
						 else {
							 e.next = Math.ceil(Math.abs(new Date($scope.listAllDashDetectorsMaintenance.serverDate) - 
									new Date(e.install_date + e.maintenance_interval*24*60*60*1000)) / (1000 * 3600 * 24));
							 e.final_date = new Date(e.install_date + e.maintenance_interval*24*60*60*1000); 
						 }
					}			
			 	);	
			}
			
			$scope.loading = undefined;         	         	
       });		 
	}
	
	$scope.getCompaniesPosition = function() {
		
		$scope.loading = true;	
		
		$scope.listAllDashCompaniesPosition = new ViewService.listAllDashCompaniesPosition();		 
		$scope.listAllDashCompaniesPosition.$view({_csrf : angular.element('#_csrf').val()}, function(){
		
			if($scope.listAllDashCompaniesPosition != null) {
				for(var i = 0; i < $scope.listAllDashCompaniesPosition.list.length; i++) {				 
					 
					 $scope.dashCompaniesPosition[i] = $scope.listAllDashCompaniesPosition.list[i];
					 $scope.dashCompaniesPosition[i].last_update_full = $scope.dashCompaniesPosition[i].last_update;
					 $scope.dashCompaniesPosition[i].last_update = timeSince($scope.listAllDashCompaniesPosition.serverDate, $scope.dashCompaniesPosition[i].last_update);				 
					 $scope.dashCompaniesPosition[i].last_value	= Math.round($scope.dashCompaniesPosition[i].last_value * 100) / 100 ;
				 }
				 
				 $scope.sumary = { alarm1 : 0, alarm2 : 0, alarm3 : 0, normal : 0, devices: 0, offLine: 0, turnOff: 0 }			 
				 				
				 $scope.listAllDashCompaniesPosition.list.forEach(
					 function(e) {
	
						$scope.sumary.devices ++;
						
						if ( e.alarmType == "OFF" || e.alarmType == "WITHOUT" ) {
							// $scope.sumary.offLine ++;
							$scope.sumary.turnOff ++;						
						}
						else if ( e.alarmType == "OFFLINE" ) {							 
						     $scope.sumary.offLine ++;						 
						}				
						else if ( e.alarmType == "NORMAL") {							 
						     $scope.sumary.normal ++;
						}
						else if ( e.alarmType == "DETECCAO") {
							$scope.sumary.alarm1 ++;							 
						}
						else if ( e.alarmType == "ALERTA") {
							$scope.sumary.alarm2 ++;							 
						}
						else if ( e.alarmType == "EVACUACAO") {
							$scope.sumary.alarm3 ++;	
							 
						}
					}			
			 	);			 			 
				
				populateDonut(new Date($scope.listAllDashCompaniesPosition.serverDate).toLocaleTimeString() );			
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
    };
		
	$scope.refreshDashboard = function() { 
		$scope.getCompaniesPosition();		
		$scope.pesquisaUser();
	}
	
	$scope.refreshDashboard();
	$scope.getDetectorsMaintenance();
	
    
    $interval(function() {
    	if($scope.$root == null) return;
    	
    	if($scope.$root.currentPage == "Dashboard" && $scope.$root.errorTimes <= 5) {
    		$scope.getCompaniesPosition();  		
    	}
    }, 10000);
    
	
	angular.element('body').removeClass('loading');		
	
});