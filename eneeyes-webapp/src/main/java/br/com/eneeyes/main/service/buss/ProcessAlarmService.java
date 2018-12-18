package br.com.eneeyes.main.service.buss;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eneeyes.main.dto.AlarmDto;
import br.com.eneeyes.main.model.CompanyDevice;
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
import br.com.eneeyes.main.service.views.CompanyDeviceAlarmViewService;

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
	private CompanyDeviceAlarmViewService companyDeviceAlarmViewService;
		
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
		
		AlarmDto alarmDto = getExistAlarm(position.getCompanyDevice().getUid());					
		
		Historic historic = new Historic();		
		position.setLastValue(new BigDecimal(-1));
		
		historic = historicService.saveByPosition(position);		
		
		AlarmType alarmType = position.getAlarmType();
		position.setHistoricId(historic.getUid());			
		
		updatePositionByHistoric(historic, alarmType);			
				
		if(alarmDto != null) {
			//Alarm de OffLine com feedback desabilitado
			if(alarmType == AlarmType.OFFLINE && !alarmDto.getAlarmOffLineOn())
				updateAlarmsAndActions(alarmType, historic);
			else
				updateAlarmsAndActions(alarmDto, alarmType, historic);
		}
	}
	
	/**
	 * @param historic
	 * Executa Procedimentos de checagem de alarmes para o detector em questão
	 * Grava histórico do alarme vigente em qq circunstância
	 */
	public void Execute(Historic historic) {
		
		AlarmDto alarmDto = getExistAlarm(historic.getCompanyDeviceId());

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

		PositionView positionView = positionViewRepository.findByCompanyDeviceId(historic.getCompanyDeviceId());
		
		if (positionView != null) {
			
			positionRepository.updatePositionById(alarmType, historic.getValue(), historic.getLastUpdate(), historic.getUid(), positionView.getUid()); 
			
		}
	}
	
	private AlarmDto getExistAlarm(Long companyDeviceId) {
		
		CompanyDevice companyDevice = new CompanyDevice(companyDeviceId);
		
		if(AlarmSingletonRepository.init()) {			
			AlarmSingletonRepository.populate(companyDeviceAlarmViewService.findAll());
		}
		
		return AlarmSingletonRepository.findByCompanyDevice(companyDevice.getUid());
		
	}
					
	private AlarmType checkExistsAlarms(AlarmDto alarm, BigDecimal lastValue ) {	
		AlarmType alarmType = AlarmType.NORMAL;

		if(alarm != null) {
						
			if( !alarm.getAlarmOn()) {
				alarmType = AlarmType.OFF;
			}				
			else if(alarm.getAlarm3On() && lastValue.compareTo( new BigDecimal(alarm.getAlarm3())) > 0 ) {				
				alarmType = AlarmType.EVACUACAO;							
			}
			else if(alarm.getAlarm2On() && lastValue.compareTo( new BigDecimal(alarm.getAlarm2())) > 0) {
				alarmType = AlarmType.ALERTA;
			}
			else if( lastValue.compareTo( new BigDecimal(alarm.getAlarm1())) > 0 || 
					(alarm.getAlarm11() != null && alarm.getAlarm11() != 0 && lastValue.compareTo( new BigDecimal(alarm.getAlarm11())) < 0 )
				) {				
				alarmType = AlarmType.DETECCAO;
			}		
		}
		else {
			alarmType = AlarmType.WITHOUT;
		}
		
		return alarmType;		
	}
	
	private void updateAlarmsAndActions(AlarmDto alarmDto, AlarmType alarmType, Historic historic) {		
		
		CompanyDevice companyDevice = new CompanyDevice(historic.getCompanyDeviceId());					
		AlarmParams alarmParams = new AlarmParams(alarmDto, alarmType); 
		
		historicAlarmService.save(historic.getValue(), companyDevice.getUid(), historic.getUid(), alarmDto, alarmType, alarmParams);
		
		if (alarmType != AlarmType.OFF) {
			updatePositionAlarm(historic, companyDevice, alarmType, alarmParams);
		}
		
	}
	
	private void updateAlarmsAndActions(AlarmType alarmType, Historic historic) {
		
		CompanyDevice companyDevice = new CompanyDevice(historic.getCompanyDeviceId());
		historicAlarmService.save(historic.getValue(), companyDevice.getUid(), historic.getUid(), alarmType);
	}
	
	/**
	 * @param position
	 * @param companyDevice
	 * @param sensor
	 * @param alarmType
	 */	
		
	private void updatePositionAlarm(Historic historic, CompanyDevice companyDevice, AlarmType alarmType, AlarmParams alarmParams) {		
				
		List<AlarmStatus> solvedOrCancelesAlarms = new ArrayList<AlarmStatus>();
		
		solvedOrCancelesAlarms.add(AlarmStatus.SOLVED);
		solvedOrCancelesAlarms.add(AlarmStatus.CANCELED);

		PositionAlarm positionAlarm = positionAlarmRepository.findByCompanyDeviceAndAlarmTypeAndAlarmStatusNotIn(companyDevice, alarmType, solvedOrCancelesAlarms);
		
		if(positionAlarm == null) {
			
			positionAlarm =  new PositionAlarm();
			positionAlarm.setCompanyDevice(companyDevice);
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
