<!-- MENSAGENS -->
<div class="row">
	<p translate="{{messagePesquisa}}" ng-show="messagePesquisa.length" class="col-md-10 col-md-offset-1 alert alert-info" style="text-align: center;"></p>
	<p translate="{{messagePesquisaError}}" ng-show="messagePesquisaError.length" class="col-md-10 col-md-offset-1 alert alert-danger" style="text-align: center;"></p>
</div>

<!-- FORMULARIO -->
<form novalidate="novalidate" name="formulario" class="form-horizontal" ng-submit="#">
	<div class="form-group">
		<label class="col-sm-4 col-md-3 control-label">CNPJ da Filial*</label>
		<div class="col-sm-6 col-md-5">
			<select id="selectFiliais" class="form-control" ng-model="user.filialId" ng-change="pesquisaUserByFilial()" required="required">
				<option value="">Selecione...</option>
				<option ng-repeat="filial in filiaisContratante" value="{{filial.id}}" ng-disabled="filial.status == 'INACTIVE'">
					{{cnpjFormatter(filial.cnpj)}}&nbsp;({{filial.tradeName}})
				</option>
			</select>
		</div>
	</div>								
	<div class="form-group btn-block">
		<div class="col-sm-12" align="right">
			<a class="btn btn-info" data-toggle="modal" data-target="#modalAdicionaRelacionamentoUser" ng-disabled="formulario.$invalid" ng-click="getUsuariosdaFilial()">
				<i class="icon-plus icon-white"></i> Adicionar Relacionamento
			</a>
		</div>
	</div>				
</form>
<hr/>

<!-- USUARIOS RELACIONADOS -->
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-info">
			<div class="panel-heading"> Usu&aacute;rio(s) Relacionado(s) </div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover" id="dataTables-users">
                        <thead>
							<tr>
								<th>Nome</th>
								<th>Login</th>
								<th>Apelido</th>									
								<th>Tipo</th>
								<th>Status</th>
								<th>Data de Cria&ccedil;&atilde;o</th>
							</tr>
                        </thead>
						<tbody>
							<tr ng-repeat="user in users.listUser" ng-class="{'danger' : user.status == 'INACTIVE', 'success' : user.status == 'ACTIVE'}"
									   data-toggle="modal" data-target="#modalRemoveRelacionamentoUser" ng-click="setLogin(user.login)">
								<td>{{user.displayName}}</td>									
								<td>{{user.login}}</td>
								<td>{{user.nickname}}</td>
								<td align="center">{{getTipoUsuario(user.role)}}</td>
								<td align="center">{{getStatus(user.status)}}</td>
								<td align="center">{{user.createDate | date: 'dd/MM/yyyy'}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL REMOVE RELACIONAMENTO -->
<div id="modalRemoveRelacionamentoUser" class="modal fade col-md-offset-3 col-md-6" style="margin-top: 130px;">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title" align="center"><b>Exclus&atilde;o de Relacionamento</b></h4>
            </header>
            <div class="modal-body" align="center">
            	<p class="alert alert-danger">Voc&ecirc; tem certeza que deseja excluir o usu&aacute;rio <span style="text-transform: uppercase;">"{{user.login}}"</span> 
            			desta lista de relacionamentos?</p> 
            </div>
            <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
            	<button class="btn btn-danger" ng-click=fecharModal()><i class="icon-thumbs-down icon-white"></i>&nbsp;N&atilde;o</button>
      			<button class="btn btn-success" ng-click=excluirRelacionamento()><i class="icon-thumbs-up icon-white"></i>&nbsp;Sim</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL ADICIONA RELACIONAMENTO -->
<div id="modalAdicionaRelacionamentoUser" class="modal fade col-md-offset-1 col-md-10">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" align="center"><b>Inclus&atilde;o de Relacionamento</b></h4>
            </header>
            <div class="modal-body" align="center">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover" id="dataTables-filiais-contrato">
                        <thead ng-show="!noData">
							<tr>
								<th></th>
								<th>Nome</th>
								<th>Login</th>
								<th>Tipo</th>
								<th>Status</th>
							</tr>
                        </thead>
						<tbody>
							<tr ng-repeat="user in usuariosFilial" ng-class="{'danger' : user.status == 'INACTIVE', 'success' : user.status == 'ACTIVE'}">
								<td align="center" width="5px" >
									<input type="checkbox" ng-model="logins[$index]" ng-true-value={{user.login}}  ng-false-value="null" ng-disabled="user.status == 'INACTIVE'" />
								</td>
								<td>{{user.displayName}}</td>									
								<td>{{user.login}}</td>
								<td>{{getTipoUsuario(user.role)}}</td>
								<td align="center">{{getStatus(user.status)}}</td>
							</tr>
						</tbody>
						<div class="alert alert-warning" ng-show="noData">Todos os usu&aacute;rios cadastrados j&aacute; est&atilde;o associadas &agrave; esta filial.</div>						
					</table>
				</div>            		
            </div>
            <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
            	<button class="btn btn-danger" ng-click="fecharModalById('modalAdicionaRelacionamentoUser')"><i class="icon-thumbs-down icon-white"></i>&nbsp;Sair</button>
      			<button class="btn btn-success" ng-click=incluirRelacionamento() ng-disabled="noData"><i class="icon-thumbs-up icon-white"></i>&nbsp;Salvar</button>
            </div>
        </div>
    </div>
</div>