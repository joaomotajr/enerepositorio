app.factory('ContaCorrente', function($resource){
    return {
       security : $resource('/security/api/pesquisarContaCorrente',{},{
    	   pesquisar : {method : 'POST'}
       }),
    };
});