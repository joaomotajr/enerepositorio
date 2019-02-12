app.factory('DeviceTypeService', function($resource){
    return {
      listAllDeviceType : $resource('/security/api/state/allDeviceType',{},{
        deviceType : {method : 'GET'}
        }),
        deletaDeviceType : $resource('/security/api/state/delete/:id', {id: '@id'},{
          deviceType : {method : 'DELETE'}
        }),                
        saveDeviceType : $resource('/security/api/state/save',{},{
        	deviceType : {method : 'POST'}
        }),
     };
});