/** Cache para o usuario logado */
var arrayUser = [];

app.controller('UserTypeController', function ($scope, FilialService) {
	
	$scope.userLogado = {
		isAdmin : false,
		isContractor : false,
		isManager : false,
		isOperator : false,
		id : '',
		cnpjRaiz : '',
		listaCnpj: [],
		listaAdquirentes: []
	}
   	
   	$scope.getUserLogado = function() {
   		
   		if(arrayUser.length == 0){
   			
   			/** TIPO DO USUARIO */
   			var tipoUsuario = $('#tipoUsuario').val();
   			if(tipoUsuario) {
   				if(tipoUsuario == "administrator"){$scope.userLogado.isAdmin = true;}
   				if(tipoUsuario == "contractor"){$scope.userLogado.isContractor = true;}
   				if(tipoUsuario == "manager"){$scope.userLogado.isManager = true;}
   				if(tipoUsuario == "operator"){$scope.userLogado.isOperator = true;}
   			}
   			
   			/** ID DO USUARIO */
   			var idUsuario = $('#idUsuario').val();
   			if(idUsuario) {
   				$scope.userLogado.id = idUsuario;
   			}
   			
   			/** CNPJ RAIZ DO USUARIO */
   			var cnpjRaiz = $('#cnpjRaizUsuario').val();
   			if(cnpjRaiz) {
   				$scope.userLogado.cnpjRaiz = cnpjRaiz;
   			}
   			
   			arrayUser[0] = $scope.userLogado;
   			
   		} else {
   			$scope.userLogado = arrayUser[0];
   		}
   		
	}
	
	/** LISTA DE CNPJs RELACIONADOS */
	$scope.getListaCnpjFromUser = function() {
		$scope.clearListaAdquirentes();
		if(arrayUser[0].listaCnpj.length == 0){
			
			$scope.userLogado.listaCnpj = [];
			if(!$scope.userLogado.isAdmin) {
				$scope.filial = {
						userId : $scope.userLogado.id
				}
			}
			$scope.pesquisa = new FilialService.pesquisa($scope.filial);
			$scope.pesquisa.$filiais({_csrf : angular.element('#_csrf').val()}, function(){
				if($scope.pesquisa.errorMessages.length == 0) {
					$scope.userLogado.listaCnpj = $scope.pesquisa.listFilial;
					arrayUser[0] = $scope.userLogado;
				}
			});
			
		}
	}
	
	$scope.setListAdquirentesById = function(cnpjId) {
		if(cnpjId != null) {
			$scope.userLogado.listaCnpj.forEach(function(filial, index, ar) {
				if(filial.id == cnpjId) {
					$scope.userLogado.listaAdquirentes = filial.adquirentes;
				}
			});
		} else {
			$scope.userLogado.listaAdquirentes = [];
		}
	}
	
	$scope.setListAdquirentesByCnpj = function(cnpj) {
		if(cnpj != null) {
			$scope.userLogado.listaCnpj.forEach(function(filial, index, ar) {
				if(filial.cnpj == cnpj) {
					$scope.userLogado.listaAdquirentes = filial.adquirentes;
				}
			});
		} else {
			$scope.userLogado.listaAdquirentes = [];
		}
	}
	
	$scope.clearListaAdquirentes = function() {
		$scope.userLogado.listaAdquirentes = [];
	}
	
	$scope.formataCnpj = function(cnpj) {
		if(!cnpj) {
			return "";
		}
		return cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g,"\$1.\$2.\$3\/$4\-\$5");
	}
   	
	$scope.getUserLogado();	
	
});