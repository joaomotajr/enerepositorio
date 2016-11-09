<div data-ng-controller="companiesController">

	<div class="row">
	    <div class="col-md-12">
	    				
			<div class="row">
						
				<div class="col-md-12">

					<div class="box box-primary" style="margin-bottom: 8px;" data-ng-class="{'collapsed-box' : selectedCompanyName}">
					  
						<div class="box-header with-border">
							<h3 class="box-title">Cadastro</h3>
							<div class="box-tools pull-right"></div>
						</div>
						
						<div class="box-body">
							
							<div class="col-md-6">
								<br />
								<div class="box box-primary">
				                    <div class="box-header with-border"><strong style="font-size:1.4em">Pesquisar Empresa.</strong></div>
				                
				                    <div class="box-body">
				                        <select id="selCompany" class="form-control select2 select2-hidden-accessible"  
				                            style="width: 100%;" 
				                            tabindex="-1" 
				                            aria-hidden="true">
				                            	<option value="" selected="selected">Selecione</option>                              				                            	
				                                <option data-ng-repeat="company in companies" data-ng-model="company" value="{{company}}" > {{company.name}} </option>				                                                    
				                        </select>
				                        							                                                                    
				                    </div>        
				                </div>        
		                            
							</div>
							<div class="col-md-6">
								<br />
								<div class="form-group">
									<label class="control-label">Nome</label>                                                                        
									<input id="idCompanyName" class="form-control" placeholder="Nome da Empresa" data-ng-model="companyName">                                                                        
								</div>
								
								<div class="form-group">
									<label class="control-label">Descrição</label>                                                                        
									<input class="form-control" placeholder="Descrição" data-ng-model="companyDescription">                                                                        
								</div>
							</div>
						</div>
						
						<div class="box-footer">							
							<div class="col-md-12">
								<button type="button" data-ng-click="selCompany();" class="btn btn-success pull-right" data-ng-disabled="!companyUid">Selecionar</button>								
								<span class="pull-right">   </span>
								<button type="button" data-ng-click="saveCompany();" class="btn btn-primary pull-right" data-ng-disabled="(companyName) ? false : true">   Salvar   </button>
								<span class="pull-right">   </span>												
								<button type="button" data-ng-click="deleteCompany();" class="btn btn-danger pull-right" data-ng-disabled="(companyName) ? false : true">   Excluir   </button>
							</div>
						</div> 
					</div>
				
				</div>
			</div>		
			
			<div class="row">
				
				<div class="col-md-12">								
					<div class="callout callout-info"
				 		data-ng-show="selectedCompanyName" 
				 		style="padding: 5px 5px 5px 25px; margin:0 0 5px 0; background-color: #3c8dbc !important; border-color: #156fa3 !important">
				 		<button type="button" class="close" data-ng-click="selCompany();" >×</button>
						<h4><i class="fa fa-industry"></i> {{selectedCompanyName}}</h4>										
					</div>				
				</div>
			</div>
			
			<div class="row" data-ng-show="selectedCompanyName">		
				 					
				<div class="col-md-3" style="height: 500px; overflow: auto">				
					<div class="form-group">			            
			        	<input type="input" class="form-control" id="input-select-node" placeholder="Pesquisar..." />
			        </div>					            										  
					<div id="treeview-company" class="treeview" style="font-size:12px;line-height:0.85"></div>				
				</div>				
				
				<div id="content" data-bind-unsafe-html="ajaxcontentCompany"></div>													
			 	
			</div>			
			
			<div id="resultErro" class="alert alert-warning col-sm-6" role="alert" data-ng-show="msgErro" >
	            <button type="button" class="close" ><span data-ng-click="msgErro='';">&times;</span></button>
	            <strong>Alerta! </strong>{{msgErro}} 
	        </div>  
	        
	        <div id="resultInfo" class="alert alert-info alert-dismissible col-sm-6" role="alert" data-ng-show="msgInfo" >
                 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                 <strong>Atenção! </strong>{{msgInfo}} <span id="msgComplemento"></span>
             </div>                     
             
             <div id="resultDanger" class="alert alert-danger alert-dismissible col-sm-6" role="alert" data-ng-show="msgDanger" >
                 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                 <strong>Atenção! </strong>{{msgDanger}}                  
             </div>
				    
		</div>
	</div>
      
</div>

    
    