//app.service('testServices', function ($http, $q) {
//
//});

app.controller('companiesController', function ($scope, $timeout, $interval, $filter, CompanyService, UnitService) {

	$scope.$root.timer = [];
		
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
		
	$scope.deleteCompany = function() {
		
		angular.element('body').addClass('loading');
		
		 $scope.deletar = new CompanyService.deletar();		 
		 $scope.deletar.$company({_csrf : angular.element('#_csrf').val(), id : $scope.companyUid}, function(){			
			 
		 	$scope.clearFormCompany();
            $scope.getCompanys();   
            
            $timeout(function () { $('#selCompany').select2();}, 200);                       
            $scope.showDanger($scope.deletar.message);
			 	         
			 	                  	         	
        }, function(data) {        	
        	$scope.show("Ops:: " + statusText);
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
            
            $timeout(function () { $('#selCompany').select2();}, 200);                       
            $scope.showInfo($scope.inclusaoCompany.message) ;
            
                                             	
         });		 
	 }	
		
	$('#selCompany').on('change', function () {
		
		if ($(this).val() == null || $(this).val() == "" )
		{
			$scope.clearFormCompany();			
		}
		else {

			$scope.selectedCompany =  $.parseJSON ($(this).val());
			
			$scope.companyUid = $scope.selectedCompany.uid;
		    $scope.companyName = $scope.selectedCompany.name;
		    $scope.companyDescription = $scope.selectedCompany.description;
		}
		
	    $scope.$apply();
	    		
	});
	
	$scope.selCompany = function() {
				
		if ($scope.selectedCompanyName == undefined)
		{
			$scope.$root.selectedCompany = $scope.selectedCompany; 
			$scope.selectedCompanyName = $scope.companyName;
			$scope.itens = getTree();
			$scope.loadTreview($scope.itens);
		}		
		else 
		{
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
	}
	 
	$scope.getCompanys = function() {
		 
		 $scope.resultCompanies = new CompanyService.listAll();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companies = $scope.resultCompanies.list;
         });		 
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
				    	
				    	//Força atualização de conteudo
				    	$scope.LoadAjaxContentCompany('clear.html');
				    	
				    	$scope.$root.selecteds.push({unitIndex: 0, areaIndex : 0, companyDetectorIndex: 0});
				    	
				    	if(node.type == 0 && $scope.$root.selectedCompany.unitsDto.length <= 0) {				    		 

				    		 $timeout(function () {$scope.LoadAjaxContentCompany('units.html');}, 50);				    		 
				    	}
				    	else if(node.type == 0 ) {
				    		
				    		$timeout(function () {$scope.LoadAjaxContentCompany('company.html');}, 50);				    		 
				    	}
				    	else if(node.type == 1) {
				    		
				    		$scope.$root.selecteds.unitIndex = node.index;				    		
				    		$timeout(function () {$scope.LoadAjaxContentCompany('units.html');}, 50);
				    		
				    	}
				    	else if(node.type == 2) {
				    		
				    		$scope.$root.selecteds.unitIndex = node.unitIndex;					    	
					    	$scope.$root.selecteds.areaIndex = node.index;					    	
					    	$timeout(function () {$scope.LoadAjaxContentCompany('areas.html');}, 50);	
					    	
				    	}
				    	else if(node.type == 3) {				    		 
					    						    
					    	$scope.$root.selecteds.unitIndex = node.unitIndex;					    	
					    	$scope.$root.selecteds.areaIndex = node.areaIndex;					    	
					    	$scope.$root.selecteds.CompanyDeviceIndex = node.index;
				    		 
				    		if (node.companyDevice.deviceType == "DETECTOR") {
				    			
				    			$timeout(function () { $scope.LoadAjaxContentCompany('companyDetectors.html'); }, 50);				    			
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
		var empresa = $scope.$root.selectedCompany.name + "<small class='label label-default pull-right'></i>" + $scope.$root.selectedCompany.unitsDto.length + "" + "</small>";
		
		itens.push({text: empresa , type : 0, nodes: [] });
		
		for (var i = 0; i < $scope.$root.selectedCompany.unitsDto.length; i++) {
			
			//Unidades
	   		var unidade = "<i class='fa fa-building' style='font-size:1.2em;'></i> " + 
	   			$scope.$root.selectedCompany.unitsDto[i].name + "<small class='label label-default pull-right'></i>" + 
	   			$scope.$root.selectedCompany.unitsDto[i].areasDto.length + "" +  "</small>";	   		
			
	   		itens[0].nodes.push({text: unidade, nodes: [], type : 1, index: i , unit : $scope.$root.selectedCompany.unitsDto[i]});
	
		   for (var j = 0; j < $scope.$root.selectedCompany.unitsDto[i].areasDto.length; j++) {			  			   
			   
			   //Áreas
			   var area = "<i class='fa fa-map-o' style='font-size:1.2em;'></i> " + 
			   		$scope.$root.selectedCompany.unitsDto[i].areasDto[j].name + "<small class='label label-default pull-right'></i>" + 
			   		$scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto.length + "" + "</small>";
			   
			   itens[0].nodes[i].nodes.push({text: area, nodes: [], type : 2, index: j, unitIndex: i , area: $scope.$root.selectedCompany.unitsDto[i].areasDto[j] });
	
			   for (var k = 0; k < $scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto.length; k++) {
				   
				   //Dispositivos
				   if($scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType == "DETECTOR")
					   var device = "<i class='fa fa fa-rss' style='font-size:1.2em;'></i> " + $scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType;
				   else
					   var device = "<i class='fa  fa-keyboard-o' style='font-size:1.2em;'></i> " + $scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType;
				   
				   itens[0].nodes[i].nodes[j].nodes.push({text: device,  type : 3, index: k, areaIndex: j, unitIndex: i, companyDevice : $scope.$root.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k] });
			   }
		   }
		}		 
		
		return itens;
	 }
	 
	$scope.getCompanys();	
	angular.element('body').removeClass('loading');
	
	$(".select2").select2();
		
});