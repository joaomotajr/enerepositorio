 	
	<div data-ng-controller="dashController">
		<div class="row">
       		<div class="col-md-12">

				<div class="box box-primary" style="margin-bottom: 8px;">
				  
					<div class="box-header with-border">
						<h3 class="box-title">STATUS</h3>									
					</div>
					
					<div class="box-body">
						<div class="row">
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-aqua">
									<div class="inner">
										<h3>{{sumary.devices}}</h3>
										<p>Monitorados</p>
									</div>
									<div class="icon">
										<i class="ion ion-bag"></i>
									</div>
									<a href="#" class="small-box-footer">More info <i
										class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
							<!-- ./col -->
	
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-green">
									<div class="inner">
										<h3>{{sumary.normal}}</h3>
										<p>Operacional</p>
									</div>
									<div class="icon">
										<i class="ion ion-stats-bars"></i>
									</div>
									<a href="#" class="small-box-footer">More info <i
										class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
							<!-- ./col -->
	
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-gray">
									<div class="inner">
										<h3>{{sumary.alarm1}}</h3>
										<p>Detecção</p>
									</div>
									<div class="icon">
										<i class="ion ion-person-add"></i>
									</div>
									<a href="#" class="small-box-footer">More info <i
										class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
							<!-- ./col -->
	
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-yellow">
									<div class="inner">
										<h3>{{sumary.alarm2}}</h3>
										<p>Alerta</p>
									</div>
									<div class="icon">
										<i class="ion ion-pie-graph"></i>
									</div>
									<a href="#" class="small-box-footer">More info <i
										class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
							<!-- ./col -->
	
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-red">
									<div class="inner">
										<h3>{{sumary.alarm3}}</h3>
										<p>Evacuação</p>
									</div>
									<div class="icon">
										<i class="ion ion-pie-graph"></i>
									</div>
									<a href="#" class="small-box-footer">More info <i
										class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
							<!-- ./col -->
	
							<div class="col-lg-2 col-xs-6">
								<!-- small box -->
								<div class="small-box bg-black">
									<div class="inner">
										<h3>{{sumary.offLine}}</h3>
										<p>Off Line</p>
									</div>
									<div class="icon">
										<i class="ion ion-pie-graph"></i>
									</div>
									<a href="#" class="small-box-footer">More info <i
										class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
							<!-- ./col -->
						</div>

						<!-- Main row -->
						<div class="row">
							<div class="col-md-9">
	
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

														<tr data-ng-repeat="item in dashCompaniesPosition">																
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
								<!-- /.box -->
	
							</div>
							<div class="col-md-3">
								<div class="box box-primary">
									<div class="box-header with-border">
										<h3 class="box-title">MANUTENÇÃO </h3>
										<div class="box-tools pull-right">
											<button class="btn btn-box-tool" data-widget="collapse">
												<i class="fa fa-minus"></i>
											</button>
										</div>
									</div>
									<!-- /.box-header -->
									<div class="box-body">
										<ul class="products-list product-list-in-box">
											<li class="item">
												<div class="product-img">
													<img src="/assets/img/default-50x50.gif" alt="Product Image">
												</div>
												<div class="product-info">
													<a href="javascript::;" class="product-title">Loga<span
														class="label label-warning pull-right"></span></a> <span
														class="product-description"> Sensor A1 </span>
												</div>
											</li>
											<!-- /.item -->
											<li class="item">
												<div class="product-img">
													<img src="/assets/img/default-50x50.gif" alt="Product Image">
												</div>
												<div class="product-info">
													<a href="javascript::;" class="product-title">Nestle <span
														class="label label-info pull-right"></span></a> <span
														class="product-description"> Sensor A2 </span>
												</div>
											</li>
											<!-- /.item -->
											<li class="item">
												<div class="product-img">
													<img src="/assets/img/default-50x50.gif" alt="Product Image">
												</div>
												<div class="product-info">
													<a href="javascript::;" class="product-title">Vopak<span
														class="label label-danger pull-right"></span></a> <span
														class="product-description"> Sensor A3 </span>
												</div>
											</li>
											<!-- /.item -->
											<li class="item">
												<div class="product-img">
													<img src="/assets/img/default-50x50.gif" alt="Product Image">
												</div>
												<div class="product-info">
													<a href="javascript::;" class="product-title">Construtora
														Quintella<span class="label label-success pull-right"></span>
													</a> <span class="product-description"> Sensor A4 </span>
												</div>
											</li>
				
										</ul>
									</div>
								
								</div>
							</div>
						</div>
					</div>
      			</div>	
       		</div>
    	</div>    
    </div>

    