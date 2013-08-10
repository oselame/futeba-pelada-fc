<!-- pfcTemplatesMenuPrincipal.jsp -->
<%@ include file="/includeTAGS.jsp"%>
<c:set var="context" value="${pageContext.request.contextPath}" scope="application"/>
<link rel="stylesheet" href="${context}/css/ltchtml.css" type="text/css" /> 
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="menuFundo" >
	<tr valign="bottom">
		<td align="center">&nbsp;</td>
		<logic:equal name="USUARIOLOGADO" value="true">
			<logic:equal name="TIPOUSUARIO" value="1">
				<td align="center"><a href="${context}/login.do"><img src="${context}/imagens/menu/adm.gif" border="0"></a></td>
			</logic:equal>
		</logic:equal>		
		<td align="center"><a href="${context}/abrirConClubeHistoria.do"><img src="${context}/imagens/menu/oclube.gif" border="0"></a></td>
		<td align="center"><a href="${context}/abrirSociosInicio.do"><img src="${context}/imagens/menu/socios.gif" border="0"></a></td>
		<td align="center"><a href="${context}/abrirRankingQuadrimestre.do"><img src="${context}/imagens/menu/ranking.gif" border="0"></a></td>
		<td align="center"><a href="${context}/abrirConJogosQuadrimestre.do"><img src="${context}/imagens/menu/jogos.gif" border="0"></a></td>
		<td align="center"><a href="${context}/abrirConFotos.do"><img src="${context}/imagens/menu/fotos.gif" border="0"></a></td>
		<td align="center"><a href="${context}/abrirConGaleriacampeoes.do"><img src="${context}/imagens/menu/galeria.gif" border="0"></a></td>
		<td align="center"><a href="${context}/abrirConEventos.do"><img src="${context}/imagens/menu/eventos.gif" border="0"></a></td>
		<td align="center"><a href="${context}/abrirConNoticias.do"><img src="${context}/imagens/menu/noticias.gif" border="0"></a></td>
		<td width="3%" align="center">&nbsp;</td>
	</tr>
</table>

