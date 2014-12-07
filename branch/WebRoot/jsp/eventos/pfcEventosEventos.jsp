<!-- pfcEventoSeventos.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<style type="text/css">
<!--
.style1 {
	font-size: 9px;
	font-style: italic;
}
-->
</style>

<p>
	<strong>
		<bean:write name="noticiaForm" property="entidade.deTitulo" /><br>
	</strong>
	<span class="style1">
		<bean:write name="noticiaForm" property="entidade.dtFimeventostring" />
	</span>
	<logic:notEmpty name="noticiaForm" property="entidade.deLink">
		<br><a href="${noticiaForm.entidade.deLink}" target="_blank"><bean:write name="noticiaForm" property="entidade.deLink" /></a>
	</logic:notEmpty>	
</p>
<p> ${noticiaForm.entidade.deNoticia} </p>
<p align="right">
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">	
			<html:button value="Editar" property="bntEditar" styleClass="bN" 
			   onclick="location.href='editarCadEvento.do?cdNoticia=${noticiaForm.entidade.noticiaPK.cdNoticia}'" />
		</logic:equal>
	</logic:equal>
</p> 
