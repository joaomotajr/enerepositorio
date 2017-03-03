 <style>
	.todo-list>li {
	    padding: 4px;
	}
	
	img {
	  *	float: left;
	  *	width: 150px;
	  *	height: 100px;
	  *	border: 1px solid #000;
	  *	margin-right: 1em;
	   	width: auto;
    	max-height: 160px;
    	height: 160px;
    	background-size: cover;
    	background-position: 50% 50%;
	}
	
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
				
<!-- 				 	<div class="box-tools pull-right">  				 			 -->
<!-- 						<span data-ng-if="alarmesFired[0]=='NORMAL' && alarmesFired[1]=='NORMAL' " class="text-success"> NORMAL </span>									                      		 -->
<!--                 		<span data-ng-if="alarmesFired[0]=='ALERTA'" class="text-warning"> ALERTA </span>									                      		 -->
<!--                 		<span data-ng-if="alarmesFired[0]=='DETECCAO'" class="text-muted"> DETECÇÃO </span>									                      											                      		 -->
<!--                 		<span data-ng-if="alarmesFired[0]=='EVACUACAO'" class="text-danger"> EVACUAÇÃO </span>  											             -->
<!--                     </div> -->
				
			</div>		
			<div class="box-body">
							
				<div class="nav-tabs-custom">
					<ul class="nav nav-tabs tabDetector">
				       	<li class="active"><a href="#tabCompanyDetector_1" id="stepTabDetector_1" data-toggle="tab">Cadastro</a></li>
				       	<li><a href="#tabCompanyDetector_2" id="stepTabDetector_2" data-toggle="tab">Configuração</a></li>
				       	<li><a href="#tabCompanyDetector_3" id="stepTabDetector_3" data-toggle="tab">Histórico</a></li>				       	
				    	<li data-ng-hide="selectedCompanyDetector" class="pull-right"><i title="[Nenhum Detector Associado ao Dispositivo]" class="fa fa-info-circle text-red"></i></li>
				    </ul>
					
					<div class="tab-content">
				    	
				    	<div class="tab-pane active" id="tabCompanyDetector_1">
				    		<div class="row">
				    			<div class="col-md-12">
					    		<form name="userForm">					    		
									
						    		<div class="row">				    			
								        <div class="col-md-4">
								        	<div class="form-group">
								                <label class="control-label">Código</label>
								                <input class="form-control" placeholder="Código do Detector" data-ng-model="selectedCompanyDetector.uid" readonly>
								            </div>	
								        </div>
								        <div class="col-md-4">
								            <div class="form-group">								            
								                <label class="control-label">Identificação</label>
								                <span class="text-red" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Identificação Obrigatorio]</span>
									            <span class="text-red" data-ng-show="userForm.username.$error.maxlength">Tamanho Máximo 8 caracteres</span>
								                <input data-disallow-spaces id="companyDetectorName" class="form-control" style="text-transform:uppercase" 
								                	placeholder="Identificação do Detector (Sem Espaços)" 
								                	data-ng-model="selectedCompanyDetector.name" data-ng-maxlength="8" name="username" 
								                	title="Identificação do Detector (Sem Espaços)"
								                required>
								            </div>
								        </div>
										
								        <div class="col-md-4">
								            <div class="form-group">
								                <label class="control-label">Descrição</label>
								                <input class="form-control" placeholder="Descrição" data-ng-model="selectedCompanyDetector.description">
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
											<label class="control-label">Identificação do Detector/Sensores</label>
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
				    			<div class="col-md-12">						    	
						    		<div class="box box-primary">
					                    <div class="box-header with-border"><strong>Lista de Detectores</strong></div>					                	 
					                    <div class="box-body">
					                    	<div class="col-md-8">
					                    		<div style="max-height: 300px; overflow: auto">					                    							                           
						                         	<table id="sensorDetails" class="display">
														<thead>
															<tr>
																<th></th>
																<th>Nome</th>
																<th>Modelo</th>																
																<th>Ação</th>																																						
															</tr>
														</thead>
														<tbody>                                                        
															<tr data-ng-repeat="item in detectors">
																<td class="details-control"></td>
																<td data-ng-class="{'selected': item.uid == selectedCompanyDetector.detectorDto.uid }">{{item.name}}</td>
																<td data-ng-class="{'selected': item.uid == selectedCompanyDetector.detectorDto.uid }">{{item.model}}</td>																
																<td>																	
																	<div data-ng-if="item.uid == selectedCompanyDetector.detectorDto.uid">
																		<button type="button" class="btn btn-danger btn-xs" data-ng-click="selecionarDetector(item)" disabled>Selecionado</button>
																	</div>	
																	<div data-ng-if="item.uid != selectedCompanyDetector.detectorDto.uid">
																		<button type="button" class="btn btn-xs" data-ng-class="(selectedCompanyDetector.uid == null) ? 'btn-primary' : 'btn-default'" 
																		data-ng-click="selecionarDetector(item)" data-ng-disabled="selectedCompanyDetector.uid != null">  Selecionar  </button>
																	</div>																							    								
																</td>																		
															</tr>                                                               
														</tbody>
													</table>
												</div>    
					                    	</div>
					                    	
					                    	<div class="col-md-4">							    
											    <input type="file" id="idInputImageDetector" style='display:none'>									                						                    
							                    <img class="profile-user-img img-responsive" style="margin: 0 auto" 
							                    	data-ng-src="{{selectedCompanyDetector.detectorDto.image}}" onError="this.src='/assets/img/cover.jpg'">
							                    	
							                    <p class="text-muted text-center data-ng-binding">
							                    	{{selectedCompanyDetector.detectorDto.name}}
							                    	<span data-ng-show="selectedCompanyDetector.detectorDto.name"> - </span>
							                    	{{selectedCompanyDetector.detectorDto.model}}
							                    </p>
								                					                															
											</div>
											
					                    </div>
					                    
					                </div>							    	
						    	</div>
				    		</div>				    					    	
				    	
				       		<div class="row">
				       			<div class="col-md-12">
				       				<button type="button" data-ng-click="saveCompanyDetector();" class="btn btn-primary pull-right" data-ng-disabled="(selectedCompanyDetector.name && selectedCompanyDetector.detectorDto.uid) ? false : true">   Salvar   </button>		       				
				       				<span class="pull-right">   </span>
				       				<button type="button" data-ng-click="deleteCompanyDetector();" class="btn btn-danger pull-right" data-ng-disabled="(selectedCompanyDetector.uid) ? false : true">   Excluir   </button>								
								</div>
							</div>						
				    	         
				       	</div><!-- /.tab-pane -->
				       	 
				       	<div class="tab-pane" id="tabCompanyDetector_2">		     
				       		
				       		<div class="row">	    	
				            	<div data-ng-if="selectedCompanyDetector.detectorDto.sensorsDto.length > 1">
				            	  	<div class="col-md-6">
						              	<div class="panel panel-primary">						                
							                <div class="panel-heading">
										    	<h3 class="panel-title" style="text-align:center;">Detector: {{selectedCompanyDetector.name}}</h3>										    								
										   	</div>									   					               	
							               	<div class="panel-body">
						               			<div class="row">							            					                 		
								                	<div data-ng-repeat="subItem in selectedCompanyDetector.detectorDto.sensorsDto">				                				                
										            	<div class="col-md-6">						                
											            	<div style=" width: 100%;" id="{{'gauge_companyDetector_' + selectedCompanyDetector.uid + '-sensor_' + subItem.uid}}"></div>						            	    
										            	</div>				                
										            </div>							                				                				                
						                		</div>	
						                		<div class="row">					                    				                    				                    
								                	<div data-ng-repeat="subItem in selectedCompanyDetector.detectorDto.sensorsDto">				                    			                    
									                	<div class="col-md-6">
									                    	<div class="description-block">
									                      		<h5 class="description-header">{{subItem.name}}</h5>
									                      		<span class="description-text">Min|Max: {{subItem.rangeMin}} | {{subItem.rangeMax}}</span>
									                      		<h5>{{subItem.unitMeterGases}}</h5>
								                      			<h5>{{subItem.gasesDto[0].name}}</h5>									                      		
																<label>Alarme </label> <a href="#" data-ng-click="configAlarm($index);" title="Troca/Selecionar Alarme"><i class="fa fa-bullhorn"></i></a>
																
																<div data-ng-repeat="item in selectedCompanyDetectorAlarms">
																	<div data-ng-if="item.sensorId == subItem.uid">
																		<label class="text-red">{{item.alarmDto.name}}</label>
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
				              	
				              	<div data-ng-if="selectedCompanyDetector.detectorDto.sensorsDto.length == 1">
				            	  	<div class="col-md-3">
						              	<div class="panel panel-primary">						                
							                <div class="panel-heading">
										    	<h3 class="panel-title" style="text-align:center;">{{selectedCompanyDetector.name}}</h3>							
										   	</div>									   					               	
							               	<div class="panel-body">
							               		<div class="row">							            					                 		
								                	<div data-ng-repeat="subItem in selectedCompanyDetector.detectorDto.sensorsDto">											        	
											        	<div style="width: 100%;" id="{{'gauge_companyDetector_' + selectedCompanyDetector.uid + '-sensor_' + subItem.uid}}"></div>											        										            					                
										            </div>
									            </div>							                				                				                
							                	<div class="row">							                    				                    				                    
								                	<div data-ng-repeat="subItem in selectedCompanyDetector.detectorDto.sensorsDto">									                	
								                    	<div class="description-block">
								                      		<h5 class="description-header"> {{subItem.name}}</h5>
								                      		<span class="description-text">Min|Man: {{subItem.rangeMin}} | {{subItem.rangeMax}}</span>
								                      		<h5>{{subItem.unitMeterGases}}</h5>
								                      		<h5>{{subItem.gasesDto[0].name}}</h5>
								                      		<label>Alarmes </label> <a href="#" data-ng-click="configAlarm($index);"><i class="fa fa-bullhorn"></i></a>
								                      		
								                      		<label class="text-red">{{selectedCompanyDetectorAlarms[0].alarmDto.name}}</label>								                      		
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
				     			     	
				   </div>			   						
				</div>
							
			</div>		
		</div>		
		
		<div id="modalAlarm" class="modal">                
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
								
								<div class="alert alert-warning" role="alert" data-ng-show="msgErroAlarm" >
					           		<button type="button" class="close" ><span data-ng-click="msgErroAlarm='';">&times;</span></button>
					           		<strong>Alerta! </strong>{{msgErroAlarm}} 
					       		</div>
							
								<div style="height: 500px; overflow: auto" data-ngcloak>
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Gas</th>                                                            
												<th>Alarme 1</th>
												<th>Alarme 2</th>
												<th>Alarme 3</th>
												<th>Ação</th>						
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