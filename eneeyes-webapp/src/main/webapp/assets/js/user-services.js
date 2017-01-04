app.factory('UserService', function($resource){
    return {
       inclusao : $resource('/eneeyes/security/api/inclusaoUser',{},{
           user : {method : 'POST'}
       }),
       edicao : $resource('/eneeyes/security/api/edicaoUser',{},{
           user : {method : 'POST'}
       }),
       remocao : $resource('/eneeyes/security/api/remocaoUser',{},{
           user : {method : 'POST'}
       }),       
       pesquisa : $resource('/eneeyes/security/api/pesquisaUser',{},{
    	   users : {method : 'POST'}
       }),
       pesquisaUser : $resource('/eneeyes/security/api/pesquisaUserNaoRelacionado',{},{
    	   naoRelacionados : {method : 'POST'}
       }),
       pesquisaByFilial : $resource('/eneeyes/security/api/pesquisaUserByFilial',{},{
    	   users : {method : 'POST'}
       })
    };
});