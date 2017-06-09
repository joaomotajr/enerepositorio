<div class="modal-body"style="padding-bottom: 0px; !important">
						
	<div class="box box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
		<div class="box-header">
			<h3 class="box-title">Cadastro / Edição</h3>
			<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
		</div>					
		<div class="box-body" style="padding-bottom: 0px; !important">

			<form class="form" name="userForm" novalidate>
			
				<div class="row">	
				
					<div class="col-md-3">																		
						<div class="box box-primary">				                    
							<div class="box-header with-border"><strong><i class="fa fa-users"></i> Perfil *</strong>
								<strong class="text-red pull-right fieldNeeded" data-ng-show='(userForm.rolename.$dirty && userForm.rolename.$invalid)'>  Campo Obrigatório</strong>
							</div>		               	
							<div class="box-body">
								<div data-ng-class="{'has-error': userForm.rolename.$dirty && userForm.rolename.$invalid}">							               
								   <select name="rolename" class="form-control" data-live-search="true"
									   style="width: 100%;" tabindex="-1" aria-hidden="true"                              
										   data-ng-options="item as item.name for item in roles | orderBy: 'name' track by item.id" 
													data-ng-model="selectedRole" required>
													<option value="">Selecione</option> 
								   </select>    
								</div>
							</div>			                    			                            
						</div>
					</div>
					
				
					<div class="col-md-5">																		
						<div class="box box-primary">				                    
							<div class="box-header with-border" style="padding:1px ! important">							
									
								<div>
									<strong><i class="fa fa-user"></i> Usuário de: </strong>
									<div class="radio3 radio-check radio-success radio-inline">
										<input type="radio" id="radio5" value="1" data-ng-model="userTipo">
										<label for="radio5">Empresa</label>
									</div>
									<div class="radio3 radio-check radio-warning radio-inline">
										<input type="radio" id="radio6" value="2" data-ng-model="userTipo">
										<label for="radio6">Central</label>
									</div>                                                                        
								</div>									
							
								<strong class="text-red pull-right fieldNeeded" data-ng-show='(userForm.companyName.$dirty && userForm.companyName.$invalid && userTipo == 1)'>  Campo Obrigatório</strong>
							</div>		               	
							<div class="box-body">
								<div data-ng-class="{'has-error': userForm.companyName.$dirty && userForm.companyName.$invalid && userTipo == 1}">							               
								   <select name="companyName" class="form-control" data-live-search="true"
									   style="width: 100%;" tabindex="-1" aria-hidden="true"                              
										   data-ng-options="item as item.name for item in companies | orderBy: 'name' track by item.uid" 
													data-ng-model="user.companyDto" data-ng-required='userTipo!=2'>
													<option value="">Selecione</option>		                                        
								   </select>    
								</div>
							</div>			                    			                            
						</div>						
					</div>
					
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
				</div>
				<div class="row">
					
					<div class="col-md-4" style="padding-right: 5px !important">
						<div class="form-group">							
							<label class="control-label">CPF</label>
							<input type="text" class="form-control" name="cpf" data-ng-model="user.cpf" data-mask="999.999.999-99" mask data-ng-model="user.cpf"/>														
<!-- 						<div data-ng-class="{'has-error': userForm.cpf.$invalid && !userForm.cpf.$pristine}"> -->
<!-- 							<label class="control-label">CPF *</label> -->
<!-- 							<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.cpf.$error.required && !userForm.cpf.$pristine'>  Campo Obrigatório</strong> -->
<!-- 							<input type="text" class="form-control" name="cpf" data-ng-model="user.cpf" data-mask="999.999.999-99" mask data-ng-model="user.cpf" required="required"/> -->
<!-- 						</div> -->
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
						<strong class="text-red pull-right fieldNeeded" 
						data-ng-show='!userForm.userlogin.$error.minLength && !userForm.userlogin.$error.maxLength && userForm.userlogin.$error.pattern && userForm.userlogin.$dirty'> Precisa Iniciar com uma Letra, e Conter Números &amp; Letras Apenas</strong>
												
						<div data-ng-class="{'has-error': !loginValid}">	
							<div class="input-group">						                    						                  
								<input name="userlogin" data-ng-model="user.login" type="text" class="form-control" placeholder="Login" 
									data-ng-disabled="isEdit" data-ng-pattern="/^[A-z][A-z0-9]*$/" data-ng-minlength="5" data-ng-maxlength="20" required>
							
								<a data-ng-if="!isEdit" href="#" title="Validar Nome de Usuário" data-ng-click="validLogin(user.login);" class="input-group-addon">
								<i data-ng-class="(!loginValid) ? 'text-red' : 'text-green'" class="fa fa-check"></i></a>								
								<a data-ng-if="isEdit" href="#" title="Validar Nome de Usuário" class="input-group-addon"><i class="fa fa-check text-green"></i></a>								
							</div>
						</div>						                                         												            			
					</div>
					
					<div class="col-md-1">
					</div>
					
					<div class="col-md-3">
						<label class="control-label" title="Senha Provisória" data-ng-if="!isEdit">Senha</label>						
						<label class="control-label" title="Senha Provisória" data-ng-if="isEdit">Resetar Senha</label>
						
						<div data-ng-class="{'has-error': !user.hash}">
							<div class="input-group">						                    						                  
								<input id="userLogin" data-ng-model="user.hash" type="text" class="form-control" name="userhash" placeholder="Senha a Ser Gerada" readonly required>
								<a href="#" title="Gerar Senha Provisória" data-ng-click="makeHash();" Class="input-group-addon"><i class="fa fa-key"></i></a>
							</div>
						</div>						                                  												            			
					</div>
					
					<div class="col-md-1">
					</div>
					
					<div class="col-md-3" data-ng-if="isEdit">					
						<label class="control-label" title="Status do Usuário" data-ng-if="isEdit">Status do Usuário</label>
						<div class="input-group">	
							<button type="button" data-ng-click="inativeUser();" class="btn btn-primary" data-ng-if="user.status=='ACTIVE'"><i class="fa fa-user"> </i>  ATIVO  </button>
							<button type="button" data-ng-click="inativeUser();" class="btn btn-danger" data-ng-if="user.status=='INACTIVE'"><i class="fa fa-user-times"> </i>INATIVO</button>
						</div>							
					</div>								
					
				</div>
				
			</form>
		</div>

		<div class="modal-footer">						
			<button type="button" data-ng-click="clearFormAlarm(); userForm.$setPristine()" class="btn btn-default" data-dismiss="modal">Cancelar</button>                                                                
			<button type="button" data-ng-click="saveUser();" class="btn btn-primary" data-dismiss="modal"	data-ng-disabled="(loginValid && emailValid && userForm.$valid) ? false : true">Salvar</button>

		</div>
	</div>
											
</div>