
<%@page import="br.com.softal.pfc.dto.Logo"%><!-- pfcTemplatesCabecalho.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" scope="page"/>
<%@page import="br.com.softal.pfc.Constantes"%>
<link rel="stylesheet" href="${context}/css/ltchtml.css" type="text/css" />
<script src="${context}/js/verificaCampos.js"></script>
<script src="${context}/js/PfcUtil.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="52">
			<a href="/inicio.do"><img src="${context}/imagens/logoPelada.gif"
					width="52" height="53" border="0">
			</a>
		</td>
		<td valign="middle">
			<a href="inicio.do"><img src="${context}/imagens/desPelada.gif" border="0">
			</a>
		</td>
		<td align="right" valign="top" width="250">
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
							<td><strong>Usu&aacute;rio:</strong>&nbsp;<%=request.getSession().getAttribute(Constantes.SESSION_NMAPELIDO) %>&nbsp;</td>
							<td><a href="${context}/logoff.do"><img alt="Sair" src="${context}/imagens/icoSair.jpg" border="0" height="20" width="20"></a></td>
							<td>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="fundoBanner">
	<tr>
		<td width="1%">
			&nbsp;
		</td>
		<td width="14%">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100" class="banner">
						<img src="/fotos/banners/<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"0"))).getNmLogo()%>" 
							width="100" height="44"
							onclick="PfcUtil.abrirLink('<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"0"))).getNmLink()%>')"
							class="banner">
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
						<img src="/fotos/banners/<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"1"))).getNmLogo()%>" 
							width="100" height="44"
							onclick="PfcUtil.abrirLink('<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"1"))).getNmLink()%>')"
							class="banner">
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
						<img src="/fotos/banners/<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"2"))).getNmLogo()%>" 
							width="100" height="44"
							onclick="PfcUtil.abrirLink('<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"2"))).getNmLink()%>')"
							class="banner">
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
						<img src="/fotos/banners/<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"3"))).getNmLogo()%>" 
							width="100" height="44"
							onclick="PfcUtil.abrirLink('<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"3"))).getNmLink()%>')"
							class="banner">
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
						<img src="/fotos/banners/<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"4"))).getNmLogo()%>" 
							width="100" height="44"
							onclick="PfcUtil.abrirLink('<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"4"))).getNmLink()%>')"
							class="banner">
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
						<img src="/fotos/banners/<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"5"))).getNmLogo()%>" 
							width="100" height="44"
							onclick="PfcUtil.abrirLink('<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"5"))).getNmLink()%>')"
							class="banner">
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
						<img src="/fotos/banners/<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"6"))).getNmLogo()%>" 
							width="100" height="44"
							onclick="PfcUtil.abrirLink('<%=((Logo)request.getAttribute((Constantes.SESSION_BANNER+"6"))).getNmLink()%>')"
							class="banner">
					</td>
				</tr>
			</table>
		</td>
		<td width="1%">
			&nbsp;
		</td>
	</tr>
</table>