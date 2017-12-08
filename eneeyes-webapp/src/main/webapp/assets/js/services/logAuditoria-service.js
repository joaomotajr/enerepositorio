app.factory('LogAuditoriaService', function($resource){    
    
    return {    	        
        // listAll : $resource('/security/api/logAuditoria/all',{},{
        //     logAuditoria : {method : 'GET'}
        // }),
        listPreDef : $resource('/security/api/logAuditoria/preDef/:interval/:currentPage/:lenPage/',
        {
            interval: '@interval',
            currentPage: '@currentPage',
            lenPage: '@lenPage'
        },{
            logAuditoria : {method : 'GET'}
        }),
        listInterval : $resource('/security/api/logAuditoria/interval/:dateIn/:dateOut/:currentPage/:lenPage/',
        {
            dateIn: '@dateIn', 
            dateIn: '@dateOut', 
            currentPage: '@currentPage',
            lenPage: '@lenPage'
        },{
            logAuditoria : {method : 'GET'}
        }),
        // listOne : $resource('/security/api/logAuditoria/obtemPorId/:id', {id: '@id'},{
        //     logAuditoria : {method : 'GET'}
        // }),
        // listByCompany : $resource('/security/api/logAuditoria/obtemPorCompanyId/:companyId', {companyId: '@companyId'},{
        //     logAuditoria : {method : 'GET'}
        // }),        
     };
});