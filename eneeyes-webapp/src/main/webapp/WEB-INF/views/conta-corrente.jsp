<section ng-controller="ContaCorrenteController">
<div class="inner" style="min-height: 700px;">
	<div class="row">
		<div class="col-lg-10">
			<h3> Conta Corrente </h3>
		</div>
	</div>
	<hr/>
	
	<div id="modalDetalheContaCorrente" class="modal fade bs-contacorrente-modal col-lg-12" tabindex="-1" role="dialog" aria-labelledby="contaCorrenteModal" aria-hidden="true">
	    
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title" align="center">
	                	<small class="pull-left text-muted">
							Conta Corrente
						</small>
	                	<b>Lista de CVs <span style="color : red">{{contaCorrente.status}}</span> no dia {{contaCorrente.data | date : 'dd/MM/yyyy'}}</b>
	                </h4>
	            </div>
	            <div class="modal-body">
					<div class="panel panel-primary">
						<div class="panel-heading">
							Detalhe CVs
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="dataTables-cvs">
			                        <thead>
										<tr>
											<th>NSU</th>
											<th>Valor bruto venda/parcela</th>
											<th>Adquirente</th>
										</tr>
			                        </thead>
									<tbody>
										<tr ng-repeat="comprovante in detalheContaCorrente.listaCv">
											<td>{{comprovante.nsuDoc}}</td>
											<td>{{comprovante.valorCompraOuParcela | currency : 'R$ '}}</td>
											<td>{{comprovante.historicoVendaCieloDto.empresaAdquirente}}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
	            </div>
	            <div class="modal-footer">
	             	<button class="btn btn-default" data-dismiss="modal" aria-label="Close"><i class="icon-exit icon-white"></i> Fechar</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	<div class="row">
		<div class="col-lg-5">
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
					<form class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-lg-3">Per&iacute;odo</label>
	                        <div class="col-lg-9">
	                            <div class="input-group">
	                                <span class="input-group-addon"><i class="icon-calendar"></i></span>
	                                <input type="text" daterangepicker ng-model="rangeData" class="form-control"/>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
                    		<label class="control-label col-lg-3">CNPJ</label>
							<div class="col-lg-9">
								<select class="form-control" ng-controller="UserTypeController" ng-model="contaCorrenteFiltro.cnpj" 
									ng-options="filial.cnpj as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj" 
									ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
									ng-change="setListAdquirentesByCnpj(contaCorrenteFiltro.cnpj)">
									<option value='' selected="selected">Selecione...</option>
								</select>								
							</div>
                		</div>	                    
	                    <div class="form-group">
                    		<label class="control-label col-lg-3">Adquirente</label>
							<div class="col-lg-9">
								<select class="form-control" ng-controller="UserTypeController" ng-model="contaCorrenteFiltro.adquirente" 
									ng-disabled="userLogado.listaAdquirentes.length == 0" 
									ng-options="adquirente.descricao as adquirente.descricao for adquirente in userLogado.listaAdquirentes">
									<option value='' selected="selected">Selecione...</option>
								</select>									
							</div>
                		</div>
	                    <div class="form-group">
                    		<label for="text1" class="control-label col-lg-3">Status</label>
		                    <div class="col-lg-9">
		                        <select data-placeholder="Adquirente" class="form-control" ng-model="contaCorrenteFiltro.status">
									<option value="">Selecione...</option>
									<option value="Recebido">Recebido</option>
									<option value="Receber">A Receber</option>
								</select>
        		            </div>
                		</div>
               			<div class="form-group">
							<div class="col-lg-12" align="right">
								<button class="btn btn-primary btn-round" ng-click="pesquisarContaCorrente()"><i class="icon-search icon-white"></i> Pesquisar</button>
							</div>
						</div>
	                </form>
		    	</div>
			</div>
		</div>
		<div class="col-lg-7">
	    	<div class="box dark">
	        	<header>
	            	<div class="icons"><i class="icon-bar-chart"></i></div>
	            	<h5>Resumo Conta Corrente</h5>
	            	<div class="toolbar">
		                <ul class="nav">
		                    <li>
		                        <a class="accordion-toggle minimize-box" data-toggle="collapse" href="#resumo">
		                            <i class="icon-chevron-up"></i>
		                        </a>
		                    </li>
		                </ul>
		            </div>
		        </header>
		        <div id="resumo" class="body collapse in">
		        	<div class="alert alert-danger" ng-show="!totalRecebido && !totalReceber">
							N&atilde;o existem valores para ser exibido.
					</div>
					<div class="row">
						<div class="col-lg-6" ng-show="totalRecebido">
							<div align="center"><h6><b>Recebido {{totalRecebido | currency : 'R$ '}}</b></h6></div>
							<div id="chartRecebido"></div>
						</div>
						<div class="col-lg-6" ng-show="totalReceber">
							<div align="center"><h6><b>A receber {{totalReceber | currency : 'R$ '}}</b></h6></div>
							<div id="chartReceber"></div>
						</div>
					</div>
		    	</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					Conta Corrente
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover" id="dataTables-conta-corrente">
	                        <thead>
								<tr>
									<th>Data</th>
									<th>Status</th>
									<th>Cielo</th>
									<th>Total</th>
								</tr>
	                        </thead>
							<tbody>
								<tr ng-repeat="contaCorrente in listaContaCorrenteRetorno" data-toggle="modal" data-target=".bs-contacorrente-modal" ng-click="mostrarDetalheContaCorrente($index)">
									<td>{{contaCorrente.data | date: 'dd/MM/yyyy'}}</td>
									<td>{{contaCorrente.status}}</td>
									<td>{{contaCorrente.valor | currency : 'R$ '}}</td>
									<td>{{contaCorrente.valor | currency : 'R$ '}}</td>
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