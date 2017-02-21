
app.controller('dashController', function ($scope, $timeout, $interval, $filter, PositionService, ViewService) {
	
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
	    });			
	}
	
	
	$scope.getCompaniesPosition = function() {
		 
		 $scope.listAllDashCompaniesPosition = new ViewService.listAllDashCompaniesPosition();		 
		 $scope.listAllDashCompaniesPosition.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.CompaniesPositions = $scope.listAllDashCompaniesPosition.list; 
			 console.log($scope.listAllDashCompaniesPosition);		         	         	
       });		 
	 }
	
	$scope.alarmFilter = function(item){
	    if(item.alarmType != 'NORMAL' || item.offLine){
	        return item;
	    }
	};
		
	//$scope.getCompaniesPosition();
	$scope.getPositions();
	
	$interval(function(){
		$scope.getPositions();
    }, 10000)
	
	angular.element('body').removeClass('loading');		
	
});