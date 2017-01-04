/**
 * 
 */

app.factory('GasService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/gas/delete/:id', {id: '@id'},{
    		gas : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/gas/all',{},{
        	gas : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/gas/obtemPorId/:id', {id: '@id'},{
        	gas : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/gas/save',{},{
        	gas : {method : 'POST'}
        }),
     };
});