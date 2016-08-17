<div ng-controller="companyController">

	<!--    
	<script type="text/ng-template" id="categoryTree">
        {{ node.text }}
        <ul class="list-group" ng-if="node.nodes">
            <li class="list-group-item node-treeview1" ng-repeat="node in node.nodes" ng-include="'categoryTree'">
				<span class="icon expand-icon glyphicon glyphicon-minus"></span>
				<span class="icon node-icon"></span>           
            </li>
        </ul>
    </script>	
     -->
				
	<div class="row">
	    <div class="col-md-12">
	    				
			<div class="row">
						
				<div class="col-md-12">
				
					<!-- DIRECT CHAT PRIMARY -->
					<div class="box box-primary" style="margin-bottom: 8px;" ng-class="{'collapsed-box' : selectedCompanyName}">
					  
						<div class="box-header with-border">
							<h3 class="box-title">Cadastro</h3>
							<div class="box-tools pull-right">
								<!-- <span data-toggle="tooltip" title="3 New Messages" class="badge bg-light-blue">3</span>-->
								<!--  <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>  -->
								<!-- <button class="btn btn-box-tool" data-toggle="tooltip" title="Contacts" data-widget="chat-pane-toggle"><i class="fa fa-comments"></i></button>-->
								<!-- <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>-->
							</div>
						</div><!-- /.box-header -->
						
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
				                                <option ng-repeat="company in companies" ng-model="company" value="{{company}}" > {{company.name}} </option>				                                                    
				                        </select>
				                        							                                                                    
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
									<label class="control-label">Descri��o</label>                                                                        
									<input class="form-control" placeholder="Descri��o" ng-model="companyDescription">                                                                        
								</div>
							</div>
						</div><!-- /.box-body -->
						
						<div class="box-footer">							
							<div class="col-md-12">
								<button type="button" ng-click="selCompany();" class="btn btn-success pull-right" ng-disabled="!companyUid">Selecionar</button>								
								<span class="pull-right">�� </span>
								<button type="button" ng-click="saveCompany();" class="btn btn-primary pull-right" ng-disabled="(companyName) ? false : true">���Salvar���</button>
								<span class="pull-right">�� </span>												
								<button type="button" ng-click="deleteCompany();" class="btn btn-danger pull-right" ng-disabled="(companyName) ? false : true">���Excluir���</button>
							</div>
						</div><!-- /.box-footer-->
					</div><!--/.direct-chat -->
				
				</div>
			</div>		
			
			<div class="row">
				
				<div class="col-md-12">								
					<div class="callout callout-info"
				 		ng-show="selectedCompanyName" 
				 		style="padding: 5px 5px 5px 25px; margin:0 0 5px 0; background-color: #3c8dbc !important; border-color: #156fa3 !important">
				 		<button type="button" class="close" ng-click="selCompany();" >�</button>
						<h4>{{selectedCompanyName}}</h4>										
					</div>				
				</div>
			</div>
			
			<div class="row" ng-show="selectedCompanyName">		
				 					
				<div class="col-md-3" style="height: 500px; overflow: auto">				
					<div class="form-group">			            
			        	<input type="input" class="form-control" id="input-select-node" placeholder="Pesquisar..." value="">
			        </div>					            										  
					<div id="treeview-company" class="treeview" style="font-size:12px;line-height:0.85"></div>				
				</div>				
				
				<div id="content" bind-unsafe-html="ajaxcontentCompany"></div>					
			 	
			</div>			
			
			<div id="resultErro" class="alert alert-warning col-sm-6" role="alert" ng-show="msgErro" >
	            <button type="button" class="close" ><span ng-click="msgErro='';">&times;</span></button>
	            <strong>Alerta! </strong>{{msgErro}} 
	        </div>  
	        
	        <div id="resultInfo" class="alert alert-info alert-dismissible col-sm-6" role="alert" ng-show="msgInfo" >
                 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                 <strong>Aten��o! </strong>{{msgInfo}} <span id="msgComplemento"></span>
             </div>                     
             
             <div id="resultDanger" class="alert alert-danger alert-dismissible col-sm-6" role="alert" ng-show="msgDanger" >
                 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                 <strong>Aten��o! </strong>{{msgDanger}}                  
             </div>
				    
		</div>
	</div>
      
</div>

    
    