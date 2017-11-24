<div data-ng-controller="logAuditoriaController">    
	<div class="row">
		<div class="col-md-12">
		
			<div class="box box-primary">						
				<div class="box-header with-border"><strong style="font-size:1.4em">Auditoria do Sistema</strong></div>
				
				<div class="box-body">					

					<div class="row">						                                                    
						<div class="col-md-12">                                                        
							<div class="box box-solid box-info" style="padding-bottom: 0px !important; margin-bottom: 0px !important;">
								
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
													<label style="margin-top: 2px !important; padding-right: 5px !important;" title="Mostrando MÃƒÂ¡ximo e Minimo"><span class="icon fa fa-caret"></span>Exibir/Exportar</label>
												</div>										        	
												<div class="form-group">
														<div class="col-md-3" style="padding-right: 5px !important;">
														<button id="exportRel" type="button" class="btn btn-default btn-xs form-control" 
															data-ng-class="(listHistoric.list || listHistoricInterval.list) ? 'selected' : 'disabled'">
														<span class="icon fa fa-file-text"></span> Relat&oacute;rio</button>
													</div>
													<div class="col-md-3" style="padding-right: 5px !important; padding-left: 5px !important;">
														<button id="exportExcel" type="button" class="btn bg-olive btn-xs form-control" title="ExportaÃ§Ã£o Permitida atÃ© 500 Linhas" 
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
												<div class="col-md-6" style="padding-right: 3px !important; padding-left: 3px !important;">
													<div class="form-group">
														<label class="control-label">Buscar por Intervalos Pr&eacute;-Definidos: </label> <br />								
														<div class="btn-group" role="group" aria-label="Basic example">
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 1}" 
																	data-ng-click="interval = enumInterval.UMA_HORA; getHistorics(0);" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 hora</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 6}" 
																	data-ng-click="interval = enumInterval.SEIS_HORAS; getHistorics(0);" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">6h</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 12}" 
																	data-ng-click="interval = enumInterval.DOZE_HORAS; getHistorics(0);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true"> 12h</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 24}" 
																	data-ng-click="interval = enumInterval.UM_DIA; getHistorics(0);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">1 dia</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 48}" 
																	data-ng-click="interval = enumInterval.DOIS_DIAS; getHistorics(0);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">2d</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 168}" 
																	data-ng-click="interval = enumInterval.SETE_DIAS; getHistorics(0);"  data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">7d</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 30}" 
																	data-ng-click="interval = enumInterval.UM_MES; getHistorics(0);" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">30d</button>												  												  
														</div>
													</div>
												</div>
												
												<div class="col-md-2" style="padding-right: 5px !important; padding-left: 3px !important;">
													<div class="form-group">
														<label class="control-label">Data inicio</label>									                	 
		
															<div class='input-group date' id='dateInA'>
															<input type="text" class="form-control" data-ng-model="dateInA" name="dateInA">
															<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
															</span>
														</div>													                					                			                                                
													</div>                                                                    
												</div>
												
												<div class="col-md-2" style="padding-right: 5px; padding-left: 3px;">
													<div class="form-group">
														<label class="control-label">Data Fim</label>							                	
														<div class='input-group date' id='dateOutA'>
															<input type="text" class="form-control" data-ng-model="dateOutA" name="dateOutA">
															<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
															</span>
														</div>							                					                							                                                
													</div>                                                                    
												</div>
												
												<div class="col-md-2">
													<div class="form-group">
														<label class="control-label">&nbsp;</label>
														<button type="button" class="btn btn-default btn-sm form-control" data-ng-class="{'btn-primary': selectedButton == 100}" data-ng-click="interval = enumInterval.CUSTOM; getHistoricInterval(0)" data-ng-disabled="(selectedCompanyDetector && selectedCompanySensor) ? false : true">Buscar</button>
													</div>
												</div>																			
											</form>								
										</div>    
										
										<div class="row">
											<div class="col-md-12">
												<table class='zui-table-info' cellspacing="0" width="100%" data-ng-visible="listHistoric">
														<thead>
															<tr>                                                                                 
																<th class="col-md-1">Data</th>
																<th class="col-md-1">Hora</th>	                      		
																<th class="col-sm-1">Entidade</th>
																<th class="col-sm-1">Ação</th>
																<th class="col-sm-1">Usuário</th>
																<th class="col-sm-1">Empresa</th>
																<th class="col-sm-1">Origem</th>																
																
															</tr>
														</thead>
												</table>
												<div style="max-height:420px; height:auto; overflow: auto">
							
													<table class='zui-table-info' cellspacing="0" width="100%" data-ng-visible="listHistoric">							
														<tbody>		
															<div data-ng-show='loading' class="overlay"><i class="fa fa-refresh fa-spin"></i></div>										
															<tr data-ng-repeat="item in logsAuditoria">
																<td class="col-md-1">{{item.dateTime | date:'dd/MM/yyyy'}}</td>
																<td class="col-md-1">{{item.dateTime | date:'HH:mm:ss'}}</td>
																<td class="col-sm-1">{{item.entity}}</td>
																<td class="col-sm-1">{{item.action}}</td>
																<td class="col-sm-1">{{item.userId}}</td>
																<td class="col-sm-1">{{item.companyId}}</td>
																<td class="col-sm-1">{{item.ip}}</td>		
															
																<!-- <td class="col-sm-1">
																	<span data-ng-if="item.soundStatus=='OFF'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: gray" title="Alerta Sonoro n&atilde;o Habilitado"></span>
																	<span data-ng-if="item.soundStatus=='ON'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: red" title="Alerta Sonoro"></span>
																	<span data-ng-if="item.soundStatus=='SILENT'" class="icon fa fa-bullhorn" style="font-size:1.4em; color: green" title="Alerta Silenciado"></span>
																</td>
																 -->
															
															</tr>
														</tbody>
													</table>								
		
													<p data-ng-hide="logsAuditoria == undefined || logsAuditoria.list.length > 0 || loading" class="text-center">NENHUM REGISTRO</p>
													
												</div>                                                         	            
											</div>
										</div>	
										<br>																		
									</div>
		
									<div class="col-md-3">
										<!-- <div class="row" data-ng-show="selectedCompanySensor">
											<div class="col-md-12">
												<div class="box box-default">
											
													<div class="box-header">
														<h4 class="box-title">{{selectedCompanyDetector.companyDetectorName}} - {{selectedCompanySensor.name}}</h4>											 	
													</div>
													
													<div class="box-body">
														<div class="col-md-12">
															<div class="row">
																<div class="box box-solid" style="font-size: 1.1em">
																	
																	<div class="box-header with-border">
																		<i class="fa fa-bell-o"></i>
																		<h3 class="box-title">Detalhes do Sensor:</h3>
																	</div>
																	
																	<div class="box-body" style="border: darkgray; border-style: double;  background-color: transparent;">
																																			
																		<dl class="dl-horizontal">
																			<dt style="width: 90px">Alarme:</dt>
																				<dd style="margin-left: 120px;">{{selectedSensorAlarm.name}}</dd>
																			<dt style="width: 90px">Unidade:</dt>
																				<dd style="margin-left: 120px;">{{selectedSensorAlarm.unitMeterGases}}</dd>																		
																			<dt style="width: 90px">Range Max:</dt>
																				<dd style="margin-left: 120px;"><strong>{{selectedCompanySensor.rangeMax}}</strong></dd>
																			<dt style="width: 90px">Detec&ccedil;&atilde;o:</dt>
																				<dd style="margin-left: 120px;"><span data-ng-show="selectedCompanySensor" class="alarm1"> {{selectedSensorAlarm.alarm1}}</span></dd>							
																			<dt style="width: 90px">Alerta:</dt>
																				<dd style="margin-left: 120px;"><span data-ng-show="selectedCompanySensor" class="alarm2"> {{selectedSensorAlarm.alarm2}}</span></dd>
																			<dt style="width: 90px">Evacua&ccedil;&atilde;o:</dt>
																				<dd style="margin-left: 120px;"><span data-ng-show="selectedCompanySensor" class="alarm3"> {{selectedSensorAlarm.alarm3}}</span></dd>
																		</dl>											                	
																		
																	</div>
																</div>
															</div>												
																
														</div>
													</div>
												</div>											
											</div>
										</div> -->
		
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
	
		</div>
	</div>	
	
</div>