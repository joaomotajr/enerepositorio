<section ng-controller="InconsistenciaController">
<div class="inner" style="min-height: 700px;">
	<div class="row">
		<div class="col-lg-10">
			<h3> Inconsist&ecirc;ncias </h3>
		</div>
	</div>
	<hr/>
	
		<div id="modalInconsistencia" class="modal fade bs-inconsistencia-modal col-lg-12" tabindex="-1" role="dialog" aria-labelledby="inconsistenciaModal" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title" align="center">
	                	<b>Detalhe da inconsist&ecirc;ncia</b>
	                </h4>
	            </div>
	            <div class="modal-body">
	            	
	            	<form class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-3" style="margin-top: 55px;" align="right">
								<label class="control-label">Adquirente</label><br>
								<label class="control-label" style="margin-top: 28px;">Data</label><br>
								<label class="control-label" style="margin-top: 28px;">NSU</label><br>
								<label class="control-label" style="margin-top: 28px;">Valor Bruto</label><br>
								<label class="control-label" style="margin-top: 28px;">Tipo de venda</label><br>
								<label class="control-label" style="margin-top: 28px;">Taxa</label><br>
								<label class="control-label" style="margin-top: 28px;">Parcelas</label><br>
							</div>
							
							<div class="col-sm-4">
			            		<div class="panel panel-info">
									<div class="panel-heading">
										Detalhe venda
									</div>
									<div class="panel-body">
										<input type="text" class="form-control" value="{{venda.autorizador}}" disabled><br>
										<input type="text" class="form-control" value="{{venda.dataLancamen | date : 'dd/MM/yyyy'}}" disabled><br>
										<input type="text" class="form-control" value="{{venda.nsuHost}}" disabled><br>
										<input type="text" class="form-control" value="{{venda.valor | currency : 'R$ '}}" disabled><br>
										<input type="text" class="form-control" value="{{venda.titComplTrans}}" disabled><br>
										<input type="text" class="form-control" value="{{taxaVenda}} %" disabled><br>
										<input type="text" class="form-control" value="{{venda.numPar}}" disabled>
									</div>
								</div>
							</div>
					
							<div class="col-sm-4">
								<div class="panel panel-success">
									<div class="panel-heading">
										Detalhe venda adquirente
									</div>
									<div class="panel-body" ng-show="!isPendente">
										<input type="text" class="form-control" value="{{comprovante.historicoVendaCieloDto.empresaAdquirente}}" disabled><br>
										<input type="text" class="form-control" value="{{comprovante.dataVendaAjuste | date : 'dd/MM/yyyy'}}" disabled><br>
										<input type="text" class="form-control" value="{{comprovante.nsuDoc}}" disabled><br>
										<input type="text" class="form-control" value="{{comprovante.valorTotalVendaCasoParceladoLoja | currency : 'R$ '}}" disabled ng-if="parcelas > 0">
										<input type="text" class="form-control" value="{{comprovante.valorCompraOuParcela | currency : 'R$ '}}" disabled ng-if="parcelas == 0"><br>
										<input type="text" class="form-control" value="{{comprovante.tipoVenda}}" disabled><br>
										<input type="text" class="form-control" value="{{taxaComprovante}} %" disabled><br>
										<input type="text" class="form-control" value="{{comprovante.totalParcelas}}" disabled>
									</div>
										<div class="panel-body" ng-show="isPendente">
											<div class="alert alert-warning">
												Venda não identificada pela adquirente
											</div>
									</div>
								</div>
							</div>
						</div>
					</form>
					<div class="row" ng-show="!isPendente">
						<div class="col-sm-12">
							<div class="panel panel-danger">
								<div class="panel-heading">
									Inconsist&ecirc;ncias
								</div>
								<div class="panel-body">
									<span ng-repeat="inconsistencia in inconsistencias">
										{{inconsistencia.descricao}}
									</span>
								</div>
							</div>
						</div>				
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="panel panel-danger">
								<div class="panel-heading">
									Justificativa
								</div>
								<div class="panel-body">
									<div class="col-sm-10">
										<select data-placeholder="justificativa" id="justificativa" class="form-control" ng-model="idJustificativa">
											<option value="">Selecione...</option>
											<option ng-repeat="tipo in tiposJustificativa" value="{{tipo.id}}">{{tipo.descricao}}</option>
										</select>
									</div>
									<div class="col-sm-2">
										<button class="btn btn-danger btn-round" ng-click="salvarJustificativa()"><i class="icon-save icon-white"></i> Confirmar</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					
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
		        	<ul class="nav nav-pills-danger">
	                    <li class="active" ng-controller="UserTypeController">
	                    	<a href="#avancado" data-toggle="tab" ng-click="clearInconsistenciaFiltro();clearListaAdquirentes();">Avan&ccedil;ado</a>
	                    </li>
	                    <li><a href="#basico" data-toggle="tab" ng-click="clearInconsistenciaFiltro()">B&aacute;sico</a>
	                    </li>
	                </ul>
	                <div class="tab-content">
	                    <div class="tab-pane fade in active" id="avancado">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-lg-2">CNPJ</label>
			                        <div class="col-lg-3">
										<select id="text1" class="form-control" ng-controller="UserTypeController" ng-model="inconsistenciaFiltro.cnpj" 
											ng-options="filial.cnpj as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj" 
											ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
											ng-change="setListAdquirentesByCnpj(inconsistenciaFiltro.cnpj)">
											<option value='' selected="selected">Selecione...</option>
										</select>	
			                        </div>
		                    		<label class="control-label col-lg-2">Adquirente</label>
		                    		 <div class="col-lg-3">
										<select class="form-control" ng-controller="UserTypeController" ng-model="inconsistenciaFiltro.adquirente" 
											ng-disabled="userLogado.listaAdquirentes.length == 0" 
											ng-options="adquirente.descricao as adquirente.descricao for adquirente in userLogado.listaAdquirentes">
											<option value='' selected="selected">Selecione...</option>
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
	                				<label class="control-label col-lg-2">Tipo Venda</label>
									<div class="col-lg-3">
										<select data-placeholder="Produto" class="form-control" ng-model="conciliacaoFiltro.tipoVenda">
											<option value="">Selecione... </option>
											<option value="Débito">D&eacute;bito</option>
											<option value="Crédito">Cr&eacute;dito</option>
											<option value="Outros">Outros</option>
										</select>
									</div>
									<div class="col-lg-7" align="right">
										<button class="btn btn-danger btn-round" ng-click="pesquisarInconsistencia()"><i class="icon-search icon-white"></i> Pesquisar</button>
									</div>
								</div>								
			                </form>
	                    </div>
	                    <div class="tab-pane fade" id="basico">
	                        <form class="form-horizontal">
								<div class="form-group">
                        			<label for="text1" class="control-label col-lg-1">CNPJ</label>
				                    <div class="col-lg-3">
										<select id="text1" class="form-control" ng-controller="UserTypeController" ng-model="inconsistenciaFiltro.cnpj" 
											ng-options="filial.cnpj as formataCnpj(filial.cnpj) for filial in userLogado.listaCnpj" 
											ng-options-disabled="filial.status == 'INACTIVE' for filial in userLogado.listaCnpj"
											ng-change="setListAdquirentesByCnpj(inconsistenciaFiltro.cnpj)">
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
										<button class="btn btn-danger btn-round" ng-click="pesquisarInconsistencia()"><i class="icon-search icon-white"></i> Pesquisar</button>
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
			<div class="panel panel-warning" ng-class="{'panel-pessimo' : inconsistencia.statusInconsistencia == 0, 
										   'panel-tipoVenda' : inconsistencia.statusInconsistencia == 1,
										   'panel-taxa' : inconsistencia.statusInconsistencia == 2,
										   'panel-data' : inconsistencia.statusInconsistencia == 3,
										   'panel-pendente' : inconsistencia.statusInconsistencia == 4,
										   'panel-pagamento' : inconsistencia.statusInconsistencia == 5,
										   'panel-recusada' : inconsistencia.statusInconsistencia == 6,
										   'panel-parcelas' : inconsistencia.statusInconsistencia == 7,
										   'panel-chargeback' : inconsistencia.statusInconsistencia == 8}">
				<div class="panel-heading">
					Lista de inconsist&ecirc;ncias
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover" id="dataTables-inconsistencia">
	                        <thead>
								<tr>
									<th>Data</th>
									<th>Hora</th>
									<th>NSU</th>
									<th>Adquirente</th>
									<th>Tipo</th>
									<th>Parc</th>
									<th>Valor</th>
									<th>CNPJ</th>
								</tr>
	                        </thead>
							<tbody>
								<tr ng-repeat="inconsistencia in listaInconsistenciaRetorno" ng-class="{'pessimo' : inconsistencia.statusInconsistencia == 0, 
										   'tipoVenda' : inconsistencia.statusInconsistencia == 1,
										   'taxa' : inconsistencia.statusInconsistencia == 2,
										   'data' : inconsistencia.statusInconsistencia == 3,
										   'pendente' : inconsistencia.statusInconsistencia == 4,
										   'pagamento' : inconsistencia.statusInconsistencia == 5,
										   'recusada' : inconsistencia.statusInconsistencia == 6,
										   'parcelas' : inconsistencia.statusInconsistencia == 7,
										   'chargeback' : inconsistencia.statusInconsistencia == 8}"
										   data-toggle="modal" data-target=".bs-inconsistencia-modal" ng-click=mostrarInconsistencia($index)>
									<td>{{inconsistencia.data | date: 'dd/MM/yyyy'}}</td>
									<td>{{inconsistencia.hora | date: 'HH:mm:ss'}}</td>
									<td>{{inconsistencia.nsu}}</td>
									<td>{{inconsistencia.adquirente}}</td>
									<td>{{inconsistencia.tipoVenda}}</td>
									<td>{{inconsistencia.parcelas}}</td>
									<td>{{inconsistencia.valorVenda | currency : 'R$ '}}</td>
									<td>{{inconsistencia.cnpj}}</td>
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