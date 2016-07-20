/**
 * 
 */

app.factory('GasService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/gas/delete/:id', {id: '@id'},{
    		gas : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/gas/all',{},{
        	gas : {method : 'GET'}
        }),
        listOne : $resource('/security/api/gas/obtemPorId/:id', {id: '@id'},{
        	gas : {method : 'GET'}
        }),
        save : $resource('/security/api/gas/save',{},{
        	gas : {method : 'POST'}
        }),
     };
});