<style>
	
	.disableDiv {
		pointer-events: none;
		opacity: 0.8;
	}
	
	.fill {
		object-fit: fill;
	}	
	
	div.chart_div table {
	    width: auto;
	    margin: 0 auto !important;
	}
	
</style>

<div class="easy-modal" style="display:none;" modal-position="">
    <form>
        <h3>Dispositivo</h3>        
        <input type="hidden" class="form-control" name="content" placeholder="type">
		 <label>Detector</label>
		 <label>Range</label>
		 <label>Unidade</label>
		<hr>			        
        <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
    </form>
</div>        
 	 
<div style="display:none;" width="100" shadow="true" popover>
    <div style="width:100%;text-align:center">{[content]}</div>
</div>
	
<div data-ng-controller="areaController">
	<div class="col-md-9">
	
		<div class="box box-primary">					
			<div class="box-header with-border"><strong style="font-size:1.4em"><i class='fa fa-map-o'></i> &aacute;rea: {{selectedArea.name}}</strong></div>		
			<div class="box-body">		
							
				<div class="nav-tabs-custom">
					<ul class="nav nav-tabs tabArea">
				       	<li class="active"><a href="#tabArea_1" id="stepTabArea_1" data-toggle="tab">Cadastro</a></li>
				       	<li><a href="#tabArea_2" id="stepTabArea_2" data-toggle="tab">Local</a></li>
				       	<li><a href="#tabArea_3" id="stepTabArea_3" data-toggle="tab">Dispositivos</a></li>
				    	<li title="Trocar Imagem da Área" class="pull-right"><a href="#" id="idBtnChooseFileArea"><i class="fa fa-file-image-o"></i></a></li>			    	
				    </ul>
					
					<div class="tab-content">
								    	
				    	<div class="tab-pane active" id="tabArea_1">
				    		<div class="row">
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">Nome *</label>
						                <input id="idAreaName" class="form-control" placeholder="Nome da Area / Matriz" ng-model="selectedArea.name">
						            </div>
						        </div>					
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">Descri&ccedil;&atilde;o</label>
						                <input class="form-control" placeholder="Descri&ccedil;&atilde;o" ng-model="selectedArea.description">
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
	      								<label><input type="radio" ng-model="selectedArea.classified" value="1" />&nbsp; Sim</label>        
					                 	<label><input type="radio" ng-model="selectedArea.classified" value="0" />&nbsp;	N&atilde;o</label>						                
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
						
						        <div class="col-md-4"></div>
							</div>				
				       		
				       		<div class="row">
				       			<div class="col-md-12">
				       				<button type="button" ng-click="newArea();" ng-show="btnNewArea" class="btn btn-success pull-right">&nbsp;&nbsp;Nova&nbsp;&nbsp;&nbsp;&nbsp;</button>								
									<span class="pull-right">&nbsp;&nbsp;</span>
				       				<button type="button" ng-click="saveArea();" class="btn btn-primary pull-right" ng-disabled="(selectedArea.name) ? false : true">&nbsp;&nbsp;Salvar&nbsp;&nbsp;</button>		       				
				       				<span class="pull-right">&nbsp;&nbsp;</span>
				       				<button type="button" ng-click="deleteArea();" class="btn btn-danger pull-right" ng-disabled="(selectedArea.uid) ? false : true">&nbsp;&nbsp;Excluir&nbsp;&nbsp;</button>								
								</div>
							</div>												
																		
							<div class="row">
								<div class="col-md-6">
									<label>Incluir Dispositivo</label>						
									<div class="entry input-group">					
								        
								        <select class="form-control" data-live-search="true" 
				                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
				                                ng-options="item as item.description for item in deviceTypes | orderBy: 'type' track by item.uid" 
				                                         ng-model="selectedDeviceType">
				                                         <option value="">Selecione</option> 
				                        </select>
				                                       
								        <span class="input-group-btn">
								        <button class="btn btn-info btn-flat" type="button" ng-click="saveCompanyDeviceInit();">OK</button>
								        </span>
									</div>
								</div>
							</div>				    	         
				       	</div>
				       	
				       	<div class="tab-pane" id="tabArea_2">				       	
				       		<div class="row">
								<div class="col-md-2">					            
									<label class="control-label">Dispositivos</label>					            
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
				                      		<img class="direct-chat-img" ng-src="{{item.detectorDto.image}}" style="width: auto ! important; height: 30px ! important ; max-height: 30px ! important;">
				                      		<div class="direct-chat-text" style="font-size: 0.7em; margin-right: 40px;"  >
				                        		{{item.name}}
				                      		</div>
				                    	</div>
									</div>														           					            
								</div>
								
								<div class="col-md-10">
									<div style="height: 430px; overflow: auto">
										<div  id="idImageArea">																					    		    
											<img src="{{selectedArea.image}}" style="width: 1000px; height: 400px; max-height: 400px;" onError="this.src='/assets/img/cover.jpg'" class="pin" easypin-id="imgDipositivosArea" />
										</div>									
									</div>
									
									<button type="button" ng-click="lockImageArea();" class="btn btn-sm btn-primary">
										<i ng-hide="!isLock" class="fa fa-lock"></i>
										<i ng-hide="isLock" class="fa fa-unlock"></i>
										&nbsp;{{btnLockUnlock}}
									</button>								
								</div>					    	
							</div>				       		         
				       	</div>
				       	
				       	<div class="tab-pane" id="tabArea_3">				       					       					       		 
				       		<div class="row">			       		 
				       		 	<div ng-repeat="item in selectedCompanyDetectorsArea">										
					              	
					            	  	<div class="col-md-3">
							              	<div class="panel panel-primary">								                
								                <div class="panel-heading">
											    	<h2 class="panel-title" style="text-align:center;">{{item.name}}</h2>							
											   	</div>											   					               	
								               	<div class="panel-body">							            					                 										                	
													<div class="row">								                	
														<img class="profile-user-img img-responsive imgDetector" style="margin: 0 auto" 
														data-ng-src="{{item.detectorDto.image}}" onError="this.src='/assets/img/cover.jpg'">												
						                			</div>								                				                				                
								                	<div class="row">							                    				                    				                    
									                	<div>									                	
									                    	<div class="description-block">																
									                      		<h4 class="description-header"><i class="fa fa-bolt"></i> {{item.detectorDto.sensorDto.name}}</h4>
									                      		<span class="description-text">Range: Min|Man: {{item.detectorDto.sensorDto.rangeMin}} | {{item.detectorDto.sensorDto.rangeMax}} </span>
																<br>
																G&aacute;s: <strong class="text-navy">{{item.detectorDto.sensorDto.gasDto.name}}</strong>																
																<span style="vertical-align:super;font-size:0.6em;color:orange" data-ng-if="item.sensorDto.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
																<span style="vertical-align:super;font-size:0.6em;color:orange" data-ng-if="item.sensorDto.unitMeterGases!='LEL_PERCENT'"> {{item.detectorDto.sensorDto.unitMeterGases}}</span>
										                	</div>									                    					                    
									                    </div>						                    					                    					                					                  			                  
									                </div>					                
								              	</div>
							              	</div>
						              	</div>
					              	
					          	</div>								       		 
				       		</div>				       		 	        
				     	</div>
				    </div>			   						
				</div>							
			</div>
		</div>			
	</div>	
</div>