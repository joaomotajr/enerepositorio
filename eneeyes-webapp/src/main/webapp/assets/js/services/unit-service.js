
app.factory('UnitService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/unit/delete/:id', {id: '@id'},{
    		unit : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/unit/all',{},{
        	unit : {method : 'GET'}
        }),
        listOne : $resource('/security/api/unit/getById/:id', {id: '@id'},{
        	unit : {method : 'GET'}
        }),
        listOneByCompanyId : $resource('/security/api/unit/getByCompanyId/:companyId', {companyId: 'companyId'},{
        	unit : {method : 'GET'}
        }),
        save : $resource('/security/api/unit/save',{},{
        	unit : {method : 'POST'}
        }),
        
        listAllFilter : $resource('/security/api/unit/allFilter',{},{
        	unit : {method : 'GET'}
        }),        
     };
});