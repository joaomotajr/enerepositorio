
app.controller('companiesController', function ($scope, $timeout, $interval, $filter, $rootScope, CompanyService, UnitService) {
		
	$scope.deleteCompany = function() {
		
		angular.element('body').addClass('loading');
		
		 $scope.deletar = new CompanyService.deletar();		 
		 $scope.deletar.$company({_csrf : angular.element('#_csrf').val(), id : $scope.companyUid}, function(){			
			 
		 	$scope.clearFormCompany();
            $scope.getCompanys();   
            
            $timeout(function () { $('#selCompany').select2();}, 200);                       
			$rootScope.showGeneralMessage($scope.deletar.message, 'DANGER');			 	                  	         	
        }, function(data) {        	
        	$scope.show("Ops:: " + statusText);
		 });		 
	}
	
	$scope.saveCompany = function() {		
		angular.element('body').addClass('loading');		
		var company = {
			uid: $scope.companyUid != undefined ? $scope.companyUid : 0,
			name: $scope.companyName,
			description: $scope.companyDescription,
			image: $scope.companyImage,		
    	}; 
		 
		$scope.inclusaoCompany = new CompanyService.save(company);		 
		$scope.inclusaoCompany.$company({_csrf : angular.element('#_csrf').val()}, function()
		{			
         	$scope.clearFormCompany();
            $scope.getCompanys();   
            
            $timeout(function () { $('#selCompany').select2();}, 200);                       
			$rootScope.showGeneralMessage($scope.inclusaoCompany.message, 'SUCCESS');	           
                                             	
         });		 
	 }	
		
	$('#selCompany').on('change', function () {		
		if ($(this).val() == null || $(this).val() == "" )
		{
			$scope.clearFormCompany();			
		} else {			
			$scope.getOneCompany($(this).val());
		}	    		
	});
	
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function() {			 
			 $scope.selectedCompany = $scope.listOne.t;			 
			 $scope.companyUid = $scope.selectedCompany.uid;
			 $scope.companyName = $scope.selectedCompany.name;
			 $scope.companyDescription = $scope.selectedCompany.description;
			 $scope.companyImage = ($scope.selectedCompany.image == null ? "/assets/img/cover.jpg" :  $scope.selectedCompany.image);
	    });		 
	}
	
	$scope.selCompany = function() {
				
		if ($scope.selectedCompanyName == undefined) {
			$scope.$root.selectedCompany = $scope.selectedCompany; 
			$scope.selectedCompanyName = $scope.companyName;
			$scope.itens = getTree();
			$scope.loadTreview($scope.itens);
		} else {
			$scope.clearFormCompany();					
			$scope.getCompanys();			
			$timeout(function () { $('#selCompany').select2(); }, 200);
		}		
	}
	
	$scope.clearFormCompany = function () {	
	    $scope.companyUid = undefined;
	    $scope.companyName = '';
	    $scope.companyDescription = '';
	    $scope.selectedCompanyName = undefined;
		$scope.LoadAjaxContentCompany('clear.html');
		$scope.companyImage = "/assets/img/cover.jpg";		
	}

	$scope.newCompany = function () {

	    $scope.companyUid = 0;
	    $scope.companyName = '';
	    $scope.companyDescription = '';
		$scope.selectedCompanyName = undefined;

		$scope.getCompanys();		
		$timeout(function () { $('#selCompany').select2(); }, 200);		
	}
	 
	$scope.$root.selecteds = [];	
	$scope.loadTreview = function(data) {		
		var initSelectableTree = function() {
			return $('#treeview-company').treeview({
				data: data,		
				expandIcon: 'glyphicon glyphicon-chevron-right',
			    collapseIcon: 'glyphicon glyphicon-chevron-down',			          
				onNodeSelected: function(event, node) {				    				    	
				    //Clear Timer(s)
					while ($scope.$root.timer.length) {				        	
						$interval.cancel($scope.$root.timer.pop());				            
					}										
					$scope.LoadAjaxContentCompany('clear.html');					
					$scope.$root.selecteds.push({unitIndex: 0, areaIndex : 0, companyDetectorIndex: 0});					
					if(node.type == 'COMPANY' && $scope.$root.selectedCompany.unitsDto.length <= 0) {
						$timeout(function () {$scope.LoadAjaxContentCompany('units.html');}, 50);
					} else if(node.type == 'COMPANY' ) {						
						$timeout(function () {$scope.LoadAjaxContentCompany('company.html');}, 200);					}
					else if(node.type == 'UNIT') {						
						$scope.$root.selecteds.unitIndex = node.index;				    		
						$timeout(function () {$scope.LoadAjaxContentCompany('units.html');}, 50);						
					} else if(node.type == 'AREA') {						
						$scope.$root.selecteds.unitIndex = node.unitIndex;					    	
						$scope.$root.selecteds.areaIndex = node.index;					    	
						$timeout(function () {$scope.LoadAjaxContentCompany('areas.html');}, 50);
					} else if(node.type == 'DETECTOR') {												
						$scope.$root.selecteds.unitIndex = node.unitIndex;					    	
						$scope.$root.selecteds.areaIndex = node.areaIndex;					    	
						$scope.$root.selecteds.CompanyDeviceIndex = node.index;				    			
						$timeout(function () { $scope.LoadAjaxContentCompany('companyDetectors.html'); }, 50);
					} else {
						$scope.$root.selecteds.unitIndex = node.unitIndex;					    	
						$scope.$root.selecteds.areaIndex = node.areaIndex;					    	
						$scope.$root.selecteds.CompanyDeviceIndex = node.index;				    			
						$timeout(function () { $scope.LoadAjaxContentCompany('companyGenerics.html'); }, 50);
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
	 };
	 
	 getTree = function() {
		 
		var itens = [];
		var itemBaseLabel = "<small class='label label-default pull-right'></i>";
		
		//Company
		var empresa = $scope.$root.selectedCompany.name + itemBaseLabel + $scope.$root.selectedCompany.unitsDto.length + "" + "</small>";
		
		itens.push({text: empresa , type : 'COMPANY', nodes: [] });		
		
		//unidades
		for (var i = 0; i < $scope.$root.selectedCompany.unitsDto.length; i++) {						
	   		var unidade = "<i class='fa fa-building' style='font-size:1.2em;'></i> " + 
	   			$scope.$root.selectedCompany.unitsDto[i].name + itemBaseLabel + 
	   			$scope.$root.selectedCompany.unitsDto[i].areasDto.length + "" +  "</small>";
			   	itens[0].nodes.push({text: unidade, nodes: [], type : 'UNIT', index: i});			
			//area
		   for (var j = 0; j < $scope.$root.selectedCompany.unitsDto[i].areasDto.length; j++) {			
			   	var area = "<i class='fa fa-map-o' style='font-size:1.2em;'></i> " + 
			   		$scope.$root.selectedCompany.unitsDto[i].areasDto[j].name + itemBaseLabel + 
			   		$scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto.length + "" + "</small>";			   
			   	itens[0].nodes[i].nodes.push({text: area, nodes: [], type : 'AREA', index: j, unitIndex: i});

				//Dispositivos
			   	for (var k = 0; k < $scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto.length; k++) {					
					var device = getDevices($scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k]);
					var deviceType = $scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType;
					itens[0].nodes[i].nodes[j].nodes.push({text: device,  type : deviceType.type, index: k, areaIndex: j, unitIndex: i });					
			   	}
		   }
		}		
		return itens;
	 };

	 function getDevices(detalhe) {
		device = "<span title=" + detalhe.deviceType.type + "><i class='fa " + detalhe.deviceType.symbol + "' style='font-size:1.2em;'></i></span>" +
		"&nbsp;<span style='font-weight: 600; font-family: Helvetica Neue,Helvetica,Arial,sans-serif'>" + detalhe.deviceType.description + "</span>";
		if (detalhe.name == undefined)
			device += "<small class='label label-danger pull-right' style='vertical-align:super;font-size:0.7em'>SEM ID</small>";
		else				
			device += "<small class='label label-default pull-right' style='vertical-align:super;font-size:0.7em'>" + detalhe.name + "</small>";			
		return device;
	 }
	 
	 $scope.getCompanys = function() {
		 
		 if($scope.$root.isFrom != "MASTER") {		 
			 $scope.getOneCompany($scope.$root.isFrom);
		 }
		 else {		
			 $scope.resultCompanies = new CompanyService.listAllView();		 
			 	$scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
				 $scope.companies = $scope.resultCompanies.list;
	        });		 
		 }
	 }; 
	 
	$scope.getCompanys();
		 
	$scope.loadEvents = function() {		
		$('#idInputImageCompany').change( encodeImageFileAsURL( function(base64Img) {		    
		    $scope.companyImage =  base64Img;
			$scope.$apply();		    
		}));		   
		 $scope.addPhoto = function() {
			 event.preventDefault();
			$('#idInputImageCompany').trigger('click');
		 }		
	};
	 	
	$timeout(function(){		
		$scope.loadEvents();		
		angular.element('body').removeClass('loading');
	}, 500);	
	
	$(".select2").select2();		
});