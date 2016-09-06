 <style>
	.todo-list>li {
	    padding: 4px;
	}
	
	img {
	  float: left;
	  width: 150px;
	  height: 100px;
	  border: 1px solid #000;
	  margin-right: 1em;
	}
	.fill {
  		object-fit: fill;	
</style>

<div class="col-md-9">
	<div class="box box-primary">
					
		<div class="box-header with-border"><strong style="font-size:1.4em"><i class='fa fa-rss'></i> {{selectedCompanyDevice.deviceType}}</strong></div>
		
		<div class="box-body">
						
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
			       	<li class="active"><a href="#tabCompanyDetector_1" data-toggle="tab">Cadastro</a></li>
			       	<!-- <li><a href="#tabCompanyDetector_2" data-toggle="tab">Mapa</a></li> -->
			       	<li><a href="#tabCompanyDetector_3" data-toggle="tab">Historico</a></li>
			    	<li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			    </ul>
				
				<div class="tab-content">
			    	
			    	<div class="tab-pane active" id="tabCompanyDetector_1">
			    		<form name="userForm">	 
				    		<div class="row">
						    	<div class="col-md-6">
						    	
						    		<div class="box box-primary box-solid">
					                    <div class="box-header with-border"><strong>Detector</strong></div>
					                	 
					                    <div class="box-body">
					                        <select class="form-control" data-live-search="true" 
					                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
					                                ng-options="item as item.name for item in detectors | orderBy: 'name' track by item.uid" 
			                                           ng-model="selectedCompanyDetector.detectorDto">
			                                           <option value="">Selecione</option> 
					                        </select>    
					                    </div>			                    			                            
					                </div>  	
						    	
						    	</div>
						    	<div class="col-md-4">	
									<div class="output">							    		    
									    <img ng-src="{{selectedCompanyDetector.detectorDto.image}}" onError="this.src='/assets/img/cover.jpg'">
									</div>							
								</div>	
						    </div>			    	
				    	
				    		<div class="row">
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">Nome</label>
						                <span class="text-red" ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Nome Obrigatorio]</span>
							            <span ng-show="userForm.username.$error.maxlength">Tamanho Máximo 15 caracteres</span>
						                <input id="idAreaName" class="form-control" placeholder="Nome da Area / Matriz" ng-model="selectedCompanyDetector.name" ng-maxlength="15" name="username" required>
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
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">Local</label>
						                <input id="idUnitName" class="form-control" placeholder="Local" ng-model="selectedCompanyDetector.local">
						            </div>
						        </div>
						        <div class="col-md-6">
						        	<div class="form-group">
						                <label class="control-label">Qtde Sensores</label>
						                <input id="idUnitName" class="form-control" placeholder="Sensors" value={{selectedCompanyDetector.detectorDto.sensorsDto.length}} disabled>
						            </div>							        
		                       	</div>		        
						    </div>
					    	<div class="box box-primary box-solid">
					    		<div class="box-header with-border"><strong>Ranges</strong></div>					                	 
				                    <div class="box-body">
								    <div class="row">
									    <div class="col-md-3">
									    	<div class="form-group">
								                <label class="control-label">Máx</label>
								                <input class="form-control" placeholder="Max" ng-model="selectedCompanyDetector.rangeMax">
								            </div>
									    </div>									    
									    <div class="col-md-3">
									    	<div class="form-group">
								                <label class="control-label">Min</label>
								                <input class="form-control" placeholder="Max" ng-model="selectedCompanyDetector.rangeMin">
								            </div>
									    </div>
									    
									    <div class="col-md-3">
									    	<div class="form-group">
								                <label class="control-label">Unidade</label>
								                <input class="form-control" placeholder="Max" ng-model="selectedCompanyDetector.rangeUnit">
								            </div>
									    </div>
									    
									    <div class="col-md-3">
									    	<div class="form-group">
									            <label class="control-label">Unidade de Medida</label>
												<select class="form-control" data-live-search="true" 
						                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
						                                ng-options="item as item.name for item in unitMetersGases | orderBy: 'name' track by item.uid" 
						                                         ng-model="gasUnitMeterGases">
						                                         <option value="">Selecione</option> 
						                        </select>               
					                        </div>
									    </div>
								    </div>
							    </div>
						    </div>
									    					
			       		</form>
			       		<div class="row">
			       			<div class="col-md-12">
			       				<button type="button" ng-click="newCompanyDetector();" class="btn btn-success pull-right">    Nova    </button>								
								<span class="pull-right">   </span>
			       				<button type="button" ng-click="saveCompanyDetector();" class="btn btn-primary pull-right" ng-disabled="(selectedCompanyDetector.name && gasUnitMeterGases) ? false : true">   Salvar   </button>		       				
			       				<span class="pull-right">   </span>
			       				<button type="button" ng-click="deleteCompanyDetector();" class="btn btn-danger pull-right" ng-disabled="(selectedCompanyDetector.uid) ? false : true">   Excluir   </button>								
							</div>
						</div>												
						
			    	         
			       	</div><!-- /.tab-pane -->
			       	<!-- 
			       	<div class="tab-pane" id="tabCompanyDetector_2">		       	        
			       	</div><!-- /.tab-pane -->
			       	
			       	 
			       	<div class="tab-pane" id="tabCompanyDetector_3">	        
			     	</div><!-- /.tab-content -->			     	
			   </div>			   						
			</div>
						
		</div>		
	</div>	
</div>     