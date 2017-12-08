<div>
	<div class="form-group" data-ng-hide="isFrom == 'MASTER'">				                    	
		<input class="form-control" type="text" data-ng-model="selectedCompany.name" disabled>			                        				                        							                                                                    
	</div>
	                                                                                                                            
	<div class="form-group" data-ng-show="isFrom == 'MASTER'">
	
		<select class="form-control" data-live-search="true" 
			style="width: 100%;" tabindex="-1" aria-hidden="true"                              
				data-ng-options="item as item.name for item in companies | orderBy: 'name' track by item.uid" 
						 data-ng-model="selectedCompany" 
						 data-ng-change="changeCompany();" required>
						 <option value="">Selecione</option> 
	
		</select>																                                                                        
	</div>
</div>