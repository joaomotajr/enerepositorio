
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
										<th>Artefato</th>										
										<th>Unidade</th>                                                            
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr data-ng-repeat="item in alarms" data-ng-class="{'danger' : item.alarmOn == false, 'success' : item.alarmOn == true}">
										<td>{{item.companyDto.name}}</td>
										<td>{{item.name}}</td>										
										<td>
											<span data-ng-if="item.deviceType.type=='DETECTOR'">{{item.gasDto.name}}</span>
											<span data-ng-if="item.deviceType.type!='DETECTOR'">{{item.deviceType.description}}</span>
										</td>
										<td><span>{{item.unitMeter.symbol}}</span></td>
										<td><button type="button" class="btn btn-primary btn-xs" data-ng-click="editAlarm($index)">editar</button></td>
										<td><a type="button" class="btn btn-danger btn-xs" data-popover=' do Alarme: [ {{item.name}} ]' data-confirm="deleteAlarm($index)">excluir</a></td>						
									</tr>                                                               
								</tbody>
							</table>
						</div>                                                       
					</div>					
					<div class="box-footer">						                                                                
						<button type="button" data-ng-click="clearFormAlarm(); userForm.$setPristine()" class="btn btn-primary pull-right" 
							data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#modalAlarmEdit">Novo</button>
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
						<h4 class="modal-title" style="text-align:center">Dispositivos Associados a este Alarme</h4>
					</div>
					
					<div class="box box-primary">
						<div class="box-header with-border"><strong>Dispositivos:</strong></div>						
						<div class="box-body">								
							<div style="max-height: 200px ! important; height:auto; overflow: auto; font-size: 0.9em  ! important">
								<p data-ng-show="!usedAlarms || usedAlarms.length <= 0" class="text-center">NENHUM DETECTOR ASSOCIADO</p>									
								<table data-ng-hide="!usedAlarms || usedAlarms.length <= 0" class="table table-hover">
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
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">                            
					<div class="modal-body"style="padding-bottom: 0px; padding-top: 5px;">							
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title">Cadastro / Edi&ccedil;&atilde;o de Alarmes</h3>
								<span class="pull-right">&nbsp&nbsp&nbsp&nbsp<i class="fas fa-power-off fa-2x" ng-class="(radioModel) ? 'text-green':'text-red'"></i></span>								
								<div class="btn-group pull-right" style="font-size: 115%;">
									<button class="btn btn-xs" ng-click="update(true);"  ng-class="(radioModel) ? 'btn-success':'btn-default'">ON</button>
									<button class="btn btn-xs" ng-click="update(false);" ng-class="(radioModel) ? 'btn-default':'btn-danger'">OFF</button>
								</div>
							</div>												
							<div class="box-body" style="padding-bottom: 0px !important;">
								<form class="form" name="userForm">								
									<div class="row">
										<div class="col-md-3" style="padding-right: 5px !important;">	
											<label class="control-label">Nome
												<strong class="text-red pull-right" style="font-stretch: extra-condensed;" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">&nbsp[Obrigat&oacute;rio]</strong> 
												<strong class="text-red pull-right" style="font-stretch: extra-condensed;" data-ng-show="userForm.username.$error.maxlength">&nbsp [M&aacute;ximo 12 caracteres]</strong>
											</label>										
											<div data-ng-class="{'has-error': userForm.username.$dirty && userForm.username.$invalid}">
												<input class="form-control inputProfile" style="text-transform:uppercase" placeholder="Nome do Alarme" 
													data-ng-model="alarmName" data-ng-maxlength="15" name="username" required>
											</div>
										</div>										
										<div class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important;">	
											<label class="control-label">Empresa
												<strong class="text-red pull-right" style="font-stretch: extra-condensed;" data-ng-show="userForm.companyName.$dirty && userForm.companyName.$invalid">&nbsp[Obrigat&oacute;rio]</strong>
											</label>			
											<div data-ng-class="{'has-error': userForm.companyName.$dirty && userForm.companyName.$invalid}">
													<jsp:include page="controls/companySelect.jsp"/>							                        							                             
											</div>	
										</div>
										<div class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important;">															
											<label class="control-label">Artefato
												<span class="text-red pull-right" style="font-stretch: extra-condensed;" data-ng-show="userForm.deviceType.$dirty && userForm.deviceType.$invalid">&nbsp[Obrigat&oacute;rio]</span>
											</label>
											<div data-ng-class="{'has-error': userForm.deviceType.$dirty && userForm.deviceType.$invalid}">										
												<select name="deviceType" class="form-control" data-live-search="true" 
													style="width: 100%;" tabindex="-1" aria-hidden="true"                              
													data-ng-options="item as item.description for item in deviceTypes | orderBy: 'type' track by item.uid" 
													data-ng-model="deviceType" required>
													<option value="">Selecione</option>
												</select>										
											</div>
										</div>
										<div class="col-md-1" style="padding-left: 5px !important; padding-right: 5px !important;">
											<label class="control-label" title="Tempo em Minutos"> ANALISE</label>
											<input type="number" data-ng-model="ANALISE" title="Tempo em Minutos" class="form-control" />
										</div>
										<div class="col-md-2" data-ng-class="{'disableDiv': !usedAlarms || usedAlarms.length <=0}" class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important;">
											<label class="control-label">Dispositivos: {{usedAlarms.length}} </label>
											<a title="Ver Dispostivos associados a este Alarme" data-toggle="modal" href="#modalDevicesConn" class="form-control btn btn-primary"> Listar <i class="fa fa-eye"></i></a>
										</div>										              
					                </div>
					                									
									<div class="row">    
										<div class="col-md-12">                  
					                       	<div class="box box-info" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">					                       	
												<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Limites do Alarme </strong> &nbsp&nbsp&nbsp&nbsp&nbsp
													<label style="margin-left: 420px;"><i class="fa fa-eye"></i> Monitoramento: </label>
													<div class="btn-group pull-right">				
														<button class="btn btn-xs" ng-click="update2(true);"  ng-class="(radioModel2) ? 'btn-success':'btn-default'">AUTO</button>
														<button class="btn btn-xs" ng-click="update2(false);" ng-class="(radioModel2) ? 'btn-default':'btn-danger'">MANUAL</button>														
													</div>													
								    			</div>								    								                	 
							                    <div class="box-body" style="padding-bottom: 0px !important">						                            	
													<div id="travar">
														<div class="row">															
															<div data-ng-show="deviceType.type != undefined">
																<div class="col-md-3" data-ng-show="deviceType.type=='DETECTOR'" style="padding-right: 5px !important;">
																	<label class="control-label"><i class="fa fa-yelp"></i> G&aacute;s
																		<span class="text-red pull-right" style="font-stretch: extra-condensed;" data-ng-show="userForm.gasName.$dirty && userForm.gasName.$invalid"> [Obrigat&oacute;rio]</span>
																	</label>
																	<div data-ng-class="{'has-error': userForm.gasName.$dirty && userForm.gasName.$invalid}">
																		<select name="gasName" class="form-control" data-live-search="true" 
																			style="width: 100%;" tabindex="-1" aria-hidden="true"                              
																				data-ng-options="item as item.name for item in gases | orderBy: 'name' track by item.uid" 
																					data-ng-model="alarmGas" data-ng-required="deviceType.type=='DETECTOR'">
																					<option value="">Selecione</option>			                                           
																		</select>
																	</div>
																</div>
																<div class="col-md-3" data-ng-show="deviceType.type!='DETECTOR'" style="padding-right: 5px !important;">
																	<label class="control-label"><i class="fa" data-ng-class="deviceType.symbol"> </i> {{deviceType.type}}</label>																	
																	<input type="text"  class="form-control" data-ng-model="deviceType.description" disabled />
																</div>																																
																<div class="col-md-3" style="padding-right: 5px !important;">
																	<label class="control-label">
																		<strong><i class="fa fa-tachometer"></i> Unidade:</strong>
																		<span class="text-red" style="font-stretch: extra-condensed;" data-ng-show="userForm.unitMeterName.$dirty && userForm.unitMeterName.$invalid"> [Obrigat&oacute;rio]</span>
																	</label>
																	<div data-ng-class="{'has-error': userForm.unitMeterName.$dirty && userForm.unitMeterName.$invalid}">
																		<select name="unitMeterName" class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true" 
																			data-ng-change="unitMeterChange();"
																			data-ng-options="item as item.description for item in unitMeters | orderBy: 'symbol' track by item.uid" 
																			data-ng-model="unitMeter" required>
																			<option value="">Selecione</option> 
																		</select>
																	</div>
																</div>																
																<div class="col-md-6" data-ng-show="unitMeter.uid==0">																	
																	<label><strong><i class="fa" data-ng-class="deviceType.symbol"> </i> Alarmar se circuito estiver:</strong></label>
																	<div class="form-control" style="padding-top:0px">																	
																		<div class="radio3 radio-check radio-inline">
																			<input type="radio" id="radio11" value="1" data-ng-model="deviceTypeDigital" data-ng-click="alarmAlarm1=1" >																			
																			<label for="radio11">Aberto </label>
																		</div>
																		<div class="radio3 radio-check radio-inline">
																			<input type="radio" id="radio12" value="0" data-ng-model="deviceTypeDigital" data-ng-click="alarmAlarm1=0">
																			<label for="radio12">Fechado </label>
																		</div>																	
																	</div>																	
																</div>															
																<div data-ng-hide="unitMeter.uid==0">
																	<div class="col-md-2" style="padding-right: 1px !important">
																		<div class="form-group">
																			<label class="control-label">																				
																				<span data-ng-class="{'text-red': errorPerfil1}" style="font-stretch: condensed;" >Alarme 1</span>
																				<span class="label label-default cursor" title="Perfil de Alerta do Alarme 1" data-ng-click="changePerfilAlarm1(true)" style="font-size: 0.6em" data-ng-bind="perfilAlarm1.type"></span>
																				<span class="label label-default cursor" title="Perfil de Alerta do Alarme 1" data-ng-click="changePerfilAlarm1(true)" style="font-size: 0.6em" data-ng-show="!perfilAlarm1">Selecionar</span>																				
																			</label>
																			
																			<select data-ng-show="showPerfilAlarm1" data-ng-change="validAlarms($event); changePerfilAlarm1(false)" 
																				class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true"
																				data-ng-options="item as item.type for item in perfilAlarms | orderBy: 'type' track by item.uid" 
																				data-ng-model="perfilAlarm1">
																				<option value="">Selecione</option> 
																			</select>

																			<div class="col-md-6" data-ng-class="{'has-error': userForm.alarmAlarm1.$invalid && !userForm.alarmAlarm1.$pristine || errorAlarm1}" 
																				style="padding-right: 1px !important; padding-left: 1px !important" title="Maior Que">	
																				<input type="text" class="form-control" name="alarmAlarm1" data-ng-model="alarmAlarm1" data-ng-change="validAlarms($event);" />
																			</div>

																			<div class="col-md-6" data-ng-class="{'has-error': userForm.alarmAlarm11.$invalid && !userForm.alarmAlarm11.$pristine || errorAlarm11}" 
																				style="padding-right: 1px !important; padding-left: 1px !important" title="Menor Que">
																				<input type="text" class="form-control" name="alarmAlarm11" data-ng-model="alarmAlarm11" data-ng-change="validAlarms($event);" />
																			</div>																	
																		</div>
																	</div>																	
																	<div class="col-md-2" style="padding-right: 1px !important; padding-left: 1px !important">															
																		<div class="form-group">
																			<input class="pull-left" type="checkbox" data-ng-model="enableAlarm2" data-ng-change="validAlarms($event);"/>
																			<label class="control-label">
																				<span data-ng-class="{'text-red': errorPerfil2}" style="font-stretch: condensed;">Alarme 2</span>
																				<span class="label label-warning cursor" title="Perfil de Alerta do Alarme 2" data-ng-click="changePerfilAlarm2(true)" style="font-size: 0.6em" data-ng-bind="perfilAlarm2.type"></span>
																				<span class="label label-warning cursor" title="Perfil de Alerta do Alarme 2"data-ng-click="changePerfilAlarm2(true)" style="font-size: 0.6em" data-ng-show="!perfilAlarm2">Selecionar</span>
																			</label>
																			
																			<select name="unitMeter" data-ng-show="showPerfilAlarm2" data-ng-change="validAlarms($event); changePerfilAlarm2(false)" 
																				class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true"
																				data-ng-options="item as item.type for item in perfilAlarms | orderBy: 'type' track by item.uid" 
																				data-ng-model="perfilAlarm2">
																				<option value="">Selecione</option> 
																			</select>

																			<div class="col-md-12" data-ng-class="{'has-error': userForm.alarmAlarm2.$invalid && !userForm.alarmAlarm2.$pristine || errorAlarm2}" 
																				style="padding-right: 1px !important; padding-left: 1px !important" title="Maior Que">	
																				<input type="text" class="form-control" name="alarmAlarm2" data-ng-disabled="!enableAlarm2" data-ng-model="alarmAlarm2" data-ng-change="validAlarms($event);" />
																			</div>
																		</div>
																	</div>																	
																	<div class="col-md-2" style="padding-left: 1px !important;padding-right: 1px !important;">
																		<div class="form-group">
																			<input class="pull-left" type="checkbox" data-ng-model="enableAlarm3"/>
																			<label class="control-label">
																				<span data-ng-class="{'text-red': errorPerfil3}" style="font-stretch: condensed;">Alarme 3</span> 
																				<span class="label label-danger cursor" title="Perfil de Alerta do Alarme 3" data-ng-click="changePerfilAlarm3(true)" style="font-size: 0.6em" data-ng-bind="perfilAlarm3.type"></span>
																				<span class="label label-danger cursor" title="Perfil de Alerta do Alarme 3" data-ng-click="changePerfilAlarm3(true)" style="font-size: 0.6em" data-ng-show="!perfilAlarm3">Selecionar</span>
																			</label>

																			<select name="unitMeter" data-ng-show="showPerfilAlarm3" data-ng-change="validAlarms($event); changePerfilAlarm3(false)" 
																				class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true"
																				data-ng-options="item as item.type for item in perfilAlarms | orderBy: 'type' track by item.uid" 
																				data-ng-model="perfilAlarm3">
																				<option value="">Selecione</option> 
																			</select>

																			<div class="col-md-12" data-ng-class="{'has-error': userForm.alarmAlarm3.$invalid && !userForm.alarmAlarm3.$pristine || errorAlarm3}" 
																				style="padding-right: 1px !important; padding-left: 1px !important" title="Maior Que">	
																				<input type="text" class="form-control" name="alarmAlarm3" data-ng-disabled="!enableAlarm3" data-ng-model="alarmAlarm3" data-ng-change="validAlarms($event);" />
																			</div>
																		</div>
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
																<div class="col-md-3">
																	<div class="checkbox3 checkbox-inline checkbox-check checkbox-round checkbox-light">												            			
																		<input type="checkbox" id="checkboxSonoroOnOff" checked>
																		<label for="checkboxSonoroOnOff">Emitir Alarme Sonoro?</label>
																	</div>
																</div>
																<div class="col-md-3">																	
																	<button type="button" class="btn btn-default pull-right" data-ng-disabled="!alarmUid" data-backdrop="static" 
																		data-keyboard="false" data-toggle="modal" data-target="#modalAlarmFeedback"><i class="fas fa-sms"></i> SMS & <i class="fa fa-envelope" aria-hidden="true"></i> E-Mail</button>																												
																</div>																	
															</div>	
														</div>														
														<hr style="margin-top: 8px !important; margin-bottom: 8px !important;">
														<div class="row"  style="padding-bottom: 5px !important">
															<div class="col-md-12">
																<div class="col-md-2">										            									            				
																	<div class="checkbox3 checkbox-round">
																		<input type="checkbox" id="checkboxActionOff" checked>
																		<label for="checkboxActionOff">A&ccedil;&otilde;es? </label>
																	</div>
																</div>
																<div class="travarAction">            
																	<div class="col-md-10" style="padding-left: 5px !important;">								                                        
																		<div class="entry input-group">
																			<span class="input-group-addon btn-add text-white bg-gray"><i class="fa fa-bullhorn">&nbsp;</i>Alarme &nbsp;&nbsp;I</span>  					
																			<textarea class="form-control" rows="1" data-ng-model="action1" placeholder="Provid&ecirc;ncias do Agente de Monitoramento se Houver alarme n&iacute;vel I (Detec&ccedil;&atilde;o)" maxlength="250"></textarea>
																		</div>
																	</div>
																</div>                                        						                                        						                                         													            			
															</div>							                                   
														</div>
														<div class="travarAction" data-ng-show="deviceType.type!='DIGITAL'">
															<div class="row" style="padding-bottom: 5px !important">
																<div class="col-md-12">
																	<div class="col-md-2"></div>							                                        
																	<div class="col-md-10" style="padding-left: 5px !important; ">								                                        
																		<div class="entry input-group">
																			<span class="input-group-addon btn-add text-white bg-orange"><i class="fa fa-bullhorn">&nbsp;</i>Alarme &nbsp;II</span>  					
																			<textarea class="form-control" data-ng-model="action2" rows="1" placeholder="Provid&ecirc;ncias do Agente de Monitoramento se Houver alarme n&iacute;vel II (Alerta)" maxlength="250"></textarea>
																		</div>
																	</div>                                        						                                        						                                         													            			
																</div>							                                   
															</div>															
															<div class="row">
																<div class="col-md-12">
																	<div class="col-md-2"></div>							                                        
																	<div class="col-md-10" style="padding-left: 5px !important; ">								                                        
																		<div class="entry input-group">
																			<span class="input-group-addon btn-add text-white bg-red"><i class="fa fa-bullhorn">&nbsp;</i>Alarme III</span>  					
																			<textarea class="form-control" data-ng-model="action3" rows="1" placeholder="Provid&ecirc;ncias do Agente de Monitoramento se Houver alarme n&iacute;vel III (Evacua&ccedil;&atilde;o)" maxlength="250"></textarea>
																		</div>
																	</div>                                        						                                        						                                         													            			
																</div>							                                   
															</div>																							
															<div class="row">
																<div class="col-md-12">
																	<div class="col-md-2"></div>							                                        
																	<div class="col-md-10" style="padding-left: 5px !important; ">								                                        
																		<div class="entry input-group">
																			<span class="input-group-addon btn-add text-white bg-black"><i class="fa fa-bullhorn">&nbsp;</i>Off - Line&nbsp;</span>  					
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
						<button type="button" data-ng-click="saveAlarm(); userForm.$setPristine();" class="btn btn-primary"
							data-ng-disabled="(alarmAlarm1 && userForm.$valid && !userForm.$pristine && 
							!(errorAlarm1 || errorAlarm11 || errorAlarm2 || errorAlarm22 || errorAlarm3 || errorAlarm33 || errorPerfil1 || errorPerfil2 || errorPerfil3)) ? false : true">&nbsp;Salvar&nbsp;
						</button>						                                
				  	</div>				  	
			  	</div>
			</div>		
		</div>

		<div id="modalAlarmFeedback" class="modal">
			<div class="modal-dialog" style="width: 750px !important;" role="document">
				<div class="modal-content">					
					<div class="modal-content">                            
						<div class="modal-body">
							<div class="box box-primary">
								<div class="box-header">
									<h3 class="box-title">ALARME FEEDBACK'S</h3>							
									<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
								</div>											
								<div class="box-body">
									<div class="box-body" style="padding-left: 5px;">									
										<div class="row">
											<div class="col-md-6" style="padding-left: 5px !important; padding-right: 5px !important">
												<div class="box box-primary" style="padding-left: 5px !important; padding-right: 5px !important">
													<div class="box-header">
														<h3 class="box-title">Enviar E-MAIL:</h3>
														<span class="text-muted pull-right"><i class="fas fa-at"></i></span>
													</div>
													<div class="box-body" data-ng-class="{'disableDivOver': !emails.length}">
														<input type="checkbox" data-ng-change="updateFeedbackAlarm('E-Mail para o Alarme Nível I', alarmEmail1);" data-ng-model="alarmEmail1" />
														<span style="font-stretch: condensed; font-weight: 800;">Alarme I</span>
														&nbsp;&nbsp;&nbsp;
														<input type="checkbox" data-ng-change="updateFeedbackAlarm('E-Mail para o Alarme Nível II', alarmEmail2);" data-ng-model="alarmEmail2" />
														<span style="font-stretch: condensed; font-weight: 800;">Alarme II</span>
														&nbsp;&nbsp;&nbsp;
														<input type="checkbox" data-ng-change="updateFeedbackAlarm('E-Mail para o Alarme Nível III', alarmEmail3);" data-ng-model="alarmEmail3" />
														<span style="font-stretch: condensed; font-weight: 800;">Alarme III</span>
														&nbsp;&nbsp;&nbsp;
														<input type="checkbox" data-ng-change="updateFeedbackAlarm('E-Mail para o Alarme OFF-Line', alarmEmailOffline);" data-ng-model="alarmEmailOffline" />
														<span style="font-stretch: condensed; font-weight: 800;">Off-Line</span>
													</div>
												</div>												
											</div>
	
											<div class="col-md-6" style="padding-left: 0px !important; padding-right: 5px !important">
												<div class="box box-primary" style="padding-left: 5px !important; padding-right: 5px !important">
													<div class="box-header">
														<h3 class="box-title">Enviar SMS:</h3>														
														<span class="text-muted pull-right"><i class="fa fa-phone-square"></i></span>
													</div>
													<div class="box-body" data-ng-class="{'disableDivOver': !mobiles.length}">
														<input type="checkbox" data-ng-change="updateFeedbackAlarm('SMS para o Alarme Nível I', alarmSms1);" data-ng-model="alarmSms1" />
														<span style="font-stretch: condensed; font-weight: 800;">Alarme 1</span>
														&nbsp;&nbsp;&nbsp;
														<input type="checkbox" data-ng-change="updateFeedbackAlarm('SMS para o Alarme Nível II', alarmSms2);" data-ng-model="alarmSms2" />
														<span style="font-stretch: condensed; font-weight: 800;">Alarme 2</span>
														&nbsp;&nbsp;&nbsp;
														<input type="checkbox" data-ng-change="updateFeedbackAlarm('SMS para o Alarme Nível III', alarmSms3);" data-ng-model="alarmSms3" />
														<span style="font-stretch: condensed; font-weight: 800;">Alarme 3</span>
														&nbsp;&nbsp;&nbsp;
														<input type="checkbox" data-ng-change="updateFeedbackAlarm('SMS para o Alarme Off-Line', alarmSmsOffline);" data-ng-model="alarmSmsOffline" />
														<span style="font-stretch: condensed; font-weight: 800;">Off-Line</span>
													</div>
												</div>
												
											</div>
											
										</div>
										<div class="row">
											<div class="col-md-6" style="padding-left: 5px !important; padding-right: 5px !important">
												<div data-ng-repeat="item in emails">	
													<div class="input-group">								                                        	
														<span class="input-group-addon"><i class="fas fa-at"></i></span>
														<input data-ng-model="item.email" type="text" class="form-control" placeholder="Email" readonly>
														<span data-ng-click="removeEmail($index);" class="input-group-addon text-red"><i class="fa fa-times-circle cursor"></i></span>
													</div>
												</div>
												<div data-ng-class="{'has-error': !emailValid}">
													<div class="input-group">
														<span class="input-group-addon" data-ng-show="emailValid"><i class="fas fa-at"></i></span>
														<span class="input-group-addon text-red" data-ng-hide="emailValid"><i class="fas fa-at"></i></span>
														<input id="alarmEmail" data-ng-model="email" type="text" class="form-control" placeholder="Email" data-ng-change="validEmail($event);">
														<span data-ng-show="emailValid" data-ng-click="addEmail(email);" class="input-group-addon text-blue"><i class="fa fa-plus-square cursor"></i></span>
														<span data-ng-hide="emailValid" class="input-group-addon text-muted"><i class="fa fa-plus-square"></i></span>
													</div>
												</div>
											</div>											
											<div class="col-md-6" style="padding-left: 5px !important;">
												<div data-ng-repeat="item in mobiles">
													<div class="input-group">
														<span class="input-group-addon"><i class="fa fa-phone-square"></i></span>
														<input data-ng-model="item.mobile" type="text" class="form-control" readonly>
														<span data-ng-click="removeMobile($index);" class="input-group-addon text-red"><i class="fa fa-times-circle cursor"></i></span>
													</div>
												</div>
												<div data-ng-class="{'has-error': !mobileValid}">	
													<div class="input-group">
														<span class="input-group-addon" data-ng-show="mobileValid"><i class="fa fa-phone-square"></i></span>
														<span class="input-group-addon text-red" data-ng-hide="mobileValid"><i class="fa fa-phone-square"></i></span>																			
														<input class="form-control alarmMobileMask" id="alarmMobile" data-ng-model="mobile" type="text" maxlength="15" 
															placeholder="(XX) XXXXX-XXXX" data-ng-change="validMobile($event);" />
														<span data-ng-show="mobileValid" data-ng-click="addMobile(mobile);" class="input-group-addon text-blue"><i class="fa fa-plus-square cursor"></i></span>
														<span data-ng-hide="mobileValid" class="input-group-addon text-muted"><i class="fa fa-plus-square"></i></span>
													</div>
												</div>
											</div>
										
										</div>									
									</div>
								</div>
							</div>											
						</div>						
						<div class="modal-footer">							
							<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-sign-out"></i></button>									                                
						</div>						
					</div>
				</div>
			</div>		
		</div>
	</div>

    
    