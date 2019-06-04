
<div  data-ng-controller="perfilAlarmController">					
	<div class="row">						                                                    
		<div class="col-md-6">                                                        
			<div class="box box-primary"  data-ng-class="(perfilAlarmName || perfilAlarmInitials) ? 'box-default' : 'box-primary'">
				<div class="box-header">
					<h3 class="box-title">Perfis de Alarmes</h3>
				</div>
				<div class="box-body">
					<div style="height: 500px; overflow: auto">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Titulo</th>
									<th>Descrição</th>
									<th>Símbolo</th>
									<th>Editar</th>
									<th>Excluir</th>						
								</tr>
							</thead>
							<tbody>                                                        
								<tr  data-ng-repeat="item in perfilAlarms">
									<td>{{item.type}}</td>
									<td>{{item.description}}</td>															        
									<td><i class="fa" data-ng-class="item.symbol" style="margin: auto; font-size: 150%"></i></td>
									<td><button type="button" class="btn btn-info btn-xs"  data-ng-click="editPerfilAlarm($index)">editar</button></td>
									<td><a type="button" class="btn btn-danger btn-xs" data-popover=' do Tipo: [ {{item.type}} ]' data-confirm="deletePerfilAlarm($index)" >excluir</a></td>						
								</tr>                                                               
							</tbody>
						</table>
					</div>                                                       
				</div>
			</div>
			<div class="alert alert-warning" role="alert"  data-ng-show="msgErroPerfilAlarm" >
				<button type="button" class="close" ><span  data-ng-click="msgErroPerfilAlarm='';">&times;</span></button>
				<strong>Alerta! </strong>{{msgErroPerfilAlarm}} 
			</div>
		</div>                                                      
															
		<div class="col-md-6">
			<div class="box box-primary"  data-ng-class="(perfilAlarmType || perfilAlarmDescription || perfilAlarmSymbol) ? 'box-primary' : 'box-default'">
				<div class="box-header">
					<h3 class="box-title">Cadastro / Edição</h3>
					<span class="text-muted pull-right cursor" data-ng-click="refreshPerfilAlarms();"><i title="Refresh" class="fa fa-refresh"></i></span>
				</div>
				<div class="box-body">
					<form class="form" name="perfilAlarmForm">									                 							     
						<div class="row">
							<div class="col-md-6">                                                                                                                                 
								<div class="form-group">
									<label class="control-label">Título</label>          
									<span class="text-red" data-ng-show="perfilAlarmNameExist">Titulo já Existe</span>
									<span class="text-red pull-right" data-ng-show="perfilAlarmForm.perfilAlarmTitulo.$error.required && !perfilAlarmForm.perfilAlarmTitulo.$pristine">[Titulo Obrigatorio]</span>
									<span class="text-red pull-right" data-ng-show="perfilAlarmForm.perfilAlarmTitulo.$error.maxlength">Tamanho Máximo 15 caracteres</span>
									<input name="perfilAlarmTitulo" id="perfilAlarmTitulo" class="form-control inputProfile" 
									placeholder="Título do Alarme"  data-ng-model="perfilAlarmType" data-ng-maxlength="15" required> 
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label">Descrição</label>                                                       
									<input class="form-control inputProfile" placeholder="Descrição do Alarme" data-ng-model="perfilAlarmDescription">                                                
								</div>
							</div>
						</div>							
						<div class="row">
							<div class="col-md-12"> 
								<label>Simbolo</label>
								<div class="btn-group input-block-level" style="width: 100%;">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width: 100%;background-color: rgba(255, 255, 255, 0.15);border-color: #ccc;">
										<span data-ng-show="perfilAlarmSymbol == null">Selecione</i><span class="caret"></span></span>
										<span><i class="fa fa-2x" data-ng-class="perfilAlarmSymbol"></i><span class="caret"></span></span>
									</button>
									<ul class="dropdown-menu list-inline" role="menu" style="width: 100%;height: 40vh;overflow-y: auto; text-align: center;">
										<li data-ng-repeat="i in symbols | orderBy: 'id'" data-ng-click="changeSymbol(i.id);" style="padding: 10px;" >
											<span class="cursor fa-2x" title="{{i.id}}" data-ng-class="i.id"></span>											
										</li>
									</ul>
								</div>
							</div>
						</div>					
					</form>
					
					<div class="box-footer">
						<button type="button"  data-ng-click="clearFormPerfilAlarm()" class="btn btn-default">Cancelar</button>                                                                
						<button type="button"  data-ng-click="savePerfilAlarm();" class="btn btn-primary" 
								data-ng-disabled="(perfilAlarmType && perfilAlarmDescription && perfilAlarmSymbol) ? false : true">&nbsp;&nbsp;&nbsp;Salvar&nbsp;&nbsp;</button>								                                                                
					</div>
				</div>
			</div>
		</div>		
	</div>		
</div>