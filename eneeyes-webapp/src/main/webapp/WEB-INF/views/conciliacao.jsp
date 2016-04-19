<section ng-controller="ConciliacaoController">
<div class="inner" style="min-height: 700px;">
	<div class="row">
		<div class="col-lg-10">
			<h3> Concilia&ccedil;&atilde;o </h3>
		</div>
	</div>
	<hr/>
	
	<div id="modalConciliacao" class="modal fade bs-conciliacao-modal col-lg-10" tabindex="-1" role="dialog" aria-labelledby="conciliacaoModal" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title" align="center">
	                	<b>Detalhe da conciliação</b>
	                </h4>
	            </div>
	            <div class="modal-body">
	            	
	            	<form class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-3" style="margin-top: 55px;" align="right">
								<label class="control-label">Data</label><br>
								<label class="control-label" style="margin-top: 28px;">NSU</label><br>
								<label class="control-label" style="margin-top: 28px;">Valor</label><br>
								<label class="control-label" style="margin-top: 28px;">Tipo de venda</label><br>
								<label class="control-label" style="margin-top: 28px;">Taxa</label><br>
							</div>
							
							<div class="col-sm-4">
			            		<div class="panel panel-info">
									<div class="panel-heading">
										Detalhe venda
									</div>
									<div class="panel-body">
										<input type="text" class="form-control" value="{{venda.dataLancamen | date : 'dd/MM/yyyy'}}" disabled><br>
										<input type="text" class="form-control" value="{{venda.nsuHost}}" disabled><br>
										<input type="text" class="form-control" value="{{venda.valor | currency : 'R$ '}}" disabled><br>
										<input type="text" class="form-control" value="{{venda.titComplTrans}}" disabled><br>
										<input type="text" class="form-control" value="{{taxaVenda}} %" disabled>
									</div>
								</div>
							</div>
					
							<div class="col-sm-4">
								<div class="panel panel-success">
									<div class="panel-heading">
										Detalhe comprovante venda
									</div>
									<div class="panel-body">
										<input type="text" class="form-control" value="{{comprovante.dataVendaAjuste | date : 'dd/MM/yyyy'}}" disabled><br>
										<input type="text" class="form-control" value="{{comprovante.nsuDoc}}" disabled><br>
										<input type="text" class="form-control" value="{{comprovante.valorTotalVendaCasoParceladoLoja | currency : 'R$ '}}" disabled ng-if="parcelas > 0">
										<input type="text" class="form-control" value="{{comprovante.valorCompraOuParcela | currency : 'R$ '}}" disabled ng-if="parcelas == 0"><br>
										<input type="text" class="form-control" value="{{comprovante.tipoVenda}}" disabled><br>
										<input type="text" class="form-control" value="{{taxaVenda}} %" disabled>
									</div>
								</div>
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
		        	<ul class="nav nav-pills-success">
	                    <li class="active" ng-controller="UserTypeController">
	                    	<a href="#avancado" data-toggle="tab" ng-click="clearConciliacaoFiltro();clearListaAdquirentes()">Avan&ccedil;ado</a>
	                    </li>
	                    <li><a href="#basico" data-toggle="tab" ng-click="clearConciliacaoFiltro()">B&aacute;sico</a>
	                    </li>
	                </ul>
	                <div class="tab-content">
	                    <div class="tab-pane fade in active" id="avancado">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-lg-2">CNPJ</label>
			                        <div class="col-lg-3">
										<select class="form-control" ng-controller="UserTypeController" ng-model="conciliacaoFiltro.cnpj" 
											ng-options="filial.cnpj as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj" 
											ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
											ng-change="setListAdquirentesByCnpj(conciliacaoFiltro.cnpj)">
											<option value='' selected="selected">Selecione...</option>
										</select>	
			                        </div>			                        
		                    		<label class="control-label col-lg-2">Adquirente</label>
									<div class="col-lg-3">
										<select class="form-control" ng-controller="UserTypeController" ng-model="conciliacaoFiltro.adquirente" 
											ng-disabled="userLogado.listaAdquirentes.length == 0" 
											ng-options="adquirente.descricao as adquirente.descricao for adquirente in userLogado.listaAdquirentes">
											<option value='' selected="selected">Selecione...</option>
										</select>											
									</div>
		                		</div>
	                			<div class="form-group">
	                				<label class="control-label col-lg-2">Tipo Venda</label>
									<div class="col-lg-3">
										<select data-placeholder="Produto" class="form-control" ng-model="conciliacaoFiltro.tipoVenda">
											<option value="">Selecione... </option>
											<option value="Débito">D&eacute;bito</option>
											<option value="Crédito">Cr&eacute;dito</option>
											<option value="Outros">Outros</option>
										</select>
									</div>
									<label class="control-label col-lg-2">Conciliação</label>
									<div class="col-lg-3">
										<select data-placeholder="Status" class="form-control" ng-model="detalheVendaFiltro.status">
											<option value="" selected="selected">Todos</option>
											<option value="Automática">Automática</option>
											<option value="Manual">Manual</option>
										</select>
		                			</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2">Per&iacute;odo</label>
			                        <div class="col-lg-3">
			                            <div class="input-group">
			                                <span class="input-group-addon"><i class="icon-calendar"></i></span>
			                                <input type="text" daterangepicker ng-model="rangeData" class="form-control"/>
			                            </div>
			                        </div>
									<div class="col-lg-5" align="right">
										<button class="btn btn-success btn-round" ng-click="pesquisarConciliacao()"><i class="icon-search icon-white"></i> Pesquisar</button>
									</div>			                        
		                		</div>								
			                </form>
	                    </div>
	                    <div class="tab-pane fade" id="basico">
	                        <form class="form-horizontal">
								<div class="form-group">
                        			<label for="text1" class="control-label col-lg-1">CNPJ</label>
				                    <div class="col-lg-3">
										<select id="text1" class="form-control" ng-controller="UserTypeController" ng-model="conciliacaoFiltro.cnpj" 
											ng-options="filial.cnpj as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj" 
											ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
											ng-change="setListAdquirentesByCnpj(conciliacaoFiltro.cnpj)">
											<option value='' selected="selected">Selecione...</option>
										</select>											
		        		            </div>
		        		            <label for="text1" class="control-label col-lg-2">Raz&atilde;o Social</label>
				                    <div class="col-lg-6">
				                        <input type="text" id="text1" placeholder="Raz&atilde;o Social" class="form-control" ng-model="conciliacaoFiltro.razaoSocial"/>
		        		            </div>
			                    </div>
			                    <div class="form-group">
									<label for="text1" class="control-label col-lg-1">NSU</label>
				                    <div class="col-lg-3">
				                        <input type="text" id="text1" placeholder="N&uacute;mero NSU" class="form-control" ng-model="conciliacaoFiltro.nsu">
		        		            </div>
		        		            <label for="text1" class="control-label col-lg-2">R$</label>
				                    <div class="col-lg-2">
				                        <input type="text" id="text1" placeholder="Valor Venda" class="form-control" ng-model="conciliacaoFiltro.valor"/>
		        		            </div>
		        		            <div class="col-lg-4" align="right">
										<button class="btn btn-success btn-round" ng-click="pesquisarConciliacao()"><i class="icon-search icon-white"></i> Pesquisar</button>
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
			<div class="panel panel-success">
				<div class="panel-heading">
					Vendas conciliadas
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover" id="dataTables-conciliacao">
	                        <thead>
								<tr>
									<th>Data</th>
									<th>Hora</th>
									<th>NSU</th>
									<th>Adquirente</th>
									<th>Tipo </th>
									<th>Parc</th>
									<th>Valor</th>
									<th>Taxa</th>
									<th>Conciliação</th>
									<th>CNPJ</th>
								</tr>
	                        </thead>
							<tbody>
								<tr ng-repeat="conciliacao in listaConciliacaoRetorno" ng-class="{'warning' : conciliacao.status == 'Manual'}"
									data-toggle="modal" data-target=".bs-conciliacao-modal" ng-click=mostrarConciliacao($index)>
									<td>{{conciliacao.data | date: 'dd/MM/yyyy'}}</td>
									<td>{{conciliacao.hora | date: 'HH:mm:ss'}}</td>
									<td>{{conciliacao.nsu}}</td>
									<td>{{conciliacao.adquirente}}</td>
									<td>{{conciliacao.tipoVenda}}</td>
									<td>{{conciliacao.parcelas}}</td>
									<td>{{conciliacao.valorVenda | currency : 'R$ '}}</td>
									<td>{{conciliacao.taxaVenda | currency : '% '}}</td>
									<td>{{conciliacao.status}}</td>
									<td>{{conciliacao.cnpj}}</td>
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