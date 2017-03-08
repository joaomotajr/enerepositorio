package br.com.eneeyes.main.service.views;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.DashCompaniesPosition;
import br.com.eneeyes.main.repository.views.DashCompaniesPositionRepository;
import br.com.eneeyes.main.result.Result;

@Named
public class DashCompaniesPositionService {
	
	@Inject
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
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
		
	}

}
