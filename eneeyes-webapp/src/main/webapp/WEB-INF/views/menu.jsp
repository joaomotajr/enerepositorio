<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:csrfMetaTags />
        <!-- MENU SECTION -->
       <div id="left" >
            <div class="media user-media well-small">
                <div class="media-body">
                    <h5 class="media-heading">Ol&aacute;, <sec:authentication property="principal.displayName" /></h5>
                </div>
            </div>

            <ul id="menu" class="collapse">
                <li class="panel" ng-show="showUserMenu == false">
                    <a href="#" ng-click="LoadAjaxContent('dashboard.html'); verificaMensagemIntegracao()">
                        <i class="icon-dashboard"></i> Resumo
                    </a>                   
                </li>
				<li class="panel" ng-show="showUserMenu == false" ng-controller="UserTypeController">
                    <a href="#" ng-click="LoadAjaxContent('conta-corrente.html');getListaCnpjFromUser();">
                        <i class="icon-usd"></i> Conta Corrente
                    </a>
                </li>
                <li class="panel" ng-show="showUserMenu == false">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#component-nav">
                        <i class="icon-legal"> </i> Concilia&ccedil;&atilde;o     
                        <span class="pull-right">
                          <i class="icon-angle-down"></i>
                        </span>
                    </a>
                    <ul class="collapse" id="component-nav" ng-controller="UserTypeController">
						<li class=""><a href="#" ng-click="LoadAjaxContent('conciliacao.html');getListaCnpjFromUser();"><i class="icon-search"></i> Pesquisa </a></li>
						<li class=""><a href="#" ng-click="LoadAjaxContent('integracao-adquirente.html');"><i class="icon-cloud-upload"></i> Importa&ccedil;&atilde;o Adquirente </a></li>
                    </ul>
                </li>
                <li class="panel" ng-show="showUserMenu == false">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle collapsed" data-target="#form-nav">
                        <i class="icon-shopping-cart"></i> Vendas
                        <span class="pull-right">
                            <i class="icon-angle-down"></i>
                        </span>
                    </a>
                    <ul class="collapse" id="form-nav" ng-controller="UserTypeController">
                    	<li class=""><a href="#" ng-click="LoadAjaxContent('vendas-resumo.html')"><i class="icon-bar-chart"></i> Resumo </a></li>
                    	<li class=""><a href="#" ng-click="LoadAjaxContent('inclusao-venda.html');getListaCnpjFromUser();">
                    		<i class="icon-plus"></i> Inclus&atilde;o manual </a>
                    	</li>
                        <li class=""><a href="#" ng-click="LoadAjaxContent('vendas.html');getListaCnpjFromUser()">
                        	<i class="icon-search"></i> Pesquisa </a>
                        </li>
                        <li class=""><a href="#" ng-click="LoadAjaxContent('integracao-tef.html')"><i class="icon-cloud-upload"></i> Importa&ccedil;&atilde;o TEF </a></li>
                    </ul>
                </li>
                <li class="panel" ng-show="showUserMenu == false" ng-controller="UserTypeController">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#pagesr-nav" 
                    	ng-click="LoadAjaxContent('pagamentos.html');getListaCnpjFromUser();">
                        <i class="icon-money"></i> Pagamentos
                    </a>
                </li>
                <li class="panel" ng-show="showUserMenu == false" ng-controller="UserTypeController">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#chart-nav" ng-click="LoadAjaxContent('inconsistencia.html');getListaCnpjFromUser();">
                        <i class="icon-warning-sign"></i> Inconsist&ecirc;ncias
                    </a>
                </li>
				<li class="panel" ng-controller="UserTypeController" ng-show="userLogado.isContractor && showUserMenu == true">
					<a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#manager-nav">
						<i class="icon-user"></i> Usuários e Filiais
						<span class="pull-right">
							<i class="icon-angle-down"></i>
						</span>
					</a>
					<ul class="collapse" id="manager-nav">
						<li class=""><a href="#" ng-click="LoadAjaxContent('user-filial-pesquisa.html')"><i class="icon-search"></i> Gerenciamento e Pesquisa </a></li>
					</ul>
				</li>
				<li class="panel" ng-controller="UserTypeController" ng-show="userLogado.isContractor && showUserMenu == true">
					<a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#group-nav">
						<i class="icon-user"></i> Grupos
						<span class="pull-right">
							<i class="icon-angle-down"></i>
						</span>
					</a>
					<ul class="collapse" id="group-nav">
						<li class=""><a href="#" ng-click="LoadAjaxContent('grupo-pesquisa.html')"><i class="icon-search"></i> Gerenciamento e Pesquisa </a></li>
						<li class=""><a href="#" ng-click="LoadAjaxContent('user-filial-relacionamento.html')"><i class="icon-check"></i> Relacionamentos </a></li>
					</ul>
				</li>				
				<li class="panel" ng-controller="UserTypeController" ng-show="userLogado.isContractor && showUserMenu == true">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" ng-click="LoadAjaxContent('taxa-pesquisa.html');getListaCnpjFromUser();">
                        <i class="icon-money"></i> Taxas
                    </a>
                </li>				
				<li class="panel" ng-controller="UserTypeController" ng-show="userLogado.isContractor && showUserMenu == true">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#chart-nav" ng-click="showMenuUser(false, 'dashboard.html')">
                        <i class="icon-arrow-left"></i> Voltar
                    </a>
                </li>              
            </ul>
        </div>
        <!--END MENU SECTION -->