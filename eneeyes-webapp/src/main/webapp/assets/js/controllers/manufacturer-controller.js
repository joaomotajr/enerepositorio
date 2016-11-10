
app.controller('manufacturerController', function ($scope, $timeout, $filter, ManufacturerService) {
			
	$scope.saveManufacturer = function() {
		
		var manufacturer = {
			uid: 0,
			name: $scope.newManufacturer			
    	}; 
		 
		$scope.inclusaoManufacturer = new ManufacturerService.save(manufacturer);		 
		$scope.inclusaoManufacturer.$manufacturer({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$scope.manufacturers.push($scope.inclusaoManufacturer.t);                     	
        });
		
		$(".popover").popover('hide');
	 }
	 
	$scope.clearFormTransmitter = function () {
	
	    $scope.manufacturerUid = undefined;
	    $scope.manufacturerName = '';
	    $scope.manufacturerInitials = '';	    
	}

	$scope.getManufacturers = function() {
		 
		 $scope.resultManufacturers = new ManufacturerService.listAll();		 
		 $scope.resultManufacturers.$manufacturer({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.manufacturers = $scope.resultManufacturers.list; 		 			 
        });		 
	 }	 
 
	 $scope.editTransmitter = function (index) {
	        $scope.manufacturerUid = $scope.manufacturers[index].uid;
	        
		    $scope.manufacturerName = $scope.manufacturers[index].name;
		    manufacturerInitials = $scope.manufacturers[index].initials;		    	    
		    	        
	        $('#idManufacturerName').focus();
	    }
	 
	 $scope.deleteTransmitter = function(index) {
		 
		 var uid = $scope.manufacturers[index].uid;		  
		 
		 $scope.deletar = new TransmitterService.deletar();		 
		 $scope.deletar.$manufacturer({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 if (!$scope.deletar.isError)
				 $scope.manufacturers.splice(index, 1);
			 else {
				 $scope.msgErro = "Erro: " + $scope.deletar.message;
				 console.log($scope.deletar.systemMessage);
			 }
         	         	
		 }, function(data) {		
			 $scope.msgErro = "Erro: " + data.statusText;
		});		 
	 }
	 
	 $scope.refreshManufacturers = function() {
		 $scope.getManufacturers();	
	 } 

	 $scope.getManufacturers();	 
	
});