app.controller('UserController', function ($scope, $timeout, $filter, UserService, FilialService) {
	
	/** VARIAVEIS - MODAL */
	$scope.user = {
		id: undefined,
		cpf: undefined,
		cnpj: undefined,
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
            	$scope.users.listUser[$scope.index].cnpj = $scope.user.cnpj;
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
        	if($scope.user.cnpj) {
        		$scope.user.cnpj = $scope.user.cnpj.replace(/[\.\/-]/g, '');
        	} else {
        		$scope.user.cnpj = $scope.userLogado.cnpjRaiz.replace(/[\.\/-]/g, '');
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
    	if($scope.user.cnpj) {
    		$scope.user.cnpj = $scope.user.cnpj.replace(/[\.\/-]/g, '');
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
    	if($scope.user.cnpj) {
    		$scope.user.cnpj = $scope.user.cnpj.replace(/[\.\/-]/g, '');
    	}
    	$scope.pesquisa = new UserService.pesquisa($scope.user);
        $scope.pesquisa.$users({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	$scope.user.cpf = $scope.cpfFormatter($scope.user.cpf);
    		$scope.user.cnpj = $scope.cnpjFormatter($scope.user.cnpj);
        	setMessagePesquisa($scope.pesquisa);
        	if($scope.pesquisa.errorMessages.length == 0) {
        		filterUsers($scope.pesquisa.listUser);
        	}
        	if($scope.users.listUser.length > 0) {
        		$timeout(function(){
        			$('#dataTables-users').dataTable().fnSort([0, 'asc']);
        		},5);	
        	} else {
        		$timeout(function(){
        			$('#dataTables-users').dataTable();
        		},5);
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
    	$scope.user.id = $scope.users.listUser[index].id;
    	$scope.user.cpf = $scope.cpfFormatter($scope.users.listUser[index].cpf);
    	$scope.user.cnpj = $scope.cnpjFormatter($scope.users.listUser[index].cnpj);
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
		if(tipo == 2) {return 'Contratante';}
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
	
	$scope.cnpjFormatter = function(cnpj) {
		if(!cnpj) {
			return "";
		}
		if(cnpj.length == 8) { //CNPJ Raiz
			return cnpj.replace(/(\d{2})(\d{3})(\d{3})/g,"\$1.\$2.\$3");	
		} else {
			return cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g,"\$1.\$2.\$3\/$4\-\$5");
		}
		
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
		$scope.user.cnpj = undefined;
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
		if($scope.userLogado.isAdmin) {
			$scope.user.filialId = undefined;
		}
   	}
	
	function filterUsers(listUsers) {
		if(!listUsers) {
			return false;
		}
   		listUsers.forEach(function(value, index, ar) {
   			if($scope.userLogado.isAdmin) {
   				$scope.users.listUser = listUsers;
   				return false;
   			} else {
   				if(value.role != 1 && value.role != 2) { // Remove Admins e Contractors
   					$scope.users.listUser.push(value);
   				}
   			}
   		});
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
    
    $scope.pesquisaUserByFilial = function() {
		$scope.user.status = 'ACTIVE';
		$('#dataTables-users').dataTable().fnDestroy();
		$scope.clearUserModal();
		clearMessagePesquisa();
		if($scope.user.filialId) {
	        $('html').addClass('loading');
	    	$scope.users.listUser = [];
	    	$scope.pesquisaByFilial = new UserService.pesquisaByFilial($scope.user);
	        $scope.pesquisaByFilial.$users({_csrf : angular.element('#_csrf').val()}, function(){
	        	$('html').removeClass('loading');
	        	if($scope.pesquisaByFilial.errorMessages.length == 0) {
	        		filterUsers($scope.pesquisaByFilial.listUser);
	        		$timeout(function(){
	        			$('#dataTables-users').dataTable().fnSort([0, 'asc']);
	        		},5);
	        	}
	        });
		} else { //Selecione...
			$scope.users.listUser = [];
			$timeout(function(){
				$('#dataTables-users').dataTable();
			},5)
		}
	}
    
    $scope.getUsuariosdaFilial = function() {
		$scope.noData = false;
		$scope.usuariosFilial = [];
		$scope.logins = [];
		$scope.modelo = {filialId: $scope.user.filialId};
    	$scope.pesquisaUser = new UserService.pesquisaUser($scope.modelo);
        $scope.pesquisaUser.$naoRelacionados({_csrf : angular.element('#_csrf').val()}, function(){
    		$scope.usuariosFilial = $scope.pesquisaUser.listUser;
    		if($scope.usuariosFilial.length == 0) {
    			$scope.noData = true;
    		} 
        });
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
    
    $scope.incluirRelacionamento = function() {
    	clearRelacionamento();
    	$scope.clearLogins = [];
    	$scope.logins.forEach(function(value, index, ar) {
    		if(value != undefined && value != 'null') {
    			$scope.clearLogins.push(value);
    		}
    	});
    	$scope.logins = $scope.clearLogins;
    	$scope.relacionamento.listaLoginUsers = $scope.logins;
    	$scope.relacionamento.filialId = $scope.user.filialId;
    	$scope.relacionaByFilial = new FilialService.relacionaByFilial($scope.relacionamento);
        $scope.relacionaByFilial.$user({_csrf : angular.element('#_csrf').val()}, function(){
        	$scope.fecharModal();
        	if($scope.relacionaByFilial.errorMessages && $scope.relacionaByFilial.errorMessages.length > 0) {
        		setMessagePesquisa($scope.relacionaByFilial);
        	}
    		$scope.user.status = 'ACTIVE';
    		$('#dataTables-users').dataTable().fnDestroy();	
    		$('html').addClass('loading');
	    	$scope.users.listUser = [];
	    	$scope.pesquisaByFilial = new UserService.pesquisaByFilial($scope.user);
	        $scope.pesquisaByFilial.$users({_csrf : angular.element('#_csrf').val()}, function(){
	        	$('html').removeClass('loading');
	        	if($scope.pesquisaByFilial.errorMessages.length == 0) {
	        		filterUsers($scope.pesquisaByFilial.listUser);
	        		$timeout(function(){
	        			$('#dataTables-users').dataTable().fnSort([0, 'asc']);
	        		},5);
	        	}
	        });
        });
    }
    
    $scope.excluirRelacionamento = function() {
    	clearRelacionamento();
    	$scope.relacionamento.listaLoginUsers = $scope.logins;
    	$scope.relacionamento.filialId = $scope.user.filialId;
    	$scope.desrelacionaByFilial = new FilialService.desrelacionaByFilial($scope.relacionamento);
        $scope.desrelacionaByFilial.$user({_csrf : angular.element('#_csrf').val()}, function(){
        	$scope.pesquisaUserByFilial();
        	$scope.fecharModal();
        	if($scope.desrelacionaByFilial.errorMessages && $scope.desrelacionaByFilial.errorMessages.length > 0) {
        		setMessagePesquisa($scope.desrelacionaByFilial);
        	}
        });
    }
    
	function clearRelacionamento() {
   		$scope.relacionamento.filialId = '';
   		$scope.relacionamento.userId = '';
   		$scope.relacionamento.listaCnpjFiliais = [];
   		$scope.relacionamento.listaLoginUsers = [];  			
   	}
	
	$scope.getFiliaisContratante = function() {
   		$scope.filiaisContratante = [];
		$scope.modelo = {id: '',cnpj: '',tradeName: '',unitName: '',cep: '',address: '',number: '',complement: '',district: '',
						 city: '',state: '',fone1: '',fone2: '',fax: '',email: '',status: undefined,userId: ''};
    	$scope.contrato = new FilialService.pesquisa($scope.modelo);
        $scope.contrato.$filiais({_csrf : angular.element('#_csrf').val()}, function(){
    		$scope.filiaisContratante = $scope.contrato.listFilial;
    		if($scope.filiaisContratante.length == 0) {
    			$scope.noData = true;
    		} 
        });   		
   	}
    
    /** FUNCOES DE INICIALIZACAO */
    $timeout(function(){
		$('#dataTables-users').dataTable();
	},5)
	
    $scope.clearUserModal();
    $scope.getFiliaisContratante();
   	
});