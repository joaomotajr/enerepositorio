
app.factory('HistoricViewService', function($resource){    
    
    return {    	               
        listIntervalDays : $resource('/security/api/historicView/findByCompanyDeviceAndIntervalDays/:companyDeviceId/:dateIn/:dateOut/:currentPage/:lenPage/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                dateIn: '@dateIn', 
                dateIn: '@dateOut', 
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            { 
                historic : {method : 'GET'
            }
        }),
        listInterval : $resource('/security/api/historicView/findByCompanyDeviceAndInterval/:companyDeviceId/:interval/:currentPage/:lenPage/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                interval: '@interval',
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            { 
                historic : {method : 'GET'
            }
        }),            
        listIntervalDaysGroupHours : $resource('/security/api/historicView/findByCompanyDeviceAndIntervalDaysGroupHours/:companyDeviceId/:dateIn/:dateOut/:currentPage/:lenPage/', 
        {
            companyDeviceId: '@companyDeviceId',             
            dateIn: '@dateIn', 
            dateIn: '@dateOut',
            currentPage: '@currentPage',
            lenPage: '@lenPage' 
            },
            {        
                historic : {method : 'GET'
            }
        }),
        listIntervalGroupHours : $resource('/security/api/historicView/findByCompanyDeviceAndIntervalGroupHours/:companyDeviceId/:interval/:currentPage/:lenPage/',  
            {
                companyDeviceId: '@companyDeviceId',                 
                interval: '@interval',
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            {        
                historic : {method : 'GET'
            }
        }), 
        listIntervalDaysGroupDays : $resource('/security/api/historicView/findByCompanyDeviceAndIntervalDaysGroupDays/:companyDeviceId/:dateIn/:dateOut/:currentPage/:lenPage/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                dateIn: '@dateIn', 
                dateIn: '@dateOut',
                currentPage: '@currentPage',
                lenPage: '@lenPage' 
            },
            {        
                historic : {method : 'GET'
            }
        }),
        listIntervalGroupDays : $resource('/security/api/historicView/findByCompanyDeviceAndIntervalGroupDays/:companyDeviceId/:interval/:currentPage/:lenPage/', 
            {
                companyDeviceId: '@companyDeviceId',                 
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