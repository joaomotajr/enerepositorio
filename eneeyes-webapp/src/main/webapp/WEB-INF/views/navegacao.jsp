	
	<script>
		var tree = [
           {
               text: "<img src='img src='/assets/img/ic_domain_black_18dp.png' alt='img04'> Empresa Número Um",
               nodes: [
                 {
                     text: "<img src='img src='/assets/img/ic_language_black.png' alt='img04'> Área Um",
                     nodes: [
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 1", "showIcon": true, id : 1
                       },
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 2"
                       }
                     ]
                     
                 },
                 {
                     text: "<img src='img src='/assets/img/ic_language_black.png' alt='img04'> Área Dois",
                     nodes: [
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 1"
                       },
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 2"
                       }
                     ]

                 }

               ]
           },
           {
               text: "<img src='img src='/assets/img/ic_domain_black_18dp.png' alt='img04'> Empresa Número Dois",
               nodes: [
                 {
                     text: "<img src='img src='/assets/img/ic_language_black.png' alt='img04'> Área Um",
                     nodes: [
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor A"
                       },
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor B"
                       }
                     ]
                 }
               ]
           }
          ];

          function getTree() {
              // Some logic to retrieve, or generate tree structure
              return tree;
          }


          var $searchableTree = $('#treeview-searchable').treeview({
              data: getTree(),
          });

          var search = function (e) {
              var pattern = $('#input-search').val();
              var options = {
                  ignoreCase: true,
                  exactMatch: false,
                  revealResults: true
              };

              var results = $searchableTree.treeview('search', [pattern, options]);
             
          }

          $('#btn-search').on('click', search);
          $('#input-search').on('keyup', search);
	
	</script>
	
	<div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Text Editors
            <small>Advanced form element</small>
          </h1>
          
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Forms</a></li>
            <li class="active">Editors</li>
          </ol>
          
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-12">
           
				<div class="col-sm-3">
		        <hr>       
		                
		          <h2>Procurar</h2>
		                      
		            <!-- <form> -->
		            <div class="form-group">
		                <label for="input-search" class="sr-only">Search Tree:</label>
		                <input type="input" class="form-control" id="input-search" placeholder="Digite para Procurar..." value="">
		            </div>		                      
		          
		          <div id="treeview-searchable" class="treeview" style="font-size:12px;line-height:0.85"></div>		
		        
		      </div>
              
            </div><!-- /.col-->
            
          </div><!-- ./row -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    
    <script type="text/javascript">
		var tree = [
           {
               text: "<img src='/assets/img/ic_domain_black_18dp.png' alt='img04'> Empresa Número Um",
               nodes: [
                 {
                     text: "<img src='img src='/assets/img//ic_language_black.png' alt='img04'> Área Um",
                     nodes: [
                       {
                           text: "<img src='img src='/assets/img//ic_settings_remote_black_24dp.png' alt='img04'>Sensor 1", "showIcon": true, id : 1
                       },
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 2"
                       }
                     ]
                     
                 },
                 {
                     text: "<img src='img src='/assets/img/ic_language_black.png' alt='img04'> Área Dois",
                     nodes: [
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 1"
                       },
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 2"
                       }
                     ]

                 }

               ]
           },
           {
               text: "<img src='img src='/assets/img/ic_domain_black_18dp.png' alt='img04'> Empresa Número Dois",
               nodes: [
                 {
                     text: "<img src='img src='/assets/img/ic_language_black.png' alt='img04'> Área Um",
                     nodes: [
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor A"
                       },
                       {
                           text: "<img src='img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor B"
                       }
                     ]
                 }
               ]
           }
          ];

          function getTree() {
              // Some logic to retrieve, or generate tree structure
              return tree;
          }


          var $searchableTree = $('#treeview-searchable').treeview({
              data: getTree(),
          });

          var search = function (e) {
              var pattern = $('#input-search').val();
              var options = {
                  ignoreCase: true,
                  exactMatch: false,
                  revealResults: true
              };

              var results = $searchableTree.treeview('search', [pattern, options]);
             
          }

          $('#btn-search').on('click', search);
          $('#input-search').on('keyup', search);
	
	</script>
    