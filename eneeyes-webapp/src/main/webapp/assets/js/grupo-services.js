app.factory('GrupoService', function($resource){
    return {
       inclui : $resource('/eneeyes/security/api/inclusaoGrupo',{},{
           grupo : {method : 'POST'}
       }),
       pesquisaSimples : $resource('/eneeyes/security/api/pesquisaSimplesGrupo',{},{
           grupo : {method : 'POST'}
       }),
       pesquisaCompleta : $resource('/eneeyes/security/api/pesquisaCompletaGrupo',{},{
           grupo : {method : 'POST'}
       }),       
       atualiza : $resource('/eneeyes/security/api/atualizaGrupo',{},{
    	   grupo : {method : 'POST'}
       }),
       remove : $resource('/eneeyes/security/api/removeGrupo',{},{
    	   grupo : {method : 'POST'}
       })
    };
});