function callPaginaAjax(url) {
	angular.element('body').scope().LoadAjaxContent(url);
}

if(document.getElementById('frm-inscricao') != null) {
	$('#btn-inscricao').click(function() {
        angular.element('#fld-inscricao').removeClass('has-error');
        angular.element('#inc-error').css('display', 'none');
        angular.element('body').scope().forms.signup.errorMessage = '';
        angular.element('body').scope().$apply();
    });

    $('#frm-inscricao').submit(function(e) {
        e.preventDefault();
        var reg = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
        var success = reg.test(this.email.value);
        if (!success) {
            angular.element('#fld-inscricao').addClass('has-error');
            angular.element('#inc-error').css('display', 'block');
        }
        if (success) {
            var t = this;
            var q = angular.element('body').scope().verificaUsuario();
            q.then(function(resolve){
                if (resolve) {
                    $(t).unbind('submit').submit();
                }
            }, function(){});
        }
    });

    $('.bs-forget-password-modal').on('show.bs.modal', function (e) {
    	var scope = angular.element('.bs-forget-password-modal').scope();
    	scope.clearForgetPasswordForm();
    	scope.$apply();
    });
}

app.controller('SiteController', function ($scope, $http, $filter, $interval, $timeout, $q, Signin) {
	$scope.$root.mensagensIntegracao = [];
	
	$scope.verificaMensagemIntegracao = function() {
		var tipoUsuario = $('#tipoUsuario').val();
		if(tipoUsuario != 'administrator') {
			var today = new Date();
			$scope.yesterday = new Date();
			$scope.yesterday.setDate(today.getDate()-1);
			$scope.yesterday.setHours(0);
			$scope.yesterday.setMinutes(0);
			$scope.yesterday.setSeconds(0);
			$scope.eventDates = {};
			$scope.datas = new DetalheVenda.historico();
			$scope.datas.$datasIntegracao({_csrf : angular.element('#_csrf').val()}, function(){
				if ($scope.datas.listaDatasIntegracao != undefined && $scope.datas.listaDatasIntegracao.length > 0) {
					for (i = 0; i < $scope.datas.listaDatasIntegracao.length; i++) {
						$scope.eventDates[new Date($scope.datas.listaDatasIntegracao[i])] = new Date($scope.datas.listaDatasIntegracao[i]);
					}
				}
				$timeout(function(){
					if (!$scope.eventDates[$scope.yesterday]) {
						if ($.inArray($scope.yesterday.toDateString(), $scope.$root.mensagensIntegracao) == -1) {
							$scope.showMessage('N\u00e3o foi efetuado a integra\u00e7\u00e3o do TEF para o dia ' + $filter('date')($scope.yesterday, 'dd/MM/yyyy') + 
							'.<br><a href="#" style="color:red" onclick="callPaginaAjax(\'integracao-tef.html\')">Clique aqui</a> para integrar o arquivo.');
							$scope.$root.mensagensIntegracao.push($scope.yesterday.toDateString());
						}
					} else {
						$timeout(function(){
							$scope.$root.mensagensIntegracao = {};
							$.gritter.removeAll();
						},2000);
					}
				},1500);
			});
		}
	};
		
	$scope.showMessage = function(msg) {
		$.gritter.add({
            title: '<center>Aten\u00e7\u00e3o!</center>',
            text: msg,
            image: '',
            sticky: true,
            time: '',
            class_name: 'my-sticky-class'
        });
	};
	
	$scope.LoadAjaxContent = function(url){
		$http.get(url)
        .success(function (data) {
        	$scope.ajaxcontent = data;
        })
        .error(function (data, status, headers, config) {
        	alert(errorThrown);
        });
	}
	
	$scope.$root.listaPeloGrafico = [];
	$scope.$root.listaContaCorrentePeloGrafico = [];
	$scope.$root.listaPagamentosPeloGrafico = [];
	$scope.$root.listaConciliacaoPeloGrafico = [];
	$scope.$root.listaInconsistenciaPeloGrafico = [];
	$scope.$root.inconsistenciaFiltro = {
			dataInicial: '',
			dataFinal : '',
			cnpj : '',
			razaoSocial : '',
			adquirente : '',
			tipoVenda : '',
			status : [],
			valor : '',
			nsu: ''
    };
	
	$scope.query = '';

	$scope.shop = {resultType:''};

	$scope.listaDeDesejo = {
		total: 0,
	};

	$scope.carrinho = {
		total: 0,
	};

	$scope.carrinhoDetalhe = {value:{sumarios:{}}};

		
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

	$scope.queryfocus = false;
    $scope.$watch('query', function(n, o) {
    	if (! $scope.queryfocus) {
    		return;
    	}

    	if (n != null && n.length < 3) {
    	    angular.element('#search-container').removeClass('show-autocomplete');
    	    angular.element('#search-container').addClass('hide-autocomplete');
    	    angular.element('#search-box').removeClass('clear-bottom');
    		return;
    	}

        angular.element('#search-container').removeClass('hide-autocomplete');
        angular.element('#search-container').addClass('show-autocomplete');
    	angular.element('#search-box').addClass('clear-bottom');

		$scope.autocomplete = new DocumentoBusca($scope.usuarioFiltro);
		$scope.autocomplete.$search({_csrf : angular.element('#_csrf').val(), q : $scope.query, totalItems: 5}, function() {}, function(data) {
			 if (data.status >= 400 && data.status <= 505 ) {
				 angular.element('body').removeClass('loading');
				 angular.element('.session-expired').modal('show');
				 $timeout(function(){
					window.location.href='/';
				 },2500);
			 }
		 });

		angular.element('body').click(function() {
			angular.element('#search-container').removeClass('show-autocomplete');
			angular.element('#search-container').addClass('hide-autocomplete');
			angular.element('#search-box').removeClass('clear-bottom');
		});
    });
    
    $scope.errorMessage = '';

    $scope.signin = function() {
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
    $scope.LoadAjaxContent("dashboard.html");
});