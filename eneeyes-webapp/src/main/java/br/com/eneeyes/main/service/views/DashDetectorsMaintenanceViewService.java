package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.DashDetectorsMaintenanceView;
import br.com.eneeyes.main.repository.views.DashDetectorsMaintenanceViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class DashDetectorsMaintenanceViewService {
	
	@Autowired
	private DashDetectorsMaintenanceViewRepository repository;
	
	public Result<?> listAll() {
		
		Result<DashDetectorsMaintenanceView> result = new Result<DashDetectorsMaintenanceView>();
		
		try {
			List<DashDetectorsMaintenanceView> lista = repository.findAll();

			if (lista != null) {
				
				result.setList(lista);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
		
	}

}
