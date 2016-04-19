app.controller('ConciliacaoController', function ($scope, $filter, $timeout, Conciliacao) {
	
	$scope.rangeData = '';
	
	$scope.conciliacaoFiltro = {
			dataInicial: undefined,
			dataFinal : undefined,
			cnpj : undefined,
			razaoSocial : undefined,
			adquirente : undefined,
			tipoVenda : undefined,
			status : undefined,
			valor : undefined,
			nsu: undefined
    };
	
	$scope.conciliacoes = {
			listValue : []
	};
	
	$scope.query = '';
	
	$scope.pesquisarConciliacao = function() {
		$('#dataTables-conciliacao').dataTable().fnDestroy();
        $('html').addClass('loading');
        if ($scope.$root.listaConciliacaoPeloGrafico.length > 0) {
        	$scope.listaConciliacaoRetorno = [];
        	$scope.listaConciliacaoRetorno = $scope.$root.listaConciliacaoPeloGrafico;
        	$scope.$root.listaConciliacaoPeloGrafico = [];
        	$timeout(function(){
        		$('#dataTables-conciliacao').dataTable().fnDestroy();
        		$('#dataTables-conciliacao').dataTable( {
                    "columnDefs": [
                        { "type": "date-eu", targets: 0 }
                    ]
                } );
    			$('#dataTables-conciliacao').dataTable().fnSort([0, 'desc']);
    			$('html').removeClass('loading');
			},500);
        } else {
        	if($scope.rangeData != '') {
        		$scope.conciliacaoFiltro.dataInicial = $scope.rangeData.substring(0, 10);
        		$scope.conciliacaoFiltro.dataFinal = $scope.rangeData.substring(13, 24);
        	}
        	$scope.conciliacoes = new Conciliacao.security($scope.conciliacaoFiltro);
            $scope.conciliacoes.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
            	$scope.listaConciliacaoRetorno = $scope.conciliacoes.listValue;
            	$timeout(function(){
            		$('#dataTables-conciliacao').dataTable( {
                        "columnDefs": [
                            { "type": "date-eu", targets: 0 }
                        ]
                    } );
            		$('#dataTables-conciliacao').dataTable().fnSort([0, 'desc']);
            		$('html').removeClass('loading');
    			},500);
            });
        }
    };
    
    $scope.mostrarConciliacao = function(index) {
    	$scope.venda = $scope.listaConciliacaoRetorno[index].vendaDto;
    	$scope.comprovante = $scope.listaConciliacaoRetorno[index].comprovanteDto;
    	$scope.taxaVenda = $scope.listaConciliacaoRetorno[index].taxaVenda;
    	$scope.parcelas = $scope.listaConciliacaoRetorno[index].parcelas;
    };
    
    $timeout(function(){
		$('#dataTables-conciliacao').dataTable();
	},500);
    
    if ($scope.$root.listaConciliacaoPeloGrafico.length > 0) {
    	$scope.pesquisarConciliacao();
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
    
	$scope.clearConciliacaoFiltro = function(){
		$scope.conciliacaoFiltro.dataInicial = undefined;
		$scope.conciliacaoFiltro.dataFinal = undefined;
		$scope.conciliacaoFiltro.cnpj = undefined;
		$scope.conciliacaoFiltro.razaoSocial = undefined;
		$scope.conciliacaoFiltro.adquirente = undefined;
		$scope.conciliacaoFiltro.tipoVenda = undefined;
		$scope.conciliacaoFiltro.status = undefined;
		$scope.conciliacaoFiltro.valor = undefined;
		$scope.conciliacaoFiltro.nsu = undefined;
		$scope.rangeData = '';
    };
	
});