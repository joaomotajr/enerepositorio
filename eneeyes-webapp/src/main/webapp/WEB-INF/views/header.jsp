<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN HEAD -->
<head>
    <meta charset="UTF-8" />
    <title>Concilie F&aacute;cil | Dashboard </title>
    <link rel="shortcut icon" href="/assets/img/favicon.ico">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
     <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <!-- GLOBAL STYLES -->
    <link rel="stylesheet" href="/assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="/assets/css/main.css" />
    <link rel="stylesheet" href="/assets/css/theme.css" />
    <link rel="stylesheet" href="/assets/css/MoneAdmin.css" />
    <link rel="stylesheet" href="/assets/plugins/Font-Awesome/css/font-awesome.css" />
    <link rel="stylesheet" href="/assets/plugins/datepicker/css/datepicker.css" />
    <link rel="stylesheet" href="/assets/css/bootstrap-fileupload.min.css" />
    <link rel="stylesheet" href="/assets/plugins/daterangepicker/daterangepicker-bs3.css" />
    <link rel="stylesheet" href="/assets/plugins/datepicker/css/datepicker.css" />
    <link href="/assets/css/4m.css" rel="stylesheet">
    
	<link href="/assets/css/jquery-ui.css" rel="stylesheet" />
	<link rel="stylesheet" href="/assets/plugins/uniform/themes/default/css/uniform.default.css" />
	<link rel="stylesheet" href="/assets/plugins/inputlimiter/jquery.inputlimiter.1.0.css" />
	<link rel="stylesheet" href="/assets/plugins/chosen/chosen.min.css" />
	<link rel="stylesheet" href="/assets/plugins/tagsinput/jquery.tagsinput.css" />
	<link rel="stylesheet" href="/assets/plugins/timepicker/css/bootstrap-timepicker.min.css" />
	<link rel="stylesheet" href="/assets/plugins/switch/static/stylesheets/bootstrap-switch.css" />
	<link rel="stylesheet" href="/assets/plugins/colorpicker/css/colorpicker.css" />
	<link rel="stylesheet" href="/assets/plugins/timepicker/css/bootstrap-timepicker.min.css" />
	<link rel="stylesheet" href="/assets/plugins/dataTables/dataTables.bootstrap.css" />
	<link rel="stylesheet" href="/assets/plugins/gritter/css/jquery.gritter.css" />
	<link href="/assets/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
	<link href="/assets/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" />
    <!--END GLOBAL STYLES -->

    <!-- PAGE LEVEL STYLES -->
    <link href="/assets/css/layout2.css" rel="stylesheet" />
       
       <link rel="stylesheet" href="/assets/plugins/timeline/timeline.css" />
    <!-- END PAGE LEVEL  STYLES -->
     <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<!-- HEADER SECTION -->
        <div id="top">

            <nav class="navbar navbar-inverse navbar-fixed-top " style="padding-top: 10px;">
                <a data-original-title="Show/Hide Menu" data-placement="bottom" data-tooltip="tooltip" class="accordion-toggle btn btn-primary btn-sm visible-xs" data-toggle="collapse" href="#menu" id="menu-toggle">
                    <i class="icon-align-justify"></i>
                </a>
                <!-- LOGO SECTION -->
                <header class="navbar-header">

                    <a href="#" class="navbar-brand">
                    <img src="/assets/img/logo-conciliefacil.png" alt="" />
                        
                        </a>
                </header>
                <!-- END LOGO SECTION -->
                <ul class="nav navbar-top-links navbar-right">

                    <!-- MESSAGES SECTION -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="label label-success">1</span>   <i class="icon-envelope-alt"></i>&nbsp; <i class="icon-chevron-down"></i>
                        </a>
                        
                        <ul class="dropdown-menu dropdown-messages">
                            <li>
                                <a href="#" ng-click="LoadAjaxContent('integracao-tef.html')">
                                    <div>
                                       <strong>Integra&ccedil;&atilde;o TEF</strong>
                                        <span class="pull-right text-muted">
                                            <em>{{yesterday | date : "dd/MM/yyyy"}}</em>
                                        </span>
                                    </div>
                                    <div>
                                    	N&atilde;o foi efetuado a integra&ccedil;&atilde;o do TEF. 
                                    	<span class="label label-danger">Importante</span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!--END MESSAGES SECTION -->

                    <!--TASK SECTION -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="label label-danger">5</span>   <i class="icon-tasks"></i>&nbsp; <i class="icon-chevron-down"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-tasks">
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong> Profile </strong>
                                            <span class="pull-right text-muted">40% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                                <span class="sr-only">40% Complete (success)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong> Pending Tasks </strong>
                                            <span class="pull-right text-muted">20% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                                <span class="sr-only">20% Complete</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong> Work Completed </strong>
                                            <span class="pull-right text-muted">60% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                                <span class="sr-only">60% Complete (warning)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong> Summary </strong>
                                            <span class="pull-right text-muted">80% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                                <span class="sr-only">80% Complete (danger)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Tasks</strong>
                                    <i class="icon-angle-right"></i>
                                </a>
                            </li>
                        </ul>

                    </li>
                    <!--END TASK SECTION -->

                    <!--ALERTS SECTION -->
                    <li class="chat-panel dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="label label-info">8</span>   <i class="icon-comments"></i>&nbsp; <i class="icon-chevron-down"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-alerts">

                            <li>
                                <a href="#">
                                    <div>
                                        <i class="icon-comment" ></i> New Comment
                                    <span class="pull-right text-muted small"> 4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="icon-twitter info"></i> 3 New Follower
                                    <span class="pull-right text-muted small"> 9 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="icon-envelope"></i> Message Sent
                                    <span class="pull-right text-muted small" > 20 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="icon-tasks"></i> New Task
                                    <span class="pull-right text-muted small"> 1 Hour ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="icon-upload"></i> Server Rebooted
                                    <span class="pull-right text-muted small"> 2 Hour ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Alerts</strong>
                                    <i class="icon-angle-right"></i>
                                </a>
                            </li>
                        </ul>

                    </li>
                    <!-- END ALERTS SECTION -->

                    <!--ADMIN SETTINGS SECTIONS -->

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-user "></i>&nbsp; <i class="icon-chevron-down "></i>
                        </a>

                        <ul class="dropdown-menu dropdown-user" ng-controller="UserTypeController">
                            <li><a href="#"><i class="icon-user"></i> Perfil </a></li>
                            <li><a href="#" ng-if="userLogado.isContractor" ng-click="showMenuUser(true, 'user-filial-pesquisa.html')"><i class="icon-gear"></i> Gerenciar Usu&aacute;rios </a></li>
                            <li class="divider"></li>
                            <li><a href="/logout"><i class="icon-signout"></i> Sair </a></li>
                        </ul>

                    </li>
                    <!--END ADMIN SETTINGS -->
                </ul>

            </nav>

        </div>
        <!-- END HEADER SECTION -->