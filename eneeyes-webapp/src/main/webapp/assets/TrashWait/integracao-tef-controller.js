app.controller('IntegracaoTefController', function ($scope, $filter, $timeout, DetalheVenda) {
	
	$scope.carregarCalendario = function() {
		$scope.datas = new DetalheVenda.historico();
		$scope.datas.$datasIntegracao({_csrf : angular.element('#_csrf').val()}, function(){
			$scope.eventDates = {};
	    	if ($scope.datas.listaDatasIntegracao.length > 0) {
	    		for (i = 0; i < $scope.datas.listaDatasIntegracao.length; i++) {
	    	        $scope.eventDates[ new Date($scope.datas.listaDatasIntegracao[i])] = new Date($scope.datas.listaDatasIntegracao[i]);
	    		}
	    	}
	    	$('#calendar').datepicker({
			    beforeShowDay: function(date) {
			        var highlight = $scope.eventDates[date];
			        if(highlight) {
			        	return {
			                classes: 'highlighted',
			                enabled : 'false'
			             };
			        } else {
			        	return {
			                classes: 'no-highlighted',
			                enabled : 'false'
			             };
			        }
			     }
			});
	    	$("#calendar").datepicker("update");
	    	$scope.carregaListaHistorico();
	    });
	}
    
    $scope.carregaListaHistorico = function() {
    	$scope.datas = new DetalheVenda.historicoIntegracao();
		$scope.datas.$datasHistoricoIntegracao({_csrf : angular.element('#_csrf').val()}, function(){
			if ($scope.datas.listValue.length > 0) {
				$timeout(function(){
					$('#dataTables-integracao').dataTable();
				},1500);
        	}
		});
    };
	
	$scope.dropbox = document.getElementById("dropbox");
    $scope.dropText = 'Solte arquivo aqui...';

    // init event handlers
    function dragEnterLeave(evt) {
        evt.stopPropagation()
        evt.preventDefault()
        $scope.$apply(function(){
            $scope.dropText = 'Solte arquivo aqui...'
            $scope.dropClass = ''
        })
    }
    $scope.dropbox.addEventListener("ondragenter", dragEnterLeave, false)
    $scope.dropbox.addEventListener("ondragleave", dragEnterLeave, false)
    $scope.dropbox.addEventListener("ondragover", function(evt) {
        evt.stopPropagation()
        evt.preventDefault()
        var clazz = 'not-available'
        var ok = evt.dataTransfer && evt.dataTransfer.types && evt.dataTransfer.types.indexOf('Files') >= 0
        $scope.$apply(function(){
            $scope.dropText = ok ? 'Solte arquivo aqui...' : 'Somente arquivo &eacute; permitido!'
            $scope.dropClass = ok ? 'over' : 'not-available'
        })
    }, false)
    $scope.dropbox.addEventListener("ondrop", function(evt) {
    	$scope.errorUpload = "";
        evt.stopPropagation()
        evt.preventDefault()
        $scope.$apply(function(){
            $scope.dropText = 'Solte arquivo aqui...'
            $scope.dropClass = ''
        })
        var files = evt.dataTransfer.files
        if (files.length > 0) {
            $scope.$apply(function(){
                $scope.files = []
                for (var i = 0; i < files.length; i++) {
                	var reader = new FileReader();
	            	reader.onload = (function(theFile) {
	            		return function(e) {
	            			var text = e.target.result;
	            			var lines = text.split(/[\r\n]+/g);
	            			if (lines[3] == undefined || lines[3].length < 383) {
	            				document.getElementById('infoFile').innerHTML = [' Tamanho do layout incompat\u00edvel'];
	            				$scope.noUpload = false;
	            			} else {
	            				document.getElementById('infoFile').innerHTML = [lines[1]];
	            				$scope.noUpload = true;;
	            			}
	            		};
	            	})(files[i]);
	            	reader.readAsText(files[i], "UTF-8");
	                $scope.files.push(files[i])
                }
                $scope.progressVisible = false;
            })
        }
    }, false)
    //============== DRAG & DROP =============

    $scope.setFiles = function(element) {
        $scope.$apply(function($scope) {
            $scope.files = [];
            for (var i = 0; i < element.files.length; i++) {
            	var reader = new FileReader();
            	reader.onload = (function(theFile) {
            		return function(e) {
            			var text = e.target.result;
            			var lines = text.split(/[\r\n]+/g);
            			if (lines[3] == undefined || lines[3].length < 383) {
            				document.getElementById('infoFile').innerHTML = [' Tamanho do layout incompat\u00edvel'];
            				$scope.noUpload = false;
            			} else {
            				document.getElementById('infoFile').innerHTML = [lines[1]];
            				$scope.noUpload = true;;
            			}
            		};
            	})(element.files[0]);
            	reader.readAsText(element.files[0], "UTF-8");
                $scope.files.push(element.files[0]);
            }
            $scope.progressVisible = false;
        });
    };

    $scope.uploadFile = function() {
    	if ($scope.noUpload) {
	        var fd = new FormData();
	        for (var i in $scope.files) {
	            fd.append("file", $scope.files[i]);
	        }
	        var xhr = new XMLHttpRequest();
	        xhr.upload.addEventListener("progress", uploadProgress, false);
	        xhr.addEventListener("load", uploadComplete, false);
	        xhr.addEventListener("error", uploadFailed, false);
	        xhr.addEventListener("abort", uploadCanceled, false);
	        xhr.open("POST", "/security/api/upload");
	        xhr.setRequestHeader('X-XSRF-Token', angular.element('#_csrf').val());
	        $scope.progressVisible = true;
	        xhr.send(fd);
    	} else {
    		$scope.showMessage("Upload n\u00e3o permitido!");
    	}
    };
    
    $scope.excluirDadosArquivo = function() {
    	document.getElementById('infoFile').innerHTML = [''];
    };

    function uploadProgress(evt) {
        $scope.$apply(function(){
            if (evt.lengthComputable) {
                $scope.progress = Math.round(evt.loaded * 100 / evt.total)
                if ($scope.progress == 100) {
                	$scope.inclusaoVisible = true;
                }
            } else {
                $scope.progress = 'unable to compute'
            }
        })
    }

    function uploadComplete(evt) {
    	$scope.$apply(function(){
            $scope.inclusaoVisible = false;
            $scope.progress = 0;
        })
        document.getElementById('infoFile').innerHTML = [''];
    	$("#excluir-selecao").trigger("click");
    	$scope.showMessage(evt.target.responseText);
    	$scope.carregarCalendario();
    	$scope.verificaMensagemIntegracao();
    }

    function uploadFailed(evt) {
    	$scope.showMessage("Ocorreu um erro ao realizar upload do arquivo.");
    }

    function uploadCanceled(evt) {
        $scope.$apply(function(){
            $scope.progressVisible = false
        })
        $scope.showMessage("O uploaad foi cancelado pelo usuario ou o browser perdeu a conexão.");
    }
	    
	$scope.showMessage = function(msg) {
		$.gritter.add({
            title: '<center>Aten\u00e7\u00e3o!</center>',
            text: msg,
            image: '',
            sticky: false,
            time: ''
        });
	};
	$scope.carregarCalendario();
	$timeout(function(){
		$("#dropbox").trigger("click");
	},1500);
});