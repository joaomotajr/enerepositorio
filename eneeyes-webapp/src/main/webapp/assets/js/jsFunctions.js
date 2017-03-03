
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

	function timeSince(date) {

	    var seconds = Math.floor((new Date() - date) / 1000);

	    var interval = Math.floor(seconds / 31536000);

	    if (interval > 1) {
	        return interval + " anos";
	    }
	    interval = Math.floor(seconds / 2592000);
	    if (interval > 1) {
	        return interval + " meses";
	    }
	    interval = Math.floor(seconds / 86400);
	    if (interval > 1) {
	        return interval + " dias";
	    }
	    interval = Math.floor(seconds / 3600);
	    if (interval > 1) {
	        return interval + " horas";
	    }
	    interval = Math.floor(seconds / 60);
	    if (interval > 1) {
	        return interval + " minutos";
	    }
	    return Math.floor(seconds) + " segundos";
	}
	
	var weekday = new Array(7);
	
	weekday[0]="Domingo";
	weekday[1]="Segunda-Feira";
	weekday[2]="Terça-Feira";
	weekday[3]="Quarta-Feira";
	weekday[4]="Quinta-Feira";
	weekday[5]="Sexta-Feira";
	weekday[6]="Sábado";
	
	