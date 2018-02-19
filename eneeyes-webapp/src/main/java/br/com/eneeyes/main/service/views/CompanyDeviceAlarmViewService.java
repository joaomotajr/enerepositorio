package br.com.eneeyes.main.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.main.model.views.CompanyDeviceAlarmView;
import br.com.eneeyes.main.repository.views.CompanyDeviceAlarmViewRepository;

@Service
public class CompanyDeviceAlarmViewService {
	
	@Autowired
	private CompanyDeviceAlarmViewRepository repository;
	
	public List<CompanyDeviceAlarmView> findAll() {
		
		return repository.findAll();		
	}

}
