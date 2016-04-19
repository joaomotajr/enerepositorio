app.factory('Inconsistencia', function($resource){
    return {
       security : $resource('/security/api/pesquisarInconsistencias',{},{
    	   pesquisar : {method : 'POST'}
       }),
       tipoJustificativa : $resource('/security/api/obterListaTipoJustificativa',{},{
           obterLista : {method : 'GET', params : {check : true}}
       }),
       incluir : $resource('/security/api/inclusaoJustificativaInconsistencia',{},{
           justificativa : {method : 'POST'}
       }),
    };
});