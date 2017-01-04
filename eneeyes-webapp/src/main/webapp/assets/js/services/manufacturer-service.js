/**
 * 
 */

app.factory('ManufacturerService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/manufacturer/delete/:id', {id: '@id'},{
    		manufacturer : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/manufacturer/all',{},{
        	manufacturer : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/manufacturer/obtemPorId/:id', {id: '@id'},{
        	manufacturer : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/manufacturer/save',{},{
        	manufacturer : {method : 'POST'}
        }),
     };
});