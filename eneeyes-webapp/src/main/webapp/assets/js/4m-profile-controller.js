app.controller('ProfileController', function($scope, $interval, Website) {
    $scope.website = new Website();

    $scope.search= {
        query : '',
        order : '',
    };

    $scope.setSearchOrder = function(order) {
        $scope.search.order = order;
        $scope.refreshPages();
    };

    $scope.refreshPages = function() {
        if($scope.website == null || $scope.website.items == null) {
            return;
        }

        Website.webpages({id: $scope.website.items[0].code, query:$scope.search.query, order:$scope.search.order}, function(collectionResult) {
            $scope.website.items[0].webpages = collectionResult.items;
        });
    };

    $scope.refresh = function() {
        $scope.refreshPages();
    };

    $scope.loadWebsites = function(){
        $scope.website.$get(function() {
            $scope.refreshPages();
        });
    };

    $scope.loadWebsites();

    $scope.$watch('search.query', function(value){
        $scope.refreshPages();
    });

    angular.element('#profile').addClass('hide-partial-loader');
    /*var timer = $interval(function(){
        $scope.website.$get(function() {

            if($scope.website.items[0].totalPages == $scope.website.items[0].webpages.length) {
                $interval.cancel(timer);
                angular.element('#profile').addClass('hide-partial-loader');
            }
        });
    }, 1000);*/
});