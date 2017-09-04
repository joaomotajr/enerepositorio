		
	<div data-ng-controller="fusionchartsController">
												
		<div class="row">				                                                    
			<div class="col-md-12">                                                        
				<div class="box box-primary" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
					<div class="box-header">
					  <h3 class="box-title">Fusion Charts Samples</h3>					  
					</div>
					<div class="box-body">
						
						<div class="row">
							<div class="col-md-5">				 
						   	<div data-fusioncharts
							    data-width="300"
							    data-height="200"
							    data-type="column2d"
							    data-datasource="{{dataSource}}">
							</div>
							</div>		
							<div class="col-md-7">
							<div data-fusioncharts
							    data-width="500"						    
							    data-height="300"						    
							    data-type="msline"
							    data-chart="{{attrs}}"
							    data-categories="{{categories}}"
							    data-dataset="{{dataset}}">
						    </div>
						    </div>
					    </div>
					    <div class="row">
					    	<div class="col-md-6">
							    <div data-fusioncharts 
								    data-width="500" 
								    data-height="300"
								    data-type="pie3d"
								    data-datasource="{{myDataSource}}">
								</div>						
							</div>
							<div class="col-md-6"> 
								
							</div>						
						</div>
						<div class="row">
							<div class="col-md-6">
								<div data-fusioncharts							
								    data-width="400"
								    data-height= "300"						    						    						    						    
								    data-type="angulargauge"						    
								    data-theme= "fint"
								    data-datasource="{{super}}">
								</div>
							</div>
							<div class="col-md-6">
								<div data-fusioncharts							
								    data-width="200"
								    data-height= "350"
								    data-type="cylinder"
								    data-theme= "fint"
								    data-datasource="{{cylinder}}">
								</div>
							</div>
						</div>			
						<div class="row">
							<div class="col-md-6">
								<div data-fusioncharts							
								    data-width="400"
									data-height= "200"						    						    						    						    
									data-chart="{{linearAtributos}}"
								    data-type="hlineargauge"						    
									data-theme= "fint"									
									data-colorrange="{{linearColors}}">
								</div>
							</div>
							<div class="col-md-6">
								<div data-fusioncharts							
								    data-width="200"
									data-height= "400"	
									data-chart="{{hledAtributos}}"						    						    						    					    						    						    						    
								    data-type="vled"						    																		
									data-colorrange="{{linearColors}}">
								</div>
							</div>
						</div>
							

					</div>
					
					<button type="button" data-ng-click="clearForm();" class="btn btn-primary pull-right">Novo</button>					
						                                                
				</div>
			</div>	
		</div>				
				
	</div>