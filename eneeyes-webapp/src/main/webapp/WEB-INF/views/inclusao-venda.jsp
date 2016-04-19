<section ng-controller="DetalheVendaController">
<div class="inner" style="min-height: 700px;">
	<div class="row">
		<div class="col-lg-10">
			<h3> Vendas - Inclus&atilde;o manual </h3>
		</div>
		<div class="col-lg-2" ng-show="isEdit">
			<h3><button class="btn btn-info" ng-click="novoRegistro()"><i class="icon-plus icon-white"></i> Novo</button></h3>
		</div>
	</div>
	<hr/>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="inclusao-manual-validate">
					<div class="form-group">
						<label class="col-sm-3 control-label">Nome Cliente</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" ng-model="vendaManual.nomeCliente" value="" >
						</div>
						<label class="col-sm-3 control-label">CNPJ</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.cpfCnpj')}}">
							<select class="form-control" ng-controller="UserTypeController" ng-model="vendaManual.cpfCnpj" 
								ng-options="filial.cnpj as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj" 
								ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
								ng-selected="vendaManual.cpfCnpj == filial.cnpj" ng-change="setListAdquirentesByCnpj(vendaManual.cpfCnpj)">
								<option value='' selected="selected">Selecione...</option>
							</select>							
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.cpfCnpj')">{{'form.error.inclusaoVenda.cpfCnpj' | translate}}</span>
						</div>
					</div>				
					<div class="form-group ">
						<label class="col-sm-3 control-label" title="NSU, Código CV ou DOC">Código da transação</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.nsu')}}">
							<input type="text" class="form-control" ng-model="vendaManual.nsuHost" format="number">
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.nsu')">{{'form.error.inclusaoVenda.nsu' | translate}}</span>
						</div>
						<label class="col-sm-3 control-label">Adquirente</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.autorizador')}}" >
							<select class="form-control" ng-controller="UserTypeController" ng-model="vendaManual.autorizador" 
								ng-disabled="userLogado.listaAdquirentes.length == 0" 
								ng-options="adquirente.descricao as adquirente.descricao for adquirente in userLogado.listaAdquirentes">
								<option value='' selected="selected">Selecione...</option>
							</select>								
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.autorizador')">{{'form.error.inclusaoVenda.autorizador' | translate}}</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Bandeira</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.nomeProduto')}}">
							<input type="text" class="form-control" ng-model="vendaManual.nomeProduto">
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.nomeProduto')">{{'form.error.inclusaoVenda.nomeProduto' | translate}}</span>
						</div>
						<label class="col-sm-3 control-label">Código do estabelecimento</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.estabelecimento')}}">
							<input type="text" class="form-control" ng-model="vendaManual.estabelecimento" format="number">
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.estabelecimento')">{{'form.error.inclusaoVenda.estabelecimento' | translate}}</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Número do cartão</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.numeroCartao')}}">
							<input type="text" class="form-control"ng-model="vendaManual.numeroCartao">
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.numeroCartao')">{{'form.error.inclusaoVenda.numeroCartao' | translate}}</span>
						</div>
						<label class="col-sm-3 control-label">Tipo de venda</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.titComplTrans')}}">
							<select data-placeholder="Produto" class="form-control" ng-model="vendaManual.titComplTrans">
								<option value="">Selecione... </option>
								<option value="Débito">D&eacute;bito</option>
								<option value="Crédito">Cr&eacute;dito</option>
								<option value="Voucher">Voucher</option>
							</select>
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.titComplTrans')">{{'form.error.inclusaoVenda.titComplTrans' | translate}}</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Data</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.data')}}">
							<input type="text" class="form-control" data-mask="99/99/9999" mask data-date-format="dd/mm/yyyy" datemonopicker ng-model="vendaManual.dataLancamen" mask/>
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.data')">{{'form.error.inclusaoVenda.data' | translate}}</span>
						</div>
						<label class="col-sm-3 control-label">Hora</label>
						<div class="col-sm-3 input-group bootstrap-timepicker {{hasError('form.error.inclusaoVenda.hora')}}">
							<input class="timepicker-24 form-control" type="text" value="00:00:00" ng-model="vendaManual.hora" />
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.hora')">{{'form.error.inclusaoVenda.hora' | translate}}</span>
                        </div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Valor</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.valor')}}">
							<input type="text" class="form-control" ng-model="vendaManual.valor" format="currency">
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.valor')">{{'form.error.inclusaoVenda.valor' | translate}}</span>
						</div>
						<label class="col-sm-3 control-label">Código de autorização</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.codigoAutoriz')}}">
							<input type="text" class="form-control" ng-model="vendaManual.codigoAutoriz" >
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.codigoAutoriz')">{{'form.error.inclusaoVenda.codigoAutoriz' | translate}}</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Código do POS</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.terminalLogico')}}">
							<input type="text" class="form-control" ng-model="vendaManual.terminalLogico" format="number">
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.terminalLogico')">{{'form.error.inclusaoVenda.terminalLogico' | translate}}</span>
						</div>
						<label class="col-sm-3 control-label">Parcelas</label>
						<div class="col-sm-3 {{hasError('form.error.inclusaoVenda.parcelas')}}">
							<input type="text" class="form-control" ng-model="vendaManual.numPar" format="number">
							<span class="text-danger" ng-show="hasError('form.error.inclusaoVenda.parcelas')">{{'form.error.inclusaoVenda.parcelas' | translate}}</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="col-lg-12" align="right">
		<button class="btn btn-info" ng-click="incluirVenda()"><i class="icon-save icon-white"></i> Salvar</button>
	</div>
</div>
</section>
<script>
	$(function () { 
		formInit();
	});
</script>