
	<div>
		<form class="form" name="userForm" novalidate>
		
			<div class="row">
			
			
				<div class="col-md-4">
																	
					<div class="box box-primary">				                    
		               	<div class="box-header with-border"><strong><i class="fa fa-industry"></i> Perfil</strong>
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
								
			</div>
			<div class="row">
			
				<div class="col-md-8">
				
					<div class="box box-primary">				                    
			             <div class="box-header with-border"><strong>Nome</strong>
				         	<strong class="text-red pull-right" data-ng-show='userForm.username.$error.required && !userForm.username.$pristine'>  [Nome Obrigatorio]</strong>
							<strong class="text-red pull-right" data-ng-show='userForm.username.$error.maxlength'>Tamanho Máximo 12 caracteres</strong>
			             </div>
			             <div class="box-body">													                                                                        
							<input class="form-control inputProfile" placeholder="Nome" data-ng-model="user.displayName" data-ng-maxlength="30" name="username" required>                                                                       
						</div>
					</div>
				</div>	
			
			</div>
			
			<div class="row">
				
					<div class="col-md-1">										            									            											
						<label>E-MAIL</label>			
					</div>
					
					<div class="col-md-6" style="padding-left: 5px !important; padding-right: 5px !important">
						<div data-ng-class="{'has-error': !emailValid}">	
							<div class="input-group">								                                        	
								<span class="input-group-addon" data-ng-show="emailValid">@</span>													                    														                    	
								<span class="input-group-addon text-red" data-ng-hide="emailValid">@</span>
								<input 
									id="alarmEmail" 
									data-ng-model="email" type="text" class="form-control" 
									placeholder="Email" 
									data-ng-change="validEmail($event);">
							</div>
						</div>						                                         												            			
					</div>
					
					<div class="col-md-1">										            									            										
						<label>Celular</label>			
					</div>
					
					<div class="col-md-4" style="padding-left: 5px !important;">
						<div data-ng-class="{'has-error': !mobileValid}">	
							<div class="input-group">
								<span class="input-group-addon" data-ng-show="mobileValid"><i class="fa fa-phone-square"></i></span>
								<span class="input-group-addon text-red" data-ng-hide="mobileValid"><i class="fa fa-phone-square"></i></span>
								
								<input class="form-control alarmCelularMask" 
									id="alarmCelular" 
									data-ng-model="celular" 
									type="text" maxlength="15" placeholder="(XX) XXXXX-XXXX" data-ng-change="validMobile($event);" />													                    	
							</div>
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
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="CPF do Cliente">CPF* </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" name="cpf" class="form-control" data-mask="999.999.999-99" mask data-ng-model="user.cpf" required="required">
		</div>						
	</div>	
	
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="Nome do Cliente">Nome* </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" name="displayName" class="form-control" data-ng-model="user.displayName" required="required">
		</div>
		<label class="col-sm-4 col-md-3 control-label">Apelido</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" class="form-control" data-ng-model="user.nickname">
		</div>
	</div>
	
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="Cidade">Telefone </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" data-mask="(99) 9999-9999" mask data-ng-model="user.fone">
		</div>
		<label class="col-sm-4 col-md-3 control-label">Celular</label>
		<div class="col-sm-6 col-md-3" >
			<input type="text" class="form-control" data-mask="(99) 99999-9999" mask data-ng-model="user.cell">
		</div>
	</div>
		
	<div class="form-group ">
		<label class="col-sm-4 col-md-2 control-label" title="Cidade">E-mail </label>
		<div class="col-sm-6 col-md-3">
			<input type="text" class="form-control" data-ng-model="user.email">
		</div>
		<label class="col-sm-4 col-md-3 control-label">Tipo*</label>
		<div class="col-sm-6 col-md-3">
			<select class="form-control" name="role" data-ng-model="user.role" required="required">
				<option value="">Selecione...</option>
				<option data-ng-show="userLogado.isAdmin" value="1">Administrador</option>
				<option data-ng-show="userLogado.isAdmin" value="2">Contratante</option>
				<option data-ng-show="userLogado.isAdmin || userLogado.isContractor" value="3">Gerente</option>
				<option data-ng-show="userLogado.isAdmin || userLogado.isContractor" value="4">Operador</option>
			</select>
		</div>
	</div>	
	
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
