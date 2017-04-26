<style>
#snoAlertBox{
    position:absolute;
    z-index:1400;
   	top:2%;
    right:4%;
    margin:0px auto;
	text-align:center;
    display:none;
}

</style>
<div data-ng-controller="logHistoricController">
		<div class="row">
		
			<div id="snoAlertBox" class="alert alert-warning" data-alert="alert">{{daysDiff}}</div>						                                                    
			<div class="col-md-12">                                                        
				<div class="box box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">	
					<div class="box-header">
					 	<h3 class="box-title">Pesquisa Historico de Detectores</h3>
					 	<div class="box-tools pull-right">
	                  		<button id="btnSelDevice" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>					                    
	                  	</div>
					</div>
					
					<div class="box-body" style="padding-bottom: 0px; !important">
						<div class="row">        		
			        		<div class="col-md-12">
				        		<div id="wizard" class="form_wizard wizard_horizontal">        		
					        		<ul class="wizard_steps anchor">
										<li>
											<a href="#step-1" class="disabled" data-ng-class="(selectedCompany) ? 'selected' : 'disabled'" rel="1">
												<span class="step_no">1</span>
												<span class="step_descr">Selecione Empresa</span>
										  	</a>
										</li>
										<li>
										  	<a href="#step-2" class="disabled" data-ng-class="(selectedCompanyDetector) ? 'selected' : 'disabled'" rel="2">
												<span class="step_no">2</span>
												<span class="step_descr">Selecione Detector</span>
										  	</a>
										</li>
										<li>
										  	<a href="#step-3" class="disabled" data-ng-class="(selectedCompanySensor) ? 'selected' : 'disabled'" rel="3">
												<span class="step_no">3</span>
												<span class="step_descr">Selecione Sensor/Gas</span>
										  	</a>
										</li>
										<li>
										  	<a href="#step-4" class="disabled" data-ng-class="(listHistoric.list || listHistoricInterval.list) ? 'selected' : 'disabled'" rel="4">
												<span class="step_no">4</span>
												<span class="step_descr">Pesquisar / Limpar Pesquisa</span>
										  	</a>
										</li>
									</ul>
				        		</div>
			        		</div>       	
			        	</div>
			        	
			        	<div class="row">      		           
				            <form class="form" name="userForm">
					            <div class="col-md-12">
					            	 
					            	<div class="col-md-3">                                                                                                                            
										<div class="form-group">
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
											
						                	<select class="form-control" data-live-search="true" 
					                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
					                                data-ng-options="item as item.companyDetectorName for item in companyDetectors | companyFilter:search | orderBy: 'companyDetectorName' track by item.companyDetectorId" 
					                                         data-ng-model="selectedCompanyDetector" 
					                                         data-ng-change="changeCompanyDetector();">
					                                         <option value="">Selecione</option> 
					                        </select>					                                                                                
											                                                                        
										</div>
									</div>		
																			
									<div class="col-md-3">
										<div class="form-group">
											
						                	<select class="form-control" 
					                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
					                                data-ng-options="item as item.name for item in findedCompanyDetector.sensorsDto | orderBy: 'name' track by item.uid" 
					                                         data-ng-model="selectedCompanySensor"
					                                         data-ng-change="changeSensor();">
					                                         <option value="">Selecione</option> 
					                        </select>		                        						                                                
										</div>									                                                                                     
									</div>
			
					        		<div class="col-md-3">
										<div class="form-group">
					        				<button type="button" class="btn btn-default btn-xs form-control" data-ng-click="clearHistoric()">Limpar Pesquisa</button>
					        			</div>
					        		</div>
				        		</div>	        		
				        	</form>				        	
				        	
			       		</div>
			       		
			       	</div>
				</div>	
				
				<div class="row">						                                                    
					<div class="col-md-12">                                                        
						<div class="box box-solid box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
							
							<div class="box-header">
								<h3 class="box-title">Selecione Intervalos Pré-Definidos ou Data de Inicio e Fim</h3>									  	
		                         
                                <div class="pull-right" style="margin-bottom: 0px ! important">   
                                	<span data-ng-hide='loading' class="icon fa fa-search fa-2.0x pull-right"></span>
			        				<label data-ng-show='loading'><span class="icon fa fa-hourglass-half"></span> Loading...</label>
								</div>
							</div>			
							
							<div class="box-body" style="padding-bottom: 0px; !important">	
								<div class="row">
								
									<div class="col-md-4">																															
										<div class="col-md-3" style="padding-right: 2px !important;">	
											<label style="margin-top: 5px !important;" title="Mostrando Máximo e Minimo"><span class="icon fa fa-object-group"></span> Agrupar</label>
										</div>
										<div class="col-md-9" style="background:gray; border-color:lightgray; border-radius: 2px 2px 2px 2px; color:white">
	                                         <div class="radio3 radio-check radio-success radio-inline">
	                                             <input type="radio" id="radio5" value="1" data-ng-model="tipoGrupo" data-ng-change="clearTipoGrupo(1)">
	                                             <label for="radio5">Nenhum</label>
	                                         </div>
	                                         <div class="radio3 radio-check radio-warning radio-inline" title="NOTE: Se houve mais de um tipo de alarme na HORA, apenas o maior será Exibido">
	                                             <input type="radio" id="radio6" value="2" data-ng-model="tipoGrupo" data-ng-change="clearTipoGrupo(2)">
	                                             <label for="radio6">Hora</label>
	                                         </div>
	                                         <div class="radio3 radio-check radio-default radio-inline" title="NOTE: Se houve mais de um tipo de alarme no DIA, apenas o maior será Exibido">
	                                             <input type="radio" id="radio7" value="3" data-ng-model="tipoGrupo" data-ng-change="clearTipoGrupo(3)">
	                                             <label for="radio7">Dia</label>
	                                         </div>
                                         </div>                                        										
									</div>		
									
				        			<div class="col-md-3" >
										<form>												
											<div class="col-md-4" style="padding-right: 2px !important;">	 
												<label style="margin-top: 5px !important;" class="control-label"><span class="icon fa fa-filter"></span> FILTRAR</label>
											</div>
											<div class="col-md-8" style="padding-left: 2px !important;">
											<select class="form-control" style="width: 100%;" tabindex="-1" aria-hidden="true" data-ng-options="item as item.name for item in filterAlarm | orderBy: 'name' track by item.uid" 
						                    	data-ng-model="selectedfilterAlarm">
						                        <option value="">Selecione</option> 
						                	</select>
						                	</div>						        							    	        
									    </form>								
				        			</div>
				        			
				        			<div class="col-md-5" >									
										<div class="col-md-3">
											<label style="margin-top: 2px !important; padding-right: 5px !important;" title="Mostrando Máximo e Minimo"><span class="icon fa fa-caret"></span>Exibir/Exportar</label>
										</div>										        	
	                            		<div class="form-group">
                                              	<div class="col-md-3" style="padding-right: 5px !important;">
												<button id="exportRel" type="button" class="btn btn-default btn-xs form-control" 
												 data-ng-class="(listHistoric.list || listHistoricInterval.list) ? 'selected' : 'disabled'">
												<span class="icon fa fa-file-text"></span> Relatório</button>
					        				</div>
					        				<div class="col-md-3" style="padding-right: 5px !important; padding-left: 5px !important;">
												<button id="exportExcel" type="button" class="btn bg-olive btn-xs form-control" title="Exportação Permitida até 500 Linhas" 
												 data-ng-class="((listHistoric.list || listHistoricInterval.list) && countHistoric <= 500) ? 'selected' : 'disabled'">
												<span class="icon fa fa-file-excel-o"></span> Excel</button>
					        				</div>
					        				<div class="col-md-3" style="padding-right: 5px !important; padding-left: 5px !important;">
					        				<button type="button" class="btn bg-navy btn-xs form-control" 
					        					data-ng-click="showGrafico();" data-ng-class="(listHistoric.list || listHistoricInterval.list) ? 'selected' : 'disabled'">
					        					<span class="icon fa fa-line-chart"></span> Gráfico</button>
					        				</div>
					        			</div>			        				    								
				        			</div>				        			
								</div>
								
								<hr style="margin-top: 5px !important; margin-bottom: 5px !important;">
								
								<div class="row">
								 	<form class="form" name="userForm">						 		
								 		
								 		<div class="col-md-5">
							        		<div class="form-group">
												<label class="control-label">Buscar por Intervalos Pré-Definidos: </label> <br />								
												<div class="btn-group" role="group" aria-label="Basic example">
													  <button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 1}" data-ng-click="getHistorics(1);" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 hora</button>
													  <button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 6}" data-ng-click="getHistorics(6);" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  6h  </button>
													  <button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 12}" data-ng-click="getHistorics(12);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  12h </button>
													  <button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 24}" data-ng-click="getHistorics(24);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 dia </button>
													  <button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 48}" data-ng-click="getHistorics(48);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  2d  </button>
													  <button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 96}" data-ng-click="getHistorics(96);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  4d  </button>
													  <button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 30}" data-ng-click="getLastMonth();" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true"> 30d  </button>												  												  
												</div>
											</div>
										</div>
								 		
										<div class="col-md-3" style="padding-right: 5px !important;">
											<div class="form-group">
												<label class="control-label">Data inicio</label>									                	 

							                	 <div class='input-group date' id='dateIn'>
													<input type="text" class="form-control" data-ng-model="dateIn" name="dateIn">
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
													</span>
							                	</div>													                					                			                                                
											</div>                                                                    
										</div>
										
										<div class="col-md-3" style="padding-right: 5px !important; padding-left: 5px !important;">
											<div class="form-group">
												<label class="control-label">Data Fim</label>			                	
							                	
							                	<div class='input-group date' id='dateOut'>
													<input type="text" class="form-control" data-ng-model="dateOut" name="dateOut">
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
													</span>
							                	</div>	
							                					                							                                                
											</div>                                                                    
										</div>
										
										<div class="col-md-1">
											<div class="form-group">
												<label class="control-label"> </label>
						        				<button type="button" class="btn btn-default btn-sm form-control" data-ng-class="{'btn-primary': selectedButton == 100}" data-ng-click="getHistoricInterval()" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">Buscar</button>
						        			</div>
						        		</div>							
						        							        		 
									</form>
									
								</div>    
								
								<div class="row">
									<div id="printHeader" class="col-md-5">
										<div class="box box-primary">
					
											<div class="box-header">
											 	<h4 class="box-title">
											 	<strong>Dispositivo: </strong>  {{selectedCompanyDetector.companyDetectorName}} 
											 	<span data-ng-show="selectedCompanySensor"> - </span> {{selectedCompanySensor.name}}</h4>											 	
											</div>
											
											<div class="box-body">
												<div class="col-md-12">
													<div class="row" data-ng-show="selectedCompanySensor">
														<div class="box box-solid" style="font-size: 1.1em">
															
															<div class="box-header with-border">
																<i class="fa fa-bell-o"></i>
																<h3 class="box-title">Detalhes do Sensor:</h3>
											                </div>
											                
											                <div class="box-body" style="background-color: #e7e7e7">
											                												                	 
																<dl class="dl-horizontal">
																	<dt>Alarme:</dt>
																		<dd>{{selectedSensorAlarm.name}}</dd>
																	<dt>Unidade:</dt>
																		<dd>{{selectedSensorAlarm.unitMeterGases}}</dd>																		
																	<dt>Range Max:</dt>
																		<dd><strong>{{selectedCompanySensor.rangeMax}}</strong></dd>
																	<dt>Detecção:</dt>
																		<dd><span data-ng-show="selectedCompanySensor" class="alarm1"> {{selectedSensorAlarm.alarm1}}</span></dd>							
																	<dt>Alerta:</dt>
																		<dd><span data-ng-show="selectedCompanySensor" class="alarm2"> {{selectedSensorAlarm.alarm2}}</span></dd>
																	<dt>Evacuação:</dt>
																		<dd><span data-ng-show="selectedCompanySensor" class="alarm3"> {{selectedSensorAlarm.alarm3}}</span></dd>
											                  	</dl>											                	
											                	
											                </div>
											            </div>
													</div>												
					                                    
												</div>
											</div>
										</div>			
									
									</div>									        		
					        		<div class="col-md-7">					        		
					        			<div style="max-height:420px; height:auto; overflow: auto">
					        				               
							                <table data-ng-if="tipoGrupo==1" class='zui-table' cellspacing="0" width="100%" data-ng-visible="listHistoric">					            				                            
							                	<thead>
							                    	<tr>                                                                                 
							                      		<th>Data</th>
							                      		<th>Hora</th>	                      		
							                      		<th>Valor</th>
							                      		<th>Alarme Status</th>			                                                                                                                            
							                     	</tr>
							                    </thead>
							                    <tbody>                                    
							                     	<tr data-ng-repeat="item in listHistoricInterval.list | alarmFilter:selectedfilterAlarm">							                     		
							                      		<td>{{item.last_update | date:'dd/MM/yyyy' }}</td>
							                      		<td>{{item.last_update | date:'HH:mm:ss' }}</td>
														<td> {{item.value}} </td>
														<td>
															<span data-ng-if="item.value < selectedSensorAlarm.alarm1 && item.value < selectedSensorAlarm.alarm1" class="label label-success"> NORMAL </span>									                      		
							                      			<span data-ng-if="item.value >= selectedSensorAlarm.alarm1 && item.value < selectedSensorAlarm.alarm2" class="label label-default"> DETECÇÃO </span>									                      		
							                      			<span data-ng-if="item.value >= selectedSensorAlarm.alarm2 && item.value < selectedSensorAlarm.alarm3" class="label label-warning"> ALERTA </span>									                      											                      		
							                      			<span data-ng-if="item.value >= selectedSensorAlarm.alarm3" class="label label-danger"> EVACUAÇÃO </span>
							                      		</td>
							                     	</tr>
							                    </tbody>
							            	</table>								            	
							            	
							            	<table data-ng-if="tipoGrupo!=1" class='zui-table' cellspacing="0" width="100%" data-ng-visible="listHistoric">					            				                            
							                	<thead>
							                    	<tr>                                                                                   
							                      		<th>Data</th>
							                      		<th>Hora</th>	                      		
							                      		<th>Máximo</th>
							                      		<th>Minimo</th>
							                      		<th>Alarme Status</th>			                                                                                                                            
							                     	</tr>
							                    </thead>
							                    <tbody>                                    
							                     	<tr data-ng-repeat="item in listHistoricInterval.list | alarmFilter:selectedfilterAlarm">							                     		
							                      		<td>{{item.last_update | date:'dd/MM/yyyy' }}</td>
							                      		<td>{{item.last_update | date:'HH:mm:ss' }}</td>
														<td> {{item.max_value}} </td>
														<td> {{item.min_value}} </td>
														<td>
															<span data-ng-if="item.max_value < selectedSensorAlarm.alarm1 && item.max_value < selectedSensorAlarm.alarm1" class="label label-success"> NORMAL </span>									                      		
							                      			<span data-ng-if="item.max_value >= selectedSensorAlarm.alarm1 && item.max_value < selectedSensorAlarm.alarm2" class="label label-default"> DETECÇÃO </span>									                      		
							                      			<span data-ng-if="item.max_value >= selectedSensorAlarm.alarm2 && item.max_value < selectedSensorAlarm.alarm3" class="label label-warning"> ALERTA </span>									                      											                      		
							                      			<span data-ng-if="item.max_value >= selectedSensorAlarm.alarm3" class="label label-danger"> EVACUAÇÃO </span>
							                      		</td>
							                     	</tr>
							                    </tbody>
							            	</table>
							            	
							            	<p data-ng-hide="listHistoricInterval == undefined || listHistoricInterval.list.length > 0 || loading" class="text-center">NENHUM REGISTRO</p>
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
			<div class="modal-dialog  modal-lg" role="document">
				<div class="modal-content">                            
					<div class="modal-body">
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Gráfico do Período:</strong> {{selectedPeriodo}}</div>															                                                                           
					  	</div>
				
						<div class="box">
							<div class="box-header">
							  <h3 class="box-title"><strong>Gráfico do Sensor:</strong> {{selectedCompanySensor.name}} - Gás: {{selectedCompanySensor.gasesDto[0].name}} </h3>
							  	<input class="pull-right ng-pristine ng-untouched ng-valid" type="checkbox" style="margin-right: 30px" data-ng-model="changeGraphic" data-ng-change="showGrafico();">
								<label data-ng-show="changeGraphic" class="pull-right">Gráfico Completo&nbsp;&nbsp;</label>
								<label data-ng-hide="changeGraphic" class="pull-right">Gráfico Medições&nbsp;&nbsp;</label>
							</div>
							<div class="box-body">
								<div  id="graficoHistorico" style="max-width: 900px; overflow-x: auto; overflow-y: hidden;"></div>
							</div>
						</div>				
				  	</div>
				  	<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>                                
				  	</div>
			  	</div>
			</div>		
		</div>

		<div id="printTable" style="visibility:hidden" >
		
			<div class="col-md-12">
				<h1>Empresa: {{selectedCompanyDetector.company}}</h1>
				<h2>Unidade: {{selectedCompanyDetector.unit}} - Área: {{selectedCompanyDetector.area}} </h2>
				<hr>
				<h3 class="box-title"><strong>Dispositivo: </strong>  {{selectedCompanyDetector.companyDetectorName}} <span data-ng-show="selectedCompanySensor"> - </span> {{selectedCompanySensor.name}}</h3>
							
				<i class="fa fa-bell-o"></i> <h3 class="box-title">Detalhes do Sensor:</h3>                       
	              												                	 
				<dl class="dl-horizontal">
					<dt>Nome do Alarme / Unidade de Medida:</dt>
						<dd><strong>{{selectedSensorAlarm.name}} / {{selectedSensorAlarm.unitMeterGases}}</strong></dd>
					<dt>Range Max:</dt>
						<dd><strong>{{selectedCompanySensor.rangeMax}}</strong></dd>
					<dt>Detecção:</dt>
						<dd><span data-ng-show="selectedCompanySensor" class="alarm1"> {{selectedSensorAlarm.alarm1}}</span></dd>							
					<dt>Alerta:</dt>
						<dd><span data-ng-show="selectedCompanySensor" class="alarm2"> {{selectedSensorAlarm.alarm2}}</span></dd>
					<dt>Evacuação:</dt>
						<dd><span data-ng-show="selectedCompanySensor" class="alarm3"> {{selectedSensorAlarm.alarm3}}</span></dd>
	            </dl>		            
	            
	            <div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Dados do Período:</strong> {{selectedPeriodo}}</div>
	            
	            <br />
	            <div id="dvData">
		            <table class='zui-table' data-cellspacing="0" data-width="100%">	                   
	                   	<tr>	                
	                   			<th>ID</th>                                                                                   
	                     		<th>Data</th>
	                     		<th>Hora</th>                     	
	                     			                     		
	                     		<th data-ng-if="tipoGrupo==1">Valor</th>
	                     		<th data-ng-if="tipoGrupo!=1">Maximo</th>
	                     		<th data-ng-if="tipoGrupo!=1">Minimo</th>
	                     		
	                     		<th>Alarme Status</th>			                                                                                                                            
	                    	</tr>                                    
	                    	<tr data-ng-repeat="item in listHistoricInterval.list | alarmFilter:selectedfilterAlarm">
	                    		<td>{{item.sensor_id}} </td>
	                     		<td>{{item.last_update | date:'dd/MM/yyyy' }}</td>
	                     		<td>{{item.last_update | date:'HH:mm:ss' }}</td>
	
							<td data-ng-if="tipoGrupo==1"> {{changeToValue(item.value)}} </td>						
							<td data-ng-if="tipoGrupo!=1"> {{item.max_value}} </td>
							<td data-ng-if="tipoGrupo!=1"> {{item.min_value}} </td>
							
							<td data-ng-if="tipoGrupo==1">
								<span data-ng-if="item.value < selectedSensorAlarm.alarm1 && item.value < selectedSensorAlarm.alarm1" class="label label-success"> NORMAL </span>									                      		
	                     			<span data-ng-if="item.value >= selectedSensorAlarm.alarm1 && item.value < selectedSensorAlarm.alarm2" class="label label-default"> DETECCAO </span>									                      		
	                     			<span data-ng-if="item.value >= selectedSensorAlarm.alarm2 && item.value < selectedSensorAlarm.alarm3" class="label label-warning"> ALERTA </span>									                      											                      		
	                     			<span data-ng-if="item.value >= selectedSensorAlarm.alarm3" class="label label-danger"> EVACUACAO </span>
	                     	</td>
	                     	
	                     	<td data-ng-if="tipoGrupo!=1">
								<span data-ng-if="item.max_value < selectedSensorAlarm.alarm1 && item.max_value < selectedSensorAlarm.alarm1" class="label label-success"> NORMAL </span>									                      		
                      			<span data-ng-if="item.max_value >= selectedSensorAlarm.alarm1 && item.max_value < selectedSensorAlarm.alarm2" class="label label-default"> DETECCAO </span>									                      		
                      			<span data-ng-if="item.max_value >= selectedSensorAlarm.alarm2 && item.max_value < selectedSensorAlarm.alarm3" class="label label-warning"> ALERTA </span>									                      											                      		
                      			<span data-ng-if="item.max_value >= selectedSensorAlarm.alarm3" class="label label-danger"> EVACUACAO </span>
                      		</td>
	                    </tr>	                   
		           	</table>            	
				</div>	
			</div>
		</div>			
</div>