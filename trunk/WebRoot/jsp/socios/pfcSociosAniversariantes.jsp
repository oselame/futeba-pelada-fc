<!-- pfcSociosAniversariantes.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html>
	<head>
		<title>PeladaFC</title>
		<script type="text/javascript">
		<!--
			function dadosSocio(cdsocio) {
				var url = "abrirSociosShowSocio.do?entidade.socioPK.cdSocio=" + cdsocio;
				document.location.href = url;
			}
			
			function consultar(valor) {
				var url = "abrirSocioAniversariantes.do?entidade.nuMesnascimento=" + valor;
				document.location.href = url;
			}
		//-->
		</script>
	</head>

	<body>
		<p>
			<strong>Aniversariantes do m&ecirc;s de </strong>
			<tag:Select name="socioForm" property="entidade.nuMesnascimento" style="width:130"
				rows="mesCombo" rowId="cdCodigo" rowLabel="deDescricao" 
				onchange="javascript:consultar(this.value);" 
				showSelecione="false"/>
		</p>
		<table width="100%" border="0" cellpadding="1" cellspacing="1"
			bgcolor="#CCCCCC">
			<tr bgcolor="#efefef">
				<td width="150">
					Apelido
				</td>
				<td>
					Nome
				</td>
				<td width="120" align="center">
					Data Nascimento
				</td>
			</tr>
			<logic:iterate id="row" property="rows" name="socioForm">
				<tr>
					<td bgcolor="#FFFFFF">
						<logic:equal name="USUARIOLOGADO" value="true">
							<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
								<bean:write name="row" property="nmApelido" /> 
							</a>
						</logic:equal>
						<logic:notEqual name="USUARIOLOGADO" value="true">
							<bean:write name="row" property="nmApelido" />
						</logic:notEqual>
					</td>
					<td bgcolor="#FFFFFF">
						<bean:write name="row" property="nmSocio"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<bean:write name="row" property="dtNascimentostring"/>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</body>
</html>
