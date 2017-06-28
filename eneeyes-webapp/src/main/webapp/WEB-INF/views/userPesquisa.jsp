	<style>
	.fieldNeeded {
	    font-size: 88%;
	}
	</style>
	<div data-ng-controller="UserController as UserController">
		<div class="row">				                                                    
			<div class="col-md-10">                                                        
				<div class="box box-primary" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
					<div class="box-header">					  
					  <h3 class="box-title">Gerenciamento e Pesquisa de Usu&aacute;rios</h3>
					  <a href="#" class="text-muted pull-right" data-ng-click="refresh();"><i title="Refresh" class="fa fa-refresh"></i></a>
					</div>
					<div class="box-body">
						<div style="height: 500px; overflow: auto;">
							<table class="table table-striped table-bordered table-hover">
		                        <thead>
									<tr>
										<th>Empresa</th>
										<th width="200px">Nick Name</th>
										<th>Login</th>
										<th>Tipo</th>
										<th>Status</th>
										<th>Data</th>
									</tr>
		                        </thead>
								<tbody>
									<tr data-ng-repeat="user in users.listUser" data-ng-class="{'danger' : user.status == 'INACTIVE', 'warning' : !user.companyDto}">
										<td width="100px" data-ng-if="!user.companyDto" align="center">MASTER</td>
										<td width="100px" data-ng-if="user.companyDto" align="center">{{user.companyDto.name}}</td>
																					   
										<td width="200px">{{user.displayName}}</td>									
										<td>{{user.login}}</td>										
										<td  data-ng-if="user.companyDto" align="center">{{user.roles[0].name}}</td>
										<td  data-ng-if="!user.companyDto" align="center">ADMINISTRATOR</td>
										<td align="center">{{getStatus(user.status)}}</td>
										<td align="center">{{user.createDate | date: 'dd/MM/yyyy'}}</td>
										
										<td>
											<button type="button" class="btn btn-primary btn-xs" data-ng-click="detalheUser($index)" data-toggle="modal" data-target="#modalDetalheUser">editar</button>
										</td>
										<td>
											<a type="button" class="btn btn-danger btn-xs" data-popover=' do usuário: [ {{user.login}} ]' data-confirm="excluirUsuario($index)" >excluir</a>
										</td>
										
									</tr>
								</tbody>
							</table>							
							
						</div>                                                       
					</div>
					
					<div class="box-footer">									
						<button type="button" data-ng-click="novoUsuario(); userForm.$setPristine()" class="btn btn-primary pull-right" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#modalDetalheUser">Novo</button>
					</div>
				</div>
								
				<div id="resultErro" class="alert alert-warning" role="alert" data-ng-show="msgErro" >
            		<button type="button" class="close" ><span data-ng-click="msgErro='';">&times;</span></button>
            		<strong>Alerta! </strong>{{msgErro}} 
        		</div>
			</div>                                                     
			
		</div>
		
		<!-- MODAL EDICAO USUARIO --> 
		<div id="modalDetalheUser" class="modal" tabindex="-1">
		    <div class="modal-dialog modal-lg" role="document">
		        <div class="modal-content">
		      	    
		      	    <jsp:include page="userCadastro.jsp"/>        
		            
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
		                    
	                        <span data-ng-show="forms.changePassword.errorMessage != ''" class="text-danger">{{forms.changePassword.errorMessage}}</span>
	                        <span data-ng-show="forms.changePassword.successMessage != ''" class="text-success">{{forms.changePassword.successMessage}}</span>
	                        
	                    </div>
	                    
	                    <div class="form-group">
	                        <button id="btn-change-password-alterar" class="btn btn-primary btn-block" data-ng-click="alterarSenha()">Salvar</button>
	                    </div>
	                    
	                </div>
	            </div>
	        </div>
	    </div>
		
	</div>
	
	


          	               
	                	
                                          
                  						
						
						                
                     
		            