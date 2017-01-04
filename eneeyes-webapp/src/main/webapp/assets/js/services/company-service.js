
app.factory('CompanyService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/company/delete/:id', {id: '@id'},{
    		company : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/company/all',{},{
        	company : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/company/obtemPorId/:id', {id: '@id'},{
        	company : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/company/save',{},{
        	company : {method : 'POST'}
        }),
     };
});