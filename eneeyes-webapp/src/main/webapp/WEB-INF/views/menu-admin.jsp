<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:csrfMetaTags />
<!-- MENU SECTION -->
<div id="left">
     <div class="media user-media well-small">
         <div class="media-body">
             <h5 class="media-heading">Ol&aacute;, <sec:authentication property="principal.displayName" /></h5>
    	</div>
	</div>

	<ul id="menu" class="collapse">
    	<li ng-controller="UserTypeController" class="panel">
        	<a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle collapsed" data-target="#cliente-nav">
            	<i class="icon-shopping-cart"></i> Clientes
            	<span class="pull-right">
                	<i class="icon-angle-down"></i>
            	</span>
        	</a>
        	<ul class="collapse" id="cliente-nav">
            	<li class=""><a href="#" ng-click="LoadAjaxContent('user-filial-pesquisa.html')"><i class="icon-search"></i> Gerenciamento e Pesquisas </a></li>
           	</ul>
        </li>
		<li ng-controller="UserTypeController" class="panel">
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
		<li ng-controller="UserTypeController" class="panel">
            <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" ng-click="LoadAjaxContent('taxa-pesquisa.html');getListaCnpjFromUser();">
                <i class="icon-money"></i> Taxas
            </a>
        </li>	
    </ul>
</div>
<!--END MENU SECTION -->