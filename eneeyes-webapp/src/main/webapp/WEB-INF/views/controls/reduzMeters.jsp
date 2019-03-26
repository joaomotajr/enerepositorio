
<span data-ng-if="item.deviceType.type!='DETECTOR'">{{item.deviceType.description}}</span>
<span data-ng-if="item.deviceType.type=='DETECTOR'">Gás: {{item.gasName}}</span>
<span data-ng-if="!item.deviceType.type">{{item.gasDto.name}}</span>
<span data-ng-if="!item.deviceType.type && item.deviceType.type!='DETECTOR'">{{item.deviceType.type}}</span>
<span style="vertical-align:super;font-size:0.6em;color:gray"> {{item.unitMeter.symbol}}</span>
