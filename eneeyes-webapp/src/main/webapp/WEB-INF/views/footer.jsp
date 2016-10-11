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
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.easing/1.3/jquery.easing.1.3.min.js"></script>
    <script src="/assets/plugins/dist/jquery.easypin.js"></script>    
    
    <!-- jQuery UI 1.11.4 -->
    <script src="/assets/plugins/jQueryUI/jquery-ui.js"></script>    

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
          
    <!-- jQuery Knob Chart -->
    <script src="/assets/plugins/knob/jquery.knob.js"></script>
    <!-- daterangepicker -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
    <script src="/assets/plugins/daterangepicker/daterangepicker.js"></script>
    <!-- datepicker -->
    <script src="/assets/plugins/datepicker/bootstrap-datepicker.js"></script>    
    <!-- Bootstrap WYSIHTML5 -->
    <script src="/assets/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>    
    <!-- Slimscroll -->
    <script src="/assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="/assets/plugins/fastclick/fastclick.min.js"></script>    
    <!-- iCheck -->
    <script src="/assets/plugins/iCheck/icheck.min.js"></script>

    <!-- AdminLTE App -->
    <script src="/assets/js/app.min.js"></script>
    
    <script src="/assets/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="/assets/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>    
    <script src="/assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>    
    <script src="/assets/plugins/chartjs/Chart.min.js"></script>
   
    
    <!-- Image Notes -->
    <script src="/assets/plugins/imageNotes/hammer.min.js"></script>
    <script src="/assets/plugins/imageNotes/jquery.hammer.js"></script>
    <script src="/assets/plugins/imageNotes/imgViewer.min.js"></script>
    <script src="/assets/plugins/imageNotes/imgNotes.js"></script>
    
    
    <!-- Select2 -->
    <script src="/assets/plugins/select2/select2.min.js"></script>    
    
    <!-- Google Maps API v3 -->	  
	<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdD8N8oojiMT-FKYc92jTS4XCCLccyBzg"  type="text/javascript"></script> -->
	<script src="http://maps.google.com/maps/api/js?key=AIzaSyBdD8N8oojiMT-FKYc92jTS4XCCLccyBzg" type="text/javascript"></script>   
	   
    <!-- Google Gauge -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <!-- END GLOBAL SCRIPTS -->

    <!-- PAGE LEVEL SCRIPTS -->    
    <script src="/assets/js/eneeyes.js"></script>
	<!--<script src="/assets/js/eneeyes-translate.js"></script> -->
	<script src="/assets/js/eneeyes-filters.js"></script>
	<script src="/assets/js/eneeyes-services.js"></script>
	<script src="/assets/js/eneeyes-site-controller.js"></script>
	<script src="/assets/js/message-translate.js"></script>	 
	<script src="/assets/js/jsFunctions.js"></script>
	<script src="/assets/js/controllers/menu-navegacao.js"></script>
	<script src="/assets/js/controllers/navegacao-controller.js"></script>
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
	
		
	<!-- PAGE LEVEL SERVICES -->
	<script src="/assets/js/services/controller-service.js"></script>
	<script src="/assets/js/services/transmitter-service.js"></script>					
	<script src="/assets/js/services/manufacturer-service.js"></script>
	<script src="/assets/js/services/sensor-service.js"></script>
	<script src="/assets/js/services/gas-service.js"></script>
	<script src="/assets/js/services/detector-service.js"></script>
	<script src="/assets/js/services/company-service.js"></script>
	<script src="/assets/js/services/companyDetector-service.js"></script>
	<script src="/assets/js/services/alarm-service.js"></script>
	<script src="/assets/js/services/companyDetectorAlarm-service.js"></script>
	

    <!-- END PAGE LEVEL SCRIPTS -->