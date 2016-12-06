/**
 * 
 */

app.factory('HistoricService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/historic/delete/:id', {id: '@id'},{
    		historic : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/historic/all',{},{
        	historic : {method : 'GET'}
        }),
        listOne : $resource('/security/api/historic/obtemPorId/:id', {id: '@id'},{
        	historic : {method : 'GET'}
        }),
        listOneBySensor : $resource('/security/api/historic/obtemPorSensorId/:id', {id: '@id'},{
        	historic : {method : 'GET'}
        }),
        listOneByCompanyDetector : $resource('/security/api/historic/obtemPorCompanyDetectorId/:id', {id: '@id'},{
        	historic : {method : 'GET'}
        }),
        save : $resource('/security/api/historic/save',{},{
        	historic : {method : 'POST'}
        }),
     };
});