 
<div data-ng-controller="sensorController">
											
	<div class="row">				                                                    
		<div class="col-md-12">                                                        
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Cadastro de Sensores</h3>
					<a href="#" class="text-muted pull-right" data-ng-click="refreshSensors();"><i title="Refresh" class="fa fa-refresh"></i></a>
				</div>
				<div class="box-body">
					<div style="height: 500px; overflow: auto">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Nome</th>
									<th>Modelo</th>
									<th>Fabricante</th>
									<th>Gas</th>
									<th>Unidade</th>
									<th>Min</th>
									<th>Max</th>                                                               
									<th>Editar</th>
									<th>Excluir</th>						
								</tr>
							</thead>
							<tbody>                                                        
								<tr data-ng-repeat="item in sensors">
									<td>{{item.name}}</td>
									<td>{{item.model}}</td>	
									<td>{{item.manufacturerDto.name}}</td>										
									<td>{{item.gasDto.name}}</td>
									<td>{{item.unitMeter.symbol}}</td>
									<td>{{item.rangeMin}}</td>
									<td>{{item.rangeMax}}</td>														        
									<td>
										<button type="button" class="btn btn-primary btn-xs" data-ng-click="editSensor($index)">editar</button>
									</td>
									<td>
										<a type="button" class="btn btn-danger btn-xs" data-popover=' do Sensor: [ {{item.name}} ]' data-confirm="deleteSensor($index)" >excluir</a>
									</td>						
								</tr>                                                               
							</tbody>
						</table>
					</div>                                                       
				</div>					
				<div class="box-footer">						                                                                
					<button type="button" data-ng-click="clearFormSensor(); userForm.$setPristine()" class="btn btn-primary pull-right" 
					data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#modalEditSensor">Novo</button>
				</div>
			</div>				
			<div class="alert alert-warning" role="alert" data-ng-show="msgErroSensor" >
				<button type="button" class="close" ><span data-ng-click="msgErroSensor='';">&times;</span></button>
				<strong>Alerta! </strong>{{msgErroSensor}} 
			</div>	 	       		
		</div>			
	</div>
	
	<div id="modalEditSensor" class="modal">                
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">                               
				<div class="modal-body" style="padding-bottom: 5px !important">					
					<div class="panel panel-default">
						<div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Edição de Sensores</strong></div>                                                                
					</div>								
					<div class="box box-primary">							
						<div class="box-header">
							<h3 class="box-title">Cadastro / Edição</h3>	
							<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>							
						</div>							
						<div class="box-body">
							<form class="form" name="sensorForm">								
								<div class="row">
									<div class="col-md-6">    					                	 
										<div class="form-group">
											<label class="control-label">Fabricante *</label>
											<span class="text-red pull-right" data-ng-show="sensorForm.sensorManufacturer.$dirty && sensorForm.sensorManufacturer.$invalid"> [Obrigat&oacute;rio]</span>											 
											<select name="sensorManufacturer" class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true"                              
													data-ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
														data-ng-model="sensorManufacturer" required>
														<option value="">Selecione</option> 
											</select>    
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">											
											<label class="control-label">Tipo de Detecção *</label>
											<span class="text-red pull-right" data-ng-show="sensorForm.sensorDetectionType.$dirty && sensorForm.sensorDetectionType.$invalid"> [Obrigat&oacute;rio]</span>											
											<select name="sensorDetectionType" class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true" 
												data-ng-options="item as item.name for item in detectionTypes | orderBy: 'name' track by item.uid" 
														data-ng-model="sensorDetectionType" required>
																<option value="">Selecione</option> 
											</select>               
										</div>
									</div>                
								</div>                    
								<div class="row">
									<div class="col-md-6">                                                                                                                
										<div class="form-group">
											<label class="control-label">Nome *</label>
											<span class="text-red pull-right" data-ng-show="sensorNameExist">Sensor já Existe</span> 
											<span class="text-red pull-right" data-ng-show="sensorForm.sensorName.$error.required && !sensorForm.sensorName.$pristine">  [Nome Obrigatorio]</span>
											<span class="text-red pull-right" data-ng-show="sensorForm.sensorName.$error.maxlength">Tamanho Máximo 15 caracteres</span>                                                                        
											<input id="idSensorName" class="form-control inputProfile" placeholder="Nome do Sensor" data-ng-model="sensorName" 
												data-ng-maxlength="15" name="sensorName" required>
										</div>
									</div>							
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Modelo *</label>
											<span class="text-red pull-right" data-ng-show="sensorForm.sensorModel.$error.required && !sensorForm.sensorModel.$pristine">  [Modelo Obrigatorio]</span>
											<span data-ng-show="sensorFormForm.controllerModel.$error.maxlength">Até Máximo 20 caracteres</span>                                                       
											<input name="sensorModel" class="form-control inputProfile" placeholder="Modelo do Sensor" data-ng-model="sensorModel" required>
										</div>
									</div>														
								</div>								
								<div class="box box-primary box-solid">
									<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Range de Detecção *</strong></div>					                	 
									<div class="box-body">
										<div class="row">
											<div class="col-md-2">
												<div class="form-group">
													<label class="control-label">Min</label>
													<span class="text-red pull-right" data-ng-show="sensorForm.sensorRangeMin.$dirty && sensorForm.sensorRangeMin.$invalid">[Obrigat&oacute;rio]</span>
													<input name="sensorRangeMin" class="form-control" placeholder="Min" data-ng-model="sensorRangeMin" required>
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<label class="control-label">Máx</label>
													<span class="text-red pull-right" data-ng-show="sensorForm.sensorRangeMax.$dirty && sensorForm.sensorRangeMax.$invalid">[Obrigat&oacute;rio]</span>
													<input name="sensorRangeMax" class="form-control" placeholder="Max" data-ng-model="sensorRangeMax" required>
												</div>
											</div>
											<!-- <div class="col-md-2">
												<div class="form-group">
													<label class="control-label">Resolução</label>
													<input class="form-control" placeholder="Unit" data-ng-model="sensorRangeUnit">
												</div>
											</div>												 -->
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label"><i class="fa fa-balance-scale"> </i> Unidade:</label>
													<span class="text-red pull-right" data-ng-show="sensorForm.unitMeter.$dirty && sensorForm.unitMeter.$invalid">[Obrigat&oacute;rio]</span>
													<select name="unitMeter" class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true"
														data-ng-options="item as item.description for item in unitMeters | orderBy: 'symbol' track by item.uid" 
														data-ng-model="unitMeter" required>
														<option value="">Selecione</option> 
													</select>
												</div>
											</div>												
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label"><i class="fa fa-yelp"></i> G&aacute;s </label>
													<span class="text-red pull-right" data-ng-show="sensorForm.sensorGas.$dirty && sensorForm.sensorGas.$invalid"> [Obrigat&oacute;rio]</span>													
													<select name="sensorGas" class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true"                              
															data-ng-options="item as item.name for item in gases | orderBy: 'name' track by item.uid" 
															data-ng-model="sensorGas" required>
															<option value="">Selecione</option> 
													</select>    
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>		
				</div>				
				<div class="modal-footer" style="padding: 8px;">
					<button type="button" data-ng-click="clearFormSensor(); sensorForm.$setPristine();" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<button type="button" data-ng-click="saveSensor(); sensorForm.$setPristine();" class="btn btn-primary"
						data-ng-disabled="sensorForm.$valid ? false : true">&nbsp;Salvar&nbsp;</button>						
					</button>                                
				</div>				
			</div>
		</div>		
	</div>
			
</div>

    
    