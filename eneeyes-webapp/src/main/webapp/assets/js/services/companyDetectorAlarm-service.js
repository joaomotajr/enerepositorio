/**
 * 
 */

app.factory('CompanyDetectorAlarmService', function($resource){    
    
    return {
        deletar : $resource('/security/api/companyDetectorAlarm/delete',{},{
    		companyDetectorAlarm : {method : 'POST'}
        }), 
        listAll : $resource('/security/api/companyDetectorAlarm/all',{},{
        	companyDetectorAlarm : {method : 'GET'}
        }),
        listPorAreaId : $resource('/security/api/companyDetectorAlarm/obtemPorAreaId/:id', {id: '@id'},{
        	companyDetectorAlarm : {method : 'GET'}
	    }),
        listPorCompanyDetectorAlarm : $resource('/security/api/companyDetectorAlarm/obtemPorCompanyDetector/:id', {id: '@id'},{        	
        	companyDetectorAlarm : {method : 'GET'}
        }),	    
        save : $resource('/security/api/companyDetectorAlarm/save',{},{
        	companyDetectorAlarm : {method : 'POST'}
        }),
        saveList : $resource('/security/api/companyDetectorAlarm/saveList',{},{
        	companyDetectorAlarm : {method : 'POST'}
        }),        
     };
});