<div data-ng-controller="navegacaoController">
	<div class="row">
        <div class="col-md-12">
        	<div class="row">      		           
	            <form class="form" name="userForm">
		            <div class="col-md-10">
		            
			            <div class="col-md-3">                                                                                                                            
							<div class="form-group">
								<label class="control-label">CompanyDetector</label>
			                	<select class="form-control" data-live-search="true" 
		                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                                data-ng-options="item as item.name for item in CompanyDetectorss | orderBy: 'name' track by item.uid" 
		                                         data-ng-model="selectedCompanyDetector" >
		                                         <option value="">Selecione</option> 
		                        </select>					                                                                                
								                                                                        
							</div>
						</div>												
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">Sensor</label>
			                	<select class="form-control" 
		                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                                data-ng-options="item as item.name for item in selectedCompanyDetector.detectorDto.sensorsDto | orderBy: 'name' track by item.uid" 
		                                         data-ng-model="selectedCompanySensor">
		                                         <option value="">Selecione</option> 
		                        </select>						                                                
							</div>                                                                    
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">Valor</label>
			                	<input class="form-control" data-ng-model="companyValor">						                                                
							</div>                                                                    
						</div>         		
						<div class="col-md-2">
							<div class="form-group">
								<label class="control-label">Ação</label>
		        				<button type="button" class="btn btn-primary btn-sm form-control" data-ng-click="saveHistoric()">Save Historic</button>
		        			</div>
		        		</div>        	        		
	        		</div>
	        	</form>	        			        	
       		</div>       		
       		<hr />
       		<div class="row">        		
        		<div class="row">      		           
		            <form class="form" name="userForm">
			            <div class="col-md-10">		            			            
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label">Data inicio</label>				                	
				                	 <!--  <input class="form-control" type="text" data-datepicker data-ng-model="dateIn" />  -->
				                	 <input type="text" class="form-control" data-date-format="dd/mm/yyyy" datemonopicker ng-model="dateIn" data-mask="99/99/9999" mask/>
				                					                			                                                
								</div>                                                                    
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label">Data Fim</label>				                	
				                	<!-- <input class="form-control" type="text" data-datepicker data-ng-model="dateOut" /> -->
				                	<input type="text" class="form-control" data-date-format="dd/mm/yyyy" datemonopicker ng-model="dateOut" data-mask="99/99/9999" mask/>				                							                                                
								</div>                                                                    
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label class="control-label">Ação</label>
			        				<button type="button" class="btn btn-primary btn-sm form-control" data-ng-click="getHistoricInterval()" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">Get Interval</button>
			        			</div>
			        		</div>         		
							<div class="col-md-2">
								<div class="form-group">
									<label class="control-label">Ação</label>
			        				<button type="button" class="btn btn-primary btn-sm form-control" data-ng-click="getHistoric()">Get All Historic</button>
			        			</div>
			        		</div>        	        		
			        		<div class="col-md-2">
								<div class="form-group">									
			        			</div>
			        		</div>
		        		</div>
		        	</form>	        			        	
	       		</div>        		        		
       		</div>
       		
       		<hr />
       		<div class="row">        		
        		<div class="row">      		           
		            <form class="form" name="userForm">
			            <div class="col-md-12">		            			            
							<div class="col-md-9">									
								<div class="btn-group btn-group-sm" role="group" aria-label="Basic example">
								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(1);"   data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 hora</button>
								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(6);"   data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  6h  </button>
								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(12);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  12h </button>
								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(24);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 dia </button>
								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(48);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  2d  </button>
								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(96);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  4d  </button>
								  <button type="button" class="btn btn-default" data-ng-click="getLastMonth();" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true"> 30d  </button>
								</div>                                                              
							</div>							        	        		
		        		</div>
		        	</form>	        			        	
	       		</div>        		        		
       		</div>       		
       		
       		<hr />
       		<div class="row">
        		<div class="col-md-12">           		        	        		
        			<button type="button" class="btn btn-primary" data-ng-click="getCompanyDetector()">Get All CompanyDetector Info</button>
        		</div>	        		
       		</div>
       		<br />
        	<div class="row">
        		<div class="col-md-12">	        	
	                <dl class="dl-horizontal" data-ng-repeat="item in CompanyDetectors">
	                    <dt>Dtct: {{item.name}} - ID: {{item.uid}}</dt>
	                        <dd>Sns1: {{item.detectorDto.sensorsDto[0].name}} - ID: {{item.detectorDto.sensorsDto[0].uid}}  Min|Max: {{item.detectorDto.sensorsDto[0].rangeMin}} | {{item.detectorDto.sensorsDto[0].rangeMax}}</dd>	                        
	                        <dd>Sns2: {{item.detectorDto.sensorsDto[1].name}} - ID: {{item.detectorDto.sensorsDto[1].uid}}  Min|Max: {{item.detectorDto.sensorsDto[1].rangeMin}} | {{item.detectorDto.sensorsDto[1].rangeMax}}</dd>
		            </dl>	            
        		</div>
        	</div>
        	<!-- 
        	<div class="row">        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveCompanyDevice()">Save CompanyDevice</button>        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveCompanyDevices()">Save CompanyDevices</button>        		
        		<button type="button" class="btn btn-primary" data-ng-click="getCompanyDevice()">Get CompanyDevice</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getOneCompanyDevice()">Get One CompanyDevice</button>
        		<button type="button" class="btn btn-primary" data-ng-click="deletarCompanyDevice()">Deletar CompanyDevice</button>
        		
        		        		
       		</div>
        	<div class="row">        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveArea()">Save Area</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getArea()">Get Area</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getOneArea()">Get One Area</button>
        		<button type="button" class="btn btn-primary" data-ng-click="deletarArea()">Deletar Area</button>        		
       		</div>
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveUnit()">Save Unit</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getUnit()">Get Unit</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getOneUnit()">Get One Unit</button>        		
        		<button type="button" class="btn btn-primary" data-ng-click="deletarUnit()">Deletar Unit</button>        		
       		</div>
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveCompany()">Save Company</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getCompany()">Get Company</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getOneCompany()">Get One Company</button>
        		<button type="button" class="btn btn-primary" data-ng-click="deletarCompany()">Deletar Company</button>        		
       		</div>
       		<hr />
       		
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveController()">Save Controller</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getController()">Get Controller</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getOneController()">Get One Controller</button>
        		<button type="button" class="btn btn-primary" data-ng-click="deletarController()">Delete Controller</button>          		
       		</div>
       		
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveGas()">Save Gas</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getGas()">Get Gas</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getOneGas()">Get One Gas</button>
        		<button type="button" class="btn btn-primary" data-ng-click="deletarGas()">Delete Gas</button>        		
       		</div>
       		
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveSensor()">Save Sensor</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getSensor()">Get Sensor</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getOneSensor()">Get One Sensor</button>
        		<button type="button" class="btn btn-primary" data-ng-click="deletarSensor()">Delete Sensor</button>        		
       		</div>
       		
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveTransmitter()">Save Transmitter</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getTransmitter()">Get Transmitter</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getOneTransmitter()">Get One Transmitter</button>   
				<button type="button" class="btn btn-primary" data-ng-click="deletarTransmitter()">delete Transmitter</button>        		     		
       		</div>
       		<br />
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" data-ng-click="saveDetector()">Save Detector</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getDetector()">Get Detector</button>
        		<button type="button" class="btn btn-primary" data-ng-click="getOneDetector()">Get One Detector</button>
        		<button type="button" class="btn btn-primary" data-ng-click="deletarDetector()">delete Detector</button>        		
       		</div>
       		-->
	    </div>
    </div>    
</div>