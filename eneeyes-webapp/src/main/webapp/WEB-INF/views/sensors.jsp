	<style>
		.todo-list>li {
		    padding: 4px;
		}	
	</style>
	 
	<div data-ng-controller="sensorController">
												
		<div class="row">				                                                    
			<div class="col-md-5">                                                        
				<div class="box box-primary" data-ng-class="(sensorName || sensorModel || sensorManufacturer || sensorDetectionType) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de Sensores</h3>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Modelo</th>                                                            
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr data-ng-repeat="item in sensors">
										<td>{{item.name}}-{{item.model}}</td>
										<td>{{item.model}}</td>															        
										<td>
											<button type="button" class="btn btn-primary btn-xs" data-ng-click="editSensor($index)">editar</button>
										</td>
										<td>
											<button type="button" class="btn btn-danger btn-xs" data-ng-click="deleteSensor($index)">excluir</button>
										</td>						
									</tr>                                                               
								</tbody>
							</table>
						</div>                                                       
					</div>
				</div>
				<div id="resultErro" class="alert alert-warning" role="alert" data-ng-show="msgErro" >
            		<button type="button" class="close" ><span data-ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
			</div>                                                      
																
			<div class="col-sm-7">
				<div class="box box-primary" data-ng-class="(sensorName || sensorModel || sensorManufacturer || sensorDetectionType) ? 'box-primary' : 'box-default'">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edição</h3>
						<a href="#" class="text-muted pull-right" data-ng-click="refreshSensors();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					
					<div class="box-body">
						<form class="form" name="sensorForm">		
						
							<div class="row">
								<div class="col-md-6">    					                	 
				                    <div class="form-group">
				                    	<label class="control-label">Fabricantes</label> 
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
							            <label class="control-label">Tipo de Detecção</label>
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
										<label class="control-label">Nome</label>
										<span class="text-red" data-ng-show="sensorForm.username.$error.required && !sensorForm.username.$pristine">  [Nome Obrigatorio]</span>
									    <span class="text-red" data-ng-show="sensorForm.username.$error.maxlength">Tamanho Máximo 15 caracteres</span>                                                                        
										<input id="idSensorName" class="form-control inputProfile" placeholder="Nome do Sensor" data-ng-model="sensorName" data-ng-maxlength="15" name="username" required>                                                                        
									</div>
								</div>							
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Modelo</label>
										<span class="text-red" data-ng-show="sensorForm.controllerModel.$error.required && !sensorForm.controllerModel.$pristine">  [Modelo Obrigatorio]</span>
								        <span data-ng-show="sensorFormForm.controllerModel.$error.maxlength">Até Máximo 20 caracteres</span>                                                       
										<input class="form-control inputProfile" placeholder="Modelo do Sensor" data-ng-model="sensorModel">                                                
									</div>
								</div>														
							</div>
	                        
	                       	<div class="box box-primary box-solid">
					    		<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Range de Detecção</strong></div>					                	 
				                    <div class="box-body">
									    <div class="row">
									    	<div class="col-md-3">
										    	<div class="form-group">
									                <label class="control-label">Min</label>
									                <input class="form-control" placeholder="Min" data-ng-model="sensorRangeMin">
									            </div>
										    </div>
										    <div class="col-md-3">
										    	<div class="form-group">
									                <label class="control-label">Máx</label>
									                <input class="form-control" placeholder="Max" data-ng-model="sensorRangeMax">
									            </div>
										    </div>
										    <div class="col-md-3">
										    	<div class="form-group">
									                <label class="control-label">Incremento</label>
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
									    </div>
							    </div>
						    </div>
	                        
	                        <div class="box box-primary box-solid">
			                    <div class="box-header with-border ui-sortable-handle "><strong><i class="fa fa-yelp"></i> Gases </strong>
			                    	<span class="text-red" data-ng-show="sensorGases.length == 0 && newGases.length == 0">  [Adicionar ao Menos Um Sensor]</span> 
			                    </div>		                	 
			                    <div class="box-body">		                    
				                    <div class="col-sm-6">			                    
				                    	<label class="control-label">Gases Detectáveis</label>
					                    <div style="max-height: 250px; height:auto; overflow: auto">                                                                       
		                                    <ul class="sort todo-list" style="padding: 1px !important" data-ng-repeat="gas in sensorGases">
		                                         <li id="{{gas.uid}}" class="{{'c' + gas.uid}}" style="padding: 4px">
		                                             <span class="handle"><i class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i></span>                                               
		                                             <span class="text">{{gas.name}} </span>
		                                         
		                                             <div class="tools">                                                        
		                                                 <i class="fa fa-trash-o" data-ng-click="deleteGas($index)"></i>
		                                             </div>
		                                         </li>                                                                                                   
		                                     </ul> 
		                                     <ul data-ng-if="sensorGases.length < 1" class="sort todo-list" style="padding: 1px !important" title="Arraste aqui para Incluir">
		                                         <li data-ng-show="newGases.length <= 0" style="background:rgb(60, 141, 188); border-color:lightgray;color:white;padding: 4px;"> Arraste aqui para Incluir</li>
		                                     </ul>
		                                </div>	                                								        
				                    </div>				                    
			                    
				                    <div class="col-sm-6">
			                    		<label class="control-label">Lista de Gases</label>		                        
			                        	<div style="max-height: 150px; height:auto; overflow: auto">				                        	                                                       
                                            <ul class="drag todo-list" style="padding: 1px !important" data-ng-repeat="gas in gases">
                                               <li id="{{gas.uid}}" class="{{'c' + gas.uid}}" style="background: #d1ddf9;">                                                        
                                                   <span class="handle"><i class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i></span>
                                                   <span class="text">{{gas.name}}</span>
                                               </li>                                
                                           </ul>
                                       </div>
				                    </div>
				                            
			                    </div>
			                    			                            
				            </div>	
				            			                 
			            </form>
			            
						<div class="box-footer">
							<button type="button" data-ng-click="clearFormSensor()" class="btn btn-default">Cancelar</button>                                                                
							<button type="button" data-ng-click="saveSensor();" class="btn btn-primary" 
								data-ng-disabled="(sensorName && sensorModel && sensorManufacturer && sensorDetectionType && (sensorGases.length != 0 || newGases.length > 0)) ? false : true">Salvar
							</button>
						</div>					
					</div>
				</div>
			</div>	
		</div>		
	</div>

    
    