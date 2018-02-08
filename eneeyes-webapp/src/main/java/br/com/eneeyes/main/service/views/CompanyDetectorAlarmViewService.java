package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.main.model.views.CompanyDetectorAlarmView;
import br.com.eneeyes.main.repository.views.CompanyDetectorAlarmViewRepository;

@Service
public class CompanyDetectorAlarmViewService {
	
	@Autowired
	private CompanyDetectorAlarmViewRepository repository;
	
	public List<CompanyDetectorAlarmView> findAll() {
		
		return repository.findAll();		
	}

}
