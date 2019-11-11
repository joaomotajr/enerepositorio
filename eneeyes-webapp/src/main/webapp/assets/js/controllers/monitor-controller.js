
app.controller('monitorController', function ($scope, $timeout, $interval, PositionAlarmMessageService, PositionAlarmService, ViewService) {

	$scope.dashCompaniesAlarm = [];
	
	$scope.updateSound = function(uid) {				
		$scope.selectedPositionAlarm = getSelectedItem(uid);
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

			$scope.listAllDashCompaniesAlarm.list.forEach(function(item, index)  {
				var exists = $scope.dashCompaniesAlarm.find(function(e) {
					return item.uid === e.uid;
				});
				if (!exists) {
					var elem = {};
					elem = item;
					elem.last_update_full = $scope.listAllDashCompaniesAlarm.list[index].last_update;
					elem.last_update = timeSinceLocal($scope.listAllDashCompaniesAlarm.list[index].last_update);				 
					elem.last_value = Math.round($scope.listAllDashCompaniesAlarm.list[index].last_value * 100) / 100;
					elem.index = index;
					$scope.dashCompaniesAlarm.push(elem);
				} else {
					exists = item;
					exists.last_update_full = $scope.listAllDashCompaniesAlarm.list[index].last_update;
					exists.last_update = timeSinceLocal($scope.listAllDashCompaniesAlarm.list[index].last_update);				 
					exists.last_value = Math.round($scope.listAllDashCompaniesAlarm.list[index].last_value * 100) / 100;
				}
			});

			$scope.dashCompaniesAlarm.forEach(function(item, index)  {
				var exists = $scope.listAllDashCompaniesAlarm.list.find(function(e) {
					return item.uid === e.uid;
				});
				if (!exists) {
					$scope.dashCompaniesAlarm.splice(index, 1);
				} else {
					if (exists.alarmStatus == "CREATED" && exists.soundStatus == "ON") {
						$scope.playSound();
					}
				}
			});
						 
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
			removeSelectedItem($scope.selectedPositionAlarm.uid);
			$scope.getCompaniesAlarm();
		});
	}
	
	$scope.getPositionAlarmMessage = function(positionAlarmId) {						 
		$scope.inclusaoPositionAlarmMessage = new PositionAlarmMessageService.listByPositionAlarmId(positionAlarmId);
		$scope.inclusaoPositionAlarmMessage.$positionAlarmMessage({_csrf : angular.element('#_csrf').val(), id : positionAlarmId }, function() {		
			$scope.selectedPositionAlarm.messages = $scope.inclusaoPositionAlarmMessage.list;						 
		});
	};
	
	$scope.editActionCreated = function(uid) {
		$scope.onlyHistoric = false;
		$scope.selectedPositionAlarm = getSelectedItem(uid);
		$scope.getPositionAlarmMessage($scope.selectedPositionAlarm.uid);		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);		
	};

	function getSelectedItem(uid) {
		return $scope.dashCompaniesAlarm.find(function(e) {
			return uid === e.uid;
		});	
	}

	function removeSelectedItem(uid) {
		 var index = $scope.dashCompaniesAlarm.find(function(e) {
			return uid === e.uid;
		});	

		if (index) {
			$scope.dashCompaniesAlarm.splice(index, 1);
		}
	}
	
	$scope.editActionReaded = function(uid) {		
		$scope.onlyHistoric = false;
		$scope.selectedPositionAlarm = getSelectedItem(uid);
		$scope.getPositionAlarmMessage($scope.selectedPositionAlarm.uid);
		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);		
	};
	
	$scope.editActionSolved = function(uid) {		
		$scope.onlyHistoric = true;
		$scope.selectedPositionAlarm = getSelectedItem(uid);
		$scope.getPositionAlarmMessage($scope.selectedPositionAlarm.uid);
		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);		
	};
	
	$scope.updateAlarmStatus = function(status) {		
		$scope.updateStatus = new PositionAlarmService.updateStatus();				 
		$scope.updateStatus.$positionAlarm({_csrf : angular.element('#_csrf').val(), alarmStatus : status, uid: $scope.selectedPositionAlarm.uid}, function() {
			removeSelectedItem($scope.selectedPositionAlarm.uid);
			$scope.getCompaniesAlarm();
		});		 
	};
			
	$scope.getCompaniesAlarm();
	    
    $interval(function() {
    	if($scope.$root == null) return;    	
    	if($scope.$root.errorTimes <= 5 && !$scope.loading) {
    		$scope.getCompaniesAlarm();    		
    	}
	}, 30000);
		
	angular.element('body').removeClass('loading');
});