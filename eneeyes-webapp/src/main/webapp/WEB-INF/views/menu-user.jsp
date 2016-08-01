<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

			

<div class="col-sm-4">
			
		<div class="btn-group">	
	    <a href="/security/cadastro.html" class="btn btn-primary"><span class="glyphicon glyphicon-user"></span> <sec:authentication property="principal.displayName" /></a>
		<a href="#" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="height:46%"><span class="caret" style="margin-top:100%"></span></a>
        <ul class="dropdown-menu">
        	<li><a href="/security/result-search.html">Home</a></li>
        	<li class="divider"></li>
            <li><a href="/security/meus-documentos.html">Meus documentos</a></li>
            <li><a href="#" data-toggle="modal" data-target=".bs-documento-modal-lg">Gerar ittiDoc</a></li>
            <li><a href="#">Solicitar ittiAct</a></li>
            <li class="divider"></li>
            <li><a href="/security/cadastro.html">Perfil</a></li>
            <li class="divider"></li>
            <li><a href="/logout">Sair</a></li>
        </ul>
        </div>
</div>

