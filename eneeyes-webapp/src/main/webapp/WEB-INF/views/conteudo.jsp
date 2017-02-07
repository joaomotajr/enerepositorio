<!-- <div id="content" bind-unsafe-html="ajaxcontent"></div>  -->

<div id="contentTab">
	<div class="content-wrapper">
	
		<section class="content-header"></section>	
			
		<section class="content">		
		
			<div class="alert alert-danger alert-dismissable" role="alert" data-ng-show="alertDanger">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				<h4><i class="icon fa fa-info"></i> Atenção!</h4>
				{{alertDanger}}
			</div>
			
			<div id="content" data-bind-unsafe-html="ajaxcontent"></div>
						
			<div class="nav-tabs-custom" id='tabs' data-ng-show="tabsShow.length > 0">
			    <ul class="nav nav-tabs">
			    	<li data-ng-repeat="item in tabsShow">
				    	<a href="{{'#'+ item.name}}" id="{{'id_' + item.name}}" class="{{item.link}}"  data-toggle='tab'>{{item.link}} &nbsp; 
				    		<button type='button' class='close' aria-label='Close' data-ng-click="removeTab($index)"><span aria-hidden='true'> &times; </span></button>
				    	</a>
			    	</li>		        
			    </ul>
			    		    
			    <div class="tab-content">			    			    	
			    	<div id="{{item.name}}" class="tab-pane" data-bind-unsafe-html="item.body" data-ng-repeat="item in tabsShow">
			    	</div>			    	
			    </div>			    
			</div>	  
		</section>
	</div>
</div>