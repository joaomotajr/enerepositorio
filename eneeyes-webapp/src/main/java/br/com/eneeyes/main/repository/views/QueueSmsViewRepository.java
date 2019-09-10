package br.com.eneeyes.main.repository.views;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.views.QueueSmsView;

public interface QueueSmsViewRepository extends JpaRepository<QueueSmsView, Long> {

	public Page<QueueSmsView> findAll(Pageable pageable);
	
	@Query("select q from QueueSmsView q LEFT JOIN FETCH q.alarmMobiles LEFT JOIN FETCH q.perfilAlarm1 LEFT JOIN FETCH q.perfilAlarm2 LEFT JOIN FETCH q.perfilAlarm3")
	public List<QueueSmsView> findAllJoined();	
}
