/**
 * 
 */


app.factory('HistoricService', function($resource){    
    
    return {    	       
        listAll : $resource('/security/api/historic/all',{},{
        	historic : {method : 'GET'}
        }),
        listOne : $resource('/security/api/historic/obtemPorId/:id', {id: '@id'},{
        	historic : {method : 'GET'}
        }),       
        save : $resource('/security/api/historic/save',{},{
        	historic : {method : 'POST'}
        }),                            
        saveByPositionUid : $resource('/api/historic/SaveByPositionUid/:uid/:value/', {uid: '@uid', value: '@value' },{
        	historic : {method : 'POST'}
        }),                             
        saveByPositionUid2 : $resource('/api/historic/SaveByPositionUid2/:uid/:value/', {uid: '@uid', value: '@value' },{
        	historic : {method : 'GET'}
        }),
        listIntervalDays : $resource('/security/api/historic/findByCompanyDetectorAndSensorAndIntervalDays/:companyDetectorId/:sensorId/:dateIn/:dateOut/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', dateIn: '@dateIn', dateIn: '@dateOut' },{        
        	historic : {method : 'GET'}
        }),
        listIntervalDetector : $resource('/security/api/historic/findByCompanyDetectorAndInterval/:companyDetectorId/:interval/', {companyDetectorId: '@companyDetectorId', interval: '@interval'},{        
        	historic : {method : 'GET'}
        }),
        listInterval : $resource('/security/api/historic/findByCompanyDetectorAndSensorAndInterval/:companyDetectorId/:sensorId/:interval/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', interval: '@interval'},{        
        	historic : {method : 'GET'}
        }),
        listLastMonth : $resource('/security/api/historic/findByCompanyDetectorAndSensorLastMonth/:companyDetectorId/:sensorId/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId'},{        
        	historic : {method : 'GET'}
        }),
     };
});
