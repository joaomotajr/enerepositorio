
app.controller('gasController', function ($scope, $timeout, $filter, GasService) {
	    
	
	$scope.saveGas = function() {
		
		angular.element('body').addClass('loading');
		
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
			$timeout(function () {				
				$scope.clearFormGas();
	            $scope.getGases();
	            
	            angular.element('body').removeClass('loading');				 
	         }, 500);	
			
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
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
			 
			 if (!$scope.deletar.isError)
				 $scope.gases.splice(index, 1);
			 else {
				 $scope.msgErro = "Erro: " + $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }
         	         	
		 }, function(data) {		
			 $scope.msgErro = "Erro: " + data.statusText;
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