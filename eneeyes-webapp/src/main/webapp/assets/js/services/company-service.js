
app.factory('CompanyService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/company/delete/:id', {id: '@id'},{
    		company : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/company/all',{},{
        	company : {method : 'GET'}
        }),
        listAllView : $resource('/security/api/company/allView',{},{
        	company : {method : 'GET'}
        }),
        listAllSumaryView : $resource('/security/api/company/allSumaryView',{},{
        	company : {method : 'GET'}
        }),
        listOne : $resource('/security/api/company/obtemPorId/:id', {id: '@id'},{
        	company : {method : 'GET'}
        }),
        save : $resource('/security/api/company/save',{},{
        	company : {method : 'POST'}
        }),
     };
});