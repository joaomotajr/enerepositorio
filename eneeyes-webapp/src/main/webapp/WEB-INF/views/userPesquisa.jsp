
	<div data-ng-controller="UserController as UserController">
		<div class="row">				                                                    
			<div class="col-md-10">                                                        
				<div class="box box-primary" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
					<div class="box-header">					  
					  <h3 class="box-title">Gerenciamento e Pesquisa de Usu&aacute;rios</h3>
					  <a href="#" class="text-muted pull-right" data-ng-click="refreshAlarms();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto;">
							<table class="table table-striped table-bordered table-hover">
		                        <thead>
									<tr>
										<th width="150px">CPF</th>
										<th width="200px">Nome</th>
										<th>Login</th>
										<th>Tipo</th>
										<th>Status</th>
										<th>Data de Cria&ccedil;&atilde;o</th>
									</tr>
		                        </thead>
								<tbody>
									<tr data-ng-repeat="user in users.listUser" data-ng-class="{'danger' : user.status == 'INACTIVE', 'success' : user.status == 'ACTIVE'}">
											   
										<td width="150px" align="center">{{cpfFormatter(user.cpf)}}</td>
										<td width="200px">{{user.displayName}}</td>									
										<td>{{user.login}}</td>
										<td align="center">{{user.roles[0].name}}</td>
										<td align="center">{{getStatus(user.status)}}</td>
										<td align="center">{{user.createDate | date: 'dd/MM/yyyy'}}</td>
										
										<td>
											<button type="button" class="btn btn-primary btn-xs" data-ng-click="detalheUser($index)" data-toggle="modal" data-target="#modalDetalheUser">editar</button>
										</td>
										<td>
											<button type="button" class="btn btn-danger btn-xs" data-ng-click="deleteAlarm($index)">excluir</button>
										</td>
										
									</tr>
								</tbody>
							</table>							
						
						</div>                                                       
					</div>
					
					<div class="box-footer">									
						<button type="button" data-ng-click="novoUsuario(); userForm.$setPristine()" class="btn btn-primary pull-right" data-toggle="modal" data-target="#modalCadastrarUser">Novo</button>
					</div>
				</div>
								
				<div id="resultErro" class="alert alert-warning" role="alert" data-ng-show="msgErro" >
            		<button type="button" class="close" ><span data-ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
			</div>                                                     
			
		</div>
		
		<div id="modalCadastrarUser" class="modal" tabindex="-1">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">                            
					<div class="modal-body"style="padding-bottom: 0px; !important">
					
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="modal-title" align="center"><b>Inclus&atilde;o de Usu&aacute;rio</b></h4>
							</div>														                                                                           
					  	</div>
											
						<div class="box box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
							<div class="box-header">
								<h3 class="box-title">Cadastro / Edição</h3>
								<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
							</div>					
							<div class="box-body" style="padding-bottom: 0px; !important">
								<jsp:include page="user-cadastro.jsp"/>
							</div>
						</div>
																
				  	</div>
				  	
			  	</div>
			</div>		
		</div>
		
		<!-- MODAL EDICAO USUARIO --> 
		<div id="modalDetalheUser" class="modal" tabindex="-1">
		    <div class="modal-dialog modal-lg" role="document">
		        <div class="modal-content">
		        
		        	<div class="modal-body"style="padding-bottom: 0px; !important">
					
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="modal-title" align="center"><b>Gerenciar Usu&aacute;rio: {{user.displayName}}</b></h4>
							</div>														                                                                           
					  	</div>
											
						<div class="box box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
							<div class="box-header">
								<h3 class="box-title">Cadastro / Edição</h3>
								<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
							</div>					
							<div class="box-body" style="padding-bottom: 0px; !important">
								<jsp:include page="user-cadastro.jsp"/>
							</div>
						</div>																
				  	</div>		            
		            
		        </div>
		    </div>
		</div>
			
		<!-- MODAL EXCLUSAO DE USUARIO -->
		<div id="modalExcluirUser" class="modal fade col-md-offset-3 col-md-6" style="margin-top: 130px;">
		    <div class="modal-dialog">
		        <div class="modal-content">
					<header class="modal-header">
		        		<button type="button" class="close" data-dismiss="modal">&times;</button>
		        		<h4 class="modal-title" align="center"><b>Exclus&atilde;o de Usu&aacute;rio</b></h4>
		            </header>          
		            <div class="modal-body" align="center">
						<p class="alert alert-danger">Voc&ecirc; tem certeza que deseja excluir o usu&aacute;rio "{{user.displayName | uppercase}}"?</p>
		            </div>
		             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
						<a class="btn btn-danger"  data-ng-click="fecharModalById('modalExcluirUser')"><i class="icon-thumbs-down icon-white"></i>&nbsp;N&atilde;o</a>
						<a class="btn btn-success" data-ng-click="excluirUsuario()"><i class="icon-thumbs-up icon-white"></i>&nbsp;Sim</a>
		            </div>
		        </div>
		    </div>
		</div>
		
		<!-- MODAL INATIVACAO DE USUARIO -->
		<div id="modalInativarUser" class="modal fade col-md-offset-3 col-md-6" style="margin-top: 130px;">
		    <div class="modal-dialog">
		        <div class="modal-content">
					<header class="modal-header">
		        		<button type="button" class="close" data-dismiss="modal">&times;</button>
		        		<h4 class="modal-title" align="center"><b>Inativa&ccedil;&atilde;o de Usu&aacute;rio</b></h4>
		            </header>          
		            <div class="modal-body" align="center">
						<p class="alert alert-danger"><strong>Aten&ccedil;&atilde;o</strong>: a inativa&ccedil;&atilde;o do usu&aacute;rio bloquear&aacute; o acesso total ao sistema. 
						Voc&ecirc; tem certeza que deseja inativar o usu&aacute;rio "{{user.displayName | uppercase}}"?</p>
		            </div>
		             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
						<a class="btn btn-danger"  data-ng-click="fecharModalById('modalInativarUser')"><i class="icon-thumbs-down icon-white"></i>&nbsp;N&atilde;o</a>
						<a class="btn btn-success" data-ng-click="inativarUser()"><i class="icon-thumbs-up icon-white"></i>&nbsp;Sim</a>
		            </div>
		        </div>
		    </div>
		</div>
		
		<!-- MODAL CHANGE PASSWORD-->
		<div class="modal fade bs-change-password-modal" id="modalSenha" tabindex="-1" role="dialog" aria-labelledby="changePasswordModal" aria-hidden="true">
	    	<div class="modal-dialog modal-sm">
	        	<div class="modal-content">
	            	<div class="modal-header">
	                	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                	<h4 class="modal-title" align="center">O usu&aacute;rio <sec:authentication property="principal.displayName" /> ir&aacute; alterar senha</h4>
	            	</div>
	            	
	            	<div class="modal-body">
	                	<div id="fld-inscricao" class="form-group">
	                    	
	                    	<div class="form-group">
	                        	<input type="password" class="form-control" placeholder="Informe uma nova senha" data-ng-model="forms.changePassword.pass1">
	                    	</div>	                    	
	                    	<div class="form-group">
	                        	<input type="password" class="form-control" placeholder="Confirme a nova senha" data-ng-model="forms.changePassword.pass2">
		                    </div>
		                    
	                        <span data-ng-show="forms.changePassword.errorMessage != ''" class="text-danger">{{forms.changePassword.errorMessage|translate}}</span>
	                        <span data-ng-show="forms.changePassword.successMessage != ''" class="text-success">{{forms.changePassword.successMessage|translate}}</span>
	                        
	                    </div>
	                    
	                    <div class="form-group">
	                        <button id="btn-change-password-alterar" class="btn btn-primary btn-block" data-ng-click="alterarSenha()">Salvar</button>
	                    </div>
	                    
	                </div>
	            </div>
	        </div>
	    </div>
		
	</div>
	
	


          	               
	                	
                                          
                  						
						
						                
                     
		            