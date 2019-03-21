app.factory('UnitMeterService', function($resource){
    return {
        listAllUnitMeter : $resource('/security/api/state/allUnitMeter',{},{
        	unitMeter : {method : 'GET'}
        }),
        deletaUnitMeter : $resource('/security/api/state/UnitMeter/delete/:id', {id: '@id'},{
          unitMeter : {method : 'DELETE'}
        }),                
        saveUnitMeter : $resource('/security/api/state/UnitMeter/save',{},{
        	unitMeter : {method : 'POST'}
        }),
     };
});