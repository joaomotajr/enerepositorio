<section ng-controller="DetalheVendaController">
<div class="inner" style="min-height: 700px;">
	<div class="row">
		<div class="col-lg-10">
			<h3> Vendas </h3>
		</div>
		<div class="col-lg-2">
			<h3><button class="btn btn-info" ng-click="LoadAjaxContent('inclusao-venda.html')"><i class="icon-plus icon-white"></i> Venda Manual</button></h3>
		</div>
	</div>
	<hr/>
	
	<div id="modalDetalheVenda" class="modal fade bs-detalhevenda-modal col-lg-12" tabindex="-1" role="dialog" aria-labelledby="detalheVendaModal" aria-hidden="true">
	    
	    <div id="modalImagemComprovante" class="modal fade bs-imagemcomprovante-modal" tabindex="-1" role="dialog" aria-labelledby="imagemComprovanteModal" aria-hidden="true">
		    <div class="modal-dialog" style="width: 350px;">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h4 class="modal-title" align="center" title="NSU, Código CV ou DOC">
		                	<small class="pull-left text-muted">
								Vendas
							</small>
		                	<b>Detalhe do comprovante - {{detalheVendaLista.nsu}}</b>
		                </h4>
		            </div>
		            <div class="modal-body" align="center">
						<img src="/assets/img/tip-cielo-2.jpg"/>
		            </div>
		            <div class="modal-footer">
		             	<button class="btn btn-default" ng-click=fecharModalImagem()><i class="icon-exit icon-white"></i> Fechar</button>
		            </div>
		        </div>
		    </div>
		</div>
	    
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title" align="center" title="NSU, Código CV ou DOC">
	                	<small class="pull-left text-muted">
							Vendas
						</small>
	                	<b>Detalhe do comprovante - {{detalheVendaLista.nsu}}</b>
	                	<!-- Este informação deverá aparecer somente quando a transação tiver uma imagem de comprovante relacionada -->
	                	<button ng-if="detalheVendaLista.meioCaptura.indexOf('App') > -1" id="example4" class="btn btn-success btn-sm btn-round" data-toggle="modal" data-target=".bs-imagemcomprovante-modal" title="Veja seu comprovante"><i class="icon-eye-open"></i> - CV</button>
	                </h4>
	            </div>
	            <div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-3 control-label" title="NSU, Código CV ou DOC">Código da transação</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.nsu}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Adquirente</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.adquirente}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Bandeira</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.nomeProduto}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Código estabelcimento</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.estabelecimento}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Tipo de venda</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.tipoVenda}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Número do cartão</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.numeroCartao}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Data</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.data | date : 'dd/MM/yyyy'}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Hora</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.hora | date : 'HH:mm:ss'}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Valor</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.valor | currency}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Código de autorização</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.codigoAutoriz}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Código do POS</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.terminalLogico}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Parcelas</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.numPar}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Nome Cliente</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.nomeCliente}}" disabled>
							</div>
							<label class="col-sm-3 control-label">CNPJ</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.cnpj}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Status</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.status}}" disabled>
							</div>
							<label class="col-sm-3 control-label">Meio de captura da venda</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="{{detalheVendaLista.meioCaptura}}" disabled>
							</div>
						</div>
					</form>
	            </div>
	            <div class="modal-footer">
	             	<button ng-if="detalheVendaLista.meioCaptura.indexOf('Manual') > -1" data-dismiss="modal" aria-label="Close" class="btn btn-info" ng-click="editarDetalheVenda()"><i class="icon-edit icon-white"></i> Editar</button>
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
	                <ul class="nav nav-pills-info">
	                    <li class="active"><a href="#avancado" data-toggle="tab" ng-click="clearDetalheVendaFiltro()">Avan&ccedil;ado</a>
	                    </li>
	                    <li><a href="#basico" data-toggle="tab" ng-click="clearDetalheVendaFiltro()">B&aacute;sico</a>
	                    </li>
	                </ul>
	                
	                <div class="tab-content">
	                    <div class="tab-pane fade in active" id="avancado">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-lg-1">Per&iacute;odo</label>
			                        <div class="col-lg-3">
			                            <div class="input-group">
			                                <span class="input-group-addon"><i class="icon-calendar"></i></span>
			                                <input type="text" daterangepicker ng-model="rangeData" class="form-control"/>
			                            </div>
			                        </div>
			                        <label class="control-label col-lg-2">Tipo Venda</label>
									<div class="col-lg-2">
										<select data-placeholder="Produto" class="form-control" ng-model="detalheVendaFiltro.tipoVenda">
											<option value="">Selecione... </option>
											<option value="Debito">D&eacute;bito</option>
											<option value="Credito">Cr&eacute;dito</option>
											<option value="Voucher">Voucher</option>
											<option value="Demais">Demais</option>
										</select>
									</div>
									<label class="control-label col-lg-1">Status</label>
									<div class="col-lg-3">
										<select data-placeholder="Status" class="form-control" ng-model="statusPesquisa" ng-click="setStatus()">
											<option value="" selected="selected">Todos</option>
											<option value="conciliado">Conciliado</option>
											<option value="nao_conciliado">N&atilde;o conciliado</option>
											<option value="inconsistente" >Inconsistente</option>
											<option value="conciliado_manual">Conciliado Manual</option>
										</select>
		                			</div>
			                    </div>
			                    <div class="form-group">
		                    		<label for="text1" class="control-label col-lg-1">CNPJ</label>
				                    <div class="col-lg-3">
										<select id="text1" class="form-control" ng-controller="UserTypeController" ng-model="detalheVendaFiltro.cnpj" 
											ng-options="filial.cnpj as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj" 
											ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
											ng-change="setListAdquirentesByCnpj(detalheVendaFiltro.cnpj)">
											<option value='' selected="selected">Selecione...</option>
										</select>											
		        		            </div>
		        		            <label for="text1" class="control-label col-lg-2">Raz&atilde;o Social</label>
				                    <div class="col-lg-6">
				                        <input type="text" id="text1" placeholder="Raz&atilde;o Social" class="form-control" ng-model="detalheVendaFiltro.razaoSocial"/>
		        		            </div>
		                		</div>
		                		<div class="form-group">
		                    		<label class="control-label col-lg-1">Adquirente</label>
									<div class="col-lg-3">
										<select class="form-control" ng-controller="UserTypeController" ng-model="detalheVendaFiltro.adquirente" 
											ng-disabled="userLogado.listaAdquirentes.length == 0" 
											ng-options="adquirente.descricao as adquirente.descricao for adquirente in userLogado.listaAdquirentes">
											<option value='' selected="selected">Selecione...</option>
										</select>											
									</div>
									<div class="col-lg-8" align="right">
										<button class="btn btn-info btn-round" ng-click="pesquisarVenda()"><i class="icon-search icon-white"></i> Pesquisar</button>
									</div>
		                		</div>
			                </form>
	                    </div>
	                    
	                    <div class="tab-pane fade" id="basico">
	                        <form class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-lg-1" for="dp2">Data</label>
			                        <div class="col-lg-3">
                            			<input type="text" class="form-control" data-date-format="dd/mm/yyyy" datemonopicker ng-model="detalheVendaFiltro.data"/>
                        			</div>
                        			<label for="text1" class="control-label col-lg-1">Cart&atilde;o</label>
				                    <div class="col-lg-3">
				                        <input type="text" id="text1" placeholder="N&uacute;mero cart&atilde;o" class="form-control" ng-model="detalheVendaFiltro.cartao"/>
		        		            </div>
		        		            <label for="text1" class="control-label col-lg-1">NSU</label>
				                    <div class="col-lg-3">
				                        <input type="text" id="text1" placeholder="N&uacute;mero NSU" class="form-control" ng-model="detalheVendaFiltro.nsu">
		        		            </div>
			                    </div>
			                    <div class="form-group">
		                    		<label for="text1" class="control-label col-lg-1">R$</label>
				                    <div class="col-lg-2">
				                        <input type="text" id="text1" placeholder="Valor Venda" class="form-control" ng-model="detalheVendaFiltro.valor"/>
		        		            </div>
		        		            <div class="col-lg-9" align="right">
										<button class="btn btn-info btn-round" ng-click="pesquisarVenda()"><i class="icon-search icon-white"></i> Pesquisar</button>
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
			<div class="panel panel-info">
				<div class="panel-heading">
					Resultado Venda
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover" id="dataTables-vendas">
	                        <thead>
								<tr>
									<th>Data</th>
									<th>Hora</th>
									<th>NSU</th>									
									<th>Adquirente</th>
									<th>Bandeira</th>
									<th>Produto</th>
									<th>Valor</th>
									<th>Taxa</th>
									<th>Parc</th>
									<th>Status</th>
									<th>CNPJ</th>
								</tr>
	                        </thead>
							<tbody>
								<tr ng-repeat="detalheVenda in listaVendasRetorno" ng-class="{'danger' : detalheVenda.estadoTransacao == 'NEGADA', 
										   'success' : detalheVenda.estadoTransacao == 'EFETUADA',
										   'warning' : detalheVenda.estadoTransacao == 'PENDENTE'}"
										   data-toggle="modal" data-target=".bs-detalhevenda-modal"
										   ng-click=mostrarDetalheVenda($index)>
									<td>{{detalheVenda.historicoVenda.dataMovimento | date: 'dd/MM/yyyy'}}</td>
									<td>{{detalheVenda.hora | date: 'hh:mm:ss'}}</td>
									<td>{{detalheVenda.nsuHost}}</td>									
									<td>{{detalheVenda.autorizador}}</td>
									<td>{{detalheVenda.nomeProduto}}</td>
									<td>{{detalheVenda.titComplTrans}}</td>
									<td>{{detalheVenda.valor | currency : 'R$ '}}</td>
									<td>{{detalheVenda.taxaServico | currency : '% '}}</td>
									<td>{{detalheVenda.numPar}}</td>
									<td>{{detalheVenda.estadoTransacao}}</td>
									<td>{{detalheVenda.cpfCnpj}}</td>
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