<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:csrfMetaTags />
        <!-- MENU SECTION -->
       
       <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

          <!-- Sidebar user panel (optional) -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="/assets/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>Alexander Pierce</p>
              <!-- Status -->
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>

          <!-- search form (Optional) -->
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form>
          <!-- /.search form -->

          <!-- Sidebar Menu -->
          <ul class="sidebar-menu">
          
            <li class="header">SELECIONE</li>
            
            <li>
              <a href="pages/widgets.html">
                <i class="fa fa-th"></i> <span>Widgets</span> <small class="label pull-right bg-green">new</small>
              </a>
            </li>
                                    
            <li><a href="#" ng-click="LoadAjaxContent('navegacao.html')"><i class="fa fa-circle-o"></i> Navegação</a></li>
          	<li class="treeview">
              <a href="#">
                <i class="fa fa-edit"></i> <span>Forms</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                    <li class="active"><a href="#" ng-click="LoadAjaxContent('dashboard.html')"><i class="fa fa-circle-o"></i> Dashboard</a></li>
		            <li><a href="#" ng-click="LoadAjaxContent('navegacao.html')"><i class="fa fa-circle-o"></i> Navegação</a></li>
	        
              </ul>
            </li>
            
	        
		                            
            
            <li class="treeview">
            	<a href="#"><i class="fa fa-link"></i> <span>Multilevel</span> <i class="fa fa-angle-left pull-right"></i></a>
              
				<ul class="treeview-menu">
					<li><a href="#">Link in level 2</a></li>
					<li><a href="#">Link in level 2</a></li>
				</ul>
            </li>
            
          </ul><!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
      </aside>
       
       
        <!--END MENU SECTION -->