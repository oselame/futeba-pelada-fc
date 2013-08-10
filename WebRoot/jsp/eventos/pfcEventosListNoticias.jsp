<%@ include file="/pfcInclude.jsp"%>
<p><strong>Not&iacute;cias</strong></p>
<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC">
	<tr bgcolor="#efefef">
		<td>
			Data
		</td>
		<td>
			Notícias
		</td>
	</tr>
	<logic:iterate name="noticiaForm" property="rows" id="row">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td width="80"><bean:write name="row" property="dtNoticiastring" /></td>
			<td>
				<a href="visualizarNoticias.do?noticiaPK.cdNoticia=<bean:write name='row' property='noticiaPK.cdNoticia'/>">
					<bean:write name="row" property="deTitulo" />
				</a>
		</tr>
	</logic:iterate>
</table>
<p align="right">
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">
			<html:button value="Novo" property="bntNovo" styleClass="bN" 
			   onclick="location.href='abrirCadNoticia.do'" />
		</logic:equal>
	</logic:equal>
</p> 

