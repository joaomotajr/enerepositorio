	
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
        		<button type="button" class="btn btn-primary" ng-click="save()">Save Area</button>
        		<button type="button" class="btn btn-primary" ng-click="get()">Get Area</button>
        		<button type="button" class="btn btn-primary" ng-click="getOne()">Get One Area</button>
        		<button type="button" class="btn btn-primary" ng-click="saveUnit()">Save Unit</button>
        		<button type="button" class="btn btn-primary" ng-click="setUnit2()">Set Unit</button>
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