<style>
	
	.disableDiv {
		pointer-events: none;
		opacity: 0.8;
	}
	
	.fill {
		object-fit: fill;
	}
</style>

	<div class="easy-modal" style="display:none;" modal-position="free">
	    <form>
	        <h3>Dispositivo</h3>
	        
	        <input type="hidden" class="form-control" name="content" placeholder="type">	        
	        <!-- 
	        <select name="uid" class="form-control" id="selCompanyDetector">
	        	<option value="" >Selecione</option>
			    <option value={{item.uid}} ng-repeat="item in selectedCompanyDetectorsArea">{{item.name}}</option>			    
			</select>
			 -->
			 <label>Detector</label>
			 <label>Range</label>
			 <label>Unidade</label>
			<hr>			        
	        <button type="button" class="btn btn-primary easy-submit">OK</button>
	    </form>
	</div>        
	 	 
	<div style="display:none;" width="130" shadow="true" popover>
	    <div style="width:100%;text-align:center;">{[content]}</div>
	</div>

<div class="col-md-9">
	<div class="box box-primary">
					
		<div class="box-header with-border"><strong style="font-size:1.4em"><i class='fa fa-map-o'></i> Área: {{selectedArea.name}}</strong></div>
		
		<div class="box-body">
		
						
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs tabArea">
			       	<li class="active"><a href="#tabArea_1" data-toggle="tab">Cadastro</a></li>
			       	<li><a href="#tabArea_2" data-toggle="tab">Local</a></li>
			       	<li><a href="#tabArea_3" data-toggle="tab">Dispositivos</a></li>
			    	<li title="Trocar Imagem da Área" class="pull-right"><a href="#" id="idBtnChooseFileArea"><i class="fa fa-file-image-o"></i></a></li>			    	
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
						<div class="col-md-2">					            
								<label class="control-label">Detectores</label>					            
							</div>
							<div class="col-md-10">
								<form>
									<input type="file" id="idInputImageArea" style='display:none'>																        
								</form>						        
							</div>				        
						</div>
						
						<div class="row">
							<div class="col-md-2">								
								<div class="box box-primary direct-chat direct-chat-primary">
									<div class="direct-chat-msg right" ng-repeat="item in selectedCompanyDetectorsArea">			                      
			                      		<img class="direct-chat-img img-sm" ng-src="{{item.detectorDto.image}}" style="width: 30px ! important; Height: 30px ! important">
			                      		<div class="direct-chat-text" style="font-size: 0.7em; margin-right: 30px;"  >
			                        		{{item.name}}
			                      		</div><!-- /.direct-chat-text -->
			                    	</div>
								</div>														           					            
							</div>
							
							<div class="col-md-10">
								<div style="overflow: auto">
									<div  id="idImageArea">																					    		    
										<img src="{{selectedArea.image}}" width="1000" onError="this.src='/assets/img/cover.jpg'" class="pin" easypin-id="imgDipositivosArea" />
									</div>									
								</div>
								
								<button type="button" ng-click="lockImageArea();" class="btn btn-sm btn-primary">Lock / Unlock</button>								
							</div>					    	
						</div>
			       		         
			       	</div><!-- /.tab-pane -->
			       	
			       	<div class="tab-pane" id="tabArea_3">
			       		<!--
			       		 <div id="chart_div" style="width: 400px; height: 120px;"></div>
			       		 -->
			       		 
			       		 <div class="row">			       		 
			       		 	<div class="col-md-4" style="padding-left: 0px ! important;" ng-repeat="item in selectedCompanyDetectorsArea">				              
				              
				              <div class="box box-widget widget-user">
				                				                				                
				                <div class="widget-user-header bg-aqua-active">
				                  <h3 class="widget-user-username">{{item.name}}</h3>
				                  <h5 class="widget-user-desc">Max: {{item.rangeMax}} | Min: {{item.rangeMin}}</h5>
				                </div>
				                				                
				                <div style="position:absolute; top: 65px ! important;">
					                <div class="col-md-6" style="padding-left: 0px ! important;">					            	    
						            	<div id="chart_div1"></div>						                					                
					                </div>				                
					                <div class="col-md-6" style="padding-left: 0px ! important;">						                
						            	<div id="chart_div2"></div>						            	    
					                </div>				                
				                </div>
				                
				                <div class="box-footer">
				                  <div class="row">
				                    <br>				                    				                    
				                    <div ng-repeat="subItem in item.detectorDto.sensorsDto">				                    			                    
					                    <div class="col-md-6">
					                      <div class="description-block">
					                        <h5 class="description-header">{{subItem.name}}</h5>
					                        <span class="description-text">{{subItem.detectionType}}</span>
					                      </div>
					                    </div>					                    
				                    </div>
				                    
				                  </div>				                  
				                </div>
				              </div>
				            </div>				            
							       		 
			       		 </div>
			       		 
			       		  
			       		 	        
			     	</div><!-- /.tab-content -->			     	
			   </div>			   						
			</div>
						
		</div>		
	</div>	
</div>     