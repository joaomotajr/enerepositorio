<!--	 Content Wrapper. Contains page     content -->
	 
<div ng-controller="detectorController">

	<style>
	.todo-list>li {
	    padding: 4px;
	}
	
	</style>				        											
	<div class="row">	
				                                                    
		<div class="col-md-5">                                                        
			<div class="box box-primary" ng-class="(detectorName || detectorModel || detectorManufacturer || detectorTransmitter) ? 'box-default' : 'box-primary'">
				<div class="box-header">
				  <h3 class="box-title">Cadastro de Detectores</h3>
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
							<tr ng-repeat="item in detectors">
								<td>{{item.name}}</td>
								<td>{{item.model}}</td>															        
								<td>
									<button type="button" class="btn btn-primary btn-xs" ng-click="editDetector($index)">editar</button>
								</td>
								<td>
									<button type="button" class="btn btn-danger btn-xs" ng-click="deleteDetector($index)">excluir</button>
								</td>						
							</tr>                                                               
						</tbody>
					</table>                                                       
				</div>
			</div>
		</div>                                                      
															
		<div class="col-sm-7">
			<div class="box box-primary" ng-class="(detectorName || detectorModel || detectorManufacturer || detectorTransmitter) ? 'box-primary' : 'box-default'">
				<div class="box-header">
					<h3 class="box-title">Cadastro / Edição</h3>
				</div>
				
				<div class="box-body">
					<form class="form">		
					
						<div class="box box-primary box-solid">
		                    <div class="box-header with-border"><strong>Fabricante</strong>
		                        <a href="#" popover> [Incluir Novo]</a>                        
			                </div>
		                	 
		                    <div class="box-body">
		                        <select class="form-control" data-live-search="true" 
		                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                                ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
                                           ng-model="detectorManufacturer">
                                           <option value="">Selecione</option> 
		                        </select>    
		                    </div>			                    			                            
		                </div>
		                
		                <div class="box box-primary box-solid">
		                    <div class="box-header with-border"><strong>Transmissor</strong></div>
		                	 
		                    <div class="box-body">
		                        <select class="form-control" data-live-search="true" 
		                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                                ng-options="item as item.name for item in transmitters | orderBy: 'name' track by item.uid" 
                                           ng-model="detectorTransmitter">
                                           <option value="">Selecione</option> 
		                        </select>    
		                    </div>			                    			                            
		                </div>		                
		                 	                                                                                                                                    
						<div class="form-group">
							<label class="control-label">Nome</label>                                                                        
							<input id="idDetectorName" class="form-control inputProfile" placeholder="Nome do Detector" ng-model="detectorName">                                                                        
						</div>							
	
						<div class="form-group">
							<label class="control-label">Modelo</label>                                                       
							<input class="form-control inputProfile" placeholder="Modelo do Detector" ng-model="detectorModel">                                                
						</div>							
						
						<!-- 
						<div class="form-group">
				            <label class="control-label">Tipo de Detecção</label>
							<select class="form-control" data-live-search="true" 
	                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
	                                ng-options="item as item.name for item in detectionTypes | orderBy: 'name' track by item.uid" 
	                                         ng-model="detectorDetectionType">
	                                         <option value="">Selecione</option> 
	                        </select>               
                        </div>
                         -->
                         
                        <div class="box box-primary box-solid">
		                    <div class="box-header with-border ui-sortable-handle "><strong>Sensores</strong></div>
		                	 
		                    <div class="box-body">
		                    
			                    <div class="col-sm-6">
			                    
			                    	<label class="control-label">Sensores</label>
				                    <div style="max-height: 250px; height:auto; overflow: auto">                                                                       
	                                    <ul class="sort todo-list" style="padding: 1px !important" ng-repeat="sensor in detectorSensors">
	                                         <li id="{{sensor.uid}}" class="{{'c' + sensor.uid}}" style="padding: 4px">
	                                             <span class="handle"><i class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i></span>                                               
	                                             <span class="text">{{sensor.name}} </span>
	                                         
	                                             <div class="tools">                                                        
	                                                 <i class="fa fa-trash-o" ng-click="deletarSensor($index)"></i>
	                                             </div>
	                                         </li>                                                                                                   
	                                     </ul> 
	                                     <ul ng-if="detectorSensors.length < 1" class="sort todo-list" style="padding: 1px !important" title="Arraste aqui para Incluir">
	                                         <li ng-show="newSensors.length <= 0" style="background:rgb(60, 141, 188); border-color:lightgray;color:white;padding: 4px;"> Arraste aqui para Incluir</li>
	                                     </ul>
	                                </div>
	                                								        
			                    </div>				                    
		                    
			                    <div class="col-sm-6">
			                    	<label class="control-label">Lista de Sensores</label>		                        
			                        <div style="max-height: 250px; height:auto; overflow: auto">				                        	                                                       
                                            <ul class="drag todo-list" style="padding: 1px !important" ng-repeat="sensor in sensors">
                                               <li id="{{sensor.uid}}" class="{{'c' + sensor.uid}}" style="background: #d1ddf9;">                                                        
                                                   <span class="handle"><i class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i></span>
                                                   <span class="text">{{sensor.name}}</span>
                                               </li>                                
                                           </ul>
                                       </div>
			                    </div>
			                            
		                    </div>
		                    			                            
			            </div>	
			            			                 
		            </form>
		            
					<div class="box-footer">
						<button type="button" ng-click="clearFormDetector()" class="btn btn-default">Cancelar</button>                                                                
						<button type="button" ng-click="saveDetector();" class="btn btn-primary" 
							ng-disabled="(detectorName && detectorModel && detectorManufacturer && detectorTransmitter) ? false : true">Salvar
						</button>							                                                                

					</div>
					      
					
				</div>
			</div>
		</div>
		
	</div>	  
		
</div>

    
    