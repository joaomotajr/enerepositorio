/**
 * 
 */

app.factory('DetectorService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/detector/delete/:id', {id: '@id'},{
    		detector : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/detector/all',{},{
        	detector : {method : 'GET'}
        }),
        listOne : $resource('/security/api/detector/obtemPorId/:id', {id: '@id'},{
        	detector : {method : 'GET'}
        }),
        save : $resource('/security/api/detector/save',{},{
        	detector : {method : 'POST'}
        }),
     };
});
