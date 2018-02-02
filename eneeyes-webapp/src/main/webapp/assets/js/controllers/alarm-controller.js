
app.controller('alarmController', function ($scope, $timeout, $filter, AlarmService, GasService, CompanyService, ViewService) {

	$('.modal')
	.on({
		'show.bs.modal': function() {
			var idx = $('.modal:visible').length;
			$(this).css('z-index', 1040 + (10 * idx));
		},
		'shown.bs.modal': function() {
			var idx = ($('.modal:visible').length) - 1; // raise backdrop after animation.
			$('.modal-backdrop').not('.stacked')
			.css('z-index', 1039 + (10 * idx))
			.addClass('stacked');
		},
		'hidden.bs.modal': function() {
			if ($('.modal:visible').length > 0) {
				// restore the modal-open class to the body element, so that scrolling works
				// properly after de-stacking a modal.
				setTimeout(function() {
					$(document.body).addClass('modal-open');
				}, 0);
			}
		}
	});
	
	$scope.saveAlarm = function() {
		
		angular.element('body').addClass('loading');
							
		var alarm = {
			uid: $scope.alarmUid != undefined ? $scope.alarmUid : 0,
			name: $scope.alarmName,
			deviceType: $scope.deviceType.uid,
			gasDto: $scope.alarmGas,			
			unitMeterGases : $scope.gasUnitMeterGases.uid,
			alarm1 : $scope.alarmAlarm1,
			alarm2 : $scope.alarmAlarm2,
			alarm3 : $scope.alarmAlarm3,
			alarm11 : $scope.alarmAlarm11,
			alarm22 : $scope.alarmAlarm22,
			alarm33 : $scope.alarmAlarm33,
			companyDto : $scope.selectedCompany,
			alarmOn: $scope.radioModel,
			alarm2On : $scope.enableAlarm2,
			alarm3On : $scope.enableAlarm3,
			alarmSigma:  $("#checkboxSigmaOnOff").prop('checked'),
			alarmEmail:  $scope.alarmEmail,
			alarmSound:  $("#checkboxSonoroOnOff").prop('checked'),
			email :	$scope.email,
			email1 :	$scope.email1,
			alarmSms:  $scope.alarmCelular,
			celular : $scope.celular,
			celular1 : $scope.celular1,
			alarmAction:  $scope.alarmAction,
			action1 : $scope.action1,
			action2 : $scope.action2,
			action3 : $scope.action3,
			action4 : $scope.action4
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
		 
	$scope.clearFormAlarm = function () {
	
	    $scope.alarmUid = undefined;  
		$scope.alarmName = '';	  
		$scope.deviceType = '';  
	    $scope.alarmGas = '';
	    $scope.gasUnitMeterGases = '';
		$scope.alarmAlarm1 = '';
		$scope.alarmAlarm2 = '';
		$scope.alarmAlarm3 = '';
		$scope.alarmAlarm11 = '';
		$scope.alarmAlarm22 = '';
		$scope.alarmAlarm33 = '';			
		$scope.email = '';
		$scope.email1 = '';
		$scope.celular = '';
		$scope.celular1 = '';
		$scope.action1 = '';
		$scope.action2 = '';
		$scope.action3 = '';
		$scope.action4 = '';
		$scope.radioModel = false;
		$scope.enableAlarm2 = false;
		$scope.enableAlarm3 = false;		

		if($scope.$root.isFrom == "MASTER")
			$scope.selectedCompany = ''
		else
			$scope.selectedCompany = $scope.companies[0];
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
			$scope.deviceType = $scope.getDeviceType($scope.alarms[index].deviceType);
		    $scope.alarmName = $scope.alarms[index].name;		    
		    $scope.alarmAlarm1 = $scope.alarms[index].alarm1;
		    $scope.alarmAlarm2 = $scope.alarms[index].alarm2;
			$scope.alarmAlarm3 = $scope.alarms[index].alarm3;
			$scope.alarmAlarm11 = $scope.alarms[index].alarm11;
		    $scope.alarmAlarm22 = $scope.alarms[index].alarm22;
		    $scope.alarmAlarm33 = $scope.alarms[index].alarm33;
		    $scope.gasUnitMeterGases = $scope.getUnitMetersGases($scope.alarms[index].unitMeterGases);
			$scope.selectedCompany = $scope.alarms[index].companyDto;
			$scope.email = $scope.alarms[index].email;
			$scope.email1 = $scope.alarms[index].email1;
			$scope.celular = $scope.alarms[index].celular;
			$scope.celular1 = $scope.alarms[index].celular1;
			$scope.action1 = $scope.alarms[index].action1;
			$scope.action2 = $scope.alarms[index].action2;
			$scope.action3 = $scope.alarms[index].action3;
			$scope.action4 = $scope.alarms[index].action4;
									
			$("#checkboxActionOff").prop('checked', $scope.alarms[index].alarmAction);
			showAction($scope.alarms[index].alarmAction);
			
			$("#checkboxSigmaOnOff").prop('checked', $scope.alarms[index].alarmSigma);
			
			$("#checkboxSonoroOnOff").prop('checked', $scope.alarms[index].alarmSound);
			
			$("#checkboxEmailOnOff").prop('checked', $scope.alarms[index].alarmEmail); 
			showEmail($scope.alarms[index].alarmEmail);
			
			$("#checkboxSmsOnOff").prop('checked', $scope.alarms[index].alarmSms); 
			showCelular($scope.alarms[index].alarmSms);
			
			$scope.radioModel = $scope.alarms[index].alarmOn;
			$scope.enableAlarm2 = $scope.alarms[index].alarm2On;
			$scope.enableAlarm3 = $scope.alarms[index].alarm3On;
			// if( $scope.alarms[index].alarmOn != true )  
			// {
			// 	$("#travar").addClass("disableDiv");
				
			// 	$('#toggle_event_editing button').eq(1).addClass('locked_active btn-default').removeClass('unlocked_active bg-black');
			// 	$('#toggle_event_editing button').eq(0).addClass('unlocked_inactive bg-black').removeClass('locked_active btn-default');
			// }				
			// else {
			// 	$("#travar").removeClass("disableDiv");
				
			// 	$('#toggle_event_editing button').eq(0).addClass('locked_active btn-default').removeClass('unlocked_active bg-black');
			// 	$('#toggle_event_editing button').eq(1).addClass('unlocked_inactive bg-black').removeClass('locked_active btn-default');
			// }
			
			$scope.validEmail();
			$scope.validMobile();
			
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
	 
	 $scope.getDeviceType = function (name) {
		 
		 for (var i = 0; i < $scope.deviceTypes.length; i++) {
             if ($scope.deviceTypes[i].name == name) {
                 
            	 return $scope.deviceTypes[i];
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
			{ name : 'GRAUS_CELSIUS', uid : 5 },
			{ name : 'VOLT', uid : 6 },
			{ name : 'AMPERE', uid : 7 },
			{ name : 'MINUTE', uid : 8 },
			{ name : 'SECOND', uid : 9 },
			{ name : 'OPEN/CLOSE', uid : 10 },
			{ name : 'KWH', uid : 11 }
		 ]; 
	 
	 $('.alarmCelularMask').keydown(function (e) {
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
		 
		 $("#alarmEmail1").prop('disabled', !checked );         
		 $("#alarmEmail1").prop('readonly', !checked);
		 		 
		 $scope.alarmEmail = checked;
		 $scope.validEmail();
	 }
	 $scope.validEmail = function ($event) {	    	

		 if (!$("#checkboxEmailOnOff").prop('checked') || validateEmail( $scope.email )) {
			 $scope.emailValid = true;
		 }
		 else if ( $scope.email == '' || $scope.email1 == null) {
			 $scope.emailValid = false;
		 }
		 else {
		    $scope.emailValid = false;
		 }
		 
		 if (!$("#checkboxEmailOnOff").prop('checked') || validateEmail( $scope.email1 )) {
			 $scope.emailValid1 = true;
		 }
		 else if ( $scope.email1 == '' || $scope.email1 == null) {
			 $scope.emailValid1 = true;
		 }
		 else {
		    $scope.emailValid1 = false;
		 }
		 
		 if(!$scope.$$phase) 
			 $scope.$apply();
	 }

	 $scope.alarmMessageError = [];
	 $scope.validAlarms = function ($event) {
		var errors = [];	    	
		$scope.errorAlarm1  = false;
		$scope.errorAlarm2  = false;
		$scope.errorAlarm3  = false;
		$scope.errorAlarm11 = false;
		$scope.errorAlarm22 = false;
		$scope.errorAlarm33 = false;

		if( !$scope.alarmAlarm11 && !$scope.alarmAlarm1) {
			errors.push("ALARME 1 Precisa de Uma valor [Maior Que] ou [Menor Que]");
			$scope.errorAlarm1 = true ;
			$scope.errorAlarm11 = true ;
		}

		if( ($scope.alarmAlarm11 && $scope.alarmAlarm1) && ($scope.alarmAlarm11 > $scope.alarmAlarm1)) {
			errors.push("ALARME 1 Precisa de Uma valor [Menor Que] Não pode ser MAIOR");
			$scope.errorAlarm1 = true ;
			$scope.errorAlarm11 = true ;
		}

		if($scope.enableAlarm2) {

			if( ($scope.alarmAlarm1 && $scope.alarmAlarm2) && ($scope.alarmAlarm1 > $scope.alarmAlarm2) ) {
				errors.push("[Alarme 2 <span class='text-black'> ({{alarmAlarm2}}) </span> Deve ser maior que o Alarme 1 <span class='text-black'> ({{alarmAlarm1}}) </span>]");
				$scope.errorAlarm1 = true ;
				$scope.errorAlarm2 = true ;
		   }

		   if( !$scope.alarmAlarm22 && !$scope.alarmAlarm2) {
				errors.push("ALARME 2 Precisa de Uma valor [Maior Que] E/ou [Menor Que]");
				$scope.errorAlarm2 = true ;
				$scope.errorAlarm22 = true ;
			}

			if( ($scope.alarmAlarm22 && $scope.alarmAlarm2) && ($scope.alarmAlarm22 > $scope.alarmAlarm2)) {
				errors.push("ALARME 2 Precisa de Uma valor [Menor Que] Não pode ser MAIOR");
				$scope.errorAlarm2 = true ;
				$scope.errorAlarm22 = true ;
			}
		}	

		if($scope.enableAlarm3) {
		
			if( !$scope.alarmAlarm33 && !$scope.alarmAlarm3) {
				errors.push("ALARME 3 Precisa de Uma valor [Maior Que] E/ou [Menor Que]");
				$scope.erroralarm3 = true ;
				$scope.errorAlarm33 = true ;
			}

			if( ($scope.alarmAlarm33 && $scope.alarmAlarm3) && ($scope.alarmAlarm33 > $scope.alarmAlarm3)) {
				errors.push("ALARME 3 Precisa de Uma valor [Menor Que] Não pode ser MAIOR");
				$scope.errorAlarm3 = true ;
				$scope.errorAlarm33 = true ;
			}
		}

		if($scope.enableAlarm2 && $scope.enableAlarm3) {

			if( ($scope.alarmAlarm2 && $scope.alarmAlarm3) && ($scope.alarmAlarm2 > $scope.alarmAlarm3) ) {
				errors.push("[Alarme 3 <span class='text-black'> ({{alarmAlarm3}}) </span> Deve ser maior que o Alarme 2 <span class='text-black'> ({{alarmAlarm2}}) </span>]");
				$scope.errorAlarm3 = true ;
				$scope.errorAlarm2 = true ;
			}
		}

		$scope.alarmMessageError = errors;

	 }
	 
	 
	 $("#checkboxSmsOnOff").click(function(e, parameters) {		 
		 showCelular($(this).prop("checked"));         
     });
	 function showCelular(checked) {
		 
		 $(".checkboxSms").prop('checked', checked);
		 
		 $("#alarmCelular").prop('disabled', !checked );         
		 $("#alarmCelular").prop('readonly', !checked); 
		 
		 $("#alarmCelular1").prop('disabled', !checked );         
		 $("#alarmCelular1").prop('readonly', !checked); 
		 		 
		 $scope.alarmCelular = checked;
		 
		 $scope.validMobile();
	 }	 
	 $scope.validMobile = function ($event) {		 
	 
		 $scope.mobileValid =  (!$("#checkboxSmsOnOff").prop('checked') || ($scope.celular != null && $scope.celular.length == 15));
		 $scope.mobileValid1 =  (
				 				 !$("#checkboxSmsOnOff").prop('checked') || 
				 				 (($scope.celular1 != null && $scope.celular1.length == 15) || (($scope.celular1 == "" || $scope.celular1 == null)  && $scope.mobileValid))  
				 				);		 
		 
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
 
	 
	 $scope.update = function (val) {
		 $scope.radioModel = val;
	 }


	 $scope.deviceTypes = 	 [		  
		  { name : 'DETECTOR', uid :  1, disabled : false },
		  { name : 'PLC', uid :  2, disabled : false },
		  { name : 'CONTROLLER', uid :  3, disabled : false },
		  { name : 'ELETRICITY', uid :  6, disabled : false },
		  { name : 'TEMPO', uid :  7, disabled : false },
		  { name : 'TEMPERATURE', uid :  8, disabled : false },
		  { name : 'DIGITAL', uid :  9, disabled : false },
	 ];
	 
     /* ----------------- Processamento ------------------*/
	 
	 $scope.refreshAlarms = function() {
		 $scope.getAlarms();	 
		 $scope.getGases();
		 $scope.getCompanys();
	 }
	 
	 $scope.refreshAlarms();
 
	 angular.element('body').removeClass('loading');
		
		
});