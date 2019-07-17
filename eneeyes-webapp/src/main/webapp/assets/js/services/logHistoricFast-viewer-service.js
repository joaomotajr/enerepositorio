app.factory('HistoricFastViewerService', function($resource) {    
    return {    	               
        listIntervalDays : $resource('/security/api/historicFastViewer/findByCompanyDeviceAndIntervalDays/:companyDeviceId/:dateIn/:dateOut/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                dateIn: '@dateIn', 
                dateOut: '@dateOut'
            }, { 
                historic : {method : 'GET'}
            }),
        listInterval : $resource('/security/api/historicFastViewer/findByCompanyDeviceAndInterval/:companyDeviceId/:interval/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                interval: '@interval'
            }, { 
            historic : {method : 'GET'}
            }),            
        listIntervalDaysGroupHours : $resource('/security/api/historicFastViewer/findByCompanyDeviceAndIntervalDaysGroupHours/:companyDeviceId/:dateIn/:dateOut/', 
            {
            companyDeviceId: '@companyDeviceId',             
            dateIn: '@dateIn', 
            dateOut: '@dateOut' 
            }, {        
                historic : {method : 'GET'}
            }),  
        listIntervalGroupHours : $resource('/security/api/historicFastViewer/findByCompanyDeviceAndIntervalGroupHours/:companyDeviceId/:interval/',  
            {
                companyDeviceId: '@companyDeviceId',                 
                interval: '@interval'
            }, {        
                historic : {method : 'GET'}
            }), 
        listIntervalDaysGroupDays : $resource('/security/api/historicFastViewer/findByCompanyDeviceAndIntervalDaysGroupDays/:companyDeviceId/:dateIn/:dateOut/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                dateIn: '@dateIn', 
                dateOut: '@dateOut' 
            }, {        
                historic : {method : 'GET'}
            }),
        listIntervalGroupDays : $resource('/security/api/historicFastViewer/findByCompanyDeviceAndIntervalGroupDays/:companyDeviceId/:interval/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                interval: '@interval'
            }, {        
                historic : {method : 'GET'}
            }),                
     };
});