app.factory('UserService', function($resource){
    return {
       inclusao : $resource('/security/api/inclusaoUser',{},{
           user : {method : 'POST'}
       }),
       edicao : $resource('/security/api/edicaoUser',{},{
           user : {method : 'POST'}
       }),
       remocao : $resource('/security/api/remocaoUser',{},{
           user : {method : 'POST'}
       }),       
       pesquisa : $resource('/security/api/pesquisaUser',{},{
    	   users : {method : 'POST'}
       }),
       pesquisaUser : $resource('/security/api/pesquisaUserNaoRelacionado',{},{
    	   naoRelacionados : {method : 'POST'}
       }),
       pesquisaByFilial : $resource('/security/api/pesquisaUserByFilial',{},{
    	   users : {method : 'POST'}
       })
    };
});