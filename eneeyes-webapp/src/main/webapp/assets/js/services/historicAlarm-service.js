
app.factory('HistoricAlarmService', function($resource){    
    
    return {    	
        listIntervalDays : $resource('/security/api/historicAlarm/findByCompanyDeviceAndIntervalDays/:companyDeviceId/:dateIn/:dateOut/:currentPage/:lenPage/', 
            {
                companyDeviceId: '@companyDeviceId', 
                dateIn: '@dateIn', 
                dateOut: '@dateOut', 
                currentPage: '@currentPage',
                lenPage: '@lenPage'
            },
            { 
                historicAlarm : {method : 'GET'
            }
        }),
        listInterval : $resource('/security/api/historicAlarm/findByCompanyDeviceAndInterval/:companyDeviceId/:interval/:currentPage/:lenPage/', 
            {
                companyDeviceId: '@companyDeviceId', 
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
