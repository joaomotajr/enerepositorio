
app.controller('logAuditoriaController', function ($scope, $timeout, $filter, LogAuditoriaService) {

	logAlarms = [];
	
	$scope.getLogAuditoria = function() {
		                                                    
		 $scope.listAllLogs = new LogAuditoriaService.listAll();		 
		 $scope.listAllLogs.$logAuditoria({_csrf : angular.element('#_csrf').val()}, function(){
			 		 
			 if($scope.listAllLogs != null && $scope.listAllLogs.list.length > 0 )
				 $scope.logsAuditoria = $scope.listAllLogs.list
       });		 
	}
	
   
   $scope.getLogAuditoria();

   angular.element('body').removeClass('loading');
    		
});