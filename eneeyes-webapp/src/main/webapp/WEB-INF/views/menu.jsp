<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:csrfMetaTags />            
	<aside class="main-sidebar">
        <section class="sidebar">
			<div class="row">				
				<a href="#" data-ng-controller="SiteController" class="logo-lg ng-scope" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')">														
					<span class="logo-lg">
						<img data-ng-if="recolheLogo" src="/assets/img/Logo_e-GAS.PNG" style="float: left; margin-left: 20px; padding:10px; width: 85%;" class="ng-scope">
					</span>					
				</a>
			</div>

	        <!-- Sidebar Menu -->
			<ul class="sidebar-menu">	            	            
	            <li class="treeview">
	            	<a href="#" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')"><i class="fas fa-chalkboard-teacher text-aqua"></i><span> Dashboard</span></a>	            		            	
	            </li>	            	            
				<!-- <li class="treeview" data-ng-if="isFrom=='MASTER'">
	            	<a href="#" data-ng-click="LoadAjaxContent('fusioncharts.html', 'Gr&aacute;ficos')"><i class="fa fa-th text-white"></i> 
	            		<span>Novos Gr&aacute;ficos</span>	            			            		
	            	</a>	            		            	
				</li> -->
				<li class="treeview">
	            	<a href="#" data-ng-click="LoadAjaxContent('general.html', 'Over-View')"><i class="fas fa-street-view text-yellow"></i><span> Over View</span></a>	            		            	
				</li>
				<li class="treeview">
	            	<a href="#">
	                	<i class="fa fa-cube text-red"></i><span>An&aacute;lise e Pesquisas</span>
	                	<i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">
						<li><a href="#" data-ng-if="isFrom=='MASTER'" data-ng-click="LoadAjaxContent('logHistoric.html', 'Log-Detectores')"><i class="fas fa-file-signature"></i> Log Dispositivos (old)</a></li>
						<li><a href="#" data-ng-click="LoadAjaxContent('logHistoricFast.html', 'Log-Dispositivos')"><i class="fas fa-file-signature"></i><span> Log Dispositivos</span>
							<span class="label label-success pull-right">Novo</span>
						</a></li>
						<li><a href="#" data-ng-click="LoadAjaxContent('logHistoricAlarm.html', 'Log-Alarmes')"><i class="fas fa-file-audio"></i> Log Alarmes</a></li>
						<li><a href="#" data-ng-click="LoadAjaxContent('logAnalytical.html', 'Analise-Comparativa')"><i class="fas fa-shekel-sign"></i> Análise Comparativa</a></li>
	              	</ul>
	            </li>	            
	            <li class="treeview">
	            	<a href="#">
	                <i class="fa fa-object-group text-green"></i><span>Monitoramento</span>
	                <i class="fa fa-angle-left pull-right"></i>
	              	</a>
	            	<ul class="treeview-menu">
		            	<li><a href="#" data-ng-click="LoadAjaxContent('monitor.html', 'Monitoramennto')"><i class="fa fa-eye text-yellow"></i><span>Monitoramento de Alarmes</span></a>
		            	</li>		            		
		            	<li class="treeview" data-ng-if="isFrom=='MASTER'">
			            	<a href="#" data-ng-click="LoadNewWindow('Analisador-Cen&aacute;rios')"><i class="fa fa-cog"></i><span>Analisador de Cen&aacute;rios</span></a>
			            </li>			            
			            <li class="treeview">
			            	<a href="#" data-ng-click="LoadAjaxContent('logAlarm.html', 'Alarmes-Reportados')"><i class="fa fa-list-alt text-blue"></i><span>Alarmes Reportados</span></a>
						</li>
						<li class="treeview">
			            	<a href="#" data-ng-click="LoadAjaxContent('logAuditoria.html', 'Auditoria')"><i class="fa fa-arrows-alt text-blue"></i><span>Auditoria do Sistema</span></a>
	            		</li>
	              	</ul>
				</li>
	            
	          	<li class="treeview">
	            	<a href="#">
	            		<i class="fa fa-lock text-aqua" ></i><span style="font-size: 100%">Cadastro</span><i class="fa fa-angle-left pull-right"></i></a>	              	
              		<ul class="treeview-menu">                		
                		<li>
                			<a href="#" data-ng-click="LoadAjaxContent('userPesquisa.html', 'Usu&aacute;rios')"><i class="fa fa-users"></i> <span>Usu&aacute;rios</span></a>
                		</li>                		
                		<li><a href="#" data-ng-click="LoadAjaxContent('companies.html', 'Empresas')"><i class="fa fa-industry text-blue"></i> Empresa</a></li>                 		
                		<li class="treeview">
			            	<a href="#"><i class="fa fa-edit text-green"></i> <span>Cadastros</span><i class="fa fa-angle-left pull-right"></i></a>
			            	<ul class="treeview-menu">
								<li class="active"><a href="#" data-ng-click="LoadAjaxContent('manufacturers.html', 'Fabricantes')"><i class="fa fa-industry"></i> Fabricantes</a></li>
								<li class="active"><a href="#" data-ng-click="LoadAjaxContent('detectors.html','Detectores')"><i class="fa fa-th-large"></i> Detectores</a></li>
								<li class="active"><a href="#" data-ng-click="LoadAjaxContent('generic.html','Genericos')"><i class="fa fa-simplybuilt"></i> Genericos</a></li>
			                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('transmitters.html','Transmissores')"><i class="fa fa-expand"></i> Transmissores</a></li>
			                    <li class="active"><a href="#" data-ng-click="LoadAjaxContent('sensors.html','Sensores')"><i class="fa fa-feed"></i> Sensores</a></li>			                    
								<li class="active"><a href="#" data-ng-click="LoadAjaxContent('gases.html', 'Gases')"><i class="fa fa-yelp"></i> Gases</a></li>
								<li class="active"><a href="#" data-ng-click="LoadAjaxContent('deviceType.html', 'Dipositivos')"><i class="fa fa-microchip"></i> Dispositivos</a></li>
								<li class="active"><a href="#" data-ng-click="LoadAjaxContent('unitMeter.html', 'Unidades')"><i class="fa fa-balance-scale"></i> Unidades</a></li>
								<li class="active"><a href="#" data-ng-click="LoadAjaxContent('perfilAlarm.html', 'Perfis-Alarme')"><i class="fas fa-walking"></i> Perfis de Alarme</a></li>
			              	</ul>
			            </li> 
			            <li><a href="#" data-ng-click="LoadAjaxContent('alarms.html', 'Alarmes')"><i class="fa fa-bullhorn text-red"></i> Alarmes</a></li>
			            <li><a href="#" data-ng-click="LoadAjaxContent('companyDetectorMaintenance.html', 'Detectores-Calibra&ccedil;&atilde;o')"><i class="fa fa-retweet text-muted"></i> Manuten&ccedil;&atilde;o</a></li>                		
		     		</ul>
            	</li>
	            
	        </ul>
        </section>        
	</aside>