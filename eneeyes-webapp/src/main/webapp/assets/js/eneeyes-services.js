app.factory('Signup', function($resource){
    return $resource('/eneeyes/api/signup',{},{
        status : {method : 'GET', params : {check : true}}
    });
});

app.factory('Signin', function($resource){
    return $resource('/eneeyes/api/signin');
});

app.factory('Contact', function($resource){
    return $resource('/eneeyes/api/website/contact',{},{
        send : {method : 'POST'},
        status : {method : 'GET'}
    });
});

app.factory('Website', function($resource){
    return $resource('/eneeyes/security/api/website/:id',{id: '@id'},{
        webpages : {method: 'GET', params: {page:0, query:'', order:''}}
    });
});

app.factory('Shop', function($resource){
    return $resource('/eneeyes/security/api/shop/:id',{id: '@id'},{});
});

app.factory('Password', function($resource){
    return $resource('/eneeyes/api/changePassword',{},{
        change : {method : 'POST'}
    });
});