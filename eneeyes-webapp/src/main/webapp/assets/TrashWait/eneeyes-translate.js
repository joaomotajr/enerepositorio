app.factory('dictionary', function() {
    return {
       'default' : 'pt-br',

       'en-us' : {},

       'pt-br' : {
    	   'form.error.inclusaoVenda.autorizador' : 'O campo adquirente deve ser preenchido.',
           'form.error.inclusaoVenda.codigoAutoriz' : 'O campo c&oacute;digo deve ser preenchido.',
           'form.error.inclusaoVenda.cpfCnpj' : 'O campo CNPJ deve ser preenchido.',
           'form.error.inclusaoVenda.data' : 'O campo data deve ser preenchido.',
           'form.error.inclusaoVenda.estabelecimento' : 'O campo c&oacute;digo do estabelecimento deve ser preenchido.',
           'form.error.inclusaoVenda.estadoTransacao' : 'O campo estado transa&ccedil;&atilde;o deve ser preenchido.',
           'form.error.inclusaoVenda.hora' : 'O campo hora deve ser preenchido.',
           'form.error.inclusaoVenda.nomeProduto' : 'O campo bandeira deve ser preenchido.',
           'form.error.inclusaoVenda.nsu' : 'O campo c&oacute;digo da transa&ccedil;&atilde;o deve ser preenchido.',
           'form.error.inclusaoVenda.numeroCartao' : 'O campo n&uacute;mero do cart&atilde;o deve ser preenchido.',
           'form.error.inclusaoVenda.parcelas' : 'O campo parcelas deve ser preenchido.',
           'form.error.inclusaoVenda.terminalLogico' : 'O campo c&oacute;digo do POS deve ser preenchido.',
           'form.error.inclusaoVenda.titComplTrans' : 'O campo tipo de venda deve ser preenchido.',
           'form.error.inclusaoVenda.valor' : 'O campo valor deve ser preenchido.',
           
           'signin.error' : 'N&atilde;o foi poss&iacute;vel autorizar o acesso. Verifique o login e senha.',
           'signin.inactive' : 'Usu&aacute;rio Inativo: Favor entrar em contato com o Concilia F&aacute;cil pelo telefone 9999999 ou suporte@xxxxx.xxx.',
           
           'password.error' : 'Senha atual n&atilde;o confere.',
           'new.password.error.distincts' : 'Nova senha n&atilde;o confere. Favor digitar corretamente..',
           'new.password.error.used' : 'Senha j&aacute; utilizada nas ultimas tr&ecirc;s trocas de senha.',
           'new.password.generic.error' : 'Ocorreu um erro ao tentar efetuar a troca da senha. Favor tentar novamente em instantes.',
           'new.password.success' : 'Senha alterada com sucesso! Voc&ecirc; ser&aacute; direcionado(a) para a tela de login.'
       }
    }
});

app.filter('translate', function($locale, dictionary){
    return function(text) {
        if (text == '' || text == null || text == undefined) {
            return '';
        }

        var translated = dictionary[$locale.id][text];

        if(translated == null || translated.length < 1) {
            translated = dictionary[dictionary.default][text];
            if(translated == null || translated.length < 1) {
                translated = text;
            }
        }

        return $.parseHTML(translated)[0].data;
    };
});