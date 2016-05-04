app.factory('Conciliacao', function($resource){
    return {
       security : $resource('/security/api/pesquisarConciliacao',{},{
    	   pesquisar : {method : 'POST'}
       }),
    };
});