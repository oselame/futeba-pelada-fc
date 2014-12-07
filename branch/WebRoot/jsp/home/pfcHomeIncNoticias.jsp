<!-- /home/incNoticias.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
	<script type="text/javascript">
	function visualizarNoticia2(cdNoticia) {
		$("#dialog-modal_" + cdNoticia).dialog({
			height: 300,
			width: 700,
			modal: true,
		 	buttons: { "Fechar": function() { $(this).dialog("close"); } } 
		});
	}

	<c:if test="${mostraPopupNoticia}">
		$(function() {
			visualizarNoticia2(0);
		});
	</c:if>	
	</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td><strong style="font-size: 12"><img src="${context}/imagens/iconoticia.jpg" width="19" height="22" >Noticias</strong></td>
</tr>
<tr>
	<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<logic:iterate name="<%=Constantes.SESSION_LIST_NOTICIAS %>" id="noticia" indexId="idx">
				<tr>
					<td width="10" valign="top">
						<img src="${context}/imagens/icoBola.gif">
					</td>
					<td>
						<a href="javascript:visualizarNoticia2(${idx})"><bean:write name="noticia" property="deTitulo" /></a>
						<div id="dialog-modal_${idx}" title="${noticia.deTitulo}" style="display:none;">
							<p> ${noticia.deNoticia} </p>
						</div>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</td>
</tr>
</table>
