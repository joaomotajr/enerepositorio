package br.com.eneeyes.main.service.historic;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessage;
import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.HistoricDto;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.enums.LogOrigem;
import br.com.eneeyes.main.model.historic.Historic;
import br.com.eneeyes.main.repository.historic.HistoricRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.IService;
import br.com.eneeyes.main.service.PositionService;
import br.com.eneeyes.main.service.buss.ProcessAlarmService;

@Service
public class HistoricService implements IService<HistoricDto> {
		
	@Autowired
	private HistoricRepository repository;
		
	@Autowired
	PositionService positionService;
	
	@Autowired
	ProcessAlarmService processAlarmService;
	
	public Boolean saveByPositionUid(Long uid, String strValue) {
		
		BigDecimal value = new BigDecimal(strValue);		
		value = value.divide(new BigDecimal(100000));
		
		Boolean ret = false;		
		Position position = positionService.findByUid(uid);
		
		if(position != null ) {	
			Historic historic = new Historic();
			historic.setCompanyDeviceId(position.getCompanyDevice().getUid());
			historic.setLastUpdate(new Date());
			historic.setValue(value);
			historic.setLogOrigem(LogOrigem.DEVICE);			
			repository.save(historic);
		
			try {
				processAlarmService.Execute(historic);
				ret = true;
				
			} catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		return ret;
	}
	
	public ResultMessage saveByPositionIOT(Long uid, String strValue) {
		
		BigDecimal value = new BigDecimal(strValue);		
		value = value.divide(new BigDecimal(100000));
		
		ResultMessage rs = new ResultMessage();
		rs.setMessage("PROCESSADO");
						
		Position position = positionService.findByUid(uid);
		
		if (position != null ) {	
			Historic historic = new Historic();

			historic.setCompanyDeviceId(position.getCompanyDevice().getUid());
			historic.setLastUpdate(new Date());
			historic.setValue(value);
			historic.setLogOrigem(LogOrigem.DEVICE);
			
			repository.save(historic);
		
			try {
				processAlarmService.Execute(historic);
				rs.setType(ResultMessageType.SUCCESS);
				
			} catch (Exception e) {
				rs.setType(ResultMessageType.ERROR);				
			}	
		} else {
			rs.setType(ResultMessageType.NO_DATA);
		}		
		
		return rs;
	}
	
public ResultMessage saveByPositionRetroIOT(Long uid, String strValue, Long date) {
		
		BigDecimal value = new BigDecimal(strValue);		
		value = value.divide(new BigDecimal(100000));
		
		ResultMessage rs = new ResultMessage();
		rs.setMessage("PROCESSADO");
						
		Position position = positionService.findByUid(uid);
		
		if (position != null ) {	
			Historic historic = new Historic();

			historic.setCompanyDeviceId(position.getCompanyDevice().getUid());
			historic.setLastUpdate(new Date(date));
			historic.setValue(value);
			historic.setLogOrigem(LogOrigem.DEVICE);
			
			repository.save(historic);
		
			try {
				processAlarmService.Execute(historic);
				rs.setType(ResultMessageType.SUCCESS);
				
			} catch (Exception e) {
				rs.setType(ResultMessageType.ERROR);				
			}	
		} else {
			rs.setType(ResultMessageType.NO_DATA);
		}		
		
		return rs;
	}
		
	public Historic saveByPosition(Position position) {
		
		Historic historic = new Historic();
		
		historic.setCompanyDeviceId(position.getCompanyDevice().getUid());
		historic.setLastUpdate(new Date());
		historic.setValue(position.getLastValue());
		historic.setLogOrigem(LogOrigem.SYSTEM);
		
		historic = repository.save(historic);
	
		return historic;
	}

	
	@Override
	public BasicResult<?> delete(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicResult<?> findOne(Long uid) {
		Result<HistoricDto> result = new Result<HistoricDto>();
		
		try {
			Historic item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new HistoricDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Histórico.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}	

	@Override
	public BasicResult<?> save(HistoricDto dto) {
		 Result<HistoricDto> result = new Result<HistoricDto>();
				
		Historic historic = new Historic(dto);
		historic.setLastUpdate(new Date());
		
		historic = repository.save(historic);
		
		dto.setUid(historic.getUid());		
		result.setEntity(dto);
				
		try {
			processAlarmService.Execute(historic);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Histórico Gravado com Sucesso.");
			
		} catch (Exception e) {
			result.setResultType( ResultMessageType.ERROR );
			result.setMessage("Erro ao gravar Historico.");				
			e.printStackTrace();
		}						
		
		return result;
	}
	
}
