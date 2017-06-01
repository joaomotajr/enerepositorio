package br.com.eneeyes.archetype.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.dto.RoleDto;
import br.com.eneeyes.archetype.model.Role;
import br.com.eneeyes.archetype.repository.RoleRepository;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


	@Service
	public class RoleService {
	
	@Inject
	private RoleRepository repository;
				
	public BasicResult<?> listAll()
	{		
		Result<RoleDto> result = new Result<RoleDto>();
					
		try {
			List<Role> lista = repository.findAll();

			if (lista != null) {
				
				List<RoleDto> dto = new ArrayList<RoleDto>();
				
				for (Role role : lista) {					
					dto.add(new RoleDto(role) );
				}
				
				result.setList(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Alarme Localizado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
	
		
		return result;		
	}

	public BasicResult<?> save(RoleDto roleDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}


}
