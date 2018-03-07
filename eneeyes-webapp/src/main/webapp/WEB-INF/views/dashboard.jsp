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

	.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
		padding: 4px !important;
	}		
</style>
 	
<div data-ng-controller="dashController">
	<div class="row" style="font-family: Helvetica Neue, Arial;">
		<div class="col-md-12">

			<div class="box box-primary">				  
				
				<div class="box-body">
					<div class="row">
						<div class="col-lg-2 col-xs-6">								
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
							<div class="small-box bg-gray">
								<div class="inner">
									<h3 data-ng-bind="sumary.alarm1"></h3>
									<p>Detec&ccedil;&atilde;o</p>
								</div>
								<div class="icon"><i class="fa fa-battery-quarter"></i></div>
								<a href="#" data-ng-class="{'deteccao': selectedStatusDashCompaniesPosition == 'DETECCAO' }" data-ng-click="filterStatus('DETECCAO')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
							
						<div class="col-lg-2 col-xs-6">
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
							<div class="small-box bg-red">
								<div class="inner">
									<h3 data-ng-bind="sumary.alarm3"></h3>
									<p>Evacua&ccedil;&atilde;o</p>
								</div>
								<div class="icon"><i class="fa fa-battery-full"></i></div>
								<a href="#" data-ng-class="{'evacuacao': selectedStatusDashCompaniesPosition == 'EVACUACAO' }" data-ng-click="filterStatus('EVACUACAO')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
							
						<div class="col-lg-2 col-xs-6">
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

					<br>

					<div class="row">
						<div class="col-md-7">

							<div class="box box-primary">
								<div class="box-header with-border">
									<label><strong>&Uacute;ltimas medi&ccedil;&otilde;es</strong></label>
									<div class="box-tools pull-right">
									
										<label data-ng-show='loading'>Loading ...</label>		
										<button class="btn btn-box-tool" data-ng-click="refreshDashboard();"><i title="Refresh" class="fa fa-refresh"></i></button>		
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>

									</div>
								</div>									
								
								<div class="box-body" style="padding: 4px !important;text-align: -webkit-center;">
									<div class="table-responsive">
										<div style="max-height: 500px; overflow: auto">
											<table class="zui-table" id="dashboard" style="font-size:95%;">
												<thead>
													<tr>														
														<th>&nbsp;&nbsp;<i class="fa fa-tags" style="font-size:1.2em;"></i>&nbsp;</th>
														<th>Empresa</th>
														<th>Detector</th>						
														<th>Artefato</th>
														<th>Status</th>
														<th>Comunica&ccedil;&atilde;o</th>
														<th>Valor</th>
														<th style="text-align: center;"><i class="fa fa-bar-chart"></i></th>
													</tr>
												</thead>
												<tbody>
													<tr data-ng-repeat="item in dashCompaniesPositionFiltered = (dashCompaniesPosition | dashCompaniesPositionFilter: selectedStatusDashCompaniesPosition)">																
														<td>&nbsp;<strong>
															<i data-ng-if="item.artefact=='TEMPERATURE'" class="fa fa-thermometer" style="font-size:1.2em;"></i>
															<i data-ng-if="item.artefact=='ELETRICITY'" class="fa fa-plug" style="font-size:1.2em;"></i>
															<i data-ng-if="item.artefact=='TIME'" class="fa fa-clock-o" style="font-size:1.2em;"></i>
															<i data-ng-if="item.artefact!='TEMPERATURE' && item.artefact!='ELETRICITY' && item.artefact!='TIME' && item.artefact!='DIGITAL'" class="fa fa-rss" style="font-size:1.2em;"></i>
															<i data-ng-if="item.artefact=='DIGITAL'" class="fa fa-flash" style="font-size:1.2em;"></i>																		
															</strong>&nbsp; 
														</td>
														<td><span data-truncate="12" data-value="{{item.companyName}}"></span></td>															
														<td>{{item.companyDeviceName}}</td>																
														<td>
															<jsp:include page="controls/reduzMeters.jsp"/>
														</td>													
														<td> 
															<span class="label" data-ng-class="{																	
																'label-primary' : item.alarmType=='OFF' || item.alarmType=='WITHOUT', 
																'label-offline' : item.alarmType=='OFFLINE',
																'label-success' : item.alarmType=='NORMAL', 
																'label-warning' : item.alarmType=='ALERTA', 
																'label-default' : item.alarmType=='DETECCAO', 
																'label-danger' : item.alarmType=='EVACUACAO'}"> {{item.alarmType}} 
															</span>
														</td>
														<td><label title="{{item.lastUpdateFull | date:'dd/MM/yyyy HH:mm'}}">&agrave; {{item.lastUpdate}}</label></td>
														<td><label>{{item.lastValue}}</label></td>
														<td class="col-lg-2"><div class="sparkbar">{{item.arrayValues}}</div></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>									
								</div>
							</div>

						</div>
							
						<div class="col-md-5">
							<div class="row">

								<div class="box box-primary">
									<div class="box-header with-border">
										<label><strong>Gr&aacute;fico Consolidado</strong></label>
										<div class="box-tools pull-right">
											<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
											<button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
										</div>
									</div>
									<div class="box-body" style="text-align: -webkit-center;">
										<div style="border:1px solid #979797;display:inline-block;padding:0 10px">
										<div data-fusioncharts							
											data-width="400"
											data-height= "400"						    						    						    						    
											data-type="doughnut2d"						    								    
											data-datasource="{{dataSource}}">
										</div>
										</div>
										
									</div>
								</div>																
							</div>							

							<div class="row">
									<div class="box box-primary" style="margin-bottom:0px">
									<div class="box-header with-border">
										<label><strong>Calibra&ccedil;&otilde;es Pr&oacute;ximas</strong></label>
										<div class="box-tools pull-right">
											<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
											<button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
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
														<a href="#" class="product-title">Detector: {{item.companyDetectorName}}<span
															data-ng-class="{'label-danger' : item.next <= 10, 'label-warning' : (item.next > 10 && item.next <= 30), 'label-default' : item.next > 30}"
															class="label pull-right">{{item.next}} Dias</span></a> <span
															class="product-description"> {{item.companyName}} Unidade/&aacute;	rea: {{item.unitName}}/{{item.areaName}}</span>
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
	</div>    
</div>