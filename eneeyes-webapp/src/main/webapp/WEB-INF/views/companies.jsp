<style>
	.list-group-item {
		padding-right: 5px !important;
	}
</style>
<div data-ng-controller="companiesController">
	<div class="row">
	    <div class="col-md-12">	    				
			<div class="row">						
				<div class="col-md-12">
					<div class="box box-primary" style="margin-bottom: 8px;" data-ng-class="{'collapsed-box' : selectedCompanyName}">							
						<div class="box-body">							
							<div class="col-md-5">
								<br />
								<div class="box box-primary">
				                    <div class="box-header with-border">
				                    	<strong data-ng-if="isFrom == 'MASTER'" style="font-size:1.4em"><i class="fa fa-industry"></i> Pesquisar Empresa</strong>
				                    	<strong data-ng-if="isFrom != 'MASTER'" style="font-size:1.4em"><i class="fa fa-industry"></i> Empresa</strong>
				                    </div>
				                
				                    <div class="box-body" data-ng-hide="isFrom == 'MASTER'">				                    	
				                    	<input class="form-control" type="text" data-ng-model="companyName" disabled>			                        				                        							                                                                    
				                    </div>        
				                    <div class="box-body" data-ng-show="isFrom == 'MASTER'">				                    	 
				                        <select id="selCompany" class="form-control select2 select2-hidden-accessible"				                        	
				                            style="width: 100%;" tabindex="-1" aria-hidden="true">
				                            	<option value="" selected="selected">Selecione</option>                              				                            	
				                                <option data-ng-repeat="company in companies" value="{{company.uid}}"> {{company.name}} </option>				                                                    
										</select>	
										&nbsp;&nbsp;										
				                    </div>
				                </div>		                            
							</div>

							<div class="col-md-7">							
								<br />
								<div class="box box-primary" data-ng-show="companyUid!=undefined">
									<div class="col-md-6">
										<input type="file" id="idInputImageCompany" style='display:none'>							    									
										<div class="box-body box-profile">
											<img class="profile-user-img img-responsive img-circle imgDetector" style="margin: 0 auto" alt="Imagem da Empresa" 
												data-ng-src="{{companyImage}}" onError="this.src='/assets/img/cover.jpg'">											
											<a href="#" class="icon fa fa-photo fa-2.0x pull-right" data-ng-click="addPhoto();" title="Trocar foto"></a>
										</div>									
									</div>									
									<div class="col-md-6">
										<br />
										<div class="form-group">
											<label class="control-label">Nome *</label>                                                                        
											<input id="idCompanyName" class="form-control" placeholder="Nome da Empresa" data-ng-model="companyName">                                                                        
										</div>								
										<div class="form-group">
											<label class="control-label">Descri&ccedil;&atilde;o</label>                                                                        
											<input class="form-control" placeholder="Descri&ccedil;&atilde;o" data-ng-model="companyDescription">                                                                        
										</div>
									</div>							
								</div>
							</div>				
						</div>
						
						<div class="box-footer" data-ng-show="companyUid!=undefined">
							<div class="col-md-12">
								<button type="button" data-ng-click="newCompany();" class="btn btn-success pull-right">&nbsp;&nbsp;Nova&nbsp;&nbsp;</button>
								<span class="pull-right">&nbsp;&nbsp;</span>
								<button type="button" data-ng-click="selCompany();" class="btn btn-default pull-right" data-ng-disabled="!companyUid">&nbsp;&nbsp;Abrir&nbsp;&nbsp;</button>
								<span class="pull-right">&nbsp;&nbsp;</span>
								<button type="button" data-ng-click="saveCompany();" class="btn btn-primary pull-right" data-ng-disabled="(companyName) ? false : true">&nbsp;Salvar&nbsp;</button>
								<span class="pull-right">&nbsp;&nbsp;</span>												
								<button type="button" data-ng-click="deleteCompany();" class="btn btn-danger pull-right" data-ng-disabled="!companyUid">&nbsp;Excluir&nbsp;</button>
							</div>
						</div> 
					</div>
				
				</div>
			</div>		
			
			<div class="row">				
				<div class="col-md-12">								
					<div class="callout callout-info" data-ng-show="selectedCompanyName" style="background-color: #3c8dbc !important; border-color: #156fa3 !important">
				 		<a href="#" class="pull-right"  data-ng-click="selCompany();"><i class="fa fa-times fa-2x"></i> </a>
						<h4  style="margin-bottom: 0px !important;"><i class="fa fa-industry"></i> {{selectedCompanyName}}</h4>		
						{{companyDescription}}						
					</div>				
				</div>
			</div>
			
			<div class="row" data-ng-show="selectedCompanyName">				 					
				<div class="col-md-3" style="height: 500px; overflow: auto">				
					<div class="form-group">			            
			        	<input type="text" class="form-control" id="input-select-node" placeholder="Pesquisar..." />
			        </div>					            										  
					<div id="treeview-company" class="treeview" style="font-size:14px;line-height:0.85"></div>				
				</div>				
				<div id="content" data-bind-unsafe-html="ajaxcontentCompany"></div>		
			</div>																    
		</div>
	</div>      
</div>

    
    