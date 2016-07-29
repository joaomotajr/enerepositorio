app.controller('companyController', function ($scope, $timeout, $filter, CompanyService, UnitService, AreaService) {
	
	$scope.saveAreaInit = function() {
		
		var area = {
			uid: 0,
			name: $scope.unitAreaInit,				
			unitDto: {uid : $scope.selectedUnit.uid}				
		};
		 
		$scope.inclusaoArea = new AreaService.save(area);
		$scope.inclusaoArea.$area({_csrf : angular.element('#_csrf').val()}, function(){         	
			
			$scope.getOneCompany($scope.companyUid);
		
        });
		 
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
		
		var unit = {
			uid: $scope.selectedUnit.uid = undefined ? 0 : $scope.selectedUnit.uid,
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
	
	$scope.deleteUnit = function() 
	{		 
		 $scope.deletar = new UnitService.deletar();		 
		 $scope.deletar.$unit({_csrf : angular.element('#_csrf').val(), id : $scope.selectedUnit.uid}, function(){			
			 $scope.msgDanger = "Unidade Excluída!!" ;
	         $('#resultDanger').hide().show('slow').delay(1000).hide('slow');         	         	
        }, function(data) {
			 if (data.status >= 400 && data.status <= 505 ) {
				 alert("Sessão");				 			 
			 }
			 else
				 {
				 	alert("Erro " & data);
				 }
		 });		 
	}
	
	$scope.deletarCompany = function() {		 
		 $scope.deletar = new CompanyService.deletar();		 
		 $scope.deletar.$company({_csrf : angular.element('#_csrf').val(), id : $scope.companyUid}, function(){			
			 
			 $scope.msgDanger = "Companhia Excluída!!" ;
	         $('#resultDanger').hide().show('slow').delay(1000).hide('slow');         	         	
        }, function(data) {
        	
			 if (data.status >= 400 && data.status <= 505 ) {
				 alert("Sessão");				 			 
			 }
			 else
				 {
				 	alert("Erro " & data);
				 }
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
 
//	 $scope.editCompany = function (index) {
//	        $scope.companyUid = $scope.companys[index].uid;
//	        
//		    $scope.companyName = $scope.companys[index].name;
//		    $scope.companyDescription = $scope.companys[index].Description;		    
//		    	        
//	        $('#idCompanyName').focus();
//	    }
	 
//	 $scope.deleteCompany = function() {
//		 
//		 $scope.deletar = new CompanyService.deletar();		 
//		 $scope.deletar.$company({_csrf : angular.element('#_csrf').val(), id : $scope.companyUid}, function(){			
//			 
//			 $scope.companys.splice(index, 1);
//         	         	
//         });
//		 
//	 }
	        
	 
	 $scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.selectedCompany = $scope.listOne.t;
			 $scope.itens = getTree();
			 $scope.loadTreview($scope.itens);			 			 
			 
         });		 
	 }

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
				    	 else if(node.type == 1) {
				    		$scope.selectedUnit = node.unit;
				    	 	$scope.LoadAjaxContentCompany('units.html');
				    	 }
				    	 else if(node.type == 2) {
				    		 $scope.selectedArea = node.area;
					    	 $scope.LoadAjaxContentCompany('areas.html');
				    	 }
				    	 else if(node.type == 3) {
				    		 $scope.selectedArea = node.companyDevice;
					    	 $scope.LoadAjaxContentCompany('companyDevices.html');
				    	 }
				    	 
				    	 $timeout(function () {
				    		 $scope.loadIchecks();
				    	 }, 500);
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
 
	 function getTree() {
		 
		var itens = [];
		
		//Empresa
		itens.push({text:  $scope.selectedCompany.name, type : 0, nodes: [] });
		
		for (var i = 0; i < $scope.selectedCompany.unitsDto.length; i++) {
			
			//Unidades
	   		var unidade = "<i class='fa fa-building' style='font-size:1.2em;'></i> " + $scope.selectedCompany.unitsDto[i].name;	   		
			itens[0].nodes.push({text: unidade, nodes: [], type : 1, unit : $scope.selectedCompany.unitsDto[i]});
	
		   for (var j = 0; j < $scope.selectedCompany.unitsDto[i].areasDto.length; j++) {			  			   
			   
			   //Áreas
			   var area = "<i class='fa fa-map-o' style='font-size:1.2em;'></i> " + $scope.selectedCompany.unitsDto[i].areasDto[j].name;
			   itens[0].nodes[i].nodes.push({text: area, nodes: [], type : 2, area: $scope.selectedCompany.unitsDto[i].areasDto[j] });
	
			   for (var k = 0; k < $scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto.length; k++) {
				   
				   //Dispositivos
				   if($scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType == "SENSOR")
					   var device = "<i class='fa fa-map-signs' style='font-size:1.2em;'></i> " + $scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType;
				   else
					   var device = "<i class='fa  fa-keyboard-o' style='font-size:1.2em;'></i> " + $scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k].deviceType;
				   
				   itens[0].nodes[i].nodes[j].nodes.push({text: device,  type : 3, companyDevice : $scope.selectedCompany.unitsDto[i].areasDto[j].companyDevicesDto[k] });
			   }
		   }
		}		 
		
		return itens;
	}
	 
	$scope.loadIchecks = function ()
	{

	    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
	      checkboxClass: 'icheckbox_minimal-blue',
	      radioClass: 'iradio_minimal-blue'
	    });	    
	    
	    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
	      checkboxClass: 'icheckbox_minimal-red',
	      radioClass: 'iradio_minimal-red'
	    });	    
	    
	    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
	      checkboxClass: 'icheckbox_flat-green',
	      radioClass: 'iradio_flat-green'
	    });
	}
	 
	$scope.getCompanys();
	$(".select2").select2();	
		
	$scope.loadIchecks();
});