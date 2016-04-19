app.factory('FilialService', function($resource){
    return {
       inclusao : $resource('/security/api/inclusaoFilial',{},{
           filial : {method : 'POST'}
       }),
       edicao : $resource('/security/api/edicaoFilial',{},{
           filial : {method : 'POST'}
       }),
       remocao : $resource('/security/api/remocaoFilial',{},{
           filial : {method : 'POST'}
       }),       
       pesquisa : $resource('/security/api/pesquisaFilial',{},{
    	   filiais : {method : 'POST'}
       }),
       pesquisaFilial : $resource('/security/api/pesquisaFilialNaoRelacionada',{},{
    	   naoRelacionadas : {method : 'POST'}
       }),
       pesquisaSemGrupo : $resource('/security/api/pesquisaFilialSemGrupo',{},{
    	   filial : {method : 'POST'}
       }),       
       relacionaByUser : $resource('/security/api/inclusaoRelacionamentoByUser',{},{
           filial : {method : 'POST'}
       }),
       desrelacionaByUser : $resource('/security/api/exclusaoRelacionamentoByUser',{},{
           filial : {method : 'POST'}
       }),
       relacionaByFilial : $resource('/security/api/inclusaoRelacionamentoByFilial',{},{
           user : {method : 'POST'}
       }),
       desrelacionaByFilial : $resource('/security/api/exclusaoRelacionamentoByFilial',{},{
           user : {method : 'POST'}
       }),
       pesquisaCep : $resource('https://viacep.com.br/ws/:cep/json/ ', {cep:'@cep'})
    };
});