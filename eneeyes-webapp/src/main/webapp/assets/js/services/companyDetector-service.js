/**
 * 
 */

app.factory('CompanyDetectorService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/companyDetector/delete/:id', {id: '@id'},{
    		companyDetector : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/companyDetector/all',{},{
        	companyDetector : {method : 'GET'}
        }),
        listOne : $resource('/security/api/companyDetector/obtemPorId/:id', {id: '@id'},{
        	companyDetector : {method : 'GET'}
        }),   
	    listPorCompanyDevice : $resource('/security/api/companyDetector/obtemPorCompanyDevice/:id', {id: '@id'},{
	    	companyDetector : {method : 'GET'}
	    }),    
	    listPorAreaId : $resource('/security/api/companyDetector/obtemPorAreaId/:id', {id: '@id'},{
	    	companyDetector : {method : 'GET'}
	    }),
        save : $resource('/security/api/companyDetector/save',{},{
        	companyDetector : {method : 'POST'}
        }),
        updateLatitudeLongitude : $resource('/security/api/companyDetector/updateLatitudeLongitude/:latitude/:longitude/:id/', {latitude: '@latitude', longitude: '@longitude', id: '@id'}, {
        	companyDetector : {method : 'PUT'}
		}),
		updateAlarm : $resource('/security/api/companyDetector/updateAlarm/:alarmId/:id/', {alarmId: '@alarmId', id: '@id'}, {
        	companyDetector : {method : 'PUT'}
		}),
		removeAlarm : $resource('/security/api/companyDetector/removeAlarm/:alarmId/:id/', {id: '@id'}, {
        	companyDetector : {method : 'PUT'}
        }),
     };
});