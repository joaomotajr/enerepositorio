<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="authenticated" value="false"/>
<sec:authorize access="hasRole('CST')">
    <c:set var="authenticated" value="true"/>
</sec:authorize>

	<div class="ajax-loader"></div> 
    <!-- jQuery 2.1.4 -->
    <script src="/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    
    <!-- Easy Pin -->
    
<!--     <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script> -->
    <script src="/assets/plugins/dist/jquery.easypin.js"></script>
    
    <!-- jQuery UI 1.11.4 -->
    <script src="/assets/plugins/jQueryUI/jquery-ui.js"></script>
    <script src="/assets/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="/assets/plugins/datatables/dataTables.bootstrap.min.js"></script>         

    <!-- GLOBAL SCRIPTS - Modificado -->
    <script src="/assets/plugins/angular/angular.min.js"></script>
	<script src="/assets/plugins/angular/angular-route.js"></script>
	<script src="/assets/plugins/angular/angular-resource.js"></script>
	<script src="/assets/plugins/angular/angular-jquery-maskedinput.js"></script>
	<script src="/assets/plugins/angular/angular-locale_pt-br.js"></script>	
	<script src="/assets/plugins/angular/angular-translate.min.js"></script>
	<script src="/assets/plugins/angular/angular-options-disabled.js"></script>
	
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.5 -->
    <script src="/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="/assets/bootstrap/js/bootstrap-treeview.js"></script>
    <script src="/assets/bootstrap/js/bootstrap-inputmask.js"></script>
    <script src="/assets/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="/assets/plugins/datepicker/moment.min.js"></script>
    <script src="/assets/plugins/datepicker/bootstrap-datetimepicker.min.js"></script>    
    
    <!-- iCheck -->
    <script src="/assets/plugins/iCheck/icheck.min.js"></script>           
    
    <!-- AdminLTE App -->
    <script src="/assets/js/app.min.js"></script>
        
    <!-- Select2 -->
    <script src="/assets/plugins/select2/select2.min.js"></script>    
    
    <!-- Google Maps API v3 -->	  
	<script src="http://maps.google.com/maps/api/js?key=AIzaSyBdD8N8oojiMT-FKYc92jTS4XCCLccyBzg" type="text/javascript"></script>   
	   
    <!-- Google Gauge -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <!-- END GLOBAL SCRIPTS -->
    
    <script type="text/javascript" src="/assets/plugins/pdf/jspdf.min.js"></script>

    <!-- PAGE LEVEL SCRIPTS -->    
    <script src="/assets/js/eneeyes.js"></script>
	<script src="/assets/js/eneeyes-filters.js"></script>
	<script src="/assets/js/eneeyes-services.js"></script>
	<script src="/assets/js/eneeyes-site-controller.js"></script>
	<script src="/assets/js/message-translate.js"></script>	 
	<script src="/assets/js/jsFunctions.js"></script>
 	<script src="/assets/js/controllers/controller-controller.js"></script> 
	<script src="/assets/js/controllers/transmitter-controller.js"></script>
	<script src="/assets/js/controllers/sensor-controller.js"></script>
	<script src="/assets/js/controllers/gas-controller.js"></script>
	<script src="/assets/js/controllers/detector-controller.js"></script>
	<script src="/assets/js/controllers/companies-controller.js"></script>
	<script src="/assets/js/controllers/company-controller.js"></script>
	<script src="/assets/js/controllers/unit-controller.js"></script>
	<script src="/assets/js/controllers/area-controller.js"></script>
	<script src="/assets/js/controllers/companyDetector-controller.js"></script>	
	<script src="/assets/js/controllers/alarm-controller.js"></script>
	<script src="/assets/js/controllers/manufacturer-controller.js"></script>
	<script src="/assets/js/controllers/dashboard-controller.js"></script>
	<script src="/assets/js/controllers/monitor-controller.js"></script>
	<script src="/assets/js/controllers/logHistoric-controller.js"></script>
	<script src="/assets/js/controllers/simulador-controller.js"></script>
	<script src="/assets/js/controllers/conteudo-controller.js"></script>
	
		
	<!-- PAGE LEVEL SERVICES -->
	<script src="/assets/js/services/controller-service.js"></script>
	<script src="/assets/js/services/transmitter-service.js"></script>					
	<script src="/assets/js/services/manufacturer-service.js"></script>
	<script src="/assets/js/services/sensor-service.js"></script>
	<script src="/assets/js/services/gas-service.js"></script>
	<script src="/assets/js/services/detector-service.js"></script>
	<script src="/assets/js/services/company-service.js"></script>
	<script src="/assets/js/services/unit-service.js"></script>
	<script src="/assets/js/services/area-service.js"></script>
	<script src="/assets/js/services/companyDevice-service.js"></script>
	<script src="/assets/js/services/companyDetector-service.js"></script>
	<script src="/assets/js/services/alarm-service.js"></script>
	<script src="/assets/js/services/companyDetectorAlarm-service.js"></script>
	<script src="/assets/js/services/position-service.js"></script>
	<script src="/assets/js/services/historic-service.js"></script>
	<script src="/assets/js/services/view-service.js"></script>
	
	<!-- PAGE LEVEL FACTORIES -->
	
		
    <!-- END PAGE LEVEL SCRIPTS -->
    
    <div class="modal fade session-expired" tabindex="-1" role="dialog" aria-labelledby="sessionExpiredModal" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="text-warning">Sess&atilde;o expirou!</h4>
				</div>
				<div class="modal-body">
					<h4>A sua sess&atilde;o expirou e ser&aacute; redirecionado para a p&aacute;gina principal do site. Nesta p&aacute;gina voc&ecirc; dever&aacute; realizar um novo acesso.</h4>
				</div>
			</div>
		</div>
	</div>