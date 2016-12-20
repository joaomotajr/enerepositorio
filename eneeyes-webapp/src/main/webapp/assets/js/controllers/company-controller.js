app.controller('companyController', function ($scope, $timeout, $filter, CompanyService) {

    var map;
		
	$scope.mapsCompanyUnits = function () {

		if($scope.selectedCompany.unitsDto.length > 1) {
			
			map = new google.maps.Map(document.getElementById('mapCompany'), {
				zoom: 8,
				mapTypeId: google.maps.MapTypeId.ROADMAP
	        });
	
	        var infowindow = new google.maps.InfoWindow();
	        var bounds = new google.maps.LatLngBounds();
	        var marker, i;

         
	        for (i = 0; i < $scope.selectedCompany.unitsDto.length; i++) {  
	        	marker = new google.maps.Marker({
	        		position: new google.maps.LatLng($scope.selectedCompany.unitsDto[i].latitude, $scope.selectedCompany.unitsDto[i].longitude),
	        		map: map
	        	});
	          
	        	bounds.extend(marker.position);
	
	        	google.maps.event.addListener(marker, 'click', (function(marker, i) {
	        		return function() {
	        			infowindow.setContent($scope.selectedCompany.unitsDto[i].name);
	        			infowindow.open(map, marker);
	        		}
	        	})(marker, i));
	        }
	        
	        map.fitBounds(bounds);
		}
        else {
        	var lat = $scope.selectedCompany.unitsDto[0].latitude; 
        	var lng = $scope.selectedCompany.unitsDto[0].longitude;
        	
        	 var myOptions = {
		        zoom: 12,
		        center: new google.maps.LatLng(lat, lng),
		        mapTypeId: google.maps.MapTypeId.ROADMAP
		    };	    
		    
		    map = new google.maps.Map(document.getElementById("mapCompany"), myOptions);        
	        var latlng = new google.maps.LatLng(lat, lng);
        	            
            new google.maps.Marker({
            	position: latlng,
                map: map,
                title : $scope.selectedCompany.unitsDto[0].name
           });
        	
        }
	}
	
	$scope.selectedCompany = {};
	angular.copy($scope.$root.selectedCompany, $scope.selectedCompany);
	
	$timeout(function () {
		$scope.mapsCompanyUnits();				    	 	
	}, 250);
		
});