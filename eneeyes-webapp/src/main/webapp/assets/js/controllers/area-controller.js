app.controller('areaController', function ($scope, $timeout, $filter, AreaService, CompanyDetectorService, DetectorService, CompanyDeviceService, CompanyService) {

	var loadGauge = false;
	angular.element('body').addClass('loading');
	
	$scope.showDanger = function(msg) {		
		angular.element('body').removeClass('loading');
		 $scope.$root.msgDanger = msg ;
        $('#resultDanger').hide().show('slow').delay(1000).hide('slow');	
	}
	
	$scope.showInfo = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.$root.msgInfo = msg;
        $('#resultInfo').hide().show('slow').delay(1000).hide('slow');
	}
	
	$scope.showErro = function(msg) {
		angular.element('body').removeClass('loading');            
        $scope.$root.msgErro = msg;        
	}
	
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.$root.selectedCompany = $scope.listOne.t;
			 $scope.itens = getTree();
			 $scope.loadTreview($scope.itens);			 
	    });		 
	}
		
	$scope.lockImageArea = function() {		
		
		$("#idImageArea").toggleClass("disableDiv");		
	}
	
	$scope.saveCompanyDeviceInit = function() {
		angular.element('body').addClass('loading');
		
		var companyDevice = {
			uid: 0,
			deviceType: $scope.sensorDetectionType.uid,
			areaDto: {uid : $scope.selectedArea.uid}				
		};
		
		$scope.inclusaoCompanyDevice = new CompanyDeviceService.save(companyDevice);
		$scope.inclusaoCompanyDevice.$companyDevice({_csrf : angular.element('#_csrf').val()}, function(){         	
							     
			$scope.clearFormArea();				        	           
			$scope.getOneCompany($scope.companyUid);
			
			$scope.showInfo($scope.inclusaoCompanyDevice.message);
						
		}, function(data) {
			$scope.showErro("Ops: " + data.statusText);
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
			classified: $scope.selectedArea.classified == 1 ? true : false,	
			unitDto: {uid : $scope.selectedUnit.uid}				
		};
		 
		$scope.inclusaoArea = new AreaService.save(area);
		$scope.inclusaoArea.$area({_csrf : angular.element('#_csrf').val()}, function(){
			
			if($scope.selectedArea.uid == undefined) {
				$scope.clearFormArea();
				$scope.getOneCompany($scope.companyUid);
			}
			
			$scope.showInfo($scope.inclusaoArea.message);
		
		}, function(data) {
			$scope.showErro("Erro: " + data.statusText);
		});			 
	}
	
	$scope.saveCompanyDetectorCoords = function() {
		
		var detectorsCoords = JSON.parse(localStorage.getItem('easypin'));		
		var item = $scope.selectedCompanyDetectorsArea;
		
		if(item == null || detectorsCoords == null) return;
		
		for (var i = 0; i < item.length; i++) {
	    	
			if($scope.selectedCompanyDetectorsArea[i].latitude != detectorsCoords.imgDipositivosArea[i].coords.lat ||
					$scope.selectedCompanyDetectorsArea[i].longitude != detectorsCoords.imgDipositivosArea[i].coords.long) {
			 
				$scope.selectedCompanyDetectorsArea[i].latitude = detectorsCoords.imgDipositivosArea[i].coords.lat;
			 	$scope.selectedCompanyDetectorsArea[i].longitude = detectorsCoords.imgDipositivosArea[i].coords.long;
			 			 
			 $scope.updateCompanyDetectorLatitudeLongitude($scope.selectedCompanyDetectorsArea[i].latitude, 
					 $scope.selectedCompanyDetectorsArea[i].longitude, 
					 $scope.selectedCompanyDetectorsArea[i].uid);
			}
	    }
	}
	
	$scope.updateCompanyDetectorLatitudeLongitude = function(latitude, longitude, id) {
		angular.element('body').addClass('loading');
						 
		$scope.updateLatitudeLongitude = new CompanyDetectorService.updateLatitudeLongitude();
		$scope.updateLatitudeLongitude.$companyDetector({_csrf : angular.element('#_csrf').val(), latitude: latitude, longitude: longitude, id : id }, function(){		
			
			$scope.showInfo($scope.updateLatitudeLongitude.message);
		
		}, function(data) {
			$scope.showErro("Ops:: " + data.statusText);
		});			 
	}
	
	$scope.newArea = function () {
		$scope.btnNewArea = false;
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
		
		$scope.areaNameInit = undefined;
		$scope.sensorDetectionType = '';
 
		$('#idAreaName').select();
	}
	
	$scope.deleteArea = function() 
	{		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new AreaService.deletar();	
		
		$scope.deletar.$area({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid}, function(){
			
			$scope.clearFormArea();
			$scope.getOneCompany($scope.companyUid);
			
			$scope.showDanger($scope.deletar.message);
	                 	         	
        }, function(data) {
        	$scope.showErro("Ops:: " + data.statusText);
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
		
		$timeout(function () {
			angular.element('body').removeClass('loading');				
		}, 200);
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
	
	$scope.deviceTypes = 
	[
	 	{ name : 'OUTROS', uid : 0 },
	 	{ name : 'DETECTOR', uid :  1 },
	 	{ name : 'PLC', uid : 2 },
	 	{ name : 'CONTROLADORA', uid : 3 },
	 	{ name : 'ALARME', uid : 4 } 			  	
	];	 	 
	
	$scope.selectedUnit = {};
	$scope.selectedArea = {};
	
	angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex], $scope.selectedUnit);
	angular.copy($scope.$root.selectedCompany.unitsDto[$scope.$root.selecteds.unitIndex].areasDto[$scope.$root.selecteds.areaIndex], $scope.selectedArea);
	
	$scope.btnNewArea = true;
	$scope.initializeArea();
		
});