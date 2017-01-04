/**
 * 
 */

app.factory('PositionAlarmService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/positionAlarm/delete/:id', {id: '@id'},{
    		positionAlarm : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/positionAlarm/all',{},{
        	positionAlarm : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/positionAlarm/obtemPorId/:id', {id: '@id'},{
        	positionAlarm : {method : 'GET'}
        }),
        listOneBySensor : $resource('/eneeyes/security/api/positionAlarm/obtemPorSensorId/:id', {id: '@id'},{
        	positionAlarm : {method : 'GET'}
        }),
        listOneByCompanyDetector : $resource('/eneeyes/security/api/positionAlarm/obtemPorCompanyDetectorId/:id', {id: '@id'},{
        	positionAlarm : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/positionAlarm/save',{},{
        	positionAlarm : {method : 'POST'}
        }),
     };
});