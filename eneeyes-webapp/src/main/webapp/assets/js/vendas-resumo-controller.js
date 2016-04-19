google.load('visualization', '1.0', {'packages':['corechart']});

app.controller('VendasResumoController', function ($scope, $filter, $timeout, VendasResumo, DetalheVenda) {
	
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
	
	$scope.pesquisarResumoVendaPorData = function() {
		if ($scope.dataMovimentoResumo != "") {
			var parts = $scope.dataMovimentoResumo.split('/');
			$scope.carregaGraficoResumo(new Date(parts[2],parts[1]-1,parts[0]));
		} else {
			$scope.showMessage("Favor informar uma data.");
		}
	};
	
	function selectAutorizador() {
	    var selectedItem = $scope.chartVenda.getSelection()[0];
	    if (selectedItem) {
	      $scope.detalheVendaFiltro.adquirente = $scope.dataVenda.getColumnLabel(selectedItem.column);
	      $scope.detalheVendaFiltro.data = $("#dataGraficoVendasResumo").val();
	      $scope.detalheVendaFiltro.status = ['EFETUADA'];
	      if ($scope.dataVenda.Ec[selectedItem.row] != undefined) {
	    	  $scope.detalheVendaFiltro.tipoVenda = $scope.dataVenda.Ec[selectedItem.row][0].uf;
	      }
	      $scope.vendas = new DetalheVenda.security($scope.detalheVendaFiltro);
	        $scope.vendas.$pesquisar({_csrf : angular.element('#_csrf').val()}, function(){
	        	if ($scope.vendas.listValue.length > 0) {
	        		$scope.$root.listaPeloGrafico = $scope.vendas.listValue;
	        		$('html').addClass('loading');
	        		$scope.LoadAjaxContent('/security/vendas.html');
	        	}
	        });
	    }
	}
	
	$scope.carregaGraficoResumo = function(data) {
		$('html').addClass('loading');
		$scope.eventDates = {};
		$scope.vendasResumo = new VendasResumo.vendas();
		$scope.vendasResumo.$pesquisar({_csrf : angular.element('#_csrf').val(), data : data}, function(){
	    	if ($scope.vendasResumo.listValue.length > 0) {
	    		$scope.dataGrafico = new google.visualization.DataTable();
	    		
	    		var arrayValores = [];
	    		var arrayCabecalho = [];
	    		var arrayAutorizador = [];
	    		arrayCabecalho.push('Genre');
	    		$scope.totalValores = 0;
	    		$scope.totalValoresDebito = 0;
	    		$scope.totalValoresCredito = 0;
	    		$scope.totalValoresOutros = 0;
	    		
	    		for (var i = 0; i < $scope.vendasResumo.listValue.length; i++) {
	    			var autorizador = $scope.vendasResumo.listValue[i].autorizador;
	    			if ($.inArray(autorizador, arrayAutorizador)  == -1) {
	    				arrayCabecalho.push($scope.vendasResumo.listValue[i].autorizador);
	    			}
	    			var result = $scope.vendasResumo.listValue[i];
	    			if (result.titComplTrans == "D\u00e9bito") {
	    				if ($.inArray(autorizador, arrayAutorizador)  == -1) {
	    					arrayValores.push({"autorizador" : autorizador, "credito" : 0, "debito" : result.valor, "outros" : 0});
	        			} else {
	        				arrayValores[arrayAutorizador.indexOf(autorizador)].debito = result.valor;
	        			}
	    			} else if (result.titComplTrans == "Cr\u00e9dito") {
	    				if ($.inArray(autorizador, arrayAutorizador)  == -1) {
	    					arrayValores.push({"autorizador": autorizador, "credito" : result.valor, "debito": 0, "outros" : 0});
	        			} else {
	        				arrayValores[arrayAutorizador.indexOf(autorizador)].credito = result.valor;
	        			}
	    			} else {
	    				if ($.inArray(autorizador, arrayAutorizador)  == -1) {
	    					arrayValores.push({"autorizador" : autorizador, "credito" : 0, "debito" : 0, "outros": result.valor});
	        			} else {
	        				arrayValores[arrayAutorizador.indexOf(autorizador)].outros = result.valor;
	        			}
	    			}
	    			arrayAutorizador.push($scope.vendasResumo.listValue[i].autorizador);
	    		}
	    		
	    		var arrayValoresCredito = [];
	    		arrayValoresCredito.push('Cr\u00e9dito');
	    		for (var i = 0; i < arrayValores.length; i++) {
	    			arrayValoresCredito.push(arrayValores[i].credito);
	    			$scope.totalValoresCredito = parseFloat($scope.totalValoresCredito) + parseFloat(arrayValores[i].credito);
	    		}
	    		
	    		var arrayValoresDebito = [];
	    		arrayValoresDebito.push('D\u00e9bito');
	    		for (var i = 0; i < arrayValores.length; i++) {
	    			arrayValoresDebito.push(arrayValores[i].debito);
	    			$scope.totalValoresDebito = parseFloat($scope.totalValoresDebito) + parseFloat(arrayValores[i].debito);
	    		}
	    		
	    		var arrayValoresOutros = [];
	    		arrayValoresOutros.push('Outros');
	    		for (var i = 0; i < arrayValores.length; i++) {
	    			arrayValoresOutros.push(arrayValores[i].outros);
	    			$scope.totalValoresOutros = parseFloat($scope.totalValoresOutros) + parseFloat(arrayValores[i].outros);
	    		}
	    		
	    		$scope.totalValores = parseFloat($scope.totalValoresCredito) + parseFloat($scope.totalValoresDebito) + parseFloat($scope.totalValoresOutros) ;
	    		
	    		$scope.dataVenda = google.visualization.arrayToDataTable([
	              arrayCabecalho,
	              arrayValoresCredito,
	              arrayValoresDebito,
	              arrayValoresOutros
	            ]);
	    		
	    		var formatter = new google.visualization.NumberFormat(
	    			      {prefix: 'R$ ', negativeColor: 'red', negativeParens: true});
	    		for (var i = 1; i < $scope.dataVenda.getNumberOfColumns(); i++) {
	    			formatter.format($scope.dataVenda, i);
				};
	    		
	            var options = {
	              vAxis: { format:'currency'},
	              width: '100%',
	              height: '100%',
	              legend: { position: 'bottom', maxLines: 3 },
	              bar: { groupWidth: '30%' },
	              isStacked: true,
	              animation: {'startup': true, duration: 1500,},
	            };
	            
	            $scope.chartVenda = new google.visualization.ColumnChart(document.getElementById("chartVendas"));
	            
	            google.visualization.events.addListener($scope.chartVenda, 'select', selectAutorizador);
	            
	            $("#chartVendas").show();
	            $scope.chartVenda.draw($scope.dataVenda, options);
	            
	            $scope.showAlertVendas = false;
	    	} else {
	    		$scope.showAlertVendas = true;
	    		$("#chartVendas").hide();
	    	}
	    	$('html').removeClass('loading');
		});
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
	
	$scope.dataMovimentoResumo = $filter('date')($scope.yesterday, "dd/MM/yyyy");
	$scope.carregaGraficoResumo($scope.yesterday);
});