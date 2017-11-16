
app.factory('HistoricService', function($resource){    
    
    return {    	       
        listAll : $resource('/security/api/historic/all',{},{
        	historic : {method : 'GET'}
        }),
        listOne : $resource('/security/api/historic/obtemPorId/:id', {id: '@id'},{
        	historic : {method : 'GET'}
        }),       
        save : $resource('/security/api/historic/save',{},{
        	historic : {method : 'POST'}
        }),                            
        saveByPositionUid : $resource('/api/historic/SaveByPositionUid/:uid/:value/', {uid: '@uid', value: '@value' },{
        	historic : {method : 'POST'}
        }),                             
        saveByPositionUid2 : $resource('/api/historic/SaveByPositionUid2/:uid/:value/', {uid: '@uid', value: '@value' },{
        	historic : {method : 'GET'}
        }),
     };
});
