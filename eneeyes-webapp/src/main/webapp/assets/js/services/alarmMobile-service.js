app.factory('AlarmMobileService', function($resource){
    return {
    	deletar : $resource('/security/api/alarmMobile/delete/:id', {id: '@id'},{
    		alarmMobile : {method : 'DELETE'}
      }),
      listByAlarmId : $resource('/security/api/alarmMobile/obtemPorAlarmId/:mobileId', {mobileId: '@mobileId'},{
        alarmMobile : {method : 'GET'}
      }),
      save : $resource('/security/api/alarmMobile/save',{},{
        alarmMobile : {method : 'POST'}
      }),
     };
});
