package br.com.eneeyes.archetype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.dto.UserDto;
import br.com.eneeyes.archetype.dto.UserPassDto;
import br.com.eneeyes.archetype.result.UserPassResult;
import br.com.eneeyes.archetype.result.UserResult;
import br.com.eneeyes.archetype.services.IUserService;

@RestController
public class UserController {

    @Autowired
    IUserService service;
    
    @RequestMapping(value="/security/api/inclusaoUser", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UserResult inclusaoUser(@RequestBody UserDto userDto) throws Exception {
		userDto.setHashDigestSha1();
		return service.save(userDto);		
    }
    
    @RequestMapping(value="/security/api/pesquisaUser", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UserResult pesquisaUser(@RequestBody UserDto userDto) throws Exception {
		return service.findAll(userDto);		
    }
 
    
    @RequestMapping(value="/security/api/remocaoUser", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UserResult remocaoUser(@RequestBody UserDto userDto) throws Exception {
		return service.delete(userDto);		
    }  
    
    @RequestMapping(value="/api/changePassword", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UserPassResult changePassword(@RequestBody UserPassDto userPassDto) {
    	userPassDto.setPasswordDigestSha1();
    	userPassDto.setNewPasswordDigestSha1();
    	userPassDto.setConfirmdDigestSha1();
    	return service.updatePassword(userPassDto);
    }

}
