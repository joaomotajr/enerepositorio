<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

	<!-- BEGIN HEAD -->
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <title>E-Gas</title>
	    
	    <link rel="shortcut icon" href="/assets/img/favicon.ico" type="image/x-icon" />
	    	      
	    <style>	    
		    .dj-hide {
			    display: none;
			}	    
	    </style>
	    
	    <!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		
		<!-- Theme style -->
	    <link rel="stylesheet" href="/assets/css/AdminLTE.min.css">
	     
	    <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
	    <link rel="stylesheet" href="/assets/css/skins/_all-skins.min.css">
	    	    	    
	    <!-- Bootstrap 3.3.5 -->
	    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
	    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap-treeview.css">
	    	    
		<link rel="stylesheet" href="/assets/plugins/select2/select2.min.css">
		<link rel="stylesheet" href="/assets/css/checkbox3.min.css">
		
		<!-- jQuery Datatbles -->
		<link rel="stylesheet" href="/assets/plugins/datatables/jquery.dataTables.css">
		<link rel="stylesheet" href="/assets/plugins/datatables/select.dataTables.min.css">
			   
	    <!-- Font Awesome -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">	    
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/v4-shims.css">
	    	    
	    <!-- Date Picker -->
	    <link rel="stylesheet" href="/assets/plugins/datepicker/datepicker.css">
	    	   		   	
		<link rel="stylesheet" href="/assets/plugins/jQueryUI/jquery-ui.min.css"></link>

		<!-- Escpeificos -->
		<link rel="stylesheet" href="/assets/css/custom.css">		
		<link rel="stylesheet" href="/assets/css/eneeyes.css">
	     
	</head>

	<!-- HEADER SECTION -->
	<header class="main-header">
		
		<a href="#" class="logo" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')">
			<span class="logo-mini"><b>ENE</b></span>			
			<span class="logo-lg" style="color:#ECF0F1 !important; font-size: 30px !important; font-weight: 700 !important;"></i> ENESENS 		
			<sub style="font-size:0.6em">&copy;</sub></span>			
		</a>

		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top" role="navigation">
	
			<!-- Sidebar toggle button-->		
			<a data-ng-controller="SiteController" href="#" class="sidebar-toggle" data-toggle="offcanvas" data-ng-click="showLogo();" role="button"> 
				<span class="sr-only">Navega��o</span>
			</a>
			
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
	
					<!-- Messages: style can be found in dropdown.less-->
					<li class="dropdown messages-menu">
					
					<a href="#"	class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-envelope-o"></i> <span class="label label-success"></span>	</a>
						<ul class="dropdown-menu">
							<li class="header">Voce tem 0 Mensagem</li>
							<li>								
								<ul class="menu">
									<li> <!-- start message -->	</li>	
								</ul>
							</li>
							<li class="footer"><a href="#">Ver Todas Mensagens</a></li>
						</ul>
					</li>						
					
					<!-- Notifications: style can be found in dropdown.less -->
					<li class="dropdown notifications-menu" data-ng-controller="monitorController">
						<audio id="alarmSound" src="/assets/img/alert_01.mp3" preload="auto"></audio>
						<a href="#"	class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-bell-o"></i> <span class="label label-warning" data-ng-bind="dashCompaniesAlarmResults.length"></span></a>						
						<ul class="dropdown-menu">
							<li class="header text-center">Voc� tem <b data-ng-bind="dashCompaniesAlarm.length"></b> Alarmes Pendentes</li>
							<hr style="margin-top: 2px !important; margin-bottom: 2px !important;">
							<li>
								<div style="max-height: 250px; height:auto; overflow: auto">
									<ul class="menu">
										<li data-ng-repeat="item in dashCompaniesAlarmResults = (dashCompaniesAlarm | filter: {alarmStatus: 'CREATED' })">
											<a href="#"> 
											<i class="fa fa-bullhorn" data-ng-class="{
												'text-black':item.alarmType=='OFFLINE', 
												'text-green':item.alarmType=='NORMAL', 
												'text-orange':item.alarmType=='ALERTA', 
												'text-gray':item.alarmType=='DETECCAO', 
												'text-red':item.alarmType=='EVACUACAO'
												}"></i>
												{{item.company_name}} {{item.company_detector_name}} {{item.last_update_full | date:'dd/MM/yyyy HH:mm'}}	
											</a>
										</li>										
									</ul>
								</div>
							</li>
							<hr style="margin-top: 2px !important; margin-bottom: 2px !important;">
							<li class="footer text-center">
								<a href="#" data-ng-click="LoadAjaxContent('monitor.html', 'Monitoramento')">Ver todos</a>
							</li>
						</ul>
						
					</li>					
					
					<li class="dropdown tasks-menu">
						<a href="#"	class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-flag-o"></i> <span class="label label-danger"></span></a>
						<ul class="dropdown-menu">
							<li class="header">Sem Tarefas Pendentes</li>
							<li>								
								<ul class="menu">
									<li>  <!-- Task item  -->	</li>									
								</ul>
							</li>
							<li class="footer"><a href="#">Ver todas as Tarefas</a></li>
						</ul>
					</li>
	
					<!-- User Account: style can be found in dropdown.less -->
					<li class="dropdown user user-menu">
						<a href="#"	class="dropdown-toggle" data-toggle="dropdown" data-ng-controller="SiteController"> 
							<img data-ng-src="{{userImage}}" class="user-image" alt="User Image"> 
							<span class="hidden-xs"><sec:authentication property="principal.displayName" /></span>
						</a>
						
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header"><img data-ng-src="{{userImage}}" class="img-circle"	alt="User Image" data-ng-controller="SiteController">
								<p><sec:authentication property="principal.displayName" /> - Administrador do Site <small>Desde Nov. 2016</small></p>
							</li>
	
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="pull-left">																	
 									<a href="#" type="button" class="btn btn-default btn-flat" data-ng-click="LoadAjaxContent('userPerfil.html', 'Perfil')">Perfil</a>
								</div>
								<div class="pull-right">
									<a href="/logout" class="btn btn-default btn-flat">Sair</a>
								</div>
							</li>
						</ul>
					</li>
	
					<!-- Control Sidebar Toggle Button -->
 					<li title="Configura&ccedil;&atilde;es"><a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a></li>
				</ul>
			</div>
		</nav>
		
	</header>

	<jsp:include page="configuracoes.jsp"/>

    <div class="control-sidebar-bg"></div>	
	
</html>	