app.factory('PerfilAlarmService', function($resource){
    return {
      listAllPerfilAlarm : $resource('/security/api/state/PerfilAlarm/allPerfilAlarm',{},{
        perfilAlarm : {method : 'GET'}
        }),
        deletaPerfilAlarm : $resource('/security/api/state/PerfilAlarm/delete/:id', {id: '@id'},{
          perfilAlarm : {method : 'DELETE'}
        }),                
        savePerfilAlarm : $resource('/security/api/state/PerfilAlarm/save',{},{
        	perfilAlarm : {method : 'POST'}
        }),
     };
});