var app = angular.module('eneeyes', ['ngResource', 'angular-jquery-maskedinput', 'pascalprecht.translate', 'dependency']);

angular.module('angularDatatable', []);

angular.module('dependency', []).config(['$httpProvider', function ($httpProvider) {

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
        	  
        	 $rootScope.errorTimes ++        	  
        	 
        	 if (rejection.status == 0) {
        		 
        		  angular.element('body').removeClass('loading'); 
        		  $rootScope.alertDanger = "Servidor Sem Resposta, Se o Problema Persistir Contate o Administrador do Sistema";
        		  $('#resultDanger').hide().show('slow').delay(15000).hide('slow');        		  
        		  
        	 }
        	 else if (rejection.status == 404) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertDanger = "Item Requisitado Inexistente, Contate o Administrador do Sistema";
        		 $('#resultDanger').hide().show('slow').delay(15000).hide('slow');
        	 }
        	 else if (rejection.status == 415) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertDanger = "Tipo de Media Insuportado, Contate o Administrador do Sistema";
        	 }
        	 else if (rejection.status == 500) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertDanger = "Algum retorno indesejado no servidor, Contate o Administrador do Sistema";
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


app.directive('validSecondPassword', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue, $scope) {
                var noMatch = viewValue != scope.signinForm.password1.$viewValue
                ctrl.$setValidity('noMatch', !noMatch)
            });
        }
    }
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

app.directive('popover', function($compile) { 
    var content =
    	"<div>" +    	
    	"<Label>Confirma a Exclus&atilde;o: {{popover}} ? </label><br>"+
    	"<button type='button' data-toggle='popover' ng-click='confirmOK();' class='btn btn-info btn-xs'> Sim</button>&nbsp&nbsp" +
    	"<button type='button' data-toggle='popover' ng-click='cancel();' class='btn btn-danger btn-xs'> N&atilde;o</button>" +
    	"</div>";
    
    return {
    	scope: {
    			popover: '@',
    			confirm: '&'
	    },
        link: function(scope, element, attrs) {
            element.popover({            
                placement: 'top',
                html: true,
                trigger: 'manual',
                clickedAway : true,
                content: $compile(content)(scope)
            });
            $(element).bind('click', function() {
            	$(element).popover('toggle');
            });
            scope.cancel = function () {                
                $(element).popover('hide');
            };
            scope.confirmOK = function () {
            	scope.confirm();
                $(element).popover('hide');
            }            
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


app.directive('datatable', function () {
    return {
        restrict: 'E, A, C',
        link: function (scope, element, attrs, controller) {
            //scope - directive internal scope

            var dataTable = element.dataTable(scope.options); //init plugin
            
            var mapToDatatableFormat = function (data) {
            	return data.map(scope.options.columnMap)
            }
            
            scope.$watch('items', function (newData) {
                console.log("new items:", scope.options);
                if (newData) {
                    dataTable.fnClearTable();
                    dataTable.fnAddData( mapToDatatableFormat(newData) )
                }
            }, true);
            
            dataTable.on('select', function (e, dt, type, indexes) {
                var rowData = table.rows( indexes ).data().toArray();
            });
            dataTable.on('deselect', function (e, dt, type, indexes) {
                var rowData = table.rows( indexes ).data().toArray();
            });
            
        },
        scope: {
            options: "=",
            items: "="
        }
    };
});