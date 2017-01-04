app.factory('ViewService', function($resource){    
    
    return {
    	        
        listAllDashCompany : $resource('/security/api/view/allDashCompany',{},{
        	view : {method : 'GET'}
        }),
        listAllOther : $resource('/security/api/view/allOther',{},{
        	view : {method : 'GET'}
        }),
     };
});
