app.factory('TaxaService', function($resource){
    return {
       inclusao : $resource('/security/api/inclusaoTaxa',{},{
           taxa : {method : 'POST'}
       }),
       pesquisa : $resource('/security/api/pesquisaTaxa',{},{
    	   taxas : {method : 'POST'}
       })
    };
});