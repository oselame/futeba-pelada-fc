PfcUtil = {};

PfcUtil.getIndiceName = function(obj) {
	var name = obj.name;
	var inicio = name.indexOf('[');
	var fim = name.indexOf(']');			
	var indice = name.substring((inicio + 1), fim);
	return indice;		
};

PfcUtil.getIndiceId = function(obj) {
	var id = obj.id;
	var inicio = id.lastIndexOf('_');
	var indice = id.substring(inicio + 1);
	return indice;		
};

PfcUtil.abrirLink = function(link) {
	window.open("http://" + link,"_blank");
};