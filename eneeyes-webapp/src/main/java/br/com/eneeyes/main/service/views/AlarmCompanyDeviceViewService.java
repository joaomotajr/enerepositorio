package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.AlarmCompanyDeviceView;
import br.com.eneeyes.main.repository.views.AlarmCompanyDeviceViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class AlarmCompanyDeviceViewService {
	
	@Autowired
	private AlarmCompanyDeviceViewRepository repository;
	
	public Result<?> listAll() {
		
		Result<AlarmCompanyDeviceView> result = new Result<AlarmCompanyDeviceView>();
		
		try {
			List<AlarmCompanyDeviceView> lista = repository.findAll();

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
	
	public Result<?> listByAlarmId(Long alarmId) {
		
		Result<AlarmCompanyDeviceView> result = new Result<AlarmCompanyDeviceView>();
		
		try {
			List<AlarmCompanyDeviceView> lista = repository.findByAlarmId(alarmId);

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
