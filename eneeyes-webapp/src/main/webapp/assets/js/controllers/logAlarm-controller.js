
angular.element(document).ready(function() {
    var bootElement = document.getElementById("tableExample");
    angular.bootstrap(bootElement, ['angularDatatable']);
});

app.controller('logAlarmController', function ($scope, $timeout, $filter, ViewService, PositionAlarmMessageService) {

	dashCompaniesAlarm = [];
	
	$scope.getCompaniesAlarm = function() {
		                                                    
		 $scope.listAllDashCompaniesAlarm = new ViewService.listAllDashCompaniesAlarm();		 
		 $scope.listAllDashCompaniesAlarm.$view({_csrf : angular.element('#_csrf').val()}, function(){
			 		 
			 if($scope.listAllDashCompaniesAlarm != null && $scope.listAllDashCompaniesAlarm.list.length > 0 )
				 $scope.dashCompaniesAlarm = $scope.listAllDashCompaniesAlarm.list;
       });		 
	}

	
	$scope.dataTableOptions = {
					
       //TODO: move some of this into datatable directive?
       columns: [          
                
           { title: "" },
           { title: "Empresa" },
           { title: "Unidade" },           
           { title: "Detector" },
           { title: "Alarme" },
           { title: "Status" },
           { title: "Data/Hora" },
           { title: "Ação" }
       ],
       columnMap: function (p) { //thing I made up
                   return [null, p.company_name, p.unit_name, p.company_detector_name, p.alarmType, p.alarmStatus, new Date(p.first_update).toLocaleString(),
                    "<button id='" + p.uid + "' class='btn btn-default btn-xs'>Histórico</button>"]
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
	
	$('#idDatatable').on('click', 'tbody td', function() {
		
		if(this.innerText=='Histórico') {
			var uid = $(this).find("button").attr('id');		
			
			$scope.selectedPositionAlarm = $.grep($scope.dashCompaniesAlarm, function (e) { return e.uid == uid ; })[0];
			$scope.getPositionAlarmMessage(uid);
			
			$timeout(function () {
	            $('#modalShowMessages').modal({ show: 'false' });                        
	        }, 200);
		}
	})
	
	$scope.getPositionAlarmMessage = function(positionAlarmId) {
						 
		$scope.inclusaoPositionAlarmMessage = new PositionAlarmMessageService.listByPositionAlarmId(positionAlarmId);
		$scope.inclusaoPositionAlarmMessage.$positionAlarmMessage({_csrf : angular.element('#_csrf').val(), id : positionAlarmId }, function(){
		
			$scope.selectedPositionAlarmMessages = $scope.inclusaoPositionAlarmMessage.list;						 
		});
	}
   
   $scope.getCompaniesAlarm();

   angular.element('body').removeClass('loading');
    		
});