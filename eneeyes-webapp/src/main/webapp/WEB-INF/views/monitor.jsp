	
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
					<!-- /.box-header -->
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
											<th>Notifi��es</th>											
										</tr>
									</thead>
									<tbody>

										<tr data-ng-repeat="item in dashCompaniesAlarm | filter:statusCreatedFilter">																
											
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>
												
											<td  data-ng-if="!item.offLine" style="padding-top: 13px ! important;"> <span class="label" data-ng-class="{'label-success' : item.alarmType=='NORMAL', 'label-warning' : item.alarmType=='ALERTA', 'label-default' : item.alarmType=='DETECCAO', 'label-danger' : item.alarmType=='EVACUACAO'}">��{{item.alarmType}}��</span></td>																						
											<td  data-ng-if="item.offLine"  style="padding-top: 13px ! important;"> <span class="label label-default offLine">�Off Line�</span></td>

											<td>
												{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}	
											</td>
											<td>
                                            	<span data-ng-if="item.emailStatus=='OFF'" class="icon fa fa-envelope" style="font-size:1.4em; color: gray" title="Aviso EMAIL n�o Habilitado"></span>
                                            	<span data-ng-if="item.emailStatus=='PENDENT'" class="icon fa fa-envelope" style="font-size:1.4em; color: orange" title="EMAIL na fila para Envio"></span>	
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY_ONE'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa"></span>
                                            	<span data-ng-if="item.emailStatus=='ERR_TRY'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha ao Enviar Email"></span>
                                            	<span data-ng-if="item.emailStatus=='SENDED'" class="icon fa fa-envelope" style="font-size:1.4em; color: green" title="Email Enviado"></span>
                                            	&nbsp;&nbsp;                                            	
                                            	<span data-ng-if="item.smsStatus=='OFF'"  style="font-size:1.4em; color: gray" title="Aviso de SMS n�o Habilitado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='PENDENT'"  style="font-size:1.4em; color: orange" title="SMS na Fila para Envio">SMS</span>	
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY_ONE'"  style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='ERR_TRY'"  style="font-size:1.4em; color: red" title="Falha ao Enviar SMS">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='SENDED'"  style="font-size:1.4em; color: green" title="SMS Enviado">SMS</span>
                                            	<span data-ng-if="item.smsStatus=='READED'"  style="font-size:1.4em; color: green" title="SMS Recebido pelo Destinat�rio">SMS</span>	                
                                           		&nbsp;&nbsp; 
                                           		<span data-ng-click="editAction($index)" class="button fa fa-info-circle" style="font-size:1.6em;" title="A��es a serem Verificadas pelo Operador"></span>
                                            </td>                                        						
										</tr>										
									</tbody>
								</table>
							</div>
						</div>
						<!-- /.table-responsive -->
					</div>							

				</div>
      		
       		</div>
    	
    		<div class="col-md-6" style="font-size: 8px !important;">

				<div class="box box-warning">
					<div class="box-header with-border">
						<h5 class="box-title">Alarmes Em An�lise</h5>
						<div class="box-tools pull-right">
						
							<label data-ng-show='loading'>Loading ...</label>		
		
							<button class="btn btn-box-tool" data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>

						</div>
					</div>									
					
					<!-- /.box-header -->
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
										
										<tr data-ng-repeat="item in dashCompaniesAlarm | filter:statusReadedFilter">																
											
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>	
											
											<td data-ng-if="!item.offLine"> <span class="label" data-ng-class="{'label-success' : item.alarmType=='NORMAL', 'label-warning' : item.alarmType=='ALERTA', 'label-default' : item.alarmType=='DETECCAO', 'label-danger' : item.alarmType=='EVACUACAO'}">�{{item.alarmType}}�</span></td>																						
											<td data-ng-if="item.offLine"> <span class="label label-default offLine">�Off Line�</span></td>

											<td>
												{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}												
											</td>											
										</tr>   																							
										
									</tbody>
								</table>
							</div>
						</div>
						<!-- /.table-responsive -->
					</div>							

				</div>
      		
       		</div>       		       		
       		
    	</div>    
    	
		 <div id="modalAction" class="modal">                
			<div class="modal-dialog" role="document">
				<div class="modal-content">                            
					<div class="modal-body"style="padding-bottom: 0px; !important">
					
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Posicionamento de Alarmes</strong></div>														                                                                           
						</div>
											
						<div class="box box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
							<div class="box-header">
								<h3 class="box-title">Dados do Alarme</h3>
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
													<dt>Alarme: </dt>
														
														<!--
														<dd> 
															<label data-ng-class="{ 
																'text-success' : selectedPositionAlarm.alarmType=='NORMAL',
																'text-warning' : selectedPositionAlarm.alarmType=='ALERTA', 
																'text-muted' : selectedPositionAlarm.alarmType=='DETECCAO', 
																'text-danger' : selectedPositionAlarm.alarmType=='EVACUACAO'}">�
																{{selectedPositionAlarm.alarmType}}
															</label>
														</dd>
														-->
														
														<dd style="margin-top: 7px; margin-bottom: 7px"><span class="label"  
															data-ng-class="{'label-success' : selectedPositionAlarm.alarmType=='NORMAL', 'label-warning' : selectedPositionAlarm.alarmType=='ALERTA', 'label-default' : selectedPositionAlarm.alarmType=='DETECCAO', 'label-danger' : selectedPositionAlarm.alarmType=='EVACUACAO'}">�{{selectedPositionAlarm.alarmType}}�
															</span>
														</dd>																													
														
													<dt>Data/Hora Evento: </dt>
														<dd>
															{{selectedPositionAlarm.first_update  | date:'yyyy-MM-dd HH:mm:ss'}}  
														</dd>
													<dt>Data/Hora Ultima M�di��o: </dt>
														<dd>
															{{selectedPositionAlarm.last_update_full  | date:'yyyy-MM-dd HH:mm:ss'}}  
														</dd>														
													<dt>Medi��es: </dt>															
														<dd>
															G�s: {{selectedPositionAlarm.gas_name}} | Medi��o: {{selectedPositionAlarm.last_value}}
															<span style="vertical-align:super;font-size:0.5em" data-ng-if="selectedPositionAlarm.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
															<span style="vertical-align:super;font-size:0.5em" data-ng-if="selectedPositionAlarm.unitMeterGases!='LEL_PERCENT'"> {{selectedPositionAlarm.unitMeterGases}}</span>
														</dd>
							                  	</dl>											                	
								                	
								            </div>
											<hr>
											<div class="box box-primary box-solid">				                    
												<div class="box-header with-border"><i class="fa fa-user"></i> Provid�ncias:
												</div>
												<div class="box-body">													                                                                        
													<input class="form-control inputProfile" data-ng-model="selectedPositionAlarm.action" disabled>                                                                       
												</div>
											</div>
											
											<div class="box box-info box-solid">				                    
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
						<button type="button" data-ng-click="savePositionAlarmMessage();" class="btn btn-primary" data-dismiss="modal"
							data-ng-disabled="(selectedPositionAlarm.feedback) ? false : true">Salvar
						</button>						                                
					</div>
					
				</div>
			</div>		
		</div>
    	
    	
    </div>

    