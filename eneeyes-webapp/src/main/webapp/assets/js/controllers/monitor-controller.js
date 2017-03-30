
app.controller('monitorController', function ($scope, $timeout, $interval, $filter, ViewService, PositionAlarmMessageService, ViewService) {
		
	$scope.getCompaniesPositionOffline = function() {
		
		$scope.loading = true;	
		
		$scope.listAllOffline = new ViewService.listAllOffline();		 
		$scope.listAllOffline.$view({_csrf : angular.element('#_csrf').val(), interval: 5}, function(){
			
			$scope.dashCompaniesOffline = [];
			 
			 for(var i = 0; i < $scope.listAllOffline.list.length; i++) {
								
				 $scope.dashCompaniesOffline[i] = $scope.listAllOffline.list[i];
				 $scope.dashCompaniesOffline[i].last_update_full = $scope.dashCompaniesOffline[i].last_update;
				 $scope.dashCompaniesOffline[i].last_update = timeSince($scope.dashCompaniesOffline[i].last_update);				 
				 $scope.dashCompaniesOffline[i].last_value	= Math.round($scope.dashCompaniesOffline[i].last_value * 100) / 100 ;								 
			 }			 
			 
		});		 
	 }	
	
	$scope.getCompaniesAlarm = function() {
		
		$scope.loading = true;	
		                                                    
		 $scope.listAllDashCompaniesAlarm = new ViewService.listAllDashCompaniesAlarm();		 
		 $scope.listAllDashCompaniesAlarm.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 
			 $scope.dashCompaniesAlarmCreated = [];
			 $scope.dashCompaniesAlarmReaded = [];			 
			 
			 $scope.listAllDashCompaniesAlarm.list.forEach(
				 function(e) {			
					 					 
					 e.last_update_full = e.last_update;
					 e.last_update = timeSince(e.last_update);				 
					 e.last_value	= Math.round(e.last_value * 100) / 100;
					 
					if (e.alarmStatus == "CREATED") {
						$scope.dashCompaniesAlarmCreated.push(e);
					}
					else if (e.alarmStatus == "READED") {
						$scope.dashCompaniesAlarmReaded.push(e);
					}
				}	
			 );		 			 
				 
			 $scope.loading = undefined;
         	         	
       });		 
	 }	
	       
	$scope.savePositionAlarmMessage = function() {
		
		var positionAlarmMessage = {
			uid: 0,
			message: $scope.selectedPositionAlarm.feedback,
			//user: null,
			positionAlarmDto: {uid: $scope.selectedPositionAlarm.uid}
		};
		 
		$scope.inclusaoPositionAlarmMessage = new PositionAlarmMessageService.save(positionAlarmMessage);
		$scope.inclusaoPositionAlarmMessage.$positionAlarmMessage({_csrf : angular.element('#_csrf').val()});			 
	}
	
	$scope.getPositionAlarmMessage = function(positionAlarmId) {
						 
		$scope.inclusaoPositionAlarmMessage = new PositionAlarmMessageService.listByPositionAlarmId(positionAlarmId);
		$scope.inclusaoPositionAlarmMessage.$positionAlarmMessage({_csrf : angular.element('#_csrf').val(), id : positionAlarmId }, function(){
		
			$scope.selectedPositionAlarm.messages = $scope.inclusaoPositionAlarmMessage.list;
						 
		});
	}

	
	$scope.editActionCreated = function(index) {
		
		$scope.isOffline = false;
		$scope.selectedPositionAlarm = $scope.dashCompaniesAlarmCreated[index];
		$scope.getPositionAlarmMessage($scope.selectedPositionAlarm.uid);
		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);		
	}
	
	$scope.editActionReaded = function(index) {
		
		$scope.isOffline = false;
		$scope.selectedPositionAlarm = $scope.dashCompaniesAlarmReaded[index];
		$scope.getPositionAlarmMessage($scope.selectedPositionAlarm.uid);
		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);		
	}
	
	$scope.editActionOffline = function(index) {
		
		$scope.isOffline = true;
		$scope.selectedPositionAlarm = $scope.dashCompaniesOffline[index];
		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);
		
	}
		
	$scope.getCompaniesAlarm();
	$scope.getCompaniesPositionOffline();
    
    $interval(function() {
    	if($scope.$root.currentPage == "Monitoramento")
    		$scope.getCompaniesAlarm();
    		$scope.getCompaniesPositionOffline();
    }, 10000);	
    
	
	angular.element('body').removeClass('loading');		
	
});