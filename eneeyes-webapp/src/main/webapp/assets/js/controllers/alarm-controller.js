
app.controller('alarmController', function ($scope, $timeout, $filter, AlarmService, GasService, CompanyService, ViewService) {
	
	$scope.saveAlarm = function() {
		
		angular.element('body').addClass('loading');
							
		var alarm = {
			uid: $scope.alarmUid != undefined ? $scope.alarmUid : 0,
			name: $scope.alarmName,
			gasDto: $scope.alarmGas,			
			unitMeterGases : $scope.gasUnitMeterGases.uid,
			alarm1 : $scope.alarmAlarm1,
			alarm2 : $scope.alarmAlarm2,
			alarm3 : $scope.alarmAlarm3,
			companyDto : $scope.company,
			alarmOn: angular.element('#alarmOn').hasClass('unlocked_inactive') == true ? true : false,
			alarmEmail:  $scope.alarmEmail,
			alarmSound:  $("#checkboxSonoroOnOff").prop('checked'),
			email :	$scope.email,
			alarmSms:  $scope.alarmCelular,
			celular : $scope.celular,
			alarmAction:  $scope.alarmAction,
			action1 : $scope.action1,
			action2 : $scope.action2,
			action3 : $scope.action3
		}; 
		 
		$scope.inclusaoAlarm = new AlarmService.save(alarm);		 
		$scope.inclusaoAlarm.$alarm({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$timeout(function () {				
				$scope.clearFormAlarm();
	            $scope.getAlarms();                     	
	            angular.element('body').removeClass('loading');				 
	         }, 500);           

		});		 
	 }
	
	
	$scope.onOffAlarm = function() {
					
		var alarm = {
			uid: $scope.alarmUid != undefined ? $scope.alarmUid : 0,			
			alarmOn: angular.element('#alarmOn').hasClass('unlocked_inactive') == true ? true : false
    	}; 
		
		$scope.inclusaoAlarm = new AlarmService.onOff(alarm);				 
		$scope.inclusaoAlarm.$alarm({_csrf : angular.element('#_csrf').val()}, function()
		{        	
			$scope.getAlarms(); 

		});		 
	 }
		 
	$scope.clearFormAlarm = function () {
	
	    $scope.alarmUid = undefined;  
	    $scope.alarmName = '';	    
	    $scope.alarmGas = '';
	    $scope.gasUnitMeterGases = '';
		$scope.alarmAlarm1 = '';
		$scope.alarmAlarm2 = '';
		$scope.alarmAlarm3 = '';	
		$scope.company = '';
	}
	 
	$scope.getAlarms = function() {
		 
		 $scope.resultAlarms = new AlarmService.listAll();		 
		 $scope.resultAlarms.$alarm({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.alarms = $scope.resultAlarms.list;			 
         });		 
	 }	 
	
	 $scope.editAlarm = function (index) {
		 
	        $scope.alarmUid = $scope.alarms[index].uid;
	        $scope.alarmGas = $scope.alarms[index].gasDto;
		    $scope.alarmName = $scope.alarms[index].name;		    
		    $scope.alarmAlarm1 = $scope.alarms[index].alarm1;
		    $scope.alarmAlarm2 = $scope.alarms[index].alarm2;
		    $scope.alarmAlarm3 = $scope.alarms[index].alarm3;
		    $scope.gasUnitMeterGases = $scope.getUnitMetersGases($scope.alarms[index].unitMeterGases);
			$scope.company = $scope.alarms[index].companyDto;
			$scope.email = $scope.alarms[index].email;
			$scope.celular = $scope.alarms[index].celular;
			$scope.action1 = $scope.alarms[index].action1;
			$scope.action2 = $scope.alarms[index].action2;
			$scope.action3 = $scope.alarms[index].action3;
									
			$("#checkboxActionOff").prop('checked', $scope.alarms[index].alarmAction);
			showAction($scope.alarms[index].alarmAction);
			
			$("#checkboxSonoroOnOff").prop('checked', $scope.alarms[index].alarmSound);
			
			$("#checkboxEmailOnOff").prop('checked', $scope.alarms[index].alarmEmail); 
			showEmail($scope.alarms[index].alarmEmail);
			
			$("#checkboxSmsOnOff").prop('checked', $scope.alarms[index].alarmSms); 
			showCelular($scope.alarms[index].alarmSms);
			
			if( $scope.alarms[index].alarmOn != true )  
			{
				$("#travar").addClass("disableDiv");
				
				$('#toggle_event_editing button').eq(1).addClass('locked_active btn-default').removeClass('unlocked_active bg-black');
				$('#toggle_event_editing button').eq(0).addClass('unlocked_inactive bg-black').removeClass('locked_active btn-default');
			}				
			else {
				$("#travar").removeClass("disableDiv");
				
				$('#toggle_event_editing button').eq(0).addClass('locked_active btn-default').removeClass('unlocked_active bg-black');
				$('#toggle_event_editing button').eq(1).addClass('unlocked_inactive bg-black').removeClass('locked_active btn-default');
			}
			
			$scope.validEmail();
			$scope.validMobile();
			
			//$scope.usedAlarms($scope.alarms[index].uid); 
			
			$scope.resultUsedAlarms = new ViewService.listAlarmCompanyDetectorSensorView();		 
			 $scope.resultUsedAlarms.$view({_csrf : angular.element('#_csrf').val(), alarmId : $scope.alarms[index].uid}, function(){			
	 			 
				 $scope.usedAlarms = $scope.resultUsedAlarms.list;
			});
			
			$timeout(function () {
	            $('#modalAlarmEdit').modal({ show: 'false' });                        
	        }, 200);
	 }
	 
	$scope.usedAlarms = function(alarmId) {
		 
		 $scope.resultUsedAlarms = new ViewService.listAlarmCompanyDetectorSensorView();		 
		 $scope.resultUsedAlarms.$view({_csrf : angular.element('#_csrf').val(), alarmId : alarmId}, function(){			
 			 
			 $scope.usedAlarms = $scope.resultUsedAlarms.list;
		});		 		 
	 }
	 
	 $scope.deleteAlarm = function(index) {
		 
		 var uid = $scope.alarms[index].uid;		  
		 
		 $scope.deletar = new AlarmService.deletar();		 
		 $scope.deletar.$alarm({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 			 
			 if (!$scope.deletar.isError)
				 $scope.alarms.splice(index, 1);
			 else {
				 $scope.msgErro = "Erro: " + $scope.deletar.message;
				 console.log($scope.deletar.systemMessage); 
			 }

		});	 
	 }	 
	 
	 $scope.getDetectionTypes = function (name) {
		 
		 for (var i = 0; i < $scope.detectionTypes.length; i++) {
             if ($scope.detectionTypes[i].name == name) {
                 
            	 return $scope.detectionTypes[i];
             }
         } 		 
	 }
	 
	 $scope.getGases = function() {
		 
		 $scope.resultGases = new GasService.listAll();		 
		 $scope.resultGases.$gas({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.gases = $scope.resultGases.list; 		 			 
         });		 
	 }	
	 
	 function getClassNameWithNumberSuffix(el) {
        var className = null;
        var regexp = /\w+\d+/;
        $($(el).attr('class').split(' ')).each(function () {
            if (regexp.test(this)) {
                className = this;
                return false;
            }
        });

        return className;
	 }
	 
	 $scope.getUnitMetersGases = function (name) {
		 
		 for (var i = 0; i < $scope.unitMetersGases.length; i++) {
             if ($scope.unitMetersGases[i].name == name) {
                 
            	 return $scope.unitMetersGases[i];
             }
         } 		 
	 }
	 
	$scope.getCompanys = function() {
		 
		 $scope.resultCompanies = new CompanyService.listAllView();		 
		 $scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function(){			
			 $scope.companies = $scope.resultCompanies.list;
        });		 
	}
	 
	 $scope.unitMetersGases = 
		 [
		  	{ name : 'DESCONHECIDO', uid : 0 },
		  	{ name : 'PPM', uid :  1 },
		  	{ name : 'PPB', uid : 2 },
		  	{ name : 'LEL_PERCENT', uid : 3 },
		  	{ name : 'LEL_PERCENT_METRO', uid : 4 },
		  	{ name : 'PERCENT_VOLUME', uid : 5 }		  	
		 ]; 
	 
	 $('#alarmCelular').keydown(function (e) {
			var key = e.charCode || e.keyCode || 0;
			$phone = $(this);

			if (key !== 8 && key !== 9) {
				if ($phone.val().length === 3) {
					$phone.val($phone.val() + ')');
				}
				if ($phone.val().length === 4) {
					$phone.val($phone.val() + ' ');
				}			
				if ($phone.val().length === 10) {
					$phone.val($phone.val() + '-');
				}
			}

			return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
		})
		
		.bind('focus click', function () {
			$phone = $(this);
			
			if ($phone.val().length === 0) {
				$phone.val('(');
			}
			else {
				var val = $phone.val();
				$phone.val('').val(val);
			}
		})
		
		.blur(function () {
			$phone = $(this);
			
			if ($phone.val() === '(') {
				$phone.val('');
			}
		});
	 
	 
	 $('#toggle_event_editing button').click(function(){
		 event.preventDefault();	    
		 
		$("#travar").toggleClass("disableDiv");			 
		
		$('#toggle_event_editing button').eq(0).toggleClass('locked_inactive locked_active bg-black btn-default');
		$('#toggle_event_editing button').eq(1).toggleClass('unlocked_inactive unlocked_active btn-default bg-black');
	 });
	 
 
	 $("#checkboxSonoroOnOff").click(function(e, parameters) {		 
		 $(".checkboxSonoro").prop('checked', $(this).prop("checked"));         
     });
	 
	 
	 $("#checkboxEmailOnOff").click(function(e, parameters) {		 
		 showEmail($(this).prop("checked"));
     });	 
	 function showEmail (checked) {
		 
		 $(".checkboxEmail").prop('checked', checked);
		 
		 $("#alarmEmail").prop('disabled', !checked );         
		 $("#alarmEmail").prop('readonly', !checked); 
		 		 
		 $scope.alarmEmail = checked;
		 $scope.validEmail();
	 }
	 $scope.validEmail = function ($event) {
	    	

		 if (!$("#checkboxEmailOnOff").prop('checked') || validateEmail( $scope.email )) {
			 $scope.emailValid = true;
		 }
		 else if ( $scope.email == '') {
			 $scope.emailValid = false;
		 }
		 else {
		    $scope.emailValid = false;
		 }
		 
		 if(!$scope.$$phase) 
			 $scope.$apply();
	 }
	 
	 
	 $("#checkboxSmsOnOff").click(function(e, parameters) {		 
		 showCelular($(this).prop("checked"));         
     });
	 function showCelular(checked) {
		 
		 $(".checkboxSms").prop('checked', checked);
		 
		 $("#alarmCelular").prop('disabled', !checked );         
		 $("#alarmCelular").prop('readonly', !checked); 
		 		 
		 $scope.alarmCelular = checked;
		 
		 $scope.validMobile();
	 }	 
	 $scope.validMobile = function ($event) {		 
	 
		 $scope.mobileValid =  (!$("#checkboxSmsOnOff").prop('checked') || ($scope.celular != null && $scope.celular.length == 15));
		 
		 if(!$scope.$$phase) 
			 $scope.$apply();					 
	 }	 
	 
	 $("#checkboxActionOff").click(function(e, parameters) {		 
		 		 
		 showAction($("#checkboxActionOff").prop('checked'));
     });
	 
	 function showAction(checked) {
	 
		 $scope.alarmAction = checked;
		 
		 if(!checked)
			 $(".travarAction").addClass("disableDiv");
		 else
			 $(".travarAction").removeClass("disableDiv");
		 
		 if(!$scope.$$phase) 
			 $scope.$apply();			
	 }	 
	 
     /* ----------------- Processamento ------------------*/
	 
	 $scope.refreshAlarms = function() {
		 $scope.getAlarms();	 
		 $scope.getGases();
		 $scope.getCompanys();
	 }
	 
	 $scope.refreshAlarms();
	 
	 angular.element('body').removeClass('loading');
		
		
});