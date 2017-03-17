
	<style>
		.todo-list>li {
		    padding: 4px;
		}	
	</style>	 
	<div data-ng-controller="alarmController as alarmController">
												
		<div class="row">				                                                    
			<div class="col-md-10">                                                        
				<div class="box box-primary" data-ng-class="(alarmName || alarmGas || gasUnitMeterGases || alarmAlarm1 || alarmAlarm2 || alarmAlarm3) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de Alarmes</h3>
					  <a href="#" class="text-muted pull-right" data-ng-click="refreshAlarms();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto;">
							<table class="table table-hover"  >
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
					<div class="modal-body">
					
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Edi��o de Alarmes</strong></div>														                                                                           
					  	</div>
											
						<div class="box box-primary" data-ng-class="(alarmName || alarmGas || gasUnitMeterGases || alarmAlarm1 || alarmAlarm2 || alarmAlarm3) ? 'box-primary' : 'box-default'">
							<div class="box-header">
								<h3 class="box-title">Cadastro / Edi��o</h3>
								<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
							</div>					
							<div class="box-body">
								<form class="form" name="userForm">		
								
									<div class="row">											
										<div class="col-md-6">
											<div class="box box-primary box-solid">				                    
							                	<div class="box-header with-border"><strong><i class="fa fa-info"></i> Nome:</strong>
							                		<strong class="text-red pull-right" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Nome Obrigatorio]</strong>
												    <strong class="text-red pull-right" data-ng-show="userForm.username.$error.maxlength">Tamanho M�ximo 12 caracteres</strong>
							                	</div>
							                    <div class="box-body">													                                                                        
													<input class="form-control inputProfile" placeholder="Nome do Alarme" data-ng-model="alarmName" data-ng-maxlength="12" name="username" required>                                                                       
												</div>
											</div>
										</div>
																	
										<div class="col-md-6">													
											<div class="box box-primary box-solid">				                    
							                	<div class="box-header with-border"><strong><i class="fa fa-industry"></i> Empresa</strong>
							                		<strong class="text-red pull-right" data-ng-show="userForm.companyName.$dirty && userForm.companyName.$invalid">  Campo Obrigat�rio</strong>
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
									
										              
					                </div>    
					                									
									<div class="row">    
										<div class="col-md-12">                  
					                       	<div class="box box-primary box-solid">
					                       	
								    			<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Limites do Alarme�</strong>
								    				<strong class="text-red pull-right" data-ng-show="(alarmAlarm1 >= alarmAlarm2 || alarmAlarm2 >= alarmAlarm3) && !userForm.alarmAlarm1.$pristine">  [Sequ�ncia de Valores dos Alarmes Inv�lida]</strong>
								    			</div>
								    								                	 
							                    <div class="box-body">
												    <div class="row">
												    
												    	<div class="col-md-3" style="padding-right: 5px !important;">															
									                    	<label class="control-label">G�s
									                    		<span class="text-red pull-right" data-ng-show="userForm.gasName.$dirty && userForm.gasName.$invalid">  [Campo Obrigat�rio]</span>
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
											            		<span class="text-red" data-ng-show="userForm.gasUnit.$dirty && userForm.gasUnit.$invalid">  [Campo Obrigat�rio]</span>
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
													                <label class="control-label">Alarme 1 <span class="label label-default" style="font-size: 0.5em">�DETEC��O�</span></label>
													                <input type="number" class="form-control" name="alarmAlarm1" placeholder="Alarme 1 - Decimal" data-ng-model="alarmAlarm1" data-ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />
													            </div>
													    	</div>
													    </div>
													    <div class="col-md-2" style="padding-right: 5px !important; padding-left: 5px !important">
													    	<div class="form-group">
												                <div data-ng-class="{'has-error': userForm.alarmAlarm2.$invalid && !userForm.alarmAlarm2.$pristine}">
												                	<label class="control-label">Alarme 2 <span class="label label-warning" style="font-size: 0.5em">�ALERTA�</span></label>
												                	<input type="number" class="form-control" name="alarmAlarm2" placeholder="Alarme 2 - Decimal" data-ng-model="alarmAlarm2" data-ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />
												            	</div>
												            </div>
													    </div>
													    <div class="col-md-2" style="padding-left: 5px !important">
													    	<div class="form-group">								                
												                <div data-ng-class="{'has-error': userForm.alarmAlarm3.$invalid && !userForm.alarmAlarm3.$pristine}">
												                	<label class="control-label">Alarme 3 <span class="label label-danger" style="font-size: 0.5em">�EVACUA��O�</span></label>
												                	<input type="number" class="form-control" name="alarmAlarm3" placeholder="Alarme 3 - Decimal" data-ng-model="alarmAlarm3" data-ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />								                							          
												            	</div>
												            </div>
													    </div>														    
								                        
								                        <div class="row">
									                        <div class="col-md-12">
										            			<div class="col-md-6">					            				
											            			<div class="checkbox3 checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-0">Emitir Alarme Sonoro? </label>
						                                          	</div>
						                                         </div>					            			
										            			
										            			<div class="col-md-2">
										            				<div class="checkbox3 checkbox-muted checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-1">Alarme 1</label>
						                                          	</div>
										            			</div>
										            			<div class="col-md-2">
										            				<div class="checkbox3 checkbox-warning checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-1">Alarme 2</label>
						                                          	</div>
										            			</div>
										            			<div class="col-md-2">
										            				<div class="checkbox3 checkbox-danger checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-1">Alarme 3</label>
						                                          	</div>
										            			</div>
										            		</div>	
													    </div>													    
													    
													    <div class="row">
									                        <div class="col-md-12">
										            			<div class="col-md-3">										            									            				
											            			<div class="checkbox3 checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-0">Enviar E-MAIL? </label>
						                                          	</div>
						                                        </div>
						                                        
						                                        <div class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important">	
						                                        	<div class="input-group">
												                    	<span class="input-group-addon">@</span>
												                    	<input type="email" class="form-control" placeholder="Email">
												                  	</div>			                                        
						                                         	<!-- 
						                                         	<div class="input-group">
													                    <div class="input-group-btn">
													                      <button type="button" class="btn btn-default">Celular</button>
													                    </div>
													                    <input type="text" class="form-control">
													             	</div>
													             	 -->												            			
							                                   	</div>			            			
										            			
										            			<div class="col-md-2">
										            				<div class="checkbox3 checkbox-muted checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-1">Alarme 1</label>
						                                          	</div>
										            			</div>
										            			<div class="col-md-2">
										            				<div class="checkbox3 checkbox-warning checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-1">Alarme 2</label>
						                                          	</div>
										            			</div>
										            			<div class="col-md-2">
										            				<div class="checkbox3 checkbox-danger checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-1">Alarme 3</label>
						                                          	</div>
										            			</div>
										            		</div>	
													    </div>	
													    
													    <div class="row">
									                        <div class="col-md-12">
										            			<div class="col-md-3">										            									            				
											            			<div class="checkbox3 checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-0">Enviar SMS? </label>
						                                          	</div>
						                                        </div>
						                                        
						                                        <div class="col-md-3" style="padding-left: 5px !important; padding-right: 5px !important">	
						                                        	<div class="input-group">
												                    	<span class="input-group-addon"><i class="fa fa-phone-square"></i></span>
												                    	<input type="text" class="form-control" placeholder="Celular">
												                  	</div>		                                        
						                                         													            			
							                                   	</div>			            			
										            			
										            			<div class="col-md-2">
										            				<div class="checkbox3 checkbox-muted checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-1">Alarme 1</label>
						                                          	</div>
										            			</div>
										            			<div class="col-md-2">
										            				<div class="checkbox3 checkbox-warning checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-1">Alarme 2</label>
						                                          	</div>
										            			</div>
										            			<div class="col-md-2">
										            				<div class="checkbox3 checkbox-danger checkbox-inline checkbox-check checkbox-light">
						                                            	<input type="checkbox" id="checkbox-fa-light-1" checked>
						                                            	<label for="checkbox-fa-light-1">Alarme 3</label>
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
							data-ng-disabled="(userForm.$valid && !(alarmAlarm1 >= alarmAlarm2 || alarmAlarm2 >= alarmAlarm3)) ? false : true">Salvar
						</button>						                                
				  	</div>
			  	</div>
			</div>		
		</div>
				
	</div>

    
    