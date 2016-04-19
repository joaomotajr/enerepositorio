<!-- MENSAGENS -->
<div class="row">
	<p translate="{{messageCadastro}}" ng-show="messageCadastro.length" class="col-md-10 col-md-offset-1 alert alert-info" style="text-align: center;"></p>
	<p translate="{{messageCadastroError}}" ng-show="messageCadastroError.length" class="col-md-10 col-md-offset-1 alert alert-danger" style="text-align: center;"></p>
</div>

<!-- FORMULARIO SELECAO -->
<form novalidate="novalidate" name="formulario" class="form-horizontal" ng-submit="#">
	<div class="form-group">
		<label class="col-sm-4 col-md-3 control-label">Login do Usu&aacute;rio*</label>
		<div class="col-sm-6 col-md-4">
			<select id="selectUsers" class="form-control" ng-model="filial.userId" ng-change="pesquisaFilialByUser()" required="required">
				<option value="">Selecione...</option>
				<option ng-repeat="otherUser in listOtherUsers" value="{{otherUser.id}}">{{otherUser.login}}&nbsp;({{getTipoUsuario(otherUser.role)}})</option>
			</select>
		</div>
	</div>								
	<div class="form-group btn-block">
		<div class="col-sm-12" align="right">
			<a class="btn btn-info" data-toggle="modal" data-target="#modalAdicionaRelacionamento" ng-disabled="formulario.$invalid || operatorHasData" ng-click="getfiliaisdoContrato()">
				<i class="icon-plus icon-white"></i> Adicionar Relacionamento
			</a>
		</div>
	</div>				
</form>
<hr/>

<!-- FILIAIS RELACIONADAS -->
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-info">
			<div class="panel-heading"> Filial(is) Relacionada(s) </div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover" id="dataTables-filiais">
                        <thead>
							<tr>
								<th>CNPJ</th>
								<th>Nome Fantasia</th>
								<th>Cidade</th>
								<th>Estado</th>
								<th>Telefone Principal</th>
								<th>Status</th>
							</tr>
                        </thead>
						<tbody>
							<tr ng-repeat="filial in filiais.listFiliais" ng-class="{'danger' : filial.status == 'INACTIVE', 'success' : filial.status == 'ACTIVE'}"
									   data-toggle="modal" data-target="#modalRemoveRelacionamento" ng-click="setCnpj(filial.cnpj)">
								<td align="center" width="250px">{{cnpjFormatter(filial.cnpj)}}</td>									
								<td>{{filial.tradeName}}</td>
								<td>{{filial.city}}</td>
								<td align="center">{{filial.state}}</td>
								<td align="center">{{filial.fone1}}</td>
								<td align="center">{{getStatus(filial.status)}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL REMOVE RELACIONAMENTO -->
<div id="modalRemoveRelacionamento" class="modal fade col-md-offset-3 col-md-6" style="margin-top: 130px;">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title" align="center"><b>Exclus&atilde;o de Relacionamento</b></h4>
            </header>
            <div class="modal-body" align="center">
            	<p class="alert alert-danger">Voc&ecirc; tem certeza que deseja excluir a filial <span style="text-transform: uppercase;">"{{cnpjFormatter(filial.cnpj)}}"</span> 
            			desta lista de relacionamentos?</p> 
            </div>
            <div class="modal-footer" align="center">
            	<button class="btn btn-danger" ng-click=fecharModal()><i class="icon-thumbs-down icon-white"></i>&nbsp;N&atilde;o</button>
      			<button class="btn btn-success" ng-click=excluirRelacionamento()><i class="icon-thumbs-up icon-white"></i>&nbsp;Sim</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL ADICIONA RELACIONAMENTO -->
<div id="modalAdicionaRelacionamento" class="modal fade col-md-offset-1 col-md-10">
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
								<th style="text-align: center;">CNPJ</th>
								<th>Nome Fantasia</th>
								<th style="text-align: center;">Status</th>
							</tr>
                        </thead>
						<tbody ng-show="userSelected.isManager">
							<tr ng-repeat="filial in filiaisContrato" ng-class="{'danger' : filial.status == 'INACTIVE', 'success' : filial.status == 'ACTIVE'}">
								<td align="center" width="5px" >
									<input type="checkbox" ng-model="cnpjs[$index]" ng-true-value={{filial.cnpj}}  ng-false-value="null" 
										ng-disabled="filial.status == 'INACTIVE'" />
								</td>
								<td align="center" width="250px">{{cnpjFormatter(filial.cnpj)}}</td>									
								<td>{{filial.tradeName}}</td>
								<td align="center">{{getStatus(filial.status)}}</td>
							</tr>
						</tbody>
						<tbody ng-show="userSelected.isOperator">
							<tr ng-repeat="filial in filiaisContrato" ng-class="{'danger' : filial.status == 'INACTIVE', 'success' : filial.status == 'ACTIVE'}">
								<td align="center" width="5px">
									<input type="radio" name="radioFilial" value={{filial.cnpj}} ng-model="cnpj" ng-click="setCnpj($event)" ng-disabled="filial.status == 'INACTIVE'"/>
								</td>
								<td align="center" width="250px">{{cnpjFormatter(filial.cnpj)}}</td>									
								<td>{{filial.tradeName}}</td>
								<td align="center">{{getStatus(filial.status)}}</td>
							</tr>
						</tbody>
						<div class="alert alert-warning" ng-show="noData">Todas as filiais do contrato j&aacute; est&atilde;o associadas &agrave; este usu&aacute;rio.</div>						
					</table>
				</div>            		
            </div>
            <div class="modal-footer" align="center">
            	<button class="btn btn-danger" ng-click="fecharModalById('modalAdicionaRelacionamento')"><i class="icon-thumbs-down icon-white"></i>&nbsp;Sair</button>
      			<button class="btn btn-success" ng-click=incluirRelacionamento() ng-disabled="noData"><i class="icon-thumbs-up icon-white"></i>&nbsp;Salvar</button>
            </div>
        </div>
    </div>
</div>