/**
 * 
 */


app.factory('HistoricService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/historic/delete/:id', {id: '@id'},{
    		historic : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/historic/all',{},{
        	historic : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/historic/obtemPorId/:id', {id: '@id'},{
        	historic : {method : 'GET'}
        }),       
        save : $resource('/eneeyes/security/api/historic/save',{},{
        	historic : {method : 'POST'}
        }),
        listIntervalDays : $resource('/eneeyes/security/api/historic/findByCompanyDetectorAndSensorAndIntervalDays/:companyDetectorId/:sensorId/:dateIn/:dateOut/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', dateIn: '@dateIn', dateIn: '@dateOut' },{        
        	historic : {method : 'GET'}
        }),
        listInterval : $resource('/eneeyes/security/api/historic/findByCompanyDetectorAndInterval/:companyDetectorId/:interval/', {companyDetectorId: '@companyDetectorId', interval: '@interval'},{        
        	historic : {method : 'GET'}
        }),
        listInterval2 : $resource('/eneeyes/security/api/historic/findByCompanyDetectorAndSensorAndInterval/:companyDetectorId/:sensorId/:interval/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', interval: '@interval'},{        
        	historic : {method : 'GET'}
        }),
        listLastMonth : $resource('/eneeyes/security/api/historic/findByCompanyDetectorLastMonth/:companyDetectorId/', {companyDetectorId: '@companyDetectorId'},{        
        	historic : {method : 'GET'}
        }),        
        listLastMonth2 : $resource('/eneeyes/security/api/historic/findByCompanyDetectorAndSensorLastMonth/:companyDetectorId/:sensorId/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId'},{        
        	historic : {method : 'GET'}
        }),
     };
});
