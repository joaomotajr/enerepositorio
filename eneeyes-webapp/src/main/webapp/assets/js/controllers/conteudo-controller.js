
app.controller('conteudoController', function ($scope, $rootScope, $timeout, $filter) {
     
	 $rootScope.alertServerDanger = undefined;
	 angular.element('body').removeClass('loading');	
	 
	 $scope.clearAlert = function() {
		 $rootScope.alertServerDanger = undefined;
	 }
	 
	 $scope.fCurrentPage = function(menu) {
		 $rootScope.currentPage = menu;
	 }

	 $rootScope.showGeneralMessage = function(msg, alertType) {
		angular.element('body').removeClass('loading');    
		$scope.generalMessage = msg;
		$scope.generalAlert = alertType;
		$("#idGeneralMessage").fadeIn();
		window.setTimeout(function () { $("#idGeneralMessage").fadeOut(300) }, 3000);
	}
	 	 
});