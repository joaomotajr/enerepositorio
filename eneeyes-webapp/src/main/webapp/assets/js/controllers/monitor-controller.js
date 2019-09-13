
app.controller('monitorController', function ($scope, $timeout, $interval, PositionAlarmMessageService, PositionAlarmService, ViewService) {

	$scope.dashCompaniesAlarm = [];
	
	$scope.updateSound = function(index) {		
		$scope.selectedPositionAlarm = $scope.dashCompaniesAlarm[index];		
		$scope.updateSoundStatus = new PositionAlarmService.updateSoundStatus();				 
		$scope.updateSoundStatus.$positionAlarm({_csrf : angular.element('#_csrf').val(), soundStatus : 'SILENT', uid: $scope.selectedPositionAlarm.uid}, function() {
			$scope.dashCompaniesAlarm[index].soundStatus = 'SILENT';
		});		
	 };
	
	$scope.playSound = function() {		
		elementLoaded = document.getElementById('alarmSound');		
		if (elementLoaded != null)
			elementLoaded.play();
	};
	
	$scope.getCompaniesAlarm = function() {		
		$scope.loading = true;		                                                    
		$scope.listAllDashCompaniesAlarm = new ViewService.listAllDashCompaniesAlarmByAlarms();		 
		$scope.listAllDashCompaniesAlarm.$view({_csrf : angular.element('#_csrf').val()}, function() {
			
			if(!$scope.listAllDashCompaniesAlarm.list) {
				$scope.loading = false;	
				return;
			}

			for(var i = 0; i < $scope.listAllDashCompaniesAlarm.list.length; i++) {				
				$scope.dashCompaniesAlarm[i] = $scope.listAllDashCompaniesAlarm.list[i];
				$scope.dashCompaniesAlarm[i].last_update_full = $scope.listAllDashCompaniesAlarm.list[i].last_update;
				$scope.dashCompaniesAlarm[i].last_update = timeSince($scope.listAllDashCompaniesAlarm.list[i].last_update);				 
				$scope.dashCompaniesAlarm[i].last_value	 = Math.round($scope.listAllDashCompaniesAlarm.list[i].last_value * 100) / 100 ;
				$scope.dashCompaniesAlarm[i].index = i; 
			
				if ($scope.dashCompaniesAlarm[i].alarmStatus == "CREATED" && $scope.dashCompaniesAlarm[i].soundStatus == "ON") {
					$scope.playSound();
				}
			}				 
			$scope.loading = undefined;         	         	
       });
	 };	
	       
	$scope.savePositionAlarmMessage = function() {		
		var positionAlarmMessage = {
			uid: 0,
			message: $scope.selectedPositionAlarm.feedback,
			lastUpdate: null,
			positionAlarmDto: { uid: $scope.selectedPositionAlarm.uid },
			userDto : {id : $scope.$root.userId}			
		};
		$scope.inclusaoPositionAlarmMessage = new PositionAlarmMessageService.save(positionAlarmMessage);
		$scope.inclusaoPositionAlarmMessage.$positionAlarmMessage({_csrf : angular.element('#_csrf').val()}, function() {				
			$scope.getCompaniesAlarm(); 
		});
	}
	
	$scope.getPositionAlarmMessage = function(positionAlarmId) {						 
		$scope.inclusaoPositionAlarmMessage = new PositionAlarmMessageService.listByPositionAlarmId(positionAlarmId);
		$scope.inclusaoPositionAlarmMessage.$positionAlarmMessage({_csrf : angular.element('#_csrf').val(), id : positionAlarmId }, function() {		
			$scope.selectedPositionAlarm.messages = $scope.inclusaoPositionAlarmMessage.list;						 
		});
	}
	
	$scope.editActionCreated = function(index) {		
		$scope.onlyHistoric = false;
		$scope.selectedPositionAlarm = $scope.dashCompaniesAlarm[index];
		$scope.getPositionAlarmMessage($scope.selectedPositionAlarm.uid);
		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);		
	}
	
	$scope.editActionReaded = function(index) {		
		$scope.onlyHistoric = false;
		$scope.selectedPositionAlarm = $scope.dashCompaniesAlarm[index];
		$scope.getPositionAlarmMessage($scope.selectedPositionAlarm.uid);
		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);		
	};
	
	$scope.editActionSolved = function(index) {		
		$scope.onlyHistoric = true;
		$scope.selectedPositionAlarm = $scope.dashCompaniesAlarm[index];
		$scope.getPositionAlarmMessage($scope.selectedPositionAlarm.uid);
		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);		
	};
	
	$scope.updateAlarmStatus = function(status) {		
		$scope.updateStatus = new PositionAlarmService.updateStatus();				 
		$scope.updateStatus.$positionAlarm({_csrf : angular.element('#_csrf').val(), alarmStatus : status, uid: $scope.selectedPositionAlarm.uid}, function() {
			$scope.getCompaniesAlarm();
		});		 
	};
			
	$scope.getCompaniesAlarm();
	    
    $interval(function() {
    	if($scope.$root == null) return;    	
    	if($scope.$root.errorTimes <= 5 && !$scope.loading) {
    		$scope.getCompaniesAlarm();    		
    	}
	}, 10000);
		
	angular.element('body').removeClass('loading');
});