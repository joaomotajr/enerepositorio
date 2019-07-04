package br.com.eneeyes.main.service.historic.exunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.eneeyes.archetype.web.result.ResultMessageType;
import br.com.eneeyes.main.dto.register.UidDto;
import br.com.eneeyes.main.model.enums.IntervalType;
import br.com.eneeyes.main.model.historic.IHistoricGroup;
import br.com.eneeyes.main.repository.historic.exunion.HistoricViewHour30Repository;
import br.com.eneeyes.main.repository.historic.exunion.HistoricViewHourAllRepository;
import br.com.eneeyes.main.repository.historic.exunion.HistoricViewHourRepository;
import br.com.eneeyes.main.result.GroupResult;

@Service
public class HistoricViewHourService {

	@Autowired
	private HistoricViewHourRepository repository;

	@Autowired
	private HistoricViewHour30Repository repository30;

	@Autowired
	private HistoricViewHourAllRepository repositoryAll;

	public GroupResult<?> findByCompanyDeviceAndInterval(Long companyDeviceId, IntervalType intervalType,
			Integer currentPage, Integer lenPage) {

		Date dataFim = new Date();
		Date dataInicio = new Date();

		if (intervalType == IntervalType.UM_MES) {
			dataInicio = addMonth(dataFim, -1);
		} else {
			dataInicio = new Date(dataFim.getTime() - (1000 * 60 * 60 * intervalType.getValue()));
		}
		return findByCompanyDeviceAndIntervalHours(companyDeviceId, dataInicio, dataFim, currentPage, lenPage);
	}

	private static Date addMonth(Date date, int qtde) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, qtde);

		return c.getTime();
	}

	public GroupResult<?> getResults(Page<IHistoricGroup> page) {

		GroupResult<IHistoricGroup> result = new GroupResult<IHistoricGroup>();

		if (page != null) {

			List<IHistoricGroup> lista = new ArrayList<IHistoricGroup>();
			for (IHistoricGroup item : page) {
				lista.add(item);
			}

			result.setFirstPage(page.isFirstPage());
			result.setLastPage(page.isLastPage());
			result.setTotalList(new Long(page.getTotalElements()).intValue());
			result.setCountPages(page.getTotalPages());

			result.setList(lista);
			result.setResultType(ResultMessageType.SUCCESS);
			result.setMessage("Executado com sucesso.");
		} else {

			result.setIsError(true);
			result.setResultType(ResultMessageType.ERROR);
			result.setMessage("Nenhum Histórico.");
		}

		return result;

	}

	public GroupResult<?> findByCompanyDeviceAndIntervalHours(Long companyDeviceId, Date dateIn, Date dateOut,
			Integer currentPage, Integer lenPage) {

		GroupResult<?> result = new GroupResult<IHistoricGroup>();

		Date date = new Date();
		int diffDaysIn = (int) ((date.getTime() - dateIn.getTime()) / (1000 * 60 * 60 * 24));
		int diffDaysOut = (int) ((date.getTime() - dateOut.getTime()) / (1000 * 60 * 60 * 24));

		try {

			Page<IHistoricGroup> page = null;
			if (diffDaysIn >= 30 && diffDaysOut >= 30) {
				page = repository30.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, dateIn, dateOut,
						new PageRequest(currentPage, lenPage));
			} else if (diffDaysIn <= 30 && diffDaysOut <= 30) {
				page = repository.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, dateIn, dateOut,
						new PageRequest(currentPage, lenPage));
			} else {
				page = repositoryAll.findByCompanyDeviceIdAndLastUpdateBetweenPaginated(companyDeviceId, dateIn,
						dateOut, new PageRequest(currentPage, lenPage));
			}
			result = getResults(page);
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}

		return result;
	}

	public GroupResult<?> findByCompanyDevicesInAndIntervalHours(UidDto ids, Date dateIn, Date dateOut,
			Integer currentPage, Integer lenPage) {

		GroupResult<?> result = new GroupResult<IHistoricGroup>();
		Date date = new Date();
		int diffDaysIn = (int) ((date.getTime() - dateIn.getTime()) / (1000 * 60 * 60 * 24));
		int diffDaysOut = (int) ((date.getTime() - dateOut.getTime()) / (1000 * 60 * 60 * 24));

		List<Long> companyDeviceIds = new ArrayList<>();
		companyDeviceIds.addAll(ids.getListId());

		try {

			Page<IHistoricGroup> page = null;
			if (diffDaysIn >= 30 && diffDaysOut >= 30) {
				page = repository30.findByCompanyDeviceInAndLastUpdateBetweenPaginated(companyDeviceIds, dateIn,
						dateOut, new PageRequest(currentPage, lenPage));
			} else if (diffDaysIn <= 30 && diffDaysOut <= 30) {
				page = repository.findByCompanyDeviceInAndLastUpdateBetweenPaginated(companyDeviceIds, dateIn, dateOut,
						new PageRequest(currentPage, lenPage));
			} else {
				page = repositoryAll.findByCompanyDeviceInAndLastUpdateBetweenPaginated(companyDeviceIds, dateIn,
						dateOut, new PageRequest(currentPage, lenPage));
			}

			result = getResults(page);
		} catch (Exception e) {
			result.setIsError(true);
			result.setMessage(e.getMessage());
		}

		return result;
	}
}
