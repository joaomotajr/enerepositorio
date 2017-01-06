<style>
	.zui-table {
		border: solid 1px #DDEEEE;
		border-collapse: collapse;
		border-spacing: 0;
		font: normal 13px Arial, sans-serif;
	}
	.zui-table thead th {
		background-color: #DDEFEF;
		border: solid 1px #DDEEEE;
		color: #336B6B !important;
		padding: 10px;
		text-align: left;
		text-shadow: 1px 1px 1px #fff;
	}
	.zui-table tbody td {
		border: solid 1px #DDEEEE;
		color: #333;
		padding: 10px;
		text-shadow: 1px 1px 1px #fff;
	}
</style>

<div data-ng-controller="navegacaoController">
	<div class="row">
        <div class="col-md-12">
        	<div class="row">      		           
	            <form class="form" name="userForm">
		            <div class="col-md-12">
		            
			            <div class="col-md-3">                                                                                                                            
							<div class="form-group">
								<label class="control-label">CompanyDetector</label>
			                	<select class="form-control" data-live-search="true" 
		                            style="width: 100%;" tabindex="-1" aria-hidden="true"                              
		                                data-ng-options="item as item.name for item in CompanyDetectors | orderBy: 'name' track by item.uid" 
		                                         data-ng-model="selectedCompanyDetector" 
		                                         data-ng-change="changeCompanyDetector();">
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
		                                         data-ng-model="selectedCompanySensor"
		                                         data-ng-change="changeSensor();">
		                                         <option value="">Selecione</option> 
		                        </select>		                        						                                                
							</div>									                                                                                     
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label class="control-label">Valor</label>
			                	<input class="form-control" data-ng-model="companyValor">						                                                
							</div>                                                                    
						</div>         		
						<div class="col-md-2">
							<div class="form-group">
								<label class="control-label">Ação</label>
		        				<button type="button" class="btn btn-primary btn-xs form-control" data-ng-click="saveHistoric()">Save Historic</button>
		        			</div>
		        		</div>        	        		
		        		<div class="col-md-2">
							<div class="form-group">
								<label class="control-label">Ação</label>
		        				<button type="button" class="btn btn-primary btn-xs form-control" data-ng-click="clearHistoric()">Clear</button>
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
       				<div class="col-md-4"></div>
       				<div class="col-md-2">
       					<label data-ng-show="selectedCompanySensor">Range Max: </label><span> {{selectedCompanySensor.rangeMax}}</span>
       				</div>
       				<div class="col-md-2">
       					<label data-ng-show="selectedCompanySensor">Alarm 1: </label><strong style='color: green'> {{selectedSensorAlarm.alarm1}}</strong>
       				</div>
       				<div class="col-md-2">
       					<label data-ng-show="selectedCompanySensor">Alarm 2: </label><strong style='color: orange'> {{selectedSensorAlarm.alarm2}}</strong>
       				</div>
       				<div class="col-md-2">
       					<label data-ng-show="selectedCompanySensor">Alarm 3: </label><strong style='color: red'> {{selectedSensorAlarm.alarm3}}</strong>
       				</div>
       			</div>       		
       		</div>	
       		<hr />
<!--        		<div class="row">        		 -->
<!--         		<div class="row">      		            -->
<!-- 		            <form class="form" name="userForm"> -->
<!-- 			            <div class="col-md-10">		            			             -->
<!-- 							<div class="col-md-3"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">Data inicio</label>				                	 -->
<!-- 				                	  <input class="form-control" type="text" data-datepicker data-ng-model="dateIn" />  -->
<!-- 				                	 <input type="text" class="form-control" data-date-format="dd/mm/yyyy" data-datemonopicker data-ng-model="dateIn" data-mask="99/99/9999" mask/> -->
				                					                			                                                
<!-- 								</div>                                                                     -->
<!-- 							</div> -->
<!-- 							<div class="col-md-3"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">Data Fim</label>				                	 -->
<!-- 				                	<input class="form-control" type="text" data-datepicker data-ng-model="dateOut" /> -->
<!-- 				                	<input type="text" class="form-control" data-date-format="dd/mm/yyyy" data-datemonopicker data-ng-model="dateOut" data-mask="99/99/9999" mask/>				                							                                                 -->
<!-- 								</div>                                                                     -->
<!-- 							</div> -->
<!-- 							<div class="col-md-2"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">Ação</label> -->
<!-- 			        				<button type="button" class="btn btn-primary btn-sm form-control" data-ng-click="getHistoricInterval()" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">Get Interval</button> -->
<!-- 			        			</div> -->
<!-- 			        		</div>         		 -->
<!-- 							<div class="col-md-2"> -->
<!-- 								<div class="form-group"> -->
<!-- 									<label class="control-label">Ação</label> -->
<!-- 			        				<button type="button" class="btn btn-primary btn-sm form-control" data-ng-click="getHistoric()">Get All Historic</button> -->
<!-- 			        			</div> -->
<!-- 			        		</div>        	        		 -->
<!-- 			        		<div class="col-md-2"> -->
<!-- 								<div class="form-group"> -->
<!-- 								<label class="control-label">Ação</label>	 -->
<!-- 									<button type="button" class="btn btn-primary" data-ng-click="listCompanyDetectors()">Get All CompanyDetector</button>								 -->
<!-- 			        			</div> -->
<!-- 			        		</div> -->
<!-- 		        		</div> -->
<!-- 		        	</form>	        			        	 -->
<!-- 	       		</div>        		        		 -->
<!--        		</div> -->
       		
<!--        		<hr /> -->
<!--        		<div class="row">        		 -->
<!--         		<div class="row">      		            -->
<!-- 		            <form class="form" name="userForm"> -->
<!-- 			            <div class="col-md-12">		            			             -->
<!-- 							<div class="col-md-6">	 -->
<!-- 								<label class="control-label">Detector / Sensor: </label>								 -->
<!-- 								<div class="btn-group btn-group-sm" role="group" aria-label="Basic example"> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(1);"   data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 hora</button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(6);"   data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  6h  </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(12);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  12h </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(24);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 dia </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(48);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  2d  </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics2(96);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">  4d  </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getLastMonth2();" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true"> 30d  </button> -->
<!-- 								</div>                                                               -->
<!-- 							</div>							        	        		 -->
							
<!-- 							<div class="col-md-6">								 -->
<!-- 								<label class="control-label">Detector: </label>	 -->
<!-- 								<div class="btn-group btn-group-sm" role="group" aria-label="Basic example"> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(1);"   data-ng-disabled="(selectedCompanyDetector) ? false : true">1 hora</button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(6);"   data-ng-disabled="(selectedCompanyDetector) ? false : true">  6h  </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(12);"  data-ng-disabled="(selectedCompanyDetector) ? false : true">  12h </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(24);"  data-ng-disabled="(selectedCompanyDetector) ? false : true">1 dia </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(48);"  data-ng-disabled="(selectedCompanyDetector) ? false : true">  2d  </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getHistorics(96);"  data-ng-disabled="(selectedCompanyDetector) ? false : true">  4d  </button> -->
<!-- 								  <button type="button" class="btn btn-default" data-ng-click="getLastMonth();" data-ng-disabled="(selectedCompanyDetector) ? false : true"> 30d  </button> -->
<!-- 								</div>                                                               -->
<!-- 							</div> -->
<!-- 		        		</div> -->
<!-- 		        	</form>	        			        	 -->
<!-- 	       		</div>        		        		 -->
<!--        		</div>       		 -->
       		
<!--        		<hr /> -->
       		
<!--         	<div class="row"> -->
<!--         		<div class="col-md-12">	        	 -->
<!-- 	                <dl class="dl-horizontal" data-ng-repeat="item in listCompanyDetectors"> -->
<!-- 	                    <dt>Dtct: {{item.name}} - ID: {{item.uid}}</dt> -->
<!-- 	                        <dd>Sns1: {{item.detectorDto.sensorsDto[0].name}} - ID: {{item.detectorDto.sensorsDto[0].uid}}  Min|Max: {{item.detectorDto.sensorsDto[0].rangeMin}} | {{item.detectorDto.sensorsDto[0].rangeMax}}</dd>	                         -->
<!-- 	                        <dd>Sns2: {{item.detectorDto.sensorsDto[1].name}} - ID: {{item.detectorDto.sensorsDto[1].uid}}  Min|Max: {{item.detectorDto.sensorsDto[1].rangeMin}} | {{item.detectorDto.sensorsDto[1].rangeMax}}</dd> -->
<!-- 		            </dl>	             -->
<!--         		</div>        		 -->
<!--         	</div> -->
<!--         	<div class="row"> -->
<!--         		<div class="col-md-4"> -->
<!--         		<dl class="dl-horizontal"> -->
<!--         			<dt>Detector: <label>{{selectedCompanyDetector.name}}</label></dt> -->
<!--         			<dd>{{selectedCompanyDetector.detectorDto.sensorsDto[0].name}}</dd> -->
<!--         			<dd>{{selectedCompanyDetector.detectorDto.sensorsDto[1].name}}</dd> -->
<!--         		</dl> -->
<!--         		</div> -->
        		
<!--         		<div class="col-md-4"> -->
<!--         			<div style="max-height:500px; height:auto; overflow: auto">	                 -->
<!-- 		                <table class='zui-table' cellspacing="0" width="100%" data-ng-visible="listHistoric">					            				                             -->
<!-- 		                	<thead> -->
<!-- 		                    	<tr>	                 -->
<!-- 		                    		<th>Sensor</th>                                                                                    -->
<!-- 		                      		<th>Data / Hora</th>	                      		 -->
<!-- 		                      		<th>Valor</th>			                                                                                                                             -->
<!-- 		                     	</tr> -->
<!-- 		                    </thead> -->
<!-- 		                    <tbody>                                     -->
<!-- 		                     	<tr data-ng-repeat="item in listHistoric.list"> -->
<!-- 		                     		<td>{{item.sensorDto.uid}} / {{item.sensorDto.name}} </td>	                                -->
<!-- 		                      		<td>{{item.lastUpdate | date:'dd/MM/yyyy HH:mm' }}</td> -->
<!-- 		                      		<td>{{item.value}}</td>			                                                         -->
<!-- 		                     	</tr> -->
<!-- 		                    </tbody> -->
<!-- 		            	</table> -->
<!-- 	            	</div>                                                         	             -->
<!--         		</div> -->
<!--         	</div> -->
        
	    </div>
    </div>    
</div>