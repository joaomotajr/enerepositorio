package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDeviceDto;
import br.com.eneeyes.main.model.Area;
import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.repository.AreaRepository;
import br.com.eneeyes.main.repository.CompanyDeviceRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;


@Named
public class CompanyDeviceService implements IService<CompanyDeviceDto> {

	@Inject
	private CompanyDeviceRepository repository;
	
	@Inject
	private AreaRepository areaRepository;

//	@Inject
//	private PositionRepository positionRepository;
	
	public BasicResult<?> save(CompanyDeviceDto dto) {
		Result<CompanyDeviceDto> result = new Result<CompanyDeviceDto>();		
		
		Area area = areaRepository.findByUid(dto.getAreaDto().getUid());
		
		CompanyDevice companyDevice = new CompanyDevice(dto);
		companyDevice.setArea(area);
		
		companyDevice = repository.save(companyDevice);
		dto.setUid(companyDevice.getUid());
		
//		createInitialPosition(area, companyDevice);
		
		result.setEntity(dto);
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

//	private void createInitialPosition(Area area, CompanyDevice companyDevice) {
//		
//		if(positionRepository.findByCompanyDevice(companyDevice) == null) {
//
//			Position position = new Position();	
//			position.setArea(area);
//			position.setCompanyDevice(companyDevice);
//			position.setLastUpdate(new Date());
//			position.setLastValue((double) 0);
//			
//			positionRepository.save(position);
//		}
//	}

	public BasicResult<?> delete(Long uid) {
				
		Result<CompanyDeviceDto> result = new Result<CompanyDeviceDto>(); 	
		
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
		
		Result<CompanyDeviceDto> result = new Result<CompanyDeviceDto>(); 	
		
		try {
			List<CompanyDevice> lista = repository.findAll();

			if (lista != null) {
				
				List<CompanyDeviceDto> dto = new ArrayList<CompanyDeviceDto>();
				
				for (CompanyDevice area   : lista) {					
					dto.add(new CompanyDeviceDto(area) );
				}
								
				result.setList(dto);
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma area.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}

	public Result<?> findOne(Long uid) {
		
		Result<CompanyDeviceDto> result = new Result<CompanyDeviceDto>();
		
		try {
			CompanyDevice item = repository.findOne(uid);

			if (item != null) {
				result.setEntity(new CompanyDeviceDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma area.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
}
