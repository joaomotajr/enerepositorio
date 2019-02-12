
app.controller('deviceTypeController', function ($scope, $timeout, DeviceTypeService) {
			
	$scope.saveDeviceType = function() {
		
		var exists =  $scope.deviceTypes.findIndex(function (i) { return i.type.toLowerCase() === $scope.deviceTypeType.toLowerCase() });
		
		if ( exists >= 0  && $scope.deviceTypeUid == undefined ) {
			$scope.deviceTypeTypeExist = "true";
			return;
		}	
		
		angular.element('body').addClass('loading');
		
		var deviceType = {
			uid: $scope.deviceTypeUid != undefined ? $scope.deviceTypeUid : 0,
			type: $scope.deviceTypeType,
			description: $scope.deviceTypeDescription,
			symbol: $scope.deviceTypeSymbol,
			graphicType: $scope.deviceTypeGraphic.type
    	}; 
		 
		$scope.inclusaoDeviceType = new DeviceTypeService.saveDeviceType(deviceType);		 
		$scope.inclusaoDeviceType.$deviceType({_csrf : angular.element('#_csrf').val()}, function()
		{         	
			$timeout(function () {				
            	$scope.clearFormDeviceType();
                $scope.getDeviceTypes();
	            
	            angular.element('body').removeClass('loading');				 
	         }, 500);
		});	
	 };
	 
	$scope.clearFormDeviceType = function () {
	
	    $scope.deviceTypeUid = undefined;
		$scope.deviceTypeType = '';
		$scope.deviceTypeDescription = '';
		$scope.deviceTypeSymbol = '';
		$scope.deviceTypeGraphic = '';
	}

	$scope.getDeviceTypes = function() {		 
		$scope.listAllDeviceType = new DeviceTypeService.listAllDeviceType();		 
		$scope.listAllDeviceType.$deviceType({_csrf : angular.element('#_csrf').val()}, function(){			
			$scope.deviceTypes = $scope.listAllDeviceType.list;
	   });		 
	};
 
	$scope.editDeviceType = function (index) {
	    $scope.deviceTypeUid = $scope.deviceTypes[index].uid;
	        
		$scope.deviceTypeType = $scope.deviceTypes[index].type;
		$scope.deviceTypeDescription = $scope.deviceTypes[index].description;		    	    
		$scope.deviceTypeSymbol = $scope.deviceTypes[index].symbol;		    	    
		
		$scope.deviceTypeGraphic = $scope.getGraphiType($scope.deviceTypes[index].graphicType);

	    $('#idDeviceTypeType').focus();
	};

	$scope.changeSymbol = function(s) {
		$scope.deviceTypeSymbol = s;
	};

	$scope.changeGraphic = function(s) {
		$scope.deviceTypeGraphic = s;
	};
	 
	$scope.deleteDeviceType = function(index) {
		 
		 var uid = $scope.deviceTypes[index].uid;		  
		 
		 $scope.deletar = new DeviceTypeService.deletaDeviceType();		 
		 $scope.deletar.$deviceType({_csrf : angular.element('#_csrf').val(), id : uid}, function(){			
			 if (!$scope.deletar.isError)
				 $scope.deviceTypes.splice(index, 1);
			 else {
				 $scope.msgErroDeviceType = $scope.deletar.message;
				 console.log($scope.deletar.systemMessage);
			 }
		});		 
	 }
	 
	 $scope.keypress = function($event) {
	    $scope.lastKey = $event.keyCode;
	    $scope.deviceTypeTypeExist = "false";
	  };
	 
	 $scope.refreshDeviceTypes = function() {
		 $scope.getDeviceTypes();	
	 };

	 $scope.getGraphiType = function (type) {		 
		for (var i = 0; i < $scope.images.length; i++) {
			if ($scope.images[i].type == type) {                 
				return $scope.images[i];
			}
		} 		 
	};
	 
	$scope.images = [
		{
        	img: '/assets/img/graphic_types/Bulb_Indicator.png',
			graphicType: 'Bulbo',
			type :'BULBINDICATOR'
		}, {
			img: '/assets/img/graphic_types/Cylinder_fill.png',
			graphicType: 'Cilindro',
			type:'CYLINDERFILL'
		}, {
			img: '/assets/img/graphic_types/Linear_Scale.png',
			graphicType: 'Escala Linear',
			type:'LINEARSCALE'
		}, {
			img: '/assets/img/graphic_types/Quarter_gauge.png',
			graphicType: 'Gauge 1',
			type:'QUARTERGAUGE'
		}, {
			img: '/assets/img/graphic_types/Rating_Meter.png',
			graphicType: 'Gauge 2',
			type:'RATINGMETER'
		}, {
			img: '/assets/img/graphic_types/Speedometer.png',
			graphicType: 'Velocimetro',
			type:'SPEEDOMETER'
		}, {
			img: '/assets/img/graphic_types/Thermometer_display.png',
			graphicType: 'Termometro',
			type:'THERMOMETERDISPLAY'
		}, {
			img: '/assets/img/graphic_types/Vertical_LEDs.png',
			graphicType: 'Leds Verticais',
			type:'VERTICALLEDS'
		}
	];

	 $scope.getDeviceTypes();	 
	 $scope.clearFormDeviceType();
	 angular.element('body').removeClass('loading');	
	 
	 $scope.symbols = [{id: 'fa-thermometer'},  {id: 'fa-glass'},{id: 'fa-music'},{id: 'fa-search'},{id: 'fa-envelope-o'},{id: 'fa-heart'},{id: 'fa-star'},{id: 'fa-star-o'},{id: 'fa-user'},{id: 'fa-film'},{id: 'fa-th-large'},{id: 'fa-th'},{id: 'fa-th-list'},{id: 'fa-check'},{id: 'fa-times'},{id: 'fa-search-plus'},{id: 'fa-search-minus'},{id: 'fa-power-off'},{id: 'fa-signal'},{id: 'fa-gear'},{id: 'fa-cog'},{id: 'fa-trash-o'},{id: 'fa-home'},{id: 'fa-file-o'},{id: 'fa-clock-o'},{id: 'fa-road'},{id: 'fa-download'},{id: 'fa-arrow-circle-o-down'},{id: 'fa-arrow-circle-o-up'},{id: 'fa-inbox'},{id: 'fa-play-circle-o'},{id: 'fa-rotate-right'},{id: 'fa-repeat'},{id: 'fa-refresh'},{id: 'fa-list-alt'},{id: 'fa-lock'},{id: 'fa-flag'},{id: 'fa-headphones'},{id: 'fa-volume-off'},{id: 'fa-volume-down'},{id: 'fa-volume-up'},{id: 'fa-qrcode'},{id: 'fa-barcode'},{id: 'fa-tag'},{id: 'fa-tags'},{id: 'fa-book'},{id: 'fa-bookmark'},{id: 'fa-print'},{id: 'fa-camera'},{id: 'fa-font'},{id: 'fa-bold'},{id: 'fa-italic'},{id: 'fa-text-height'},{id: 'fa-text-width'},{id: 'fa-align-left'},{id: 'fa-align-center'},{id: 'fa-align-right'},{id: 'fa-align-justify'},{id: 'fa-list'},{id: 'fa-dedent'},{id: 'fa-outdent'},{id: 'fa-indent'},{id: 'fa-video-camera'},{id: 'fa-photo'},{id: 'fa-image'},{id: 'fa-picture-o'},{id: 'fa-pencil'},{id: 'fa-map-marker'},{id: 'fa-adjust'},{id: 'fa-tint'},{id: 'fa-edit'},{id: 'fa-pencil-square-o'},{id: 'fa-share-square-o'},{id: 'fa-check-square-o'},{id: 'fa-arrows'},{id: 'fa-step-backward'},{id: 'fa-fast-backward'},{id: 'fa-backward'},{id: 'fa-play'},{id: 'fa-pause'},{id: 'fa-stop'},{id: 'fa-forward'},{id: 'fa-fast-forward'},{id: 'fa-step-forward'},{id: 'fa-eject'},{id: 'fa-chevron-left'},{id: 'fa-chevron-right'},{id: 'fa-plus-circle'},{id: 'fa-minus-circle'},{id: 'fa-times-circle'},{id: 'fa-check-circle'},{id: 'fa-question-circle'},{id: 'fa-info-circle'},{id: 'fa-crosshairs'},{id: 'fa-times-circle-o'},{id: 'fa-check-circle-o'},{id: 'fa-ban'},{id: 'fa-arrow-left'},{id: 'fa-arrow-right'},{id: 'fa-arrow-up'},{id: 'fa-arrow-down'},{id: 'fa-mail-forward'},{id: 'fa-share'},{id: 'fa-expand'},{id: 'fa-compress'},{id: 'fa-plus'},{id: 'fa-minus'},{id: 'fa-asterisk'},{id: 'fa-exclamation-circle'},{id: 'fa-gift'},{id: 'fa-leaf'},{id: 'fa-fire'},{id: 'fa-eye'},{id: 'fa-eye-slash'},{id: 'fa-warning'},{id: 'fa-exclamation-triangle'},{id: 'fa-plane'},{id: 'fa-calendar'},{id: 'fa-random'},{id: 'fa-comment'},{id: 'fa-magnet'},{id: 'fa-chevron-up'},{id: 'fa-chevron-down'},{id: 'fa-retweet'},{id: 'fa-shopping-cart'},{id: 'fa-folder'},{id: 'fa-folder-open'},{id: 'fa-arrows-v'},{id: 'fa-arrows-h'},{id: 'fa-bar-chart-o'},{id: 'fa-bar-chart'},{id: 'fa-twitter-square'},{id: 'fa-facebook-square'},{id: 'fa-camera-retro'},{id: 'fa-key'},{id: 'fa-gears'},{id: 'fa-cogs'},{id: 'fa-comments'},{id: 'fa-thumbs-o-up'},{id: 'fa-thumbs-o-down'},{id: 'fa-star-half'},{id: 'fa-heart-o'},{id: 'fa-sign-out'},{id: 'fa-linkedin-square'},{id: 'fa-thumb-tack'},{id: 'fa-external-link'},{id: 'fa-sign-in'},{id: 'fa-trophy'},{id: 'fa-github-square'},{id: 'fa-upload'},{id: 'fa-lemon-o'},{id: 'fa-phone'},{id: 'fa-square-o'},{id: 'fa-bookmark-o'},{id: 'fa-phone-square'},{id: 'fa-twitter'},{id: 'fa-facebook-f'},{id: 'fa-facebook'},{id: 'fa-github'},{id: 'fa-unlock'},{id: 'fa-credit-card'},{id: 'fa-rss'},{id: 'fa-hdd-o'},{id: 'fa-bullhorn'},{id: 'fa-bell'},{id: 'fa-certificate'},{id: 'fa-hand-o-right'},{id: 'fa-hand-o-left'},{id: 'fa-hand-o-up'},{id: 'fa-hand-o-down'},{id: 'fa-arrow-circle-left'},{id: 'fa-arrow-circle-right'},{id: 'fa-arrow-circle-up'},{id: 'fa-arrow-circle-down'},{id: 'fa-globe'},{id: 'fa-wrench'},{id: 'fa-tasks'},{id: 'fa-filter'},{id: 'fa-briefcase'},{id: 'fa-arrows-alt'},{id: 'fa-group'},{id: 'fa-users'},{id: 'fa-chain'},{id: 'fa-link'},{id: 'fa-cloud'},{id: 'fa-flask'},{id: 'fa-cut'},{id: 'fa-scissors'},{id: 'fa-copy'},{id: 'fa-files-o'},{id: 'fa-paperclip'},{id: 'fa-save'},{id: 'fa-floppy-o'},{id: 'fa-square'},{id: 'fa-navicon'},{id: 'fa-reorder'},{id: 'fa-bars'},{id: 'fa-list-ul'},{id: 'fa-list-ol'},{id: 'fa-strikethrough'},{id: 'fa-underline'},{id: 'fa-table'},{id: 'fa-magic'},{id: 'fa-truck'},{id: 'fa-pinterest'},{id: 'fa-pinterest-square'},{id: 'fa-google-plus-square'},{id: 'fa-google-plus'},{id: 'fa-money'},{id: 'fa-caret-down'},{id: 'fa-caret-up'},{id: 'fa-caret-left'},{id: 'fa-caret-right'},{id: 'fa-columns'},{id: 'fa-unsorted'},{id: 'fa-sort'},{id: 'fa-sort-down'},{id: 'fa-sort-desc'},{id: 'fa-sort-up'},{id: 'fa-sort-asc'},{id: 'fa-envelope'},{id: 'fa-linkedin'},{id: 'fa-rotate-left'},{id: 'fa-undo'},{id: 'fa-legal'},{id: 'fa-gavel'},{id: 'fa-dashboard'},{id: 'fa-tachometer'},{id: 'fa-comment-o'},{id: 'fa-comments-o'},{id: 'fa-flash'},{id: 'fa-bolt'},{id: 'fa-sitemap'},{id: 'fa-umbrella'},{id: 'fa-paste'},{id: 'fa-clipboard'},{id: 'fa-lightbulb-o'},{id: 'fa-exchange'},{id: 'fa-cloud-download'},{id: 'fa-cloud-upload'},{id: 'fa-user-md'},{id: 'fa-stethoscope'},{id: 'fa-suitcase'},{id: 'fa-bell-o'},{id: 'fa-coffee'},{id: 'fa-cutlery'},{id: 'fa-file-text-o'},{id: 'fa-building-o'},{id: 'fa-hospital-o'},{id: 'fa-ambulance'},{id: 'fa-medkit'},{id: 'fa-fighter-jet'},{id: 'fa-beer'},{id: 'fa-h-square'},{id: 'fa-plus-square'},{id: 'fa-angle-double-left'},{id: 'fa-angle-double-right'},{id: 'fa-angle-double-up'},{id: 'fa-angle-double-down'},{id: 'fa-angle-left'},{id: 'fa-angle-right'},{id: 'fa-angle-up'},{id: 'fa-angle-down'},{id: 'fa-desktop'},{id: 'fa-laptop'},{id: 'fa-tablet'},{id: 'fa-mobile-phone'},{id: 'fa-mobile'},{id: 'fa-circle-o'},{id: 'fa-quote-left'},{id: 'fa-quote-right'},{id: 'fa-spinner'},{id: 'fa-circle'},{id: 'fa-mail-reply'},{id: 'fa-reply'},{id: 'fa-github-alt'},{id: 'fa-folder-o'},{id: 'fa-folder-open-o'},{id: 'fa-smile-o'},{id: 'fa-frown-o'},{id: 'fa-meh-o'},{id: 'fa-gamepad'},{id: 'fa-keyboard-o'},{id: 'fa-flag-o'},{id: 'fa-flag-checkered'},{id: 'fa-terminal'},{id: 'fa-code'},{id: 'fa-mail-reply-all'},{id: 'fa-reply-all'},{id: 'fa-star-half-empty'},{id: 'fa-star-half-full'},{id: 'fa-star-half-o'},{id: 'fa-location-arrow'},{id: 'fa-crop'},{id: 'fa-code-fork'},{id: 'fa-unlink'},{id: 'fa-chain-broken'},{id: 'fa-question'},{id: 'fa-info'},{id: 'fa-exclamation'},{id: 'fa-superscript'},{id: 'fa-subscript'},{id: 'fa-eraser'},{id: 'fa-puzzle-piece'},{id: 'fa-microphone'},{id: 'fa-microphone-slash'},{id: 'fa-shield'},{id: 'fa-calendar-o'},{id: 'fa-fire-extinguisher'},{id: 'fa-rocket'},{id: 'fa-maxcdn'},{id: 'fa-chevron-circle-left'},{id: 'fa-chevron-circle-right'},{id: 'fa-chevron-circle-up'},{id: 'fa-chevron-circle-down'},{id: 'fa-html5'},{id: 'fa-css3'},{id: 'fa-anchor'},{id: 'fa-unlock-alt'},{id: 'fa-bullseye'},{id: 'fa-ellipsis-h'},{id: 'fa-ellipsis-v'},{id: 'fa-rss-square'},{id: 'fa-play-circle'},{id: 'fa-ticket'},{id: 'fa-minus-square'},{id: 'fa-minus-square-o'},{id: 'fa-level-up'},{id: 'fa-level-down'},{id: 'fa-check-square'},{id: 'fa-pencil-square'},{id: 'fa-external-link-square'},{id: 'fa-share-square'},{id: 'fa-compass'},{id: 'fa-toggle-down'},{id: 'fa-caret-square-o-down'},{id: 'fa-toggle-up'},{id: 'fa-caret-square-o-up'},{id: 'fa-toggle-right'},{id: 'fa-caret-square-o-right'},{id: 'fa-euro'},{id: 'fa-eur'},{id: 'fa-gbp'},{id: 'fa-dollar'},{id: 'fa-usd'},{id: 'fa-rupee'},{id: 'fa-inr'},{id: 'fa-cny'},{id: 'fa-rmb'},{id: 'fa-yen'},{id: 'fa-jpy'},{id: 'fa-ruble'},{id: 'fa-rouble'},{id: 'fa-rub'},{id: 'fa-won'},{id: 'fa-krw'},{id: 'fa-bitcoin'},{id: 'fa-btc'},{id: 'fa-file'},{id: 'fa-file-text'},{id: 'fa-sort-alpha-asc'},{id: 'fa-sort-alpha-desc'},{id: 'fa-sort-amount-asc'},{id: 'fa-sort-amount-desc'},{id: 'fa-sort-numeric-asc'},{id: 'fa-sort-numeric-desc'},{id: 'fa-thumbs-up'},{id: 'fa-thumbs-down'},{id: 'fa-youtube-square'},{id: 'fa-youtube'},{id: 'fa-xing'},{id: 'fa-xing-square'},{id: 'fa-youtube-play'},{id: 'fa-dropbox'},{id: 'fa-stack-overflow'},{id: 'fa-instagram'},{id: 'fa-flickr'},{id: 'fa-adn'},{id: 'fa-bitbucket'},{id: 'fa-bitbucket-square'},{id: 'fa-tumblr'},{id: 'fa-tumblr-square'},{id: 'fa-long-arrow-down'},{id: 'fa-long-arrow-up'},{id: 'fa-long-arrow-left'},{id: 'fa-long-arrow-right'},{id: 'fa-apple'},{id: 'fa-windows'},{id: 'fa-android'},{id: 'fa-linux'},{id: 'fa-dribbble'},{id: 'fa-skype'},{id: 'fa-foursquare'},{id: 'fa-trello'},{id: 'fa-female'},{id: 'fa-male'},{id: 'fa-gittip'},{id: 'fa-gratipay'},{id: 'fa-sun-o'},{id: 'fa-moon-o'},{id: 'fa-archive'},{id: 'fa-bug'},{id: 'fa-vk'},{id: 'fa-weibo'},{id: 'fa-renren'},{id: 'fa-pagelines'},{id: 'fa-stack-exchange'},{id: 'fa-arrow-circle-o-right'},{id: 'fa-arrow-circle-o-left'},{id: 'fa-toggle-left'},{id: 'fa-caret-square-o-left'},{id: 'fa-dot-circle-o'},{id: 'fa-wheelchair'},{id: 'fa-vimeo-square'},{id: 'fa-turkish-lira'},{id: 'fa-try'},{id: 'fa-plus-square-o'},{id: 'fa-space-shuttle'},{id: 'fa-slack'},{id: 'fa-envelope-square'},{id: 'fa-wordpress'},{id: 'fa-openid'},{id: 'fa-institution'},{id: 'fa-mortar-board'},{id: 'fa-yahoo'},{id: 'fa-google'},{id: 'fa-reddit'},{id: 'fa-reddit-square'},{id: 'fa-stumbleupon-circle'},{id: 'fa-stumbleupon'},{id: 'fa-delicious'},{id: 'fa-digg'},{id: 'fa-pied-piper'},{id: 'fa-pied-piper-alt'},{id: 'fa-drupal'},{id: 'fa-joomla'},{id: 'fa-language'},{id: 'fa-fax'},{id: 'fa-building'},{id: 'fa-child'},{id: 'fa-paw'},{id: 'fa-spoon'},{id: 'fa-cube'},{id: 'fa-cubes'},{id: 'fa-behance'},{id: 'fa-behance-square'},{id: 'fa-steam'},{id: 'fa-steam-square'},{id: 'fa-recycle'},{id: 'fa-car'},{id: 'fa-cab'},{id: 'fa-tree'},{id: 'fa-spotify'},{id: 'fa-deviantart'},{id: 'fa-soundcloud'},{id: 'fa-database'},{id: 'fa-file-pdf-o'},{id: 'fa-file-word-o'},{id: 'fa-file-excel-o'},{id: 'fa-file-powerpoint-o'},{id: 'fa-file-photo-o'},{id: 'fa-file-picture-o'},{id: 'fa-file-image-o'},{id: 'fa-file-zip-o'},{id: 'fa-file-archive-o'},{id: 'fa-file-sound-o'},{id: 'fa-file-audio-o'},{id: 'fa-file-movie-o'}, {id: 'fa-file-video-o'},{id: 'fa-file-code-o'},{id: 'fa-vine'},{id: 'fa-codepen'},{id: 'fa-jsfiddle'},{id: 'fa-life-bouy'},{id: 'fa-support'},{id: 'fa-circle-o-notch'},{id: 'fa-rebel'},{id: 'fa-ge'},{id: 'fa-empire'},{id: 'fa-git-square'},{id: 'fa-git'},{id: 'fa-hacker-news'},{id: 'fa-tencent-weibo'},{id: 'fa-qq'},{id: 'fa-wechat'},{id: 'fa-paper-plane'},{id: 'fa-paper-plane-o'},{id: 'fa-history'},{id: 'fa-genderless'},{id: 'fa-circle-thin'},{id: 'fa-header'},{id: 'fa-paragraph'},{id: 'fa-sliders'},{id: 'fa-share-alt'},{id: 'fa-share-alt-square'},{id: 'fa-bomb'},{id: 'fa-soccer-ball-o'},{id: 'fa-futbol-o'},{id: 'fa-tty'},{id: 'fa-binoculars'},{id: 'fa-plug'},{id: 'fa-slideshare'},{id: 'fa-twitch'},{id: 'fa-yelp'},{id: 'fa-newspaper-o'},{id: 'fa-wifi'},{id: 'fa-calculator'},{id: 'fa-paypal'},{id: 'fa-google-wallet'},{id: 'fa-cc-visa'},{id: 'fa-cc-mastercard'},{id: 'fa-cc-discover'},{id: 'fa-cc-amex'},{id: 'fa-cc-paypal'},{id: 'fa-cc-stripe'},{id: 'fa-bell-slash'},{id: 'fa-bell-slash-o'},{id: 'fa-trash'},{id: 'fa-copyright'},{id: 'fa-at'},{id: 'fa-eyedropper'},{id: 'fa-paint-brush'},{id: 'fa-birthday-cake'},{id: 'fa-area-chart'},{id: 'fa-pie-chart'},{id: 'fa-line-chart'},{id: 'fa-lastfm'},{id: 'fa-lastfm-square'},{id: 'fa-toggle-off'},{id: 'fa-toggle-on'},{id: 'fa-bicycle'},{id: 'fa-bus'},{id: 'fa-ioxhost'},{id: 'fa-angellist'},{id: 'fa-cc'},{id: 'fa-shekel'},{id: 'fa-sheqel'},{id: 'fa-ils'},{id: 'fa-meanpath'},{id: 'fa-buysellads'},{id: 'fa-connectdevelop'},{id: 'fa-dashcube'},{id: 'fa-forumbee'},{id: 'fa-leanpub'},{id: 'fa-sellsy'},{id: 'fa-shirtsinbulk'},{id: 'fa-simplybuilt'},{id: 'fa-skyatlas'},{id: 'fa-cart-plus'},{id: 'fa-cart-arrow-down'},{id: 'fa-diamond'},{id: 'fa-ship'},{id: 'fa-user-secret'},{id: 'fa-motorcycle'},{id: 'fa-street-view'},{id: 'fa-facebook-official'},{id: 'fa-pinterest-p'},{id: 'fa-whatsapp'},{id: 'fa-server'},{id: 'fa-user-plus'},{id: 'fa-user-times'},{id: 'fa-hotel'},{id: 'fa-viacoin'},{id: 'fa-train'},{id: 'fa-subway'},{id: 'fa-medium'}];

});