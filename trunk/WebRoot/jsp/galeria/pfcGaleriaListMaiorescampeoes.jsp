<!-- pfcGaleriaListMaiorescampeoes.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Quadrimestre"%>
<table width="100%" border="0" cellpadding="1" cellspacing="1"	bgcolor="#CCCCCC">
	<tr bgcolor="#efefef">
		<td colspan="3">
			<b>Maiores Campeões</b>
		</td>
	</tr>
	<logic:iterate id="row" property="rows" name="quadrimestreForm">
		<tr class="pfcCampoTexto" valign="top">
			<td width="140">
				<bean:write name="row" property="socio.nmApelido"/>
			</td>
			<td width="100">
				<bean:write name="row" property="nuTitulos"/> Títulos				
			</td>
			<td>
				<logic:iterate id="rowx" property="quadrimestres" name="row">
					<bean:write name="rowx" property="deQuadrimestre"/><br>
				</logic:iterate>
			</td>
		</tr>
	</logic:iterate>	
</table>