
app.controller('fusionchartsController', function ($scope, $timeout, $filter) {
	
	
	$scope.dataSource = {
		    chart: {
		        caption: "Harry's SuperMart",
		        subCaption: "Top 5 stores in last month by revenue",
		        numberPrefix: "$",
		        theme: "fint"
		    },
		    data: [{
		        label: "Bakersfield Central",
		        value: "880000"
		    }, {
		        label: "Garden Groove harbour",
		        value: "730000"
		    }, {
		        label: "Los Angeles Topanga",
		        value: "590000"
		    }, {
		        label: "Compton-Rancho Dom",
		        value: "520000"
		    }, {
		        label: "Daly City Serramonte",
		        value: "330000"
		    }]
		};
	
	$scope.attrs = {
		    "caption": "Sales - 2012 v 2013",
		    "numberprefix": "$",
		    "plotgradientcolor": "",
		    "bgcolor": "FFFFFF",
		    "showalternatehgridcolor": "0",
		    "divlinecolor": "CCCCCC",
		    "showvalues": "0",
		    "showcanvasborder": "0",
		    "canvasborderalpha": "0",
		    "canvasbordercolor": "CCCCCC",
		    "canvasborderthickness": "1",
		    "yaxismaxvalue": "30000",
		    "captionpadding": "30",
		    "linethickness": "3",
		    "yaxisvaluespadding": "15",
		    "legendshadow": "0",
		    "legendborderalpha": "0",
		    "palettecolors": "#f8bd19,#008ee4,#33bdda,#e44a00,#6baa01,#583e78",
		    "showborder": "0"
		};

		$scope.categories = [
		    {
		        "category": [
		            {
		                "label": "Jan"
		            },
		            {
		                "label": "Feb"
		            },
		            {
		                "label": "Mar"
		            },
		            {
		                "label": "Apr"
		            },
		            {
		                "label": "May"
		            },
		            {
		                "label": "Jun"
		            },
		            {
		                "label": "Jul"
		            },
		            {
		                "label": "Aug"
		            },
		            {
		                "label": "Sep"
		            },
		            {
		                "label": "Oct"
		            },
		            {
		                "label": "Nov"
		            },
		            {
		                "label": "Dec"
		            }
		        ]
		    }
		];

		$scope.dataset = [
		    {
		        "seriesname": "2013",
		        "data": [
		            {
		                "value": "22400"
		            },
		            {
		                "value": "24800"
		            },
		            {
		                "value": "21800"
		            },
		            {
		                "value": "21800"
		            },
		            {
		                "value": "24600"
		            },
		            {
		                "value": "27600"
		            },
		            {
		                "value": "26800"
		            },
		            {
		                "value": "27700"
		            },
		            {
		                "value": "23700"
		            },
		            {
		                "value": "25900"
		            },
		            {
		                "value": "26800"
		            },
		            {
		                "value": "24800"
		            }
		        ]
		    },
		    {
		        "seriesname": "2012",
		        "data": [
		            {
		                "value": "10000"
		            },
		            {
		                "value": "11500"
		            },
		            {
		                "value": "12500"
		            },
		            {
		                "value": "15000"
		            },
		            {
		                "value": "16000"
		            },
		            {
		                "value": "17600"
		            },
		            {
		                "value": "18800"
		            },
		            {
		                "value": "19700"
		            },
		            {
		                "value": "21700"
		            },
		            {
		                "value": "21900"
		            },
		            {
		                "value": "22900"
		            },
		            {
		                "value": "20800"
		            }
		        ]
		    }
		];
	
		$scope.myDataSource = {
			    chart: {
			        caption: "Age profile of website visitors",
			        subcaption: "Last Year",
			        startingangle: "120",
			        showlabels: "0",
			        showlegend: "1",
			        enablemultislicing: "0",
			        slicingdistance: "15",
			        showpercentvalues: "1",
			        showpercentintooltip: "0",
			        plottooltext: "Age group : $label Total visit : $datavalue",
			        theme: "fint"
			    },
			    data: [
			        {
			            label: "Teenage",
			            value: "1250400"
			        },
			        {
			            label: "Adult",
			            value: "1463300"
			        },
			        {
			            label: "Mid-age",
			            value: "1050700"
			        },
			        {
			            label: "Senior",
			            value: "491000"
			        }
			    ]
			}	    
		
		$scope.ggDataSource = {
		  		  
		        dials: {
		            dial: [{
		                id: "crntYr",
		                value: "78",
		                showValue: "1",
		                tooltext: "Current year's average : $value",
		                rearExtension: "5"
		            }]
		        }
		    }
		
		$scope.dados = {
			dial: [{
                id: "crntYr",
                value: "78",
                showValue: "1",
                tooltext: "Current year's average : $value",
                rearExtension: "5"
            }]				
		}
		
		$scope.clearForm = function() {
			
			$scope.dados.dial[0].value = 00;
			
		}
		
		$scope.colors = {
				
			 color: [
				{
	                minValue: "0",
	                maxValue: "120",
	                code: "#D8D8D8"
	            },
				{
	                minValue: "120",
	                maxValue: "170",
	                code: "#6baa01"
	            }, {
	                minValue: "170",
	                maxValue: "200",
	                code: "#f8bd19"
	            }, {
	                minValue: "200",
	                maxValue: "300",
	                code: "#e44a00"
	            }]
		
		}
		
		
		$scope.atributos = {
		        
            caption: "Server CPU Utilization",
            subcaption: "forum.hsm.com",
            lowerLimit: "0",
            upperLimit: "300",
            editMode: "1",
            showValue: "1",
            valueBelowPivot: "1",
            tickValueDistance: "25",
            gaugeFillMix: "{dark-30},{light-60},{dark-10}",
            gaugeFillRatio: "15",
            theme: "fint",
            valueFontSize: "14"
		        
		};
	
	
	 angular.element('body').removeClass('loading');
			
});