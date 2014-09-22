$(function() {


	$.fn.isDigito = function(e) {
		if (!e)
			e = window.event;
		if (e.keyCode)
			code = e.keyCode;
		if (e.which)
			code = e.which;
		var c = String.fromCharCode(code);
		var reDigits = /^\d+$/;
		return reDigits.test(c);
	};

	$.fn.setCursorPosition = function(elem,pos) {
		if (elem.get(0).setSelectionRange) {
			elem.get(0).setSelectionRange(pos, pos);
		} else if (elem.get(0).createTextRange) {
			var range = elem.get(0).createTextRange();
			range.collapse(true);
			range.moveEnd('character', pos);
			range.moveStart('character', pos);
			range.select();
		}
	};
	
	$.fn.getCursorPosition = function(elem) {
        var input = elem.get(0);
        if (!input) return;
        if ('selectionStart' in input) {
            return input.selectionStart;
        } else if (document.selection) {
            // IE
            input.focus();
            var sel = document.selection.createRange();
            var selLen = document.selection.createRange().text.length;
            sel.moveStart('character', -input.value.length);
            return sel.text.length - selLen;
        }
    }
	

	$.fn.getSelectionStart = function(elem) {
		if (elem.lengh == 0)
			return -1;
		var input = elem.get(0);

		var pos = input.value.length;

		if (input.createTextRange) {
			var r = document.selection.createRange().duplicate();
			r.moveEnd('character', input.value.length);
			if (r.text == '')
				pos = input.value.length;
			pos = input.value.lastIndexOf(r.text);
		} else if (typeof (input.selectionStart) != "undefined")
			pos = input.selectionStart;

		return pos;
	}
	
	$.fn.getSelectionEnd = function(elem){
		if (elem.lengh == 0)
			return -1;
		input = elem.get(0);

		var pos = input.value.length;

		if (input.createTextRange) {
			var r = document.selection.createRange().duplicate();
			r.moveStart('character', -input.value.length);
			if (r.text == '')
				pos = input.value.length;
			pos = input.value.lastIndexOf(r.text);
		} else if (typeof (input.selectionEnd) != "undefined")
			pos = input.selectionEnd;

		return pos;
	}
	
	$.fn.getSelection = function(elem) {
		if (elem.lengh == 0)
			return -1;
		var s = $.fn.getSelectionStart(elem);
		var e = $.fn.getSelectionEnd(elem);
		return elem.get(0).value.substring(s, e);
	}

	$.fn.lastIndexOf = function(elem) {
		nextPos = elem.val().indexOf("_");
		return nextPos = ((nextPos != -1) ? nextPos : elem.val().length);
	};

});