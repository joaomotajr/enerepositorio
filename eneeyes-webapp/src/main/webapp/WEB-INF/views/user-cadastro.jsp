<!-- MENSAGENS -->
<div class="row">
	<p translate="{{messageCadastro}}" data-ng-show="messageCadastro.length" class="col-md-10 col-md-offset-1 alert alert-info" style="text-align: center;"></p>
	<p translate="{{messageCadastroError}}" data-ng-show="messageCadastroError.length" class="col-md-10 col-md-offset-1 alert alert-danger" style="text-align: center;"></p>
</div>

<!-- FORMULARIO DE CADASTRO/EDICAO DE USUARIO -->
<form novalidate="novalidate" name="formCadastro" class="form-horizontal" data-ng-submit="saveUser()">
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="CPF do Cliente">CPF* </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" name="cpf" class="form-control" data-mask="999.999.999-99" mask data-ng-model="user.cpf" required="required">
		</div>	
		<label class="col-sm-4 col-md-3 control-label">CNPJ Raiz* </label>
		<div class="col-sm-6 col-md-3" ng-if="userLogado.isAdmin">
			<input type="text" name="cnpj" class="form-control" data-mask="99.999.999" mask data-ng-model="user.cnpj" required="required">
		</div>				
	</div>	
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="Nome do Cliente">Nome* </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" name="displayName" class="form-control" data-ng-model="user.displayName" required="required">
		</div>
		<label class="col-sm-4 col-md-3 control-label">Apelido</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" class="form-control" data-ng-model="user.nickname">
		</div>
	</div>
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="Cidade">Telefone </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" data-mask="(99) 9999-9999" mask data-ng-model="user.fone">
		</div>
		<label class="col-sm-4 col-md-3 control-label">Celular</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" class="form-control" data-mask="(99) 99999-9999" mask data-ng-model="user.cell">
		</div>
	</div>	
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="Cidade">E-mail </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" data-ng-model="user.email">
		</div>
		<label class="col-sm-4 col-md-3 control-label">Tipo*</label>
		<div class="col-sm-6 col-md-3">
			<select class="form-control" name="role" data-ng-model="user.role" required="required">
				<option value="">Selecione...</option>
				<option data-ng-show="userLogado.isAdmin" value="1">Administrador</option>
				<option data-ng-show="userLogado.isAdmin" value="2">Contratante</option>
				<option data-ng-show="userLogado.isAdmin || userLogado.isContractor" value="3">Gerente</option>
				<option data-ng-show="userLogado.isAdmin || userLogado.isContractor" value="4">Operador</option>
			</select>
		</div>
	</div>	
	<div class="form-group">
		<label class="col-sm-4 col-md-2 control-label">Login*</label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" data-ng-model="user.login" required="required">
		</div>
		<label class="col-sm-4 col-md-3 control-label">Senha*</label>
		<div class="col-sm-6 col-md-3">
			<input type="password" class="form-control" data-ng-model="user.hash" data-ng-disabled="isEdit" required="required">
		</div>
	</div>	
	<br />	
	<div class="btn-block">
		<div class="col-sm-12" align="right">
		
			<!-- Botao Incluir Filial -->
			<a class="btn btn-success" data-ng-show="isEdit && userLogado.isAdmin && user.role == 2"  data-ng-click="clearMessageCadastro()" data-toggle="modal" data-target="#modalCadastrarFilial">
				<i class="icon-briefcase icon-white"></i>&nbsp;Adicionar Filial
			</a>
		
			<!-- Botao Excluir -->
			<a class="btn btn-danger" data-ng-show="isEdit" data-ng-click="clearMessageCadastro()" data-toggle="modal" data-target="#modalExcluirUser">
				<i class="icon-remove icon-white"></i>&nbsp;Excluir
			</a>
			
			<!-- Botao Ativar -->
			<a class="btn btn-success" data-ng-show="isEdit && user.status == 'INACTIVE'" data-ng-click="ativarUser()">
				<i class="icon-ok icon-white"></i>&nbsp;Ativar
			</a>
			
			<!-- Botao Inativar -->
			<a class="btn btn-warning" data-ng-show="isEdit && user.status == 'ACTIVE'" data-ng-click="clearMessageCadastro()" data-toggle="modal" data-target="#modalInativarUser">
				<i class="icon-ban-circle icon-white"></i>&nbsp;Inativar
			</a>
			
			<!-- Botao Salvar (Adicao) ou Atualizar (Atualizacao) -->
			<button type="submit"  class="btn btn-primary" data-ng-disabled="formCadastro.$invalid">
				<div data-ng-show="isCad"><i class="icon-save icon-white"></i>&nbsp;Salvar</div>
				<div data-ng-show="isEdit"><i class="icon-edit icon-white"></i>&nbsp;Atualizar</div>
			</button>
			
		</div>
		<sub data-ng-show="isCad" class="col-sm-4">* Preenchimento Obrigat&oacute;rio</sub>	
	</div>				
</form>