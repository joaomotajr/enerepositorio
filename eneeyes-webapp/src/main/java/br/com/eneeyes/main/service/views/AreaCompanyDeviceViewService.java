package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.AreaCompanyDeviceView;
import br.com.eneeyes.main.repository.views.AreaCompanyDeviceViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class AreaCompanyDeviceViewService {
	
	@Autowired
	private AreaCompanyDeviceViewRepository repository;
	
	public Result<?> listByAreaId(Long areaId) {		
		Result<AreaCompanyDeviceView> result = new Result<AreaCompanyDeviceView>();		
		try {
			List<AreaCompanyDeviceView> lista = repository.findByAreaId(areaId);

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
