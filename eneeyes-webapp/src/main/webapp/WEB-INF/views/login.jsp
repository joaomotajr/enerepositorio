<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->


<head>
	<base href="eneeyes">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Eneeyes | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="/eneeyes/assets/bootstrap/css/bootstrap.min.css">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    
<!--     Ionicons -->
<!--     <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"> -->
    
    <!-- Theme style -->
    <link rel="stylesheet" href="/eneeyes/assets/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/eneeyes/assets/plugins/iCheck/square/blue.css">

  </head>
    
    <!-- BEGIN BODY -->	    
    <body class="hold-transition login-page">
	    <div class="login-box">
			    
			<div class="login-logo">
		 		<a href="#"><b>Ene</b>eyes</a>
			</div>
	      	
	      	
	      	<div id="login" class="tab-pane active">
		      	<div class="login-box-body">
		        	<p class="login-box-msg">Sign in to start your session</p>
		        	<form>
		          
		          		<div class="form-group has-feedback">
		            		<input type="email" class="form-control" placeholder="Email" ng-model="forms.signin.login">
		            		<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
		          		</div>
		          
		          		<div class="form-group has-feedback">
		            		<input type="password" class="form-control" placeholder="Password" ng-model="forms.signin.credential">
		            		<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		          		</div>
		          
		          		<div id="signin-error" class="alert alert-danger" style="display:none">
							{{errorMessage | translate}}
				  		</div>
				  
		          		<div class="row">
		            		<div class="col-xs-8">
		              			<div class="checkbox icheck">
		                			<label>
		                  			<input type="checkbox"> Remember Me
		                			</label>
		              			</div>
		            		</div><!-- /.col -->	            
		            		<div class="col-xs-4">
		              			<button type="button" ng-click="signin()" class="btn btn-primary btn-block btn-flat">Sign In</button>
		            		</div><!-- /.col -->	            
		          		</div>
		       		</form>
		
		        	<a href="#forgot">Esqueci a Senha</a><br>
		        	<a href="#" class="text-center">Solicitar Cadastramento</a>
				</div><!-- /.login-box-body -->
			</div>			
			
	    </div><!-- /.login-box -->

   
   		<script src="/eneeyes/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>    
    	<script src="/eneeyes/assets/bootstrap/js/bootstrap.min.js"></script>    	
    	<script src="/eneeyes/assets/plugins/iCheck/icheck.min.js"></script>
    	<script src="/eneeyes/assets/js/login.js"></script>
    	<script>
		      $(function () {
		        $('input').iCheck({
		          checkboxClass: 'icheckbox_square-blue',
		          radioClass: 'iradio_square-blue',
		          increaseArea: '20%' // optional
		        });
		      });
		</script>
	</body>
    <!-- END BODY -->
</html>