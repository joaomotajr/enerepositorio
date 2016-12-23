<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->
<head>
     <meta charset="UTF-8" />
    <title>Eneeyes | Log in</title>
    <link rel="shortcut icon" href="/assets/img/favicon.ico" />    
            
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
     <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <!-- GLOBAL STYLES -->
    
     <!-- PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/login.css" />
    <link rel="stylesheet" href="/assets/plugins/magic/magic.css" />
    <link href="/assets/css/4m.css" rel="stylesheet">    
     <!-- END PAGE LEVEL STYLES -->
     
     <!-- Theme style -->
    <link rel="stylesheet" href="/assets/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/assets/plugins/iCheck/square/blue.css">
     
   <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->

<body style="background-size: cover">

   <!-- PAGE CONTENT --> 
    <div class="container">
    	<div class="login-logo">
		 		<a href="#"><b>Ene</b>eyes</a>
		</div>
	    <div class="tab-content">
	    
	        <div id="login" class="tab-pane active">
	            <form class="form-signin">
	                <p class="text-muted text-center btn-block btn btn-primary btn-rect">
	                    Informe seu usu&aacute;rio e senha
	                </p>
	                <input type="text" placeholder="Entre com seu usu&aacute;rio" class="form-control" ng-model="forms.signin.login"/>
	                <input type="password" placeholder="Entre com sua senha" class="form-control" ng-model="forms.signin.credential"/>
	                <div id="signin-error" class="alert alert-danger" style="display:none">
						{{errorMessage | translate}}
					</div>
					<div id="signin-alert" class="alert alert-warning" style="display:none">
				        <ul class="list-inline">
				            <li><small>Prezado(a) <strong>{{forms.signexpired.username}}</strong>, sua senha expirou. Clique </small>
				            <a class="text-muted" href="#changePassword" data-toggle="tab">aqui</a><small> para troc&aacute;-la.</small></li>
				        </ul>
					</div>
	                <button class="btn text-muted text-center btn-success" ng-click="signin()">Entrar</button>
	            </form>
	        </div>
	        
	        <div id="forgot" class="tab-pane">
	            <form class="form-signin">
	                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Entre com um e-mail v&aacute;lido</p>
	                <input type="email"  required="required" placeholder="Entre com seu e-mail"  class="form-control" />
	                <br />
	                <button class="btn text-muted text-center btn-success">Recupere sua senha</button>
	            </form>
	        </div>
	        
	        <div id="changePassword" class="tab-pane">
                <form class="form-signin">
                	<div id="password-success" class="alert alert-success" style="display:none">
						{{passwordSuccessMessage | translate}}
					</div>
	                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Digite uma nova senha e confirme</p>
	                <input type="password" placeholder="senha atual" class="form-control" ng-model="forms.signexpired.password"/>
	                <input type="password" placeholder="nova senha" class="form-control" ng-model="forms.signexpired.newPassword"/>
	                <input type="password" placeholder="confirme a nova senha" class="form-control" ng-model="forms.signexpired.confirm"/>
	                <div class="text-muted alert"><sup>* Aten&ccedil;&atilde;o: N&atilde;o repetir as tr&ecirc;s ultimas senhas</sup></div>	
	                <div id="password-error" class="alert alert-danger" style="display:none">
						{{passwordErrorMessage | translate}}
					</div>
	                <button class="btn text-muted text-center btn-success" ng-click="changePasswordExpired()">Salvar</button>
	            </form>
	        </div>
	         
	        <div id="signup" class="tab-pane">
	            <form class="form-signin">
	                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Please Fill Details To Register</p>
	                 <input type="text" placeholder="First Name" class="form-control" />
	                 <input type="text" placeholder="Last Name" class="form-control" />
	                <input type="text" placeholder="Username" class="form-control" />
	                <input type="email" placeholder="Your E-mail" class="form-control" />
	                <input type="password" placeholder="password" class="form-control" />
	                <input type="password" placeholder="Re type password" class="form-control" />
	                <button class="btn text-muted text-center btn-success">Registre-se</button>
	            </form>
	        </div>
	        
	    </div>
	    <div class="text-center">
	        <ul class="list-inline">
	            <li><a class="text-muted" href="#login" data-toggle="tab">Entrar</a></li>
	            <li><a class="text-muted" href="#forgot" data-toggle="tab">Esqueceu sua senha</a></li>
	            <li><a class="text-muted" href="#signup" data-toggle="tab">Cadastre-se</a></li>
	            <li><a class="text-muted" href="#changePassword" data-toggle="tab">Trocar Senha</a></li>
	        </ul>
	    </div>
	</div>

	  <!--END PAGE CONTENT -->     
	      
      <!-- PAGE LEVEL SCRIPTS -->
      
      <script src="/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
      <script src="/assets/bootstrap/js/bootstrap.min.js"></script>     
      
   <script src="/assets/js/login.js"></script>
      <!--END PAGE LEVEL SCRIPTS -->

</body>
    <!-- END BODY -->
</html>