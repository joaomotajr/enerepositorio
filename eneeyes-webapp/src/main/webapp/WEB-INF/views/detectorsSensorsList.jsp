	<div class="col-md-12">						    	
		<div class="box box-info collapsed-box">
			<div class="box-header with-border"><strong>Lista de Detectores </strong> <a class="text-red" data-widget="collapse" data-ng-hide="selectedCompanyDetector"> ( Clique e Selecione um Detector Cadastrado)</a>
				<div class="box-tools pull-right" title="Clique para Selecionar/Verificar Detector(es)">
					<button class="btn btn-box-tool" data-widget="collapse">
						<i class="fa fa-plus"></i>
					</button>
				</div>					                    
			</div>					                	 
			<div class="box-body">
				<div class="col-md-8">
					<div style="max-height: 300px; overflow: auto">					                    							                           
						<table id="sensorDetails" class="display">
							<thead>
								<tr>
									<th></th>
									<th>Detector</th>
									<th>Modelo</th>																
									<th>Ação</th>																																						
								</tr>
							</thead>
							<tbody>                                                        
								<tr data-ng-repeat="item in detectors">
									<td class="details-control"></td>
									<td data-ng-class="{'selected': item.uid == selectedCompanyDetector.detectorDto.uid }">{{item.name}}</td>
									<td data-ng-class="{'selected': item.uid == selectedCompanyDetector.detectorDto.uid }">{{item.model}}</td>																
									<td>																	
										<div data-ng-if="item.uid == selectedCompanyDetector.detectorDto.uid">
											<button type="button" class="btn btn-danger btn-xs" data-ng-click="selecionarDetector(item)" disabled>Selecionado</button>
										</div>	
										<div data-ng-if="item.uid != selectedCompanyDetector.detectorDto.uid">
											<button type="button" class="btn btn-xs" data-ng-class="(selectedCompanyDetector.uid == null) ? 'btn-primary' : 'btn-default'" 
											data-ng-click="selecionarDetector(item)" data-ng-disabled="selectedCompanyDetector.uid != null"> Selecionar </button>
										</div>																							    								
									</td>																		
								</tr>                                                               
							</tbody>
						</table>
					</div>    
				</div>
				
				<div class="col-md-4">							    
					<input type="file" id="idInputImageDetector" style='display:none'>									                						                    
					<img class="profile-user-img img-responsive imgDetector" style="margin: 0 auto" 
						data-ng-src="{{selectedCompanyDetector.detectorDto.image}}" onError="this.src='/assets/img/cover.jpg'">
						
					<p class="text-muted text-center data-ng-binding">
						{{selectedCompanyDetector.detectorDto.name}}
						<span data-ng-show="selectedCompanyDetector.detectorDto.name"> - </span>
						{{selectedCompanyDetector.detectorDto.model}}
					</p>
																													
				</div>
				
			</div>
			
		</div>							    	
	</div>
