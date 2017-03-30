<div data-ng-controller="datatableController">    
	<div class="row">
		<div class="col-md-12">
		
			<div class="box box-primary">						
				<div class="box-header with-border"><strong style="font-size:1.4em">Datatables</strong></div>
				
				<div class="box-body">
					<div class="col-md-12">
					
						<div id="tableExample">
						    <div>
						        <p>jQuery DataTable and Master/detail using the same results</p>
						
						        <h1>DataTable</h1>
						        <table data-datatable data-options="dataTableOptions" data-items="contacts"></table>
						        
						        <h1>Master/detail</h1>
						        <ul data-ng-repeat="contact in contacts">
						            <li>
						                <label>
						                    <input type="checkbox" data-ng-model="contact.selected">
						                    [{{contact.id}}] {{contact.firstName}} {{contact.lastName}}
						                </label>
						            </li>
						        </ul>
						        
						        <h1>Selections</h1>
						        <code>{{selections()}}</code>
						        
						    </div>
						</div>										
						
					</div>		
				</div>		
				
			</div>	
	
		</div>
	</div>
</div>