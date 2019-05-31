app.filter('longdate', function($filter) {
    return function(timestamp, format) {
        if(timestamp == null || timestamp == 'null' || timestamp == undefined) return;
        var date = new Date(timestamp);
        return $filter('date')(date, format);
    };
});

app.filter('maxlength', function($filter) {
    return function(s, size) {
        if(s == null || s == 'null' || s == undefined) return;
        if(s.length < size) return s;
        return s.substr(0, size) + '...';
    };
});

app.filter('renderHtml', function($filter) {
    return function(s) {
        if (s == null) {
            return;
        }
        var parse = $.parseHTML(s);
        if (parse == null) {
            return;
        }
        return parse[0].data;
    }
});

app.filter('companyFilter', function () {
    return function (objects, criteria) {
        var filterResult = [];
        if (!criteria)
            return null;

        for (var index in objects) {                        
        	 if (objects[index] != null && objects[index].companyId == criteria.company.uid  ) {
                 filterResult.push(objects[index]);
             }
        }
        return filterResult;
    };
});

app.filter('detectorFilter', function () {
    return function (objects, criteria) {
        var filterResult = [];
        if (!criteria)
            return null;

        for (var index in objects) {                        
        	 if (objects[index] != null && objects[index].device == criteria.deviceType  ) {
                 filterResult.push(objects[index]);
             }
        }
        return filterResult;
    };
});