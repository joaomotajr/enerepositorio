package br.com.eneeyes.main.service.register;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.register.DetectorDto;
import br.com.eneeyes.main.model.register.Detector;
import br.com.eneeyes.main.repository.register.DetectorRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;
import br.com.eneeyes.main.service.IService;


@Named
public class DetectorService implements IService<DetectorDto> {
	
	@Inject
	private DetectorRepository repository;	
	
	public BasicResult<?> save(DetectorDto dto) {
		Result<DetectorDto> result = new Result<DetectorDto>();
		
//		Company company = companyRepository.findByUid(dto.getCompanyDto().getUid());
//		
//		Detector detector = new Detector(dto);
//		detector.setCompany(company);		
//		detector = repository.save(detector);
//		
//		dto.setUid(detector.getUid());	
		
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");					
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<DetectorDto> result = new Result<DetectorDto>(); 	
		
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
		
		Result<DetectorDto> result = new Result<DetectorDto>(); 	
		
		try {
			List<Detector> lista = repository.findAll();

			if (lista != null) {				
				
				List<DetectorDto> dto = new ArrayList<DetectorDto>();
				
				for (Detector detector   : lista) {					
					dto.add(new DetectorDto(detector) );
				}
				
				result.setList(dto);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Unidade.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public Result<?> findOne(Long uid) {
		
		Result<DetectorDto> result = new Result<DetectorDto>();
		
		try {
			Detector item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new DetectorDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Unidade.");
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
