</*! jq.multiinput * Version: 1.0.1 * Build date: 2020-07-03 */
!function(h,t){var s="multiInput",e={limitMessage:"Limit reached",addText:"Add",removeText:"Remove"},n={input:!1,clearInputs:1,limit:3,separator:"\n",inputSeparator:"||",trimSeparator:!1,onElementAdd:null,onElementRemove:null,json:!1,addButtonHtml:'<a class="add" style="cursor: pointer;"><i class="fa fa-lg fa-plus-circle"></i><span class="sr-only">'+e.addText+"</span></a>",removeButtonHtml:'<a class="remove" style="cursor: pointer;"><i class="fa fa-lg fa-minus-circle"></i><span class="sr-only">'+e.removeText+"</span></a>",i18n:e};function a(t,e){this.options=h.extend({},n,e),this.$el=h(t),this.element=this.options.element?this.options.element:h(t),this.elementId=this.element.attr("id"),this.elementInput=this.options.input?this.options.input:h("<input>").attr({name:this.elementId+"Input",id:this.elementId+"Input",type:"text"}),this.elementInputs=null,this.elementCount=0,this.addLink=h(this.options.addButtonHtml),this.removeLink=h(this.options.removeButtonHtml),this.escSeparator=this.options.separator.replace(/[\-\[\]\/{}()*+?.\\^$|]/g,"\\$&"),this.escInputSeparator=this.options.inputSeparator.replace(/[\-\[\]\/{}()*+?.\\^$|]/g,"\\$&"),this.trimEx=new RegExp("^("+this.escSeparator+")+|("+this.escSeparator+")+$","gm"),this.trimExInput=new RegExp("^("+this.escInputSeparator+")+|("+this.escInputSeparator+")+$","gm"),this.init()}a.prototype={init:function(){var t=this;return t.element.length&&(t.elementInputs=t.fillElementsValues(t.element),t.element.hide().before(t.elementInputs)),this},clearInputs:function(t){h(":input",t).each(function(){switch(h(this).attr("type")){case"button":case"reset":case"submit":break;case"checkbox":case"radio":h(this).attr("checked",!1);break;default:h(this).val("")}}),this.saveElementsValues()},createElement:function(t,e,n){var i=this,s=t.clone(!1),a=h("<div>").addClass("inputWrapper").hide().append(s);if(h("[id]",a).each(function(){h(this).attr("id",h(this).attr("id")+e)}),this.options.json||h("[name]",a).each(function(){h(this).attr("name",h(this).attr("name")+e)}),h(".number",s).text(e+1),i.clearInputs(a),i.options.json)r=n;else var r=n.split(i.options.inputSeparator);var o=h(":input",a);if(i.options.json)h.each(r,function(t,e){h("[name="+t+"]",a).val(e)});else if(r.length)for(var p=0;p<r.length;p++)h(o[p]).val(r[p]);var l=i.addLink.clone(!1).click(function(t){if(t.preventDefault(),i.elementCount<i.options.limit){var e=i.createElement(i.elementInput,i.elementCount,""),n=h(this).parents(".inputWrapper").index();h(".number",e).text(n+2),h(this).parents(".inputWrapper").after(e),h(this).parents(".inputWrapper").nextAll(".inputWrapper").each(function(t){h(this).find(".number").text(n+t+2)}),e.show(0,function(){h(this).removeAttr("style")}),i.elementCount++,i.addElementEvents(e),i.saveElementsValues()}else alert(i.options.i18n.limitMessage)}),u=i.removeLink.clone(!1).click(function(t){t.preventDefault();var e=h(this).parents(".inputWrapper").clone();if(1<h(".inputWrapper",i.elementInputs).length){var n=h(this).parents(".inputWrapper").index();h(this).parents(".inputWrapper").nextAll(".inputWrapper").each(function(t){h(this).find(".number").text(n+t+1)}),h(this).parents(".inputWrapper").hide(0,function(){h(this).remove(),i.saveElementsValues(),i.elementCount--})}else i.clearInputs(h(this).parent());"function"==typeof i.options.onElementRemove&&i.options.onElementRemove(e,i)});return a.append(l).append(u),a},fillElementsValues:function(t){var e,n=this,i=t.attr("id");e=n.options.json?t.html()?JSON.parse(t.html()):[]:t.html().replace(/[\s\r\n]+$/,"").split(n.options.separator);var s,a=t.hasClass("required")?"required":"",r=h("<div>").attr("id",i+"Inputs").addClass("multiInput");if(e.length)for(var o=0;o<e.length;o++)s=n.createElement(n.elementInput,o,e[o]).addClass(i+"Input").addClass(a).show(),r.append(s),n.elementCount++,n.addElementEvents(s);else s=n.createElement(n.elementInput,0,"").addClass(i+"Input").show(),r.append(s),n.elementCount++,n.addElementEvents(s);return r},addElementEvents:function(t){var e=this;h("[name]",t).bind("change keyup mouseup",function(){return e.saveElementsValues(),!1}),"function"==typeof e.options.onElementAdd&&e.options.onElementAdd(t,e)},saveElementsValues:function(){var n=this;if(n.elementInputs){var t=n.elementInputs.children(".inputWrapper"),i=[];if(t.each(function(){var t=h(":input",h(this)),e=n.options.json?{}:[];h.each(t,function(){var t=h(this).attr("name");t&&n.options.json?e[t]=h(this).val():e.push(h(this).val())}),n.options.json||(e=e.join(n.options.inputSeparator),n.options.trimSeparator&&(e=e.replace(n.trimExInput,""))),i.push(e)}),n.options.json)n.element.text(JSON.stringify(i).replace(/\[\[/,"[ [").replace(/]]/,"] ]"));else{var e=i.join(n.options.separator);n.options.trimSeparator&&(e=e.replace(n.trimEx,"")),n.element.text(e)}}}},h.fn[s]=function(e){var n,i=arguments;return e===t||"object"==typeof e?this.each(function(){h.data(this,"plugin_"+s)||h.data(this,"plugin_"+s,new a(this,e))}):"string"==typeof e&&"_"!==e[0]&&"init"!==e?(this.each(function(){var t=h.data(this,"plugin_"+s);t instanceof a&&"function"==typeof t[e]&&(n=t[e].apply(t,Array.prototype.slice.call(i,1))),"destroy"===e&&h.data(this,"plugin_"+s,null)}),n!==t?n:this):void 0}}(jQuery,(window,void document));