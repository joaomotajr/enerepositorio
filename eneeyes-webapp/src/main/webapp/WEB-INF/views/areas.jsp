<style>
		float: left;
		width: 650px;
		height: 300px;
		border: 1px solid #000;
		margin-right: 1em;
	}
	
	.fill {
		object-fit: fill;
	}
</style>
   
<div class="col-md-9">
	<div class="box box-primary">
					
		<div class="box-header with-border"><strong style="font-size:1.4em"><i class='fa fa-map-o'></i> �rea: {{selectedArea.name}}</strong></div>
		
		<div class="box-body">
						
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs tabArea">
			       	<li class="active"><a href="#tabArea_1" data-toggle="tab">Cadastro</a></li>
			       	<li><a href="#tabArea_2" data-toggle="tab">Local</a></li>
			       	<li><a href="#tabArea_3" data-toggle="tab">Dispositivos</a></li>
			    	<li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			    </ul>
				
				<div class="tab-content">
			    	
			    	<div class="tab-pane active" id="tabArea_1">
			    		<div class="row">
					        <div class="col-md-6">
					            <div class="form-group">
					                <label class="control-label">Nome</label>
					                <input id="idAreaName" class="form-control" placeholder="Nome da Area / Matriz" ng-model="selectedArea.name">
					            </div>
					        </div>
					
					        <div class="col-md-6">
					            <div class="form-group">
					                <label class="control-label">Descri��o</label>
					                <input class="form-control" placeholder="Descri��o" ng-model="selectedArea.description">
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
				                 	<label><input type="radio" ng-model="selectedUnit.classified" value="false" />&nbsp;	N�o</label>						                
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
			       				<button type="button" ng-click="newArea();" ng-show="btnNewArea" class="btn btn-success pull-right">����Nova����</button>								
								<span class="pull-right">�� </span>
			       				<button type="button" ng-click="saveArea();" class="btn btn-primary pull-right" ng-disabled="(selectedArea.name) ? false : true">���Salvar���</button>		       				
			       				<span class="pull-right">�� </span>
			       				<button type="button" ng-click="deleteArea();" class="btn btn-danger pull-right" ng-disabled="(selectedArea.uid) ? false : true">���Excluir���</button>								
							</div>
						</div>												
																	
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
			       	
			       	<div class="tab-pane" id="tabArea_2">
			       	
			       	<div class="row">
						<div class="col-md-3">					            
								<label class="control-label">Detectores</label>					            
							</div>
							<div class="col-md-9">
								<form>
									<input type="file" id="idInputImageArea" style='display:none'>
									<button id="idBtnChooseFileArea" class="btn btn-sm"><i class="fa fa-file-image-o"></i> Background</button>							        
								</form>						        
							</div>				        
						</div>
						<div class="row">
							<div class="col-md-3">	
							
								<ul class="drag todo-list" style="padding: 1px !important" ng-repeat="item in selectedCompanyDetectorsArea">
	                                 <li style="background: #d1ddf9;">                                                        
	                                     <img class="img-circle img-sm" src="{{item.detectorDto.image}}" alt="user image">
	                                     <span class="text">{{item.name}}</span>
	                                 </li>                                
	                             </ul>
							
								<!-- 
								<div class="box-comment">
									<div class="output" ng-repeat="item in selectedCompanyDetectorsArea">
										<img class="img-circle img-sm" src="{{item.detectorDto.image}}" alt="user image">  
										<div class="comment-text">
											<span class="username">{{item.name}}</span>
											<span>[{{getCompanyDetector(item.detectorDto.image)}}]</span>											
                                		</div>
                                	</div>
                                </div>
								-->
								           					            
							</div>
							<div class="col-md-9">																					    		    
								<img ng-src="{{selectedArea.image}}" onError="this.src='/assets/img/cover.jpg'">								
							</div>					    	
						</div>
			       		         
			       	</div><!-- /.tab-pane -->
			       	
			       	<div class="tab-pane" id="tabArea_3">	        
			     	</div><!-- /.tab-content -->			     	
			   </div>			   						
			</div>
						
		</div>		
	</div>	
</div>     