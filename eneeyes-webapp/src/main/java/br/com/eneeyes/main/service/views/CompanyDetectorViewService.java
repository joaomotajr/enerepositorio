package br.com.eneeyes.main.service.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.CompanyDetectorView;
import br.com.eneeyes.main.repository.views.CompanyDetectorViewRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class CompanyDetectorViewService {
	
	@Autowired
	private CompanyDetectorViewRepository repository;
	
	public BasicResult<?> existsSensor(Long uid) {
		Result<CompanyDetectorView> result = new Result<CompanyDetectorView>();
			
		try {
			Long resp = repository.countBySensorId(uid);
			
			if (resp > 0) {
				
				result.setResultType( ResultMessageType.YES_DATA );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.NO_DATA );
				result.setMessage("Nenhum Dado.");
			}
			
		} catch (Exception e) {			
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	public BasicResult<?> existsDetector(Long uid) {
		Result<CompanyDetectorView> result = new Result<CompanyDetectorView>();
			
		try {
			Long resp = repository.countByDetectorId(uid);
			
			if (resp > 0) {
				
				result.setResultType( ResultMessageType.YES_DATA );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.NO_DATA );
				result.setMessage("Nenhum Dado.");
			}
			
		} catch (Exception e) {			
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

}
