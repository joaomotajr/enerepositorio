	
	<div ng-controller="navegacaoController">
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
        <section class="content">
        	<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveCompanyDetector()">Save CompanyDetector</button>        	        		
        		<button type="button" class="btn btn-primary" ng-click="getCompanyDetector()">Get CompanyDetector</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneCompanyDetector()">Get One CompanyDetector</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneCompanyDetectorDevice()">Get Device CompanyDetector </button>
        		<button type="button" class="btn btn-primary" ng-click="getOneCompanyDetectorDeviceByArea()">Get Area CompanyDetector  </button>
        		<button type="button" class="btn btn-primary" ng-click="deletarCompanyDetector()">Deletar CompanyDetector</button>
        		
        		        		
       		</div>
        
        	<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveCompanyDevice()">Save CompanyDevice</button>        		
        		<button type="button" class="btn btn-primary" ng-click="saveCompanyDevices()">Save CompanyDevices</button>        		
        		<button type="button" class="btn btn-primary" ng-click="getCompanyDevice()">Get CompanyDevice</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneCompanyDevice()">Get One CompanyDevice</button>
        		<button type="button" class="btn btn-primary" ng-click="deletarCompanyDevice()">Deletar CompanyDevice</button>
        		
        		        		
       		</div>
        	<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveArea()">Save Area</button>
        		<button type="button" class="btn btn-primary" ng-click="getArea()">Get Area</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneArea()">Get One Area</button>
        		<button type="button" class="btn btn-primary" ng-click="deletarArea()">Deletar Area</button>        		
       		</div>
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveUnit()">Save Unit</button>
        		<button type="button" class="btn btn-primary" ng-click="getUnit()">Get Unit</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneUnit()">Get One Unit</button>        		
        		<button type="button" class="btn btn-primary" ng-click="getUnitFilter()">Get One Unit Filter</button>
        		<button type="button" class="btn btn-primary" ng-click="deletarUnit()">Deletar Unit</button>        		
       		</div>
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveCompany()">Save Company</button>
        		<button type="button" class="btn btn-primary" ng-click="getCompany()">Get Company</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneCompany()">Get One Company</button>
        		<button type="button" class="btn btn-primary" ng-click="deletarCompany()">Deletar Company</button>        		
       		</div>
       		<hr />
       		
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveController()">Save Controller</button>
        		<button type="button" class="btn btn-primary" ng-click="getController()">Get Controller</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneController()">Get One Controller</button>
        		<button type="button" class="btn btn-primary" ng-click="deletarController()">Delete Controller</button>          		
       		</div>
       		
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveGas()">Save Gas</button>
        		<button type="button" class="btn btn-primary" ng-click="getGas()">Get Gas</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneGas()">Get One Gas</button>
        		<button type="button" class="btn btn-primary" ng-click="deletarGas()">Delete Gas</button>        		
       		</div>
       		
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveSensor()">Save Sensor</button>
        		<button type="button" class="btn btn-primary" ng-click="getSensor()">Get Sensor</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneSensor()">Get One Sensor</button>
        		<button type="button" class="btn btn-primary" ng-click="deletarSensor()">Delete Sensor</button>        		
       		</div>
       		
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveTransmitter()">Save Transmitter</button>
        		<button type="button" class="btn btn-primary" ng-click="getTransmitter()">Get Transmitter</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneTransmitter()">Get One Transmitter</button>   
				<button type="button" class="btn btn-primary" ng-click="deletarTransmitter()">delete Transmitter</button>        		     		
       		</div>
       		<br />
       		<div class="row">        		
        		<button type="button" class="btn btn-primary" ng-click="saveDetector()">Save Detector</button>
        		<button type="button" class="btn btn-primary" ng-click="getDetector()">Get Detector</button>
        		<button type="button" class="btn btn-primary" ng-click="getOneDetector()">Get One Detector</button>
        		<button type="button" class="btn btn-primary" ng-click="deletarDetector()">delete Detector</button>        		
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