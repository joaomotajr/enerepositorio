
angular.element(document).ready(function() {
    var bootElement = document.getElementById("tableExample");
    angular.bootstrap(bootElement, ['angularDatatable']);
});

app.controller('datatableController', function ($scope, $timeout, $filter, ViewService ) {
	

	$scope.getCompaniesAlarm = function() {
		                                                    
		 $scope.listAllDashCompaniesAlarm = new ViewService.listAllDashCompaniesAlarm();		 
		 $scope.listAllDashCompaniesAlarm.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 		 
			 console.log($scope.listAllDashCompaniesAlarm.list);    	
       });		 
	 }

       
       $scope.selections = function () {
           return $scope.contacts.filter(function (contact) {
       		return contact.selected;
       	})
       }
       
       $scope.dataTableOptions = {
           //TODO: move some of this into datatable directive?
           columns: [               
               { title: "ID" },
               { title: "Empresa" },
               { title: "Área" },
               { title: "Detector" },
               { title: "Gás" },
               { title: "Alarme" }
           ],
           columnMap: function (p) { //thing I made up
               return [ p.uid, p.company_name, p.area_name, p.company_detector_name, p.gas_name, p.alarmType ]
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
       
       $scope.getCompaniesAlarm();

       angular.element('body').removeClass('loading');
		
});