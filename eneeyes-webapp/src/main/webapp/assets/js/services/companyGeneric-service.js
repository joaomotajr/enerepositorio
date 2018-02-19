/**
 * 
 */

app.factory('CompanyGenericService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/companyDetector/delete/:id', {id: '@id'},{
    		companyGeneric : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/companyGeneric/all',{},{
        	companyGeneric : {method : 'GET'}
        }),
        listOne : $resource('/security/api/companyGeneric/obtemPorId/:id', {id: '@id'},{
        	companyGeneric : {method : 'GET'}
        }),   
	    listPorCompanyDevice : $resource('/security/api/companyGeneric/obtemPorCompanyDevice/:id', {id: '@id'},{
	    	companyGeneric : {method : 'GET'}
	    }),    
	    listPorAreaId : $resource('/security/api/companyGeneric/obtemPorAreaId/:id', {id: '@id'},{
	    	companyGeneric : {method : 'GET'}
	    }),
        save : $resource('/security/api/companyGeneric/save',{},{
        	companyGeneric : {method : 'POST'}
        }),        
		updateAlarm : $resource('/security/api/companyGeneric/updateAlarm/:alarmId/:id/', {alarmId: '@alarmId', id: '@id'}, {
        	companyGeneric : {method : 'PUT'}
		}),
		removeAlarm : $resource('/security/api/companyGeneric/removeAlarm/:alarmId/:id/', {id: '@id'}, {
        	companyGeneric : {method : 'PUT'}
        }),
     };
});