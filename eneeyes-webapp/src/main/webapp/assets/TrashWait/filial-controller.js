app.controller('FilialController', function ($scope, $timeout, $filter, FilialService, UserService) {
	
	/** VARIAVEIS - MODAL */
	$scope.filial = {
		id: undefined,
		cnpj: undefined,
		tradeName: undefined,
		companyName: undefined,
		cep: undefined,
		address: undefined,
		number: undefined,
		complement: undefined,
		district: undefined,
		city: undefined,
		state: undefined,
		fone1: undefined,
		fone2: undefined,
		fax: undefined,
		email: undefined,
		status: undefined,
		userId: undefined
	};
	$scope.filiais = {
		listFiliais : []
	};	
	$scope.userSelected = {
		isManager: false,
		isOperator: false
	};
	$scope.cnpjRaiz = '';
	$scope.usuario = [];
	$scope.messageCadastro = '';
	$scope.messageCadastroError = '';
	$scope.messagePesquisaError = '';
	$scope.listContractors = [];
	$scope.listOtherUsers = [];
	$scope.operatorHasData = false;
	$scope.isEditFilial = false;
	$scope.noData = false;
	$scope.index = '';
	
    $scope.saveFilial = function() {
    	$('html').addClass('loading');
    	
    	/** EDICAO DE FILIAL */
    	if($scope.filial.id) { 
        	$scope.edicao = new FilialService.edicao($scope.filial);
            var cnpj = $scope.cnpjRaiz +'\/'+$scope.filial.cnpj;
            $scope.edicao.cnpj = cnpj.replace(/[\.\/-]/g, '');
        	$scope.edicao.cep = $scope.edicao.cep.replace(/[-]/g, '');
            $scope.edicao.$filial({_csrf : angular.element('#_csrf').val()}, function(){
            	$('html').removeClass('loading');
            	
            	// Atualiza o listFiliais
            	$scope.filiais.listFiliais[$scope.index].cnpj = cnpj.replace(/[\.\/-]/g, '');
            	$scope.filiais.listFiliais[$scope.index].tradeName = $scope.filial.tradeName;
            	$scope.filiais.listFiliais[$scope.index].companyName = $scope.filial.companyName;
            	$scope.filiais.listFiliais[$scope.index].cep = $scope.filial.cep;
            	$scope.filiais.listFiliais[$scope.index].address = $scope.filial.address;
            	$scope.filiais.listFiliais[$scope.index].number = $scope.filial.number;
            	$scope.filiais.listFiliais[$scope.index].complement = $scope.filial.complement;
            	$scope.filiais.listFiliais[$scope.index].district = $scope.filial.district;
            	$scope.filiais.listFiliais[$scope.index].city = $scope.filial.city;
            	$scope.filiais.listFiliais[$scope.index].state = $scope.filial.state;
            	$scope.filiais.listFiliais[$scope.index].fone1 = $scope.filial.fone1;
            	$scope.filiais.listFiliais[$scope.index].fone2 = $scope.filial.fone2;
            	$scope.filiais.listFiliais[$scope.index].fax = $scope.filial.fax;
            	$scope.filiais.listFiliais[$scope.index].email = $scope.filial.email;
            	$scope.filiais.listFiliais[$scope.index].status = $scope.filial.status;
            	$scope.filiais.listFiliais[$scope.index].userId = $scope.filial.userId;
            	
            	// variavel para atualizar o dataTable
            	var data = [cnpj,
            	            $scope.filial.tradeName,
            	            $scope.filial.city,
            	            $scope.filial.state,
            	            $scope.filial.fone1,
            	            $scope.getStatus($scope.filial.status),
            	            $scope.getUserLogin($scope.filial.userId)];
            	
            	$('#dataTables-filiais').DataTable().row($scope.index).data(data).draw();
            	$scope.fecharModal();
            	setMessagePesquisa($scope.edicao);
            });  
            
        /** INCLUSAO DE FILIAL */
    	} else {
        	$scope.filial.status = 'ACTIVE';
        	if($scope.user) {
        		$scope.filial.userId = $scope.user.id;
        		var cnpj = $scope.user.cnpj + '.' + $scope.filial.cnpj;
        		$scope.filial.cnpj = cnpj;
        	}
        	$scope.inclusao = new FilialService.inclusao($scope.filial);
        	if($scope.inclusao.cnpj){
        		$scope.inclusao.cnpj = $scope.inclusao.cnpj.replace(/[\.\/-]/g, '');
        	}
        	if($scope.inclusao.cep) {
        		$scope.inclusao.cep = $scope.inclusao.cep.replace(/[-]/g, '');
        	}
            $scope.inclusao.$filial({_csrf : angular.element('#_csrf').val()}, function(){
            	$('html').removeClass('loading');
            	setMessageCadastro($scope.inclusao);
            	if($scope.inclusao.errorMessages.length > 0) {
            		$scope.filial.cnpj = $scope.filial.cnpj.substring(11);
            	}
            });
    	}
    }
    
    /** EXCLUSAO DE FILIAL */
    $scope.excluirFilial = function() {
        $('html').addClass('loading');
        clearMessageCadastro();
        var cnpj = $scope.cnpjRaiz +'\/'+$scope.filial.cnpj;
        $scope.filial.cnpj = cnpj.replace(/[\.\/-]/g, '');
    	$scope.filial.cep = $scope.filial.cep.replace(/[-]/g, '');
    	$scope.remocao = new FilialService.remocao($scope.filial);
        $scope.remocao.$filial({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	$scope.filiais.listFiliais.splice($scope.index, 1);
        	$('#dataTables-filiais').DataTable().row($scope.index).remove().draw();
        	$scope.fecharModal();
        	setMessagePesquisa($scope.remocao);
        });    	
    }
    
    /** PESQUISA FILIAL */
    $scope.pesquisaFilial = function() {
    	$('#dataTables-filiais').dataTable().fnDestroy();
        $('html').addClass('loading');
        clearMessageCadastro();
        clearMessagePesquisa();
    	$scope.filiais.listFiliais = [];
    	$scope.pesquisa = new FilialService.pesquisa($scope.filial);
    	if($scope.pesquisa.cnpj) {
    		$scope.pesquisa.cnpj = $scope.pesquisa.cnpj.replace(/[\.\/-]/g, '');	
    	}
        $scope.pesquisa.$filiais({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	setMessagePesquisa($scope.pesquisa);
        	if($scope.pesquisa.errorMessages.length == 0) {
        		$scope.filiais.listFiliais = $scope.pesquisa.listFilial;
        		$timeout(function(){
        			$('#dataTables-filiais').dataTable().fnSort([0, 'asc']);
        		},5);
        	}
        });
    }
    
	/** ATIVACAO DE FILIAL */
	$scope.ativarFilial = function() {
		$scope.ativaInativaFilial('ACTIVE');		
	}
	
	/** INATIVACAO DE FILIAL */
	$scope.inativarFilial = function() {
		$scope.ativaInativaFilial('INACTIVE');
	}
    
	$scope.ativaInativaFilial = function(status) {
		clearFilialModal();
		$scope.filial = $.extend(true, {}, $scope.filiais.listFiliais[$scope.index]);
		$scope.filial.status = status;
    	$scope.ativaInativa = new FilialService.edicao($scope.filial);
        $scope.ativaInativa.$filial({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	$scope.filiais.listFiliais[$scope.index] = $.extend(true, {}, $scope.filial);
        	var data = [$scope.cnpjFormatter($scope.filial.cnpj),
        	            $scope.filial.tradeName,
        	            $scope.filial.city,
        	            $scope.filial.state,
        	            $scope.filial.fone1,
        	            $scope.getStatus($scope.filial.status),
        	            $scope.getUserLogin($scope.filial.userId)];
        	$('#dataTables-filiais').DataTable().row($scope.index).data(data).draw();
        	$scope.fecharModal();
        });	
	}    
    
    /** PESQUISA DE CEP */
	$scope.pesquisarCep = function() {
    	$scope.address = FilialService.pesquisaCep.get({'cep': $scope.filial.cep}, function() {
    		if(!$scope.address.erro) {
    			$scope.messageCadastro = '';
    			$scope.filial.address = $scope.address.logradouro;
        		$scope.filial.district = $scope.address.bairro;
        		$scope.filial.address = $scope.address.logradouro;
        		$scope.filial.city = $scope.address.localidade;
        		$scope.filial.state = $scope.address.uf;	
    		} else {
    			$timeout(function () { $scope.messageCadastro = "CEP n&atilde;o encontrado!"; }, 5);
    		}
    	});
	}
    
    /** FUNCOES AUXILIARES */
    $scope.detalheFilial = function(index) {
    	$scope.index = index;
    	clearMessageCadastro();
    	$scope.filial.id = $scope.filiais.listFiliais[$scope.index].id;
    	var cnpj = $scope.cnpjFormatter($scope.filiais.listFiliais[$scope.index].cnpj);
    	$scope.cnpjRaiz = cnpj.substring(0,10);
    	$scope.filial.cnpj = cnpj.substring(11);
        $scope.filial.tradeName = $scope.filiais.listFiliais[$scope.index].tradeName;
        $scope.filial.companyName = $scope.filiais.listFiliais[$scope.index].companyName;
        $scope.filial.cep = $scope.cepFormatter($scope.filiais.listFiliais[$scope.index].cep);
        $scope.filial.address = $scope.filiais.listFiliais[$scope.index].address;
        $scope.filial.number = $scope.filiais.listFiliais[$scope.index].number;
        $scope.filial.complement = $scope.filiais.listFiliais[$scope.index].complement;
        $scope.filial.district = $scope.filiais.listFiliais[$scope.index].district;
        $scope.filial.city = $scope.filiais.listFiliais[$scope.index].city;
        $scope.filial.state = $scope.filiais.listFiliais[$scope.index].state;
        $scope.filial.fone1 = $scope.filiais.listFiliais[$scope.index].fone1;
        $scope.filial.fone2 = $scope.filiais.listFiliais[$scope.index].fone2;
        $scope.filial.fax = $scope.filiais.listFiliais[$scope.index].fax;
        $scope.filial.email = $scope.filiais.listFiliais[$scope.index].email;
        $scope.filial.status = $scope.filiais.listFiliais[$scope.index].status;
        $scope.filial.userId = $scope.filiais.listFiliais[$scope.index].userId;
    	$scope.isEditFilial = true;
    }
    
	$scope.getStatus = function(status) {
		if(!status) {return null;}
		if(status == 'ACTIVE') {return 'Ativo';}
		if(status == 'INACTIVE') {return 'Inativo';}
	}
	
	$scope.cnpjFormatter = function(cnpj) {
		if(!cnpj) {
			return "";
		}
		return cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g,"\$1.\$2.\$3\/$4\-\$5");
	}
	
	$scope.cepFormatter = function(cep) {
		if(!cep) {
			return "";
		}
		return cep.replace(/(\d{5})(\d{3})/g,"\$1-\$2");
	}
	
	$scope.getUserLogin = function(id) {
		var login = "";
		$scope.usuario.forEach(function(user) {
		    if(user.id == id) {
		    	login = user.login;
		    	return false;
		    }
		});
		return login;
	} 
	
	$scope.fecharModal = function() {
    	$(".modal").modal("hide");
    	clearFilialModal();
    }
	
	$scope.fecharModalById = function(id) {
    	$("#"+id).modal("hide");
    }
	
	function clearFilialModal() {
   		$scope.filial.id = undefined;
		$scope.filial.cnpj = undefined;
		$scope.filial.tradeName = undefined;
		$scope.filial.companyName = undefined;
		$scope.filial.cep = undefined;
		$scope.filial.address = undefined;
		$scope.filial.number = undefined;
		$scope.filial.complement = undefined;
		$scope.filial.district = undefined;
		$scope.filial.city = undefined;
		$scope.filial.state = undefined;
		$scope.filial.fone1 = undefined;
		$scope.filial.fone2 = undefined;
		$scope.filial.fax = undefined;
		$scope.filial.email = undefined;
		$scope.filial.status = undefined;
		if($scope.userLogado.isAdmin) {
			$scope.filial.userId = undefined;
		}
		//$("#selectContractor option[value='']").attr("selected","selected");
   	}
	
	function clearMessageCadastro() {
   		$scope.messageCadastro = '';
    	$scope.messageCadastroError = '';
   	}
	
   	function clearMessagePesquisa() {
   		$scope.messagePesquisaError = "";
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
		   clearFilialModal();
	       $scope.messageCadastro = param.messages[0].message;
	       $timeout(function () { $scope.messageCadastro = ""; }, 3000);
	       return false;
	   }
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
		   clearFilialModal();
	       $scope.messagePesquisa = param.messages[0].message;
	       $timeout(function () { $scope.messagePesquisa = ""; }, 3000);
	       return false;
	   }
    }	
   
	$scope.setUsersByRole = function() {
		$scope.usuario = [];
    	$scope.pesquisa = new UserService.pesquisa($scope.usuario);
        $scope.pesquisa.$users({_csrf : angular.element('#_csrf').val()}, function(){
        	if($scope.pesquisa.errorMessages.length == 0) {
        		$scope.usuario = $scope.pesquisa.listUser;
        		$scope.listContractors = [];
        		$scope.usuario.forEach(setListContractors);
        		$scope.listOtherUsers = [];
        		$scope.usuario.forEach(setOtherUsers);
        		$scope.listOtherUsers.sort(function(a,b){
					if(a.login < b.login) return -1;
					if(a.login > b.login) return 1;
					if(a.login < b.login) return -1;
					if(a.login > b.login) return 1;
					return 0;
    			});
        	}
        });
	}
	
	//** Popula array de usuarios contratantes
	function setListContractors(value, index, ar) {
		if(value.role == 2) {
			$scope.listContractors.push(value);
		}
		var html = "<select id='selectContractor' class='form-control' ng-model='filial.userId' required='required'><option value=''>Selecione...</option>";
		if($scope.listContractors.length > 0) {
			for(i=0; i < $scope.listContractors.length; i++){
				html += "<option ng-repeat='contractor in listContractors' value='"+$scope.listContractors[i].id+"'>"+$scope.listContractors[i].login+"</option>";
			}
		}
		html += "</select>";
		$('#selectContractor').html(html);	
	}
	
	// Popula array de usuarios managers e operators
	function setOtherUsers(value, index, ar) {
		if(value.role == 3 || value.role == 4) {
			$scope.listOtherUsers.push(value);
		}
	}
	
	/** ESPECIFICOS DE RELACIONAMENTO USUARIO X FILIAL */
	$scope.cnpjs = [];
	$scope.filiaisContrato = [];
	$scope.relacionamento = {
		filialId: '',
		userId: '',
		listaCnpjFiliais: [],
		listaLoginUsers: []	
	}
	
	$scope.pesquisaFilialByUser = function() {
		$('#dataTables-filiais').dataTable().fnDestroy();
		clearFilialModal()
		clearMessageCadastro();
		if($scope.filial.userId) {
			$('html').addClass('loading');
			$scope.operatorHasData = false;
			$scope.filiais.listFiliais = [];
			$scope.listOtherUsers.forEach(setUserSelected);
	    	$scope.pesquisa = new FilialService.pesquisa($scope.filial);
	        $scope.pesquisa.$filiais({_csrf : angular.element('#_csrf').val()}, function(){
	        	if($scope.pesquisa.errorMessages.length == 0) {
	        		$('html').removeClass('loading');
	        		$scope.filiais.listFiliais = $scope.pesquisa.listFilial;
	        		
	        		//bloqueia o botao para relacionar filiais na tela de relacionamentos por usuario, 
	        		//quando o usuario eh operador e ja tem filial relacionada
	        		if($scope.userSelected.isOperator && $scope.pesquisa.listFilial != null && $scope.pesquisa.listFilial.length > 0) {
		    			$scope.operatorHasData = true;
		    		}
	        		
	        		$timeout(function(){
	        			$('#dataTables-filiais').dataTable().fnSort([0, 'asc']);
	        		},5);
	        	}
	        });
		} else { //Selecione...
			$scope.filiais.listFiliais = [];
			$timeout(function(){
				$('#dataTables-filiais').dataTable();
			},5)
		}
	}
	
	// Define o tipo do usuario selecionado no combo da tela de relacionamentos
	function setUserSelected(value, index, ar) {
		if(value.id == $scope.filial.userId) {
			if(value.role == 3) {
				$scope.userSelected.isManager = true;
				$scope.userSelected.isOperator = false;
			}
			if(value.role == 4) {
				$scope.userSelected.isManager = false;
				$scope.userSelected.isOperator = true;
			}
		}
	}
	
	$scope.getTipoUsuario = function(tipo) {
		if(!tipo) {return null;}
		if(tipo == 1) {return 'Administrador';}
		if(tipo == 2) {return 'Contratante';}
		if(tipo == 3) {return 'Gerente';}
		if(tipo == 4) {return 'Operador';}
	}
	
	$scope.getfiliaisdoContrato = function() {
		$scope.noData = false;
		$scope.filiaisContrato = [];
		$scope.cnpjs = [];
		$scope.modelo = {userId: $scope.filial.userId};
    	$scope.pesquisaFilialContrato = new FilialService.pesquisaFilial($scope.modelo);
        $scope.pesquisaFilialContrato.$naoRelacionadas({_csrf : angular.element('#_csrf').val()}, function(){
    		$scope.filiaisContrato = $scope.pesquisaFilialContrato.listFilial;
    		if($scope.filiaisContrato.length == 0) {
    			$scope.noData = true;
    		} 
        });
	}
	
	$scope.setCnpj = function (event) {
		$scope.cnpjs = [];
		if(event.target != undefined) {
			$scope.cnpjs[0] = event.target.value;
		} else {
			$scope.cnpjs[0] = event;
			$scope.filial.cnpj = event;
		}
	}
	
    $scope.incluirRelacionamento = function() {
    	clearRelacionamento();
    	$scope.clearCnpjs = [];
    	$scope.cnpjs.forEach(function(value, index, ar) {
    		if(value != undefined && value != 'null') {
    			$scope.clearCnpjs.push(value);
    		}
    	});
    	$scope.cnpjs = $scope.clearCnpjs;
    	$scope.relacionamento.listaCnpjFiliais = $scope.cnpjs;
    	$scope.relacionamento.userId = $scope.filial.userId;
    	$scope.relacionaByUser = new FilialService.relacionaByUser($scope.relacionamento);
        $scope.relacionaByUser.$filial({_csrf : angular.element('#_csrf').val()}, function(){
        	$scope.pesquisaFilialByUser();
        	$scope.fecharModal();
        	if($scope.relacionaByUser.errorMessages && $scope.relacionaByUser.errorMessages.length > 0){
        		setMessageCadastro($scope.relacionaByUser);
        	}
        });
    }
	
    $scope.excluirRelacionamento = function() {
    	clearRelacionamento();
    	$scope.relacionamento.listaCnpjFiliais = $scope.cnpjs;
    	$scope.relacionamento.userId = $scope.filial.userId;
    	$scope.desrelacionaByUser = new FilialService.desrelacionaByUser($scope.relacionamento);
        $scope.desrelacionaByUser.$filial({_csrf : angular.element('#_csrf').val()}, function(){
        	$scope.pesquisaFilialByUser();
        	$scope.fecharModal();
        	if($scope.desrelacionaByUser.errorMessages && $scope.desrelacionaByUser.errorMessages.length > 0){
        		setMessageCadastro($scope.desrelacionaByUser);
        	}        	
        });
    }
    
   	function clearRelacionamento() {
   		$scope.relacionamento.filialId = '';
   		$scope.relacionamento.userId = '';
   		$scope.relacionamento.listaCnpjFiliais = [];
   		$scope.relacionamento.listaLoginUsers = [];  			
   	}
	
	/** FUNCOES DE INICIALIZACAO */
	$timeout(function(){
		$('#dataTables-filiais').dataTable();
	},5)
	$scope.setUsersByRole();
	
	

	/*
	$scope.contractor = {
		id: '',
    	login: '',
    	role: '2',
    	status: 'ACTIVE'
	};
	
	$scope.cnpj = '';
   	*/
	
});