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
        listAllHistoricView : $resource('/security/api/view/allHistoricView',{},{
        	view : {method : 'GET'}
        }),
        listIntervalDays : $resource('/security/api/view/findByCompanyDetectorAndSensorAndIntervalDays/:companyDetectorId/:sensorId/:dateIn/:dateOut/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', dateIn: '@dateIn', dateIn: '@dateOut' },{        
        	historic : {method : 'GET'}
        }),
        listInterval : $resource('/security/api/view/findByCompanyDetectorAndSensorAndInterval/:companyDetectorId/:sensorId/:interval/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', interval: '@interval'},{        
        	historic : {method : 'GET'}
        }),        
        listLastMonth : $resource('/security/api/view/findByCompanyDetectorAndSensorLastMonth/:companyDetectorId/:sensorId/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId'},{        
        	historic : {method : 'GET'}
        }),
        listIntervalDaysGroupHours : $resource('/security/api/view/findByCompanyDetectorAndSensorAndIntervalDaysGroupHours/:companyDetectorId/:sensorId/:dateIn/:dateOut/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', dateIn: '@dateIn', dateIn: '@dateOut' },{        
        	historic : {method : 'GET'}
        }),
        listIntervalGroupHours : $resource('/security/api/view/findByCompanyDetectorAndSensorAndIntervalGroupHours/:companyDetectorId/:sensorId/:interval/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId', interval: '@interval'},{        
        	historic : {method : 'GET'}
        }),        
        listLastMonthGroupHours : $resource('/security/api/view/findByCompanyDetectorAndSensorLastMonthGroupHours/:companyDetectorId/:sensorId/', {companyDetectorId: '@companyDetectorId', sensorId: '@sensorId'},{        
        	historic : {method : 'GET'}
        }),
     };
});
