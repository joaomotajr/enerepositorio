<style>
	.zui-table {
		border: solid 1px #DDEEEE;
		border-collapse: collapse;
		border-spacing: 0;
		font: normal 13px Arial, sans-serif;
	}
	.zui-table thead th {
		background-color: #DDEFEF;
		border: solid 1px #DDEEEE;
		color: #336B6B !important;
		padding: 10px;
		text-align: left;
		text-shadow: 1px 1px 1px #fff;
	}
	.zui-table tbody td {
		border: solid 1px #DDEEEE;
		color: #333;
		padding: 10px;
		text-shadow: 1px 1px 1px #fff;
	}
	
	.alarm1 {
		color: green;
		font-weight: 800;
	}
	
	.alarm2 {
		color: orange;
		font-weight: 800;
	}
	
	.alarm3 {
		color: red;
		font-weight: 800;
	}

</style>

<div data-ng-controller="logHistoricController">
		<div class="row">						                                                    
			<div class="col-md-12">                                                        
				<div class="box box-primary">
					
					<div class="box-header">
					 	<h3 class="box-title">Pesquisa Historico de Detectores</h3>
					</div>
					
					<div class="box-body">
						<div class="row">        		
			        		<div class="col-md-12">
				        		<div id="wizard" class="form_wizard wizard_horizontal">        		
					        		<ul class="wizard_steps anchor">
										<li>
											<a href="#step-1" class="disabled" data-ng-class="(selectedCompany) ? 'selected' : 'disabled'" rel="1">
												<span class="step_no">1</span>
												<span class="step_descr">Passo 1<br><small>Selecione Empresa</small></span>
										  	</a>
										</li>
										<li>
										  	<a href="#step-2" class="disabled" data-ng-class="(selectedCompanyDetector) ? 'selected' : 'disabled'" rel="2">
												<span class="step_no">2</span>
												<span class="step_descr">Passo 2<br><small>Selecione Detector</small></span>
										  	</a>
										</li>
										<li>
										  	<a href="#step-3" class="disabled" data-ng-class="(selectedCompanySensor) ? 'selected' : 'disabled'" rel="3">
												<span class="step_no">3</span>
												<span class="step_descr">Passo 3<br><small>Selecione Sensor/Gas</small></span>
										  	</a>
										</li>
										<li>
										  	<a href="#step-4" class="disabled" data-ng-click="showGrafico();" data-ng-class="(listHistoric.list || listHistoricInterval.list) ? 'selected' : 'disabled'" rel="4">
												<span class="step_no">4</span>
												<span class="step_descr">Passo 4<br><small>Escolha a Pesquisa</small></span>
										  	</a>
										</li>
									</ul>
				        		</div>
			        		</div>       	
			        	</div>
			        	
			        	<div class="row">      		           
				            <form class="form" name="userForm">
					            <div class="col-md-12">
					            	
					            	<div class="col-md-1">
					            	</div>
					            	 
					            	<div class="col-md-3">                                                                                                                            
										<div class="form-group">
											<label class="control-label">Empresa</label>
											
											<select class="form-control" data-live-search="true" 
					                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
					                                data-ng-options="item as item.name for item in companies | orderBy: 'name' track by item.uid" 
					                                         data-ng-model="selectedCompany" 
					                                         data-ng-change="changeCompany();">
					                                         <option value="">Selecione</option> 
					                        </select>																                                                                        
										</div>
									</div>
					            
						            <div class="col-md-3">                                                                                                                            
										<div class="form-group">
											<label class="control-label">Detector</label>
											
						                	<select class="form-control" data-live-search="true" 
					                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
					                                data-ng-options="item as item.companyDetectorName for item in CompanyDetectors | companyFilter:search | orderBy: 'name' track by item.companyDetectorId" 
					                                         data-ng-model="selectedCompanyDetector" 
					                                         data-ng-change="changeCompanyDetector();">
					                                         <option value="">Selecione</option> 
					                        </select>					                                                                                
											                                                                        
										</div>
									</div>		
																			
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label">Sensor</label>
											
						                	<select class="form-control" 
					                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
					                                data-ng-options="item as item.name for item in findedCompanyDetector.sensorsDto | orderBy: 'name' track by item.uid" 
					                                         data-ng-model="selectedCompanySensor"
					                                         data-ng-change="changeSensor();">
					                                         <option value="">Selecione</option> 
					                        </select>		                        						                                                
										</div>									                                                                                     
									</div>
			
					        		<div class="col-md-2">
										<div class="form-group">
											<label class="control-label">.</label>
					        				<button type="button" class="btn btn-primary btn-xs form-control" data-ng-click="clearHistoric()">Limpa Pesquisa</button>
					        			</div>
					        		</div>
				        		</div>	        		
				        	</form>				        	
				        	
			       		</div>
			       		
		       			<div class="row">       		
			       			<div class="col-md-12" style='font-size: 1.2em'>
			       				<div class="col-md-1">
			       				</div>
			       				<div class="col-md-3">
			       					<label class="text-muted">Range Alarmes do Sensor: </label>
			       				</div>
			       				<div class="col-md-2">
			       					<label data-ng-show="selectedCompanySensor">Range Max: </label><span data-ng-show="selectedCompanySensor"> {{selectedCompanySensor.rangeMax}}</span>
			       				</div>
			       				<div class="col-md-2">
			       					<label data-ng-show="selectedCompanySensor">Alarm 1: </label><span data-ng-show="selectedCompanySensor" class="alarm1"> {{selectedSensorAlarm.alarm1}}</span>
			       				</div>
			       				<div class="col-md-2">
			       					<label data-ng-show="selectedCompanySensor">Alarm 2: </label><span data-ng-show="selectedCompanySensor" class="alarm2"> {{selectedSensorAlarm.alarm2}}</span>
			       				</div>
			       				<div class="col-md-2">
			       					<label data-ng-show="selectedCompanySensor">Alarm 3: </label><span data-ng-show="selectedCompanySensor" class="alarm3"> {{selectedSensorAlarm.alarm3}}</span>
			       				</div>
			       			</div>       		
			       		</div>	
			       
 			       		<br />   
			       		
			       		<div class="row">						                                                    
							<div class="col-md-6">                                                        
								<div class="box box-primary">
									<div class="box-header">
									  <h3 class="box-title">Selecione Intervalo</h3>
									  
									  <div class="pull-right" style="margin-bottom: 0px ! important">                                        
		                                    <label><span class="icon fa fa-reorder"></span> FILTRAR &nbsp;</label>
												<select data-ng-options="item as item.name for item in filterAlarm | orderBy: 'name' track by item.uid" 
						                                data-ng-model="selectedfilterAlarm">
						                                <option value="">Selecione</option> 
						                        </select>               
		                                </div>									  
									  
									</div>
									<div class="box-body">	
										<div class="row">
										 	<form class="form" name="userForm">
												<div class="col-md-4">
													<div class="form-group">
														<label class="control-label">Data inicio</label>									                	 
									                	 <input type="text" class="form-control" data-date-format="dd/mm/yyyy" data-datemonopicker data-ng-model="dateIn" data-mask="99/99/9999" mask/>									                					                			                                                
													</div>                                                                    
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="control-label">Data Fim</label>				                	
									                	<input type="text" class="form-control" data-date-format="dd/mm/yyyy" data-datemonopicker data-ng-model="dateOut" data-mask="99/99/9999" mask/>				                							                                                
													</div>                                                                    
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="control-label">Clique</label>
								        				<button type="button" class="btn btn-primary btn-sm form-control" data-ng-click="getHistoricInterval()" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">Buscar Historico</button>
								        			</div>
								        		</div>								        		 
											</form>
										</div>    
										
										<div class="row">        		
							        		<div class="col-md-12">
							        			<div style="max-height:400px; height:auto; overflow: auto">	                
									                <table class='zui-table' cellspacing="0" width="100%" data-ng-visible="listHistoric">					            				                            
									                	<thead>
									                    	<tr>	                
									                    		<th>Sensor</th>                                                                                   
									                      		<th>Data / Hora</th>	                      		
									                      		<th>Valor</th>			                                                                                                                            
									                     	</tr>
									                    </thead>
									                    <tbody>                                    
									                     	<tr data-ng-repeat="item in listHistoricInterval.list | alarmFilter:selectedfilterAlarm">
									                     		<td>{{item.sensorDto.uid}} / {{item.sensorDto.name}} </td>	                               
									                      		<td>{{item.lastUpdate | date:'dd/MM/yyyy HH:mm' }}</td>

																<td> {{item.value}}									                      		
									                      			<span data-ng-if="item.value >= selectedSensorAlarm.alarm1 && item.value < selectedSensorAlarm.alarm2" class="label label-success pull-right">1</span>									                      		
									                      			<span data-ng-if="item.value >= selectedSensorAlarm.alarm2 && item.value < selectedSensorAlarm.alarm3" class="label label-warning pull-right">2</span>									                      											                      		
									                      			<span data-ng-if="item.value >= selectedSensorAlarm.alarm3" class="label label-danger pull-right">3</span>
									                      		</td>
									                     	</tr>
									                    </tbody>
									            	</table>								            	
									            	
									            	<p data-ng-hide="listHistoricInterval == undefined || listHistoricInterval.list.length > 0" class="text-center">NENHUM REGISTRO</p>
								            	</div>                                                         	            
							        		</div>							        		
							        	</div>
										                                               
									</div>
								</div>				
							</div>                                                      
																				
							<div class="col-sm-6">
								<div class="box box-primary">
									<div class="box-header">
										<h3 class="box-title">Clique em um Intevalo Pré-Definido</h3>
									</div>
									<div class="box-body">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label class="control-label">Intervalos: </label> <br />								
													<div class="btn-group" role="group" aria-label="Basic example">
													  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(1);"   data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 hora</button>
													  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(6);"   data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  6h  </button>
													  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(12);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  12h </button>
													  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(24);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 dia </button>
													  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(48);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  2d  </button>
													  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(96);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  4d  </button>
													  <button type="button" class="btn btn-default" data-ng-click="getLastMonth2();" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true"> 30d  </button>
													</div>
												</div>
											</div>
										</div>
										
										<div class="row">        		
							        		<div class="col-md-12">
							        			<div style="max-height:400px; height:auto; overflow: auto">	                
									                <table class='zui-table' cellspacing="0" width="100%" data-ng-visible="listHistoric">					            				                            
									                	<thead>
									                    	<tr>	                
									                    		<th>Sensor</th>                                                                                   
									                      		<th>Data / Hora</th>	                      		
									                      		<th>Valor</th>			                                                                                                                            
									                     	</tr>
									                    </thead>
									                    <tbody>                                    
									                     	<tr data-ng-repeat="item in listHistoric.list">
									                     		<td>{{item.sensorDto.uid}} / {{item.sensorDto.name}} </td>	                               
									                      		<td>{{item.lastUpdate | date:'dd/MM/yyyy HH:mm' }}</td>
<!-- 									                      		<td>{{item.value}}</td>	 -->
									                      		
									                      		<td> {{item.value}}									                      		
									                      			<span data-ng-if="item.value >= selectedSensorAlarm.alarm1 && item.value < selectedSensorAlarm.alarm2" class="label label-success pull-right">1</span>									                      		
									                      			<span data-ng-if="item.value >= selectedSensorAlarm.alarm2 && item.value < selectedSensorAlarm.alarm3" class="label label-warning pull-right">2</span>									                      											                      		
									                      			<span data-ng-if="item.value >= selectedSensorAlarm.alarm3" class="label label-danger pull-right">3</span>
									                      		</td>
									                      				                                                        
									                     	</tr>
									                    </tbody>
									            	</table>								            	
									            	
									            	<p data-ng-hide="listHistoric == undefined || listHistoric.list.length > 0" class="text-center">NENHUM REGISTRO</p>
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
		
		<div id="modalGraficoHistorico" class="modal">                
			<div class="modal-dialog" role="document">
				<div class="modal-content">                            
					<div class="modal-body">
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center">Selecione Alarme para o Sensor: <strong> {{selectedSensor.name}} </strong> - Valor Máximo: <strong>{{selectedSensor.rangeMax}}</strong> </div>                                                                           
					  	</div>
				
						<div class="box">
							<div class="box-header">
							  <h3 class="box-title">Cadastro de Alarmes</h3>
							</div>
							<div class="box-body">
								<div style="max-width: 800px; overflow: auto" id="graficoHistorico"></div>                                                       
							</div>
						</div>				
				  	</div>
				  	<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>                                
				  	</div>
			  	</div>
			</div>		
		</div>	
		
			 
</div>