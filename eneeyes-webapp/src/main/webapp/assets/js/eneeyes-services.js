app.factory('Signin', function($resource){
    return $resource('/api/signin');
});

//app.factory('Password', function($resource){
//    return {
//    	update : $resource('/security/api/user/edicaoUser',{},{
//            user : {method : 'PUT'}
//        }),
//	    updatePass : $resource('/api/user/changePassword',{},{
//	        user : {method : 'PUT'}
//	    })
//    };
//});