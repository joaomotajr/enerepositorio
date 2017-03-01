<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- BEGIN HEAD -->
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <title>Eneeyes</title>
	    <link rel="shortcut icon" href="/assets/img/favicon.ico">
	    
	    <style>
	    
	    .dj-hide {
		    display: none;
		}
	    
	    </style>
	    
	    <!-- Tell the browser to be responsive to screen width -->
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	    	    	    
	    <!-- Bootstrap 3.3.5 -->
	    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
	    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap-treeview.css">	    
	    <link rel="stylesheet" href="/assets/plugins/bootstrap-select/bootstrap-select.min.css">	        
	    	    
		<link rel="stylesheet" href="/assets/plugins/select2/select2.min.css">
		
		<!-- jQuery Datatbles -->
		<link rel="stylesheet" href="/assets/plugins/datatables/jquery.dataTables.css">
			   
	    <!-- Font Awesome -->
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	    
	    <!-- Ionicons 
	    <link rel="stylesheet" href="/assets/css/ionicons/ionicons.min.css"> -->
	    
	    <!-- iCheck for checkboxes and radio inputs  -->
    	<link rel="stylesheet" href="/assets/plugins/iCheck/all.css"> 
	    	    
	    <!-- Theme style -->
	    <link rel="stylesheet" href="/assets/css/AdminLTE.min.css">
	     
	    <!-- AdminLTE Skins. Choose a skin from the css/skins
	         folder instead of downloading all of them to reduce the load. -->
	    <link rel="stylesheet" href="/assets/css/skins/_all-skins.min.css">
	    
	    <!-- Date Picker -->
	    <link rel="stylesheet" href="/assets/plugins/datepicker/datepicker.css">
	    <!-- Daterange picker -->
	    <link rel="stylesheet" href="/assets/plugins/daterangepicker/daterangepicker-bs3.css">
	   	
	   	<!-- Image Notes -->
		<style type="text/css" media="all">@import "/assets/plugins/imageNotes/css/marker.css";</style>		
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" media="screen">

		<link rel="stylesheet" href="/assets/css/custom.css">
			    	    
	    <style>
	    
	     	body.loading {
				overflow: hidden;
			}
			
			body.loading .ajax-loader {
				display: block;
			}  
			
			.ajax-loader {
				display: none;
				position: fixed;
				z-index: 2000;
				top: 0;
				left: 0;
				height: 100%;
				width: 100%; 	
				background: rgba(255, 255, 255, .5) url('/assets/img/loader.gif') 50% 50% no-repeat;
			}
			  
	    </style>
	    
	</head>

	<!-- HEADER SECTION -->

<header class="main-header">

	<!-- Logo -->
	<a href="#" class="logo" data-ng-click="LoadAjaxContent('dashboard.html', 'Dashboard')"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>ENE</b></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>Ene</b>EYES</span>
	</a>

	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" role="navigation">

		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<!-- Messages: style can be found in dropdown.less-->
				<li class="dropdown messages-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fa fa-envelope-o"></i> <span class="label label-success">1</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">Voce tem 1 Mensagem</li>
						<li>
							<!-- inner menu: contains the actual data -->
							<ul class="menu">
								<li>
									<!-- start message --> <a href="#">
										<div class="pull-left">
											<img src="/assets/img/avatar_160x160.png" class="img-circle"
												alt="User Image">
										</div>
										<h4>
											Time de Desenvolvimento <small><i class="fa fa-clock-o"></i> 5
												mins</small>
										</h4>
										<p>Bem Vindo ao nosso Site!!</p>
								</a>
								</li>
								<!-- end message -->
<!-- 								<li><a href="#"> -->
<!-- 										<div class="pull-left"> -->
<!-- 											<img src="/assets/img/user3-128x128.jpg" class="img-circle" -->
<!-- 												alt="User Image"> -->
<!-- 										</div> -->
<!-- 										<h4> -->
<!-- 											AdminLTE Design Team <small><i class="fa fa-clock-o"></i> -->
<!-- 												2 hours</small> -->
<!-- 										</h4> -->
<!-- 										<p>Why not buy a new awesome theme?</p> -->
<!-- 								</a></li> -->
<!-- 								<li><a href="#"> -->
<!-- 										<div class="pull-left"> -->
<!-- 											<img src="/assets/img/user4-128x128.jpg" class="img-circle" -->
<!-- 												alt="User Image"> -->
<!-- 										</div> -->
<!-- 										<h4> -->
<!-- 											Developers <small><i class="fa fa-clock-o"></i> Today</small> -->
<!-- 										</h4> -->
<!-- 										<p>Why not buy a new awesome theme?</p> -->
<!-- 								</a></li> -->
<!-- 								<li><a href="#"> -->
<!-- 										<div class="pull-left"> -->
<!-- 											<img src="/assets/img/user3-128x128.jpg" class="img-circle" -->
<!-- 												alt="User Image"> -->
<!-- 										</div> -->
<!-- 										<h4> -->
<!-- 											Sales Department <small><i class="fa fa-clock-o"></i> -->
<!-- 												Yesterday</small> -->
<!-- 										</h4> -->
<!-- 										<p>Why not buy a new awesome theme?</p> -->
<!-- 								</a></li> -->
<!-- 								<li><a href="#"> -->
<!-- 										<div class="pull-left"> -->
<!-- 											<img src="/assets/img/user4-128x128.jpg" class="img-circle" -->
<!-- 												alt="User Image"> -->
<!-- 										</div> -->
<!-- 										<h4> -->
<!-- 											Reviewers <small><i class="fa fa-clock-o"></i> 2 days</small> -->
<!-- 										</h4> -->
<!-- 										<p>Why not buy a new awesome theme?</p> -->
<!-- 								</a></li> -->
							</ul>
						</li>
						<li class="footer"><a href="#">Ver Todas Mensagens</a></li>
					</ul></li>
					

				<!-- Notifications: style can be found in dropdown.less -->
				<li class="dropdown notifications-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fa fa-bell-o"></i> <span class="label label-warning">2</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">Voce tem 2 Alarmes Pendentes</li>
						<li>
							<!-- inner menu: contains the actual data -->
							<ul class="menu">
								
								<li><a href="#"> <i class="fa fa-warning text-yellow"></i>
										Alarme Um
								</a></li>
								<li><a href="#"> <i class="fa fa-warning text-red"></i>
										Alarme Dois
								</a></li>
								
							</ul>
						</li>
						<li class="footer"><a href="#">Ver todos</a></li>
					</ul></li>

				<!-- Tasks: style can be found in dropdown.less -->
				<li class="dropdown tasks-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fa fa-flag-o"></i> <span class="label label-danger">1</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">Voce Tem um Tarefa</li>
						<li>
							<!-- inner menu: contains the actual data -->
							<ul class="menu">
								<li>
									<!-- Task item --> <a href="#">
										<h3>
											Ler manual do aplicativo <small class="pull-right">20%</small>
										</h3>
										<div class="progress xs">
											<div class="progress-bar progress-bar-aqua"
												style="width: 20%" role="progressbar" aria-valuenow="20"
												aria-valuemin="0" aria-valuemax="100">
												<span class="sr-only">20% Completo</span>
											</div>
										</div>
								</a>
								</li>
																
								<!-- end task item -->
							</ul>
						</li>
						<li class="footer"><a href="#">Ver todas as Tarefas</a></li>
					</ul></li>

				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="/assets/img/avatar_128x128.png" class="user-image"
						alt="User Image"> <span class="hidden-xs"><sec:authentication
								property="principal.displayName" /></span>
				</a>
				<ul class="dropdown-menu">

						<!-- User image -->
						<li class="user-header"><img
							src="/assets/img/avatar_128x128.png" class="img-circle"
							alt="User Image">
							<p>
								<sec:authentication property="principal.displayName" />
								- Administrador do Site <small>Desde Nov. 2016</small>
							</p></li>

						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="#" class="btn btn-default btn-flat">Perfil</a>
							</div>
							<div class="pull-right">
								<a href="/logout" class="btn btn-default btn-flat">Sair</a>
							</div>
						</li>
					</ul></li>

				<!-- Control Sidebar Toggle Button -->
				<li title="Configurações"><a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a></li>
			</ul>
		</div>
	</nav>
</header>

<!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>
<!-- END HEADER SECTION -->