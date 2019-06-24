package br.com.eneeyes.main.repository.historic;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eneeyes.main.model.historic.HistoricViewHour30;
import br.com.eneeyes.main.model.historic.IHistoricHourGroup;

public interface HistoricViewHour30Repository extends JpaRepository<HistoricViewHour30, Long> {

	@Query("select h from HistoricViewHour30 h where h.companyDeviceId = ?1 and h.lastUpdateHour between ?2 and ?3")
	public Page<IHistoricHourGroup> findByCompanyDeviceIdAndLastUpdateBetweenPaginated(Long companyDeviceId, Date in,
			Date out, Pageable pageable);

	@Query("select h from HistoricViewHour30 h where h.companyDeviceId in ?1 and h.lastUpdateHour between ?2 and ?3")
	public Page<IHistoricHourGroup> findByCompanyDeviceInAndLastUpdateBetweenPaginated(List<Long> companyDevice, Date in,
			Date out, Pageable pageable);
}
