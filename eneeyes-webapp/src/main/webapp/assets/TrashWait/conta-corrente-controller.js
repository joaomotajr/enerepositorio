app.controller('ContaCorrenteController', function ($scope, $filter, $timeout, ContaCorrente) {
	
	$scope.rangeData = '';
	
	$scope.contaCorrenteFiltro = {
			dataInicial: '',
			dataFinal : '',
			adquirente: '',
			status : '',
			cnpj : '' //TODO: Adicionar ao DTO
    };
	
	$scope.contaCorrente = {
			listValue : []
	};
	
	$scope.detalheContaCorrente = {
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
	
	$scope.pesquisarContaCorrente = function() {
		$('#dataTables-conta-corrente').dataTable().fnDestroy();
        $('html').addClass('loading');
        if ($scope.$root.listaContaCorrentePeloGrafico.length > 0) {
        	$scope.listaContaCorrenteRetorno = [];
        	$scope.listaContaCorrenteRetorno = $scope.$root.listaContaCorrentePeloGrafico;
        	$scope.$root.listaContaCorrentePeloGrafico = [];
        	
        	$scope.totalRecebido = 0;
        	$scope.totalReceber = 0;
        	for (var i = 0; i < $scope.listaContaCorrenteRetorno.length; i++) {
        		if ($scope.listaContaCorrenteRetorno[i].status == 'Recebido') {
        			$scope.totalRecebido = $scope.totalRecebido + parseFloat($scope.listaContaCorrenteRetorno[i].valor); 
        		} else if ($scope.listaContaCorrenteRetorno[i].status == 'A receber') {
        			$scope.totalReceber = $scope.totalReceber + parseFloat($scope.listaContaCorrenteRetorno[i].valor);
        		}
			};
        	$timeout(function(){
        		var data = google.visualization.arrayToDataTable([
	        	                                                  ['Adquirente', 'Valor'],
	        	                                                  ['Cielo', $scope.totalRecebido],
	        	                                                  ]);
	        	var formatter = new google.visualization.NumberFormat(
	  			      {prefix: 'R$ ', negativeColor: 'red', negativeParens: true});
	        	formatter.format(data, 1);

	        	var options = {
	        			width: '100%',
	        			height: '100%',
	        			legend: { position: 'bottom', maxLines: 3 },
	        			slices: {  1: {offset: 0.0},
	        				1: {offset: 0.0},
	        				2: {offset: 0.0},
	        				3: {offset: 0.0},
	        			},
	        			is3D: true,
	        	};

	        	$scope.chartRecebido = new google.visualization.PieChart(document.getElementById('chartRecebido'));
	            $scope.chartRecebido.draw(data, options);

	            var data = google.visualization.arrayToDataTable([
	                                                              ['Adquirente', 'Valor'],
	                                                              ['Cielo', $scope.totalReceber],
	                                                              ]);
	            
	            var formatter = new google.visualization.NumberFormat(
	    			      {prefix: 'R$ ', negativeColor: 'red', negativeParens: true});
	          	formatter.format(data, 1);

	            var options = {
	            		width: '100%',
	            		height: '100%',
	            		legend: { position: 'bottom', maxLines: 3 },
	            		slices: {  
	            			1: {offset: 0.1},
	            			1: {offset: 0.1},
	            			2: {offset: 0.1},
	            		},
	            		is3D: true,
	            };

	            $scope.chartReceber = new google.visualization.PieChart(document.getElementById('chartReceber'));
	            $scope.chartReceber.draw(data, options);
	            $('#dataTables-conta-corrente').dataTable().fnSort([0, 'desc']);
	            $('html').removeClass('loading');
			},1000);
        } else {
        	$scope.contaCorrenteFiltro.dataInicial = $scope.rangeData.substring(0, 10);
        	$scope.contaCorrenteFiltro.dataFinal = $scope.rangeData.substring(13, 24);
        	$scope.contaCorrente = new ContaCorrente.security($scope.contaCorrenteFiltro);
        	$scope.contaCorrente.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
	        	$scope.listaContaCorrenteRetorno = $filter('orderBy')($scope.contaCorrente.listValue, '-data');
	        	
	        	$scope.totalRecebido = 0;
	        	$scope.totalReceber = 0;
	        	for (var i = 0; i < $scope.listaContaCorrenteRetorno.length; i++) {
	        		if ($scope.listaContaCorrenteRetorno[i].status == 'Recebido') {
	        			$scope.totalRecebido = $scope.totalRecebido + parseFloat($scope.listaContaCorrenteRetorno[i].valor); 
	        		} else if ($scope.listaContaCorrenteRetorno[i].status == 'A receber') {
	        			$scope.totalReceber = $scope.totalReceber + parseFloat($scope.listaContaCorrenteRetorno[i].valor);
	        		}
				}; 
				$timeout(function(){
					var data = google.visualization.arrayToDataTable([
		        	                                                  ['Adquirente', 'Valor'],
		        	                                                  ['Cielo', $scope.totalRecebido],
		        	                                                  ]);
		        	var formatter = new google.visualization.NumberFormat(
		  			      {prefix: 'R$ ', negativeColor: 'red', negativeParens: true});
		        	formatter.format(data, 1);

		        	var options = {
		        			width: '100%',
		        			height: '100%',
		        			legend: { position: 'bottom', maxLines: 3 },
		        			slices: {  1: {offset: 0.0},
		        				1: {offset: 0.0},
		        				2: {offset: 0.0},
		        				3: {offset: 0.0},
		        			},
		        			is3D: true,
		        	};

		        	$scope.chartRecebido = new google.visualization.PieChart(document.getElementById('chartRecebido'));
		            $scope.chartRecebido.draw(data, options);

		            var data = google.visualization.arrayToDataTable([
		                                                              ['Adquirente', 'Valor'],
		                                                              ['Cielo', $scope.totalReceber],
		                                                              ]);
		            
		            var formatter = new google.visualization.NumberFormat(
		    			      {prefix: 'R$ ', negativeColor: 'red', negativeParens: true});
		          	formatter.format(data, 1);

		            var options = {
		            		width: '100%',
		            		height: '100%',
		            		legend: { position: 'bottom', maxLines: 3 },
		            		slices: {  
		            			1: {offset: 0.1},
		            			1: {offset: 0.1},
		            			2: {offset: 0.1},
		            		},
		            		is3D: true,
		            };

		            $scope.chartReceber = new google.visualization.PieChart(document.getElementById('chartReceber'));
		            $scope.chartReceber.draw(data, options);
		            $('#dataTables-conta-corrente').dataTable( {
	                    "columnDefs": [
	                        { "type": "date-eu", targets: 0 }
	                    ]
	                } );
	    			$('#dataTables-conta-corrente').dataTable().fnSort([0, 'desc']);
	    			$('html').removeClass('loading');
				},500);
	        });
        }
    };
    
    $scope.mostrarDetalheContaCorrente = function(index) {
    	$('#dataTables-cvs').dataTable().fnDestroy();
    	$scope.parseFloat = parseFloat;
    	$scope.parseInt = parseInt;
    	$scope.detalheContaCorrente.listaCv = [];
    	$scope.detalheContaCorrente.status = $scope.listaContaCorrenteRetorno[index].status;
    	$scope.detalheContaCorrente.data = $scope.listaContaCorrenteRetorno[index].data;
    	$scope.detalheContaCorrente.listaCv = $scope.listaContaCorrenteRetorno[index].listaComprovantes;
    	$timeout(function(){
			$('#dataTables-cvs').dataTable();
		},500);
    };
    
    $timeout(function(){
    	$('#dataTables-conta-corrente').dataTable( {
            "columnDefs": [
                { "type": "date-eu", targets: 0 }
            ]
        } );
	},500);
    
    if ($scope.$root.listaContaCorrentePeloGrafico.length > 0) {
    	$scope.pesquisarContaCorrente();
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
});