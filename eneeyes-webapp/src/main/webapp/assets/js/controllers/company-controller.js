app.controller('companyController', function ($scope, $timeout, $filter, CompanyService, UnitService, AreaService, CompanyDeviceService, CompanyDetectorService, DetectorService) {

    var map;
	var geocoder;
	/*----------------------------------------------------------------- C O M P A N Y   D E V I C E----------------------------------------------------------------------*/
	
	$scope.saveCompanyDetector = function() {
		angular.element('body').addClass('loading');
		
		var companyDetector = {
			uid: $scope.selectedCompanyDetector.uid == undefined ? 0 : $scope.selectedCompanyDetector.uid,
			name: $scope.selectedCompanyDetector.name,
			companyDeviceDto: {uid : $scope.selectedCompanyDevice.uid},
			detectorDto: {uid: $scope.selectedCompanyDetector.detectorDto.uid},
			local: $scope.selectedCompanyDetector.local,
			description: $scope.selectedCompanyDetector.description			
		 }
		 
		$scope.inclusaoCompanyDetector = new CompanyDetectorService.save(companyDetector);
		$scope.inclusaoCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val()}, function(){		
			
			angular.element('body').removeClass('loading');
			$scope.msgInfo = "Detector Gravado!" ;
           $('#resultInfo').hide().show('slow').delay(1000).hide('slow');
		
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
		});			 
	}
	
	$scope.getDetectors = function() {
		 
		 $scope.resultDetectors = new DetectorService.listAll();		 
		 $scope.resultDetectors.$detector({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.detectors = $scope.resultDetectors.list; 			 
        });		 
	 }
	
			
	$scope.getOneCompanyDetector = function() {
		
		$scope.getDetectors();
		
		$scope.resultCompanyDetector = new CompanyDetectorService.listPorCompanyDevice();		 
		$scope.resultCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDevice.uid }, function(){			
			$scope.selectedCompanyDetector = $scope.resultCompanyDetector.t;          	         	
        });		 
	}
	
	$scope.getCompanyDetectorArea = function() {
				
		$scope.resultCompanyDetectorsArea = new CompanyDetectorService.listPorIdArea();		 
		$scope.resultCompanyDetectorsArea.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedArea.uid }, function(){			
			$scope.selectedCompanyDetectorsArea = $scope.resultCompanyDetectorsArea.list;          	         	
        });		 
	}
	
	$scope.getCompanyDetector = function(uid) {
		var result = "";
							
		result =  uid + " Image ";          	         	
        	 
		return result;
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
			
			$scope.getOneCompany($scope.companyUid);
			angular.element('body').removeClass('loading');
			
		}, function(data) {
			angular.element('body').removeClass('loading');
			$scope.msgErro = "Erro: " + data.statusText;
		});		 
	 }	
	
	$scope.deviceTypes = 
		[
		 	{ name : 'OUTROS', uid : 0 },
		 	{ name : 'DETECTOR', uid :  1 },
		 	{ name : 'PLC', uid : 2 },
		 	{ name : 'CONTROLADORA', uid : 3 },
		 	{ name : 'ALARME', uid : 4 } 			  	
		];
	
	/*-------------------------------------------------------------------------- A R E A -------------------------------------------------------------------------------*/
		
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
	
	/*-------------------------------------------------------------------------- U N I T -------------------------------------------------------------------------------*/
	
	$scope.newUnit = function () {
		$scope.btnNewUnit = false;
		$scope.clearFormUnit();
	}	
		
	$scope.deleteUnit = function() 
	{		 
		angular.element('body').addClass('loading');		
		$scope.deletar = new UnitService.deletar();	
		
		$scope.deletar.$unit({_csrf : angular.element('#_csrf').val(), id : $scope.selectedUnit.uid}, function(){
			$scope.getOneCompany($scope.companyUid);					
			angular.element('body').removeClass('loading');
			
			$scope.msgDanger = "Unidade Excluída!!" ;
	        $('#resultDanger').hide().show('slow').delay(1000).hide('slow');         	         	
        }, function(data) {
        	$scope.msgErro = "Erro: " + statusText;
		});		 
	}
	
	$scope.clearFormUnit = function () {
		
		$scope.selectedUnit.uid = undefined
	    $scope.selectedUnit.name = '';	    
		$scope.selectedUnit.email  = '';
		$scope.selectedUnit.address  = '';
		$scope.selectedUnit.city  = '';
		$scope.selectedUnit.state  = '';
		$scope.selectedUnit.zip  = '';
		$scope.selectedUnit.phone  = '';
		$scope.selectedUnit.mobile  = '';
		$scope.selectedUnit.url  = '';
		$scope.selectedUnit.unitType  = undefined;
		$scope.selectedUnit.latitude = null;	
		$scope.selectedUnit.longitude = null;
	 
		$('#idUnitName').select();
	}

	$scope.saveUnitInit = function() {
		
		var unit = {
			uid: 0,
			name: $scope.unitNameInit,				
			companyDto: {uid : $scope.selectedCompany.uid}				
		};
		 
		$scope.inclusaoUnit = new UnitService.save(unit);
		$scope.inclusaoUnit.$unit({_csrf : angular.element('#_csrf').val()}, function(){         	
			
			$scope.getOneCompany($scope.companyUid);		
        });
		 
	 }
	
	$scope.saveUnit = function() {
		angular.element('body').addClass('loading');				 
		
		validMapUnit();
		
		var unit = {
			uid: $scope.selectedUnit.uid == undefined ? 0 : $scope.selectedUnit.uid,
			name: $scope.selectedUnit.name,
			email: $scope.selectedUnit.email,
			address: $scope.selectedUnit.address,
			city: $scope.selectedUnit.city,
			state: $scope.selectedUnit.state,
			zip: $scope.selectedUnit.zip,
			phone: $scope.selectedUnit.phone,
			mobile: $scope.selectedUnit.mobile,
			url: $scope.selectedUnit.url,
			unitType: $scope.selectedUnit.unitType,
			latitude: $scope.selectedUnit.latitude,
			longitude: $scope.selectedUnit.longitude,			
			companyDto: {uid : $scope.selectedCompany.uid}				
		};		 
		 
		$scope.inclusaoUnit = new UnitService.save(unit);
		$scope.inclusaoUnit.$unit({_csrf : angular.element('#_csrf').val()}, function(){         	
			
			$scope.getOneCompany($scope.companyUid);
			
			angular.element('body').removeClass('loading');
			$scope.msgInfo = "Unidade Gravada!" ;
           $('#resultInfo').hide().show('slow').delay(1000).hide('slow');
		
       });       
	}
	
	$scope.getUnitAddressValid = function() {
	
		if($scope.selectedUnit.address != null && $scope.selectedUnit.state != null && $scope.selectedUnit.city != null) {
					    	 		
	 		return ($scope.selectedUnit.address == null ? "" : $scope.selectedUnit.address) + ", " + ($scope.selectedUnit.state == null ? "" : $scope.selectedUnit.state) + ", " + ($scope.selectedUnit.city == null ? "" : $scope.selectedUnit.city);				    	 		
	 		
	 	}
	 	else if ( $scope.selectedUnit.zip != null) {
	 		
	 		return ($scope.selectedUnit.zip == null ? "" : $scope.selectedUnit.zip);
	 	}
	 	else {
	 		return "";
	 	}
	}
	
	showResultUnitCoordinates = function (result) {		
		$scope.selectedUnit.latitude = result.geometry.location.lat();
		$scope.selectedUnit.longitude = result.geometry.location.lng();	
		validMapUnit();	
		$scope.$apply();		
	}	 
	
	$scope.getCoordinatesUnit = function () {
	
		getCoordinates(showResultUnitCoordinates,  $scope.getUnitAddressValid() );	
		
	}
	
	validMapUnit = function() {
		$scope.mapUnitOK = ( ($scope.selectedUnit.latitude != null && $scope.selectedUnit.latitude != 0)  && ($scope.selectedUnit.longitude != null && $scope.selectedUnit.longitude != 0) );
		
		if($scope.mapUnitOK)
		{
			$timeout(function () {           
				mapUnit($scope.selectedUnit.latitude, $scope.selectedUnit.longitude );
			}, 500);		
		}
	}
	/*-------------------------------------------------------------------------- C O M P A N Y -----------------------------------------------------------------------------*/
	
	$scope.deleteCompany = function() {
		
		angular.element('body').addClass('loading');
		
		 $scope.deletar = new CompanyService.deletar();		 
		 $scope.deletar.$company({_csrf : angular.element('#_csrf').val(), id : $scope.companyUid}, function(){			
			 
			 $scope.clearFormCompany();
	         $scope.getCompanys();   
			 
			 angular.element('body').removeClass('loading');
			 $scope.msgDanger = "Companhia Excluída!!" ;
	         $('#resultDanger').hide().show('slow').delay(1000).hide('slow');         	         	
        }, function(data) {        	
        	$scope.msgErro = "Erro:: " + statusText;
		 });		 
	}
	
	$scope.saveCompany = function() {
		
		angular.element('body').addClass('loading');		
		var company = {
			uid: $scope.companyUid != undefined ? $scope.companyUid : 0,
			name: $scope.companyName,
			description: $scope.companyDescription		
    	}; 
		 
		$scope.inclusaoCompany = new CompanyService.save(company);		 
		$scope.inclusaoCompany.$company({_csrf : angular.element('#_csrf').val()}, function()
		{            
			
         	$scope.clearFormCompany();
            $scope.getCompanys();   
            
            $timeout(function () {                    
            	$('#selCompany').select2();
            }, 200);
            
            angular.element('body').removeClass('loading');
            
            $scope.msgInfo = "Companhia Gravada!" ;
            $('#resultInfo').hide().show('slow').delay(1000).hide('slow');
                                             	
         });		 
	 }	
		
	$('#selCompany').on('change', function () {
		
		if ($(this).val() == null || $(this).val() == "" )
		{
			$scope.clearFormCompany();			
		}
		else {
			var company =  $.parseJSON ($(this).val());
			
			$scope.companyUid = company.uid;
		    $scope.companyName = company.name;
		    $scope.companyDescription = company.description;
		}
		
	    $scope.$apply();
	    		
	});
	
	$scope.selCompany = function() {
				
		if ($scope.selectedCompanyName == undefined)
		{
			$scope.selectedCompanyName = $scope.companyName;
			$scope.getOneCompany($scope.companyUid);
		}		
		else 
		{
			$scope.clearFormCompany();
			$scope.selectedUnit = undefined;
						
			$scope.getCompanys();
			
			$timeout(function () {                    
            	$('#selCompany').select2();
            }, 100);
		}		
	}
	
	$scope.clearFormCompany = function () {
	
	    $scope.companyUid = undefined;
	    $scope.companyName = '';
	    $scope.companyDescription = '';
	    $scope.selectedCompanyName = undefined;	    
	}
	 
	$scope.getCompanys = function() {
		 
		 $scope.resultCompanies = new CompanyService.listAll();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companies = $scope.resultCompanies.list;
         });		 
	} 	        
	 
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.selectedCompany = $scope.listOne.t;
			 $scope.itens = getTree();
			 $scope.loadTreview($scope.itens);			 
         });		 
	 }

	/*-------------------------------------------------------------------------- T R E E V I E W -----------------------------------------------------------------------------*/
	 $scope.loadTreview = function(data) {
		 
		 var initSelectableTree = function() {
			 return $('#treeview-company').treeview({
					data: data,		
					expandIcon: 'glyphicon glyphicon-chevron-right',
			        collapseIcon: 'glyphicon glyphicon-chevron-down',			          
				    onNodeSelected: function(event, node) {
				    
				    	if(node.type == 0 && $scope.selectedCompany.unitsDto.length <= 0) {				    		 
				    		 $scope.LoadAjaxContentCompany('companyInit.html');
				    	}
				    	else if(node.type == 0 ) {				    		 
				    		 $scope.LoadAjaxContentCompany('company.html');
				    		 initializeCompany();
				    		 
				    		 $timeout(function () {
				    			 mapsCompany();
				    		 }, 500);
				    	}
				    	else if(node.type == 1) {
				    		$scope.btnNewUnit = true;
				    		$scope.selectedUnit = node.unit;
				    		$scope.selectedUnitIndex = node.index;			    		
				    	 	$scope.LoadAjaxContentCompany('units.html');
				    	 	
				    	 	initializeUnit();					    	 				    	 	
				    	}
				    	else if(node.type == 2) {
				    		//Se não foi clicado no Node da Unidade
					    	if($scope.selectedUnit == undefined)
					    		$scope.selectedUnit = $scope.selectedCompany.unitsDto[node.unitIndex];
					    						    	
				    		$scope.btnNewArea = true;
				    		$scope.selectedArea = node.area;
				    		$scope.selectedAreaIndex = node.index;
					    	$scope.LoadAjaxContentCompany('areas.html');				    	 
					    	
					    	initializeArea();
				    	}
				    	else if(node.type == 3) {
				    	
				    		 //Se não foi clicado no Node da Unidade
					    	if($scope.selectedUnit == undefined)
					    		$scope.selectedUnit = $scope.selectedCompany.unitsDto[node.unitIndex];
					    	 
					    	//Se não foi clicado no Node da Área
					    	if($scope.selectedArea == undefined)
					    		$scope.selectedArea = $scope.selectedCompany.unitsDto[node.unitIndex].areasDto[node.areaIndex];
					    	 
				    		$scope.selectedCompanyDevice = node.companyDevice;
				    		$scope.selectedCompanyDeviceIndex = node.index;
				    		 
				    		if (node.companyDevice.deviceType == "DETECTOR") {
				    			$scope.getOneCompanyDetector();				    			 
					    		$scope.LoadAjaxContentCompany('companyDetectors.html');
				    	 	}
				    		else if (node.companyDevice.deviceType == "PLC" || node.companyDevice.deviceType == "CONTROLLER") 
				    			$scope.LoadAjaxContentCompany('companyPlcs.html');					    		 
				    	}
				    }
			     });
			};
			
		var $selectableTree = initSelectableTree();
		
        var findSelectableNodes = function() {
        	return $selectableTree.treeview('search', [ $('#input-select-node').val(), { ignoreCase: false, exactMatch: false } ]);
        };
        
        var selectableNodes = findSelectableNodes();        	

        $('#input-select-node').on('keyup', function (e) {
        	selectableNodes = findSelectableNodes();
        	$('.select-node').prop('disabled', !(selectableNodes.length >= 1));
        });
	 }
 
	  getTree = function() {
		 
		var itens = [];
		
		//Company
		var empresa = $scope.selectedCompany.name + "<small class='label label-default pull-right'></i>" + $scope.selectedCompany.unitsDto.length + "" + "</small>";
		
		itens.push({text: empresa , type : 0, nodes: [] });
		
		for (var i = 0; i < $scope.selectedCompany.unitsDto.length; i++) {
			
			//Unidades
	   		var unidade = "<i class='fa fa-building' style='font-size:1.2em;'></i> " + 
	   			$scope.selectedCompany.unitsDto[i].name + "<small class='label label-default pull-right'></i>" + 
	   			$scope.selectedCompany.unitsDto[i].areasDto.length + "" +  "</small>";	   		
			
	   		itens[0].nodes.push({text: unidade, nodes: [], type : 1, index: i , unit : $scope.selectedCompany.unitsDto[i]});
	
		   for (var j = 0; j < $scope.selectedCompany.unitsDto[i].areasDto.length; j++) {			  			   
			   
			   //Áreas
			   var area = "<i class='fa fa-map-o' style='font-size:1.2em;'></i> " + 
			   		$scope.selectedCompany.unitsDto[i].areasDto[j].name + "<small class='label label-default pull-right'></i>" + 
			   		$scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto.length + "" + "</small>";
			   
			   itens[0].nodes[i].nodes.push({text: area, nodes: [], type : 2, index: j, unitIndex: i , area: $scope.selectedCompany.unitsDto[i].areasDto[j] });
	
			   for (var k = 0; k < $scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto.length; k++) {
				   
				   //Dispositivos
				   if($scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType == "DETECTOR")
					   var device = "<i class='fa fa fa-rss' style='font-size:1.2em;'></i> " + $scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType;
				   else
					   var device = "<i class='fa  fa-keyboard-o' style='font-size:1.2em;'></i> " + $scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType;
				   
				   itens[0].nodes[i].nodes[j].nodes.push({text: device,  type : 3, index: k, areaIndex: j, unitIndex: i, companyDevice : $scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k] });
			   }
		   }
		}		 
		
		return itens;
	 }	 
	 
	 /*--------------------------------------------------------------------------   M A P S  &  E V E N T S -----------------------------------------------------------------------*/
	 
	 function initializeArea()
	 {
		$timeout(function () {
			
			$('.tabArea a').on('click', function (event) {
			    event.preventDefault();  
			    
			    if ($(event.target).attr('href') == "#tabArea_2") {	
			    	
			    	$scope.getCompanyDetectorArea();			    	
				}			   
			});
			
			$('#idBtnChooseFileArea').click(function(event) {
			    event.preventDefault();	    
			    $('#idInputImageArea').trigger('click');			    
			});
			 
			$('#idInputImageArea').change( encodeImageFileAsURL( function(base64Img) {				
				$scope.selectedArea.image =  base64Img;
				initPin();
				$scope.$apply();					    
			}));
			
			initPin();
			 
		}, 450);
				
	 }	

	 initPin = function() {
		 var $easyInstance = $('.pin').easypin({
	         init: '{"example_image1":{"0":{"content":"Captan America","undefined":"save pin!","coords":{"lat":"530","long":"179"}},"1":{"content":"Thor Odinson","undefined":"save pin!","coords":{"lat":"892","long":"109"}},"2":{"content":"Hulk","undefined":"save pin!","coords":{"lat":"56","long":"133"}},"3":{"content":"Black Widow","undefined":"save pin!","coords":{"lat":"717","long":"242"}},"4":{"content":"Hawkeye","undefined":"save pin!","coords":{"lat":"173","long":"221"}},"5":{"content":"Iron Man","undefined":"save pin!","coords":{"lat":"280","long":"161"}},"canvas":{"src":"https://i.ytimg.com/vi/48fKIXlxaXk/maxresdefault.jpg","width":"1000","height":"562"}}}',
	         done: function(element) {
	             return true;
	         }
	     });
	
	     $easyInstance.easypin.event( "get.coordinates", function($instance, data, params ) {
	
	         console.log( data, params);
	
	     });
	
	     $( ".coords" ).click(function( event ) {
	         $easyInstance.easypin.fire( "get.coordinates", {param1: 1, param2: 2, param3: 3}, function(data) {
	             return JSON.stringify(data);
	         });
	     });
	 }
	 
	 function initializeUnit()
	 {	
	 	if (!geocoder) {
	 		$timeout(function () {
				$('.tabUnit a').on('click', function (event) {
					event.preventDefault();
					    
					if ($(event.target).attr('href') == "#tabUnit_2") {
						validMapUnit();
					}
				});
				 
				 geocoder = new google.maps.Geocoder();
				 map = new google.maps.Map(document.getElementById("mapUnit"),
				{				 
					 zoom: 12,
					 center: new google.maps.LatLng(-23.5505199,-46.63330940000003),
					 mapTypeId: google.maps.MapTypeId.ROADMAP
				});		
			 }, 400);			 
		 }
	}
	
	function initializeCompany()
	 {	
	 	if (!geocoder) {
			 $timeout(function () {                    
				 geocoder = new google.maps.Geocoder();
				 map = new google.maps.Map(document.getElementById("mapCompany"),
				{				 
					 zoom: 12,
					 center: new google.maps.LatLng(-23.5505199,-46.63330940000003),
					 mapTypeId: google.maps.MapTypeId.ROADMAP
				});		
			 }, 500);
		 }
	}	       
	
	getCoordinates = function (callBack, address) {
		 
		 var resp = {lat: 0, lng: 0};		 
		 address = address || 'Sao Paulo, Brasil';
		 
		 if (geocoder) {
			 geocoder.geocode({
		        'address': address
			 }, function (results, status) {
				 if (status == google.maps.GeocoderStatus.OK) {		
				 			 
					callBack(results[0]);			 
				 }
			 });
		 }
	 }


	 mapUnit = function (lat, lng) {
	    
	    var myOptions = {
	        zoom: 12,
	        center: new google.maps.LatLng(lat, lng),
	        mapTypeId: google.maps.MapTypeId.ROADMAP
	    };	    
	    
	    map = new google.maps.Map(document.getElementById("mapUnit"), myOptions);        
        var latlng = new google.maps.LatLng(lat, lng);
        
        new google.maps.Marker({
            position: latlng,
            map: map,
            title : $scope.selectedUnit.name
       });       
	}
	
	 mapsCompany = function () {
	    
	    var myOptions = {
	        zoom: 8,
	        center: new google.maps.LatLng(-23.5345239, -46.75677889999997),
	        mapTypeId: google.maps.MapTypeId.ROADMAP
	    };	    
	    
	    map = new google.maps.Map(document.getElementById("mapCompany"), myOptions);
	    
	    for (var i = 0; i < $scope.selectedCompany.unitsDto.length; i++) {
	    	
	        latlng = new google.maps.LatLng($scope.selectedCompany.unitsDto[i].latitude, $scope.selectedCompany.unitsDto[i].longitude);
	        new google.maps.Marker({
	            position: latlng,
	            map: map,
	            title: $scope.selectedCompany.unitsDto[i].name
	        }); 
	    }	    
	}
	 
	$scope.getCompanys();
	$(".select2").select2();
	
});