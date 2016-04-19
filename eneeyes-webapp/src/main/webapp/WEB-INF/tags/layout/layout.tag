<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="context" required="false" type="java.lang.String" %>
<c:set var="context" value="${(empty context) ? 'default' : context}"/>
<c:set var="tipoUsuario" value="${(empty tipoUsuario) ? '' : tipoUsuario}"/>
<c:set var="idUsuario" value="${(empty idUsuario) ? '' : idUsuario}"/>
<c:set var="cnpjRaizUsuario" value="${(empty cnpjRaizUsuario) ? '' : cnpjRaizUsuario}"/>
<html lang="en" ng-app="formobili">
	
	<!-- HEADER - INI-->
	<header>
		<c:if test="${context == 'default'}">
			<jsp:include page="login.jsp"/>
		</c:if>		
		<c:if test="${context == 'authenticated'}">
			<jsp:include page="header.jsp"/>
		</c:if>
		<c:if test="${context == 'authenticated-admin'}">
			<jsp:include page="header-admin.jsp"/>
		</c:if>	
	</header>
	<!-- HEADER - FIM -->
	
	<!-- BODY - INI -->
    <c:if test="${context != 'authenticated-admin'}"><body ng-controller="SiteController" ng-cloak class="ng-cloak padTop53"></c:if>		
    <c:if test="${context == 'authenticated-admin'}"><body ng-controller="SiteController" ng-cloak class="ng-cloak padTop82"></c:if>	
       
        <input type="hidden" id="_csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" id="tipoUsuario" value="${tipoUsuario}">
        <input type="hidden" id="idUsuario" value="${idUsuario}">
        <input type="hidden" id="cnpjRaizUsuario" value="${cnpjRaizUsuario}">
        
        <div id="wrap" >
	    	<span id="rootMenu">
		        <c:if test="${context == 'authenticated'}">
		            <jsp:include page="menu.jsp"/>
			       	<jsp:include page="conteudo.jsp"/>
		        </c:if>
		        <c:if test="${context == 'authenticated-admin'}">
		            <jsp:include page="menu-admin.jsp"/>
			       	<jsp:include page="conteudo.jsp"/>		            
		        </c:if>			        
	        </span>
	        <jsp:doBody/>
		</div>
		
		<jsp:include page="footer.jsp"/>
        <c:if test="${context == 'authenticated' || context == 'authenticated-admin'}">
			<jsp:include page="footer-user.jsp"/>
		</c:if>

    </body>
	<!-- BODY - FIM -->
	
</html>