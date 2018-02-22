app.factory('ViewService', function($resource){    
    
    return {       
       listAllDashDetectorsMaintenance : $resource('/security/api/view/allDashDetectorsMaintenanceView',{},{
           view : {method : 'GET'}
       }),
        // listAreaCompanyDetectorsAlarms : $resource('/security/api/view/findAreaCompanyDeviceAlarmViewByAreaId/:areaId/',{ areaId: '@areaId'},{
        // 	view : {method : 'GET'}
        // }),
        listAreaCompanyDeviceAlarms : $resource('/security/api/view/findAreaCompanyDeviceAlarmViewByAreaId/:areaId/',{ areaId: '@areaId'},{
        	view : {method : 'GET'}
        }),   
        listCompanyDeviceAlarms : $resource('/security/api/view/findAreaCompanyDeviceAlarmViewByCompanyDeviceId/:companyDeviceId/',{ companyDeviceId: '@companyDeviceId'},{
        	view : {method : 'GET'}
        }),    
        // listCompanyGenericAlarms : $resource('/security/api/view/findAreaCompanyGenericAlarmViewByCompanyDeviceId/:companyDeviceId/',{ companyDeviceId: '@companyDeviceId'},{
        // 	view : {method : 'GET'}
        // }),
        // listCompanyDetectorsAlarms : $resource('/security/api/view/findAreaCompanyDetectorAlarmViewByCompanyDeviceId/:companyDeviceId/',{ companyDeviceId: '@companyDeviceId'},{
        //  	view : {method : 'GET'}
        // }),
        listAllDashCompany : $resource('/security/api/view/allDashCompany',{},{
        	view : {method : 'GET'}
        }),
        listAllDashCompaniesPosition : $resource('/security/api/view/allDashCompaniesPosition',{},{
        	view : {method : 'GET'}
        }),
        listOneDashCompaniesPositionByCompanyId : $resource('/security/api/view/allDashCompaniesPositionByCompanyId/:companyId/',{ companyId: '@companyId'},{
        	view : {method : 'GET'}
        }),
        listAllDashCompaniesAlarm : $resource('/security/api/view/allDashCompaniesAlarm',{},{
        	view : {method : 'GET'}
        }),               
        listAlarmCompanyDetectorSensorView : $resource('/security/api/view/findAlarmCompanyDetectorSensorViewByAlarmId/:alarmId/', {alarmId: '@alarmId'},{        
        	view : {method : 'GET'}
        }),
        listAllOffline : $resource('/security/api/view/offline/:interval/', {interval: '@interval'},{        
        	view : {method : 'GET'}
        }),                                            
        existsDetector : $resource('/security/api/view/existsDetectorById/:id', {id: '@id'},{
            view : {method : 'GET'}
        }), 
        existsSensor : $resource('/security/api/view/existsSensorById/:id', {id: '@id'},{
            view : {method : 'GET'}
        }),        
     };
});
