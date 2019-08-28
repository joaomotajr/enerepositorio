app.factory('HistoricFastViewerService', function($resource) {    
    return {    	               
        listPreDefined : $resource('/security/api/historicFastViewer/findByCompanyDevicePreDefined/:companyDeviceId/:interval/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                interval: '@interval'
            }, { 
                historic : {method : 'GET'}
            }),            
        listPreDefinedGroupHours : $resource('/security/api/historicFastViewer/findByCompanyDevicePreDefinedGroupHours/:companyDeviceId/:interval/',  
            {
                companyDeviceId: '@companyDeviceId',                 
                interval: '@interval'
            }, {        
                historic : {method : 'GET'}
            }), 
        listPreDefinedGroupDays : $resource('/security/api/historicFastViewer/findByCompanyDevicePreDefinedGroupDays/:companyDeviceId/:interval/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                interval: '@interval'
            }, {        
                historic : {method : 'GET'}
            }),                
        listInterval : $resource('/security/api/historicFastViewer/findByCompanyDeviceAndInterval/:companyDeviceId/:dateIn/:dateOut/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                dateIn: '@dateIn', 
                dateOut: '@dateOut'
            }, { 
                historic : {method : 'GET'}
            }),
        listIntervalGroupHours : $resource('/security/api/historicFastViewer/findByCompanyDeviceAndIntervalGroupHours/:companyDeviceId/:dateIn/:dateOut/', 
            {
                companyDeviceId: '@companyDeviceId',             
                dateIn: '@dateIn', 
                dateOut: '@dateOut' 
            }, {        
                historic : {method : 'GET'}
            }),  
        listIntervalGroupDays : $resource('/security/api/historicFastViewer/findByCompanyDeviceAndIntervalGroupDays/:companyDeviceId/:dateIn/:dateOut/', 
            {
                companyDeviceId: '@companyDeviceId',                 
                dateIn: '@dateIn', 
                dateOut: '@dateOut' 
            }, {        
                historic : {method : 'GET'}
            }),
     };
});