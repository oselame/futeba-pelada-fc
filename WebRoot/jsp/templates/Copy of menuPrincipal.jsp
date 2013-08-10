<%@ include file="/pfcInclude.jsp"%>
<table width="100%"  border="0" cellpadding="0" cellspacing="0" background="../imagens/menuFundo.gif">
	<tr>
		<td align="center">&nbsp;</td>
		<logic:equal name="USUARIOLOGADO" value="true">
			<td align="center"><a href="../adm/inicio.do"><img src="../imagens/adm.gif" width=47 height=18 border="0"></a></td>
		</logic:equal>		
		<td align="center"><a href="../oclube/inicio.do"><img src="../imagens/oclube.gif" width=47 height=18 border="0"></a></td>
		<td align="center"><a href="../socios/inicio.do"><img src="../imagens/socios.gif" width=39 height=18 border="0"></a></td>
		<td align="center"><a href="../ranking/inicio.do"><img src="../imagens/ranking.gif" width=50 height=18 border="0"></a></td>
		<td align="center"><a href="../jogos/inicio.do"><img src="../imagens/jogos.gif" width="36" height="18" border="0"></a></td>
		<td align="center"><a href="../abrirConFotos.do"><img src="../imagens/fotos.gif" width=32 height=18 border="0"></a></td>
		<td align="center"><a href="../abrirConGaleriacampeoes.do"><img src="../imagens/galeria.gif" width=60 height=18 border="0"></a></td>
		<td align="center"><a href="../abrirConEventos.do"><img src="../imagens/eventos.gif" width=47 height=18 border="0"></a></td>
		<td align="center"><a href="../abrirConNoticias.do"><img src="../imagens/noticias.gif" border="0"></a></td>
		<td width="3%" align="center">&nbsp;</td>
	</tr>
</table>

