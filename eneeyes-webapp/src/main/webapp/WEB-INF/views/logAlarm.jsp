<div data-ng-controller="logAlarmController">    
	<div class="row">
		<div class="col-md-12">		
			<div class="box box-primary">						
				<div class="box-header with-border"><strong style="font-size:1.4em">Logs de Alarmes</strong>
				<a href="#" class="text-muted pull-right" data-ng-click="getCompaniesAlarm();"><i title="Refresh" class="fa fa-refresh"></i></a>
				</div>
				<div class="box-body">
					<div class="col-md-12">					
						<div id="tableExample">
						    <div>						        
						        <table id='idDatatable' data-datatable data-options="dataTableOptions" data-items="dashCompaniesAlarm"></table>						      						        
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
						<div class="box box-primary" style="padding-bottom: 8px !important; margin-bottom: 8px !important;">							
							<div class="box-header">
								<h3 class="box-title">Dados do Dispositivo e Alarme</h3>							
								<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>								
							</div>										
							<div class="box-body" style="padding-bottom: 0px !important">
								<form class="form" name="userForm">								
									<div class="row">											
										<div class="col-md-12">
										
											<div class="box-body" style="background-color: black; color:white">											                												                	 
												<dl class="dl-horizontal">
													<dt>Empresa:</dt>
														<dd>{{selectedPositionAlarm.company_name}}</dd>
													<dt>Unidade / Área:</dt>
														<dd>{{selectedPositionAlarm.unit_name}} / {{selectedPositionAlarm.area_name}}</dd>													
													<dt>Detector / Sensor</dt>
														<dd>{{selectedPositionAlarm.company_detector_name}} / {{selectedPositionAlarm.sensor_name}}</dd>
																																							
													<dt data-ng-if="!isOffline">Alarme: </dt>														
														<dd data-ng-if="!isOffline" style="margin-top: 7px; margin-bottom: 7px">
															<span class="label"  
																data-ng-class="{'label-success' : selectedPositionAlarm.alarmType=='NORMAL', 'label-warning' : selectedPositionAlarm.alarmType=='ALERTA', 'label-default' : selectedPositionAlarm.alarmType=='DETECCAO', 'label-danger' : selectedPositionAlarm.alarmType=='EVACUACAO'}"> {{selectedPositionAlarm.alarmType}}
															</span>
														</dd>													
													<dt data-ng-if="!isOffline">Data/Hora Evento: </dt>
														<dd data-ng-if="!isOffline">
															{{selectedPositionAlarm.first_update  | date:'yyyy-MM-dd HH:mm:ss'}}  
														</dd>
													<dt>Data/Hora Última Medição: </dt>
														<dd>
															{{selectedPositionAlarm.last_update  | date:'yyyy-MM-dd HH:mm:ss'}}  
														</dd>
														
													<dt>Artefato/Medi&ccedil;&atilde;o: </dt>															
														<dd>
															<span data-ng-if="selectedPositionAlarm.deviceType !='DETECTOR'">{{selectedPositionAlarm.deviceDescription}}</span>
															<span data-ng-if="selectedPositionAlarm.deviceType =='DETECTOR'">Gás: {{selectedPositionAlarm.gasName}}</span>															
															<span data-ng-if="selectedPositionAlarm.last_value>0">
																| Medi&ccedil;&atilde;o: {{selectedPositionAlarm.last_value}}
																<span style="vertical-align:super;font-size:0.5em"> {{selectedPositionAlarm.unitMeterSymbol}}</span>																
															</span>
														</dd>																										
													<dt>Alertas Emitidos: </dt>
														<dd>Sonoro: 
															<span data-ng-if="selectedPositionAlarm.soundStatus=='OFF'" class="icon fa fa-bullhorn" style="color: gray" title="Alerta Sonoro não Habilitado"></span>
															<span data-ng-if="selectedPositionAlarm.soundStatus=='ON'" class="icon fa fa-bullhorn" style="color: red" title="Alerta Sonoro"></span>
															<span data-ng-if="selectedPositionAlarm.soundStatus=='SILENT'" class="icon fa fa-bullhorn" style="color: green" title="Alerta Silenciado"></span>
														</dd>	
														<dd>
															Email:
															<span data-ng-if="selectedPositionAlarm.emailStatus=='OFF'" class="icon fa fa-envelope" style="color: gray" title="Aviso EMAIL não Habilitado"></span>
															<span data-ng-if="selectedPositionAlarm.emailStatus=='PENDENT'" class="icon fa fa-envelope" style="color: orange" title="EMAIL na fila para Envio"></span>	
															<span data-ng-if="selectedPositionAlarm.emailStatus=='ERR_TRY_ONE'" class="icon fa fa-envelope" style="color: red" title="Falha na 1a. Tentativa"></span>
															<span data-ng-if="selectedPositionAlarm.emailStatus=='ERR_TRY'" class="icon fa fa-envelope" style="color: red" title="Falha ao Enviar Email"></span>
															<span data-ng-if="selectedPositionAlarm.emailStatus=='SENDED'" class="icon fa fa-envelope" style="color: green" title="Email Enviado"></span>
														</dd>
														<dd>
															SMS:
															<span data-ng-if="selectedPositionAlarm.smsStatus=='OFF'"  style="color: gray" title="Aviso de SMS não Habilitado"> SMS</span>
															<span data-ng-if="selectedPositionAlarm.smsStatus=='PENDENT'"  style="color: orange" title="SMS na Fila para Envio"> SMS</span>	
															<span data-ng-if="selectedPositionAlarm.smsStatus=='ERR_TRY_ONE'"  style="color: red" title="Falha na 1a. Tentativa"> SMS</span>
															<span data-ng-if="selectedPositionAlarm.smsStatus=='ERR_TRY'"  style="color: red" title="Falha ao Enviar SMS"> SMS</span>
															<span data-ng-if="selectedPositionAlarm.smsStatus=='SENDED'"  style="color: green" title="SMS Enviado"> SMS</span>
															<span data-ng-if="selectedPositionAlarm.smsStatus=='READED'"  style="color: green" title="SMS Recebido pelo Destinatário"> SMS</span>	                
														</dd>
														<dd>
															Integração Sigma:
															<span data-ng-if="selectedPositionAlarm.sigmaStatus=='OFF'" class="icon fa fa-exchange" style="font-size:1.4em; color: gray" title="Sem Integração ao Sigma Habilitada"></span>
			                                            	<span data-ng-if="selectedPositionAlarm.sigmaStatus=='ERROR'" class="icon fa fa-exchange" style="font-size:1.4em; color: red" title="Falha ao Informar Sigma"></span>
			                                            	<span data-ng-if="selectedPositionAlarm.sigmaStatus=='SENDED'" class="icon fa fa-exchange" style="font-size:1.4em; color: green" title="Integração ao Sigma Realizada"></span>
			                                            	<span data-ng-if="selectedPositionAlarm.sigmaStatus=='ON'" class="icon fa fa-exchange" style="font-size:1.4em; color: orange" title="Integração ao Sigma em fila"></span>
			                                            </dd>														
			                                            <dt data-ng-if="selectedPositionAlarm.action">Providências Imediatas:</dt>
														<dd>
															<span data-ng-if="selectedPositionAlarm.action" style="color: gray" title="Aviso de SMS não Habilitado"> {{selectedPositionAlarm.action}}</span>
														</dd>	
													<dt>Status do Alarme: </dt>
														<dd>
															<span data-ng-if="selectedPositionAlarm.alarmStatus=='AUTO'" class="icon fa fa-bell" style="color: green" title="Alarme encerrado automaticamente"> AUTO (Encerrado após normalização)</span>
															<span data-ng-if="selectedPositionAlarm.alarmStatus=='CREATED'" class="icon fa fa-bell-slash" style="color: red" title="Alarme não Tratado" >REPORTADO (Ainda não tratado)</span>
															<span data-ng-if="selectedPositionAlarm.alarmStatus=='READED'" class="icon fa fa-exclamation" style="color: ORANGE" title="Alarme em Análise" >CHECADO (Em análise)</span>
															<span data-ng-if="selectedPositionAlarm.alarmStatus=='SOLVED'" class="icon fa fa-bell" style="color: BLUE" title="Alarme Resolvido"> RESOLVIDO (Encerrado pelo Monitoramento)</span>
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