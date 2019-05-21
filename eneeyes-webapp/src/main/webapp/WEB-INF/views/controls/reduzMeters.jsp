
<span data-ng-if="item.deviceType !='DETECTOR'">{{item.deviceDescription}}</span>
<span data-ng-if="item.deviceType =='DETECTOR'">Gás: {{item.gasName}}</span>
<span data-ng-if="!item.deviceType ">{{item.gasDto.name}}</span>
<span data-ng-if="!item.deviceType  && item.deviceType !='DETECTOR'">{{item.deviceType}}</span>
<span style="vertical-align:super;font-size:0.6em;color:gray"> {{item.unitMeterSymbol}}</span>
