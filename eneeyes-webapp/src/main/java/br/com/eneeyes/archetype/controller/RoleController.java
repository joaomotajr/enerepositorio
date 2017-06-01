package br.com.eneeyes.archetype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.dto.RoleDto;
import br.com.eneeyes.archetype.services.RoleService;
import br.com.eneeyes.main.result.BasicResult;

@RestController
public class RoleController {

    @Autowired
    RoleService service;
    
    @RequestMapping(value="/security/api/role/inclusaoRole", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BasicResult<?> inclusaoRole(@RequestBody RoleDto roleDto) throws Exception {
		
		return service.save(roleDto);		
    }
    
    @RequestMapping(value="/security/api/role/pesquisaRoles", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BasicResult<?> pesquisaRole() throws Exception {
		return service.listAll();		
    }
 
    
    @RequestMapping(value="/security/api/role/remocaoRole", method = RequestMethod.DELETE, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BasicResult<?> remocaoRole(@PathVariable Long uid) throws Exception {
		return service.delete(uid);		
    }  
   
}
