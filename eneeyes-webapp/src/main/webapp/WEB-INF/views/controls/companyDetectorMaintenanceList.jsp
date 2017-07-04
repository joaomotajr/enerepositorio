
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
				<td>{{item.date  | date:"dd/MM/yyyy" }}</td>
				<td data-ng-if="item.historicMaintenaceType == 'DESCONHECIDO'" title="Calibra��o do Detector"><i class="fa fa-question-circle"></i></td>
				<td data-ng-if="item.historicMaintenaceType == 'CALIBRATION'" title="Calibra��o do Detector"><i class="fa fa-retweet"></i></td>	
				<td data-ng-if="item.historicMaintenaceType == 'MAINTENANCE'" title="Manuten��o do Detector"><i class="fa fa-wrench"></i></td>
				<td data-ng-if="item.historicMaintenaceType == 'OUTROS'" title="Servi�o n�o catalogado no Detector"><i class="fa fa-circle"></i></td>
				
				<td>{{item.description}}</td>									
			</tr>                                                               
		</tbody>
	</table>												
