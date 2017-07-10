	

		
	<style>
		.small-box>.icon {
	 		top: 5px ! important;
	 		font-size: 40px ! important;
		}
		
		.small-box>.inner {
			padding: 0px !important; 
			padding-left:10px !important; 
			padding-right:10px !important;
		}
		
		.all {
			background: white !important;
			color: #00c0ef !important;
		}
		
		.normal {
			background: white !important;
			color: #00a65a !important;
		}
		
		.deteccao {
			background: white !important;
			color: gray !important;
		}
		
		.alerta {
			background: white !important;
			color: #f39c12 !important;
		}
		
		.evacuacao {
			background: white !important;
			color: #dd4b39 !important;
		}
		
		.off {
			background: white !important;
			color: black !important;
		}
	</style>
 	
	<div data-ng-controller="dashController">
		<div class="row">
       		<div class="col-md-12">

				<div class="box box-primary" style="margin-bottom: 8px;">
				  
					<div class="box-header with-border">
						<h3 class="box-title">STATUS</h3>
						<a href="#" class="text-muted pull-right" data-ng-click="refreshDashboard();"><i title="Refresh" class="fa fa-refresh"></i></a>									
					</div>
					
					<div class="box-body">
						<div class="row">
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-aqua">
									<div class="inner">
										<h3 data-ng-bind="sumary.devices"></h3>
										<p>Monitorados</p>
									</div>
									<div class="icon"><i class="fa fa-tv"></i></div>
									<a href="#" data-ng-class="{'all': selectedStatusDashCompaniesPosition == 'ALL' }" data-ng-click="filterStatus('ALL')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
								
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-green">
									<div class="inner">
										<h3 data-ng-bind="sumary.normal"></h3>
										<p>Operacional</p>
									</div>
									<div class="icon"><i class="fa fa-check"></i></div>
									<a href="#" data-ng-class="{'normal': selectedStatusDashCompaniesPosition == 'NORMAL' }" data-ng-click="filterStatus('NORMAL')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
								
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-gray">
									<div class="inner">
										<h3 data-ng-bind="sumary.alarm1"></h3>
										<p>Detecção</p>
									</div>
									<div class="icon"><i class="fa fa-battery-quarter"></i></div>
									<a href="#" data-ng-class="{'deteccao': selectedStatusDashCompaniesPosition == 'DETECCAO' }" data-ng-click="filterStatus('DETECCAO')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
								
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-yellow">
									<div class="inner">
										<h3 data-ng-bind="sumary.alarm2"></h3>
										<p>Alerta</p>
									</div>
									<div class="icon"><i class="fa fa-battery-half"></i></div>
									<a href="#" data-ng-class="{'alerta': selectedStatusDashCompaniesPosition == 'ALERTA' }" data-ng-click="filterStatus('ALERTA')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
								
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-red">
									<div class="inner">
										<h3 data-ng-bind="sumary.alarm3"></h3>
										<p>Evacuação</p>
									</div>
									<div class="icon"><i class="fa fa-battery-full"></i></div>
									<a href="#" data-ng-class="{'evacuacao': selectedStatusDashCompaniesPosition == 'EVACUACAO' }" data-ng-click="filterStatus('EVACUACAO')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
								
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-black">
									<div class="inner">
										<h3 data-ng-bind="sumary.offLine"></h3>
										<p>Off Line</p>
									</div>
									<div class="icon" style="color:white !important"><i class="fa fa-battery-empty"></i></div>
									<a href="#" data-ng-class="{'off': selectedStatusDashCompaniesPosition == 'OFF' }" data-ng-click="filterStatus('OFF')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>							
						</div>

						<!-- Main row -->
						<div class="row">
							<div class="col-md-8">
	
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
									
									<div class="box-body">
										<div class="table-responsive">
											<div style="max-height: 500px; overflow: auto">
												<table class="table table-striped" id="dashboard" style="font-size:1.0em;">
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
														<tr data-ng-repeat="item in dashCompaniesPositionFiltered = (dashCompaniesPosition | dashCompaniesPositionFilter: selectedStatusDashCompaniesPosition)">																
															<td>{{item.uid}}</td>
															
															<td>{{item.company_name}}</td>
															<td>{{item.company_detector_name}}</td>	
															<td>{{item.sensor_name}}</td>
															<td>{{item.gas_name}}</td>
															
															<td style="padding-top: 13px ! important;" data-ng-if="!item.offLine"> <span class="label" data-ng-class="{'label-primary' : item.alarmType=='OFF', 'label-success' : item.alarmType=='NORMAL', 'label-warning' : item.alarmType=='ALERTA', 'label-default' : item.alarmType=='DETECCAO', 'label-danger' : item.alarmType=='EVACUACAO'}"> {{item.alarmType}} </span></td>																						
															<td style="padding-top: 13px ! important;" data-ng-if="item.offLine"> <span class="label label-default offLine"> Off Line </span></td>

															<td>
																<label title="{{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}">  
<!-- 																	data-ng-class="{  -->
<!-- 																	'text-success' : item.alarmType=='NORMAL', -->
<!-- 																	'text-warning' : item.alarmType=='ALERTA',  -->
<!-- 																	'text-muted' : item.alarmType=='DETECCAO',  -->
<!-- 																	'text-danger' : item.alarmType=='EVACUACAO'}"  -->
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
									</div>
								</div>
	
							</div>
							 
							<div class="col-md-4">
								<div class="row">
								<div class="box box-primary" style="margin-bottom:0px">
									<div class="box-header with-border">
										<h3 class="box-title">CALIBRAÇÕES PRÓXIMAS </h3>
										<div class="box-tools pull-right">
											<button class="btn btn-box-tool" data-widget="collapse">
												<i class="fa fa-minus"></i>
											</button>
										</div>
									</div>
									
									<div class="box-body">
										<div class="list-group" style="max-height: 188px !important; height:auto; overflow: auto;">
										<ul class="products-list product-list-in-box">
											<li class="item" data-ng-repeat="item in listAllDashDetectorsMaintenance.list | orderBy : 'item.next'" style="padding: 5px 0;">
												<div class="product-img">												         
													<img data-ng-src="{{item.image}}" alt="Product Image" >
												</div>
												<div class="product-info">
													<a href="#" class="product-title">Detector: {{item.company_detector_name}}<span
														data-ng-class="{'label-danger' : item.next <= 10, 'label-warning' : (item.next > 10 && item.next <= 30), 'label-default' : item.next > 30}"
														class="label pull-right">{{item.next}} Dias</span></a> <span
														class="product-description"> {{item.company_name}} Unidade/Área: {{item.unit_name}}/{{item.area_name}}</span>
												</div>
											</li>				
										</ul>
										</div>
									</div>
								
								</div>
								</div>
								<div class="row">
									<div class="box box-primary">
									    <div class="box-header with-border">
									        <h3 class="box-title">GRÁFICOS STATUS CONSOLIDADO</h3>
									        <div class="box-tools pull-right">
									            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
									        </div>
									    </div><!-- /.box-header -->
									    <div class="box-body">
									        <div class="row">
									            <div class="col-md-8">
									                <div class="chart-responsive">
									                    <canvas id="pieChart" height="155" width="269" style="width: 269px; height: 155px;"></canvas>
									                </div><!-- ./chart-responsive -->
									            </div><!-- /.col -->
									            <div class="col-md-4">
									              <ul class="chart-legend clearfix">
									              <li><i class="fa fa-circle-o text-green"></i> Operacional</li>
									                  <li><i class="fa fa-circle-o text-red"></i> Evacuação</li>
									                  <li><i class="fa fa-circle-o text-gray"></i> Detecção</li>
									                  <li><i class="fa fa-circle-o text-yellow"></i> Alerta</li>									                  
									                  <li><i class="fa fa-circle-o text-black"></i> Off</li>
									                  <li><i class="fa fa-circle-o text-light-blue"></i> Turn Off</li>
									              </ul>
									            </div><!-- /.col -->
									        </div><!-- /.row -->
									    </div><!-- /.box-body -->									    
									</div>
								
								</div>
							</div>
							
						</div>
					</div>
      			</div>	
       		</div>
    	</div>    
    </div>

    