
app.controller('CompanyDetectorMaintenanceHistoricController', function ($scope, $timeout, $filter, ViewService, 
		CompanyService, CompanyDetectorService, CompanyDetectorMaintenanceHistoricService) {

	var lastDate = "";
	$scope.dateTwoEqual = true;
	$scope.dateOneMaior = true;
	
	
	$scope.changeCompanyDetector = function() {
		          
		if($scope.selectedCompanyDetector == null) return;
		
		$scope.companyDetectorMaintenanceHistoric = undefined;
		$scope.getOneCompanyDetector();				 
	}
	
	$scope.getCompanyDetectorMaintenanceHistoric = function() {		 
		
		$scope.companyDetectorMaintenanceHistoric = new CompanyDetectorMaintenanceHistoricService.listPorCompanyDetector();		
		$scope.companyDetectorMaintenanceHistoric.$companyDetectorMaintenanceHistoric(
				{_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.uid}, function(){
			
			if($scope.companyDetectorMaintenanceHistoric.list != null)
				lastDate = $scope.companyDetectorMaintenanceHistoric.list[ $scope.companyDetectorMaintenanceHistoric.list.length -1].date;
					
		});		 
	}
	
	$scope.changeCompany = function() { 
			
		if($scope.selectedCompany == null) return;
		
		$scope.search = {company: $scope.selectedCompany};
	}
	
	$scope.getOneCompany = function(companyId) {
		 
		 $scope.listOne = new CompanyService.listOne();		 
		 $scope.listOne.$company({_csrf : angular.element('#_csrf').val(), id : companyId}, function(){			
			 
			 $scope.selectedCompany = $scope.listOne.t;
			 
			 $scope.companyUid = $scope.selectedCompany.uid;
			 $scope.companyName = $scope.selectedCompany.name;
			 $scope.companyDescription = $scope.selectedCompany.description;
			 
			 $scope.search = {company: $scope.selectedCompany};
					 			 
	    });		 
	}
	
	$scope.getCompanyDetectors = function() {
				 
		 $scope.listAllDashCompany = new ViewService.listAllDashCompany();		 
		 $scope.listAllDashCompany.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 $scope.companyDetectors = $scope.listAllDashCompany.list; 				         	         	
		 });		
	}
	
	
	$scope.getOneCompanyDetector = function() {
		
		$scope.resultCompanyDetector = new CompanyDetectorService.listOne();		 
		$scope.resultCompanyDetector.$companyDetector({_csrf : angular.element('#_csrf').val(), id : $scope.selectedCompanyDetector.companyDetectorId }, function(){			
			
			$scope.selectedCompanyDetector = $scope.resultCompanyDetector.t;			

			if($scope.selectedCompanyDetector.installDate == null) {
				$scope.clearForm();
				$scope.selectedCompanyDetector = undefined;				
				$scope.msgErroInfoHistoric = "Detector Sem Data de Instalação, Verifique!"
			}
			else {
				lastDate = new Date($scope.selectedCompanyDetector.installDate);
				$scope.msgErroInfoHistoric = "";
				reloadDates();
				$scope.selectedCompanyDetector.companyDetectorId = $scope.selectedCompanyDetector.uid;
				$scope.getCompanyDetectorMaintenanceHistoric();
			}
        });	
	}
	
	$scope.getCompanys = function() {
		 
		 if($scope.$root.isFrom != "MASTER")	{
		 
			 $scope.getOneCompany($scope.$root.isFrom);
		 }
		 else {		
			 $scope.resultCompanies = new CompanyService.listAllView();		 
			 	$scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
				 $scope.companies = $scope.resultCompanies.list;
				 $scope.getCompanyDetectors();
	        });		 
		 }
	 }

	$scope.getHistoricMaintenaceTypes = function (name) {
		 
		 for (var i = 0; i < $scope.historicMaintenaceTypes.length; i++) {
            if ($scope.historicMaintenaceTypes[i].name == name) {
                
           	 return $scope.historicMaintenaceTypes[i];
            }
        } 		 
	 }
	
	$scope.validDateOne = function ($event) {

        if ($('#dateOne').val().match(/[^0-9\/]/g) != null) {
            $scope.dateOneValid = false;
        }	        
        else if ($('#dateOne').val() == '') {
            $scope.dateOneValid = false;            
        }
        else if (getDate( $('#dateOne').val() ) < new Date(lastDate)) {
        	$scope.dateOneMaior = false;            
        }        
        else {
            $scope.dateOneValid = true;
            $scope.dateOneMaior = true;
        }
    }
	
	$scope.validDateTwo = function ($event) {

        if ($('#dateTwo').val().match(/[^0-9\/]/g) != null) {
            $scope.dateTwoValid = false;
        }	        
        else if ($('#dateTwo').val() == '') {
            $scope.dateTwoValid = false ;
        }
        else if ($('#dateOne').val().localeCompare($('#dateTwo').val()) != 0) {
        	$scope.dateTwoValid = false ;
        	$scope.dateTwoEqual = false;
        }
        else {
            $scope.dateTwoValid = true;
            $scope.dateTwoEqual = true;
        }
    }
	
	$scope.clearForm = function () {
			
	    $scope.description = '';
	    $scope.selectedHistoricMaintenaceType = '';
	    	    	    
	    $('#dateOne').val('');						
		$('#dateTwo').val('');
		
		$('#deliveryDate').val('');
		$scope.selectedCompanyDetector.garantyDays = 0;
		$scope.selectedCompanyDetector.descriptionDelivery = '';			
		$('#installDate').val('');
		$scope.selectedCompanyDetector.descriptionInstall = '';
		$scope.selectedCompanyDetector.maintenanceInterval = 0;
	}
	
	function reloadDates() {
		
		if($scope.selectedCompanyDetector.deliveryDate != null) {
			var dataAdm = new Date($scope.selectedCompanyDetector.deliveryDate);	
	        $('#deliveryDate').val(dataAdm.getUTCDate() + "/" + (dataAdm.getUTCMonth() + 1) + "/" + dataAdm.getUTCFullYear());	
		}
		
		if($scope.selectedCompanyDetector.installDate != null) {
			var dataAdm = new Date($scope.selectedCompanyDetector.installDate);	
	        $('#installDate').val(dataAdm.getUTCDate() + "/" + (dataAdm.getUTCMonth() + 1) + "/" + dataAdm.getUTCFullYear());	        
		}	
	}
	
	$scope.saveCompanyDetectorMaintenaceHistoric = function() {
		angular.element('body').addClass('loading');
						
		var companyDetectorMaintenaceHistoric = {
			uid: 0,			
			description: $scope.description,			
			date : getDate($('#dateTwo').val()),
			historicMaintenaceType : $scope.selectedHistoricMaintenaceType.uid,
			companyDetectorDto: {uid: $scope.selectedCompanyDetector.uid}
		 }
		 
		$scope.inclusao = new CompanyDetectorMaintenanceHistoricService.save(companyDetectorMaintenaceHistoric);
		$scope.inclusao.$companyDetectorMaintenanceHistoric({_csrf : angular.element('#_csrf').val()}, function(){		
			
			angular.element('body').removeClass('loading');
			
			$scope.companyDetectorMaintenanceHistoric.list.push($scope.inclusao.t);
			$scope.clearForm();		

		});			 
	}
	
	$("#datemask").inputmask("dd/mm/yyyy", { "placeholder": "dd/mm/yyyy" });
    $("#datemask2").inputmask("mm/dd/yyyy", { "placeholder": "mm/dd/yyyy" });
    $("[data-mask]").inputmask();
	
	$scope.getCompanys();	
	$scope.getCompanyDetectors();
		
	angular.element('body').removeClass('loading');	
});