package br.com.eneeyes.services.user;

import br.com.eneeyes.archetype.model.acl.result.UserPassResult;
import br.com.eneeyes.archetype.model.acl.result.UserResult;
import br.com.eneeyes.controllers.api.dto.user.UserDto;
import br.com.eneeyes.controllers.api.dto.user.UserPassDto;

public interface UserService {
	
	public UserResult save(UserDto userDto);
	
	public UserResult findAll(UserDto userDto);
	
	public UserResult userNaoRelacionado(UserDto userDto);
	
	public UserResult findByFilialId(UserDto userDto);
	
	public UserResult update(UserDto userDto);
	
	public UserPassResult updatePassword(UserPassDto userPassDto);
	
	public UserResult delete(UserDto userDto);

}
