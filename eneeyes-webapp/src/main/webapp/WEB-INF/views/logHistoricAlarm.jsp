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

<div data-ng-controller="logHistoricAlarmController">
	<div class="row">
	
		<div id="snoAlertBox" class="alert alert-warning" data-alert="alert">{{daysDiff}}</div>						                                                    
		<div class="col-md-12">                                                        
			<div class="box box-danger" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">	
				<div class="box-header">
					<h3 class="box-title">Pesquisa Historico de Alarmes</h3>
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
										<a href="#step-2" class="disabled" data-ng-class="(selectedCompanyDetector) ? 'selected' : 'disabled'" rel="2">
											<span class="step_no">2</span>
											<span class="step_descr">Selecione Detector</span>
										</a>
									</li>
									<li>
										<a href="#step-3" class="disabled" data-ng-class="(selectedCompanyDetector) ? 'selected' : 'disabled'" rel="3">
											<span class="step_no">3</span>
											<span class="step_descr">Sensor/G&aacute;s</span>
										</a>
									</li>
									<li>
										<a href="#step-4" class="disabled" data-ng-class="(listHistoric.list || listHistoricInterval.list) ? 'selected' : 'disabled'" rel="4">
											<span class="step_no">4</span>
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
									
								<div class="col-md-3">
									<jsp:include page="controls/companySelect.jsp"/>					            		
								</div>
							
								<div class="col-md-3">                                                                                                                            
									<div class="form-group">
										<select class="form-control" data-live-search="true" 
											style="width: 100%;" tabindex="-1" aria-hidden="true"                              
											data-ng-options="item as item.companyDetectorName for item in companyDetectors | companyFilter:search | orderBy: 'companyDetectorName' track by item.companyDeviceId" 
											data-ng-model="selectedCompanyDetector" 
											data-ng-change="changeCompanyDetector();">
											<option value="">Selecione</option> 
										</select>
									</div>
								</div>		
																		
								<div class="col-md-3">
									<div class="form-group">
										<input class="form-control" type="text" data-ng-model="selectedCompanyDetector.sensorName" disabled>									
									</div>									                                                                                     
								</div>
		
								<div class="col-md-3">
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
					<div class="box box-solid box-danger" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
						
						<div class="box-header">
							<h3 class="box-title">Selecione Intervalos Pr&eacute;-Definidos ou Data de Inicio/Fim</h3>									  	
								
							<div class="pull-right" style="margin-bottom: 0px ! important">   
								<span data-ng-hide='loading' class="icon fa fa-search fa-2.0x pull-right"></span>								
							</div>
						</div>			
						
						<div class="box-body" style="padding-bottom: 0px !important">	
							<div class="col-md-9">
								<div class="row">								
									<div class="col-md-5" >
										<form>												
											<div class="col-md-4" style="padding-right: 2px !important;">	 
												<label style="margin-top: 5px !important;" class="control-label"><span class="icon fa fa-filter"></span> FILTRAR</label>
											</div>
											<div class="col-md-8" style="padding-left: 2px !important;">
											<select class="form-control" style="width: 100%;" tabindex="-1" aria-hidden="true" data-ng-options="item as item.name for item in filterAlarm | orderBy: 'name' track by item.uid" 
												data-ng-model="selectedfilterAlarm">
												<option value="">Selecione</option> 
											</select>
											</div>						        							    	        
										</form>								
									</div>
									
									<div class="col-md-7" >									
										<div class="col-md-3">
											<label style="margin-top: 2px !important; padding-right: 5px !important;" title="Mostrando MÃ¡ximo e Minimo"><span class="icon fa fa-caret"></span>Exibir/Exportar</label>
										</div>										        	
										<div class="form-group">
												<div class="col-md-3" style="padding-right: 5px !important;">
												<button id="exportRel" type="button" class="btn btn-default btn-xs form-control" 
													data-ng-class="(listHistoric.list || listHistoricInterval.list) ? 'selected' : 'disabled'">
												<span class="icon fa fa-file-text"></span> Relat&oacute;rio</button>
											</div>
											<div class="col-md-3" style="padding-right: 5px !important; padding-left: 5px !important;">
												<button id="exportExcel" type="button" class="btn bg-olive btn-xs form-control" title="Exportação Permitida até 500 Linhas" 
													data-ng-class="((listHistoric.list || listHistoricInterval.list) && countHistoric <= 500) ? 'selected' : 'disabled'">
												<span class="icon fa fa-file-excel-o"></span> Excel</button>
											</div>
											<div class="col-md-3" style="padding-right: 5px !important; padding-left: 5px !important;">
											<button type="button" class="btn bg-navy btn-xs form-control" 
												data-ng-click="showGrafico();" data-ng-class="(listHistoric.list || listHistoricInterval.list) ? 'selected' : 'disabled'">
												<span class="icon fa fa-line-chart"></span> Gr&aacute;fico</button>
											</div>
										</div>			        				    								
									</div>				        			
								</div>
								
								<hr style="margin-top: 5px !important; margin-bottom: 5px !important;">
								
								<div class="row">
									<form class="form" name="userForm">
										<div class="col-md-5" style="padding-right: 3px !important; padding-left: 3px !important;">
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
										
										<div class="col-md-3" style="padding-right: 5px !important; padding-left: 3px !important;">
											<div class="form-group">
												<label class="control-label">Data inicio</label>									                	 

													<div class='input-group date' id='dateInA'>
													<input type="text" class="form-control" data-ng-model="dateInA" name="dateInA">
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>													                					                			                                                
											</div>                                                                    
										</div>
										
										<div class="col-md-3" style="padding-right: 5px; padding-left: 3px;">
											<div class="form-group">
												<label class="control-label">Data Fim</label>							                	
												<div class='input-group date' id='dateOutA'>
													<input type="text" class="form-control" data-ng-model="dateOutA" name="dateOutA">
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>							                					                							                                                
											</div>                                                                    
										</div>
										
										<div class="col-md-1">
											<div class="form-group">
												<label class="control-label">&nbsp;</label>
												<button type="button" class="btn btn-default btn-sm form-control font-weight-bold" data-ng-class="{'btn-primary': selectedButton == 100}" 
												data-ng-click="interval = enumInterval.CUSTOM; getHistoricInterval(0)" data-ng-disabled="selectedCompanyDetector.sensorName ? false : true">
													<i class="fa fa-search"></i>
												</button>
											</div>
										</div>																			
									</form>								
								</div>    
								
								<div class="row">
									<div class="col-md-12">
										<table class='zui-table-danger' cellspacing="0" width="100%" data-ng-visible="listHistoric">
												<thead>
													<tr>                                                                                 
														<th class="col-md-1">Data</th>
														<th class="col-md-1">Hora</th>	                      		
														<th class="col-sm-1">Valor</th>
														<th class="col-sm-1">Alarm</th>
														<th class="col-sm-1">Status</th>
														<th class="col-sm-1">Sigma</th>
														<th class="col-sm-1">Sound</th>
														<th class="col-sm-1">Email</th>
														<th class="col-sm-1">SMS</th>
													</tr>
												</thead>
										</table>
										<div style="max-height:420px; height:auto; overflow: auto">
					
											<table class='zui-table-danger' cellspacing="0" width="100%" data-ng-visible="listHistoric">							
												<tbody>		
													<div data-ng-show='loading' class="overlay"><i class="fa fa-refresh fa-spin"></i></div>										
													<tr data-ng-repeat="item in listHistoricInterval.list | alarmFilter:selectedfilterAlarm">
														<td class="col-md-1">{{item.date | date:'dd/MM/yyyy'}}</td>
														<td class="col-md-1">{{item.date | date:'HH:mm:ss'}}</td>
														<td class="col-sm-1">{{item.value}}</td>
														<td class="col-sm-1">
															<span class="label label-success" data-ng-if="item.alarmOn">ON</span>
															<span class="label label-danger" data-ng-if="!item.alarmOn">OFF</span>														
														</td>													

														<td class="col-sm-1" style="text-shadow:  0px 20px 20px #999;vertical-align: middle;">
															<span class="label"  
																data-ng-class="{
																	'label-offline' : item.alarmType=='OFFLINE',
																	'label-success' : item.alarmType=='NORMAL', 
																	'label-warning' : item.alarmType=='ALERTA', 
																	'label-default' : item.alarmType=='DETECCAO', 
																	'label-danger' : item.alarmType=='EVACUACAO'}"> {{item.alarmType}}
															</span>
														</td>
														
														<td class="col-sm-1">
															<span data-ng-if="item.sigmaStatus=='OFF'" class="icon fa fa-exchange" style="font-size:1.4em; color: gray" title="Sem Integra&ccedil;&atilde;o ao Sigma Habilitada"></span>
															<span data-ng-if="item.sigmaStatus=='ERROR'" class="icon fa fa-exchange" style="font-size:1.4em; color: red" title="Falha ao Informar Sigma"></span>
															<span data-ng-if="item.sigmaStatus=='SENDED'" class="icon fa fa-exchange" style="font-size:1.4em; color: green" title="Integra&ccedil;&atilde;o ao Sigma Realizada"></span>
															<span data-ng-if="item.sigmaStatus=='ON'" class="icon fa fa-exchange" style="font-size:1.4em; color: orange" title="Integra&ccedil;&atilde;o ao Sigma em fila"></span>
														</td>
														<td class="col-sm-1">
															<span data-ng-if="item.soundStatus=='OFF'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: gray" title="Alerta Sonoro n&atilde;o Habilitado"></span>
															<span data-ng-if="item.soundStatus=='ON'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: red" title="Alerta Sonoro"></span>
															<span data-ng-if="item.soundStatus=='SILENT'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: green" title="Alerta Silenciado"></span>
														</td>
														
														<td class="col-sm-1">
															<span data-ng-if="item.emailStatus=='OFF'" class="icon fa fa-envelope" style="font-size:1.4em; color: gray" title="Aviso EMAIL n&atilde;o Habilitado"></span>
															<span data-ng-if="item.emailStatus=='PENDENT'" class="icon fa fa-envelope" style="font-size:1.4em; color: orange" title="EMAIL na fila para Envio"></span>	
															<span data-ng-if="item.emailStatus=='ERR_TRY_ONE'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa"></span>
															<span data-ng-if="item.emailStatus=='ERR_TRY'" class="icon fa fa-envelope" style="font-size:1.4em; color: red" title="Falha ao Enviar Email"></span>
															<span data-ng-if="item.emailStatus=='SENDED'" class="icon fa fa-envelope" style="font-size:1.4em; color: green" title="Email Enviado"></span>
														</td>													
														<td class="col-sm-1">
															<span data-ng-if="item.smsStatus=='OFF'"  style="font-size:1.4em; color: gray" title="Aviso de SMS n&atilde;o Habilitado">SMS</span>
															<span data-ng-if="item.smsStatus=='PENDENT'"  style="font-size:1.4em; color: orange" title="SMS na Fila para Envio">SMS</span>	
															<span data-ng-if="item.smsStatus=='ERR_TRY_ONE'"  style="font-size:1.4em; color: red" title="Falha na 1a. Tentativa">SMS</span>
															<span data-ng-if="item.smsStatus=='ERR_TRY'"  style="font-size:1.4em; color: red" title="Falha ao Enviar SMS">SMS</span>
															<span data-ng-if="item.smsStatus=='SENDED'"  style="font-size:1.4em; color: green" title="SMS Enviado">SMS</span>
															<span data-ng-if="item.smsStatus=='READED'"  style="font-size:1.4em; color: green" title="SMS Recebido pelo Destinat&aacute;rio">SMS</span>
														</td>
													</tr>
												</tbody>
											</table>								

											<p data-ng-hide="listHistoricInterval == undefined || listHistoricInterval.list.length > 0 || loading" class="text-center">NENHUM REGISTRO</p>
											
										</div>                                                         	            
									</div>
								</div>	
								<br>																		
							</div>

							<div class="col-md-3">
								<div class="row">
									<div class="col-md-12">
										<div class="box box-default">
									
											<div class="box-header">
												<strong>Dispositivo: </strong> {{selectedCompanyDetector.companyDetectorName}} ({{selectedCompanyDetector.detectorName}})
											</div>
											
											<div class="box-body">
												<div class="col-md-12">
													<div class="row">
														<div class="box box-solid" style="font-size: 1.1em">
															
															<div class="box-header with-border">
																<i class="fa fa-bell-o"></i>
																<h3 class="box-title">Sensor/Alarme:</h3>
															</div>
															
															<div class="box-body" style="border: darkgray; border-style: double;  background-color: transparent;">																																	
																<dl class="dl-horizontal">
																	<dt style="width: 90px">Sensor (nome):</dt>
																		<dd style="margin-left: 120px;">{{selectedCompanyDetector.sensorName}}</dd>
																	<dt style="width: 90px">Alarme (nome):</dt>
																		<dd style="margin-left: 120px;">{{findedCompanyDevice.alarmDto.name}}</dd>
																	<dt style="width: 90px">Unidade:</dt>																		
																		<dd style="margin-left: 120px;"> {{findedCompanyDevice.alarmDto.unitMeter.symbol}}</dd>																		
																	<dt style="width: 90px">Range Max:</dt>
																		<dd style="margin-left: 120px;"><strong>{{selectedCompanyDetector.rangeMax}}</strong></dd>
																	<dt style="width: 90px">Detec&ccedil;&atilde;o:</dt>
																		<dd style="margin-left: 120px;"><span data-ng-show="findedCompanyDevice.alarmDto" class="alarm1"> {{findedCompanyDevice.alarmDto.alarm1}}</span></dd>							
																	<dt style="width: 90px">Alerta:</dt>
																		<dd style="margin-left: 120px;"><span data-ng-show="findedCompanyDevice.alarmDto" class="alarm2"> {{findedCompanyDevice.alarmDto.alarm2}}</span></dd>
																	<dt style="width: 90px">Evacua&ccedil;&atilde;o:</dt>
																		<dd style="margin-left: 120px;"><span data-ng-show="findedCompanyDevice.alarmDto" class="alarm3"> {{findedCompanyDevice.alarmDto.alarm3}}</span></dd>
																</dl>											                	
																
															</div>
														</div>
													</div>												
														
												</div>
											</div>
										</div>											
									</div>
								</div>

								<div class="row">
									<div class="col-md-12">
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
											<label data-ng-show='countHistoric > 0'><span class="icon fa fa-text-width"> Total Itens da Pesquisa: {{countHistoric}}</span>
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
									</div>
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

	<div id="printTable" style="visibility:hidden">	
		<div class="col-md-12">
			<h1>Empresa: {{selectedCompanyDetector.company}}</h1>
			<h2>Unidade: {{selectedCompanyDetector.unit}} - &Aacute;rea: {{selectedCompanyDetector.area}} </h2>
			<hr>						
			<h3 class="box-title"><strong>Dispositivo: </strong> {{selectedCompanyDetector.companyDetectorName}} ({{selectedCompanyDetector.detectorName}})					
			<i class="fa fa-bell-o"></i> <h3 class="box-title">Detalhes do Sensor:</h3>																					
			<dl class="dl-horizontal">
				<dt>Sensor (nome):</dt>
					<dd>{{selectedCompanyDetector.sensorName}}</dd>
				<dt>Nome do Alarme / Unidade de Medida:</dt>
					<dd style="margin-left: 120px;"> {{findedCompanyDevice.alarmDto.unitMeter.symbol}}</dd>					
				<dt>Range Max:</dt>
					<dd><strong>{{selectedCompanyDetector.rangeMax}}</strong></dd>
				<dt>Detecri&ccedil;&atilde;:</dt>
					<dd><span data-ng-show="ffindedCompanyDevice.alarmDto.alarm1" class="alarm1"> {{findedCompanyDevice.alarmDto.alarm1}}</span></dd>							
				<dt>Alerta:</dt>
					<dd><span data-ng-show="ffindedCompanyDevice.alarmDto.alarm2" class="alarm2"> {{findedCompanyDevice.alarmDto.alarm2}}</span></dd>
				<dt>Evacua&ccedil;&atilde;o:</dt>
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
					<tr data-ng-repeat="item in listHistoricInterval.list | alarmFilter:selectedfilterAlarm">
						<td>{{item.sensorId}} </td>
						<td>{{item.lastUpdate | date:'dd/MM/yyyy' }}</td>
						<td>{{item.lastUpdate | date:'HH:mm:ss' }}</td>

						<td data-ng-if="tipoGrupo==1"> {{changeToValue(item.value)}} </td>						
						<td data-ng-if="tipoGrupo!=1"> {{item.maxValue}} </td>
						<td data-ng-if="tipoGrupo!=1"> {{item.minValue}} </td>
						
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