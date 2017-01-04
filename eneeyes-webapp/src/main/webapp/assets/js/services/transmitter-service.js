/**
 * 
 */

app.factory('TransmitterService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/transmitter/delete/:id', {id: '@id'},{
    		transmitter : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/transmitter/all',{},{
        	transmitter : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/transmitter/obtemPorId/:id', {id: '@id'},{
        	transmitter : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/transmitter/save',{},{
        	transmitter : {method : 'POST'}
        }),
     };
});