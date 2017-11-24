app.factory('LogAuditoriaService', function($resource){    
    
    return {    	        
        listAll : $resource('/security/api/logAuditoria/all',{},{
            logAuditoria : {method : 'GET'}
        }),
        listOne : $resource('/security/api/logAuditoria/obtemPorId/:id', {id: '@id'},{
            logAuditoria : {method : 'GET'}
        }),
        listByCompany : $resource('/security/api/logAuditoria/obtemPorCompanyId/:companyId', {companyId: '@companyId'},{
            logAuditoria : {method : 'GET'}
        }),        
     };
});