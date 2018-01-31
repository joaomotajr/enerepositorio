 
<div  data-ng-controller="genericController">
					
	<div class="row">	
																	
		<div class="col-md-6">                                                        
			<div class="box box-primary"  data-ng-class="(genericName || genericModel || genericManufacturer) ? 'box-default' : 'box-primary'">
				<div class="box-header">
					<h3 class="box-title">Cadastro de Dispositivos de Medição</h3>					  
				</div>
				<div class="box-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Modelo</th>                                                            
								<th>Editar</th>
								<th>Excluir</th>						
							</tr>
						</thead>
						<tbody>                                                        
							<tr  data-ng-repeat="item in generics">
								<td>{{item.name}}</td>
								<td>{{item.model}}</td>															        
								<td>
									<button type="button" class="btn btn-info btn-xs"  data-ng-click="editGeneric($index)">editar</button>
								</td>
								<td>
									<button type="button" class="btn btn-danger btn-xs"  data-ng-click="deleteGeneric($index)">excluir</button>
								</td>						
							</tr>                                                               
						</tbody>
					</table>                                                       
				</div>
			</div>
			
			<div id="resultErro" class="alert alert-warning" role="alert"  data-ng-show="msgErro" >
				<button type="button" class="close" ><span  data-ng-click="msgErro='';">&times;</span></button>
				<strong>Alerta! </strong>{{msgErro}} 
			</div>
			
		</div>                                                      
															
		<div class="col-sm-6">
			<div class="box box-primary"  data-ng-class="(genericName || genericModel || genericManufacturer) ? 'box-primary' : 'box-default'">
				<div class="box-header">
					<h3 class="box-title">Cadastro / Edição</h3>
					<a href="#" class="text-muted pull-right"  data-ng-click="refreshGenerics();"><i title="Refresh" class="fa fa-refresh"></i></a>
				</div>
				<div class="box-body">
					<form name="genericForm">

						<div class="row">
							<div class="col-md-4">							
								<div class="form-group">    
									<label class="control-label"><i class="fa fa-industry"></i> Fabricante
										<span class="text-red pull-right" data-ng-show="userForm.genericManufacturer.$dirty && userForm.genericManufacturer.$invalid">&nbsp[Campo Obrigat&oacute;rio]</span>
									</label>			                    
									<select name="genericManufacturer" class="form-control" data-live-search="true" 
										style="width: 100%;" tabindex="-1" aria-hidden="true"                              
											data-ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
												data-ng-model="genericManufacturer">
											<option value="">Selecione</option> 
									</select>			                    
								</div>
							</div>

							<div class="col-md-4">
								<label class="control-label"><i class="fa fa-simplybuilt"></i> Dispositivo
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
							
							<div class="col-md-4" style="padding-left: 5px !important; padding-right: 5px !important;">
								<label class="control-label">																												
									<strong><i class="fa fa-tachometer"> </i> Unidade:</strong>
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
							
						</div> 							        
						<div class="row">
							<div class="col-md-6">                                                                                                                            
								<div class="form-group">
									<label class="control-label">Nome</label>
									<span class="text-red"  data-ng-show="genericForm.genericName.$error.required && !genericForm.genericName.$pristine">  [Nome Obrigatorio]</span>
									<span  data-ng-show="genericForm.genericName.$error.maxlength">Até Máximo 20 caracteres</span>                                                                        
									<input id="idGenericName" class="form-control inputProfile" placeholder="Nome do Dispositivo"  data-ng-model="genericName"  data-ng-maxlength="20" name="genericName" required>                                                                        
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label">Modelo</label>
									<span class="text-red"  data-ng-show="genericForm.genericModel.$error.required && !genericForm.genericModel.$pristine">  [Modelo Obrigatorio]</span>
									<span  data-ng-show="genericForm.genericModel.$error.maxlength">Até Máximo 20 caracteres</span>                                                       
									<input class="form-control inputProfile" placeholder="Modelo do Dispositivo"  data-ng-model="genericModel"  data-ng-maxlength="20" name="genericModel" required>                                                
								</div>                                                                    
							</div>
						</div>
							
					</form>						
					
					<div class="box-footer">
						<button type="button"  data-ng-click="clearFormGeneric()" class="btn btn-default">Cancelar</button>                                                                
						<button type="button"  data-ng-click="saveGeneric();" class="btn btn-primary" 
								data-ng-disabled="(genericName && genericModel && genericManufacturer) ? false : true">&nbsp;Salar&nbsp;</button>								                                                                
					</div>                           
					
				</div>
			</div>
		</div>			
	</div>	  
		
</div>

    
    