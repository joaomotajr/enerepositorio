 	
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
											<th>Notificões</th>
											<th>Ação</th>
										</tr>
									</thead>
									<tbody>

										<tr data-ng-repeat="item in dashCompaniesAlarm">																
											
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>
												
											<td data-ng-if="!item.offLine"> <span class="label" data-ng-class="{'label-success' : item.alarmType=='NORMAL', 'label-warning' : item.alarmType=='ALERTA', 'label-default' : item.alarmType=='DETECCAO', 'label-danger' : item.alarmType=='EVACUACAO'}"> {{item.alarmType}} </span></td>																						
											<td data-ng-if="item.offLine"> <span class="label label-default offLine"> Off Line </span></td>

											<td>
												<label title="{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}"  data-ng-class="{ 
													'text-success' : item.alarmType=='NORMAL',
													'text-warning' : item.alarmType=='ALERTA', 
													'text-muted' : item.alarmType=='DETECCAO', 
													'text-danger' : item.alarmType=='EVACUACAO'}"> 
													{{item.last_update}} atrás
												</label>
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
                                            </td>
                                            <td>                                            	
                                      			<a href="#" popover>teste</a>
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
						<h5 class="box-title">Alarmes Em Análise</h5>
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
<!-- 											<th>ID</th> -->
											<th>Empresa</th>
											<th>Detector</th>	
<!-- 											<th>Sensor</th> -->
<!-- 											<th>Gás</th> -->
											<th>Alarme</th>
											<th>Data/hora</th>
<!-- 											<th>Valor</th> -->
										</tr>
									</thead>
									<tbody>

										<tr data-ng-repeat="item in dashCompaniesAlarm">																
<!-- 											<td>{{item.uid}}</td> -->
											
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>	
<!-- 											<td>{{item.sensor_name}}</td> -->
<!-- 											<td>{{item.gas_name}}</td> -->
											
											<td data-ng-if="!item.offLine"> <span class="label" data-ng-class="{'label-success' : item.alarmType=='NORMAL', 'label-warning' : item.alarmType=='ALERTA', 'label-default' : item.alarmType=='DETECCAO', 'label-danger' : item.alarmType=='EVACUACAO'}"> {{item.alarmType}} </span></td>																						
											<td data-ng-if="item.offLine"> <span class="label label-default offLine"> Off Line </span></td>

											<td>
												<label title="{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}"  data-ng-class="{ 
													'text-success' : item.alarmType=='NORMAL',
													'text-warning' : item.alarmType=='ALERTA', 
													'text-muted' : item.alarmType=='DETECCAO', 
													'text-danger' : item.alarmType=='EVACUACAO'}"> 
													{{item.last_update}} atrás
												</label>
											</td> 
											<!-- 
											<td>
												<label  data-ng-class="{ 
													'text-success' : item.alarmType=='NORMAL',
													'text-warning' : item.alarmType=='ALERTA', 
													'text-muted' : item.alarmType=='DETECCAO', 
													'text-danger' : item.alarmType=='EVACUACAO'}"> 

													<span style="vertical-align:super;font-size:0.5em" data-ng-if="item.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
													<span style="vertical-align:super;font-size:0.5em" data-ng-if="item.unitMeterGases!='LEL_PERCENT'"> {{item.unitMeterGases}}</span>
												</label>
											</td>
											 -->
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
    </div>

    