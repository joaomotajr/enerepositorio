app.controller('PagamentosController', function ($scope, $filter, $timeout, Pagamentos) {
	
	$scope.rangeData = '';
	
	$scope.pagamentosFiltro = {
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
	
	$scope.pagamentos = {
			listValue : []
	};
	
	$scope.detalhePagamento = {
			numeroRo: '',
			data: '',
			valorPago : '',
			taxa :'',
			comissao : '',
			valorBruto : '',
			listaCv : '',
			previsaoPagamento : '',
			dataEnvioBanco : '',
			banco : '',
			agencia : '',
			conta : '',
			status : '',
			produto : '',
    };
	
	$scope.query = '';
	
	$scope.pesquisarPagamentos = function() {
		$('#dataTables-pagamentos').dataTable().fnDestroy();
        $('html').addClass('loading');
        if ($scope.$root.listaPagamentosPeloGrafico.length > 0) {
        	$scope.listaPagamentosRetorno = [];
        	$scope.listaPagamentosRetorno = $scope.$root.listaPagamentosPeloGrafico;
        	$scope.$root.listaPagamentosPeloGrafico = [];
        	$timeout(function(){
        		$('#dataTables-pagamentos').dataTable().fnDestroy();
        		$('#dataTables-pagamentos').dataTable( {
                    "columnDefs": [
                        { "type": "date-eu", targets: 0 }
                    ]
                } );
    			$('#dataTables-pagamentos').dataTable().fnSort([0, 'desc']);
    			$('html').removeClass('loading');
			},500);
        } else {
        	if($scope.rangeData != '') {
        		$scope.pagamentosFiltro.dataInicial = $scope.rangeData.substring(0, 10);
        		$scope.pagamentosFiltro.dataFinal = $scope.rangeData.substring(13, 24);
        	}
	    	$scope.pagamentos = new Pagamentos.security($scope.pagamentosFiltro);
	        $scope.pagamentos.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
	        	$scope.listaPagamentosRetorno = $scope.pagamentos.listValue;
	        	$timeout(function(){
	        		$('#dataTables-pagamentos').dataTable( {
	                    "columnDefs": [
	                        { "type": "date-eu", targets: 0 }
	                    ]
	                } );
	        		$('#dataTables-pagamentos').dataTable().fnSort([0, 'desc']);
	    			$('html').removeClass('loading');
				},500);
	        });
        }
    };
    
    $scope.mostrarDetalhePagamentos = function(index) {
    	$('#dataTables-cvs').dataTable().fnDestroy();
    	$scope.parseFloat = parseFloat;
    	$scope.parseInt = parseInt;
    	$scope.detalhePagamento.numeroRo = $scope.listaPagamentosRetorno[index].numeroRO;
    	$scope.detalhePagamento.data = $filter('date')($scope.listaPagamentosRetorno[index].dataApresentacao, "dd/MM/yyyy");
    	$scope.detalhePagamento.valorPago = $filter('currency')($scope.listaPagamentosRetorno[index].valorLiquido, "R$ ");
    	$scope.detalhePagamento.taxa = $scope.listaPagamentosRetorno[index].taxaComissao;
    	$scope.detalhePagamento.comissao = $filter('currency')($scope.listaPagamentosRetorno[index].valorComissao, "R$ ");
    	$scope.detalhePagamento.valorBruto = $filter('currency')($scope.listaPagamentosRetorno[index].valorBruto, "R$ ");
    	$scope.detalhePagamento.listaCv = $scope.listaPagamentosRetorno[index].comprovantes;
    	$scope.detalhePagamento.previsaoPagamento = $filter('date')($scope.listaPagamentosRetorno[index].dataPrevistaPagamento, "dd/MM/yyyy");
    	$scope.detalhePagamento.dataEnvioBanco = $filter('date')($scope.listaPagamentosRetorno[index].dataEnvioBanco, "dd/MM/yyyy");
    	$scope.detalhePagamento.banco = $scope.listaPagamentosRetorno[index].banco;
    	$scope.detalhePagamento.agencia = $scope.listaPagamentosRetorno[index].agencia;
    	$scope.detalhePagamento.conta = $scope.listaPagamentosRetorno[index].contaCorrente;
    	$scope.detalhePagamento.status = $scope.listaPagamentosRetorno[index].statusPagamento.descricao;
    	$scope.detalhePagamento.produto = $scope.listaPagamentosRetorno[index].identificadorProduto.descricao;
    	$scope.detalhePagamento.tipo = $scope.listaPagamentosRetorno[index].identificadorProduto.tipo;
    	$timeout(function(){
			$('#dataTables-cvs').dataTable();
		},500);
    };
    
    $timeout(function(){
		$('#dataTables-pagamentos').dataTable();
	},500);
    
    if ($scope.$root.listaPagamentosPeloGrafico.length > 0) {
    	$scope.pesquisarPagamentos();
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
    
	$scope.clearPagamentosFiltro = function(){
		$scope.pagamentosFiltro.dataInicial = '';
		$scope.pagamentosFiltro.dataFinal = '';
		$scope.pagamentosFiltro.data = '';
		$scope.pagamentosFiltro.cnpj = '';
		$scope.pagamentosFiltro.razaoSocial = '';
		$scope.pagamentosFiltro.adquirente = '';
		$scope.pagamentosFiltro.tipoVenda = '';
		$scope.pagamentosFiltro.status = [];
		$scope.pagamentosFiltro.cartao = '';
		$scope.pagamentosFiltro.nsu = '';
		$scope.pagamentosFiltro.valor = '';
		$scope.rangeData = '';
    };
});