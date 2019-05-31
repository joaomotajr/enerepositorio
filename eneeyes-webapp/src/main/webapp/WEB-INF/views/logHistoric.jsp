<style>
	#snoAlertBox {
		position:absolute;
		z-index:1400;
		top:90%;
		right:4%;
		margin:0px auto;
		text-align:center;
		display:none;
	}

	.cursor {
		cursor: pointer;
	}	
</style>

<div data-ng-controller="logHistoricController">
	<div class="row">
	
		<div id="snoAlertBox" class="alert alert-warning" data-alert="alert">{{daysDiff}}</div>						                                                    
		<div class="col-md-12">                                                        
			<div class="box box-primary" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">	
				<div class="box-header">
					<h3 class="box-title">Pesquisa Historico de Dispositivos</h3>
					<div class="box-tools pull-right">
						<button id="btnSelDevice" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>					                    
					</div>
				</div>
				
				<div class="box-body" style="padding-bottom: 0px; padding: 0px">
					<div class="row">        		
						<div class="col-md-12">
							<div id="wizard" class="form_wizard wizard_horizontal">        		
								<ul class="wizard_steps anchor">
									<li>
										<a href="#step-1" class="disabled" data-ng-class="(selectedCompany) ? 'selected' : 'disabled'" rel="1">
											<span class="step_no">1</span>
											<span class="step_descr">Selecione Empresa</span>
										</a>
									</li>
									<li>
										<a href="#step-2" class="disabled" data-ng-class="(selectedUnit) ? 'selected' : 'disabled'" rel="2">
											<span class="step_no">2</span>
											<span class="step_descr">Selecione Unidade</span>
										</a>
									</li>
									<li>
										<a href="#step-3" class="disabled" data-ng-class="(selectedArea) ? 'selected' : 'disabled'" rel="3">
											<span class="step_no">3</span>
											<span class="step_descr">Selecione Área</span>
										</a>
									</li>
									<li>
										<a href="#step-4" class="disabled" data-ng-class="(selectedCompanyDetector) ? 'selected' : 'disabled'" rel="4">
											<span class="step_no">4</span>
											<span class="step_descr">Selecione Detector</span>
										</a>
									</li>
									<li>
										<a href="#step-5" class="disabled" data-ng-class="(selectedCompanyDetector) ? 'selected' : 'disabled'" rel="5">
											<span class="step_no">5</span>
											<span class="step_descr">Sensor/G&aacute;s</span>
										</a>
									</li>
									<li>
										<a href="#step-6" class="disabled" data-ng-class="(listHistoricInterval.list) ? 'selected' : 'disabled'" rel="6">
											<span class="step_no">6</span>
											<span class="step_descr">Pesquisar / Limpar Pesquisa</span>
										</a>
									</li>
								</ul>
							</div>
						</div>       	
					</div>
					
					<div class="row">      		           
						<form class="form" name="userForm">
							<div class="col-md-12">									
								<div class="col-md-2">
									<jsp:include page="controls/companySelect.jsp"/>
								</div>							
								<div class="col-md-2">                                                                                                                            
									<div class="form-group" data-ng-hide="units.length==1">	
										<select class="form-control" data-live-search="true" 
											style="width: 100%;" tabindex="-1" aria-hidden="true"											
											data-ng-options="item as item.unit for item in units | orderBy: 'unit' track by item.unitId" 
											data-ng-model="selectedUnit" 
											data-ng-change="changeUnit();">
											<option value="">Selecione</option> 
										</select>
									</div>
									<div class="form-group" data-ng-show="units.length==1">	
										<input class="form-control" type="text" data-ng-model="selectedUnit.unit" disabled>			                        				                        							                                                                    
									</div>
								</div>
								<div class="col-md-2">                                                                                                                            
									<div class="form-group" data-ng-hide="areas.length==1">
										<select class="form-control" data-live-search="true" 
											style="width: 100%;" tabindex="-1" aria-hidden="true"                              
											data-ng-options="item as item.area for item in areas | orderBy: 'area' track by item.areaId" 
											data-ng-model="selectedArea" 
											data-ng-change="changeArea();">
											<option value="">Selecione</option> 
										</select>
									</div>
									<div class="form-group" data-ng-show="areas.length==1">
										<input class="form-control" type="text" data-ng-model="selectedArea.area" disabled>			                        				                        							                                                                    
									</div>
								</div>
								<div class="col-md-2">                                                                                                                            
									<div class="form-group" data-ng-hide="companyDetectors.length==1">
										<select class="form-control" data-live-search="true" 
											style="width: 100%;" tabindex="-1" aria-hidden="true"                              
											data-ng-options="item as item.companyDetectorName for item in companyDetectors | orderBy: 'companyDetectorName' track by item.companyDeviceId" 
											data-ng-model="selectedCompanyDetector" 
											data-ng-change="changeCompanyDetector();">
											<option value="">Selecione</option> 
										</select>
									</div>
									<div class="form-group" data-ng-show="companyDetectors.length==1">
										<input class="form-control" type="text" data-ng-model="selectedCompanyDetector.companyDetectorName" disabled>			                        				                        							                                                                    
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<input class="form-control" type="text" data-ng-model="selectedCompanyDetector.sensorName" disabled>										
									</div>									                                                                                     
								</div>		
								<div class="col-md-2">
									<div class="form-group">
										<button type="button" class="btn btn-default btn-xs form-control" data-ng-click="clearHistoric()">Limpar Pesquisa</button>
									</div>
								</div>
							</div>	        		
						</form>				        	
						
					</div>
					
				</div>
			</div>	
			
			<div class="row">						                                                    
				<div class="col-md-12">                                                        
					<div class="box box-solid box-primary" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
						
						<div class="box-header">
							<h3 class="box-title">Selecione Intervalos Pr&eacute;-Definidos ou Data de Inicio/Fim</h3>									  	
								
							<div class="pull-right" style="margin-bottom: 0px ! important">   
								<span data-ng-hide='loading' class="icon fa fa-search fa-2.0x pull-right"></span>								
							</div>
						</div>			
						
						<div class="box-body" style="padding-bottom: 0px !important">	
							<div class="row">
							
								<div class="col-md-4">																															
									<div class="col-md-3" style="padding-right: 2px !important;">	
										<label style="margin-top: 5px !important;" title="Mostrando Máximo e Mínimo"><span class="icon fa fa-object-group"></span> Agrupar</label>
									</div>
									<div class="col-md-9" style="padding-right: 5px; padding-left: 5px; background:gray; border-color:lightgray; border-radius: 2px 2px 2px 2px; color:white">
											<div class="radio3 radio-check radio-success radio-inline">
												<input type="radio" id="radio5" value="1" data-ng-model="tipoGrupo" data-ng-change="clearTipoGrupo(1)">
												<label for="radio5">Nenhum</label>
											</div>
											<div class="radio3 radio-check radio-warning radio-inline" title="NOTE: Se houve mais de um tipo de alarme na HORA, apenas o maior será Exibido">
												<input type="radio" id="radio6" value="2" data-ng-model="tipoGrupo" data-ng-change="clearTipoGrupo(2)">
												<label for="radio6">Hora</label>
											</div>
											<div class="radio3 radio-check radio-default radio-inline" title="NOTE: Se houve mais de um tipo de alarme no DIA, apenas o maior será Exibido">
												<input type="radio" id="radio7" value="3" data-ng-model="tipoGrupo" data-ng-change="clearTipoGrupo(3)">
												<label for="radio7">Dia</label>
											</div>
										</div>                                        										
								</div>		
			
								<div class="col-md-1" ></div>
								
								<div class="col-md-5" >									
									<div class="col-md-3">
										<label style="margin-top: 2px !important; padding-right: 5px !important;" title="Mostrando Máximo e Máximo"><span class="icon fa fa-caret"></span>Exibir/Exportar</label>
									</div>										        	
									<div class="form-group">
											<div class="col-md-3" style="padding-right: 5px !important;">
											<button id="exportRel" type="button" class="btn btn-default btn-xs form-control" 
												data-ng-class="(listHistoricInterval.list) ? 'selected' : 'disabled'">
											<span class="icon fa fa-file-text"></span> Relat&oacute;rio</button>
										</div>
										<div class="col-md-3" style="padding-right: 5px !important; padding-left: 5px !important;">
											<button id="exportExcel" type="button" class="btn bg-olive btn-xs form-control" title="Exportação Permitida até 500 Linhas" 
												data-ng-class="((listHistoricInterval.list) && countHistoric <= 500) ? 'selected' : 'disabled'">
											<span class="icon fa fa-file-excel-o"></span> Excel</button>
										</div>
										<div class="col-md-3" style="padding-right: 5px !important; padding-left: 5px !important;">
										<button type="button" class="btn bg-navy btn-xs form-control" 
											data-ng-click="showGrafico();" data-ng-class="(listHistoricInterval.list) ? 'selected' : 'disabled'">
											<span class="icon fa fa-line-chart"></span> Gr&aacute;fico</button>
										</div>
									</div>			        				    								
								</div>				        			
							</div>
							
							<hr style="margin-top: 5px !important; margin-bottom: 5px !important;">
							
							<div class="row">
								<form class="form" name="userForm">						 		
									
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label">Buscar por Intervalos Pr&eacute;-Definidos: </label> <br />								
											<div class="btn-group" role="group" aria-label="Basic example">
													<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 1}" 
														data-ng-click="interval = enumInterval.UMA_HORA; getHistorics(0);" data-ng-disabled="(selectedCompanyDetector.sensorName) ? false : true">1 hora</button>
													<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 6}" 
														data-ng-click="interval = enumInterval.SEIS_HORAS; getHistorics(0);" data-ng-disabled="(selectedCompanyDetector.sensorName) ? false : true">6h</button>
													<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 12}" 
														data-ng-click="interval = enumInterval.DOZE_HORAS; getHistorics(0);"  data-ng-disabled="(selectedCompanyDetector.sensorName) ? false : true"> 12h</button>
													<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 24}" 
														data-ng-click="interval = enumInterval.UM_DIA; getHistorics(0);"  data-ng-disabled="(selectedCompanyDetector.sensorName) ? false : true">1 dia</button>
													<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 48}" 
														data-ng-click="interval = enumInterval.DOIS_DIAS; getHistorics(0);"  data-ng-disabled="(selectedCompanyDetector.sensorName) ? false : true">2d</button>
													<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 168}" 
														data-ng-click="interval = enumInterval.SETE_DIAS; getHistorics(0);"  data-ng-disabled="(selectedCompanyDetector.sensorName) ? false : true">7d</button>
													<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 30}" 
														data-ng-click="interval = enumInterval.UM_MES; getHistorics(0);" data-ng-disabled="(selectedCompanyDetector.sensorName) ? false : true">30d</button>												  												  
											</div>
										</div>
									</div>
									
									<div class="col-md-3" style="padding-right: 5px !important;">
										<div class="form-group">
											<label class="control-label">Data inicio</label>									                	 

												<div class='input-group date' id='dateIn'>
												<input type="text" class="form-control" data-ng-model="dateIn" name="dateIn">
												<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>													                					                			                                                
										</div>                                                                    
									</div>
									
									<div class="col-md-3" style="padding-right: 5px !important; padding-left: 5px !important;">
										<div class="form-group">
											<label class="control-label">Data Fim</label>							                	
											<div class='input-group date' id='dateOut'>
												<input type="text" class="form-control" data-ng-model="dateOut" name="dateOut">
												<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>							                					                							                                                
										</div>                                                                    
									</div>
									
									<div class="col-md-1">
										<div class="form-group">
											<label class="control-label">&nbsp;</label>
											<button type="button" class="btn btn-default btn-sm form-control" data-ng-class="{'btn-primary': selectedButton == 100}" 
												data-ng-click="interval = enumInterval.CUSTOM; getHistorics(0)" data-ng-disabled="(selectedCompanyDetector.sensorName) ? false : true">Buscar
											</button>
										</div>
									</div>
																			
								</form>
								
							</div>    
							
							<div class="row">
								<div id="printHeader" class="col-md-4">
									<div class="box box-primary">
				
										<div class="box-header">
											<h4 class="box-title">
											<strong>Dispositivo: </strong> {{selectedCompanyDetector.companyDetectorName}} ({{selectedCompanyDetector.detectorName}})
										</div>
										
										<div class="box-body">
											<div class="col-md-12">
												<div class="row" data-ng-show="selectedCompanyDetector.sensorName">
													<div class="box box-solid" style="font-size: 1.1em">
														
														<div class="box-header with-border">
															<i class="fa fa-bell-o"></i>
															<h3 class="box-title">Detalhes do Sensor/alarme:</h3>
														</div>
														
														<div class="box-body" style="border: darkgray; border-style: double;  background-color: transparent;">
																																
															<dl class="dl-horizontal">
																<dt>Sensor (nome):</dt>
																	<dd>{{selectedCompanyDetector.sensorName}}</dd>
																<dt>Alarme (nome):</dt>
																	<dd>{{findedCompanyDevice.alarmDto.name}}</dd>
																<dt>Unidade:</dt>
																<dd data-ng-bind="findedCompanyDevice.alarmDto.unitMeter.symbol"></dd>
																<dt>Range Max:</dt>
																	<dd><strong>{{selectedCompanyDetector.rangeMax}}</strong></dd>
																<dt>Detec&ccedil;&atilde;o:</dt>
																	<dd><span data-ng-show="findedCompanyDevice.alarmDto" class="alarm1"> {{findedCompanyDevice.alarmDto.alarm1}}</span></dd>							
																<dt>Alerta:</dt>
																	<dd><span data-ng-show="findedCompanyDevice.alarmDto" class="alarm2"> {{findedCompanyDevice.alarmDto.alarm2}}</span></dd>
																<dt>Evacua&ccedil;&atilde;o:</dt>
																	<dd><span data-ng-show="findedCompanyDevice.alarmDto" class="alarm3"> {{findedCompanyDevice.alarmDto.alarm3}}</span></dd>
															</dl>											                	
															
														</div>
													</div>
												</div>												
													
											</div>
										</div>
									</div>			
								
								</div>									        		
								<div class="col-md-5">
									<table data-ng-if="tipoGrupo==1" class='zui-table' cellspacing="0" width="100%">
											<thead>
												<tr>
													<th>Id</th>                                                                                 
													<th>Data</th>
													<th>Hora</th>	                      		
													<th>Valor</th>
													<th style="padding: 1px">Origem</th>
												</tr>
											</thead>
									</table>
									<div style="max-height:420px; height:auto; overflow: auto">
				
										<table data-ng-if="tipoGrupo==1" class='zui-table' cellspacing="0" width="100%">							
											<tbody>		
												<div data-ng-show='loading' class="overlay"><i class="fa fa-refresh fa-spin"></i></div>	
												
												<tr data-ng-repeat="item in listHistoricInterval.list">
													<td>{{item.uid}}</td>
													<td>{{item.lastUpdate | date:'dd/MM/yyyy' }}</td>
													<td>{{item.lastUpdate | date:'HH:mm:ss' }}</td>
													<td> {{item.value}} </td>
													<td>
														<span data-ng-if="item.logOrigem == 'DEVICE'">DISPOSITIVO</span>
														<span data-ng-if="item.logOrigem == 'MANUAL'">AN&Aacute;LISE DE CEN&Aacute;RIO</span>
														<span data-ng-if="item.logOrigem == 'SYSTEM'">SISTEMA</span>
														<span data-ng-if="item.logOrigem == 'OTHER' || item.logOrigem == null">DESCONHECIDA</span>
													</td>
												</tr>
											</tbody>
										</table>								            	
										
										<table data-ng-if="tipoGrupo!=1" class='zui-table' cellspacing="0" width="100%">														            				                            
											<thead>
												<tr>                                                                                   
													<th>Data</th>
													<th>Hora</th>	                      		
													<th>M&aacute;ximo</th>
													<th>Minimo</th>													
												</tr>
											</thead>
											<tbody>		
												<div data-ng-show='loading' class="overlay"><i class="fa fa-refresh fa-spin"></i></div>											               
												
												<tr data-ng-repeat="item in listHistoricInterval.list">	
													<td>{{item.lastUpdate | date:'dd/MM/yyyy' }}</td>
													<td>{{item.lastUpdate | date:'HH:mm:ss' }}</td>
													<td> {{item.maxValue}} </td>
													<td> {{item.minValue}} </td>													
												</tr>
											</tbody>
										</table>

										<p data-ng-hide="listHistoricInterval == undefined || listHistoricInterval.list.length > 0 || loading" class="text-center">NENHUM REGISTRO</p>
										
									</div>                                                         	            
								</div>
								
								<div class="col-md-3">
									<br>						
									<div class="row" style=" vertical-align: middle; text-align: center;">									
										<label><span class="icon fa fa-columns"> Itens por P&aacute;gina:</span><span class="text-red" data-ng-show="!lenPageValid"> [0 - 2000]</span></label>
									</div>

									<div class="row" style=" vertical-align: middle; text-align: center;">
										<div class="col-md-4" style="padding-left:2px; padding-right: 2px;">
											<button class="btn btn-info" type="button" data-ng-show="countPages > 0" data-ng-class="{disabled: currentPage == 0, 'cursor': currentPage != 0}"  data-ng-click="getHistorics(0)">
											&LeftTriangleBar; Primeira
											</button>											
										</div>
										
										<div data-ng-class="{'has-error': !lenPageValid}">
										<div class="col-md-3" style="padding-left:2px; padding-right: 2px;">
											<input id="allownumericwithoutdecimal" data-ng-change="changeLenPage();" data-ng-keydown="validLenPage($event)" type="text" class="form-control" data-ng-model="lenPage">
										</div>
										</div>

										<div class="col-md-4" style="padding-left:2px; padding-right: 2px;">
											<button class="btn btn-info" data-ng-show="countPages > 0" data-ng-class="{disabled: currentPage == countPages-1, 'cursor': currentPage != countPages}" data-ng-click=" getHistorics(countPages-1)">
											&uacute;ltima &RightTriangleBar;
											</button>
										</div>
									</div>
									
									<br>
									<hr>									

									<div class="row" style=" vertical-align: middle; text-align: center;">
										<label data-ng-show='countHistoric > 0'><span class="icon fa fa-text-width"> Total Itens da Pesquisa: </span> {{countHistoric}}</label>
									</div>
									<div class="row" style=" vertical-align: middle; display: inline-block; text-align: center;">										
										<ul class="pagination inline">													
											<li data-ng-show="countPages > 0" data-ng-class="{disabled: currentPage == 0, 'cursor': currentPage != 0}">
												<a data-ng-click="n=currentPage-1; getHistorics(n)">&lang;</a>
											</li>
											
											<li data-ng-repeat="n in listPages" data-ng-class="{active: currentPage == n, 'cursor': currentPage !=n }">
												<a data-ng-click="getHistorics(n)" data-ng-bind="n"></a>
											</li>

											<li data-ng-show="countPages > 0" data-ng-class="{disabled: currentPage == countPages-1, 'cursor': currentPage != countPages}"> 
												<a data-ng-click="n=currentPage+1; getHistorics(n)">&rang;</a>
											</li>													
										</ul>										
									</div>
									<div class="row">
									<div style="vertical-align: middle; text-align: center; font-size: 05.em">
										<label data-ng-show='countHistoric > 0'> P&aacute;ginas: {{countPages}}</label>
									</div>
									</div>							
									
									<br>

								</div>
							</div>
																			
						</div>
					</div>				
				</div>																		
			</div>								
		</div>                                                      
	</div>		
	
	<div id="modalGraficoHistorico" class="modal">                
		<div class="modal-dialog  modal-lg" role="document">
			<div class="modal-content">                            
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Gr&aacute;fico do Per&iacute;odo:</strong> {{selectedPeriodo}}</div>															                                                                           
					</div>
			
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"><strong>Gr&aacute;fico do Sensor:</strong> {{selectedCompanyDetector.sensorName}} - G&aacute;s: {{findedCompanyDevice.alarmDto.gasDto.gasDto.name}} </h3>
							<input class="pull-right ng-pristine ng-untouched ng-valid" type="checkbox" style="margin-right: 30px" data-ng-model="changeGraphic" data-ng-change="showGrafico();">
							<label data-ng-show="changeGraphic" class="pull-right">Gr&aacute;fico Completo&nbsp;&nbsp;</label>
							<label data-ng-hide="changeGraphic" class="pull-right">Gr&aacute;fico Medi&ccedil;&otilde;es&nbsp;&nbsp;</label>
						</div>
						<div class="box-body">
							<div  id="graficoHistorico" style="max-width: 900px; overflow-x: auto; overflow-y: hidden;"></div>
						</div>
					</div>				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>                                
				</div>
			</div>
		</div>		
	</div>

	<div id="printTable" style="visibility:hidden" >
	
		<div class="col-md-12">
			<h1>Empresa: {{selectedCompanyDetector.company}}</h1>
			<h2>Unidade: {{selectedCompanyDetector.unit}} - &Aacute;rea: {{selectedCompanyDetector.area}} </h2>
			<hr>
			<h3 class="box-title"><strong>Dispositivo: </strong> {{selectedCompanyDetector.companyDetectorName}} ({{selectedCompanyDetector.detectorName}})
									
			<i class="fa fa-bell-o"></i> <h3 class="box-title">Detalhes do Sensor/alarme:</h3>                       
																					
			<dl class="dl-horizontal">
				<dt>Sensor (nome):</dt>
					<dd>{{selectedCompanyDetector.sensorName}}</dd>
				<dt>Nome do Alarme / Unidade de Medida:</dt>
					<dd>{{findedCompanyDevice.alarmDto.name}} / {{findedCompanyDevice.alarmDto.unitMeter.symbol}}</dd>					
				<dt>Range Max:</dt>
					<dd><strong>{{selectedCompanyDetector.rangeMax}}</strong></dd>
				<dt>Detecrição:</dt>
					<dd><span data-ng-show="ffindedCompanyDevice.alarmDto.alarm1" class="alarm1"> {{findedCompanyDevice.alarmDto.alarm1}}</span></dd>							
				<dt>Alerta:</dt>
					<dd><span data-ng-show="ffindedCompanyDevice.alarmDto.alarm2" class="alarm2"> {{findedCompanyDevice.alarmDto.alarm2}}</span></dd>
				<dt>Evacuação:</dt>
					<dd><span data-ng-show="ffindedCompanyDevice.alarmDto.alarm3" class="alarm3"> {{findedCompanyDevice.alarmDto.alarm3}}</span></dd>
			</dl>		            
			
			<div class="panel-heading" style="text-align:center;font-size:1.5em"><strong>Dados do Per&iacute;odo:</strong> {{selectedPeriodo}}</div>
			
			<br />
			<div id="dvData">
				<table class='zui-table' data-cellspacing="0" data-width="100%">	                   
					<tr>	                
							<th>ID</th>                                                                                   
							<th>Data</th>
							<th>Hora</th>                     	
															
							<th data-ng-if="tipoGrupo==1">Valor</th>
							<th data-ng-if="tipoGrupo!=1">M&aacute;ximo</th>
							<th data-ng-if="tipoGrupo!=1">M&iacute;nimo</th>
							
							<th data-ng-if="tipoGrupo==1">Origem Informa&ccedil;&atilde;o</th>			                                                                                                                            
						</tr>                                    

						<tr data-ng-repeat="item in listHistoricInterval.list">
							<td>{{item.sensorId}} </td>
							<td>{{item.lastUpdate | date:'dd/MM/yyyy' }}</td>
							<td>{{item.lastUpdate | date:'HH:mm:ss' }}</td>

						<td data-ng-show="tipoGrupo==1"> {{changeToValue(item.value)}} </td>						
						<td data-ng-show="tipoGrupo!=1"> {{item.maxValue}} </td>
						<td data-ng-show="tipoGrupo!=1"> {{item.minValue}} </td>
												
						<td data-ng-if="tipoGrupo==1">
							<span data-ng-if="item.logOrigem == 'DEVICE'">DISPOSITIVO</span>
							<span data-ng-if="item.logOrigem == 'MANUAL'">AN&Aacute;LISE DE CEN&Aacute;RIO</span>
							<span data-ng-if="item.logOrigem == 'SYSTEM'">SISTEMA</span>
							<span data-ng-if="item.logOrigem == 'OTHER' || item.logOrigem == null">DESCONHECIDA</span>							
						</td>						
					</tr>	                   
				</table>            	
			</div>	
		</div>
	</div>			
</div>