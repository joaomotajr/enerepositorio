   
<div class="col-md-9">
	<div class="box box-primary">
					
		<div class="box-header with-border"><strong style="font-size:1.4em"><i class='fa fa-map-o'></i> Área: {{selectedArea.name}}</strong></div>
		
		<div class="box-body">
						
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
			       	<li class="active"><a href="#tab_1" data-toggle="tab">Cadastro</a></li>
			       	<li><a href="#tab_2" data-toggle="tab">Mapa</a></li>
			       	<li><a href="#tab_3" data-toggle="tab">Detalhes</a></li>
			    	<li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			    </ul>
				
				<div class="tab-content">
			    	
			    	<div class="tab-pane active" id="tab_1">
			    		<div class="row">
					        <div class="col-md-6">
					            <div class="form-group">
					                <label class="control-label">Nome</label>
					                <input id="idAreaName" class="form-control" placeholder="Nome da Area / Matriz" ng-model="selectedArea.name">
					            </div>
					        </div>
					
					        <div class="col-md-6">
					            <div class="form-group">
					                <label class="control-label">Descrição</label>
					                <input class="form-control" placeholder="Descrição" ng-model="selectedArea.description">
					            </div>
					        </div>
					    </div>
						
						<div class="row">
					        <div class="col-md-8">
					            <div class="form-group">
					                <label class="control-label">Local</label>
					                <input id="idUnitName" class="form-control" placeholder="Local" ng-model="selectedArea.local">
					            </div>
					        </div>
					
					        <div class="col-md-4">
					        	<label class="control-label">Area Classificada</label>
				                <div class="form-group">				                 	
      									<label><input type="radio" ng-model="selectedUnit.classified" value="true" />&nbsp; Sim</label>        
				                 	<label><input type="radio" ng-model="selectedUnit.classified" value="false" />&nbsp;	Não</label>						                
				                </div>
					        						            
					        </div>
					    </div>
						
						<div class="row">							
					        <div class="col-md-4">
					            <div class="form-group">
					                <label class="control-label">Latitude:</label>
					                <input id="idUnitName" class="form-control" placeholder="Latitude" ng-model="selectedArea.longitude">
					            </div>
					        </div>
					
					        <div class="col-md-4">
					        	<div class="form-group">
					                <label class="control-label">Longitude:</label>
					                <input class="form-control" placeholder="Longitude" ng-model="selectedArea.latitude">
					            </div>					            
					        </div>
					
					        <div class="col-md-4">			            
				                					            
					        </div>
						</div>				
			       		
			       		<div class="row">
			       			<div class="col-md-12">
			       				<button type="button" ng-click="newArea();" ng-show="btnNewArea" class="btn btn-success pull-right">    Nova    </button>								
								<span class="pull-right">   </span>
			       				<button type="button" ng-click="saveArea();" class="btn btn-primary pull-right" ng-disabled="(selectedArea.name) ? false : true">   Salvar   </button>		       				
			       				<span class="pull-right">   </span>
			       				<button type="button" ng-click="deleteArea();" class="btn btn-danger pull-right" ng-disabled="(selectedArea.uid) ? false : true">   Excluir   </button>								
							</div>
						</div>
												
					<!-- <div class="row" ng-show="selectedArea.companyDevicesDto.length <= 0">  -->												
						<div class="row">
							<div class="col-md-6">
								<label>Incluir Dispositivo</label>						
								<div class="entry input-group">					
							        
							        <select class="form-control" data-live-search="true" 
			                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
			                                ng-options="item as item.name for item in deviceTypes | orderBy: 'name' track by item.uid" 
			                                         ng-model="sensorDetectionType">
			                                         <option value="">Selecione</option> 
			                        </select>
			                                       
							        <span class="input-group-btn">
							        <button class="btn btn-info btn-flat" type="button" ng-click="saveCompanyDeviceInit();">OK</button>
							        </span>
								</div>
							</div>
						</div>
			    	         
			       	</div><!-- /.tab-pane -->
			       	
			       	<div class="tab-pane" id="tab_2">	         
			       	</div><!-- /.tab-pane -->
			       	
			       	<div class="tab-pane" id="tab_3">	        
			     	</div><!-- /.tab-content -->			     	
			   </div>			   						
			</div>
						
		</div>		
	</div>	
</div>     