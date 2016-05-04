app.factory('VendasResumo', function($resource){
    return {
       vendas : $resource('/security/api/vendas/:data',{data : '@data'},{
    	   pesquisar : {method : 'POST'}
       }),
    };
});