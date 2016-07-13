<!-- Content Wrapper. Contains page     content -->
	 
<div ng-controller="controllerController">
	<section class="content-header"></section>

	<section class="content">
		<div class="row content-container">	
					                                                    
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
						<tr ng-repeat="item in controllers.list">
							<td>{{item.name}}</td>
							<td>{{item.model}}</td>															        
							<td>
								<button type="button" class="btn btn-info btn-xs" ng-click="editarController($index)" 
									style="margin-top: -7px; margin-bottom: -7px">editar</button>
							</td>
							<td>
								<button type="button" class="btn btn-danger btn-xs" ng-click="deletarController($index)" 
									style="margin-top: -7px; margin-bottom: -7px">excluir</button>
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
						<form class="form-horizontal">
						<div class="box-body">                                                                                                                                    
							<div class="form-group">
								<label class="control-label">Nome</label>                                                                        
								<input id="idControllerNome" class="form-control inputProfile" placeholder="Nome da PLC/Controladora" ng-model="controllerName">                                                                        
							</div>
		
							<div class="form-group">
								<label class="control-label">Modelo</label>                                                       
								<input class="form-control inputProfile" placeholder="Modelo do PLC/Controladora"" ng-model="controllerModel">                                                
							</div>                                                                    
							
							<div class="form-group">
								<label class="control-label">Fabricante</label>                                                       
								<input class="form-control inputProfile" placeholder="Fabrincate da PLC/Controladora" ng-model="controllerManufacturer">                                                
							</div>
						</div><!-- /.box-body -->
		
						<div class="box-footer">                                                                
							<button type="button" ng-click="saveController();" class="btn btn-info" 
								ng-disabled="(controllerName && controllerModel && controllerManufacturer) ? false : true">OK</button>                                                                
							<button type="button" ng-click="clearFormController()" class="btn btn-default">Cancelar</button>                                                                
						</div><!-- /.box-footer -->
						</form>
					</div>
				</div>
			</div>
			
		</div>	  
	</section>	
</div>

    
    