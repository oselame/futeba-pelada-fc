<!-- pfcQuadrimestreListQuadrimestres.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<p><strong>Quadrimestres</strong></p>
<script>
	function editar(valor) {
		var url = "editarCadQuadrimestre.do?nuAno=" + valor;
		document.location.href=url;
	}
</script>
<html:form action="abrirConQuadrimestre.do">
<table width="100%" border="0" cellpadding="1" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr bgcolor="#efefef">
		<td width="40">
			Ano
		</td>
		<td width="90">
			Quadrimestre
		</td>
		<td>
			Data
		</td>
	</tr>
	<logic:iterate id="row" property="rows" name="quadrimestreForm">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td  width="25">
				<logic:equal name="USUARIOLOGADO" value="true">
					<logic:equal name="TIPOUSUARIO" value="1">				
						<a href="javascript:editar(${row.quadrimestrePK.nuAno});">
							<bean:write name="row" property="quadrimestrePK.nuAno" />
						</a>
					</logic:equal>
				</logic:equal>
				<logic:notEqual name="USUARIOLOGADO" value="true">
					<logic:equal name="TIPOUSUARIO" value="1">	
						<bean:write name="row" property="quadrimestrePK.nuAno" />
					</logic:equal>
				</logic:notEqual>
			</td>
			<td align="center">
				<bean:write name="row" property="quadrimestrePK.cdQuadrimestre" />
			</td>
			<td>
				<bean:write name="row" property="dtIniciostring" /> até <bean:write name="row" property="dtFimstring" />
			</td>
		</tr>
	</logic:iterate>
</table>

	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">	
			<p align="right">
				<html:button property="Novo" value="Novo" styleClass="bN" onclick="location.href='abrirCadQuadrimestre.do'" />
			</p>
		</logic:equal>
	</logic:equal>

</html:form>