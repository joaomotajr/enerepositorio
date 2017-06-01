app.controller('UserController', function ($scope, $timeout, $filter, UserService, CompanyService) {

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
   			}
   			
   			arrayUser[0] = $scope.userLogado;
   			
   		} else {
   			$scope.userLogado = arrayUser[0];
   		}
   		
	}
	
	/** VARIAVEIS - MODAL */
	$scope.user = {
		id: undefined,
		cpf: undefined,		
    	displayName: undefined,
    	nickname: undefined,
    	fone: undefined,
    	cell: undefined,
    	email: undefined,
    	role: undefined,
    	login: undefined,
    	hash: undefined,
    	status: undefined,
    	createDate: undefined,
    	filialId: undefined
	};
	$scope.users = {
		listUser : []
	};
	$scope.messagePesquisa = ""
	$scope.messagePesquisaError = '';
	$scope.messageCadastro = '';
	$scope.messageCadastroError = '';
	$scope.isCad = true;
	$scope.isEdit = false;	
	$scope.index = '';
	
	/** BOTAO NOVO USUARIO */
	$scope.novoUsuario = function() {
		$scope.isCad = true;
		$scope.isEdit = false;
		$scope.clearUserModal();
		$scope.clearMessageCadastro();
	}
	
    $scope.saveUser = function() {
    	$('html').addClass('loading');
    	
    	/** EDICAO DE USUARIO */
    	if($scope.user.id) {
    		if($scope.user.cpf) {
        		$scope.user.cpf = $scope.user.cpf.replace(/[\.-]/g, '');
        	}
        	if($scope.user.cnpj) {
        		$scope.user.cnpj = $scope.user.cnpj.replace(/[\.\/-]/g, '');
        	}
        	$scope.edicao = new UserService.edicao($scope.user);
            $scope.edicao.$user({_csrf : angular.element('#_csrf').val()}, function(){
            	$('html').removeClass('loading');
            	
            	// Atualiza o listUser
            	$scope.users.listUser[$scope.index].id = $scope.user.id;
            	$scope.users.listUser[$scope.index].cpf = $scope.user.cpf;            	
            	$scope.users.listUser[$scope.index].displayName = $scope.user.displayName;
            	$scope.users.listUser[$scope.index].nickname = $scope.user.nickname;
            	$scope.users.listUser[$scope.index].fone = $scope.user.fone;
            	$scope.users.listUser[$scope.index].cell = $scope.user.cell;
            	$scope.users.listUser[$scope.index].email = $scope.user.email;
            	$scope.users.listUser[$scope.index].role = $scope.user.role;
            	$scope.users.listUser[$scope.index].login = $scope.user.login;
            	$scope.users.listUser[$scope.index].hash = $scope.user.hash;
            	$scope.users.listUser[$scope.index].status = $scope.user.status;
            	$scope.users.listUser[$scope.index].createDate = $scope.user.createDate;
            	$scope.users.listUser[$scope.index].filialId = $scope.user.filialId;
            	
            	// variavel para atualizar o dataTable
            	var data = [$scope.cpfFormatter($scope.user.cpf),
            	            $scope.user.displayName,
            	            $scope.user.login,
            	            $scope.getTipoUsuario($scope.user.role),
            	            $scope.getStatus($scope.user.status),
            	            $filter('date')($scope.user.createDate, 'dd/MM/yyyy')];
            	
            	$('#dataTables-users').DataTable().row($scope.index).data(data).draw();
            	$scope.fecharModal();
            	setMessagePesquisa($scope.edicao);
            });   	
        
        /** INCLUSAO DE USUARIO */
    	} else {
    		$('#dataTables-users').dataTable().fnDestroy();
    		
    		$scope.users.listUser = [];
        	$scope.user.status = 'ACTIVE';
        	
        	if($scope.user.cpf) {
        		$scope.user.cpf = $scope.user.cpf.replace(/[\.-]/g, '');
        	}
        	
        	$scope.inclusao = new UserService.inclusao($scope.user);
            
        	$scope.inclusao.$user({_csrf : angular.element('#_csrf').val()}, function(){
            	$('html').removeClass('loading');
            	$scope.fecharModal();
            	setMessagePesquisa($scope.inclusao);
            });
            
        	$timeout(function(){
    			$('#dataTables-users').dataTable();
    		},5);
    	}
    }
    
	/** EXCLUSAO DE USUARIO */
    $scope.excluirUsuario = function() {
        $('html').addClass('loading');
        
        $scope.clearMessageCadastro();
        
        if($scope.user.cpf) {
    		$scope.user.cpf = $scope.user.cpf.replace(/[\.-]/g, '');
    	}
    	
    	$scope.remocao = new UserService.remocao($scope.user);
        $scope.remocao.$user({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	setMessageCadastro($scope.remocao);
        	if($scope.remocao.errorMessages.length == 0) {
	        	$scope.users.listUser.splice($scope.index, 1);
	        	$('#dataTables-users').DataTable().row($scope.index).remove().draw();
	        	$scope.fecharModal();
        	} else {
        		$scope.fecharModalById('modalExcluirUser');
        	}
        });    	
    }
	
    /** PESQUISA DE USUARIO */
	$scope.pesquisaUser = function() {
    	$('#dataTables-users').dataTable().fnDestroy();
        $('html').addClass('loading');
        
        clearMessagePesquisa();
    	
        $scope.users.listUser = [];
    	
    	if($scope.user.cpf) {
    		$scope.user.cpf = $scope.user.cpf.replace(/[\.-]/g, '');
    	}    	
    	
    	$scope.pesquisa = new UserService.pesquisa($scope.user);
        $scope.pesquisa.$users({_csrf : angular.element('#_csrf').val()}, function(){
        	
        	$('html').removeClass('loading');

        	$scope.user.cpf = $scope.cpfFormatter($scope.user.cpf);
    		
        	setMessagePesquisa($scope.pesquisa);
        	
        	if($scope.pesquisa != null && $scope.pesquisa.listUser != null)
        		$scope.users.listUser = $scope.pesquisa.listUser;

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
	
	/** ATIVACAO DE USUARIO */
	$scope.ativarUser = function() {
		$scope.ativaInativaUser('ACTIVE');		
	}
	
	/** INATIVACAO DE USUARIO */
	$scope.inativarUser = function() {
		$scope.ativaInativaUser('INACTIVE');
	}
    
	$scope.ativaInativaUser = function(status) {
		$scope.clearMessageCadastro();
		$scope.clearUserModal();
		$scope.user = $.extend(true, {}, $scope.users.listUser[$scope.index]);
		$scope.user.status = status;
    	$scope.ativaInativa = new UserService.edicao($scope.user);
        $scope.ativaInativa.$user({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	$scope.users.listUser[$scope.index] = $.extend(true, {}, $scope.user);
        	
        	var data = [$scope.cpfFormatter($scope.users.listUser[$scope.index].cpf),
        	            $scope.users.listUser[$scope.index].displayName,
        	            $scope.users.listUser[$scope.index].login,
        	            $scope.getTipoUsuario($scope.users.listUser[$scope.index].role),
        	            $scope.getStatus($scope.users.listUser[$scope.index].status),
        	            $filter('date')($scope.users.listUser[$scope.index].createDate, 'dd/MM/yyyy')];
        	
        	$('#dataTables-users').DataTable().row($scope.index).data(data).draw();
        	
        	$scope.fecharModal();
        });	
	}
	
	/** FUNCOES AUXILIARES */
    $scope.detalheUser = function(index) {
    	
    	$scope.clearMessageCadastro();
    	
    	$scope.index = index;
    	$scope.user.company = $scope.users.listUser[index].companyDto;
    	$scope.user.id = $scope.users.listUser[index].id;
    	$scope.user.cpf = $scope.cpfFormatter($scope.users.listUser[index].cpf);
		$scope.user.displayName = $scope.users.listUser[index].displayName;
		$scope.user.nickname = $scope.users.listUser[index].nickname;
		$scope.user.fone = $scope.users.listUser[index].fone;
		$scope.user.cell = $scope.users.listUser[index].cell;
		$scope.user.email = $scope.users.listUser[index].email;
		$scope.user.role = $scope.users.listUser[index].role;
		$scope.user.login = $scope.users.listUser[index].login;
		$scope.user.hash = $scope.users.listUser[index].hash;
		$scope.user.status = $scope.users.listUser[index].status;
		$scope.user.createDate = $scope.users.listUser[index].createDate;
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
	
	$scope.fecharModal = function() {
    	$(".modal").modal("hide");
    	$scope.clearUserModal();
    }
	
	$scope.fecharModalById = function(id) {
    	$("#"+id).modal("hide");
    }
	
	$scope.clearUserModal = function() {
		$scope.user.id = undefined;
		$scope.user.cpf = undefined;
		$scope.user.displayName = undefined;
		$scope.user.nickname = undefined;
		$scope.user.fone = undefined;
		$scope.user.cell = undefined;
		$scope.user.email = undefined;
		$scope.user.role = undefined;
		$scope.user.login = undefined;
		$scope.user.hash = undefined;
		$scope.user.status = undefined;
		$scope.user.createDate = undefined;
		
   	}

	function clearMessagePesquisa() {
   		$scope.messagePesquisaError = "";
   	}
   	
    function setMessagePesquisa(param) {
 	   // mensagem de erro
 	   if(param.errorMessages && param.errorMessages.length > 0) {
 		   $scope.messagePesquisaError = param.errorMessages[0].message;
 		  $timeout(function () { $scope.messagePesquisaError = ""; }, 3000);
 		   return false;
 	   }	   
 	   // mensagem de sucesso
 	   if(param.messages && param.messages.length > 0) {
 	       $scope.messagePesquisa = param.messages[0].message;
 	       $timeout(function () { $scope.messagePesquisa = ""; }, 3000);
 	       return false;
 	   }
     }
    
    $scope.clearMessageCadastro = function() {
    	$scope.messageCadastro = "";
   		$scope.messageCadastroError = "";
   	}
    
    function setMessageCadastro(param) {
  	   // mensagem de erro
  	   if(param.errorMessages && param.errorMessages.length > 0) {
  		   $scope.messageCadastroError = param.errorMessages[0].message;
  		   $timeout(function () { $scope.messageCadastroError = ""; }, 3000);
  		   return false;
  	   }	   
  	   // mensagem de sucesso
  	   if(param.messages && param.messages.length > 0) {
  	       $scope.messageCadastro = param.messages[0].message;
  	       $timeout(function () { $scope.messageCadastro = ""; }, 3000);
  	       return false;
  	   }
      }
    
    /** ESPECIFICOS DE RELACIONAMENTO USUARIO X FILIAL */
    $scope.usuariosFilial = [];
    $scope.logins = [];
    $scope.filiaisContratante = [];
    $scope.relacionamento = {
		filialId: '',
		userId: '',
		listaCnpjFiliais: [],
		listaLoginUsers: []
	}   
   
    $scope.setLogin = function (event) {
    	$scope.logins = [];
		if(event.target != undefined) {
			$scope.logins[0] = event.target.value;
		} else {
			$scope.logins[0] = event;
			$scope.user.login = event;
		}
	}
    
    $scope.getCompanys = function() {
		 
		 $scope.resultCompanies = new CompanyService.listAllView();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companies = $scope.resultCompanies.list;
       });		 
	}
    
    $scope.validEmail = function ($event) {	    	

		 if (validateEmail( $scope.email )) {
			 $scope.emailValid = true;
		 }		 
		 else {
		    $scope.emailValid = false;
		 }		 
		 
	 }
    
    $scope.refresh = function() {
    	$scope.clearUserModal();
    	$scope.pesquisaUser();
    	$scope.getCompanys();
    }
    
    $scope.refresh();
    $scope.getUserLogado();
    
    angular.element('body').removeClass('loading');
   	
});