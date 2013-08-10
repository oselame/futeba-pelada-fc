 <!-- pfcJogosCadastroJogadores.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<style>
<!--
	.tdLeft{
		border-left-style: solid;
		border-left-width: 1px;
	}
-->
</style>
<html:form action="/salvarCadSocioPartida.do" >

	<p>
		<a href="editarCadPartida.do?cdPartida=${sociopartidaForm.entidade.sociopartidaPK.cdPartida}">Cadastro de Jogos</a> | <strong>Jogadores</strong>
	</p>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				Dados do Jogo
			</td>
		</tr>
	</table>
	<br>
	<table width="100%"  border="0" cellspacing="0" cellpadding="2">
	<tag:espacador />
	<tr>
		<td width="120" align="right"><b>Ano:</b></td>
		<td width="50%">
			<bean:write name="sociopartidaForm" property="entidade.partida.nuAno"/>
		</td>	
		<td align="right" width="100"><b>Quadrimestre:</b> </td>
		<td>
			<bean:write name="sociopartidaForm" property="entidade.partida.cdQuadrimestre"/>
		</td>
			
	</tr>
	<tr>
		<td width="120" align="right"><b>Partida:</b></td>
		<td width="50%">
			<bean:write name="sociopartidaForm" property="entidade.sociopartidaPK.cdPartida"/>
		</td>	
		<td align="right"><b>Data da Partida:</b></td>
		<td>
			<bean:write name="sociopartidaForm" property="entidade.partida.dtPartidaformatada"/>
		</td>
		
	</tr>	
	<tr>
		<td  align="right">
			<b>Placar</b>
		</td>
		<td colspan="3">
			${nmTimeA}&nbsp;${sociopartidaForm.entidade.partida.dePlacar}&nbsp;${nmTimeB}
		</td>
	</tr>

	</table>
	<br>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				Participantes
			</td>
		</tr>
	</table>
	<br>
	<tag:Msg></tag:Msg>
	<html:hidden name="sociopartidaForm" property="entidade.sociopartidaPK.cdPartida" />	
	<html:hidden name="sociopartidaForm" property="nuJogadores" styleId="nuJogadores" />
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" >
	<tr>
		<td align="right">Participantes selecionados Time ${nmTimeA}:</td>
		<td width="20"><html:text name="sociopartidaForm" 
				property="nuJogadorTimeA" 
				styleId="nuJogadorTimeA"
				size="3" 
				readonly="true"/></td>
	</tr>
	<tr>
		<td align="right">Participantes selecionados Time ${nmTimeB}:</td>
		<td><html:text name="sociopartidaForm" 
				property="nuJogadorTimeB" 
				styleId="nuJogadorTimeB"
				size="3" 
				readonly="true"/></td>
	</tr>
	</table>
	
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
	<tr bgcolor="#CCCCCC" >
		<td width="25" align="center"><b>&nbsp;</b></td>
		<td ><b>Participante</label></b></td>
		<td width="30" align="center" bgcolor="#CCFFCC" title="Limpa Times" class="tdLeft"><b>LT</b></td>
		<td width="50" align="center" bgcolor="#CCFFCC"><b>Time ${nmTimeA}</b></td>
		<td width="50" align="center" bgcolor="#CCFFCC"><b>Time ${nmTimeB}</b></td>
		<td width="30" title="Gols"class="tdLeft"><b>Gols</b></td>
		<td width="30" title="Gols Contra"><b>Cont</b></td>
		<td width="20" align="center" bgcolor="#FFFFFF" title="Limpa os cartões" class="tdLeft"><b>LC</b></td>
		<td width="20" align="center" bgcolor="#FF0000" title="Cartão Vermelho"><b>&nbsp;</b></td>
		<td width="20" align="center" bgcolor="#0000FF" title="Cartão Azul"><b>&nbsp;</b></td>
		<td width="20" align="center" bgcolor="#FFFF00" title="Cartão Amarelo"><b>&nbsp;</b></td>
		<td width="30" align="center" title="Goleiro" class="tdLeft"><b>G</b></td>
		<td width="30" align="center" title="Atrazado" ><b>AT</b></td>
	</tr>
	<% int i = 1; %>
	<logic:iterate name="sociopartidaForm" property="rows" id="row" indexId="idx" >	
	<tr bgcolor="#FFFFFF" onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
		<td align="center">
			<%=i%>
		</td>
		<td align="left">
			<bean:write name="row" property="socio.nmApelido"/>
			<%-- (<bean:write name="row" property="socio.nmSocio"/>) --%>
			<html:hidden name="row" property="sociopartidaPK.cdSocio" indexed="true" />
			<html:hidden name="row" property="sociopartidaPK.cdPartida" indexed="true"/>
		</td>
		
		<td align="center" bgcolor="#CCFFCC" class="tdLeft">
			<html:radio name="row" property="cdTime" value="0" 
				indexed="true" styleId="cdTime_${idx}_0" 
				onclick="desmarcaCartoes(this);somaJogadoresTimes(${idx})"/>
		</td>
		<td align="center" bgcolor="#CCFFCC">
			<html:radio name="row" property="cdTime" value="1" 
				indexed="true" styleId="cdTime_${idx}_1" 
				onclick="somaJogadoresTimes(${idx})"/>
		</td>
		<td align="center" bgcolor="#CCFFCC">
			<html:radio name="row" property="cdTime" value="2" 
				indexed="true" styleId="cdTime_${idx}_2"
				onclick="somaJogadoresTimes(${idx})"/>
		</td>

		<td align="center" class="tdLeft">
			<html:text name="row" property="nuGol" style="width:30;text-align: right;" indexed="true"/>
		</td>
		
		<td align="center">
			<html:text name="row" property="nuGolcontra" style="width:30;text-align: right;" indexed="true"/>
		</td>		

		<td align="center" class="tdLeft">
			<html:radio name="row" property="tpCartao" value="0" indexed="true" styleId="tpCartao_${idx}_0" />
		</td>
		<td align="center">
			<html:radio name="row" property="tpCartao" value="1" indexed="true" styleId="tpCartao_${idx}_1" />
		</td>
		<td align="center">
			<html:radio name="row" property="tpCartao" value="2" indexed="true" styleId="tpCartao_${idx}_2" />
		</td>
		<td align="center">
			<html:radio name="row" property="tpCartao" value="3" indexed="true" styleId="tpCartao_${idx}_3" />
		</td>
		<td align="center" class="tdLeft">
			<html:hidden name="row" property="flGoleiro" styleId="hidden_flGoleiro_${idx}" indexed="true"/>
			<input type="checkbox" value="1" id="goleiro_${idx}" 
				onclick="setaValorGoleiro(this);" 
				${row.flGoleiro eq 1 ? "checked=\"checked\"" : ""}>
			<%--
			<html:checkbox name="row" property="flGoleiro" value="1" indexed="true" 
				styleId="flGoleiro_${idx}" onclick="setaValorGoleiro(this);"/>
			 --%>
		</td>	
		<td align="center">
			<html:hidden name="row" property="flAtrazado" styleId="hidden_flAtrazado_${idx}" indexed="true"/>
			<input type="checkbox" value="1" id="atrazado_${idx}"
				onclick="setaValorAtrazado(this); validaAtrazado(this);" 
				${row.flAtrazado eq 1 ? "checked=\"checked\"" : ""}>
			<%--
			<html:checkbox name="row" property="flAtrazado" value="1" indexed="true" 
				styleId="flAtrazado_${idx}" onclick="setaValorAtrazado(this); validaAtrazado(this);" />
			 --%>
		</td>			
	</tr>
	<%
		i++;
	%>
	</logic:iterate>	
	</table>	
	<p align="right">
		<logic:equal name="USUARIOLOGADO" value="true">
			<logic:equal name="TIPOUSUARIO" value="1">
				<logic:notEqual name="sociopartidaForm" property="entidade.partida.flConcluida" value="1">
					<html:submit value="Salvar" styleClass="bn" onclick="return somaTimes();" />
				</logic:notEqual>
			</logic:equal>
		</logic:equal>
	</p>
	<b>LT: </b>Limpa times<br>
	<b>LC: </b>Limpa Cartões<br>
	<b>G: </b>Goleiro<br>
	<b>AT: </b>Atrazado<br>
	
	<br>
	<script>
		function setaValorGoleiro(obj) {
			var indice = PfcUtil.getIndiceId(obj);
			var objeto = document.getElementById("goleiro_"+indice);
			var hidden = document.getElementById("hidden_flGoleiro_"+indice);
			if (obj.checked) {
				objeto.setAttribute("checked","checked");
				hidden.setAttribute("value","1");
			} else {
				objeto.removeAttribute("checked");
				hidden.setAttribute("value","0");
			}
		}
		
		function setaValorAtrazado(obj) {
			var indice = PfcUtil.getIndiceId(obj);
			var objeto = document.getElementById("atrazado_"+indice);
			var hidden = document.getElementById("hidden_flAtrazado_"+indice);
			if (obj.checked) {
				objeto.setAttribute("checked","checked");
				hidden.setAttribute("value","1");
			} else {
				objeto.removeAttribute("checked");
				hidden.setAttribute("value","0");
			}
		}
		
		function validaAtrazado(obj) {
			var indice = PfcUtil.getIndiceId(obj);
			if (document.getElementById("cdTime_"+indice+"_0").checked) {
				alert('Para marcar o Participante como atrazado é necessário selecionar um dos times.');
				obj.checked=false;
			}
		}
	
		function somaTimes() {
			var oNuJogadores = document.sociopartidaForm["nuJogadores"].value;
			var oNuJogadorTimeA = 0;
			var oNuJogadorTimeB = 0;
			for (i = 0;i < oNuJogadores;i++) {
				var campoA = "cdTime_" + i + "_1";
				var campoB = "cdTime_" + i + "_2";
				var oCampoA = document.getElementById(campoA);
				var oCampoB = document.getElementById(campoB);
				if (oCampoA.checked) {
					oNuJogadorTimeA = oNuJogadorTimeA + 1;
				} else if (oCampoB.checked) {
					oNuJogadorTimeB = oNuJogadorTimeB + 1;
				}
			}
			document.getElementById("nuJogadorTimeA").value = oNuJogadorTimeA;
			document.getElementById("nuJogadorTimeB").value = oNuJogadorTimeB;
			if ((oNuJogadorTimeA == 0) || (oNuJogadorTimeA > 8)) {
				alert("Numero de jogadores selecionados para o time ''${nmTimeA}'' é inválido!");
				return false;
			}
			if ((oNuJogadorTimeB == 0) || (oNuJogadorTimeB > 8)) { 
				alert("Numero de jogadores selecionados para o time ''${nmTimeB}'' é inválido!");
				return false;
			}
			return true;
		}

		function desmarcaCartoes(obj) {
			var nome = obj.name;
			var index1 = nome.indexOf('[');
			var index2 = nome.indexOf(']');			
			var indice = nome.substring((index1 + 1), index2);
			var onuGol = "row[" + indice + "].nuGol";
			var otpCartao = "tpCartao_" + indice + "_0";
			document.getElementsByName(onuGol)[0].value = "0";			
			document.getElementById(otpCartao).checked = true;			
		}
		
		function somaJogadoresTimes(index) {
			var oNuJogadores = document.sociopartidaForm["nuJogadores"].value;
			var oNuJogadorTimeA = 0;
			var oNuJogadorTimeB = 0;
			for (i = 0;i < oNuJogadores;i++) {
				var campoA = "cdTime_" + i + "_1";
				var campoB = "cdTime_" + i + "_2";
				var oCampoA = document.getElementById(campoA);
				var oCampoB = document.getElementById(campoB);
				if (oCampoA.checked) {
					oNuJogadorTimeA = oNuJogadorTimeA + 1;
				} else if (oCampoB.checked) {
					oNuJogadorTimeB = oNuJogadorTimeB + 1;
				}
			}
			if (oNuJogadorTimeA > 8) {				
				var campo0 = "cdTime_" + index + "_0";
				document.getElementById(campo0).checked = true;
				oNuJogadorTimeA = 8;
				alert("Não é possível selecionar mais jogadores para o time ''${nmTimeA}''!");
			}
			if (oNuJogadorTimeB > 8) {				
				var campo0 = "cdTime_" + index + "_0";
				document.getElementById(campo0).checked = true;
				oNuJogadorTimeB = 8;
				alert("Não é possível selecionar mais jogadores para o time ''${nmTimeB}''!");
			}
			document.getElementById("nuJogadorTimeA").value = oNuJogadorTimeA;
			document.getElementById("nuJogadorTimeB").value = oNuJogadorTimeB;
			return true;
		}		
	</script>	
</html:form>
<br>
