/**
 * 
 */


app.controller('navegacaoController', function ($scope, $timeout, $filter) {
		
	$('#treeview-searchable').treeview({
	     data: getTree(),
	 });
	
	 search = function (e) {
	     var pattern = $('#input-search').val();
	     var options = {
	         ignoreCase: true,
	         exactMatch: false,
	         revealResults: true
	     };

	     var results = $searchableTree.treeview('search', [pattern, options]);
	    
	 }

	
});