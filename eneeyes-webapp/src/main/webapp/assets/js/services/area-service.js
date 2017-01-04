app.factory('AreaService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/area/delete/:id', {id: '@id'},{
            area : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/area/all',{},{
            area : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/area/obtemPorId/:id', {id: '@id'},{
            area : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/area/save',{},{
            area : {method : 'POST'}
        }),
     };
});