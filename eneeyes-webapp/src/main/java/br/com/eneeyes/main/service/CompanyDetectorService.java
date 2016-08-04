package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDetectorDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.repository.CompanyDetectorRepository;
import br.com.eneeyes.main.repository.CompanyDeviceRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class CompanyDetectorService implements IService<CompanyDetectorDto> {

	@Inject
	private CompanyDetectorRepository repository;
	
	@Inject
	private CompanyDeviceRepository companyDeviceRepository;
	
	public BasicResult<?> save(CompanyDetectorDto dto) {
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>();		
		
		CompanyDevice companyDevice = companyDeviceRepository.findOne(dto.getCompanyDevice().getUid());
		
		CompanyDetector companyDetector = new CompanyDetector(dto);
		companyDetector.setCompanyDevice(companyDevice);
		
		companyDetector = repository.save(companyDetector);
		dto.setUid(companyDetector.getUid());
				
		result.setEntity(dto);
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Área Excluída.");
			
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

	public BasicResult<?> findByIdDeviceType(Long uid) {
		
		Result<CompanyDetectorDto> result = new Result<CompanyDetectorDto>(); 	
		
		try {
			
			CompanyDevice companyDevice = companyDeviceRepository.findOne(uid);
			
			
			
			List<CompanyDetector> lista = repository.findByCompanyDevice(companyDevice);

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
