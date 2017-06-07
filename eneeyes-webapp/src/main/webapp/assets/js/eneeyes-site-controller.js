app.controller('SiteController', function ($scope, $http, $filter, $interval, $timeout, $q, Signin, $sce, Password) {

	$scope.$root.recolheLogo = true;
	
	$scope.$root.timer = [];
	$scope.$root.currentPage = "";
	$scope.$root.errorTimes = 0;
	$scope.$root.currentTabOpened = "";
	
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
		
		tabIsOpened = $(".nav-tabs").find("." + title ).attr('id');
					
		if (tabIsOpened) {		
			
			$timeout(function() {
				$("#" + tabIsOpened).trigger("click");				
			},300);
		}
		else {
		
			$scope.$root.currentTabOpened = tabIsOpened;
			angular.element('body').addClass('loading');		
			$http.get(url)
	        .success(function (data) {
	        	
	        	var num_tabs = $("div#tabs ul li").length + 1;
	        	
	        	var sBody = {
	        		name : "tab_" + num_tabs,
	        		link : title,
	        		body : data
	        	};
	        	
	        	$scope.tabsShow.push(sBody);     	
	           
	        	$timeout(function() {
					$("#id_tab_" + num_tabs).trigger("click");				
				},300);
	            
	        })
	        .error(function (data, status, headers, config) {
	        	angular.element('body').removeClass('loading');
	        	alert(config);
	        });
		}
	}
	
	$scope.removeTab = function (index) {
		if (index == 0) return;
		
		$timeout(function(){
			$("#id_tab_" + index).trigger("click");				
		},100);
		
		$scope.tabsShow.splice(index, 1);
		
		$timeout(function(){
			$("#id_tab_" + index).trigger("click");				
		},100);
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
     },     
     changePassword : {
        pass1 : '',
        pass2 : '',
        errorMessage: '',
        successMessage: '',
     }
    };
    
    $scope.errorMessage = '';

    $scope.signin = function() {
    	
//    	$scope.forms.signin.login = "joaomotajunior@gmail.com"
//    	$scope.forms.signin.credential = "123456";
    		
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

            			$scope.errorMessage = 'Usuário ou Senha Inválidos!!!';

            			angular.element('#signin-error').css('display','block');
            			angular.element('#signin-user').addClass('has-error');
            			angular.element('#signin-pass').addClass('has-error');            		
            			
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
		});    	
    	
    }

	angular.element('body').addClass('loading');
    $scope.LoadAjaxContent('dashboard.html', 'Dashboard');
   
});