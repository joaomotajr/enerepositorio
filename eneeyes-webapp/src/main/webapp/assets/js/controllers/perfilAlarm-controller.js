
app.controller('perfilAlarmController', function ($scope, $timeout, PerfilAlarmService) {
			
	$scope.savePerfilAlarm = function() {
		
		var exists =  $scope.perfilAlarms.findIndex(function (i) { return i.type.toLowerCase() === $scope.perfilAlarmType.toLowerCase(); });		
		if ( exists >= 0  && $scope.perfilAlarmUid == undefined ) {
			$scope.perfilAlarmNameExist = "true";
			return;
		}	
		
		angular.element('body').addClass('loading');		
		var perfilAlarm = {
			uid: $scope.perfilAlarmUid != undefined ? $scope.perfilAlarmUid : 0,
			type: $scope.perfilAlarmType,
			description: $scope.perfilAlarmDescription,
			symbol: $scope.perfilAlarmSymbol
    	}; 
		 
		$scope.inclusaoPerfilAlarm = new PerfilAlarmService.savePerfilAlarm(perfilAlarm);		 
		$scope.inclusaoPerfilAlarm.$perfilAlarm({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$timeout(function () {				
            	$scope.clearFormPerfilAlarm();
                $scope.getPerfilAlarms();	            
	            angular.element('body').removeClass('loading');				 
	         }, 500);
		});	
	 };
	 
	$scope.clearFormPerfilAlarm = function () {	
	    $scope.perfilAlarmUid = undefined;
		$scope.perfilAlarmType = '';
		$scope.perfilAlarmDescription = '';
		$scope.perfilAlarmSymbol = '';
	};

	$scope.getPerfilAlarms = function() {		 
		$scope.listAllPerfilAlarm = new PerfilAlarmService.listAllPerfilAlarm();		 
		$scope.listAllPerfilAlarm.$perfilAlarm({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.perfilAlarms = $scope.listAllPerfilAlarm.list;
	   });		 
	};
 
	$scope.editPerfilAlarm = function (index) {
	    $scope.perfilAlarmUid = $scope.perfilAlarms[index].uid;	        
		$scope.perfilAlarmType = $scope.perfilAlarms[index].type;
		$scope.perfilAlarmDescription = $scope.perfilAlarms[index].description;		    	    
		$scope.perfilAlarmSymbol = $scope.perfilAlarms[index].symbol;
	    $('#perfilAlarmTitulo').focus();
	};

	$scope.changeSymbol = function(s) {
		$scope.perfilAlarmSymbol = s;
	};
	 
	$scope.deletePerfilAlarm = function(index) {		 
		 var uid = $scope.perfilAlarms[index].uid;		 
		 $scope.deletar = new PerfilAlarmService.deletaPerfilAlarm();		 
		 $scope.deletar.$perfilAlarm({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 if (!$scope.deletar.isError)
				 $scope.perfilAlarms.splice(index, 1);
			 else {
				 $scope.msgErroPerfilAlarm = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage);
			 }
		});		 
	 };	
	 
	 $scope.refreshPerfilAlarms = function() {				
		$scope.symbols = faSymbols;
		$scope.clearFormPerfilAlarm();
		$scope.getPerfilAlarms();
	 };

	 $scope.getGraphiType = function (type) {		 
		for (var i = 0; i < $scope.images.length; i++) {
			if ($scope.images[i].type == type) {                 
				return $scope.images[i];
			}
		} 		 
	};
	
	$scope.refreshPerfilAlarms();
	angular.element('body').removeClass('loading');	
});