
app.controller('companyGenericController', function ($scope, $interval, $rootScope, $timeout, $filter, AlarmService, 
	CompanyService, GenericService, CompanyGenericService, PositionService, ViewService) {

	var loadGoogleCharts = false;

	$scope.saveCompanyGeneric = function() {
		angular.element('body').addClass('loading');
						
		var companyGeneric = {
			uid: $scope.selectedCompanyGeneric.uid == undefined ? 0 : $scope.selectedCompanyGeneric.uid,
			name: $scope.selectedCompanyGeneric.name.toUpperCase(),
			companyDeviceDto: {uid : $scope.selectedCompanyDevice.uid},
			genericDto: {uid: $scope.selectedCompanyGeneric.genericDto.uid},			
			local: $scope.selectedCompanyGeneric.local,
			serialNumber: $scope.selectedCompanyGeneric.serialNumber		
		 };
	 
		$scope.inclusaoCompanyGeneric = new CompanyGenericService.save(companyGeneric);
		$scope.inclusaoCompanyGeneric.$companyGeneric({_csrf : angular.element('#_csrf').val()}, function(){		
			
			if($scope.selectedCompanyGeneric.uid == undefined) {
				$scope.selectedCompanyGeneric = $scope.inclusaoCompanyGeneric.t;				

				$scope.getOneCompany($scope.companyUid);
			}		
			
			angular.element('body').removeClass('loading');			
			$rootScope.showGeneralMessage($scope.inclusaoCompanyGeneric.message, 'SUCCESS');	

		});			 
	};
	
	$scope.clearCompanyGeneric = function () {
		$scope.selectedCompanyGeneric.detectorDto.image = "/assets/img/cover.jpg";
		$timeout(function () {						
		    $scope.selectedCompanyGeneric.uid = undefined;
		    $scope.selectedCompanyGeneric.name = '';
		    $scope.selectedCompanyDevice.uid = undefined;	    	    
		    $scope.selectedCompanyGeneric.genericDto = '';	    
		    $scope.selectedCompanyGeneric.local = '';
		    $scope.selectedCompanyGeneric.description = '';	    
		}, 100);
	};
	
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.$root.selectedCompany = $scope.listOne.t;
			 $scope.itens = getTree();
			 $scope.loadTreview($scope.itens);			 
	    });		 
	}
	
	$scope.initializeGeneric =  function()  {				
		   
		$("#stepTabGeneric_1").trigger("click");
	};	 
	 
	$scope.getAlarms = function() {
		
		$scope.resultAlarms = new AlarmService.listByCompanyId();
		 $scope.resultAlarms.$alarm({_csrf : angular.element('#_csrf').val(), companyId : $scope.$root.selectedCompany.uid }, function(){			 
			 $scope.alarms = $scope.resultAlarms.list;			 
        });		 
	};

	$scope.getGenerics = function() {		 
		$scope.resultgetGenerics = new GenericService.listAll();		 
		$scope.resultgetGenerics.$generic({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.generics = $scope.resultgetGenerics.list; 	
			
			if ($scope.selectedCompanyDevice != null)
				$scope.getOneCompanyGeneric();			
	   });		 
	};

	$scope.getOneCompanyGeneric = function() {
		
		$scope.search = { unitMeterGases: null, gas : null };
				
		$scope.resultCompanyGeneric = new CompanyGenericService.listPorCompanyDevice();		 
		$scope.resultCompanyGeneric.$companyGeneric({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDevice.uid }, function(){			
			
			$scope.selectedCompanyGeneric = $scope.resultCompanyGeneric.t;								
			
			if($scope.selectedCompanyGeneric != null) {								
				$scope.getPositionsAndIds($scope.selectedCompanyDevice.uid);
				$scope.getCompanyDetectorAlarm($scope.selectedCompanyGeneric.companyDeviceDto.uid);
			}
		});		 
	};

	$scope.getPositionsAndIds = function(companyDeviceId) {
		
		$scope.listOnePosition = new PositionService.listOneByCompanyDevice();		 
		$scope.listOnePosition.$position({_csrf : angular.element('#_csrf').val(), id : companyDeviceId}, function() {
			
			$scope.selectedCompanyGenericPosition = $scope.listOnePosition.t;		
		});
	};

	$scope.selecionarGeneric = function(item) {
		
		if($scope.selectedCompanyGeneric == undefined) {
			$scope.selectedCompanyGeneric = [];
		}

		$scope.selectedCompanyGeneric.genericDto = item;
	};

	$scope.getCompanyDetectorAlarm = function(companyDeviceId) {
		
		$scope.resultDetectors = new ViewService.listCompanyDeviceAlarms();		 
		$scope.resultDetectors.$view({_csrf : angular.element('#_csrf').val(), companyDeviceId : companyDeviceId}, function(){						
			$scope.selectedCompanyGenericAlarms = $scope.resultDetectors.list;

			$scope.selectedCompanyGenericAlarm = $scope.resultDetectors.list[0];				
		});
	};
		
	
	/* ------------------------------------- Inicio Processamento --------------------------------------------*/
	
	if($scope.$root.selecteds.unitIndex != undefined) {
		
		$scope.selectedUnit = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex]);
		$scope.selectedArea = angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex]);	
		$scope.selectedCompanyDevice = angular.copy($scope.selectedArea.companyDevicesDto[$scope.$root.selecteds.CompanyDeviceIndex]);
				
		$scope.getAlarms();	
		$scope.getGenerics();	
	}
	
	$scope.initializeGeneric();		
});