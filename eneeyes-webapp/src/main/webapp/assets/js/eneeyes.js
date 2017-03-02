var app = angular.module('eneeyes', ['ngResource', 'angular-jquery-maskedinput', 'pascalprecht.translate', 'dependency']);

angular.module('dependency', []) 
.config(['$httpProvider', function ($httpProvider) {

  	$httpProvider.interceptors.push(function ($q, $rootScope, $timeout) {
  	  
      return {
    	      	  
          'request': function (config) {
              return config;
          },
          'requestError': function (rejection) {
              console.log('request error');
              return rejection;
          },
          'response': function (response) {
              $rootScope.loading = false;          
              return response;	
          },
          'responseError': function (rejection) {
        	  
        	console.log('response error');        	  
        	  
        	 if (rejection.status == 0) {
        		  angular.element('body').removeClass('loading'); 
        		  $rootScope.alertDanger = "Ops Servidor Sem Resposta, Se o Problema Persistir Contate o Administrador do Sistema";
        		  $('#resultDanger').hide().show('slow').delay(5000).hide('slow');        		  
        		  
        	 }
        	 else if (rejection.status == 404) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertDanger = "Ops Item Requisitado Inexistente, Contate o Administrador do Sistema";
        		 $('#resultDanger').hide().show('slow').delay(5000).hide('slow');
        	 }
        	 else if (rejection.status == 415) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertDanger = "Tipo de Media Insuportado, Contate o Administrador do Sistema";
        	 }
        	 else if (rejection.status == 500) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertDanger = "Ops alguma retorno indesejado no servidor, Contate o Administrador do Sistema";
        	 }
        	 else if (rejection.status > 400 && rejection.status <= 505 ) {
     		    	angular.element('body').removeClass('loading');
	  			    angular.element('.session-expired').modal('show');
	  				
	  				$timeout(function(){
	  					window.location.href='/';
	  				},2500);	  				
	  				
	                return response;
	  		 }
        	 else {
        		  angular.element('body').removeClass('loading'); 
        		  $rootScope.alertDanger = rejection.statusText;
        	 }
//        	  else {
//        		  //Propagar o erro para tratamento local
//        		  return $q.reject(rejection);
//        	  }               
          }
      };
  });

}]);

app.directive('disallowSpaces', function() {'1'
	  return {
	    restrict: 'A',

	    link: function($scope, $element) {
	      $element.bind('input', function() {
	        $(this).val($(this).val().replace(/ /g, ''));
	      });
	    }
	  };
})


app.directive('validateRange', ['$parse', function($parse) {

    function link($scope, $element, $attrs, ngModel) {
        var attrRange, range = [];

        function validate(value) {
            var validMin = true, validMax = true;
            if (typeof range[0] === 'number') {
                ngModel.$setValidity('min', value >= range[0]);
                validMin = value >= range[0];
            }
            if (typeof range[1] === 'number') {
                ngModel.$setValidity('max', value <= range[1]);
                validMax = value <= range[1];
            }
            return validMin && validMax ? value : undefined;
        }

        attrRange = $attrs.validateRange.split(/,/);

        range[0] = $parse(attrRange[0] || '')($scope);
        range[1] = $parse(attrRange[1] || '')($scope);

        $scope.$watchCollection('[' + $attrs.validateRange + ']', function(vals) {
            range = vals;
            validate(ngModel.$viewValue);
        });

        ngModel.$parsers.unshift(validate);
        ngModel.$formatters.unshift(validate);
    }

    return {
        link: link,
        require: 'ngModel'
    };
    
}]);

app.filter('companyFilter', function () {
    return function (objects, criteria) {
        var filterResult = new Array();

        if (!criteria)
            return null;

        for (index in objects) {
                        
        	 if (objects[index] != null && objects[index].companyId == criteria.company.uid  ) {

                 filterResult.push(objects[index]);
             }   
        }

        return filterResult;
    }
});

app.filter('numberFixedLen', function () {
    return function (n, len) {
        var num = parseInt(n, 10);
        len = parseInt(len, 10);
        if (isNaN(num) || isNaN(len)) {
            return n;
        }
        num = ''+num;
        while (num.length < len) {
            num = '0'+num;
        }
        return num;
    };
});

app.directive('bindUnsafeHtml', ['$compile', '$timeout', function ($compile, $timeout) {
    return function(scope, element, attrs) {
        scope.$watch(
          function(scope) {
            // watch the 'bindUnsafeHtml' expression for changes
            return scope.$eval(attrs.bindUnsafeHtml);
          },
          function(value) {
        	//Checa se servidor enviou página de login, devidor sessão expirada
        	if(value != undefined && value.indexOf('loginPage') > 0) {
        		        		
        		angular.element('.session-expired').modal('show');
  				
  				$timeout(function(){
  					window.location.href='/';
  				},2500);        		
        	}
        	else
                // when the 'bindUnsafeHtml' expression changes
                // assign it into the current DOM
        		element.html(value);

            // compile the new DOM and link it to the current
            // scope.
            // NOTE: we only compile .childNodes so that
            // we don't get into infinite loop compiling ourselves
            $compile(element.contents())(scope);
          }
      );
  };
}]);

app.directive('numberOnly', function () {
	  return {
	      restrict: 'A',
	      require: 'ngModel',
	      scope: {
	          ngModel: '='
	      },
	      link: function (scope) {          
	          scope.$watch('ngModel', function(newValue,oldValue) {  
	              var arr = String(newValue).split("");
	              if (arr.length === 0) return;
	              if (arr.length === 1 && (arr[0] == '-' || arr[0] === '.' )) return;
	              if (arr.length === 2 && newValue === '-.') return;
	              if (isNaN(newValue)) {
	                  scope.ngModel = oldValue;
	              }
	          });
	      }
	  };
});

app.directive('format', ['$filter', function($filter) {
	return {
		require: 'ngModel',
		link: function(scope, element, attrs, ngModelController) {
			if (!ngModelController) return;

			ngModelController.$formatters.unshift(function (a) {
				if (ngModelController.$modelValue == "") {
					return ngModelController.$modelValue;
				} else {
					if (attrs.format == "currency") {
						if (ngModelController.$modelValue != null) {
							return ngModelController.$modelValue;
						}
					} else {
						return ngModelController.$modelValue;
					}
					
				}
            });

			ngModelController.$parsers.unshift(function (viewValue) {
				if (attrs.format == "currency") {
					element.priceFormat({
			            prefix: '',
			            centsSeparator: ',',
			            thousandsSeparator: '.'
			        });
					return element[0].value;
				} else {
					var plainNumber = viewValue.replace(/[^\d|\-+|\,+]/g, '');
	                element.val(plainNumber);
	                return plainNumber;
				}
            });
		}
	};
}]);

app.directive("daterangepicker", function () {
	  return {
		  restrict: "A",
		  require: "ngModel",
		  link: function (scope, elem, attrs, ngModelCtrl) {
	      var updateModel = function (dateText) {
	        scope.$apply(function () {
	          ngModelCtrl.$setViewValue(dateText);
	        });
	      };
	      elem.daterangepicker(
	              {
	                  ranges: {
	                      'Hoje': [moment(), moment()],
	                      'Ontem': [moment().subtract('days', 1), moment().subtract('days', 1)],
	                      '\u00daltimos 7 Dias': [moment().subtract('days', 6), moment()],
	                      '\u00daltimos 30 Dias': [moment().subtract('days', 29), moment()],
	                      'Esse M\u00eas': [moment().startOf('month'), moment().endOf('month')],
	                      'M\u00eas Passado': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
	                  },
	              		onSelect: function (dateText) {
	              			updateModel(dateText);
	              		}
	              },
	      function (start, end) {
	          $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	          updateModel(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
	      }
	      );
	    }
	}
});

app.directive("datemonopicker", ['$filter', function($filter) {
	  return {
		  restrict: "A",
		  require: "ngModel",
		  link: function (scope, elem, attrs, ngModelCtrl) {
	      var updateModel = function (dateText) {
	        scope.$apply(function () {
	          ngModelCtrl.$setViewValue(dateText);
	        });
	      };
	      elem.datepicker()
	      	.on('changeDate', function (ev) {
	      		updateModel($filter('date')(ev.date, "dd/MM/yyyy"));
	      		$(this).datepicker('hide');
	      	});
	    }
	}
}]);

//app.directive('popover', function($compile) { 
//    var content =
//    	"<div>" +
//    	"<label> Fabricante </label>" +
//    	"<input type='text' class='input-small' ng-model='newManufacturer' style='color:navy ! important;' />&nbsp&nbsp"  +
//    	"<button type='button' data-dismiss='popover-content' data-toggle='popover' ng-click='saveManufacturer();' class='btn btn-info btn-xs' ng-disabled='(newManufacturer) ? false : true'> OK</button>" +
//    	"</div>";
//    
//    return {
//        link: function(scope, element, attrs) {
//            element.popover({            
//                placement: 'top',
//                html: true,
//                clickedAway : true,
//                content: $compile(content)(scope)
//            });
//        }
//    
//    };
//});

app.directive('myLink', function () {
    return {
        scope: {
            enabled: '=myLink'
        },
        link: function (scope, element, attrs) {
            element.bind('click', function (event) {
                if (!scope.enabled) {
                    event.preventDefault();
                }
            });
        }
    };
});

(function($){$.fn.priceFormat=function(options){var defaults={prefix:'US$ ',suffix:'',centsSeparator:'.',thousandsSeparator:',',limit:false,centsLimit:2,clearPrefix:false,clearSufix:false,allowNegative:false,insertPlusSign:false};var options=$.extend(defaults,options);return this.each(function(){var obj=$(this);var is_number=/[0-9]/;var prefix=options.prefix;var suffix=options.suffix;var centsSeparator=options.centsSeparator;var thousandsSeparator=options.thousandsSeparator;var limit=options.limit;var centsLimit=options.centsLimit;var clearPrefix=options.clearPrefix;var clearSuffix=options.clearSuffix;var allowNegative=options.allowNegative;var insertPlusSign=options.insertPlusSign;if(insertPlusSign)allowNegative=true;function to_numbers(str){var formatted='';for(var i=0;i<(str.length);i++){char_=str.charAt(i);if(formatted.length==0&&char_==0)char_=false;if(char_&&char_.match(is_number)){if(limit){if(formatted.length<limit)formatted=formatted+char_}else{formatted=formatted+char_}}}return formatted}function fill_with_zeroes(str){while(str.length<(centsLimit+1))str='0'+str;return str}function price_format(str){var formatted=fill_with_zeroes(to_numbers(str));var thousandsFormatted='';var thousandsCount=0;if(centsLimit==0){centsSeparator="";centsVal=""}var centsVal=formatted.substr(formatted.length-centsLimit,centsLimit);var integerVal=formatted.substr(0,formatted.length-centsLimit);formatted=(centsLimit==0)?integerVal:integerVal+centsSeparator+centsVal;if(thousandsSeparator||$.trim(thousandsSeparator)!=""){for(var j=integerVal.length;j>0;j--){char_=integerVal.substr(j-1,1);thousandsCount++;if(thousandsCount%3==0)char_=thousandsSeparator+char_;thousandsFormatted=char_+thousandsFormatted}if(thousandsFormatted.substr(0,1)==thousandsSeparator)thousandsFormatted=thousandsFormatted.substring(1,thousandsFormatted.length);formatted=(centsLimit==0)?thousandsFormatted:thousandsFormatted+centsSeparator+centsVal}if(allowNegative&&(integerVal!=0||centsVal!=0)){if(str.indexOf('-')!=-1&&str.indexOf('+')<str.indexOf('-')){formatted='-'+formatted}else{if(!insertPlusSign)formatted=''+formatted;else formatted='+'+formatted}}if(prefix)formatted=prefix+formatted;if(suffix)formatted=formatted+suffix;return formatted}function key_check(e){var code=(e.keyCode?e.keyCode:e.which);var typed=String.fromCharCode(code);var functional=false;var str=obj.val();var newValue=price_format(str+typed);if((code>=48&&code<=57)||(code>=96&&code<=105))functional=true;if(code==8)functional=true;if(code==9)functional=true;if(code==13)functional=true;if(code==46)functional=true;if(code==37)functional=true;if(code==39)functional=true;if(allowNegative&&(code==189||code==109))functional=true;if(insertPlusSign&&(code==187||code==107))functional=true;if(!functional){e.preventDefault();e.stopPropagation();if(str!=newValue)obj.val(newValue)}}function price_it(){var str=obj.val();var price=price_format(str);if(str!=price)obj.val(price)}function add_prefix(){var val=obj.val();obj.val(prefix+val)}function add_suffix(){var val=obj.val();obj.val(val+suffix)}function clear_prefix(){if($.trim(prefix)!=''&&clearPrefix){var array=obj.val().split(prefix);obj.val(array[1])}}function clear_suffix(){if($.trim(suffix)!=''&&clearSuffix){var array=obj.val().split(suffix);obj.val(array[0])}}$(this).bind('keydown.price_format',key_check);$(this).bind('keyup.price_format',price_it);$(this).bind('focusout.price_format',price_it);if(clearPrefix){$(this).bind('focusout.price_format',function(){clear_prefix()});$(this).bind('focusin.price_format',function(){add_prefix()})}if(clearSuffix){$(this).bind('focusout.price_format',function(){clear_suffix()});$(this).bind('focusin.price_format',function(){add_suffix()})}if($(this).val().length>0){price_it();clear_prefix();clear_suffix()}})};$.fn.unpriceFormat=function(){return $(this).unbind(".price_format")};$.fn.unmask=function(){var field=$(this).val();var result="";for(var f in field){if(!isNaN(field[f])||field[f]=="-")result+=field[f]}return result}})(jQuery);