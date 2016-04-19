<!-- MENSAGENS -->
<div class="row">
	<p translate="{{messagePesquisa}}" ng-show="messagePesquisa.length" class="col-md-10 col-md-offset-1 alert alert-info" style="text-align: center;"></p>
	<p translate="{{messagePesquisaError}}" ng-show="messagePesquisaError.length" class="col-md-10 col-md-offset-1 alert alert-danger" style="text-align: center;"></p>
</div>

<!-- FORMULARIO PESQUISA -->     
<form novalidate="novalidate" name="formulario" class="form-horizontal" ng-submit="pesquisaUser()">
	<div class="form-group ">
		<label class="col-sm-4 col-md-3 control-label" title="CPF Cliente">CPF </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" data-mask="999.999.999-99" mask ng-model="user.cpf">
		</div>	
		<label class="col-sm-4 col-md-2 control-label">CNPJ Raiz </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" data-mask="99.999.999" mask ng-model="user.cnpj">
		</div>
	</div>	
	<div class="form-group ">
		<label class="col-sm-4 col-md-3 control-label" title="Nome do Cliente">Nome do Usu&aacuterio</label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" ng-model="user.displayName">
		</div>
		<label class="col-sm-4 col-md-2 control-label">Apelido</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" class="form-control" ng-model="user.nickname">
		</div>
	</div>					
	<div class="form-group">
		<label class="col-sm-4 col-md-3 control-label">Login</label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" ng-model="user.login">
		</div>
		<label class="col-sm-4 col-md-2 control-label">Tipo de Usu&aacute;rio</label>
		<div class="col-sm-6 col-md-3">
			<select class="form-control" ng-model="user.role">
				<option value="">Selecione...</option>
				<option ng-show="userLogado.isAdmin" value="1">Administrador</option>
				<option ng-show="userLogado.isAdmin" value="2">Contratante</option>
				<option ng-show="userLogado.isAdmin || userLogado.isContractor" value="3">Gerente</option>
				<option ng-show="userLogado.isAdmin || userLogado.isContractor" value="4">Operador</option>
			</select>
		</div>
	</div>	
	<div class="form-group">
		<label class="col-sm-4 col-md-3 control-label">Status</label>
		<div class="col-sm-6 col-md-3">
			<select class="form-control" ng-model="user.status">
				<option value="">Selecione...</option>
				<option value="ACTIVE">Ativo</option>
				<option value="INACTIVE">Inativo</option>
			</select>
		</div>
		<div class="col-sm-6 col-md-5" align="right">
			<button class="btn btn-info btn-round"><i class="icon-search icon-white"></i> Pesquisar</button>
		</div>
	</div>	
</form>

<!-- BOTAO NOVO USUARIO -->
<hr/>
	<div class="form-group">
		<div class="col-sm-12" align="left">
			<a class="btn btn-success" data-toggle="modal" data-target="#modalCadastrarUser" 
				ng-click="novoUsuario()"><i class="icon-plus icon-white"></i>&nbsp;Novo Usu&aacute;rio</a>
		</div>
	</div>
<hr/>

<!-- RESULTA DA PESQUISA -->
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-info">
			<div class="panel-heading"> Resultado </div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover" id="dataTables-users">
                        <thead>
							<tr>
								<th width="150px">CPF</th>
								<th width="200px">Nome</th>
								<th>Login</th>
								<th>Tipo</th>
								<th>Status</th>
								<th>Data de Cria&ccedil;&atilde;o</th>
							</tr>
                        </thead>
						<tbody>
							<tr ng-repeat="user in users.listUser" ng-class="{'danger' : user.status == 'INACTIVE', 'success' : user.status == 'ACTIVE'}"
									   data-toggle="modal" data-target="#modalDetalheUser" ng-click="detalheUser($index)">
								<td width="150px" align="center">{{cpfFormatter(user.cpf)}}</td>
								<td width="200px">{{user.displayName}}</td>									
								<td>{{user.login}}</td>
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

<!-- MODAL CADASTRO USUARIO -->
<div id="modalCadastrarUser" class="modal fade col-md-12" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" align="center"><b>Inclus&atilde;o de Usu&aacute;rio</b></h4>
            </header>
            <div class="modal-body box dark" align="center">
				<jsp:include page="user-cadastro.jsp"/>
            </div>
             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<button class="col-md-1 col-md-offset-11 btn btn-default" ng-click="fecharModal()"><i class="icon-exit icon-white"></i>Fechar</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL EDICAO USUARIO -->
<div id="modalDetalheUser" class="modal fade col-md-12" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" align="center"><b>Gerenciar Usu&aacute;rio: {{user.displayName}}</b></h4>
            </header>
            <div class="modal-body box dark" align="center">
				<jsp:include page="user-cadastro.jsp"/>
            </div>
            <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<button class="col-md-1 col-md-offset-11 btn btn-default" ng-click="fecharModal()"><i class="icon-exit icon-white"></i>Fechar</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL EXCLUSAO DE USUARIO -->
<div id="modalExcluirUser" class="modal fade col-md-offset-3 col-md-6" style="margin-top: 130px;">
    <div class="modal-dialog">
        <div class="modal-content">
			<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title" align="center"><b>Exclus&atilde;o de Usu&aacute;rio</b></h4>
            </header>          
            <div class="modal-body" align="center">
				<p class="alert alert-danger">Voc&ecirc; tem certeza que deseja excluir o usu&aacute;rio "{{user.displayName | uppercase}}"?</p>
            </div>
             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<a class="btn btn-danger"  ng-click="fecharModalById('modalExcluirUser')"><i class="icon-thumbs-down icon-white"></i>&nbsp;N&atilde;o</a>
				<a class="btn btn-success" ng-click="excluirUsuario()"><i class="icon-thumbs-up icon-white"></i>&nbsp;Sim</a>
            </div>
        </div>
    </div>
</div>

<!-- MODAL INATIVACAO DE USUARIO -->
<div id="modalInativarUser" class="modal fade col-md-offset-3 col-md-6" style="margin-top: 130px;">
    <div class="modal-dialog">
        <div class="modal-content">
			<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title" align="center"><b>Inativa&ccedil;&atilde;o de Usu&aacute;rio</b></h4>
            </header>          
            <div class="modal-body" align="center">
				<p class="alert alert-danger"><strong>Aten&ccedil;&atilde;o</strong>: a inativa&ccedil;&atilde;o do usu&aacute;rio bloquear&aacute; o acesso total ao sistema. 
				Voc&ecirc; tem certeza que deseja inativar o usu&aacute;rio "{{user.displayName | uppercase}}"?</p>
            </div>
             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<a class="btn btn-danger"  ng-click="fecharModalById('modalInativarUser')"><i class="icon-thumbs-down icon-white"></i>&nbsp;N&atilde;o</a>
				<a class="btn btn-success" ng-click="inativarUser()"><i class="icon-thumbs-up icon-white"></i>&nbsp;Sim</a>
            </div>
        </div>
    </div>
</div>

<!-- MODAL CADASTRO FILIAL -->
<div id="modalCadastrarFilial" class="modal fade col-md-offset-1 col-md-10" style="margin-top: -25px;">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" align="center"><b>Inclus&atilde;o de Filial</b></h4>
            </header>
            <div class="modal-body box dark" align="center" ng-controller="FilialController">
				<jsp:include page="filial-cadastro.jsp"/>
            </div>
             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<button class="col-md-1 col-md-offset-11 btn btn-default" ng-click="fecharModalById('modalCadastrarFilial')"><i class="icon-exit icon-white"></i>Fechar</button>
            </div>
        </div>
    </div>
</div>