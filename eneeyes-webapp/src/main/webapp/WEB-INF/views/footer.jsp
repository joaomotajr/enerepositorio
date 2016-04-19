<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="authenticated" value="false"/>
<sec:authorize access="hasRole('CST')">
    <c:set var="authenticated" value="true"/>
</sec:authorize>
    <!-- GLOBAL SCRIPTS -->
    <script src="/assets/plugins/jquery-2.0.3.min.js"></script>
    <script src="/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="/assets/plugins/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="https://www.google.com/jsapi" type="text/javascript"></script>
	<script src="/assets/js/angular.min.js"></script>
	<script src="/assets/js/angular-route.js"></script>
	<script src="/assets/js/angular-resource.js"></script>
	<script src="/assets/js/angular-jquery-maskedinput.js"></script>
	<script src="/assets/js/angular-locale_pt-br.js"></script>
	
	
	
	<script src="/assets/js/angular-translate.min.js"></script>
	<script src="/assets/js/angular-options-disabled.js"></script>
    <!-- END GLOBAL SCRIPTS -->

    <!-- PAGE LEVEL SCRIPTS -->
    <!-- <script src="/assets/plugins/flot/jquery.flot.js"></script>
    <script src="/assets/plugins/flot/jquery.flot.resize.js"></script>
    <script src="/assets/plugins/flot/jquery.flot.time.js"></script>
    <script src="/assets/plugins/flot/jquery.flot.stack.js"></script> -->
<!--     <script src="/assets/js/for_index.js"></script> -->
    
    <script src="/assets/plugins/jasny/js/bootstrap-fileupload.js"></script>
	<script src="/assets/js/jquery-ui.min.js"></script>
	<script src="/assets/plugins/uniform/jquery.uniform.min.js"></script>
	<script src="/assets/plugins/inputlimiter/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="/assets/plugins/chosen/chosen.jquery.min.js"></script>
	
	<script src="/assets/plugins/daterangepicker/daterangepicker.js"></script>
	<script src="/assets/plugins/daterangepicker/moment.min.js"></script>
	<script src="/assets/plugins/datepicker/js/bootstrap-datepicker.js"></script>
	<script src="/assets/js/formsInit.js"></script>	
	
	<script src="/assets/plugins/tagsinput/jquery.tagsinput.min.js"></script>
	<script src="/assets/plugins/validVal/js/jquery.validVal.min.js"></script>
	<script src="/assets/plugins/switch/static/js/bootstrap-switch.min.js"></script>
	<script src="/assets/plugins/jquery.dualListbox-1.3/jquery.dualListBox-1.3.min.js"></script>
	<script src="/assets/plugins/autosize/jquery.autosize.min.js"></script>
	<script src="/assets/plugins/jasny/js/bootstrap-inputmask.js"></script>
	<script src="/assets/plugins/colorpicker/js/bootstrap-colorpicker.js"></script>
	<script src="/assets/plugins/timepicker/js/bootstrap-timepicker.min.js"></script>
	<script src="/assets/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="/assets/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="/assets/plugins/dataTables/date-eu.js"></script>
    <script src="/assets/plugins/gritter/js/jquery.gritter.js"></script>
    <script src="/assets/js/4m.js"></script>
	<script src="/assets/js/4m-translate.js"></script>
	<script src="/assets/js/4m-filters.js"></script>
	<script src="/assets/js/4m-services.js"></script>
	<script src="/assets/js/4m-site-controller.js"></script>
	<script src="/assets/js/message-translate.js"></script>

	<script src="/assets/js/detalhe-venda-services.js"></script>
	<script src="/assets/js/detalhe-venda-site-controller.js"></script>
	<script src="/assets/js/vendas-resumo-services.js"></script>
	<script src="/assets/js/vendas-resumo-controller.js"></script>
	<script src="/assets/js/integracao-tef-controller.js"></script>
	<script src="/assets/js/dashboard-services.js"></script>
	<script src="/assets/js/dashboard-site-controller.js"></script>
	<script src="/assets/js/pagamentos-services.js"></script>
	<script src="/assets/js/pagamentos-controller.js"></script>
	<script src="/assets/js/conta-corrente-services.js"></script>
	<script src="/assets/js/conta-corrente-controller.js"></script>
	<script src="/assets/js/conciliacao-services.js"></script>
	<script src="/assets/js/conciliacao-controller.js"></script>
	<script src="/assets/js/inconsistencia-services.js"></script>
	<script src="/assets/js/inconsistencia-controller.js"></script>
	<script src="/assets/js/integracao-adquirente-controller.js"></script>
	<script src="/assets/js/integracao-adquirente-services.js"></script>
	<script src="/assets/js/userType-controller.js"></script>
	<script src="/assets/js/user-controller.js"></script>
	<script src="/assets/js/user-services.js"></script>
	<script src="/assets/js/filial-controller.js"></script>
	<script src="/assets/js/filial-services.js"></script>
	<script src="/assets/js/taxa-controller.js"></script>
	<script src="/assets/js/taxa-services.js"></script>
	<script src="/assets/js/grupo-controller.js"></script>
	<script src="/assets/js/grupo-services.js"></script>
	
    <!-- END PAGE LEVEL SCRIPTS -->