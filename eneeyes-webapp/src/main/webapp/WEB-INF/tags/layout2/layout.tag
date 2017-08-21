<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="context" required="false" type="java.lang.String" %>

<c:set var="context" value="${(empty context) ? 'default' : context}"/>
<c:set var="tipoUsuario" value="${(empty tipoUsuario) ? '' : tipoUsuario}"/>
<c:set var="idUsuario" value="${(empty idUsuario) ? '' : idUsuario}"/>
<c:set var="isMaster" value="${(empty isFrom) ? '' : isFrom}"/>

<html lang="en" data-ng-app="eneeyes">
		
	<header>				
		<jsp:include page="header2.jsp"/>		
	</header>
		
	<!-- BODY - INI -->
    <body data-ng-controller="SiteController" data-ng-cloak class="hold-transition skin-blue sidebar-mini">	
       
        <input type="hidden" id="_csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" id="tipoUsuario" value="${tipoUsuario}">
        <input type="hidden" id="idUsuario" value="${idUsuario}">
        <input type="hidden" id="isFrom" value="${isFrom}">
                
        <div class="wrapper" >
	    	<span id="rootMenu">
			    
			    <jsp:include page="simulador.jsp"/>			     	
		        
	        </span>
	        <jsp:doBody/>
		</div>
		
		<jsp:include page="footer.jsp" />        	

    </body>
	<!-- BODY - FIM -->
	
</html>