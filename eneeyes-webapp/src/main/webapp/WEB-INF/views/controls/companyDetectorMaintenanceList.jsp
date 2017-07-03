
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
<!-- 				<td data-ng-if="item.historicMaintenaceType == 'CALIBRATION'">CALIBRAÇÃO</td>	 -->
<!-- 				<td data-ng-if="item.historicMaintenaceType == 'MAINTENANCE'">MANUTENÇÃO</td> -->
<!-- 				<td data-ng-if="item.historicMaintenaceType == 'DESCONHECIDO'">OUTROS</td> -->
				<td data-ng-if="item.historicMaintenaceType == 'CALIBRATION'" title="Calibração do Detector"><i class="fa fa-retweet"></i></td>	
				<td data-ng-if="item.historicMaintenaceType == 'MAINTENANCE'" title="Manutenção do Detector"><i class="fa fa-wrench"></i></td>
				<td data-ng-if="item.historicMaintenaceType == 'DESCONHECIDO'" title="Serviço não catalogado no Detector"><i class="fa fa-question-circle"></i>>OUTROS</td>
				
				<td>{{item.description}}</td>									
			</tr>                                                               
		</tbody>
	</table>												
