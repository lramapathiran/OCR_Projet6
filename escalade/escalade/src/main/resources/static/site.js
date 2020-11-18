$(document).ready(function () {
	
				function onElementAddRemove() {
					var elements = $('#areasDataInputs .inputElement');
	                
	                for(var i=0; i<elements.length; i++) {
	                	
	                	var el = elements[i];
	                	 $('.areaName', $(el)).attr('name','areas['+i+'].areaName');
			             $('.routesNumber', $(el)).attr('name','areas['+i+'].routesNumber');
			             $('.cotationsRange', $(el)).attr('name','areas['+i+'].cotationsRange');	
	                	
	                }
				}
				
		        $('#areasData').multiInput({
		        	
		            json: true,
		            input: $('<div class="row inputElement">\n' +
		                '<div class="multiinput-title col-sm-3">Secteur <span class="number">1</span></div>\n' +
		                '<div class="form-group col-sm-4">\n' +
		                '<input class="form-control areaName" name="areas[0].areaName" placeholder="Nom" type="text">\n' +
		                '</div>\n' +
		                '<div class="form-group col-sm-2">\n' +
		                '<input class="form-control routesNumber" name="areas[0].routesNumber" placeholder="Voies" type="number" min="0">\n' +
		                '</div>\n' +
		                '<div class="form-group col-sm-2">\n' +
		                '<input class="form-control cotationsRange" name="areas[0].cotationsRange" placeholder="Cotations" type="text" min="0">\n' +
		                '<small class="form-text text-muted ml-3">Ex:3a à 9c</small>\n' +
		                '</div>\n'),
		            limit: 10,
		        
		            onElementAdd: function (e, plugin) {
		            	
		            	onElementAddRemove();       
		            },
		            
		            onElementRemove: function (el, plugin) {
		            	
		            	onElementAddRemove();
		            }
		        });
});