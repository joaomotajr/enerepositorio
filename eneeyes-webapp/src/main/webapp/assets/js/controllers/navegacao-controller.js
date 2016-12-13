app.factory('HistoricService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/historic/delete/:id', {id: '@id'},{
    		historic : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/historic/all',{},{
        	historic : {method : 'GET'}
        }),
        listOne : $resource('/security/api/historic/obtemPorId/:id', {id: '@id'},{
        	historic : {method : 'GET'}
        }),       
        save : $resource('/security/api/historic/save',{},{
        	historic : {method : 'POST'}
        }),
        listIntervalDays : $resource('/security/api/historic/findByCompanyDetectorAndSensorAndIntervalDays/:companyDetectorId/:sensorId/:dateIn/:dateOut/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', dateIn: '@dateIn', dateIn: '@dateOut' },{        
        	historic : {method : 'GET'}
        }),
        listInterval : $resource('/security/api/historic/findByCompanyDetectorAndSensorAndInterval/:companyDetectorId/:sensorId/:interval/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', interval: '@interval'},{        
        	historic : {method : 'GET'}
        }),
        listLastMonth : $resource('/security/api/historic/findByCompanyDetectorAndSensorLastMonth/:companyDetectorId/:sensorId/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId'},{        
        	historic : {method : 'GET'}
        }),
        listTop10 : $resource('/security/api/historic/findByTop10CompanyDetectorAndSensor/:companyDetectorId/:sensorId/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId'},{        
        	historic : {method : 'GET'}
        }),
     };
});


app.factory('DetectorService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/detector/delete/:id', {id: '@id'},{
    		detector : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/detector/all',{},{
        	detector : {method : 'GET'}
        }),
        listOne : $resource('/security/api/detector/obtemPorId/:id', {id: '@id'},{
        	detector : {method : 'GET'}
        }),       
        save : $resource('/security/api/detector/save',{},{
        	detector : {method : 'POST'}
        }),
     };
});

app.factory('TransmitterService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/transmitter/delete/:id', {id: '@id'},{
    		transmitter : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/transmitter/all',{},{
        	transmitter : {method : 'GET'}
        }),
        listOne : $resource('/security/api/transmitter/obtemPorId/:id', {id: '@id'},{
        	transmitter : {method : 'GET'}
        }),
        save : $resource('/security/api/transmitter/save',{},{
        	transmitter : {method : 'POST'}
        }),
     };
});

app.factory('SensorService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/sensor/delete/:id', {id: '@id'},{
    		sensor : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/sensor/all',{},{
        	sensor : {method : 'GET'}
        }),
        listOne : $resource('/security/api/sensor/obtemPorId/:id', {id: '@id'},{
        	sensor : {method : 'GET'}
        }),
        save : $resource('/security/api/sensor/save',{},{
        	sensor : {method : 'POST'}
        }),
     };
});

app.factory('GasService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/gas/delete/:id', {id: '@id'},{
    		gas : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/gas/all',{},{
        	gas : {method : 'GET'}
        }),
        listOne : $resource('/security/api/gas/obtemPorId/:id', {id: '@id'},{
        	gas : {method : 'GET'}
        }),
        save : $resource('/security/api/gas/save',{},{
        	gas : {method : 'POST'}
        }),
     };
});

app.factory('ControllerService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/controller/delete/:id', {id: '@id'},{
    		controller : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/controller/all',{},{
        	controller : {method : 'GET'}
        }),
        listOne : $resource('/security/api/controller/obtemPorId/:id', {id: '@id'},{
        	controller : {method : 'GET'}
        }),
        save : $resource('/security/api/controller/save',{},{
        	controller : {method : 'POST'}
        }),
     };
});

app.factory('CompanyDetectorService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/companyDetector/delete/:id', {id: '@id'},{
    		companyDetector : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/companyDetector/all',{},{
        	companyDetector : {method : 'GET'}
        }),
        listOne : $resource('/security/api/companyDetector/obtemPorId/:id', {id: '@id'},{
        	companyDetector : {method : 'GET'}
        }),   
	    listPorIdDeviceType : $resource('/security/api/companyDetector/obtemPorIdDeviceType/:id', {id: '@id'},{
	    	companyDetector : {method : 'GET'}
	    }),    
	    listPorIdArea : $resource('/security/api/companyDetector/obtemPorIdArea/:id', {id: '@id'},{
	    	companyDetector : {method : 'GET'}
	    }),
        save : $resource('/security/api/companyDetector/save',{},{
        	companyDetector : {method : 'POST'}
        }),
     };
});

app.factory('CompanyDeviceService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/companyDevice/delete/:id', {id: '@id'},{
    		companyDevice : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/companyDevice/all',{},{
        	companyDevice : {method : 'GET'}
        }),
        listOne : $resource('/security/api/companyDevice/obtemPorId/:id', {id: '@id'},{
        	companyDevice : {method : 'GET'}
        }),
        save : $resource('/security/api/companyDevice/save',{},{
        	companyDevice : {method : 'POST'}
        }),
     };
});

app.factory('AreaService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/area/delete/:id', {id: '@id'},{
            area : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/area/all',{},{
            area : {method : 'GET'}
        }),
        listOne : $resource('/security/api/area/obtemPorId/:id', {id: '@id'},{
            area : {method : 'GET'}
        }),
        save : $resource('/security/api/area/save',{},{
            area : {method : 'POST'}
        }),
     };
});

app.factory('UnitService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/unit/delete/:id', {id: '@id'},{
    		unit : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/unit/all',{},{
        	unit : {method : 'GET'}
        }),
        listOne : $resource('/security/api/unit/obtemPorId/:id', {id: '@id'},{
        	unit : {method : 'GET'}
        }),
        save : $resource('/security/api/unit/save',{},{
        	unit : {method : 'POST'}
        }),
        setParent : $resource('/security/api/unit/setparent/:id/:parentid/', {id: '@id', parentid: '@parentid'}, {
        	unit : {method : 'GET'}
        }),
        listAllFilter : $resource('/security/api/unit/allFilter',{},{
        	unit : {method : 'GET'}
        }),        
     };
});

app.factory('CompanyService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/company/delete/:id', {id: '@id'},{
    		company : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/company/all',{},{
        	company : {method : 'GET'}
        }),
        listOne : $resource('/security/api/company/obtemPorId/:id', {id: '@id'},{
        	company : {method : 'GET'}
        }),
        save : $resource('/security/api/company/save',{},{
        	company : {method : 'POST'}
        }),
     };
});

app.controller('navegacaoController', function ($scope, $timeout, $filter, AreaService, UnitService, CompanyService, CompanyDeviceService, ControllerService, GasService, SensorService, TransmitterService, DetectorService, CompanyDetectorService, HistoricService) {
	
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
        	console.log($scope.inclusao);
        	
        	$scope.companyValor = '';
        	$scope.selectedCompanyDetector = '';
        	$scope.selectedCompanySensor = '';
        });		 
	 }
	
	$scope.getHistoric = function() {
		$scope.listAll = new HistoricService.listAll();		 
		$scope.listAll.$historic({_csrf : angular.element('#_csrf').val()}, function(){         	
       	console.log($scope.listAll);      	
       	
       });		
	}
	
	$scope.getHistorics = function(interval) {
		
		$scope.listInterval = new HistoricService.listInterval();		
		$scope.listInterval.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid, sensorId: $scope.selectedCompanySensor.uid, interval: interval }, function(){
			
       	console.log($scope.listInterval);      	
       	
       });		
	}
	
	$scope.getLast10 = function() {
		$scope.listTop10 = new HistoricService.listTop10();		
		$scope.listTop10.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid, sensorId: $scope.selectedCompanySensor.uid }, function(){
			
       	console.log($scope.listTop10);      	
       	
       });		
	}
	
	$scope.getLastMonth = function() {
		$scope.listLastMonth = new HistoricService.listLastMonth();		
		$scope.listLastMonth.$historic({_csrf : angular.element('#_csrf').val(), companyDetectorId: $scope.selectedCompanyDetector.uid, sensorId: $scope.selectedCompanySensor.uid }, function(){
			
       	console.log($scope.listLastMonth);      	
       	
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
			       	
			console.log($scope.listLastMonth);      	
       });		
	}		
		
	$scope.getCompanyDetectors = function() {
		 
		 $scope.listAll = new CompanyDetectorService.listAll();		 
		 $scope.listAll.$companyDetector({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.CompanyDetectorss = $scope.listAll.list; 
			 console.log($scope.listAll);		         	         	
        });		 
	 }
	
    getDate = function (data, end) {

        if (data == undefined)
            return null;

        var dataAdm = new Date();        
        var newDate = data.split('/', 3);

        dataAdm.setDate(newDate[0]);
        dataAdm.setMonth(newDate[1] - 1);
        dataAdm.setYear(newDate[2]);
        
        if (end)
        	dataAdm.setHours(23, 59, 59, 999);
        else	
        	dataAdm.setHours(0, 0, 0, 0);
        
        return dataAdm;
    }


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
	
	function closeTab() {
		alert(url);
	}

	
	$scope.getCompanyDetectors();
	
});