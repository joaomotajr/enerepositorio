<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:csrfMetaTags />
        <!-- MENU SECTION - Modificado  -->
       
       <!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">
		
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

          <!-- Sidebar user panel (optional) -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="/assets/img/user2-160x160.jpg" class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p><sec:authentication property="principal.displayName" /></p>					
				  	<!-- Status -->
				  	<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>

          	<!-- search form (Optional) -->
          	<form action="#" method="get" class="sidebar-form">
				<div class="input-group">
					<input type="text" name="q" class="form-control" placeholder="Search...">
					<span class="input-group-btn">
					<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
					</span>
	          </div>
          	</form>
          	<!-- /.search form -->

	          <!-- Sidebar Menu -->
			<ul class="sidebar-menu">
					        	  
	            <li class="header">SELECIONE</li>	            
	            <li>
	            	<a href="#" ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')"><i class="fa fa-th"></i> <span>Dashboard</span></a>
	            	<a href="#" ng-click="LoadAjaxContent('navegacao.html', 'Navegação')"><i class="fa fa-circle-o"></i><span>Navegação</span></a>
	            	<a href="#" ng-click="LoadAjaxContent('cadastro.html', 'Cadastro')"><i class="fa fa-circle-o"></i><span>User</span></a>
	            </li>	                                    
	            
	          	<li class="treeview">
	            	<a href="#">
	                <i class="fa fa-edit"></i> <span>Cadastro Equipamentos</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">
	                    <li class="active"><a href="#" ng-click="LoadAjaxContent('controllers.html', 'Controladoras')"><i class="fa fa-tasks"></i> Controladoras</a></li>
	                    <li class="active"><a href="#" ng-click="LoadAjaxContent('transmitters.html','Transmissores')"><i class="fa fa-expand"></i> Transmissores</a></li>
	                    <li class="active"><a href="#" ng-click="LoadAjaxContent('sensors.html','Sensores')"><i class="fa fa-feed"></i> Sensores</a></li>
	                    <li class="active"><a href="#" ng-click="LoadAjaxContent('detectors.html','Detectores')"><i class="fa fa-th-large"></i> Detectores</a></li>
	                    <li class="active"><a href="#" ng-click="LoadAjaxContent('gases.html', 'Gases')"><i class="fa fa-yelp"></i> Gases</a></li>
			            		        
	              	</ul>
	            </li>
	            
	            <li class="treeview">
	            	<a href="#">
	                <i class="fa fa-cube"></i> <span>Cadastro Clientes</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">			            		        
			            <li><a href="#" ng-click="LoadAjaxContent('companies.html', 'Empresas')"><i class="fa fa-industry"></i> Empresa</a></li>
			            <li><a href="#" ng-click="LoadAjaxContent('alarms.html', 'Alarmes')"><i class="fa fa-bullhorn"></i> Alarmes</a></li>
			            <li><a href="#" ng-click="LoadAjaxContent('cadastro.html', 'Cadastro')"><i class="fa fa-bullhorn"></i> Cadastro</a></li>
	              	</ul>
	            </li>	                  
	        </ul>
        </section>        
	</aside>