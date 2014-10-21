<!-- pfcTemplatesConteudoDefault.jsp -->
<%@ include file="/includeTAGS.jsp"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld" %>
<c:set var="context" value="${pageContext.request.contextPath}" scope="application"/>

<html>
<head>
	<title>PeladaFC</title>
	<style type="text/css">
		<!--
		.banner {	border: 1px solid #CCCCCC;
			background-color: #FFFFFF;
		}
		.style1 {color: #666666}
		-->
	</style>
	<link rel="stylesheet" href="${context}/css/HTML.css" type="text/css">
	<link rel="stylesheet" href="${context}/css/ltchtml.css" type="text/css" />
</head>
<body bgcolor=#FFFFFF>
	<div >
		<!-- ################################################################################### -->
		<!-- ################################# CABECALHO ####################################### -->
		<!-- ################################################################################### -->
		<tiles:insert attribute="cabecalho"/>
		<!-- ################################################################################### -->
		<!-- ################################# FIM CABECALHO ################################### -->
		<!-- ################################################################################### -->
	</div >
	<div>
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="superiorFundo"><img src="${context}/imagens/superiorFundo.gif" width=17 height=12></td>
			</tr>
			</table>
			<table width="100%" class="menuFundo"  border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<!-- ################################################################################### -->
						<!-- ################################# MENU PRINCIPAL ################################## -->
						<!-- ################################################################################### -->
						<jsp:include page="/jsp/templates/pfcTemplatesMenuPrincipal.jsp"></jsp:include>
						<!-- ################################################################################### -->
						<!-- ################################# FIM MENU PRINCIPAL ############################## -->
						<!-- ################################################################################### -->			
					</td>
					<td width="202"><img src="${context}/imagens/menuFinal2.gif" width=202 height=18></td>
				</tr>
			</table>
	</div>
	<div>
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="bottom" class="campoFundo2">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		            	<tr>
		            		<td width="15%">&nbsp;</td>
		            		<td valign="bottom">
								<!-- ################################################################################### -->
								<!-- ################################# TITULO ########################################## -->
								<!-- ################################################################################### -->
		            			<tiles:insert attribute="titulo"/>
								<!-- ################################################################################### -->
								<!-- ################################# FIM TITULO ###################################### -->
								<!-- ################################################################################### -->
							</td>
		           		</tr>
		           	</table>
				</td>
				<td width="271"><img src="${context}/imagens/campoFinal2.jpg" width=271 height=51></td>
			</tr>
		</table>
	</div>
	<div>
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>&nbsp;</td>
				<td width="319"><img src="${context}/imagens/embaixo.gif" width=319 height=18></td>
			</tr>
		</table>	
	</div>
	<div style="height: auto;">
		<div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
			   	<td valign="top">
					<table width="100%" height="235"  border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="50">&nbsp;</td>
							<td valign="top">
								<br>
								<!-- ################################################################################### -->
								<!-- ################################# CONTEUDO ######################################## -->
								<!-- ################################################################################### -->
								<tiles:insert attribute="conteudo"/>
								<!-- ################################################################################### -->
								<!-- ################################# FIM CONTEUDO #################################### -->
								<!-- ################################################################################### -->
							</td>
							<td width="50" valign="top">&nbsp;</td>
							<td width="200" valign="top">
								<!-- ################################################################################### -->
								<!-- ################################# MENU ############################################ -->
								<!-- ################################################################################### -->
								<tiles:insert attribute="menu"/>
								<!-- ################################################################################### -->
								<!-- ################################# FIM MENU ######################################## -->
								<!-- ################################################################################### -->
							</td>
						</tr>
					</table>
				</td>
				</tr>
			</table>		
		</div>
		<div >
			<tiles:insert attribute="rodape"/>
		</div>
	</div>
</body>
</html>