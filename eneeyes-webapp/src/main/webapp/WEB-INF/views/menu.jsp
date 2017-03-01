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
					<img src="/assets/img/avatar_160x160.png" class="img-circle" alt="User Image">
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
					<input type="text" name="q" class="form-control" placeholder="Procurar" disabled>
					<span class="input-group-btn">
					<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
					</span>
	          </div>
          	</form>
          	<!-- /.search form -->

	          <!-- Sidebar Menu -->
			<ul class="sidebar-menu">
					        	  
	            <li class="header">SELECIONE</li>
	           
	            	            
	            <li class="treeview">
	            	<a href="#" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')"><i class="fa fa-th text-aqua"></i> 
	            		<span>Dashboard</span>
	            		<span class="label label-success pull-right">Novo</span>	            		
	            	</a>	            	
	            </li>
	            <li class="treeview">
	            	<a href="#" data-ng-click="LoadAjaxContent('simulador.html', 'Simulador')"><i class="fa fa-circle-o"></i>
	            	<span>Simulador</span>
	            	<span class="label label-danger pull-right">Temp</span>
	            	</a>
	            </li>	
	            <li class="treeview">
	            	<a href="#"><i class="fa fa-circle-o"></i>
	            	<span>User</span>
	            	<span class="label label-primary pull-right">Breve</span>
	            	</a>
	            </li>	                                    
	           
	          	<li class="treeview">
	            	<a href="#">
	                <i class="fa fa-edit"></i> <span>Cadastros</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">
	            		<li class="active"><a href="#" data-ng-click="LoadAjaxContent('manufacturers.html', 'Fabricantes')"><i class="fa fa-industry"></i> Fabricantes</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('controllers.html', 'Controladoras')"><i class="fa fa-tasks"></i> Controladoras</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('transmitters.html','Transmissores')"><i class="fa fa-expand"></i> Transmissores</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('sensors.html','Sensores')"><i class="fa fa-feed"></i> Sensores</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('detectors.html','Detectores')"><i class="fa fa-th-large"></i> Detectores</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('gases.html', 'Gases')"><i class="fa fa-yelp"></i> Gases</a></li>
			            		        
	              	</ul>
	            </li>
	            
	            <li class="treeview">
	            	<a href="#">
	                <i class="fa fa-cube"></i> <span>Clientes</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">			            		        
			            <li><a href="#" data-ng-click="LoadAjaxContent('companies.html', 'Empresas')"><i class="fa fa-industry"></i> Empresa</a></li>
			            <li><a href="#" data-ng-click="LoadAjaxContent('alarms.html', 'Alarmes')"><i class="fa fa-bullhorn"></i> Alarmes</a></li>			            	            
	              	</ul>
	            </li>	                  
	            
	            <li class="treeview">
	            	<a href="#">
	                <i class="fa fa-search"></i> <span>Consultas</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">			            		        
			            <li><a href="#" data-ng-click="LoadAjaxContent('logHistoric.html', 'Log Detectores')"><i class="fa fa-files-o"></i> Log Detectores</a></li>
<!-- 			            <li><a href="#" data-ng-click="LoadAjaxContent('logHistoricAlarm.html', 'Log Alarmes')"><i class="fa fa-file-audio-o"></i> Log Alarmes</a></li>			            	             -->
	              	</ul>
	            </li>
	            
	        </ul>
        </section>        
	</aside>