<!-- /home/incEventos.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<logic:notEmpty name="<%=Constantes.SESSION_LISTA_EVENTOS %>">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><strong style="font-size: 12"><img src="../imagens/icoevento.jpg"  width="19" height="22">Eventos</strong></td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<logic:iterate name="<%=Constantes.SESSION_LISTA_EVENTOS %>" id="evento">
					<tr>
						<td width="10" valign="top">
							<img src="../imagens/icoBola.gif">
						</td>
						<td>
							<a href="javascript:visualizarEvento(${evento.noticiaPK.cdNoticia});"><bean:write name="evento" property="deTitulo" /></a>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</td>
	</tr>
	</table>
</logic:notEmpty>