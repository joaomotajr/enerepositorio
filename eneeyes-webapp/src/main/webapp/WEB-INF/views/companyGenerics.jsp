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
	<div data-ng-controller="companyGenericController">
		<div class="box box-primary">
			<div class="box-header with-border">			
				<strong style="font-size:1.4em"><i class='fa fa-rss'></i> {{selectedCompanyDevice.deviceType}} <span data-ng-show="selectedCompanyGeneric.name">-</span> {{selectedCompanyGeneric.name}}</strong>				
			</div>		
				
			<div class="box-body">
							
				<div class="nav-tabs-custom">
					<ul class="nav nav-tabs tabGeneric">
				       	<li class="active"><a href="#tabCompanyGeneric_1" id="stepTabGeneric_1" data-toggle="tab">Cadastro</a></li>
				       	<li><a href="#tabCompanyGeneric_2" id="stepTabGeneric_2" data-toggle="tab">Configura&ccedil;&atilde;o</a></li>
				    	<li data-ng-hide="selectedCompanyGeneric" class="pull-right"><i title="[Nenhuma Associação ao Dispositivo]" class="fa fa-info-circle text-red"></i></li>				    	
				    </ul>
					
					<div class="tab-content">
				    	
				    	<div class="tab-pane active" id="tabCompanyGeneric_1">
				    		<div class="row">
				    			<div class="col-md-6">
									<form name="userForm">					    		
									
											<div class="col-md-4" style="padding-right: 5px !important;">
												<div class="form-group">
													<label class="control-label">C&oacute;digo</label>
													<input class="form-control" placeholder="C&oacute;digo do Dispositivo" data-ng-model="selectedCompanyGeneric.uid" readonly>
												</div>	
											</div>
											<div class="col-md-4" style="padding-right: 5px !important; padding-left: 5px !important;">
												<div class="form-group">								            
													<label class="control-label">Identifica&ccedil;&atilde;o *</label>
													<span class="text-red" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Identifica&ccedil;&atilde;o Obrigat&eacute;rio]</span>
													<span class="text-red" data-ng-show="userForm.username.$error.maxlength">Tamanho M&aacute;ximo 8 caracteres</span>
													
													<input data-disallow-spaces class="form-control" style="text-transform:uppercase" 
														placeholder="Identifica&ccedil;&atilde;o do Dispositivo (Sem Espa&ccedil;os)" 
														data-ng-model="selectedCompanyGeneric.name" data-ng-maxlength="8" name="username" 
														title="Identifica&ccedil;&atilde;o do Dispositivo (Sem Espa&ccedil;os)"
														required>
												</div>
											</div>											
											<div class="col-md-4" style="padding-left: 5px !important;">
												<div class="form-group">
													<label class="control-label">Nr. de S&eacute;rie</label>
													<input class="form-control" placeholder="Nro de S&eacute;rie do Dispositivo" data-ng-maxlength="24" 
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
									</form>
								</div>
								<div class="col-md-6">
										
										<div class="panel panel-primary" data-ng-if="selectedCompanyGenericPosition.uid">

											<div class="panel-heading">
												<h3 class="panel-title" style="text-align:center;">{{selectedCompanyGeneric.genericDto.name}} - {{selectedCompanyGeneric.genericDto.model}}</h3>												
											</div>	

											<div class="panel-body" style="padding-bottom: 1px">						    																							
												<ul class="list-group">													
													<li class="list-group-item" style="padding: 1px 8px;">
														<label><i class="fa fa-feed"></i>&nbsp;Dispositivo:&nbsp;</label>{{selectedCompanyGeneric.genericDto.deviceType}}
													</li>													
													<li class="list-group-item" style="padding: 1px 8px;">
															<label><i class="fa fa-feed"></i>&nbsp;Medi&ccedil;&atilde;o: em:&nbsp;</label>{{selectedCompanyGeneric.genericDto.unitMeterGases}}
														</li>
													<li class="list-group-item" style="padding: 1px 8px;">
														<label>Id:</label>&nbsp;{{selectedCompanyGenericPosition.uid}}
													</li>
													<li class="list-group-item" style="padding: 1px 8px;" title="Data/Hora: {{selectedCompanyGenericPosition.lastUpdate | date:'dd/MM/yyyy HH:mm'}}">
														<label>Status:</label>&nbsp;{{selectedCompanyGenericPosition.alarmType}}&nbsp;&nbsp;&nbsp;<label>Medi&ccedil;&atilde;o:</label>&nbsp;{{selectedCompanyGenericPosition.lastValue}} 
													</li>
												</ul>
											</div>
										</div>	

										<div data-ng-if="!selectedCompanyGenericPosition.uid">
											<label class="control-label">Selecione Dispositivo Compatível *</label>
											<table class="table table-bordered table-hover">
												<thead>
													<tr>														
														<th>Dispositivo</th>
														<th>Modelo</th>
														<th>Unidade</th>																
														<th>Ação</th>																																						
													</tr>
												</thead>
												<tbody>                                                        
													<tr data-ng-repeat="item in generics | filter: {deviceType: selectedCompanyDevice.deviceType }">
														
														<td data-ng-class="{'selected': item.uid == selectedCompanyGeneric.genericDto.uid }">{{item.name}}</td>
														<td data-ng-class="{'selected': item.uid == selectedCompanyGeneric.genericDto.uid }">{{item.model}}</td>
														<td data-ng-class="{'selected': item.uid == selectedCompanyGeneric.genericDto.uid }">{{item.unitMeterGases}}</td>
														<td>																	
															<div data-ng-if="item.uid == selectedCompanyGeneric.genericDto.uid">
																<button type="button" class="btn btn-danger btn-xs" data-ng-click="selecionarGeneric(item)" disabled>Selecionado</button>
															</div>	
															<div data-ng-if="item.uid != selectedCompanyGeneric.genericDto.uid">
																<button type="button" class="btn btn-xs" data-ng-class="(selectedCompanyGeneric.uid == null) ? 'btn-primary' : 'btn-default'" 
																data-ng-click="selecionarGeneric(item)" data-ng-disabled="selectedCompanyGeneric.uid != null"> Selecionar </button>
															</div>																							    								
														</td>																		
													</tr>                                                               
												</tbody>
											</table>
											<p data-ng-show="generics == undefined || generics.list.length == 0" class="text-center">Nenhum Dispositivo Compatível</p>
										</div>									
								</div>
				    		</div>				    			    					    	
				    	
				       		<div class="row">
				       			<div class="col-md-12">
				       				<button type="button" data-ng-click="saveCompanyGeneric();" class="btn btn-primary pull-right" data-ng-disabled="(selectedCompanyGeneric.name && selectedCompanyGeneric.genericDto.uid) ? false : true">&nbsp;Salvar&nbsp;</button>		       				
				       				<span class="pull-right">&nbsp;</span>
									   <button type="button" data-ng-click="deleteCompanyGeneric();" class="btn btn-danger pull-right" data-ng-if=selectedCompanyGeneric.uid">&nbsp;Excluir&nbsp;</button>
									   <button type="button" data-ng-click="deleteCompanyDevice();" class="btn btn-danger pull-right" data-ng-disabled="(selectedCompanyDevice.uid) ? false : true">&nbsp;Excluir&nbsp;</button>
								</div>
							</div>						
				    	         
				       	</div><!-- /.tab-pane -->
				       	 
				       	<div class="tab-pane" id="tabCompanyGeneric_2">
							   
							<div class="panel panel-primary">								                
								<div class="panel-heading">
									<h2 class="panel-title" style="text-align:center;"><strong><i class="fa fa-rss" style="font-size:1.2em;"></i></strong> {{selectedCompanyDeviceAlarm.companyDetectorName}}</h2>							
								</div>											   					               	
								<div class="panel-body">
									<div style="height: 250px; overflow: auto">
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
														<jsp:include page="controls/reduzMeters.jsp"/>
													</td>
													<td>{{item.alarm1}} <span data-ng-if="item.alarm11">/{{item.alarm11}}</span></td>													
													<td>{{item.alarm2}}</td>
													<td>{{item.alarm3}}</td>												
													<td>
														<div data-ng-if="(item.unitMeterGases != selectedCompanyGeneric.genericDto.unitMeterGases || 
															item.deviceType != selectedCompanyGeneric.companyDeviceDto.deviceType)">
															<button type="button" class="btn btn-offLine btn-xs" disabled>Incompativel</button>
														</div>																										
														<div data-ng-if="item.uid == selectedCompanyDeviceAlarm.alarmId">
															<button type="button" class="btn btn-danger btn-xs" data-ng-click="toggleAlarm(null)">&nbsp;&nbsp;&nbsp;Remover&nbsp;&nbsp;&nbsp;</button>
														</div>
														<div data-ng-if="(item.unitMeterGases == selectedCompanyGeneric.genericDto.unitMeterGases && 
															item.deviceType == selectedCompanyGeneric.companyDeviceDto.deviceType) && item.uid != selectedCompanyDeviceAlarm.alarmId">
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
				   </div>			   						
				</div>
							
			</div>		
		</div>		
	</div>	
</div>