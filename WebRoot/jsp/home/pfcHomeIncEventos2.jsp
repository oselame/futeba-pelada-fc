<!-- /home/incEventos2.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<script>
	function visualizarEvento2(cdNoticia) {
		$("#dialog-evento_" + cdNoticia).dialog({
			height: 280,
			width: 680,
			modal: true,
		 	buttons: { "Fechar": function() { $(this).dialog("close"); } } 
		});
	}

	<c:if test="${mostraPopupEvento}">
		$(function() {
			visualizarEvento2(0);
		});
	</c:if>	
</script>
<style type="text/css">
	.evento {
		padding-left: 15px;
		background-position: left;
		background-repeat: no-repeat;
		background-image: url("../imagens/icoBola.gif");
	}
</style>
<logic:iterate name="<%=Constantes.SESSION_LISTA_EVENTOS %>" id="evento" indexId="idx">
	<div class="evento">
		<a href="javascript:visualizarEvento2(${idx})">
			<div class="${evento.styleClass}">
				<bean:write name="evento" property="dtNoticiastring" /> -
				<bean:write name="evento" property="deTitulo" /></a>
			</div>
			<div id="dialog-evento_${idx}" title="${evento.deTitulo}" style="display:none;">
				<p> ${evento.deNoticia} </p>
			</div>
	</div>
</logic:iterate>
