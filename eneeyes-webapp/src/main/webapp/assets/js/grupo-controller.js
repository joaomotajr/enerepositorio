app.controller('GrupoController', function ($scope, $timeout, $filter, GrupoService, UserService, FilialService) {
	
	/** VARIAVEIS - MODAL */
	$scope.grupo = {
		id: undefined,
		nome: undefined,
		descricao: undefined,
		listaUsers: [],
		listaFiliais: []
	};
	$scope.grupos = {
		listaGrupo : []
	};
	$scope.filiaisSemGrupo = [];
	$scope.usersManager = [];
	$scope.cnpjFiliais = [];
	$scope.cpfUsersManager = [];
	$scope.selectedAll = '';
	$scope.messagePesquisa = ""
	$scope.messagePesquisaError = '';
	$scope.messageCadastro = '';
	$scope.messageCadastroError = '';
	$scope.isCad = true;
	$scope.isEdit = false;	
	$scope.index = '';
	
	/** BOTAO NOVO USUARIO */
	$scope.novoGrupo = function() {
		$scope.isCad = true;
		$scope.isEdit = false;
		$scope.clearGrupoModal();
		$scope.clearMessageCadastro();
		clearMessagePesquisa();
	}
	
    $scope.saveGrupo = function() {
    	$('html').addClass('loading');
    	
    	/** EDICAO DE GRUPO */
    	if($scope.grupo.id) {
        	$scope.atualiza = new GrupoService.atualiza($scope.grupo);
            $scope.atualiza.$grupo({_csrf : angular.element('#_csrf').val()}, function(){
            	$('html').removeClass('loading');
            	
            	// Atualiza o listUser
            	$scope.grupos.listaGrupo[$scope.index].id = $scope.grupo.id;
            	$scope.grupos.listaGrupo[$scope.index].nome = $scope.grupo.nome;
            	$scope.grupos.listaGrupo[$scope.index].descricao = $scope.grupo.descricao;
            	
            	// variavel para atualizar o dataTable
            	var data = [$scope.grupo.nome,
            	            $scope.grupo.descricao];
            	
            	$('#dataTables-grupos').DataTable().row($scope.index).data(data).draw();
            	if($scope.atualiza.errorMessages && $scope.atualiza.errorMessages.length > 0) {
            		setMessageCadastro($scope.atualiza);
            	} else {
            		$scope.fecharModal();
            		setMessagePesquisa($scope.atualiza);
            		$scope.clearGrupoModal();
            	}
            });   	
        
        /** INCLUSAO DE GRUPO */
    	} else {
    		$('#dataTables-grupos').dataTable().fnDestroy();
    		$scope.grupos.listaGrupo = [];
        	$scope.inclusao = new GrupoService.inclui($scope.grupo);
            $scope.inclusao.$grupo({_csrf : angular.element('#_csrf').val()}, function(){
            	$('html').removeClass('loading');
            	if($scope.inclusao.errorMessages && $scope.inclusao.errorMessages.length > 0) {
            		setMessageCadastro($scope.inclusao);
            	} else {
            		$scope.fecharModal();
            		setMessagePesquisa($scope.inclusao);
            		$scope.clearGrupoModal();
            	}
            });
            $timeout(function(){
    			$('#dataTables-grupos').dataTable();
    		},5);
    	}
    }
    
	/** EXCLUSAO DE GRUPO */
    $scope.excluirGrupo = function() {
        $('html').addClass('loading');
        $scope.clearMessageCadastro();
    	$scope.remove = new GrupoService.remove($scope.grupo);
        $scope.remove.$grupo({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	setMessageCadastro($scope.remove);
        	if($scope.remove.errorMessages.length == 0) {
	        	$scope.grupos.listaGrupo.splice($scope.index, 1);
	        	$('#dataTables-grupos').DataTable().row($scope.index).remove().draw();
	        	$scope.fecharModal();
	        	$scope.grupo.nome = '';
        	} else {
        		$scope.fecharModalById('modalExcluirGrupo');
        	}
        });    	
    }
	
    /** PESQUISA GRUPO */
	$scope.pesquisaGrupo = function() {
    	$('#dataTables-grupos').dataTable().fnDestroy();
        $('html').addClass('loading');
        clearMessagePesquisa();
        $scope.clearMessageCadastro();
    	$scope.grupos.listaGrupo = [];
    	$scope.pesquisaSimples = new GrupoService.pesquisaSimples($scope.grupo);
        $scope.pesquisaSimples.$grupo({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	setMessagePesquisa($scope.pesquisaSimples);
        	if($scope.pesquisaSimples.errorMessages.length == 0) {
        		$scope.grupos.listaGrupo = $scope.pesquisaSimples.listaGrupos; 
        	}
        	if($scope.grupos.listaGrupo.length > 0) {
        		$timeout(function(){
        			$('#dataTables-grupos').dataTable().fnSort([0, 'asc']);
        		},5);	
        	} else {
        		$timeout(function(){
        			$('#dataTables-grupos').dataTable();
        		},5);
        	}
        });
    }
	
	/** DETALHE DOS GRUPOS */
	$scope.detalheGrupo = function(index) {
		$scope.grupo.listaUsers = [];
		$scope.grupo.listaFiliais = [];
		$scope.clearMessageCadastro();
    	$scope.index = index;
    	$scope.grupo.id = $scope.grupos.listaGrupo[index].id;
    	$scope.grupo.nome = $scope.grupos.listaGrupo[index].nome;
    	$scope.grupo.descricao = $scope.grupos.listaGrupo[index].descricao;
    	
    	$scope.pesquisaCompleta = new GrupoService.pesquisaCompleta($scope.grupo);
        $scope.pesquisaCompleta.$grupo({_csrf : angular.element('#_csrf').val()}, function(){
        	if($scope.pesquisaCompleta.errorMessages.length == 0) {
        		$scope.grupo.listaUsers = $scope.pesquisaCompleta.listaGrupos[0].listaUsers;
        		$scope.grupo.listaFiliais = $scope.pesquisaCompleta.listaGrupos[0].listaFiliais;
                $scope.cpfUsersManager = [];
                $scope.grupo.listaUsers.forEach(function(user, index, listaUser) {
                	$scope.cpfUsersManager[user.id] = user.id.toString();
                });
        	}
        });
        
    	$scope.isCad = false;
    	$scope.isEdit = true;
    }
	
	/** FUNCOES - FILIAIS */
	$scope.getFiliaisSemGrupo = function() {
		$scope.noData = false;
		$scope.filiaisSemGrupo = [];
		$('#dataTables-modal-filiais').dataTable().fnDestroy();
		$scope.modelo = {};
    	$scope.pesquisaSemGrupo = new FilialService.pesquisaSemGrupo($scope.modelo);
        $scope.pesquisaSemGrupo.$filial({_csrf : angular.element('#_csrf').val()}, function(){
    		$scope.filiaisSemGrupo = $scope.pesquisaSemGrupo.listFilial;
    		if($scope.filiaisSemGrupo.length == 0) {
    			$scope.noData = true;
    		} 
    		$timeout(function(){
    			$('#dataTables-modal-filiais').dataTable({
    				'aoColumnDefs': [{'bSortable': false, 'aTargets': [0]}]
    			}).fnSort([1, 'asc']);
    		},5)
        });   		
   	}
	
	$scope.setFiliais = function(index, cnpj) {
	   if($scope.cnpjFiliais[index] != null && $scope.cnpjFiliais[index] == index){
			$scope.grupo.listaFiliais.push($scope.filiaisSemGrupo[index]);
	   } else {
		   var indexRemove = null;
		   $scope.grupo.listaFiliais.forEach(function(filial, idx, listaFiliais) {
			   if(filial.cnpj == cnpj) {
				   indexRemove = idx;
			   }
		   });
		   if(indexRemove != null) {
			   $scope.grupo.listaFiliais.splice(indexRemove, 1);	   
		   }
	   }
	}
	
	$scope.removeFilial = function(cnpj) {
		var indexRemove = null;
		$scope.filiaisSemGrupo.forEach(function(filial, idx, listaFiliais) {
			if(filial.cnpj == cnpj) {
				$scope.cnpjFiliais[idx] = null;
				indexRemove = idx;
			}
		});
		$scope.setFiliais(indexRemove, cnpj);
	}
	
	/** FUNCOES - USUARIOS */
	$scope.getUsersManager = function() {
		$scope.usersManager = [];
		$('#dataTables-modal-users').dataTable().fnDestroy();
		$scope.modelo = {role: 3};
		$scope.pesquisa = new UserService.pesquisa($scope.modelo);
        $scope.pesquisa.$users({_csrf : angular.element('#_csrf').val()}, function(){
    		$scope.usersManager = $scope.pesquisa.listUser;
    		if($scope.usersManager.length == 0) {
    			$scope.noData = true;
    		} 
    		$timeout(function(){
    			$('#dataTables-modal-users').dataTable({
    				'aoColumnDefs': [{'bSortable': false, 'aTargets': [0]}]
    			}).fnSort([1, 'asc']);
    		},5)
        });   		
   	}	
	
	$scope.setUsers = function(id, cpf) {
	   if($scope.cpfUsersManager[id] != "null"){
		   	$scope.usersManager.forEach(function(user, index, managers) {
		   		if(user.cpf == cpf) {
		   			$scope.grupo.listaUsers.push(user);
		   		}
		   	});
	   } else {
		   var index = null;
		   $scope.grupo.listaUsers.forEach(function(user, idx, listaUsers) {
			   if(user.cpf == cpf) {
				   index = idx;
			   }
		   });
		   if(index != null) {
			   $scope.grupo.listaUsers.splice(index, 1);	   
		   }
	   }
	}
	
	$scope.removeUser = function(id, cpf) {
		$scope.cpfUsersManager[id] = "null";
		$scope.setUsers(id, cpf);
	}	
	
	$scope.checkAllFiliais = function() {
		if($scope.selectedAll) {
            $scope.selectedAll = true;
        } else {
            $scope.selectedAll = false;
        }
	}
	
	/** FUNCOES AUXILIARES */
	$scope.getStatus = function(status) {
		if(!status) {return null;}
		if(status == 'ACTIVE') {return 'Ativo';}
		if(status == 'INACTIVE') {return 'Inativo';}
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
	
	$scope.cpfFormatter = function(cpf) {
		if(!cpf) {
			return "";
		}
		return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g,"\$1.\$2.\$3-\$4");
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
    
	$scope.fecharModal = function() {
    	$(".modal").modal("hide");
    }
	
	$scope.fecharModalById = function(id) {
    	$("#"+id).modal("hide");
    }
	
	$scope.clearGrupoModal = function() {
		$('#dataTables-grupos').dataTable().fnDestroy();
		$('#dataTables-modal-filiais').dataTable().fnDestroy();
		$('#dataTables-modal-users').dataTable().fnDestroy();
		$scope.grupo.id = undefined;
		$scope.grupo.nome = undefined;
		$scope.grupo.descricao = undefined;
		$scope.grupo.listaUsers = [];
		$scope.grupo.listaFiliais = [];
		$scope.grupos.listaGrupo = [];
		$scope.filiaisSemGrupo = [];
		$scope.usersManager = [];
		$scope.cnpjFiliais = [];
		$scope.cpfUsersManager = [];
		$timeout(function(){
			$('#dataTables-grupos').dataTable();
		},5);
		$timeout(function(){
			$('#dataTables-modal-filiais').dataTable();
		},5);
		$timeout(function(){
			$('#dataTables-modal-users').dataTable();
		},5);		
   	}
    
	/** FUNCOES DE INICIALIZACAO */
	$scope.clearGrupoModal();
	$('[data-toggle="tooltip"]').tooltip(); 
    $timeout(function(){
		$('#dataTables-grupos').dataTable();
	},5)
	$timeout(function(){
		$('#dataTables-modal-filiais').dataTable();
	},5);
	$timeout(function(){
		$('#dataTables-modal-users').dataTable();
	},5);	
   	
});