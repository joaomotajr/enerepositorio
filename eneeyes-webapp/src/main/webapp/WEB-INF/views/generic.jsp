 
<div  data-ng-controller="genericController">
					
	<div class="row">	
																	
		<div class="col-md-6">                                                        
			<div class="box box-primary"  data-ng-class="(genericName || genericModel || genericManufacturer) ? 'box-default' : 'box-primary'">
				<div class="box-header">
					<h3 class="box-title">Cadastro de Dispositivos de Medi&ccedil;&atilde;o</h3>					  
				</div>
				<div class="box-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Simbolo</th>
								<th>Nome</th>
								<th>Modelo</th>                                                            
								<th>Editar</th>
								<th>Excluir</th>						
							</tr>
						</thead>
						<tbody>                                                        
							<tr data-ng-repeat="item in generics">										
								<td>&nbsp;<i class="fa" data-ng-class="item.deviceType.symbol" style="font-size:125%;"></i>&nbsp;</td>
								<td>{{item.name}}</td>
								<td>{{item.model}}</td>															        
								<td><button type="button" class="btn btn-primary btn-xs"   data-ng-click="editGeneric($index)">editar</button></td>
								<td><button type="button" class="btn btn-danger btn-xs" data-ng-click="deleteGeneric($index)">excluir</button></td>						
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
			<div class="box box-primary"  data-ng-class="(genericName || genericModel || genericManufacturer || genericRangeMin || genericRangeMax) ? 'box-primary' : 'box-default'">
				<div class="box-header">
					<h3 class="box-title">Cadastro / Edi&ccedil;&atilde;o</h3>
					<span class="text-muted pull-right cursor" data-ng-click="refreshGenerics();"><i title="Refresh" class="fa fa-refresh"></i></span>
				</div>
				<div class="box-body">
					<form name="genericForm">
						<div class="row">
							<div class="col-md-6">							    
								<input type="file" id="idInputImageGeneric" style='display:none'>							    
								<div class="box box-primary">
									<div class="box-body box-profile">                          
										<img class="profile-user-img img-responsive img-circle imgDetector" style="margin: 0 auto" alt="Imagem do Perfil" 
											data-ng-src="{{genericImage}}" onError="this.src='/assets/img/cover.jpg'">
										<p class="text-muted text-center data-ng-binding">{{genericName}} - {{genericModel}} </p>                    
										<a href="#" class="icon fa fa-photo fa-2.0x pull-right" data-ng-click="addPhoto();" title="Trocar foto"></a>																									
									</div>
								</div>															
							</div>						
							
							<div>
								<div class="col-md-6">							
									<div class="form-group">    
										<label class="control-label"><i class="fa fa-industry"></i> Fabricante</label>
										<span class="text-red pull-right" data-ng-show="genericForm.genericManufacturer.$dirty && genericForm.genericManufacturer.$invalid">[Obrigat&oacute;rio]</span>
										
										<div data-ng-class="{'has-error': genericForm.genericManufacturer.$dirty && genericForm.genericManufacturer.$invalid}">
										<select name="genericManufacturer" class="form-control" data-live-search="true" 
											style="width: 100%;" tabindex="-1" aria-hidden="true"                              
												data-ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
													data-ng-model="genericManufacturer" required>
												<option value="">Selecione</option> 
										</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<label class="control-label"><i class="fa fa-simplybuilt"></i> Dispositivo</label>
									<span class="text-red pull-right" data-ng-show="genericForm.deviceType.$dirty && genericForm.deviceType.$invalid">[Obrigat&oacute;rio]</span>
									
									<div data-ng-class="{'has-error': genericForm.deviceType.$dirty && genericForm.deviceType.$invalid}">               							                									
										<select name="deviceType" class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true"                              
											data-ng-options="item as item.description for item in deviceTypes | orderBy: 'type' track by item.uid" 
											data-ng-model="deviceType" required>
											<option value="">Selecione</option> 
										</select>							
									</div>
								</div>
							</div>		
							
						</div>							
						<div class="box box-primary box-solid">
							<div class="box-header with-border"><strong><i class="fa fa-dashboard"></i> Range de Detec&ccedil;&atilde;o *</strong></div>					                	 
							<div class="box-body">
								<div class="row">
									<div class="col-md-6">
										<label class="control-label"><i class="fa fa-balance-scale"> </i> Unidade:</label>
										<span class="text-red pull-right" data-ng-show="genericForm.unitMeter.$dirty && genericForm.unitMeter.$invalid">[Obrigat&oacute;rio]</span>
										
										<div data-ng-class="{'has-error': genericForm.unitMeter.$dirty && genericForm.unitMeter.$invalid}"> 
											<select name="unitMeter" class="form-control" data-live-search="true" style="width: 100%;" tabindex="-1" aria-hidden="true" data-ng-change="unitMeterChange();"
												data-ng-options="item as item.description for item in unitMeters | orderBy: 'symbol' track by item.uid" 
												data-ng-model="unitMeter" required>
												<option value="">Selecione</option> 
											</select>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label">Min</label>
											<input class="form-control" placeholder="Min" data-ng-model="genericRangeMin" data-ng-disabled="unitMeter.uid==0" required>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label">M&aacute;x</label>
											<input class="form-control" placeholder="Max" data-ng-model="genericRangeMax" data-ng-disabled="unitMeter.uid==0" required>
										</div>
									</div>									
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">                                                                                                                            
								<div class="form-group">
									<label class="control-label">Nome</label>
									<span class="text-red" data-ng-show="genericNameExist">Dispositivo j&aacute; Existe</span>
									<span class="text-red"  data-ng-show="genericForm.genericName.$error.required && !genericForm.genericName.$pristine">  [Nome Obrigat&oacute;rio]</span>
									<span  data-ng-show="genericForm.genericName.$error.maxlength">20 caracteres</span>                                                                        
									<div data-ng-class="{'has-error': genericForm.genericName.$dirty && genericForm.genericName.$invalid}">
										<input id="idGenericName" class="form-control inputProfile" placeholder="Nome do Dispositivo"  data-ng-model="genericName"  data-ng-maxlength="20" name="genericName" required>                                                                        
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label">Modelo</label>
									<span class="text-red"  data-ng-show="genericForm.genericModel.$error.required && !genericForm.genericModel.$pristine">  [Modelo Obrigat&oacute;rio]</span>
									<span  data-ng-show="genericForm.genericModel.$error.maxlength">20 caracteres</span> 
									<div data-ng-class="{'has-error': genericForm.genericModel.$dirty && genericForm.genericModel.$invalid}">
										<input class="form-control inputProfile" placeholder="Modelo do Dispositivo" name="genericModel" data-ng-model="genericModel"  data-ng-maxlength="20" name="genericModel" required>                                                
									</div>
								</div>                                                                    
							</div>
						</div>							
					</form>					
					<div class="box-footer">
						<button type="button" data-ng-click="clearFormGeneric(); genericForm.$setPristine()" class="btn btn-default">Cancelar</button>                                                                
						<button type="button" data-ng-click="saveGeneric(); genericForm.$setPristine()" class="btn btn-primary" 
								data-ng-disabled="genericForm.$valid ? false : true">&nbsp;Salvar&nbsp;</button>
					</div>					
				</div>
			</div>
		</div>			
	</div>	  
		
</div>

    
    