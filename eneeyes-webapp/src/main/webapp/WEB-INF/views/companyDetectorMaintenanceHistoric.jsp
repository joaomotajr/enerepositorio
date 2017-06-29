	<form name="userFormMaintance">
		<div class="row">				    			
			<div class="col-md-3">											        	
				<div class="form-group">
					<label>Data de Entrega:</label>
					<div class="input-group">                                                            
						<div class="input-group-addon">
							<i class="fa fa-calendar" data-ng-hide='deliveryDateValid'></i>
							<i class="fa fa-calendar-times-o" style="color:red" data-ng-show='deliveryDateValid' title="Data Inválida"></i>                                                                 
						</div>
						<input id="deliveryDate" type="text" class="form-control" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="" data-ng-keyup="validDeliveryDate($event);">															                                                            
					</div>
				</div> 
																																
			</div>
			<div class="col-md-3">
				<div class="form-group">								            
					<label class="control-label">Garantia <span style="font-size: 80%">(Dias)</span></label>											                
					<input class="form-control" type="number"
						placeholder="Dias" 
						data-ng-model="selectedCompanyDetector.garantyDays" name="garanty" 
						title="Prazo de Garantia do Fabricante em Dias."
					required>
				</div>
			</div>
			
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label">Detalhes sobre a Entrega: </label>
					<input class="form-control" placeholder="Descrição" data-ng-model="selectedCompanyDetector.descriptionDelivery">
				</div>
			</div>
		</div>						               		
		
		<div class="row">				    			
			<div class="col-md-3">											        	
				<div class="form-group">
					<label>Data de Instalação:</label>
					<div class="input-group">                                                            
						<div class="input-group-addon">
							<i class="fa fa-calendar" data-ng-hide='installDateValid'></i>
							<i class="fa fa-calendar-times-o" style="color:red" data-ng-show='installDateValid' title="Data Inválida"></i>                                                                 
						</div>
						<input id="installDate" type="text" class="form-control" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="" data-ng-keyup="validInstallDate($event);">															                                                            
					</div>
				</div>						                										        											        		
			</div>
			<div class="col-md-3">
				<div class="form-group">								            
					<label class="control-label">Manutenção: <span style="font-size: 80%">(Dias)</span></label>
					
					<input class="form-control" type="number"
						placeholder="Dias" 
						data-ng-model="selectedCompanyDetector.maintenanceInterval" name="maintenanceInterval" 
						title="Intervalo Recomendado de Manutenção/Calibração."
					required>
				</div>
			</div>
			
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label">Detalhes Sobre a Instalação:</label>
					<input class="form-control" placeholder="Descrição" data-ng-model="selectedCompanyDetector.descriptionInstall">
				</div>
			</div>
		</div>
	
	</form>
	<hr>
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
								<th>Detalhe(s)</th>																																						
							</tr>
						</thead>
						<tbody>                                                        
							<tr data-ng-repeat="item in companyDetectorMaintenanceHistoric.list">
								
								<td>{{item.uid}}</td>
								<td>{{item.date  | date:"dd/MM/yyyy HH:mm" }}</td>	
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