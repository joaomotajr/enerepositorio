package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.utils.Util;
import br.com.eneeyes.archetype.web.config.auth.signin.SigninUtils;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.LogAuditoriaDto;
import br.com.eneeyes.main.model.LogAuditoria;
import br.com.eneeyes.main.model.enums.ActionType;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.views.LogAuditoriaView;
import br.com.eneeyes.main.repository.LogAuditoriaRepository;
import br.com.eneeyes.main.repository.views.LogAuditoriaViewRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.GroupResult;
import br.com.eneeyes.main.result.Result;

@Service
public class LogAuditoriaService implements IService<LogAuditoriaDto> {

	@Autowired
	private LogAuditoriaRepository repository;

	@Autowired
	private LogAuditoriaViewRepository repositoryView;
	
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

	public GroupResult<?> listPreDefView(IntervalType intervalType, Integer currentPage, Integer lenPage) {
		
		GroupResult<LogAuditoriaView> result = new GroupResult<LogAuditoriaView>(); 	
		
		try {
			
			Date fim = new Date(); 
			Date inicio = new Date();
			
			if(intervalType ==  IntervalType.UM_MES)				
				inicio = Util.addMonth(fim, -1);
			else
				inicio = new Date(fim.getTime() - (1000 * 60 * 60 * intervalType.getValue()));								
			
			Page<LogAuditoriaView> page = null;
			
			page= repositoryView.findByDateTimeBetweenPaginated(inicio, fim, new PageRequest(currentPage, lenPage));			
			
			result = getResults(page);	
			

		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public GroupResult<?> listIntervalView(Date dateIn, Date dateOut, Integer currentPage, Integer lenPage) {
		
		GroupResult<LogAuditoriaView> result = new GroupResult<LogAuditoriaView>(); 	
		
		try {									
			
			Page<LogAuditoriaView> page = null;
			
			page= repositoryView.findByDateTimeBetweenPaginated(dateIn, dateOut, new PageRequest(currentPage, lenPage));			
			
			result = getResults(page);	
			

		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public GroupResult<?> listPreDefByCompanyIdView(Long companyId, IntervalType intervalType, Integer currentPage, Integer lenPage ) {
		
		GroupResult<LogAuditoriaView> result = new GroupResult<LogAuditoriaView>();
		
		try {
			Date fim = new Date(); 
			Date inicio = new Date();
			
			if(intervalType ==  IntervalType.UM_MES)				
				inicio = Util.addMonth(fim, -1);
			else
				inicio = new Date(fim.getTime() - (1000 * 60 * 60 * intervalType.getValue()));								
			
			Page<LogAuditoriaView> page = null;
							
			
			page = repositoryView.findByCompanyIdAndDateTimeBetweenPaginated(companyId, inicio, fim, new PageRequest(currentPage, lenPage));								
			
			result = getResults(page);	
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public GroupResult<?> listIntervalByCompanyIdView(Long companyId, Date dateIn, Date dateOut, Integer currentPage, Integer lenPage ) {
		
		GroupResult<LogAuditoriaView> result = new GroupResult<LogAuditoriaView>();
		
		try {								
			
			Page<LogAuditoriaView> page = null;							
			
			page = repositoryView.findByCompanyIdAndDateTimeBetweenPaginated(companyId, dateIn, dateOut, new PageRequest(currentPage, lenPage));								
			
			result = getResults(page);	
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	private GroupResult<LogAuditoriaView> getResults(Page<LogAuditoriaView> page) {
		
		GroupResult<LogAuditoriaView> result = new GroupResult<LogAuditoriaView>();
		
		if (page != null) {
			
			List<LogAuditoriaView> lista = new ArrayList<LogAuditoriaView>();
			for (LogAuditoriaView item : page) {
				
				lista.add(item);					
			}
			
			result.setFirstPage(page.isFirstPage());
			result.setLastPage(page.isLastPage());
			result.setTotalList(new Long(page.getTotalElements()).intValue());			
			result.setCountPages(page.getTotalPages());
			
			result.setList(lista);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Executado com sucesso.");	
		} else {
			
			result.setIsError(true);
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage("Nenhum Histórico.");
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

}
