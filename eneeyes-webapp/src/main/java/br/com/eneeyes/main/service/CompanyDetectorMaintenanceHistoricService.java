package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDetectorMaintenanceHistoricDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.CompanyDetectorMaintenanceHistoric;
import br.com.eneeyes.main.repository.CompanyDetectorMaintenanceHistoricRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.Result;

@Service
public class CompanyDetectorMaintenanceHistoricService implements IService<CompanyDetectorMaintenanceHistoricDto> {

	@Autowired
	private CompanyDetectorMaintenanceHistoricRepository repository;
	
	public BasicResult<?> save(CompanyDetectorMaintenanceHistoricDto dto) {
		
		Result<CompanyDetectorMaintenanceHistoricDto> result = new Result<CompanyDetectorMaintenanceHistoricDto>(); 	
		
		CompanyDetectorMaintenanceHistoric companyDetectorMaintenanceHistoric = new CompanyDetectorMaintenanceHistoric(dto);		
		CompanyDetector companyDetector = new CompanyDetector(dto.getCompanyDetectorDto());
		companyDetectorMaintenanceHistoric.setCompanyDetector(companyDetector);		
		companyDetectorMaintenanceHistoric = repository.save(companyDetectorMaintenanceHistoric);
		
		dto.setUid(companyDetectorMaintenanceHistoric.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Executado com sucesso.");	
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		Result<CompanyDetectorMaintenanceHistoricDto> result = new Result<CompanyDetectorMaintenanceHistoricDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Empresa Excluída.");
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<CompanyDetectorMaintenanceHistoricDto> result = new Result<CompanyDetectorMaintenanceHistoricDto>(); 	
		
		try {
			List<CompanyDetectorMaintenanceHistoric> lista = repository.findAll();

			if (lista != null) {
				
				List<CompanyDetectorMaintenanceHistoricDto> dto = new ArrayList<CompanyDetectorMaintenanceHistoricDto>();
				
				for (CompanyDetectorMaintenanceHistoric companyDetectorMaintenanceHistoric   : lista) {					
					dto.add(new CompanyDetectorMaintenanceHistoricDto(companyDetectorMaintenanceHistoric) );
				}
				
				result.setList(dto);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Empresa Localizada.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}


	public Result<?> findOne(Long uid) {
		
		Result<CompanyDetectorMaintenanceHistoricDto> result = new Result<CompanyDetectorMaintenanceHistoricDto>();
		
		try {
			CompanyDetectorMaintenanceHistoric item = repository.findOne(uid);

			if (item != null) {
				
				result.setEntity(new CompanyDetectorMaintenanceHistoricDto(item));
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Empresa Localizada.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
	

	public BasicResult<?> findByCompanyDetectorId(Long uid) {
		Result<CompanyDetectorMaintenanceHistoricDto> result = new Result<CompanyDetectorMaintenanceHistoricDto>();
		
		CompanyDetector companyDetector = new CompanyDetector();
		companyDetector.setUid(uid);
		
		try {
			List<CompanyDetectorMaintenanceHistoric> lista = repository.findByCompanyDetector(companyDetector);
			
			if (lista != null) {
				
				List<CompanyDetectorMaintenanceHistoricDto> dto = new ArrayList<CompanyDetectorMaintenanceHistoricDto>();
				
				for (CompanyDetectorMaintenanceHistoric companyDetectorMaintenanceHistoric   : lista) {					
					dto.add(new CompanyDetectorMaintenanceHistoricDto(companyDetectorMaintenanceHistoric) );
				}
								
				result.setList(dto);
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
