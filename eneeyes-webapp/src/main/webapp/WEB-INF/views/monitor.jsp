	
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
											<th>Notifições</th>											
										</tr>
									</thead>
									<tbody>

										<tr data-ng-repeat="item in dashCompaniesAlarm">																
											
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>
												
											<td data-ng-if="!item.offLine"> <span class="label" data-ng-class="{'label-success' : item.alarmType=='NORMAL', 'label-warning' : item.alarmType=='ALERTA', 'label-default' : item.alarmType=='DETECCAO', 'label-danger' : item.alarmType=='EVACUACAO'}">  {{item.alarmType}}  </span></td>																						
											<td data-ng-if="item.offLine"> <span class="label label-default offLine"> Off Line </span></td>

											<td>
												<label title="{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}"  data-ng-class="{ 
													'text-success' : item.alarmType=='NORMAL',
													'text-warning' : item.alarmType=='ALERTA', 
													'text-muted' : item.alarmType=='DETECCAO', 
													'text-danger' : item.alarmType=='EVACUACAO'}">Á 
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
                                           		&nbsp;&nbsp; 
                                           		<span data-ng-click="editAction($index)" class="button fa fa-info-circle" style="font-size:1.6em; color: navy" title="Ações a serem Verificadas pelo Operador"></span>
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
											<th>Empresa</th>
											<th>Detector</th>	
											<th>Alarme</th>
											<th>Data/hora</th>
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
													'text-danger' : item.alarmType=='EVACUACAO'}">Á  
													{{item.last_update}} atrás
												</label>
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
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">Posicionamento do alarme</h4>
					</div>
					<div class="modal-body">					
						<form>								                                        
                        	<div class="form-group">
                            	<label class="control-label"><i class="fa fa-user"> Ações a Serem Executadas</i></label>  					
								<textarea class="form-control" data-ng-model="positionAlarmAction" readonly disabled></textarea>							
							</div>  
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Sair</button>
						<button type="button" class="btn bt-primary">OK</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
           </div>
    	
    	
    </div>

    