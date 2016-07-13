  
<div id="content" bind-unsafe-html="ajaxcontent"></div>

<div id="content">
	<div class="content-wrapper">
	
		<section class="content-header">			
		</section>	
	
		<section class="content">		
				
			<div class="nav-tabs-custom" id='tabs' ng-show="tabsShow.length > 0">
			    <ul class="nav nav-tabs">
			    	<li ng-repeat="item in tabsShow">
				    	<a href="{{'#'+ item.name}}" id="{{'id_' + item.name}}"  data-toggle='tab'>{{item.link}}
				    		<button type='button' class='close' aria-label='Close' ng-click="removeTab($index)"><span aria-hidden='true'>  &times; </span></button>
				    	</a>
			    	</li>		        
			    </ul>
			    		    
			    <div class="tab-content">			    			    	
			    	<div id="{{item.name}}" class="tab-pane" bind-unsafe-html="item.body" ng-repeat="item in tabsShow">
			    	</div>			    	
			    </div>			    		    
			    
			</div>	  
		</section>
	</div>
</div>