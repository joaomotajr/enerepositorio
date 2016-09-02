(function($){
	$("#esqueciSenha").on('click', function(){
		$('#modalLogin').modal('hide');
	});
	
    var section;
    $('#navbar, #navcmd').on('click', 'li a', function(){
        var clickedAnchor = $(this);

        if(clickedAnchor.data('section') != 'prior' && clickedAnchor.data('section') != 'next') {
            section = clickedAnchor.data('section');
        }

        if(clickedAnchor.data('section') == 'prior' || clickedAnchor.data('section') == 'next') {
            var nav = $('#navbar li a'), item = null, pos;
            nav.each(function(i){
                if($(this).data('section') == section && clickedAnchor.data('section') == 'prior' && item == undefined) {
                    pos = i - 1;
                    if(pos < 0) {
                        pos = nav.length-1;
                    }
                    item = $(nav.get(pos)).data('section');
                }

                if($(this).data('section') == section && clickedAnchor.data('section') == 'next' && item == undefined) {
                    pos = i + 1;
                    if(pos == nav.length) {
                        pos = 0;
                    }
                    item = $(nav.get(pos)).data('section');
                }
            });

            section = item;

            if(section == null && clickedAnchor.data('section') == 'prior') {
                section = $(nav.get(nav.length-1)).data('section');
            }
            if(section == null && clickedAnchor.data('section') == 'next') {
                section = $(nav.get(1)).data('section');
            }
        }

        sectionScrollTo = $('#' + section);
        if(sectionScrollTo != null && sectionScrollTo.offset() != null)
            $('html,body').animate({scrollTop: sectionScrollTo.offset().top}, 500);
    });

    $('#btnSignin').click('click', function(){
        var sectionScrollTo = $('#pricing');
        $('html,body').animate({scrollTop: sectionScrollTo.offset().top}, 500);
    });
}(jQuery));

var app = angular.module('eneeyes', ['ngResource', 'angular-jquery-maskedinput', 'pascalprecht.translate', 'ngOptionsDisabled']);

function signin() {
    $('body').addClass('loading');
    setTimeout(function(){
        window.location.href='/profile.html';
    }, 800);
}

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

app.directive('bindUnsafeHtml', ['$compile', function ($compile) {
    return function(scope, element, attrs) {
        scope.$watch(
          function(scope) {
            // watch the 'bindUnsafeHtml' expression for changes
            return scope.$eval(attrs.bindUnsafeHtml);
          },
          function(value) {
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


app.directive('iCheck', ['$timeout', '$parse', function ($timeout, $parse) {
    return {
        require: 'ngModel',
        link: function ($scope, element, $attrs, ngModel) {
            return $timeout(function () {
                var value = $attrs.value;
                var $element = $(element);

                // Instantiate the iCheck control.                            
                $element.iCheck({
                    checkboxClass: 'icheckbox_square-green',
                    radioClass: 'iradio_square-green',
                    increaseArea: '20%'
                });

                // If the model changes, update the iCheck control.
                $scope.$watch($attrs.ngModel, function (newValue) {
                    $element.iCheck('update');
                });

                // If the iCheck control changes, update the model.
                $element.on('ifChanged', function (event) {
                    if ($element.attr('type') === 'radio' && $attrs.ngModel) {
                        $scope.$apply(function () {
                            ngModel.$setViewValue(value);
                        });
                    }
                });

            });
        }
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
	      	});
	    }
	}
}]);

app.directive('popover', function($compile) { 
    var content =
    	"<div>" +
    	"<label> Fabricante </label>" +
    	"<input type='text' class='input-small' ng-model='newManufacturer' style='color:navy ! important;' />&nbsp&nbsp"  +
    	"<button type='button' data-dismiss='popover-content' data-toggle='popover' ng-click='saveManufacturer();' class='btn btn-info btn-xs' ng-disabled='(newManufacturer) ? false : true'> OK</button>" +
    	"</div>";
    
    return {
        link: function(scope, element, attrs) {
            element.popover({            
                placement: 'top',
                html: true,
                clickedAway : true,
                content: $compile(content)(scope)
            });
        }
    
    };
});

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