package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.QueueEmailView;
import br.com.eneeyes.main.repository.views.QueueEmailViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class QueueEmailViewService {
	
	@Autowired
	private QueueEmailViewRepository repository;
	
	public Result<?> listAll() {
		
		Result<QueueEmailView> result = new Result<QueueEmailView>();
		
		try {
			List<QueueEmailView> lista = repository.findAll();

			if (lista != null) {				
				
				result.setList(lista);				
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
	
	public List<QueueEmailView> findAll() {
		List<QueueEmailView> lista = null;
		
		try {
			 lista = repository.findAll();	
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
}