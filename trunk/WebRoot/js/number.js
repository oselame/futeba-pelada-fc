NumberUtil = {};

NumberUtil.NumberKeypress = function(event) {
	if (navigator.appName == 'Microsoft Internet Explorer') {
		var tecla = event.keyCode;
	} else if (navigator.appName == 'Netscape') {  
		var tecla = event.which;
	}
	if ((tecla > 47 && tecla < 58)) // numeros de 0 a 9  
		return true;
	else {
		if (tecla != 8) // backspace  
			//event.keyCode = 0;  
			return false;
		else
			return true;
	}
}

NumberUtil.NumberFormat = function(event, obj, formato) {
	if (navigator.appName == 'Microsoft Internet Explorer') {
		var tecla = event.keyCode;
	} else if (navigator.appName == 'Netscape') {  
		var tecla = event.which;
	}
	if ((tecla > 47 && tecla < 58)) {// numeros de 0 a 9  
		if (obj.value == "") {
			return true;
		} else {
			if (obj.value.length < parseInt(formato)) {
				return true
			} else {
				return false;
			}
		}
	} else {
		if (tecla == 0) {
			return true;
		} else if (tecla != 8) // backspace  
			//event.keyCode = 0;  
			return false;
		else
			return true;
	}
}