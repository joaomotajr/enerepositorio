app.factory('Perfil', function($resource){
    return {
       global : $resource('/api/perfil',{},{
        salvar : {method : 'POST', params : {check : true}}
       }),
       security : $resource('/security/api/perfil',{},{
        validar : {method : 'POST', params : {checkPerfilUsuario : true}}
       }),
       documentos : $resource('/security/api/perfil/documento/:uid',{uid: '@uid'},{
        lista : {method : 'POST'},
        obter : {method : 'POST'},
       }),
    };
});