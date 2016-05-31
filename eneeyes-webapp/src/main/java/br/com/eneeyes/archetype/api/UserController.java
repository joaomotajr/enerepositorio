package br.com.eneeyes.archetype.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eneeyes.archetype.dto.user.UserDto;
import br.com.eneeyes.archetype.dto.user.UserPassDto;
import br.com.eneeyes.archetype.result.UserPassResult;
import br.com.eneeyes.archetype.result.UserResult;
import br.com.eneeyes.archetype.services.user.UserService;

@RestController
public class UserController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    UserService service;
    
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
    
    @RequestMapping(value="/security/api/pesquisaUserNaoRelacionado", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UserResult pesquisaUserNaoRelacionado(@RequestBody UserDto userDto) throws Exception {
		return service.userNaoRelacionado(userDto);		
    }
    
    @RequestMapping(value="/security/api/pesquisaUserByFilial", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UserResult pesquisaUserByFilial(@RequestBody UserDto userDto) throws Exception {
		return service.findByFilialId(userDto);		
    }
    
    @RequestMapping(value="/security/api/edicaoUser", method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UserResult edicaoUser(@RequestBody UserDto userDto) throws Exception {
		return service.update(userDto);		
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
