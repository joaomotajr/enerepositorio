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
   
    <style>
    .todo-list>li {
	    padding: 4px ! important;
	    color: gainsboro;
	}
	
	.passAlert {
		padding: 6px ! important;
		margin-top: 3px ! important;
	}
	
	</style>
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
		            		<input type="password" class="form-control" placeholder="Senha" data-ng-model="forms.signin.credential" 
		            			data-ng-keyup="$event.keyCode == 13 ? signin() : null">
		            		<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		          		</div>
		          
		          		<div id="signin-error" class="alert alert-danger" style="display:none">
							{{errorMessage}}
				  		</div>
				  		
			  			<div class="alert alert-warning" id="signinAlert" data-ng-show="signinAlert" style="display:none;">					        
							<small>Prezado(a) <strong>{{forms.signexpired.username}}</strong>, sua senha expirou. Clique </small>
							<a class="text-muted" href="#" data-ng-click="showNewPass=true; signinAlert = false; forms.signexpired.password=forms.signin.credential; focusNewPass=true" >aqui</a><small> para troc&aacute;-la.</small>					        
						</div>
						
						<div id="password-success" class="alert alert-success" style="display:none">
							{{successMessage}}
						</div>
				  
		          		<div class="row">
		            		<div class="col-xs-8">
		            		</div>	            
		            		<div class="col-xs-4">
		              			<button type="button" data-ng-click="signin()" class="btn btn-primary btn-block btn-flat" data-ng-disabled="showNewPass"		              			
		              			data-ng-disabled="(forms.signin.login == '' || forms.signin.credential == '') ? true : false">Entrar</button>
		            		</div>	            
		          		</div>
		       		</form>
		       		
		       		<form class="form-signin" id="formSignin" data-ng-show="showNewPass" name="signinForm" style="display:none;">
		       		
						<p class="text-muted text-center btn-block btn btn-primary btn-rect">Digite uma nova senha e confirme</p>
						<input type="password" placeholder="senha atual" title="Senha Informada pelo Administrador" 
							class="form-control" data-ng-model="forms.signexpired.password" name="password" required/>
							
						<input type="password" placeholder="nova senha" class="form-control" data-ng-model="forms.signexpired.newPassword" data-focus-me="focusNewPass"
							data-ng-minlength="8" data-ng-maxlength="20" data-ng-pattern="/(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z])/" name="password1" required />
							
						<input type="password" placeholder="confirme a nova senha" class="form-control" data-ng-model="forms.signexpired.confirm" 
							data-valid-Second-Password='{{forms.signexpired.newPassword}}' name="password2" required/>
										
						<div class="alert alert-danger passAlert" data-ng-if="signinForm.password.$error.required && signinForm.password.$dirty">
							<i class="fa fa-ban"></i><span> Digite Senha Atual!</span>
						</div>
						<div class="alert alert-danger passAlert" data-ng-show="!signinForm.password.$error.required && signinForm.password1.$error.required && signinForm.password1.$dirty">
							<i class="fa fa-ban"></i><span> Digite a Nova Senha!</span>
						</div>
						<div class="alert alert-danger passAlert" 
							data-ng-show="!signinForm.password1.$error.required && (signinForm.password1.$error.minlength || signinForm.password1.$error.maxlength) && signinForm.password1.$dirty">																
							<i class="fa fa-ban"></i><span > Senha precisar ter entre 8 and 20 digitos!</span>
						</div>
						<div class="alert alert-danger passAlert" 
							data-ng-show="!signinForm.password1.$error.required && !signinForm.password1.$error.minlength && !signinForm.password1.$error.maxlength && signinForm.password1.$error.pattern && signinForm.password1.$dirty">
							<i class="fa fa-ban"></i><span> Precisa Uma letra Maiúscula &amp; Uma Minúscula, e Um Número ou Simbolo!</span>
						</div>
						<div class="alert alert-danger passAlert" data-ng-show="!signinForm.password1.$error.required && signinForm.password2.$error.required && signinForm.password2.$dirty"> 	
							<i class="fa fa-ban"></i><span> Confirme sua Senha!</span>
						</div>
						<div class="alert alert-danger passAlert" data-ng-show="!signinForm.password2.$error.required && signinForm.password2.$error.noMatch && signinForm.password1.$dirty"> 	
							<i class="fa fa-ban"></i><span> Senhas Não Conferem!</span>
						</div>
						<br>						
						
						<div class="row">
		            		<div class="col-xs-8">
		            		</div>	            
		            		<div class="col-xs-4">		              			            		
		            		<button data-ng-click="changePasswordExpired()" class="btn btn-default btn-block btn-flat" 
		            		 	data-ng-disabled="!signinForm.$valid">Salvar
		            		</button>	          
		            		</div>  
		          		</div>
						
					</form>		       		

				</div><!-- /.login-box-body -->
			</div>		
							
			
	    </div><!-- /.login-box -->

   
   		<script src="/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>    
    	<script src="/assets/bootstrap/js/bootstrap.min.js"></script>    	
    	<script src="/assets/js/login.js"></script>
    	
	</body>
    <!-- END BODY -->
</html>