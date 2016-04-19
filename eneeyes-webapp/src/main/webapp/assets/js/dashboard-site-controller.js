google.load('visualization', '1.0', {'packages':['corechart']});
google.load("visualization", "1.1", {packages:["bar"]});


app.controller('DashboardController', function ($scope, $filter, $timeout, Dashboard, DetalheVenda, ContaCorrente, Pagamentos, Conciliacao, Inconsistencia) {
	
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
			valor : '',
    };
	
	$scope.contaCorrenteFiltro = {
			dataInicial: '',
			dataFinal : '',
			adquirente: '',
			status : '',
    };
	
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

	$scope.conciliacaoFiltro = {
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
	
	$scope.today = new Date();
	$scope.lastMonth = new Date();
	$scope.lastMonth.setMonth($scope.today.getMonth()-1);
	$scope.lastMonth.setDate($scope.lastMonth.getDate()+1);
	
	$scope.nextMonth = new Date();
	$scope.nextMonth.setMonth($scope.today.getMonth()+1);
	$scope.nextMonth.setDate($scope.nextMonth.getDate()-1);
	
	$scope.contaCorrenteFiltro.dataInicial = $filter('date')($scope.lastMonth, 'dd/MM/yyyy');
	$scope.contaCorrenteFiltro.dataFinal = $filter('date')($scope.nextMonth, 'dd/MM/yyyy');
	
	$scope.pagamentosFiltro.dataInicial = $filter('date')($scope.lastMonth, 'dd/MM/yyyy');
	$scope.pagamentosFiltro.dataFinal = $filter('date')($scope.yesterday, 'dd/MM/yyyy');
	
	$scope.conciliacaoFiltro.dataInicial = $filter('date')($scope.lastMonth, 'dd/MM/yyyy');
	$scope.conciliacaoFiltro.dataFinal = $filter('date')($scope.yesterday, 'dd/MM/yyyy');
	
	$scope.inconsistenciaFiltro.dataInicial = $filter('date')($scope.lastMonth, 'dd/MM/yyyy');
	$scope.inconsistenciaFiltro.dataFinal = $filter('date')($scope.yesterday, 'dd/MM/yyyy');
	
	function selectRecebido() {
	    var selectedItem = $scope.chartRecebido.getSelection()[0];
	    if (selectedItem) {
	    	$scope.contaCorrenteFiltro.status = 'Recebido';
	    	$scope.contaCorrente = new ContaCorrente.security($scope.contaCorrenteFiltro);
	        $scope.contaCorrente.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
	        	if ($scope.contaCorrente.listValue.length > 0) {
	        		$scope.$root.listaContaCorrentePeloGrafico = $scope.contaCorrente.listValue;
	        		$('html').addClass('loading');
	        		$scope.LoadAjaxContent('/security/conta-corrente.html');
	        	}
	        });
	    }
	}
	
	function selectReceber() {
		var selectedItem = $scope.chartReceber.getSelection()[0];
	    if (selectedItem) {
	    	$scope.contaCorrenteFiltro.status = 'Receber';
	    	$scope.contaCorrente = new ContaCorrente.security($scope.contaCorrenteFiltro);
	        $scope.contaCorrente.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
	        	if ($scope.contaCorrente.listValue.length > 0) {
	        		$scope.$root.listaContaCorrentePeloGrafico = $scope.contaCorrente.listValue;
	        		$('html').addClass('loading');
	        		$scope.LoadAjaxContent('/security/conta-corrente.html');
	        	}
	        });
	    }
	}
	
	function selectPagamentos() {
	    var selectedItem = $scope.chartPagamentos.getSelection()[0];
	    if (selectedItem) {
	    	$scope.pagamentosFiltro.tipoVenda = $scope.dataPagamentos.getColumnLabel(selectedItem.column);
	    	$scope.pagamentosFiltro.adquirente = $scope.dataPagamentos.getFormattedValue(selectedItem.row, selectedItem.row)
	    	$scope.pagamentos = new Pagamentos.security($scope.pagamentosFiltro);
	        $scope.pagamentos.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
	        	if ($scope.pagamentos.listValue.length > 0) {
	        		$scope.$root.listaPagamentosPeloGrafico = $scope.pagamentos.listValue;
	        		$('html').addClass('loading');
	        		$scope.LoadAjaxContent('/security/pagamentos.html');
	        	}
	        });
	    }
	}
	
	function selectConciliacao() {
	    var selectedItem = $scope.chartConciliacao.getSelection()[0];
	    if (selectedItem) {
	    	$scope.conciliacaoFiltro.tipoVenda = $scope.dataConciliacao.getColumnLabel(selectedItem.column);
	    	$scope.conciliacaoFiltro.adquirente = $scope.dataConciliacao.getFormattedValue(selectedItem.row, selectedItem.row)
	    	$scope.conciliacao = new Conciliacao.security($scope.conciliacaoFiltro);
	    	$scope.conciliacao.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
	        	if ($scope.conciliacao.listValue.length > 0) {
	        		$scope.$root.listaConciliacaoPeloGrafico = $scope.conciliacao.listValue;
	        		$('html').addClass('loading');
	        		$scope.LoadAjaxContent('/security/conciliacao.html');
	        	}
	        });
	    }
	}
	
	function selectInconsistencia() {
	    var selectedItem = $scope.chartInconsistencia.getSelection()[0];
	    if (selectedItem) {
	    	$scope.inconsistenciaFiltro.tipoVenda = $scope.dataInconsistencia.getColumnLabel(selectedItem.column);
	    	$scope.inconsistenciaFiltro.adquirente = $scope.dataInconsistencia.getFormattedValue(selectedItem.row, selectedItem.row)
	    	$scope.inconsistencias = new Inconsistencia.security($scope.inconsistenciaFiltro);
	    	$scope.inconsistencias.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
	        	if ($scope.inconsistencias.listValue.length > 0) {
	        		$scope.$root.listaInconsistenciaPeloGrafico = $scope.inconsistencias.listValue;
	        		$scope.$root.inconsistenciaFiltro = $scope.inconsistenciaFiltro;
	        		$('html').addClass('loading');
	        		$scope.LoadAjaxContent('/security/inconsistencia.html');
	        	}
	        });
	    }
	}
	
	/* Graficos Conta Corrente */
	$scope.carregarGraficoContaCorrente = function() {
		$("#divContaCorrente").css({ 'display': "block" });
		$("#divConciliacao").css({ 'display': "none" });
		$("#divPagamentos").css({ 'display': "none" });
		$("#divInconsistencias").css({ 'display': "none" });
		
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
//	        		    title : '\u00daltimos 30 dias recebido ' + $filter('currency')($scope.totalRecebido, 'R$ '),
//                  	    titleTextStyle : {fontSize: 12},
	        			width: '100%',
	        			height: '100%',
	        			legend: { position: 'bottom', maxLines: 3 },
	        			slices: {  1: {offset: 0.0},
	        				1: {offset: 0.0},
	        				2: {offset: 0.0},
	        				3: {offset: 0.0},
	        			},
	        			is3D: true,
	        			animation: {'startup': true, duration: 2000},
	        	};
	        	
	        	$scope.chartRecebido = new google.visualization.PieChart(document.getElementById('chartRecebido'));
	        	google.visualization.events.addListener($scope.chartRecebido, 'select', selectRecebido);
	            
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
	            		animation: {'startup': true, duration: 2000},
	            };
	
	            $scope.chartReceber = new google.visualization.PieChart(document.getElementById('chartReceber'));
	            google.visualization.events.addListener($scope.chartReceber, 'select', selectReceber);
	            
	            $scope.chartReceber.draw(data, options);
			},500);
        });
    };
	
	// Grafico de pagamentos
	$scope.carregarGraficoPagamentos = function() {
		$("#divContaCorrente").css({ 'display': "none" });
		$("#divConciliacao").css({ 'display': "none" });
		$("#divPagamentos").css({ 'display': "block" });
		$("#divInconsistencias").css({ 'display': "none" });
		$(".btn-primary-dashboard").removeClass("active");
		
    	$scope.pagamentos = new Pagamentos.security($scope.pagamentosFiltro);
    	$scope.totalCredito = 0;
    	$scope.totalDebito = 0;
    	$scope.totalOutros = 0;
        $scope.pagamentos.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
        	$scope.listaPagamentosRetorno = $scope.pagamentos.listValue;
        	for (var i = 0; i < $scope.listaPagamentosRetorno.length; i++) {
	    		if ($scope.listaPagamentosRetorno[i].identificadorProduto != null && $scope.listaPagamentosRetorno[i].identificadorProduto.tipo == "Cr\u00e9dito") {
	    			$scope.totalCredito = $scope.totalCredito + parseFloat($scope.listaPagamentosRetorno[i].valorLiquido); 
	    		} else if ($scope.listaPagamentosRetorno[i].identificadorProduto != null && $scope.listaPagamentosRetorno[i].identificadorProduto.tipo == "D\u00e9bito") {
	    			$scope.totalDebito = $scope.totalDebito + parseFloat($scope.listaPagamentosRetorno[i].valorLiquido);
	    		} else {
	    			$scope.totalOutros = $scope.totalOutros + parseFloat($scope.listaPagamentosRetorno[i].valorLiquido);
	    		}
			}; 
        	$timeout(function(){
        		$scope.dataPagamentos = google.visualization.arrayToDataTable([
          		['Adquirente', 'Cr\u00e9dito', 'D\u00e9bito', 'Outros'],
          		['Cielo', $scope.totalCredito, $scope.totalDebito, $scope.totalOutros],
          		]);
        		
        		var formatter = new google.visualization.NumberFormat(
	    			      {prefix: 'R$ ', negativeColor: 'red', negativeParens: true});
        		for (var i = 1; i < $scope.dataPagamentos.getNumberOfColumns(); i++) {
	    			formatter.format($scope.dataPagamentos, i);
				};

                  var options = {
                    chartArea: {width: '70%'},
                    isStacked: true,
                    legend: { position: 'bottom', maxLines: 3 },
                    hAxis: {
                  	  format:'currency',
                      minValue: 0,
                    },
                    vAxis: {
                      title: 'Adquirentes'
                    },
                    animation: {'startup': true, duration: 2000,},
                  };
                  $scope.chartPagamentos = new google.visualization.BarChart(document.getElementById('chartPagamentos'));
                  google.visualization.events.addListener($scope.chartPagamentos, 'select', selectPagamentos);
                  $scope.chartPagamentos.draw($scope.dataPagamentos, options);
			},500);
        });
	};
	
	// Grafico de conciliacao
	$scope.carregarGraficoConciliacao = function() {
		$("#divContaCorrente").css({ 'display': "none"});
		$("#divConciliacao").css({ 'display': "block" });
		$("#divPagamentos").css({ 'display': "none" });
		$("#divInconsistencias").css({ 'display': "none" });
		$(".btn-primary-dashboard").removeClass("active");
		
		$scope.conciliacao = new Conciliacao.security($scope.conciliacaoFiltro);
        $scope.conciliacao.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
        	$scope.totalCreditoVendas = 0;
        	$scope.totalDebitoVendas = 0;
        	$scope.totalOutrosVendas = 0;
        	
        	$scope.totalCreditoAdquirente = 0;
        	$scope.totalDebitoAdquirente = 0;
        	$scope.totalOutrosAdquirente = 0;
        	
        	for (var i = 0; i < $scope.conciliacao.listValue.length; i++) {
	    		if ($scope.conciliacao.listValue[i].vendaDto.titComplTrans != null && $scope.conciliacao.listValue[i].vendaDto.titComplTrans == "Cr\u00e9dito") {
	    			$scope.totalCreditoVendas = $scope.totalCreditoVendas + parseFloat($scope.conciliacao.listValue[i].vendaDto.valor); 
	    		} else if ($scope.conciliacao.listValue[i].vendaDto.titComplTrans != null && $scope.conciliacao.listValue[i].vendaDto.titComplTrans == "D\u00e9bito") {
	    			$scope.totalDebitoVendas = $scope.totalDebitoVendas + parseFloat($scope.conciliacao.listValue[i].vendaDto.valor);
	    		} else {
	    			$scope.totalOutrosVendas = $scope.totalOutrosVendas + parseFloat($scope.conciliacao.listValue[i].vendaDto.valor);
	    		}
	    		
	    		if ($scope.conciliacao.listValue[i].comprovanteDto.resumos[0].identificadorProduto != null && $scope.conciliacao.listValue[i].comprovanteDto.resumos[0].identificadorProduto.tipo == "Cr\u00e9dito") {
	    			$scope.totalCreditoAdquirente = $scope.totalCreditoAdquirente + parseFloat($scope.conciliacao.listValue[i].comprovanteDto.valorCompraOuParcela); 
	    		} else if ($scope.conciliacao.listValue[i].comprovanteDto.resumos[0].identificadorProduto != null && $scope.conciliacao.listValue[i].comprovanteDto.resumos[0].identificadorProduto.tipo == "D\u00e9bito") {
	    			$scope.totalDebitoAdquirente = $scope.totalDebitoAdquirente + parseFloat($scope.conciliacao.listValue[i].comprovanteDto.valorCompraOuParcela);
	    		} else {
	    			$scope.totalOutrosAdquirente = $scope.totalOutrosAdquirente + parseFloat($scope.conciliacao.listValue[i].comprovanteDto.valorCompraOuParcela);
	    		}
	    		
			};
        	
        	$timeout(function(){
        		$scope.dataConciliacao = google.visualization.arrayToDataTable([
                  ['Genre', 'Cr\u00e9dito', 'D\u00e9bito', 'Outros', { role: 'annotation' } ],
                  ['Cielo', $scope.totalCreditoAdquirente, $scope.totalDebitoAdquirente, $scope.totalOutrosAdquirente, ''],
                  ['Vendas', $scope.totalCreditoVendas, $scope.totalDebitoVendas, $scope.totalOutrosVendas, '']
                ]);
        		
        		var formatter = new google.visualization.NumberFormat(
	    			      {prefix: 'R$ ', negativeColor: 'red', negativeParens: true});
        		for (var i = 1; i < $scope.dataConciliacao.getNumberOfColumns(); i++) {
	    			formatter.format($scope.dataConciliacao, i);
				};
      		
        		var options = {
                        chartArea: {width: '70%'},
                        isStacked: true,
                        legend: { position: 'bottom', maxLines: 3 },
                        bar: { groupWidth: '30%' },
                        hAxis: {
                      	  format:'currency',
                          minValue: 0,
                        },
                        vAxis: { format:'currency'},
                        animation: {'startup': true, duration: 2000,},
                      };

        		$scope.chartConciliacao = new google.visualization.ColumnChart(document.getElementById('chartConciliacao'));
        		google.visualization.events.addListener($scope.chartConciliacao, 'select', selectConciliacao);
        		$scope.chartConciliacao.draw($scope.dataConciliacao, options);
			},500);
        });
		
    	$('html').removeClass('loading');
	};
	
	// Grafico de Inconsistencias
	$scope.carregarGraficoInconsistencias = function() {
		$("#divContaCorrente").css({ 'display': "none" });
		$("#divConciliacao").css({ 'display': "none" });
		$("#divPagamentos").css({ 'display': "none" });
		$("#divInconsistencias").css({ 'display': "block" });
		$(".btn-primary-dashboard").removeClass("active");
		
		$scope.inconsistencias = new Inconsistencia.security($scope.inconsistenciaFiltro);
        $scope.inconsistencias.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
        	$scope.listaInconsistenciaRetorno = $scope.inconsistencias.listValue;
        	$scope.totalCreditoInconsistencia = 0;
        	$scope.totalDebitoInconsistencia = 0;
        	$scope.totalOutrosInconsistencia = 0;
        	for (var i = 0; i < $scope.inconsistencias.listValue.length; i++) {
        		if ($scope.inconsistencias.listValue[i].adquirente == 'Cielo') {
		    		if ($scope.inconsistencias.listValue[i].tipoVenda != null && $scope.inconsistencias.listValue[i].tipoVenda == "Cr\u00e9dito") {
		    			$scope.totalCreditoInconsistencia = $scope.totalCreditoInconsistencia + parseFloat($scope.inconsistencias.listValue[i].valorVenda); 
		    		} else if ($scope.inconsistencias.listValue[i].tipoVenda != null && $scope.inconsistencias.listValue[i].tipoVenda == "D\u00e9bito") {
		    			$scope.totalDebitoInconsistencia = $scope.totalDebitoInconsistencia + parseFloat($scope.inconsistencias.listValue[i].valorVenda);
		    		} else {
		    			$scope.totalOutrosInconsistencia = $scope.totalOutrosInconsistencia + parseFloat($scope.inconsistencias.listValue[i].valorVenda);
		    		}
        		}
			};
        	$timeout(function(){
    			
        		$scope.dataInconsistencia = google.visualization.arrayToDataTable([
                   ['Genre', 'Cr\u00e9dito', 'D\u00e9bito', 'Outros', { role: 'annotation' } ],
                   ['Cielo', $scope.totalCreditoInconsistencia, $scope.totalDebitoInconsistencia, $scope.totalOutrosInconsistencia, '']
                 ]);
    			 
    			 var formatter = new google.visualization.NumberFormat(
	    			      {prefix: 'R$ ', negativeColor: 'red', negativeParens: true});
    			 for (var i = 1; i < $scope.dataInconsistencia.getNumberOfColumns(); i++) {
	    			formatter.format($scope.dataInconsistencia, i);
    			 };
       		
    			 var options = {
	                 chartArea: {width: '70%'},
	                 isStacked: true,
	                 legend: { position: 'bottom', maxLines: 3 },
	                 bar: { groupWidth: '30%' },
	                 hAxis: {
	               	  format:'currency',
	                   minValue: 0,
	                 },
	                 vAxis: { format:'currency'},
	                 animation: {'startup': true, duration: 2000,},
	               };
               
               $scope.chartInconsistencia = new google.visualization.ColumnChart(document.getElementById("chartInconsistencias"));
               google.visualization.events.addListener($scope.chartInconsistencia, 'select', selectInconsistencia);
               $scope.chartInconsistencia.draw($scope.dataInconsistencia, options);
        		
        		
			},500);
        });
		

    	$('html').removeClass('loading');
	};
	$scope.carregarGraficoContaCorrente();
});