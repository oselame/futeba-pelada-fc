var chamadaSubmit = 0; //verifica se a funcao foi chamada pelo submit ou nao
var camposOk = 1;
var conjCorrente = ""

function verificarCampos (nmForm, confirmar, conjunto){	// nmTagInicio, nmTagFim){ - 
	//return true 	//desativa a funcao
	if (navigator.appName == "Netscape"){
		var nada = undefined
	}
	else{
		var nada = null
	}

	//
	conjCorrente = conjunto

	//faz um looping entre todos os campos do form e ativa funcao onClick----------------------
	chamadaSubmit = 1;
	n = 0
	tam = document.forms[nmForm].elements.length
	
	while (n < tam && camposOk == 1){
		if (document.forms[nmForm].elements[n].type != "button" &&
		    document.forms[nmForm].elements[n].type != "submit" &&
		    document.forms[nmForm].elements[n].type != "reset" &&
			document.forms[nmForm].elements[n].type != "file" &&
			(document.forms[nmForm].elements[n].type != "checkbox" || document.forms[nmForm].elements[n].valida == "true") &&
			document.forms[nmForm].elements[n].disabled == false &&
		    document.forms[nmForm].elements[n].onclick != nada){
			document.forms[nmForm].elements[n].onclick()
		}
		
		n = n + 1
	}

	//flag para nao permitir onClick nos campos------------------------------------------------
	chamadaSubmit = 0;

	if(confirmar != null && camposOk == 1){
		if(confirm("Deseja salvar seus dados em nosso banco de currículos? \n Após salvos, os dados não poderão mais ser modificados."))
			return true;
		else
			return false;
	}
	if(camposOk == 1){
		return true;
	}
	else{
		camposOk = 1;
		return false;
	}

}


function verificarIntervDatas (campoInicio, campoFim, obrigatorio, conjunto){ //formato obrigatorio dd/mm/yyyy ou mm/aaaa

	if (conjunto == conjCorrente){
		dtInicio = document.forms[0].elements[campoInicio].value
		dtFim = document.forms[0].elements[campoFim].value

		//muda a data de mm/aaaa para dd/mm/aaaa---------------------------------------------------------------------
		dtInicio = new String(dtInicio);
		dtFim = new String(dtFim);

		if(dtInicio.length == 7)
			dtInicio = "01/" + dtInicio;
		if(dtFim.length == 7)
			dtFim = "01/" + dtFim;


		//verifica se o campo é obrigatorio-------------------------------------------------------------------------
		if(dtInicio == "" || obrigatorio != 1 && dtFim == ""){
			return false;
    		}

		//verifica se a data inicial é maior que a final-------------------------------------------------------------
		splitInicio = dtInicio.split("/")
		diaInicio = splitInicio[0]
		if(diaInicio.length == 1)
			diaInicio = "0" + diaInicio
		mesInicio = splitInicio[1]
		if(mesInicio.length == 1)
			mesInicio = "0" + mesInicio		
		inicio = new Number(splitInicio[2] + mesInicio + diaInicio)
		
		splitFim = dtFim.split("/")
		diaFim = splitFim[0]
		if(diaFim.length == 1)
			diaFim = "0" + diaFim
		mesFim = splitFim[1]
		if(mesFim.length == 1)
			mesFim = "0" + mesFim
		fim = new Number(splitFim[2] + mesFim + diaFim)
		
		//alert(inicio + " " + fim)
		
		if(fim < inicio){
			alert("Data de início maior que data de fim.")
			camposOk = 0
			document.forms[0].elements[campoInicio].focus()
			return false;
		}

	}
}

function verificaCpf (elemento, obrigatorio, campoVerificador, conjunto) {//proprio campo, se é obrigatorio(1) ou nao, campo verificador do cpf
	//return;

	if (chamadaSubmit == 1  && conjunto == conjCorrente){

		valorVerificador = eval("document.forms[0]['" +campoVerificador+ "'].value");
		cpf = elemento.value + valorVerificador

		if(cpf == "" && obrigatorio != 1){
			return false;
    		}
		if(elemento.value == ""){
			alert("Campo obrigatório não preenchido.")
			camposOk = 0;
			elemento.focus()
			return  false;
		}
		n = 0
		parar = "false"
			//alert(cpf.charAt(n))
		while(n < (cpf.length - 1) && parar != "true"){
			if(cpf.charAt(n) != cpf.charAt(n + 1)){
				parar = "true"
			}
			n = n + 1;
		}
		//alert(n)
		if(n == 10){
			alert ("O CPF digitado é inválido.");
      			camposOk = 0;
			elemento.focus()
			return false;
		}


		strcpf = cpf;
  		str_aux = "";
  
		for (i = 0; i <= strcpf.length - 1; i++)
    	if ((strcpf.charAt(i)).match(/\d/)) 
			str_aux += strcpf.charAt(i);
    	else if (!(strcpf.charAt(i)).match(/[\.\-]/)) {
      		alert ("O CPF digitado é inválido.");
      		camposOk = 0;
			elemento.focus()
			return false;
    	}
  
 	 	if (str_aux.length != 11) {
    		alert ("O CPF digitado é inválido.");
			camposOk = 0;
			elemento.focus()
			return false;
		}
  
  		soma1 = soma2 = 0;
  		for (i = 0; i <= 8; i++) {
    		soma1 += str_aux.charAt(i) * (10-i);
    		soma2 += str_aux.charAt(i) * (11-i);
  		}
  		d1 = ((soma1 * 10) % 11) % 10;
  		d2 = (((soma2 + (d1 * 2)) * 10) % 11) % 10;
  		if ((d1 != str_aux.charAt(9)) || (d2 != str_aux.charAt(10))) {
			if (navigator.appName != "Netscape"){
    				alert ("O CPF digitado é inválido.");
       				camposOk = 0;
				elemento.focus()
				return false;
			}
  		}
	}
}
function verificaTexto(elemento, obrigatorio, conjunto){
	//return;
	if (chamadaSubmit == 1 && conjunto == conjCorrente){
		if(elemento.value == "" && obrigatorio != 1){
			return false;
    		}
		if(elemento.value == ""){
			alert("Campo obrigatório não preenchido.")
			camposOk = 0;
			elemento.focus()
			return false;
		}
	}
}

function verificaLista(elemento, nuOrdem, conjunto){
	//return;
	if (chamadaSubmit == 1 && conjunto == conjCorrente){
		if(elemento.options.selectedIndex == nuOrdem){
			alert("Campo obrigatório não preenchido.")
			camposOk = 0;
			elemento.focus()
			return false;
    		}
		else{
			return false
		}
	}
}
function verificaNumero(elemento, obrigatorio, maximo, conjunto){
	//return;
	if (chamadaSubmit == 1 && conjunto == conjCorrente){
		if(elemento.value == "" && obrigatorio != 1){
			return false;
    		}

		if(elemento.value == ""){
			alert("Campo obrigatório não preenchido.")
			camposOk = 0;
			elemento.focus()
			return false;
		}
		
		//verifica se é um numero
		numero = new Number(elemento.value)
		if (isNaN(numero) == true){
			alert ("Campo preenchido com valores não numericos.");
			camposOk = 0;
			elemento.focus()
			return false;
		}
		
		//verifica se é menor ou igual ao maximo
		if(maximo != undefined){
			if(numero > maximo){
				alert("Campo preenchido com valores maiores que o máximo: " + maximo)
				camposOk = 0;
				elemento.focus()
				return false;
			}
		}
	}
}

function verificaData(elemento, obrigatorio, conjunto){	//so mes e ano
	//return;

	if (chamadaSubmit == 1 && conjunto == conjCorrente){	//verifica se a funcao foi chamada pela funcao "verificaCampos"
		if(elemento.value == "" && obrigatorio != 1){	//se nao for obrigatorio e estiver vazio sai da funcao
			return false;
    		}
		if(elemento.value == ""){
			alert("Campo obrigatório não preenchido.");
			camposOk = 0;
			elemento.focus()
			return false;
		}

		//conta quantidade de barras
		novaData = new String(elemento.value).valueOf( );
		qtBarras = 0;
		tamData = novaData.length
		for(nuCarac = 0; nuCarac < tamData; nuCarac++){
			if(novaData.substr(nuCarac,1) == "/"){
				qtBarras ++
			}
		}

		//verifica se o formato é mm/aaaa----------------------------------------------------------
		splitData = novaData.split("/")
		if(qtBarras == 1){
			a = new Number(splitData[1])
			m = new Number(splitData[0])
			d = "01"
		}
		if(qtBarras == 2){
			a = new Number(splitData[2])
			m = new Number(splitData[1])
			d = new Number(splitData[0])
		}
		if(qtBarras == 0 || qtBarras > 2){
			alert ("O campo data digitado é inválido.");
			camposOk = 0;
			elemento.focus()
			return false;
		}


		//verifica se os valores sao numeros--------------------------------------------------------
		if (isNaN(d) == true || isNaN(m) == true || isNaN(a) == true || new String(a).length > 4) {
			alert ("O campo data digitado é inválido.");
			camposOk = 0;
			elemento.focus()
			return false;
		}

		//verifica se a data esta certa-------------------------------------------------------------
  		if (((d > 31) || (!d) || (m > 12) || (!m) || (a < 1900) || (m < 1) || (d < 1)) ||       
     		((d == 31) && ((m == 2) || (m == 4) || (m == 6) || (m == 9) || (m == 11))) ||
     		((d == 30) && (m == 2)) ||
     		((d == 29) && (m == 2) && ((a % 4) || ((!(a % 100)) && (a % 400))))) {
			camposOk = 0;
			elemento.focus()
    		alert ("O campo data digitado é inválido.");
    		return false;
		}
	}
}


function checkRadio (nmCheck, nmRadio){ // checkRadio(this, '<nomeRadio>')
	//return
	var checarPrimeiro;
	if(chamadaSubmit == 0){
		formulario = nmCheck.form.name  //pega o nome do form do objeto
		if(nmCheck.checked == true){
			n = 0
			qtRadios = eval("document." + formulario + "['" + nmRadio + "'].length")
			while(n < qtRadios){
				//alert(eval("document." + formulario + "['" + nmRadio + "'][n].checked"))
				if(eval("document." + formulario + "['" + nmRadio + "'][n].checked") == true)
					checarPrimeiro = true
				n = n + 1;
			}
			if(checarPrimeiro != true)
				eval("document." + formulario + "['" + nmRadio + "'][0].checked = true")
		}
		else{
			n = 0
			qtRadios = eval("document." + formulario + "['" + nmRadio + "'].length")
			while(n < qtRadios){
				eval("document." + formulario + "['" + nmRadio + "'][n].checked = false")
				n = n + 1;
			}
		}
	}
}
function maxCarac (elemento, nuMax){
	formAtual = elemento.form.name // pega o nome do form do campo
	valorCaracAtual = eval("document." + formAtual + "['" + elemento.name + "'].value")
	nuCaracAtual = valorCaracAtual.length + 2
	//alert(valorCaracAtual.length)
	if(nuCaracAtual > nuMax){
		alert("O número máximo de caracteres neste campo é " + nuMax)
		teste = eval("document." + formAtual + "['" + elemento.name + "']")
		teste.value = valorCaracAtual.slice(0, (nuMax -1))
	}	
}

function verificaHidden(nmCampo, validador, mensagem, conjunto){
	 if(conjunto == conjCorrente){
			if(validador == 'ok'){
			return
		}
		else{
			alert (mensagem);
			camposOk = 0;
			document.forms[0][nmCampo].focus()
			return false;
		}	
	}
}


//verifica se existe pelo menos uma opcao selecionada
function verificaMultiplo(obj){
	if(chamadaSubmit == 1){
		name = obj.name
		checks = document.forms[0][name]
		qt = checks.length
		umMarcado = false
		for (i = 0; i < qt; i++){
			if(checks[i].checked == true){
				umMarcado = true
				break
			}
		}

		if(umMarcado == false){
			alert("Você precisa marcar pelo menos um item!")
			camposOk = 0;
			obj.focus()
			return false;
		}
	}
	
}


function verificaRadio(obj){
	var checado = false;
	nmCheck = obj.name
	if(chamadaSubmit == 0){
		formulario = nmCheck.form.name  //pega o nome do form do objeto
		conjRadio = document.forms[formulario][nmCheck]
		n = 0
		qtRadios = conjRadio.length
		while(n < qtRadios){
			if(conjRadio[n].checked == true){
				checado = true;
			}
			n = n + 1;
		}
		
	}
}





