
app.controller('monitorController', function ($scope, $timeout, $interval, $filter, ViewService) {
	
	$scope.dashCompaniesAlarm = [];
	
	$scope.getCompaniesAlarm = function() {
		
		$scope.loading = true;	
		                                                    
		 $scope.listAllDashCompaniesAlarm = new ViewService.listAllDashCompaniesAlarm();		 
		 $scope.listAllDashCompaniesAlarm.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 
			 for(var i = 0; i < $scope.listAllDashCompaniesAlarm.list.length; i++) {				 
				 
				 $scope.dashCompaniesAlarm[i] = $scope.listAllDashCompaniesAlarm.list[i];
				 $scope.dashCompaniesAlarm[i].last_update_full = $scope.dashCompaniesAlarm[i].last_update;
				 $scope.dashCompaniesAlarm[i].last_update = timeSince($scope.dashCompaniesAlarm[i].last_update);				 
				 $scope.dashCompaniesAlarm[i].last_value	= Math.round($scope.dashCompaniesAlarm[i].last_value * 100) / 100 ;
			 }
			 
			 $scope.sumary = {
					 alarm1 :  0, 
					 alarm2 : 0,
					 alarm3 : 0,
					 normal : 0,
					 devices: 0,
					 offLine: 0						 
			 }			 
			 				
			 $scope.listAllDashCompaniesAlarm.list.forEach(
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
		
	$scope.getCompaniesAlarm();
    
    $interval(function() {
    	$scope.getCompaniesAlarm();     						
    }, 10000);	
    
	
	angular.element('body').removeClass('loading');		
	
});