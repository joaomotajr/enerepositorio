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

	var pieChartCanvas;
	var pieChart;
	var PieData;

	function populateDonut() {
		PieData = 
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
		      label: "Evacuação"
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
		      label: "Detecção"
		    },
		    {
		      value: $scope.sumary.turnOff,
		      color: "#72afd2",
		      highlight: "#72afd2",
		      label: "Turn Off"
		    }
		  ];
	}
	
	var pieOptions = {
	    //Boolean - Whether we should show a stroke on each segment
	    segmentShowStroke: true,
	    //String - The colour of each segment stroke
	    segmentStrokeColor: "#fff",
	    //Number - The width of each segment stroke
	    segmentStrokeWidth: 1,
	    //Number - The percentage of the chart that we cut out of the middle
	    percentageInnerCutout: 50, // This is 0 for Pie charts
	    //Number - Amount of animation steps
	    animationSteps: 100,
	    //String - Animation easing effect
	    animationEasing: "easeOutBounce",
	    //Boolean - Whether we animate the rotation of the Doughnut
	    animateRotate: true,
	    //Boolean - Whether we animate scaling the Doughnut from the centre
	    animateScale: false,
	    //Boolean - whether to make the chart responsive to window resizing
	    responsive: true,
	    // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
	    maintainAspectRatio: false,
	    //String - A legend template
	    legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>",
	    //String - A tooltip template
	    tooltipTemplate: "<%=value %> Dispositivos <%=label%>"
	};
	
	$scope.dashCompaniesPosition = [];
	$scope.dashDetectorsMaintenance = [];
	
	$scope.getDetectorsMaintenance = function() {
		
		$scope.loading = true;	
		
		$scope.listAllDashDetectorsMaintenance = new ViewService.listAllDashDetectorsMaintenance();		 
		$scope.listAllDashDetectorsMaintenance.$view({_csrf : angular.element('#_csrf').val()}, function(){
			
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
			
			$scope.loading = undefined;         	         	
       });		 
	}
	
	$scope.getCompaniesPosition = function() {
		
		$scope.loading = true;	
		
		$scope.listAllDashCompaniesPosition = new ViewService.listAllDashCompaniesPosition();		 
		$scope.listAllDashCompaniesPosition.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 
			 for(var i = 0; i < $scope.listAllDashCompaniesPosition.list.length; i++) {				 
				 
				 $scope.dashCompaniesPosition[i] = $scope.listAllDashCompaniesPosition.list[i];
				 $scope.dashCompaniesPosition[i].last_update_full = $scope.dashCompaniesPosition[i].last_update;
				 $scope.dashCompaniesPosition[i].last_update = timeSince($scope.listAllDashCompaniesPosition.serverDate, $scope.dashCompaniesPosition[i].last_update);				 
				 $scope.dashCompaniesPosition[i].last_value	= Math.round($scope.dashCompaniesPosition[i].last_value * 100) / 100 ;
			 }
			 
			 $scope.sumary = {
					 alarm1 :  0, 
					 alarm2 : 0,
					 alarm3 : 0,
					 normal : 0,
					 devices: 0,
					 offLine: 0,
					 turnOff: 0
			 }			 
			 				
			 $scope.listAllDashCompaniesPosition.list.forEach(
				 function(e) {

					$scope.sumary.devices ++;
					
					var offDate = (new Date() - new Date(e.last_update_full)) / 1000;
					
					// off line por mais de 5 minutos
					
					if ( e.alarmType == "OFF" ) {
						$scope.sumary.offLine ++;
						$scope.sumary.turnOff ++;						
					}
					else if ( offDate > 300 ) {							 
					     $scope.sumary.offLine ++;
					     e.offLine = true;
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
			
			populateDonut();
			
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
    		pieChart.Doughnut(PieData, pieOptions);
    	}
    }, 10000);
    
    $timeout(function () {
    	pieChartCanvas = $("#pieChart").get(0).getContext("2d");
		pieChart = new Chart(pieChartCanvas);
    	
    	pieChart.Doughnut(PieData, pieOptions);
			            
    }, 500);
    
	
	angular.element('body').removeClass('loading');		
	
});