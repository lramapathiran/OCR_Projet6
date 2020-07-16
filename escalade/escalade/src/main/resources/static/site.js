$(document).ready(function () {
		        $('#participants').multiInput({
		            json: true,
		            input: $('<div class="row inputElement">\n' +
		                '<div class="multiinput-title col-xs-12">Secteur <span class="number">1</span></div>\n' +
		                '<div class="form-group col-xs-6">\n' +
		                '<input class="form-control" name="firstname" placeholder="Nom" type="text">\n' +
		                '</div>\n' +
		                '<div class="form-group col-xs-6">\n' +
		                '<input class="form-control" name="tn_lastname" placeholder="Nombre de Voies" type="number" min="0">\n' +
		                '</div>\n' +
		                '</div>\n'),
		            limit: 10,
		            onElementAdd: function (el, plugin) {
		                console.log(plugin.elementCount);
		                var inputs = $(':input', $(el));
		                var values = {};
		                $.each(inputs, function () {
		                    var name = $(this).attr('name');
		                    values[name] = $(this).val();
		                });
		                console.log(JSON.stringify(values));
		            },
		            onElementRemove: function (el, plugin) {
		                console.log(plugin.elementCount);
		                var inputs = $(':input', $(el));
		                var values = {};
		                $.each(inputs, function () {
		                    var name = $(this).attr('name');
		                    values[name] = $(this).val();
		                });
		                console.log(JSON.stringify(values));
		            }
		        });
});