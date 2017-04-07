<div data-ng-controller="datatableController">    
	<div class="row">
		<div class="col-md-12">
		
			<div class="box box-primary">						
				<div class="box-header with-border"><strong style="font-size:1.4em">Logs de Alarmes</strong></div>
				
				<div class="box-body">
					<div class="col-md-12">
					
						<div id="tableExample">
						    <div>						        
						        <table id='idDatatable' data-datatable data-options="dataTableOptions" data-items="listAllDashCompaniesAlarm.list"></table>
						      						        
						    </div>
						</div>										
						
					</div>		
				</div>		
				
			</div>	
	
		</div>
	</div>	
	
	<div id="modalShowMessages" class="modal">                
		<div class="modal-dialog" role="document">
			<div class="modal-content">                            
				<div class="modal-body"style="padding-bottom: 0px; !important">
				
					<div class="panel panel-default">
						<div class="panel-heading" style="text-align:center;font-size:1.5em">
							<strong>Historico do Alarme</strong>														
						</div>														                                                                           
					</div>
										
															
					<div class="box-body">
						<form class="form" name="userForm">		
						
							<div class="row">											
								<div class="col-md-12">																		
																				
									<div class="box box-info box-solid" data-ng-if="selectedPositionAlarmMessages.length > 0">				                    
										<div class="box-header with-border"><i class="fa fa-envelope"></i> Mensagens:
										</div>
										<div class="box-body">
											<div style="max-height: 200px; height:auto; overflow: auto">
												<dl class="dl-horizontal" data-ng-repeat="item in selectedPositionAlarmMessages">																													
													<dt>Data/Hora: {{item.lastUpdate | date:'yyyy-MM-dd HH:mm:ss'}}</dt>
														<dd>{{item.message}}</dd>														 
												</dl>
											</div>                                                                    
										</div>
									</div>								
								</div>												  
							</div>    
																								
						</form>
					</div>
					
									
				</div>
				
				<div class="modal-footer">						
					<button type="button" class="btn btn-default" data-dismiss="modal">OK</button>									                                
				</div>
				
			</div>
		</div>		
	</div>
	
</div>