app.factory('Pagamentos', function($resource){
    return {
       security : $resource('/security/api/pesquisarPagamentos',{},{
    	   pesquisar : {method : 'POST'}
       }),
    };
});