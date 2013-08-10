<!-- pfcGaleriaListGalerias.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<%@page import="br.com.softal.pfc.Quadrimestre"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"   prefix="bean"%>
<p><strong>Galeria dos Campeões</strong></p>
<table border="0" cellpadding="1" cellspacing="1"	bgcolor="#CCCCCC">
	<% 
		String nuAno = ""; 
	%>
<logic:iterate id="row" property="rows" name="quadrimestreForm">
	<% if (!((Quadrimestre) row).getQuadrimestrePK().getNuAno().toString().equalsIgnoreCase(nuAno)) { 
		nuAno = ((Quadrimestre) row).getQuadrimestrePK().getNuAno().toString();
	%>
	<tr bgcolor="#efefef">
		<td colspan="3">
			<b><bean:write name="row" property="quadrimestrePK.nuAno"/></b>
		</td>
	</tr>
	<%} %> 
	<tr class="pfcCampoTexto" valign="top">
		<td width="140">
			<logic:equal name="row" property="quadrimestrePK.cdQuadrimestre" value="4">
				Anual
			</logic:equal>
			<logic:notEqual name="row" property="quadrimestrePK.cdQuadrimestre" value="4">
				<bean:write name="row" property="quadrimestrePK.cdQuadrimestre"/>º Quadrimestre
			</logic:notEqual>
		</td>
		<td width="140">
			<bean:write name="row" property="socio.nmApelido"/>
		</td>
		<td>
			<img src="/abrirSocioImagemSocio.do?socioPK.cdSocio=${row.socio.socioPK.cdSocio}">
		</td>
	</tr>
</logic:iterate>

	<tr bgcolor="#efefef">
		<td colspan="3">
			<b>Campeões Anos Anteriores </b>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td colspan="3">
			<c:if test="${not empty nmArqCampeosAnterior}">
				<a target="_blank" href="../fotos/campeoes/${nmArqCampeosAnterior}">
					Galeria
				</a>
			</c:if>
		</td>
	</tr>
		
</table>

