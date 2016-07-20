<!--	 Content Wrapper. Contains page     content -->
 
	 
<div ng-controller="sensorController">
					
		<div class="row">	
					                                                    
			<div class="col-md-6">                                                        
				<div class="box box-primary" ng-class="(sensorName || sensorModel || sensorManufacturer) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de Sensores</h3>
					</div>
					<div class="box-body">
					<table class="table table-hover">
					<thead>
						<tr>
							<th>Nome</th>
							<th>Modelo</th>                                                            
							<th>Editar</th>
							<th>Excluir</th>						
						</tr>
					</thead>
					<tbody>                                                        
						<tr ng-repeat="item in sensors">
							<td>{{item.name}}</td>
							<td>{{item.model}}</td>															        
							<td>
								<button type="button" class="btn btn-info btn-xs" ng-click="editSensor($index)">editar</button>
							</td>
							<td>
								<button type="button" class="btn btn-danger btn-xs" ng-click="deleteSensor($index)">excluir</button>
							</td>						
						</tr>                                                               
					</tbody>
				</table>                                                       
				</div>
				</div>
			</div>                                                      
																
			<div class="col-sm-6">
				<div class="box box-primary" ng-class="(sensorName || sensorModel || sensorManufacturer) ? 'box-primary' : 'box-default'">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edição</h3>
					</div>
					
					<div class="box-body">
						<form class="form">		
						
							<div class="box box-default box-solid">
			                    <div class="box-header with-border"><strong>Fabricante</strong>
			                        <a href="#" popover> [Incluir Novo]</a>                        
				                </div>
			                	 
			                    <div class="box-body">
			                        <select class="form-control" data-live-search="true" 
			                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
			                                ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
                                            ng-model="sensorManufacturer">
                                            <option value="">Selecione</option> 
			                        </select>    
			                    </div>
			                    			                            
			                </div>		                
			                 							                                                                                                                                    
							<div class="form-group">
								<label class="control-label">Nome</label>                                                                        
								<input id="idSensorName" class="form-control inputProfile" placeholder="Nome do Sensor" ng-model="sensorName">                                                                        
							</div>
							
							<div class="box box-default box-solid">
			                    <div class="box-header with-border"><strong>TEste</strong></div>
			                	 
			                    <div class="box-body">
			                    
			                    <div class="col-sm-6">
			                    qw3eqeqw
			                    </div>
			                    <div class="col-sm-6">
			                    qerqwew
			                    </div>
			                            
			                    </div>
			                    			                            
				            </div>	
							
		
							<div class="form-group">
								<label class="control-label">Modelo</label>                                                       
								<input class="form-control inputProfile" placeholder="Modelo do Sensor" ng-model="sensorModel">                                                
							</div>
							
							<div class="form-group">
					            <label class="control-label">Tipo de Detecção</label>
								<select class="form-control" data-live-search="true" 
		                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                                ng-options="item as item.name for item in detectionTypes | orderBy: 'name' track by item.uid" 
		                                         ng-model="sensorDetectionType">
		                                         <option value="">Selecione</option> 
		                        </select>               
	                        </div>			                 
			            </form>
			            
						<div class="box-footer">
							<button type="button" ng-click="clearFormSensor()" class="btn btn-default">Cancelar</button>                                                                
							<button type="button" ng-click="saveSensor();" class="btn btn-primary" 
								ng-disabled="(sensorName && sensorModel && sensorManufacturer) ? false : true">Salvar</button>								                                                                
						</div>
						      
						
					</div>
				</div>
			</div>
			
		</div>	  
		
</div>

    
    