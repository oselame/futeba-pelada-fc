<!-- pfcRelatorioAtrasos.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<link type="text/css" rel="stylesheet" href="${urlCSS}/blue/green.css" >
<script>
	jQuery(document).ready(function(){
		jQuery(".tablesorter").tablesorter({
			widgets: ['zebra']
		});
	});
</script>
<table width="100%" border="0" cellpadding="0" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr bgcolor="#CCCCCC">
		<td bgcolor="#efefef">
			Relatório de Atrasos
		</td>
	</tr>
</table>
<br>
<html:form action="/consultarRelatorioAtrasos" >
	<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td valign="top" align="right" width="80">Ano*:</td>
		<td>
			<tag:Number name="relatorioForm" property="entidade.nuAno" style="width:80" formato="4"/> (yyyy)
		</td>
	</tr>
	<tr>
		<td valign="top" align="right" width="80">Mês:</td>
		<td>
			<tag:Select name="relatorioForm" property="entidade.nuMes" style="width:130"
				rows="mesCombo" rowId="cdCodigo" rowLabel="deDescricao" 
				showSelecione="true"/>
		</td>
	</tr>
	<tr>
		<td valign="top" align="right" width="80">Peladeiro:</td>
		<td>
			<tag:Select name="relatorioForm" property="entidade.cdSocio" style="width:320"
				rows="listSocios" rowId="cdCodigo" rowLabel="deDescricao" 
				showSelecione="true"/>
		</td>
	</tr>
	<tr>
		<td align="right" colspan="2">
			<html:submit value="Consultar" property="bntSalvar" 
				styleClass="bH" onclick="return verificaObrigatorios();"/>
			<html:button value="Imprimir" property="bntImprimir" 
				styleClass="bN" onclick="imprimeRelatorio();"/>
		</td>
	</tr>
	</table>
	
	<logic:iterate id="row" property="entidade.socios" name="relatorioForm">
		<b>Mês: ${row.dsMes}/${relatorioForm.entidade.nuAno}	</b>
		<table border="0" cellpadding="0" cellspacing="0" width="100%" class="tablesorter">
		<thead>
			<tr>
				<th class="header" width="40" align="center">Seq.</th>
				<th class="header" width="100" align="center">Data</th>
				<th class="header" width="100" align="center">Placar</th>
				<th class="header">Atrasados</th>
			</tr>
		</thead>
		<tbody>
				<logic:iterate id="row2" property="socios" name="row" indexId="idx">
				<tr>
					<td align="center">${idx+1}</td>
					<td align="center">
						${row2.dtPartidastring}
					</td>
					<td align="center">
						${row2.nuGolvencedor} x ${row2.nuGolperdedor}
					</td>
					<td>
						${row2.nmApelido}
					</td>
				</tr>
				</logic:iterate>
		</tbody>
		</table>
	</logic:iterate>	
</html:form>
<script>
    function imprimeRelatorio() {
    	if (verificaObrigatorios()) {
    		document.relatorioForm.action="${pageContext.request.contextPath}/imprimirRelatorioAtrasos.do";
    		document.relatorioForm.submit();
    	}
    }
    
	function verificaObrigatorios() {
		document.relatorioForm.action="${pageContext.request.contextPath}/consultarRelatorioAtrasos.do";
		if (document.relatorioForm["entidade.nuAno"].value == "") {
			alert("Ano não informado!");
			document.relatorioForm["entidade.nuAno"].focus();
			return false;
		} else {
			return true;
		}
	}
</script> 