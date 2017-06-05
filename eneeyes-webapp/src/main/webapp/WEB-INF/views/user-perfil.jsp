<div data-ng-controller="UserPerfilController as UserPerfilController">
	<div id="modalPerfilUser" class="modal" tabindex="-1">
	    <div class="modal-dialog modal-lg" role="document">
	    	<div class="modal-content">
				<div class="modal-body"style="padding-bottom: 0px; !important">
										
					<div class="box box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
						<div class="box-header">
							<h3 class="box-title">Cadastro / Edição</h3>
							<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
						</div>					
						<div class="box-body" style="padding-bottom: 0px; !important">
				
							<form class="form" name="userForm" novalidate>
							
								<div class="row">	
																	
									<div class="col-md-4">				
										<div class="box box-primary">				                    
											 <div class="box-header with-border"><strong>Apelido *</strong>
												<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.displayname.$error.required && !userForm.displayname.$pristine'>  Campo Obrigatório</strong>
												<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.displayname.$error.maxlength'>Tamanho Máximo 15 caracteres</strong>
											 </div>
											 <div class="box-body">													                                                                        
												<input class="form-control inputProfile" placeholder="Apelido" data-ng-model="user.displayName" data-ng-maxlength="15" name="displayname" required>                                                                       
											</div>
										</div>
									</div>
									
									<div class="col-md-2">
									</div>
									
									<div class="col-md-6">							    
									    <input type="file" id="idInputImageUser" style='display:none'>							    
									    <div class="box box-primary">
							                <div class="box-body box-profile">                          
							                    <img class="profile-user-img img-responsive img-circle imgUser" style="margin: 0 auto" alt="Imagem do Perfil" 
							                    	data-ng-src="{{user.image}}" onError="this.src='/assets/img/cover.jpg'">
							                    <p class="text-muted text-center data-ng-binding">{{user.nickname}}</p>                    
							                    <a href="#" class="icon fa fa-photo fa-2.0x pull-right" data-ng-click="addPhoto();" title="Trocar foto"></a>
							                    					                    	                
							                </div>
						                </div>															
									</div>
									
																	
								</div>
								<div class="row">
									
									<div class="col-md-4" style="padding-right: 5px !important">
										<div class="form-group">							
											<label class="control-label">CPF</label>
											<input type="text" class="form-control" name="cpf" data-ng-model="user.cpf" data-mask="999.999.999-99" mask data-ng-model="user.cpf"/>														
										</div>
									</div>
									
									<div class="col-md-8">				
										<div class="form-group">										            
											<label class="control-label">Nome *</label>
											<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.nickname.$error.required && !userForm.nickname.$pristine'>  Campo Obrigatório</strong>
											<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.nickname.$error.maxlength'> Tamanho Máximo 50 caracteres</strong>							 			             													                                                                        
											<input class="form-control inputProfile" placeholder="Nome Completo" data-ng-model="user.nickname" data-ng-maxlength="50" name="nickname" required>						
										</div>
									</div>	
								
								</div>
								
								<div class="row">
									<div class="col-md-3">
										<label class="control-label" title="Cidade">Telefone </label>
										<input type="text" class="form-control" placeholder="Telefone Fixo" data-mask="(99) 9999-9999" mask data-ng-model="user.fone">					
									</div>
									
									<div class="col-md-3">
										<label class="control-label">Celular</label>					
										<input type="text" class="form-control" placeholder="Celular" data-mask="(99) 99999-9999" mask data-ng-model="user.cell">					
									</div>
									
									<div class="col-md-6">						
										<div data-ng-class="{'has-error': !emailValid}">
											<label class="control-label" title="Email">E-mail</label>	
					                        <div class="input-group">								                                        	
						                    	<span class="input-group-addon" data-ng-show="emailValid">@</span>													                    														                    	
						                    	<span class="input-group-addon text-red" data-ng-hide="emailValid">@</span>
						                    	<input data-ng-model="user.email" type="text" class="form-control" placeholder="Email" data-ng-change="validEmail($event);">
						                    </div>
					                  	</div>											
									</div>					
								</div>				
								<hr>
								<div class="row">
									<div class="col-md-3">
										<label class="control-label" title="Login">Login *</label>
										<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.userlogin.$error.required && !userForm.userlogin.$pristine'>  Campo Obrigatório</strong>
										
										<div data-ng-class="{'has-error': !loginValid}">	
											<div class="input-group">						                    						                  
												<input name="userlogin" data-ng-model="user.login" type="text" class="form-control" placeholder="Login" data-ng-disabled="isEdit" required>
											
												<a data-ng-if="!isEdit" href="#" title="Validar Nome de Usuário" data-ng-click="validLogin(user.login);" class="input-group-addon">
												<i data-ng-class="(!loginValid) ? 'text-red' : 'text-green'" class="fa fa-check"></i></a>								
												<a data-ng-if="isEdit" href="#" title="Validar Nome de Usuário" class="input-group-addon"><i class="fa fa-check text-green"></i></a>								
											</div>
										</div>						                                         												            			
									</div>
																	
									
								</div>
								
							</form>
						</div>
				
						<div class="modal-footer">						
							<button type="button" data-ng-click="clearFormAlarm(); userForm.$setPristine()" class="btn btn-default" data-dismiss="modal">Cancelar</button>                                                                
							<button type="button" data-ng-click="saveUser();" class="btn btn-primary" data-dismiss="modal"	data-ng-disabled="(loginValid && userForm.$valid) ? false : true">Salvar</button>
				
						</div>
					</div>
															
				</div>
			</div>
		</div>
	</div>			
</div>