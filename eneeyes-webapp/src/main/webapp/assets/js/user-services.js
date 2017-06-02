app.factory('UserService', function($resource){
    return {
       save : $resource('/security/api/user/inclusaoUser',{},{
           user : {method : 'POST'}
       }),
       update : $resource('/security/api/user/edicaoUser',{},{
           user : {method : 'PUT'}
       }),
       deletar : $resource('/security/api/user/remocaoUser',{},{
           user : {method : 'DELETE'}
       }),       
       listAll : $resource('/security/api/user/pesquisaUsers',{},{
    	   users : {method : 'GET'}
       }),
       listOne : $resource('/security/api/user/obtemPorId/:id', {id: '@id'},{
    	   user : {method : 'GET'}
       }),
       listByLogin : $resource('/security/api/user/pesquisaUserByLogin/:login', {login: '@login'},{
     	   user : {method : 'GET'}
       })
    };
});