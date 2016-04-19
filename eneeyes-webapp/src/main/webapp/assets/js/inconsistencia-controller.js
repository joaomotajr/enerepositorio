app.controller('InconsistenciaController', function ($scope, $filter, $timeout, Inconsistencia) {
	
	$scope.rangeData = '';
	
	$scope.inconsistenciaFiltro = {
			dataInicial: '',
			dataFinal : '',
			cnpj : '',
			razaoSocial : '',
			adquirente : '',
			tipoVenda : '',
			status : [],
			valor : '',
			nsu: ''
    };
	
	$scope.justificativa = {
			id: '',
			tipoJustificativa : {},
			usuario : '',
			data : '',
    };
	
	$scope.inconsistencias = {
			listValue : []
	};
	
	$scope.query = '';
	
	$scope.pesquisarInconsistencia = function() {
		$('#dataTables-inconsistencia').dataTable().fnDestroy();
        $('html').addClass('loading');
        if ($scope.$root.listaInconsistenciaPeloGrafico.length > 0) {
        	$scope.listaInconsistenciaRetorno = [];
        	$scope.listaInconsistenciaRetorno = $scope.$root.listaInconsistenciaPeloGrafico;
        	$scope.$root.listaInconsistenciaPeloGrafico = [];
        	$scope.inconsistenciaFiltro = $scope.$root.inconsistenciaFiltro;
        	$timeout(function(){
        		$('#dataTables-inconsistencia').dataTable().fnDestroy();
        		$('#dataTables-inconsistencia').dataTable( {
                    "columnDefs": [
                        { "type": "date-eu", targets: 0 }
                    ]
                } );
    			$('#dataTables-inconsistencia').dataTable().fnSort([0, 'desc']);
    			$('html').removeClass('loading');
			},500);
        } else {
        	if($scope.rangeData != '') {
        		$scope.inconsistenciaFiltro.dataInicial = $scope.rangeData.substring(0, 10);
            	$scope.inconsistenciaFiltro.dataFinal = $scope.rangeData.substring(13, 24);
        	}
        	$scope.inconsistencias = new Inconsistencia.security($scope.inconsistenciaFiltro);
            $scope.inconsistencias.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
            	$scope.listaInconsistenciaRetorno = $scope.inconsistencias.listValue;
            	$timeout(function(){
            		$('#dataTables-inconsistencia').dataTable( {
	                    "columnDefs": [
	                        { "type": "date-eu", targets: 0 }
	                    ]
	                } );
	    			$('#dataTables-inconsistencia').dataTable().fnSort([0, 'desc']);
        			$('html').removeClass('loading');
				},500);
            });
        }
    };
    
    $scope.mostrarInconsistencia = function(index) {
    	$("#justificativa").prop('disabled', false);
    	$scope.venda = $scope.listaInconsistenciaRetorno[index].vendaDto;
    	$scope.comprovante = $scope.listaInconsistenciaRetorno[index].comprovanteDto;
    	$scope.taxaVenda = $scope.listaInconsistenciaRetorno[index].taxaVenda;
    	$scope.taxaComprovante = $scope.listaInconsistenciaRetorno[index].taxaComprovante;
    	$scope.parcelas = $scope.listaInconsistenciaRetorno[index].parcelas;
    	$scope.inconsistencias = $scope.listaInconsistenciaRetorno[index].motivos;
    	$scope.isPendente = $scope.listaInconsistenciaRetorno[index].pendente
    	
    	$scope.tipoJustificativa = new Inconsistencia.tipoJustificativa();
    	$scope.tipoJustificativa.$obterLista({_csrf : angular.element('#_csrf').val()}, function(){
    		$scope.tiposJustificativa = $scope.tipoJustificativa.listValue;
        });
    };
    
    $scope.salvarJustificativa = function() {
    	$scope.justificativa.tipoJustificativa.id = $scope.idJustificativa;
    	$scope.venda.justificativa = $scope.justificativa;
    	$scope.incluir = new Inconsistencia.incluir($scope.venda);
        $scope.incluir.$justificativa({_csrf : angular.element('#_csrf').val()}, function(){
        	$scope.showMessage("Justificativa inclu&iacute;da com sucesso.");
        	$("#justificativa").prop('disabled', true);
        });
    };
    
    $('#modalInconsistencia').on('hidden.bs.modal', function () {
    	$scope.pesquisarInconsistencia();
    })
    
    $timeout(function(){
		$('#dataTables-inconsistencia').dataTable();
	},500);
    
    if ($scope.$root.listaInconsistenciaPeloGrafico.length > 0) {
    	$scope.pesquisarInconsistencia();
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
	
	$scope.clearInconsistenciaFiltro = function(){
		$scope.inconsistenciaFiltro.dataInicial = '';
		$scope.inconsistenciaFiltro.dataFinal = '';
		$scope.inconsistenciaFiltro.cnpj = '';
		$scope.inconsistenciaFiltro.razaoSocial = '';
		$scope.inconsistenciaFiltro.adquirente = '';
		$scope.inconsistenciaFiltro.tipoVenda = '';
		$scope.inconsistenciaFiltro.status = [];
		$scope.inconsistenciaFiltro.valor = '';
		$scope.inconsistenciaFiltro.nsu = '';
		$scope.rangeData = '';
    };
});