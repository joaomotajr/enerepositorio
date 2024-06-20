	
	<div data-ng-controller="CompanyDetectorMaintenanceHistoricController as CompanyDetectorMaintenanceHistoricController" style="background: Gainsboro">
		<br>					 
		<div class="row">
			<div class="col-md-1">
			</div>				                                                    
			<div class="col-md-10">		 
				
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">Cadastro / Edi&ccedil;&atilde;o</h3> <span data-ng-if="isFrom != 'MASTER'" style="font-size:1.2em; color:red"> (Acesso restrito apenas a usuário Master)</span>
						<span class="text-muted pull-right"><i class="fa fa-fa-search"></i></span>
					</div>					
					<div class="box-body" style="padding-bottom: 0px !important;">
						<div class="row">
							<div class="col-md-3">
								<label class="control-label">Empresa: </label>
					        	<jsp:include page="controls/companySelect.jsp"/>    	
							</div>
				        	<div class="col-md-3">
				        		<label class="control-label">Detector: </label>
				        		<select class="form-control" data-live-search="true" 
									style="width: 100%;" tabindex="-1" aria-hidden="true"                              
									data-ng-options="item as item.companyDetectorName for item in companyDetectors | companyFilter:search | detectorFilter:search2 | orderBy: 'companyDetectorName' track by item.companyDeviceId"
									data-ng-model="selectedCompanyDetector" 
									data-ng-change="changeCompanyDetector();">
									<option value="">Selecione</option> 
								</select>
							</div>
							<div class="col-md-6">
																    			
								<div class="col-md-6">											        	
									<div class="form-group">
										<label>Data de Entrega:</label>
					
										<div class='input-group date'>
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>                                                                 
											</div>
											<input type="text" class="form-control" readonly id="deliveryDate">
										</div>	
					
									</div>																															
								</div>
								<div class="col-md-6">
									<div class="form-group">								            
										<label class="control-label">Garantia <span style="font-size: 80%">(Dias)</span></label>											                
										<input class="form-control" type="number"
											placeholder="Dias" 
											data-ng-model="selectedCompanyDetector.garantyDays" name="garanty" 
											title="Prazo de Garantia do Fabricante em Dias."
											readonly>
									</div>
								</div>								
							</div>						
						</div>
						
						<div class="row">
							<div class="col-md-6">
							</div>	
							<div class="col-md-6">	
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Data de Instalação:</label>							                	
										<div class='input-group date'>
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>                                                                
											</div>											
											<input type="text" class="form-control" readonly id="installDate">
										</div>							                					                							                                                
									</div>     			                										        											        		
								</div>
								<div class="col-md-6">
									<div class="form-group">								            
										<label class="control-label">Calibração: <span style="font-size: 80%">(Dias)</span></label>
										
										<input class="form-control" type="number" style="background:azure" 
											placeholder="Dias" 
											data-ng-model="selectedCompanyDetector.maintenanceInterval" name="maintenanceInterval" 
											title="Intervalo Recomendado de Manutenção/Calibração."
										readonly>
									</div>
								</div>		
							</div>	
						</div>
						<hr>
				
						<div class="row" data-ng-class="{'disableDivOver': !selectedCompanyDetector }">				
							<div class="col-md-6">
								<div class="box box-primary" data-ng-class="(selectedCompanyDetector) ? 'box-primary' : 'box-danger'">
									<div class="box-header with-border">
										<Label class="box-title">Hist&oacute;rico de Manuten&ccedil;&atilde;o do Detector</label>
										<div class="box-tools pull-right" title="Clique para Epandir">
											<button class="btn btn-box-tool" data-widget="collapse">
												<i class="fa fa-minus"></i>
											</button>
										</div>					
									</div>
									<div class="list-group" style="max-height: 250px ! important; height:auto; overflow: auto; font-size: 0.8em  ! important">									
										<div class="box-body">																			                    								               
					            			<jsp:include page="controls/companyDetectorMaintenanceList.jsp"/>
					            		</div>
				            		</div>
				            	</div>	
				            </div>			                    	
				        	<div class="col-md-6">
					        	<div class="box box-primary" data-ng-class="(selectedCompanyDetector) ? 'box-primary' : 'box-danger'">
									<div class="box-header">
										<h3 class="box-title">Inclus&atilde;o</h3>
										<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
									</div>					
									<div class="box-body">			  			      	
					        	
										<form class="form" name="userForm">
											<div class="row">
												<div class="col-md-6">											                
													<label class="control-label">Tipo</label>
													<div class="input-group">
														<div class="input-group-addon">
															<i data-ng-if="!selectedHistoricMaintenaceType.uid" title="Escolha o Tipo de Serviço" class="fa fa-hand-o-right text-red"></i>															
															<i data-ng-if="selectedHistoricMaintenaceType.uid == '1'" title="Manutenção do Detector" class="fa fa-retweet"></i>														                                                                 
															<i data-ng-if="selectedHistoricMaintenaceType.uid == '2'" title="Calibração do Detector" class="fa fa-wrench"></i>
															<i data-ng-if="selectedHistoricMaintenaceType.uid == '3'" title="Outros Tipos de Serviços no Detector" class="fa fa-circle"></i>
														</div>
														<div data-ng-class="{'has-error': !selectedHistoricMaintenaceType }" 
															data-ng-init="historicMaintenaceTypes = [{name: 'MANUTEN&Ccedil&Atilde;O', uid :  1, icon:'fa-retweet'},{name: 'CALIBRA&Ccedil&Atilde;O', uid : 2, icon:'fa-wrench' },{name: 'OUTROS', uid : 3, icon:'fa-circle' }];">
															<select name="nameSelectedHistoricMaintenaceType" class="form-control" data-live-search="true" 
																	style="width: 100%;" tabindex="-1" aria-hidden="true"                              
																		data-ng-options="item as item.name for item in historicMaintenaceTypes | orderBy: 'name' track by item.uid" 
																		data-ng-model="selectedHistoricMaintenaceType" required>
																				 <option value="">Selecione</option> 
															</select>									                        
														</div>
													</div>
												</div>													
											</div>
											
											
											<div class="row">				    			
												<div class="col-md-6">											        	
													<div class="form-group">
														<label class="control-label">Data: </label> <span style="font-size: 0.8em; color:red" data-ng-hide='dateOneMaior'> Menor que &uacute;ltima Registrada!</span> 
														<div class="input-group" data-ng-class="{'has-error': !dateOneValid }">                                                            
															<div class="input-group-addon">
																<i class="fa fa-calendar" data-ng-show='dateOneValid'></i>
																<i class="fa fa-calendar-times-o" style="color:red" data-ng-hide='dateOneValid' title="Data Inválida"></i>                                                                 
															</div>
															<input id="dateOne" type="text" class="form-control" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="" data-ng-keyup="validDateOne($event);">															                                                            
														</div>
													</div>						                										        											        		
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">Data: (Confirmar) </label> <span style="font-size: 0.8em; color:red" data-ng-hide='dateTwoEqual'> Datas Diferentes</span>
														<div class="input-group" data-ng-class="{'has-error': !dateTwoValid }">                                                            
															<div class="input-group-addon">
																<i class="fa fa-calendar" data-ng-show='dateTwoValid'></i>
																<i class="fa fa-calendar-times-o" style="color:red" data-ng-hide='dateTwoValid' title='Data Inv&oacute;lida'></i>                                                                 
															</div>
															<input id="dateTwo" type="text" class="form-control" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="" data-ng-keyup="validDateTwo($event);">															                                                            
														</div>
													</div>
												</div>
											</div>
											<div class="row">				    			
												<div class="col-md-12">
													<div class="form-group">
														<label class="control-label">Detalhes Sobre a Instala&ccedil;&atilde;o:</label>
														<input class="form-control" placeholder="Descri&ccedil;&atilde;o" data-ng-model="description">
													</div>
												</div>
											</div>
										</form>
										
									</div>
									
									<div class="box-footer">
										<button type="button"  data-ng-click="clearForm()" class="btn btn-default">Cancelar</button>                                                                
										<button type="button"  data-ng-click="saveCompanyDetectorMaintenaceHistoric();" class="btn btn-primary" 
											 data-ng-disabled="(selectedCompanyDetector && dateOneValid && dateTwoValid && selectedHistoricMaintenaceType && description ) ? false : true">Incluir</button>								                                                                
									</div>
								</div>
				        	</div>
				        		    			                    			                            
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
		
		
		
	
