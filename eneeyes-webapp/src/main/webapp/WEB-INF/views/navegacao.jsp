	
	<div data-ng-controller="navegacaoController">
        <div class="row">
        <div class="col-md-12"> 	
        	<div class="row">      		           
	            <form class="form" name="userForm">
		            <div class="col-md-6">
			            <div class="col-md-3">                                                                                                                            
							<div class="form-group">
								<label class="control-label">CompanyDetector</label>
			                	<input class="form-control" data-ng-model="companyDetectorUid">					                                                                                
								                                                                        
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">Sensor</label>
			                	<input class="form-control" data-ng-model="companySensorUid">						                                                
							</div>                                                                    
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">Valor</label>
			                	<input class="form-control" data-ng-model="companyValor">						                                                
							</div>                                                                    
						</div>         		
						<div class="col-md-3">
							<div class="form-group">
								<label>Ação</label>
		        				<button type="button" class="btn btn-primary" data-ng-click="saveHistoric()">Save Historic</button>
		        			</div>
		        		</div>        	        		
	        		</div>
	        	</form>	        			        	
       		</div>
       		
        	<hr />
        	
        	<div class="row">	        	
                <dl class="dl-horizontal" data-ng-repeat="item in CompanyDetectors">
                    <dt>Detector: {{item.name}} - ID: {{item.uid}}</dt>
                        <dd>Sensor1: {{item.detectorDto.sensorsDto[0].name}} - ID: {{item.detectorDto.sensorsDto[0].uid}} / Min|Man: {{item.detectorDto.sensorsDto[0].rangeMin}} | {{item.detectorDto.sensorsDto[0].rangeMax}}</dd>	                        
                        <dd>Sensor2: {{item.detectorDto.sensorsDto[1].name}} - ID: {{item.detectorDto.sensorsDto[1].uid}} / Min|Man: {{item.detectorDto.sensorsDto[1].rangeMin}} | {{item.detectorDto.sensorsDto[1].rangeMax}}</dd>
	            </dl>	            
        	</div>
        	
        	<div class="row">        		
        		<!-- <button type="button" class="btn btn-primary" data-ng-click="saveCompanyDetector()">Save CompanyDetector</button> -->        	        		
        		<button type="button" class="btn btn-primary" data-ng-click="getCompanyDetector()">Get CompanyDetector</button>
        		<!-- <button type="button" class="btn btn-primary" data-ng-click="getOneCompanyDetector()">Get One CompanyDetector</button> -->
        		<!-- <button type="button" class="btn btn-primary" data-ng-click="getOneCompanyDetectorDevice()">Get Device CompanyDetector </button> -->
        		<!-- <button type="button" class="btn btn-primary" data-ng-click="getOneCompanyDetectorDeviceByArea()">Get Area CompanyDetector  </button> -->
        		<!-- <button type="button" class="btn btn-primary" data-ng-click="deletarCompanyDetector()">Deletar CompanyDetector</button>  -->      		
        		        		
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