	<div class="row">
		<div class="col-md-6">			
			<div class="box box-primary collapsed-box">
				<div class="box-header with-border">
					<Label class="box-title">Histórico de Manutenção do Detector</label>
					<div class="box-tools pull-right" title="Clique para Epandir">
						<button class="btn btn-box-tool" data-widget="collapse">
							<i class="fa fa-plus"></i>
						</button>
					</div>					
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>					
								<th>ID</th>
								<th>Data</th>
								<th>Tipo</th>																
								<th>Detalhe(s)</th>																																						
							</tr>
						</thead>
						<tbody>                                                        
							<tr data-ng-repeat="item in companyDetectorMaintenanceHistoric.list">
								
								<td>{{item.uid}}</td>
								<td>{{item.date  | date:"dd/MM/yyyy HH:mm" }}</td>
								<td>{{item.historicMaintenaceType}}</td>	
								<td>{{item.description}}</td>									
							</tr>                                                               
						</tbody>
					</table>												
				
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
	</div>