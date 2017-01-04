app.factory('GrupoService', function($resource){
    return {
       inclui : $resource('/security/api/inclusaoGrupo',{},{
           grupo : {method : 'POST'}
       }),
       pesquisaSimples : $resource('/security/api/pesquisaSimplesGrupo',{},{
           grupo : {method : 'POST'}
       }),
       pesquisaCompleta : $resource('/security/api/pesquisaCompletaGrupo',{},{
           grupo : {method : 'POST'}
       }),       
       atualiza : $resource('/security/api/atualizaGrupo',{},{
    	   grupo : {method : 'POST'}
       }),
       remove : $resource('/security/api/removeGrupo',{},{
    	   grupo : {method : 'POST'}
       })
    };
});