	<div data-ng-controller="UserController">
		<div class="row">				                                                    
			<div class="col-md-12">                                                        
				<div class="box box-primary" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
					<div class="box-header">
					  <h3 class="box-title">Gerenciamento e Pesquisa de Usu&aacute;rios</h3>
					  <a href="#" class="text-muted pull-right" data-ng-click="refresh();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					
					<div class="box-body">					
						
						<!-- FORMULARIO PESQUISA      
						<form novalidate="novalidate" name="formulario" class="form-horizontal" data-ng-submit="pesquisaUser()">
							<div class="form-group ">
								<label class="col-sm-4 col-md-3 control-label" title="CPF Cliente">CPF </label>
								<div class="col-sm-6 col-md-3">
									<input type="text" class="form-control" data-mask="999.999.999-99" mask data-ng-model="user.cpf">
								</div>								
								
							</div>	
							<div class="form-group ">
								<label class="col-sm-4 col-md-3 control-label" title="Nome do Cliente">Nome do Usu&aacuterio</label>
								<div class="col-sm-6 col-md-3">
									<input type="text" class="form-control" data-ng-model="user.displayName">
								</div>
								<label class="col-sm-4 col-md-2 control-label">Apelido</label>
								<div class="col-sm-6 col-md-3" >
									<input type="text" class="form-control" data-ng-model="user.nickname">
								</div>
							</div>					
							<div class="form-group">
								<label class="col-sm-4 col-md-3 control-label">Login</label>
								<div class="col-sm-6 col-md-3">
									<input type="text" class="form-control" data-ng-model="user.login">
								</div>
								<label class="col-sm-4 col-md-2 control-label">Tipo de Usu&aacute;rio</label>
								<div class="col-sm-6 col-md-3">
									<select class="form-control" data-ng-model="user.role">
										<option value="">Selecione...</option>
										<option data-ng-show="userLogado.isAdmin" value="1">Administrador</option>
										<option data-ng-show="userLogado.isAdmin || userLogado.isContractor" value="3">Gerente</option>
										<option data-ng-show="userLogado.isAdmin || userLogado.isContractor" value="4">Operador</option>
									</select>
								</div>
							</div>	
							<div class="form-group">
								<label class="col-sm-4 col-md-3 control-label">Status</label>
								<div class="col-sm-6 col-md-3">
									<select class="form-control" data-ng-model="user.status">
										<option value="">Selecione...</option>
										<option value="ACTIVE">Ativo</option>
										<option value="INACTIVE">Inativo</option>
									</select>
								</div>
								<div class="col-sm-6 col-md-5" align="right">
									<button class="btn btn-info btn-round"><i class="icon-search icon-white"></i> Pesquisar</button>
								</div>
							</div>	
						</form>
						
						-->
						
						<!-- RESULTA DA PESQUISA -->
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-info">
									<div class="panel-heading"> Resultado </div>
									<div class="panel-body">
										<div class="table-responsive">
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
													<tr data-ng-repeat="user in pesquisa.listUser" data-ng-class="{'danger' : user.status == 'INACTIVE', 'success' : user.status == 'ACTIVE'}"
															   data-toggle="modal" data-target="#modalDetalheUser" data-ng-click="detalheUser($index)">
															   
														<td width="150px" align="center">{{cpfFormatter(user.cpf)}}</td>
														<td width="200px">{{user.displayName}}</td>									
														<td>{{user.login}}</td>
														<td align="center">{{user.roles[0].name}}</td>
														<td align="center">{{getStatus(user.status)}}</td>
														<td align="center">{{user.createDate | date: 'dd/MM/yyyy'}}</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>						                                                 
					</div>
					
					<div class="box-footer">
						<div class="form-group">
							<div class="col-sm-12" align="left">
								<a class="btn btn-success" data-toggle="modal" data-target="#modalCadastrarUser" 
									data-ng-click="novoUsuario()"><i class="icon-plus icon-white"></i>&nbsp;Novo Usu&aacute;rio</a>
							</div>
						</div>						                                                                
						
					</div>
				</div>
				
				<div id="resultErro" class="alert alert-warning" role="alert" data-ng-show="msgErro" >
            		<button type="button" class="close" ><span data-ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
			</div>                                                     
			
		</div>
		
		<!-- MODAL CADASTRO USUARIO -->
		<div id="modalCadastrarUser" class="modal fade col-md-12" tabindex="-1">
		    <div class="modal-dialog">
		        <div class="modal-content">
		        	<header class="modal-header">
		        		<button type="button" class="close" data-dismiss="modal">&times;</button>
		                <h4 class="modal-title" align="center"><b>Inclus&atilde;o de Usu&aacute;rio</b></h4>
		            </header>
		            <div class="modal-body box dark" align="center">
						<jsp:include page="user-cadastro.jsp"/>
		            </div>
		             <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
						<button class="col-md-1 col-md-offset-11 btn btn-default" data-ng-click="fecharModal()"><i class="icon-exit icon-white"></i>Fechar</button>
		            </div>
		        </div>
		    </div>
		</div>
		
		<!-- MODAL EDICAO USUARIO -->
		<div id="modalDetalheUser" class="modal fade col-md-12" tabindex="-1">
		    <div class="modal-dialog">
		        <div class="modal-content">
		        	<header class="modal-header">
		        		<button type="button" class="close" data-dismiss="modal">&times;</button>
		                <h4 class="modal-title" align="center"><b>Gerenciar Usu&aacute;rio: {{user.displayName}}</b></h4>
		            </header>
		            <div class="modal-body box dark" align="center">
						<jsp:include page="user-cadastro.jsp"/>
		            </div>
		            <div class="modal-footer" style="margin-top: -1px; padding-top: 15px; padding-bottom: 15px;">
						<button class="col-md-1 col-md-offset-11 btn btn-default" data-ng-click="fecharModal()"><i class="icon-exit icon-white"></i>Fechar</button>
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
	
	


          	               
	                	
                                          
                  						
						
						                
                     
		            