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
import br.com.eneeyes.main.service.AlarmService;
import br.com.eneeyes.main.service.HistoricAlarmService;
import br.com.eneeyes.main.service.historic.HistoricService;

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
	
//	@Autowired
//	private CompanyDetectorAlarmService companyDetectorAlarmAlarmService;
	
	@Autowired
	private HistoricAlarmService historicAlarmService;
	
	@Autowired
	private HistoricService historicService;
	
	@Autowired
	private AlarmService alarmService;
		
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
		
//		CompanyDetectorAlarmDto companyDetectorAlarmDto = getExistAlarm(position.getCompanyDetector().getUid(), position.getSensor().getUid());
//		CompanyDetectorAlarmDto companyDetectorAlarmDto = getExistAlarm(position.getCompanyDetector().getUid());
		AlarmDto alarmDto = getExistAlarmII(position.getCompanyDetector().getUid());
					
		Historic historic = new Historic();
		
		position.setLastValue(new BigDecimal(-1));
		historic = historicService.saveByPosition(position);
		
		AlarmType alarmType = position.getAlarmType();
		position.setHistoricId(historic.getUid());			
		updatePositionByHistoric(historic, alarmType);
			
//		if(companyDetectorAlarmDto != null) {	
//			updateAlarmsAndActions(companyDetectorAlarmDto.getAlarmDto(), alarmType, historic);
//		}
		
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
				
//		CompanyDetectorAlarmDto companyDetectorAlarmDto = getExistAlarm(historic.getCompanyDetectorId(), historic.getSensorId());
//		CompanyDetectorAlarmDto companyDetectorAlarmDto = getExistAlarm(historic.getCompanyDetectorId());
		
		AlarmDto alarmDto = getExistAlarmII(historic.getCompanyDetectorId());
		
//		AlarmType alarmType = checkExistsAlarms(companyDetectorAlarmDto, historic.getValue());
		AlarmType alarmType = checkExistsAlarms(alarmDto, historic.getValue());
		
		updatePositionByHistoric(historic, alarmType);
		
		if(alarmType == AlarmType.NORMAL)
			return;
		else if(alarmType == AlarmType.WITHOUT)
			updateAlarmsAndActions(alarmType, historic);
		else {
//			updateAlarmsAndActions(companyDetectorAlarmDto.getAlarmDto(), alarmType, historic);
			updateAlarmsAndActions(alarmDto, alarmType, historic);
		}
		
	}
	
	private void updatePositionByHistoric(Historic historic, AlarmType alarmType) {
		
//		PositionView positionView = positionViewRepository.findByCompanyDetectorIdAndSensorId(historic.getCompanyDetectorId(), historic.getSensorId());
		PositionView positionView = positionViewRepository.findByCompanyDetectorId(historic.getCompanyDetectorId());
		
		if (positionView != null) {
			
			positionRepository.updatePositionById(alarmType, historic.getValue(), historic.getLastUpdate(), historic.getUid(), positionView.getUid()); 
			
		}
	}
	
////	private CompanyDetectorAlarmDto getExistAlarm(Long companyDetectorId, Long sensorid) {
//	private CompanyDetectorAlarmDto getExistAlarm(Long companyDetectorId) {
//				
//		CompanyDetector companyDetector = new CompanyDetector(companyDetectorId);
////		Sensor sensor = new Sensor(sensorid);
//		
//		if(CompanyDetectorAlarmSingletonRepository.init()) {
//			CompanyDetectorAlarmSingletonRepository.populate(companyDetectorAlarmAlarmService.findAll());
//		}
//		
////		return CompanyDetectorAlarmSingletonRepository.findByCompanyDetectorAndSensor(companyDetector.getUid(), sensor.getUid());
//		return CompanyDetectorAlarmSingletonRepository.findByCompanyDetector(companyDetector.getUid());	
//		
//	}
	
	private AlarmDto getExistAlarmII(Long companyDetectorId) {
		
		CompanyDetector companyDetector = new CompanyDetector(companyDetectorId);
		
		
		if(AlarmSingletonRepository.init()) {
			AlarmSingletonRepository.populate(alarmService.findAll());
		}
		
		return AlarmSingletonRepository.findByCompanyDetector(companyDetector.getUid());
		
	}
	
	
//	private AlarmType checkExistsAlarms(CompanyDetectorAlarmDto companyDetectorAlarmDto, BigDecimal lastValue ) {						
	private AlarmType checkExistsAlarms(AlarmDto alarm, BigDecimal lastValue ) {	
		AlarmType alarmType = AlarmType.NORMAL;
				
//		if(companyDetectorAlarmDto != null) {
		if(alarm != null) {
			
//			AlarmDto alarm = companyDetectorAlarmDto.getAlarmDto();
						
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
//		Sensor sensor = new Sensor(historic.getSensorId());
					
		AlarmParams alarmParams = new AlarmParams(alarmDto, alarmType); 
		
//		historicAlarmService.save(historic.getValue(), companyDetector.getUid(), sensor.getUid(), historic.getUid(), alarmDto, alarmType, alarmParams);
		historicAlarmService.save(historic.getValue(), companyDetector.getUid(), historic.getUid(), alarmDto, alarmType, alarmParams);
		
		//if (alarmType != AlarmType.NORMAL && alarmType != AlarmType.OFF && alarmType != AlarmType.WITHOUT) {
		if (alarmType != AlarmType.OFF) {
			
//			updatePositionAlarm(historic, companyDetector, sensor, alarmType, alarmParams);
			updatePositionAlarm(historic, companyDetector, alarmType, alarmParams);
		}
		
	}
	
	private void updateAlarmsAndActions(AlarmType alarmType, Historic historic) {
		
		CompanyDetector companyDetector = new CompanyDetector(historic.getCompanyDetectorId());
//		Sensor sensor = new Sensor(historic.getSensorId());
				
//		historicAlarmService.save(historic.getValue(), companyDetector.getUid(), sensor.getUid(), historic.getUid(), alarmType);		
		historicAlarmService.save(historic.getValue(), companyDetector.getUid(), historic.getUid(), alarmType);
	}
	
	/**
	 * @param position
	 * @param companyDetector
	 * @param sensor
	 * @param alarmType
	 */	
//	private void updatePositionAlarm(Historic historic, CompanyDetector companyDetector, Sensor sensor,	AlarmType alarmType, AlarmParams alarmParams) {		
	private void updatePositionAlarm(Historic historic, CompanyDetector companyDetector, AlarmType alarmType, AlarmParams alarmParams) {		
				
		List<AlarmStatus> solvedOrCancelesAlarms = new ArrayList<AlarmStatus>();
		
		solvedOrCancelesAlarms.add(AlarmStatus.SOLVED);
		solvedOrCancelesAlarms.add(AlarmStatus.CANCELED);

//		PositionAlarm positionAlarm = positionAlarmRepository.findByCompanyDetectorAndSensorAndAlarmTypeAndAlarmStatusNotIn(companyDetector, sensor, alarmType, solvedOrCancelesAlarms);
		PositionAlarm positionAlarm = positionAlarmRepository.findByCompanyDetectorAndAlarmTypeAndAlarmStatusNotIn(companyDetector, alarmType, solvedOrCancelesAlarms);
		
		if(positionAlarm == null) {
			
			positionAlarm =  new PositionAlarm();
			positionAlarm.setCompanyDetector(companyDetector);
//			positionAlarm.setSensor(sensor);
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
