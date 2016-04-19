<!-- MENSAGENS -->
<div class="row">
	<p translate="{{messagePesquisa}}" ng-show="messagePesquisa.length" class="col-md-10 col-md-offset-1 alert alert-info" style="text-align: center;"></p>
	<p translate="{{messagePesquisaError}}" ng-show="messagePesquisaError.length" class="col-md-10 col-md-offset-1 alert alert-danger" style="text-align: center;"></p>
</div>

<!-- FORMULARIO PESQUISA -->  
<form novalidate="novalidate" name="formulario" class="form-horizontal" ng-submit="pesquisaFilial()">
	<div class="form-group ">
		<label class="col-sm-4 col-md-3 control-label" title="CNPJ">CNPJ Raiz</label>
		<div class="col-sm-6 col-md-3">
			<input type="text" name="cnpj" class="form-control" data-mask="99.999.999" mask ng-model="filial.cnpj" required="required"">
		</div>
		<label class="col-sm-4 col-md-2 control-label">Nome Fantasia</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" class="form-control" ng-model="filial.tradeName">
		</div>
	</div>					
	<div class="form-group">
		<label class="col-sm-4 col-md-3 control-label">Razão Social </label>
		<div class="col-sm-6 col-md-5">
			<input type="text" name="companyName" class="form-control" ng-model="filial.companyName" required="required">
		</div>
		<label class="col-sm-4 col-md-1 control-label">Status</label>
		<div class="col-sm-6 col-md-2">
			<select class="form-control" ng-model="filial.status" required="required">
				<option value="">Selecione...</option>
				<option value="ACTIVE">Ativo</option>
				<option value="INACTIVE">Inativo</option>
			</select>
		</div>		
	</div>
	<div class="form-group">
		<label class="col-sm-4 col-md-3 control-label">Usu&aacute;rio Contratante </label>
		<div class="col-sm-6 col-md-3">
			<select id="selectContractor" class="form-control" ng-model="filial.userId" required="required"></select>
		</div>
		<div class="col-sm-6 col-md-5" align="right">
			<button class="btn btn-info btn-round"><i class="icon-search icon-white"></i> Pesquisar</button>
		</div>
	</div>	
</form>
<hr/>
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-info">
			<div class="panel-heading"> Resultado </div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover" id="dataTables-filiais">
                        <thead>
							<tr>
								<th width="180px">CNPJ</th>
								<th>Nome Fantasia</th>
								<th>Cidade</th>
								<th>Estado</th>
								<th>Telefone Principal</th>
								<th>Status</th>
								<th>Contratante</th>
							</tr>
                        </thead>
						<tbody>
							<tr ng-repeat="filial in filiais.listFiliais" 
										ng-class="{'danger' : filial.status == 'INACTIVE', 'success' : filial.status == 'ACTIVE'}"
									   	data-toggle="modal" data-target="#modalDetalheFilial" ng-click="detalheFilial($index)">
								<td align="center" width="180px">{{cnpjFormatter(filial.cnpj)}}</td>									
								<td>{{filial.tradeName}}</td>
								<td>{{filial.city}}</td>
								<td align="center">{{filial.state}}</td>
								<td align="center">{{filial.fone1}}</td>
								<td align="center">{{getStatus(filial.status)}}</td>
								<td align="center">{{getUserLogin(filial.userId)}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL EDICAO FILIAL -->
<div id="modalDetalheFilial" class="modal fade col-md-12" style="margin-top: -25px;">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" align="center"><b>Gerenciar Filial: {{filial.tradeName}}</b></h4>
            </header>
            <div class="modal-body box dark" align="center">
				<jsp:include page="filial-cadastro.jsp"/>
            </div>
             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<button class="col-md-1 col-md-offset-11 btn btn-default" ng-click="fecharModal()"><i class="icon-exit icon-white"></i>Fechar</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL EXCLUSAO DE FILIAL -->
<div id="modalRemoveFilial" class="modal fade col-md-offset-2 col-md-8" style="margin-top: 130px;">
    <div class="modal-dialog">
        <div class="modal-content">
        	<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title" align="center"><b>Exclus&atilde;o de Filial</b></h4>
            </header>
            <div class="modal-body" align="center">
            	<p class="alert alert-danger">
            		<strong>Aten&ccedil;&atilde;o</strong>: A exclus&atilde;o de uma filial excluir&aacute; tamb&eacute;m todos os usu&aacute;rios que 
            		estiverem &uacute;nica e exclusivamente relacionados &agrave; ela. Voc&ecirc; tem certeza que deseja continuar?
            	</p> 
            </div>
            <div class="modal-footer" align="center">
            	<button class="btn btn-danger" ng-click="fecharModalById('modalRemoveFilial')"><i class="icon-thumbs-down icon-white"></i>&nbsp;N&atilde;o</button>
      			<button class="btn btn-success" ng-click="excluirFilial()"><i class="icon-thumbs-up icon-white"></i>&nbsp;Sim</button>
            </div>
        </div>
    </div>
</div>

<!-- MODAL INATIVACAO DE FILIAL -->
<div id="modalInativarFilial" class="modal fade col-md-offset-2 col-md-8" style="margin-top: 130px;">
    <div class="modal-dialog">
        <div class="modal-content">
			<header class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title" align="center"><b>Inativa&ccedil;&atilde;o de Filial</b></h4>
            </header>          
            <div class="modal-body" align="center">
				<p class="alert alert-danger"><strong>Aten&ccedil;&atilde;o</strong>: a inativa&ccedil;&atilde;o da filial remover&aacute; todos os relacionamentos 
				para ela cadastrados, inclusive dos grupos. Voc&ecirc; tem certeza que deseja inativar a filial "{{filial.tradeName | uppercase}}"?</p>
            </div>
             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
				<a class="btn btn-danger"  ng-click="fecharModalById('modalInativarFilial')"><i class="icon-thumbs-down icon-white"></i>&nbsp;N&atilde;o</a>
				<a class="btn btn-success" ng-click="inativarFilial()"><i class="icon-thumbs-up icon-white"></i>&nbsp;Sim</a>
            </div>
        </div>
    </div>
</div>