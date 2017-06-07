app.factory('Signin', function($resource){
    return $resource('/api/signin');
});

app.factory('Password', function($resource){
    return $resource('/api/user/changePassword',{},{
        change : {method : 'POST'}
    });
});