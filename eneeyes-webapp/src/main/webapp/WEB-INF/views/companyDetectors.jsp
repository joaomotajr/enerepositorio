   
<div class="col-md-9">
	<div class="box box-primary">
					
		<div class="box-header with-border"><strong style="font-size:1.4em"><i class='fa fa-rss'></i> {{selectedCompanyDevice.deviceType}}</strong></div>
		
		<div class="box-body">
						
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
			       	<li class="active"><a href="#tab_1" data-toggle="tab">Cadastro</a></li>
			       	<li><a href="#tab_2" data-toggle="tab">Mapa</a></li>
			       	<li><a href="#tab_3" data-toggle="tab">Detalhes</a></li>
			    	<li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			    </ul>
				
				<div class="tab-content">
			    	
			    	<div class="tab-pane active" id="tab_1" data-toggle="tab">
			    	
			    		<div class="row">
					    	<div class="col-md-6">
					    	
					    		<div class="box box-primary box-solid">
				                    <div class="box-header with-border"><strong>Detector</strong></div>
				                	 
				                    <div class="box-body">
				                        <select class="form-control" data-live-search="true" 
				                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
				                                ng-options="item as item.name for item in detectors | orderBy: 'name' track by item.uid" 
		                                           ng-model="selectedCompanyDetector.detector">
		                                           <option value="">Selecione</option> 
				                        </select>    
				                    </div>			                    			                            
				                </div>  	
					    	
					    	</div>
					    </div>
			    	
			    	
			    		<div class="row">
					        <div class="col-md-6">
					            <div class="form-group">
					                <label class="control-label">Nome</label>
					                <input id="idAreaName" class="form-control" placeholder="Nome da Area / Matriz" ng-model="selectedCompanyDetector.name">
					            </div>
					        </div>
					
					        <div class="col-md-6">
					            <div class="form-group">
					                <label class="control-label">Descrição</label>
					                <input class="form-control" placeholder="E-mail" ng-model="selectedCompanyDetector.description">
					            </div>
					        </div>
					    </div>
						
						<div class="row">
					        <div class="col-md-8">
					            <div class="form-group">
					                <label class="control-label">Local</label>
					                <input id="idUnitName" class="form-control" placeholder="Local" ng-model="selectedCompanyDetector.local">
					            </div>
					        </div>					
					        
					    </div>
						
						<div class="row">							
					        <div class="col-md-4">
					            <div class="form-group">
					                <label class="control-label">Latitude</label>
					                <input id="idUnitName" class="form-control" placeholder="Latitude" ng-model="selectedCompanyDetector.longitude">
					            </div>
					        </div>
					
					        <div class="col-md-4">						        
					        	<div class="form-group">
					                <label class="control-label">Longitude</label>
					                <input class="form-control" placeholder="Longitude" ng-model="selectedCompanyDetector.latitude">
					            </div>						        					            
					        </div>
					
					        <div class="col-md-4">            
					        </div>
						</div>				
			       		
			       		<div class="row">
			       			<div class="col-md-12">
			       				<button type="button" ng-click="newCompanyDetector();" class="btn btn-success pull-right">    Nova    </button>								
								<span class="pull-right">   </span>
			       				<button type="button" ng-click="saveCompanyDetector();" class="btn btn-primary pull-right" ng-disabled="selectedCompanyDetector.name ? false : true">   Salvar   </button>		       				
			       				<span class="pull-right">   </span>
			       				<button type="button" ng-click="deleteCompanyDetector();" class="btn btn-danger pull-right" ng-disabled="(selectedCompanyDetector.uid) ? false : true">   Excluir   </button>								
							</div>
						</div>												
						
			    	         
			       	</div><!-- /.tab-pane -->
			       	
			       	<div class="tab-pane" id="tab_2" data-toggle="tab">	 
			       			       		
			       	        
			       	</div><!-- /.tab-pane -->
			       	
			       	<div class="tab-pane" id="tab_3" data-toggle="tab">	        
			     	</div><!-- /.tab-content -->			     	
			   </div>			   						
			</div>
						
		</div>		
	</div>	
</div>     