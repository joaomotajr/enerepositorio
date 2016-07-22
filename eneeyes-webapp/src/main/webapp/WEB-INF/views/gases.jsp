<!--	 Content Wrapper. Contains page     content -->
 
	 
<div ng-controller="gasController">
					
	<div class="row">	
				                                                    
		<div class="col-md-6">                                                        
			<div class="box box-primary" ng-class="(gasName || gasCas || gasFormula || gasUnitMeterGases) ? 'box-default' : 'box-primary'">
				<div class="box-header">
				  <h3 class="box-title">Cadastro de Gases</h3>
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
					<tr ng-repeat="item in gases">
						<td>{{item.name}}</td>
						<td>{{item.formula}}</td>															        
						<td>
							<button type="button" class="btn btn-info btn-xs" ng-click="editGas($index)">editar</button>
						</td>
						<td>
							<button type="button" class="btn btn-danger btn-xs" ng-click="deleteGas($index)">excluir</button>
						</td>						
					</tr>                                                               
				</tbody>
			</table>                                                       
			</div>
			</div>
		</div>                                                      
															
		<div class="col-sm-6">
			<div class="box box-primary" ng-class="(gasName || gasCas || gasFormula || gasUnitMeterGases) ? 'box-primary' : 'box-default'">
				<div class="box-header">
					<h3 class="box-title">Cadastro / Edição</h3>
				</div>
				<div class="box-body">
					<form class="form">					
		                 							                                                                                                                                    
						<div class="form-group">
							<label class="control-label">Nome</label>                                                                        
							<input id="idGasName" class="form-control inputProfile" placeholder="Nome do Gas" ng-model="gasName">                                                                        
						</div>
	
						<div class="form-group">
							<label class="control-label">Número CAS</label>                                                       
							<input class="form-control inputProfile" placeholder="C A S" ng-model="gasCas">                                                
						</div>
						
						<div class="form-group">
							<label class="control-label">Fórmula</label>                                                       
							<input class="form-control inputProfile" placeholder="Fórmula do Gás" ng-model="gasFormula">                                                
						</div>
						<div class="form-group">
				            <label class="control-label">Unidade de Medida</label>
							<select class="form-control" data-live-search="true" 
	                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
	                                ng-options="item as item.name for item in unitMetersGases | orderBy: 'name' track by item.uid" 
	                                         ng-model="gasUnitMeterGases">
	                                         <option value="">Selecione</option> 
	                        </select>               
                        </div>
                       			                 
		            </form>	
		            				
					
					<div class="box-footer">
						<button type="button" ng-click="clearFormGas()" class="btn btn-default">Cancelar</button>                                                                
						<button type="button" ng-click="saveGas();" class="btn btn-primary" 
							ng-disabled="(gasName && gasCas && gasFormula && gasUnitMeterGases) ? false : true">Salvar</button>								                                                                
					</div>
					      
					
				</div>
			</div>
		</div>
		
	</div>	  
		
</div>

    
    