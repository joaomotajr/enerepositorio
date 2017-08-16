<style>
	.selectedSquare {
		text-decoration: none !important; 
		color: black !important;
		background: #d2d6de !important;
		background-color: #d2d6de !important;
	}

</style>
<div data-ng-controller="generalController" >
												
	<div class="row">				                                                    
		<div class="col-md-12">                                                        
			
			<div class="box box-primary">
				
				<div class="box-header content-header" style="background: whitesmoke">
					
					<h3 class="box-title">Vis&atilde;o Empresas</h3>					  

					<ol class="breadcrumb" style="top: 5px !important;">
						<li><a href="#"><i class="fa fa-industry"></i> {{selectedCompany.companyName}}</a></li>
						<li data-ng-show="selectedUnit"><a href="#"><i class="fa fa-building"></i> {{selectedUnit.name}}</a></li>
						<li data-ng-show="selectedArea"><a href="#"><i class="fa fa-map-o"></i> {{selectedArea.name}}</a></li>						
					</ol>
					
				</div>

				<div class="box-body" style="background: whitesmoke">
					<!-- Selected Companies -->
					<div class="row">
						<div class="col-md-3 col-sm-6 col-xs-12" data-ng-repeat="item in companiesSumary" >
							<a href="#" style="text-decoration: none !important; color: black !important;" data-ng-click="selectCompany($index)">
							<div class="info-box">
								<span class="info-box-icon bg-navy" 
									ng-class="{'selectedSquare': $index == selectedCompany.selected}"
									style="padding: 1px; text-transform: uppercase; font-size: 65px; font-weight: 700">{{item.companyName.substring(0,1)}}</i></span>
								<div class="info-box-content">										
									<span class="info-box-number" truncate="12" value="{{item.companyName}}" />
									<div class="col-md-9" style="padding-left: 5px !important;">
										<span class="info-box-text">Unidades:</span>
									</div>
									<div class="col-md-3">
										<span class="pull-right" zeros="2" value="{{item.units}}" />
									</div>
									<div class="col-md-9" style="padding-left: 5px !important;">
										<span class="info-box-text">Areas:</span>
									</div>
									<div class="col-md-3">
										<span class="pull-right" zeros="2" value="{{item.areas}}" />
									</div>		
									<div class="col-md-9" style="padding-left: 5px !important;">
										<span class="info-box-text">Dispositivos:</span>
									</div>
									<div class="col-md-3">
										<span class="pull-right" zeros="2" value="{{item.devices}}" />
									</div>
								</div>
							</div>
							</a>
						</div>
						<!-- Selected Companies Fim -->
					</div>	

					<!-- Selected Units -->
					<div class="row" data-ng-show="selectedUnits">							
						<div class="col-md-4 col-sm-8" data-ng-repeat="item in companyComplete">
							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title"><strong><i class="fa fa-building"></i>&nbsp;Unidade:</strong>&nbsp;{{item.name}}</h3>
									<div class="box-tools pull-right">
										<a href="#" class="btn btn-box-tool"><i class="fa fa-square-o" data-ng-click="selectUnit($index);" title="Expandir Unidade"></i></a>
									</div>
								</div>
								<div class="box-body">										
									<row>
										<div class="col-md-2" style="padding-left: 5px !important;">
											<strong >Endere&ccedil;o:</strong>
										</div>
										<div class="col-md-10">
											<span class=" pull-right">&nbsp;{{item.address}}</span>
										</div>										
									</row>
									<row>
										<div class="col-md-2" style="padding-left: 5px !important;">
											<strong >Cidade:</strong>
										</div>
										<div class="col-md-10">
											<span class=" pull-right">&nbsp;{{item.city}}</span>
										</div>										
									</row>
									<row>
										<div class="col-md-2" style="padding-left: 5px !important;">
											<strong>Estado:</strong>
										</div>
										<div class="col-md-10">
											<span class="pull-right">&nbsp;{{item.state}}</span>
										</div>										
									</row>
									<row>
										<div class="col-md-2" style="padding-left: 5px !important;">
											<strong>Areas:</strong>
										</div>
										<div class="col-md-10">
											<span class="badge bg-black pull-right">&nbsp;{{item.areasDto.length}}</span>
										</div>										
									</row>									
								</div>
							</div>
						</div>
					</div>
					<!-- Selected Units Fim -->
					
					<!-- Selected Unit e Areas -->
					<div class="row">
						<div class="col-md-12" data-ng-show="selectedUnit">
							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title"><strong><i class="fa fa-building"></i>&nbsp;{{selectedUnit.name}} / &Aacute;reas:</strong></h3>
									<div class="box-tools pull-right">
										<a href="#" class="btn btn-box-tool"><i class="fa fa-times" data-ng-click="closeSelectedUnit();" title="Fechar Unidades"></i></a>
									</div>
								</div>
								
								<div class="box-body">									
									<div class="row" data-ng-show="selectedAreas">							
										<div class="col-md-4 col-sm-8" data-ng-repeat="item in selectedUnit.areasDto">
											<div class="box box-default box-solid">
												<div class="box-header with-border">
													<h3 class="box-title"><strong><i class="fa fa-map-o"></i>&nbsp;&Aacute;rea:</strong>&nbsp;{{item.name}}</h3>
													<div class="box-tools pull-right">
														<a href="#" class="btn btn-box-tool"><i class="fa fa-square-o" title="Expandir Area" data-ng-click="selectArea($index);"></i></a>
													</div>
												</div>
												<div class="box-body">																			
													<row>
														<div class="col-md-4" style="padding-left: 5px !important;">
															<strong>Dispositivos:</strong>
														</div>
														<div class="col-md-8">
															<span class="badge bg-black pull-right">&nbsp;{{item.companyDevicesDto.length}}</span>
														</div>										
													</row>									
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12" data-ng-show="selectedArea">
											<div class="box box-default box-solid">
												<div class="box-header with-border">
													<h3 class="box-title"><strong><i class="fa fa-building"></i>&nbsp;{{selectedArea.name}} / Dispositivos:</strong></h3>
													<div class="box-tools pull-right">
														<a href="#" class="btn btn-box-tool"><i class="fa fa-times" data-ng-click="closeSelectedArea();" title="Fechar Area"></i></a>
													</div>
												</div>
												<div class="box-body">
												</div>

												<div class="row">			       		 
													<div ng-repeat="item in selectedArea">				              					            						              	
														
														<div class="col-md-6">
															<div class="panel panel-primary">								                
																<div class="panel-heading">
																	<h2 class="panel-title" style="text-align:center;">{{item.companyDetectorName}}</h2>							
																</div>											   					               	
																<div class="panel-body">							            					                 										                	
																	<div class="row">								                	
																		<div style=" width: 100%; display: flex; justify-content: center; text-align: center;">
																			<!-- <div id="{{'sensor_' + item.$$hashKey}}" style="height:370px;"></div> -->
																			
																			<div data-fusioncharts							
																				data-width="400"
																				data-height= "300"						    						    						    						    
																				data-type="angulargauge"						    
																				data-theme= "fint"
																				data-datasource="{{item.dataSource}}">
																			</div>

																		</div>
																		
																		
																	</div>								                				                				                
																	<div class="row">							                    				                    				                    
																		<div >									                	
																			<div class="description-block">																
																				<h4 class="description-header"><i class="fa fa-bolt"></i> {{item.sensorName}}</h4>
																				<span class="description-text">Range: Min|Man: {{item.rangeMin}} | {{item.rangeMax}} </span>
																				<br>
																				G&aacute;s: <strong class"text-navy">{{item.gasName}}</strong>																
																				<span style="vertical-align:super;font-size:0.6em;color:orange" data-ng-if="item.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
																				<span style="vertical-align:super;font-size:0.6em;color:orange" data-ng-if="item.unitMeterGases!='LEL_PERCENT'"> {{item.unitMeterGases}}</span>
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
									
								</div>									
							</div>
						</div>
					</div>					
					<!-- Selected Unit e Areas Fim -->	
				</div>
<!-- 				
				<div class="box-footer">						                                                                
					<button type="button" data-ng-click="" class="btn btn-primary pull-right">Novo</button>
				</div>				 -->
																	
			</div>
		</div>
	</div>				
			
</div>