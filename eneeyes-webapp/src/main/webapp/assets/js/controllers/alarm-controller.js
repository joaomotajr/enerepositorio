
app.controller('alarmController', function ($scope, $timeout, DeviceTypeService, AlarmService, GasService, CompanyService,
	ViewService, UnitMeterService, PerfilAlarmService, AlarmEmailService, AlarmMobileService) {

	$('.modal')
	.on({
		'show.bs.modal': function() {
			var idx = $('.modal:visible').length;
			$(this).css('z-index', 1040 + (10 * idx));
		}, 'shown.bs.modal': function() {
			var idx = ($('.modal:visible').length) - 1;
			$('.modal-backdrop').not('.stacked')
			.css('z-index', 1039 + (10 * idx))
			.addClass('stacked');
		}, 'hidden.bs.modal': function() {
			if ($('.modal:visible').length > 0) {			
				setTimeout(function() {
					$(document.body).addClass('modal-open');
				}, 0);
			}
		}
	});

	$scope.emails = [];
	$scope.mobiles = [];
	$scope.changed = true;
	
	$scope.saveAlarm = function() {		
		angular.element('body').addClass('loading');		
		if($scope.unitMeter.uid == 0) {
			$scope.alarmAlarm1= $scope.deviceTypeDigital;
		}
				
		var alarm = {
			uid: $scope.alarmUid != undefined ? $scope.alarmUid : 0,
			name: $scope.alarmName,
			deviceType : {uid: $scope.deviceType.uid},
			unitMeter : {uid: $scope.unitMeter.uid},
			alarm1 : $scope.alarmAlarm1,
			perfilAlarmDto1 : {uid:$scope.perfilAlarm1.uid},
			alarm2 : $scope.alarmAlarm2,
			perfilAlarmDto2 : {uid: $scope.perfilAlarm2 ? $scope.perfilAlarm2.uid : undefined},
			alarm3 : $scope.alarmAlarm3,
			perfilAlarmDto3 : {uid: $scope.perfilAlarm3 ? $scope.perfilAlarm3.uid : undefined},
			alarm11 : $scope.alarmAlarm11,
			alarm22 : $scope.alarmAlarm22,
			alarm33 : $scope.alarmAlarm33,
			alarmOffLineOn: $("#checkboxOfflineOnOff").prop('checked'),
			companyDto : $scope.selectedCompany,
			alarmOn: $scope.radioModel,
			alarm2On : $scope.enableAlarm2,
			alarm3On : $scope.enableAlarm3,
			alarmSigma:  $("#checkboxSigmaOnOff").prop('checked'),			
			alarmEmail:  $("#checkboxEmailOnOff").prop('checked'),
			alarmSound:  $("#checkboxSonoroOnOff").prop('checked'),
			alarmSms:  $("#checkboxSmsOnOff").prop('checked'),
			alarmAction:  $scope.alarmAction,
			action1 : $scope.action1,
			action2 : $scope.action2,
			action3 : $scope.action3,
			action4 : $scope.action4			
		}; 

		if(!$scope.enableAlarm2) 
		   alarm.perfilAlarmDto2=null;

		if(!$scope.enableAlarm3) 
		   alarm.perfilAlarmDto3=null;
		
		if($scope.alarmGas)
			alarm.gasDto = $scope.alarmGas;
		 
		$scope.inclusaoAlarm = new AlarmService.save(alarm);
		$scope.inclusaoAlarm.$alarm({_csrf : angular.element('#_csrf').val()}, function() {         	
			$timeout(function () {		
				if (alarm.uid) {
					$scope.clearFormAlarm();
					$scope.getAlarms();
					angular.element('#modalAlarmEdit').modal('toggle');
				} else {					
					$scope.alarmUid	= $scope.inclusaoAlarm.t.uid;
					$scope.changed = false;
				}
	            angular.element('body').removeClass('loading');				 
	         }, 500);
		});		 
	 };
		 
	$scope.clearFormAlarm = function () {
		$scope.showPerfilAlarm1 = false;
		$scope.showPerfilAlarm2 = false;
		$scope.showPerfilAlarm3 = false;
		$scope.perfilAlarm1 = '';
		$scope.perfilAlarm2 = '';
		$scope.perfilAlarm3 = '';
	    $scope.alarmUid = undefined;  
		$scope.alarmName = '';	  
		$scope.deviceType = '';  
	    $scope.alarmGas = '';		
		$scope.unitMeter = '';
		$scope.alarmAlarm1 = '';
		$scope.alarmAlarm2 = '';
		$scope.alarmAlarm3 = '';
		$scope.alarmAlarm11 = '';
		$scope.alarmAlarm22 = '';
		$scope.alarmAlarm33 = '';
		$scope.action1 = '';
		$scope.action2 = '';
		$scope.action3 = '';
		$scope.action4 = '';
		$scope.radioModel = false;
		$scope.enableAlarm2 = false;
		$scope.enableAlarm3 = false;
		$scope.email = '';
		$scope.emailValid = true;
		$scope.emailsValid = true;
		$scope.mobile = '';
		$scope.mobileValid = true;
		$scope.mobilesValid = true;
		$scope.emails = [];
		$scope.mobiles = [];		
		$("#checkboxSigmaOnOff").prop('checked', false);
		$("#checkboxOfflineOnOff").prop('checked', true);
		$("#checkboxSonoroOnOff").prop('checked', false);
		$("#checkboxEmailOnOff").prop('checked', false);
		$("#checkboxSmsOnOff").prop('checked', false);
		$("#checkboxActionOff").prop('checked', false);

		if($scope.$root.isFrom == "MASTER")
			$scope.selectedCompany = '';
		else
			$scope.selectedCompany = $scope.companies[0];
	};
	 
	$scope.getAlarms = function() {		 
		 $scope.resultAlarms = new AlarmService.listAll();		 
		 $scope.resultAlarms.$alarm({_csrf : angular.element('#_csrf').val()}, function() {			
			 $scope.alarms = $scope.resultAlarms.list;			 
         });		 
	 };	 

	 $scope.changePerfilAlarm1 = function(show) {
		 if (show) {
			$scope.showPerfilAlarm1 = !$scope.showPerfilAlarm1;
		  } else if ($scope.perfilAlarm1) {		
			$scope.showPerfilAlarm1 = false;	 
		 }
	 };

	 $scope.changePerfilAlarm2 = function(show) {
		if (show) {
		   $scope.showPerfilAlarm2 = !$scope.showPerfilAlarm2;
	   	} else if ($scope.perfilAlarm1) {		
		   $scope.showPerfilAlarm2 = false;	 
		}
	};

	$scope.changePerfilAlarm3 = function(show) {
		if (show) {
		   $scope.showPerfilAlarm3 = !$scope.showPerfilAlarm3;
	   	} else if ($scope.perfilAlarm1) {		
		   $scope.showPerfilAlarm3 = false;	 
		}
	};
	
	$scope.editAlarm = function (index) {
		
		$scope.showPerfilAlarm1 = false;
		$scope.showPerfilAlarm2 = false;
		$scope.showPerfilAlarm3 = false;

		$scope.alarmUid = $scope.alarms[index].uid;
		$scope.alarmGas = $scope.alarms[index].gasDto;
		$scope.deviceType = $scope.alarms[index].deviceType;
		$scope.alarmName = $scope.alarms[index].name;		    
		$scope.alarmAlarm1 = $scope.alarms[index].alarm1;
		$scope.unitMeter = $scope.alarms[index].unitMeter;

		if ($scope.unitMeter.uid == 0) {				
			$scope.deviceTypeDigital = $scope.alarms[index].alarm1;
		}
		$scope.alarmAlarm2 = $scope.alarms[index].alarm2;
		$scope.alarmAlarm3 = $scope.alarms[index].alarm3;
		$scope.alarmAlarm11 = $scope.alarms[index].alarm11;
		$scope.alarmAlarm22 = $scope.alarms[index].alarm22;
		$scope.alarmAlarm33 = $scope.alarms[index].alarm33;
		$scope.unitMeter = $scope.alarms[index].unitMeter;
		$scope.selectedCompany = $scope.alarms[index].companyDto;
		$scope.action1 = $scope.alarms[index].action1;
		$scope.action2 = $scope.alarms[index].action2;
		$scope.action3 = $scope.alarms[index].action3;
		$scope.action4 = $scope.alarms[index].action4;
								
		$("#checkboxActionOff").prop('checked', $scope.alarms[index].alarmAction);
		showAction($scope.alarms[index].alarmAction);
		
		$("#checkboxSigmaOnOff").prop('checked', $scope.alarms[index].alarmSigma);
		$("#checkboxOfflineOnOff").prop('checked', $scope.alarms[index].alarmOffLineOn);				
		$("#checkboxSonoroOnOff").prop('checked', $scope.alarms[index].alarmSound);		
		$("#checkboxEmailOnOff").prop('checked', $scope.alarms[index].alarmEmail);		
		$("#checkboxSmsOnOff").prop('checked', $scope.alarms[index].alarmSms); 
		
		$scope.radioModel = $scope.alarms[index].alarmOn;
		$scope.enableAlarm2 = $scope.alarms[index].alarm2On;
		$scope.enableAlarm3 = $scope.alarms[index].alarm3On;
		$scope.perfilAlarm1 = $scope.alarms[index].perfilAlarmDto1;
		$scope.perfilAlarm2 = $scope.alarms[index].perfilAlarmDto2;
		$scope.perfilAlarm3 = $scope.alarms[index].perfilAlarmDto3;
		$scope.emails = $scope.alarms[index].alarmEmailsDto;
		$scope.mobiles = $scope.alarms[index].alarmMobilesDto;
		
		showEmail($scope.alarms[index].alarmEmail);
		showMobile($scope.alarms[index].alarmSms);
		usedAlarms($scope.alarms[index].uid);								
		angular.element('#modalAlarmEdit').modal('toggle');
	 };
	 
	function usedAlarms(alarmId) {		 
		$scope.resultUsedAlarms = new ViewService.listAlarmCompanyDeviceView();		 
		$scope.resultUsedAlarms.$view({_csrf : angular.element('#_csrf').val(), alarmId : alarmId}, function() {		 
			$timeout(function () {				
				$scope.usedAlarms = $scope.resultUsedAlarms.list;
	         }, 500);			 
		});		 		 
	}	 
		
	$scope.deleteAlarm = function(index) {		 
		var uid = $scope.alarms[index].uid;		 
		$scope.deletar = new AlarmService.deletar();		 
		$scope.deletar.$alarm({_csrf : angular.element('#_csrf').val(), id : uid}, function() {			 			 
			if (!$scope.deletar.isError)
				$scope.alarms.splice(index, 1);
			else {
				$scope.msgErro = "Erro: " + $scope.deletar.message;
				console.log($scope.deletar.systemMessage); 
			}
		});
	 };	 
	 
	$scope.getDeviceTypes = function() {		 
		$scope.listAllDeviceType = new DeviceTypeService.listAllDeviceType();		 
		$scope.listAllDeviceType.$deviceType({_csrf : angular.element('#_csrf').val()}, function() {			
			$scope.deviceTypes = $scope.listAllDeviceType.list;
	   });		 
	};

	$scope.getGases = function() {
		$scope.resultGases = new GasService.listAll();		
		$scope.resultGases.$gas({_csrf : angular.element('#_csrf').val()}, function() {			
			$scope.gases = $scope.resultGases.list; 		 			 
        });		 
	};

	$scope.getCompanys = function() {
		$scope.resultCompanies = new CompanyService.listAllView();		 
		$scope.resultCompanies.$company({_csrf : angular.element('#_csrf').val()}, function() {			
				 $scope.companies = $scope.resultCompanies.list;
	    }); 
	};	
	 
	 $('.alarmMobileMask').keydown(function (e) {
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
			} else {
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

	$("#checkboxSigmaOnOff").click(function(e, parameters) {		 		 
		$scope.userForm.$pristine = false;
		if(!$scope.$$phase) 
			$scope.$apply();
    });
 
	$("#checkboxSonoroOnOff").click(function(e, parameters) {
		$scope.userForm.$pristine = false;
		$(".checkboxSonoro").prop('checked', $(this).prop("checked")); 
		if(!$scope.$$phase) 
			$scope.$apply();
	});

	$("#checkboxOfflineOnOff").click(function(e, parameters) {
		$scope.userForm.$pristine = false;
		if(!$scope.$$phase) 
			$scope.$apply();
	});
		 
	$("#checkboxEmailOnOff").click(function(e, parameters) {
		$scope.userForm.$pristine = false;
		showEmail($(this).prop("checked"));		
    });	 
	 
	function showEmail (checked) {
		$("#alarmEmail").prop('disabled', !checked );         
		$("#alarmEmail").prop('readonly', !checked);		
		$scope.alarmEmail = checked;
		$scope.validEmail();
	}

	$scope.validEmail = function ($event) {
		if ($("#checkboxEmailOnOff").prop('checked')) {			 
			if ($scope.emails && $scope.emails.length > 0) {
				$scope.emailsValid = true;
			} else {
				$scope.emailsValid = false;
			}
			if ( $scope.email == '' || $scope.email == null) {
				$scope.emailValid = true;
			} else if (!validateEmail($scope.email)) {
				$scope.emailValid = false;
			} else {
				$scope.emailValid = true;
			}
		} else {			
			$scope.emailValid = true;			
		   	$scope.emailsValid = true;				
		}				 
		if(!$scope.$$phase) 
			$scope.$apply();
	};

	$("#checkboxSmsOnOff").click(function(e, parameters) {		 
		showMobile($(this).prop("checked"));
    });	

	function showMobile(checked) {		
		$("#alarmMobile").prop('disabled', !checked);
		$("#alarmMobile").prop('readonly', !checked);
		$scope.alarmMobile = checked;		 
		$scope.validMobile();
	}	

	$scope.validMobile = function ($event) {	 
		if  ($("#checkboxSmsOnOff").prop('checked')) {
			if ($scope.mobiles && $scope.mobiles.length > 0) {
				$scope.mobilesValid = true;
			} else {
				$scope.mobilesValid = false;
			}
			if ( $scope.mobile == '' || $scope.mobile == null) {
				$scope.mobileValid = true;
			} else if ($scope.mobile.length !== 15) {
				$scope.mobileValid = false;
			} else {
				$scope.mobileValid = true;
			}
		} else {
			$scope.mobileValid = true;			
		   	$scope.mobilesValid = true;
		}			 
		if(!$scope.$$phase) 
			 $scope.$apply();					 
	};

	$scope.alarmMessageError = [];
	$scope.validAlarms = function ($event) {
		var errors = [];	    	
		$scope.errorAlarm1  = false;
		$scope.errorAlarm2  = false;
		$scope.errorAlarm3  = false;
		$scope.errorAlarm11 = false;
		$scope.errorAlarm22 = false;
		$scope.errorAlarm33 = false;
		$scope.errorPerfil1 = false;
		$scope.errorPerfil2 = false;
		$scope.errorPerfil3 = false;

		if( !$scope.alarmAlarm11 && !$scope.alarmAlarm1) {
			errors.push("ALARME 1 Precisa de Uma valor [Maior Que] ou [Menor Que]");
			$scope.errorAlarm1 = true ;
			$scope.errorAlarm11 = true ;			
		}

		if( ($scope.alarmAlarm11 && $scope.alarmAlarm1) && (Number($scope.alarmAlarm11) > Number($scope.alarmAlarm1))) {
			errors.push("ALARME 1 Precisa de Uma valor [Menor Que] N�O pode ser MAIOR");
			$scope.errorAlarm1 = true ;
			$scope.errorAlarm11 = true ;
		}

		if(!$scope.perfilAlarm1) {
			errors.push("ALARME 1 Selecione um Perfil de Alerta");
			$scope.errorPerfil1 = true ;
		}

		if($scope.enableAlarm2) {
			if( ($scope.alarmAlarm1 && $scope.alarmAlarm2) && (Number($scope.alarmAlarm1) > Number($scope.alarmAlarm2) )) {
				errors.push("[Alarme 2 <span class='text-black'> ({{alarmAlarm2}}) </span> Deve ser maior que o Alarme 1 <span class='text-black'> ({{alarmAlarm1}}) </span>]");
				$scope.errorAlarm1 = true ;
				$scope.errorAlarm2 = true ;
		   }
		   if( !$scope.alarmAlarm22 && !$scope.alarmAlarm2) {
				errors.push("ALARME 2 Precisa de Uma valor [Maior Que] E/ou [Menor Que]");
				$scope.errorAlarm2 = true ;
				$scope.errorAlarm22 = true ;
			}
			if( ($scope.alarmAlarm22 && $scope.alarmAlarm2) && (Number($scope.alarmAlarm22) > Number($scope.alarmAlarm2))) {
				errors.push("ALARME 2 Precisa de Uma valor [Menor Que] N�O pode ser MAIOR");
				$scope.errorAlarm2 = true ;
				$scope.errorAlarm22 = true ;
			}
			if(!$scope.perfilAlarm2) {
				errors.push("ALARME 2 Selecione um Perfil de Alerta");
				$scope.errorPerfil2 = true ;
			}
		}	

		if($scope.enableAlarm3) {		
			if( !$scope.alarmAlarm33 && !$scope.alarmAlarm3) {
				errors.push("ALARME 3 Precisa de Uma valor [Maior Que] E/ou [Menor Que]");
				$scope.erroralarm3 = true ;
				$scope.errorAlarm33 = true ;
			}
			if( ($scope.alarmAlarm33 && $scope.alarmAlarm3) && (Number($scope.alarmAlarm33) > Number($scope.alarmAlarm3))) {
				errors.push("ALARME 3 Precisa de Uma valor [Menor Que] N�O pode ser MAIOR");
				$scope.errorAlarm3 = true ;
				$scope.errorAlarm33 = true ;
			}
			if(!$scope.perfilAlarm3) {
				errors.push("ALARME 3 Selecione um Perfil de Alerta");
				$scope.errorPerfil3 = true ;
			}
		}

		if($scope.enableAlarm2 && $scope.enableAlarm3) {
			if( ($scope.alarmAlarm2 && $scope.alarmAlarm3) && (Number($scope.alarmAlarm2) > Number($scope.alarmAlarm3))) {
				errors.push("[Alarme 3 <span class='text-black'> ({{alarmAlarm3}}) </span> Deve ser maior que o Alarme 2 <span class='text-black'> ({{alarmAlarm2}}) </span>]");
				$scope.errorAlarm3 = true ;
				$scope.errorAlarm2 = true ;
			}
		}
		$scope.alarmMessageError = errors;
	};	 
		 
	$("#checkboxActionOff").click(function(e, parameters) {
		$scope.userForm.$pristine = false;
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
	};

	$scope.getUnitMeters = function() {		 
		$scope.listAllUnitMeter = new UnitMeterService.listAllUnitMeter();		 
		$scope.listAllUnitMeter.$unitMeter({_csrf : angular.element('#_csrf').val()}, function() {			
			$scope.unitMeters = $scope.listAllUnitMeter.list;
	   });		 
	};

	$scope.saveAlarmEmail = function(alarmEmail) {		 
		$scope.inclusaoAlarmEmail = new AlarmEmailService.save(alarmEmail);
		$scope.inclusaoAlarmEmail.$alarmEmail({_csrf : angular.element('#_csrf').val()}, function() {         	
			$scope.emails.push($scope.inclusaoAlarmEmail.t);
			$scope.validEmail();
			$scope.email = '';
		});
	};

	$scope.saveAlarmMobile = function(alarmMobile) {		 
		$scope.inclusaoAlarmMobile = new AlarmMobileService.save(alarmMobile);
		$scope.inclusaoAlarmMobile.$alarmMobile({_csrf : angular.element('#_csrf').val()}, function() {         	
			$scope.mobiles.push($scope.inclusaoAlarmMobile.t);
			$scope.validMobile();
			$scope.mobile = '';
		});
	};

	$scope.addEmail = function (email) {
		var alarmEmail = {
			uid: null, 
			email: email, 
			alarmDto: {uid : $scope.alarmUid}
		};
		$scope.saveAlarmEmail(alarmEmail);
	};

	$scope.addMobile = function (mobile) {
		var alarmMobile = {
			uid: null, 
			mobile: mobile, 
			alarmDto: {uid : $scope.alarmUid}
		};
		$scope.saveAlarmMobile(alarmMobile);
	};

	$scope.removeAlarmEmail = function(alarmEmail) {		 
		$scope.deletarAlarmEmail = new AlarmEmailService.deletar(alarmEmail.uid);
		$scope.deletarAlarmEmail.$alarmEmail({_csrf : angular.element('#_csrf').val(), id : alarmEmail.uid}, function()  {        				
			$scope.emails.splice(
				$scope.emails.indexOf(
					$scope.emails.filter(function (f) {
						return f.uid == alarmEmail.uid;
					})[0]
				), 1
			);
			$("#checkboxEmailOnOff").prop('checked', $scope.emails.length >0);
		});
	};

	$scope.removeAlarmMobile = function(alarmMobile) {		 
		$scope.deletarAlarmMobile = new AlarmMobileService.deletar(alarmMobile.uid);
		$scope.deletarAlarmMobile.$alarmMobile({_csrf : angular.element('#_csrf').val(), id : alarmMobile.uid}, function()  {        				
			$scope.mobiles.splice(
				$scope.mobiles.indexOf(
					$scope.mobiles.filter(function (f) {
						return f.uid == alarmMobile.uid;
					})[0]
				), 1
			);			
			$("#checkboxSmsOnOff").prop('checked', $scope.mobiles.length >0);
		});
	};

	$scope.removeEmail = function (index) {
		$scope.removeAlarmEmail($scope.emails[index]);
	};

	$scope.removeMobile = function (index) {
		$scope.removeAlarmMobile($scope.mobiles[index]);
	};

	$scope.unitMeterChange = function() {
		if($scope.unitMeter && $scope.unitMeter.uid==0) {
			$scope.genericRangeMin = 0;
			$scope.genericRangeMax = 1;
		}
	};

	$scope.getPerfilAlarms = function() {		 
		$scope.listAllPerfilAlarm = new PerfilAlarmService.listAllPerfilAlarm();		 
		$scope.listAllPerfilAlarm.$perfilAlarm({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.perfilAlarms = $scope.listAllPerfilAlarm.list;
	   });		 
	};
	      
	$scope.refreshAlarms = function() {
		$scope.getDeviceTypes();
	 	$scope.getUnitMeters();
		$scope.getAlarms();	 
		$scope.getGases();
		$scope.getCompanys();
		$scope.getPerfilAlarms();
	};	 
	 
	 $scope.refreshAlarms(); 
	 angular.element('body').removeClass('loading');		
});