 <style>
		.disableDiv {
			pointer-events: none;
			opacity: 0.6;
		}	
			
	</style>
<div data-ng-controller="detectorController">
					        											
	<div class="row">	
				                                                    
		<div class="col-md-12">                                                        
			<div class="box box-primary" data-ng-class="(detectorName || detectorModel || detectorManufacturer || detectorTransmitter) ? 'box-default' : 'box-primary'">
				<div class="box-header">
				  <h3 class="box-title">Cadastro de Detectores</h3>
				  <a href="#" class="text-muted pull-right" data-ng-click="refreshDetectors();"><i title="Refresh" class="fa fa-refresh"></i></a>
				</div>
				<div class="box-body">
					<div style="height: 500px; overflow: auto">						
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Nome</th>
									<th>Modelo</th>
									<th>Fabricante</th>
									<th>Transmissor</th>
									<th>Sensor(es)</th>
									<th>Imagem</th>                                                              
									<th>Editar</th>
									<th>Excluir</th>						
								</tr>
							</thead>
							<tbody>                                                        
								<tr data-ng-repeat="item in detectors">
									<td>{{item.name}}</td>
									<td>{{item.model}}</td>
									<td>{{item.manufacturerDto.name}}</td>		
									<td>{{item.transmitterDto.name}}</td>
									<td>{{item.sensorDto.name}}</td>
									<td>			
										<img class="direct-chat-img" data-ng-src="{{item.image}}" style="width: auto ! important; height: 25px ! important ; max-height: 25px ! important;">
									</td>											        
									<td>
										<button type="button" class="btn btn-primary btn-xs" data-ng-click="editDetector($index)">editar</button>
									</td>
									<td>
										<a type="button" class="btn btn-danger btn-xs" data-popover=' do Detector: [ {{item.name}} ]' data-confirm="deleteDetector($index)" >excluir</a>
									</td>						
								</tr>                                                               
							</tbody>
						</table>
					</div>                                                       
				</div>
				
				<div class="box-footer">						                                                                
					<button type="button" data-ng-click="clearFormDetector(); userForm.$setPristine()" class="btn btn-primary pull-right" 
					data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#modalEditDetector">Novo</button>
				</div>
			</div>
			
			<div class="alert alert-warning" role="alert" data-ng-show="msgErroDetector" >
           		<button type="button" class="close" ><span data-ng-click="msgErroDetector='';">&times;</span></button>
           		<strong>Alerta! </strong>{{msgErroDetector}} 
       		</div>
		</div>                                                      
				
	</div>	 
	
	<div id="modalEditDetector" class="modal">                
		<div class="modal-dialog  modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-body" style="padding-bottom: 5px !important">					
					<div class="panel panel-default">
						<div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Edi&ccedil;&atilde;o de Detectores</strong></div>	
				  	</div>
				  							
					<div class="box box-primary" data-ng-class="(detectorName || detectorModel || detectorManufacturer || detectorTransmitter) ? 'box-primary' : 'box-default'">
					
						<div class="box-header">
							<h3 class="box-title">Cadastro / Edi&ccedil;&atilde;o</h3>							
						</div>
						
						<div class="box-body">
							<form class="form" name="userForm">					
								<div class="row">							
									<div class="col-md-6">							    
									    <input type="file" id="idInputImageDetector" style='display:none'>							    
									    <div class="box box-primary">
							                <div class="box-body box-profile">                          
							                    <img class="profile-user-img img-responsive img-circle imgDetector" style="margin: 0 auto" alt="Imagem do Perfil" 
							                    	data-ng-src="{{detectorImage}}" onError="this.src='/assets/img/cover.jpg'">
							                    <p class="text-muted text-center data-ng-binding">{{detectorName}} - {{detectorModel}} </p>                    
							                    <a href="#" class="icon fa fa-photo fa-2.0x pull-right" data-ng-click="addPhoto();" title="Trocar foto"></a>
							                    					                    	                
							                </div>
						                </div>															
									</div>							
									<div class="row">
					                 	<div class="col-md-6">                                                                                                                                    
											<div class="form-group">
												<label class="control-label">Nome *</label>
												<span class="text-red" data-ng-show="detectorNameExist">Detector j&aacute; Existe</span> 
												<span class="text-red" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Nome Obrigat&oacute;rio]</span>
										        <span class="text-red" data-ng-show="userForm.username.$error.maxlength">Tamanho M&aacute;ximo 15 caracteres</span>                                                                        
												<input id="idDetectorName" data-ng-keydown="keypress($event)" class="form-control inputProfile" placeholder="Nome do Detector" data-ng-model="detectorName" data-ng-maxlength="15" name="username" required>                                                                        
											</div>							
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label">Modelo *</label>                                                       
												<input class="form-control inputProfile" placeholder="Modelo do Detector" data-ng-model="detectorModel">                                                
											</div>
										</div>							
									</div>							
								</div>
								
								<div class="row">
										<div class="col-md-4" style=" padding-left: 5px !important">
										<div class="box box-primary box-solid">				                    
						                	<div class="box-header with-border"><strong><i class="fa fa-industry"></i> Fabricante *</strong></div>
						                    <div class="box-body">
						                        <select class="form-control" data-live-search="true" 
						                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
						                                data-ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
				                                           data-ng-model="detectorManufacturer"
				                                           data-ng-change="changeManufacturer();">
				                                           <option value="">Selecione</option> 
						                        </select>    
						                    </div>			                    			                            
						                </div>
					                </div>
					                
					                <div class="col-md-4" style="padding-right: 5px !important; padding-left: 5px !important">															
						                <div class="box box-primary box-solid">
						                    <div class="box-header with-border"><strong><i class="fa fa-expand"></i> Transmissor *</strong></div>
						                	 
						                    <div class="box-body">
						                        <select class="form-control" data-live-search="true" 
						                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
						                                data-ng-options="item as item.name for item in transmitters | manufacturerFilter:search | orderBy: 'name' track by item.uid"  
				                                           data-ng-model="detectorTransmitter">
				                                           <option value="">Selecione</option> 
						                        </select>    
						                    </div>			                    			                            
						                </div>		     
									</div>

									<div class="col-md-4" style="padding-right: 5px !important;">
						                <div class="box box-danger box-solid">
						                    <div class="box-header with-border"><strong><i class="fa fa-feed"></i> Sensor *</strong></div>
						                	 
						                    <div class="box-body">
						                        <select class="form-control" data-live-search="true" 
						                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
													data-ng-options="item as item.name for item in sensors | manufacturerFilter:search | orderBy: 'name' track by item.uid" 
													data-ng-model="detectorSensor">	 
				                                    <option value="">Selecione</option> 
						                        </select>    
						                    </div>			                    			                            
						                </div>		     
									</div>	
								</div>								
								<hr style="margin-top: 5px !important; margin-bottom: 5px !important;">												            			                  
				            </form>
							
						</div>	
					</div>									
			  	</div>
			  	
			  	<div class="modal-footer" style="padding: 8px;">
			  		<button type="button" data-ng-click="clearFormDetector()" class="btn btn-default" data-dismiss="modal">Cancelar</button>

					<button type="button" data-ng-click="saveDetector();" class="btn btn-primary" data-dismiss="modal"
						data-ng-disabled="(detectorName && detectorModel && detectorManufacturer && detectorTransmitter && detectorSensor) ? false : true">Salvar
					</button>					                               										
			  	</div>
			  	
		  	</div>
		</div>		
	</div> 
		
</div>