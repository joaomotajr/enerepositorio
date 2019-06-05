
app.controller('manufacturerController', function ($scope, $timeout, $filter, ManufacturerService) {
			
	$scope.saveManufacturer = function() {
		
		var exists =  $scope.manufacturers.findIndex(function (i) { return i.name.toLowerCase() === $scope.manufacturerName.toLowerCase() });
		
		if ( exists >= 0  && $scope.manufacturerUid == undefined ) {
			$scope.manufacturerNameExist = "true";
			return;
		}	
		
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
				 $scope.msgErroManufacturer = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage);
			 }
		});		 
	 }
	 
	 $scope.keypress = function($event) {
	    $scope.lastKey = $event.keyCode
	    $scope.manufacturerNameExist = "false";
	  };
	 
	 $scope.refreshManufacturer = function() {
		 $scope.getManufacturers();	
	 } 

	 $scope.refreshManufacturer();	 
	 angular.element('body').removeClass('loading');	
});