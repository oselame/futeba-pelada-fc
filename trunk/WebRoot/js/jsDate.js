/**
 * Retorna TRUE se o browser for Internet Explorer.
 */
function F_isIExplorer() {
    return (navigator.appName != "Netscape");
}

/**
 * Retorna o código da tecla digitada no OnKeyPress, OnKeyDown e OnKeyUp.
 */
function C_TeclaDigitada(event) {
    if (F_isIExplorer()) {
        return event.keyCode;
    } else {
        return event.charCode;
    }
}

/**
 * Retorna o código da tecla de controle digitada no OnKeyPress, OnKeyDown e OnKeyUp.
 */
function C_TeclaControleDigitada(event) {
    if (F_isIExplorer()) {
        if (event.type == "keypress") {
            return 0;
        } else {
            return event.keyCode;
        }
    } else {
        return event.keyCode; 
    }
}

/** 
 * Retorna a posição do cursor dentro do campo.
 */
function C_getPosTextoDigitado(ctrl) {
  	if (F_isIExplorer()) {
        ctrl.focus();
        range = document.selection.createRange();
        return (ctrl.value.length - range.move("character", 50000)); 
    } else {
        return ctrl.selectionStart;
    }
}

/**
 * Cancela o evento OnKeyPress, OnKeyDown, OnKeyUp.
 */
function C_CancelaEvento(event) {
    if (F_isIExplorer()) {
        event.returnValue = false;
    } else {
        event.preventDefault();
    }
}

/** 
 * Retorna o texto se o caracter "caracter" for digitado na posição atual do
 * cursor.
 */
function C_getTextoDigitado(ctrl, caracter) {
  	if (F_isIExplorer()) {
        ctrl.focus();
        range = document.selection.createRange();
        tamSel = String(range.text).length;
        posIni = (ctrl.value.length - range.move("character", 50000));
        posFim = posIni + tamSel;
    } else {
        posIni = ctrl.selectionStart;
        posFim = ctrl.selectionEnd;
    }
    textodigitado = ctrl.value.substr(0, posIni) + caracter + ctrl.value.substr(posFim);
    return textodigitado;
}

/**
 * Retorna TRUE se o evento OnKeyPress pode ser processado.
 */
function C_NaoPodeProcessarOnKeyPress(ctrl, event) {
    tecla = C_TeclaDigitada(event);
    return (ctrl.readOnly || tecla == 0 || tecla == 13 || event.ctrlKey || 
        event.ctrlLeft || C_TeclaControleDigitada(event) > 0);
}

/**
 * Retorna a data como um Array de 3 posições contendo:
 * [0] = DD, [1] = MM, [2] = AAAA
 */
function CD_getDataSplit(texto) {
    dataSplit = texto.split("/");
    numBarrasCompletar = 3 - dataSplit.length;
    for (nPosData=0;nPosData<numBarrasCompletar;nPosData++) {
        texto = texto + "/";
    }
    if (numBarrasCompletar > 0) {
        dataSplit = texto.split("/");
    }
    return dataSplit;
}

/**
 * Retorna a data formatada. Caso a data esteja incompleta, retorna a data
 * de forma completa.
 */
function CD_getValorFormatado(texto) {
    if (texto == "") {
        return "";
    }
    //Inicializa os valores da variáveis de controle
    dia = 0;
    mes = 0;
    ano = 0;
    dataAtual = new Date();
    //Pega os valores do dia, mês e ano
    dataSplit = CD_getDataSplit(texto);
    //Formata o dia
    dia = Number(dataSplit[0]);
    if (dia == 0) {
        dia = dataAtual.getDate();
    }
    if (String(dia).length == 1) {
        dia = "0" + dia;
    }
    //Formata o mês
    mes = Number(dataSplit[1])
    if (mes == 0) {
        mes = dataAtual.getMonth()+1; //getMonth() - 0 a 11
    }
    if (String(mes).length == 1) {
        mes = "0" + mes;
    }
    //Formata o ano
    ano = dataSplit[2]
    if ((dataSplit[2] != "00") && (Number(ano) == 0)) {
        ano = dataAtual.getFullYear();
    } else if (String(dataSplit[2]).length <= 2) {
        if (String(ano).length == 1) {
            ano = "0" + ano;
        }
        inicioAno = String(dataAtual.getFullYear()).substr(0,2);
        ano = Number(inicioAno + ano);
        if ((ano - 50) >= dataAtual.getFullYear()) {
            ano = ano - 100;
        }
    }
    return dia + "/" + mes + "/" + ano;
}

/** 
 * Retorna uma mensagem de erro caso o formato do campo não for válido.
 */
function CD_isFormatoValido(textoDigitado) {
    if (textoDigitado == "") {
        return "";
    }
    //Pega os valores do dia, mês e ano
    dataSplit = CD_getDataSplit(textoDigitado);
    dia = Number(dataSplit[0]);
    mes = Number(dataSplit[1]);
    ano = Number(dataSplit[2]);
    //Valida a data
    if (isNaN(dia) || isNaN(mes) || isNaN(ano)) {
        return "A data digitada é inválida.";
    }
    // Verifica se é ano bissexto
    bissexto = ((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0);
    //Verifica se o ano é válido
    min_ano = 1753; //Menor ano: SQLServer=1753, DB2=0001, Oracle=-4712, Informix=0001
    if (ano > 99 && ano < min_ano) {
        return "O ano informado deve ser maior que " + min_ano + ".";
    }
    //verifica se o mês é válido
    if (mes > 12) {
        return "O mês não pode ser maior que 12.";
    }
    //verifica se o dia é valido
    if ((mes == 2) && (bissexto) && (dia > 29)) {
        return "O mês " + mes + " não pode ter mais que 29 dias.";
    }
    if ((mes == 2) && (!bissexto) && (dia > 28)) {
        return "O mês " + mes + " não pode ter mais que 28 dias.";
    }
    if ((dia > 31) && ((mes == 1) || (mes == 3) || (mes == 5) || (mes == 7) || (mes == 8) || (mes == 10) || (mes == 12))) {
        return "O mês " + mes + " não pode ter mais que 31 dias.";
    }
    if ((dia > 30) && ((mes == 4) || (mes == 6) || (mes == 9) || (mes == 11))) {
        return "O mês " + mes + " não pode ter mais que 30 dias.";
    }
    return "";
}

/**
  * verifica se o texto exitentes em "ctrl" é uma data válida.
  */
function CD_verificaValor(ctrl) {
    //formata o campo data    
    valordigitado = CD_getValorFormatado(ctrl.value);
    ctrl.value = valordigitado;
    //verifica se o texto é válido
    msgformato = CD_isFormatoValido(valordigitado);
    //se não for válido, mostra uma mensagem
    if (msgformato != "") {
        alert(msgformato + 'Valor digitado inválido \"' + valordigitado + "\".");
		ctrl.focus();        
        //if(ctrl.className.indexOf('erro')==-1){
        //	ctrl.className=ctrl.className+' erro';
        //}
        return false;
    }
    //if(ctrl.className.indexOf('erro')!=-1){
      	//ctrl.className=ctrl.className.substring(0,ctrl.className.indexOf('erro'));
    //}
    return true;
}
	
/** 
 * Tratamento de digitação no componente 
 */
function CD_KPS(ctrl, event) {
    //Nas situações abaixo não deve fazer validação não faz nada
    if (C_NaoPodeProcessarOnKeyPress(ctrl, event)) {
        return;
    }
    //inicializa as variáveis de controle
    tecla = C_TeclaDigitada(event);
    //aceita numeros e a barra
    if (!( (tecla >= 48 && tecla <= 57) || tecla == 47)) {        
        C_CancelaEvento(event);
        return;            
    }
    //pega o texto que está sendo digitado
    textoDigitado = C_getTextoDigitado(ctrl, String.fromCharCode(tecla));
    //Pega os valores do dia, mês e ano
    dataSplit = CD_getDataSplit(textoDigitado);
    //Verifica se não existe mais que duas barras
    if (dataSplit.length > 3) {
        C_CancelaEvento(event);
        return;            
    }
    //completa as barras
    pos = C_getPosTextoDigitado(ctrl);
    if ( tecla != 47 && String(textoDigitado).length == pos+1 &&
         (String(dataSplit[0]).length == 3 || String(dataSplit[1]).length == 3) ) { 
        ctrl.value = ctrl.value + "/";
    } else {
        //verifica o formato do dia/mes/ano
        if ( (String(dataSplit[0]).length > 2) || (String(dataSplit[1]).length > 2) || (String(dataSplit[2]).length > 4) ) {
            C_CancelaEvento(event);
            return;            
        }
    }
}

/** 
  * Trata a saída do campo para não permitir que o campo fique com valores inválidos 
  */
function CD_BLR(ctrl) {
    //verifica se o texto existente no campo é válido
    return CD_verificaValor(ctrl);
}