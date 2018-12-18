package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.AreaCompanyDeviceAlarmView;
import br.com.eneeyes.main.repository.views.AreaCompanyDeviceAlarmViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class AreaCompanyDeviceAlarmViewService {
	
	@Autowired
	private AreaCompanyDeviceAlarmViewRepository repository;
	
	public Result<?> listAll() {
		
		Result<AreaCompanyDeviceAlarmView> result = new Result<AreaCompanyDeviceAlarmView>();
		
		try {
			List<AreaCompanyDeviceAlarmView> lista = repository.findAll();

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
		
		Result<AreaCompanyDeviceAlarmView> result = new Result<AreaCompanyDeviceAlarmView>();	
		
		try {
			List<AreaCompanyDeviceAlarmView> lista = repository.findByAreaId(areaId);

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
	
	public Result<?> listByCompanyDeviceId(Long companyDeviceId) {
		
		Result<AreaCompanyDeviceAlarmView> result = new Result<AreaCompanyDeviceAlarmView>();	
		
		try {
			AreaCompanyDeviceAlarmView areaCompanyDeviceAlarmView = repository.findByCompanyDeviceId(companyDeviceId);

			if (areaCompanyDeviceAlarmView != null) {									
				result.setEntity(areaCompanyDeviceAlarmView);
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
