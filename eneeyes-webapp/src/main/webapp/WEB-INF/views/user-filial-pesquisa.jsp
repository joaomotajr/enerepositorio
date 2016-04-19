<div class="inner" style="min-height: 700px;">
    <div class="row">
        <div class="col-lg-12" ng-controller="UserTypeController">
            <h3> Gerenciamento e Pesquisa de Usu&aacute;rios e Filiais</h3>
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
	                <ul class="nav nav-pills-info" ng-controller="FilialController">
	                    <li class="active"><a href="#usuario" data-toggle="tab">Usu&aacute;rio</a></li>
	                    <li><a href="#filial" data-toggle="tab" ng-click="setUsersByRole()">Filial</a></li>
	                </ul>	                
	                <div class="tab-content">
	                
	                	<!-- USUARIO -->
	                     <div id="usuario" class="tab-pane fade in active" ng-controller="UserController">
			                <jsp:include page="user-pesquisa.jsp"/>
	                    </div>
	                    <!-- USUARIO - FIM -->
	                    
	                    <!-- FILIAL -->
	                     <div id="filial" class="tab-pane fade" ng-controller="FilialController">
							<jsp:include page="filial-pesquisa.jsp"/>
	                    </div>
	                    <!-- FILIAL - FIM -->
	                    
	                </div>
		    	</div>
			</div>
		</div>
	</div>
	
</div>