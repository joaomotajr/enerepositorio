<section ng-controller="PagamentosController">
<div class="inner" style="min-height: 700px;">
	<div class="row">
		<div class="col-lg-10">
			<h3> Pagamentos </h3>
		</div>
	</div>
	<hr/>
	
	<div id="modalPagamentos" class="modal fade bs-pagamentos-modal col-lg-12" tabindex="-1" role="dialog" aria-labelledby="pagamentosModal" aria-hidden="true">
	    
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title" align="center">
	                	<small class="pull-left text-muted">
							Pagamentos
						</small>
	                	<b>{{detalhePagamento.listaCv[0].historicoVendaCieloDto.empresaAdquirente}} - Vendas a {{detalhePagamento.tipo}} pagas em {{detalhePagamento.dataEnvioBanco}}</b>
	                	<small class="pull-right text-muted">
							Resumo {{detalhePagamento.numeroRo}}
						</small>
	                </h4>
	            </div>
	            <div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-3 control-label">Valor bruto</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalhePagamento.valorBruto}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Banco</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalhePagamento.banco}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Taxa %</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalhePagamento.taxa}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Ag&ecirc;ncia</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalhePagamento.agencia}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Taxa R$</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalhePagamento.comissao}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Conta</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalhePagamento.conta}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Valor pago</label>
							<div class="col-sm-3">
								<input type="text" class="form-control success" value="{{detalhePagamento.valorPago}}" disabled style="color : green" >
							</div>
							<label class="col-sm-3 control-label">Produto</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalhePagamento.produto}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Status</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" value="{{detalhePagamento.status}}" disabled>
							</div>
						</div>
						<div class="panel panel-warning">
							<div class="panel-heading">
								Detalhe das vendas
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover" id="dataTables-cvs">
				                        <thead>
											<tr>
												<th>NSU</th>
												<th>Valor bruto venda/parcela</th>
												<th>Taxa</th>
												<th>Valor pago venda/parcela</th>
												<th>Parcela</th>
												<th>Meio captura</th>
											</tr>
				                        </thead>
										<tbody>
											<tr ng-repeat="comprovante in detalhePagamento.listaCv">
												<td>{{comprovante.nsuDoc}}</td>
												<td>{{comprovante.valorCompraOuParcela | currency : 'R$ '}}</td>
												<td>{{parseFloat(comprovante.valorCompraOuParcela) * (parseFloat(detalhePagamento.taxa)/parseInt(100))  | currency : 'R$ -'}}</td>
												<td style="color : green"><b>{{parseFloat(comprovante.valorCompraOuParcela) - (parseFloat(comprovante.valorCompraOuParcela) * (parseFloat(detalhePagamento.taxa)/parseInt(100)))  | currency : 'R$ '}}</b></td>
												<td>{{comprovante.parcela}}</td>
												<td>{{comprovante.meioCaptura.descricao}}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</form>
	            </div>
	            <div class="modal-footer">
	             	<button class="btn btn-default" data-dismiss="modal" aria-label="Close"><i class="icon-exit icon-white"></i> Fechar</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	<div class="row">
		<div class="col-lg-12">
	    	<div class="box dark">
	        	<header>
	            	<div class="icons"><i class="icon-edit"></i></div>
	            	<h5>Pesquisas</h5>
	            	<div class="toolbar">
		                <ul class="nav">
		                    <li>
		                        <a class="accordion-toggle minimize-box" data-toggle="collapse" href="#pesquisas">
		                            <i class="icon-chevron-up"></i>
		                        </a>
		                    </li>
		                </ul>
		            </div>
		        </header>
		        <div id="pesquisas" class="body collapse in">
	                <ul class="nav nav-pills-warning">
	                    <li class="active" ng-controller="UserTypeController">
	                    	<a href="#avancado" data-toggle="tab" ng-click="clearPagamentosFiltro();clearListaAdquirentes();">Avan&ccedil;ado</a>
	                    </li>
	                    <li><a href="#basico" data-toggle="tab" ng-click="clearPagamentosFiltro()">B&aacute;sico</a>
	                    </li>
	                </ul>
	                <div class="tab-content">
	                    <div class="tab-pane fade in active" id="avancado">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-lg-2">Per&iacute;odo</label>
			                        <div class="col-lg-3">
			                            <div class="input-group">
			                                <span class="input-group-addon"><i class="icon-calendar"></i></span>
			                                <input type="text" daterangepicker ng-model="rangeData" class="form-control"/>
			                            </div>
			                        </div>
		                    		<label class="control-label col-lg-2">CNPJ</label>
									<div class="col-lg-3">
										<select id="text1" class="form-control" ng-controller="UserTypeController" ng-model="pagamentosFiltro.cnpj" 
											ng-options="filial.cnpj as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj" 
											ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
											ng-change="setListAdquirentesByCnpj(pagamentosFiltro.cnpj)">
											<option value='' selected="selected">Selecione...</option>
										</select>	
									</div>	
		                		</div>
	                			<div class="form-group">
	                				<label class="control-label col-lg-2">Tipo Venda</label>
									<div class="col-lg-3">
										<select data-placeholder="Produto" class="form-control" ng-model="pagamentosFiltro.tipoVenda">
											<option value="">Selecione... </option>
											<option value="Débito">D&eacute;bito</option>
											<option value="Crédito">Cr&eacute;dito</option>
											<option value="Outros">Outros</option>
										</select>
									</div>
		                    		<label class="control-label col-lg-2">Adquirente</label>
									<div class="col-lg-3">
										<select class="form-control" ng-controller="UserTypeController" ng-model="pagamentosFiltro.adquirente" 
											ng-disabled="userLogado.listaAdquirentes.length == 0" 
											ng-options="adquirente.descricao as adquirente.descricao for adquirente in userLogado.listaAdquirentes">
											<option value='' selected="selected">Selecione...</option>
										</select>										
									</div>		
									<div align="right">
										<button class="btn btn-warning btn-round" ng-click="pesquisarPagamentos()"><i class="icon-search icon-white"></i> Pesquisar</button>
									</div>
								</div>
	                			<div class="form-group">

								</div>								
			                </form>
	                    </div>
	                    <div class="tab-pane fade" id="basico">
	                        <form class="form-horizontal">
								<div class="form-group">
                        			<label for="text1" class="control-label col-lg-1">CNPJ</label>
				                    <div class="col-lg-3">
										<select id="text1" class="form-control" ng-controller="UserTypeController" ng-model="pagamentosFiltro.cnpj" 
											ng-options="filial.cnpj as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj" 
											ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
											ng-change="setListAdquirentesByCnpj(pagamentosFiltro.cnpj)">
											<option value='' selected="selected">Selecione...</option>
										</select>										
		        		            </div>
		        		            <label for="text1" class="control-label col-lg-2">Raz&atilde;o Social</label>
				                    <div class="col-lg-6">
				                        <input type="text" id="text1" placeholder="Raz&atilde;o Social" class="form-control" ng-model="pagamentosFiltro.razaoSocial"/>
		        		            </div>
			                    </div>
			                    <div class="form-group">
									<label for="text1" class="control-label col-lg-1">NSU</label>
				                    <div class="col-lg-3">
				                        <input type="text" id="text1" placeholder="N&uacute;mero NSU" class="form-control" ng-model="pagamentosFiltro.nsu">
		        		            </div>
		        		            <label for="text1" class="control-label col-lg-2">R$</label>
				                    <div class="col-lg-2">
				                        <input type="text" id="text1" placeholder="Valor Venda" class="form-control" ng-model="pagamentosFiltro.valor"/>
		        		            </div>
		        		            <div class="col-lg-4" align="right">
										<button class="btn btn-warning btn-round" ng-click="pesquisarPagamentos()"><i class="icon-search icon-white"></i> Pesquisar</button>
									</div>		                    		
		                		</div>
			                </form>
	                    </div>
	                </div>
		    	</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-warning">
				<div class="panel-heading">
					Resultado Pagamentos
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover" id="dataTables-pagamentos">
	                        <thead>
								<tr>
									<th>Data envio banco</th>
									<th>Valor pago</th>
									<th>Qtde CV</th>
									<th>Adquirente</th>
								</tr>
	                        </thead>
							<tbody>
								<tr ng-repeat="resumo in listaPagamentosRetorno" data-toggle="modal" data-target=".bs-pagamentos-modal" ng-click=mostrarDetalhePagamentos($index)>
									<td>{{resumo.dataEnvioBanco | date: 'dd/MM/yyyy'}}</td>
									<td>{{resumo.valorLiquido | currency : 'R$ '}}</td>
									<td>{{resumo.quantidadeCVAceitos}}</td>
									<td>{{resumo.comprovantes[0].historicoVendaCieloDto.empresaAdquirente}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</section>
<script>
	$(function () { 
		$('[data-toggle="tooltip"]').tooltip(); 
		formInit(); 
	});
</script>