
app.controller('monitorController', function ($scope, $timeout, $interval, $filter, ViewService, PositionAlarmMessageService) {
	
	$scope.dashCompaniesAlarm = [];
	
	$scope.getCompaniesAlarm = function() {
		
		$scope.loading = true;	
		                                                    
		 $scope.listAllDashCompaniesAlarm = new ViewService.listAllDashCompaniesAlarm();		 
		 $scope.listAllDashCompaniesAlarm.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 
			 $scope.dashCompaniesAlarm = []; 
			 
			 for(var i = 0; i < $scope.listAllDashCompaniesAlarm.list.length; i++) {				 
				 
				 $scope.dashCompaniesAlarm[i] = $scope.listAllDashCompaniesAlarm.list[i];
				 $scope.dashCompaniesAlarm[i].last_update_full = $scope.dashCompaniesAlarm[i].last_update;
				 $scope.dashCompaniesAlarm[i].last_update = timeSince($scope.dashCompaniesAlarm[i].last_update);				 
				 $scope.dashCompaniesAlarm[i].last_value	= Math.round($scope.dashCompaniesAlarm[i].last_value * 100) / 100 ;
			 }
			 
			 $scope.loading = undefined;
         	         	
       });		 
	 }	
	       
	$scope.savePositionAlarmMessage = function() {
		angular.element('body').addClass('loading');

		var positionAlarmMessage = {
			uid: 0,
			message: $scope.selectedPositionAlarm.feedback,
			//user: null,
			positionAlarmDto: {uid: $scope.selectedPositionAlarm.uid}
		};
		 
		$scope.inclusaoPositionAlarmMessage = new PositionAlarmMessageService.save(positionAlarmMessage);
		$scope.inclusaoPositionAlarmMessage.$positionAlarmMessage({_csrf : angular.element('#_csrf').val()});			 
	}
	
	$scope.editAction = function(index) {
		
		$scope.selectedPositionAlarm = $scope.dashCompaniesAlarm[index];
		
		$timeout(function () {
            $('#modalAction').modal({ show: 'false' });                        
        }, 200);
		
	}
	
	$scope.statusCreatedFilter = function(item){
		if(item.alarmStatus === "CREATED"){
		    return item;
		}
	};
	 
	$scope.statusReadedFilter = function(item){
		if(item.alarmStatus === "READED"){
			return item;
        }
	};
		
	$scope.getCompaniesAlarm();
    
    $interval(function() {
    	if($scope.$root.currentPage == "Monitoramento")
    		$scope.getCompaniesAlarm();     						
    }, 10000);	
    
	
	angular.element('body').removeClass('loading');		
	
});