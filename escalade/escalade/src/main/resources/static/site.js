$(document).ready(function () {
	
				var i = 0;
		        $('#areasData').multiInput({
		        	
		            json: true,
		            input: $('<div class="row inputElement">\n' +
		                '<div class="multiinput-title col-sm-3">Secteur <span class="number">1</span></div>\n' +
		                '<div class="form-group col-sm-4">\n' +
		                '<input class="form-control areaName" name="areas[].areaName" placeholder="Nom" type="text">\n' +
		                '</div>\n' +
		                '<div class="form-group col-sm-2">\n' +
		                '<input class="form-control routesNumber" name="areas[].routesNumber" placeholder="Voies" type="number" min="0">\n' +
		                '</div>\n' +
		                '<div class="form-group col-sm-2">\n' +
		                '<input class="form-control cotationsRange" name="areas[].cotationsRange" placeholder="Cotations" type="text" min="0">\n' +
		                '<small class="form-text text-muted ml-3">Ex:3a à 9c</small>\n' +
		                '</div>\n'),
		            limit: 10,
		        
		            onElementAdd: function (el, plugin) {
		                
		                $('.areaName', $(el)).attr('name','areas['+i+'].areaName');
		                $('.routesNumber', $(el)).attr('name','areas['+i+'].routesNumber');
		                $('.cotationsRange', $(el)).attr('name','areas['+i+'].cotationsRange');
		               
		               i++;
		                
		            },
		            onElementRemove: function (el, plugin) {
		            	
		                i--;
		                
//		                for(j=0; j <= i; j++){
//		    				msg += "\n Textbox #" + i + " : " + $('#textbox' + i).val();
//		    			}
		            }
		        });
});