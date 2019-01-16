app.factory('DeviceTypeService', function($resource){
    return {
        listAllDeviceType : $resource('/security/api/state/allDeviceType',{},{
        	deviceType : {method : 'GET'}
        }),
     };
});