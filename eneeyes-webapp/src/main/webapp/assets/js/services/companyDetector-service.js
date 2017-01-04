/**
 * 
 */

app.factory('CompanyDetectorService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/companyDetector/delete/:id', {id: '@id'},{
    		companyDetector : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/companyDetector/all',{},{
        	companyDetector : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/companyDetector/obtemPorId/:id', {id: '@id'},{
        	companyDetector : {method : 'GET'}
        }),   
	    listPorCompanyDevice : $resource('/eneeyes/security/api/companyDetector/obtemPorCompanyDevice/:id', {id: '@id'},{
	    	companyDetector : {method : 'GET'}
	    }),    
	    listPorIdArea : $resource('/eneeyes/security/api/companyDetector/obtemPorIdArea/:id', {id: '@id'},{
	    	companyDetector : {method : 'GET'}
	    }),
        save : $resource('/eneeyes/security/api/companyDetector/save',{},{
        	companyDetector : {method : 'POST'}
        }),
        saveList : $resource('/eneeyes/security/api/companyDetector/saveList',{},{
        	companyDetector : {method : 'POST'}
        }),
        updateLatitudeLongitude : $resource('/eneeyes/security/api/companyDetector/updateLatitudeLongitude/:latitude/:longitude/:id/', {latitude: '@latitude', longitude: '@longitude', id: '@id'}, {
        	companyDetector : {method : 'GET'}
        }),
     };
});