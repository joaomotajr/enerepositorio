<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
	
	td.details-control {		
		background: url("/assets/plugins/datatables/images/details_open.png") no-repeat center center;		
		cursor: pointer;
	}	

	tr.shown td.details-control {
		background: url("/assets/plugins/datatables/images/details_close.png") no-repeat center center;
	}	
	
	.selected {		
		font-weight: 800;	
	}
	
</style>

<div class="col-md-9">
	<div data-ng-controller="companyDetectorController">
		<div class="box box-primary">
			<div class="box-header with-border">			
				<strong style="font-size:1.4em"><i class='fa fa-rss'></i> {{selectedCompanyDevice.deviceType}} <span data-ng-show="selectedCompanyDetector.name">-</span> {{selectedCompanyDetector.name}}</strong>
				
				<div class="box-tools pull-right">  
					<div class="btn-group">
                		<button data-ng-if="alarmesFired[0] != null" type="button" class="btn btn-xs" title="{{alarmesFired[0]}}"
                		data-ng-class="{'bg-black' : alarmesFired[0]=='OFFLINE', 'bg-green' : alarmesFired[0]=='NORMAL', 'bg-orange' : alarmesFired[0]=='ALERTA', 'bg-gray' : alarmesFired[0]=='DETECCAO', 'bg-red' : alarmesFired[0]=='EVACUACAO'}">S1</button>
                   		<button data-ng-if="alarmesFired[1] != null" type="button" class="btn btn-xs" title="{{alarmesFired[1]}}"
                   		data-ng-class="{'bg-black' : alarmesFired[1]=='OFFLINE', 'bg-green' : alarmesFired[1]=='NORMAL', 'bg-orange' : alarmesFired[1]=='ALERTA', 'bg-gray' : alarmesFired[1]=='DETECCAO', 'bg-red' : alarmesFired[1]=='EVACUACAO'}">S2</button>                  
                 	</div>
                </div>				
			</div>		
				
			<div class="box-body">
							
				<div class="nav-tabs-custom">
					<ul class="nav nav-tabs tabDetector">
				       	<li class="active"><a href="#tabCompanyDetector_1" id="stepTabDetector_1" data-toggle="tab">Cadastro</a></li>
				       	<li><a href="#tabCompanyDetector_4" id="stepTabDetector_4" data-toggle="tab">Manuten&ccedil;&atilde;o/Instala&ccedil;&atilde;o</a></li>
				       	<li><a href="#tabCompanyDetector_2" id="stepTabDetector_2" data-toggle="tab">Configura&ccedil;&atilde;o</a></li>
				       	<li><a href="#tabCompanyDetector_3" id="stepTabDetector_3" data-toggle="tab">Gr&aacute;fico do Hist&oacute;rico</a></li>				       	
				    	<li data-ng-hide="selectedCompanyDetector" class="pull-right"><i title="[Nenhum Detector Associado ao Dispositivo]" class="fa fa-info-circle text-red"></i></li>				    	
				    </ul>
					
					<div class="tab-content">
				    	
				    	<div class="tab-pane active" id="tabCompanyDetector_1">
				    		<div class="row">
				    			<div class="col-md-12">
					    		<form name="userForm">					    		
									
						    		<div class="row">				    			
								        <div class="col-md-2">
								        	<div class="form-group">
								                <label class="control-label">C&oacute;digo</label>
								                <input class="form-control" placeholder="C&oacute;digo do Detector" data-ng-model="selectedCompanyDetector.uid" readonly>
								            </div>	
								        </div>
								        <div class="col-md-2">
								            <div class="form-group">								            
								                <label class="control-label">Identifica&ccedil;&atilde;o</label>
								                <span class="text-red" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Identifica&ccedil;&atilde;o Obrigatorio]</span>
									            <span class="text-red" data-ng-show="userForm.username.$error.maxlength">Tamanho M&aacute;ximo 8 caracteres</span>
								                <input data-disallow-spaces id="companyDetectorName" class="form-control" style="text-transform:uppercase" 
								                	placeholder="Identifica&ccedil;&atilde;o do Detector (Sem Espa&ccedil;os)" 
								                	data-ng-model="selectedCompanyDetector.name" data-ng-maxlength="8" name="username" 
								                	title="Identifica&ccedil;&atilde;o do Detector (Sem Espa&ccedil;os)"
								                required>
								            </div>
								        </div>
								        
								        <div class="col-md-2">
								        	<div class="form-group">
								                <label class="control-label">Nr. de S&eacute;rie</label>
								                <input class="form-control" placeholder="Nro de S&eacute;rie do Detector" data-ng-maxlength="24" 
								                data-ng-model="selectedCompanyDetector.serialNumber">
								            </div>	
								        </div>
										
								        <div class="col-md-6">
								            <div class="form-group">
								                <label class="control-label">Descri&ccedil;&atilde;o</label>
								                <input class="form-control" placeholder="Descri&ccedil;&atilde;o" data-ng-model="selectedCompanyDetector.description">
								            </div>
								        </div>
								    </div>
									
									<div class="row">
								        <div class="col-md-6">
								            <div class="form-group">
								                <label class="control-label">Local</label>
								                <input id="idUnitName" class="form-control" placeholder="Local" data-ng-model="selectedCompanyDetector.local">
								            </div>
								        </div>

										<div class="col-md-6">
											<label class="control-label">Identifica&ccedil;&atilde;o do Detector/Sensores</label>
											<div class="box box-primary collapsed-box">
												<div class="box-header with-border">
													<Label class="box-title">{{selectedCompanyDetector.detectorDto.name}}-{{selectedCompanyDetector.detectorDto.model}} Sensores: {{selectedCompanyDetector.detectorDto.sensorsDto.length}} </label>
													<div class="box-tools pull-right" title="Clique para mais detalhes">
														<button class="btn btn-box-tool" data-widget="collapse">
															<i class="fa fa-plus"></i>
														</button>
													</div>
													<!-- /.box-tools -->
												</div>
												<!-- /.box-header -->
												<div class="box-body">
													<table class="table table-bordered table-hover">
														<thead>
															<tr>					
																<th>ID</th>
																<th>Status</th>																
																<th>Nome</th>																																						
															</tr>
														</thead>
														<tbody>                                                        
															<tr data-ng-repeat="item in listOnePositionNoTimer.list">
																
																<td>{{item.uid}}</td>
																<td>{{item.alarmType}}</td>	
																<td>{{item.sensorDto.name}}</td>																						
															</tr>                                                               
														</tbody>
													</table>												
												
												</div>
												<!-- /.box-body -->
											</div>
											<!-- /.box -->
										</div>													        
								    </div>								    		    					
					       		</form>
					       		</div>
				    		</div>
				    		
				    		<div class="row">

				    			<jsp:include page="detectorsSensorsList.jsp"/>
				    			
				    		</div>				    					    	
				    	
				       		<div class="row">
				       			<div class="col-md-12">
				       				<button type="button" data-ng-click="saveCompanyDetector();" class="btn btn-primary pull-right" data-ng-disabled="(selectedCompanyDetector.name && selectedCompanyDetector.detectorDto.uid) ? false : true">&nbsp;Salvar&nbsp;</button>		       				
				       				<span class="pull-right">&nbsp;</span>
				       				<button type="button" data-ng-click="deleteCompanyDetector();" class="btn btn-danger pull-right" data-ng-disabled="(selectedCompanyDetector.uid) ? false : true">&nbsp;Excluir&nbsp;</button>								
								</div>
							</div>						
				    	         
				       	</div><!-- /.tab-pane -->
				       	 
				       	<div class="tab-pane" id="tabCompanyDetector_2">		     
				       		
				       		<div class="row">	 							   
				            	<div data-ng-repeat="sensor in selectedCompanyDetector.detectorDto.sensorsDto">
				            	  	<div class="col-md-4">
						              	<div class="panel panel-primary">						                

							                <div class="panel-heading">
										    	<h3 class="panel-title" style="text-align:center;"><i class="fa fa-rss"></i> Detector: {{selectedCompanyDetector.name}}</h3>										    								
										   	</div>									   					               	

							               	<div class="panel-body">
						               			<div class="row">								                	
											        <div style=" width: 100%; display: flex; justify-content: center; text-align: center;"													
														id="{{'gauge_companyDetector_' + selectedCompanyDetector.uid + '-sensor_' + sensor.uid}}">
													</div>												
						                		</div>	
						                		<div class="row">					                    				                    				                    
								                	
													<div class="description-block">																	
														<h5 class="description-header"><i class="fa fa-bolt"></i> {{sensor.name}}</h5>
														<span class="description-text">Range: Min|Max: {{sensor.rangeMin}} | {{sensor.rangeMax}}</span>
														<br>
														G&aacute;s: <strong class"text-navy">{{sensor.gasesDto[0].name}}</strong>																
														<span style="vertical-align:super;font-size:0.6em;color:orange" data-ng-if="sensor.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
														<span style="vertical-align:super;font-size:0.6em;color:orange" data-ng-if="sensor.unitMeterGases!='LEL_PERCENT'"> {{sensor.unitMeterGases}}</span>													
														
														<hr style="margin-top: 8px !important; margin-bottom: 8px !important;">							                                
														
														<a href="#" data-ng-click="configAlarm($index);" title="Troca/Selecionar Alarme"><i class="fa fa-bullhorn"></i> Alarme </a>																												
														<div data-ng-repeat="alarm in selectedCompanyDetectorAlarms">
															<div data-ng-if="alarm.sensorId == sensor.uid">
																<label class="text-red">{{alarm.alarmDto.name}}</label>
															</div>
															<div data-ng-if="alarm.sensorId != sensor.uid">
																<label class="text-red">Sem Alarme</label>
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
				       					       	 
				       	<div class="tab-pane" id="tabCompanyDetector_3">	   
			       			<div class="row">				            	
			            	  	<div class="col-md-12">
					              	<div class="panel panel-primary">						                
						                <div class="panel-heading">
									    	<h3 class="panel-title" style="text-align:center;">{{selectedCompanyDetector.name}}</h3>							
									   	</div>									   					               	
						               	<div class="panel-body">
						               		<input class="pull-right ng-pristine ng-untouched ng-valid" type="checkbox" style="margin-right: 30px" data-ng-model="changeGraphic">
					               			<label data-ng-show="changeGraphic" class="pull-right">Gr&aacute;fico Completo&nbsp;&nbsp;</label>
											<label data-ng-hide="changeGraphic" class="pull-right">Gr&aacute;fico Medi&ccedil;&otilde;es&nbsp;&nbsp;</label>
						               							               							               							               		
						               		<div data-ng-if="selectedCompanyDetector.detectorDto.sensorsDto.length == 1">
						               			<ul class="nav nav-tabs" id="myTab">
												 	<li class="active"><a data-target="sensor1" data-toggle="tab">{{selectedCompanyDetector.detectorDto.sensorsDto[0].name}}</a></li>												 	
												 												  	
												</ul>
									
												<div class="tab-content">
													<div class="tab-pane active" id="sensor1">
													  	<div class="row">													  		
									               			<div style="max-width: 800px; overflow-x: auto; overflow-y: hidden;" id="{{'line_companyDetector_' + selectedCompanyDetector.uid + '-sensor_' + selectedCompanyDetector.detectorDto.sensorsDto[0].uid}}"></div>									               			
									               		</div>
								               		</div>												  	
												</div>					               		
						               		</div>
						               		
						               		<div data-ng-if="selectedCompanyDetector.detectorDto.sensorsDto.length > 1">
						               			<ul class="nav nav-tabs" id="myTab">
												 	<li class="active"><a data-target="#sensor1" data-toggle="tab">{{selectedCompanyDetector.detectorDto.sensorsDto[0].name}}</a></li>
												  	<li><a data-target="#sensor2" data-toggle="tab">{{selectedCompanyDetector.detectorDto.sensorsDto[1].name}}</a></li>										  	
												</ul>
									
												<div class="tab-content">
												  	<div class="tab-pane active" id="sensor1">
												  		<div class="row">												  															  			
									               			<div style="max-width: 800px; overflow-x: auto; overflow-y: hidden;" id="{{'line_companyDetector_' + selectedCompanyDetector.uid + '-sensor_' + selectedCompanyDetector.detectorDto.sensorsDto[0].uid}}"></div>
									               		</div>
												  	</div>
												  	<div class="tab-pane" id="sensor2">
												  		<div class="row">
									               			<div style="max-width: 800px; overflow-x: auto; overflow-y: hidden;" id="{{'line_companyDetector_' + selectedCompanyDetector.uid + '-sensor_' + selectedCompanyDetector.detectorDto.sensorsDto[1].uid}}"></div>
									               		</div>
												  	</div>												  	
												</div>			
						               		</div>
						               								               		
					    				</div>
					    			</div>
					    		</div>
					    	</div>
				       	     
				     	</div><!-- /.tab-content -->	
				     	
				     	<div class="tab-pane" id="tabCompanyDetector_4">	   
			       			<div class="row">				            	
			            	  	<div class="col-md-12">
					              	<div class="panel panel-primary">						                
						                <div class="panel-heading">
									    	<h3 class="panel-title" style="text-align:center;">{{selectedCompanyDetector.name}}</h3>							
									   	</div>									   					               	
						               	<div class="panel-body">						               		
				    						<jsp:include page="companyDetectorMaintenanceForm.jsp"/>
											<hr>
											
											<div class="col-md-8">													
												<div>			
													<div class="box box-primary collapsed-box">
														<div class="box-header with-border">
															<Label class="box-title">Hist&oacute;rico de Manuten&ccedil;&atilde;o do Detector</label>
															<div class="box-tools pull-right" title="Clique para Epandir">
																<button class="btn btn-box-tool" data-widget="collapse">
																	<i class="fa fa-plus"></i>
																</button>
															</div>					
														</div>
														<!-- /.box-header -->
														<div class="box-body">											
															<jsp:include page="controls/companyDetectorMaintenanceList.jsp"/>
														</div>
													</div>
												</div>								               								               		
											</div>
												
					    				</div>
					    			</div>
					    		</div>
					    		
					    						    		
					    	</div>					    	
				       	     
				       	    <div class="row">
				       			<div class="col-md-12">
				       				<button type="button" data-ng-click="saveCompanyDetector();" class="btn btn-primary pull-right" data-ng-disabled="(selectedCompanyDetector.name && selectedCompanyDetector.detectorDto.uid) ? false : true">Salvar</button>		       				
				       				<span class="pull-right">&nbsp;</span>
				       				<button type="button" data-ng-click="deleteCompanyDetector();" class="btn btn-danger pull-right" data-ng-disabled="(selectedCompanyDetector.uid) ? false : true">Excluir</button>								
								</div>
							</div>
							
				     	</div><!-- /.tab-content -->
				     			     	
				   </div>			   						
				</div>
							
			</div>		
		</div>		
		
		<div id="modalAlarm" class="modal">                
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">                            
					<div class="modal-body">
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center">Selecione Alarme para o Sensor: <strong> {{selectedSensor.name}} </strong> - Valor M&aacute;ximo: <strong>{{selectedSensor.rangeMax}}</strong> </div>                                                                           
					  	</div>
				
						<div class="box">
							<div class="box-header">
							  <h3 class="box-title">Cadastro de Alarmes</h3>
							</div>
							<div class="box-body">
								
								<div class="alert alert-warning" role="alert" data-ng-show="msgErroAlarm" >
					           		<button type="button" class="close" ><span data-ng-click="msgErroAlarm='';">&times;</span></button>
					           		<strong>Alerta! </strong>{{msgErroAlarm}} 
					       		</div>
							
								<div style="height: 500px; overflow: auto">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Gas</th>                                                            
												<th>Alarme 1</th>
												<th>Alarme 2</th>
												<th>Alarme 3</th>
												<th>A&ccedil;&atilde;o</th>						
											</tr>
										</thead>
										<tbody>                                                        
											<tr data-ng-repeat="item in alarms | gasFilter:search">
												<td>{{item.name}}</td>
												<td>{{item.gasDto.name}}</td>															        
												<td>{{item.alarm1}}</td>
												<td>{{item.alarm2}}</td>
												<td>{{item.alarm3}}</td>
												<td>																										
													<div data-ng-if="item.uid == selectedAlarm.uid">
														<button type="button" class="btn btn-danger btn-xs" data-ng-click="removerAlarm(item.uid)">Remover</button>
													</div>
													<div data-ng-if="item.uid != selectedAlarm.uid">
														<button type="button" class="btn btn-primary btn-xs"  data-ng-click="selecionarAlarm(item.uid, $index)">Selecionar</button>
													</div>
													
												</td>																		
											</tr>        
											                                                       
										</tbody>
									</table>
									<p data-ng-hide="alarms.length > 0 " class="text-center">NENHUM ALARME ATENDE ESTE DETECTOR.</p>
								</div>                                                       
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
</div>