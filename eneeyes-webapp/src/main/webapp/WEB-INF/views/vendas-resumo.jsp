<section id="conteudoVendasResumo" ng-controller="VendasResumoController">
	<div class="inner" style="min-height: 700px;">
	    <div class="row">
	        <div class="col-lg-12">
	            <h3> Resumo Venda </h3>
	        </div>
	    </div>
		<hr />

		<div id="pesquisas" class="body collapse in">
        	<form class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-lg-1" for="dp2">Data</label>
                       <div class="col-lg-2">
                  			<input type="text" class="form-control" data-date-format="dd/mm/yyyy" datemonopicker ng-model="dataMovimentoResumo" data-mask="99/99/9999" mask/>
              			</div>
              			<div align="right" class="col-lg-2">
              				<button class="btn btn-info btn-round" ng-click="pesquisarResumoVendaPorData()"><i class="icon-search icon-white"></i> Pesquisar</button>
                  		</div>
                   </div>
      		</form>
    	</div>

		<div id="divVendas" class="row">
			<div class="col-lg-6">
				<div class="panel panel-info" style="height: 300px">
					<div class="panel-heading">
						<i class="icon-shopping-cart"></i>
						Vendas do dia {{dataMovimentoResumo | date : "dd/MM/yyyy"}}
					</div>
					<div class="panel-body">
						<div align="center" ng-show="!showAlertVendas"><b>Vendas - Total de {{totalValores | currency: 'R$': 2}}</b></div>
						<input type="hidden" id="dataGraficoVendasResumo" value="{{dataMovimentoResumo | date : 'dd/MM/yyyy'}}"/>
						<div id="chartVendas"/>
						<div class="alert alert-danger" ng-show="showAlertVendas">
							N&atilde;o foi efetuada a integra&ccedil;&atilde;o do TEF para o dia {{dataMovimentoResumo | date : "dd/MM/yyyy"}}.
							<a href="#" ng-click="LoadAjaxContent('integracao-tef.html')" class="alert-link">Clique aqui</a> para integrar o arquivo.
						</div>
					</div>
				</div>
			</div>
			<!-- Resumo informativo referente ao grafico -->
			<div class="col-lg-6" >
				<div class="panel panel-info" style="height: 300px">
					<div class="panel-heading">
						<i class="icon-icon-info-sign"></i>
						Informações referente aos seus pagamentos
					</div>
					<div class="panel-body" ng-show="!showAlertVendas">
						<div ng-if="totalValores > 0">O total de vendas é de <b>{{totalValores | currency: 'R$': 2}}</b>, sendo que deste montante suas vendas foram atigindas da seguinte forma:</div>
						<div ng-if="totalValoresDebito > 0">Vendas no d&eacute;bito totaliza <b>{{totalValoresDebito | currency: 'R$': 2}}</b>.</div>
						<div ng-if="totalValoresCredito > 0">Vendas no cr&eacute;dito totaliza <b>{{totalValoresCredito | currency: 'R$': 2}}</b>.</div>
						<div ng-if="totalValoresOutros > 0">Vendas de outros tipos totalizam <b>{{totalValoresOutros | currency: 'R$': 2}}</b>.</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
	angular.element('body').scope().verificaMensagemIntegracao();
	$(function () { 
		formInit(); 
	});
</script>
</section>