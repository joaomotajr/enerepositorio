app.factory('UnitMeterService', function($resource){
    return {
        listAllUnitMeter : $resource('/security/api/state/allUnitMeter',{},{
        	unitMeter : {method : 'GET'}
        }),
     };
});