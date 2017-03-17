 	
	<div data-ng-controller="monitorController">
		<div class="row">
       		<div class="col-md-12">

				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">ULTIMAS MEDIÇÕES</h3>
						<div class="box-tools pull-right">
						
							<label data-ng-show='loading'>Loading ...</label>		
		
							<button class="btn btn-box-tool" data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>

						</div>
					</div>									
										<!-- /.box-header -->
					<div class="box-body">
						<div class="table-responsive">
							<div style="max-height: 500px; overflow: auto">
								<table class="table no-margin">
									<thead>
										<tr>														
											<th>ID</th>
											<th>Empresa</th>
											<th>Detector</th>	
											<th>Sensor</th>
											<th>Gás</th>
											<th>Status</th>
											<th>Comunicação</th>
											<th>Valor</th>
										</tr>
									</thead>
									<tbody>

										<tr data-ng-repeat="item in dashCompaniesAlarm">																
											<td>{{item.uid}}</td>
											
											<td>{{item.company_name}}</td>
											<td>{{item.company_detector_name}}</td>	
											<td>{{item.sensor_name}}</td>
											<td>{{item.gas_name}}</td>
											
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
												<label  data-ng-class="{ 
													'text-success' : item.alarmType=='NORMAL',
													'text-warning' : item.alarmType=='ALERTA', 
													'text-muted' : item.alarmType=='DETECCAO', 
													'text-danger' : item.alarmType=='EVACUACAO'}"> 
													{{item.last_value}} 
													<span style="vertical-align:super;font-size:0.5em" data-ng-if="item.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
													<span style="vertical-align:super;font-size:0.5em" data-ng-if="item.unitMeterGases!='LEL_PERCENT'"> {{item.unitMeterGases}}</span>
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
    </div>

    