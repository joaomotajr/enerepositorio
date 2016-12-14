
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
	
	getDate = function (data, end) {

        if (data == undefined)
            return null;

        var dataAdm = new Date();        
        var newDate = data.split('/', 3);

        dataAdm.setDate(newDate[0]);
        dataAdm.setMonth(newDate[1] - 1);
        dataAdm.setYear(newDate[2]);
        
        if (end)
        	dataAdm.setHours(23, 59, 59, 999);
        else	
        	dataAdm.setHours(0, 0, 0, 0);
        
        return dataAdm;
    }
