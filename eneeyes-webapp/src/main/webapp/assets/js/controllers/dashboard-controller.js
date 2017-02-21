app.controller('dashController', function ($scope, $timeout, $interval, $filter, PositionService) {
	
	$scope.getPositions = function() {
		
		 $scope.listPositions = new PositionService.listAll();		 
		 $scope.listPositions.$position({_csrf : angular.element('#_csrf').val()}, function() {		
			 
			 $scope.sumary = {
					 alarm1 :  0, 
					 alarm2 : 0,
					 alarm3 : 0,
					 normal : 0,
					 devices: 0,
					 offLine: 0						 
			 }			 
			 
			 var twoMinutesLater = new Date();
			 //twoMinutesLater.setMinutes(twoMinutesLater.getMinutes() + 2);
				
			 $scope.listPositions.list.forEach(
				function(e) {

						$scope.sumary.devices ++;
						
						var offDate = (twoMinutesLater - new Date(e.lastUpdate)) / 1000;
						
						if ( offDate > 120 ) {							 
						     $scope.sumary.offLine ++;
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
							
			
	    });			
	}
		
	$scope.getPositions();
	
	$interval(function(){
		$scope.getPositions();
    }, 20000)
	
	angular.element('body').removeClass('loading');		
	
});