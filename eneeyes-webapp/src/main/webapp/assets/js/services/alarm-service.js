app.factory('AlarmService', function($resource) {    
    return {
    	  deletar : $resource('/security/api/alarm/delete/:id', {id: '@id'},{
    		  alarm : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/alarm/all',{},{
        	alarm : {method : 'GET'}
        }),
        listByCompanyId : $resource('/security/api/alarm/obtemPorCompanyId/:companyId', {id: '@companyId'},{
        	alarm : {method : 'GET'}
        }),
        listOne : $resource('/security/api/alarm/obtemPorId/:id', {id: '@id'},{
        	alarm : {method : 'GET'}
        }),
        save : $resource('/security/api/alarm/save',{},{
        	alarm : {method : 'POST'}
        }),
        onOff : $resource('/security/api/alarm/onOff',{},{
        	alarm : {method : 'PUT'}
        }),        
        updateFeedback : $resource('/security/api/alarm/updateAlarmFeedback/:email1/:email2/:email3/:emailOffline/:sms1/:sms2/:sms3/:smsOffline/:uid', { 
            email1: '@email1', 
            email2: '@email2', 
            email3: '@email3', 
            emailOffline: '@emailOffline', 
            sms1: '@sms1',
            sms2: '@sms2',
            sms3: '@sms3',
            smsOffline: '@smsOffline',
            uid: '@uid' },{
        	alarm : {method : 'PUT'}
        }),
     };
});