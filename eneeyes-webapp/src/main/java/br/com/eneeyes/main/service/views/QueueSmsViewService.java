package br.com.eneeyes.main.service.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.QueueSmsView;
import br.com.eneeyes.main.repository.views.QueueSmsViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class QueueSmsViewService {
	
	@Autowired
	private QueueSmsViewRepository repository;
	
	public Result<?> listAll() {
		
		Result<QueueSmsView> result = new Result<QueueSmsView>();
		
		try {
			List<QueueSmsView> lista = repository.findAll();

			if (lista != null) {				
				
				result.setList(lista);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Sms.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
		
	}
	
	public List<QueueSmsView> findAllLimit() {
		
		List<QueueSmsView> lista = new ArrayList<>();		
		try {
			Page<QueueSmsView> page = null;
			page = repository.findAll(new PageRequest(1, 2));			 
			if (page != null) {				 
				for (QueueSmsView item : page) {
					lista.add(item);
				}
			}			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<QueueSmsView> findAll() {
		List<QueueSmsView> lista = null;		
		try {
			 lista = repository.findAllJoined();			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
}