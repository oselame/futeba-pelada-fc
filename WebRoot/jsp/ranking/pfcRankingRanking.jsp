<!-- pfcRankingRanking.jsp -->
<%@page import="br.com.softal.pfc.Constantes"%><%@ include file="/pfcInclude.jsp"%>
<p><strong>${Constantes.SESSION_TITULO}</strong></p>
<p><strong>${periodoQuadrimestre}</strong></p>
<table width="100%"  border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC">
	<tr bgcolor="#efefef">
		<td width="25">&nbsp;</td>
		<td>Nome</td>
		<td width="35" align="center" title="Pontos">P</td>
		<td width="35" align="center" title="Jogos">J</td>
		<td width="35" align="center" title="Vitórias">V</td>
	    <td width="35" align="center" title="Empates">E</td>
	    <td width="35" align="center" title="Derrotas">D</td>
	    <td width="35" align="center" bgcolor="#FF7553" title="Cartôes vermelhos">C</td>	    
	    <td width="35" align="center" bgcolor="#66CCFF" title="Cartões azuis">C</td>
	    <td width="35" align="center" bgcolor="#FFFF00" title="Cartões amarelos">C</td>
	    <td width="35" align="center" title="Anterior">A</td>
	</tr>
	<logic:iterate name="classificacaoForm" property="rows" id="row">
	<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
		<td width="25" ><bean:write name="row" property="nuClassificacao" /></td>
		<td ><bean:write name="row" property="socio.nmApelido" /></td> <!-- nmSocio -->
		<td width="35" align="center" ><bean:write name="row" property="nuPontos" /></td>
		<td width="35" align="center" ><bean:write name="row" property="nuJogos" /></td>
		<td width="35" align="center" ><bean:write name="row" property="nuVitorias" /></td>
	    <td width="35" align="center" ><bean:write name="row" property="nuEmpates" /></td>
	    <td width="35" align="center" ><bean:write name="row" property="nuDerrotas" /></td>
	    <td width="35" align="center" bgcolor="#FF7553" ><bean:write name="row" property="nuCartaovermelho" /></td>
	    <td width="35" align="center" bgcolor="#66CCFF" ><bean:write name="row" property="nuCartaoazul" /></td>
	    <td width="35" align="center" bgcolor="#FFFF00" ><bean:write name="row" property="nuCartaoamarelo" /></td>
	    <td width="35" align="center" ><bean:write name="row" property="nuPosicaoanterior" /></td>
	</tr>
	</logic:iterate>
</table>
