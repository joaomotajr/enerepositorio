/**
 * 
 */

app.factory('PositionService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/position/delete/:id', {id: '@id'},{
    		position : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/position/all',{},{
        	position : {method : 'GET'}
        }),
        listOne : $resource('/security/api/position/obtemPorId/:id', {id: '@id'},{
        	position : {method : 'GET'}
        }),
        listOneBySensor : $resource('/security/api/position/obtemPorSensorId/:id', {id: '@id'},{
        	position : {method : 'GET'}
        }),
        listOneByCompanyDetector : $resource('/security/api/position/obtemPorCompanyDetectorId/:id', {id: '@id'},{
        	position : {method : 'GET'}
        }),
        listByAreaId : $resource('/security/api/position/obtemPorAreaId/:id', {id: '@id'},{
        	position : {method : 'GET'}
        }),
        save : $resource('/security/api/position/save',{},{
        	position : {method : 'POST'}
        }),
     };
});