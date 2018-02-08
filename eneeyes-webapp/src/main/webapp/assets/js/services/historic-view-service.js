
app.factory('HistoricViewService', function($resource){    
    
    return {    	               
        listIntervalDays : $resource('/security/api/historicView/findByCompanyDetectorAndIntervalDays/:companyDetectorId/:dateIn/:dateOut/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId',                 
                dateIn: '@dateIn', 
                dateIn: '@dateOut', 
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            { 
                historic : {method : 'GET'
            }
        }),
        listInterval : $resource('/security/api/historicView/findByCompanyDetectorAndInterval/:companyDetectorId/:interval/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId',                 
                interval: '@interval',
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            { 
                historic : {method : 'GET'
            }
        }),            
        listIntervalDaysGroupHours : $resource('/security/api/historicView/findByCompanyDetectorAndIntervalDaysGroupHours/:companyDetectorId/:dateIn/:dateOut/:currentPage/:lenPage/', 
        {
            companyDetectorId: '@companyDetectorId',             
            dateIn: '@dateIn', 
            dateIn: '@dateOut',
            currentPage: '@currentPage',
            lenPage: '@lenPage' 
            },
            {        
                historic : {method : 'GET'
            }
        }),
        listIntervalGroupHours : $resource('/security/api/historicView/findByCompanyDetectorAndIntervalGroupHours/:companyDetectorId/:interval/:currentPage/:lenPage/',  
            {
                companyDetectorId: '@companyDetectorId',                 
                interval: '@interval',
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            {        
                historic : {method : 'GET'
            }
        }), 
        listIntervalDaysGroupDays : $resource('/security/api/historicView/findByCompanyDetectorAndIntervalDaysGroupDays/:companyDetectorId/:dateIn/:dateOut/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId',                 
                dateIn: '@dateIn', 
                dateIn: '@dateOut',
                currentPage: '@currentPage',
                lenPage: '@lenPage' 
            },
            {        
                historic : {method : 'GET'
            }
        }),
        listIntervalGroupDays : $resource('/security/api/historicView/findByCompanyDetectorAndIntervalGroupDays/:companyDetectorId/:interval/:currentPage/:lenPage/', 
            {
                companyDetectorId: '@companyDetectorId',                 
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