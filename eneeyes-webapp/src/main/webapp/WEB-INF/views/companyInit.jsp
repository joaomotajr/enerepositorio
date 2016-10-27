<div ng-controller="companiesController">    
	<div class="col-md-9">
		<!--  <div class="box box-primary" ng-show="selectedCompany.unitsDto.length <= 0"> -->
		<div class="box box-primary">
						
			<div class="box-header with-border"><strong style="font-size:1.4em">Inicialização do Cadastro</strong></div>
			
			<div class="box-body">
				<div class="col-md-6">
					<label>Incluir Unidade</label>						
					<div class="entry input-group">					
				        <input type="text" class="form-control" placeholder="Digite o Nome da Unidade" ng-model="unitNameInit">
				        <span class="input-group-btn">
				        <button class="btn btn-info btn-flat" type="button" ng-click="saveUnitInit();">OK</button>
				        </span>
					</div>
				</div>		
			</div>		
			
		</div>	
	</div>
</div>


