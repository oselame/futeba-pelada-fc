<!-- pfcHomeInicio.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html>
	<head>
		<title>PeladaFC</title>
		<style type="text/css">
<!--
.banner {
	border: 1px solid #CCCCCC;
	background-color: #FFFFFF;
}

.campoFundo {
	background-attachment: fixed;
	background-image: url(imagens/campoPrincipal.jpg);
	background-repeat: no-repeat;
	background-position: right;
}

.campo {
	width: 100%;
	border: 1px solid #70DA2F;
}
-->
</style>
		<link href="../css/HTML.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
.style1 {
	color: #666666
}

.textoRanking {
	font-size: 10px;
	color: #666666;
	background-image: url(../imagens/rankingDes.gif);
}

.legenda {
	font-size: 9px;
	color: #999999;
}

.linhaLegenda {
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 1px;
	border-left-width: 0px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #CCCCCC;
	border-right-color: #CCCCCC;
	border-bottom-color: #CCCCCC;
	border-left-color: #CCCCCC;
	font-size: 10px;
	color: #666666;
}

.style3 {
	color: #FF9900
}
-->
</style>
<link rel="stylesheet" href="../css/ltchtml.css" type="text/css" />
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
		   <tr>
		   		<td height="52px">
					<%@ include file="../templates/cabecalho.jsp"%>
					<%--<jsp:include page="../templates/cabecalho.jsp" />
				--%></td>
			<tr>
				<td height="89px">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" background="../imagens/campoFundo.gif">
						<tr>
							<td class="campoFundo">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="9%">
											&nbsp;
										</td>
										<td>
											${DESCRITIVO_SITE}								
										</td>
										<td width="9%">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="295" background="../imagens/campoDir.jpg" width="295" height="89" align="right">
								<%@ include file="../home/incLogin.jsp"%>
								<%--<jsp:include page="../home/incLogin.jsp" />
							--%></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="18px">			
					<table width="100%" border="0" cellpadding="0" cellspacing="0" background="../imagens/menuFundo.gif">
						<tr>
							<td>
								<%@ include file="../templates/menuPrincipal.jsp"%>
								<%--<jsp:include page="../templates/menuPrincipal.jsp" />
							--%></td>
							<td width="263" background="../imagens/menuFinal.jpg" width="263" height="18">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		   <tr>
		   	<td height="100%" valign="top">
		   					<script>
					function visualizarEvento(valor) {
						document.location.href="/visualizarEventos.do?noticiaPK.cdNoticia=" + valor;
					}
					
					function visualizarNoticia(valor) {
						document.location.href="/visualizarNoticias.do?noticiaPK.cdNoticia=" + valor;
					}
					
				</script> 
		   	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="10" background="../imagens/noticiasFundo.gif">
										<img src="../imagens/noticiasFundo.gif" width=5 height=90>
									</td>
									<td valign="top" background="../imagens/noticiasFundo.gif">
											<%@ include file="../home/incNoticias.jsp"%>	
											<%--<jsp:include page="../home/incNoticias.jsp" />					
									--%></td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<td>
										<table border="0" width="100%" cellpadding="0" cellspacing="0">
											<tr>
												<td valign="top">
													<%@ include file="../home/incEventos.jsp"%>
													<%--<jsp:include page="../home/incEventos.jsp" />
												--%></td>
												<td width="2px">
													<img alt="" src="" width="10px">
												</td>
												<td width="48%">
													<%@ include file="../home/incUltimojogo.jsp"%>	
													<%--<jsp:include page="../home/incUltimojogo.jsp" />									
												--%></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td width="102" valign="top">
							<img src="../imagens/rankingFig.gif" width=102 height=118>
						</td>
						<td width="30%" valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="118" valign="top" background="../imagens/rankingFundo.jpg">
										<%@ include file="../home/incClassificacaoquadratual.jsp"%>
										<%--<jsp:include page="../home/incClassificacaoquadratual.jsp" />
									--%></td>
								</tr>
								<tr>
									<td>
										<br>
										<%@ include file="../home/incAniversariantes.jsp"%>
										<%--<jsp:include page="../home/incAniversariantes.jsp" />
									--%></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
		   	
		   	
		   	
		   	</td>
		   </tr>
		   <tr>
			   	<td height="10">
			   		<%@ include file="../templates/rodape.jsp"%>
			   		<%--<jsp:include page="../templates/rodape.jsp" />
			   	--%></td>
		   </tr>		   
		</table>		
	</body>
</html>
