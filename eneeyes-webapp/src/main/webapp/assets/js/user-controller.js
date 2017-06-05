app.controller('UserController', function ($scope, $timeout, $filter, UserService, CompanyService, RoleService) {

	var arrayUser = [];
	
	$scope.isLock = true;
    $scope.inativeUserInfo = 'INATIVO';    
	
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
	
	$scope.isCad = true;
	$scope.isEdit = false;	
	$scope.index = '';
	
	/** BOTAO NOVO USUARIO */
	$scope.novoUsuario = function() {
		$scope.loginValid = false;
		$scope.isCad = true;
		$scope.isEdit = false;
		$scope.clearUserModal();
		$scope.validEmail();
	}
	
    $scope.saveUser = function() {
    	$('html').addClass('loading');
    	
    	/** EDICAO DE USUARIO */
    	if($scope.user.id) {
    		
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
        
        /** INCLUSAO DE USUARIO */
    	} else {
    		    		 		
        	$scope.user.status = 'ACTIVE';
        	$scope.user.roles[0] = $scope.selectedRole; 
        	
        	if($scope.user.cpf) {
        		$scope.user.cpf = $scope.user.cpf.replace(/[\.-]/g, '');
        	}
        	
        	$scope.save = new UserService.save($scope.user);            
        	$scope.save.$user({_csrf : angular.element('#_csrf').val()}, function(){
        		
        		$scope.users.listUser.push($scope.save.t);
        		
            	$('html').removeClass('loading');            	         	            	
            });            
    	}
    }
    
	/** EXCLUSAO DE USUARIO */
    $scope.excluirUsuario = function(index) {
        $('html').addClass('loading');
        
        var userIndex = index;  
            	
    	$scope.deletar = new UserService.deletar($scope.user);
    	$scope.deletar.$user({_csrf : angular.element('#_csrf').val(), id : $scope.users.listUser[index].id}, function(){
        	$('html').removeClass('loading');
        	        	
	        $scope.users.listUser.splice(userIndex, 1);
        });    	
    	
    }
	
	$scope.pesquisaUsers = function() {
        $('html').addClass('loading');
                
        $scope.users.listUser = [];
    	
    	if($scope.user.cpf) {
    		$scope.user.cpf = $scope.user.cpf.replace(/[\.-]/g, '');
    	}    	
    	
    	$scope.pesquisa = new UserService.listAll();
        $scope.pesquisa.$users({_csrf : angular.element('#_csrf').val()}, function(){
        	
        	$('html').removeClass('loading');

        	$scope.user.cpf = $scope.cpfFormatter($scope.user.cpf);
        	
        	if($scope.pesquisa != null && $scope.pesquisa.list != null)
        		$scope.users.listUser = $scope.pesquisa.list;        
		});
    }
	
	/** FUNCOES AUXILIARES */
    $scope.detalheUser = function(index) {
    	
    	$scope.loginValid = true;
    	
    	$scope.index = index;
    	   	
    	$scope.user.id = $scope.users.listUser[index].id;
    	$scope.user.cpf = $scope.cpfFormatter($scope.users.listUser[index].cpf);
		$scope.user.displayName = $scope.users.listUser[index].displayName;
		$scope.user.nickname = $scope.users.listUser[index].nickname;
		$scope.user.fone = $scope.users.listUser[index].fone;
		$scope.user.cell = $scope.users.listUser[index].cell;
		$scope.user.email = $scope.users.listUser[index].email;
		$scope.selectedRole = $scope.users.listUser[index].roles[0];
		$scope.user.login = $scope.users.listUser[index].login;		
		$scope.user.hash = "##############################";
		$scope.user.status = $scope.users.listUser[index].status;
		$scope.user.createDate = $scope.users.listUser[index].createDate;
		
		if($scope.users.listUser[index].companyDto == null)
			$scope.userTipo = 2
		else {
			$scope.userTipo = 1;
			$scope.user.companyDto = $scope.users.listUser[index].companyDto;
		}
		
		$scope.validEmail();
				
		$scope.isCad = false;
    	$scope.isEdit = true;
    }
    
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
		
		$scope.selectedRole = undefined;
		$scope.user.id = undefined;
		$scope.user.cpf = '';
		$scope.user.displayName = '';
		$scope.user.nickname = '';
		$scope.user.fone = '';
		$scope.user.cell = '';
		$scope.user.email = '';
		$scope.user.roles = [1];		
		$scope.user.login = '';
		$scope.user.hash = '';
		$scope.user.status = '';
		$scope.user.createDate = '';
		$scope.user.companyDto = undefined;
		$scope.user.reset = false;
		$scope.userTipo = null;
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
    
    $scope.makeHash = function() {
		var pass = "";
		
    	for (var i= 0; i < 7; i++) {
    		pass += getRandomChar();
    	}
    	
    	$scope.user.hash = pass;
    	$scope.user.reset = true;
 	}
       
    
    $scope.getCompanys = function() {
		 
		 $scope.resultCompanies = new CompanyService.listAllView();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companies = $scope.resultCompanies.list;
			 $scope.companies.push({uid: 0 , company_id: 0});
			 
       });		 
	}
    
    $scope.getRoles = function() {
		 
		 $scope.resultRoles = new RoleService.listAll();		 
		 $scope.resultRoles.$roles({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.roles = $scope.resultRoles.list;
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
    
    getRandomChar = function() {
		
		var ascii = [[48, 57],[64,90],[97,122]];
		var i = Math.floor(Math.random()*ascii.length);
		
		return String.fromCharCode(Math.floor(Math.random()*(ascii[i][1]-ascii[i][0]))+ascii[i][0]);
	}
    
	$scope.inativeUser = function() {		
				 
	     $scope.user.status = $scope.user.status == 'INACTIVE'  ? 'ACTIVE' : 'INACTIVE';		
	}
		
    $scope.refresh = function() {
    	$scope.clearUserModal();
    	$scope.pesquisaUsers();
    	$scope.getCompanys();
    	$scope.getRoles();
    }
    
    $scope.refresh();
    $scope.getUserLogado();
      
    angular.element('body').removeClass('loading');
   	
});