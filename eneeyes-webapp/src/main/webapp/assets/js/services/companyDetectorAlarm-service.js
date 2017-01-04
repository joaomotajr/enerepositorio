/**
 * 
 */

app.factory('CompanyDetectorAlarmService', function($resource){    
    
    return {
        deletar : $resource('/eneeyes/security/api/companyDetectorAlarm/delete',{},{
    		companyDetectorAlarm : {method : 'POST'}
        }), 
        listAll : $resource('/eneeyes/security/api/companyDetectorAlarm/all',{},{
        	companyDetectorAlarm : {method : 'GET'}
        }),
        listPorCompanyDetectorAlarm : $resource('/eneeyes/security/api/companyDetectorAlarm/obtemPorCompanyDetector/:id', {id: '@id'},{        	
        	companyDetectorAlarm : {method : 'GET'}
        }),	    
        save : $resource('/eneeyes/security/api/companyDetectorAlarm/save',{},{
        	companyDetectorAlarm : {method : 'POST'}
        }),
        saveList : $resource('/eneeyes/security/api/companyDetectorAlarm/saveList',{},{
        	companyDetectorAlarm : {method : 'POST'}
        }),        
     };
});