
app.factory('HistoricViewService', function($resource){    
    
    return {    	               
        listIntervalDays : $resource('/security/api/historicView/findByCompanyDetectorAndSensorAndIntervalDays/:companyDetectorId/:sensorId/:dateIn/:dateOut/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId', 
                sensorId: '@sensorId', 
                dateIn: '@dateIn', 
                dateIn: '@dateOut', 
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            { 
                historic : {method : 'GET'
            }
        }),
        listInterval : $resource('/security/api/historicView/findByCompanyDetectorAndSensorAndInterval/:companyDetectorId/:sensorId/:interval/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId', 
                sensorId: '@sensorId', 
                interval: '@interval',
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            { 
                historic : {method : 'GET'
            }
        }),            
        listIntervalDaysGroupHours : $resource('/security/api/historicView/findByCompanyDetectorAndSensorAndIntervalDaysGroupHours/:companyDetectorId/:sensorId/:dateIn/:dateOut/:currentPage/:lenPage/', 
        {
            companyDetectorId: '@companyDetectorId', 
            sensorId: '@sensorId', 
            dateIn: '@dateIn', 
            dateIn: '@dateOut',
            currentPage: '@currentPage',
            lenPage: '@lenPage' 
            },
            {        
                historic : {method : 'GET'
            }
        }),
        listIntervalGroupHours : $resource('/security/api/historicView/findByCompanyDetectorAndSensorAndIntervalGroupHours/:companyDetectorId/:sensorId/:interval/:currentPage/:lenPage/',  
            {
                companyDetectorId: '@companyDetectorId', 
                sensorId: '@sensorId', 
                interval: '@interval',
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            {        
                historic : {method : 'GET'
            }
        }), 
        listIntervalDaysGroupDays : $resource('/security/api/historicView/findByCompanyDetectorAndSensorAndIntervalDaysGroupDays/:companyDetectorId/:sensorId/:dateIn/:dateOut/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId', 
                sensorId: '@sensorId', 
                dateIn: '@dateIn', 
                dateIn: '@dateOut',
                currentPage: '@currentPage',
                lenPage: '@lenPage' 
            },
            {        
                historic : {method : 'GET'
            }
        }),
        listIntervalGroupDays : $resource('/security/api/historicView/findByCompanyDetectorAndSensorAndIntervalGroupDays/:companyDetectorId/:sensorId/:interval/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId', 
                sensorId: '@sensorId', 
                interval: '@interval',
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            {        
                historic : {method : 'GET'
            }
        }),                
     };
});