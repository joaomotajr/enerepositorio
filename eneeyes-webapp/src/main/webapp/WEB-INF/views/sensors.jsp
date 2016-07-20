<!--	 Content Wrapper. Contains page     content -->
 
	 
<div ng-controller="sensorController">
		<style>
		      #sortable1, #sortable2 {
		        border: 1px solid #eee;
		        width: 120px;
		        min-height: 20px;
		        list-style-type: none;
		        margin: 0;
		        padding: 5px 0 0 0;
		        float: left;
		        margin-right: 10px;
		      }
		      #sortable1 li, #sortable2 li {
		        margin: 0 5px 5px 5px;
		        padding: 5px;
		        font-size: 1.2em;
		        width: 120px;
		      }    
		</style>
		        											
		<div class="row">	
					                                                    
			<div class="col-md-5">                                                        
				<div class="box box-primary" ng-class="(sensorName || sensorModel || sensorManufacturer || sensorDetectionType) ? 'box-default' : 'box-primary'">
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
																
			<div class="col-sm-7">
				<div class="box box-primary" ng-class="(sensorName || sensorModel || sensorManufacturer || sensorDetectionType) ? 'box-primary' : 'box-default'">
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
	                        
	                        <div class="box box-default box-solid">
			                    <div class="box-header with-border ui-sortable-handle "><strong>Gases</strong></div>
			                	 
			                    <div class="box-body">
			                    
				                    <div class="col-sm-6">
				                    
				                    	<label class="control-label">Detectáveis</label>
					                    <div style="max-height: 250px; height:auto; overflow: auto">                                                                       
		                                    <ul class="sort todo-list" style="padding: 1px !important" ng-repeat="gas in sensorGases">
		                                         <li id="{{gas.uid}}" class="{{'c' + gas.uid}}" style="padding: 4px">
		                                             <span class="handle"><i class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i></span>                                               
		                                             <span class="text">{{gas.name}} </span>
		                                         
		                                             <div class="tools">                                                        
		                                                 <i class="fa fa-trash-o" ng-click="deletarGas($index)"></i>
		                                             </div>
		                                         </li>                                                                                                   
		                                     </ul> 
		                                     <ul ng-if="sensorGases.length < 1" class="sort todo-list" style="padding: 1px !important" title="Arraste aqui para Incluir">
		                                         <li ng-show="newGases.length <= 0" style="background:rgb(60, 141, 188); border-color:lightgray;color:white;padding: 4px;"> Arraste aqui para Incluir</li>
		                                     </ul>
		                                </div>
		                                								        
				                    </div>				                    
			                    
				                    <div class="col-sm-6">
				                    	<label class="control-label">Lista de Gases</label>		                        
				                        <div style="max-height: 250px; height:auto; overflow: auto">				                        	                                                       
                                             <ul class="drag todo-list" style="padding: 1px !important" ng-repeat="gas in gases">
                                                <li id="{{gas.uid}}" class="{{'c' + gas.uid}}" style="padding: 4px">                                                        
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
							<button type="button" ng-click="clearFormSensor()" class="btn btn-default">Cancelar</button>                                                                
							<button type="button" ng-click="saveSensor();" class="btn btn-primary" 
								ng-disabled="(sensorName && sensorModel && sensorManufacturer && sensorDetectionType) ? false : true">Salvar</button>								                                                                
						</div>
						      
						
					</div>
				</div>
			</div>
			
		</div>	  
		
</div>

    
    