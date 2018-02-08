package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDetectorDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.enums.ActionType;
import br.com.eneeyes.main.repository.CompanyDetectorRepository;
import br.com.eneeyes.main.repository.CompanyDeviceRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.LogResult;
import br.com.eneeyes.main.result.Result;

@Service
public class CompanyDetectorService implements IService<CompanyDetectorDto> {

	@Autowired
	private CompanyDetectorRepository repository;
		
	@Autowired
	private CompanyDeviceRepository companyDeviceRepository;
	
	@Autowired
	private CompanyDeviceService companyDeviceService;
		
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private LogAuditoriaService logAuditoriaService;
	
	public BasicResult<?> save(CompanyDetectorDto dto) {
		LogResult<CompanyDetectorDto> result = new LogResult<CompanyDetectorDto>();		
		
		CompanyDevice companyDevice = companyDeviceRepository.findOne(dto.getCompanyDeviceDto().getUid());
		
		CompanyDetector companyDetector = new CompanyDetector(dto);
		companyDetector.setCompanyDevice(companyDevice);				
		companyDetector = repository.save(companyDetector);		
						
		result.setEntity(new CompanyDetectorDto(companyDetector));
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Detector Gravado com Sucesso.");
		
		ActionType actionType = dto.getUid() == null || dto.getUid() == 0 ? ActionType.CREATE : ActionType.UPDATE;
		
		if (actionType == ActionType.CREATE) {
			createInitialPosition(companyDetector);
			
			updateCompanyDeviceName(dto.getName(), dto.getCompanyDeviceDto().getUid());
		}
		
		logAuditoriaService.save(this.getClass().getSimpleName(), actionType, result.toString());
		
		return result;
	}
	
	private void createInitialPosition(CompanyDetector companyDetector) {
				
		positionService.createInitialPosition(companyDetector);		

	}
	
	private void updateCompanyDeviceName(String name, Long uid) {
		
		companyDeviceService.updateCompanyDeviceName(name, uid);
	}

	public BasicResult<?> delete(Long uid) {
				
		LogResult<CompanyDetectorDto> result = new LogResult<CompanyDetectorDto>(); 	
				
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
		
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>(); 	
		
		try {
			List<CompanyDetector> lista = repository.findAll();

			if (lista != null) {
				
				List<CompanyDetectorDto> dto = new ArrayList<CompanyDetectorDto>();
				
				for (CompanyDetector companyDetector   : lista) {					
					dto.add(new CompanyDetectorDto(companyDetector));
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
		
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>();
		
		try {
			CompanyDetector item = repository.findOne(uid);

			if (item != null) {
				result.setEntity(new CompanyDetectorDto(item));
				
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
		
	Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>();;
		
		try {			
			CompanyDetector item = repository.findByCompanyDeviceUid(uid);

			if (item != null) {
				result.setEntity(new CompanyDetectorDto(item));				
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
	
	public BasicResult<?> updateLatitudeLongitude(Double latitude, Double longitude, Long uid) {
		
		LogResult<CompanyDetectorDto> result = new LogResult<CompanyDetectorDto>();
		repository.setLatitudeLongitude(latitude, longitude, uid);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Coordenadadas Salvas com sucesso.");
		
		logAuditoriaService.save(this.getClass().getSimpleName(), ActionType.UPDATE, result.toString());
		
		return result;	
	}
	
	public BasicResult<?> removeAlarm(Long uid) {
		
		LogResult<CompanyDetectorDto> result = new LogResult<CompanyDetectorDto>();
		repository.setAlarm(null, uid);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Alarm gravado/removido com sucesso.");
		
		logAuditoriaService.save(this.getClass().getSimpleName(), ActionType.UPDATE, result.toString());
		
		return result;	
	}
	
	public BasicResult<?> updateAlarm(Long alarmId, Long uid) {
		
		LogResult<CompanyDetectorDto> result = new LogResult<CompanyDetectorDto>();
		repository.setAlarm(alarmId, uid);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Alarm gravado/removido com sucesso.");
		
		logAuditoriaService.save(this.getClass().getSimpleName(), ActionType.UPDATE, result.toString());
		
		return result;	
	}
	
	public BasicResult<?> findByArea(Long uid) {
		
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>(); 	
		
		try {
			
			List<CompanyDevice> companyDevice = companyDeviceRepository.findCompanyDeviceByAreaId(uid);
			
			List<CompanyDetector> lista = repository.findByCompanyDeviceIn(companyDevice);

			if (lista != null) {
				
				List<CompanyDetectorDto> dto = new ArrayList<CompanyDetectorDto>();
				
				for (CompanyDetector companyDetector   : lista) {					
					dto.add(new CompanyDetectorDto(companyDetector));
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
	
	public List<CompanyDetector> findByAreaId(Long uid) {
			
		List<CompanyDevice> companyDevice = companyDeviceRepository.findCompanyDeviceByAreaId(uid);
		
		return repository.findByCompanyDeviceIn(companyDevice);

	}

}
