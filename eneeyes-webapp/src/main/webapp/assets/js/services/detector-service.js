/**
 * 
 */

app.factory('DetectorService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/detector/delete/:id', {id: '@id'},{
    		detector : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/detector/all',{},{
        	detector : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/detector/obtemPorId/:id', {id: '@id'},{
        	detector : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/detector/save',{},{
        	detector : {method : 'POST'}
        }),
     };
});
