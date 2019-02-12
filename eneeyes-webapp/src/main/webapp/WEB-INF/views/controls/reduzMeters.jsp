
<span data-ng-if="item.deviceType.type!='DETECTOR'">{{item.deviceType.description}}</span>

<span data-ng-if="item.deviceType.type=='DETECTOR'">Gás: {{item.gasName}}</span>
<span data-ng-if="!item.deviceType.type">{{item.gasDto.name}}</span>

<span data-ng-if="!item.deviceType.type && item.deviceType.type!='DETECTOR'">{{item.deviceType.type}}</span>

<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='LEL_PERCENT_METRO'"> LEL%m</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='PERCENT_VOLUME'"> VOL%</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='GRAUS_CELSIUS'"> °C</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='M3_HOUR'"> m³/hora</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='M3'"> m³</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" 
    data-ng-if="item.unitMeterGases!='M3_HOUR' && 
                item.unitMeterGases!='M3' &&
                item.unitMeterGases!='LEL_PERCENT' && 
                item.unitMeterGases!='PERCENT_VOLUME' && 
                item.unitMeterGases!='GRAUS_CELSIUS' && 
                item.unitMeterGases!='LEL_PERCENT_METRO'">{{item.unitMeterGases}}</span>



    