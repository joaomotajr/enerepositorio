	<div data-ng-controller="dashController">
		<div class="row">
       		<div class="col-md-12">

				<div class="box box-primary" style="margin-bottom: 8px;">
				  
					<div class="box-header with-border">
						<h3 class="box-title">STATUS</h3>
						<div class="box-tools pull-right"></div>
					</div>
					
					<div class="box-body">
					
				          <!-- Info boxes -->
				          <div class="row">
				            
				            <div class="col-md-3 col-sm-6 col-xs-12">
				              <div class="info-box">
				                <span class="info-box-icon bg-aqua"><i class="fa fa-gears"></i></span>
				                <div class="info-box-content">
				                  <span class="info-box-text">MONITORADOS</span>
				                  <span class="info-box-number">125<small></small></span>
				                </div><!-- /.info-box-content -->
				              </div><!-- /.info-box -->
				            </div><!-- /.col -->
				            
				            <div class="col-md-3 col-sm-6 col-xs-12">
				              <div class="info-box">
				                <span class="info-box-icon bg-red"><i class="fa fa-thumbs-o-down"></i></span>
				                <div class="info-box-content">
				                  <span class="info-box-text">A L E R T A</span>
				                  <span class="info-box-number">03</span>
				                </div><!-- /.info-box-content -->
				              </div><!-- /.info-box -->
				            </div><!-- /.col -->
				
				            <!-- fix for small devices only -->
				            <div class="clearfix visible-sm-block"></div>
				
				            <div class="col-md-3 col-sm-6 col-xs-12">
				              <div class="info-box">
				                <span class="info-box-icon bg-green"><i class="fa fa-thumbs-o-up"></i></span>
				                <div class="info-box-content">
				                  <span class="info-box-text">O K</span>
				                  <span class="info-box-number">118</span>
				                </div><!-- /.info-box-content -->
				              </div><!-- /.info-box -->
				            </div><!-- /.col -->
				            
				            <div class="col-md-3 col-sm-6 col-xs-12">
				              <div class="info-box">
				                <span class="info-box-icon bg-yellow"><i class="fa fa-houzz"></i></span>
				                <div class="info-box-content">
				                  <span class="info-box-text">A T E N Ç Ã O</span>
				                  <span class="info-box-number">04</span>
				                </div><!-- /.info-box-content -->
				              </div><!-- /.info-box -->
				            </div><!-- /.col -->
				          </div><!-- /.row -->
				
				          <!-- Main row -->
				          <div class="row">
				          	<div class="col-md-8">
				          	
		          	              <div class="box box-info">
					                <div class="box-header with-border">
					                  <h3 class="box-title">Ultimos Alertas</h3>
					                  <div class="box-tools pull-right">
					                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
					                  </div>
					                </div><!-- /.box-header -->
					                <div class="box-body">
					                  <div class="table-responsive">
					                    <table class="table no-margin">
					                      <thead>
					                        <tr>
					                          <th>Alerta ID</th>
					                          <th>Empresa/Unidade</th>
					                          <th>Área</th>
					                          <th>Dispositivo</th>
					                        </tr>
					                      </thead>
					                      <tbody>
					                        <tr>
					                          <td><a href="pages/examples/invoice.html">OR9842</a></td>
					                          <td>Loga</td>
					                          <td><span class="label label-success">Desligado</span></td>
					                          <td><div class="sparkbar" data-color="#00a65a" data-height="20">Sensor A1</div></td>
					                        </tr>
					                        <tr>
					                          <td><a href="pages/examples/invoice.html">OR1848</a></td>
					                          <td>Nestle</td>
					                          <td><span class="label label-warning">Pendente</span></td>
					                          <td><div class="sparkbar" data-color="#f39c12" data-height="20">Sensor A2</div></td>
					                        </tr>
					                        <tr>
					                          <td><a href="pages/examples/invoice.html">OR7429</a></td>
					                          <td>Vopak</td>
					                          <td><span class="label label-danger">Reestart</span></td>
					                          <td><div class="sparkbar" data-color="#f56954" data-height="20">Sensor A3</div></td>
					                        </tr>
					                        <tr>
					                          <td><a href="pages/examples/invoice.html">OR7429</a></td>
					                          <td>Construtora Quintella</td>
					                          <td><span class="label label-info">Calibragem</span></td>
					                          <td><div class="sparkbar" data-color="#00c0ef" data-height="20">Sensor A4</div></td>
					                        </tr>
					                        <tr>
					                          <td><a href="pages/examples/invoice.html">OR1848</a></td>
					                          <td>INB</td>
					                          <td><span class="label label-warning">Alarmado</span></td>
					                          <td><div class="sparkbar" data-color="#f39c12" data-height="20">Sensor A5</div></td>
					                        </tr>
					                        <tr>
					                          <td><a href="pages/examples/invoice.html">OR7429</a></td>
					                          <td>SCGás</td>
					                          <td><span class="label label-danger">Atenção</span></td>
					                          <td><div class="sparkbar" data-color="#f56954" data-height="20">Sensor A6</div></td>
					                        </tr>
					                        <tr>
					                          <td><a href="pages/examples/invoice.html">OR9842</a></td>
					                          <td>Anglo American</td>
					                          <td><span class="label label-success">Instalação</span></td>
					                          <td><div class="sparkbar" data-color="#00a65a" data-height="20">Sensor A7</div></td>
					                        </tr>
					                      </tbody>
					                    </table>
					                  </div><!-- /.table-responsive -->
					                </div><!-- /.box-body -->
					                <div class="box-footer clearfix">
					                  <a href="javascript::;" class="btn btn-sm btn-info btn-flat pull-left">Baixar Alertas</a>
					                  <a href="javascript::;" class="btn btn-sm btn-default btn-flat pull-right">Ver Todos Alertas</a>
					                </div><!-- /.box-footer -->
					              </div><!-- /.box -->
							
				          	</div>
				          	<div class="col-md-4">
				          	
				          	<div class="box box-primary">
				                <div class="box-header with-border">
				                  <h3 class="box-title">Dispositivos para Manutenção Preventiva</h3>
				                  <div class="box-tools pull-right">
				                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
				                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
				                  </div>
				                </div><!-- /.box-header -->
				                <div class="box-body">
				                  <ul class="products-list product-list-in-box">
				                    <li class="item">
				                      <div class="product-img">
				                        <img src="/assets/img/default-50x50.gif" alt="Product Image">
				                      </div>
				                      <div class="product-info">
				                        <a href="javascript::;" class="product-title">Loga<span class="label label-warning pull-right"></span></a>
				                        <span class="product-description">
				                          Sensor A1
				                        </span>
				                      </div>
				                    </li><!-- /.item -->
				                    <li class="item">
				                      <div class="product-img">
				                        <img src="/assets/img/default-50x50.gif" alt="Product Image">
				                      </div>
				                      <div class="product-info">
				                        <a href="javascript::;" class="product-title">Nestle <span class="label label-info pull-right"></span></a>
				                        <span class="product-description">
				                          Sensor A2
										 
				                        </span>
				                      </div>
				                    </li><!-- /.item -->
				                    <li class="item">
				                      <div class="product-img">
				                        <img src="/assets/img/default-50x50.gif" alt="Product Image">
				                      </div>
				                      <div class="product-info">
				                        <a href="javascript::;" class="product-title">Vopak<span class="label label-danger pull-right"></span></a>
				                        <span class="product-description">
				                          Sensor A3
				                        </span>
				                      </div>
				                    </li><!-- /.item -->
				                    <li class="item">
				                      <div class="product-img">
				                        <img src="/assets/img/default-50x50.gif" alt="Product Image">
				                      </div>
				                      <div class="product-info">
				                        <a href="javascript::;" class="product-title">Construtora Quintella<span class="label label-success pull-right"></span></a>
				                        <span class="product-description">
				                          Sensor A4
				                        </span>
				                      </div>
				                    </li><!-- /.item -->
				                  </ul>
				                </div><!-- /.box-body -->
				                <div class="box-footer text-center">
				                  <a href="javascript::;" class="uppercase">Ver Todos Dispositivos</a>
				                </div><!-- /.box-footer -->
				              </div><!-- /.box -->  	
				          	
				          	
				          	</div>
				          
				          </div><!-- /.row -->
										
					
       				</div>
      			</div>	
       		</div>
    	</div>    
    </div>
<!--     <script src="/assets/plugins/adminlte/dashboard2.js"></script>           -->
<!--     <script src="/assets/plugins/adminlte/dashboard.js"></script>            -->

    