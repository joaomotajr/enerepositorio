app.factory('CompanyDetectorMaintenanceHistoricService', function($resource){    
    
    return {
        deletar : $resource('/security/api/companyDetectorMaintenanceHistoric/delete',{},{
        	companyDetectorMaintenanceHistoric : {method : 'POST'}
        }), 
        listAll : $resource('/security/api/companyDetectorMaintenanceHistoric/all',{},{
        	companyDetectorMaintenanceHistoric : {method : 'GET'}
        }),
        listPorCompanyDetector : $resource('/security/api/companyDetectorMaintenanceHistoric/obtemPorCompanyDetector/:id', {id: '@id'},{        	
        	companyDetectorMaintenanceHistoric : {method : 'GET'}
        }),	    
        save : $resource('/security/api/companyDetectorMaintenanceHistoric/save',{},{
        	companyDetectorMaintenanceHistoric : {method : 'POST'}
        }),        
     };
});