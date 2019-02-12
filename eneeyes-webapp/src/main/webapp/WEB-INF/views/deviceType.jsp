
<div  data-ng-controller="deviceTypeController">					
	<div class="row">						                                                    
		<div class="col-md-6">                                                        
			<div class="box box-primary"  data-ng-class="(deviceTypeName || deviceTypeInitials) ? 'box-default' : 'box-primary'">
				<div class="box-header">
					<h3 class="box-title">Tipos de Dispositivos</h3>
				</div>
				<div class="box-body">
					<div style="height: 500px; overflow: auto">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Tipo</th>
									<th>Descrição</th>
									<th>Símbolo</th>
									<th>Editar</th>
									<th>Excluir</th>						
								</tr>
							</thead>
							<tbody>                                                        
								<tr  data-ng-repeat="item in deviceTypes">
									<td>{{item.type}}</td>
									<td>{{item.description}}</td>															        
									<td><i class="fa" data-ng-class="item.symbol" style="margin: auto; font-size: 150%"></i></td>
									<td><button type="button" class="btn btn-info btn-xs"  data-ng-click="editDeviceType($index)">editar</button></td>
									<td><a type="button" class="btn btn-danger btn-xs" data-popover=' do Tipo: [ {{item.type}} ]' data-confirm="deleteDeviceType($index)" >excluir</a></td>						
								</tr>                                                               
							</tbody>
						</table>
					</div>                                                       
				</div>
			</div>
			<div class="alert alert-warning" role="alert"  data-ng-show="msgErroDeviceType" >
				<button type="button" class="close" ><span  data-ng-click="msgErroDeviceType='';">&times;</span></button>
				<strong>Alerta! </strong>{{msgErroDeviceType}} 
			</div>
		</div>                                                      
															
		<div class="col-md-6">
			<div class="box box-primary"  data-ng-class="(deviceTypeType || deviceTypeDescription || deviceTypeSymbol) ? 'box-primary' : 'box-default'">
				<div class="box-header">
					<h3 class="box-title">Cadastro / Edição</h3>
					<a href="#" class="text-muted pull-right"  data-ng-click="refreshDeviceType();"><i title="Refresh" class="fa fa-refresh"></i></a>
				</div>
				<div class="box-body">
					<form class="form">									                 							     
						<div class="row">
							<div class="col-md-6">                                                                                                                                 
								<div class="form-group">
									<label class="control-label">Tipo</label>          
									<span class="text-red" data-ng-show="deviceTypeNameExist">Dispositivo já Existe</span>                                                               
									<input id="deviceTypeType" data-ng-keydown="keypress($event)" class="form-control inputProfile" placeholder="Tipo do Dispostivo"  data-ng-model="deviceTypeType">                                                                        
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label">Descrição</label>                                                       
									<input class="form-control inputProfile" placeholder="Descrição do Dispositivos"  data-ng-model="deviceTypeDescription">                                                
								</div>
							</div>
						</div>							
						<div class="row">
							<div class="col-md-12"> 
								<label>Simbolo</label>
								<div class="btn-group input-block-level" style="width: 100%;">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width: 100%;background-color: rgba(255, 255, 255, 0.15);border-color: #ccc;">
										<span data-ng-show="deviceTypeSymbol == null">Selecione</i><span class="caret"></span></span>
										<span><i class="fa fa-2x" data-ng-class="deviceTypeSymbol"></i><span class="caret"></span></span>
									</button>
									<ul class="dropdown-menu list-inline" role="menu" style="width: 100%;height: 40vh;overflow-y: auto; text-align: center;">
										<li data-ng-repeat="i in symbols | orderBy: 'id'" data-ng-click="changeSymbol(i.id);" style="padding: 10px;" >
											<span class="cursor fa fa-2x" title="{{i.id}}" data-ng-class="i.id"></span>
											<!-- <br> <span style="font-size: 0.8em">{{i.id}}</span> -->
										</li>
									</ul>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12"> 
								<div class="box box-primary box-solid">
									<div class="box-header with-border">
										<h3 class="box-title">Visualização selecionada: <strong>{{deviceTypeGraphic.graphicType}}</strong></h3>
									</div>					                	 
									<div class="box-body">
											<div class="btn-group input-block-level" style="width: 100%;">
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width: 100%;background-color: rgba(255, 255, 255, 0.15);border-color: #ccc;text-align: -webkit-center;">
												<span data-ng-show="deviceTypeGraphic == null">Selecione</i><span class="caret"></span></span>
												<span><i class="fa fa-2x"></i><span class="caret"></span></span>
												<img class="img-responsive imgGraphic" ng-show="deviceTypeGraphic.img" src="{{deviceTypeGraphic.img}}" />
											</button>
											<ul class="dropdown-menu list-inline" role="menu" style="width: 100%;height: 40vh;overflow-y: auto; text-align: center;">
												<li data-ng-repeat="i in images" data-ng-click="changeGraphic(i);" style="padding: 5px; border-style: double;" >
													<span class="cursor" ng-bind="i.graphicType"></span><br>
													<img class="cursor imgGraphicList img-thumbnail" ng-src="{{i.img}}" />												
												</li>
											</ul>
										</div>										
									</div>
								</div>
							</div>
						</div>
					</form>
					
					<div class="box-footer">
						<button type="button"  data-ng-click="clearFormDeviceType()" class="btn btn-default">Cancelar</button>                                                                
						<button type="button"  data-ng-click="saveDeviceType();" class="btn btn-primary" 
								data-ng-disabled="(deviceTypeType && deviceTypeDescription && deviceTypeSymbol && deviceTypeGraphic) ? false : true">&nbsp;&nbsp;&nbsp;Salvar&nbsp;&nbsp;</button>								                                                                
					</div>
				</div>
			</div>
		</div>		
	</div>		
</div>