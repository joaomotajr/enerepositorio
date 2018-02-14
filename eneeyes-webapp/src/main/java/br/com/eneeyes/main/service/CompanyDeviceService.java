package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDeviceDto;
import br.com.eneeyes.main.model.Area;
import br.com.eneeyes.main.model.CompanyDevice;
import br.com.eneeyes.main.model.enums.ActionType;
import br.com.eneeyes.main.repository.AreaRepository;
import br.com.eneeyes.main.repository.CompanyDeviceRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.LogResult;
import br.com.eneeyes.main.result.Result;

@Service
public class CompanyDeviceService implements IService<CompanyDeviceDto> {

	@Autowired
	private CompanyDeviceRepository repository;
	
	@Autowired
	private AreaRepository areaRepository;		
	
	@Autowired
	private LogAuditoriaService logAuditoriaService;

	public int updateCompanyDeviceName(String name, Long uid) {
			
		return repository.updateCompanyDeviceName(name, uid);		
	}

	public BasicResult<?> save(CompanyDeviceDto dto) {
		LogResult<CompanyDeviceDto> result = new LogResult<CompanyDeviceDto>();	
		ActionType actionType = dto.getUid() == null || dto.getUid() == 0 ? ActionType.CREATE : ActionType.UPDATE;
		
		Area area = areaRepository.findOne(dto.getAreaDto().getUid());
		
		CompanyDevice companyDevice = new CompanyDevice(dto);
		companyDevice.setArea(area);
		
		companyDevice = repository.save(companyDevice);
		dto.setUid(companyDevice.getUid());
		
		result.setEntity(dto);
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Dispositivos Incluído/Gravado com Sucesso.");	
		
		logAuditoriaService.save(this.getClass().getSimpleName(), actionType, result.toString());
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		LogResult<CompanyDeviceDto> result = new LogResult<CompanyDeviceDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Dispositivo Excluído.");
			
			logAuditoriaService.save(this.toString(), ActionType.DELETE, result.toString());
			
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
				result.setMessage("Nenhum Dispositivo.");
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
				result.setMessage("Nenhuma Dispositivo.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
}
