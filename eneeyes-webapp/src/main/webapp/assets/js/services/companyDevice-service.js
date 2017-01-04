/**
 * 
 */

app.factory('CompanyDeviceService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/companyDevice/delete/:id', {id: '@id'},{
    		companyDevice : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/companyDevice/all',{},{
        	companyDevice : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/companyDevice/obtemPorId/:id', {id: '@id'},{
        	companyDevice : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/companyDevice/save',{},{
        	companyDevice : {method : 'POST'}
        }),
     };
});
