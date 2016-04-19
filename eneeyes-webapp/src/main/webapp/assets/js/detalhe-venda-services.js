app.factory('DetalheVenda', function($resource){
    return {
       security : $resource('/security/api/pesquisar',{},{
    	   pesquisar : {method : 'POST'}
       }),
       historico : $resource('/security/api/datasIntegracao',{},{
           datasIntegracao : {method : 'GET', params : {check : true}}
       }),
       historicoIntegracao : $resource('/security/api/datasHistoricoIntegracao',{},{
           datasHistoricoIntegracao : {method : 'POST'}
       }),
       inclusao : $resource('/security/api/inclusaoVenda',{},{
           venda : {method : 'POST'}
       }),
    };
});