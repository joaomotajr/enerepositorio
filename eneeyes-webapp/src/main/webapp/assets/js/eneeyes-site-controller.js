//function callPaginaAjax(url) {
//	angular.element('body').scope().LoadAjaxContent(url);
//}

app.controller('SiteController', function ($scope, $http, $filter, $interval, $timeout, $q, Signin, $sce) {
	
	
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
		
		title= title.replace(/\s/g,'');
		
		tabIsOpened = $(".nav-tabs").find("." + title ).attr('id');
					
		if (tabIsOpened)
			$("#" + tabIsOpened).trigger("click");
		else {
			$http.get(url)
	        .success(function (data) {
	        	
	        	var num_tabs = $("div#tabs ul li").length + 1;
	        	
	        	var sBody = {
	        		name : "tab_" + num_tabs,
	        		link : title,
	        		body : data
	        	};
	        	
	        	$scope.tabsShow.push(sBody);     	
	           
	        	$timeout(function(){
					$("#id_tab_" + num_tabs).trigger("click");				
				},300);
	            
	        })
	        .error(function (data, status, headers, config) {
	        	alert(errorThrown);
	        });
		}
	}
	
	$scope.removeTab = function (index) {
		if (index == 0) return;
		
		$scope.tabsShow.splice(index, 1);
		
		$timeout(function(){
			$("#id_tab_" + index).trigger("click");				
		},300);
	};
		
	$scope.showUserMenu = false;
	$scope.showMenuUser = function(show, link) {
		$scope.showUserMenu = show;	
		$scope.LoadAjaxContent(link);
	}
	
    $scope.forms = {
     shopcart : {
     	errorMessage : '',
     },
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
     },     
     signup : {
        email : '',
        errorMessage: '',
     },
     changePassword : {
        pass1 : '',
        pass2 : '',
        errorMessage: '',
        successMessage: '',
     },
     contact : {
        name : '',
        website : '',
        email : '',
        message : '',
        hasFieldError : function(field) {
            if($scope.forms.contact.errorMessage == null) {
                return '';
            }

            for(var i = 0; i < $scope.forms.contact.errorMessage.length; i++) {
                var m = $scope.forms.contact.errorMessage[i];
                if(m.substring(m.indexOf(field)) == field + '.error')
                    return 'has-error';
            }
            return '';
        },
     },
    };
    
    $scope.errorMessage = '';

    $scope.signin = function() {
    	
    	$scope.forms.signin.login = "joaomotajunior@gmail.com"
    	$scope.forms.signin.credential = "123456";
    		
        angular.element('html').addClass('loading');
        angular.element('#signin-error').css('display','none');
        angular.element('#signin-user').removeClass('has-error');
        angular.element('#signin-pass').removeClass('has-error');

        $scope.result = new Signin({
            login : $scope.forms.signin.login,
            credential : $scope.forms.signin.credential,
        });
        $scope.result.$save({_csrf : angular.element('#_csrf').val()},function(){
            if($scope.result.resultType == 'SUCCESS') {
                window.location.href = '/';
            } else {
            	if($scope.result.errorMessages != null && $scope.result.errorMessages.length > 0) {
            		$scope.errorMessage = $scope.result.errorMessages[0].message;
            		angular.element('html').removeClass('loading');
            		
            		var errorMessage = $scope.errorMessage.split('#')[0];
            		var userId = $scope.errorMessage.split('#')[1];
            		var username = $scope.errorMessage.split('#')[2];
            		
            		if(errorMessage == 'password.expired') {
            			angular.element('#signin-alert').css('display','block');
            			$scope.forms.signexpired.userId = userId;
            			$scope.forms.signexpired.username = username;
            		} else {
            			angular.element('#signin-error').css('display','block');
            			angular.element('#signin-user').addClass('has-error');
            			angular.element('#signin-pass').addClass('has-error');
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

    $scope.verificaUsuario = function() {
    	angular.element('body').addClass('loading');
        var success = true;
        var deferred = $q.defer();

        $http.post('/api/perfil/verifica-usario?_csrf='+angular.element('#_csrf').val(),{email:$scope.forms.signup.email})
        .success(function(data) {
        	angular.element('body').removeClass('loading');
            done = true;
            if (data.errorMessages.length > 0) {
                deferred.resolve(false);
                $scope.forms.signup.errorMessage = data.errorMessages[0].message;
            } else {
                deferred.resolve(true);
            }
        })
		.error(function(data, status) {
			if (status >= 400 && status <= 505 ) {
				angular.element('body').removeClass('loading');
				angular.element('.session-expired').modal('show');
				$timeout(function(){
					window.location.href='/';
				},2500);
			}
		});

        return deferred.promise;
    };

	$scope.clearForgetPasswordForm = function () {
		if ($scope.forms.forgetPassword == null) {
			return;
		}
		$scope.forms.forgetPassword.email1 = '';
		$scope.forms.forgetPassword.email2 = '';
		$scope.forms.forgetPassword.errorMessage = null;
		$scope.forms.forgetPassword.successMessage = null;
	};

    $scope.recuperarSenha = function() {
    	angular.element('body').addClass('loading');
    	$scope.forms.forgetPassword.errorMessage = null;
        $scope.forms.forgetPassword.successMessage = null;
        $http.post('/api/perfil/recover-password?_csrf='+angular.element('#_csrf').val(),
			{email1:$scope.forms.forgetPassword.email1, email2:$scope.forms.forgetPassword.email2}).success(function(data) {
			angular.element('body').removeClass('loading');
			if (data.errorMessages.length > 0) {
				$scope.forms.forgetPassword.errorMessage = data.errorMessages[0].message;
			}
			if (data.successMessages.length > 0) {
				$scope.forms.forgetPassword.successMessage = data.successMessages[0].message;
				$timeout(function(){ $('.bs-forget-password-modal').modal('hide'); }, 2000);
			}
		})
		.error(function(data, status) {
			if (status >= 400 && status <= 505 ) {
				angular.element('.bs-forget-password-modal').modal('hide');
				angular.element('body').removeClass('loading');
				angular.element('.session-expired').modal('show');
				$timeout(function(){
					window.location.href='/';
				},2500);
			}
		});
    };

    $scope.alterarSenha = function() {
    	angular.element('body').addClass('loading');
    	
    	$scope.forms.changePassword.errorMessage = null;
    	$scope.forms.changePassword.successMessage = null;
    	
        $http.post('/security/api/change-password?_csrf='+angular.element('#_csrf').val(),
            {pass1:$scope.forms.changePassword.pass1, pass2:$scope.forms.changePassword.pass2}).success(function(data) {
            angular.element('body').removeClass('loading');
            if (data.errorMessages.length > 0) {
                $scope.forms.changePassword.errorMessage = data.errorMessages[0].message;
            }
            if (data.successMessages.length > 0) {
                $scope.forms.changePassword.successMessage = data.successMessages[0].message;
                $timeout(function(){ $('#modalSenha').modal('hide'); }, 1500);
            }
        })
		.error(function(data, status) {
			if (status >= 400 && status <= 505 ) {
				angular.element('#modalSenha').modal('hide');
				angular.element('body').removeClass('loading');
				angular.element('.session-expired').modal('show');
				$timeout(function(){
					window.location.href='/';
				},2500);
			}
		});
    };
    
    $scope.passwordErrorMessage = '';
    $scope.passwordSuccessMessage = '';
    
    $scope.changePasswordExpired = function() {
    	
        angular.element('html').addClass('loading');
        angular.element('#password-success').css('display','none');
        angular.element('#password-error').css('display','none');

        $scope.password = new Password($scope.forms.signexpired);
        $scope.password.$change({_csrf : angular.element('#_csrf').val()},function(){
        	angular.element('html').removeClass('loading');
            if($scope.password.resultType == 'SUCCESS') {
            	$scope.passwordSuccessMessage = $scope.password.messages[0].message;
            	angular.element('#password-success').css('display','block');
            	$scope.forms.signexpired = [];
            	$timeout(function(){
					window.location.href='/';
				},5000);
            } else {
            	if($scope.password.errorMessages != null && $scope.password.errorMessages.length > 0) {
            		$scope.passwordErrorMessage = $scope.password.errorMessages[0].message;
            		angular.element('html').removeClass('loading');
        			angular.element('#password-error').css('display','block');
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
    	
    }

    $scope.alterarSenhaAlteracaoIndevida = function(token) {
    	angular.element('body').addClass('loading');
        $scope.forms.changePassword.errorMessage = null;
        $scope.forms.changePassword.successMessage = null;
        $http.post('/api/forgot-password/' + token + '?_csrf='+angular.element('#_csrf').val(),
            {pass1:$scope.forms.changePassword.pass1, pass2:$scope.forms.changePassword.pass2}).success(function(data) {
            angular.element('body').removeClass('loading');
            if (data.errorMessages.length > 0) {
                $scope.forms.changePassword.errorMessage = data.errorMessages[0].message;
            }
            if (data.successMessages.length > 0) {
                $scope.forms.changePassword.successMessage = data.successMessages[0].message;
                $timeout(function(){
                    window.location.href='/';
                },1500);
            }
        })
		.error(function(data, status) {
			if (status >= 400 && status <= 505 ) {
				angular.element('#modalSenha').modal('hide');
				angular.element('body').removeClass('loading');
				angular.element('.session-expired').modal('show');
				$timeout(function(){
					window.location.href='/';
				},2500);
			}
		});
    };

    $scope.buscar = function(sumario) {
    	var q = sumario.tags[0].descricao;
    	window.location.href='/result-search.html?q='+q;
    };

    $scope.startContactForm = function() {
        $scope.forms.contact.errorMessage = null;
        $scope.forms.contact.successMessage = null;
        $scope.forms.contact.waitMessage = null;
    };

    $scope.sendContact = function() {
        angular.element('body').addClass('loading');

        $scope.forms.contact.waitMessage = null;
        $scope.forms.contact.errorMessage = null;
        $scope.forms.contact.successMessage = null;

        var _csrf = angular.element('#_csrf').val();

        $scope.result = new Contact({
            name : $scope.forms.contact.name,
            website : $scope.forms.contact.website,
            email : $scope.forms.contact.email,
            message : $scope.forms.contact.message,
        });

        $scope.result.$send({_csrf : _csrf}, function(){
            angular.element('body').removeClass('loading');

            if($scope.result.value == false) {
                $scope.forms.contact.errorMessage = $scope.result.message.split(',');
                console.log($scope.forms.contact.errorMessage);
                return;
            }

            $scope.forms.contact.waitMessage = 'forms.contact.waitMessage';

            if($scope.result.value == true) {
                $scope.forms.contact.waitMessage = null;
                return;
            }

            var timer = $interval(function() {
                $scope.result.$status(function(){
                    $scope.forms.contact.waitMessage = $scope.result.message;
                    if($scope.result.value == true || $scope.result.value == false) {
                        $interval.cancel(timer);
                    }
                    if($scope.result.value == false) {
                        $scope.forms.contact.waitMessage = null;
                        $scope.forms.contact.errorMessage = [];
                        $scope.forms.contact.errorMessage[0] = $scope.result.code + ' - ' + $scope.result.message;
                    }
                    if($scope.result.value == true) {
                        $scope.forms.contact.waitMessage = null;
                        $scope.forms.contact.successMessage = $scope.result.message;
                    }
                });
            }, 1000);
        }, function(data) {
			if (data.status >= 400 && data.status <= 505 ) {
				angular.element('body').removeClass('loading');
				angular.element('.session-expired').modal('show');
				$timeout(function(){
					window.location.href='/';
				},2500);
			}
        });
    };

   $scope.LoadAjaxContent('dashboard.html', 'Dashboard')
   
});