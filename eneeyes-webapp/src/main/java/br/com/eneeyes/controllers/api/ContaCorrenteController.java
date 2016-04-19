package br.com.eneeyes.controllers.api;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.controllers.api.dto.contaCorrente.ContaCorrenteFiltroDto;
import br.com.eneeyes.controllers.api.result.ContaCorrenteResult;
import br.com.eneeyes.services.contaCorrente.ContaCorrenteService;

@RestController
public class ContaCorrenteController {

    Log log = LogFactory.getLog(getClass());

    @Inject
    ContaCorrenteService contaCorrenteService;
    
    @RequestMapping(value="/security/api/pesquisarContaCorrente", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ContaCorrenteResult query(@RequestBody ContaCorrenteFiltroDto contaCorrenteFiltro, HttpSession session) {
        
    	return contaCorrenteService.pesquisar(contaCorrenteFiltro);
    }
}