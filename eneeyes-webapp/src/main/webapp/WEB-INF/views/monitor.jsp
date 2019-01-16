<style>

	.rui-table {		
		border-spacing: 0;
		font: normal 14px Arial, sans-serif;
	}
	.rui-table thead th {				
		padding: 5px;
		text-align: center;	
		font-weight: 500;	
	}
	.rui-table tbody td {
		border: solid 1px gray;
		color: #333;
		padding: 5px;		
	}

</style>

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
											<th>Itera&ccedil;&atilde;es</th>
											<th>Reportar</th>											
										</tr>
									</thead>
									<tbody>
										<tr data-ng-repeat="item in dashCompaniesAlarm | filter: {alarmStatus: 'CREATED' } track by item.uid">											
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>											
											<td style="padding-top: 13px ! important;"> 
												<span class="label" 
													data-ng-class="{
													'label-offline' : item.alarmType=='OFFLINE', 
													'label-success' : item.alarmType=='NORMAL', 
													'label-warning' : item.alarmType=='ALERTA', 
													'label-default' : item.alarmType=='DETECCAO', 
													'label-danger' : item.alarmType=='EVACUACAO'}"> {{item.alarmType}} 
													</span>
												</td>	
											<td>{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}</td>
											<td>
												<span data-ng-if="item.sigmaStatus=='OFF'" class="icon fa fa-exchange" style="font-size:1.4em; color: gray" title="Sem Integra&ccedil;&atilde;o ao Sigma Habilitada"></span>
                                            	<span data-ng-if="item.sigmaStatus=='ERROR'" class="icon fa fa-exchange" style="font-size:1.4em; color: red" title="Falha ao Informar Sigma"></span>
                                            	<span data-ng-if="item.sigmaStatus=='SENDED'" class="icon fa fa-exchange" style="font-size:1.4em; color: green" title="Integra&ccedil;&atilde;o ao Sigma Realizada"></span>
                                            	<span data-ng-if="item.sigmaStatus=='ON'" class="icon fa fa-exchange" style="font-size:1.4em; color: orange" title="Integra&ccedil;&atilde;o ao Sigma em fila"></span>
                                            	&nbsp;&nbsp;
												<span data-ng-if="item.soundStatus=='OFF'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: gray" title="Alerta Sonoro n&atilde;o Habilitado"></span>
                                            	<a href="#" data-ng-click="updateSound(item.index);" data-ng-if="item.soundStatus=='ON'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: red" title="Alerta Sonoro"></a>
                                            	<span data-ng-if="item.soundStatus=='SILENT'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: green" title="Alerta Silenciado"></span>
                                            	&nbsp;&nbsp;
                                            	<span data-ng-if="item.emailStatus=='OFF'" class="icon fa fa-envelope" style="font-size:1.4em; color: gray" title="Aviso EMAIL n&atilde;o Habilitado"></span>
                                            	<span data-ng-if="item.emailStatus=='PENDENT'" class="icon fa fa-envelope" style="font-size:1.4em; color: orange" title="EMAIL na fila para Envio"></span>	
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY_ONE'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa"></span>
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha ao Enviar Email"></span>
                                            	<span data-ng-if="item.emailStatus=='SENDED'" class="icon fa fa-envelope" style="font-size:1.4em; color: green" title="Email Enviado"></span>
                                            	&nbsp;&nbsp;                                            	
                                            	<span data-ng-if="item.smsStatus=='OFF'"  style="font-size:1.4em; color: gray" title="Aviso de SMS n&atilde;o Habilitado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='PENDENT'"  style="font-size:1.4em; color: orange" title="SMS na Fila para Envio">SMS</span>	
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY_ONE'"  style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY'"  style="font-size:1.4em; color: red" title="Falha ao Enviar SMS">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='SENDED'"  style="font-size:1.4em; color: green" title="SMS Enviado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='READED'"  style="font-size:1.4em; color: green" title="SMS Recebido pelo Destinat&aacute;rio">SMS</span>	                
                                           		&nbsp;&nbsp; 
                                           	</td>
                                           	<td>
                                           		<a href="#" data-ng-click="editActionCreated(item.index)" class="button fa fa-list-ol" style="font-size:1.6em;" title="A&ccedil;&otilde;es a serem Verificadas pelo Operador"></a>
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
						<h5 class="box-title">Alarmes Em An&aacute;lise</h5>
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
											<th>Itera&ccedil;&atilde;es</th>
											<th>Reportar</th>
										</tr>
									</thead>
									<tbody>
										
										<tr data-ng-repeat="item in dashCompaniesAlarm | filter: {alarmStatus: 'READED' } track by item.uid">																
												
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>
												<td style="padding-top: 13px ! important;"> 
													<span class="label" 
														data-ng-class="{
															'label-offline' : item.alarmType=='OFFLINE', 
															'label-success' : item.alarmType=='NORMAL', 
															'label-warning' : item.alarmType=='ALERTA', 
															'label-default' : item.alarmType=='DETECCAO', 
															'label-danger' : item.alarmType=='EVACUACAO'}"> 
																{{item.alarmType}} 
													</span>
												</td>
											<td>
												{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}	
											</td>
											<td>
												<span data-ng-if="item.sigmaStatus=='OFF'" class="icon fa fa-exchange" style="font-size:1.4em; color: gray" title="Sem Integra&ccedil;&atilde;o ao Sigma Habilitada"></span>
                                            	<span data-ng-if="item.sigmaStatus=='ERROR'" class="icon fa fa-exchange" style="font-size:1.4em; color: red" title="Falha ao Informar Sigma"></span>
                                            	<span data-ng-if="item.sigmaStatus=='SENDED'" class="icon fa fa-exchange" style="font-size:1.4em; color: green" title="Integra&ccedil;&atilde;o ao Sigma Realizada"></span>
                                            	<span data-ng-if="item.sigmaStatus=='ON'" class="icon fa fa-exchange" style="font-size:1.4em; color: orange" title="Integra&ccedil;&atilde;o ao Sigma em fila"></span>
                                            	&nbsp;&nbsp;
                                            	<span data-ng-if="item.emailStatus=='OFF'" class="icon fa fa-envelope" style="font-size:1.4em; color: gray" title="Aviso EMAIL n;&atilde;o Habilitado"></span>
                                            	<span data-ng-if="item.emailStatus=='PENDENT'" class="icon fa fa-envelope" style="font-size:1.4em; color: orange" title="EMAIL na fila para Envio"></span>	
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY_ONE'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa"></span>
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha ao Enviar Email"></span>
                                            	<span data-ng-if="item.emailStatus=='SENDED'" class="icon fa fa-envelope" style="font-size:1.4em; color: green" title="Email Enviado"></span>
                                            	&nbsp;&nbsp;                                            	
                                            	<span data-ng-if="item.smsStatus=='OFF'"  style="font-size:1.4em; color: gray" title="Aviso de SMS n;&atilde;o Habilitado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='PENDENT'"  style="font-size:1.4em; color: orange" title="SMS na Fila para Envio">SMS</span>	
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY_ONE'"  style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY'"  style="font-size:1.4em; color: red" title="Falha ao Enviar SMS">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='SENDED'"  style="font-size:1.4em; color: green" title="SMS Enviado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='READED'"  style="font-size:1.4em; color: green" title="SMS Recebido pelo Destinat;&aacute;rio">SMS</span>	                
                                           		&nbsp;&nbsp;
                                           	</td>
                                           	<td>
                                           		<a href="#" data-ng-click="editActionReaded(item.index)" class="button fa fa-list-ol" style="font-size:1.6em;" title="A&ccedil;&otilde;es a serem Verificadas pelo Operador"></a>
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

				<div class="box box-SUCCESS">
					<div class="box-header with-border">
						<h5 class="box-title">Alarmes Encerrados [Resolvidos].</h5>
						<div class="box-tools pull-right">						
							<label data-ng-show='loading'>Loading ...</label>		
							<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							<button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-remove"></i></button>
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
											<th>Itera&ccedil;&atilde;es</th>
											<th>Hist&oacute;rico</th>
										</tr>
									</thead>
									<tbody>
										
										<tr data-ng-repeat="item in dashCompaniesAlarm | filter: {alarmStatus: 'SOLVED' } track by item.uid">																
												
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>
												<td style="padding-top: 13px ! important;"> 
													<span class="label" 
														data-ng-class="{
															'label-offline' : item.alarmType=='OFFLINE', 
															'label-success' : item.alarmType=='NORMAL', 
															'label-warning' : item.alarmType=='ALERTA', 
															'label-default' : item.alarmType=='DETECCAO', 
															'label-danger' : item.alarmType=='EVACUACAO'}"> 
																{{item.alarmType}} 
													</span>
												</td>
											<td>
												{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}	
											</td>
											<td>
												<span data-ng-if="item.sigmaStatus=='OFF'" class="icon fa fa-exchange" style="font-size:1.4em; color: gray" title="Sem Integra&ccedil;&atilde;o ao Sigma Habilitada"></span>
                                            	<span data-ng-if="item.sigmaStatus=='ERROR'" class="icon fa fa-exchange" style="font-size:1.4em; color: red" title="Falha ao Informar Sigma"></span>
                                            	<span data-ng-if="item.sigmaStatus=='SENDED'" class="icon fa fa-exchange" style="font-size:1.4em; color: green" title="Integra&ccedil;&atilde;o ao Sigma Realizada"></span>
                                            	<span data-ng-if="item.sigmaStatus=='ON'" class="icon fa fa-exchange" style="font-size:1.4em; color: orange" title="Integra&ccedil;&atilde;o ao Sigma em fila"></span>
                                            	&nbsp;&nbsp;
                                            	<span data-ng-if="item.emailStatus=='OFF'" class="icon fa fa-envelope" style="font-size:1.4em; color: gray" title="Aviso EMAIL n;&atilde;o Habilitado"></span>
                                            	<span data-ng-if="item.emailStatus=='PENDENT'" class="icon fa fa-envelope" style="font-size:1.4em; color: orange" title="EMAIL na fila para Envio"></span>	
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY_ONE'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa"></span>
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha ao Enviar Email"></span>
                                            	<span data-ng-if="item.emailStatus=='SENDED'" class="icon fa fa-envelope" style="font-size:1.4em; color: green" title="Email Enviado"></span>
                                            	&nbsp;&nbsp;                                            	
                                            	<span data-ng-if="item.smsStatus=='OFF'"  style="font-size:1.4em; color: gray" title="Aviso de SMS n;&atilde;o Habilitado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='PENDENT'"  style="font-size:1.4em; color: orange" title="SMS na Fila para Envio">SMS</span>	
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY_ONE'"  style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY'"  style="font-size:1.4em; color: red" title="Falha ao Enviar SMS">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='SENDED'"  style="font-size:1.4em; color: green" title="SMS Enviado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='READED'"  style="font-size:1.4em; color: green" title="SMS Recebido pelo Destinat;&aacute;rio">SMS</span>	                
                                           		&nbsp;&nbsp;
                                           	</td>
                                           	<td>
                                           		<a href="#" data-ng-click="editActionSolved(item.index)" class="button fa fa-list-ul" style="font-size:1.6em; color: black" title="A&ccedil;&otilde;es Executadas."></a>
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
								<strong>Posicionamento de Alarmes</strong>																
							</div>														                                                                           
						</div>
											
						<div class="box box-primary" style="padding-bottom: 8px !important; margin-bottom: 8px !important;">
							<div class="box-header">
								<h3  class="box-title">Dados do Dispositivo e Alarme</h3>								
								<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
							</div>					
							<div class="box-body" style="padding-bottom: 0px !important;">
								<form class="form" name="userForm">		
								
									<div class="row">											
										<div class="col-md-12">
										
											<div class="box-body" style="padding-bottom: 0px; background-color: #e7e7e7">											                												                	 
												<dl class="dl-horizontal">
													<dt>Empresa:</dt>
														<dd>{{selectedPositionAlarm.company_name}}</dd>
													<dt>Unidade / Area:</dt>
														<dd>{{selectedPositionAlarm.unit_name}} / {{selectedPositionAlarm.area_name}}</dd>													
													<dt>Dispositivo</dt>
														<dd>{{selectedPositionAlarm.company_detector_name}}</dd>
													<dt data-ng-if="selectedPositionAlarm.company_detector_local">Local</dt>
														<dd data-ng-if="selectedPositionAlarm.company_detector_local">{{selectedPositionAlarm.company_detector_local}}</dd>
													<dt>Alarme: </dt>														
														<dd style="margin-top: 7px; margin-bottom: 7px">
															<span class="label"  
																data-ng-class="{
																	'label-offline' : selectedPositionAlarm.alarmType=='OFFLINE',
																	'label-success' : selectedPositionAlarm.alarmType=='NORMAL', 
																	'label-warning' : selectedPositionAlarm.alarmType=='ALERTA', 
																	'label-default' : selectedPositionAlarm.alarmType=='DETECCAO', 
																	'label-danger' : selectedPositionAlarm.alarmType=='EVACUACAO'}"> {{selectedPositionAlarm.alarmType}}
															</span>
														</dd>													
													<dt>Data/Hora Evento: </dt>
														<dd>
															{{selectedPositionAlarm.first_update  | date:'yyyy-MM-dd HH:mm:ss'}}  
														</dd>
													<dt>Data/Hora Ultima Medi&ccedil;&atilde;o: </dt>
														<dd>
															{{selectedPositionAlarm.last_update_full  | date:'yyyy-MM-dd HH:mm:ss'}}  
														</dd>														
													<dt>Artefato/Medi&ccedil;&atilde;o: </dt>															
														<dd>
															{{selectedPositionAlarm.artefact}}
															<span data-ng-if="selectedPositionAlarm.last_value>0">
																| Medi&ccedil;&atilde;o: {{selectedPositionAlarm.last_value}}
																<span style="vertical-align:super;font-size:0.5em" data-ng-if="selectedPositionAlarm.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
																<span style="vertical-align:super;font-size:0.5em" data-ng-if="selectedPositionAlarm.unitMeterGases!='LEL_PERCENT'"> {{selectedPositionAlarm.unitMeterGases}}</span>
															</span>
														</dd>													
							                  	</dl>								                	
								            </div>
								            
											<hr style="margin-top: 8px !important; margin-bottom: 8px !important;">
											<div class="box box-primary box-solid" style="padding-bottom: 8px !important; margin-bottom: 8px !important;">				                    
												<div class="box-header with-border"><i class="fa fa-user"></i> Provid&ecirc;ncias:
												</div>
												<div class="box-body">													                                                                        
													<input class="form-control inputProfile" placeholder="Nenhuma Provid&ecirc;ncia Registrada para o alarme" data-ng-model="selectedPositionAlarm.action" disabled>												
												</div>
											</div>											
																						
											<div class="box box-info box-solid" data-ng-if="selectedPositionAlarm.messages.length > 0" style="padding-bottom: 8px !important; margin-bottom: 8px !important;">				                    
												<div class="box-header with-border"><i class="fa fa-envelope"></i> Hist&oacute;rico de Mensagens:
												</div>
												<div class="box-body" style="font-size: 0.8em">

													<table class='rui-table' data-cellspacing="0" data-width="100%">
														<thead>
															<tr>	                
																<th class="col-md-4">Data</th>                                                                                   
																<th class="col-md-1">Usuario</th>
																<th class="col-md-7">Mensagem</th>
															</tr>                                    
														</thead>															
														<tbody>
															<tr data-ng-repeat="item in selectedPositionAlarm.messages">
																<td>{{item.lastUpdate | date:'dd/MM/yyyy HH:mm:ss'}}</td>
																<td>{{item.userDto.displayName}}</td>
																<td>{{item.message}}</td>															
															</tr>
														</tbody>	                   
													</table>												
												</div>
											</div>											
											
											<div data-ng-if="!onlyHistoric" class="box box-info box-solid" style="padding-bottom: 8px !important; margin-bottom: 8px !important;">				                    
												<div class="box-header with-border"><i class="fa fa-history"></i> Feedback:</div>
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
						                                                                
						<button type="button" data-ng-click="savePositionAlarmMessage();" class="btn btn-primary" data-dismiss="modal"
							title="Salvar Nova Mensagem"
							data-ng-disabled="(selectedPositionAlarm.feedback) ? false : true">Registrar Provid&ecirc;ncia 
						</button>						                                
					</div>
					
				</div>
			</div>		
		</div>    	
    	
    </div>

    