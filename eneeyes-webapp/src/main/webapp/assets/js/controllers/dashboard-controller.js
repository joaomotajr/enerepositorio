
app.controller('dashController', function ($scope, $timeout, $interval, $filter, PositionService, ViewService) {
	
	$scope.getCompaniesPosition = function() {
		
		$scope.loading = true;	
		
		 $scope.listAllDashCompaniesPosition = new ViewService.listAllDashCompaniesPosition();		 
		 $scope.listAllDashCompaniesPosition.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 
			 $scope.sumary = {
					 alarm1 :  0, 
					 alarm2 : 0,
					 alarm3 : 0,
					 normal : 0,
					 devices: 0,
					 offLine: 0						 
			 }			 
			 
			 var twoMinutesLater = new Date();
				
			 $scope.listAllDashCompaniesPosition.list.forEach(
				function(e) {

						$scope.sumary.devices ++;
						
						var offDate = (twoMinutesLater - new Date(e.last_update)) / 1000;
						
						// off line por mais de 5 minutos
						if ( offDate > 300 ) {							 
						     $scope.sumary.offLine ++;
						     e.offLine = true;
						}
						
						if ( e.alarmType == "NORMAL") {
							 
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
    	$scope.getCompaniesPosition();     						
    }, 10000);	
    
	
	angular.element('body').removeClass('loading');		
	
});