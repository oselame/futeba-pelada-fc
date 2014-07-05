<!-- pfcSociosInicio.jsp -->
<%@ include file="/pfcInclude.jsp"%>

<h1>Lista de s&oacute;cios</h1>

<script type="text/javascript">
<!--
	function dadosSocio(cdsocio) {
		var url = "abrirSociosShowSocio.do?entidade.socioPK.cdSocio=" + cdsocio;
		document.location.href = url;
	}
//-->
</script>
	<p align="right">
		<logic:equal name="USUARIOLOGADO" value="true">
			<logic:equal name="TIPOUSUARIO" value="1">
				<html:button value="Novo" property="bntNovo" styleClass="bN" onclick="location.href='abrirCadSocio.do'" />
			</logic:equal>
		</logic:equal>
	</p>

	<!-- Socios Patrimonial -->	
	<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC">
	<tbody><thead><h3>Patrimônial</h3></thead></tbody>
	<tr bgcolor="#efefef">
		<td width="25">
			&nbsp;
		</td>
		<td>
			Apelido
		</td>
		<td width="300">
			Nome
		</td>
		<logic:equal name="USUARIOLOGADO" value="true">
			<td width="250">
				E-mail
			</td>
		</logic:equal>
		<td width="150">
			Time
		</td>
	</tr>
	<% int i = 1; %>
	<logic:iterate id="row" property="sociospatrimonial" name="socioForm">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td width="25" align="center"><%=i%>
			</td>
			<td>
				<logic:equal name="USUARIOLOGADO" value="true">
					<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
						<bean:write name="row" property="nmApelido" /> 
					</a>
				</logic:equal>
				<logic:notEqual name="USUARIOLOGADO" value="true">
					<bean:write name="row" property="nmApelido" />
				</logic:notEqual>
			</td>
			<td width="300">
				<logic:equal name="USUARIOLOGADO" value="true">
					<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
						<bean:write name="row" property="nmSocio" />
					</a>
				</logic:equal>
				<logic:notEqual name="USUARIOLOGADO" value="true">
					<bean:write name="row" property="nmSocio" />
				</logic:notEqual>
			</td>
			<logic:equal name="USUARIOLOGADO" value="true">
				<td width="250">
					<bean:write name="row" property="deEmail" />
				</td>	
			</logic:equal>		
			<td width="150">
				<bean:write name="row" property="nmTime" />
			</td>
		</tr>
		<%
		i++;
		%>
	</logic:iterate>
	</table>
	<br>

	<!-- Socios Preferencial -->	
	<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC">
	<tbody><thead><h3>Preferêncial</h3></thead></tbody>
	<tr bgcolor="#efefef">
		<td width="25">
			&nbsp;
		</td>
		<td>
			Apelido
		</td>
		<td width="300" title="Pontos">
			Nome
		</td>
		<logic:equal name="USUARIOLOGADO" value="true">
			<td width="250">
				E-mail
			</td>
		</logic:equal>		
		<td width="150" title="Pontos">
			Time
		</td>
	</tr>
	<% i = 1; %>
	<logic:iterate id="row" property="sociospreferencial" name="socioForm">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td width="25" align="center"><%=i%>
			</td>
			<td>
				<logic:equal name="USUARIOLOGADO" value="true">
					<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
						<bean:write name="row" property="nmApelido" /> 
					</a>
				</logic:equal>
				<logic:notEqual name="USUARIOLOGADO" value="true">
					<bean:write name="row" property="nmApelido" />
				</logic:notEqual>
			</td>
			<td width="300">
				<logic:equal name="USUARIOLOGADO" value="true">
					<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
						<bean:write name="row" property="nmSocio" />
					</a>
				</logic:equal>
				<logic:notEqual name="USUARIOLOGADO" value="true">
					<bean:write name="row" property="nmSocio" />
				</logic:notEqual>
			</td>
			<logic:equal name="USUARIOLOGADO" value="true">
				<td width="250">
					<bean:write name="row" property="deEmail" />
				</td>	
			</logic:equal>				
			<td width="150">
				<bean:write name="row" property="nmTime" />
			</td>
		</tr>
		<%
		i++;
		%>
	</logic:iterate>
	</table>
	<br>
	
	<!-- Socios Benemérito -->	
	<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC">
	<tbody><thead><h3>Benemérito</h3></thead></tbody>
	<tr bgcolor="#efefef">
		<td width="25">
			&nbsp;
		</td>
		<td>
			Apelido
		</td>
		<td width="300" title="Pontos">
			Nome
		</td>
		<logic:equal name="USUARIOLOGADO" value="true">
			<td width="250">
				E-mail
			</td>
		</logic:equal>		
		<td width="150" title="Pontos">
			Time
		</td>
	</tr>
	<% i = 1; %>
	<logic:iterate id="row" property="sociosbenemerito" name="socioForm">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td width="25" align="center"><%=i%>
			</td>
			<td>
				<logic:equal name="USUARIOLOGADO" value="true">
					<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
						<bean:write name="row" property="nmApelido" /> 
					</a>
				</logic:equal>
				<logic:notEqual name="USUARIOLOGADO" value="true">
					<bean:write name="row" property="nmApelido" />
				</logic:notEqual>
			</td>
			<td width="300">
				<logic:equal name="USUARIOLOGADO" value="true">
					<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
						<bean:write name="row" property="nmSocio" />
					</a>
				</logic:equal>
				<logic:notEqual name="USUARIOLOGADO" value="true">
					<bean:write name="row" property="nmSocio" />
				</logic:notEqual>
			</td>
			<logic:equal name="USUARIOLOGADO" value="true">
				<td width="250">
					<bean:write name="row" property="deEmail" />
				</td>	
			</logic:equal>				
			<td width="150">
				<bean:write name="row" property="nmTime" />
			</td>
		</tr>
		<%
		i++;
		%>
	</logic:iterate>
	</table>
	<br>
	
	<!-- Socios Avulsos -->	
	<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC">
	<tbody><thead><h3>Avulso</h3></thead></tbody>
	<tr bgcolor="#efefef">
		<td width="25">
			&nbsp;
		</td>
		<td>
			Apelido
		</td>
		<td width="300" title="Pontos">
			Nome
		</td>
		<logic:equal name="USUARIOLOGADO" value="true">
			<td width="250">
				E-mail
			</td>
		</logic:equal>		
		<td width="150" title="Pontos">
			Time
		</td>
	</tr>
	<% i = 1; %>
	<logic:iterate id="row" property="sociosavulso" name="socioForm">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td width="25" align="center"><%=i%>
			</td>
			<td>
				<logic:equal name="USUARIOLOGADO" value="true">
					<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
						<bean:write name="row" property="nmApelido" /> 
					</a>
				</logic:equal>
				<logic:notEqual name="USUARIOLOGADO" value="true">
					<bean:write name="row" property="nmApelido" />
				</logic:notEqual>
			</td>
			<td width="300">
				<logic:equal name="USUARIOLOGADO" value="true">
					<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
						<bean:write name="row" property="nmSocio" />
					</a>
				</logic:equal>
				<logic:notEqual name="USUARIOLOGADO" value="true">
					<bean:write name="row" property="nmSocio" />
				</logic:notEqual>
			</td>
			<logic:equal name="USUARIOLOGADO" value="true">
				<td width="250">
					<bean:write name="row" property="deEmail" />
				</td>	
			</logic:equal>			
			<td width="150">
				<bean:write name="row" property="nmTime" />
			</td>
		</tr>
		<%
		i++;
		%>
	</logic:iterate>
	</table>	
	
	<logic:equal name="USUARIOLOGADO" value="true">
	<br>
		<!-- Socios Fora de Uso -->	
		<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC">
		<tbody><thead><h3>Desligados</h3></thead></tbody>
		<tr bgcolor="#efefef">
			<td width="25">
				&nbsp;
			</td>
			<td>
				Apelido
			</td>
			<td width="300" title="Pontos">
				Nome
			</td>
			<logic:equal name="USUARIOLOGADO" value="true">
				<td width="250">
					E-mail
				</td>
			</logic:equal>			
			<td width="150" title="Pontos">
				Time
			</td>
		</tr>
		<% i = 1; %>
		<logic:iterate id="row" property="sociosforauso" name="socioForm">
			<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
				<td width="25" align="center"><%=i%>
				</td>
				<td>
					<logic:equal name="USUARIOLOGADO" value="true">
						<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
							<bean:write name="row" property="nmApelido" /> 
						</a>
					</logic:equal>
					<logic:notEqual name="USUARIOLOGADO" value="true">
						<bean:write name="row" property="nmApelido" />
					</logic:notEqual>
				</td>
				<td width="300">
					<logic:equal name="USUARIOLOGADO" value="true">
						<a href="javascript:dadosSocio('${row.socioPK.cdSocio}')">
							<bean:write name="row" property="nmSocio" />
						</a>
					</logic:equal>
					<logic:notEqual name="USUARIOLOGADO" value="true">
						<bean:write name="row" property="nmSocio" />
					</logic:notEqual>
				</td>
				<logic:equal name="USUARIOLOGADO" value="true">
					<td width="250">
						<bean:write name="row" property="deEmail" />
					</td>	
				</logic:equal>				
				<td width="150">
					<bean:write name="row" property="nmTime" />
				</td>
			</tr>
			<%
			i++;
			%>
		</logic:iterate>
		</table>
	</logic:equal>		

<p align="right">
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">
			<html:button value="Novo" property="bntNovo" styleClass="bN" onclick="location.href='abrirCadSocio.do'" />
		</logic:equal>
	</logic:equal>
</p>