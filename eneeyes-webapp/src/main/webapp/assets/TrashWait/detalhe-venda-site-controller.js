app.controller('DetalheVendaController', function ($scope, $filter, $timeout, DetalheVenda) {
	
	$scope.rangeData = '';
	$scope.statusPesquisa = '';
	
	$scope.detalheVendaFiltro = {
			dataInicial: '',
			dataFinal : '',
			data: '',
			cnpj : '',
			razaoSocial : '',
			adquirente : '',
			tipoVenda : '',
			status : [],
			cartao : '',
			nsu : '',
			valor : '',
    };
	
	$scope.vendas = {
			listValue : []
	};
	
	$scope.detalheVendaLista = {
			data: '',
			hora: '',
			adquirente : '',
			nomeProduto :'',
			cnpj : '',
			tipoVenda : '',
			status : '',
			nsu : '',
			valor : '',
    };
	
	if ($scope.$root.vendaManual != undefined) {
		$scope.vendaManual = {
			id: $scope.$root.vendaManual.id,
			dataLancamen: $filter('date')($scope.$root.vendaManual.dataLancamen, "dd/MM/yyyy"),
			hora: $filter('date')($scope.$root.vendaManual.hora, "HH:mm:ss"),
			autorizador: $scope.$root.vendaManual.autorizador,
			nomeProduto: $scope.$root.vendaManual.nomeProduto,
			cpfCnpj: $scope.$root.vendaManual.cpfCnpj,
			titComplTrans: $scope.$root.vendaManual.titComplTrans,
			estadoTransacao: $scope.$root.vendaManual.estadoTransacao,
			nsuHost: $scope.$root.vendaManual.nsuHost,
			valor: $filter('currency')($scope.$root.vendaManual.valor, '', 2),
			estabelecimento: $scope.$root.vendaManual.estabelecimento,
			numeroCartao: $scope.$root.vendaManual.numeroCartao,
			codigoAutoriz : $scope.$root.vendaManual.codigoAutoriz,
			terminalLogico: $scope.$root.vendaManual.terminalLogico,
			numPar: $scope.$root.vendaManual.numPar,
			meioCaptura: $scope.$root.vendaManual.meioCaptura,
			nomeCliente: $scope.$root.vendaManual.nomeCliente
	    };
		$scope.isEdit = true;
		$scope.$root.vendaManual = undefined;
	} else {
		$scope.vendaManual = {
				id: '0',
				dataLancamen: '',
				hora: '',
				autorizador: '',
				nomeProduto: '',
				cpfCnpj: '',
				titComplTrans: '',
				estadoTransacao: '',
				nsuHost: '',
				valor: '',
				estabelecimento: '',
				numeroCartao: '',
				codigoAutoriz : '',
				terminalLogico: '',
				numPar: '',
				meioCaptura: '',
				nomeCliente: ''
	    };
	}
	
	$scope.query = '';
	
	$scope.setStatus = function() {
		if(!$scope.statusPesquisa == '') {
			$scope.detalheVendaFiltro.status.push($scope.statusPesquisa); 
		} else {
			$scope.detalheVendaFiltro.status = [];
		}
	}
	
	$scope.pesquisarVenda = function() {
		$('#dataTables-vendas').dataTable().fnDestroy();
        $('html').addClass('loading');
        if ($scope.$root.listaPeloGrafico.length > 0) {
        	$scope.listaVendasRetorno = [];
        	$scope.listaVendasRetorno = $scope.$root.listaPeloGrafico;
        	$scope.$root.listaPeloGrafico = [];
        	$timeout(function(){
        		$('#dataTables-vendas').dataTable( {
                    "columnDefs": [
                        { "type": "date-eu", targets: 0 }
                    ]
                } );
    			$('#dataTables-vendas').dataTable().fnSort([0, 'desc']);
    			$('html').removeClass('loading');
			},500);
        } else {
        	if($scope.rangeData != '') {
        		$scope.detalheVendaFiltro.dataInicial = $scope.rangeData.substring(0, 10);
        		$scope.detalheVendaFiltro.dataFinal = $scope.rangeData.substring(13, 24);
        	}
        	$scope.vendas = new DetalheVenda.security($scope.detalheVendaFiltro);
            $scope.vendas.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
            	$scope.listaVendasRetorno = $scope.vendas.listValue;
            	$timeout(function(){
            		$('#dataTables-vendas').dataTable( {
                        "columnDefs": [
                            { "type": "date-eu", targets: 0 }
                        ]
                    } );
        			$('#dataTables-vendas').dataTable().fnSort([0, 'desc']);
        			$('html').removeClass('loading');
				},500);
            });
        }
    };
    
    $scope.incluirVenda = function() {
        $('html').addClass('loading');
        if ($scope.isEdit) {
        	$scope.vendaManual.isEdit = "true";
        } else {
        	$scope.vendaManual.isEdit = "false";
        }
    	$scope.inclusao = new DetalheVenda.inclusao($scope.vendaManual);
        $scope.inclusao.$venda({_csrf : angular.element('#_csrf').val()}, function(){
        	$('html').removeClass('loading');
        	if ($scope.inclusao.errorMessages.length == 0) {
	        	$scope.vendaManual.id = '';
	        	$scope.vendaManual.dataLancamen = '';
	        	$scope.vendaManual.hora = '';
	        	$scope.vendaManual.autorizador = '';
	        	$scope.vendaManual.nomeProduto = '';
	        	$scope.vendaManual.cpfCnpj = '';
	        	$scope.vendaManual.titComplTrans = '';
	        	$scope.vendaManual.estadoTransacao = '';
	        	$scope.vendaManual.nsuHost = '';
	        	$scope.vendaManual.valor = '';
	        	$scope.vendaManual.estabelecimento = '';
	        	$scope.vendaManual.numeroCartao = '';
	        	$scope.vendaManual.codigoAutoriz = '';
	        	$scope.vendaManual.terminalLogico = '';
	        	$scope.vendaManual.numPar = '';
	        	$scope.vendaManual.meioCaptura = '';
	        	$scope.vendaManual.nomeCliente = '';
	        	$scope.showMessage($scope.inclusao.messages[0].message);
        	} else {
        		if ($scope.inclusao.errorMessages[0].message.indexOf("#") == -1) {
        			$scope.showMessage($scope.inclusao.errorMessages[0].message);
        		}
        	}
        });
    };
    
    $scope.mostrarDetalheVenda = function(index) {
    	$scope.detalheVendaLista.data = $scope.listaVendasRetorno[index].dataLancamen;
    	$scope.detalheVendaLista.hora = $scope.listaVendasRetorno[index].hora;
    	$scope.detalheVendaLista.adquirente = $scope.listaVendasRetorno[index].autorizador;
    	$scope.detalheVendaLista.bandeira = $scope.listaVendasRetorno[index].nomeProduto;
    	$scope.detalheVendaLista.cnpj = $scope.listaVendasRetorno[index].cpfCnpj;
    	$scope.detalheVendaLista.tipoVenda = $scope.listaVendasRetorno[index].titComplTrans;
    	$scope.detalheVendaLista.status = $scope.listaVendasRetorno[index].estadoTransacao;
    	$scope.detalheVendaLista.nsu = $scope.listaVendasRetorno[index].nsuHost;
    	$scope.detalheVendaLista.valor = $scope.listaVendasRetorno[index].valor;
    	$scope.detalheVendaLista.estabelecimento = $scope.listaVendasRetorno[index].estabelecimento;
    	$scope.detalheVendaLista.numeroCartao = $scope.listaVendasRetorno[index].numeroCartao;
    	$scope.detalheVendaLista.codigoAutoriz = $scope.listaVendasRetorno[index].codigoAutoriz;
    	$scope.detalheVendaLista.terminalLogico = $scope.listaVendasRetorno[index].terminalLogico;
    	$scope.detalheVendaLista.numPar = $scope.listaVendasRetorno[index].numPar;
    	$scope.detalheVendaLista.meioCaptura = $scope.listaVendasRetorno[index].meioCaptura;
    	$scope.detalheVendaLista.nomeProduto = $scope.listaVendasRetorno[index].nomeProduto;
    	$scope.detalheVendaLista.nomeCliente = $scope.listaVendasRetorno[index].nomeCliente;
    	if ($scope.listaVendasRetorno[index].meioCaptura.indexOf('Manual') > -1) {
    		$scope.$root.vendaManual = $scope.listaVendasRetorno[index];
    	}
    };
    
    $scope.editarDetalheVenda = function() {
    	$("#modalDetalheVenda").modal("hide");
    	$timeout(function(){
    		$scope.LoadAjaxContent('inclusao-venda.html');
    	},500);
    };
    
    $timeout(function(){
		$('#dataTables-vendas').dataTable();
	},500);
    
    if ($scope.$root.listaPeloGrafico.length > 0) {
    	$scope.pesquisarVenda();
    };
    
    $scope.showMessage = function(msg) {
		$.gritter.add({
            title: '<center>Aten\u00e7\u00e3o!</center>',
            text: msg,
            image: '',
            sticky: false,
            time: ''
        });
	};
	
	$scope.hasError = function(errorMessage) {
        var hasError = false;

        if ($scope.inclusao == undefined || $scope.inclusao.errorMessages == undefined) {
            return hasError;
        }

        angular.forEach($scope.inclusao.errorMessages, function(value) {
            var index = errorMessage.length * -1;
            if (value.message.substr(index) == errorMessage) {
                hasError = 'has-error';
            }
        });
        return hasError;
    };
    
    $scope.fecharModalImagem = function() {
    	$("#modalImagemComprovante").modal("hide");
    };
    
    $scope.novoRegistro = function() {
    	$scope.vendaManual.id = '';
    	$scope.vendaManual.dataLancamen = '';
    	$scope.vendaManual.hora = '';
    	$scope.vendaManual.autorizador = '';
    	$scope.vendaManual.nomeProduto = '';
    	$scope.vendaManual.cpfCnpj = '';
    	$scope.vendaManual.titComplTrans = '';
    	$scope.vendaManual.estadoTransacao = '';
    	$scope.vendaManual.nsuHost = '';
    	$scope.vendaManual.valor = '';
    	$scope.vendaManual.estabelecimento = '';
    	$scope.vendaManual.numeroCartao = '';
    	$scope.vendaManual.codigoAutoriz = '';
    	$scope.vendaManual.terminalLogico = '';
    	$scope.vendaManual.numPar = '';
    	$scope.vendaManual.meioCaptura = '';
    	$scope.vendaManual.nomeCliente = '';
    	$scope.isEdit = false;
    };
    
    $scope.clearDetalheVendaFiltro = function(){
    	$scope.detalheVendaFiltro.dataInicial = '';
    	$scope.detalheVendaFiltro.dataFinal = '';
    	$scope.detalheVendaFiltro.data = '';
    	$scope.detalheVendaFiltro.cnpj = '';
    	$scope.detalheVendaFiltro.razaoSocial = '';
    	$scope.detalheVendaFiltro.adquirente = '';
    	$scope.detalheVendaFiltro.tipoVenda = '';
    	$scope.detalheVendaFiltro.status = [];
    	$scope.detalheVendaFiltro.cartao = '';
    	$scope.detalheVendaFiltro.nsu = '';
    	$scope.detalheVendaFiltro.valor = '';
    	$scope.rangeData = '';
    	$scope.statusPesquisa = '';
    };
});