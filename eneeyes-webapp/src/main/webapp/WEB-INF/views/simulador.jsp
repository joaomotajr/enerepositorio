<div data-ng-controller="simuladorController">
		<br>
		<div class="row">						                                                    
			<div class="col-md-12">                                                        
				<div class="box box-primary">
					
					<div class="box-header">
					 	<h3 class="box-title">Gera Log para Detectores/Sensores</h3>
					</div>
					
					<div class="box-body">
			        	
			        	<div class="row">      		           
				            <form class="form" name="userForm">
					            <div class="col-md-12">
					            	 
					            	<div class="col-md-2">                                                                                                                            
										<div class="form-group">
											<label class="control-label">Empresa</label>
											
											<select class="form-control" data-live-search="true" 
					                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
					                                data-ng-options="item as item.name for item in companies | orderBy: 'name' track by item.uid" 
					                                         data-ng-model="selectedCompany" 
					                                         data-ng-change="changeCompany();">
					                                         <option value="">Selecione</option> 
					                        </select>																                                                                        
										</div>
									</div>
					            
						            <div class="col-md-2">                                                                                                                            
										<div class="form-group">
											<label class="control-label">Detector</label>
											
						                	<select class="form-control" data-live-search="true" 
					                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
					                                data-ng-options="item as item.companyDetectorName for item in CompanyDetectors | companyFilter:search | orderBy: 'name' track by item.companyDeviceId" 
					                                         data-ng-model="selectedCompanyDetector" 
					                                         data-ng-change="changeCompanyDetector();">
					                                         <option value="">Selecione</option> 
					                        </select>					                                                                                
											                                                                        
										</div>
									</div>		
																			
									<div class="col-md-2">
										<div class="form-group">
											<label class="control-label">Sensor</label>
											<input class="form-control" type="text" data-ng-model="selectedCompanyDetector.sensorName" disabled>																	                	
										</div>									                                                                                     
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label class="control-label">Valor</label>
											
												<input class="form-control" type="number" data-ng-model="valor" name="valor" min="-100"  max="{{selectedCompanyDetector.rangeMax}}" required>
																                	 
												<p data-ng-show="valor > selectedCompanyDetector.rangeMax" class="help-block text-red">Valor M&aacute;ximo : {{selectedCompanyDetector.rangeMax}} </p>											
												<p data-ng-show="!userForm.valor.$pristine && !userForm.valor.$error.required && userForm.valor.$error.min" class="help-block text-red">Valor Inv&aacute;lido</p>											
												<p data-ng-show="userForm.valor.$error.required && !userForm.valor.$pristine" class="help-block text-red">Valor Obrigat&oacute;rio</p>
											
										</div>                                                                    
									</div>         		
									<div class="col-md-2">
										<div class="form-group">
											<label class="control-label">A&ccedil;&atilde;o</label>
					        				<button type="button" class="btn btn-primary btn-xs form-control" data-ng-disabled="!userForm.$valid || (valor > selectedCompanyDetector.rangeMax)" data-ng-click="saveHistoric()">Save Historic</button>
					        			</div>
					        		</div>
			
					        		<div class="col-md-1">
										<div class="form-group">
											<label class="control-label">.</label>
					        				<button type="button" class="btn btn-primary btn-xs form-control" data-ng-click="clearHistoric()">Limpa</button>
					        			</div>
					        		</div>					        		

				        		</div>	        		
				        	</form>				
				        	
				        	<div class="col-md-3">  
					        	<div id="result" class="alert alert-success" role="alert" data-ng-show="msg" >
					           		<button type="button" class="close" ><span data-ng-click="msg='';">&times;</span></button>
					           		<strong>Alerta! </strong>{{msg}} 
					       		</div>      			        	
				       		</div>        	
				        	
			       		</div>
			       		
		       			<div class="row">       		
			       			<div class="col-md-12" style='font-size: 1.3em'>											       				
			       				<div class="col-md-2">
			       					<label data-ng-show="findedCompanyDevice.alarmDto">Range Max: </label><span> {{selectedCompanyDetector.rangeMax}}</span>
			       				</div>
			       				<div class="col-md-4">			       					
			       					<label data-ng-show="findedCompanyDevice.alarmDto">Alarme: </label><span> {{findedCompanyDevice.alarmDto.name}}</span>
			       				</div>
			       				<div class="col-md-2">
			       					<label data-ng-show="findedCompanyDevice.alarmDto">Alarm 1: </label><strong style='color: gray'> {{findedCompanyDevice.alarmDto.alarm1}}</strong>
			       				</div>
			       				<div class="col-md-2">
			       					<label data-ng-show="findedCompanyDevice.alarmDto">Alarm 2: </label><strong style='color: orange'> {{findedCompanyDevice.alarmDto.alarm2}}</strong>
			       				</div>
			       				<div class="col-md-2">
			       					<label data-ng-show="findedCompanyDevice.alarmDto">Alarm 3: </label><strong style='color: red'> {{findedCompanyDevice.alarmDto.alarm3}}</strong>
			       				</div>
			       			</div>       		
			       		</div>
					                                                       
					</div>
				</div>
				
			</div>											

		</div>	 
</div>