<!-- MENSAGENS -->
<div class="row">
	<p translate="{{messagePesquisa}}" ng-show="messagePesquisa.length" class="col-md-10 col-md-offset-1 alert alert-info" style="text-align: center;"></p>
	<p translate="{{messagePesquisaError}}" ng-show="messagePesquisaError.length" class="col-md-10 col-md-offset-1 alert alert-danger" style="text-align: center;"></p>
</div>

<!-- FORMULARIO PESQUISA -->     
<form novalidate="novalidate" name="formulario" class="form-horizontal" ng-submit="#">
	<div class="form-group ">
		<label class="col-sm-4 col-md-3 control-label" title="CNPJ">CNPJ </label>
		<div class="col-sm-6 col-md-3">
			<select class="form-control" ng-controller="UserTypeController" ng-model="cnpjId" 
				ng-options="filial.id as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj"
				ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
				ng-change="setListAdquirentesById(cnpjId);clearAll();" required="required">
				<option value='' selected="selected">Selecione...</option>
			</select>
		</div>
		<label class="col-sm-4 col-md-2 control-label">Adquirente </label>
		<div class="col-sm-6 col-md-3">
			<select class="form-control" ng-controller="UserTypeController" ng-disabled="userLogado.listaAdquirentes.length == 0" ng-change="pesquisaTaxa()"
					ng-model="taxa.adquirenteId" ng-options="adquirente.id as adquirente.descricao for adquirente in userLogado.listaAdquirentes" required="required">
				<option value='' selected="selected">Selecione...</option>
			</select>
		</div>
	</div>	
	<hr/>
	<div class="form-group ">
		<label class="col-sm-4 col-md-3 control-label" title="Credito">Cr&eacute;dito</label>
		<div class="col-sm-6 col-md-3">
			<input type="number" step="0.1" class="form-control" ng-model="taxa.credito" required="required">
		</div>
		<label class="col-sm-4 col-md-2 control-label">D&eacute;bito</label>
		<div class="col-sm-6 col-md-3" >
			<input type="number" step="0.1" class="form-control" ng-model="taxa.debito" required="required">
		</div>
	</div>	
	<div class="form-group ">
		<label class="col-sm-4 col-md-3 control-label" title="Credito">Cr&eacute;dito (2-6 parcelas)</label>
		<div class="col-sm-6 col-md-3">
			<input type="number" step="0.1" class="form-control" ng-model="taxa.credito2a6" required="required">
		</div>
		<label class="col-sm-4 col-md-2 control-label">Antecipa&ccedil;&atilde;o</label>
		<div class="col-sm-6 col-md-3" >
			<input type="number" step="0.1" class="form-control" ng-model="taxa.antecipacao" required="required">
		</div>
	</div>	
	<div class="form-group">
		<label class="col-sm-4 col-md-3 control-label" title="Credito">Cr&eacute;dito (7-12 parcelas)</label>
		<div class="col-sm-6 col-md-3">
			<input type="number" step="0.1" class="form-control" ng-model="taxa.credito7a12" required="required">
		</div>
		<div class="col-sm-6 col-md-5" align="right">
			<a class="btn btn-success" ng-click="gravarTaxa()" ng-disabled="formulario.$invalid || userLogado.listaAdquirentes.length == 0">
				<i class="icon-plus icon-white"></i>&nbsp;Gravar
			</a>
		</div>
	</div>	
</form>
<hr/>

<!-- RESULTA DA PESQUISA -->
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-info">
			<div class="panel-heading"> Resultado </div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover" id="dataTables-taxas">
                        <thead>
							<tr>
								<th width="120px">Cr&eacute;dito</th>
								<th width="120px">Cr&eacute;dito(2/6)</th>
								<th width="120px">Cr&eacute;dito(7/12)</th>
								<th width="120px">D&eacute;bito</th>
								<th width="120px">Antecipa&ccedil;&atilde;o</th>
								<th>Data e Hora de Cria&ccedil;&atilde;o</th>
							</tr>
                        </thead>
						<tbody>
							<tr ng-repeat="tx in taxas.listTaxa" ng-class="{'warning' : tx.id != taxa.id, 'success' : tx.id == taxa.id}">
								<td width="120px" align="center">{{tx.credito}}%</td>
								<td width="120px" align="center">{{tx.credito2a6}}%</td>									
								<td width="120px" align="center">{{tx.credito7a12}}%</td>
								<td width="120px" align="center">{{tx.debito}}%</td>
								<td width="120px" align="center">{{tx.antecipacao}}%</td>
								<td align="center">{{tx.dataInicio | date: 'dd/MM/yyyy HH:mm:ss'}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
