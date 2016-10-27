<!--	 Content Wrapper. Contains page     content -->

	<style>
		.todo-list>li {
		    padding: 4px;
		}
		
		img {
		  *float: left;
		  *width: 150px;
		  *height: 150px;
		  *border: 1px solid #000;
		  *margin-right: 1em;
		    width: auto;
    		max-height: 160px;
    		height: 160px;
		}
			
	</style>
	 
<div ng-controller="detectorController">
					        											
	<div class="row">	
				                                                    
		<div class="col-md-5">                                                        
			<div class="box box-primary" ng-class="(detectorName || detectorModel || detectorManufacturer || detectorTransmitter) ? 'box-default' : 'box-primary'">
				<div class="box-header">
				  <h3 class="box-title">Cadastro de Detectores</h3>
				</div>
				<div class="box-body">
					<div style="height: 500px; overflow: auto">
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
			
			<div id="resultErro" class="alert alert-warning" role="alert" ng-show="msgErro" >
           		<button type="button" class="close" ><span ng-click="msgErro='';">&times;</span></button>
           		<strong>Alerta! </strong>{{msgErro}} 
       		</div>
		</div>                                                      
															
		<div class="col-sm-7">
			<div class="box box-primary" ng-class="(detectorName || detectorModel || detectorManufacturer || detectorTransmitter) ? 'box-primary' : 'box-default'">
				<div class="box-header">
					<h3 class="box-title">Cadastro / Edição</h3>
					<a href="#" class="text-muted pull-right" ng-click="refreshDetectors();"><i title="Refresh" class="fa fa-refresh"></i></a>
				</div>
				
				<div class="box-body">
					<form class="form" name="userForm">					
						<div class="row">							
							<div class="col-md-6">							    
							    <input type="file" id="idInputImageDetector" style='display:none'>							    
							    <div class="box box-primary">
					                <div class="box-body box-profile">                          
					                    <img class="profile-user-img img-responsive img-circle" style="margin: 0 auto" alt="Imagem do Perfil" 
					                    	ng-src="{{detectorImage}}" onError="this.src='/assets/img/cover.jpg'">
					                    <p class="text-muted text-center ng-binding">{{detectorName}} - {{detectorModel}} </p>                    
					                    <a href="#" class="icon fa fa-photo fa-2.0x pull-right" ng-click="addPhoto();" title="Trocar foto"></a>
					                    					                    	                
					                </div>
				                </div>															
							</div>							
							<div class="row">
			                 	<div class="col-md-6">                                                                                                                                    
									<div class="form-group">
										<label class="control-label">Nome</label>
										<span class="text-red" ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Nome Obrigatorio]</span>
								        <span class="text-red" ng-show="userForm.username.$error.maxlength">Tamanho Máximo 15 caracteres</span>                                                                        
										<input id="idDetectorName" class="form-control inputProfile" placeholder="Nome do Detector" ng-model="detectorName" ng-maxlength="15" name="username" required>                                                                        
									</div>							
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Modelo</label>                                                       
										<input class="form-control inputProfile" placeholder="Modelo do Detector" ng-model="detectorModel">                                                
									</div>
								</div>							
							</div>							
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="box box-primary box-solid">
				                    <div class="box-header with-border"><strong></i> Fabricante</strong>
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
			                </div>
			                
			                <div class="col-md-6">
				                <div class="box box-primary box-solid">
				                    <div class="box-header with-border"><strong><i class="fa fa-expand"></i> Transmissor</strong></div>
				                	 
				                    <div class="box-body">
				                        <select class="form-control" data-live-search="true" 
				                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
				                                ng-options="item as item.name for item in transmitters | orderBy: 'name' track by item.uid" 
		                                           ng-model="detectorTransmitter">
		                                           <option value="">Selecione</option> 
				                        </select>    
				                    </div>			                    			                            
				                </div>		     
			                </div>
		                </div>		     
                        <div class="box box-primary box-solid">
		                    <div class="box-header with-border ui-sortable-handle ">
		                    	<strong><i class="fa fa-feed"></i> Sensores </strong>
		                    	<span class="text-red" ng-show="detectorSensors.length == 0 && newSensors.length == 0">  [Adicionar ao Menos Um Sensor]</span>
		                    </div>
		                	 
		                    <div class="box-body">
		                    
			                    <div class="col-sm-6">
			                    
			                    	<label class="control-label">Sensores</label>			                    	
				                    <div style="max-height: 250px; height:auto; overflow: auto">                                                                       
	                                    <ul class="sort todo-list" style="padding: 1px !important" ng-repeat="sensor in detectorSensors">
	                                         <li id="{{sensor.uid}}" class="{{'c' + sensor.uid}}" style="padding: 4px">
	                                             <span class="handle"><i class="fa fa-ellipsis-v"></i> <i class="fa fa-ellipsis-v"></i></span>                                               
	                                             <span class="text">{{sensor.name}} </span>
	                                         
	                                             <div class="tools">                                                        
	                                                 <i class="fa fa-trash-o" ng-click="deleteSensor($index)"></i>
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
							ng-disabled="(detectorName && detectorModel && detectorManufacturer && detectorTransmitter && (detectorSensors.length != 0 || newSensors.length > 0)) ? false : true">Salvar
						</button>
					</div>
					
				</div>
			</div>
		</div>
		
	</div>	  
		
</div>

    
    