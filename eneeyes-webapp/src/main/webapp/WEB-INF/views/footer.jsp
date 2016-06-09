<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="authenticated" value="false"/>
<sec:authorize access="hasRole('CST')">
    <c:set var="authenticated" value="true"/>
</sec:authorize>

    <!-- jQuery 2.1.4 -->
    <script src="/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>


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
    
    <!-- Sparkline -->
    <script src="/assets/plugins/sparkline/jquery.sparkline.min.js"></script>
    <!-- jvectormap -->
    <script src="/assets/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
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
    <!-- AdminLTE App -->
    <script src="/assets/js/app.min.js"></script>
    <!-- iCheck -->
    <script src="/assets/plugins/iCheck/icheck.min.js"></script>
    <!-- END GLOBAL SCRIPTS -->

    <!-- PAGE LEVEL SCRIPTS -->    
    <script src="/assets/js/4m.js"></script>
	<script src="/assets/js/4m-translate.js"></script>
	<script src="/assets/js/4m-filters.js"></script>
	<script src="/assets/js/4m-services.js"></script>
	<script src="/assets/js/4m-site-controller.js"></script>
	<script src="/assets/js/message-translate.js"></script>	 
	<script src="/assets/js/menu-navegacao.js"></script>
	<script src="/assets/js/navegacao-controller.js"></script>		

    <!-- END PAGE LEVEL SCRIPTS -->