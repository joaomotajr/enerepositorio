
app.factory('HistoricAlarmService', function($resource){    
    
    return {    	
        listIntervalDays : $resource('/security/api/historicAlarm/findByCompanyDetectorAndSensorAndIntervalDays/:companyDetectorId/:sensorId/:dateIn/:dateOut/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId', 
                sensorId: '@sensorId', 
                dateIn: '@dateIn', 
                dateIn: '@dateOut', 
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            { 
                historicAlarm : {method : 'GET'
            }
        }),
        listInterval : $resource('/security/api/historicAlarm/findByCompanyDetectorAndSensorAndInterval/:companyDetectorId/:sensorId/:interval/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId', 
                sensorId: '@sensorId', 
                interval: '@interval',
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            { 
                historicAlarm : {method : 'GET'
            }
        }),        
        listAll : $resource('/security/api/historicAlarm/all',{},{
        	historicAlarm : {method : 'GET'}
        }),
        listOne : $resource('/security/api/historicAlarm/obtemPorId/:id', {id: '@id'},{
        	historicAlarm : {method : 'GET'}
        }),
     };
});
