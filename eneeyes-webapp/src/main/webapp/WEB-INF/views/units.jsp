<style>
   .mapOK {
       color: green;
   }
   
   .mapNOK {
   		color: red;
   	}
</style>

<div class="col-md-9">
	<div data-ng-controller="unitController">
	<!-- <div class="box box-primary" data-ng-show="selectedCompany.unitsDto.length > 0"> -->
	<div class="box box-primary">
					
		<div class="box-header with-border"><strong style="font-size:1.4em"><i class='fa fa-building'></i>Unidade: {{selectedUnit.name}}</strong></div>
		
		<div class="box-body">
						
			<div class="nav-tabs-custom">
				<ul class="nav nav-tabs tabUnit">
   					<li><a href="#tabUnit_1" id="stepTabUnit_1" data-toggle="tab">Cadastro</a></li>
			       	<li><a href="#tabUnit_2" id="stepTabUnit_2" data-toggle="tab">Localização</a></li>			       	
			    	<li class="pull-right"><a href="#" class="text-muted" data-ng-click="getCoordinatesUnit();" title="Validar Local."><i class="fa fa-map" data-ng-class="(mapUnitOK) ? 'mapOK' : 'mapNOK'"></i></a></li>
			    </ul>
							
				<div class="tab-content">		    	
					       	
			       	<div class="tab-pane active" id="tabUnit_1">
			       		<form name="userForm">	    	    
			       			<div class="row">
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">Nome *</label>
						                <span class="text-red" data-ng-show="userForm.username.$error.required && !userForm.username.$pristine">  [Nome Obrigatorio]</span>
						                <span data-ng-show="userForm.username.$error.maxlength">Tamanho Máximo 15 caracteres</span>
						                <input id="idUnitName" class="form-control" placeholder="Nome do Unidade / Matriz" data-ng-model="selectedUnit.name" data-ng-maxlength="15" name="username" required>
						            </div>
						        </div>
						
						        <div class="col-md-6">
						            <div class="form-group">
						                <label class="control-label">E-Mail</label>
						                <span class="text-red"  data-ng-show="userForm.email.$error.email">  [E-Mail Invalido]</span>
						                <input type="email" class="form-control" placeholder="E-mail" data-ng-model="selectedUnit.email" name="email">
						            </div>
						        </div>
						    </div>
						
							<div class="row">
						        <div class="col-md-8">
						            <div class="form-group">
						                <label class="control-label">Endereco</label>
						                <input id="idUnitName" class="form-control" placeholder="Endereço e Número" data-ng-model="selectedUnit.address">
						            </div>
						        </div>
						
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Cidade</label>
						                <input class="form-control" placeholder="Cidade" data-ng-model="selectedUnit.city">
						            </div>
						        </div>
						    </div>
						
							<div class="row">
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Estado</label>
						                <input id="idUnitName" class="form-control" placeholder="Estado" data-ng-model="selectedUnit.state">
						            </div>
						        </div>
						
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">CEP</label>
						                <input class="form-control" placeholder="CEP" data-ng-model="selectedUnit.zip">
						            </div>
						        </div>
						
						        <div class="col-md-4">						            
					                <label class="control-label">Tipo de Unidade</label>
					                <div class="form-group">						                
					                 	<label><input type="radio" data-ng-model="selectedUnit.unitType" value="UNICA"  />&nbsp; Unica</label>
       									<label><input type="radio" data-ng-model="selectedUnit.unitType" value="MATRIZ" />&nbsp; Matriz</label>        
					                 	<label><input type="radio" data-ng-model="selectedUnit.unitType" value="FILIAL" />&nbsp;	Filial</label>						                
					                </div>						            
						        </div>
						    </div>
						
							<div class="row">
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Contato(s)</label>
						                <input id="idUnitName" class="form-control" placeholder="Nome do Contato" data-ng-model="selectedUnit.contact">
						            </div>
						        </div>
						
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Fone</label>
						                <input class="form-control" placeholder="Fone" data-ng-model="selectedUnit.phone">
						            </div>
						        </div>
						
						        <div class="col-md-4">
						            <div class="form-group">
						                <label class="control-label">Celular</label>
						                <input class="form-control" placeholder="Celular" data-ng-model="selectedUnit.mobile">
						            </div>
						        </div>
						    </div>
			       		</form>
			       		
			       		<div class="row">
			       			<div class="col-md-12">
			       				<button type="button" data-ng-click="newUnit();" data-ng-show="btnNewUnit" class="btn btn-success pull-right">    Nova    </button>								
								<span class="pull-right">  </span>
			       				<button type="button" data-ng-click="saveUnit();" data-ng-model="btnSaveUnit" class="btn btn-primary pull-right" data-ng-disabled="(selectedUnit.name) ? false : true">   Salvar   </button>		       				
			       				<span class="pull-right">  </span>
			       				<button type="button" data-ng-click="deleteUnit();" class="btn btn-danger pull-right" data-ng-disabled="(selectedUnit.uid) ? false : true">   Excluir   </button>								
							</div>
						</div>
											
						<div class="row" data-ng-show="selectedUnit.areasDto.length <= 0">
							<div class="col-md-6">
								<label>Incluir Área</label>						
								<div class="entry input-group">					
							        <input type="text" class="form-control" placeholder="Digite o Nome da Área" data-ng-model="areaNameInit">
							        <span class="input-group-btn">
							        <button class="btn btn-info btn-flat" type="button" data-ng-click="saveAreaInit();">OK</button>
							        </span>
								</div>
							</div>
						</div>     
			       	</div><!-- /.tab-pane -->
			       	
			       	<div class="tab-pane" id="tabUnit_2">
			       			    
			       		<input name="latitude"  id='latUnit' data-ng-model="selectedUnit.latitude" type="hidden" placeholder="Latitude"  disabled>
            			<input name="longitude" id='lngUnit' data-ng-model="selectedUnit.longitude"  type="hidden" placeholder="Longitude" disabled>				
												
						<div style="height: 400px; overflow: auto">
							<div align="center"  id="mapUnit" style="height: 400px; width: 800px"></div>
						</div>    
						
			       	</div><!-- /.tab-pane -->	       	
			       			     				     	
			   	</div>	<!-- /.tab-content -->		   						
			</div>						
		</div>		
	</div>
	</div>	
</div> 