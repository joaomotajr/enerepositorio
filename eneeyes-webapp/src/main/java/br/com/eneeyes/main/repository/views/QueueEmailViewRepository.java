package br.com.eneeyes.main.repository.views;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.QueueEmailView;

public interface QueueEmailViewRepository extends JpaRepository<QueueEmailView, Long> {
	
public Page<QueueEmailView> findAll(Pageable pageable);
	
	@Query("select q from QueueEmailView q LEFT JOIN FETCH q.alarmEmails LEFT JOIN FETCH q.perfilAlarm1 LEFT JOIN FETCH q.perfilAlarm2 LEFT JOIN FETCH q.perfilAlarm3")
	public List<QueueEmailView> findAllJoined();	
}
