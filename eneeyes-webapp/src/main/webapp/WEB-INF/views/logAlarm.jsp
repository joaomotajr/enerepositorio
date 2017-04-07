<div data-ng-controller="logAlarmController">    
	<div class="row">
		<div class="col-md-12">
		
			<div class="box box-primary">						
				<div class="box-header with-border"><strong style="font-size:1.4em">Logs de Alarmes</strong></div>
				
				<div class="box-body">
					<div class="col-md-12">
					
						<div id="tableExample">
						    <div>						        
						        <table id='idDatatable' data-datatable data-options="dataTableOptions" data-items="listAllDashCompaniesAlarm.list"></table>
						      						        
						    </div>
						</div>										
						
					</div>		
				</div>		
				
			</div>	
	
		</div>
	</div>	
	
	<div id="modalShowMessages" class="modal">                
		<div class="modal-dialog" role="document">
			<div class="modal-content">                            
				
					<div class="modal-content">                            
					<div class="modal-body"style="padding-bottom: 0px; !important">
					
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center;font-size:1.5em">
								<strong>Historico do Alarme</strong>														
							</div>														                                                                           
						</div>
											
						<div class="box box-primary" style="padding-bottom: 8px; !important; margin-bottom: 8px !important;">
							
							<div class="box-header">
								<h3 class="box-title">Dados do Dispositivo e Alarme</h3>							
								<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
							</div>		
										
							<div class="box-body" style="padding-bottom: 0px; !important">
								<form class="form" name="userForm">		
								
									<div class="row">											
										<div class="col-md-12">
										
											<div class="box-body" style="background-color: #e7e7e7">											                												                	 
												<dl class="dl-horizontal">
													<dt>Empresa:</dt>
														<dd>{{selectedPositionAlarm.company_name}}</dd>
													<dt>Unidade / �rea:</dt>
														<dd>{{selectedPositionAlarm.unit_name}} / {{selectedPositionAlarm.area_name}}</dd>													
													<dt>Detector / Sensor</dt>
														<dd>{{selectedPositionAlarm.company_detector_name}} / {{selectedPositionAlarm.sensor_name}}</dd>
																																							
													<dt data-ng-if="!isOffline">Alarme: </dt>														
														<dd data-ng-if="!isOffline" style="margin-top: 7px; margin-bottom: 7px">
															<span class="label"  
																data-ng-class="{'label-success' : selectedPositionAlarm.alarmType=='NORMAL', 'label-warning' : selectedPositionAlarm.alarmType=='ALERTA', 'label-default' : selectedPositionAlarm.alarmType=='DETECCAO', 'label-danger' : selectedPositionAlarm.alarmType=='EVACUACAO'}">�{{selectedPositionAlarm.alarmType}}
															</span>
														</dd>													
													<dt data-ng-if="!isOffline">Data/Hora Evento: </dt>
														<dd data-ng-if="!isOffline">
															{{selectedPositionAlarm.first_update  | date:'yyyy-MM-dd HH:mm:ss'}}  
														</dd>
													<dt>Data/Hora Ultima Medi��o: </dt>
														<dd>
															{{selectedPositionAlarm.last_update  | date:'yyyy-MM-dd HH:mm:ss'}}  
														</dd>														
													<dt>Medi��es: </dt>															
														<dd>
															G�s: {{selectedPositionAlarm.gas_name}} | Medi��o: {{selectedPositionAlarm.last_value}}
															<span style="vertical-align:super;font-size:0.5em" data-ng-if="selectedPositionAlarm.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
															<span style="vertical-align:super;font-size:0.5em" data-ng-if="selectedPositionAlarm.unitMeterGases!='LEL_PERCENT'"> {{selectedPositionAlarm.unitMeterGases}}</span>
														</dd>
													<dt>Alertas Emitidos: </dt>
														<dd>Sonoro: 
															<span data-ng-if="selectedPositionAlarm.soundStatus=='OFF'" class="icon fa fa-bullhorn" style="color: gray" title="Alerta Sonoro n�o Habilitado"></span>
															<span data-ng-if="selectedPositionAlarm.soundStatus=='ON'" class="icon fa fa-bullhorn" style="color: red" title="Alerta Sonoro"></span>
															<span data-ng-if="selectedPositionAlarm.soundStatus=='SILENT'" class="icon fa fa-bullhorn" style="color: green" title="Alerta Silenciado"></span>
														</dd>	
														<dd>
															Email:
															<span data-ng-if="selectedPositionAlarm.emailStatus=='OFF'" class="icon fa fa-envelope" style="color: gray" title="Aviso EMAIL n�o Habilitado"></span>
															<span data-ng-if="selectedPositionAlarm.emailStatus=='PENDENT'" class="icon fa fa-envelope" style="color: orange" title="EMAIL na fila para Envio"></span>	
															<span data-ng-if="selectedPositionAlarm.emailStatus=='ERR_TRY_ONE'" class="icon fa fa-envelope" style="color: red" title="Falha na 1a. Tentativa"></span>
															<span data-ng-if="selectedPositionAlarm.emailStatus=='ERR_TRY'" class="icon fa fa-envelope" style="color: red" title="Falha ao Enviar Email"></span>
															<span data-ng-if="selectedPositionAlarm.emailStatus=='SENDED'" class="icon fa fa-envelope" style="color: green" title="Email Enviado"></span>
														</dd>
														<dd>
															SMS:
															<span data-ng-if="selectedPositionAlarm.smsStatus=='OFF'"  style="color: gray" title="Aviso de SMS n�o Habilitado"> SMS</span>
															<span data-ng-if="selectedPositionAlarm.smsStatus=='PENDENT'"  style="color: orange" title="SMS na Fila para Envio"> SMS</span>	
															<span data-ng-if="selectedPositionAlarm.smsStatus=='ERR_TRY_ONE'"  style="color: red" title="Falha na 1a. Tentativa"> SMS</span>
															<span data-ng-if="selectedPositionAlarm.smsStatus=='ERR_TRY'"  style="color: red" title="Falha ao Enviar SMS"> SMS</span>
															<span data-ng-if="selectedPositionAlarm.smsStatus=='SENDED'"  style="color: green" title="SMS Enviado"> SMS</span>
															<span data-ng-if="selectedPositionAlarm.smsStatus=='READED'"  style="color: green" title="SMS Recebido pelo Destinat�rio"> SMS</span>	                
														</dd>
														<dd>
															<span data-ng-if="selectedPositionAlarm.action"> Provd�ncias Imediatas:</span>
															<span data-ng-if="selectedPositionAlarm.action" style="color: gray" title="Aviso de SMS n�o Habilitado"> {{selectedPositionAlarm.action}}</span>
														</dd>	
																							
							                  	</dl>								                	
								            </div>
								            
											<hr style="margin-top: 8px !important; margin-bottom: 8px !important;">
																					
																						
											<div class="box box-info box-solid" data-ng-if="selectedPositionAlarmMessages.length > 0">				                    
												<div class="box-header with-border"><i class="fa fa-envelope"></i> Mensagens:
												</div>
												<div class="box-body">
													<div style="max-height: 200px; height:auto; overflow: auto">
														<dl class="dl-horizontal" data-ng-repeat="item in selectedPositionAlarmMessages">																													
															<dt>Data/Hora: {{item.lastUpdate | date:'yyyy-MM-dd HH:mm:ss'}}</dt>
																<dd>{{item.message}}</dd>														 
														</dl>
													</div>                                                                    
												</div>
											</div>											
											
										</div>																		
													  
									</div>    
																										
								</form>
							</div>
						</div>
										
					</div>
					
					<div class="modal-footer">
						<button type="button" class="btn btn-info" disabled><i class="fa fa-print"></i></button>						
						<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-sign-out"></i></button>									                                
					</div>
					
				</div>				
				
			</div>
		</div>		
	</div>
	
</div>