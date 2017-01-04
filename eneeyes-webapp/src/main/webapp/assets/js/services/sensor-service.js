/**
 * 
 */

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