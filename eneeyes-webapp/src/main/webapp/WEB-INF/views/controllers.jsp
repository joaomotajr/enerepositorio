<!--	 Content Wrapper. Contains page     content -->
 
	 
<div ng-controller="controllerController">
					
		<div class="row">	
					                                                    
			<div class="col-md-6">                                                        
				<div class="box box-primary" ng-class="(controllerName || controllerModel || controllerManufacturer) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de: PLCs / Controladoras</h3>
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
								<tr ng-repeat="item in controllers">
									<td>{{item.name}}</td>
									<td>{{item.model}}</td>															        
									<td>
										<button type="button" class="btn btn-info btn-xs" ng-click="editController($index)">editar</button>
									</td>
									<td>
										<button type="button" class="btn btn-danger btn-xs" ng-click="deleteController($index)">excluir</button>
									</td>						
								</tr>                                                               
							</tbody>
						</table>                                                       
					</div>
				</div>
				
				<div id="resultErro" class="alert alert-warning" role="alert" ng-show="msgErro" >
            		<button type="button" class="close" ><span ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
				
			</div>                                                      
																
			<div class="col-sm-6">
				<div class="box box-primary" ng-class="(controllerName || controllerModel || controllerManufacturer) ? 'box-primary' : 'box-default'">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edição</h3>
					</div>
					<div class="box-body">
						<form name="controllerForm">		
						
							<div class="box box-primary box-solid">
			                    <div class="box-header with-border"><strong> Fabricante</strong>
			                        <a href="#" popover> [Incluir Novo]</a>                        
				                </div>
			                	 
			                    <div class="box-body">
			                        <select class="form-control" data-live-search="true" 
			                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
			                                ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
                                            ng-model="controllerManufacturer">
                                            <option value="">Selecione</option> 
			                        </select>    
			                    </div>
			                    			                            
			                </div>
			                 							        
			                <div class="row">
			                 	<div class="col-md-6">                                                                                                                            
									<div class="form-group">
										<label class="control-label">Nome</label>
										<span class="text-red" ng-show="controllerForm.controllerName.$error.required && !controllerForm.controllerName.$pristine">  [Nome Obrigatorio]</span>
								        <span ng-show="controllerForm.controllerName.$error.maxlength">Até Máximo 20 caracteres</span>                                                                        
										<input id="idControllerName" class="form-control inputProfile" placeholder="Nome da PLC/Controladora" ng-model="controllerName" ng-maxlength="20" name="controllerName" required>                                                                        
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Modelo</label>
										<span class="text-red" ng-show="controllerForm.controllerModel.$error.required && !controllerForm.controllerModel.$pristine">  [Modelo Obrigatorio]</span>
								        <span ng-show="controllerForm.controllerModel.$error.maxlength">Até Máximo 20 caracteres</span>                                                       
										<input class="form-control inputProfile" placeholder="Modelo do PLC/Controladora" ng-model="controllerModel" ng-maxlength="20" name="controllerModel" required>                                                
									</div>                                                                    
								</div>
							</div>
			                 
			            </form>						
						
						<div class="box-footer">
							<button type="button" ng-click="clearFormController()" class="btn btn-default">Cancelar</button>                                                                
							<button type="button" ng-click="saveController();" class="btn btn-primary" 
								ng-disabled="(controllerName && controllerModel && controllerManufacturer) ? false : true">Salvar</button>								                                                                
						</div>                           
						
					</div>
				</div>
			</div>
			
		</div>	  
		
</div>

    
    