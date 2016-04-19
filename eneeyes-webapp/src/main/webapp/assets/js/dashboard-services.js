app.factory('Dashboard', function($resource){
    return {
       vendas : $resource('/security/api/vendas',{},{
    	   pesquisar : {method : 'POST'}
       }),
    };
});