
app.controller('manufacturerController', function ($scope, $timeout, $filter, ManufacturerService) {
			
	$scope.saveManufacturer = function() {
		
		angular.element('body').addClass('loading');
		
		var manufacturer = {
			uid: $scope.manufacturerUid != undefined ? $scope.manufacturerUid : 0,
			name: $scope.manufacturerName,
			initials: $scope.manufacturerInitials			
    	}; 
		 
		$scope.inclusaoManufacturer = new ManufacturerService.save(manufacturer);		 
		$scope.inclusaoManufacturer.$manufacturer({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$timeout(function () {				
            	$scope.clearFormManufacturer();
                $scope.getManufacturers();
	            
	            angular.element('body').removeClass('loading');				 
	         }, 500);
			
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
		});
	
	 }
	 
	$scope.clearFormManufacturer = function () {
	
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
 
	 $scope.editManufacturer = function (index) {
	        $scope.manufacturerUid = $scope.manufacturers[index].uid;
	        
		    $scope.manufacturerName = $scope.manufacturers[index].name;
		    $scope.manufacturerInitials = $scope.manufacturers[index].initials;		    	    
		    	        
	        $('#idManufacturerName').focus();
	    }
	 
	 $scope.deleteManufacturer = function(index) {
		 
		 var uid = $scope.manufacturers[index].uid;		  
		 
		 $scope.deletar = new ManufacturerService.deletar();		 
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