package br.com.eneeyes.main.service.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.views.AreaCompanyDeviceViewDto;
import br.com.eneeyes.main.model.views.AreaCompanyDeviceView;
import br.com.eneeyes.main.repository.views.AreaCompanyDeviceViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class AreaCompanyDeviceViewService {
	
	@Autowired
	private AreaCompanyDeviceViewRepository repository;
	
	public Result<?> listByAreaId(Long areaId) {		
		Result<AreaCompanyDeviceViewDto> result = new Result<>();		
		try {
			List<AreaCompanyDeviceView> lista = repository.findByAreaId(areaId);

			if (lista != null) {
				List<AreaCompanyDeviceViewDto> dto = new ArrayList<>();				
				for (AreaCompanyDeviceView areaCompanyDeviceView : lista) {					
					dto.add(new AreaCompanyDeviceViewDto(areaCompanyDeviceView));
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
