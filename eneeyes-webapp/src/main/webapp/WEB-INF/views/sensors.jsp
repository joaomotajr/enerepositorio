	<style>
		.todo-list>li {
		    padding: 4px;
		}
		
		.disableDiv {
			pointer-events: none;
			opacity: 0.6;
		}		
	</style>
	 
	<div data-ng-controller="sensorController">
												
		<div class="row">				                                                    
			<div class="col-md-12">                                                        
				<div class="box box-primary" data-ng-class="(sensorName || sensorModel || sensorManufacturer || sensorDetectionType) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de Sensores</h3>
					  <a href="#" class="text-muted pull-right" data-ng-click="refreshSensors();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Modelo</th>
										<th>Fabricante</th>
										<th>Gas</th>
										<th>Unidade</th>
										<th>Min</th>
										<th>Max</th>                                                               
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr data-ng-repeat="item in sensors">
										<td>{{item.name}}</td>
										<td>{{item.model}}</td>	
										<td>{{item.manufacturerDto.name}}</td>
										<!-- <td>{{item.gasesDto[0].name}}</td> -->
										<td>{{item.gasDto.name}}</td>
										<td>{{item.unitMeterGases}}</td>
										<td>{{item.rangeMin}}</td>
										<td>{{item.rangeMax}}</td>														        
										<td>
											<button type="button" class="btn btn-primary btn-xs" data-ng-click="editSensor($index)">editar</button>
										</td>
										<td>
											<a type="button" class="btn btn-danger btn-xs" data-popover=' do Sensor: [ {{item.name}} ]' data-confirm="deleteSensor($index)" >excluir</a>
										</td>						
									</tr>                                                               
								</tbody>
							</table>
						</div>                                                       
					</div>
					
					<div class="box-footer">						                                                                
						<button type="button" data-ng-click="clearFormSensor(); userForm.$setPristine()" class="btn btn-primary pull-right" 
						data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#modalEditSensor">Novo</button>
					</div>
				</div>
				
				<div class="alert alert-warning" role="alert" data-ng-show="msgErroSensor" >
	           		<button type="button" class="close" ><span data-ng-click="msgErroSensor='';">&times;</span></button>
	           		<strong>Alerta! </strong>{{msgErroSensor}} 
	       		</div>
	 	       		
			</div>                                                      
			
		</div>
		
		<div id="modalEditSensor" class="modal">                
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">                               
					<div class="modal-body" style="padding-bottom: 5px !important">
					
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Edição de Sensores</strong></div>							                                                                           
					  	</div>							
								
						<div class="box box-primary" data-ng-class="(sensorName || sensorModel || sensorManufacturer || sensorDetectionType) ? 'box-primary' : 'box-default'">
							
							<div class="box-header">
								<h3 class="box-title">Cadastro / Edição</h3>	
								<span class="text-muted pull-right" data-ng-click="refreshSensors();"><i title="Refresh" class="fa fa-pencil-square-o"></i></span>							
							</div>
							
							<div class="box-body">
								<form class="form" name="sensorForm">		
								
									<div class="row">
										<div class="col-md-6">    					                	 
						                    <div class="form-group">
						                    	<label class="control-label">Fabricante *</label> 
						                        <select class="form-control" data-live-search="true" 
						                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
						                                data-ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
				                                           data-ng-model="sensorManufacturer">
				                                           <option value="">Selecione</option> 
						                        </select>    
						                    </div>
						                </div>
						                <div class="col-md-6">
							                <div class="form-group">
									            <label class="control-label">Tipo de Detecção *</label>
												<select class="form-control" data-live-search="true" 
						                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
						                                data-ng-options="item as item.name for item in detectionTypes | orderBy: 'name' track by item.uid" 
						                                         data-ng-model="sensorDetectionType">
						                                         <option value="">Selecione</option> 
						                        </select>               
					                        </div>
				                        </div>                
					                </div>                    
					                <div class="row">
					                	<div class="col-md-6">                                                                                                                
											<div class="form-group">
												<label class="control-label">Nome *</label>
												<span class="text-red" data-ng-show="sensorNameExist">Sensor já Existe</span> 
												<span class="text-red" data-ng-show="sensorForm.username.$error.required && !sensorForm.username.$pristine">  [Nome Obrigatorio]</span>
											    <span class="text-red" data-ng-show="sensorForm.username.$error.maxlength">Tamanho Máximo 15 caracteres</span>                                                                        
												<input id="idSensorName"  data-ng-keydown="keypress($event)" class="form-control inputProfile" placeholder="Nome do Sensor" data-ng-model="sensorName" data-ng-maxlength="15" name="username" required>                                                                        
											</div>
										</div>							
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label">Modelo *</label>
												<span class="text-red" data-ng-show="sensorForm.controllerModel.$error.required && !sensorForm.controllerModel.$pristine">  [Modelo Obrigatorio]</span>
										        <span data-ng-show="sensorFormForm.controllerModel.$error.maxlength">Até Máximo 20 caracteres</span>                                                       
												<input class="form-control inputProfile" placeholder="Modelo do Sensor" data-ng-model="sensorModel">                                                
											</div>
										</div>														
									</div>
			                        
			                       	<div class="box box-primary box-solid">
							    		<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Range de Detecção *</strong></div>					                	 
						                    <div class="box-body">
											    <div class="row">
											    	<div class="col-md-2">
												    	<div class="form-group">
											                <label class="control-label">Min</label>
											                <input class="form-control" placeholder="Min" data-ng-model="sensorRangeMin">
											            </div>
												    </div>
												    <div class="col-md-2">
												    	<div class="form-group">
											                <label class="control-label">Máx</label>
											                <input class="form-control" placeholder="Max" data-ng-model="sensorRangeMax">
											            </div>
												    </div>
												    <div class="col-md-2">
												    	<div class="form-group">
											                <label class="control-label">Resolução</label>
											                <input class="form-control" placeholder="Unit" data-ng-model="sensorRangeUnit">
											            </div>
												    </div>
												    
												    <div class="col-md-3">
												    	<div class="form-group">
												            <label class="control-label">Unid.Medida</label>
															<select class="form-control" data-live-search="true" 
									                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
									                                data-ng-options="item as item.name for item in unitMetersGases | orderBy: 'name' track by item.uid" 
									                                         data-ng-model="gasUnitMeterGases">
									                                         <option value="">Selecione</option> 
									                        </select>               
								                        </div>
													</div>
													
													<div class="col-md-3">
														<div class="form-group">
															<label class="control-label">Elemento (Gás)</label> 
															<select class="form-control" data-live-search="true" 
																style="width: 100%;" tabindex="-1" aria-hidden="true"                              
																	data-ng-options="item as item.name for item in gases | orderBy: 'name' track by item.uid" 
																	   data-ng-model="sensorGas">
																	   <option value="">Selecione</option> 
															</select>    
														</div>              
								                        
												    </div>

											    </div>
									    </div>
								    </div>
			                        
			                        <!-- <div class="box box-primary box-solid">
					                    <div class="box-header with-border ui-sortable-handle ">
					                    	<strong><i class="fa fa-yelp"></i> Gases</strong>
					                    	<span class="text-red pull-right" data-ng-show="sensorGases.length == 0"><strong>[Adicionar um Gás]</strong></span>
					                    	<span class="text-white pull-right" data-ng-show="existSensor"> <strong>[ Altera&ccedil;&atilde;o N&atilde;o Permitida, H&aacute; Equipamento(s) Relacionado(s) a Este Sensor ]</strong></span>					                    				                    	 
					                    </div>
					                    		                	 
					                    <div class="box-body" data-ng-class="{'disableDiv': existSensor}">		                    
						                    
						                    <div class="col-sm-6">			                    
						                    
						                    	<label class="control-label">Gases Detectáveis</label>
							                    <div style="max-height: 250px; height:auto; overflow: auto">                                                                       
				                                    <ul class="sort todo-list" style="padding: 1px !important" data-ng-repeat="item in sensorGases">
				                                         <li style="padding: 4px; border-left: 3px solid #639ed9;">				                                         	    
				                                            <span class="text"><strong>Gás:</strong> {{item.name}}</span>				                                         
				                                         	<div class="pull-right">                                                        
			                                                	<a href="#" data-ng-click="deleteGas($index)"><i class="fa fa-arrow-right"></i></a>
			                                             	</div>
				                                             
				                                         </li>                                                                                                   
				                                     </ul>				                                     
				                                </div>	                                								        
						                    </div>				                    
					                    
						                    <div class="col-sm-6">
					                    		<label class="control-label">Lista de Gases</label>		                        
					                        	<div style="max-height: 150px; height:auto; overflow: auto">				                        	                                                       
		                                            <ul class="todo-list" style="padding: 1px !important" data-ng-repeat="item in gases | gasSensorFilter:searchGas track by $index">
		                                               <li style="background: #d1ddf9; border-left: 3px solid #639ed9;">
		                                                   
		                                                   	<a data-ng-if="sensorGases.length < 1" href="#" data-ng-click="addGasSensor(item.index)"><i class="fa fa-arrow-left"></i></a>
		                                                	<span data-ng-if="sensorGases.length >= 1"><i class="fa fa-arrow-left"></i></span>
		                                                	<span class="text"><strong>Gás:</strong> {{item.name}}</span>
		                                                   
		                                               </li>                                
		                                           </ul>
		                                       </div>
						                    </div>								                            
					                    </div>	
					                    						                    			                            
						            </div>	                  -->
					            </form>							            										
							</div>
						</div>		
				  	</div>
				  	
				  	<div class="modal-footer" style="padding: 8px;">
						<button type="button" data-ng-click="clearFormSensor()" class="btn btn-default" data-dismiss="modal">Cancelar</button>                                                                
						<!-- <button type="button" data-ng-click="saveSensor();" class="btn btn-primary" data-dismiss="modal"
							data-ng-disabled="(sensorName && sensorModel && sensorManufacturer && sensorDetectionType && (sensorGases.length != 0 || newGases.length > 0)) ? false : true">Salvar
						</button> -->
						<button type="button" data-ng-click="saveSensor();" class="btn btn-primary" data-dismiss="modal"
							data-ng-disabled="(sensorName && sensorModel && sensorManufacturer && sensorDetectionType && sensorGas) ? false : true">Salvar
						</button>                                
				  	</div>
				  	
			  	</div>
			</div>		
		</div>
				
	</div>

    
    