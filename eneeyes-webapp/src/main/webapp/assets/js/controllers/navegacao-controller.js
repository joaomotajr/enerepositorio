
app.controller('navegacaoController', function ($scope, $timeout, $filter, AreaService, UnitService, CompanyService, CompanyDeviceService, ControllerService, GasService, SensorService, TransmitterService, DetectorService, CompanyDetectorService, HistoricService, CompanyDetectorAlarmService) {
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.msg = msg;
        $('#result').hide().show('slow').delay(500).hide('slow');
	}
	
	$scope.clearHistoric = function() {
			
		$scope.companyValor = '';
        $scope.selectedCompanyDetector = '';
        $scope.selectedCompanySensor = '';
			
	}
	
	$scope.saveHistoric = function() {
		
		$scope.historic = {
				uid: 0,	
				value: $scope.companyValor,
				lastUpdate: null,
				companyDetectorDto: {uid: $scope.selectedCompanyDetector.uid},
				sensorDto: {uid: $scope.selectedCompanySensor.uid}
			 }	
		 
		 $scope.inclusao = new HistoricService.save($scope.historic);		 
		 $scope.inclusao.$historic({_csrf : angular.element('#_csrf').val()}, function(){         	
        	        	
        	$scope.showInfo('Salvo');        	
        	$scope.companyValor = '';

        });		 
	 }
	
	$scope.getHistoric = function() {
		$scope.listHistoric = new HistoricService.listAll();		 
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val()}, function(){         	
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getHistorics2 = function(interval) {
		
		$scope.listHistoric = new HistoricService.listInterval2();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid, sensorId: $scope.selectedCompanySensor.uid, interval: interval }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getHistorics = function(interval) {
		
		$scope.listHistoric = new HistoricService.listInterval();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid, interval: interval }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getLastMonth = function() {
		$scope.listHistoric = new HistoricService.listLastMonth();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
		
	$scope.getLastMonth2 = function() {
		$scope.listHistoric = new HistoricService.listLastMonth2();		
		$scope.listHistoric.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid, sensorId: $scope.selectedCompanySensor.uid }, function(){
			
       	console.log($scope.listHistoric);      	
       	
       });		
	}
	
	$scope.getHistoricInterval = function() {
		
		var dataInicio = new Date(getDate($scope.dateIn));
		var dataFim = new Date(getDate($scope.dateOut, true));
		
		$scope.listIntervalDays = new HistoricService.listIntervalDays();		
		$scope.listIntervalDays.$historic({_csrf : angular.element('#_csrf').val(),			
			companyDetectorId: $scope.selectedCompanyDetector.uid, 
			sensorId: $scope.selectedCompanySensor.uid,
			dateIn: dataInicio,
			dateOut: dataFim
		}, function(){
			       	
			console.log($scope.listIntervalDays);      	
       });		
	}		
		
	$scope.getCompanyDetectors = function() {
		 
		 $scope.listAll = new CompanyDetectorService.listAll();		 
		 $scope.listAll.$companyDetector({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.CompanyDetectors = $scope.listAll.list; 
			 console.log($scope.listAll);		         	         	
        });		 
	 }
	
	$scope.listCompanyDetectors = function() {
		$scope.getCompanyDetectors();
		$scope.listCompanyDetectors = angular.copy($scope.CompanyDetectors);     		 
	}	
	
	$scope.changeCompanyDetector = function() {
		
		$scope.selectedSensorAlarm = undefined;
		$scope.selectedCompanySensor = undefined;
		
		if($scope.selectedCompanyDetector == null) return;
		
		$scope.resultCompanyDetectorAlarm = new CompanyDetectorAlarmService.listPorCompanyDetectorAlarm();		 
		$scope.resultCompanyDetectorAlarm.$companyDetectorAlarm({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid}, function(){			
			$scope.selectedCompanyDetectorAlarms = $scope.resultCompanyDetectorAlarm.list;
        });		 
	}
	
	$scope.changeSensor = function() {
		
		if($scope.selectedCompanySensor == null) return;
		
		var detectorAlarmIndex = $scope.selectedCompanyDetectorAlarms.findIndex(function (i) { return i.sensorId === $scope.selectedCompanySensor.uid});				
		if (detectorAlarmIndex >= 0) {			
			$scope.selectedSensorAlarm = $scope.selectedCompanyDetectorAlarms[detectorAlarmIndex].alarmDto ;
		}
		
	}

/*-----------------------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------------------------*/
		/*-----------------------------------------------------------------------------------------------------------------*/
			/*-----------------------------------------------------------------------------------------------------------------*/
	
	$scope.det1 = {
		uid: 1
	 } ;
	$scope.det2 = {
			uid: 2
		}; 
	
	$scope.det3 = {
			uid: 3
		 } ;
		
	$scope.detector = {
		uid: 0,
		name: 'Detector',
		manufacturer: {uid: 1},
		model: 'modelo',
		transmitter : {uid: 1}
		, sensorsDto: []
	 }
		
		
		//$scope.detector.sensorsDto.push($scope.det1);
	 	$scope.detector.sensorsDto.push($scope.det2);
	 	//$scope.detector.sensorsDto.push($scope.det3);
		 
		$scope.saveDetector = function() {
			 
			 $scope.inclusao = new DetectorService.save($scope.detector);		 
			 $scope.inclusao.$detector({_csrf : angular.element('#_csrf').val()}, function(){         	
	         	console.log($scope.inclusao);         	
	         });
			 
		 }
		 
		 $scope.getDetector = function() {
			 
			 $scope.listAll = new DetectorService.listAll();		 
			 $scope.listAll.$detector({_csrf : angular.element('#_csrf').val()}, function(){			
				 console.log($scope.listAll);		         	         	
	         });		 
		 }
		 
		 $scope.getOneDetector = function() {
			 
			 $scope.listOne = new DetectorService.listOne();		 
			 $scope.listOne.$detector({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
				 console.log($scope.listOne);
	         	         	
	         });			 
		 }
		 
		 $scope.getOneCompanyDetectorDevice = function() {
			 
			 $scope.listOne = new DetectorService.listOne();		 
			 $scope.listOne.$detector({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
				 console.log($scope.listOne);	         	         	
	         });			 
		 }
		 		 
		 $scope.deletarDetector = function() {			 
			 $scope.deletar = new DetectorService.deletar();		 
			 $scope.deletar.$detector({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
				 console.log($scope.deletar);	         	         	
	         });			 
		 }	
	/*-----------------------------------------------------------------------------------------------------------------*/
	
	$scope.transmitter = {
			uid: 0,		
			name: 'Transmitter',
			manufacturer: {uid: 1},
			model: "werwerwere",
			commProtocol : 'MODBUS'
		 } 
		 
		 $scope.saveTransmitter = function() {
			 
			 $scope.inclusao = new TransmitterService.save($scope.transmitter);		 
			 $scope.inclusao.$transmitter({_csrf : angular.element('#_csrf').val()}, function(){         	
	         	console.log($scope.inclusao);         	
	         });
			 
		 }
		 
		 $scope.getTransmitter = function() {
			 
			 $scope.listAll = new TransmitterService.listAll();		 
			 $scope.listAll.$transmitter({_csrf : angular.element('#_csrf').val()}, function(){			
				 console.log($scope.listAll);		         	         	
	         });		 
		 }
		 
		 $scope.getOneTransmitter = function() {
			 
			 $scope.listOne = new TransmitterService.listOne();		 
			 $scope.listOne.$transmitter({_csrf : angular.element('#_csrf').val(), id : 3}, function(){			
				 console.log($scope.listOne);
	         	         	
	         });
			 
		 }
		 
		 $scope.deletarTransmitter = function() {
			 
			 $scope.deletar = new TransmitterService.deletar();		 
			 $scope.deletar.$transmitter({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
				 console.log($scope.deletar);
	         	         	
	         });
			 
		 }
		 
	
	
/*-----------------------------------------------------------------------------------------------------------------*/
	
		$scope.gas1 = {
			uid: 1			
		 } ;
		$scope.gas2 = {
				uid: 2			
			 } ;
		
		$scope.gas3 = {
				uid: 3			
			 } ;
		
		$scope.gas4 = {
				uid: 4			
			 } ;
	
	$scope.sensor = {
		uid: 0,			
		detectionType: 0,
		name: 'Sensor',
		manufacturer: {uid: 1},
		model: 'modelo',
		gasesDto: []
	 }
		
	 $scope.sensor.gasesDto.push($scope.gas1);
	 $scope.sensor.gasesDto.push($scope.gas2);
	 $scope.sensor.gasesDto.push($scope.gas3);
	 $scope.sensor.gasesDto.push($scope.gas4);
	 
	 $scope.saveSensor = function() {
		 
		 $scope.inclusao = new SensorService.save($scope.sensor);		 
		 $scope.inclusao.$sensor({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusao);         	
         });
		 
	 }
	 
	 $scope.getSensor = function() {
		 
		 $scope.listAll = new SensorService.listAll();		 
		 $scope.listAll.$sensor({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneSensor = function() {
		 
		 $scope.listOne = new SensorService.listOne();		 
		 $scope.listOne.$sensor({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.listOne);
         	         	
         });
		 
	 }
	 
	 
	 $scope.deletarSensor = function() {
		 
		 $scope.deletar = new SensorService.deletar();		 
		 $scope.deletar.$sensor({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.deletar);
         	         	
         });
		 
	 }
	 /*-----------------------------------------------------------------------------------------------------------------*/
	
	$scope.gas = {
		uid: 0,		
		name: 'Controller',
		formula: "H2O",
		cas: 'cas',
		unitMeterGases: 0
	 } 
	 
	 $scope.saveGas = function() {
		 
		 $scope.inclusao = new GasService.save($scope.gas);		 
		 $scope.inclusao.$gas({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusao);         	
         });
		 
	 }
	 
	 $scope.getGas = function() {
		 
		 $scope.listAll = new GasService.listAll();		 
		 $scope.listAll.$gas({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneGas = function() {
		 
		 $scope.listOne = new GasService.listOne();		 
		 $scope.listOne.$gas({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.listOne);
         	         	
         });
		 
	 }
	 
	 $scope.deletarGas = function() {
		 
		 $scope.deletar = new GasService.deletar();		 
		 $scope.deletar.$gas({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.deletar);
         	         	
         });
		 
	 }
	 /*-----------------------------------------------------------------------------------------------------------------*/
	
//	$scope.controller = {
//		uid: 0,		
//		name: 'Controller',
//		manufacturer: 'Manufacturer1',
//		model: "werwerwere"
//	 } 
	
	$scope.controller = {
			uid: 0,		
			name: 'Controller',
			manufacturer: {uid: 1},
			model: "werwerwere"
		 } 
	 
	 $scope.saveController = function() {
		 
		 $scope.inclusao = new ControllerService.save($scope.controller);		 
		 $scope.inclusao.$controller({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusao);         	
         });
		 
	 }
	 
	 $scope.getController = function() {
		 
		 $scope.listAll = new ControllerService.listAll();		 
		 $scope.listAll.$controller({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneController = function() {
		 
		 $scope.listOne = new ControllerService.listOne();		 
		 $scope.listOne.$controller({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.listOne);
         	         	
         });
		 
	 }
	 
	 $scope.deletarController = function() {
		 
		 $scope.deletar = new ControllerService.deletar();		 
		 $scope.deletar.$controller({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.deletar);
         	         	
         });
		 
	 }

/*-----------------------------------------------------------------------------------------------------------------*/		 
	 $scope.companyDetector = {
		uid: 0,
		name: 'Navegação',
		companyDevice: {uid : 2},
		detector: {uid : 1},
		local: "ererererererer",
		description: "dfdfdfdsfsdd"
		
	 } 
	
	 $scope.saveCompanyDetector = function() {
				 
		 $scope.inclusao = new CompanyDetectorService.save($scope.companyDetector);		 
		 $scope.inclusao.$companyDetector({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusao);         	
         });
		 
	 }
	 
	 $scope.getCompanyDetector = function() {
		 
		 $scope.listAll = new CompanyDetectorService.listAll();		 
		 $scope.listAll.$companyDetector({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.CompanyDetectors = $scope.listAll.list; 
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneCompanyDetector = function() {
		 
		 $scope.listOne = new CompanyDetectorService.listOne();		 
		 $scope.listOne.$companyDetector({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.listOne);         	         	
         });		 
	 }
	 
	 $scope.getOneCompanyDetectorDeviceByArea = function() {
		 
		 $scope.listPorIdArea = new CompanyDetectorService.listPorIdArea();		 
		 $scope.listPorIdArea.$companyDetector({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.listPorIdArea);         	         	
         });		 
	 }
	 
	 $scope.getOneCompanyDetectorDevice = function() {
		 
		 $scope.listPorIdDeviceType = new CompanyDetectorService.listPorIdDeviceType();		 
		 $scope.listPorIdDeviceType.$companyDetector({_csrf : angular.element('#_csrf').val(), id : 3}, function(){			
			 console.log($scope.listPorIdDeviceType);         	         	
         });
		 
	 }
			
			
	 /*-----------------------------------------------------------------------------------------------------------------*/
	 
	$scope.companyDevice = {
		uid: 0,
		deviceType: 1,
		areaDto: {uid : 16}
		
	 } 
	
	
	 $scope.saveCompanyDevice = function() {
				 
		 $scope.inclusao = new CompanyDeviceService.save($scope.companyDevice);		 
		 $scope.inclusao.$companyDevice({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusao);         	
         });
		 
	 }
	 
	 $scope.getCompanyDevice = function() {
		 
		 $scope.listAll = new CompanyDeviceService.listAll();		 
		 $scope.listAll.$companyDevice({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneCompanyDevice = function() {
		 
		 $scope.listOne = new CompanyDeviceService.listOne();		 
		 $scope.listOne.$companyDevice({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.listOne);
         	         	
         });
		 
	 }
	 
	 $scope.deletarCompanyDevice = function() {
		 
		 $scope.deletar = new CompanyDeviceService.deletar();		 
		 $scope.deletar.$companyDevice({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.deletar);
         	         	
         });
		 
	 }
	 /*-----------------------------------------------------------------------------------------------------------------*/
	 
	 $scope.area = {
		uid: 0,
		name: "Area Tres",
		description: "Area Tres",
		local: "Area Tres",	
		latitude: 12.2345545,		
		longitude: 9.232323,
		classified: true,
		date: null,
		unitDto: {uid: 5}
	 } 
	 
	 $scope.saveArea = function() {
		 
		 $scope.inclusao = new AreaService.save($scope.area);		 
		 $scope.inclusao.$area({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusao);         	
         });
		 
	 }
	 
	 $scope.getArea = function() {
		 
		 $scope.listAll = new AreaService.listAll();		 
		 $scope.listAll.$area({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneArea = function() {
		 
		 $scope.listOne = new AreaService.listOne();		 
		 $scope.listOne.$area({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
			 console.log($scope.listOne);
         	         	
         });
		 
	 }
	 
	 $scope.deletarArea = function() {		 
		 $scope.deletar = new AreaService.deletar();		 
		 $scope.deletar.$area({_csrf : angular.element('#_csrf').val(), id : 2}, function(){			
			 console.log($scope.deletar);         	         	
         });		 
	 }
	 /*-----------------------------------------------------------------------------------------------------------------*/
	 
	 $scope.unit = {
		uid: 0,
		name: "Unit Quatro",
		email: null,
		url: null,
		phone: null,
		mobile: null,
		address: null,
		city: null,
		state: null,
		zip: null,
		unitType: 1,
		date: null,
		latitude: 12.2345545,		
		longitude: 9.232323
		, companyDto: {uid : 1}				
	 }	 
	 

	 $scope.saveUnit = function() {
		 
		 $scope.inclusaoUnit = new UnitService.save($scope.unit);
		 $scope.inclusaoUnit.$unit({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusaoUnit);         	
         });
		 
	 }
	 
	 $scope.getUnit = function() {
		 
		 $scope.listAll = new UnitService.listAll();		 
		 $scope.listAll.$unit({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneUnit = function() {
		 
		 $scope.listOne = new UnitService.listOne();	 
		 
		 $scope.listOne.$unit({_csrf : angular.element('#_csrf').val(), id : 1}, function(){
			 console.log($scope.listOne);
         	         	
         });		 
	 }	  
	 
	 $scope.setUnit = function() {
		 
		 $scope.setParent = new UnitService.setParent($scope.unit);
		 $scope.setParent.$unit({_csrf : angular.element('#_csrf').val(), id : 2, parentid: 1} , function(){         	
         	console.log($scope.setParent);         	
         });
		 
	 }
	 
	 $scope.deletarUnit = function() {		 
		 $scope.deletar = new UnitService.deletar();		 
		 $scope.deletar.$unit({_csrf : angular.element('#_csrf').val(), id : 3}, function(){			
			 console.log($scope.deletar);         	         	
         });		 
	 }
	 
	 /*-----------------------------------------------------------------------------------------------------------------*/
	 
	 $scope.company = {
		uid: 0,
		name: "Teste",
		description : "teste"
	 }	 
	 

	 $scope.saveCompany = function() {
		 
		 $scope.inclusaoUnit = new CompanyService.save($scope.company);
		 $scope.inclusaoUnit.$company({_csrf : angular.element('#_csrf').val()}, function(){         	
         	console.log($scope.inclusaoUnit);         	
         });
		 
	 }
	 
	 $scope.getCompany = function() {
		 
		 $scope.listAll = new CompanyService.listAll();		 
		 $scope.listAll.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAll);		         	         	
         });		 
	 }
	 
	 $scope.getOneCompany = function() {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : 1}, function(){			
				 console.log($scope.listOne);
	         	         	
	         });		 
		 }
	 
	 $scope.deletarCompany = function() {		 
		 $scope.deletar = new CompanyService.deletar();		 
		 $scope.deletar.$company({_csrf : angular.element('#_csrf').val(), id : 2}, function(){			
			 console.log($scope.deletar);         	         	
         });		 
	 }
		
	$scope.getCompanyDetectors();
	
});