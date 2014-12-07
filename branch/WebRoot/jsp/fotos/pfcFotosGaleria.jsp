<!-- pfcFotosGaleria.jsp -->
<%@page import="br.com.softal.pfc.Constantes"%>
<form action="../../fotos/index.jsp" target="_blank" method="post" name="frmTemp">
<input type="hidden" name="cdGaleria" value="${Constantes.SESSION_CODIGO_GALERIA}">
<script>
	//location.href="../../fotos/index.jsp?cdGaleria=${Constantes.SESSION_CODIGO_GALERIA}";
	document.forms[0].submit();
</script>
</form>