
app.controller('gasController', function ($scope, $timeout, $filter, GasService) {
	
	$scope.saveGas = function() {
		
		var exists =  $scope.gases.findIndex(function (i) { return i.name.toLowerCase()  === $scope.gasName.toLowerCase() });
		
		if ( exists >= 0  && $scope.gasUid == undefined ) {
			$scope.gasNameExist = "true";
			return;
		}
	
		angular.element('body').addClass('loading');
		
		var gas = {
			uid: $scope.gasUid != undefined ? $scope.gasUid : 0,
			name: $scope.gasName,
			cas: $scope.gasCas,
			formula: $scope.gasFormula,
    	}; 
		 
		$scope.inclusaoGas = new GasService.save(gas);		 
		$scope.inclusaoGas.$gas({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$timeout(function () {				
				$scope.clearFormGas();
	            $scope.getGases();
	            
	            angular.element('body').removeClass('loading');				 
	         }, 500);
		});		 
	 }
		 
	$scope.clearFormGas = function () {	
	    $scope.gasUid = undefined;
	    $scope.gasName = '';
	    $scope.gasCas = '';
	    $scope.gasFormula = '';
	    $scope.gasNameExist = "false";
	}
	 
	$scope.getGases = function() {		 
		 $scope.resultGases = new GasService.listAll();		 
		 $scope.resultGases.$gas({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.gases = $scope.resultGases.list; 		 			 
         });		 
	 }	
 
	 $scope.editGas = function (index) {
	        $scope.gasUid = $scope.gases[index].uid;	        
		    $scope.gasName = $scope.gases[index].name;
		    $scope.gasCas = $scope.gases[index].cas;
		    $scope.gasFormula = $scope.gases[index].formula;		    		
	        $('#idGasName').focus();
	    }
	 
	 $scope.deleteGas = function(index) {
		 		 
		 var uid = $scope.gases[index].uid;		  
		 
		 $scope.deletar = new GasService.deletar();		 
		 $scope.deletar.$gas({_csrf : angular.element('#_csrf').val(), id : uid}, function() {			 
			 if (!$scope.deletar.isError)
				 $scope.gases.splice(index, 1);
			 else {
				 $scope.msgErroGas = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }
		});		 
	 };
	 
	 $scope.keypress = function($event) {
	    $scope.lastKey = $event.keyCode;
	    $scope.gasNameExist = "false";
	  };
	 
	 $scope.getGases();	 	 
	 angular.element('body').removeClass('loading');	
});