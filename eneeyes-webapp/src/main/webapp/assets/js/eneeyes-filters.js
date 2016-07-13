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

app.filter('renderFriendlyDate', function($filter) {
    return function(s, currentFormat) {
        if (s == null) {
            return;
        }

        var i = s.split("/");

        var d = i[0];
        var m = i[1];
        var y = i[2];

        var p = ' ' + $filter('translate')('preposition') + ' ';

        m = $filter('renderHtml')($filter('translate')('month.'+parseInt(m)));

        return d + p + m + p + y;
    }
});