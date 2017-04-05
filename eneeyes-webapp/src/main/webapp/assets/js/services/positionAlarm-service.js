app.factory('PositionAlarmService', function($resource){    
    
    return {  	
        updateStatus : $resource('/security/api/positionAlarm/updateAlarmStatus/:alarmStatus/:uid/', {alarmStatus: '@alarmStatus', uid: '@uid' },{
        	positionAlarm : {method : 'PUT'}
        }),                                      
        updateSoundStatus : $resource('/security/api/positionAlarm/updateSoundStatus/:soundStatus/:uid/', {soundStatus: '@soundStatus', uid: '@uid' },{
        	positionAlarm : {method : 'PUT'}
        }),
     };
});