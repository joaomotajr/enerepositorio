app.factory('AlarmService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/alarm/delete/:id', {id: '@id'},{
    		alarm : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/alarm/all',{},{
        	alarm : {method : 'GET'}
        }),
        listOne : $resource('/security/api/alarm/obtemPorId/:id', {id: '@id'},{
        	alarm : {method : 'GET'}
        }),
        save : $resource('/security/api/alarm/save',{},{
        	alarm : {method : 'POST'}
        }),
        onOff : $resource('/security/api/alarm/onOff',{},{
        	alarm : {method : 'PUT'}
        }),
     };
});
