package br.com.eneeyes.main.service.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.procs.ProcDashboardDto;
import br.com.eneeyes.main.dto.views.DashCompaniesPositionDto;
import br.com.eneeyes.main.model.procs.ProcDashboard;
import br.com.eneeyes.main.model.views.DashCompaniesPosition;
import br.com.eneeyes.main.repository.procs.ProcDashboardRepository;
import br.com.eneeyes.main.repository.views.DashCompaniesPositionRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class DashCompaniesPositionService {
	
	@Autowired
	private DashCompaniesPositionRepository repository;
	
	@Autowired
	private ProcDashboardRepository procDashboarRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
		
	
	public Result<?> testProcedure(String value) {
 
		Result<ProcDashboardDto> result = new Result<ProcDashboardDto>();
		
		List<ProcDashboard> lista = procDashboarRepository.findAllLast();
		
		List<ProcDashboardDto> dto = new ArrayList<ProcDashboardDto>();
		
		for (ProcDashboard procDashboard   : lista) {					
			dto.add(new ProcDashboardDto(procDashboard) );
		}
		

		result.setList(dto);
		
		return result;
	}
	

	//@SuppressWarnings("unused")
	public Result<?> listAll() {
		
		Result<DashCompaniesPositionDto> result = new Result<DashCompaniesPositionDto>();
		
		try {
			List<DashCompaniesPosition> lista = repository.findAll();

			if (lista != null) {				

				List<DashCompaniesPositionDto> dto = new ArrayList<DashCompaniesPositionDto>();
				
				for (DashCompaniesPosition dashCompaniesPosition   : lista) {					
					dto.add(new DashCompaniesPositionDto(dashCompaniesPosition) );
				}
					
				result.setList(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Posição.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
		
	}	
	
	public Result<?> listByCompanyId(Long companyId) {
		
		Result<DashCompaniesPositionDto> result = new Result<DashCompaniesPositionDto>();
		
		try {
			List<DashCompaniesPosition> lista = repository.findByCompanyId(companyId);

			if (lista != null) {				

				List<DashCompaniesPositionDto> dto = new ArrayList<DashCompaniesPositionDto>();
				
				for (DashCompaniesPosition dashCompaniesPosition   : lista) {					
					dto.add(new DashCompaniesPositionDto(dashCompaniesPosition) );
				}
				
				result.setList(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Posição.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
		
	}

	public BasicResult<?> listOffline(Integer periodo) {
		Result<DashCompaniesPositionDto> result = new Result<DashCompaniesPositionDto>();
		
		try {
			
			Date nowD = new Date(); 
			Date lastMinutes = new Date(nowD.getTime() - (1000 * 60 *  periodo));
			
			List<DashCompaniesPosition> lista = repository.findByLastMinutesOfLastUpdate(lastMinutes);

			if (lista != null) {				

				List<DashCompaniesPositionDto> dto = new ArrayList<DashCompaniesPositionDto>();
				
				for (DashCompaniesPosition dashCompaniesPosition   : lista) {					
					dto.add(new DashCompaniesPositionDto(dashCompaniesPosition) );
				}
				
				result.setList(dto);					
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Posição.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
	
	public BasicResult<?> listOfflineByCompanyId(Integer periodo, Long companyId) {
		Result<DashCompaniesPositionDto> result = new Result<DashCompaniesPositionDto>();
		
		try {
			
			Date nowD = new Date(); 
			Date lastMinutes = new Date(nowD.getTime() - (1000 * 60 *  periodo));
			
			List<DashCompaniesPosition> lista = repository.findByLastMinutesOfLastUpdateAndCompanyId(lastMinutes, companyId);

			if (lista != null) {				

				List<DashCompaniesPositionDto> dto = new ArrayList<DashCompaniesPositionDto>();
				
				for (DashCompaniesPosition dashCompaniesPosition   : lista) {					
					dto.add(new DashCompaniesPositionDto(dashCompaniesPosition) );
				}
				
				result.setList(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Posição.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}

}
