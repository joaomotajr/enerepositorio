/**
 * 
 */

app.factory('CompanyDetectorAlarmService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/companyDetectorAlarm/delete/:id', {id: '@id'},{
    		companyDetectorAlarm : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/companyDetectorAlarm/all',{},{
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