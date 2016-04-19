<!-- MENSAGENS DE CADASTRO DE FILIAIS -->
<div class="row">
	<p translate="{{messageCadastro}}" ng-show="messageCadastro.length" class="col-md-10 col-md-offset-1 alert alert-info" style="text-align: center;"></p>
	<p translate="{{messageCadastroError}}" ng-show="messageCadastroError.length" class="col-md-10 col-md-offset-1 alert alert-danger" style="text-align: center;"></p>
</div>

<!-- FORMULARIO DE CADASTRO/EDICAO DE FILIAL -->
<form novalidate="novalidate" name="formulario" class="form-horizontal" ng-submit="saveFilial()">
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="CNPJ">CNPJ* </label>
		<label ng-show="isEdit" class="col-sm-4 col-md-1 control-label" title="CNPJ">{{user.cnpj}}</label> <!-- Tela de Edicao de Usuario -->
		<label ng-show="isEditFilial" class="col-sm-4 col-md-1 control-label" title="CNPJ">{{cnpjRaiz}}</label> <!-- Tela de Pesquisa de Filial -->
		<div class="col-sm-4 col-md-2 block" style="margin-left: 20px;">
			<input type="text" name="cnpj" class="form-control" data-mask="9999-99" mask ng-model="filial.cnpj" 
				ng-disabled="userLogado.isContractor || filial.status == 'INACTIVE'" required="required">
		</div>
		<label class="col-sm-4 col-md-3 control-label" style="margin-left: -20px;">Nome Fantasia*</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" name="tradeName" class="form-control" ng-model="filial.tradeName" ng-disabled="filial.status == 'INACTIVE'" required="required">
		</div>
	</div>					
	<div class="form-group">
		<label class="col-sm-4 col-md-2 control-label">Razão Social*</label>
		<div class="col-sm-6 col-md-9">
			<input type="text" name="companyName" class="form-control" ng-model="filial.companyName" ng-disabled="filial.status == 'INACTIVE'" required="required">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-4 col-md-2 control-label">CEP*</label>
		<div class="col-sm-6 col-md-3">
			<input type="text" name="cep" class="form-control" data-mask="99999-999" mask ng-model="filial.cep" ng-disabled="filial.status == 'INACTIVE'" required="required">
		</div>
		<a class="col-md-1 btn btn-info" ng-click="pesquisarCep()" title="Pesquisar Endere&ccedil;o" ng-disabled="filial.status == 'INACTIVE'">
			<i class="icon-search icon-white"></i>&nbsp;
		</a>
	</div>	
	<div class="form-group">
		<label class="col-sm-4 col-md-2 control-label">Logradouro*</label>
		<div class="col-sm-6 col-md-5">
			<input type="text" name="address" class="form-control" ng-model="filial.address" ng-disabled="filial.status == 'INACTIVE'" required="required">
		</div>
		<label class="col-sm-4 col-md-2 control-label" title="Numero">Numero* </label>
		<div class="col-sm-6 col-md-2">
			<input type="text" name="number" class="form-control" ng-model="filial.number" ng-disabled="filial.status == 'INACTIVE'" required="required" >
		</div>		
	</div>
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label">Complemento</label>
		<div class="col-sm-6 col-md-4" >
			<input type="text" class="form-control" ng-model="filial.complement" ng-disabled="filial.status == 'INACTIVE'">
		</div>
		<label class="col-sm-4 col-md-2 control-label" title="Numero">Bairro* </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" name="district" class="form-control" ng-model="filial.district" ng-disabled="filial.status == 'INACTIVE'" required="required" >
		</div>
	</div>
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="Cidade">Cidade* </label>
		<div class="col-sm-6 col-md-4">
			<input type="text" name="city" class="form-control" ng-model="filial.city" ng-disabled="filial.status == 'INACTIVE'" required="required">
		</div>
		<label class="col-sm-4 col-md-2 control-label">Estado*</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" name="state" class="form-control" maxlength="2" ng-model="filial.state" ng-disabled="filial.status == 'INACTIVE'" required="required">
		</div>
	</div>
	<div class="form-group ">
		<label class="col-sm-4 col-md-3 control-label" title="Cidade">Telefone Principal* </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" name="fone1" class="form-control" data-mask="(99) 9999-9999" mask ng-model="filial.fone1" ng-disabled="filial.status == 'INACTIVE'" required="required">
		</div>
		<label class="col-sm-4 col-md-2 control-label">Telefone</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" class="form-control" data-mask="(99) 9999-9999" mask ng-model="filial.fone2" ng-disabled="filial.status == 'INACTIVE'">
		</div>
	</div>
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="Cidade">Fax </label>
		<div class="col-sm-6 col-md-4">
			<input type="text" name="fax" class="form-control" data-mask="(99) 9999-9999" mask ng-model="filial.fax" ng-disabled="filial.status == 'INACTIVE'">
		</div>
		<label class="col-sm-4 col-md-2 control-label">E-mail*</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" name="email" class="form-control" ng-model="filial.email" ng-disabled="filial.status == 'INACTIVE'" required="required">
		</div>
	</div>
	<br />
	<div class="btn-block">
		<label class="col-sm-4 col-md-2 control-label" title="Contratante">Contratante </label>
		<div class="col-sm-6 col-md-4">
			<input type="text" class="form-control alert alert-warning" ng-model="user.displayName" ng-show="isEdit" disabled="disabled">
			<input type="text" class="form-control alert alert-warning" ng-value="getUserLogin(filial.userId)" ng-show="isEditFilial" disabled="disabled">
		</div>
		<div align="right">
		
			<!-- Botao Excluir -->
			<a class="btn btn-danger" ng-show="isEditFilial && userLogado.isAdmin" data-toggle="modal" data-target="#modalRemoveFilial">
				<i class="icon-remove icon-white"></i>&nbsp;Excluir
			</a>
			
			<!-- Botao Ativar -->
			<a class="btn btn-success" ng-show="isEditFilial && userLogado.isAdmin && filial.status == 'INACTIVE'" ng-click="ativarFilial()">
				<i class="icon-ok icon-white"></i>&nbsp;Ativar
			</a>
			
			<!-- Botao Inativar -->
			<a class="btn btn-warning" ng-show="isEditFilial && userLogado.isAdmin && filial.status == 'ACTIVE'" data-toggle="modal" data-target="#modalInativarFilial">
				<i class="icon-ban-circle icon-white"></i>&nbsp;Inativar
			</a>			
			
			<!-- Botao Salvar (Adicao) ou Atualizar (Atualizacao) -->
			<button type="submit"  class="btn btn-primary" ng-disabled="formulario.$invalid || filial.status == 'INACTIVE'">
				<i ng-show="isCad" class="icon-save icon-white"></i>
				<i ng-show="isEditFilial" class="icon-edit icon-white"></i> 
				Salvar
			</button>
			
		</div>
		<sub ng-show="isCad" class="col-sm-4">* Preenchimento Obrigat&oacute;rio</sub>	
	</div>				
</form>