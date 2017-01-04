<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="authenticated" value="false"/>
<sec:authorize access="hasRole('CST')">
    <c:set var="authenticated" value="true"/>
</sec:authorize>
	<div class="ajax-loader"></div> 
    <!-- jQuery 2.1.4 -->
    <script src="/eneeyes/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    
    <!-- Easy Pin -->
    
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="/eneeyes/assets/plugins/dist/jquery.easypin.js"></script>
    
    <!-- jQuery UI 1.11.4 -->
    <script src="/eneeyes/assets/plugins/jQueryUI/jquery-ui.js"></script>
    <script src="/eneeyes/assets/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="/eneeyes/assets/plugins/datatables/dataTables.bootstrap.min.js"></script>
         

    <!-- GLOBAL SCRIPTS - Modificado -->
    <script src="/eneeyes/assets/plugins/angular/angular.min.js"></script>
	<script src="/eneeyes/assets/plugins/angular/angular-route.js"></script>
	<script src="/eneeyes/assets/plugins/angular/angular-resource.js"></script>
	<script src="/eneeyes/assets/plugins/angular/angular-jquery-maskedinput.js"></script>
	<script src="/eneeyes/assets/plugins/angular/angular-locale_pt-br.js"></script>	
	<script src="/eneeyes/assets/plugins/angular/angular-translate.min.js"></script>
	<script src="/eneeyes/assets/plugins/angular/angular-options-disabled.js"></script>
	
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.5 -->
    <script src="/eneeyes/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="/eneeyes/assets/bootstrap/js/bootstrap-treeview.js"></script>
    <script src="/eneeyes/assets/bootstrap/js/bootstrap-inputmask.js"></script>
    <script src="/eneeyes/assets/plugins/datepicker/bootstrap-datepicker.js"></script>
    
    <!-- iCheck -->
    <script src="/eneeyes/assets/plugins/iCheck/icheck.min.js"></script>           
    
    <!-- AdminLTE App -->
    <script src="/eneeyes/assets/js/app.min.js"></script>
        
    <!-- Select2 -->
    <script src="/eneeyes/assets/plugins/select2/select2.min.js"></script>    
    
    <!-- Google Maps API v3 -->	  
	<script src="http://maps.google.com/maps/api/js?key=AIzaSyBdD8N8oojiMT-FKYc92jTS4XCCLccyBzg" type="text/javascript"></script>   
	   
    <!-- Google Gauge -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <!-- END GLOBAL SCRIPTS -->

    <!-- PAGE LEVEL SCRIPTS -->    
    <script src="/eneeyes/assets/js/eneeyes.js"></script>
	<script src="/eneeyes/assets/js/eneeyes-filters.js"></script>
	<script src="/eneeyes/assets/js/eneeyes-services.js"></script>
	<script src="/eneeyes/assets/js/eneeyes-site-controller.js"></script>
	<script src="/eneeyes/assets/js/message-translate.js"></script>	 
	<script src="/eneeyes/assets/js/jsFunctions.js"></script>
	<script src="/eneeyes/assets/js/controllers/navegacao-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/controller-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/transmitter-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/sensor-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/gas-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/detector-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/companies-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/company-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/unit-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/area-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/companyDetector-controller.js"></script>	
	<script src="/eneeyes/assets/js/controllers/alarm-controller.js"></script>
	<script src="/eneeyes/assets/js/controllers/manufacturer-controller.js"></script>
	
		
	<!-- PAGE LEVEL SERVICES -->
	<script src="/eneeyes/assets/js/services/controller-service.js"></script>
	<script src="/eneeyes/assets/js/services/transmitter-service.js"></script>					
	<script src="/eneeyes/assets/js/services/manufacturer-service.js"></script>
	<script src="/eneeyes/assets/js/services/sensor-service.js"></script>
	<script src="/eneeyes/assets/js/services/gas-service.js"></script>
	<script src="/eneeyes/assets/js/services/detector-service.js"></script>
	<script src="/eneeyes/assets/js/services/company-service.js"></script>
	<script src="/eneeyes/assets/js/services/unit-service.js"></script>
	<script src="/eneeyes/assets/js/services/area-service.js"></script>
	<script src="/eneeyes/assets/js/services/companyDevice-service.js"></script>
	<script src="/eneeyes/assets/js/services/companyDetector-service.js"></script>
	<script src="/eneeyes/assets/js/services/alarm-service.js"></script>
	<script src="/eneeyes/assets/js/services/companyDetectorAlarm-service.js"></script>
	<script src="/eneeyes/assets/js/services/position-service.js"></script>
	<script src="/eneeyes/assets/js/services/historic-service.js"></script>
	<script src="/eneeyes/assets/js/services/positionAlarm-service.js"></script>
	
	<!-- PAGE LEVEL FACTORIES -->
	
		
    <!-- END PAGE LEVEL SCRIPTS -->