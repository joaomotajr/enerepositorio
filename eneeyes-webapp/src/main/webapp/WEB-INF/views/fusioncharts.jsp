		
	<div data-ng-controller="fusionchartsController">
												
		<div class="row">				                                                    
			<div class="col-md-10">                                                        
				<div class="box box-primary" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
					<div class="box-header">
					  <h3 class="box-title">Fusion Charts Samples</h3>					  
					</div>
					<div class="box-body">
					
					   	<div data-fusioncharts
						    data-width="300"
						    data-height="200"
						    data-type="column2d"
						    data-datasource="{{dataSource}}">
						</div>		
						
						<div data-fusioncharts
						    data-width="600"						    
						    data-height="400"						    
						    data-type="msline"
						    data-chart="{{attrs}}"
						    data-categories="{{categories}}"
						    data-dataset="{{dataset}}">
					    </div>
					    
					    <div data-fusioncharts 
						    data-width="600" 
						    data-height="400"
						    data-type="pie3d"
						    data-datasource="{{myDataSource}}">
						</div>						
						
						<div data-fusioncharts							
						    data-width="400"
						    data-height= "300"
						    data-chart="{{atributos}}"						    						    						    
						    data-type="angulargauge"
						    data-colorrange="{{colors}}"
						    data-theme= "fint"
						    data-datasource="{{ggDataSource}}">
						</div>						
																		                                                       
					</div>
					
					<button type="button" data-ng-click="clearForm();" class="btn btn-primary pull-right">Novo</button>					
						                                                
				</div>
			</div>	
		</div>				
				
	</div>