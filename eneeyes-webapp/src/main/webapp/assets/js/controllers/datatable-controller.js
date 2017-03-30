
angular.element(document).ready(function() {
    var bootElement = document.getElementById("tableExample");
    angular.bootstrap(bootElement, ['angularDatatable']);
});

app.controller('datatableController', function ($scope, $timeout, $filter, CompanyService) {
	

	$scope.getCompaniesAlarm = function() {
		
		$scope.loading = true;	
		                                                    
		 $scope.listAllDashCompaniesAlarm = new ViewService.listAllDashCompaniesAlarm();		 
		 $scope.listAllDashCompaniesAlarm.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 
			 $scope.dashCompaniesAlarm = []; 
			 
			 for(var i = 0; i < $scope.listAllDashCompaniesAlarm.list.length; i++) {				 
				 
				 $scope.dashCompaniesAlarm[i] = $scope.listAllDashCompaniesAlarm.list[i];
				 $scope.dashCompaniesAlarm[i].last_update_full = $scope.dashCompaniesAlarm[i].last_update;
				 $scope.dashCompaniesAlarm[i].last_update = timeSince($scope.dashCompaniesAlarm[i].last_update);				 
				 $scope.dashCompaniesAlarm[i].last_value	= Math.round($scope.dashCompaniesAlarm[i].last_value * 100) / 100;
				 
				 var offDate = (new Date() - new Date($scope.dashCompaniesAlarm[i].last_update_full)) / 1000;
					
					// off line por mais de 5 minutos
					if ( offDate > 300 ) {							 
						$scope.dashCompaniesAlarm[i].offLine = offDate;
					     e.offLine = true;
					}
			 }
				 
			 $scope.loading = undefined;
         	         	
       });		 
	 }

	$scope.contacts = [
           { id: 1, firstName: "John", lastName: "Smith" },
           { id: 2, firstName: "Steve", lastName: "Buscemi" }
       ]
       
       $scope.selections = function () {
           return $scope.contacts.filter(function (contact) {
       		return contact.selected;
       	})
       }
       
       $scope.dataTableOptions = {
           //TODO: move some of this into datatable directive?
           columns: [
               { title: "Select" },
               { title: "ID" },
               { title: "Last name" },
               { title: "First name" }
           ],
           columnMap: function (p) { //thing I made up
               return [ null, p.id, p.firstName, p.lastName ]
           },
           columnDefs: [ {
               orderable: true,
               className: 'select-checkbox',
               targets:   0,
               sortable: true,
               aTargets: [0, 1]
           } ],
           select: { // DataTables Select extension
               style: 'multi', //multi-item selection
               selector: 'td:first-child'
           },
           order: [[ 1, 'asc' ]]
       };

       angular.element('body').removeClass('loading');
		
});