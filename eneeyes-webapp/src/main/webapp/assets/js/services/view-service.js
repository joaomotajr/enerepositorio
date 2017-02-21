app.factory('ViewService', function($resource){    
    
    return {
    	        
        listAllDashCompany : $resource('/security/api/view/allDashCompany',{},{
        	view : {method : 'GET'}
        }),
        listAllDashCompaniesPosition : $resource('/security/api/view/allDashCompaniesPosition',{},{
        	view : {method : 'GET'}
        }),
        listAllOther : $resource('/security/api/view/allOther',{},{
        	view : {method : 'GET'}
        }),
     };
});
