package br.com.eneeyes.main.service.buss;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.CompanyDetector;
import br.com.eneeyes.main.model.Position;
import br.com.eneeyes.main.model.PositionAlarm;
import br.com.eneeyes.main.model.enums.AlarmStatus;
import br.com.eneeyes.main.model.enums.AlarmType;
import br.com.eneeyes.main.model.historic.Historic;
import br.com.eneeyes.main.model.views.PositionView;
import br.com.eneeyes.main.repository.PositionAlarmRepository;
import br.com.eneeyes.main.repository.PositionRepository;
import br.com.eneeyes.main.repository.singleton.AlarmSingletonRepository;
import br.com.eneeyes.main.repository.views.PositionViewRepository;
import br.com.eneeyes.main.service.HistoricAlarmService;
import br.com.eneeyes.main.service.historic.HistoricService;
import br.com.eneeyes.main.service.views.CompanyDetectorAlarmViewService;

/**
 * @author f752766
 *
 */
@Service
public class ProcessAlarmService {
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private PositionViewRepository positionViewRepository;
	
	@Autowired
	private HistoricAlarmService historicAlarmService;
	
	@Autowired
	private HistoricService historicService;
	
	@Autowired
	private CompanyDetectorAlarmViewService companyDetectorAlarmViewService;
		
	@Autowired
	private PositionAlarmRepository positionAlarmRepository;
	
	private Historic historic;
	
	public final Historic getHistoric() {
		return historic;
	}

	public final void setHistoric(Historic historic) {
		this.historic = historic;
	}
	
	
	/**
	 * @param position
	 * Executa Procedimentos para Detectores Offline, ou seja, 
	 * sem comunicação a mais do que o tempo parametrizado.
	 */
	public void ExecuteOfflineProcedures(Position position) {
		
		AlarmDto alarmDto = getExistAlarm(position.getCompanyDetector().getUid());					
		
		Historic historic = new Historic();		
		position.setLastValue(new BigDecimal(-1));
		
		historic = historicService.saveByPosition(position);		
		
		AlarmType alarmType = position.getAlarmType();
		position.setHistoricId(historic.getUid());			
		
		updatePositionByHistoric(historic, alarmType);			
		
		if(alarmDto != null) {	
			updateAlarmsAndActions(alarmDto, alarmType, historic);
		}
	}
	
	/**
	 * @param historic
	 * Executa Procedimentos de checagem de alarmes para o detector em questão
	 * Grava histórico do alarme vigente em qq circunstância
	 */
	public void Execute(Historic historic) {
		
		AlarmDto alarmDto = getExistAlarm(historic.getCompanyDetectorId());

		AlarmType alarmType = checkExistsAlarms(alarmDto, historic.getValue());
		
		updatePositionByHistoric(historic, alarmType);
		
		if(alarmType == AlarmType.NORMAL)
			return;
		else if(alarmType == AlarmType.WITHOUT)
			updateAlarmsAndActions(alarmType, historic);
		else {
			updateAlarmsAndActions(alarmDto, alarmType, historic);
		}
		
	}
	
	private void updatePositionByHistoric(Historic historic, AlarmType alarmType) {

		PositionView positionView = positionViewRepository.findByCompanyDetectorId(historic.getCompanyDetectorId());
		
		if (positionView != null) {
			
			positionRepository.updatePositionById(alarmType, historic.getValue(), historic.getLastUpdate(), historic.getUid(), positionView.getUid()); 
			
		}
	}
	
	private AlarmDto getExistAlarm(Long companyDetectorId) {
		
		CompanyDetector companyDetector = new CompanyDetector(companyDetectorId);
		
		if(AlarmSingletonRepository.init()) {			
			AlarmSingletonRepository.populate(companyDetectorAlarmViewService.findAll());
		}
		
		return AlarmSingletonRepository.findByCompanyDetector(companyDetector.getUid());
		
	}
					
	private AlarmType checkExistsAlarms(AlarmDto alarm, BigDecimal lastValue ) {	
		AlarmType alarmType = AlarmType.NORMAL;

		if(alarm != null) {
						
			if( !alarm.getAlarmOn()) {
				alarmType = AlarmType.OFF;
			}				
			else if( lastValue.compareTo( new BigDecimal(alarm.getAlarm3())) > 0 ) {				
				alarmType = AlarmType.EVACUACAO;							
			}
			else if( lastValue.compareTo( new BigDecimal(alarm.getAlarm2())) > 0 ) {				
				alarmType = AlarmType.ALERTA;
			}
			else if( lastValue.compareTo( new BigDecimal(alarm.getAlarm1())) > 0 ) {				
				alarmType = AlarmType.DETECCAO;
			}		
		}
		else {
			alarmType = AlarmType.WITHOUT;
		}
		
		return alarmType;		
	}
	
	private void updateAlarmsAndActions(AlarmDto alarmDto, AlarmType alarmType, Historic historic) {		
		
		CompanyDetector companyDetector = new CompanyDetector(historic.getCompanyDetectorId());					
		AlarmParams alarmParams = new AlarmParams(alarmDto, alarmType); 
		
		historicAlarmService.save(historic.getValue(), companyDetector.getUid(), historic.getUid(), alarmDto, alarmType, alarmParams);
		
		if (alarmType != AlarmType.OFF) {
			updatePositionAlarm(historic, companyDetector, alarmType, alarmParams);
		}
		
	}
	
	private void updateAlarmsAndActions(AlarmType alarmType, Historic historic) {
		
		CompanyDetector companyDetector = new CompanyDetector(historic.getCompanyDetectorId());
		historicAlarmService.save(historic.getValue(), companyDetector.getUid(), historic.getUid(), alarmType);
	}
	
	/**
	 * @param position
	 * @param companyDetector
	 * @param sensor
	 * @param alarmType
	 */	
		
	private void updatePositionAlarm(Historic historic, CompanyDetector companyDetector, AlarmType alarmType, AlarmParams alarmParams) {		
				
		List<AlarmStatus> solvedOrCancelesAlarms = new ArrayList<AlarmStatus>();
		
		solvedOrCancelesAlarms.add(AlarmStatus.SOLVED);
		solvedOrCancelesAlarms.add(AlarmStatus.CANCELED);

		PositionAlarm positionAlarm = positionAlarmRepository.findByCompanyDetectorAndAlarmTypeAndAlarmStatusNotIn(companyDetector, alarmType, solvedOrCancelesAlarms);
		
		if(positionAlarm == null) {
			
			positionAlarm =  new PositionAlarm();
			positionAlarm.setCompanyDetector(companyDetector);
			positionAlarm.setFirstUpdate(new Date());
			positionAlarm.setLastValue(historic.getValue());
			positionAlarm.setAlarmStatus(AlarmStatus.CREATED);
			positionAlarm.setLastUpdate(new Date());
			positionAlarm.setAlarmType(alarmType);
			positionAlarm.setEmailStatus(alarmParams.getEmailStatus());
			positionAlarm.setSmsStatus(alarmParams.getSmsStatus());
			positionAlarm.setAction(alarmParams.getAction());
			positionAlarm.setSoundStatus(alarmParams.getSoundStatus());
			positionAlarm.setSigmaStatus(alarmParams.getSigmaStatus());
		}
		else {
			// TODO Verificar se haverá reenvio de Actions em casos de Reinscidência do mesmo alerta				
			positionAlarm.setLastUpdate(new Date());
			positionAlarm.setLastValue(historic.getValue());				
		}
		
		positionAlarmRepository.save(positionAlarm);			
	}
	
	
}
