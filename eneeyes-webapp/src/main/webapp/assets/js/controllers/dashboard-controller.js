
app.controller('dashController', function ($scope, $timeout, $interval, $filter, ViewService) {
	
	$scope.dashCompaniesPosition = [];
	
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
					 offLine: 0						 
			 }			 
			 				
			 $scope.listAllDashCompaniesPosition.list.forEach(
				 function(e) {

					$scope.sumary.devices ++;
					
					var offDate = (new Date() - new Date(e.last_update_full)) / 1000;
					
					// off line por mais de 5 minutos
					if ( offDate > 300 ) {							 
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
			 
			$scope.loading = undefined;         	         	
       });		 
	 }
		
	$scope.getCompaniesPosition();
    
    $interval(function() {
    	if($scope.$root == null) return;
    	if($scope.$root.currentPage == "Dashboard" && $scope.$root.errorTimes <= 5)
    		$scope.getCompaniesPosition();     						
    }, 10000);	
    
	
	angular.element('body').removeClass('loading');		
	
});