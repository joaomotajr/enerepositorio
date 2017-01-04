/**
 * 
 */

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