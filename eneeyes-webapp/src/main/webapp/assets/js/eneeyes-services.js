app.factory('Signin', function($resource){
    return $resource('/api/signin');
});
