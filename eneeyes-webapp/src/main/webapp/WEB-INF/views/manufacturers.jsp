 
	<div  data-ng-controller="manufacturerController">					
		<div class="row">						                                                    
			<div class="col-md-6">                                                        
				<div class="box box-primary"  data-ng-class="(manufacturerName || manufacturerInitials) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de Fabricantes</h3>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Iniciais</th>                                                            
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr  data-ng-repeat="item in manufacturers">
										<td>{{item.name}}</td>
										<td>{{item.initials}}</td>															        
										<td>
											<button type="button" class="btn btn-info btn-xs"  data-ng-click="editManufacturer($index)">editar</button>
										</td>
										<td>
											<button type="button" class="btn btn-danger btn-xs"  data-ng-click="deleteManufacturer($index)">excluir</button>
										</td>						
									</tr>                                                               
								</tbody>
							</table>
						</div>                                                       
					</div>
				</div>
				<div id="resultErro" class="alert alert-warning" role="alert"  data-ng-show="msgErro" >
            		<button type="button" class="close" ><span  data-ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
			</div>                                                      
																
			<div class="col-sm-6">
				<div class="box box-primary"  data-ng-class="(manufacturerName || manufacturerInitials) ? 'box-primary' : 'box-default'">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edição</h3>
						<a href="#" class="text-muted pull-right"  data-ng-click="refreshManufacturer();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					<div class="box-body">
						<form class="form">		
									                 							     
			                <div class="row">
		                 		<div class="col-md-6">                                                                                                                                 
									<div class="form-group">
										<label class="control-label">Nome</label>                                                                        
										<input id="idTransmitterName" class="form-control inputProfile" placeholder="Nome do Fabricante"  data-ng-model="manufacturerName">                                                                        
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Iniciais</label>                                                       
										<input class="form-control inputProfile" placeholder="Iniciais do Fabricante"  data-ng-model="manufacturerInitials">                                                
									</div>
								</div>
							</div>																	                 
			            </form>			            				
						
						<div class="box-footer">
							<button type="button"  data-ng-click="clearFormManufacturer()" class="btn btn-default">Cancelar</button>                                                                
							<button type="button"  data-ng-click="saveManufacturer();" class="btn btn-primary" 
								 data-ng-disabled="(manufacturerName && manufacturerInitials) ? false : true">  Salvar  </button>								                                                                
						</div>
						      
						
					</div>
				</div>
			</div>		
		</div>		
	</div>

    
    