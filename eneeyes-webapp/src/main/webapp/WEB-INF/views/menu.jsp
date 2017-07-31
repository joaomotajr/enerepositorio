<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:csrfMetaTags />
    <!-- MENU Modificado  -->       
    
    <!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">
		
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

	        <!-- Sidebar Menu -->
			<ul class="sidebar-menu">
	            	            
	            <li class="treeview">
	            	<a href="#" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')"><i class="fa fa-th text-aqua"></i> 
	            		<span>Dashboard</span>	            			            		
	            	</a>	            		            	
	            </li>
	            	            
				<li class="treeview" data-ng-if="isFrom=='MASTER'">
	            	<a href="#" data-ng-click="LoadAjaxContent('fusioncharts.html', 'Gr&aacute;ficos')"><i class="fa fa-th text-white"></i> 
	            		<span>Novos Gr&aacute;ficos</span>	            			            		
	            	</a>	            		            	
				</li>

				<li class="treeview">
	            	<a href="#">
	                <i class="fa fa-cube text-red"></i> <span>Sistema: G&aacute;s &amp; Alarme</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">			            
			            <li><a href="#" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')"><i class="fa fa-th text-aqua"></i><span>Dashboard</span></a></li>
			            <li><a href="#" data-ng-click="LoadAjaxContent('logHistoric.html', 'Log-Detectores')"><i class="fa fa-files-o"></i> Log Detectores</a></li>			            	            
	              	</ul>
	            </li>	            
	            
	            <li class="treeview">
	            	<a href="#">
	                <i class="fa fa-object-group text-green"></i> <span>Seguran&ccedil;a Conectada</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">
		            	<li><a href="#" data-ng-click="LoadAjaxContent('monitor.html', 'Monitoramento')"><i class="fa fa-eye text-yellow"></i> 
		            		<span>Monitoramento</span></a>
		            	</li>
		            		
		            	<li class="treeview" data-ng-if="isFrom=='MASTER'">
			            	<a href="#" data-ng-click="LoadAjaxContent('simulador.html', 'Analisador-Cen�rios')"><i class="fa fa-cog"></i>
			            	<span>Analisador de Cen&aacute;rios</span>			            	
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
                			<a href="#" data-ng-click="LoadAjaxContent('userPesquisa.html', 'Usu&aacute;rios')"><i class="fa fa-users text-yellow"></i> <span>Usu&aacute;rios</span></a>
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
			            <li><a href="#" data-ng-click="LoadAjaxContent('companyDetectorMaintenance.html', 'Detectores-Calibra&ccedil;&atilde;o')"><i class="fa fa-retweet"></i> Calibra&ccedil;&atilde;o &amp; Manuten&ccedil;&atilde;o</a></li>
                		
		     		</ul>
            	</li>
	            
	        </ul>
        </section>        
	</aside>