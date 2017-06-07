app.controller('UserPerfilController', function ($scope, $timeout, $filter, UserService) {

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
    	    	    		
		if($scope.user.cpf) {
    		$scope.user.cpf = $scope.user.cpf.replace(/[\.-]/g, '');
    	}
		
    	$scope.update = new UserService.update($scope.user);
        $scope.update.$user({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');        	     	            	            	            	
        });             
    }
    
    $scope.testeUser = function() {
    	alert($scope.userPerfil);
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
	        	nickname : $scope.userResult.t.nickName,
	        	fone : $scope.userResult.t.fone,
	        	cell : $scope.userResult.t.cell,
	        	email : $scope.userResult.t.email,
	        	login : $scope.userResult.t.login,
	        	image : $scope.userResult.t.image
        	};
        	
        	$timeout(function () {
        		if($scope.userPerfil.image == null) $scope.userPerfil.image = "/assets/img/cover.jpg";
        		
	            $('#modalPerfilUser').modal({ show: 'false' });                        
	        }, 800);
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
    	
		 $scope.resultLogin = new UserService.listByLogin();		 
		 $scope.resultLogin.$user({_csrf : angular.element('#_csrf').val(), 'login' : login}, function() { 			 
			 if($scope.resultLogin.resultType == "NO_DATA")
				 $scope.loginValid = true;
			 else
			 	$scope.loginValid = false;   
      });		 
	}       
   
    $scope.validEmail = function ($event) {	    	
		
		 if (validateEmail( $scope.user.email )) {
			 $scope.emailValid = true;
		 }
		 else if ( $scope.user.email == null || $scope.user.email == '') {
			 $scope.emailValid = true;
		 }
		 else {
		    $scope.emailValid = false;
		 }		 
	 }
	
	$scope.loadEvents = function() {
		
		$('#idInputImageUser').change( encodeImageFileAsURL( function(base64Img) {		    
		    $scope.user.image =  base64Img;
			$scope.$apply();		    
		}));		
		  
		 $scope.addPhoto = function() {
			 event.preventDefault();
			$('#idInputImageUser').trigger('click');
		 }		
	}
	
	$timeout(function () {                    
		 $scope.loadEvents();
	 }, 300);
   	
});