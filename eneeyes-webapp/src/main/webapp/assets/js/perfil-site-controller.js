app.controller('PerfilController', function ($scope, $filter, Perfil) {
	$scope.result = {value : {loginModificado : false}},
	$scope.forms = {
		perfil : {
		nome : '', 	email : '',	endereco : '', cidade: '', estado : '', pais: '', ocupacao : '', vinculo : '',		
		//experiencia profissional
		titulo : '', filiacao : '', tempoAcademia : '',
		numeroArtigos : '',	papel : '', organizacao : '',
		tempoInovacao : '', numeroPatentes : '', status : '',
		empresa : '', tempoAtuacao : '', numeroProdutos : '',
      },
      recuperarSenha : {
      	email1 : '',
      	email2: '',
      }
    };

	$scope.carregarMeusDocumentos = function() {
		$scope.meusDocumentos = new Perfil.documentos();
		$scope.meusDocumentos.$lista({_csrf : angular.element('#_csrf').val()},function(){}, function(data) {
			 if (data.status >= 400 && data.status <= 505 ) {
				 angular.element('body').removeClass('loading');
				 angular.element('.session-expired').modal('show');
				 $timeout(function(){
					window.location.href='/';
				 },2500);
			 }
		 });
	};

	$scope.carregarDocumento = function(uid, mtVersion) {
		window.location.href = "/security/documento/" + uid + "/mtVersion/" + mtVersion;
	};
	
    $scope.hasError = function(errorMessage) {
        var hasError = false;

        if ($scope.result == undefined || $scope.result.errorMessages == undefined) {
            return hasError;
        }

        angular.forEach($scope.result.errorMessages, function(value) {
            var index = errorMessage.length * -1;
            if (value.message.substr(index) == errorMessage) {
                hasError = 'has-error';
            }
        });
        return hasError;
    };

    $scope.hasErrors = function() {
        var hasError = false;

        if ($scope.result == undefined || $scope.result.errorMessages == undefined) {
            return hasError;
        }

        if ($scope.result.errorMessages.length > 0) {
            hasError = 'has-error';
        }

        return hasError;
    };

	$scope.validar = function() {
	    var security = new Perfil.security()
	    security.$validar({_csrf : angular.element('#_csrf').val()}, function(result) {
            $scope.result = result;
            $scope.forms.perfil.nome = $scope.result.value.nome;
            $scope.forms.perfil.email = $scope.result.value.email;
            $scope.forms.perfil.endereco = $scope.result.value.endereco;
            $scope.forms.perfil.cidade = $scope.result.value.cidade;
            $scope.forms.perfil.estado = $scope.result.value.estado;
            $scope.forms.perfil.pais = $scope.result.value.pais;
            $scope.forms.perfil.ocupacao = $scope.result.value.ocupacao;
            $scope.forms.perfil.vinculo = $scope.result.value.vinculo;
        }, function(data) {
			 if (data.status >= 400 && data.status <= 505 ) {
				 angular.element('body').removeClass('loading');
				 angular.element('.session-expired').modal('show');
				 $timeout(function(){
					window.location.href='/';
				 },2500);
			 }
		 });
	};

    $scope.salvar = function() {
        $scope.result = new Perfil.global({
        	nome : $scope.forms.perfil.nome,
        	email : $scope.forms.perfil.email,
        	endereco : $scope.forms.perfil.endereco,
        	cidade : $scope.forms.perfil.cidade,
            estado : $scope.forms.perfil.estado,
            pais : $scope.forms.perfil.pais,
        	ocupacao : $scope.forms.perfil.ocupacao,
        	vinculo : $scope.forms.perfil.vinculo,
    		//experiencia profissional
        	experienciaProfissional : {
        		titulo : $scope.forms.perfil.titulo,
        		filiacao : $scope.forms.perfil.filiacao,
        		tempoAcademia : $scope.forms.perfil.tempoAcademia,
        		numeroArtigos : $scope.forms.perfil.numeroArtigos,
        		papel : $scope.forms.perfil.papel,
        		organizacao : $scope.forms.perfil.organizacao,
        		tempoInovacao : $scope.forms.perfil.tempoInovacao,
        		numeroPatentes : $scope.forms.perfil.numeroPatentes,
        		status : $scope.forms.perfil.status,
        		empresa : $scope.forms.perfil.empresa,
        		tempoAtuacao : $scope.forms.perfil.tempoAtuacao,
        		numeroProdutos : $scope.forms.perfil.numeroProdutos
        	},
        	//interesse
        	interesse : {
        		uid : $scope.forms.perfil.uid,
        		ciencia : {
	        		ramosCientificos : $scope.forms.perfil.ramosCientificos,
	        		linhaPesquisa1 : $scope.forms.perfil.linhaPesquisa1,
	        		pesquisaRelacionadas1 : $scope.forms.perfil.pesquisaRelacionadas1,
	        		univInstitutosPesquisas1 : $scope.forms.perfil.univInstitutosPesquisas1,
	        		pesquisadores1 : $scope.forms.perfil.pesquisadores1,
	        		linhaPesquisa2 : $scope.forms.perfil.linhaPesquisa2,
	        		pesquisaRelacionadas2 : $scope.forms.perfil.pesquisaRelacionadas2,
	        		univInstitutosPesquisas2 : $scope.forms.perfil.univInstitutosPesquisas2,
	        		pesquisadores2 : $scope.forms.perfil.pesquisadores2,
	        		linhaPesquisa3 : $scope.forms.perfil.linhaPesquisa3,
	        		pesquisaRelacionadas3 : $scope.forms.perfil.pesquisaRelacionadas3,
	        		univInstitutosPesquisas3 : $scope.forms.perfil.univInstitutosPesquisas3,
	        		pesquisadores3 : $scope.forms.perfil.pesquisadores3
        		},
        		tecnologia : {
        			camposTecnologicos : $scope.forms.perfil.camposTecnologicos,
        			aplicacoesInteresse1 : $scope.forms.perfil.aplicacoesInteresse1,
        			aplicacoesRelacionadas1 : $scope.forms.perfil.aplicacoesRelacionadas1,
        			empresas1 : $scope.forms.perfil.empresas1,
        			inventores1 : $scope.forms.perfil.inventores1,
        			aplicacoesInteresse2 : $scope.forms.perfil.aplicacoesInteresse2,
        			aplicacoesRelacionadas2 : $scope.forms.perfil.aplicacoesRelacionadas2,
        			empresas2 : $scope.forms.perfil.empresas2,
        			inventores2 : $scope.forms.perfil.inventores2,
        			aplicacoesInteresse3 : $scope.forms.perfil.aplicacoesInteresse3,
        			aplicacoesRelacionadas3 : $scope.forms.perfil.aplicacoesRelacionadas3,
        			empresas3 : $scope.forms.perfil.empresas3,
        			inventores3 : $scope.forms.perfil.inventores3
        		},
        		mercado : {
        			segmentoMercado : $scope.forms.perfil.segmentoMercado,
        			produtosSimilares1 : $scope.forms.perfil.produtosSimilares1,
        			produtosSubstitutos1 : $scope.forms.perfil.produtosSubstitutos1,
        			empresasMercado1 : $scope.forms.perfil.empresasMercado1,
        			vendedores1 : $scope.forms.perfil.vendedores1,
        			produtosSimilares2 : $scope.forms.perfil.produtosSimilares2,
        			produtosSubstitutos2 : $scope.forms.perfil.produtosSubstitutos2,
        			empresasMercado2 : $scope.forms.perfil.empresasMercado2,
        			vendedores2 : $scope.forms.perfil.vendedores2,
        			produtosSimilares3 : $scope.forms.perfil.produtosSimilares3,
        			produtosSubstitutos3 : $scope.forms.perfil.produtosSubstitutos3,
        			empresasMercado3 : $scope.forms.perfil.empresasMercado3,
        			vendedores3 : $scope.forms.perfil.vendedores3
        		}
        	
        	}
        });

        $scope.result.$salvar({_csrf : angular.element('#_csrf').val()}, function(){
        	if($scope.result.resultType != 'ERROR'){
        		$scope.forms.perfil.email = $scope.result.value.email;
        		$scope.forms.perfil.successMessages = true;
        		if($scope.forms.perfil.autenticado == true){
                	window.location.href = "/result-search.html";
                }else{
                	window.location.href = "/cadastro-sucess.html";
                }
        	}
        }, function(data) {
			 if (data.status >= 400 && data.status <= 505 ) {
				 angular.element('body').removeClass('loading');
				 angular.element('.session-expired').modal('show');
				 $timeout(function(){
					window.location.href='/';
				 },2500);
			 }
		 });
    };
});