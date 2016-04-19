<!-- MENSAGENS -->
<div class="row">
	<p translate="{{messageCadastro}}" ng-show="messageCadastro.length" class="col-md-10 col-md-offset-1 alert alert-info" style="text-align: center;"></p>
	<p translate="{{messageCadastroError}}" ng-show="messageCadastroError.length" class="col-md-10 col-md-offset-1 alert alert-danger" style="text-align: center;"></p>
</div>

<!-- FORMULARIO DE CADASTRO/EDICAO DE GRUPO -->
<form novalidate="novalidate" name="formCadastro" class="form-horizontal" ng-submit="saveGrupo()">
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="CPF do Cliente">Nome* </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" name="nome" placeholder="Nome do grupo" class="form-control" ng-model="grupo.nome" required="required">
		</div>	
		<label class="col-sm-3 col-md-2 control-label">Descri&ccedil;&atilde;o </label>
		<div class="col-sm-4 col-md-4">
			<input type="text" name="descricao" placeholder="Descri&ccedil;&atilde;o do grupo" class="form-control" ng-model="grupo.descricao">
		</div>	
	</div>	
	<hr/>
	<!-- BOTAO INCLUIR FILIAIS -->
	<div class="form-group">
		<div class="col-sm-11" align="right">
			<a class="btn btn-success" data-toggle="modal" data-target="#modalAdicionaFiliais" 
				ng-click="getFiliaisSemGrupo()"><i class="icon-plus icon-white"></i>&nbsp;Incluir Filiais</a>
		</div>
	</div>
	<!-- TABELA FILIAIS -->
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<div class="panel panel-success">
				<div class="panel-heading" align="left"> Filiais* </div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
	                        <thead>
								<tr>
									<th style="text-align: center;" width="180px">CNPJ</th>
									<th>Nome Fantasia</th>
									<th>Cidade</th>
									<th style="text-align: center;">Estado</th>
									<th style="text-align: center;">Status</th>
									<th style="text-align: center;">A&ccedil;&atilde;o 
										<a href="#" ng-show="isEdit" align="right" style="cursor: help;" data-toggle="tooltip" 
											data-placement = "top"
											title="Atenção: Ao excluir uma filial já cadastrada, não será possível adicioná-la novamente neste processo de edição.">
												<i class="icon-warning-sign icon-white"  style="color:orange;"></i>
										</a>
									</th>
								</tr>
	                        </thead>
							<tbody>
								<tr ng-repeat="filial in grupo.listaFiliais">
									<td align="center" width="180px">{{cnpjFormatter(filial.cnpj)}}</td>									
									<td>{{filial.tradeName}}</td>
									<td>{{filial.city}}</td>
									<td align="center">{{filial.state}}</td>
									<td align="center">{{getStatus(filial.status)}}</td>
									<td align="center" ng-click="removeFilial(filial.cnpj)"><i class="icon-remove icon-red"></i></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr/>
	<!-- BOTAO INCLUIR USUARIOS -->
	<div class="form-group">
		<div class="col-sm-11" align="right">
			<a class="btn btn-info" data-toggle="modal" data-target="#modalAdicionaUsers" 
				ng-click="getUsersManager()"><i class="icon-plus icon-white"></i>&nbsp;Incluir Usu&aacute;rios</a>
		</div>
	</div>
	<!-- TABELA USUARIOS -->
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<div class="panel panel-info">
				<div class="panel-heading" align="left"> Usu&aacute;rios* </div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
	                        <thead>
								<tr>
									<th style="text-align: center;" width="150px">CPF</th>
									<th width="200px">Nome</th>
									<th>Login</th>
									<th style="text-align: center;">Status</th>
									<th style="text-align: center;">A&ccedil;&atilde;o</th>
								</tr>
	                        </thead>
							<tbody>
								<tr ng-repeat="user in grupo.listaUsers">
									<td width="150px" align="center">{{cpfFormatter(user.cpf)}}</td>
									<td width="200px">{{user.displayName}}</td>									
									<td>{{user.login}}</td>
									<td align="center">{{getStatus(user.status)}}</td>
									<td align="center" ng-click="removeUser(user.id, user.cpf)"><i class="icon-remove icon-red"></i></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr/>
	<!-- BOTOES -->	
	<div class="btn-block">
		<sub class="col-sm-4">* Preenchimento Obrigat&oacute;rio</sub>
		<div class="col-sm-12" align="right">
			<!-- Botao Excluir -->
			<a class="btn btn-danger" ng-show="isEdit" ng-click="clearMessageCadastro()" data-toggle="modal" data-target="#modalExcluirGrupo">
				<i class="icon-remove icon-white"></i>&nbsp;Excluir
			</a>		
			<!-- Botao Salvar (Adicao) ou Atualizar (Atualizacao) -->
			<button type="submit"  class="btn btn-primary" ng-disabled="formCadastro.$invalid || grupo.listaUsers.length == 0 || grupo.listaFiliais.length == 0">
				<div ng-show="isCad"><i class="icon-save icon-white"></i>&nbsp;Salvar</div>
				<div ng-show="isEdit"><i class="icon-edit icon-white"></i>&nbsp;Atualizar</div>
			</button>
		</div>
	</div>				
</form>