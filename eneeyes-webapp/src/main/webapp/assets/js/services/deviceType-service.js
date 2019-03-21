app.factory('DeviceTypeService', function($resource){
    return {
      listAllDeviceType : $resource('/security/api/state/DeviceType/allDeviceType',{},{
        deviceType : {method : 'GET'}
        }),
        deletaDeviceType : $resource('/security/api/state/DeviceType/delete/:id', {id: '@id'},{
          deviceType : {method : 'DELETE'}
        }),                
        saveDeviceType : $resource('/security/api/state/DeviceType/save',{},{
        	deviceType : {method : 'POST'}
        }),
     };
});