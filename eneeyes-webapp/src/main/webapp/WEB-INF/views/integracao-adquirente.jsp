<section ng-controller="IntegracaoAdquirenteController">            
	<div class="inner">
		<div class="row">
			<div class="col-lg-12">
				<h3> Integração com Adquirente </h3>
			</div>
		</div>
		<hr />
		<div class="row">
			<div class="col-lg-12">
				<div class="box">
					<header class="dark">
						<div class="icons"><i class="icon-cloud-upload"></i></div>
						<h5>Integração dos arquivos</h5>
						<div class="toolbar">
							<ul class="nav pull-right">
								<li>
									<a href="#integracaoTEF" data-toggle="collapse" class="accordion-toggle minimize-box">
										<i class="icon-chevron-up"></i>
									</a>
								</li>
							</ul>
						</div>
					</header>
					<div id="integracaoTEF" class="body collapse in">
						<form class="form-horizontal">
							<div class="form-group">
								<div class="col-lg-8">
									<div class="fileupload fileupload-new col-lg-12" data-provides="fileupload">
										<div class="input-group col-lg-12">
											<span class="btn btn-file btn-info col-lg-12">
											<span class="fileupload-new col-lg-12">Selecione o arquivo ou arraste até aqui</span>
											<span class="fileupload-exists">Alterar Arquivo</span>
												<input type="file" multiple id="dropbox" onchange="angular.element(this).scope().setFiles(this)" />
											</span>
											<a href="#" class="btn btn-danger fileupload-exists" id="excluir-selecao" data-dismiss="fileupload" ng-click="excluirDadosArquivo()">Excluir seleção</a>
											<br/><br/>
											<div class="col-lg-12">
												<i class="icon-file fileupload-exists"></i>
												<span class="fileupload-preview"></span>
												<div id="infoFile"></div><br>
												</div>
												<input type="button" ng-click="uploadFile()" value="Enviar" class="btn btn-success fileupload-exists"/>
												<br/>
											</div>
										</div>
									</div>
									<div class="col-lg-4">
										<label class="control-label" >Status da integração:</label>
										<br>
										<br>
										<span>Importação</span><span class="pull-right"><small>{{progress}}%</small></span>
										<div class="progress mini">
											<div class="progress-bar progress-bar-info" ng-style="{'width': progress+'%'}" role="progressbar" ng-style="{'width': progress+'%'}" role="progressbar" aria-valuenow="1" aria-valuemin="0" aria-valuemax="100"></div>
										</div>
										<span ng-show="inclusaoVisible">Inclusão dos dados</span>
										<img ng-show="inclusaoVisible" src="/assets/img/294.gif"/>
									</div>
								</div>
							</form>
						</div>
       				</div>
   				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						Hist&oacute;rico Integra&ccedil;&atilde;o
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover" id="dataTables-integracao">
	                        	<thead>
									<tr>
										<th>Data</th>
										<th>Adquirente</th>
									</tr>
	                        	</thead>
								<tbody>
									<tr ng-repeat="historicoVenda in datas.listValue" >
										<td>{{historicoVenda.dataProcessamento | date: 'dd/MM/yyyy'}}</td>
										<td>{{historicoVenda.empresaAdquirente}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		
</section>