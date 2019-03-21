app.controller('unitMeterController', function ($scope, $timeout, UnitMeterService) {
			
	$scope.saveUnitMeter = function() {
		
		var exists =  $scope.unitMeters.findIndex(function (i) { 
				return i.symbol.toLowerCase() === $scope.unitMeterSymbol.toLowerCase(); 
			});
		
		if ( exists >= 0  && $scope.unitMeterUid == undefined ) {
			$scope.unitMeterTypeExist = "true";
			return;
		}	
		
		angular.element('body').addClass('loading');
		
		var unitMeter = {
			uid: $scope.unitMeterUid != undefined ? $scope.unitMeterUid : 0,			
			description: $scope.unitMeterDescription,
			symbol: $scope.unitMeterSymbol
    	}; 
		 
		$scope.inclusaoUnitMeter = new UnitMeterService.saveUnitMeter(unitMeter);		 
		$scope.inclusaoUnitMeter.$unitMeter({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$timeout(function () {				
            	$scope.clearFormUnitMeter();
                $scope.getUnitMeters();
	            
	            angular.element('body').removeClass('loading');				 
	         }, 500);
		});	
	};
	 
	$scope.clearFormUnitMeter = function () {
	
	    $scope.unitMeterUid = undefined;		
		$scope.unitMeterDescription = '';
		$scope.unitMeterSymbol = '';		
	};

	$scope.getUnitMeters = function() {		 
		$scope.listAllUnitMeter = new UnitMeterService.listAllUnitMeter();		 
		$scope.listAllUnitMeter.$unitMeter({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.unitMeters = $scope.listAllUnitMeter.list;
	   });		 
	};
 
	$scope.editUnitMeter = function (index) {
	    $scope.unitMeterUid = $scope.unitMeters[index].uid;	    
		$scope.unitMeterDescription = $scope.unitMeters[index].description;
		$scope.unitMeterSymbol = $scope.unitMeters[index].symbol;

	    $('#idUnitMeterDescription').focus();
	};
	 
	$scope.deleteUnitMeter = function(index) {
		 
		 var uid = $scope.unitMeters[index].uid;		  
		 
		 $scope.deletar = new UnitMeterService.deletaUnitMeter();		 
		 $scope.deletar.$unitMeter({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 if (!$scope.deletar.isError)
				 $scope.unitMeters.splice(index, 1);
			 else {
				 $scope.msgErroUnitMeter = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage);
			 }
		});		 
	};
	 
	$scope.keypress = function($event) {
	    $scope.lastKey = $event.keyCode;
	    $scope.unitMeterTypeExist = "false";
	};
		 
	$scope.getUnitMeters();	
		
	$scope.clearFormUnitMeter();
	angular.element('body').removeClass('loading');	

});