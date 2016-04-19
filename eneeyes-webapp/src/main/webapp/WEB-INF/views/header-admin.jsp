<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- BEGIN HEAD -->
<head>
    <meta charset="UTF-8" />
    <title>Concilie F&aacute;cil | Manager Module </title>
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
                    	<p style="padding-top: 10px;"><marquee behavior=scroll><i>Manager Module</i></marquee></p>
		      		</a>
                </header>
                <!-- END LOGO SECTION -->
                <ul class="nav navbar-top-links navbar-right">

                    <!--ADMIN SETTINGS SECTIONS -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-user "></i>&nbsp; <i class="icon-chevron-down "></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="/logout"><i class="icon-signout"></i> Sair </a></li>
                        </ul>

                    </li>
                    <!--END ADMIN SETTINGS -->
                </ul>

            </nav>

        </div>
        <!-- END HEADER SECTION -->