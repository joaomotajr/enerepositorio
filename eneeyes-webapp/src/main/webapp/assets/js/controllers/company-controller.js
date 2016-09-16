// Mudar a Company pra cá ou deixar só o maps???
app.controller('companyController', function ($scope, $timeout, $filter, CompanyService) {

    var map;
		
	$scope.mapsCompanyUnits = function () {
		
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

	$scope.selectedCompany = $scope.$root.selectedCompany;
	
	$timeout(function () {
		$scope.mapsCompanyUnits();				    	 	
	}, 250);
		
});