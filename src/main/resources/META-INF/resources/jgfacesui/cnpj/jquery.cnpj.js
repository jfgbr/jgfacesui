$(function() {

	$.fn.extend({
		cnpj : function() {

			var	maskCnpj = "__.___.___/____-__", 

			removeMask = function(value) {
				return value.replace(/\D/g, "");
			};

			setMask = function(elem) {
				var v = removeMask(elem.val());

				v = v.replace(/^(\d{2})(\d)/, "$1.$2");
				v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");
				v = v.replace(/\.(\d{3})(\d)/, ".$1/$2");
				v = v.replace(/(\d{4})(\d)/, "$1-$2");

				pos = v.length;
				v = v + maskCnpj.substring(pos, maskCnpj.length);
				elem.val(v);
				$.fn.setCursorPosition(elem,$.fn.lastIndexOf(elem));
			};

			clear = function(elem) {
				var v = removeMask(elem.val());

				if (v.length != 14) {
					elem.val("");
				}
			};

			verifyKeyCode = function(length, e) {
				if (length >= 14)
					// backspace / enter / tab
					if (!(e.which == '8' || e.which == '13' || e.which == "9"))
						e.preventDefault();
			};
			
			return this.each(function() {
				$(this).live("keyup", function(e) {
					var v = removeMask($(this).val());
					verifyKeyCode(v.length, e);
					setMask($(this));	
				})
				.live("keypress",function(e) {
					if (!(e.which == '8' || e.which == '13'
							|| e.which == "9"
							|| e.which == "0" || e.which == '118')) {
						if (!($.fn.isDigito(e))) {
							e.preventDefault();
							return false;
						}
					} else {
						if (e.which == '13') {
							clear($(this));		
						}
					}
				})
				.live("keydown", function(e) {
					var v = removeMask($(this).val());
					verifyKeyCode(v.length, e);
						
					if (e.which == '13') {
						if (!(v.length == 14)) {
							clear($(this));
							e.preventDefault();
							return false;
						}
	
					}
	
					if (e.which == "8") {
						i = $.fn.lastIndexOf($(this));
						c = $(this).val().charAt((i == 0) ? i : i - 1);
						cMask = maskCnpj.charAt((i == 0) ? i : i - 1);
						if (cMask != "_") {
							setMask($(this));
							$.fn.setCursorPosition($(this),i - 1);
						}
					} else {
						setMask($(this));
					}
	
				})
				.on("paste", function(e) {
					var elem = $(this);
					var v = removeMask(elem.val());
					elem.val(v);
					setTimeout(function() {
						setMask(elem);
					}, 100);
				})
				.live("blur", function() {
	
					clear($(this));
	
				})
				.live("focus", function() {
					if ($(this).val().length == 0) {
						$(this).val(maskCnpj);
						$.fn.setCursorPosition($(this),0);
					}
				})
				.live("click", function() {
					$.fn.setCursorPosition($(this),$.fn.lastIndexOf($(this)));
				})
				;
			});
		}
	});	
});