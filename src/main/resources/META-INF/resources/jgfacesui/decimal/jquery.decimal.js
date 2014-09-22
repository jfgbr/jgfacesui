$(function() {
	
	$.fn.extend({
	decimal : function(options) {

		var settings = $.extend({
			// Valor Padrão
			separadorDecimal : ",",
			separadorDezenas : ".",
			qtdDecimal : 2,
			exibeValorPadrao : true

		}, options);
		


		return this.each(function() {
			var valorInicial = "0@";
			var selectionStart;
			var selectionEnd;
			var selectionText;
			
			for ( var i = 0; i < settings.qtdDecimal; i++) {
				valorInicial+= "0";
			}
			valorInicial = valorInicial.replace("@", settings.separadorDecimal);
			
			setMaskMoeda = function(elem, separadorDecimal, separadorDezenas, qtdDecimal) {
				var v = elem.val();
				var currentPos = $.fn.getCursorPosition(elem);
				var initialLength = v.length;
				//retira a formatação
				v = v.replace(/\D/g, "");
				
				var len = v.length;
				if (0 == len)
					v = valorInicial;
				else if (len < qtdDecimal)
		        	v = v.replace(/(\d)/,"0@0$1");
		        else if (qtdDecimal == len)
		        	v = v.replace(/(\d)/,"0@$1");
		        else if (len > qtdDecimal){
		        	var r = "(\\d)";
		            var m = "$1";
		            var j = 2;
		            if (v.charAt(0) == '0' && elem.val().charAt(1) != separadorDecimal){
		            	v = v.substring(1,v.length);
		            	currentPos--;
		            	len--;
		            	initialLength--;
		            }
		            
		            for (var i = 3; i < (len-qtdDecimal); i += 3){
		            	r += "(\\d{3})";
		            	if (j>1)
		            		m+="#";
		            	m += "$"+ j;
		            	j+=1;
		            }
		            
		            if (j>1){
			            r += "(\\d{"+qtdDecimal+"})$";
			            m += "@$" + j;
			            reg = new RegExp(r);
			            v = v.replace(reg,m);
		            }else{
		            	r += "(\\d{"+qtdDecimal+"})$@$1";
		            	reg = new RegExp(r);
		            	v = v.replace(reg ,'@$1');
		            }
		        }
		        
//		        (\d{3})(\d{3})(\d{3})(\d{3})(\d{3})(\d{2})$
//		        $1.$2.$3.$4.$5,$6
				if (v != "" && v.charAt(0) == '0' && v.charAt(1) != '\@'){
					var inicio = 0;
					inicio++;
					for (var i=1;i<v.length;i++){
						if (v.charAt(i+1) != '\@' && v.charAt(i) == '0'){
							inicio++;
						} else {
							i = v.length;
						}
					}
					v = v.substring(inicio,v.length);
				}
				v = v.replace(/@/g,separadorDecimal).replace(/#/g,separadorDezenas);
				
				var finalLength = v.length; 
				elem.val(v);				
				$.fn.setCursorPosition(elem, currentPos+finalLength-initialLength);
			};
			
			
			$(this).bind('click', function(e) {
				//$.fn.setCursorPosition($(this), $.fn.lastIndexOf($(this)));
			})
			.bind("keyup", function(e) {
				setMaskMoeda($(this),settings.separadorDecimal,settings.separadorDezenas,settings.qtdDecimal);
			})
			.bind("keypress", function(e) {
				if (!(e.which == '8' || e.which == '13'
						|| e.which == "9" || e.which == "46" || e.which == "0" || (e.which >= "37" && e.which <= "40") )) {
					if (!($.fn.isDigito(e))) {
						e.preventDefault();
						return false;
					}
				}
			})
			.bind("keydown", function(e) {
				if (e.which == '8' || e.which == "46") {
					var value = $(this).val();
					if (selectionStart != selectionEnd) {
						$(this).val(value.substring(0,selectionStart)+value.substring(selectionEnd,value.length));
						selectionEnd = selectionStart;
						$.fn.setCursorPosition($(this), selectionStart);
						e.preventDefault();
					} 
					else {
						var pos = $.fn.getSelectionStart($(this));
						var newPos;
						if (e.which == '8') {
							pos--;
							newPos = pos;							
						} else {
							newPos = pos + 1;
						}
						if (value.charAt(pos) == settings.separadorDecimal || 
							value.charAt(pos) == settings.separadorDezenas) {
							$.fn.setCursorPosition($(this), newPos);
						}
					}
				}
//				if (e.which == '13') {
//					e.preventDefault();
//					return false;
//				}

				setMaskMoeda($(this),settings.separadorDecimal,settings.separadorDezenas,settings.qtdDecimal);
			})
			.bind("blur", function() {
				if (!settings.exibeValorPadrao) {
					if ($(this).val() == valorInicial) {
						$(this).val("");
					}
				}
			})
			.bind("focus", function() {
				if ($(this).val().length == 0) {
					$(this).val(valorInicial);
					//$.fn.setCursorPosition($(this), $.fn.lastIndexOf($(this)));
				}
			})
			.on("paste", function(e) {
				var elem = $(this);
				elem.val("");
				setTimeout(function() {
					setMaskMoeda(elem,settings.separadorDecimal,settings.separadorDezenas,settings.qtdDecimal);
			    }, 100);
			})
			.on("mouseup", function(e){
				selectionStart = $.fn.getSelectionStart($(this));
				selectionEnd = $.fn.getSelectionEnd($(this));
				selectionText = $.fn.getSelection($(this));
			})
			;

		});
	}});
});