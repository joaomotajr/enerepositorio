
app.controller('companyGenericController', function ($scope, $interval, $rootScope, $timeout, $filter, AlarmService, 
	CompanyService, GenericService, CompanyGenericService, PositionService, ViewService, CompanyDeviceService) {

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
		$timeout(function () {						
		    $scope.selectedCompanyGeneric.uid = undefined;
		    $scope.selectedCompanyGeneric.name = '';
		    $scope.selectedCompanyDevice.uid = undefined;	    	    
		    $scope.selectedCompanyGeneric.genericDto = '';	    
		    $scope.selectedCompanyGeneric.local = '';
		    $scope.selectedCompanyGeneric.description = '';	    
		}, 100);
	};

	
	$scope.deleteCompanyDevice = function() {		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new CompanyDeviceService.deletar();		
		$scope.deletar.$companyDevice({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDevice.uid}, function(){
			$rootScope.showGeneralMessage($scope.deletar.message, 'DANGER');
			$scope.generics = [];
			$scope.selectedCompanyGeneric = undefined;
			$scope.getOneCompany($scope.companyUid);			
			angular.element('body').removeClass('loading');	                 	         	
		});
	};

	$scope.deleteCompanyGeneric = function() {		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new CompanyGenericService.deletar();		
		$scope.deletar.$companyGeneric({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyGeneric.uid}, function(){
			$rootScope.showGeneralMessage($scope.deletar.message, 'DANGER');
			$scope.clearCompanyGeneric();
			$scope.getOneCompany($scope.companyUid);			
			angular.element('body').removeClass('loading');	                 	         	
		});		 
	};

	$scope.getOneCompany = function(companyId) {		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function() {			 
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

	$scope.toggleAlarm = function(alarm) {
		
		if(alarm != null && ($scope.selectedCompanyDeviceAlarm.rangeMax < alarm.alarm1 || $scope.selectedCompanyDeviceAlarm.rangeMax < alarm.alarm2 || $scope.selectedCompanyDeviceAlarm.rangeMax < alarm.alarm3 )) {
			$scope.msgErroAlarm = "Alarm Selecionado excede Range Max do Sensor, Verifique!";
			return;
		}

		if(alarm != null) {
			$scope.updateAlarm = new CompanyDeviceService.updateAlarm();
			$scope.updateAlarm.$companyDevice({_csrf : angular.element('#_csrf').val(), alarmId: alarm.uid, id : $scope.selectedCompanyDevice.uid }, function(){						
				if (!$scope.updateAlarm.isError) {
					$scope.selectedCompanyDeviceAlarm.alarmId = alarm.uid;
					$scope.getCompanyDeviceAlarm($scope.selectedCompanyGeneric.companyDeviceDto.uid);	
				}			
			});	
		}
		else {

			$scope.removeAlarm = new CompanyDeviceService.removeAlarm();
			$scope.removeAlarm.$companyDevice({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDevice.uid }, function(){						
				if (!$scope.removeAlarm.isError) {
					$scope.selectedCompanyDeviceAlarm.alarmId = null;			 
					$scope.getCompanyDeviceAlarm($scope.selectedCompanyGeneric.companyDeviceDto.uid);	
				}			
			});
		}
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
		$scope.resultCompanyGeneric.$companyGeneric({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDevice.uid }, function() {
			
			$scope.selectedCompanyGeneric = $scope.resultCompanyGeneric.t;			
			if($scope.selectedCompanyGeneric != null) {								
				$scope.getPositionsAndIds($scope.selectedCompanyDevice.uid);
				$scope.getCompanyDeviceAlarm($scope.selectedCompanyGeneric.companyDeviceDto.uid);
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

	$scope.getCompanyDeviceAlarm = function(companyDeviceId) {		
		$scope.resultDevices = new ViewService.listCompanyDeviceAlarms();		 
		$scope.resultDevices.$view({_csrf : angular.element('#_csrf').val(), companyDeviceId : companyDeviceId}, function(){						
			$scope.selectedCompanyDeviceAlarms = [];
			$scope.selectedCompanyDeviceAlarms.push($scope.resultDevices.t);
			$scope.selectedCompanyDeviceAlarm = $scope.resultDevices.t;		
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