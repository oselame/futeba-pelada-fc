<!-- pfcRankingConsulta.jsp -->
<%@page import="br.com.softal.pfc.Constantes"%>
<%@ include file="/pfcInclude.jsp"%>

<script>
	function changeQuadrimestre(value) {
		var nuAno = document.classificacaoForm["entidade.nuAno"].value;
		//--var cdQuadrimestre = '${classificacaoForm.entidade.cdQuadrimestre}';
		PfcDwr.getDatasquadrimestre(nuAno, value, changeQuadrimestreRetorno);
	}
	function changeQuadrimestreRetorno(retorno) {
		var combo = document.getElementById('entidade.cdPartida');
		for(x=combo.options.length; x>1; x--) combo.remove(x - 1);
		DWRUtil.addOptions('entidade.cdPartida', retorno);		
	}
</script>

<p><strong>Consulta de Ranking</strong></p>
<html:form action="/abrirConRanking.do" method="POST">
<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
	<tr bgcolor="#CCCCCC">
		<td bgcolor="#efefef">Consulta</td>
	</tr>
</table>
<br>
<table width="100%"  border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td width="150" align="right">Ano : </td>
		<td>
			<html:select name="classificacaoForm" property="entidade.nuAno" style="width:100% ">
				<html:options collection="<%=Constantes.SESSION_LISTA_ANOS%>" property="cdCodigo" labelProperty="deDescricao"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td align="right">Quadrimestre: </td>
		<td>			
		<html:radio name="classificacaoForm" property="entidade.cdQuadrimestre" value="1" onclick="changeQuadrimestre(this.value);" />
			1&nbsp;&nbsp;&nbsp;&nbsp;
			<html:radio name="classificacaoForm" property="entidade.cdQuadrimestre" value="2" onclick="changeQuadrimestre(this.value);" />
			2 &nbsp;&nbsp;&nbsp;&nbsp;
			<html:radio name="classificacaoForm" property="entidade.cdQuadrimestre" value="3" onclick="changeQuadrimestre(this.value);" />
			3 &nbsp;&nbsp;&nbsp; 
			<html:radio name="classificacaoForm" property="entidade.cdQuadrimestre" value="4" onclick="changeQuadrimestre(this.value);" />
			Anual &nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td align="right">Partida: </td>
		<td>
			<html:select name="classificacaoForm" property="entidade.cdPartida" styleId="entidade.cdPartida">
				<html:option value="">---- Todos ----</html:option>
				<html:options collection="<%=Constantes.SESSION_LISTA_PARTIDAS_QUADRIMESTRE %>" property="partidaPK.cdPartida" labelProperty="dtPartidaformatada"/>
			</html:select>									
		</td>
	</tr>	
	<tr>
		<td align="right">&nbsp; </td>
		<td align="right">			
			<html:submit value="Consultar" property="bntConsultar" styleClass="bN" />
		</td>
	</tr>
</table>
</html:form>
<br>
