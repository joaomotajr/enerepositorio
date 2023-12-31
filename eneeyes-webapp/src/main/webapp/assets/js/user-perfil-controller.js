app.controller('UserPerfilController', function ($scope, $timeout, $filter, $window, UserService) {

	$scope.userLogado = {
		isAdmin : false,		
		isManager : false,
		isOperator : false,
		id : ''	
	}
	   	
   	$scope.getUserLogado = function() {
   			    
	    angular.element('body').removeClass('loading');		
	
		var tipoUsuario = $('#tipoUsuario').val();
		var idUsuario = $('#idUsuario').val();
		
		if(idUsuario) {
			$scope.userLogado.id = idUsuario;
			$scope.pesquisaUser();			
		}   		   		
	}
	
    $scope.saveUser = function() {
    	$('html').addClass('loading');
    	    	    		
		if($scope.userPerfil.cpf) {
    		$scope.userPerfil.cpf = $scope.userPerfil.cpf.replace(/[\.-]/g, '');
    	}
		
    	$scope.updateProfile = new UserService.updateProfile($scope.userPerfil);
        $scope.updateProfile.$user({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	
        	if($scope.updateProfile.t.reset)
        		$window.location.href = "/logout"; 
        	else {
        		$scope.$root.userImage = $scope.userResult.t.image;        	
        		$scope.selfClose();
        	}
        });             
    }
        
	$scope.pesquisaUser = function() {
    	
        $('html').addClass('loading');
    	
    	$scope.userResult = new UserService.listOne();
        $scope.userResult.$user({_csrf : angular.element('#_csrf').val(), id : $scope.userLogado.id }, function(){
                	
        	$('html').removeClass('loading');        	  
        	        	
        	$scope.userPerfil = {
        		id: $scope.userResult.t.id,
	        	cpf : $scope.cpfFormatter($scope.userResult.t.cpf),		
	        	displayName : $scope.userResult.t.displayName,
	        	nickname : $scope.userResult.t.nickname,
	        	fone : $scope.userResult.t.fone,
	        	cell : $scope.userResult.t.cell,
	        	email : $scope.userResult.t.email,
	        	login : $scope.userResult.t.login,
	        	image : $scope.userResult.t.image
        	};
        	
        	if($scope.userPerfil.image == null) $scope.userPerfil.image = "/assets/img/cover.jpg";
    	
        	$scope.validEmail();
    		$scope.loginValid = true;   		
	        
		});
    }

	
	$scope.cpfFormatter = function(cpf) {
		if(!cpf) {
			return "";
		}
		return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g,"\$1.\$2.\$3-\$4");
	}
 
    $scope.validLogin = function(login) {
		
    	if(login == "") return;
    	
    	var oldLogin = login;
    		
		$scope.resultLogin = new UserService.listByLogin();		 
		$scope.resultLogin.$user({_csrf : angular.element('#_csrf').val(), 'login' : login}, function() { 			 
			if($scope.resultLogin.resultType == "NO_DATA")
				$scope.loginValid = true;
			else if($scope.resultLogin.resultType == "SUCCESS" && $scope.resultLogin.t.login == oldLogin )
				$scope.loginValid = true;
			else
				$scope.loginValid = false;   
      });		 
	}
   
    $scope.validEmail = function ($event) {	    	
		
		 if (validateEmail( $scope.userPerfil.email )) {
			 $scope.emailValid = true;
		 }
		 else if ( $scope.userPerfil.email == null || $scope.userPerfil.email == '') {
			 $scope.emailValid = true;
		 }
		 else {
		    $scope.emailValid = false;
		 }		 
	 }
	
	$scope.loadEvents = function() {
		
		$('#idInputImageUser').change( encodeImageFileAsURL( function(base64Img) {		    
		    $scope.userPerfil.image =  base64Img;
			$scope.$apply();		    
		}));		
		  
		 $scope.addPhoto = function() {
			 event.preventDefault();
			$('#idInputImageUser').trigger('click');
		 }		
	}
	
	$scope.selfClose = function() {
		
		var index = $scope.tabsShow.findIndex( function(e) { return e.link ===  $scope.currentPage });    	
    	$scope.removeTab(index);		
	}
	
	$scope.getUserLogado();
	
	$timeout(function () {                    
		 $scope.loadEvents();
	 }, 300);
   	
});