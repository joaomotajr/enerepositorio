
app.controller('alarmController', function ($scope, $timeout, $filter, AlarmService, GasService) {

	$scope.saveAlarm = function() {
		
		angular.element('body').addClass('loading');
					
		var alarm = {
			uid: $scope.alarmUid != undefined ? $scope.alarmUid : 0,
			name: $scope.alarmName,
			gasDto: $scope.alarmGas,			
			unitMeterGases : $scope.gasUnitMeterGases.uid,
			alarm1 : $scope.alarmAlarm1,
			alarm2 : $scope.alarmAlarm2,
			alarm3 : $scope.alarmAlarm3	    
    	}; 
		 
		$scope.inclusaoAlarm = new AlarmService.save(alarm);		 
		$scope.inclusaoAlarm.$alarm({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$timeout(function () {				
				$scope.clearFormAlarm();
	            $scope.getAlarms();                     	
	            angular.element('body').removeClass('loading');				 
	         }, 500);
           
//		}, function(data) {
//			angular.element('body').removeClass('loading');
//			$scope.msgErro = "Erro: " + data.statusText;
		});		 
	 }
		 
	$scope.clearFormAlarm = function () {
	
	    $scope.alarmUid = undefined;  
	    $scope.alarmName = '';	    
	    $scope.alarmGas = '';
	    $scope.gasUnitMeterGases = '';
		$scope.alarmAlarm1 = '';
		$scope.alarmAlarm2 = '';
		$scope.alarmAlarm3 = '';		
	}
	 
	$scope.getAlarms = function() {
		 
		 $scope.resultAlarms = new AlarmService.listAll();		 
		 $scope.resultAlarms.$alarm({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.alarms = $scope.resultAlarms.list;			 
         });		 
	 }	 
 
	 $scope.editAlarm = function (index) {
	        $scope.alarmUid = $scope.alarms[index].uid;
	        $scope.alarmGas = $scope.alarms[index].gasDto;
		    $scope.alarmName = $scope.alarms[index].name;		    
		    $scope.alarmAlarm1 = $scope.alarms[index].alarm1;
		    $scope.alarmAlarm2 = $scope.alarms[index].alarm2;
		    $scope.alarmAlarm3 = $scope.alarms[index].alarm3;
		    $scope.gasUnitMeterGases = $scope.getUnitMetersGases($scope.alarms[index].unitMeterGases);
		 		    		    
	        $('#idAlarmName').focus();
	    }
	 
	 $scope.deleteAlarm = function(index) {
		 
		 var uid = $scope.alarms[index].uid;		  
		 
		 $scope.deletar = new SensorService.deletar();		 
		 $scope.deletar.alarm({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 			 
			 if (!$scope.deletar.isError)
				 $scope.alarms.splice(index, 1);
			 else {
				 $scope.msgErro = "Erro: " + $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }
         	         	
//		 }, function(data) {		
//			 angular.element('body').removeClass('loading');
//			 $scope.msgErro = "Erro: " + data.statusText;
		});	 
	 }	 
	 
	 $scope.getDetectionTypes = function (name) {
		 
		 for (var i = 0; i < $scope.detectionTypes.length; i++) {
             if ($scope.detectionTypes[i].name == name) {
                 
            	 return $scope.detectionTypes[i];
             }
         } 		 
	 }
	 
	 $scope.getGases = function() {
		 
		 $scope.resultGases = new GasService.listAll();		 
		 $scope.resultGases.$gas({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.gases = $scope.resultGases.list; 		 			 
         });		 
	 }	
	 
	 function getClassNameWithNumberSuffix(el) {
        var className = null;
        var regexp = /\w+\d+/;
        $($(el).attr('class').split(' ')).each(function () {
            if (regexp.test(this)) {
                className = this;
                return false;
            }
        });

        return className;
	 }
	 
	 $scope.getUnitMetersGases = function (name) {
		 
		 for (var i = 0; i < $scope.unitMetersGases.length; i++) {
             if ($scope.unitMetersGases[i].name == name) {
                 
            	 return $scope.unitMetersGases[i];
             }
         } 		 
	 }
	 
	 $scope.unitMetersGases = 
		 [
		  	{ name : 'DESCONHECIDO', uid : 0 },
		  	{ name : 'PPM', uid :  1 },
		  	{ name : 'PPB', uid : 2 },
		  	{ name : 'LEL_PERCENT', uid : 3 },
		  	{ name : 'LEL_PERCENT_METRO', uid : 4 },
		  	{ name : 'PERCENT_VOLUME', uid : 5 }		  	
		 ]; 
	 
	 $scope.getAlarms();	 
	 $scope.getGases();

});