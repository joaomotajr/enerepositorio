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
	    listPorIdArea : $resource('/security/api/companyDetector/obtemPorIdArea/:id', {id: '@id'},{
	    	companyDetector : {method : 'GET'}
	    }),
        save : $resource('/security/api/companyDetector/save',{},{
        	companyDetector : {method : 'POST'}
        }),
     };
});