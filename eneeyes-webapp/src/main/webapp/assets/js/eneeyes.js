var app = angular.module('eneeyes', ['ngResource', 'dependency', 'ng-fusioncharts']);

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
        		  $rootScope.alertServerDanger = "Servidor Sem Resposta, Se o Problema Persistir Contate o Administrador do Sistema";
        		  $('#resultServerDanger').hide().show('slow').delay(15000).hide('slow');        		  
        		  
        	 }
        	 else if (rejection.status == 403) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertServerDanger = "403 Acesso ao Módulo ou Recurso Não Permitido!";
        		 $('#resultServerDanger').hide().show('slow').delay(15000).hide('slow');
        	 }
        	 else if (rejection.status == 404) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertServerDanger = "404 - Item Requisitado Inexistente, Contate o Administrador do Sistema.";
        		 $('#resultServerDanger').hide().show('slow').delay(15000).hide('slow');
        	 }
        	 else if (rejection.status == 405) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertServerDanger = "405 - Chamada Inválida, Contate o Administrador do Sistema.";
        		 
        		 $timeout(function(){
        			 	$('#resultServerDanger').hide();
	  					window.location.href='/';
	  			 },2500);        		 
        	 }
        	 else if (rejection.status == 415) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertServerDanger = "415 - Tipo de Media Insuportado, Contate o Administrador do Sistema.";
        	 }
        	 else if (rejection.status == 500) {
        		 angular.element('body').removeClass('loading'); 
        		 $rootScope.alertServerDanger = "500 - Algum retorno indesejado no servidor, Contate o Administrador do Sistema";
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
        		  $rootScope.alertServerDanger = rejection.statusText;
        	 }
//        	  else {
//        		  //Propagar o erro para tratamento local
//        		  return $q.reject(rejection);
//        	  }               
          }
      };
  });

}]);


app.directive('bindUnsafeHtml', ['$compile', '$timeout', function ($compile, $timeout) {
    return function(scope, element, attrs) {
        scope.$watch(
          function(scope) {
            // watch the 'bindUnsafeHtml' expression for changes
            return scope.$eval(attrs.bindUnsafeHtml);
          },
          function(value) {
        	//Checa se servidor enviou pÃ¡gina de login, devidor sessÃ£o expirada
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



app.directive('focusMe', function($timeout) {
	  return {
	    scope: { trigger: '=focusMe' },
	    link: function(scope, element) {
	      scope.$watch('trigger', function(value) {
	        
	    	  if(value === true) { 	          
	    		  element[0].focus();
	    		  scope.trigger = false;	          
	    	  }
	      });
	    }
	  };
	});


app.directive('validSecondPassword', function () {
    return {
    	scope: {
    		validSecondPassword: '@'			
    	},
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {            
            ctrl.$parsers.unshift(function (viewValue, $scope) {
            	var noMatch = (viewValue != scope.validSecondPassword);
                ctrl.$setValidity('noMatch', !noMatch);

                return viewValue;
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

app.directive('myLink', function() {
	  return {
	    restrict: 'A',
	    scope: {
	      enabled: '=myLink'
	    },
	    link: function(scope, element, attrs) {
	      element.bind('click', function(event) {
	        if(!scope.enabled) {
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

            var dataTable = element.dataTable(scope.options);
            
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