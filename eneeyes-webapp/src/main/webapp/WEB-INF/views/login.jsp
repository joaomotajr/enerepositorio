<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="loginPage">
    <title>E-Gas | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    
    <!-- Theme style -->
    <link rel="stylesheet" href="/assets/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/assets/plugins/iCheck/square/blue.css">

  </head>
    
    <!-- BEGIN BODY -->	    
    <body class="hold-transition login-page">
	    <div class="login-box">
			    
			<div class="login-logo">
		 		<a href="#"><b>E</b>Gas</a>
			</div>	      	
	      	
	      	<div id="login" class="tab-pane active">
		      	<div class="login-box-body">
		        	<p class="login-box-msg">Digite suas Credencias</p>
		        	<form>
		          
		          		<div class="form-group has-feedback">
		            		<input type="text" class="form-control" placeholder="Email/Usuário" data-ng-model="forms.signin.login" data-ng-keyup="$event.keyCode == 13 ? signin() : null">
		            		<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
		          		</div>
		          
		          		<div class="form-group has-feedback">
		            		<input type="password" class="form-control" placeholder="Senha" data-ng-model="forms.signin.credential" data-ng-keyup="$event.keyCode == 13 ? signin() : null">
		            		<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		          		</div>
		          
		          		<div id="signin-error" class="alert alert-danger" style="display:none">
							{{errorMessage | translate}}
				  		</div>
				  		
			  			<div id="signin-alert" class="alert alert-warning" style="display:none">
					        <ul class="list-inline">
					            <li><small>Prezado(a) <strong>{{forms.signexpired.username}}</strong>, sua senha expirou. Clique </small>
					            <a class="text-muted" href="#changePassword" data-toggle="tab">aqui</a><small> para troc&aacute;-la.</small></li>
					        </ul>
						</div>
				  
		          		<div class="row">
		            		<div class="col-xs-8">
		            		</div>	            
		            		<div class="col-xs-4">
		              			<button type="button" data-ng-click="signin()" class="btn btn-primary btn-block btn-flat"		              			
		              			data-ng-disabled="(forms.signin.login == '' || forms.signin.credential == '') ? true : false">Entrar</button>
		            		</div>	            
		          		</div>
		       		</form>
		       		
		       		<div id="changePassword" class="tab-pane" style="display:none">
		                <form class="form-signin">
		                	<div id="password-success" class="alert alert-success" style="display:none">
								{{passwordSuccessMessage | translate}}
							</div>
			                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Digite uma nova senha e confirme</p>
			                <input type="password" placeholder="senha atual" class="form-control" data-ng-model="forms.signexpired.password"/>
			                <input type="password" placeholder="nova senha" class="form-control" data-ng-model="forms.signexpired.newPassword"/>
			                <input type="password" placeholder="confirme a nova senha" class="form-control" data-ng-model="forms.signexpired.confirm"/>
			                <div class="text-muted alert"><sup>* Aten&ccedil;&atilde;o: N&atilde;o repetir as tr&ecirc;s ultimas senhas</sup></div>	
			                <div id="password-error" class="alert alert-danger" style="display:none">
								{{passwordErrorMessage | translate}}
							</div>
			                <button class="btn text-muted text-center btn-success" data-ng-click="changePasswordExpired()">Salvar</button>
			            </form>
			        </div>

				</div><!-- /.login-box-body -->
			</div>			
			
	    </div><!-- /.login-box -->

   
   		<script src="/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>    
    	<script src="/assets/bootstrap/js/bootstrap.min.js"></script>    	
    	<script src="/assets/plugins/iCheck/icheck.min.js"></script>
    	<script src="/assets/js/login.js"></script>
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