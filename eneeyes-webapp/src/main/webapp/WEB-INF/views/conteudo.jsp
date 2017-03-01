<!-- <div id="content" bind-unsafe-html="ajaxcontent"></div>  -->

<div data-ng-controller="conteudoController">

	<div class="content-wrapper">
	
		<section class="content-header"></section>	
			
		<section class="content">		
			
			<div id="resultDanger" class="alert alert-danger alert-dismissable dj-hide" role="alert" data-ng-class="{'dj-hide':!alertDanger}">
				<button type="button" class="close" ><span data-ng-click="clearAlert();">&times;</span></button>
				<h4 data-ng-cloak><i class="icon fa fa-info"></i> Aten��o!</h4>
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