<!-- pfcJogosConsulta.jsp -->
<%@page import="br.com.softal.pfc.Constantes"%>
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<p>
	<strong>Consulta de Jogos</strong>
</p>
<html:form action="/abrirConsultaJogos.do" method="POST">
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				Consulta
			</td>
		</tr>
	</table>
	<br>
	<table width="100%" border="0" cellspacing="0" cellpadding="2">
		<tr>
			<td width="150" align="right">
				Ano :
			</td>
			<td>
				<tag:Select name="partidaForm" property="entidade.nuAno" style="width:160"
							rows="<%=Constantes.SESSION_LIST_ANOS %>" rowId="cdCodigo" rowLabel="deDescricao" showSelecione="false" />				
			</td>
		</tr>
		<tr>
			<td align="right"> 
				Quadrimestre:
			</td>
			<td>
				<logic:iterate name="<%=Constantes.SESSION_LIST_QUADRIMESTRES %>" id="i">
					<html:radio name="partidaForm" property="entidade.cdQuadrimestre" value="${i.quadrimestrePK.cdQuadrimestre}" />${i.quadrimestrePK.cdQuadrimestre}&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:iterate>
				
			</td>
		</tr>
		<tr>
			<td align="right">
				&nbsp;
			</td>
			<td align="right">
				<html:submit value="Consultar" property="bntConsultar" styleClass="bN" />
			</td>
		</tr>
	</table>
</html:form>
<br>
