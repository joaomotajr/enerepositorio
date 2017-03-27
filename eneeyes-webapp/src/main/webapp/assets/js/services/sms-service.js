app.service('smsServices', function ($http, $q) {

    var constSiappUri = "http://api.allcancesms.com.br/sms/1/text/query?";

    this.sendSMS = function (to, text) {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: constSiappUri + "username=System100&password=NQENsXdl&to=" + to + "&text=" + text,
        }).then(function successCallback(response) {
            deferred.resolve(response);
        }, function errorCallback(response) {
            deferred.reject(response);
        });

        return deferred.promise;

    };
    
});
