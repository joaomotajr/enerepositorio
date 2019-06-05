<div  data-ng-controller="unitMeterController">						
	<div class="row">				                                                    
		<div class="col-md-6">                                                        
			<div class="box box-primary"  data-ng-class="(unitMeterDescription || unitMeterSymbol) ? 'box-default' : 'box-primary'">
				<div class="box-header">
					<h3 class="box-title">Cadastro de Unidades de Medidas</h3>					  
				</div>
				<div class="box-body">
					<div style="height: 500px; overflow: auto">
						<table class="table table-hover">
							<thead>
								<tr>									
									<th>Símbolo</th>
									<th>Description</th>
									<th>Editar</th>
									<th>Excluir</th>						
								</tr>
							</thead>
							<tbody>                                                        
								<tr  data-ng-repeat="item in unitMeters" data-ng-class="{'bg-info': item.uid==0}">
									<td>{{item.symbol}}</td>
									<td>{{item.description}}</td>									
									<td><button type="button" class="btn btn-primary btn-xs" data-ng-disabled="item.uid==0"  data-ng-click="editUnitMeter($index)">editar</button></td>
									<td><a type="button" class="btn btn-danger btn-xs" data-ng-disabled="item.uid==0" data-popover=' da Unidade: [ {{item.description}} ]' data-confirm="deleteUnitMeter($index)" >excluir</a></td>						
								</tr>                                                               
							</tbody>
						</table>
					</div>                                                       
				</div>
			</div>				
			<div class="alert alert-warning" role="alert"  data-ng-show="msgErroUnitMeter" >
				<button type="button" class="close" ><span  data-ng-click="msgErroUnitMeter='';">&times;</span></button>
				<strong>Alerta! </strong>{{msgErroUnitMeter}} 
			</div>
		</div>															
		<div class="col-sm-6">
			<div class="box box-primary"  data-ng-class="(unitMeterDescription || unitMeterSymbol) ? 'box-primary' : 'box-default'">
				<div class="box-header">
					<h3 class="box-title">Cadastro / Edição</h3>
				</div>
				<div class="box-body">
					<form class="form">			                 							                                                                                                                                    
						<div class="form-group" data-ng-class="{'has-error': unitMeterDescriptionExist=='true'}">
							<label class="control-label">Description</label>
							<span data-ng-show="unitMeterDescriptionExist">Unidade já Existe</span>                                                             
							<input data-ng-keydown="keypress($event)" id="idUnitMeterDescription" class="form-control inputProfile" placeholder="Descrição da Unidade de Medida"  data-ng-model="unitMeterDescription">
						</div>		
						<div class="form-group">
							<label class="control-label">Simbolo</label>                                                       
							<input class="form-control inputProfile" placeholder="Simbolo"  data-ng-model="unitMeterSymbol">
						</div>														
					</form>						
					<div class="box-footer">
						<button type="button"  data-ng-click="clearFormUnitMeter()" class="btn btn-default">Cancelar</button>                                                                
						<button type="button"  data-ng-click="saveUnitMeter();" class="btn btn-primary" 
								data-ng-disabled="(unitMeterDescription && unitMeterSymbol) ? false : true">Salvar</button>								                                                                
					</div>						
				</div>
			</div>
		</div>					
	</div>				
</div>

    
    