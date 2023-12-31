app.controller('logHistoricFastController', function ($scope, $timeout, CompanyService, CompanyDeviceService, HistoricFastViewerService, ViewService) {

	var loadGoogleCharts = false;	
	$scope.countHistoric = 0;
	var companyDetectors = [];
	
	function printData() {	
	   var divToPrint = document.getElementById("printTable");	   	   
	   divToPrint.style.visibility = "visible";	   
	   newWin= window.open("");
	   newWin.document.write(divToPrint.outerHTML);
	   newWin.print();
	   newWin.close();	   
	   divToPrint.style.visibility = "hidden";
	}

	$('#exportRel').on('click',function(){
		printData();
	});	
	
	$("#exportExcel").click(function(e) {
	    window.open('data:application/vnd.ms-excel,' + encodeURIComponent( $('div[id$=dvData]').html()));
	    e.preventDefault();
	});	
	 	
	$scope.buttonClick = function (s) { 
		$scope.selectedButton = s; 
	};
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.msg = msg;
        $('#result').hide().show('slow').delay(500).hide('slow');
	};
	
	$scope.clearTipoGrupo = function(grupo) {
		$scope.listHistoricInterval = undefined;
	};
	
	$scope.clearHistoric = function() {
		
		if($scope.$root.isFrom == "MASTER")
			$scope.selectedCompany = '';

		$scope.selectedUnit = '';
		$scope.units = '';
		$scope.selectedArea = '';		
		$scope.areas = '';
		$scope.selectedCompanyDetector = '';
		$scope.companyDetectors = '';
        $scope.findedCompanyDetector = '';
        $scope.listHistoricInterval = undefined;
        $scope.dateInF = undefined;
        $scope.dateOutF = undefined;
		$scope.tipoGrupoF = 1;        			
		$scope.selectedButton  = undefined;
	};			

	$scope.enumInterval = ({
        UMA_HORA: "UMA_HORA",
        SEIS_HORAS: "SEIS_HORAS",
        DOZE_HORAS: "DOZE_HORAS",
        UM_DIA: "UM_DIA",
        DOIS_DIAS: "DOIS_DIAS",
        SETE_DIAS: "SETE_DIAS",
		UM_MES: "UM_MES",
		CUSTOM: "CUSTOM"
	});		

	$scope.interval = $scope.enumInterval.UMA_HORA;
	$scope.getHistorics = function() {
		$scope.loading = true;
		if($scope.interval == $scope.enumInterval.CUSTOM) {			
			$scope.getHistoricInterval();
		} else {			
			$scope.getHistoricsPreDefined();
		}
	};
	
	$scope.getHistoricsPreDefined = function() {				
		$scope.selectedPeriodo = setInterval($scope.interval);
		$scope.selectedButton = $scope.interval;
		if($scope.tipoGrupoF == 1)
			$scope.listHistoricInterval = new HistoricFastViewerService.listPreDefined();
		else if($scope.tipoGrupoF == 2)
			$scope.listHistoricInterval = new HistoricFastViewerService.listPreDefinedGroupHours();
		else if($scope.tipoGrupoF == 3)
			$scope.listHistoricInterval = new HistoricFastViewerService.listPreDefinedGroupDays();		
				
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(), 
			companyDeviceId: $scope.selectedCompanyDetector.companyDeviceId, 
			interval: $scope.interval
		}, function(){
			$timeout(function () {
			if($scope.listHistoricInterval != null && $scope.listHistoricInterval.list != null > 0 && $scope.listHistoricInterval.list.length > 0) {								
				if($scope.listHistoricInterval != null && $scope.listHistoricInterval.list.length > 0 && ! $('#btnSelDevice').children('i').hasClass('fa-plus')) 
					$(function() { $('#btnSelDevice').click(); })  	
			}			
			$scope.loading = false;
			}, 500);		
		});		
	};
		
	$scope.getHistoricInterval = function() {
		var dataInicio = new Date($('#dateInF').data().DateTimePicker.date._d);
		var dataFim = new Date($('#dateOutF').data().DateTimePicker.date._d);
		if (dataFim < dataInicio) {			
			$scope.daysDiff ="ATEN��O! Data Final Precisa ser Maior que Inicial.";
			$("#snoAlertBox").fadeIn();			
			window.setTimeout(function () { $("#snoAlertBox").fadeOut(300)}, 3000);			
			daysExceed = true;
			return;
		}

		$scope.selectedPeriodo = dataInicio.toLocaleString() + ' & ' + dataFim.toLocaleString();		
		$scope.selectedButton = 100;		
		if($scope.tipoGrupoF == 1)			
			$scope.listHistoricInterval = new HistoricFastViewerService.listInterval();
		else if($scope.tipoGrupoF == 2)
			$scope.listHistoricInterval = new HistoricFastViewerService.listIntervalGroupHours();
		else if($scope.tipoGrupoF == 3)
			$scope.listHistoricInterval = new HistoricFastViewerService.listIntervalGroupDays();
				
		$scope.listHistoricInterval.$historic({_csrf : angular.element('#_csrf').val(),			
			companyDeviceId: $scope.selectedCompanyDetector.companyDeviceId, 
			dateIn: dataInicio,
			dateOut: dataFim
		}, function() {			
			$scope.loading = false;
			if($scope.listHistoricInterval != null && $scope.listHistoricInterval.list && $scope.listHistoricInterval.list.length > 0 && ! $('#btnSelDevice').children('i').hasClass('fa-plus')) 
				$(function() { $('#btnSelDevice').click(); })     	
       });		
	};	
		
	function setInterval(interval) {		
		if ( interval == $scope.enumInterval.UMA_HORA )
			return  "�ltima Hora";
		else if ( interval == $scope.enumInterval.SEIS_HORAS )
			return "�ltimas Seis Horas";
		else if ( interval == $scope.enumInterval.DOZE_HORAS )
			return "�ltimas Doze Horas";
		else if ( interval == $scope.enumInterval.DOIS_DIAS )
			return "�ltimas Dois Dias";
		else if ( interval == $scope.enumInterval.SETE_DIAS )
			return "�ltimos Sete Dias";
		else if ( interval == $scope.enumInterval.UM_MES )
			return "�ltimo M�s";
		else 
			return 'Desconhecido';				
	}
	
	$scope.getCompanyDetectors = function() {		 
		 $scope.listAllDashCompany = new ViewService.listAllDashCompany();		 
		 $scope.listAllDashCompany.$view({_csrf : angular.element('#_csrf').val()}, function(){
			companyDetectors = $scope.listAllDashCompany.list; 				         	         	
        });		 
	 };
		
	$scope.getCompanys = function() {		 
		 if($scope.$root.isFrom != "MASTER") {		 
			 $scope.getOneCompany($scope.$root.isFrom);
		 } else {		
			 $scope.resultCompanies = new CompanyService.listAllView();		 
			 	$scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
				 $scope.companies = $scope.resultCompanies.list;
	        });		 
		 }
	}; 

	$scope.getOneCompany = function(companyId) {		
	   $scope.listOne = new CompanyService.listOne();		 
	   $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function() {			
		   $scope.selectedCompany = $scope.listOne.t;
		   $scope.changeCompany();
	   });		 
   	};

	$scope.changeCompany = function() {		
		clearSteps(1);
		if($scope.selectedCompany == null) return;	
		$scope.searchUnit($scope.selectedCompany);
	};
		
	$scope.searchUnit = function(company) {		
		$scope.units = [];
		companyDetectors.forEach( function (i) {
			if (i.companyId == company.uid && !unitRepeat($scope.units, i)) {
				$scope.units.push(i);
			}
		});
		if($scope.units.length==1) {
			$timeout(function () {
				$scope.selectedUnit = $scope.units[0];
				$scope.changeUnit();
			}, 200);
		}
	};

	function unitRepeat(itens, item) {
		var units = itens.filter(function(i) {
				return (i.companyId == item.companyId && i.unitId == item.unitId);
			}
		);
		return units.length > 0;
	}

	$scope.changeUnit = function() { 
		clearSteps(2);
		if($scope.selectedUnit == null) return;
		$scope.searchArea($scope.selectedCompany, $scope.selectedUnit.unitId);
	};
	
	$scope.searchArea = function(company, unitId) {
		$scope.areas = [];
		companyDetectors.forEach( function (i) {
			if (i.companyId == company.uid && i.unitId == unitId  && !areaRepeat($scope.areas, i)) {
				$scope.areas.push(i);
			}
		});
		if($scope.areas.length==1) {
			$timeout(function () {				
				$scope.selectedArea = $scope.areas[0];
				$scope.changeArea();
			}, 200);
		}
	};

	function areaRepeat(itens, item) {
		var areas = itens.filter(function(i) {
				return (i.companyId == item.companyId && i.unitId == item.unitId && i.areaId == item.areaId);
			}
		);
		return areas.length > 0;
	}	

	$scope.changeArea = function() { 
		clearSteps(3);
		if($scope.selectedArea == null) return;
		$scope.searchCompanyDetector ($scope.selectedCompany, $scope.selectedUnit.unitId, $scope.selectedArea.areaId);
	};

	$scope.searchCompanyDetector = function(company, unitId, areaId) {
		$scope.companyDetectors = [];
		companyDetectors.forEach( function (i) {
			if (i.companyId == company.uid && i.unitId == unitId && i.areaId == areaId) {
				$scope.companyDetectors.push(i);
			}
		});	
		if($scope.companyDetectors.length==1) {
			$timeout(function () {				
				$scope.selectedCompanyDetector = $scope.companyDetectors[0];
				$scope.changeCompanyDetector();
			}, 200);
		}	
	};

	$scope.changeCompanyDetector = function() {		
		if($scope.selectedCompanyDetector == null) return;
		$scope.getCompanyDevice($scope.selectedCompanyDetector.companyDeviceId);
	};

	$scope.getCompanyDevice = function(uid) {
		$scope.resultCompanyDevice = new CompanyDeviceService.listOne();
		$scope.resultCompanyDevice.$companyDevice({_csrf : angular.element('#_csrf').val(), id : uid }, function(){						
			if (!$scope.resultCompanyDevice.isError) {
				$scope.findedCompanyDevice = $scope.resultCompanyDevice.t;			
			}			
		});
	};

	function clearSteps(step) {
		if (step == 1) {
			$scope.units = [];			
			$scope.selectedUnit = '';			
		}
		if (step == 1 || step == 2) {
			$scope.areas = [];
			$scope.selectedArea = '';
		}
		if (step == 1 || step == 2 || step == 3) {
			$scope.companyDetectors = [];
			$scope.selectedCompanyDetector = '';
		}		
	}
	
	$scope.changedGraphic = function() {
		$scope.count=0;
	};
	
	$scope.showGrafico = function() {		
		if(! loadGoogleCharts) {				
			google.charts.load('current', { 'packages': ['corechart', 'line'] });				
			loadGoogleCharts = true;
		}		
		google.charts.setOnLoadCallback(formatLineSensor);
		$timeout(function () {
			$('#modalGraficoHistoricoF').modal({ show: 'false' });
		}, 300);
	};
	
	function formatLineSensor() {
		var value = $scope.listHistoricInterval.list;		
		var red =     $scope.findedCompanyDevice == null  ? 0 :  $scope.findedCompanyDevice.alarmDto.alarm3;
		var yellow =  $scope.findedCompanyDevice == null  ? 0 :  $scope.findedCompanyDevice.alarmDto.alarm2;
		var orange =  $scope.findedCompanyDevice == null  ? 0 :  $scope.findedCompanyDevice.alarmDto.alarm1;		
		var data = new google.visualization.DataTable();
	      
		if($scope.tipoGrupoF == 1) {
			data.addColumn('string', 'Data');
			data.addColumn('number', 'Valor');
		} else {
			data.addColumn('string', 'Data');
			data.addColumn('number', 'M�ximo');			
			data.addColumn('number', 'M�nimo');
			data.addColumn('number', 'M�dia');
			data.addColumn('number', 'Soma');
		}

	    var itens = [];	    	    
	    for(var i in value) {
	    	var itemDate = new Date( value[i].lastUpdate );
	    	changeDate = weekday[itemDate.getDay()] + ' ' + itemDate.toLocaleDateString() + ' as ' + itemDate.toLocaleTimeString();	    	
	    	if($scope.tipoGrupoF == 1) {
	    		itens.push([changeDate, value[i].value]);
	    	} else {
	    		itens.push([changeDate, value[i].maxValue, value[i].minValue, value[i].avgValue, value[i].sumValue]);
	    	}
		}
	      
	    data.addRows(itens);		
		var options; 
	    if ($scope.changeGraphic) {			
		    options = {	          
		          legend: {position: 'none'},
		          width: 900,
		          height: 450,
		    	  hAxis: {
		    		  gridlines: {color: '#333', count: 4},
		    		  baselineColor: '#fbf6a7',
		    		  textPosition: 'none'	    	  
		    	  },
		    	  vAxis: {	    		  
		    		  maxValue: $scope.selectedCompanyDetector.rangeMax,
					  minValue: 0,
					  avgValue: 0,
					  sumValue: 0,
		    		  textStyle: {
	                      'color': '#8C8C8C',
	                      'fontName': 'Calibri',
	                      'fontStyle' : 'bold',
	                      'fontSize': 9,
	                  },
		    		  ticks: [
		    		          {v:0, f: 'Range M�ximo: 0' }, 
		    		          {v: orange, f: 'Dete��o: ' + orange}, 
		    		          {v: yellow, f: 'Alerta: ' + yellow}, 
		    		          {v: red, f: 'Evacua��o: ' + red}, 
		    		          {v: $scope.selectedCompanyDetector.rangeMax, f: 'Range M�ximo: ' + $scope.selectedCompanyDetector.rangeMax}
		    		        ],
		    	  },
		    	  //curveType: 'function',
		          pointSize:1
		      };
	    } else {
			   options = {				          
		          legend: {position: 'none'},
		          width: 900,
		          height: 450,
		    	  hAxis: {
		    		  gridlines: {color: '#333', count: 4},
		    		  baselineColor: '#fbf6a7',
		    		  textPosition: 'none'	    	  
		    	  },
		    	  vAxis: {		    		  
		    		  textStyle: {
	                      'color': '#8C8C8C',
	                      'fontName': 'Calibri',
	                      'fontStyle' : 'bold',
	                      'fontSize': 9,
	                  },
		    	  },
		    	  //curveType: 'function',
		          pointSize:1
		      };			
		}
	    
	    objChart = document.getElementById("graficoHistorico");
	    var chart = new google.visualization.LineChart(objChart);
	    chart.draw(data, options);	
	}	
	
	$scope.changeToValue = function(valor) {
		if (!valor) {			
			valor = "-";
			return valor;
		} else {
	  		return valor.toString().replace(".", ",");
		}
	};

	$scope.clearHistoric();
	$scope.getCompanys();
	$scope.getCompanyDetectors();
	$scope.tipoGrupoF = 1;	
	
	$timeout(function(){
		angular.element('body').removeClass('loading');		
		$('#dateInF').datetimepicker(
			{ 	defaultDate: new Date(), 
				format:'DD/MM/YYYY HH:mm:ss',
				autoclose: false,
				language: 'br'
			}
		);
		$('#dateOutF').datetimepicker(
			{ 	defaultDate: new Date(), 
				format:'DD/MM/YYYY HH:mm:ss',
				autoclose: true,
	            language: 'br'
			}
		);		
		$("#dateInF").on("dp.change",function (e) {
	        $('#dateOutF').data("DateTimePicker").setMinDate(e.date);
	        // $(this).data('DateTimePicker').hide();
		});
		$("#dateOutF").on("dp.change",function (e) {
	        $('#dateInF').data("DateTimePicker").setMaxDate(e.date);
	        // $(this).data('DateTimePicker').hide();
		});		
	}, 500);	
});