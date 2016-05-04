<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="context" required="false" type="java.lang.String" %>
<c:set var="context" value="${(empty context) ? 'default' : context}"/>

<html lang="en" ng-app="formobili">
	
	<!-- HEADER - INI-->
	<header>
		<c:if test="${context == 'default'}">
			<jsp:include page="login.jsp"/>
		</c:if>		
		<c:if test="${context == 'authenticated'}">
			<jsp:include page="header.jsp"/>
		</c:if>
	</header>
	<!-- HEADER - FIM -->
	
	<!-- BODY - INI -->
    <body ng-controller="SiteController" ng-cloak class="hold-transition skin-blue sidebar-mini">	
       
        <input type="hidden" id="_csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        
        <div class="wrapper" >
	    	<span id="rootMenu">
		        <c:if test="${context == 'authenticated'}">
		            <jsp:include page="menu.jsp"/>
			       	<jsp:include page="conteudo.jsp"/>
			       	
		        </c:if>
	        </span>
	        <jsp:doBody/>
		</div>
		
		<jsp:include page="footer.jsp" />
        <c:if test="${context == 'authenticated'}">
			<jsp:include page="footer-user.jsp" />
		</c:if>

    </body>
	<!-- BODY - FIM -->
	
</html>