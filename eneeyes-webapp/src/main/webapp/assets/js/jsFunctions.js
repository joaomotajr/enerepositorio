
	encodeImageFileAsURL = function (cb) {
	    return function(){
	        var file = this.files[0];
	        var reader  = new FileReader();
	        reader.onloadend = function () {
	            cb(reader.result);
	        }
	        reader.readAsDataURL(file);
	    }		    
	}
	
	
	//	$scope.loadIchecks = function ()
//	{
//
//	    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
//	      checkboxClass: 'icheckbox_minimal-blue',
//	      radioClass: 'iradio_minimal-blue'
//	    });	    
//	    
//	    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
//	      checkboxClass: 'icheckbox_minimal-red',
//	      radioClass: 'iradio_minimal-red'
//	    });	    
//	    
//	    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
//	      checkboxClass: 'icheckbox_flat-green',
//	      radioClass: 'iradio_flat-green'
//	    });
//	}