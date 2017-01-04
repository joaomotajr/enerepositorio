
app.factory('UnitService', function($resource){    
    
    return {
    	deletar : $resource('/eneeyes/security/api/unit/delete/:id', {id: '@id'},{
    		unit : {method : 'DELETE'}
        }),        
        listAll : $resource('/eneeyes/security/api/unit/all',{},{
        	unit : {method : 'GET'}
        }),
        listOne : $resource('/eneeyes/security/api/unit/obtemPorId/:id', {id: '@id'},{
        	unit : {method : 'GET'}
        }),
        save : $resource('/eneeyes/security/api/unit/save',{},{
        	unit : {method : 'POST'}
        }),
        setParent : $resource('/eneeyes/security/api/unit/setparent/:id/:parentid/', {id: '@id', parentid: '@parentid'}, {
        	unit : {method : 'GET'}
        }),
        listAllFilter : $resource('/eneeyes/security/api/unit/allFilter',{},{
        	unit : {method : 'GET'}
        }),        
     };
});