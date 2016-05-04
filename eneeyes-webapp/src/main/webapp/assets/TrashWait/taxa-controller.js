app.controller('TaxaController', function ($scope, $timeout, $filter, TaxaService) {
	
	/** VARIAVEIS - MODAL */
	$scope.taxa = {
		id: undefined,
		credito: undefined,
		credito2a6: undefined,
		credito7a12: undefined,
		debito: undefined,
		antecipacao: undefined,
		dataInicio: undefined,
		adquirenteId: undefined
	}
	$scope.cnpjId = '';
	$scope.messagePesquisa = ""
	$scope.messagePesquisaError = '';
	$scope.taxas = {
		listTaxa: []		
	}

    /** INCLUSAO DE USUARIO */
    $scope.gravarTaxa = function() {
    	$('#dataTables-taxas').dataTable().fnDestroy();
        $('html').addClass('loading');
        clearMessagePesquisa();
    	$scope.inclusao = new TaxaService.inclusao($scope.taxa);
        $scope.inclusao.$taxa({_csrf : angular.element('#_csrf').val()}, function(){
        	setMessagePesquisa($scope.inclusao);
        	$scope.pesquisaTaxa();
        });
    }	
	
    /** PESQUISA DE TAXA */
	$scope.pesquisaTaxa = function() {
		if(!$scope.taxa.adquirenteId) {
			$scope.clearAll();
			return false;
		}
    	$('#dataTables-taxas').dataTable().fnDestroy();
        $('html').addClass('loading');
        clearMessagePesquisa();
    	$scope.taxas.listTaxa = [];
    	$scope.pesquisa = new TaxaService.pesquisa($scope.taxa);
        $scope.pesquisa.$taxas({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	setMessagePesquisa($scope.pesquisa);
        	if($scope.pesquisa.errorMessages.length == 0) {
        		if($scope.pesquisa.listTaxa.length > 0) {
        			$scope.taxas.listTaxa = $scope.pesquisa.listTaxa;
        			$scope.populaTaxa($scope.pesquisa.listTaxa)
        		} else {
        			var id = $scope.taxa.adquirenteId;
            		$scope.clearTaxaModal();
            		$scope.taxa.adquirenteId = id;
            	}
        		$timeout(function(){
        			$('#dataTables-taxas').dataTable().fnSort([5, 'desc']);
        		},5);
        	}
        });
    }
	
	/** FUNCOES AUXILIARES */
	$scope.populaTaxa = function(listTaxa) {
		if(!listTaxa) {
			return false;
		}
		listTaxa.sort(function(tx1, tx2) {
			  return tx2.id - tx1.id;
		});
		$scope.taxa.id = listTaxa[0].id;
		$scope.taxa.credito = listTaxa[0].credito;
		$scope.taxa.credito2a6 = listTaxa[0].credito2a6;
		$scope.taxa.credito7a12 = listTaxa[0].credito7a12;
		$scope.taxa.debito = listTaxa[0].debito;
		$scope.taxa.antecipacao = listTaxa[0].antecipacao;
		$scope.taxa.dataInicio = listTaxa[0].dataInicio;
		$scope.taxa.adquirenteId = listTaxa[0].adquirenteId;
	}
	
	$scope.clearAll = function() {
		$('#dataTables-taxas').dataTable().fnDestroy();
		$scope.taxas.listTaxa = [];
		$scope.clearTaxaModal();
		clearMessagePesquisa();
		$timeout(function(){
			$('#dataTables-taxas').dataTable();
		},5);
	}
	
	$scope.clearTaxaModal = function() {
		$scope.taxa.id = undefined;
		$scope.taxa.credito = undefined;
		$scope.taxa.credito2a6 = undefined;
		$scope.taxa.credito7a12 = undefined;
		$scope.taxa.debito = undefined;
		$scope.taxa.antecipacao = undefined;
		$scope.taxa.dataInicio = undefined;
		$scope.taxa.adquirenteId = undefined;
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
	
	/** FUNCOES DE INICIALIZACAO */
    $timeout(function(){
		$('#dataTables-taxas').dataTable();
	},5)
	
    $scope.clearTaxaModal();
   	
});