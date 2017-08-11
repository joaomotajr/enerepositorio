		
	<div data-ng-controller="generalController">
												
		<div class="row">				                                                    
			<div class="col-md-12">                                                        
				
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">Visão Geral</h3>					  
					</div>
					<div class="box-body">
						
						<div id="resumo" class="row" style="margin-left: -30px; margin-right: -30px;">
							<div class="col-lg-12">
								<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
									<a href="#" class="menuDisable" my-link="false">
										<div class="card red summary-inline">
											<div class="card-body" style="padding-bottom: 8px !important">
												<div style="float:left">
													<i class="icon fa fa-male fa-4x"></i>
													<br />
													<span>{{truncar(coordenadorTi.nome, 30);}}</span>
												</div>
												<div class="content">
													<div class="title">{{coordenacao.funcionarios}}</div>
													<div class="sub-title">Funcionários</div>
												</div>
												<div class="clear-both"></div>
											</div>
										</div>
									</a>
								</div>							
							</div>
						</div>												
														                                                       
					</div>
					
					<div class="box-footer">						                                                                
						<button type="button" data-ng-click="" class="btn btn-primary pull-right">Novo</button>
					</div>				
						                                                
				</div>
			</div>	
		</div>				
				
	</div>