
	<style>
		.todo-list>li {
		    padding: 4px;
		}	
	</style>	 
	<div data-ng-controller="alarmController as alarmController">
												
		<div class="row">				                                                    
			<div class="col-md-6">                                                        
				<div class="box box-primary" data-ng-class="(alarmName || alarmGas || gasUnitMeterGases || alarmAlarm1 || alarmAlarm2 || alarmAlarm3) ? 'box-default' : 'box-primary'">
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
										<th>Unit</th>                                                            
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr data-ng-repeat="item in alarms">
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
				</div>
				
				<div id="resultErro" class="alert alert-warning" role="alert" data-ng-show="msgErro" >
            		<button type="button" class="close" ><span data-ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
			</div>                                                      
																
			<div class="col-sm-6">
				<div class="box box-primary" data-ng-class="(alarmName || alarmGas || gasUnitMeterGases || alarmAlarm1 || alarmAlarm2 || alarmAlarm3) ? 'box-primary' : 'box-default'">
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
					                                data-ng-options="item as item.name for item in gases | orderBy: 'name' track by item.uid" 
			                                           data-ng-model="alarmGas">
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
						                                data-ng-options="item as item.name for item in unitMetersGases | orderBy: 'name' track by item.uid" 
						                                         data-ng-model="gasUnitMeterGases">
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
										<span class="text-red" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Nome Obrigatorio]</span>
									    <span class="text-red" data-ng-show="userForm.username.$error.maxlength">Tamanho Máximo 15 caracteres</span>                                                                        
										<input id="idAlarmName" class="form-control inputProfile" placeholder="Nome do Alarme" data-ng-model="alarmName" data-ng-maxlength="15" name="username" required>                                                                        
									</div>
								</div>							
								<div class="col-md-6">
									<div class="form-group">
										                                                
									</div>
								</div>														
							</div>	                        
	                       	<div class="box box-primary box-solid">
				    			<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Limites do Alarme </strong>
				    				<span class="text-red" data-ng-show="(alarmAlarm1 >= alarmAlarm2 || alarmAlarm2 >= alarmAlarm3) && !userForm.alarmAlarm1.$pristine">  [Sequência de Valores inválida]</span>
				    			</div>					                	 
			                    <div class="box-body">
								    <div class="row">
								    	<div class="col-md-4">
									    	<div data-ng-class="{'has-error': userForm.alarmAlarm1.$invalid && !userForm.alarmAlarm1.$pristine}">
								                <label class="control-label">Alarme 1 <span class="label-default" style="font-size: 0.7em"> DETECÇÃO </span></label>
								                <input type="number" class="form-control" name="alarmAlarm1" placeholder="Alarme 1 - Decimal" data-ng-model="alarmAlarm1" data-ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />
								            </div>
									    </div>
									    <div class="col-md-4">
									    	<div class="form-group">
								                <div data-ng-class="{'has-error': userForm.alarmAlarm2.$invalid && !userForm.alarmAlarm2.$pristine}">
								                <label class="control-label">Alarme 2 <span class="label-warning" style="font-size: 0.7em"> ALERTA </span></label>
								                <input type="number" class="form-control" name="alarmAlarm2" placeholder="Alarme 2 - Decimal" data-ng-model="alarmAlarm2" data-ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />
								            </div>
								            </div>
									    </div>
									    <div class="col-md-4">
									    	<div class="form-group">								                
								                <div data-ng-class="{'has-error': userForm.alarmAlarm3.$invalid && !userForm.alarmAlarm3.$pristine}">
								                	<label class="control-label">Alarme 3 <span class="label-danger" style="font-size: 0.7em"> EVACUAÇÃO </span></label>
								                	<input type="number" class="form-control" name="alarmAlarm3" placeholder="Alarme 3 - Decimal" data-ng-model="alarmAlarm3" data-ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01" required />								                							          
								            	</div>
								            </div>
									    </div>									    
								    </div>
						    	</div>
						    </div>				            			                 
			            </form>
			            
						<div class="box-footer">
							<button type="button" data-ng-click="clearFormAlarm(); userForm.$setPristine()" class="btn btn-default">Cancelar</button>                                                                
							<button type="button" data-ng-click="saveAlarm();" class="btn btn-primary" 
								data-ng-disabled="(alarmName && alarmGas && gasUnitMeterGases && alarmAlarm1 && alarmAlarm2 && alarmAlarm3) ? false : true">Salvar
							</button>
						</div>					
					</div>
				</div>
			</div>	
		</div>		
	</div>

    
    