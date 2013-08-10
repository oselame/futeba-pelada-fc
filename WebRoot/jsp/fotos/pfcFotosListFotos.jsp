<!-- pfcFotosListFotos.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<strong>Galeria de Fotos</strong>
<br><br>

<script>
	function visualizar(codigo) {
		document.frmTemp.cdGaleria.value = codigo;
		document.frmTemp.submit();
	}
	
	function editar(codigo) {
		document.location.href="editarCadFotos.do?galeriaPK.cdGaleria=" + codigo;
	}
</script>

<table width="100%" border="0" cellpadding="1" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr bgcolor="#efefef">
		<td width="25">
			&nbsp;
		</td>
		<td width="25">
			Data
		</td>
		<td>
			Galeria
		</td>
		<logic:equal name="USUARIOLOGADO" value="true">
			<logic:equal name="TIPOUSUARIO" value="1">
				<td width="40">
					&nbsp;
				</td>
			</logic:equal>
		</logic:equal>
	</tr>
	<%
	int i = 1;
	%>
	<logic:iterate id="row" property="rows" name="galeriaForm">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td width="25" align="center"><%=i%>
			<td width="80"><bean:write name="row" property="dtGaleriastring" /></td>
			<td>
				<logic:notEqual name="row" property="nuFotos" value="0">
					<a href="javascript:visualizar(<bean:write name='row' property='galeriaPK.cdGaleria'/>)">
						<bean:write name="row" property="deGaleria" />
					</a>
				</logic:notEqual>
				<logic:equal name="row" property="nuFotos" value="0">
					<bean:write name="row" property="deGaleria" />
				</logic:equal>				
			</td>
			<logic:equal name="USUARIOLOGADO" value="true">
				<logic:equal name="TIPOUSUARIO" value="1">
					<td width="40" align="center">
						<a href="javascript:editar(<bean:write name='row' property='galeriaPK.cdGaleria'/>)">
							Editar
						</a>					
					</td>
				</logic:equal>
			</logic:equal>
		</tr>
		<%
		i++;
		%>
	</logic:iterate>
</table>

<p align="right">
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">
			<html:button value="Novo" property="bntNovo" styleClass="bN" 
			   onclick="location.href='abrirCadFotos.do'" />
		</logic:equal>
	</logic:equal>
</p> 

<form action="../../fotos/index.jsp" target="_blank" method="post" name="frmTemp">

<input type="hidden" name="cdGaleria" value="${Constantes.SESSION_CODIGO_GALERIA}">
</form>

