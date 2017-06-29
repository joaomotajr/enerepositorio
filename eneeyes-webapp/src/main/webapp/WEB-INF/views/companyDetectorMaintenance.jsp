	
	<div data-ng-controller="CompanyDetectorMaintenanceHistoricController as CompanyDetectorMaintenanceHistoricController" style="background: Gainsboro">
		<br>					 
		<div class="row">
			<div class="col-md-1">
			</div>				                                                    
			<div class="col-md-10">		 
			
				<div class="box box-primary" style="padding-bottom: 0px; !important; margin-bottom: 0px !important;">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edição</h3>
						<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
					</div>					
					<div class="box-body" style="padding-bottom: 0px; !important">
						<div class="row">
							<div class="col-md-3">
								<label class="control-label">Empresa: </label>
					        	<jsp:include page="controls/companySelect.jsp"/>    	
							</div>
				        	<div class="col-md-3">
				        		<label class="control-label">Detector: </label>
				        		<jsp:include page="controls/companyDetectorSelect.jsp"/>				        		
							</div>
							<div class="col-md-6">
							</div>
						</div>
						<hr>
						<div class="row">
							<form class="form" name="userForm">
							<div class="col-md-3"">											                
								<label class="control-label">Tipo
									<span class="text-red" data-ng-show="userForm.selectedHistoricMaintenaceType.$dirty && userForm.selectedHistoricMaintenaceType.$invalid">  [Campo Obrigatório]</span>
								</label>
								<div data-ng-class="{'has-error': userForm.selectedHistoricMaintenaceType.$dirty && userForm.selectedHistoricMaintenaceType.$invalid}">
									<select name="selectedHistoricMaintenaceType" class="form-control" data-live-search="true" 
											style="width: 100%;" tabindex="-1" aria-hidden="true"                              
												data-ng-options="item as item.name for item in historicMaintenaceTypes | orderBy: 'name' track by item.uid" 
														 data-ng-model="selectedHistoricMaintenaceType" required>
														 <option value="">Selecione</option> 
									</select>									                        
								</div>
							</div>
							</form>							
							
						</div>
						
						<div class="col-md-12">												    
							<div class="box box-primary collapsed-box">                
			                	<div class="box-header with-border">
			                		<Label class="box-title">Dados de Manutenção/Calibração</label>
									<div class="box-tools pull-right" title="Clique para Epandir">
										<button class="btn btn-box-tool" data-widget="collapse">
											<i class="fa fa-plus"></i>
										</button>
									</div>			                		
			                	</div>
			                	
			                    <div class="box-body">			                    								               
			                        <jsp:include page="companyDetectorMaintenanceForm.jsp"/>			                    	
			                    </div>			                    			                            
			                </div>
						</div>					
						<div class="col-md-12">												                    								               
			            	<jsp:include page="controls/companyDetectorMaintenanceList.jsp"/>			                    	
			        	</div>	    			                    			                            
			                
					</div>	
				</div>
				
				
			</div>
			<div class="col-md-1">
			</div>				
		</div>
		
		<div class="row">
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<br />
			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<br />		
		</div>
	</div>
