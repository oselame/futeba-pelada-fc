	function anoBissexto(intYear) {
		if (intYear % 100 == 0) {
			if (intYear % 400 == 0) { 
				return true; 
			}
		} else {
			if ((intYear % 4) == 0) { 
				return true; 
			}
		}
		return false;
	}


	function converteData(obj) {
		if ((obj.value == "/") || (obj.value == "//")) {
			var data = new Date();
			var oDia = data.getDate();
			var oMes = (data.getMonth() + 1);
			var oAno = (1900 + data.getYear());
			if (oDia < 10) {
				oDia = '0' + oDia;
			}
			if (oMes < 10) {
				oMes = '0' + oMes;
			}
			obj.value = oDia  + "/" + oMes + "/" + oAno;
		}
		formataData(obj);
	}
	
	function validaData(obj) {
		if (obj.value != "") {
			var data = obj.value.split("/");
			var oDia = data[0];
			var oMes = data[1];
			var oAno = data[2];
			var oDiasMes = 31;
			if ((oMes = 4) || (oMes = 6) || (oMes = 9) || (oMes = 11)) {
				oDiasMes = 30;  
			} else if (oMes = 2) {
				oDiasMes = 28;
				if (anoBissexto(oAno)) {
					oDiasMes = 29;
				}
			}
			//-- validacao
			if ((oDia > oDiasMes) || (oMes > 12)) {				
				if (obj.className.indexOf('erro') == -1) {
        			obj.className = obj.className + ' erro';
        		}
				return false;
			} else {
			    if(obj.className.indexOf('erro')!=-1){
			      	obj.className = obj.className.substring(0,obj.className.indexOf('erro'));
			    }
			    return true;
			}			
		}
	}