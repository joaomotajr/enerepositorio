package br.com.eneeyes.main.repository.views;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.views.CompanyView;

public interface CompanyViewRepository extends JpaRepository<CompanyView, Long> {
	
}
