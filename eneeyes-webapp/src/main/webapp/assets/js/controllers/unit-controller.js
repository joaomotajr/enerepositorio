app.controller('unitController', function ($scope, $timeout, $filter, UnitService, AreaService, CompanyService) {

    var map;
	var geocoder;
	var loadGauge = false;
	
	$scope.showDanger = function(msg) {		
		angular.element('body').removeClass('loading');
		 $scope.$root.msgDanger = msg ;
        $('#resultDanger').hide().show('slow').delay(1000).hide('slow');	
	}
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.$root.msgInfo = msg;
        $('#resultInfo').hide().show('slow').delay(1000).hide('slow');
	}
	
	$scope.showErro = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.$root.msgErro = msg;
	}	
	
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.$root.selectedCompany = $scope.listOne.t;
			 $scope.itens = getTree();
			 $scope.loadTreview($scope.itens);			 
	    });		 
	}	
	
	$scope.saveAreaInit = function() {
		angular.element('body').addClass('loading');
		
		var area = {
			uid: 0,
			name: $scope.areaNameInit,				
			unitDto: {uid : $scope.selectedUnit.uid}				
		};
		 
		$scope.inclusaoArea = new AreaService.save(area);
		$scope.inclusaoArea.$area({_csrf : angular.element('#_csrf').val()}, function(){
			$scope.showInfo("Nova Área Incluída!");
			$scope.clearFormUnit();						
			$scope.getOneCompany($scope.companyUid);			
			
        });
		 
	 }
	
	$scope.newUnit = function () {
		$scope.btnNewUnit = false;
		$scope.clearFormUnit();
	}	
		
	$scope.deleteUnit = function() 
	{		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new UnitService.deletar();	
		
		$scope.deletar.$unit({_csrf : angular.element('#_csrf').val(), id : $scope.selectedUnit.uid}, function(){
			
			$scope.showDanger("Unidade Excluída!");
			$scope.clearFormUnit();
			$scope.getOneCompany($scope.companyUid);				
	        
        }, function(data) {
        	$scope.showErro("Erro: " + data.statusText);
		});		 
	}
	
	$scope.clearFormUnit = function () {
		
		$scope.selectedUnit.uid = undefined
	    $scope.selectedUnit.name = '';	    
		$scope.selectedUnit.email  = '';
		$scope.selectedUnit.address  = '';
		$scope.selectedUnit.city  = '';
		$scope.selectedUnit.state  = '';
		$scope.selectedUnit.zip  = '';
		$scope.selectedUnit.phone  = '';
		$scope.selectedUnit.mobile  = '';
		$scope.selectedUnit.url  = '';
		$scope.selectedUnit.unitType  = undefined;
		$scope.selectedUnit.latitude = null;	
		$scope.selectedUnit.longitude = null;
		
		$scope.areaNameInit = undefined;
	 
		$('#idUnitName').select();
	}
	
	$scope.saveUnit = function() {
		angular.element('body').addClass('loading');				 
		
		validMapUnit();
		
		var unit = {
			uid: $scope.selectedUnit.uid == undefined ? 0 : $scope.selectedUnit.uid,
			name: $scope.selectedUnit.name,
			email: $scope.selectedUnit.email,
			address: $scope.selectedUnit.address,
			city: $scope.selectedUnit.city,
			state: $scope.selectedUnit.state,
			zip: $scope.selectedUnit.zip,
			phone: $scope.selectedUnit.phone,
			mobile: $scope.selectedUnit.mobile,
			url: $scope.selectedUnit.url,
			unitType: $scope.selectedUnit.unitType,
			latitude: $scope.selectedUnit.latitude,
			longitude: $scope.selectedUnit.longitude,			
			companyDto: {uid : $scope.selectedCompany.uid}				
		};		 
		 
		$scope.inclusaoUnit = new UnitService.save(unit);
		$scope.inclusaoUnit.$unit({_csrf : angular.element('#_csrf').val()}, function(){  
			
			if($scope.selectedUnit.uid == undefined) {
				$scope.clearFormUnit();
				$scope.getOneCompany($scope.companyUid);
			}
			
			$scope.showInfo("Unidade Gravada");
					
       });       
	}
	
	$scope.getUnitAddressValid = function() {
	
		if($scope.selectedUnit.address != null && $scope.selectedUnit.state != null && $scope.selectedUnit.city != null) {					    	 		
	 		return ($scope.selectedUnit.address == null ? "" : 
	 			$scope.selectedUnit.address) + ", " + ($scope.selectedUnit.state == null ? "" : $scope.selectedUnit.state) + ", " + ($scope.selectedUnit.city == null ? "" : $scope.selectedUnit.city);	 		
	 	}
	 	else if ( $scope.selectedUnit.zip != null) {	 		
	 		return ($scope.selectedUnit.zip == null ? "" : 
	 			$scope.selectedUnit.zip);
	 	}
	 	else {
	 		return "";
	 	}
	}
	
	showResultUnitCoordinates = function (result) {		
		$scope.selectedUnit.latitude = result.geometry.location.lat();
		$scope.selectedUnit.longitude = result.geometry.location.lng();	
		validMapUnit();	
		$scope.$apply();		
	}	 
	
	$scope.getCoordinatesUnit = function () {
	
		getCoordinates(showResultUnitCoordinates,  $scope.getUnitAddressValid() );	
		
	}
	
	getCoordinates = function (callBack, address) {
	 
		 var resp = {lat: 0, lng: 0};		 
		 address = address || 'Sao Paulo, Brasil';
		 
		 if (geocoder) {
			 geocoder.geocode({
		        'address': address
			 }, function (results, status) {
				 if (status == google.maps.GeocoderStatus.OK) {		
				 			 
					callBack(results[0]);			 
				 }
			 });
		 }
	}
	
	validMapUnit = function() {
		$scope.mapUnitOK = ( ($scope.selectedUnit.latitude != null && $scope.selectedUnit.latitude != 0)  && ($scope.selectedUnit.longitude != null && $scope.selectedUnit.longitude != 0) );
		
		if($scope.mapUnitOK)
		{
			$timeout(function () {           
				mapUnit($scope.selectedUnit.latitude, $scope.selectedUnit.longitude );
			}, 500);		
		}
	} 

	$scope.initializeUnit = function()
	 {	
	 	if (!geocoder) {
	 		$timeout(function () {
				$('.tabUnit a').on('click', function (event) {
					event.preventDefault();
					    
					if ($(event.target).attr('href') == "#tabUnit_2") {
						validMapUnit();
					}
				});
				 
				 geocoder = new google.maps.Geocoder();
				 map = new google.maps.Map(document.getElementById("mapUnit"),
				{				 
					 zoom: 12,
					 center: new google.maps.LatLng(-23.5505199,-46.63330940000003),
					 mapTypeId: google.maps.MapTypeId.ROADMAP
				});		
			 }, 400);			 
		 }
	 	
	 	$("#stepTabUnit_1").trigger("click");
	}
	
	mapUnit = function (lat, lng) {
	    
	    var myOptions = {
	        zoom: 12,
	        center: new google.maps.LatLng(lat, lng),
	        mapTypeId: google.maps.MapTypeId.ROADMAP
	    };	    
	    
	    map = new google.maps.Map(document.getElementById("mapUnit"), myOptions);        
        var latlng = new google.maps.LatLng(lat, lng);
        
        new google.maps.Marker({
            position: latlng,
            map: map,
            title : $scope.selectedUnit.name
       });       
	}
	
	$scope.selectedUnit = {};		
	angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex], $scope.selectedUnit);	
	
	$scope.btnNewUnit = true;	
	$scope.initializeUnit();

		
});