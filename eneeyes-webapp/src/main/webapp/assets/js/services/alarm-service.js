app.factory('AlarmService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/alarm/delete/:id', {id: '@id'},{
    		alarm : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/alarm/all',{},{
        	alarm : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/alarm/obtemPorId/:id', {id: '@id'},{
        	alarm : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/alarm/save',{},{
        	alarm : {method : 'POST'}
        }),
     };
});
