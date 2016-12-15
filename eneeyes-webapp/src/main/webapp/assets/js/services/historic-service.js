/**
 * 
 */


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
        listInterval : $resource('/security/api/historic/findByCompanyDetectorAndInterval/:companyDetectorId/:interval/', {companyDetectorId: '@companyDetectorId', interval: '@interval'},{        
        	historic : {method : 'GET'}
        }),
        listInterval2 : $resource('/security/api/historic/findByCompanyDetectorAndSensorAndInterval/:companyDetectorId/:sensorId/:interval/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', interval: '@interval'},{        
        	historic : {method : 'GET'}
        }),
        listLastMonth : $resource('/security/api/historic/findByCompanyDetectorAndSensorLastMonth/:companyDetectorId/:sensorId/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId'},{        
        	historic : {method : 'GET'}
        }),        
     };
});
