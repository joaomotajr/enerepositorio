 	<style>
		
	.offLine {		
		background-color: black ! important;
		color: white ! important;	
	}	
	</style>




	<div data-ng-controller="dashController">
		<div class="row">
       		<div class="col-md-12">

				<div class="box box-primary" style="margin-bottom: 8px;">
				  
					<div class="box-header with-border">
						<h3 class="box-title">STATUS</h3>
						<div class="box-tools pull-right"></div>
					</div>'
					
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
										<p>Status Operacional</p>
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
										<p>Alarme 1 - Detecção</p>
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
										<p>Alarm 2 - Alerta</p>
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
										<p>Alarm3 - Evacuação</p>
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
							<div class="col-md-8">
	
								<div class="box box-info">
									<div class="box-header with-border">
										<h3 class="box-title">Ultimos Alertas</h3>
										<div class="box-tools pull-right">
											<button class="btn btn-box-tool" data-widget="collapse">
												<i class="fa fa-minus"></i>
											</button>
											<button class="btn btn-box-tool" data-widget="remove">
												<i class="fa fa-times"></i>
											</button>
										</div>
									</div>
									
														<!-- /.box-header -->
									<div class="box-body">
										<div class="table-responsive">
											<div style="max-height: 300px; overflow: auto">
												<table class="table no-margin">
													<thead>
														<tr>
															<th>Alerta ID</th>
															<th>Sensor</th>
															<th>Gás</th>
															<th>Alarme</th>
															<th>Última Comunicação</th>
														</tr>
													</thead>
													<tbody>
														<tr data-ng-repeat="item in listPositions.list | filter:alarmFilter">																
															<td>{{item.uid}}</td>
															<td>{{item.sensorDto.name}}</td>	
															<td>{{item.sensorDto.gasesDto[0].name}}</td>
															
															<td data-ng-if="!item.offLine"> <span class="label" data-ng-class="{'label-warning' : item.alarmType=='ALERTA', 'label-default' : item.alarmType=='DETECCAO', 'label-danger' : item.alarmType=='EVACUACAO'}"> {{item.alarmType}} </span></td>																						
															<td data-ng-if="item.offLine"> <span class="label label-default offLine"> Off Line </span></td>
															<td>{{item.lastUpdate | date:'dd/MM/yyyy HH:mm'}}</td>
														</tr>   																							
														
													</tbody>
												</table>
											</div>
										</div>
										<!-- /.table-responsive -->
									</div>	
								
<!-- 									/.box-body -->
<!-- 									<div class="box-footer clearfix"> -->
<!-- 										<a href="javascript::;" -->
<!-- 											class="btn btn-sm btn-info btn-flat pull-left">Baixar -->
<!-- 											Alertas</a> <a href="javascript::;" -->
<!-- 											class="btn btn-sm btn-default btn-flat pull-right">Ver -->
<!-- 											Todos Alertas</a> -->
<!-- 									</div> -->
<!-- 									/.box-footer -->
								</div>
								<!-- /.box -->
	
							</div>
							<div class="col-md-4">
								<div class="box box-primary">
									<div class="box-header with-border">
										<h3 class="box-title">Manutenção Preventiva</h3>
										<div class="box-tools pull-right">
											<button class="btn btn-box-tool" data-widget="collapse">
												<i class="fa fa-minus"></i>
											</button>
											<button class="btn btn-box-tool" data-widget="remove">
												<i class="fa fa-times"></i>
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
											<!-- /.item -->
										</ul>
									</div>
									
<!-- 									/.box-body -->
<!-- 									<div class="box-footer text-center"> -->
<!-- 										<a href="javascript::;" class="uppercase">Ver Todos -->
<!-- 											Dispositivos</a> -->
<!-- 									</div> -->
<!-- 									/.box-footer -->
									
								</div>
								<!-- /.box -->
	
	
							</div>
						</div>
						<!-- /.row -->
					</div>
      			</div>	
       		</div>
    	</div>    
    </div>

    