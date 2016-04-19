package br.com.eneeyes.services.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import br.com.eneeyes.archetype.model.acl.Role;
import br.com.eneeyes.archetype.model.acl.RoleType;
import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.model.acl.result.UserPassResult;
import br.com.eneeyes.archetype.model.acl.result.UserResult;
import br.com.eneeyes.archetype.utils.MessageTranslateView;
import br.com.eneeyes.archetype.web.result.ResultErrorMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.archetype.web.result.ResultSuccessMessage;
import br.com.eneeyes.controllers.api.dto.user.UserDto;
import br.com.eneeyes.controllers.api.dto.user.UserPassDto;


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
