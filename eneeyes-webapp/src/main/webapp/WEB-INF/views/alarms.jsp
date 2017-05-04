
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
		
		.list-group-item {                    
            padding: 5px 15px !important;
            border: none !important;
         }
         
         .list-group {
            margin-bottom: 0px !important;
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
										<th>Gas</th>
										<th>Unit</th>                                                            
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr data-ng-repeat="item in alarms">
										<td>{{item.companyDto.name}}</td>
										<td>{{item.name}}</td>
										<td>{{item.gasDto.name}}</td>															        
										<td>{{item.unitMeterGases}}</td>
										<td>
											<button type="button" class="btn btn-primary btn-xs" data-ng-click="editAlarm($index)">editar</button>
										</td>
										<td>
											<button type="button" class="btn btn-danger btn-xs" data-ng-click="deleteAlarm($index)">excluir</button>
										</td>						
									</tr>                                                               
								</tbody>
							</table>
						</div>                                                       
					</div>
					
					<div class="box-footer">						                                                                
						<button type="button" data-ng-click="clearFormAlarm(); userForm.$setPristine()" class="btn btn-primary pull-right" data-toggle="modal" data-target="#modalAlarmEdit">Novo</button>
					</div>
				</div>
				
				<div id="resultErro" class="alert alert-warning" role="alert" data-ng-show="msgErro" >
            		<button type="button" class="close" ><span data-ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
			</div>                                                     
			
		</div>
				
		<div id="modalAlarmEdit" class="modal">                
			<div class="modal-dialog  modal-lg" role="document">
				<div class="modal-content">                            
					<div class="modal-body"style="padding-bottom: 0px; !important">
					
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Edição de Alarmes</strong></div>														                                                                           
					  	</div>
											
						<div class="box box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
							<div class="box-header">
								<h3 class="box-title">Cadastro / Edição</h3>
								<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
							</div>					
							<div class="box-body" style="padding-bottom: 0px; !important">
								<form class="form" name="userForm">		
								
									<div class="row">											
										<div class="col-md-3">
											<div class="box box-primary">				                    
							                	<div class="box-header with-border"><strong>Nome</strong>
							                		<strong class="text-red pull-right" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Nome Obrigatorio]</strong>
												    <strong class="text-red pull-right" data-ng-show="userForm.username.$error.maxlength">Tamanho Máximo 12 caracteres</strong>
							                	</div>
							                    <div class="box-body">													                                                                        
													<input class="form-control inputProfile" placeholder="Nome do Alarme" data-ng-model="alarmName" data-ng-maxlength="15" name="username" required>                                                                       
												</div>
											</div>
										</div>
																	
										<div class="col-md-3">													
											<div class="box box-primary">				                    
							                	<div class="box-header with-border"><strong><i class="fa fa-industry"></i> Empresa</strong>
							                		<strong class="text-red pull-right" data-ng-show="userForm.companyName.$dirty && userForm.companyName.$invalid">  Campo Obrigatório</strong>
							                	</div>
							                	
							                    <div class="box-body">
							                    	<div data-ng-class="{'has-error': userForm.companyName.$dirty && userForm.companyName.$invalid}">							               
							                        <select name="companyName" class="form-control" data-live-search="true"
							                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
							                                data-ng-options="item as item.name for item in companies | orderBy: 'name' track by item.uid" 
					                                           data-ng-model="company" required>
					                                           <option value="">Selecione</option> 
							                        </select>    
							                    	</div>
							                    </div>			                    			                            
							                </div>
							        	</div>					
							        	
							        	<div class="col-md-6">
							        		<div class="box box-primary" style="padding-bottom: 0px; !important;">
							        			<div class="box-header with-border"><strong><i class="fa fa-rss"></i> Dispositivos Associados a este Alarme</strong>
							                		<strong class="text-red pull-right" data-ng-show="userForm.companyName.$dirty && userForm.companyName.$invalid">  Campo Obrigatório</strong>
							                	</div>
							                	
									        	<div class="box-body" style="padding-bottom: 0px; !important; padding-top: 0px; !important;">									        		                                                                                       
				                                    <div class="list-group" style="max-height: 50px ! important; height:auto; overflow: auto; font-size: 0.9em  ! important">
				                                    	<p data-ng-show="!usedAlarms || usedAlarms.length <= 0" class="text-center">NENHUM DETECTOR ASSOCIADO</p>
				                                        <span class="list-group-item" data-ng-repeat="item in usedAlarms">
				                                        	<strong>Detector/Sensor:</strong> {{item.company_detector_name}}/{{item.sensor_name}} - <strong>Local:</strong> {{item.company_detector_local}} 			                                            
				                                        </span>                            
				                                    </div>					                                
									        	</div>
									        		
								        	</div>								        	
							        	</div>					
										              
					                </div>    
					                									
									<div class="row">    
										<div class="col-md-12">                  
					                       	<div class="box box-info" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
					                       	
								    			<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Limites do Alarme </strong>
								    				<strong class="text-red pull-right" data-ng-show="(alarmAlarm1 >= alarmAlarm2 || alarmAlarm2 >= alarmAlarm3) && !userForm.alarmAlarm1.$pristine">  [Sequência de Valores dos Alarmes Inválida]</strong>
								    			</div>
								    								                	 
							                    <div class="box-body" style="padding-bottom: 0px; !important">
							                      	<div class="row">
								                      	<div class="col-md-9"> 
				                                          	<div class="btn-group" id="toggle_event_editing">
																<button type="button" id='alarmOff' class="btn btn-default locked_active">OFF</button>
																<button type="button" id='alarmOn' class="btn bg-black unlocked_inactive">ON</button>
															</div>
														</div>
														<div class="col-md-3">					            				
									            			<div class="checkbox3 checkbox-danger checkbox-inline checkbox-check checkbox-circle checkbox-light">
				                                            	<input type="checkbox" id="checkboxSigmaOnOff" checked>
				                                            	<label for="checkboxSigmaOnOff">Integrar ao Sigma? </label>
				                                          	</div>
				                                        </div>
													</div>
						                            <hr style="margin-top: 5px !important; margin-bottom: 5px !important;">
						                            
												    <div class="row">
												    	
												    	<div id="travar">
												    	
													    	<div class="col-md-3" style="padding-right: 5px !important;">															
										                    	<label class="control-label">Gás
										                    		<span class="text-red pull-right" data-ng-show="userForm.gasName.$dirty && userForm.gasName.$invalid">  [Campo Obrigatório]</span>
										                    	</label>
										                    	<div data-ng-class="{'has-error': userForm.gasName.$dirty && userForm.gasName.$invalid}">
											                        <select name="gasName" class="form-control" data-live-search="true" 
											                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
											                                data-ng-options="item as item.name for item in gases | orderBy: 'name' track by item.uid" 
									                                           data-ng-model="alarmGas" required>
									                                           <option value="">Selecione</option>			                                           
											                        </select>											                		
											                	</div>
											                </div>				                
											                
											                <div class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important;">											                
												            	<label class="control-label">Unidade
												            		<span class="text-red" data-ng-show="userForm.gasUnit.$dirty && userForm.gasUnit.$invalid">  [Campo Obrigatório]</span>
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
													    
													    	<div class="col-md-2" style="padding-right: 5px !important">
													    		<div class="form-group">
															    	<div data-ng-class="{'has-error': userForm.alarmAlarm1.$invalid && !userForm.alarmAlarm1.$pristine}">
														                <label class="control-label">Alarme 1 <span class="label label-default" style="font-size: 0.5em"> DETECÇÃO </span></label>
														                <input type="number" class="form-control" name="alarmAlarm1" placeholder="Alarme 1 - Decimal" data-ng-model="alarmAlarm1" data-ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />
														            </div>
														    	</div>
														    </div>
														    
														    <div class="col-md-2" style="padding-right: 5px !important; padding-left: 5px !important">
														    	<div class="form-group">
													                <div data-ng-class="{'has-error': userForm.alarmAlarm2.$invalid && !userForm.alarmAlarm2.$pristine}">
													                	<label class="control-label">Alarme 2 <span class="label label-warning" style="font-size: 0.5em"> ALERTA </span></label>
													                	<input type="number" class="form-control" name="alarmAlarm2" placeholder="Alarme 2 - Decimal" data-ng-model="alarmAlarm2" data-ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />
													            	</div>
													            </div>
														    </div>
														  
														    <div class="col-md-2" style="padding-left: 5px !important">
														    	<div class="form-group">								                
													                <div data-ng-class="{'has-error': userForm.alarmAlarm3.$invalid && !userForm.alarmAlarm3.$pristine}">
													                	<label class="control-label">Alarme 3 <span class="label label-danger" style="font-size: 0.5em"> EVACUAÇÃO </span></label>
													                	<input type="number" class="form-control" name="alarmAlarm3" placeholder="Alarme 3 - Decimal" data-ng-model="alarmAlarm3" data-ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />								                							          
													            	</div>
													            </div>
														    </div>
									                        
									                        <div class="row">
										                        <div class="col-md-12">
											            			<div class="col-md-6">					            				
												            			<div class="checkbox3 checkbox-inline checkbox-check checkbox-round  checkbox-light">												            			
							                                            	<input type="checkbox" id="checkboxSonoroOnOff" checked>
							                                            	<label for="checkboxSonoroOnOff">Emitir Alarme Sonoro? </label>
							                                          	</div>
							                                         </div>					      
							                 							                                               			
											            			<!-- 
											            			<div class="col-md-2">											            			
											            				<div class="checkbox3 checkbox-muted checkbox-inline checkbox-check checkbox-light">
							                                            	<input class="checkboxSonoro" type="checkbox" id="checkboxSonoro1" checked disabled>
							                                            	<label for="checkboxSonoro1">Alarme 1</label>
							                                          	</div>
											            			</div>
											            			<div class="col-md-2">
											            				<div class="checkbox3 checkbox-warning checkbox-inline checkbox-check checkbox-light">
							                                            	<input class="checkboxSonoro" type="checkbox" id="checkboxSonoro2" checked disabled>
							                                            	<label for="checkboxSonoro2">Alarme 2</label>
							                                          	</div>
											            			</div>
											            			<div class="col-md-2">
											            				<div class="checkbox3 checkbox-danger checkbox-inline checkbox-check checkbox-light">
							                                            	<input class="checkboxSonoro" type="checkbox" id="checkboxSonoro3" checked disabled>
							                                            	<label for="checkboxSonoro3">Alarme 3</label>
							                                          	</div>
											            			</div>
											            			 -->
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
														                    	<input 
														                    		id="alarmEmail" 
														                    		data-ng-model="email" type="text" class="form-control" 
														                    		placeholder="Email" 
														                    		data-ng-change="validEmail($event);">
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
														                    		id="alarmCelular" 
														                    		data-ng-model="celular" 
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
							                                            	<label for="checkboxActionOff">Ações a Realizar? </label>
							                                          	</div>
							                                        </div>
							                            			<div class="travarAction">            
								                                    	<div class="col-md-10" style="padding-left: 5px !important;">								                                        
								                                        	<div class="entry input-group" id="">
								                                        		<span class="input-group-addon btn-add text-white bg-gray"><i class="fa fa-bullhorn"> Alarme 1</i></span>  					
																				<textarea class="form-control" rows="1" data-ng-model="action1" placeholder="Providências do Agente de Monitoramento se Houver alarme 1" maxlength="250"></textarea>																				                                   						
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
								                                        	<div class="entry input-group" id="">
								                                        		<span class="input-group-addon btn-add text-white bg-orange"><i class="fa fa-bullhorn"> Alarme 2</i></span>  					
																				<textarea class="form-control" data-ng-model="action2" rows="1" placeholder="Providências do Agente de Monitoramento se Houver alarme 2" maxlength="250"></textarea>																				                                   						
																			</div>
																		</div>                                        						                                        						                                         													            			
								                                   	</div>							                                   
								                                </div>
								                                
								                                <div class="row">
											                        <div class="col-md-12">
												            			<div class="col-md-2">												            			
								                                        </div>							                                        
								                                    	<div class="col-md-10" style="padding-left: 5px !important; ">								                                        
								                                       		<div class="entry input-group" id="">
							                                        			<span class="input-group-addon btn-add text-white bg-red"><i class="fa fa-bullhorn"> Alarme 3</i></span>  					
																				<textarea class="form-control" data-ng-model="action3" rows="1" placeholder="Providências do Agente de Monitoramento se Houver alarme 3" maxlength="250"></textarea>																				                                   						
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
					            						            		
					            </form>
							</div>
						</div>
										
				  	</div>
				  	
				  	<div class="modal-footer">						
						<button type="button" data-ng-click="clearFormAlarm(); userForm.$setPristine()" class="btn btn-default" data-dismiss="modal">Cancelar</button>                                                                
						<button type="button" data-ng-click="saveAlarm();" class="btn btn-primary" data-dismiss="modal"
							data-ng-disabled="(emailValid && mobileValid && emailValid1 && mobileValid1 && userForm.$valid && !(alarmAlarm1 >= alarmAlarm2 || alarmAlarm2 >= alarmAlarm3)) ? false : true">Salvar
						</button>						                                
				  	</div>
				  	
			  	</div>
			</div>		
		</div>
				
	</div>

    
    