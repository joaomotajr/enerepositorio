package br.com.eneeyes.main.service.views;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.DashCompaniesPosition;
import br.com.eneeyes.main.repository.views.DashCompaniesPositionRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class DashCompaniesPositionService {
	
	@Autowired
	private DashCompaniesPositionRepository repository;
	
	public Result<?> listAll() {
		
		Result<DashCompaniesPosition> result = new Result<DashCompaniesPosition>();
		
		try {
			List<DashCompaniesPosition> lista = repository.findAll();

			if (lista != null) {				
				
				result.setList(lista);				
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
		
		Result<DashCompaniesPosition> result = new Result<DashCompaniesPosition>();
		
		try {
			List<DashCompaniesPosition> lista = repository.findByCompanyId(companyId);

			if (lista != null) {				
				
				result.setList(lista);				
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
		Result<DashCompaniesPosition> result = new Result<DashCompaniesPosition>();
		
		try {
			
			Date nowD = new Date(); 
			Date lastMinutes = new Date(nowD.getTime() - (1000 * 60 *  periodo));
			
			List<DashCompaniesPosition> lista = repository.findByLastMinutesOfLastUpdate(lastMinutes);

			if (lista != null) {				
				
				result.setList(lista);				
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
		Result<DashCompaniesPosition> result = new Result<DashCompaniesPosition>();
		
		try {
			
			Date nowD = new Date(); 
			Date lastMinutes = new Date(nowD.getTime() - (1000 * 60 *  periodo));
			
			List<DashCompaniesPosition> lista = repository.findByLastMinutesOfLastUpdateAndCompanyId(lastMinutes, companyId);

			if (lista != null) {				
				
				result.setList(lista);				
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
