 
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
										<th>Fabricante</th>                                                            
										<th>Editar</th>
										<th>Excluir</th>						
									</tr>
								</thead>
								<tbody>                                                        
									<tr  data-ng-repeat="item in transmitters">
										<td>{{item.name}}</td>
										<td>{{item.model}}</td>															        
										<td>{{item.manufacturerDto.name}}</td>
										<td>
											<button type="button" class="btn btn-primary btn-xs"  data-ng-click="editTransmitter($index)">editar</button>
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
				<div class="alert alert-warning" role="alert"  data-ng-show="msgErroTransmitter" >
            		<button type="button" class="close" ><span  data-ng-click="msgErroTransmitter='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErroTransmitter}} 
        		</div>
			</div>                                                      
																
			<div class="col-sm-6">
				<div class="box box-primary"  data-ng-class="(transmitterName || transmitterModel || transmitterManufacturer) ? 'box-primary' : 'box-default'">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edição</h3>
						<a href="#" class="text-muted pull-right"  data-ng-click="refreshTransmitters();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					<div class="box-body">
						<form class="form">					
								 
			                <div class="form-group">    
		                    	<label class="control-label">Fabricantes</label>  
		                        <select class="form-control" data-live-search="true" 
		                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                                 data-ng-options="item as item.name for item in manufacturers | orderBy: 'name' track by item.uid" 
	                                           data-ng-model="transmitterManufacturer">
	                                          <option value="">Selecione</option> 
		                        </select>
	                        </div>
	                        						     
			                <div class="row">
		                 		<div class="col-md-6">                                                                                                                                 
									<div class="form-group">
										<label class="control-label">Nome</label>   
										<span class="text-red" data-ng-show="transmitterNameExist">Transmissor ja Existe</span>                                                                      
										<input id="idTransmitterName" data-ng-keydown="keypress($event)" class="form-control inputProfile" placeholder="Nome do Transmissor"  data-ng-model="transmitterName">                                                                        
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
					            <label class="control-label">Procotolo de Comunicação</label>
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
								 data-ng-disabled="(transmitterName && transmitterModel && transmitterManufacturer && transmitterCommProtocol) ? false : true">  Salvar  </button>								                                                                
						</div>
						      
						
					</div>
				</div>
			</div>		
		</div>		
	</div>

    
    