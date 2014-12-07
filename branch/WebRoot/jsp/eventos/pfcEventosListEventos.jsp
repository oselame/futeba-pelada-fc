<!-- pfcEventosListEventos.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<p><strong>Eventos</strong></p>
<script>
	function visualizar(codigo) {
		document.location.href="visualizarEventos.do?noticiaPK.cdNoticia=" + codigo;
	}
</script>
<table width="100%" border="0" cellpadding="1" cellspacing="1"	bgcolor="#CCCCCC">
	<tr bgcolor="#efefef">
		<td>
			Data
		</td>
		<td>
			Evento
		</td>
	</tr>
	<logic:iterate name="noticiaForm" property="rows" id="row">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td width="80"><bean:write name="row" property="dtNoticiastring" /></td>
			<td>
				<a href="javascript:visualizar(<bean:write name='row' property='noticiaPK.cdNoticia'/>)">
					<bean:write name="row" property="deTitulo" />
				</a>
			</td>
		</tr>
	</logic:iterate>
</table>
<p align="right">
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">	
			<html:button value="Novo" property="bntNovo" styleClass="bN" 
			   onclick="location.href='abrirCadEvento.do'" />
		</logic:equal>
	</logic:equal>
</p> 

