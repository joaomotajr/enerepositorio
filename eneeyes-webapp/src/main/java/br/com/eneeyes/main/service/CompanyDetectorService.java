package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDetectorDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.register.Sensor;
import br.com.eneeyes.main.repository.CompanyDetectorAlarmRepository;
import br.com.eneeyes.main.repository.CompanyDetectorRepository;
import br.com.eneeyes.main.repository.CompanyDeviceRepository;
import br.com.eneeyes.main.repository.PositionRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class CompanyDetectorService implements IService<CompanyDetectorDto> {

	@Inject
	private CompanyDetectorRepository repository;
	
	@Inject
	private CompanyDeviceRepository companyDeviceRepository;
	
	@Inject
	private PositionRepository positionRepository;
	
	@Inject
	private CompanyDetectorAlarmRepository companyDetectorAlarmRepository;
	
	@Inject
	PositionService positionService;

	
	public BasicResult<?> save(CompanyDetectorDto dto) {
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>();		
		
		CompanyDevice companyDevice = companyDeviceRepository.findOne(dto.getCompanyDeviceDto().getUid());
		
		CompanyDetector companyDetector = new CompanyDetector(dto);
		companyDetector.setCompanyDevice(companyDevice);				
		companyDetector = repository.save(companyDetector);
		
		createInitialPosition(companyDetector);
						
		result.setEntity(new CompanyDetectorDto(companyDetector));
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Detector Gravado com Sucesso.");	
		
		return result;
	}
	
	public BasicResult<?> save(List<CompanyDetectorDto> listDto) {
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>();		

		List<CompanyDetector> list = new ArrayList<CompanyDetector>();
		
		for (CompanyDetectorDto companyDetectorDto   : listDto) {
			
			CompanyDetector companyDetector = new CompanyDetector(companyDetectorDto);
			list.add(companyDetector);
			createInitialPosition(companyDetector);
		}
		
		list = repository.save(list);					
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}
	
	private void createInitialPosition(CompanyDetector companyDetector) {
		
		Set<Sensor> sensors = companyDetector.getDetector().getSensors();
		
		for (Sensor sensor   : sensors) {
		
			if(positionRepository.countByCompanyDetectorAndSensor(companyDetector, sensor) == 0) {
	
				Position position = new Position();	

				position.setCompanyDetector(companyDetector);
				position.setSensor(sensor);
				position.setLastUpdate(new Date());
				position.setLastValue((double) 0);
				
				positionRepository.save(position);
			}
		}
	}


	public BasicResult<?> delete(Long uid) {
				
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>(); 	
				
		try {					
			companyDetectorAlarmRepository.deleteAlarm(uid);			
			repository.delete(uid);
			
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Detector Excluído.");
			
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
		
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>();
		
		try {
			CompanyDevice companyDevice = new CompanyDevice();
			companyDevice.setUid(uid);
			
			CompanyDetector item = repository.findByCompanyDevice(companyDevice);

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
		
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>();
		repository.setLatitudeLongitude(latitude, longitude, uid);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");
		
		return result;	
	}
	
	public BasicResult<?> findByArea(Long uid) {
		
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>(); 	
		
		try {
			
			List<CompanyDevice> companyDevice = companyDeviceRepository.findCompanyDeviceByIdArea(uid);
			
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

}
