/**
 * 
 */

app.factory('GenericService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/generic/delete/:id', {id: '@id'},{
    		generic : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/generic/all',{},{
        	generic : {method : 'GET'}
        }),
        listOne : $resource('/security/api/generic/obtemPorId/:id', {id: '@id'},{
        	generic : {method : 'GET'}
        }),
        save : $resource('/security/api/generic/save',{},{
        	generic : {method : 'POST'}
        }),
     };
});
