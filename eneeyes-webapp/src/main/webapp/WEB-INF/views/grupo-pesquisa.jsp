<div class="inner" style="min-height: 700px;">
    <div class="row">
        <div class="col-lg-12" ng-controller="UserTypeController">
            <h3> Gerenciamento e Pesquisa de Grupos</h3>
        </div>
    </div>
	<hr />
	<div class="row">
		<div class="col-md-12">
	    	<div class="box dark">
	        	<header>
	            	<div class="icons"><i class="icon-edit"></i></div>
	            	<h5>Pesquisa</h5>
	            	<div class="toolbar">
		                <ul class="nav">
		                    <li>
		                        <a class="accordion-toggle minimize-box" data-toggle="collapse" href="#pesquisas">
		                            <i class="icon-chevron-up"></i>
		                        </a>
		                    </li>
		                </ul>
		            </div>
		        </header>
		        <div ng-controller="UserTypeController" id="pesquisas" class="body collapse in">
	                <div class="tab-content">
	                
	                	<!-- GRUPO -->
	                     <div id="grupo" class="tab-pane fade in active" ng-controller="GrupoController">
			                <jsp:include page="grupo.jsp"/>
	                    </div>
	                    <!-- GRUPO - FIM -->
	                    
	                </div>
		    	</div>
			</div>
		</div>
	</div>
	
</div>