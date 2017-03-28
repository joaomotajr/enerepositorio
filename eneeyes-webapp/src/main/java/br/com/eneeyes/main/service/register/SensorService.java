package br.com.eneeyes.main.service.register;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.register.SensorDto;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.repository.register.SensorRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.IService;


@Named
public class SensorService implements IService<SensorDto> {
	
	@Inject
	private SensorRepository repository;

	
	public BasicResult<?> save(SensorDto dto) {
		Result<SensorDto> result = new Result<SensorDto>();
				
		Sensor sensor = new Sensor(dto);	
		sensor = repository.save(sensor);
		
		dto.setUid(sensor.getUid());		
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");					
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<SensorDto> result = new Result<SensorDto>(); 	
		
		try {			
			repository.delete(uid);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Sensor Excluído.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setSystemMessage(e.getMessage());
			result.setMessage("Sensor Não Pode Ser Excluído");
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<SensorDto> result = new Result<SensorDto>(); 	
		
		try {
			List<Sensor> lista = repository.findAll();

			if (lista != null) {				
				
				List<SensorDto> dto = new ArrayList<SensorDto>();
				
				for (Sensor sensor   : lista) {					
					dto.add(new SensorDto(sensor) );
				}
				
				result.setList(dto);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Sensor Localizado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public Result<?> findOne(Long uid) {
		
		Result<SensorDto> result = new Result<SensorDto>();
		
		try {
			Sensor item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new SensorDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Sensor Localizado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}

	public int setParent(Long id, Long parentId) {
		// TODO Auto-generated method stub
		return 0;
	}		
}
