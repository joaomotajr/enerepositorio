
app.controller('conteudoController', function ($scope, $rootScope, $timeout, $filter) {
     
	 $rootScope.alertDanger = undefined;
	 angular.element('body').removeClass('loading');	
	 
	 $scope.clearAlert = function() {
		 $rootScope.alertDanger = undefined;
	 }
});