app.controller('SiteController', function ($scope, $http, $filter, $interval, $timeout, $q, Signin, $sce, UserService) {

	$scope.$root.recolheLogo = true;
	
	$scope.$root.timer = [];
	$scope.$root.currentPage = "";
	$scope.$root.errorTimes = 0;	
	$scope.$root.isFrom = $('#isFrom').val();
	$scope.$root.userId = $('#idUsuario').val();
	$scope.$root.userImage = '/assets/img/avatar_128x128.png';
	
	$scope.showLogo = function(){
		$scope.$root.recolheLogo = !$scope.$root.recolheLogo; 
	}
	
	$scope.LoadAjaxContentCompany = function(url){
		$http.get(url)
	    .success(function (data) {
	    	$scope.ajaxcontentCompany = data;
	    })
	    .error(function (data, status, headers, config) {
	    	alert(errorThrown);
	    });
	}
		
	$scope.tabsShow = [];
	
	$scope.LoadAjaxContent = function(url, title) {
		
		if($scope.$root.currentPage == title) return;
		
		title= title.replace(/\s/g,'');		
		$scope.$root.currentPage = title;
				
		var idTab = $scope.tabsShow.findIndex( function(e) { return e.link === title });
					
		if (idTab >= 0 ) {		
			
			$timeout(function() {
				$("#id_tab_" + $scope.tabsShow[idTab].link).trigger("click");				
			},100);
		}
		else {

			$http.get(url)
	         	.success(function (data) {
	        	
	        	var sBody = {
	        		name : "tab_" + title,
	        		link : title,
					body : data			
	        	};
	        	
	        	$scope.tabsShow.push(sBody);     	
	           
	        	$timeout(function() {
					$("#id_tab_" + title).trigger("click");				
				},100);
	            
	        })
	        .error(function (data, status, headers, config) {

				$rootScope.alertServerDanger = config;
				angular.element('body').removeClass('loading');
					        	
	        });
		}
	}
	
	$scope.removeTab = function (index) {
			
		if($scope.$root.currentPage == $scope.tabsShow[index].link) {
			
			$timeout(function(){			
				$('#id_' + $scope.tabsShow[index - 1].name).trigger("click");				
			}, 100);

			$scope.tabsShow.splice(index, 1);				
		}
		else {

			$scope.tabsShow.splice(index, 1);					
		}		
	};

    $scope.forms = {
	    signin : {
	    	login : '',
	        credential : '',
	     },
		     signexpired : {
		     userId : '',
		     username: '',
		     password : '',
		     newPassword: '',
		    confirm : ''
	    }
    };
    
    $scope.errorMessage = false;

    $scope.signin = function() {

        angular.element('html').addClass('loading');
        angular.element('#signin-error').css('display','none');
        angular.element('#signin-user').removeClass('has-error');
        angular.element('#signin-pass').removeClass('has-error');

        $scope.result = new Signin({
            login : $scope.forms.signin.login,
            credential : $scope.forms.signin.credential,
        });
        $scope.result.$save({_csrf : angular.element('#_csrf').val()}, function(){
            if($scope.result.resultType == 'SUCCESS') {
            	
                window.location.href = '/';
                
            } else {
            	if($scope.result.errorMessages != null && $scope.result.errorMessages.length > 0) {
            		$scope.errorMessage = $scope.result.errorMessages[0].message;
            		angular.element('html').removeClass('loading');
            		
            		var errorMessage = $scope.errorMessage.split('#')[0];
            		var userId = $scope.errorMessage.split('#')[1];
            		var username = $scope.errorMessage.split('#')[2];
            		
            		if(errorMessage == 'signin.new') {
            			
            			angular.element('#formSignin').css('display','block');
            			angular.element('#signinAlert').css('display','block');
            			
            			$scope.signinAlert = true;
            			$scope.showNewPass = false;
            			
            			$scope.forms.signexpired.userId = userId;
            			$scope.forms.signexpired.username = username;
            		}		
        			else if(errorMessage == "signin.error") {
							
            			angular.element('#signin-error').css('display','block');
            			angular.element('#signin-user').addClass('has-error');
            			angular.element('#signin-pass').addClass('has-error');            		
            			
            		} else {
            			angular.element('#signin-error2').css('display','block');            			
            		}
            	}
            }
        },
		function(data) {
			if (data.status >= 400 && data.status <= 505 ) {
				angular.element('html').removeClass('loading');
				angular.element('.session-expired').modal('show');
				$timeout(function(){
					window.location.href='/';
				},2500);
			}
		});
    };

    $scope.changePasswordExpired = function() {
    	
        angular.element('html').addClass('loading');
        angular.element('#password-success').css('display','none');
        angular.element('#password-error').css('display','none');

        $scope.updatePass = new UserService.updatePass($scope.forms.signexpired);
        $scope.updatePass.$user({_csrf : angular.element('#_csrf').val()},function(){
        	
        	angular.element('html').removeClass('loading');
            
        	if($scope.updatePass.resultType == 'SUCCESS') {
            	
        		$scope.successMessage = $scope.updatePass.message + " Refa�a o Login";          
            	
            	angular.element('#formSignin').css('display','none');            	
            	angular.element('#password-success').css('display','block');
    			
            	$scope.forms.signexpired = [];
            	
            	angular.element('html').removeClass('loading');
            
            	$timeout(function(){
            		angular.element('#password-success').css('display','none');
            		$scope.forms.signin.credential = $scope.forms.signexpired.confirm;
            		$scope.forms.signin.credential = '';
            		$scope.showNewPass = false;            		
										
				},5000);
            
            } else {
            	
            	$scope.errorMessage = 'Erro ao Atualizar Senha!!!';

    			angular.element('#signin-error').css('display','block');
    			angular.element('#signin-user').addClass('has-error');
    			angular.element('#signin-pass').addClass('has-error');            		
            	            	
            }        
		});    	
    	
    }

	angular.element('body').addClass('loading');
    $scope.LoadAjaxContent('dashboard.html', 'Dashboard');
   
});