

    
<div class="col-md-9">
	<div class="box box-primary" ng-show="selectedCompany.unitsDto.length > 0">
					
		<div class="box-header with-border"><strong style="font-size:1.4em"><i class='fa fa-building'></i> {{selectedUnit.name}}</strong></div>
		
		<div class="box-body">
						
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs">
   					<li><a href="#tabUnit_1" data-toggle="tab">Cadastro</a></li>
			       	<li><a href="#tabUnit_2" data-toggle="tab">Mapa</a></li>
			       	<li><a href="#tabUnit_3" data-toggle="tab">Detalhes</a></li>
			    	<li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
			    </ul>
				
				<div class="tab-content">		    	
					       	
			       	<div class="tab-pane active" id="tabUnit_1">	    
			       		<div class="row">
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">Nome</label>
						                <input id="idUnitName" class="form-control" placeholder="Nome do Unidade / Matriz" ng-model="selectedUnit.name">
						            </div>
						        </div>
						
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">E-Mail</label>
						                <input class="form-control" placeholder="E-mail" ng-model="selectedUnit.email">
						            </div>
						        </div>
						    </div>
						
						<div class="row">
						        <div class="col-md-8">
						            <div class="form-group">
						                <label class="control-label">Endereço</label>
						                <input id="idUnitName" class="form-control" placeholder="Endereço e Número" ng-model="selectedUnit.address">
						            </div>
						        </div>
						
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Cidade</label>
						                <input class="form-control" placeholder="Cidade" ng-model="selectedUnit.city">
						            </div>
						        </div>
						    </div>
						
						<div class="row">
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Estado</label>
						                <input id="idUnitName" class="form-control" placeholder="Estado" ng-model="selectedUnit.state">
						            </div>
						        </div>
						
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">CEP</label>
						                <input class="form-control" placeholder="CEP" ng-model="selectedUnit.zip">
						            </div>
						        </div>
						
						        <div class="col-md-4">
						            
					                <label class="control-label">Tipo de Unidade</label>
					                <div class="form-group">						                
					                 	<label><input type="radio" ng-model="selectedUnit.unitType" value="UNICA" />&nbsp; Unica</label>
       									<label><input type="radio" ng-model="selectedUnit.unitType" value="MATRIZ" />&nbsp; Matriz</label>        
					                 	<label><input type="radio" ng-model="selectedUnit.unitType" value="FILIAL" />&nbsp;	Filial</label>						                
					                </div>
						            
						        </div>
						    </div>
						
						<div class="row">
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Contato(s)</label>
						                <input id="idUnitName" class="form-control" placeholder="Nome do Contato" ng-model="selectedUnit.contact">
						            </div>
						        </div>
						
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Fone</label>
						                <input class="form-control" placeholder="Fone" ng-model="selectedUnit.phone">
						            </div>
						        </div>
						
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Celular</label>
						                <input class="form-control" placeholder="Celular" ng-model="selectedUnit.mobile">
						            </div>
						        </div>
						    </div>
			       		
			       		<div class="row">
			       			<div class="col-md-12">
			       				<button type="button" ng-click="newUnit();" ng-show="btnNewUnit" class="btn btn-success pull-right">    Nova    </button>								
								<span class="pull-right">   </span>
			       				<button type="button" ng-click="saveUnit();" ng-model="btnSaveUnit" class="btn btn-primary pull-right" ng-disabled="(selectedUnit.name) ? false : true">   Salvar   </button>		       				
			       				<span class="pull-right">   </span>
			       				<button type="button" ng-click="deleteUnit();" class="btn btn-danger pull-right" ng-disabled="(selectedUnit.uid) ? false : true">   Excluir   </button>								
							</div>
						</div>
												
						<div class="row" ng-show="selectedUnit.areasDto.length <= 0">
							<div class="col-md-6">
								<label>Incluir Área</label>						
								<div class="entry input-group">					
							        <input type="text" class="form-control" placeholder="Digite o Nome da Area" ng-model="areaNameInit">
							        <span class="input-group-btn">
							        <button class="btn btn-info btn-flat" type="button" ng-click="saveAreaInit();">OK</button>
							        </span>
								</div>
							</div>
						</div>     
			       	</div><!-- /.tab-pane -->
			       	
			       	<div class="tab-pane" id="tabUnit_2">	    
			       		Em Construção Mapa ...     
			       	</div><!-- /.tab-pane -->
			       	
			       	<div class="tab-pane" id="tabUnit_3">
			       		Em Construção Detalhes ...	        
			     	</div><!-- /.tab-pane -->
			     				     	
			   </div>	<!-- /.tab-content -->		   						
			</div>
						
		</div>		
	</div>	
</div>     