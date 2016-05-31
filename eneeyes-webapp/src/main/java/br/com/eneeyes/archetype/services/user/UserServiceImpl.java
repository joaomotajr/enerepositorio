package br.com.eneeyes.archetype.services.user;

import javax.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.dto.user.UserDto;
import br.com.eneeyes.archetype.dto.user.UserPassDto;
import br.com.eneeyes.archetype.result.UserPassResult;
import br.com.eneeyes.archetype.result.UserResult;


@Service
public class UserServiceImpl implements UserService {
	
	private Log log = LogFactory.getLog(getClass());

	
	@Autowired
	private Validator validator;


  @Override
  public UserResult save(UserDto userDto)
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public UserResult findAll(UserDto userDto)
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public UserResult userNaoRelacionado(UserDto userDto)
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public UserResult findByFilialId(UserDto userDto)
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public UserResult update(UserDto userDto)
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public UserPassResult updatePassword(UserPassDto userPassDto)
  {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public UserResult delete(UserDto userDto)
  {
    // TODO Auto-generated method stub
    return null;
  }

		

}
