package br.com.eneeyes.archetype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.dto.UserDto;
import br.com.eneeyes.archetype.services.UserService;
import br.com.eneeyes.main.result.BasicResult;

@RestController
public class UserController {

    @Autowired
    UserService service;
    
    @RequestMapping(value="/security/api/user/inclusaoUser", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BasicResult<?> inclusaoUser(@RequestBody UserDto userDto) throws Exception {
		userDto.setHashDigestSha1();
		return service.save(userDto);		
    }
    
    @RequestMapping(value="/security/api/user/pesquisaUsers", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BasicResult<?> pesquisaUsers() throws Exception {
		return service.listAll();		
    } 
    
    @RequestMapping(value="/security/api/user/remocaoUser", method = RequestMethod.DELETE, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BasicResult<?> remocaoUser(@PathVariable Long uid) throws Exception {
		return service.delete(uid);		
    }  
   
}
