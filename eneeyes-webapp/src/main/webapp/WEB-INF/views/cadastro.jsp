<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<c:if test="${layoutContext ==  null || layoutContext == '' }">
    <c:set var="layoutContext" value="authenticated"/>
</c:if>
<layout:layout context="${layoutContext}">
	
    <section id="cadastro" class="margin-top-90px ng-cloak" ng-cloak ng-controller="PerfilController">
        <div ng-include="formUrl"></div>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <form class="form-horizontal">
                        <div class="alert alert-dismissible alert-danger" ng-show="hasErrors()">
                            <button type="button" class="close" data-dismiss="alert">x</button>
                            <strong>{{'form.error.perfil' | translate}}</strong>
                        </div>
                        <div class="alert alert-dismissible alert-success" ng-show="forms.perfil.successMessages">
                            <button type="button" class="close" data-dismiss="alert">x</button>
                            <strong>{{'forms.perfil.successMessage' | translate}}</strong>
                        </div>
                        <fildset>
                            <legend>
	                            <span>
	                            	Dados Pessoais
	                            </span>
	                            <span ng-show="result.value.loginModificado == true" style="padding-left: 70%">
	                          		<button class="btn btn-xs btn-primary" data-toggle="modal" data-target=".bs-change-password-modal">Alterar senha</button>
                                </span>
                            </legend>
                            <div class="form-group {{hasError('form.error.perfil.nome')}}">
                                <label for="nome" class="col-sm-2 control-label">Nome completo:</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="nome" placeholder="Nome"
                                           ng-model="forms.perfil.nome">
                                    <span class="text-danger" ng-show="hasError('form.error.perfil.nome')">{{'form.error.perfil.nome' | translate}}</span>
                                </div>
                            </div>
                            <div class="form-group {{hasError('form.error.perfil.email') || hasError('form.error.perfil.existe')}}">
                                <label for="email" class="col-sm-2 control-label">E-mail:</label>

                                <div class="col-sm-10">
                                    <input ng-show="result.value.loginModificado == false" type="text"
                                           class="form-control" id="email" placeholder="E-mail"
                                           ng-model="forms.perfil.email" disabled>
                                    <span ng-show="result.value.loginModificado == true">{{forms.perfil.email}}</span>
                                    <span class="text-danger" ng-show="hasError('form.error.perfil.email')">{{'form.error.perfil.email' | translate}}</span>
                                    <span class="text-danger" ng-show="hasError('form.error.perfil.existe')">{{'form.error.perfil.existe' | translate}}</span>
                                </div>
                            </div>
                            <div class="form-group {{hasError('form.error.perfil.cidade')}}">
                                <label for="cidade" class="col-sm-2 control-label">Cidade:</label>

                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="cidade" placeholder="Cidade" ng-model="forms.perfil.cidade">
                                    <span class="text-danger" ng-show="hasError('form.error.perfil.cidade')">{{'form.error.perfil.cidade' | translate}}</span>
                                </div>
                               
                                <label for="estado" class="col-sm-1 control-label">Estado:</label>

                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="estado" placeholder="Estado" ng-model="forms.perfil.estado">
                                    <span class="text-danger" ng-show="hasError('form.error.perfil.estado')">{{'form.error.perfil.estado' | translate}}</span>
                                </div>
                                
                                <label for="cidade" class="col-sm-1 control-label">Pa&iacute;s:</label>

                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="pais" placeholder="Pa&iacute;s" ng-model="forms.perfil.pais">
                                    <span class="text-danger" ng-show="hasError('form.error.perfil.pais')">{{'form.error.perfil.pais' | translate}}</span>
                                </div>
                            </div>
                            <div class="form-group">
                            <label for="cidade" class="col-sm-2 control-label">Tipo V&iacute;nculo:</label>
                                <div class="checkbox">
                                    <div class="col-sm-2">
                                        <label>
                                        	<input type="radio" name="tipoVinculo" ng-model="forms.perfil.corporate" ng-value="false" checked> Instituição de Pesquisas
                                        </label>
                                    </div>
                                    <div class="col-sm-2">
                                        <label>
											<input type="radio" name="tipoVinculo" ng-model="forms.perfil.corporate" ng-value="true"> Corporate
											
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group {{hasError('form.error.perfil.vinculo')}}">
                                <label for="vinculo" class="col-sm-2 control-label">V&iacute;nculo:</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="vinculo"
                                           placeholder="Institui&ccedil;&atilde;o de Pesquisa ou Empresa"
                                           ng-model="forms.perfil.vinculo">
                                    <span class="text-danger" ng-show="hasError('form.error.perfil.vinculo')">{{'form.error.perfil.vinculo' | translate}}</span>
                                </div>
                            </div>                          
                            <div class="form-group {{hasError('form.error.perfil.ocupacao')}}">
                                <label for="ocupacao" class="col-sm-2 control-label">Ocupa&ccedil;&atilde;o:</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="ocupacao"
                                           placeholder="Fun&ccedil;&atilde;o ou Cargo" ng-model="forms.perfil.ocupacao">
                                    <span class="text-danger" ng-show="hasError('form.error.perfil.ocupacao')">{{'form.error.perfil.ocupacao' | translate}}</span>
                                </div>
                            </div>
                        </fildset>

                        <fildset>
                            <legend>Experi&ecirc;ncia Profissional</legend>
                            <div class="form-group">
                                <div class="checkbox">
                                    <div class="col-sm-2 col-sm-offset-1">
                                        <label><input type="checkbox" id="chkAcademica">Acad&ecirc;mica</label>
                                    </div>
                                </div>
                            </div>
                            <div id="academica" hidden="true">
                                <div class="form-group">
                                    <label for="titulo" class="col-sm-2 control-label">T&iacute;tulo:</label>

                                    <div class="col-sm-2">
                                        <select class="form-control" id="titulo" ng-model="forms.perfil.titulo">
                                            <option value="" selected="selected" disabled>:: Selecione ::</option>
                                            <option>Graduado</option>
                                            <option>Mestre</option>
                                            <option>Doutor</option>
                                            <option>P&oacute;s-Doutor</option>
                                            <option>Professor</option>
                                        </select>
                                    </div>
                                <!--  
                                </div>
                                <div class="form-group">
                                -->
                                    <label for="filiacao" class="col-sm-2 control-label">Filia&ccedil;&atilde;o:</label>

                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="filiacao" placeholder="Filia&ccedil;&atilde;o" ng-model="forms.perfil.filiacao">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="tempoAcademia" class="col-sm-2 control-label">Tempo academia:</label>

                                    <div class="col-sm-2">
                                        <select class="form-control" id="tempoAcademia"
                                                ng-model="forms.perfil.tempoAcademia">
                                            <option value="" selected="selected" disabled>:: Selecione ::</option>
                                            <option>menos de 1 ano</option>
                                            <option>de 1 a 3 anos</option>
                                            <option>de 3 a 10 anos</option>
                                            <option>mais de 10 anos</option>
                                        </select>
                                    </div>
                                <!--  
                                </div>
                                <div class="form-group">
                                -->
                                    <label for="numeroArtigos" class="col-sm-2 control-label">Artigos publicados:</label>

                                    <div class="col-sm-2">
                                        <select class="form-control" id="numeroArtigos"
                                                ng-model="forms.perfil.numeroArtigos">
                                            <option value="" selected="selected" disabled>:: Selecione ::</option>
                                            <option>menos de 5</option>
                                            <option>de 5 a 20</option>
                                            <option>de 20 a 100</option>
                                            <option>mais de 100</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="checkbox">
                                    <div class="col-sm-2 col-sm-offset-1">
                                        <label><input type="checkbox" id="chkTecnlogica">Tecnol&oacute;gica</label>
                                    </div>
                                </div>
                            </div>
                            <div id="tecnologica" hidden="true">
                                <div class="form-group">
                                    <label for="papel" class="col-sm-2 control-label">Papel:</label>

                                    <div class="col-sm-2">
                                        <select class="form-control" id="papel" ng-model="forms.perfil.papel">
                                            <option value="" selected="selected" disabled>:: Selecione ::</option>
                                            <option>Inventor</option>
                                            <option>Analista</option>
                                            <option>Especialista</option>
                                            <option>Agente</option>
                                            <option>Gestor</option>
                                            <option>Financiador</option>
                                        </select>
                                    </div>
                                <!--  
                                </div>
                                <div class="form-group">
                                -->
                                    <label for="tempoInovacao" class="col-sm-2 control-label">Tempo inova&ccedil;&atilde;o:</label>

                                    <div class="col-sm-2">
                                        <select class="form-control" id="tempoInovacao"
                                                ng-model="forms.perfil.tempoInovacao">
                                            <option value="" selected="selected" disabled>:: Selecione ::</option>
                                            <option>menos de 1 ano</option>
                                            <option>de 1 a 3 anos</option>
                                            <option>de 3 a 10 anos</option>
                                            <option>mais de 10 anos</option>
                                        </select>
                                    </div>
                                <!-- 
                                </div>
                                <div class="form-group">
                                -->
                                    <label for="numeroPatentes" class="col-sm-2 control-label">Patentes aplicadas:</label>

                                    <div class="col-sm-2">
                                        <select class="form-control" id="numeroPatentes"
                                                ng-model="forms.perfil.numeroPatentes">
                                            <option value="" selected="selected" disabled>:: Selecione ::</option>
                                            <option>nenhuma</option>
                                            <option>apenas 1</option>
                                            <option>de 2 a 5</option>
                                            <option>de 6 a 20</option>
                                            <option>mais de 20</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="checkbox">
                                    <div class="col-sm-2 col-sm-offset-1">
                                        <label><input type="checkbox" id="chkMercado">Mercado</label>
                                    </div>
                                </div>
                            </div>
                            <div id="mercado" hidden="true">
                                <div class="form-group">
                                    <label for="status" class="col-sm-2 control-label">Status:</label>

                                    <div class="col-sm-2">
                                        <select class="form-control" id="status" ng-model="forms.perfil.status">
                                            <option value="" selected="selected" disabled>:: Selecione ::</option>
                                            <option>Vendedor</option>
                                            <option>Analista</option>
                                            <option>Especialista</option>
                                            <option>Gestor</option>
                                            <option>Conselheiro</option>
                                            <option>Acionista</option>
                                        </select>
                                    </div>
                                <!-- 
                                </div>
                                <div class="form-group">
                                -->
                                    <label for="tempoAtuacao" class="col-sm-2 control-label">Tempo de atua&ccedil;&atilde;o:</label>

                                    <div class="col-sm-2">
                                        <select class="form-control" id="tempoAtuacao"
                                                ng-model="forms.perfil.tempoAtuacao">
                                            <option value="" selected="selected" disabled>:: Selecione ::</option>
                                            <option>menos de 1 ano</option>
                                            <option>de 1 a 3 anos</option>
                                            <option>de 3 a 10 anos</option>
                                            <option>mais de 10 anos</option>
                                        </select>
                                    </div>
                                <!--  
                                </div>
                                <div class="form-group">
                                -->
                                    <label for="numeroProdutos" class="col-sm-2 control-label">Produtos lan&ccedil;ados:</label>

                                    <div class="col-sm-2">
                                        <select class="form-control" id="numeroProdutos"
                                                ng-model="forms.perfil.numeroProdutos">
                                            <option value="" selected="selected" disabled>:: Selecione ::</option>
                                            <option>menos de 5</option>
                                            <option>de 5 a 20</option>
                                            <option>de 20 a 100</option>
                                            <option>mais de 100</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </fildset>

                        <fildset>
                            <legend>Interesses</legend>
                            <div class="form-group">
                                <div class="checkbox">
                                    <div class="col-sm-2 col-sm-offset-1">
                                        <label><input type="checkbox" id="chkCiencia">Ci&ecirc;ncia</label>
                                    </div>
                                </div>
                            </div>

                            <div id="ciencia" hidden="true">
                                <div class="form-group">
                                    <label for="ramosCientificos" class="col-sm-2 control-label">Ramo Cient&iacute;fico:</label>

                                    <div class="col-sm-4">
                                    	<input type="text" class="form-control" placeholder="Ramo Cient&iacute;fico" ng-model="forms.perfil.ramosCientificos" ng-click="ramosCientificosFocus = true" id="ramosCientificos">
                                    	<div id="ramosCientificos-container" class="hide-autocomplete" style="z-index:2000; position:absolute;top:30px;left:-85px;width:610px">
		                                    <div class="autocomplete col-sm-8 col-sm-offset-2 margin-top-5px box-padding">
		                                        <div class="list-group" ng-repeat="ramoCientifico in ramoCientificoAutocomplete" ng-click="selecionarRamoCientifico(ramoCientifico)">
		                                            <div class="margin-left-5px">
		                                                <div>
		                                                    <a href="#">{{ramoCientifico.descricao}}</a>
		                                                </div>
		                                                <hr>
		                                            </div>
		                                        </div>
		                                    </div>
                                		</div>
                                    </div>
                                <!-- 
                                </div>

                                <div class="form-group">
                                 -->
                                    <label for="linhaPesquisa1" class="col-sm-2 control-label">Linha de Pesquisa:</label>

                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" placeholder="Linha de Pesquisa" ng-model="forms.perfil.linhaPesquisa1" ng-click="linhaPesquisa1 = true" id="linhaPesquisa1">
                                    	<div id="linhaPesquisa1-container" class="hide-autocomplete" style="z-index:2000; position:absolute;top:30px;left:-85px;width:610px">
		                                    <div class="autocomplete col-sm-8 col-sm-offset-2 margin-top-5px box-padding">
		                                        <div class="list-group" ng-repeat="linhaPesquisa1 in linhaPesquisa1Autocomplete" ng-click="selecionarlinhaPesquisa1(linhaPesquisa1)">
		                                            <div class="margin-left-5px">
		                                                <div>
		                                                    <a href="#">{{linhaPesquisa1.descricao}}</a>
		                                                </div>
		                                                <hr>
		                                            </div>
		                                        </div>
		                                    </div>
                                		</div>
                                    </div>
                                </div>
                            </div>

                            <!-- ####### TECNOLOGIA ####### -->
                            <div class="form-group">
                                <div class="checkbox">
                                    <div class="col-sm-2 col-sm-offset-1">
                                        <label><input type="checkbox" id="chkTecnologia">Tecnologia</label>
                                    </div>
                                </div>
                            </div>
                            <div id="tecnologia" hidden="true">
                                <div class="form-group">
                                    <label for="camposTecnologicos" class="col-sm-2 control-label">Campo tecnol&oacute;gico:</label>
									<div class="col-sm-4">
	                                    <input type="text" class="form-control" placeholder="Campo tecnol&oacute;gico" ng-model="forms.perfil.camposTecnologicos" ng-click="camposTecnologicos = true" id="camposTecnologicos">
	                                   	<div id="camposTecnologicos-container" class="hide-autocomplete" style="z-index:2000; position:absolute;top:30px;left:-85px;width:610px">
		                                    <div class="autocomplete col-sm-8 col-sm-offset-2 margin-top-5px box-padding">
		                                        <div class="list-group" ng-repeat="camposTecnologicos in camposTecnologicosAutocomplete" ng-click="selecionarcamposTecnologicos(camposTecnologicos)">
		                                            <div class="margin-left-5px">
		                                                <div>
		                                                    <a href="#">{{camposTecnologicos.descricao}}</a>
		                                                </div>
		                                                <hr>
		                                            </div>
		                                        </div>
		                                    </div>
	                               		</div>
                               		</div>
                               <!-- 
                                </div>

                                <div class="form-group">
                                --> 
                                    <label for="aplicacoesInteresse1" class="col-sm-2 control-label">Aplica&ccedil;&atilde;o de interesse:</label>

									<div class="col-sm-4">
	                                    <input type="text" class="form-control" placeholder="Aplica&ccedil;&atilde;o de interesse" ng-model="forms.perfil.aplicacoesInteresse1" ng-click="aplicacoesInteresse1 = true" id="aplicacoesInteresse1">
	                                   	<div id="aplicacoesInteresse1-container" class="hide-autocomplete" style="z-index:2000; position:absolute;top:30px;left:-85px;width:610px">
		                                    <div class="autocomplete col-sm-8 col-sm-offset-2 margin-top-5px box-padding">
		                                        <div class="list-group" ng-repeat="aplicacoesInteresse1 in aplicacoesInteresse1Autocomplete" ng-click="selecionaraplicacoesInteresse1(aplicacoesInteresse1)">
		                                            <div class="margin-left-5px">
		                                                <div>
		                                                    <a href="#">{{aplicacoesInteresse1.descricao}}</a>
		                                                </div>
		                                                <hr>
		                                            </div>
		                                        </div>
		                                    </div>
	                               		</div>
                               		</div>
                                </div>
                            </div>

                            <!--###### MERCADO ######  -->
                            <div class="form-group">
                                <div class="checkbox">
                                    <div class="col-sm-2 col-sm-offset-1">
                                        <label><input type="checkbox" id="chkIntMercado">Mercado</label>
                                    </div>
                                </div>
                            </div>
                            <div id="intMercado" hidden="true">
                                <div class="form-group">
                                    <label for="segmentoMercado" class="col-sm-2 control-label">Segmento de mercado:</label>
									<div class="col-sm-4">
	                                    <input type="text" class="form-control" placeholder="Segmento de mercado" ng-model="forms.perfil.segmentoMercado" ng-click="segmentoMercado = true" id="segmentoMercado">
	                                   	<div id="segmentoMercado-container" class="hide-autocomplete" style="z-index:2000; position:absolute;top:30px;left:-85px;width:610px">
		                                    <div class="autocomplete col-sm-8 col-sm-offset-2 margin-top-5px box-padding">
		                                        <div class="list-group" ng-repeat="segmentoMercado in segmentoMercadoAutocomplete" ng-click="selecionarsegmentoMercado(segmentoMercado)">
		                                            <div class="margin-left-5px">
		                                                <div>
		                                                    <a href="#">{{segmentoMercado.descricao}}</a>
		                                                </div>
		                                                <hr>
		                                            </div>
		                                        </div>
		                                    </div>
	                               		</div>
                               		</div>
                            <!--
                            	</div>

                                <div class="form-group">
                            -->
                                    <label for="produtosSimilares1" class="col-sm-2 control-label">Produto similar:</label>
									<div class="col-sm-4">
	                                    <input type="text" class="form-control" placeholder="Produto similar" ng-model="forms.perfil.produtosSimilares1" ng-click="produtosSimilares1 = true" id="produtosSimilares1">
	                                   	<div id="produtosSimilares1-container" class="hide-autocomplete" style="z-index:2000; position:absolute;top:30px;left:-85px;width:610px">
		                                    <div class="autocomplete col-sm-8 col-sm-offset-2 margin-top-5px box-padding">
		                                        <div class="list-group" ng-repeat="produtosSimilares1 in produtosSimilares1Autocomplete" ng-click="selecionarprodutosSimilares1(produtosSimilares1)">
		                                            <div class="margin-left-5px">
		                                                <div>
		                                                    <a href="#">{{produtosSimilares1}}</a>
		                                                </div>
		                                                <hr>
		                                            </div>
		                                        </div>
		                                    </div>
	                               		</div>
                               		</div>
                                    </div>
                                </div>
                            

                        </fildset>
                        <fildset>
                            <legend>
	                            <span>
	                            	Dados Financeiros
	                            </span>
                            </legend>

                            <div class="form-group">
                                <label for="emailPaypal" class="col-sm-2 control-label">E-mail PayPal:</label>

                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="emailPaypal"
                                           placeholder="E-mail cadastrado no PayPal para recebimento das vendas dos documentos"
                                           ng-model="forms.perfil.emailPaypal">
<!--                                     <span class="text-danger" ng-show="hasError('form.error.perfil.emailPaypal')">{{'form.error.perfil.emailPaypal' | translate}}</span> -->
                                </div>
                            </div>
                        </fildset>
                        
                        <fieldset ng-show="!hasCheckboxAceite()">
                        	<legend>
	                            <span>
	                            </span>
                            </legend>
							<div class="form-group">
	                        	<div class="col-sm-4 col-sm-offset-2">
	                        		<input type="checkbox" ng-model="checked"> Li e concordo com os
	                        		<a href="#" data-toggle="modal" data-target=".bs-termo-aceite-modal">termos de uso</a>.
	                        	</div> 
							</div>
                        </fieldset>
                        
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <a class="btn btn-default btn-block" onclick="window.location.href='/security/result-search.html'">Cancelar</a>
                            </div>
                            <div class="col-sm-4 col">
                                <a class="btn btn-primary btn-block" ng-click="salvar()" ng-disabled="!hasCheckboxAceiteSalvar()">Salvar</a>
                            </div>
                        </div>
                        <div class="alert alert-dismissible alert-danger" ng-show="hasErrors()">
                            <button type="button" class="close" data-dismiss="alert">x</button>
                            <strong>{{'form.error.perfil.campos.obrigatorios' | translate}}</strong>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</layout:layout>

<div class="modal fade bs-clean-modal" tabindex="-1" role="dialog" aria-labelledby="cleanModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Aten&ccedil;&atilde;o</h4>
			</div>
			<div class="modal-body">
				<p>Deseja realmente apagar os dados desta se&ccedil;&atilde;o?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" id="btnFecharSession">Fechar</button>
				<button type="button" class="btn btn-primary" id="btnClearSession">Sim</button>
				<input type="hidden" id="hdnCheckboxSession" value="">
				<input type="hidden" id="hdnDivSession" value="">
			</div>
		</div>
	</div>
</div>


<script>
		$("#btnFecharSession").click(function () {
			var checkbox = $("#hdnCheckboxSession").val();
			var div = $("#hdnDivSession").val();
			$('#' + checkbox +'').prop('checked', true);
			$('#' + div +'').prop("hidden", false);
			$('.bs-clean-modal').modal('toggle');
		});
		$("#btnClearSession").click(function () {
			var checkbox = $("#hdnCheckboxSession").val();
			switch (checkbox) {
				case 'chkAcademica':
					angular.element("#cadastro").scope().forms.perfil.titulo = null;
					angular.element("#cadastro").scope().forms.perfil.filiacao = null;
					angular.element("#cadastro").scope().forms.perfil.tempoAcademia = null;
					angular.element("#cadastro").scope().forms.perfil.numeroArtigos = null;
					$("#titulo").val(null);
					$("#filiacao").val(null);
					$("#tempoAcademia").val(null);
					$("#numeroArtigos").val(null);
					break;
				case 'chkTecnlogica':
					angular.element("#cadastro").scope().forms.perfil.papel = null;
					angular.element("#cadastro").scope().forms.perfil.organizacao = null;
					angular.element("#cadastro").scope().forms.perfil.tempoInovacao = null;
					angular.element("#cadastro").scope().forms.perfil.numeroPatentes = null;
					$("#papel").val(null);
					$("#organizacao").val(null);
					$("#tempoInovacao").val(null);
					$("#numeroPatentes").val(null);
					break;
				case 'chkMercado':
					angular.element("#cadastro").scope().forms.perfil.status = null;
					angular.element("#cadastro").scope().forms.perfil.empresa = null;
					angular.element("#cadastro").scope().forms.perfil.tempoAtuacao = null;
					angular.element("#cadastro").scope().forms.perfil.numeroProdutos = null;
					$("#status").val(null);
					$("#empresa").val(null);
					$("#tempoAtuacao").val(null);
					$("#numeroProdutos").val(null);
					break;
				case 'chkCiencia':
					angular.element("#cadastro").scope().forms.perfil.ramosCientificos = null;
					angular.element("#cadastro").scope().forms.perfil.linhaPesquisa1 = null;
					angular.element("#cadastro").scope().forms.perfil.linhaPesquisa2 = null;
					angular.element("#cadastro").scope().forms.perfil.linhaPesquisa3 = null;
					angular.element("#cadastro").scope().forms.perfil.pesquisaRelacionadas1 = null;
					angular.element("#cadastro").scope().forms.perfil.pesquisaRelacionadas2 = null;
					angular.element("#cadastro").scope().forms.perfil.pesquisaRelacionadas2 = null;
					angular.element("#cadastro").scope().forms.perfil.univInstitutosPesquisas1 = null;
					angular.element("#cadastro").scope().forms.perfil.univInstitutosPesquisas2 = null;
					angular.element("#cadastro").scope().forms.perfil.univInstitutosPesquisas3 = null;
					angular.element("#cadastro").scope().forms.perfil.pesquisadores1 = null;
					angular.element("#cadastro").scope().forms.perfil.pesquisadores2 = null;
					angular.element("#cadastro").scope().forms.perfil.pesquisadores3 = null;
					$("#ramosCientificos").val(null);
	                $("#linhaPesquisa1").val(null);
	                $("#linhaPesquisa2").val(null);
	                $("#linhaPesquisa3").val(null);
	                $("#pesquisaRelacionadas1").val(null);
	                $("#pesquisaRelacionadas2").val(null);
	                $("#pesquisaRelacionadas3").val(null);
	                $("#univInstitutosPesquisas1").val(null);
	                $("#univInstitutosPesquisas2").val(null);
	                $("#univInstitutosPesquisas3").val(null);
	                $("#pesquisadores1").val(null);
	                $("#pesquisadores2").val(null);
	                $("#pesquisadores3").val(null);
					break;
				case 'chkTecnologia':
					angular.element("#cadastro").scope().forms.perfil.camposTecnologicos = null;
					angular.element("#cadastro").scope().forms.perfil.aplicacoesInteresse1 = null;
					angular.element("#cadastro").scope().forms.perfil.aplicacoesInteresse2 = null;
					angular.element("#cadastro").scope().forms.perfil.aplicacoesInteresse3 = null;
					angular.element("#cadastro").scope().forms.perfil.aplicacoesRelacionadas1 = null;
					angular.element("#cadastro").scope().forms.perfil.aplicacoesRelacionadas2 = null;
					angular.element("#cadastro").scope().forms.perfil.aplicacoesRelacionadas3 = null;
					angular.element("#cadastro").scope().forms.perfil.empresas1 = null;
					angular.element("#cadastro").scope().forms.perfil.empresas2 = null;
					angular.element("#cadastro").scope().forms.perfil.empresas3 = null;
					angular.element("#cadastro").scope().forms.perfil.inventores1 = null;
					angular.element("#cadastro").scope().forms.perfil.inventores2 = null;
					angular.element("#cadastro").scope().forms.perfil.inventores3 = null;
					$("#camposTecnologicos").val(null);
	                $("#aplicacoesInteresse1").val(null);
	                $("#aplicacoesInteresse2").val(null);
	                $("#aplicacoesInteresse3").val(null);
	                $("#aplicacoesRelacionadas1").val(null);
	                $("#aplicacoesRelacionadas2").val(null);
	                $("#aplicacoesRelacionadas3").val(null);
	                $("#empresas1").val(null);
	                $("#empresas2").val(null);
	                $("#empresas3").val(null);
	                $("#inventores1").val(null);
	                $("#inventores2").val(null);
	                $("#inventores3").val(null);
					break;
				case 'chkIntMercado':
					angular.element("#cadastro").scope().forms.perfil.segmentoMercado = null;
					angular.element("#cadastro").scope().forms.perfil.produtosSimilares1 = null;
					angular.element("#cadastro").scope().forms.perfil.produtosSimilares2 = null;
					angular.element("#cadastro").scope().forms.perfil.produtosSimilares3 = null;
					angular.element("#cadastro").scope().forms.perfil.produtosSubstitutos1 = null;
					angular.element("#cadastro").scope().forms.perfil.produtosSubstitutos2 = null;
					angular.element("#cadastro").scope().forms.perfil.produtosSubstitutos3 = null;
					angular.element("#cadastro").scope().forms.perfil.empresasMercado1 = null;
					angular.element("#cadastro").scope().forms.perfil.empresasMercado2 = null;
					angular.element("#cadastro").scope().forms.perfil.empresasMercado3 = null;
					angular.element("#cadastro").scope().forms.perfil.vendedores1 = null;
					angular.element("#cadastro").scope().forms.perfil.vendedores2 = null;
					angular.element("#cadastro").scope().forms.perfil.vendedores3 = null;
					$("#segmentoMercado").val(null);
	                $("#produtosSimilares1").val(null);
	                $("#produtosSimilares2").val(null);
	                $("#produtosSimilares3").val(null);
	                $("#produtosSubstitutos1").val(null);
	                $("#produtosSubstitutos2").val(null);
	                $("#produtosSubstitutos3").val(null);
	                $("#empresasMercado1").val(null);
	                $("#empresasMercado2").val(null);
	                $("#empresasMercado3").val(null);
	                $("#vendedores1").val(null);
	                $("#vendedores2").val(null);
	                $("#vendedores3").val(null);
			}
			$('.bs-clean-modal').modal('toggle');	
		});
        $("#chkAcademica").change(function () {
            if($('#chkAcademica').prop("checked")){
                $("#academica").prop("hidden", false);
            }else{
            	$("#academica").prop("hidden", true);
            	$("#hdnCheckboxSession").val("chkAcademica");
            	$("#hdnDivSession").val("academica");
            	$('.bs-clean-modal').modal();
            }
        });
        $("#chkTecnlogica").change(function () {
            if($('#chkTecnlogica').prop("checked")){
                $("#tecnologica").prop("hidden", false);
            }else{
            	$("#tecnologica").prop("hidden", true);
            	$("#hdnCheckboxSession").val("chkTecnlogica");
            	$("#hdnDivSession").val("tecnologica");
            	$('.bs-clean-modal').modal();
            }
        });
        $("#chkMercado").change(function () {
            if($('#chkMercado').prop("checked")){
                $("#mercado").prop("hidden", false);
            }else{
            	$("#mercado").prop("hidden", true);
            	$("#hdnCheckboxSession").val("chkMercado");
            	$("#hdnDivSession").val("mercado");
            	$('.bs-clean-modal').modal();
            }
        });
        $("#chkCiencia").change(function () {
            if($('#chkCiencia').prop("checked")){
                $("#ciencia").prop("hidden", false);
            }else{
            	$("#ciencia").prop("hidden", true);
            	$("#hdnCheckboxSession").val("chkCiencia");
            	$("#hdnDivSession").val("ciencia");
            	$('.bs-clean-modal').modal();
            }
        });
        $("#chkTecnologia").change(function () {
            if($('#chkTecnologia').prop("checked")){
                $("#tecnologia").prop("hidden", false);
            }else{
            	$("#tecnologia").prop("hidden", true);
            	$("#hdnCheckboxSession").val("chkTecnologia");
            	$("#hdnDivSession").val("tecnologia");
            	$('.bs-clean-modal').modal();
                
            }
        });
        $("#chkIntMercado").change(function () {
            if($('#chkIntMercado').prop("checked")){
                $("#intMercado").prop("hidden", false);
            }else{
            	$("#intMercado").prop("hidden", true);
            	$("#hdnCheckboxSession").val("chkIntMercado");
            	$("#hdnDivSession").val("intMercado");
            	$('.bs-clean-modal').modal();
            }
        });
	</script>
<c:if test="${layoutContext == 'authenticated' }">
	<div class="modal fade bs-change-password-modal" id="modalSenha" tabindex="-1" role="dialog" aria-labelledby="changePasswordModal" aria-hidden="true">
	    <div class="modal-dialog modal-sm">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" align="center">O usu&aacute;rio <sec:authentication property="principal.displayName" /> ir&aacute; alterar senha</h4>
	            </div>
	            <div class="modal-body">
	                <div id="fld-inscricao" class="form-group">
	                    <div class="form-group">
	                        <input type="password" class="form-control" placeholder="Informe uma nova senha" ng-model="forms.changePassword.pass1">
	                    </div>
	                    <div class="form-group">
	                        <input type="password" class="form-control" placeholder="Confirme a nova senha" ng-model="forms.changePassword.pass2">
	                    </div>
	                        <span ng-show="forms.changePassword.errorMessage != ''" class="text-danger">{{forms.changePassword.errorMessage|translate}}</span>
	                        <span ng-show="forms.changePassword.successMessage != ''" class="text-success">{{forms.changePassword.successMessage|translate}}</span>
	                    </div>
	                    <div class="form-group">
	                        <button id="btn-change-password-alterar" class="btn btn-primary btn-block" ng-click="alterarSenha()">Salvar</button>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
    <script>
            $(function(){
                angular.element('#cadastro').scope().validar();
                angular.element('#cadastro').scope().forms.perfil.autenticado = true;
                angular.element("#cadastro").scope().forms.perfil.uid = $.parseHTML('<sec:authentication property="principal.perfil.interesse.uid" />')[0].data;
            });
    </script>
    <sec:authentication var="experiencia" property="principal.perfil.experienciaProfissional" />
    <c:if test="${experiencia.titulo != null && experiencia.titulo != ''}">
	    <script>
	    	$('#chkAcademica').prop("checked", true);
	    	$("#academica").prop("hidden", false);
	    	$(function(){
		    	if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.titulo" />') != null)
			    	angular.element("#cadastro").scope().forms.perfil.titulo = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.titulo" />')[0].data;
				if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.filiacao" />') != null)
		    		angular.element("#cadastro").scope().forms.perfil.filiacao = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.filiacao" />')[0].data;
				if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.tempoAcademia" />') != null)
					angular.element("#cadastro").scope().forms.perfil.tempoAcademia = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.tempoAcademia" />')[0].data;
				if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.numeroArtigos" />') != null)
					angular.element("#cadastro").scope().forms.perfil.numeroArtigos = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.numeroArtigos" />')[0].data;
			});
		</script>		
    </c:if>
    <c:if test="${experiencia.papel != null && experiencia.papel != ''}">
    	<script>
    		$('#chkTecnlogica').prop("checked", true);
            $("#tecnologica").prop("hidden", false);
    		$(function() {
        		if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.papel" />') != null)
	    			angular.element("#cadastro").scope().forms.perfil.papel = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.papel" />')[0].data;
				if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.organizacao" />') != null)
	                angular.element("#cadastro").scope().forms.perfil.organizacao = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.organizacao" />')[0].data;
				if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.tempoInovacao" />') != null)
	                angular.element("#cadastro").scope().forms.perfil.tempoInovacao = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.tempoInovacao" />')[0].data;
				if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.numeroPatentes" />') != null)
	                angular.element("#cadastro").scope().forms.perfil.numeroPatentes = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.numeroPatentes" />')[0].data;
    		});
    	</script>
    </c:if>
    <c:if test="${experiencia.status != null && experiencia.status != ''}">
    	<script>
    		$('#chkMercado').prop("checked", true);
            $("#mercado").prop("hidden", false);
    		$(function() {
        		if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.status" />') != null)
    				angular.element("#cadastro").scope().forms.perfil.status = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.status" />')[0].data;
				if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.empresa" />') != null)
	                angular.element("#cadastro").scope().forms.perfil.empresa = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.empresa" />')[0].data;
				if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.tempoAtuacao" />') != null)
					angular.element("#cadastro").scope().forms.perfil.tempoAtuacao = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.tempoAtuacao" />')[0].data;
				if($.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.numeroProdutos" />') != null)
					angular.element("#cadastro").scope().forms.perfil.numeroProdutos = $.parseHTML('<sec:authentication property="principal.perfil.experienciaProfissional.numeroProdutos" />')[0].data;
    		});
    	</script>
    </c:if>
    
    <sec:authentication var="interesse" property="principal.perfil.interesse" />
    <c:if test="${interesse.ciencia != null}">
		<script>
			$(function() {
				if($.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.ramosCientificos" />')[0].data != 'null'){
					$('#chkCiencia').prop("checked", true);
		        	$("#ciencia").prop("hidden", false);
					angular.element("#cadastro").scope().forms.perfil.ramosCientificos = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.ramosCientificos" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.linhaPesquisa1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.linhaPesquisa1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.linhaPesquisa2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.linhaPesquisa2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.linhaPesquisa3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.linhaPesquisa3" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.pesquisaRelacionadas1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.pesquisaRelacionadas1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.pesquisaRelacionadas2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.pesquisaRelacionadas2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.pesquisaRelacionadas3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.pesquisaRelacionadas3" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.univInstitutosPesquisas1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.univInstitutosPesquisas1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.univInstitutosPesquisas2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.univInstitutosPesquisas2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.univInstitutosPesquisas3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.univInstitutosPesquisas3" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.pesquisadores1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.pesquisadores1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.pesquisadores2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.pesquisadores2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.pesquisadores3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.ciencia.pesquisadores3" />')[0].data;
				}
			});
		</script>
	</c:if>
	
	<c:if test="${interesse.tecnologia != null}">
		<script>
			
			$(function() {
				if($.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.camposTecnologicos" />')[0].data  != 'null'){
					$('#chkTecnologia').prop("checked", true);
		        	$("#tecnologia").prop("hidden", false);
					angular.element("#cadastro").scope().forms.perfil.camposTecnologicos = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.camposTecnologicos" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.aplicacoesInteresse1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.aplicacoesInteresse1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.aplicacoesInteresse2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.aplicacoesInteresse2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.aplicacoesInteresse3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.aplicacoesInteresse3" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.aplicacoesRelacionadas1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.aplicacoesRelacionadas1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.aplicacoesRelacionadas2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.aplicacoesRelacionadas2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.aplicacoesRelacionadas3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.aplicacoesRelacionadas3" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.empresas1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.empresas1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.empresas2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.empresas2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.empresas3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.empresas3" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.inventores1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.inventores1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.inventores2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.inventores2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.inventores3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.tecnologia.inventores3" />')[0].data;
				}
			});
		</script>
	</c:if>
	
	<c:if test="${interesse.mercado != null}">
		<script>
			$(function() {
	        	if($.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.segmentoMercado" />')[0].data != 'null'){
	        		$('#chkIntMercado').prop("checked", true);
		        	$("#intMercado").prop("hidden", false);
					angular.element("#cadastro").scope().forms.perfil.segmentoMercado = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.segmentoMercado" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.produtosSimilares1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.produtosSimilares1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.produtosSimilares2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.produtosSimilares2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.produtosSimilares3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.produtosSimilares3" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.produtosSubstitutos1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.produtosSubstitutos1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.produtosSubstitutos2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.produtosSubstitutos2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.produtosSubstitutos3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.produtosSubstitutos3" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.empresasMercado1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.empresasMercado1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.empresasMercado2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.empresasMercado2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.empresasMercado3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.empresasMercado3" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.vendedores1 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.vendedores1" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.vendedores2 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.vendedores2" />')[0].data;
	                angular.element("#cadastro").scope().forms.perfil.vendedores3 = $.parseHTML('<sec:authentication property="principal.perfil.interesse.mercado.vendedores3" />')[0].data;
	        	}
			});
		</script>
	</c:if>
</c:if>
<c:if test="${layoutContext == 'default' }">
    <script>
            $(function(){
                angular.element('#cadastro').scope().forms.perfil.email = '${inscricao_email}';
                angular.element('#cadastro').scope().$apply();
            });
    </script>
</c:if>