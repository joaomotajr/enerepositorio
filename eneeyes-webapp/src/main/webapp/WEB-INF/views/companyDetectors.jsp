 <style>
	.todo-list>li {
	    padding: 4px;
	}
	
	img {
	  *	float: left;
	  *	width: 150px;
	  *	height: 100px;
	  *	border: 1px solid #000;
	  *	margin-right: 1em;
	   	width: auto;
    	max-height: 160px;
    	height: 160px;
    	background-size: cover;
    	background-position: 50% 50%;
	}
		
</style>

<div class="col-md-9">

	<div ng-controller="companyDetectorController">
	<div class="box box-primary">					
		<div class="box-header with-border"><strong style="font-size:1.4em"><i class='fa fa-rss'></i> {{selectedCompanyDevice.deviceType}}</strong></div>		
		<div class="box-body">
						
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs tabDetector">
			       	<li class="active"><a href="#tabCompanyDetector_1" id="stepTabDetector_1" data-toggle="tab">Cadastro</a></li>
			       	<li><a href="#tabCompanyDetector_2" id="stepTabDetector_2" data-toggle="tab">Configura��o</a></li>
			       	<li><a href="#tabCompanyDetector_3" id="stepTabDetector_3" data-toggle="tab">Hist�rico</a></li>
			    	<li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			    </ul>
				
				<div class="tab-content">
			    	
			    	<div class="tab-pane active" id="tabCompanyDetector_1">
			    		<form name="userForm">			    			
				    		<div class="row">							
								<div class="col-md-4">							    
								    <input type="file" id="idInputImageDetector" style='display:none'>							    
								    <div class="box box-primary">
						                <div class="box-body box-profile">						                    
						                    <img class="profile-user-img img-responsive img-circle" style="margin: 0 auto" ng-src="{{selectedCompanyDetector.detectorDto.image}}" onError="this.src='/assets/img/cover.jpg'">
						                    <p class="text-muted text-center ng-binding">{{selectedCompanyDetector.detectorDto.name}} - {{selectedCompanyDetector.detectorDto.model}}</p>						                    					                    	                
						                </div>
					                </div>					                															
								</div>
								<div class="col-md-2">
					            	<label></label>
					            </div>
															
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
								</div>							
							</div>
							
				    		<div class="row">				    			
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">Nome</label>
						                <span class="text-red" ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Nome Obrigatorio]</span>
							            <span ng-show="userForm.username.$error.maxlength">Tamanho M�ximo 15 caracteres</span>
						                <input id="idAreaName" class="form-control" placeholder="Nome do Detector" ng-model="selectedCompanyDetector.name" ng-maxlength="15" name="username" required>
						            </div>
						        </div>
								
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">Descri��o</label>
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
						    		    					
			       		</form>
			       		<div class="row">
			       			<div class="col-md-12">
			       				<button type="button" ng-click="newCompanyDetector();" class="btn btn-success pull-right">����Nova����</button>								
								<span class="pull-right">�� </span>
			       				<button type="button" ng-click="saveCompanyDetector();" class="btn btn-primary pull-right" ng-disabled="(selectedCompanyDetector.name) ? false : true">���Salvar���</button>		       				
			       				<span class="pull-right">�� </span>
			       				<button type="button" ng-click="deleteCompanyDetector();" class="btn btn-danger pull-right" ng-disabled="(selectedCompanyDetector.uid) ? false : true">���Excluir���</button>								
							</div>
						</div>												
						
			    	         
			       	</div><!-- /.tab-pane -->
			       	 
			       	<div class="tab-pane" id="tabCompanyDetector_2">		     
			       	
			       		<div class="row">			       		 
			       		 	<div>				              
				            	
				            	<div ng-if="selectedCompanyDetector.detectorDto.sensorsDto.length > 1">
				            	  	<div class="col-md-6">
						              	<div class="panel panel-success">				              
							                
							                <div class="panel-heading">
										    	<h3 class="panel-title" style="text-align:center;">{{selectedCompanyDetector.name}}</h3>							
										   	</div>
										   					               	
							               	<div class="panel-body">							            					                 		
							                	<div ng-repeat="subItem in selectedCompanyDetector.detectorDto.sensorsDto">				                				                
									            	<div class="col-md-6">						                
										            	<div id="{{'sensor_' + subItem.$$hashKey}}"></div>						            	    
									            	</div>				                
									            </div>							                				                				                
							                	<div class="row">							                    				                    				                    
								                	<div ng-repeat="subItem in selectedCompanyDetector.detectorDto.sensorsDto">				                    			                    
									                	<div class="col-md-6">
									                    	<div class="description-block">
									                      		<h5 class="description-header">{{subItem.name}}</h5>
									                      		<span class="description-text" style="font-size:0.7em">{{subItem.detectionType}} Min|Max: {{subItem.rangeMin}} | {{subItem.rangeMax}} </span>
										                	</div>
									                    </div>					                    
								                    </div>						                    					                    					                					                  			                  
								                </div>					                
							              	</div> <!-- Panel Body -->				              	   
						              	</div> <!-- Panel  -->
					              	</div>  <!-- Col 06  -->
				              	</div>
				              	
				              	<div ng-if="selectedCompanyDetector.detectorDto.sensorsDto.length == 1">
				            	  	<div class="col-md-3">
						              	<div class="panel panel-success">				              
							                
							                <div class="panel-heading">
										    	<h3 class="panel-title">{{selectedCompanyDetector.name}}</h3>							
										   	</div>
										   					               	
							               	<div class="panel-body">							            					                 		
							                	<div ng-repeat="subItem in selectedCompanyDetector.detectorDto.sensorsDto">									            							                
										        	<div id="{{'sensor_' + subItem.$$hashKey}}"></div>									            					                
									            </div>							                				                				                
							                	<div class="row">							                    				                    				                    
								                	<div ng-repeat="subItem in selectedCompanyDetector.detectorDto.sensorsDto">									                	
								                    	<div class="description-block">
								                      		<h5 class="description-header"> {{subItem.name}}</h5>
								                      		<span class="description-text" style="font-size:0.7em">{{subItem.detectionType}} Min|Man: {{subItem.rangeMin}} | {{subItem.rangeMax}} </span>
									                	</div>									                    					                    
								                    </div>						                    					                    					                					                  			                  
								                </div>					                
							              	</div> <!-- Panel Body -->				              	   
						              	</div> <!-- Panel  -->
					              	</div>  <!-- Col 06  -->
				              	</div>
				              	
				              	
				              	              				              
				          	</div> <!-- Repeat Detectores -->		            
							       		 
			       		 </div>  
			       	  	        
			       	</div>
			       	
			       	 
			       	<div class="tab-pane" id="tabCompanyDetector_3">	        
			     	</div><!-- /.tab-content -->			     	
			   </div>			   						
			</div>
						
		</div>		
	</div>
	</div>	
</div>     