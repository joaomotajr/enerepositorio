
	<style>
		.todo-list>li {
		    padding: 4px;
		}		
		.disableDiv {
			pointer-events: none;
			opacity: 0.5;
		}		
		.row {
			padding-bottom: 5px !important;
		}		
		.box {
			margin-bottom: 5px !important;
		}
		 .multipleMessages {
		 	color: #9f3a38;
    		background: antiquewhite;
		 }			
	</style>
		
	<div data-ng-controller="alarmController as alarmController">
												
		<div class="row">				                                                    
			<div class="col-md-10">                                                        
				<div class="box box-primary" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de Alarmes</h3>
					  <a href="#" class="text-muted pull-right" data-ng-click="refreshAlarms();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto;">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Empresa</th>
										<th>Nome</th>
										<th>Dispositivo</th>
										<th>Artefato</th>										
										<th>Unit</th>                                                            
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr data-ng-repeat="item in alarms" data-ng-class="{'danger' : item.alarmOn == false, 'success' : item.alarmOn == true}">
										<td>{{item.companyDto.name}}</td>
										<td>{{item.name}}</td>
										<td>
											<span data-ng-if="item.deviceType!='DETECTOR'">STANDALONE</span>
											<span data-ng-if="item.deviceType=='DETECTOR'">{{item.deviceType}}</span>											
										</td>
										<td>
											<span data-ng-if="item.deviceType=='DETECTOR'">{{item.gasDto.name}}</span>
											<span data-ng-if="item.deviceType!='DETECTOR'">{{item.deviceType}}</span>											
										</td>
										<td>
											<span>{{item.unitMeterGases}}</span>
										</td>
										<td>
											<button type="button" class="btn btn-primary btn-xs" data-ng-click="editAlarm($index)">editar</button>
										</td>
										<td>
											<a type="button" class="btn btn-danger btn-xs" data-popover=' do Alarme: [ {{item.name}} ]' data-confirm="deleteAlarm($index)">excluir</a>
										</td>						
									</tr>                                                               
								</tbody>
							</table>
						</div>                                                       
					</div>
					
					<div class="box-footer">						                                                                
						<button type="button" data-ng-click="clearFormAlarm(); userForm.$setPristine()" class="btn btn-primary pull-right" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#modalAlarmEdit">Novo</button>
					</div>
				</div>
				
				<div id="resultErro" class="alert alert-warning" role="alert" data-ng-show="msgErro" >
            		<button type="button" class="close" ><span data-ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
			</div>                                                     
			
		</div>

		<div id="modalDevicesConn" class="modal fade">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" align="center">Dispositivos Associados a este Alarme</h4>
					</div>
					
						<div class="box box-primary">
							<div class="box-header with-border"><strong><i class="fa fa-rss"></i> Dispositivos:</strong></div>
							
							<div class="box-body">									        		                                                                                       
								
								<div style="max-height: 200px ! important; height:auto; overflow: auto; font-size: 0.9em  ! important">
									<p data-ng-show="!usedAlarms || usedAlarms.length <= 0" class="text-center">NENHUM DETECTOR ASSOCIADO</p>
									
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Identificacao</th>
												<th>Dispositivo</th>
												<th>Local</th>
												
											</tr>
										</thead>
										<tbody>                                                        
											<tr data-ng-repeat="item in usedAlarms">
												<td>{{item.companyDetectorName}}</td>
												<td>{{item.deviceName}}</td>
												<td>{{item.companyDeviceLocal}}</td>
											</tr>                                                               
										</tbody>
									</table>	

								</div>					                                
							</div>
								
						</div>								        	
					
					
				</div>
			</div>
		</div>
				
		<div id="modalAlarmEdit" class="modal fade">
			<div class="modal-dialog  modal-lg" role="document">
				<div class="modal-content">                            
					<div class="modal-body"style="padding-bottom: 0px; padding-top: 5px;">
							
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title">Cadastro / Edi&ccedil;&atilde;o de Alarmes</h3>
								<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
							</div>
												
							<div class="box-body" style="padding-bottom: 0px !important;">
								<form class="form" name="userForm">		
								
									<div class="row">

										<div class="col-md-3" style="padding-right: 5px !important;">	
											<label class="control-label">Nome
												<strong class="text-red pull-right" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">&nbsp[Nome Obrigat&oacute;rio]</strong> 
												<strong class="text-red pull-right" data-ng-show="userForm.username.$error.maxlength">Tamanho M&aacute;ximo 12 caracteres</strong>
											</label>
										
											<div data-ng-class="{'has-error': userForm.username.$dirty && userForm.username.$invalid}">
												<input class="form-control inputProfile" placeholder="Nome do Alarme" data-ng-model="alarmName" data-ng-maxlength="15" name="username" required>
											</div>
										</div>										
										
										<div class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important;">	
											<label class="control-label">Empresa
												<strong class="text-red pull-right" data-ng-show="userForm.companyName.$dirty && userForm.companyName.$invalid">&nbspCampo Obrigat&oacute;rio</strong>
											</label>			
											<div data-ng-class="{'has-error': userForm.companyName.$dirty && userForm.companyName.$invalid}">
													<jsp:include page="controls/companySelect.jsp"/>							                        							                             
											</div>	
										</div>								

										<div class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important;">															
											<label class="control-label">Dispositivo
												<span class="text-red pull-right" data-ng-show="userForm.deviceType.$dirty && userForm.deviceType.$invalid">&nbsp[Campo Obrigat&oacute;rio]</span>
											</label>
											<div data-ng-class="{'has-error': userForm.deviceType.$dirty && userForm.deviceType.$invalid}">               							                		
										
												<select name="deviceType" class="form-control" data-live-search="true" 
													style="width: 100%;" tabindex="-1" aria-hidden="true"                              
													data-ng-options="item as item.name for item in deviceTypes | orderBy: 'name' track by item.uid" 
													data-ng-model="deviceType" required>
													<option value="">Selecione</option> 
												</select>
										
											</div>
										</div>

										<div class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important;">
											<label class="control-label">Associados : </label>											
											<a title="Ver Dispostivos associados a este Alarme" data-toggle="modal" href="#modalDevicesConn" class="form-control btn btn-primary"> Listar <i class="fa fa-eye"></i></a>
										</div>
										              
					                </div>    
					                									
									<div class="row">    
										<div class="col-md-12">                  
					                       	<div class="box box-info" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
					                       	
								    			<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Limites do Alarme </strong> &nbsp&nbsp&nbsp&nbsp&nbsp
													<div class="btn-group  pull-right">																
														<button class="btn btn-xs" ng-click="update(true);" ng-class="(radioModel) ? 'btn-success' : 'btn-default'">ON</button>																
														<button class="btn btn-xs" ng-click="update(false);" ng-class="(radioModel) ? 'btn-default' : 'btn-danger'">OFF</button>													   
													</div>															
								    			</div>
								    								                	 
							                    <div class="box-body" style="padding-bottom: 0px !important">						                            	
													<div id="travar">
														<div class="row">	
															<div  data-ng-show="deviceType.name=='DIGITAL'">																
																<div class="col-md-6">
																	<label><strong><i class="fa fa-flash"> </i> Alarmar se Circuito estiver:</strong></label>
																	<div class="form-control" style="padding-top:0px">																	
																		<div class="radio3 radio-check radio-inline">
																			<input type="radio" id="radio11" value="1" data-ng-model="deviceTypeDigital" data-ng-click="gasUnitMeterGases.uid=12; alarmAlarm1=1" >																			
																			<label for="radio11">Aberto </label>
																		</div>
																		<div class="radio3 radio-check radio-inline">
																			<input type="radio" id="radio12" value="0" data-ng-model="deviceTypeDigital" data-ng-click="gasUnitMeterGases.uid=12; alarmAlarm1=0">
																			<label for="radio12">Fechado </label>
																		</div>																	
																	</div>                                                    
																</div>																														
															</div>
													
															<div  data-ng-show="deviceType.name!='DIGITAL' && deviceType.name != undefined">
																<div class="col-md-3" style="padding-right: 5px !important;" data-ng-show="deviceType.name=='DETECTOR'">
																	<label class="control-label"><i class="fa fa-yelp"></i> G&aacute;s
																		<span class="text-red pull-right" data-ng-show="userForm.gasName.$dirty && userForm.gasName.$invalid">  [Campo Obrigat&oacute;rio]</span>
																	</label>
																	<div data-ng-class="{'has-error': userForm.gasName.$dirty && userForm.gasName.$invalid}">
																		<select name="gasName" class="form-control" data-live-search="true" 
																			style="width: 100%;" tabindex="-1" aria-hidden="true"                              
																				data-ng-options="item as item.name for item in gases | orderBy: 'name' track by item.uid" 
																					data-ng-model="alarmGas" data-ng-required="deviceType.name=='DETECTOR'">
																					<option value="">Selecione</option>			                                           
																		</select>											                		
																	</div>
																</div>		
																																
																<div class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important;">
																	<label class="control-label">																		
																			<strong data-ng-show="deviceType.name=='DETECTOR'"><i class="fa fa-tachometer"> </i> Unidade:</strong>
																			<strong data-ng-show="deviceType.name=='ELETRICITY'"><i class="fa fa-plug"> </i> Eletricidade Medida em:</strong>
																			<strong data-ng-show="deviceType.name=='TIME'"><i class="fa fa-clock-o"> </i> Tempo Contado em:</strong>
																			<strong data-ng-show="deviceType.name=='TEMPERATURE'"><i class="fa fa-thermometer"> </i> Temperatura Calculada em:</strong>
																			<strong data-ng-show="deviceType.name=='FLOW'"><i class="fa fa-database"> </i> Vazão Medida em:</strong>

																			<span class="text-red" data-ng-show="userForm.gasUnit.$dirty && userForm.gasUnit.$invalid">  [Campo Obrigat&oacute;rio]</span>
																	</label>
																	<div data-ng-class="{'has-error': userForm.gasUnit.$dirty && userForm.gasUnit.$invalid}">
																		<select name="gasUnit" class="form-control" data-live-search="true" 
																				style="width: 100%;" tabindex="-1" aria-hidden="true"                              
																					data-ng-options="item as item.name for item in unitMetersGases | orderBy: 'name' track by item.uid" 
																								data-ng-model="gasUnitMeterGases" required>
																								<option value="">Selecione</option> 
																		</select>									                        
																	</div>
																</div>	

																<div class="col-md-3" data-ng-show="deviceType.name!='DETECTOR'"></div>
															
																<div class="col-md-2" style="padding-right: 5px !important">
																	<div class="form-group">
																		<label class="control-label">Alarme 1 <span class="label label-default" style="font-size: 0.5em"> DETEC&Ccedil;&Atilde;O </span></label>
																		
																		<div class="col-md-6" data-ng-class="{'has-error': userForm.alarmAlarm1.$invalid && !userForm.alarmAlarm1.$pristine || errorAlarm1}" 
																			style="padding-right: 1px !important; padding-left: 1px !important" title="Maior Que">	
																			<input type="text" class="form-control" name="alarmAlarm1" 
																				data-ng-model="alarmAlarm1"
																				data-ng-change="validAlarms($event);" />
																		</div>																	
																		<div class="col-md-6" data-ng-class="{'has-error': userForm.alarmAlarm11.$invalid && !userForm.alarmAlarm11.$pristine || errorAlarm11}" 
																			style="padding-right: 1px !important; padding-left: 1px !important" title="Menor Que">
																			<input type="text" class="form-control" name="alarmAlarm11" 
																				data-ng-model="alarmAlarm11" 
																				data-ng-change="validAlarms($event);" />
																		</div>																	
																	</div>
																</div>
																
																<div class="col-md-2" style="padding-right: 5px !important; padding-left: 5px !important">															
																	<div class="form-group">
																		<input class="pull-left" type="checkbox" data-ng-model="enableAlarm2" data-ng-change="validAlarms($event);" />&nbsp
																		<label class="control-label"> Alarme 2 <span class="label label-warning" style="font-size: 0.5em"> ALERTA </span></label>
																		
																		<div class="col-md-12" data-ng-class="{'has-error': userForm.alarmAlarm2.$invalid && !userForm.alarmAlarm2.$pristine || errorAlarm2}" 
																			style="padding-right: 1px !important; padding-left: 1px !important" title="Maior Que">	
																			<input type="text" class="form-control" name="alarmAlarm2"
																				data-ng-disabled="!enableAlarm2" 
																				data-ng-model="alarmAlarm2" 
																				data-ng-change="validAlarms($event);" />
																		</div>																	
																		<!-- <div class="col-md-6" data-ng-class="{'has-error': userForm.alarmAlarm22.$invalid && !userForm.alarmAlarm22.$pristine || errorAlarm22}" 
																			style="padding-right: 1px !important; padding-left: 1px !important" title="Menor Que">
																			<input type="text" class="form-control" name="alarmAlarm22" 
																				data-ng-disabled="!enableAlarm2" 
																				data-ng-model="alarmAlarm22" 
																				data-ng-change="validAlarms($event);" />
																		</div>																	 -->
																	</div>
																</div>
																
																<div class="col-md-2" style="padding-left: 5px !important">
																	<div class="form-group">
																		<input class="pull-left" type="checkbox" data-ng-model="enableAlarm3" />&nbsp
																		<label class="control-label"> Alarme 3 <span class="label label-danger" style="font-size: 0.5em"> EVACUA&Ccedil;&Atilde;O </span></label>
																		
																		<div class="col-md-12" data-ng-class="{'has-error': userForm.alarmAlarm3.$invalid && !userForm.alarmAlarm3.$pristine || errorAlarm3}" 
																			style="padding-right: 1px !important; padding-left: 1px !important" title="Maior Que">	
																			<input type="text" class="form-control" name="alarmAlarm3" 
																				data-ng-disabled="!enableAlarm3"
																				data-ng-model="alarmAlarm3" 
																				data-ng-change="validAlarms($event);"  />
																		</div>																	
																		<!-- <div class="col-md-6" data-ng-class="{'has-error': userForm.alarmAlarm33.$invalid && !userForm.alarmAlarm33.$pristine || errorAlarm33}" 
																			style="padding-right: 1px !important; padding-left: 1px !important" title="Menor Que">
																			<input type="text" class="form-control" name="alarmAlarm33" 
																				data-ng-disabled="!enableAlarm3"
																				data-ng-model="alarmAlarm33" 
																				data-ng-change="validAlarms($event);" />
																		</div>																	 -->
																	</div>
																</div>
															</div>
														</div>
														<hr style="margin-top: 8px !important; margin-bottom: 8px !important;">
														<div class="row">
																
															<div class="col-md-12">
																<div class="col-md-3">					            				
																	<div class="checkbox3 checkbox-inline checkbox-check checkbox-round checkbox-light">
																		<input type="checkbox" id="checkboxSigmaOnOff" checked>
																		<label for="checkboxSigmaOnOff">Integra&ccedil;&atilde;o Sigma?</label>
																	</div>
																</div>
																<div class="col-md-3">
																	<div class="checkbox3 checkbox-inline checkbox-check checkbox-round checkbox-light">												            			
																		<input type="checkbox" id="checkboxOfflineOnOff" checked>
																		<label for="checkboxOfflineOnOff">Habilitar Alarme OFFLine?</label>
																	</div>
																</div>
																<div class="col-md-6">					            				
																	<div class="checkbox3 checkbox-inline checkbox-check checkbox-round checkbox-light">												            			
																		<input type="checkbox" id="checkboxSonoroOnOff" checked>
																		<label for="checkboxSonoroOnOff">Emitir Alarme Sonoro?</label>
																	</div>
																</div>																	
															</div>	
														</div>			

														<div class="row">
															<div class="col-md-12">
																<div class="col-md-2">										            									            				
																	<div class="checkbox3 checkbox-inline checkbox-check checkbox-round checkbox-light">
																		<input type="checkbox" 
																		id="checkboxEmailOnOff" checked>
																		<label for="checkboxEmailOnOff">Enviar E-MAIL? </label>
																	</div>
																</div>
																
																<div class="col-md-4" style="padding-left: 5px !important; padding-right: 5px !important">
																	<div data-ng-class="{'has-error': !emailValid}">	
																		<div class="input-group">								                                        	
																			<span class="input-group-addon" data-ng-show="emailValid">@</span>													                    														                    	
																			<span class="input-group-addon text-red" data-ng-hide="emailValid">@</span>
																			<input id="alarmEmail" data-ng-model="email" type="text" class="form-control" 
																				placeholder="Email" data-ng-change="validEmail($event);">
																		</div>
																	</div>						                                         												            			
																</div>
																
																<div class="col-md-2">										            									            				
																	<div class="checkbox3 checkbox-inline checkbox-check checkbox-round checkbox-light">												            			
																		<input type="checkbox" id="checkboxSmsOnOff" checked>
																		<label for="checkboxSmsOnOff">Enviar SMS? </label>
																	</div>
																</div>
																
																<div class="col-md-4" style="padding-left: 5px !important;">
																	<div data-ng-class="{'has-error': !mobileValid}">	
																		<div class="input-group">
																			<span class="input-group-addon" data-ng-show="mobileValid"><i class="fa fa-phone-square"></i></span>
																			<span class="input-group-addon text-red" data-ng-hide="mobileValid"><i class="fa fa-phone-square"></i></span>
																			
																			<input class="form-control alarmCelularMask" 
																				id="alarmCelular" data-ng-model="celular" 
																				type="text" maxlength="15" placeholder="(XX) XXXXX-XXXX" data-ng-change="validMobile($event);" />													                    	
																		</div>
																	</div>						                                         													            			
																</div>														    	
															
															</div>
														</div>														
														
														<div class="row">
															<div class="col-md-12">
																<div class="col-md-2">												            		
																</div>
																
																<div class="col-md-4" style="padding-left: 5px !important; padding-right: 5px !important">
																	<div data-ng-class="{'has-error': !emailValid1}">	
																		<div class="input-group">								                                        	
																			<span class="input-group-addon" data-ng-show="emailValid1">@</span>													                    														                    	
																			<span class="input-group-addon text-red" data-ng-hide="emailValid1">@</span>
																			<input 
																				id="alarmEmail1" 
																				data-ng-model="email1" type="text" class="form-control" 
																				placeholder="Email" 
																				data-ng-change="validEmail($event);">
																		</div>
																	</div>						                                         												            			
																</div>
																
																<div class="col-md-2">												            			
																</div>
																
																<div class="col-md-4" style="padding-left: 5px !important;">
																	<div data-ng-class="{'has-error': !mobileValid1}">	
																		<div class="input-group">
																			<span class="input-group-addon" data-ng-show="mobileValid1"><i class="fa fa-phone-square"></i></span>
																			<span class="input-group-addon text-red" data-ng-hide="mobileValid1"><i class="fa fa-phone-square"></i></span>
																			
																			<input class="form-control alarmCelularMask" 
																				id="alarmCelular1" 
																				data-ng-model="celular1" 
																				type="text" maxlength="15" placeholder="(XX) XXXXX-XXXX" data-ng-change="validMobile($event);" />
																																						
																		</div>
																	</div>						                                         													            			
																</div>
															
															
															</div>
														</div>
																									
														<!-- 
														<div class="row">
															<div class="col-md-12">
																<div class="col-md-2">										            									            				
																	<div class="checkbox3 checkbox-round">
																		<input type="checkbox" 
																		id="checkboxEmailOnOff" checked>
																		<label for="checkboxEmailOnOff">Enviar E-MAIL? </label>
																	</div>
																</div>
																
																<div class="col-md-4" style="padding-left: 5px !important; padding-right: 5px !important">
																	<div data-ng-class="{'has-error': !emailValid}">	
																		<div class="input-group">								                                        	
																			<span class="input-group-addon" data-ng-show="emailValid">@</span>													                    														                    	
																			<span class="input-group-addon text-red" data-ng-hide="emailValid">@</span>
																			<input 
																				id="alarmEmail" 
																				data-ng-model="email" type="text" class="form-control" 
																				placeholder="Email" 
																				data-ng-change="validEmail($event);">
																		</div>
																	</div>						                                         												            			
																</div>			            			
																
																<div class="col-md-2">
																	<div class="checkbox3 checkbox-muted checkbox-inline checkbox-check checkbox-light">
																		<input class="checkboxEmail" type="checkbox" id="checkboxEmail1" checked disabled>
																		<label for="checkboxEmail1">Alarme 1</label>
																	</div>
																</div>
																<div class="col-md-2">
																	<div class="checkbox3 checkbox-warning checkbox-inline checkbox-check checkbox-light">
																		<input class="checkboxEmail" type="checkbox" id="checkboxEmail2" checked disabled>
																		<label for="checkboxEmail2">Alarme 2</label>
																	</div>
																</div>
																<div class="col-md-2">
																	<div class="checkbox3 checkbox-danger checkbox-inline checkbox-check checkbox-light">
																		<input class="checkboxEmail" type="checkbox" id="checkboxEmail3" checked disabled>
																		<label for="checkboxEmail3">Alarme 3</label>
																	</div>
																</div>
															</div>	
														</div>	
															
														<div class="row">
															<div class="col-md-12">
																<div class="col-md-2">										            									            				
																	<div class="checkbox3 checkbox-round">
																		<input type="checkbox" id="checkboxSmsOnOff" checked>
																		<label for="checkboxSmsOnOff">Enviar SMS? </label>
																	</div>
																</div>
																
																<div class="col-md-4" style="padding-left: 5px !important; padding-right: 5px !important">
																	<div data-ng-class="{'has-error': !mobileValid}">	
																		<div class="input-group">
																			<span class="input-group-addon" data-ng-show="mobileValid"><i class="fa fa-phone-square"></i></span>
																			<span class="input-group-addon text-red" data-ng-hide="mobileValid"><i class="fa fa-phone-square"></i></span>
																			<input class="form-control" 
																				id="alarmCelular" 
																				data-ng-model="celular" 
																				type="text" maxlength="15" placeholder="(XX) XXXXX-XXXX" data-ng-change="validMobile($event);" />													                    	
																		</div>
																	</div>						                                         													            			
																</div>			            			
																
																<div class="col-md-2">
																	<div class="checkbox3 checkbox-muted checkbox-inline checkbox-check checkbox-light" style="color:gray">
																		<input class="checkboxSms" type="checkbox" id="checkboxSms1" checked disabled>
																		<label for="checkboxSms1">Alarme 1</label>
																	</div>
																</div>
																<div class="col-md-2">
																	<div class="checkbox3 checkbox-warning checkbox-inline checkbox-check checkbox-light">
																		<input class="checkboxSms" type="checkbox" id="checkboxSms2" checked disabled>
																		<label for="checkboxSms2">Alarme 2</label>
																	</div>
																</div>
																<div class="col-md-2">
																	<div class="checkbox3 checkbox-danger checkbox-inline checkbox-check checkbox-light">
																		<input class="checkboxSms" type="checkbox" id="checkboxSms3" checked disabled>
																		<label for="checkboxSms3">Alarme 3</label>
																	</div>
																</div>
															</div>	
														</div>	
															-->
														<hr style="margin-top: 8px !important; margin-bottom: 8px !important;">

														<div class="row"  style="padding-bottom: 5px !important">
															<div class="col-md-12">
																<div class="col-md-2">										            									            				
																	<div class="checkbox3 checkbox-round">
																		<input type="checkbox" id="checkboxActionOff" checked>
																		<label for="checkboxActionOff">A&ccedil;&otilde;es a Realizar? </label>
																	</div>
																</div>
																<div class="travarAction">            
																	<div class="col-md-10" style="padding-left: 5px !important;">								                                        
																		<div class="entry input-group" id="">
																			<span class="input-group-addon btn-add text-white bg-gray"><i class="fa fa-bullhorn">&nbsp;</i>Alarme 1</span>  					
																			<textarea class="form-control" rows="1" data-ng-model="action1" placeholder="Provid&ecirc;ncias do Agente de Monitoramento se Houver alarme n&iacute;vel 1 (Detec&ccedil;&atilde;)" maxlength="250"></textarea>																				                                   						
																		</div>
																	</div>
																</div>                                        						                                        						                                         													            			
															</div>							                                   
														</div>
														<div class="travarAction">    
															<div class="row" style="padding-bottom: 5px !important">
																<div class="col-md-12">
																	<div class="col-md-2">												            			
																	</div>							                                        
																	<div class="col-md-10" style="padding-left: 5px !important; ">								                                        
																		<div class="entry input-group">
																			<span class="input-group-addon btn-add text-white bg-orange"><i class="fa fa-bullhorn">&nbsp;</i>Alarme 2</span>  					
																			<textarea class="form-control" data-ng-model="action2" rows="1" placeholder="Provid&ecirc;ncias do Agente de Monitoramento se Houver alarme n&iacute;vel 2 (Alerta)" maxlength="250"></textarea>																				                                   						
																		</div>
																	</div>                                        						                                        						                                         													            			
																</div>							                                   
															</div>
															
															<div class="row">
																<div class="col-md-12">
																	<div class="col-md-2">												            			
																	</div>							                                        
																	<div class="col-md-10" style="padding-left: 5px !important; ">								                                        
																		<div class="entry input-group">
																			<span class="input-group-addon btn-add text-white bg-red"><i class="fa fa-bullhorn">&nbsp;</i>Alarme 3</span>  					
																			<textarea class="form-control" data-ng-model="action3" rows="1" placeholder="Provid&ecirc;ncias do Agente de Monitoramento se Houver alarme 3 (Evacua&ccedil;&atilde;o)" maxlength="250"></textarea>																				                                   						
																		</div>
																	</div>                                        						                                        						                                         													            			
																</div>							                                   
															</div>	
																							
															<div class="row">
																<div class="col-md-12">
																	<div class="col-md-2">												            			
																	</div>							                                        
																	<div class="col-md-10" style="padding-left: 5px !important; ">								                                        
																		<div class="entry input-group">
																			<span class="input-group-addon btn-add text-white bg-black"><i class="fa fa-bullhorn">&nbsp;</i>Off - Line</span>  					
																			<textarea class="form-control" data-ng-model="action4" rows="1" placeholder="Provid&ecirc;ncias do Agente de Monitoramento se Dispositivo n&atilde;o Envia Dados " maxlength="250"></textarea>																				                                   						
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
					            						            		
					            </form>
							</div>							

							 <div class="ui multipleMessages">
								<ul class="list" data-ng-repeat="item in alarmMessageError">
									<li class="text-red" data-bind-unsafe-html="item" />								
								</ul>
							</div>

						</div>						
										
				  	</div>
				  	
				  	<div class="modal-footer" style="padding-top: 1px">						
						<button type="button" data-ng-click="clearFormAlarm(); userForm.$setPristine()" class="btn btn-default" data-dismiss="modal">Cancelar</button>                                                                
						<button type="button" data-ng-click="saveAlarm();" class="btn btn-primary" data-dismiss="modal"
							data-ng-disabled="(emailValid && mobileValid && emailValid1 && mobileValid1 && userForm.$valid && !(errorAlarm1 || errorAlarm11 || errorAlarm2 || errorAlarm22 || errorAlarm3 || errorAlarm33)) ? false : true">&nbsp;Salvar&nbsp;
						</button>						                                
				  	</div>
				  	
			  	</div>
			</div>		
		</div>
				
	</div>

    
    