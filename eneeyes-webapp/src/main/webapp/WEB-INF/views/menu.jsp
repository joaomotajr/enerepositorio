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
				<div class="pull-left image" data-ng-controller="SiteController">
					<img data-ng-src="{{userImage}}" class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p><sec:authentication property="principal.displayName" /></p>					
				  	<!-- Status -->
				  	<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>	

			<div class="row"> 
				
				<a href="#" data-ng-controller="SiteController" class="logo-lg" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')">
									 	 			 
				 	<span class="logo-lg" >	 		
			 			<img data-ng-if="recolheLogo" src="/assets/img/Logo_e-GAS.PNG" style="background-color: white; float: left; margin-left: 20px; padding:5px; width: 85%;"> 			 
					</span>
					
				</a>
			</div>			
			
			
          	<!-- search form (Optional) -->
          	<!--
          	<form action="#" method="get" class="sidebar-form">
				<div class="input-group">
					<input type="text" name="q" class="form-control" placeholder="Procurar" disabled>
					<span class="input-group-btn">
					<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
					</span>
	          </div>
          	</form>
          	-->
          	<!-- /.search form -->
        

	          <!-- Sidebar Menu -->
			<ul class="sidebar-menu">
					        	  
<!-- 	            <li class="header">SELECIONE</li> -->
	            	            
	            <li class="treeview">
	            	<a href="#" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')"><i class="fa fa-th text-aqua"></i> 
	            		<span>Dashboard</span>	            			            		
	            	</a>	            		            	
	            </li>
	            <!--
	            <li class="treeview">
	            	<a href="#" data-ng-click="LoadAjaxContent('simulador.html', 'Simulador')"><i class="fa fa-circle-o"></i>
	            	<span>Simulador</span>
	            	<span class="label label-danger pull-right">Temp</span>
	            	</a>
	            </li>	
	             
	            <li class="treeview">
	            	<a href="#" data-ng-click="LoadAjaxContent('datatable.html', 'datatable')"><i class="fa fa-circle-o"></i>
	            	<span>Datatable</span>
	            	<span class="label label-danger pull-right">Temp</span>
	            	</a>
	            </li>
	             -->
	            
	            <!-- 
	            <li class="treeview">
	            	<a href="#">
	                <i class="fa fa-unlock-alt text-aqua"></i> <span>Segurança</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">			            		        
			            <li><a href="#" data-ng-click="LoadAjaxContent('userPesquisa.html', 'Usuários')"><i class="fa fa-users"></i> Usuários</a></li>
			            <li><a href="#" data-ng-click="LoadAjaxContent('', '')"><i class="fa fa-bullhorn"></i> Grupos de Usuários</a></li>			            	            
	              	</ul>
	            </li>	                                    
	           -->
	           
	           <!-- 
	          	<li class="treeview">
	            	<a href="#"><i class="fa fa-edit"></i> <span>Cadastros</span><i class="fa fa-angle-left pull-right"></i>	</a>
	            	<ul class="treeview-menu">
	            		<li class="active"><a href="#" data-ng-click="LoadAjaxContent('manufacturers.html', 'Fabricantes')"><i class="fa fa-industry"></i> Fabricantes</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('controllers.html', 'Controladoras')"><i class="fa fa-tasks"></i> Controladoras</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('transmitters.html','Transmissores')"><i class="fa fa-expand"></i> Transmissores</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('sensors.html','Sensores')"><i class="fa fa-feed"></i> Sensores</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('detectors.html','Detectores')"><i class="fa fa-th-large"></i> Detectores</a></li>
	                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('gases.html', 'Gases')"><i class="fa fa-yelp"></i> Gases</a></li>
			            		        
	              	</ul>
	            </li>
	            -->
	            <li class="treeview">
	            	<a href="#">
	                <i class="fa fa-cube text-red"></i> <span>Segurança de Gás e Alarme</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">			            
			            <li><a href="#" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')"><i class="fa fa-th text-aqua"></i><span>Dashboard</span></a></li>
			            <li><a href="#" data-ng-click="LoadAjaxContent('logHistoric.html', 'Log-Detectores')"><i class="fa fa-files-o"></i> Log Detectores</a></li>			            	            
	              	</ul>
	            </li>	                  
	            
	            
	            <li class="treeview">
	            	<a href="#">
	                <i class="fa fa-object-group text-green"></i> <span>Segurança Conectada</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">
		            	<li><a href="#" data-ng-click="LoadAjaxContent('monitor.html', 'Monitoramento')"><i class="fa fa-eye text-yellow"></i> 
		            		<span>Monitoramento</span></a>
		            	</li>
		            		
		            	<li class="treeview" data-ng-if="isFrom=='MASTER'">
			            	<a href="#" data-ng-click="LoadAjaxContent('simulador.html', 'Simulador')"><i class="fa fa-circle-o"></i>
			            	<span>Simulador</span>
			            	<span class="label label-danger pull-right">Temp</span>
			            	</a>
			            </li>		            		        
			            
			            <li class="treeview">
			            	<a href="#" data-ng-click="LoadAjaxContent('logAlarm.html', 'Log-Alarmes')"><i class="fa fa-list-alt text-blue"></i><span>Log Alarmes</span>
			            	<span class="label label-success pull-right">Novo</span>
			            	</a>
	            		</li>
	              	</ul>
	            </li>
	            
	          	<li class="treeview">
	            	<a href="#">
	            		<i class="fa fa-lock text-white"></i>
	                	<span style="font-size: 100%">Seg. de Todas as Coisas</span>
	                	<i class="fa fa-angle-left pull-right"></i>
	              	</a>
	              	
              		<ul class="treeview-menu">                		
                		<li>
                			<a href="#" data-ng-click="LoadAjaxContent('userPesquisa.html', 'Usuários')"><i class="fa fa-users text-yellow"></i> <span>Usuários</span></a>
                		</li>                		
                		<li><a href="#" data-ng-click="LoadAjaxContent('companies.html', 'Empresas')"><i class="fa fa-industry"></i> Empresa</a></li> 
                		
                		<li class="treeview">
			            	<a href="#"><i class="fa fa-edit text-green"></i> <span>Cadastros</span><i class="fa fa-angle-left pull-right"></i></a>
			            	<ul class="treeview-menu">
			            		<li class="active"><a href="#" data-ng-click="LoadAjaxContent('manufacturers.html', 'Fabricantes')"><i class="fa fa-industry"></i> Fabricantes</a></li>
			                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('controllers.html', 'Controladoras')"><i class="fa fa-tasks"></i> Controladoras</a></li>
			                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('transmitters.html','Transmissores')"><i class="fa fa-expand"></i> Transmissores</a></li>
			                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('sensors.html','Sensores')"><i class="fa fa-feed"></i> Sensores</a></li>
			                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('detectors.html','Detectores')"><i class="fa fa-th-large"></i> Detectores</a></li>
			                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('gases.html', 'Gases')"><i class="fa fa-yelp"></i> Gases</a></li>					            		        
			              	</ul>
			            </li> 
			            <li><a href="#" data-ng-click="LoadAjaxContent('alarms.html', 'Alarmes')"><i class="fa fa-bullhorn"></i> Alarmes</a></li>
			            <li><a href="#" data-ng-click="LoadAjaxContent('companyDetectorMaintenance.html', 'Detectores-Calibração')"><i class="fa fa-bullhorn"></i> Calibração / Manutenção</a></li>              		
                		
		     		</ul>
            	</li>
	            
	        </ul>
        </section>        
	</aside>