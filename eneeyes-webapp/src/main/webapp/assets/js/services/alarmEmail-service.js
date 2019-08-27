app.factory('AlarmEmailService', function($resource){
    return {
    	deletar : $resource('/security/api/alarmEmail/delete/:id', {id: '@id'},{
    		alarmEmail : {method : 'DELETE'}
      }),
      listByAlarmId : $resource('/security/api/alarmEmail/obtemPorAlarmId/:alarmId', {alarmId: '@alarmId'},{
        alarmEmail : {method : 'GET'}
      }),
      save : $resource('/security/api/alarmEmail/save',{},{
        alarmEmail : {method : 'POST'}
      }),
     };
});
