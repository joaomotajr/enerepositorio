<section ng-controller=UserTypeController>
	<section ng-if="userLogado.isAdmin">
		<jsp:include page="user-filial-pesquisa.jsp"/>	
	</section>
	<section ng-if="!userLogado.isAdmin">
		<section id="conteudoDashboard" ng-controller="DashboardController">
			<div class="inner" style="min-height: 700px;">
			    <div class="row">
			        <div class="col-lg-12">
			            <h3> Resumo </h3>
			        </div>
			    </div>
			    <hr />
			    <div class="row">
			        <div class="col-lg-12">
			            <div style="text-align: center">
			            	<a href="#" class="btn btn-primary-dashboard btn-lg active" ng-click="carregarGraficoContaCorrente()"><i class="icon-usd"></i> Conta Corrente</a>
			               	<a href="#" class="btn btn-success-dashboard btn-lg" ng-click="carregarGraficoConciliacao()"><i class="icon-legal"></i> Concilia&ccedil;&atilde;o</a>
			               	<a href="#" class="btn btn-warning-dashboard btn-lg" ng-click="carregarGraficoPagamentos();"><i class="icon-money"></i> Pagamentos</a>
			               	<a href="#" class="btn btn-danger-dashboard btn-lg" ng-click="carregarGraficoInconsistencias();"><i class="icon-warning-sign"></i> Inconsist&ecirc;ncias</a>
			            </div>
			        </div>
				</div>
				<BR>
				<div class="row">
					<div id="divContaCorrente">
						<div class="col-lg-12">
							<div class="col-lg-6">
								<div class="panel panel-primary" style="height: 320px">
									<div class="panel-heading">
										<i class="icon-usd"></i>
										Conta corrente
									</div>
									<div class="panel-body">
										<div class="alert alert-danger" ng-show="!totalRecebido && !totalReceber">
											N&atilde;o existem valores para ser exibido.
										</div>
										<div class="col-lg-6" ng-show="totalRecebido">
											<div align="center"><h6><b>Últimos 30 dias recebido {{totalRecebido | currency : 'R$ '}}</b></h6></div>
											<div id="chartRecebido"></div>
										</div>
										<div class="col-lg-6" ng-show="totalReceber">
											<div align="center"><h6><b>Próximos 30 dias a receber {{totalReceber | currency : 'R$ '}}</b></h6></div>
											<div id="chartReceber"></div>
										</div>
									</div>
								</div>
							</div>
								
							<!-- Resumo informativo referente ao grafico -->
							<div class="col-lg-6">
								<div class="panel panel-primary" style="height: 320px">
									<div class="panel-heading">
										<i class="icon-info-sign"></i>
										Informações referente a conta corrente
									</div>
									<div class="panel-body">
										<div>
											<span ng-show="totalRecebido">
												O total recebido nos últimos 30 dias é de <b> {{totalRecebido | currency : 'R$ '}}</b>, sendo: <BR>
												Cielo: {{totalRecebido | currency : 'R$ '}}.
											</span>
											<hr ng-show="totalRecebido">
											<span ng-show="totalReceber">
												O total a receber para os próximos 30 dias é de <b> {{totalReceber | currency : 'R$ '}}</b>, sendo:<BR>
												Cielo {{totalReceber | currency : 'R$ '}}.
											</span>
										</div>
										<div class="alert alert-danger" ng-show="!totalRecebido && !totalReceber">
											N&atilde;o existem valores para ser exibido.
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Fim conta corrente -->
					
					<div id="divConciliacao"  style="display: none">
						<div class="col-lg-12">
							<div class="col-lg-6">
								<div class="panel panel-success" style="height: 300px">
									<div class="panel-heading">
										<i class="icon-legal"></i>
										Concilia&ccedil;&atilde;o referente aos últimos 30 dias
									</div>
									<div class="panel-body">
										<div class="col-lg-12" ng-show="totalCreditoVendas + totalDebitoVendas + totalOutrosVendas > 0">
											<div align="center"><b>Transações acatadas</b></div>
											<div id="chartConciliacao"></div>
										</div>
										<div class="alert alert-danger" ng-show="totalCreditoVendas + totalDebitoVendas + totalOutrosVendas == 0">
											N&atilde;o existem valores para ser exibido.
										</div>
									</div>
								</div>
							</div>
							<!-- Resumo informativo referente ao grafico -->
							<div class="col-lg-6" >
								<div class="panel panel-success" style="height: 300px">
									<div class="panel-heading">
										<i class="icon-info-sign"></i>
										Informações referente a concilia&ccedil;&atilde;o
									</div>
									<div class="panel-body">
										<div ng-show="totalCreditoVendas + totalDebitoVendas + totalOutrosVendas > 0">
											Nos últimos 30 dias foram efetuadas <b>{{totalCreditoVendas + totalDebitoVendas + totalOutrosVendas | currency : 'R$ '}}</b> em vendas, sendo<br>
											<b>{{totalCreditoAdquirente + totalDebitoAdquirente + totalOutrosAdquirente | currency : 'R$ '}}</b> de vendas acatadas pelos adquirentes.
										</div>
										<div class="alert alert-danger" ng-show="totalCreditoVendas + totalDebitoVendas + totalOutrosVendas == 0">
											N&atilde;o existem valores para ser exibido.
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Fim inconsistencias -->
					
					<div id="divPagamentos"  style="display: none">
						<div class="col-lg-12">
							<div class="col-lg-6" >
								<div class="panel panel-warning" style="height: 300px">
									<div class="panel-heading">
										<i class="icon-money"></i>
										Pagamentos referentes aos últimos 30 dias
									</div>
									<div class="panel-body">
										<div align="center" ng-show="totalCredito + totalDebito + totalOutros > 0"><b>Pagamentos - Total de {{totalCredito + totalDebito + totalOutros | currency : 'R$ '}}</b></div>
										<div id="chartPagamentos" ng-show="totalCredito + totalDebito + totalOutros > 0"></div>
										<div class="alert alert-danger" ng-show="totalCredito + totalDebito + totalOutros == 0">
											N&atilde;o existem valores para ser exibido.
										</div>
									</div>
								</div>
							</div>
							<!-- Resumo informativo referente ao grafico -->
							<div class="col-lg-6" >
								<div class="panel panel-warning" style="height: 300px">
									<div class="panel-heading">
										<i class="icon-info-sign"></i>
										Informações referente aos seus pagamentos
									</div>
									<div class="panel-body">
										<div ng-show="totalCredito + totalDebito + totalOutros > 0">
											O total de pagamentos é de <b>{{totalCredito + totalDebito + totalOutros | currency : 'R$ '}}</b>, sendo que deste montante seus pagamentos foram efetuados da seguinte forma:<br>
											Pagamentos recebidos da Cielo <b>{{totalCredito + totalDebito + totalOutros | currency : 'R$ '}}</b>.<br>
										</div>
										<div class="alert alert-danger" ng-show="totalCredito + totalDebito + totalOutros == 0">
											N&atilde;o existem valores para ser exibido.
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Fim pagamentos -->
					
					<div id="divInconsistencias"  style="display: none">
						<div class="col-lg-12">
							<div class="col-lg-6">
								<div class="panel panel-danger" style="height: 300px">
									<div class="panel-heading">
										<i class="icon-warning-sign"></i>
										Inconsistências dos últimos 30 dias
									</div>
									<div class="panel-body">
										<div align="center" ng-show="totalCreditoInconsistencia + totalDebitoInconsistencia + totalOutrosInconsistencia > 0"><b>Inconsistências - Total de {{totalCreditoInconsistencia + totalDebitoInconsistencia + totalOutrosInconsistencia | currency : 'R$ '}}</b></div>
										<div id="chartInconsistencias" ng-show="totalCreditoInconsistencia + totalDebitoInconsistencia + totalOutrosInconsistencia > 0"></div>
										<div class="alert alert-danger" ng-show="totalCreditoInconsistencia + totalDebitoInconsistencia + totalOutrosInconsistencia == 0">
											N&atilde;o existem valores para ser exibido.
										</div>
									</div>
								</div>
							</div>
							<!-- Resumo informativo referente ao grafico -->
							<div class="col-lg-6" >
								<div class="panel panel-danger" style="height: 300px">
									<div class="panel-heading">
										<i class="icon-info-sign"></i>
										Informações referente as inconsistências
									</div>
									<div class="panel-body">
										<div ng-show="totalCreditoInconsistencia + totalDebitoInconsistencia + totalOutrosInconsistencia > 0">O total de inconsistências é de <b> {{totalCreditoInconsistencia + totalDebitoInconsistencia + totalOutrosInconsistencia | currency : 'R$ '}}</b>, sendo que deste montante suas inconsistências estão distribuídas da seguinte forma:
										<b>{{totalCreditoInconsistencia + totalDebitoInconsistencia + totalOutrosInconsistencia | currency : 'R$ '}}</b> inconsistente da Cielo.</div>
										<div class="alert alert-danger" ng-show="totalCreditoInconsistencia + totalDebitoInconsistencia + totalOutrosInconsistencia == 0">
											N&atilde;o existem valores para ser exibido.
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Fim inconsistencias -->
				</div>
			</div>
			<script>
				angular.element('body').scope().verificaMensagemIntegracao();
			</script>
		</section>
	</section>	
</section>