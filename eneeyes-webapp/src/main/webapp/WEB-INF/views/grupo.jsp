<!-- MENSAGENS -->
<div class="row">
	<p translate="{{messagePesquisa}}" ng-show="messagePesquisa.length" class="col-md-10 col-md-offset-1 alert alert-info" style="text-align: center;"></p>
	<p translate="{{messagePesquisaError}}" ng-show="messagePesquisaError.length" class="col-md-10 col-md-offset-1 alert alert-danger" style="text-align: center;"></p>
</div>

<!-- FORMULARIO PESQUISA -->     
<form novalidate="novalidate" name="formulario" class="form-horizontal" ng-submit="pesquisaGrupo()">
	<div class="form-group ">
		<label class="col-sm-4 col-md-3 control-label" title="Nome do Grupo">Nome </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" ng-model="grupo.nome">
		</div>	
		<div class="col-sm-6 col-md-1" align="right">
			<button class="btn btn-info btn-round"><i class="icon-search icon-white"></i> Pesquisar</button>
		</div>
	</div>	
</form>

<!-- BOTAO NOVO GRUPO -->
<hr/>
	<div class="form-group">
		<div class="col-sm-12" align="left">
			<a class="btn btn-success" data-toggle="modal" data-target="#modalCadastrarGrupo" 
				ng-click="novoGrupo()"><i class="icon-plus icon-white"></i>&nbsp;Novo Grupo</a>
		</div>
	</div>
<hr/>

<!-- RESULTADO DA PESQUISA -->
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-info">
			<div class="panel-heading"> Resultado </div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover" id="dataTables-grupos">
                        <thead>
							<tr>
								<th width="300px">Nome</th>
								<th>Descri&ccedil;&atilde;o</th>
							</tr>
                        </thead>
						<tbody>
							<tr ng-repeat="gp in grupos.listaGrupo" ng-class="{'danger' : gp.nome == '', 'success' : gp.nome != ''}"
									   data-toggle="modal" data-target="#modalDetalheGrupo" ng-click="detalheGrupo($index)">
								<td width="200px">{{gp.nome}}</td>									
								<td>{{gp.descricao}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL CADASTRO GRUPO -->
<div id="modalCadastrarGrupo" class="modal fade col-md-12">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" align="center"><b>Inclus&atilde;o de Grupo</b></h4>
            </header>
            <div class="modal-body box dark" align="center">
				<jsp:include page="grupo-cadastro.jsp"/>
            </div>
             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<button class="col-md-1 col-md-offset-11 btn btn-default" ng-click="fecharModal()"><i class="icon-exit icon-white"></i>Fechar</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL EDICAO GRUPO -->
<div id="modalDetalheGrupo" class="modal fade col-md-12">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" align="center"><b>Atualizar Grupo: {{grupo.nome}}</b></h4>
            </header>
            <div class="modal-body box dark" align="center">
				<jsp:include page="grupo-cadastro.jsp"/>
            </div>
            <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<button class="col-md-1 col-md-offset-11 btn btn-default" ng-click="fecharModalById('modalDetalheGrupo')"><i class="icon-exit icon-white"></i>Fechar</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL EXCLUSAO DE GRUPO -->
<div id="modalExcluirGrupo" class="modal fade col-md-offset-3 col-md-6" style="margin-top: 130px;">
    <div class="modal-dialog">
        <div class="modal-content">
			<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title" align="center"><b>Exclus&atilde;o de Grupo</b></h4>
            </header>          
            <div class="modal-body" align="center">
				<p class="alert alert-danger">Voc&ecirc; tem certeza que deseja excluir o grupo "{{grupo.nome | uppercase}}"?</p>
            </div>
             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<a class="btn btn-danger"  ng-click="fecharModalById('modalExcluirGrupo')"><i class="icon-thumbs-down icon-white"></i>&nbsp;N&atilde;o</a>
				<a class="btn btn-success" ng-click="excluirGrupo()"><i class="icon-thumbs-up icon-white"></i>&nbsp;Sim</a>
            </div>
        </div>
    </div>
</div>

<!-- MODAL ADICIONA FILIAL -->
<div id="modalAdicionaFiliais" class="modal fade col-md-offset-1 col-md-10" style="margin-top: 70px;">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" ng-click="fecharModalById('modalAdicionaFiliais')">&times;</button>
                <h4 class="modal-title" align="center"><b>Filiais Desagrupadas</b></h4>
            </header>
            <div class="modal-body" align="center">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover" id="dataTables-modal-filiais">
                        <thead ng-show="!noData">
							<tr>
								<td style="text-align: center;">
									<!-- <input type="checkbox" ng-model="selectedAll" ng-change="checkAllFiliais()"> -->
								</td>
								<th>CNPJ</th>
								<th>Nome Fantasia</th>
								<th>Status</th>
							</tr>
                        </thead>
						<tbody>
							<tr ng-repeat="fls in filiaisSemGrupo" ng-class="{'danger' : fls.status == 'INACTIVE', 'success' : fls.status == 'ACTIVE'}">
								<td align="center" width="5px" >
									<input type="checkbox" ng-model="cnpjFiliais[$index]" ng-change="setFiliais($index, fls.cnpj)" 
										ng-true-value={{$index}} ng-false-value="null" ng-disabled="fls.status == 'INACTIVE'" />
								</td>
								<td align="center" width="250px">{{cnpjFormatter(fls.cnpj)}}</td>									
								<td>{{fls.tradeName}}</td>
								<td align="center">{{getStatus(fls.status)}}</td>
							</tr>
						</tbody>
						<div class="alert alert-warning" ng-show="noData">N&atilde;o existem filiais desagrupadas para este contrato..</div>						
					</table>
				</div>            		
            </div>
            <div class="modal-footer" align="center">
      			<button class="btn btn btn-default" ng-click="fecharModalById('modalAdicionaFiliais')"><i class="icon-exit icon-white"></i>&nbsp;Fechar</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL ADICIONA USER -->
<div id="modalAdicionaUsers" class="modal fade col-md-offset-1 col-md-10" style="margin-top: 70px;">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" ng-click="fecharModalById('modalAdicionaUsers')">&times;</button>
                <h4 class="modal-title" align="center"><b>Gerentes</b></h4>
            </header>
            <div class="modal-body" align="center">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover" id="dataTables-modal-users">
                        <thead ng-show="!noData">
							<tr>
								<td style="text-align: center;">
									<!-- <input type="checkbox" ng-model="selectedAll" ng-change="checkAllFiliais()"> -->
								</td>
								<th width="150px">CPF</th>
								<th width="200px">Nome</th>
								<th>Login</th>
								<th>Status</th>
							</tr>
                        </thead>
						<tbody>
							<tr ng-repeat="usr in usersManager" ng-class="{'danger' : usr.status == 'INACTIVE', 'success' : usr.status == 'ACTIVE'}">							
								<td align="center" width="5px" >
									<input type="checkbox" ng-model="cpfUsersManager[usr.id]" ng-change="setUsers(usr.id, usr.cpf)" 
										ng-true-value={{usr.id}} ng-false-value="null" ng-disabled="usr.status == 'INACTIVE'" />										
								</td>
								<td width="150px" align="center">{{cpfFormatter(usr.cpf)}}</td>
								<td width="200px">{{usr.displayName}}</td>									
								<td>{{usr.login}}</td>
								<td align="center">{{getStatus(usr.status)}}</td>
							</tr>
						</tbody>
					</table>
				</div>            		
            </div>
            <div class="modal-footer" align="center">
      			<button class="btn btn btn-default" ng-click="fecharModalById('modalAdicionaUsers')"><i class="icon-exit icon-white"></i>&nbsp;Fechar</button>
            </div>
        </div>
    </div>
</div>
