app.factory('UserService', function($resource){
    return {
       save : $resource('/security/api/user/inclusaoUser',{},{
           user : {method : 'POST'}
       }),
       update : $resource('/security/api/user/edicaoUser',{},{
           user : {method : 'PUT'}
       }),
       updateProfile : $resource('/security/api/user/edicaoUserProfile',{},{
           user : {method : 'PUT'}
       }),
       updatePass : $resource('/api/user/changePassword',{},{
	        user : {method : 'PUT'}
	    }),
       deletar : $resource('/security/api/user/remocaoUser/:id', {id: '@id'},{
           user : {method : 'DELETE'}
       }),       
       listAll : $resource('/security/api/user/pesquisaUsers',{},{
    	   users : {method : 'GET'}
       }),
       listOne : $resource('/security/api/user/pesquisaUserById/:id', {id: '@id'},{
    	   user : {method : 'GET'}
       }),
       listByLogin : $resource('/security/api/user/pesquisaUserByLogin/:login', {login: '@login'},{
     	   user : {method : 'GET'}
       })
    };
});