//package br.com.eneeyes.main.service.views;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import br.com.eneeyes.archetype.web.result.ResultMessageType;
//import br.com.eneeyes.main.model.views.AreaCompanyGenericAlarmView;
//import br.com.eneeyes.main.repository.views.AreaCompanyGenericAlarmViewRepository;
//import br.com.eneeyes.main.result.Result;
//
//@Service
//public class AreaCompanyGenericAlarmViewService {
//	
//	@Autowired
//	private AreaCompanyGenericAlarmViewRepository repository;
//	
//	public Result<?> listAll() {
//		
//		Result<AreaCompanyGenericAlarmView> result = new Result<AreaCompanyGenericAlarmView>();
//		
//		try {
//			List<AreaCompanyGenericAlarmView> lista = repository.findAll();
//
//			if (lista != null) {				
//				
//				result.setList(lista);				
//				result.setResultType( ResultMessageType.SUCCESS );
//				result.setMessage("Executado com sucesso.");
//			} else {
//				result.setIsError(true);
//				result.setResultType( ResultMessageType.ERROR );
//				result.setMessage("Nenhum Dispositivo.");
//			}
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;	
//		
//	}
//	
//	public Result<?> listByAreaId(Long areaId) {
//		
//		Result<AreaCompanyGenericAlarmView> result = new Result<AreaCompanyGenericAlarmView>();	
//		
//		try {
//			List<AreaCompanyGenericAlarmView> lista = repository.findByAreaId(areaId);
//
//			if (lista != null) {
//									
//				result.setList(lista);
//				result.setResultType( ResultMessageType.SUCCESS );
//				result.setMessage("Executado com sucesso.");
//			} else {
//				result.setIsError(true);
//				result.setResultType( ResultMessageType.ERROR );
//				result.setMessage("Nenhum Dispositivo.");
//			}
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;
//	}
//	
//	public Result<?> listByCompanyDeviceId(Long companyDeviceId) {
//		
//		Result<AreaCompanyGenericAlarmView> result = new Result<AreaCompanyGenericAlarmView>();	
//		
//		try {
//			List<AreaCompanyGenericAlarmView> lista = repository.findByCompanyDeviceId(companyDeviceId);
//
//			if (lista != null) {
//									
//				result.setList(lista);
//				result.setResultType( ResultMessageType.SUCCESS );
//				result.setMessage("Executado com sucesso.");
//			} else {
//				result.setIsError(true);
//				result.setResultType( ResultMessageType.ERROR );
//				result.setMessage("Nenhum Dispositivo.");
//			}
//		} catch (Exception e) {
//			result.setIsError(true);
//			result.setMessage(e.getMessage());
//		}
//		
//		return result;
//	}
//}
