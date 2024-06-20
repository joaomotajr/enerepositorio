	<form name="userForm">
		<div class="row">				    			
			<div class="col-md-3">											        	
				<div class="form-group">
					<label>Data de Entrega:</label>

					<div class='input-group date' id='datePicker1'>
						<div class="input-group-addon">
							<i class="fa fa-calendar"></i>                                                                 
						</div>
						<input type="text" class="form-control" readonly>
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
					<label class="control-label">Data de Instalação:</label>							                	
					<div class='input-group date' id='datePicker2'>
						<div class="input-group-addon">
							<i class="fa fa-calendar"></i>                                                                
						</div>
						<input type="text" class="form-control" readonly>
					</div>							                					                							                                                
				</div>     			                										        											        		
			</div>
			<div class="col-md-3">
				<div class="form-group">								            
					<label class="control-label">Calibração: <span style="font-size: 80%">(Dias)</span></label>
					
					<input class="form-control" type="number" style="background:azure" 
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