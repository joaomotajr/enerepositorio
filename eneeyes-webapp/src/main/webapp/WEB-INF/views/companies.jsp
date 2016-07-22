<div ng-controller="companyController">
						
	<style>
	
	.tabs-left {
	  	border-bottom: none;
	  	padding-top: 2px;
	}
	
	.tabs-left {
	  	border-right: 1px solid #ddd;
	}
	
	
	.tabs-left>li {
	  	float: none;
	 	margin-bottom: 2px;
	}
	
	.tabs-left>li {
	  	margin-right: -1px;
	}	
	
	.tabs-left>li.active>a,
	.tabs-left>li.active>a:hover,
	.tabs-left>li.active>a:focus {
	  	border-bottom-color: #ddd;
		border-right-color: transparent;
	}
		
	.tabs-left>li>a {
		border-radius: 4px 0 0 4px;
		margin-right: 0;
		display: block;
	}
	</style>			
				
	<div class="row">
	    <div class="col-md-12">
	    
				
			<div class="row">
						
				<div class="col-md-12">
				
					<!-- DIRECT CHAT PRIMARY -->
					<div class="box box-primary direct-chat direct-chat-primary">
					  
						<div class="box-header with-border">
							<h3 class="box-title">Empresas</h3>
							<div class="box-tools pull-right">
								<span data-toggle="tooltip" title="3 New Messages" class="badge bg-light-blue">3</span>
								<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
								<button class="btn btn-box-tool" data-toggle="tooltip" title="Contacts" data-widget="chat-pane-toggle"><i class="fa fa-comments"></i></button>
								<button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
							</div>
						</div><!-- /.box-header -->
						
						<div class="box-body">
							<div class="col-md-1">
							</div>
							<div class="col-md-5">
								<br />
								<div class="box box-primary">
				                    <div class="box-header with-border"><strong style="font-size:1.4em">Pesquisar Empresa.</strong></div>
				                
				                    <div class="box-body">
				                        <select id="selMatriculaFuncionario" class="form-control select2 select2-hidden-accessible" 
				                            style="width: 100%;" 
				                            tabindex="-1" 
				                            aria-hidden="true">                              
				                                <option ng-repeat="company in companies">
				                                    {{company.name}}
				                                </option>                    
				                        </select>
				                        <div class="box-footer">
				                        	<button type="button" ng-click="newCompany();" class="btn btn-primary pull-right">Nova</button>
				                        </div>							                                                                    
				                    </div>        
				                </div>        
		                            
							</div>
							<div class="col-md-6">
								<br />
								<div class="form-group">
									<label class="control-label">Nome</label>                                                                        
									<input id="idCompanyName" class="form-control" placeholder="Nome da Empresa" ng-model="companyName">                                                                        
								</div>
								
								<div class="form-group">
									<label class="control-label">Descrição</label>                                                                        
									<input class="form-control" placeholder="Descrição" ng-model="companyDescription">                                                                        
								</div>
							</div>
						</div><!-- /.box-body -->
						
						<div class="box-footer">							
							<div class="col-md-12">
																			
								
									<button type="button" ng-click="saveCompany();" class="btn btn-success pull-right">   Salvar   </button>
																<span class="pull-right">   </span>
									<button type="button" ng-click="newCompany();" class="btn btn-primary pull-right">Selecionar</button>
												
							</div>
						</div><!-- /.box-footer-->
					</div><!--/.direct-chat -->
				
				</div>
			</div>
		
			<div class="row">		
						<div class="col-md-1">						
						</div>
													
						<div class="col-md-2">
						  <!-- required for floating -->
						<!-- Nav tabs -->
							<ul class="nav nav-tabs tabs-left sideways">							
							    <li class="active"><a href="#profile-v" data-toggle="tab">Unidade</a></li>
							    <li><a href="#messages-v" data-toggle="tab">Áreas</a></li>
							    <li><a href="#settings-v" data-toggle="tab">Dispositivos</a></li>
							</ul>
						</div>
						
						<div class="col-md-9">
					  <!-- Tab panes -->
					 	<div class="tab-content">
					   					    	
					    	<div class="tab-pane active" id="profile-v">
					    		
					    		<div class="box box-primary">
									<div class="box-header">
									  <h3 class="box-title">Unidades</h3>
									</div>
									<div class="box-body">
									</div>
								</div>
											    	
					    	</div>
					    	
					    	<div class="tab-pane" id="messages-v">
					    	
					    		<div class="box box-primary">
									<div class="box-header">
									  <h3 class="box-title">Áreas</h3>
									</div>
									<div class="box-body">
									</div>
								</div>
					    	</div>
					    	
					    	<div class="tab-pane" id="settings-v">
					    		
					    		<div class="box box-primary">
									<div class="box-header">
									  <h3 class="box-title">Dispositivos</h3>
									</div>
									<div class="box-body">
									</div>
								</div>
					    	
					    	</div>
						</div>
					</div>
					</div>
			
			
			<div class="clearfix"></div>	    
		</div>
	</div>
      
</div>

    
    