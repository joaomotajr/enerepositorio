	
	<div class="content-wrapper" ng-controller="navegacaoController">
        <!-- Content Header (Page header) -->
        <section class="content-header">        
          <!-- 
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Forms</a></li>
            <li class="active">Editors</li>
          </ol>
           -->          
        </section>

        <!-- Main content - Modificado -->
        <section class="content" style="padding: 0px;">
        	<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveArea()">Save Area</button>
        		<button type="button" class="btn btn-primary" ng-click="getArea()">Get Area</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneArea()">Get One Area</button>        		
       		</div>
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveUnit()">Save Unit</button>
        		<button type="button" class="btn btn-primary" ng-click="getUnit()">Get Unit</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneUnit()">Get One Unit</button>        		
       		</div>
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveCompany()">Save Company</button>
        		<button type="button" class="btn btn-primary" ng-click="getCompany()">Get Company</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneCompany()">Get One Company</button>        		
       		</div>
	         <div class="row">
	            <div class="col-md-12">           
					<div class="col-sm-3">					
					<!-- 		                
			          	<h2>Procurar</h2>	                      
			            
			            <div class="form-group">
			                <label for="input-search" class="sr-only">Search Tree:</label>
			                <input type="input" class="form-control" id="input-search" placeholder="Digite para Procurar..." value="">
			            </div>		                      
			        -->
			        	<div id="treeview-searchable" class="treeview" style="font-size:12px;line-height:0.85"></div>		
			        	
			      </div>
	              
	         </div><!-- /.col-->
            
          </div><!-- ./row -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->