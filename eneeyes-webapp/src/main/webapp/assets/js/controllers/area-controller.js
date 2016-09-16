app.controller('areaController', function ($scope, $timeout, $filter, AreaService, CompanyDetectorService, DetectorService) {

	var loadGauge = false;
		
	$scope.lockImageArea = function() {		
		
		$("#idImageArea").toggleClass("disableDiv");		
	}

	$scope.saveAreaInit = function() {
		angular.element('body').addClass('loading');
		
		var area = {
			uid: 0,
			name: $scope.areaNameInit,				
			unitDto: {uid : $scope.selectedUnit.uid}				
		};
		 
		$scope.inclusaoArea = new AreaService.save(area);
		$scope.inclusaoArea.$area({_csrf : angular.element('#_csrf').val()}, function(){         	
			
			$scope.getOneCompany($scope.companyUid);
			angular.element('body').removeClass('loading');
        });
		 
	 }
	
	$scope.saveArea = function() {
		angular.element('body').addClass('loading');
		
		$scope.saveCompanyDetectorCoords();
		
		var area = {
			uid: $scope.selectedArea.uid == undefined ? 0 : $scope.selectedArea.uid,
			name: $scope.selectedArea.name,
			date: $scope.date = null,
			description: $scope.selectedArea.description,
			local: $scope.selectedArea.local,
			latitude: $scope.selectedArea.latitude,
			longitude: $scope.selectedArea.longitude,
			image: $scope.selectedArea.image,
			classified: $scope.selectedArea.classified,			
			unitDto: {uid : $scope.selectedUnit.uid}				
		};
		 
		$scope.inclusaoArea = new AreaService.save(area);
		$scope.inclusaoArea.$area({_csrf : angular.element('#_csrf').val()}, function(){         	
			
			$scope.getOneCompany($scope.companyUid);
			
			angular.element('body').removeClass('loading');
			$scope.msgInfo = "Unidade Gravada!" ;
           $('#resultInfo').hide().show('slow').delay(1000).hide('slow');
		
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
		});			 
	}
	
	$scope.newArea = function () {
		$scope.btnAreaUnit = false;
		$scope.clearFormArea();
	}
	
	$scope.clearFormArea = function () {
		
		$scope.selectedArea.uid = undefined;
		$scope.selectedArea.name = '';		
		$scope.selectedArea.description = '';
		$scope.selectedArea.local = '';
		$scope.selectedArea.latitude = '';
		$scope.selectedArea.longitude = '';
		$scope.selectedArea.classified = false;		
 
		$('#idAreaName').select();
	}
	
	$scope.deleteArea = function() 
	{		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new AreaService.deletar();	
		
		$scope.deletar.$area({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid}, function(){
			$scope.getOneCompany($scope.companyUid);					
			angular.element('body').removeClass('loading');
			
			$scope.msgDanger = "Área Excluída!!" ;
	        $('#resultDanger').hide().show('slow').delay(1000).hide('slow');         	         	
        }, function(data) {
        	$scope.msgErro = "Erro: " + statusText;
		});		 
	}	
	
	$scope.getCompanyDetectorArea = function() {
		
		$scope.resultCompanyDetectorsArea = new CompanyDetectorService.listPorIdArea();		 
		$scope.resultCompanyDetectorsArea.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid }, function(){			
			$scope.selectedCompanyDetectorsArea = $scope.resultCompanyDetectorsArea.list;          	         	
        });		 
	}
	 
	 /*--------------------------------------------------------------------------   M A P S  &  E V E N T S -----------------------------------------------------------------------*/

	 initializeDetector =  function()
		 {
			 
			$timeout(function () {
				
				$('.tabDetector a').on('click', function (event) {
				    event.preventDefault();	
				    
				    if ($(event.target).attr('href') == "#tabCompanyDetector_2") {			    	
				    	initGaugeDetector();
					}
				    else if ($(event.target).attr('href') == "#tabCompanyDetector_3") {
				    	
				    }			
				});
												
				initGaugeDetector = function() {				
					if(! loadGauge) {
						google.charts.load('current', { 'packages': ['gauge'] });
						loadGauge = true;
					}				
					google.charts.setOnLoadCallback(drawGaugesDetector);
				}				
							
			}, 500);
			
			$("#stepTabDetector_1").trigger("click");	
		 }
	 
	 $scope.initializeArea =  function()
	 {
		$scope.selectedCompanyDetectorsArea = [];
		 
		$timeout(function () {
			
			$('.tabArea a').on('click', function (event) {
			    event.preventDefault();  
			    
			    if ($(event.target).attr('href') == "#tabArea_2") {			    	
			    	initializeEasyPin();			    	
				}
			    else if ($(event.target).attr('href') == "#tabArea_3") {
			    	initGaugeAreas();
			    }
			});
			
			$('#idBtnChooseFileArea').click(function(event) {
			    event.preventDefault();	    
			    $('#idInputImageArea').trigger('click');			    
			});
			 
			$('#idInputImageArea').change( encodeImageFileAsURL( function(base64Img) {				
				$scope.selectedArea.image =  base64Img;
				$scope.$apply();					    
			}));
			
			initGaugeAreas = function() {				
				if(! loadGauge) {
					google.charts.load('current', { 'packages': ['gauge'] });
					loadGauge = true;
				}				
				google.charts.setOnLoadCallback(drawGaugesArea);
			}
			
			$scope.getCompanyDetectorArea();
						
		}, 500);
		
		$("#stepTabArea_1").trigger("click");	
	 }	  
		
	drawGaugesArea = function() {
		
		if($scope.selectedArea.companyDevicesDto.length > 0)
			setDetectorsAreas();		

		//	 setInterval(function () {
		//   data.setValue(2, 1, 60 + Math.round(20 * Math.random()));
		//	 chart.draw(data, options);
		//	 }, 26000);
	}
	
	setDetectorsAreas = function() {
		
		var detectors = $scope.selectedCompanyDetectorsArea;
		for (var i = 0; i < detectors.length; i++) {
			
			var sensors = detectors[i].detectorDto.sensorsDto;		
			
			for (var j = 0; j < sensors.length; j++) {
				
				var gaugeOptions = {
				     width: 220, height: 120,
				     redFrom: 90, redTo: 100,
				     yellowFrom: 75, yellowTo: 90,
				     minorTicks: 5
				};
				
				var gaugeData = new google.visualization.DataTable();
				
				gaugeData.addColumn('number', sensors[j].name);
			    gaugeData.addRows(1);
			    gaugeData.setCell(0, 0, sensors[j].rangeMax);
			    
			    console.log('sensor_' + sensors[j].$$hashKey);			    
			    gauge = new google.visualization.Gauge(document.getElementById('sensor_' + sensors[j].$$hashKey));
			    gauge.draw(gaugeData, gaugeOptions);
				
			}
	    }		
	}
	
	drawGaugesDetector = function() {
					
		var sensors = $scope.selectedCompanyDetector.detectorDto.sensorsDto;		
		
		for (var j = 0; j < sensors.length; j++) {
			
			var gaugeOptions = {
			     width: 220, height: 120,
			     redFrom: 90, redTo: 100,
			     yellowFrom: 75, yellowTo: 90,
			     minorTicks: 5
			};
			
			var gaugeData = new google.visualization.DataTable();
			
			gaugeData.addColumn('number', sensors[j].name);
		    gaugeData.addRows(1);
		    gaugeData.setCell(0, 0, sensors[j].rangeMax);
		    
		    console.log('sensor_' + sensors[j].$$hashKey);			    
		    gauge = new google.visualization.Gauge(document.getElementById('sensor_' + sensors[j].$$hashKey));
		    gauge.draw(gaugeData, gaugeOptions);			
		}	    		
	}
		
	initializeEasyPin = function() {
    	
		var itens;
		var limit = 0;
		$('.easy-marker').remove();
		
		if($scope.selectedArea.companyDevicesDto.length > 0) {
			
	    	itens = getDetectorsCoordinates();
	    	
	    	$timeout(function () {	    		
	    		limit = $scope.selectedCompanyDetectorsArea.length;
	    	}, 300);
	    	
	    	$timeout(function () {           
	    		easyPin(itens, limit);			    		
	    		$('.pin').trigger("click")
	    	}, 600);	    	
		}	
    	    	
    	$scope.lockImageArea();
		
	}
	
	easyPin = function(itens, limit) {			
		
		var imgDipositivosArea = itens;
		
		var $easyInstance = $('.pin').easypin({
			 init: { imgDipositivosArea },           
		    responsive: true,
            limit: limit
		});	
    }	
	
	getDetectorsCoordinates = function() {
				
		var pinItensString = "";
				
		var item = $scope.selectedCompanyDetectorsArea;
		for (var i = 0; i < item.length; i++) {
	    				
			var pinItem = ({
					content: item[i].name,
					uid: item[i].uid,
					coords: {
		                lat: item[i].latitude == null ? 0 :  item[i].latitude,
		                long: item[i].longitude == null ? 0 : item[i].longitude
		            }
				})				
							
			pinItensString += (String.fromCharCode(34) + i + String.fromCharCode(34) + ":" + JSON.stringify(pinItem) + (i == item.length -1 ? ', "canvas":{}' : ', '));				
			 
	    }
		
		return JSON.parse('{' + pinItensString + '}');;
	}
	
	$scope.selectedUnit = $scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex];
	$scope.selectedArea = $scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex];			
	$scope.initializeArea();
		
});