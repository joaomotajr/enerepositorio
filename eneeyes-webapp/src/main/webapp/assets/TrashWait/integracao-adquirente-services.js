app.factory('IntegracaoAdquirente', function($resource){
    return {
       historicoIntegracao : $resource('/security/api/datasHistoricoIntegracaoAdquirente',{},{
           datasHistoricoIntegracao : {method : 'POST'}
       }),
    };
});