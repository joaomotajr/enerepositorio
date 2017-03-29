app.factory('PositionAlarmMessageService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/positionAlarmMessage/delete/:id', {id: '@id'},{
            positionAlarmMessage : {method : 'DELETE'}
        }),        
        listByPositionAlarmId : $resource('/security/api/positionAlarmMessage/obtemPorPositionAlarmId/:id', {id: '@id'},{
            positionAlarmMessage : {method : 'GET'}
        }),
        save : $resource('/security/api/positionAlarmMessage/save',{},{
            positionAlarmMessage : {method : 'POST'}
        }),
     };
});