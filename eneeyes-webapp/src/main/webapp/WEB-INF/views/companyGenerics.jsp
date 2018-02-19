<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
	
	td.details-control {		
		background: url("/assets/plugins/datatables/images/details_open.png") no-repeat center center;		
		cursor: pointer;
	}	

	tr.shown td.details-control {
		background: url("/assets/plugins/datatables/images/details_close.png") no-repeat center center;
	}	
	
	.selected {		
		font-weight: 800;	
	}
	
</style>

<div class="col-md-9">
	<div data-ng-controller="companyGenericController">
		<div class="box box-primary">
			<div class="box-header with-border">			
				<strong style="font-size:1.4em"><i class='fa fa-rss'></i> {{selectedCompanyDevice.deviceType}} <span data-ng-show="selectedCompanyGeneric.name">-</span> {{selectedCompanyGeneric.name}}</strong>				
			</div>		
				
			<div class="box-body">
							
				<div class="nav-tabs-custom">
					<ul class="nav nav-tabs tabDetector">
				       	<li class="active"><a href="#tabCompanyDetector_1" id="stepTabDetector_1" data-toggle="tab">Cadastro</a></li>
				       	<li><a href="#tabCompanyDetector_2" id="stepTabDetector_2" data-toggle="tab">Configura&ccedil;&atilde;o</a></li>
				    	<li data-ng-hide="selectedCompanyGeneric" class="pull-right"><i title="[Nenhum Detector Associado ao Dispositivo]" class="fa fa-info-circle text-red"></i></li>				    	
				    </ul>
					
					<div class="tab-content">
				    	
				    	<div class="tab-pane active" id="tabCompanyDetector_1">
				    		<div class="row">
				    			<div class="col-md-12">
					    		<form name="userForm">					    		
								
								        <div class="col-md-4">
								        	<div class="form-group">
								                <label class="control-label">C&oacute;digo</label>
								                <input class="form-control" placeholder="C&oacute;digo do Dispositivo" data-ng-model="selectedCompanyGeneric.uid" readonly>
								            </div>	
								        </div>
								        <div class="col-md-4">
								            <div class="form-group">								            
								                <label class="control-label">Identifica&ccedil;&atilde;o *</label>
								                <span class="text-red" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Identifica&ccedil;&atilde;o Obrigatorio]</span>
									            <span class="text-red" data-ng-show="userForm.username.$error.maxlength">Tamanho M&aacute;ximo 8 caracteres</span>
												
												<input data-disallow-spaces class="form-control" style="text-transform:uppercase" 
								                	placeholder="Identifica&ccedil;&atilde;o do Detector (Sem Espa&ccedil;os)" 
								                	data-ng-model="selectedCompanyGeneric.name" data-ng-maxlength="8" name="username" 
								                	title="Identifica&ccedil;&atilde;o do Detector (Sem Espa&ccedil;os)"
								                	required>
								            </div>
								        </div>
								        
								        <div class="col-md-4">
								        	<div class="form-group">
								                <label class="control-label">Nr. de S&eacute;rie</label>
								                <input class="form-control" placeholder="Nro de S&eacute;rie do Detector" data-ng-maxlength="24" 
								                data-ng-model="selectedCompanyGeneric.serialNumber">
								            </div>	
										</div>
																			
								        <div class="col-md-12">
								            <div class="form-group">
								                <label class="control-label">Descri&ccedil;&atilde;o</label>
								                <input class="form-control" placeholder="Descri&ccedil;&atilde;o" data-ng-model="selectedCompanyGeneric.description">
								            </div>
								        </div>								    									
									
										<div class="col-md-12">
											<div class="form-group">
												<label class="control-label">Local</label>
												<input id="idUnitName" class="form-control" placeholder="Local" data-ng-model="selectedCompanyGeneric.local">
											</div>
										</div>
																	
					       		</form>
					       		</div>
				    		</div>				    			    					    	
				    	
				       		<div class="row">
				       			<div class="col-md-12">
				       				<button type="button" data-ng-click="saveCompanyDetector();" class="btn btn-primary pull-right" data-ng-disabled="(selectedCompanyGeneric.name && selectedCompanyGeneric.detectorDto.uid) ? false : true">&nbsp;Salvar&nbsp;</button>		       				
				       				<span class="pull-right">&nbsp;</span>
				       				<button type="button" data-ng-click="deleteCompanyDetector();" class="btn btn-danger pull-right" data-ng-disabled="(selectedCompanyGeneric.uid) ? false : true">&nbsp;Excluir&nbsp;</button>								
								</div>
							</div>						
				    	         
				       	</div><!-- /.tab-pane -->
				       	 
				       	<div class="tab-pane" id="tabCompanyDetector_2">
							   
							<div class="row" style="margin-right: 5px !important; margin-left: 5px !important;">			       		 
								<div data-ng-repeat="item in selectedCompanyGenericAlarms">
									
									<div class="col-md-10">
										<div class="panel panel-primary">								                
											<div class="panel-heading">
												<h2 class="panel-title" style="text-align:center;"><strong><i class="fa fa-rss" style="font-size:1.2em;"></i></strong> {{selectedCompanyGenericAlarm.companyDetectorName}}</h2>							
											</div>											   					               	
											<div class="panel-body">							            					                 										                	
												
											</div>
										</div>
									</div>									
								</div> 
								<!-- /.tab-repeat -->
							</div>				       		 
						</div>			     			     	
				   </div>			   						
				</div>
							
			</div>		
		</div>		
	</div>	
</div>