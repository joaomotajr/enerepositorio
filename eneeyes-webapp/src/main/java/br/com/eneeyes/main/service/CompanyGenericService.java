package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyGenericDto;
import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.CompanyGeneric;
import br.com.eneeyes.main.model.enums.ActionType;
import br.com.eneeyes.main.repository.CompanyDeviceRepository;
import br.com.eneeyes.main.repository.CompanyGenericRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.LogResult;
import br.com.eneeyes.main.result.Result;

@Service
public class CompanyGenericService implements IService<CompanyGenericDto> {

	@Autowired
	private CompanyGenericRepository repository;
		
	@Autowired
	private CompanyDeviceRepository companyDeviceRepository;
	
	@Autowired
	private CompanyDeviceService companyDeviceService;
		
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private LogAuditoriaService logAuditoriaService;
	
	public BasicResult<?> save(CompanyGenericDto dto) {
		LogResult<CompanyGenericDto> result = new LogResult<CompanyGenericDto>();		
		
		CompanyDevice companyDevice = companyDeviceRepository.findOne(dto.getCompanyDeviceDto().getUid());
		
		CompanyGeneric companyGeneric = new CompanyGeneric(dto);
		companyGeneric.setCompanyDevice(companyDevice);				
		companyGeneric = repository.save(companyGeneric);		
						
		result.setEntity(new CompanyGenericDto(companyGeneric));
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Detector Gravado com Sucesso.");
		
		ActionType actionType = dto.getUid() == null || dto.getUid() == 0 ? ActionType.CREATE : ActionType.UPDATE;
		
		if (actionType == ActionType.CREATE) {
			createInitialPosition(companyGeneric);
			
			updateCompanyDeviceName(dto.getName(), dto.getCompanyDeviceDto().getUid());
		}
		
		logAuditoriaService.save(this.getClass().getSimpleName(), actionType, result.toString());
		
		return result;
	}
	
	private void createInitialPosition(CompanyGeneric companyDetector) {
				
		positionService.createInitialPosition(companyDetector);		

	}
	
	private void updateCompanyDeviceName(String name, Long uid) {
		
		companyDeviceService.updateCompanyDeviceName(name, uid);
	}

	public BasicResult<?> delete(Long uid) {
				
		LogResult<CompanyGenericDto> result = new LogResult<CompanyGenericDto>(); 	
				
		try {		
			repository.delete(uid);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Detector Excluído.");
			
			logAuditoriaService.save(this.toString(), ActionType.DELETE, result.toString());
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<CompanyGenericDto> result = new Result<CompanyGenericDto>(); 	
		
		try {
			List<CompanyGeneric> lista = repository.findAll();

			if (lista != null) {
				
				List<CompanyGenericDto> dto = new ArrayList<CompanyGenericDto>();
				
				for (CompanyGeneric companyDetector   : lista) {					
					dto.add(new CompanyGenericDto(companyDetector));
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Detector Cadastrado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}

	public Result<?> findOne(Long uid) {
		
		Result<CompanyGenericDto> result = new Result<CompanyGenericDto>();
		
		try {
			CompanyGeneric item = repository.findOne(uid);

			if (item != null) {
				result.setEntity(new CompanyGenericDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Detector Cadastrado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
	
	public Result<?> findByCompanyDevice(Long uid) {
		
	Result<CompanyGenericDto> result = new Result<CompanyGenericDto>();;
		
		try {			
			CompanyGeneric item = repository.findByCompanyDeviceUid(uid);

			if (item != null) {
				result.setEntity(new CompanyGenericDto(item));				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Detector Cadastrado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}	
	
	public BasicResult<?> removeAlarm(Long uid) {
		
		LogResult<CompanyGenericDto> result = new LogResult<CompanyGenericDto>();
		repository.setAlarm(null, uid);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Alarm gravado/removido com sucesso.");
		
		logAuditoriaService.save(this.getClass().getSimpleName(), ActionType.UPDATE, result.toString());
		
		return result;	
	}
	
	public BasicResult<?> updateAlarm(Long alarmId, Long uid) {
		
		LogResult<CompanyGenericDto> result = new LogResult<CompanyGenericDto>();
		repository.setAlarm(alarmId, uid);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Alarm gravado/removido com sucesso.");
		
		logAuditoriaService.save(this.getClass().getSimpleName(), ActionType.UPDATE, result.toString());
		
		return result;	
	}
	
	public BasicResult<?> findByArea(Long uid) {
		
		Result<CompanyGenericDto> result = new Result<CompanyGenericDto>(); 	
		
		try {
			
			List<CompanyDevice> companyDevice = companyDeviceRepository.findCompanyDeviceByAreaId(uid);
			
			List<CompanyGeneric> lista = repository.findByCompanyDeviceIn(companyDevice);

			if (lista != null) {
				
				List<CompanyGenericDto> dto = new ArrayList<CompanyGenericDto>();
				
				for (CompanyGeneric companyDetector   : lista) {					
					dto.add(new CompanyGenericDto(companyDetector));
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Detector Cadastrado.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public List<CompanyGeneric> findByAreaId(Long uid) {
			
		List<CompanyDevice> companyDevice = companyDeviceRepository.findCompanyDeviceByAreaId(uid);
		
		return repository.findByCompanyDeviceIn(companyDevice);

	}

}
