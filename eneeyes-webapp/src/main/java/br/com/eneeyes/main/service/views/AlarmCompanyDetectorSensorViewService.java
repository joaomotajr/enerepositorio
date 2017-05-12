package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.AlarmCompanyDetectorSensorView;
import br.com.eneeyes.main.repository.views.AlarmCompanyDetectorSensorViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class AlarmCompanyDetectorSensorViewService {
	
	@Autowired
	private AlarmCompanyDetectorSensorViewRepository repository;
	
	public Result<?> listAll() {
		
		Result<AlarmCompanyDetectorSensorView> result = new Result<AlarmCompanyDetectorSensorView>();
		
		try {
			List<AlarmCompanyDetectorSensorView> lista = repository.findAll();

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
	
	public Result<?> findAlarmCompanyDetectorSensorViewByAlarmId(Long alarmId) {
		
		Result<AlarmCompanyDetectorSensorView> result = new Result<AlarmCompanyDetectorSensorView>();
		
		try {
			List<AlarmCompanyDetectorSensorView> lista = repository.findByAlarmId(alarmId);

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
