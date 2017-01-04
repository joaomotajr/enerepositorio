/**
 * 
 */

app.factory('SensorService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/sensor/delete/:id', {id: '@id'},{
    		sensor : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/sensor/all',{},{
        	sensor : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/sensor/obtemPorId/:id', {id: '@id'},{
        	sensor : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/sensor/save',{},{
        	sensor : {method : 'POST'}
        }),
     };
});