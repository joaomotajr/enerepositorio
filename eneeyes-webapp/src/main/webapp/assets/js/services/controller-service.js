/**
 * 
 */

app.factory('ControllerService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/controller/delete/:id', {id: '@id'},{
    		controller : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/controller/all',{},{
        	controller : {method : 'GET'}
        }),
        listOne : $resource('/security/api/controller/obtemPorId/:id', {id: '@id'},{
        	controller : {method : 'GET'}
        }),
        save : $resource('/security/api/controller/save',{},{
        	controller : {method : 'POST'}
        }),
     };
});
