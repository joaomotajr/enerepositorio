<!-- 	<audio id="alarmSound" src="/assets/img/alert_01.mp3" preload="auto"></audio> -->
		
	<div data-ng-controller="monitorController">
		<div class="row">
       		<div class="col-md-6" style="font-size: 8px !important;">

				<div class="box box-danger">
					<div class="box-header with-border">
						<h5 class="box-title">Alarmes Pendentes</h5>
						<div class="box-tools pull-right">						
							
							<label data-ng-show='loading'>Loading ...</label>		
							<button class="btn btn-box-tool" data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
							
						</div>
					</div>									

					<div class="box-body" style="font-size: 8px !important;">
						<div class="table-responsive table-condensed">
							<div style="max-height: 500px; overflow: auto">
								<table class="table no-margin" style="font-size: 12px !important;">
									<thead>
										<tr>
											<th>Empresa</th>
											<th>Detector</th>
											<th>Alarme</th>
											<th>Data/hora</th>
											<th>Notificações</th>											
										</tr>
									</thead>
									<tbody>

										<tr data-ng-repeat="item in dashCompaniesAlarmCreated">
											
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>
											
											<td style="padding-top: 13px ! important;"> <span class="label" data-ng-class="{'label-success' : item.alarmType=='NORMAL', 'label-warning' : item.alarmType=='ALERTA', 'label-default' : item.alarmType=='DETECCAO', 'label-danger' : item.alarmType=='EVACUACAO'}"> {{item.alarmType}} </span></td>	
											<td>
												{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}	
											</td>
											<td>
												<span data-ng-if="item.soundStatus=='OFF'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: gray" title="Alerta Sonoro não Habilitado"></span>
                                            	<a href="#" data-ng-click="updateSoundStatus($index);" data-ng-if="item.soundStatus=='ON'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: red" title="Alerta Sonoro"></a>
                                            	<span data-ng-if="item.soundStatus=='SILENT'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: green" title="Alerta Silenciado"></span>
                                            	&nbsp;&nbsp;
                                            	<span data-ng-if="item.emailStatus=='OFF'" class="icon fa fa-envelope" style="font-size:1.4em; color: gray" title="Aviso EMAIL não Habilitado"></span>
                                            	<span data-ng-if="item.emailStatus=='PENDENT'" class="icon fa fa-envelope" style="font-size:1.4em; color: orange" title="EMAIL na fila para Envio"></span>	
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY_ONE'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa"></span>
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha ao Enviar Email"></span>
                                            	<span data-ng-if="item.emailStatus=='SENDED'" class="icon fa fa-envelope" style="font-size:1.4em; color: green" title="Email Enviado"></span>
                                            	&nbsp;&nbsp;                                            	
                                            	<span data-ng-if="item.smsStatus=='OFF'"  style="font-size:1.4em; color: gray" title="Aviso de SMS não Habilitado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='PENDENT'"  style="font-size:1.4em; color: orange" title="SMS na Fila para Envio">SMS</span>	
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY_ONE'"  style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY'"  style="font-size:1.4em; color: red" title="Falha ao Enviar SMS">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='SENDED'"  style="font-size:1.4em; color: green" title="SMS Enviado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='READED'"  style="font-size:1.4em; color: green" title="SMS Recebido pelo Destinatário">SMS</span>	                
                                           		&nbsp;&nbsp; 
                                           		<span data-ng-click="editActionCreated($index)" class="button fa fa-info-circle" style="font-size:1.6em;" title="Ações a serem Verificadas pelo Operador"></span>
                                            </td>                                        						
										</tr>										
									</tbody>
								</table>
							</div>
						</div>
					</div>							

				</div>
      		
       		</div>
    	
    		<div class="col-md-6" style="font-size: 8px !important;">

				<div class="box box-warning">
					<div class="box-header with-border">
						<h5 class="box-title">Alarmes Em Análise</h5>
						<div class="box-tools pull-right">
						
							<label data-ng-show='loading'>Loading ...</label>		
		
							<button class="btn btn-box-tool" data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>

						</div>
					</div>					
					
					<div class="box-body" style="font-size: 8px !important;">
						<div class="table-responsive table-condensed">
							<div style="max-height: 500px; overflow: auto">
								<table class="table no-margin" style="font-size: 12px !important;">
									<thead>
										<tr>		
											<th>Empresa</th>
											<th>Detector</th>
											<th>Alarme</th>
											<th>Data/hora</th>
											<th>Notificações</th>
										</tr>
									</thead>
									<tbody>
										
										<tr data-ng-repeat="item in dashCompaniesAlarmReaded">																
												
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>
												<td style="padding-top: 13px ! important;"> <span class="label" data-ng-class="{'label-success' : item.alarmType=='NORMAL', 'label-warning' : item.alarmType=='ALERTA', 'label-default' : item.alarmType=='DETECCAO', 'label-danger' : item.alarmType=='EVACUACAO'}"> {{item.alarmType}} </span></td>
											<td>
												{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}	
											</td>
											<td>
                                            	<span data-ng-if="item.emailStatus=='OFF'" class="icon fa fa-envelope" style="font-size:1.4em; color: gray" title="Aviso EMAIL não Habilitado"></span>
                                            	<span data-ng-if="item.emailStatus=='PENDENT'" class="icon fa fa-envelope" style="font-size:1.4em; color: orange" title="EMAIL na fila para Envio"></span>	
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY_ONE'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa"></span>
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha ao Enviar Email"></span>
                                            	<span data-ng-if="item.emailStatus=='SENDED'" class="icon fa fa-envelope" style="font-size:1.4em; color: green" title="Email Enviado"></span>
                                            	&nbsp;&nbsp;                                            	
                                            	<span data-ng-if="item.smsStatus=='OFF'"  style="font-size:1.4em; color: gray" title="Aviso de SMS não Habilitado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='PENDENT'"  style="font-size:1.4em; color: orange" title="SMS na Fila para Envio">SMS</span>	
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY_ONE'"  style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY'"  style="font-size:1.4em; color: red" title="Falha ao Enviar SMS">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='SENDED'"  style="font-size:1.4em; color: green" title="SMS Enviado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='READED'"  style="font-size:1.4em; color: green" title="SMS Recebido pelo Destinatário">SMS</span>	                
                                           		&nbsp;&nbsp; 
                                           		<span data-ng-click="editActionReaded($index)" class="button fa fa-info-circle" style="font-size:1.6em;" title="Ações a serem Verificadas pelo Operador"></span>
                                            </td>        											
										</tr>   																							
										
									</tbody>
								</table>
							</div>
						</div>						
					</div>							

				</div>
      		
       		</div>       		       		
       		
    	</div>    
    	
    	<div class="row">
    		<div class="col-md-6" style="font-size: 8px !important;">

				<div class="box box-info">
					<div class="box-header with-border">
						<h5 class="box-title">Detectores Off Line a mais de 5 minutos</h5>
						<div class="box-tools pull-right">
						
							<label data-ng-show='loading'>Loading ...</label>		
		
							<button class="btn btn-box-tool" data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>

						</div>
					</div>					
					
					<div class="box-body" style="font-size: 8px !important;">
						<div class="table-responsive table-condensed">
							<div style="max-height: 500px; overflow: auto">
								<table class="table no-margin" style="font-size: 12px !important;">
									<thead>
										<tr>		
											<th>Empresa</th>
											<th>Detector</th>
											<th>Alarme</th>
											<th>Data/hora</th>											
										</tr>
									</thead>
									<tbody>
										
										<tr data-ng-repeat="item in dashCompaniesOffline">																
												
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>											
																																	
											<td style="padding-top: 13px ! important;"> <span class="label label-default offLine"> Off Line </span></td>

											<td>
												{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}	
											</td>
											<td>
												<span data-ng-click="editActionOffline($index)" class="button fa fa-info-circle" style="font-size:1.6em;" title="Informações sobre o Dispositivo"></span>
											</td>											        											
										</tr>   																							
										
									</tbody>
								</table>
							</div>
						</div>						
					</div>							

				</div>
      		
       		</div>  
    	</div>
    	
		<div id="modalAction" class="modal">                
			<div class="modal-dialog" role="document">
				<div class="modal-content">                            
					<div class="modal-body"style="padding-bottom: 0px; !important">
					
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center;font-size:1.5em">
								<strong data-ng-if="!isOffline">Posicionamento de Alarmes</strong>
								<strong data-ng-if="isOffline">Posicionamento de Dispositivo Offline </strong>								
							</div>														                                                                           
						</div>
											
						<div class="box box-primary" style="padding-bottom: 8px; !important; margin-bottom: 8px !important;">
							<div class="box-header">
								<h3 data-ng-if="!isOffline" class="box-title">Dados do Dispositivo e Alarme</h3>
								<h3 data-ng-if="isOffline" class="box-title">Dados do Dispositivo</h3>								
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
													<dt>Data/Hora Ultima Medição: </dt>
														<dd>
															{{selectedPositionAlarm.last_update_full  | date:'yyyy-MM-dd HH:mm:ss'}}  
														</dd>														
													<dt>Medições: </dt>															
														<dd>
															Gás: {{selectedPositionAlarm.gas_name}} | Medição: {{selectedPositionAlarm.last_value}}
															<span style="vertical-align:super;font-size:0.5em" data-ng-if="selectedPositionAlarm.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
															<span style="vertical-align:super;font-size:0.5em" data-ng-if="selectedPositionAlarm.unitMeterGases!='LEL_PERCENT'"> {{selectedPositionAlarm.unitMeterGases}}</span>
														</dd>
													
							                  	</dl>											                	
								                	
								            </div>
											<hr style="margin-top: 8px !important; margin-bottom: 8px !important;">
											<div class="box box-primary box-solid" data-ng-if="!isOffline" style="padding-bottom: 8px; !important; margin-bottom: 8px !important;">				                    
												<div class="box-header with-border"><i class="fa fa-user"></i> Providências:
												</div>
												<div class="box-body">													                                                                        
													<input class="form-control inputProfile" data-ng-model="selectedPositionAlarm.action" disabled>                                                                       
												</div>
											</div>											
																						
											<div class="box box-info box-solid" data-ng-if="selectedPositionAlarm.messages.length >0" style="padding-bottom: 8px; !important; margin-bottom: 8px !important;">				                    
												<div class="box-header with-border"><i class="fa fa-envelope"></i> Histórico de Mensagens:
												</div>
												<div class="box-body" style="font-size: 0.8em">
													<div style="max-height: 100px; height:auto; overflow: auto">
													<dl class="dl-horizontal" data-ng-repeat="item in selectedPositionAlarm.messages">
																													
														<dt>Data/Hora: {{item.lastUpdate | date:'yyyy-MM-dd HH:mm:ss'}}</dt>
															<dd>{{item.message}}</dd>																							
														 
													</dl>
													</div>                                                                    
												</div>
											</div>											
											
											<div class="box box-info box-solid" data-ng-if="!isOffline" style="padding-bottom: 8px; !important; margin-bottom: 8px !important;">				                    
												<div class="box-header with-border"><i class="fa fa-history"></i> Feedback:
												</div>
												<div class="box-body">													                                                                        
													<input class="form-control inputProfile" data-ng-model="selectedPositionAlarm.feedback">                                                                       
												</div>
											</div>
										</div>																		
													  
									</div>    
																										
								</form>
							</div>
						</div>
										
					</div>
					
					<div class="modal-footer">						
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						
						<button data-ng-if="selectedPositionAlarm.alarmStatus=='READED'" type="button" data-ng-click="updateAlarmStatus('SOLVED')" class="btn btn-success" data-dismiss="modal"
							title="Encerrar Alarme"
							data-ng-disabled="(selectedPositionAlarm.feedback) ? false : true">Encerrar Alarme
						</button>
						                                                                
						<button data-ng-if="!isOffline" type="button" data-ng-click="savePositionAlarmMessage();" class="btn btn-primary" data-dismiss="modal"
							title="Salvar Nova Mensagem"
							data-ng-disabled="(selectedPositionAlarm.feedback) ? false : true">Registrar Providència 
						</button>						                                
					</div>
					
				</div>
			</div>		
		</div>
    	
    	
    </div>

    