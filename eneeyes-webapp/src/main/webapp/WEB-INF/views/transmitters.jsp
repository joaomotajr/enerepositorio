 
	<div  data-ng-controller="transmitterController">					
		<div class="row">						                                                    
			<div class="col-md-6">                                                        
				<div class="box box-primary"  data-ng-class="(transmitterName || transmitterModel || transmitterManufacturer) ? 'box-default' : 'box-primary'">
					<div class="box-header">
					  <h3 class="box-title">Cadastro de Transmissores</h3>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto">
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
									<tr  data-ng-repeat="item in transmitters">
										<td>{{item.name}}</td>
										<td>{{item.model}}</td>															        
										<td>
											<button type="button" class="btn btn-info btn-xs"  data-ng-click="editTransmitter($index)">editar</button>
										</td>
										<td>
											<button type="button" class="btn btn-danger btn-xs"  data-ng-click="deleteTransmitter($index)">excluir</button>
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
				<div class="box box-primary"  data-ng-class="(transmitterName || transmitterModel || transmitterManufacturer) ? 'box-primary' : 'box-default'">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edi��o</h3>
						<a href="#" class="text-muted pull-right"  data-ng-click="refreshTransmitters();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					<div class="box-body">
						<form class="form">		
						
							<div class="box box-primary box-solid">
			                    <div class="box-header with-border"><strong><i class="fa fa-industry"></i> Fabricante</strong>
			                        <a href="#" data-popover> [Incluir Novo]</a>                        
				                </div>
			                	 
			                    <div class="box-body">
			                        <select class="form-control" data-live-search="true" 
			                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
			                                 data-ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
                                             data-ng-model="transmitterManufacturer">
                                            <option value="">Selecione</option> 
			                        </select>    
			                    </div>
			                    			                            
			                </div>
			                 							     
			                <div class="row">
		                 		<div class="col-md-6">                                                                                                                                 
									<div class="form-group">
										<label class="control-label">Nome</label>                                                                        
										<input id="idTransmitterName" class="form-control inputProfile" placeholder="Nome do Transmissor"  data-ng-model="transmitterName">                                                                        
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Modelo</label>                                                       
										<input class="form-control inputProfile" placeholder="Modelo do Transmissor"  data-ng-model="transmitterModel">                                                
									</div>
								</div>
							</div>
							
							<div class="form-group">
					            <label class="control-label">Procotolo de Comunica��o</label>
								<select class="form-control" data-live-search="true" 
		                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                                 data-ng-options="item as item.name for item in commProtocols | orderBy: 'name' track by item.uid" 
		                                          data-ng-model="transmitterCommProtocol">
		                                         <option value="">Selecione</option> 
		                        </select>               
	                        </div>										                 
			            </form>			            				
						
						<div class="box-footer">
							<button type="button"  data-ng-click="clearFormTransmitter()" class="btn btn-default">Cancelar</button>                                                                
							<button type="button"  data-ng-click="saveTransmitter();" class="btn btn-primary" 
								 data-ng-disabled="(transmitterName && transmitterModel && transmitterManufacturer && transmitterCommProtocol) ? false : true">��Salvar��</button>								                                                                
						</div>
						      
						
					</div>
				</div>
			</div>		
		</div>		
	</div>

    
    