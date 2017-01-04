/**
 * 
 */

app.factory('PositionService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/position/delete/:id', {id: '@id'},{
    		position : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/position/all',{},{
        	position : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/position/obtemPorId/:id', {id: '@id'},{
        	position : {method : 'GET'}
        }),
        listOneBySensor : $resource('/eneeyes/security/api/position/obtemPorSensorId/:id', {id: '@id'},{
        	position : {method : 'GET'}
        }),
        listOneByCompanyDetector : $resource('/eneeyes/security/api/position/obtemPorCompanyDetectorId/:id', {id: '@id'},{
        	position : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/position/save',{},{
        	position : {method : 'POST'}
        }),
     };
});