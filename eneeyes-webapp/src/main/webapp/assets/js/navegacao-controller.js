app.factory('DetectorService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/detector/delete',{},{
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
    	deletar : $resource('/security/api/transmitter/delete',{},{
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
    	deletar : $resource('/security/api/sensor/delete',{},{
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
    	deletar : $resource('/security/api/gas/delete',{},{
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
    	deletar : $resource('/security/api/controller/delete',{},{
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


app.factory('CompanyDeviceService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/companyDevice/delete',{},{
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
    	deletar : $resource('/security/api/area/delete',{},{
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
    	deletar : $resource('/security/api/unit/delete',{},{
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
    	deletar : $resource('/security/api/company/delete',{},{
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


//app.factory('SetParentService', function($resource){
//    return {	        
//    setParent :  $resource('/security/api/unit/setparent/:id/:parentid/',  {id: '@id', parentid: '@parentid'}, {},
//    		{query: {method: 'GET', isArray:true}})	    
//    };
//});

app.controller('navegacaoController', function ($scope, $timeout, $filter, AreaService, UnitService, CompanyService, CompanyDeviceService, ControllerService, GasService, SensorService, TransmitterService, DetectorService) {
		
	$('#treeview-searchable').treeview({
	     data: getTree(),
	 });
	
	search = function (e) {
	     var pattern = $('#input-search').val();
	     var options = {
	         ignoreCase: true,
	         exactMatch: false,
	         revealResults: true
	     };

	     var results = $searchableTree.treeview('search', [pattern, options]);
	    
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
		uid: 1,		
		name: 'Detector',
		manufacturer: 'manufacturer',
		model: 'modelo',
		transmitter : {uid: 4}
		, sensorsDto: []
	 }
		
		
		$scope.detector.sensorsDto.push($scope.det1);
	 	$scope.detector.sensorsDto.push($scope.det2);
	 	$scope.detector.sensorsDto.push($scope.det3);
		 
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
	
	/*-----------------------------------------------------------------------------------------------------------------*/
	
	$scope.transmitter = {
			uid: 0,		
			name: 'Transmitter',
			manufacturer: 'Manufacturer1',
			model: "werwerwere",
			commProtocol : 5
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
	
	$scope.sensor = {
		uid: 2,		
		detectionType: 0,
		name: 'Sensor',
		manufacturer: 'manufacturer',
		model: 'modelo',
		gasesDto: []
	 }
		
	 $scope.sensor.gasesDto.push($scope.gas1);
	 $scope.sensor.gasesDto.push($scope.gas2);
	 $scope.sensor.gasesDto.push($scope.gas3);
	 
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
	 /*-----------------------------------------------------------------------------------------------------------------*/
	
	$scope.controller = {
		uid: 0,		
		name: 'Controller',
		manufacturer: 'Manufacturer1',
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
	 /*-----------------------------------------------------------------------------------------------------------------*/
	 
	$scope.companyDevice = {
		uid: 0,
		deviceType: 1,
		areaDto: {uid : 1}
		
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
	 /*-----------------------------------------------------------------------------------------------------------------*/
	 
	 $scope.area = {
		uid: 0,
		name: "Teste",
		description: "Teste",
		local: "Teste",	
		latitude: 12.2345545,		
		longitude: 9.232323,
		classified: true,
		date: null,
		unitDto: {uid: 1}
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
	 /*-----------------------------------------------------------------------------------------------------------------*/
	 
	 $scope.unit = {
		uid: 0,
		name: "Teste",
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
	 
	 $scope.getUnitFilter = function() {
		 
		 $scope.listAllFilter = new UnitService.listAllFilter();		 
		 $scope.listAllFilter.$unit({_csrf : angular.element('#_csrf').val()}, function(){			
			 console.log($scope.listAllFilter);		         	         	
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
	 
	 $scope.company = {
		uid: 0,
		name: "Teste"						
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
 
//	 $scope.setUnit = function () {
//		 
//		$scope.val = SetParentService.setParent.query({_csrf : angular.element('#_csrf').val()}, {id : 2, parentid: 1}, function(result) {
//			if(result.resultType == "SUCCESS"){
//				window.location.href='/';
//			}
//			console.log(result);
//	    }, function(data) {
//			 if (data.status >= 400 && data.status <= 505 ) {
//				 
//			 }
//		 });
//	}	 
	
});