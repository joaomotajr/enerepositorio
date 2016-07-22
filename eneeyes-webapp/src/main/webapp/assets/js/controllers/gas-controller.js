
app.controller('gasController', function ($scope, $timeout, $filter, GasService) {
	    
	
	$scope.saveGas = function() {
		
		var gas = {
			uid: $scope.gasUid != undefined ? $scope.gasUid : 0,
			name: $scope.gasName,
			cas: $scope.gasCas,
			formula: $scope.gasFormula,
			unitMeterGases : $scope.gasUnitMeterGases.uid
    	}; 
		 
		$scope.inclusaoGas = new GasService.save(gas);		 
		$scope.inclusaoGas.$gas({_csrf : angular.element('#_csrf').val()}, function()
		{         	
         	
         	$scope.clearFormGas();
            $scope.getGases();
                     	
         });		 
	 }
		 
	$scope.clearFormGas = function () {
	
	    $scope.gasUid = undefined;
	    $scope.gasName = '';
	    $scope.gasCas = '';
	    $scope.gasFormula = '';	
	    $scope.gasUnitMeterGases = '';
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
		    $scope.gasUnitMeterGases = $scope.getUnitMetersGases($scope.gases[index].unitMeterGases);
		    
		    	        
	        $('#idGasName').focus();
	    }
	 
	 $scope.deleteGas = function(index) {
		 
		 var uid = $scope.gases[index].uid;		  
		 
		 $scope.deletar = new GasService.deletar();		 
		 $scope.deletar.$gas({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 
			 $scope.gass.splice(index, 1);
         	         	
         });		 
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
	 
	 $scope.getGases();	 
	
});