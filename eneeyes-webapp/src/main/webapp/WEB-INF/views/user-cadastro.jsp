
	<div>
		<form class="form" name="userForm" novalidate>
		
			<div class="row">
			
			
				<div class="col-md-3">
																	
					<div class="box box-primary">				                    
		               	<div class="box-header with-border"><strong><i class="fa fa-users"></i> Perfil</strong>
		               		<strong class="text-red pull-right" data-ng-show='(userForm.roleName.$dirty && userForm.roleName.$invalid)'>  Campo Obrigatório</strong>
		               	</div>		               	
		                <div class="box-body">
		                   	<div data-ng-class="{'has-error': userForm.roleName.$dirty && userForm.roleName.$invalid}">							               
		                       <select name="roleName" class="form-control" data-live-search="true"
		                           style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                               data-ng-options="item as item.name for item in roles | orderBy: 'name' track by item.uid" 
		                                        data-ng-model="user.roles" required>
		                                        <option value="">Selecione</option> 
		                       </select>    
		                   	</div>
		            	</div>			                    			                            
		            </div>
		       	</div>
		       	
			
				<div class="col-md-5">
																	
					<div class="box box-primary">				                    
		               	<div class="box-header with-border"><strong><i class="fa fa-industry"></i> Empresa</strong>
		               		<strong class="text-red pull-right" data-ng-show='(userForm.companyName.$dirty && userForm.companyName.$invalid)'>  Campo Obrigatório</strong>
		               	</div>		               	
		                <div class="box-body">
		                   	<div data-ng-class="{'has-error': userForm.companyName.$dirty && userForm.companyName.$invalid}">							               
		                       <select name="companyName" class="form-control" data-live-search="true"
		                           style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                               data-ng-options="item as item.name for item in companies | orderBy: 'name' track by item.uid" 
		                                        data-ng-model="user.company" required>
		                                        <option value="">Selecione</option> 
		                       </select>    
		                   	</div>
		            	</div>			                    			                            
		            </div>
		            
		       	</div>
		       	
		       	<div class="col-md-4">
				
					<div class="box box-primary">				                    
			             <div class="box-header with-border"><strong>Apelido</strong>
				         	<strong class="text-red pull-right" data-ng-show='userForm.display.$error.required && !userForm.display.$pristine'>  [Obrigatorio]</strong>
							<strong class="text-red pull-right" data-ng-show='userForm.display.$error.maxlength'>Tamanho Máximo 15 caracteres</strong>
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
				    	<div data-ng-class="{'has-error': userForm.cpf.$invalid && !userForm.cpf.$pristine}">
			                <label class="control-label">CPF</label>
			                <input type="text" class="form-control" name="cpf" data-ng-model="user.cpf" data-mask="999.999.999-99" mask data-ng-model="user.cpf" required="required"/>
			            </div>
			    	</div>
			    </div>
				
				<div class="col-md-8">				
					<div class="form-group">			            
			             <label class="control-label">Nome
				         	<strong class="text-red pull-right" data-ng-show='userForm.nickname.$error.required && !userForm.nickname.$pristine'>  [Obrigatorio]</strong>
							<strong class="text-red pull-right" data-ng-show='userForm.nickname.$error.maxlength'> Tamanho Máximo 50 caracteres</strong>
			             </label>			             													                                                                        
						<input class="form-control inputProfile" placeholder="Nome Completo" data-ng-model="user.nickName" data-ng-maxlength="50" name="nickname" required>						
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
					<label class="control-label" title="Cidade">E-mail @</label>					
					<input type="text" class="form-control" data-ng-model="user.email">					
				
				</div>
				
			</div>				
			<hr>
			<div class="row">
				<div class="col-md-4">
					<label class="control-label" title="Cidade">Login</label>
					<div data-ng-class="{'has-error': !loginValid}">	
						<div class="input-group">						                    						                  
							<input id="userLogin" 
								data-ng-model="user.login" type="text" class="form-control" 
								placeholder="Login">
							<a title="Validar Nome de Usuário" data-ng-click="validLogin($event);" class="input-group-addon"><i class="fa fa-check"></i></a>
						</div>
					</div>						                                         												            			
				</div>
				
				<div class="col-md-2">
				</div>
				
				<div class="col-md-4">
					<label class="control-label" title="Cidade">Senha</label>
						
						<div class="input-group">						                    						                  
							<input id="userLogin" 
								data-ng-model="hash" type="text" class="form-control" 
								placeholder="Senha a Ser Gerada" readonly>
							<span title="Gerar Senha Provisória" data-ng-click="makeHash($event);" class="input-group-addon"><i class="fa fa-key"></i></span>
						</div>
											                                         												            			
				</div>								
								
				
			</div>
			
		</form>
	</div>

	<div class="modal-footer">						
		<button type="button" data-ng-click="clearFormAlarm(); userForm.$setPristine()" class="btn btn-default" data-dismiss="modal">Cancelar</button>                                                                
		<button type="button" data-ng-click="saveAlarm();" class="btn btn-primary" data-dismiss="modal"	data-ng-disabled="(userForm.$valid) ? false : true">Salvar</button>						                                
 	</div>
 	
	<!-- 
	

	
	<div class="form-group">
		<label class="col-sm-4 col-md-2 control-label">Login*</label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" data-ng-model="user.login" required="required">
		</div>
		<label class="col-sm-4 col-md-3 control-label">Senha*</label>
		<div class="col-sm-6 col-md-3">
			<input type="password" class="form-control" data-ng-model="user.hash" data-ng-disabled="isEdit" required="required">
		</div>
	</div>	
	 
	 
	<br />	
	
	<div class="btn-block">
		<div class="col-sm-12" align="right">
		
	
			<a class="btn btn-danger" data-ng-show="isEdit" data-ng-click="clearMessageCadastro()" data-toggle="modal" data-target="#modalExcluirUser">
				<i class="icon-remove icon-white"></i>&nbsp;Excluir
			</a>
			
	
			<a class="btn btn-success" data-ng-show="isEdit && user.status == 'INACTIVE'" data-ng-click="ativarUser()">
				<i class="icon-ok icon-white"></i>&nbsp;Ativar
			</a>
			
	
			<a class="btn btn-warning" data-ng-show="isEdit && user.status == 'ACTIVE'" data-ng-click="clearMessageCadastro()" data-toggle="modal" data-target="#modalInativarUser">
				<i class="icon-ban-circle icon-white"></i>&nbsp;Inativar
			</a>
			
	
			<button type="submit"  class="btn btn-primary" data-ng-disabled="formCadastro.$invalid">
				<div data-ng-show="isCad"><i class="icon-save icon-white"></i>&nbsp;Salvar</div>
				<div data-ng-show="isEdit"><i class="icon-edit icon-white"></i>&nbsp;Atualizar</div>
			</button>
			
		</div>
		<sub data-ng-show="isCad" class="col-sm-4">* Preenchimento Obrigat&oacute;rio</sub>	
	</div>
	-->				
