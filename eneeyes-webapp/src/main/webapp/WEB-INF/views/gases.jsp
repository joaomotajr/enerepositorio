 
	<div  data-ng-controller="gasController">						
		<div class="row">				                                                    
			<div class="col-md-6">                                                        
				<div class="box box-primary"  data-ng-class="(gasName || gasCas || gasFormula || gasUnitMeterGases) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de Gases</h3>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Nome</th>
										<th>F�rumla</th>
										<th>C.A.S</th>                                                        
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr  data-ng-repeat="item in gases">
										<td>{{item.name}}</td>
										<td>{{item.formula}}</td>
										<td>{{item.cas}}</td>																        
										<td>
											<button type="button" class="btn btn-info btn-xs"  data-ng-click="editGas($index)">editar</button>
										</td>
										<td>
											<button type="button" class="btn btn-danger btn-xs"  data-ng-click="deleteGas($index)">excluir</button>
										</td>						
									</tr>                                                               
								</tbody>
							</table>
						</div>                                                       
					</div>
				</div>
				
				<div class="alert alert-warning" role="alert"  data-ng-show="msgErroGas" >
            		<button type="button" class="close" ><span  data-ng-click="msgErroGas='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErroGas}} 
        		</div>
			</div>                                                      
																
			<div class="col-sm-6">
				<div class="box box-primary"  data-ng-class="(gasName || gasCas || gasFormula || gasUnitMeterGases) ? 'box-primary' : 'box-default'">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edi��o</h3>
					</div>
					<div class="box-body">
						<form class="form">					
			                 							                                                                                                                                    
							<div data-ng-class="{'has-error': gasNameExist=='true'}">
								<label class="control-label">Nome</label>           
								<span data-ng-show="gasNameExist">Gas j� Existe</span>                                                             
								<input data-ng-keydown="keypress($event)" id="idGasName" class="form-control inputProfile" placeholder="Nome do Gas"  data-ng-model="gasName">                                                                        
							</div>
		
							<div class="form-group">
								<label class="control-label">N�mero CAS</label>                                                       
								<input class="form-control inputProfile" placeholder="C A S"  data-ng-model="gasCas">                                                
							</div>
							
							<div class="form-group">
								<label class="control-label">F�rmula</label>                                                       
								<input class="form-control inputProfile" placeholder="F�rmula do G�s"  data-ng-model="gasFormula">                                                
							</div>
							
							<!-- 
							<div class="form-group">
					            <label class="control-label">Unidade de Medida</label>
								<select class="form-control" data-live-search="true" 
		                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                                 data-ng-options="item as item.name for item in unitMetersGases | orderBy: 'name' track by item.uid" 
		                                          data-ng-model="gasUnitMeterGases">
		                                         <option value="">Selecione</option> 
		                        </select>               
	                        </div>
	                         -->
	                       			                 
			            </form>	
						
						<div class="box-footer">
							<button type="button"  data-ng-click="clearFormGas()" class="btn btn-default">Cancelar</button>                                                                
							<button type="button"  data-ng-click="saveGas();" class="btn btn-primary" 
								 data-ng-disabled="(gasName && gasCas && gasFormula) ? false : true">Salvar</button>								                                                                
						</div>					      
						
					</div>
				</div>
			</div>					
		</div>				
	</div>

    
    