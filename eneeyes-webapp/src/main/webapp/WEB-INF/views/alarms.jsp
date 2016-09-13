	 
	<div ng-controller="alarmController">
		<style>
			.todo-list>li {
			    padding: 4px;
			}	
		</style>										
		<div class="row">				                                                    
			<div class="col-md-5">                                                        
				<div class="box box-primary" ng-class="(alarmName || alarmGas || gasUnitMeterGases || alarmAlarm1 || alarmAlarm2 || alarmAlarm3) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de Alarmes</h3>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Gas</th>                                                            
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr ng-repeat="item in alarms">
										<td>{{item.name}}</td>
										<td>{{item.gasDto.name}}</td>															        
										<td>
											<button type="button" class="btn btn-primary btn-xs" ng-click="editAlarm($index)">editar</button>
										</td>
										<td>
											<button type="button" class="btn btn-danger btn-xs" ng-click="deleteAlarm($index)">excluir</button>
										</td>						
									</tr>                                                               
								</tbody>
							</table>
						</div>                                                       
					</div>
				</div>
				<div id="resultErro" class="alert alert-warning" role="alert" ng-show="msgErro" >
            		<button type="button" class="close" ><span ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
			</div>                                                      
																
			<div class="col-sm-7">
				<div class="box box-primary" ng-class="(alarmName || alarmGas || gasUnitMeterGases || alarmAlarm1 || alarmAlarm2 || alarmAlarm3) ? 'box-primary' : 'box-default'">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edição</h3>
					</div>
					
					<div class="box-body">
						<form class="form" name="userForm">		
						
							<div class="row">
								<div class="col-md-6">
									<div class="box box-primary box-solid">
					                    <div class="box-header with-border"><strong><i class="fa fa-yelp"></i> Gases</strong></div>
					                	 
					                    <div class="box-body">
					                        <select class="form-control" data-live-search="true" 
					                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
					                                ng-options="item as item.name for item in gases | orderBy: 'name' track by item.uid" 
			                                           ng-model="alarmGas">
			                                           <option value="">Selecione</option>			                                           
					                        </select>    
					                    </div>			                    			                            
					                </div>		
				                </div>				                
				                <div class="col-md-6">
					                <div class="box box-primary box-solid">
					                	<div class="box-header with-border"><strong><i class="fa fa-line-chart"></i> Unid. Medida</strong></div>
					                	
							            <div class="box-body">
											<select class="form-control" data-live-search="true" 
						                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
						                                ng-options="item as item.name for item in unitMetersGases | orderBy: 'name' track by item.uid" 
						                                         ng-model="gasUnitMeterGases">
						                                         <option value="">Selecione</option> 
						                     </select>
				                        </div>               
			                        </div>
		                        </div>                
			                </div>                    
			                <div class="row">
			                	<div class="col-md-6">                                                                                                                
									<div class="form-group">
										<label class="control-label">Nome</label>
										<span class="text-red" ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Nome Obrigatorio]</span>
									    <span class="text-red" ng-show="userForm.username.$error.maxlength">Tamanho Máximo 15 caracteres</span>                                                                        
										<input id="idAlarmName" class="form-control inputProfile" placeholder="Nome do Sensor" ng-model="alarmName" ng-maxlength="15" name="username" required>                                                                        
									</div>
								</div>							
								<div class="col-md-6">
									<div class="form-group">
										                                                
									</div>
								</div>														
							</div>	                        
	                       	<div class="box box-primary box-solid">
				    			<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Limites do Alarme</strong></div>					                	 
			                    <div class="box-body">
								    <div class="row">
								    	<div class="col-md-4">
									    	<div class="form-group">
								                <label class="control-label">Alarme 1</label>
								                <input class="form-control" placeholder="Alarme 1" ng-model="alarmAlarm1">
								            </div>
									    </div>
									    <div class="col-md-4">
									    	<div class="form-group">
								                <label class="control-label">Alarme 2</label>
								                <input class="form-control" placeholder="Max" ng-model="alarmAlarm2">
								            </div>
									    </div>
									    <div class="col-md-4">
									    	<div class="form-group">
								                <label class="control-label">Alarme 1</label>
								                <input class="form-control" placeholder="Unit" ng-model="alarmAlarm3">
								            </div>
									    </div>									    
								    </div>
						    	</div>
						    </div>				            			                 
			            </form>
			            
						<div class="box-footer">
							<button type="button" ng-click="clearFormAlarm()" class="btn btn-default">Cancelar</button>                                                                
							<button type="button" ng-click="saveAlarm();" class="btn btn-primary" 
								ng-disabled="(alarmName && alarmGas && gasUnitMeterGases && alarmAlarm1 && alarmAlarm2 && alarmAlarm3) ? false : true">Salvar
							</button>
						</div>					
					</div>
				</div>
			</div>	
		</div>		
	</div>

    
    