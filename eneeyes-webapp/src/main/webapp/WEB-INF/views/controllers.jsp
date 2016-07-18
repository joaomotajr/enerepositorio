<!--	 Content Wrapper. Contains page     content -->
 
	 
<div ng-controller="controllerController">
					
		<div class="row">	
					                                                    
			<div class="col-md-6">                                                        
				<div class="box box-primary" ng-class="(controllerNome || controllerDescricao) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Controllers Cadastrados</h3>
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
			</div>                                                      
																
			<div class="col-sm-6">
				<div class="box box-primary" ng-class="(controllerNome || controllerDescricao) ? 'box-primary' : 'box-default'">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edição</h3>
					</div>
					<div class="box-body">
						<form class="form">		
						
							<div class="box box-default box-solid">
			                    <div class="box-header with-border"><strong>Fabricante</strong>
			                        <a href="#" popover> [Incluir Novo]</a>                        
				                </div>
			                
			                    <div class="box-body">
			                        <select id="selMatriculaFuncionario" class="form-control select2 select2-hidden-accessible" 
			                            style="width: 100%;" 
			                            tabindex="-1" 
			                            aria-hidden="true">                              
			                                <option ng-repeat="manufacturer in manufacturers">
			                                    {{manufacturer.nome}}
			                                </option>                    
			                        </select>    
			                    </div>        
			                </div>
			                 							                                                                                                                                    
							<div class="form-group">
								<label class="control-label">Nome</label>                                                                        
								<input id="idControllerNome" class="form-control inputProfile" placeholder="Nome da PLC/Controladora" ng-model="controllerName">                                                                        
							</div>
		
							<div class="form-group">
								<label class="control-label">Modelo</label>                                                       
								<input class="form-control inputProfile" placeholder="Modelo do PLC/Controladora" ng-model="controllerModel">                                                
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

    
    