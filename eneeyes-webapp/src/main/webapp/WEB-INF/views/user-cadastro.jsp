<div class="modal-body"style="padding-bottom: 0px; !important">
						
	<div class="box box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
		<div class="box-header">
			<h3 class="box-title">Cadastro / Edi��o</h3>
			<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
		</div>					
		<div class="box-body" style="padding-bottom: 0px; !important">

			<form class="form" name="userForm" novalidate>
			
				<div class="row">	
				
					<div class="col-md-3">																		
						<div class="box box-primary">				                    
							<div class="box-header with-border"><strong><i class="fa fa-users"></i> Perfil *</strong>
								<strong class="text-red pull-right fieldNeeded" data-ng-show='(userForm.rolename.$dirty && userForm.rolename.$invalid)'>  Campo Obrigat�rio</strong>
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
							<div class="box-header with-border"><strong><i class="fa fa-industry"></i> Empresa *</strong>
								<strong class="text-red pull-right fieldNeeded" data-ng-show='(userForm.companyName.$dirty && userForm.companyName.$invalid)'>  Campo Obrigat�rio</strong>
							</div>		               	
							<div class="box-body">
								<div data-ng-class="{'has-error': userForm.companyName.$dirty && userForm.companyName.$invalid}">							               
								   <select name="companyName" class="form-control" data-live-search="true"
									   style="width: 100%;" tabindex="-1" aria-hidden="true"                              
										   data-ng-options="item as item.name for item in companies | orderBy: 'name' track by item.uid" 
													data-ng-model="user.companyDto" required>
													<option value="">Selecione</option>		                                        
								   </select>    
								</div>
							</div>			                    			                            
						</div>						
					</div>
					
					<div class="col-md-4">				
						<div class="box box-primary">				                    
							 <div class="box-header with-border"><strong>Apelido *</strong>
								<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.displayname.$error.required && !userForm.displayname.$pristine'>  Campo Obrigat�rio</strong>
								<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.displayname.$error.maxlength'>Tamanho M�ximo 15 caracteres</strong>
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
<!-- 							<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.cpf.$error.required && !userForm.cpf.$pristine'>  Campo Obrigat�rio</strong> -->
<!-- 							<input type="text" class="form-control" name="cpf" data-ng-model="user.cpf" data-mask="999.999.999-99" mask data-ng-model="user.cpf" required="required"/> -->
<!-- 						</div> -->
						</div>
					</div>
					
					<div class="col-md-8">				
						<div class="form-group">										            
							<label class="control-label">Nome *</label>
							<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.nickname.$error.required && !userForm.nickname.$pristine'>  Campo Obrigat�rio</strong>
							<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.nickname.$error.maxlength'> Tamanho M�ximo 50 caracteres</strong>							 			             													                                                                        
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
<!-- 						<label class="control-label" title="Cidade">E-mail</label>					 -->
<!-- 						<input type="text" class="form-control" data-ng-model="user.email"> -->
						
						<div data-ng-class="{'has-error': !emailValid}">	
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
					<div class="col-md-4">
						<label class="control-label" title="Cidade">Login *</label>
						<strong class="text-red pull-right fieldNeeded" data-ng-show='userForm.userlogin.$error.required && !userForm.userlogin.$pristine'>  Campo Obrigat�rio</strong>
						
						<div data-ng-class="{'has-error': !loginValid}">	
							<div class="input-group">						                    						                  
								<input name="userlogin" data-ng-model="user.login" type="text" class="form-control" placeholder="Login" data-ng-disabled="isEdit" required>
								
									<a data-ng-if="!isEdit" href="#" title="Validar Nome de Usu�rio" data-ng-click="validLogin(user.login);" class="input-group-addon">
									<i data-ng-class="(!loginValid) ? 'text-red' : 'text-green'" class="fa fa-check"></i></a>								
									<a data-ng-if="isEdit" href="#" title="Validar Nome de Usu�rio" class="input-group-addon"><i class="fa fa-check text-green"></i></a>								
								
							</div>
						</div>						                                         												            			
					</div>
					
					<div class="col-md-2">
					</div>
					
					<div class="col-md-4">
						<label class="control-label" title="Senha Provis�ria" data-ng-if="!isEdit">Senha</label>						
						<label class="control-label" title="Senha Provis�ria" data-ng-if="isEdit">Resetar Senha</label>
						
						<div data-ng-class="{'has-error': !user.hash}">
							<div class="input-group">						                    						                  
								<input id="userLogin" data-ng-model="user.hash" type="text" class="form-control" name="userhash" placeholder="Senha a Ser Gerada" readonly required>
								<a href="#" title="Gerar Senha Provis�ria" data-ng-click="makeHash();" Class="input-group-addon"><i class="fa fa-key"></i></a>
							</div>
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