	
<select class="form-control" data-live-search="true" 
   style="width: 100%;" tabindex="-1" aria-hidden="true"                              
   data-ng-options="item as item.companyDetectorName for item in companyDetectors | companyFilter:search | orderBy: 'companyDetectorName' track by item.companyDetectorId" 
   data-ng-model="selectedCompanyDetector" 
   data-ng-change="changeCompanyDetector();">
   <option value="">Selecione</option> 
</select>																                                                                        




