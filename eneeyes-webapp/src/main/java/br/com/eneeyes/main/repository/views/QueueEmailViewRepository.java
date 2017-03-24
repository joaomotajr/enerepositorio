package br.com.eneeyes.main.repository.views;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eneeyes.main.model.views.QueueEmailView;

public interface QueueEmailViewRepository extends JpaRepository<QueueEmailView, Long> {
	
}
