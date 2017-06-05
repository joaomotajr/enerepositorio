app.controller('UserPerfilController', function ($scope, $timeout, $filter, UserService, CompanyService, RoleService) {

	var arrayUser = [];
	
	$scope.userLogado = {
		isAdmin : false,		
		isManager : false,
		isOperator : false,
		id : ''	
	}
	   	
   	$scope.getUserLogado = function() {
   		
   		if(arrayUser.length == 0){
   			
   			/** TIPO DO USUARIO */
   			var tipoUsuario = $('#tipoUsuario').val();
   			if(tipoUsuario) {
   				if(tipoUsuario == "administrator"){$scope.userLogado.isAdmin = true;}
   				if(tipoUsuario == "manager"){$scope.userLogado.isManager = true;}
   				if(tipoUsuario == "operator"){$scope.userLogado.isOperator = true;}
   			}
   			
   			/** ID DO USUARIO */
   			var idUsuario = $('#idUsuario').val();
   			if(idUsuario) {
   				$scope.userLogado.id = idUsuario;
   				$scope.pesquisaUser();
   			}
   			
   			arrayUser[0] = $scope.userLogado;
   			
   		} else {
   			$scope.userLogado = arrayUser[0];
   		}
   		
	}
	
	/** VARIAVEIS - MODAL */
	$scope.user = {
		id: undefined,
		cpf: '',		
    	displayName: '',
    	nickname: '',
    	fone: '',
    	cell: '',
    	email: '',
    	roles: [1],
    	login: '',
    	hash: '',
    	status: '',
    	createDate: '',
    	companyDto : undefined
	};
	$scope.users = {
		listUser : []
	};
	
    $scope.saveUser = function() {
    	$('html').addClass('loading');
    	
    	    		
		if($scope.user.cpf) {
    		$scope.user.cpf = $scope.user.cpf.replace(/[\.-]/g, '');
    	}
    	
		$scope.user.roles[0] = $scope.selectedRole;
		
    	$scope.update = new UserService.update($scope.user);
        $scope.update.$user({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	
        	// Atualiza o listUser
        	$scope.users.listUser[$scope.index].id = $scope.user.id;
        	$scope.users.listUser[$scope.index].cpf = $scope.user.cpf;            	
        	$scope.users.listUser[$scope.index].displayName = $scope.user.displayName;
        	$scope.users.listUser[$scope.index].nickname = $scope.user.nickname;
        	$scope.users.listUser[$scope.index].fone = $scope.user.fone;
        	$scope.users.listUser[$scope.index].cell = $scope.user.cell;
        	$scope.users.listUser[$scope.index].email = $scope.user.email;
        	$scope.users.listUser[$scope.index].roles[0] = $scope.user.roles[0];;
        	$scope.users.listUser[$scope.index].login = $scope.user.login;
        	$scope.users.listUser[$scope.index].hash = $scope.user.hash;
        	$scope.users.listUser[$scope.index].status = $scope.user.status;
        	$scope.users.listUser[$scope.index].companyDto = $scope.user.companyDto;   
        	            	            	            	
        });       
       
    }
	
	$scope.pesquisaUser = function() {
    	
        $('html').addClass('loading');
            	
    	if($scope.user.cpf) {
    		$scope.user.cpf = $scope.user.cpf.replace(/[\.-]/g, '');
    	}    	
    	
    	$scope.pesquisa = new UserService.listOne();
        $scope.pesquisa.$user({_csrf : angular.element('#_csrf').val(), id : $scope.userLogado.id }, function(){
                	
        	$('html').removeClass('loading');

        	$scope.user.cpf = $scope.cpfFormatter($scope.user.cpf);
        	
        	if($scope.pesquisa != null && $scope.pesquisa.list != null)
        		$scope.users.listUser = $scope.pesquisa.list;        
		});
    }
	
//	/** FUNCOES AUXILIARES */
//    $scope.detalheUser = function(index) {
//    	
//    	$scope.loginValid = true;
//    	
//    	$scope.index = index;
//    	   	
//    	$scope.user.id = $scope.users.listUser[index].id;
//    	$scope.user.cpf = $scope.cpfFormatter($scope.users.listUser[index].cpf);
//		$scope.user.displayName = $scope.users.listUser[index].displayName;
//		$scope.user.nickname = $scope.users.listUser[index].nickname;
//		$scope.user.fone = $scope.users.listUser[index].fone;
//		$scope.user.cell = $scope.users.listUser[index].cell;
//		$scope.user.email = $scope.users.listUser[index].email;
//		$scope.user.login = $scope.users.listUser[index].login;
//		$scope.user.createDate = $scope.users.listUser[index].createDate;
//		
//		$scope.validEmail();
//				
//		$scope.isCad = false;
//    	$scope.isEdit = true;
//    }
//    
	$scope.getTipoUsuario = function(tipo) {
		if(!tipo) {return null;}
		if(tipo == 1) {return 'Administrador';}
		if(tipo == 3) {return 'Gerente';}
		if(tipo == 4) {return 'Operador';}
	}
	
	$scope.getStatus = function(status) {
		if(!status) {return null;}
		if(status == 'ACTIVE') {return 'Ativo';}
		if(status == 'INACTIVE') {return 'Inativo';}
	}
	
	$scope.cpfFormatter = function(cpf) {
		if(!cpf) {
			return "";
		}
		return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g,"\$1.\$2.\$3-\$4");
	}
	
	$scope.clearUserModal = function() {		
				
		$scope.user.cpf = '';
		$scope.user.displayName = '';
		$scope.user.nickname = '';
		$scope.user.fone = '';
		$scope.user.cell = '';
		$scope.user.email = '';		
		$scope.user.createDate = '';
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
	
    $scope.getUserLogado();
    
    $scope.user.image = "/assets/img/cover.jpg";
    
    $timeout(function () {                    
		 $scope.loadEvents();
	 }, 500);
    
    angular.element('body').removeClass('loading');
   	
});