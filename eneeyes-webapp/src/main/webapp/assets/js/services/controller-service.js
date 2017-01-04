/**
 * 
 */

app.factory('ControllerService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/controller/delete/:id', {id: '@id'},{
    		controller : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/controller/all',{},{
        	controller : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/controller/obtemPorId/:id', {id: '@id'},{
        	controller : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/controller/save',{},{
        	controller : {method : 'POST'}
        }),
     };
});
