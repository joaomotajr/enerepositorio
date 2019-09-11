package br.com.eneeyes.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.CompanyDto;
import br.com.eneeyes.main.model.Company;
import br.com.eneeyes.main.model.enums.ActionType;
import br.com.eneeyes.main.model.views.CompanySumaryView;
import br.com.eneeyes.main.model.views.CompanyView;
import br.com.eneeyes.main.repository.CompanyRepository;
import br.com.eneeyes.main.repository.views.CompanySumaryViewRepository;
import br.com.eneeyes.main.repository.views.CompanyViewRepository;
import br.com.eneeyes.main.result.BasicResult;
import br.com.eneeyes.main.result.LogResult;
import br.com.eneeyes.main.result.Result;

@Service
public class CompanyService implements IService<CompanyDto> {

	@Autowired
	private CompanyRepository repository;
	
	@Autowired
	private CompanyViewRepository companyViewRepository;
	
	@Autowired
	private CompanySumaryViewRepository companySumaryViewRepository;
	
	@Autowired
	private LogAuditoriaService logAuditoriaService;
	
	public BasicResult<?> save(CompanyDto dto) {
		
		LogResult<CompanyDto> result = new LogResult<CompanyDto>(); 	
		ActionType actionType = dto.getUid() == null || dto.getUid() == 0 ? ActionType.CREATE : ActionType.UPDATE;
		
		Company company = new Company(dto);		
		company = repository.save(company);
		
		dto.setUid(company.getUid());				
		result.setEntity(dto);
		
		result.setResultType( ResultMessageType.SUCCESS );
		result.setMessage("Empresa Gravada com sucesso.");	
		
		logAuditoriaService.save(this.getClass().getSimpleName(), actionType, result.toString());
		
		return result;
	}

	public BasicResult<?> delete(Long uid) {
				
		LogResult<CompanyDto> result = new LogResult<CompanyDto>(); 	
		
		try {			
			repository.delete(uid);
			result.setResultType( ResultMessageType.SUCCESS );
			result.setMessage("Empresa Excluída.");
			
			logAuditoriaService.save(this.getClass().getSimpleName(), ActionType.DELETE, result.toString());
			
		} catch (Exception e) {
			e.printStackTrace();			
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		
		return result;		
	}

	public Result<?> listAll() {
		
		Result<CompanyDto> result = new Result<CompanyDto>(); 	
		
		try {
			List<Company> lista = repository.findAll();
			if (lista != null) {				
				List<CompanyDto> dto = new ArrayList<CompanyDto>();				
				for (Company company   : lista) {					
					dto.add(new CompanyDto(company) );
				}				
				result.setList(dto);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}		
		return result;
	}
	
	public Result<?> listAllView() {
		
		Result<CompanyView> result = new Result<CompanyView>(); 	
		
		try {
			List<CompanyView> lista = companyViewRepository.findAll();
			if (lista != null) {
								
				result.setList(lista);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}
	
	public Result<?> listAllSumaryView() {
		
		Result<CompanySumaryView> result = new Result<CompanySumaryView>(); 	
		
		try {
			List<CompanySumaryView> lista = companySumaryViewRepository.findAll();
			if (lista != null) {
								
				result.setList(lista);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	

	}


	public Result<?> findOne(Long uid) {
		
		Result<CompanyDto> result = new Result<CompanyDto>();
		
		try {
			Company item = repository.findOne(uid);
			if (item != null) {				
				result.setEntity(new CompanyDto(item));				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
	
	public Result<?> listOneView(Long uid) {
		
		Result<CompanyView> result = new Result<CompanyView>();
		
		try {
			CompanyView item = companyViewRepository.findOne(uid);

			if (item != null) {
				
				List<CompanyView> companiesView = new ArrayList<CompanyView>();													
				companiesView.add(item);
				
				result.setList(companiesView);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
	
	public Result<?> listOneSumaryView(Long uid) {
		
		Result<CompanySumaryView> result = new Result<CompanySumaryView>();
		
		try {
			CompanySumaryView item = companySumaryViewRepository.findOne(uid);

			if (item != null) {
				
				List<CompanySumaryView> companiesView = new ArrayList<CompanySumaryView>();													
				companiesView.add(item);
				
				result.setList(companiesView);
				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhuma Compania.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
	}
}
