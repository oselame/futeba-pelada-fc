<!-- /home/incNoticias2.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
	<script type="text/javascript">
	function visualizarNoticia2(cdNoticia) {
		$("#dialog-noticia_" + cdNoticia).dialog({
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
	<style type="text/css">
		.noticia {
			padding-left: 15px;
			background-position: left;
			background-repeat: no-repeat;
			background-image: url("../imagens/icoBola.gif");
		}
	</style>
		<logic:iterate name="<%=Constantes.SESSION_LIST_NOTICIAS %>" id="noticia" indexId="idx">
			<div class="noticia">
				<div class="${noticia.styleClass}">
					<a href="javascript:visualizarNoticia2(${idx})">
						<bean:write name="noticia" property="dtNoticiastring" /> -
						<bean:write name="noticia" property="deTitulo" /></a>
				</div>
				<div id="dialog-noticia_${idx}" title="${noticia.deTitulo}" style="display:none;">
					<p> ${noticia.deNoticia} </p>
				</div>
			</div>
		</logic:iterate>
