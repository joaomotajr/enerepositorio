<div data-ng-controller="companyController" id="company.html">    

	<div class="col-md-9">
	
		<div class="box box-primary">						
			<div class="box-header with-border"><strong style="font-size:1.4em">Unidades</strong>
			<a href="#" class="text-muted pull-right" data-ng-click="mapsCompanyUnits();"><i class="fa fa-map"></i></a>
			</div>
			
			<div class="box-body">
				<div class="col-md-12">
					<label>Mapas das Unidades</label>						
					<div style="max-width: 800px; overflow: auto">					
				        <div id="mapCompany" style="height: 500px; width: 800px"></div>
					</div>					
					
				</div>		
			</div>		
			
		</div>	

	</div>
</div>