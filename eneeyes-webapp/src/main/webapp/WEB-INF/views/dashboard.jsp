
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
		border-style: solid;
    	border-color:#00c0ef !important;
	}
	
	.normal {
		background: white !important;
		color: #00a65a !important;
		border-style: solid;
    	border-color:#00a65a !important;
	}
	
	.deteccao {
		background: white !important;
		color: gray !important;
		border-style: solid;
    	border-color:gray !important;
	}
	
	.alerta {
		background: white !important;
		color: #f39c12 !important;
		border-style: solid;
    	border-color:#f39c12 !important;
	}
	
	.evacuacao {
		background: white !important;
		color: #dd4b39 !important;
		border-style: solid;
    	border-color: #dd4b39 !important;
	}
	
	.off {
		background: white !important;
		color: black !important;
		border-style: solid;
    	border-color: black !important;
	}		

	.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
		padding: 4px !important;
	}

	.info-box-min {
		min-height: 80px;
	}

	.info-box-content-min {
		margin-left: 70px;
		padding-bottom: 8px;
	}

	.info-box-icon-min {
		height: 90px;
    	width: 70px;
		line-height: 80px;
		line-height: 80px;
	}

	.info-box-icon-min>i {
		padding-top: 25%;
		font-size: 35px;
	}		

	.col-lg-6, .col-xs-6 {
		padding-right: 5px;
    	padding-left: 5px;
	}

	.bg-blackness {
		background-color: #444343;
	}
</style>
 	
<div data-ng-controller="dashController">
	<div class="row" style="font-family: Helvetica Neue, Arial;">
		<div class="col-md-12">

			<div class="box box-primary">
				
				<div class="box-body">
					<div class="row">
						<div class="col-lg-2 col-xs-6">
							<div class="info-box info-box-min bg-aqua">
								<span class="info-box-icon info-box-icon-min cursor" data-ng-class="{'all': selectedStatusDashCompaniesPosition == 'ALL' }" data-ng-click="filterStatus('ALL')"><i class="fa fa-tv"></i></span>
								<div class="info-box-content info-box-content-min">
									<span class="info-box-text">Monitorados</span>
									<span class="info-box-number" data-ng-bind="sumary.devices"></span>
									<div class="progress">
										<div class="progress-bar" style="width:100%"></div>
									</div>
									<span class="progress-description">											
										Todos Dispositivos
									</span>
								</div>
							</div>											
						</div>
							
						<div class="col-lg-2 col-xs-6">
							<div class="info-box info-box-min bg-green">
								<span class="info-box-icon info-box-icon-min cursor" data-ng-class="{'normal': selectedStatusDashCompaniesPosition == 'NORMAL' }" data-ng-click="filterStatus('NORMAL')"><i class="fa fa-check"></i></span>
								<div class="info-box-content info-box-content-min">
									<span class="info-box-text">Operacional</span>
									<span class="info-box-number" data-ng-bind="sumary.normal.value"></span>
									<div class="progress">
										<div class="progress-bar" ng-style="{'width' : sumary.normal.percent + '%' }"></div>
									</div>
									<span class="progress-description">
										Em Funcionamento
									</span>
								</div>
							</div>						
						</div>
							
						<div class="col-lg-2 col-xs-6">
							<div class="info-box info-box-min bg-gray">
								<span class="info-box-icon info-box-icon-min cursor" data-ng-class="{'deteccao': selectedStatusDashCompaniesPosition == 'DETECCAO'}" data-ng-click="filterStatus('DETECCAO')"><i class="fa fa-battery-quarter"></i></span>
								<div class="info-box-content info-box-content-min">
									<span class="info-box-text">Detec&ccedil;&atilde;o</span>
									<span class="info-box-number" data-ng-bind="sumary.alarm1.value"></span>
									<div class="progress">
										<div class="progress-bar" ng-style="{'width' : sumary.alarm1.percent + '%' }"></div>
									</div>
									<span class="progress-description">
										Alarme N&iacute;vel I
									</span>
								</div>
							</div>						
						</div>
							
						<div class="col-lg-2 col-xs-6">
							<div class="info-box info-box-min bg-yellow">
								<span class="info-box-icon info-box-icon-min cursor" data-ng-class="{'alerta': selectedStatusDashCompaniesPosition == 'ALERTA'}" data-ng-click="filterStatus('ALERTA')"><i class="fa fa-battery-half"></i></span>
								<div class="info-box-content info-box-content-min">
									<span class="info-box-text">Alerta</span>
									<span class="info-box-number" data-ng-bind="sumary.alarm2.value"></span>
									<div class="progress">
										<div class="progress-bar" ng-style="{'width' : sumary.alarm2.percent + '%' }"></div>
									</div>
									<span class="progress-description">
										Alarme N&iacute;vel II
									</span>
								</div>
							</div>						
						</div>
							
						<div class="col-lg-2 col-xs-6">
							<div class="info-box info-box-min bg-red">
								<span class="info-box-icon info-box-icon-min cursor" data-ng-class="{'evacuacao': selectedStatusDashCompaniesPosition == 'EVACUACAO' }" data-ng-click="filterStatus('EVACUACAO')"><i class="fa fa-battery-full"></i></span>
								<div class="info-box-content info-box-content-min">
									<span class="info-box-text">Evacua&ccedil;&atilde;o</span>
									<span class="info-box-number" data-ng-bind="sumary.alarm3.value"></span>
									<div class="progress">
										<div class="progress-bar" ng-style="{'width' : sumary.alarm3.percent + '%' }"></div>
									</div>
									<span class="progress-description">
										Alarme N&iacute;vel III
									</span>
								</div>
							</div>						
						</div>
							
						<div class="col-lg-2 col-xs-6">
							<div class="info-box info-box-min bg-black bg-blackness">
								<span class="info-box-icon info-box-icon-min cursor" data-ng-class="{'off': selectedStatusDashCompaniesPosition == 'OFFLINE' }" data-ng-click="filterStatus('OFFLINE')"><i class="fa fa-battery-empty"></i></span>
								<div class="info-box-content info-box-content-min">
									<span class="info-box-text">Off Line</span>
									<span class="info-box-number" data-ng-bind="sumary.offLine.value"></span>
									<div class="progress">
											<div class="progress-bar" ng-style="{'width' : sumary.offLine.percent + '%' }"></div>
									</div>
									<span class="progress-description">
										Sem Comunica&ccedil;&atilde;o
									</span>
								</div>
							</div>						
						</div>							
					</div>
					<!-- <div class="row">														
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
									<h3 data-ng-bind="sumary.normal.value"></h3>
									<p>Operacional</p>
								</div>
								<div class="icon"><i class="fa fa-check"></i></div>
								<a href="#" data-ng-class="{'normal': selectedStatusDashCompaniesPosition == 'NORMAL' }" data-ng-click="filterStatus('NORMAL')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
							
						<div class="col-lg-2 col-xs-6">
							<div class="small-box bg-gray">
								<div class="inner">
									<h3 data-ng-bind="sumary.alarm1.value"></h3>
									<p>Detec&ccedil;&atilde;o</p>
								</div>
								<div class="icon"><i class="fa fa-battery-quarter"></i></div>
								<a href="#" data-ng-class="{'deteccao': selectedStatusDashCompaniesPosition == 'DETECCAO' }" data-ng-click="filterStatus('DETECCAO')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
							
						<div class="col-lg-2 col-xs-6">					
							<div class="small-box bg-yellow">
								<div class="inner">
									<h3 data-ng-bind="sumary.alarm2.value"></h3>
									<p>Alerta</p>
								</div>
								<div class="icon"><i class="fa fa-battery-half"></i></div>
								<a href="#" data-ng-class="{'alerta': selectedStatusDashCompaniesPosition == 'ALERTA' }" data-ng-click="filterStatus('ALERTA')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
							
						<div class="col-lg-2 col-xs-6">
							<div class="small-box bg-red">
								<div class="inner">
									<h3 data-ng-bind="sumary.alarm3.value"></h3>
									<p>Evacua&ccedil;&atilde;o</p>
								</div>
								<div class="icon"><i class="fa fa-battery-full"></i></div>
								<a href="#" data-ng-class="{'evacuacao': selectedStatusDashCompaniesPosition == 'EVACUACAO' }" data-ng-click="filterStatus('EVACUACAO')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
							
						<div class="col-lg-2 col-xs-6">	
							<div class="small-box bg-black">
								<div class="inner">
									<h3 data-ng-bind="sumary.offLine.value"></h3>
									<p>Off Line</p>
								</div>
								<div class="icon" style="color:white !important"><i class="fa fa-battery-empty"></i></div>
								<a href="#" data-ng-class="{'off': selectedStatusDashCompaniesPosition == 'OFFLINE' }" data-ng-click="filterStatus('OFFLINE')" class="small-box-footer">Selecione <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>							
					</div> -->
					<br>
					<div class="row">
						<div class="col-md-8">
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
											<table class="table table-bordered" style="font-size:95%;">
												<thead>
													<tr>														
														<th style="text-align:center; vertical-align: middle;">&nbsp;&nbsp;<i class="fa fa-tags"></i></i>&nbsp;&nbsp;</th>
														<th>
															<span style="font-size: 1.1em !important; text-decoration: none !important" data-ng-click="toggleQuestao('companyName')">
																<i class="fa fa-sort-alpha-asc cursor" aria-hidden="true"
																	data-ng-class="{'fa-sort-alpha-asc': row == 'ASC', 'fa-sort-alpha-desc': row == 'DESC', 'text-gray': orderOptions != 'companyName' && orderOptions != '-companyName'}"></i>
															</span>Empresa
														</th>
														<th>
															<span style="font-size: 1.1em !important; text-decoration: none !important" data-ng-click="toggleQuestao('companyDeviceName')">
																<i class="fa fa-sort-alpha-asc cursor" aria-hidden="true"
																	data-ng-class="{'fa-sort-alpha-asc': row == 'ASC', 'fa-sort-alpha-desc': row == 'DESC', 'text-gray': orderOptions != 'companyDeviceName' && orderOptions != '-companyDeviceName'}"></i>
															</span>Device
														</th>						
														<th>
															<span style="font-size: 1.1em !important; text-decoration: none !important" data-ng-click="toggleQuestao('deviceType.type')">
																<i class="fa fa-sort-alpha-asc cursor" aria-hidden="true"
																	data-ng-class="{'fa-sort-alpha-asc': row == 'ASC', 'fa-sort-alpha-desc': row == 'DESC', 'text-gray': orderOptions != 'deviceType.type' && orderOptions != '-deviceType.type'}"></i>
															</span>
															Artefato
														</th>
														<th>Status</th>
														<th>Comunica&ccedil;&atilde;o</th>
														<th>Medi&ccedil;&atilde;o</th>
														<th style="text-align: center; font-size:1.1em; vertical-align: middle;"><i class="fa fa-bar-chart"></i></th>
													</tr>
												</thead>
												<tbody>
													<tr data-ng-repeat="item in dashCompaniesPositionFiltered = (dashCompaniesPosition | dashCompaniesPositionFilter: selectedStatusDashCompaniesPosition) | orderBy: orderOptions">
														<td style="text-align:center; vertical-align: middle; color: #575757;">&nbsp;&nbsp;<i class="fa" data-ng-class="item.deviceType.symbol" ></i>&nbsp;&nbsp;</td>
														<td><span data-truncate="12" data-value="{{item.companyName}}"></span></td>
														<td>{{item.companyDeviceName}}</td>
														<td>
															<jsp:include page="controls/reduzMeters.jsp"/>
														</td>													
														<td style="text-shadow:  0px 20px 20px #999;vertical-align: middle;"> 
															<span class="label" style="font-size:65%;" data-ng-class="{																	
																'label-primary' : item.alarmType=='WITHOUT', 
																'label-info' : item.alarmType=='OFF',
																'label-offline' : item.alarmType=='OFFLINE',
																'label-success' : item.alarmType=='NORMAL', 
																'label-warning' : item.alarmType=='ALERTA', 
																'label-default' : item.alarmType=='DETECCAO', 
																'label-danger' : item.alarmType=='EVACUACAO'}">
																<span data-ng-if="item.alarmType=='WITHOUT'">S/ ALARME</span>
																<span data-ng-if="item.alarmType=='OFF'">ALARME OFF</span>
																<span data-ng-if="item.alarmType!='WITHOUT' && item.alarmType!='OFF'">{{item.alarmType}}</span>																  
															</span>
														</td>
														<td><label title="{{item.lastUpdateFull | date:'dd/MM/yyyy HH:mm'}}">&agrave; {{item.lastUpdate}}</label></td>
														<td>
															<label data-ng-if="item.alarmType=='OFFLINE'">-</label>
															<label data-ng-if="item.alarmType!='OFFLINE'">{{item.lastValue}}</label>
														</td>
														<td class="col-md-2"><div class="sparkbar">{{item.arrayValues}}</div></td>
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
								<div class="box box-primary">
									<div class="box-header with-border">
										<label><strong>Gr&aacute;fico Consolidado</strong></label>
										<div class="box-tools pull-right">
											<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
											<button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
										</div>
									</div>
									<div class="box-body" style="text-align: -webkit-center;">
										<div>
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