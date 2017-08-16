package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.AreaCompanyDetectorAlarmView;
import br.com.eneeyes.main.repository.views.AreaCompanyDetectorAlarmViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class AreaCompanyDetectorAlarmViewService {
	
	@Autowired
	private AreaCompanyDetectorAlarmViewRepository repository;
	
	public Result<?> listAll() {
		
		Result<AreaCompanyDetectorAlarmView> result = new Result<AreaCompanyDetectorAlarmView>();
		
		try {
			List<AreaCompanyDetectorAlarmView> lista = repository.findAll();

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
	
	public Result<?> listByAreaId(Long areaId) {
		
		Result<AreaCompanyDetectorAlarmView> result = new Result<AreaCompanyDetectorAlarmView>();	
		
		try {
			List<AreaCompanyDetectorAlarmView> lista = repository.findByAreaId(areaId);

			if (lista != null) {
									
				result.setList(lista);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Dispositivo.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
}
