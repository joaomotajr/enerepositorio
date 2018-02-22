
<span data-ng-if="item.artefact">{{item.artefact}}</span>

<span data-ng-if="!item.artefact && item.deviceType=='DETECTOR'">{{item.gasDto.name}}</span>
<span data-ng-if="!item.artefact && item.deviceType!='DETECTOR'">{{item.deviceType}}</span>

<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='LEL_PERCENT_METRO'"> LEL%m</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='LEL_PERCENT'"> LEL%</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='PERCENT_VOLUME'"> VOL%</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" data-ng-if="item.unitMeterGases=='GRAUS_CELSIUS'"> °C'</span>
<span style="vertical-align:super;font-size:0.6em;color:gray" 
    data-ng-if="item.unitMeterGases!='LEL_PERCENT' &&  item.unitMeterGases!='PERCENT_VOLUME' && item.unitMeterGases!='GRAUS_CELSIUS' && item.unitMeterGases!='LEL_PERCENT_METRO'">{{item.unitMeterGases}}</span>



    