function mudacor(ref, cor) {
	ref.style.backgroundColor = cor;
}

function toggleColor(objElement) {	
	var className = objElement.className;
	var vClassName = className.split(" ");
	if (vClassName[0] == "pfcCampoTexto") {
		vClassName[0] = "pfcCampoTextoFocus";
	} else {
		vClassName[0] = "pfcCampoTexto";
	}
	var classe = "";
	classe = vClassName[0];
	for (i=1; i < vClassName.length; i++) {
		classe = classe + " " + vClassName[i];
	}
	objElement.className = classe;
}

