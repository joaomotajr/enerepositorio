/**
 * 
 */

app.factory('CompanyDeviceService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/companyDevice/delete/:id', {id: '@id'},{
    		companyDevice : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/companyDevice/all',{},{
        	companyDevice : {method : 'GET'}
        }),
        listOne : $resource('/security/api/companyDevice/obtemPorId/:id', {id: '@id'},{
        	companyDevice : {method : 'GET'}
        }),
        save : $resource('/security/api/companyDevice/save',{},{
        	companyDevice : {method : 'POST'}
        }),
        updateAlarm : $resource('/security/api/companyDevice/updateAlarm/:alarmId/:id/', {alarmId: '@alarmId', id: '@id'}, {
        	companyDevice : {method : 'PUT'}
        }),
        removeAlarm : $resource('/security/api/companyDevice/removeAlarm/:alarmId/:id/', {id: '@id'}, {
              companyDevice : {method : 'PUT'}
        }),
     };
});
