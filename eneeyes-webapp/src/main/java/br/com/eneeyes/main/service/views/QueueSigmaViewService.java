package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.model.views.QueueSigmaView;
import br.com.eneeyes.main.repository.views.QueueSigmaViewRepository;
import br.com.eneeyes.main.result.Result;

@Service
public class QueueSigmaViewService {
	
	@Autowired
	private QueueSigmaViewRepository repository;
	
	public Result<?> listAll() {
		
		Result<QueueSigmaView> result = new Result<QueueSigmaView>();
		
		try {
			List<QueueSigmaView> lista = repository.findAll();

			if (lista != null) {				
				
				result.setList(lista);				
				result.setResultType( ResultMessageType.SUCCESS );
				result.setMessage("Executado com sucesso.");
			} else {
				result.setIsError(true);
				result.setResultType( ResultMessageType.ERROR );
				result.setMessage("Nenhum Sigma.");
			}
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}
		
		return result;	
		
	}
	
	public List<QueueSigmaView> findAll() {
		List<QueueSigmaView> lista = null;
		
		try {
			 lista = repository.findAll();	
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
}