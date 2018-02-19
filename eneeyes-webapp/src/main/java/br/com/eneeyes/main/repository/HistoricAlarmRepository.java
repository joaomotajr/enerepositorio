package br.com.eneeyes.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.HistoricAlarm;

public interface HistoricAlarmRepository extends JpaRepository<HistoricAlarm, Long> {
	
	@Query("select h from HistoricAlarm h where h.companyDeviceId = ?1 and h.date between ?2 and ?2")
	public List<HistoricAlarm> findByCompanyDeviceIdAndLastUpdateBetween(Long companyDeviceId,  Date in, Date out);
	
	@Query("select h from HistoricAlarm h where h.companyDeviceId = ?1 and h.date between ?2 and ?3")
	public Page<HistoricAlarm> findByCompanyDeviceIdAndLastUpdateBetweenPaginated(Long companyDeviceId, Date in, Date out, Pageable pageable);
	
}
