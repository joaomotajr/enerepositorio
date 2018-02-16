<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
	
	td.details-control {		
		background: url("/assets/plugins/datatables/images/details_open.png") no-repeat center center;		
		cursor: pointer;
	}	

	tr.shown td.details-control {
		background: url("/assets/plugins/datatables/images/details_close.png") no-repeat center center;
	}	
	
	.selected {		
		font-weight: 800;	
	}
	
</style>

<div class="col-md-9">
	<div data-ng-controller="companyDetectorController">
		<div class="box box-primary">
			<div class="box-header with-border">			
				<strong style="font-size:1.4em"><i class='fa fa-rss'></i> {{selectedCompanyDevice.deviceType}} <span data-ng-show="selectedCompanyGeneric.name">-</span> {{selectedCompanyGeneric.name}}</strong>				
			</div>		
				
			<div class="box-body">
							
				<div class="nav-tabs-custom">
					<ul class="nav nav-tabs tabDetector">
				       	<li class="active"><a href="#tabCompanyDetector_1" id="stepTabDetector_1" data-toggle="tab">Cadastro</a></li>
				       	<li><a href="#tabCompanyDetector_2" id="stepTabDetector_2" data-toggle="tab">Configura&ccedil;&atilde;o</a></li>
				    	<li data-ng-hide="selectedCompanyGeneric" class="pull-right"><i title="[Nenhum Detector Associado ao Dispositivo]" class="fa fa-info-circle text-red"></i></li>				    	
				    </ul>
					
					<div class="tab-content">
				    	
				    	<div class="tab-pane active" id="tabCompanyDetector_1">
				    		<div class="row">
				    			<div class="col-md-12">
					    		<form name="userForm">					    		
									
						    		<div class="col-md-7">
									
								        <div class="col-md-4">
								        	<div class="form-group">
								                <label class="control-label">C&oacute;digo</label>
								                <input class="form-control" placeholder="C&oacute;digo do Detector" data-ng-model="selectedCompanyGeneric.uid" readonly>
								            </div>	
								        </div>
								        <div class="col-md-4">
								            <div class="form-group">								            
								                <label class="control-label">Identifica&ccedil;&atilde;o *</label>
								                <span class="text-red" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Identifica&ccedil;&atilde;o Obrigatorio]</span>
									            <span class="text-red" data-ng-show="userForm.username.$error.maxlength">Tamanho M&aacute;ximo 8 caracteres</span>
												
												<input data-disallow-spaces id="companyDetectorName" class="form-control" style="text-transform:uppercase" 
								                	placeholder="Identifica&ccedil;&atilde;o do Detector (Sem Espa&ccedil;os)" 
								                	data-ng-model="selectedCompanyGeneric.name" data-ng-maxlength="8" name="username" 
								                	title="Identifica&ccedil;&atilde;o do Detector (Sem Espa&ccedil;os)"
								                	required>
								            </div>
								        </div>
								        
								        <div class="col-md-4">
								        	<div class="form-group">
								                <label class="control-label">Nr. de S&eacute;rie</label>
								                <input class="form-control" placeholder="Nro de S&eacute;rie do Detector" data-ng-maxlength="24" 
								                data-ng-model="selectedCompanyGeneric.serialNumber">
								            </div>	
										</div>
																			
								        <div class="col-md-12">
								            <div class="form-group">
								                <label class="control-label">Descri&ccedil;&atilde;o</label>
								                <input class="form-control" placeholder="Descri&ccedil;&atilde;o" data-ng-model="selectedCompanyGeneric.description">
								            </div>
								        </div>								    									
									
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label">Local</label>
												<input id="idUnitName" class="form-control" placeholder="Local" data-ng-model="selectedCompanyGeneric.local">
											</div>
										</div>
									</div>									
					       		</form>
					       		</div>
				    		</div>				    			    					    	
				    	
				       		<div class="row">
				       			<div class="col-md-12">
				       				<button type="button" data-ng-click="saveCompanyDetector();" class="btn btn-primary pull-right" data-ng-disabled="(selectedCompanyGeneric.name && selectedCompanyGeneric.detectorDto.uid) ? false : true">&nbsp;Salvar&nbsp;</button>		       				
				       				<span class="pull-right">&nbsp;</span>
				       				<button type="button" data-ng-click="deleteCompanyDetector();" class="btn btn-danger pull-right" data-ng-disabled="(selectedCompanyGeneric.uid) ? false : true">&nbsp;Excluir&nbsp;</button>								
								</div>
							</div>						
				    	         
				       	</div><!-- /.tab-pane -->
				       	 
				       	<div class="tab-pane" id="tabCompanyDetector_2">
							   
							<div class="row" style="margin-right: 5px !important; margin-left: 5px !important;">			       		 
								<div data-ng-repeat="item in selectedCompanyGenericAlarms">
									
									<div class="col-md-10">
										<div class="panel panel-primary">								                
											<div class="panel-heading">
												<h2 class="panel-title" style="text-align:center;"><strong><i class="fa fa-rss" style="font-size:1.2em;"></i></strong> {{selectedCompanyGenericAlarm.companyDetectorName}}</h2>							
											</div>											   					               	
											<div class="panel-body">							            					                 										                	
												<div class="row">
													<div class="col-md-6">
														<span class="pull-left text-muted" data-ng-if="selectedCompanyGenericAlarm.alarmOn==null"><i class="fa-lg fa fa-bell-slash-o" title="Sem Alarme"></i></span>
														<span class="pull-left text-black" title="{{selectedCompanyGenericAlarm.alarmName}}" data-ng-if="selectedCompanyGenericAlarm.alarmOn==false"><i class="fa-lg fa fa-bell-slash" title="Alarm Off"></i></span>
														<span class="pull-left text-navy" title="{{selectedCompanyGenericAlarm.alarmName}}" data-ng-if="selectedCompanyGenericAlarm.alarmOn==true"><i class="fa-lg fa fa-bell" title=" Alarm On"></i></span>
													</div>
													<div class="col-md-6">
														<a href="#" class="pull-right" data-ng-click="configAlarm($index);" title="Troca/Selecionar Alarme"><i class="fa-lg fa fa-gears" title="Atribuir/Remover Alarme"></i></a>	
													</div>
												</div>																	

												<div class="row">
													<div class="col-md-12">								                	
														<div class="col-md-6">								                	
															<div style=" width: 100%; display: flex; justify-content: center; text-align: center;">														
																<div data-fusioncharts							
																	data-width="180"
																	data-height= "260"																				    						    						    					    						    						    						    															
																	data-type="vled"
																	data-datasource="{{selectedCompanyGenericAlarm.dataSource}}">
																</div>
															</div>
														</div>
														<div class="col-md-6">
															<br>
															<ul class="list-group">																		
																<li class="list-group-item" style="padding: 0px 15px;">
																	<label>Sensor:</label><span class="pull-right">{{selectedCompanyGeneric.detectorDto.sensorDto.name}}</span>
																</li>
																<li class="list-group-item" style="padding: 0px 15px;">
																	<label>G&aacute;s:</label> 
																	<span class="pull-right">
																		{{selectedCompanyGenericAlarm.gasName}}
																		<span style="vertical-align:super;font-size:0.6em;color:orange" data-ng-if="selectedCompanyGenericAlarm.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
																		<span style="vertical-align:super;font-size:0.6em;color:orange" data-ng-if="selectedCompanyGenericAlarm.unitMeterGases!='LEL_PERCENT'"> {{selectedCompanyGenericAlarm.unitMeterGases}}
																	</span>
																</span>
																</li>
																
																<li class="list-group-item" style="padding: 0px 15px;">
																	<label>Range: Min|Man:</label><span class="pull-right">{{selectedCompanyGenericAlarm.rangeMin}} | {{selectedCompanyGenericAlarm.rangeMax}}</span>
																</li>
																<span data-ng-if="!selectedCompanyGenericAlarm.alarmOn">
																		<br><hr><br><br><hr>
																</span>
																<span data-ng-if="selectedCompanyGenericAlarm.alarmOn">
																	<li class="list-group-item" style="padding: 0px 15px;">
																		<label>Alarme Atribu&iacute;do:</label><span class="pull-right">{{selectedCompanyGenericAlarm.alarmName}}</span> 
																	</li>
																	<li class="list-group-item list-group-item-info" style="padding: 2px 15px;">
																		<span class="badge"><i class="fa fa-angle-right"></i> {{selectedCompanyGenericAlarm.alarm1}}</span> <label>Alarme1:</label> Detec&ccedil;&atilde;o
																	</li>
																	<li class="list-group-item list-group-item-warning" style="padding: 2px 15px;">
																		<span class="badge"><i class="fa fa-angle-right"></i> {{selectedCompanyGenericAlarm.alarm2}}</span> <label>Alarme2:</label> Alerta
																	</li>
																	<li class="list-group-item list-group-item-danger" style="padding: 2px 15px;">
																		<span class="badge"><i class="fa fa-angle-right"></i> {{selectedCompanyGenericAlarm.alarm3}}</span> <label>Alarme3:</label> Evacua&ccedil;&atilde;o:
																	</li>
																</span>
															</ul>
														</div>													
													</div>
												</div>
											</div>
										</div>
									</div>									
								</div> 
								<!-- /.tab-repeat -->
							</div>				       		 
						</div>			     			     	
				   </div>			   						
				</div>
							
			</div>		
		</div>		
		
		<div id="modalAlarm" class="modal">                
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">                            
					<div class="modal-body">
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center; font-size: 1.3em;">
								Alarme para o Sensor: <strong>{{selectedCompanyGenericAlarm.sensorName}} </strong> - 
								Artefato: <strong>{{selectedCompanyGenericAlarm.gasName}}</strong> - 
								Valor M&aacute;ximo: <strong>{{selectedCompanyGenericAlarm.rangeMax}}</strong>
							</div>
					  	</div>
				
						<div class="box">
							<div class="box-header">
							  <h3 class="box-title">Cadastro de Alarmes</h3>
							</div>
							<div class="box-body">
								
								<div class="alert alert-warning" role="alert" data-ng-show="msgErroAlarm" >
					           		<button type="button" class="close" ><span data-ng-click="msgErroAlarm='';">&times;</span></button>
					           		<strong>Alerta! </strong>{{msgErroAlarm}} 
					       		</div>
							
								<div style="height: 400px; overflow: auto">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Id.</th>
												<th>Nome</th>
												<th>Artefato</th>                                                    												
												<th>Alarme 1</th>
												<th>Alarme 2</th>
												<th>Alarme 3</th>
												<th>A&ccedil;&atilde;o</th>																		
											</tr>
										</thead>
										<tbody>                                                        
											<tr data-ng-repeat="item in alarms">
												<td>{{item.uid}}</td>
												<td>{{item.name}}</td>
												<td>
													<span data-ng-if="item.deviceType=='DETECTOR'">{{item.gasDto.name}}</span>
													<span data-ng-if="item.deviceType!='DETECTOR'">{{item.deviceType}}</span>
													<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
													<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases!='LEL_PERCENT'"> {{item.unitMeterGases}}
												</td>
												<td>{{item.alarm1}}</td>
												<td>{{item.alarm2}}</td>
												<td>{{item.alarm3}}</td>												
												<td>
													<div data-ng-if="(item.unitMeterGases != selectedCompanyGeneric.detectorDto.sensorDto.unitMeterGases && 
														item.gasDto.name != selectedCompanyGeneric.detectorDto.sensorDto.gasDto.name)">
														<button type="button" class="btn btn-offLine btn-xs" disabled>Incompativel</button>
													</div>																										
													<div data-ng-if="item.uid == selectedCompanyGenericAlarm.alarmId">
														<button type="button" class="btn btn-danger btn-xs" data-ng-click="toggleAlarm(null)">&nbsp;&nbsp;&nbsp;Remover&nbsp;&nbsp;&nbsp;</button>
													</div>
													<div data-ng-if="(item.unitMeterGases == selectedCompanyGeneric.detectorDto.sensorDto.unitMeterGases && 
														item.gasDto.name == selectedCompanyGeneric.detectorDto.sensorDto.gasDto.name) && item.uid != selectedCompanyGenericAlarm.alarmId">
														<button type="button" class="btn btn-primary btn-xs" data-ng-click="toggleAlarm(item)">&nbsp;&nbsp;Selecionar&nbsp;&nbsp;</button>
													</div>													
												</td>													
											</tr>											                                                       
										</tbody>
									</table>
									<p data-ng-hide="alarms.length > 0 " class="text-center">NENHUM ALARME ATENDE ESTE DETECTOR.</p>
								</div>                                                       
							</div>
						</div>				
				  	</div>
				  	<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>                                
				  	</div>
			  	</div>
			</div>		
		</div>	
		
	</div>	
</div>