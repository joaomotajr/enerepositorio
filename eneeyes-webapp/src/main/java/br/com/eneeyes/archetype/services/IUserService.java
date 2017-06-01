package br.com.eneeyes.archetype.services;

import br.com.eneeyes.archetype.dto.UserDto;
import br.com.eneeyes.archetype.dto.UserPassDto;
import br.com.eneeyes.archetype.result.UserPassResult;
import br.com.eneeyes.archetype.result.UserResult;

public interface IUserService {
	
	public UserResult save(UserDto userDto);
	
	public UserResult findAll(UserDto userDto);
	
	public UserPassResult updatePassword(UserPassDto userPassDto);
	
	public UserResult delete(UserDto userDto);

}
