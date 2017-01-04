/**
 * 
 */

app.factory('ManufacturerService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/manufacturer/delete/:id', {id: '@id'},{
    		manufacturer : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/manufacturer/all',{},{
        	manufacturer : {method : 'GET'}
        }),
        listOne : $resource('/security/api/manufacturer/obtemPorId/:id', {id: '@id'},{
        	manufacturer : {method : 'GET'}
        }),
        save : $resource('/security/api/manufacturer/save',{},{
        	manufacturer : {method : 'POST'}
        }),
     };
});