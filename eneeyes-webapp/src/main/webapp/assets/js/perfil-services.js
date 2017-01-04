app.factory('Perfil', function($resource){
    return {
       global : $resource('/eneeyes/api/perfil',{},{
        salvar : {method : 'POST', params : {check : true}}
       }),
       security : $resource('/eneeyes/security/api/perfil',{},{
        validar : {method : 'POST', params : {checkPerfilUsuario : true}}
       }),
       documentos : $resource('/eneeyes/security/api/perfil/documento/:uid',{uid: '@uid'},{
        lista : {method : 'POST'},
        obter : {method : 'POST'},
       }),
    };
});