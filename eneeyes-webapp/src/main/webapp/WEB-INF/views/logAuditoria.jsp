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
										<!-- <div class="row">								
											<div class="col-md-5" >																			
											</div>
											
											<div class="col-md-7" >													    								
											</div>				        			
										</div> -->
										
										<hr style="margin-top: 5px !important; margin-bottom: 5px !important;">
										
										<div class="row">
											<form class="form" name="userForm">
												<div class="col-md-5" style="padding-right: 3px !important; padding-left: 3px !important;">
													<div class="form-group">
														<label class="control-label">Buscar por Intervalos Pr&eacute;-Definidos: </label> <br />								
														<div class="btn-group" role="group" aria-label="Basic example">
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 1}" 
																	data-ng-click="interval = enumInterval.UMA_HORA; getLogAuditoria(0);">1 hora</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 6}" 
																	data-ng-click="interval = enumInterval.SEIS_HORAS; getLogAuditoria(0);">6h</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 12}" 
																	data-ng-click="interval = enumInterval.DOZE_HORAS; getLogAuditoria(0);" > 12h</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 24}" 
																	data-ng-click="interval = enumInterval.UM_DIA; getLogAuditoria(0);" >1 dia</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 48}" 
																	data-ng-click="interval = enumInterval.DOIS_DIAS; getLogAuditoria(0);" >2d</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 168}" 
																	data-ng-click="interval = enumInterval.SETE_DIAS; getLogAuditoria(0);" >7d</button>
																<button type="button" class="btn btn-default" data-ng-class="{'btn-primary': selectedButton == 30}" 
																	data-ng-click="interval = enumInterval.UM_MES; getLogAuditoria(0);">30d</button>												  												  
														</div>
													</div>
												</div>
												
												<div class="col-md-3" style="padding-right: 5px !important; padding-left: 3px !important;">
													<div class="form-group">
														<label class="control-label">Data inicio</label>									                	 
		
															<div class='input-group date' id='dateInAuditoria'>
															<input type="text" class="form-control" data-ng-model="dateInAuditoria" name="dateInAuditoria">
															<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
															</span>
														</div>													                					                			                                                
													</div>                                                                    
												</div>
												
												<div class="col-md-3" style="padding-right: 5px; padding-left: 3px;">
													<div class="form-group">
														<label class="control-label">Data Fim</label>							                	
														<div class='input-group date' id='dateOutAuditoria'>
															<input type="text" class="form-control" data-ng-model="dateOutAuditoria" name="dateOutAuditoria">
															<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
															</span>
														</div>							                					                							                                                
													</div>                                                                    
												</div>
												
												<div class="col-md-1">
													<div class="form-group">
														<label class="control-label">&nbsp;</label>
														<button type="button" class="btn btn-default btn-sm form-control" data-ng-class="{'btn-primary': selectedButton == 100}" data-ng-click="interval = enumInterval.CUSTOM; getHistoricInterval(0)">
															<i class="fa fa-search"></i>
														</button>
													</div>
												</div>																			
											</form>								
										</div>    
										
										<div class="row">
											<div class="col-md-12">
												<table class='zui-table-info' cellspacing="0" width="100%">
														<thead>
															<tr>                                                                                 
																<th class="col-md-1">Data</th>
																<th class="col-md-1">Hora</th>	                      		
																<th class="col-md-3">Entidade</th>
																<th class="col-md-1">Ação</th>
																<th class="col-md-2">Usuário</th>
																<th class="col-md-2">Empresa</th>
																<th class="col-md-1">Detalhe</th>																
															</tr>
														</thead>
												</table>
												<div style="max-height:420px; height:auto; overflow: auto">
							
													<table class='zui-table-info' cellspacing="0" width="100%">
														<tbody>		
															<div data-ng-show='loading' class="overlay"><i class="fa fa-refresh fa-spin"></i></div>										
															<tr data-ng-repeat="item in logsAuditoria">
																<td class="col-md-1">{{item.dateTime | date:'dd/MM/yyyy'}}</td>
																<td class="col-md-1">{{item.dateTime | date:'HH:mm:ss'}}</td>
																<td class="col-md-3">{{item.entity}}</td>
																<td class="col-md-1">{{item.actionName}}</td>
																<td class="col-md-2">{{item.userName}}</td>
																<td class="col-md-2">
																	<span data-ng-if="item.companyId==null">MASTER</span>
																	<span data-ng-if="item.companyId!=null">{{item.companyName}}</span>
																</td>																
																<td class="col-md-1">																		
																	<a type="button" class="btn btn-info btn-xs"  data-ng-click="details($index)">Detalhes</a>
																</td>															
															</tr>
														</tbody>
													</table>								
		
													<p data-ng-hide="logsAuditoria == undefined || logsAuditoria.length > 0" class="text-center">NENHUM REGISTRO</p>
													
												</div>                                                         	            
											</div>
										</div>	
										<br>																		
									</div>
		
									<div class="col-md-3">										
		
										<div class="row">
											<div class="col-md-12">
												<br>						
												<div class="row" style=" vertical-align: middle; text-align: center;">									
													<label><span class="icon fa fa-columns"> Itens por P&aacute;gina:</span><span class="text-red" data-ng-show="!lenPageValid"> [0 - 2000]</span></label>
												</div>
											
												<div class="row" style=" vertical-align: middle; text-align: center;">
													<div class="col-md-4" style="padding-left:2px; padding-right: 2px;">
														<button class="btn btn-info" type="button" data-ng-show="countPages > 0" data-ng-class="{disabled: currentPage == 0, 'cursor': currentPage != 0}"  data-ng-click="getLogAuditoria(0)">
														&LeftTriangleBar; Primeira
														</button>											
													</div>
													
													<div data-ng-class="{'has-error': !lenPageValid}">
													<div class="col-md-3" style="padding-left:2px; padding-right: 2px;">
														<input id="allownumericwithoutdecimal" data-ng-change="changeLenPage();" data-ng-keydown="validLenPage($event)" type="text" class="form-control" data-ng-model="lenPage">
													</div>
													</div>
											
													<div class="col-md-4" style="padding-left:2px; padding-right: 2px;">
														<button class="btn btn-info" data-ng-show="countPages > 0" data-ng-class="{disabled: currentPage == countPages-1, 'cursor': currentPage != countPages}" data-ng-click=" getLogAuditoria(countPages-1)">
														&uacute;ltima &RightTriangleBar;
														</button>
													</div>
												</div>
												
												<br>
												<hr>									
											
												<div class="row" style=" vertical-align: middle; text-align: center;">
													<label data-ng-show='countAuditoria > 0'><span class="icon fa fa-text-width"> Total Itens da Pesquisa: {{countHistoric}}</span>
												</div>
												<div class="row" style=" vertical-align: middle; display: inline-block; text-align: center;">										
													<ul class="pagination inline">													
														<li data-ng-show="countPages > 0" data-ng-class="{disabled: currentPage == 0, 'cursor': currentPage != 0}">
															<a data-ng-click="n=currentPage-1; getLogAuditoria(n)">&lang;</a>
														</li>
														
														<li data-ng-repeat="n in listPages" data-ng-class="{active: currentPage == n, 'cursor': currentPage !=n }">
															<a data-ng-click="getLogAuditoria(n)" data-ng-bind="n"></a>
														</li>
											
														<li data-ng-show="countPages > 0" data-ng-class="{disabled: currentPage == countPages-1, 'cursor': currentPage != countPages}"> 
															<a data-ng-click="n=currentPage+1; getLogAuditoria(n)">&rang;</a>
														</li>													
													</ul>										
												</div>
												<div class="row">
													<div style="vertical-align: middle; text-align: center; font-size: 05.em">
														<label data-ng-show='countAuditoria > 0'> P&aacute;ginas: {{countPages}}</label>
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
	
	<div id="modalShowMessages" class="modal">                
		<div class="modal-dialog" role="document">
			<div class="modal-content">                            
				
					<div class="modal-content">                            
					<div class="modal-body"style="padding-bottom: 0px; !important">
					
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align:center;font-size:1.5em">
								<strong>AUDITORIA</strong>														
							</div>														                                                                           
						</div>
											
						<div class="box box-primary" style="padding-bottom: 8px; !important; margin-bottom: 8px !important;">
							
							<div class="box-header">
								<h3 class="box-title">Informações Gravadas</h3>							
								<span class="text-muted pull-right"><i class="fa fa-pencil-square-o"></i></span>
							</div>		
										
							<div class="box-body" style="padding-bottom: 0px; !important">
								<form class="form" name="userForm">		
								
									<div class="row">											
										<div class="col-md-12">
										
											<div class="box-body">													
												<table class="table">
													<tr ng-repeat="(key,value) in logAuditoriaDetails">
														<td>{{key}}</td>
														<td>{{value}}</td>
													</tr>
												</table>
											</div>							
											
										</div>																		
														
									</div>    
																										
								</form>
							</div>
						</div>
										
					</div>
					
					<div class="modal-footer">
						<button type="button" class="btn btn-info" disabled><i class="fa fa-print"></i></button>						
						<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-sign-out"></i></button>									                                
					</div>
					
				</div>				
				
			</div>
		</div>		
	</div>
	
</div>