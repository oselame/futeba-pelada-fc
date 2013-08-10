<!-- /cabecalhoFrame.jsp -->
<%@ include file="pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<script src="${urlJS}/verificaCampos.js"></script>
<script>
	function logoff() {
		parent.document.location.href="logoff.jsp";
	}
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="52">
			<a href="home/inicio.do"><img src="${context}/imagens/logoPelada.gif"
					width=52 height=53 border="0">
			</a>
		</td>
		<td>
			<a href="inicio.do"><img src="${context}/imagens/desPelada.gif" width=312
					height=26 border="0">
			</a>
		</td>
		<td width="300" align="right" valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right">
						<a href="mailto:${emailgroups}">
							<img src="${context}/imagens/faleConosco.jpg" width=129 height=16 border="0">
						</a>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						<table cellpadding="0" cellspacing="0">
						<tr>
							<td><strong>Usu&aacute;rio:</strong>&nbsp;${NMAPELIDO}&nbsp;</td>
							<td><a href="javascript:logoff()"><img alt="Sair" src="${context}/imagens/icoSair.jpg" border="0" height="20" width="20"></a></td>
							<td>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="fundoBanner" >
	<tr>
		<td width="1%">
			&nbsp;
		</td>
		<td width="14%">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100" class="banner">
						<img src="../fotos/banners/${Constantes.SESSION_BANNER+'0'}" width="100" height="44">
					</td>
					<td width="5">
						<img src="${context}/imagens/ladoBannerEsq.jpg" width="5" height="46">
					</td>
				</tr>
			</table>
		</td>
		<td width="14%">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100" class="banner">
						<img src="/fotos/banners/${Constantes.SESSION_BANNER+'1'}" width="100" height="44">
					</td>
					<td width="5">
						<img src="${context}/imagens/ladoBannerEsq.jpg" width="5" height="46">
					</td>
				</tr>
			</table>
		</td>
		<td width="14%">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100" class="banner">
						<img src="/fotos/banners/${Constantes.SESSION_BANNER+'2'}" width="100" height="44">
					</td>
					<td width="5">
						<img src="${context}/imagens/ladoBannerEsq.jpg" width="5" height="46">
					</td>
				</tr>
			</table>
		</td>
		<td width="14%">
			<table height="46" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100" class="banner">
						<img src="/fotos/banners/${Constantes.SESSION_BANNER+'3'}" width="100" height="44">
					</td>
				</tr>
			</table>
		</td>
		<td width="14%">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="5">
						<img src="${context}/imagens/ladoBannerDir.gif" width="5" height="46">
					</td>
					<td width="100" class="banner">
						<img src="/fotos/banners/${Constantes.SESSION_BANNER+'4'}" width="100" height="42">
					</td>
				</tr>
			</table>
		</td>
		<td width="14%">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="5">
						<img src="${context}/imagens/ladoBannerDir.gif" width="5" height="46">
					</td>
					<td width="100" class="banner">
						<img src="/fotos/banners/${Constantes.SESSION_BANNER+'5'}" width="100" height="44">
					</td>
				</tr>
			</table>
		</td>
		<td width="14%">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="5">
						<img src="${context}/imagens/ladoBannerDir.gif" width="5" height="46">
					</td>
					<td width="100" class="banner">
						<img src="/fotos/banners/${Constantes.SESSION_BANNER+'6'}" width="100" height="44">
					</td>
				</tr>
			</table>
		</td>
		<td width="1%">
			&nbsp;
		</td>
	</tr>
</table>