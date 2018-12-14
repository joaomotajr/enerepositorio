<select class="form-control" data-live-search="true" 
   style="width: 100%;" tabindex="-1" aria-hidden="true"                              
   data-ng-options="item as item.companyDetectorName for item in companyDetectors | companyFilter:search | detectorFilter:search2 | orderBy: 'companyDetectorName' track by item.companyDeviceId" 
   data-ng-model="selectedCompanyDetector" 
   data-ng-change="changeCompanyDetector();">
   <option value="">Selecione</option> 
</select>