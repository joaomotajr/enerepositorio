package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.config.auth.signin.SigninUtils;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.LogAuditoriaDto;
import br.com.eneeyes.main.model.LogAuditoria;
import br.com.eneeyes.main.model.enums.ActionType;
import br.com.eneeyes.main.repository.LogAuditoriaRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class LogAuditoriaService implements IService<LogAuditoriaDto> {

	@Autowired
	private LogAuditoriaRepository repository;
	
	public BasicResult<?> save(LogAuditoriaDto dto) {
		Result<LogAuditoriaDto> result = new Result<LogAuditoriaDto>();		
				
		LogAuditoria logAuditoria = new LogAuditoria(dto);
		logAuditoria = repository.save(logAuditoria);
		
		dto.setUid(logAuditoria.getUid());
				
		result.setEntity(dto);
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Área Gravada com sucesso.");	
		
		return result;
	}
	
	public BasicResult<?> save(String entityName, ActionType actionType, String detail) {
		
		LogAuditoriaDto logAuditoria = new LogAuditoriaDto();		
			
		logAuditoria.setDateTime(new Date());
		logAuditoria.setEntity(entityName);
		logAuditoria.setDetail(detail);
		logAuditoria.setAction(actionType);
		logAuditoria.setUserId(SigninUtils.principal().getId());
		
		if(SigninUtils.principal().getCompany() != null)
			logAuditoria.setCompanyId(SigninUtils.principal().getCompany().getUid());
		
		logAuditoria.setIp("");
		
		return save(logAuditoria);
	}
	
	public BasicResult<?> delete(Long uid) {
				
		Result<LogAuditoriaDto> result = new Result<LogAuditoriaDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Área Excluída.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<LogAuditoriaDto> result = new Result<LogAuditoriaDto>(); 	
		
		try {
			List<LogAuditoria> lista = repository.findAll();

			if (lista != null) {
				
				List<LogAuditoriaDto> dto = new ArrayList<LogAuditoriaDto>();
				
				for (LogAuditoria item   : lista) {					
					dto.add(new LogAuditoriaDto(item) );
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Área.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}

	public Result<?> findOne(Long uid) {
		
		Result<LogAuditoriaDto> result = new Result<LogAuditoriaDto>();
		
		try {
			LogAuditoria item = repository.findOne(uid);

			if (item != null) {
				result.setEntity(new LogAuditoriaDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Área.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
	
	public Result<?> listByCompanyId(Long companyId) {
		
		Result<LogAuditoriaDto> result = new Result<LogAuditoriaDto>(); 	
		
		try {
			List<LogAuditoria> lista = repository.findByCompanyId(companyId);

			if (lista != null) {
				
				List<LogAuditoriaDto> dto = new ArrayList<LogAuditoriaDto>();
				
				for (LogAuditoria item : lista) {					
					dto.add(new LogAuditoriaDto(item) );
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
}
