app.factory('RoleService', function($resource){
    return {
       save : $resource('/security/api/role/inclusaoRole',{},{
           role : {method : 'POST'}
       }),
       update : $resource('/security/api/role/edicaoRole',{},{
           role : {method : 'PUT'}
       }),
       deletar : $resource('/security/api/role/remocaoRole',{},{
           role : {method : 'DELETE'}
       }),       
       listAll : $resource('/security/api/role/pesquisaRoles',{},{
    	   roles : {method : 'GET'}
       }),
       listOne : $resource('/security/api/role/obtemPorId/:id', {id: '@id'},{
    	   role : {method : 'GET'}
    	})
    };
});