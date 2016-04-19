app.config(['$translateProvider', function ($translateProvider) {

	$translateProvider.translations('pt-br', {
		'generic.error' : 'N&atilde;o foi poss&iacute;vel realizar a opera&ccedil;&atilde;o. Favor contatar o suporte t&eacute;cnico.',
		'generic.success.no.data' : 'N&atilde;o existem dados que correspondam a pesquisa efetuada.',
		
		'user.error.role' : 'Atributo role obrigat&oacute;rio.',
        'user.error.exist' : 'Login j&aacute; existente na base dados.',
        "user.error.filial.exist" : "Usu&aacuterio relacionado a uma Filial. &Eacute; necess&aacute;rio remover o relacionamento antes de remover o usu&aacute;rio.",
        'user.success.save' : 'Usu&aacute;rio inclu&iacute;do com sucesso!',
        'user.success.update': 'Usu&aacute;rio atualizado com sucesso!',
        'user.success.delete': 'Usu&aacute;rio exclu&iacute;do com sucesso!',
        'user.success.found' : 'Usu&aacute;rio encontrado.',
        'user_related_success': 'Usu&aacute;rio relacionado com sucesso!',
        
        'filial_user_grouped': 'Para remover este relacionamento &eacute; necess&aacute;rio exclu&iacute;-lo do grupo.',
        
		'filial.error.user' : 'Usu&aacute;rio contratante obrigat&oacute;rio.',
		'filial.error.user.role' : 'S&oacute; &eacute; poss&iacute;vel incluir filial para usu&aacute;rios contratantes.',
        'filial.error.exist' : 'CNPJ j&aacute; existente na base dados.',
        'filial.success.save' : 'Filial inclu&iacute;da com sucesso!',
        'filial.success.update': 'Filial atualizada com sucesso!',
        'filial.success.delete': 'Filial exclu&iacute;da com sucesso!',
        'filial_related_success': 'Filial relacionada com sucesso!',
        
        'grupo.error.exist' : 'Nome de grupo j&aacute; existente na base dados.',
        'grupo.success.save' : 'Grupo inclu&iacute;do com sucesso!',
        'grupo.success.update': 'Grupo atualizado com sucesso!',
        'grupo.success.delete': 'Grupo exclu&iacute;do com sucesso!'
	});
	
	$translateProvider.preferredLanguage('pt-br');
	
	$translateProvider.useSanitizeValueStrategy('sanitizeParameters');
  
}]);