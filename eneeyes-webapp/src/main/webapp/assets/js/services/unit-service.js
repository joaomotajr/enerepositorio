
app.factory('UnitService', function($resource){    
    
    return {
    	deletar : $resource('/security/api/unit/delete/:id', {id: '@id'},{
    		unit : {method : 'DELETE'}
        }),        
        listAll : $resource('/security/api/unit/all',{},{
        	unit : {method : 'GET'}
        }),
        listOne : $resource('/security/api/unit/obtemPorId/:id', {id: '@id'},{
        	unit : {method : 'GET'}
        }),
        save : $resource('/security/api/unit/save',{},{
        	unit : {method : 'POST'}
        }),
        setParent : $resource('/security/api/unit/setparent/:id/:parentid/', {id: '@id', parentid: '@parentid'}, {
        	unit : {method : 'GET'}
        }),
        listAllFilter : $resource('/security/api/unit/allFilter',{},{
        	unit : {method : 'GET'}
        }),        
     };
});