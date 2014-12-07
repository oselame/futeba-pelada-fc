<!-- /home/pfcHomeInicio2.jsp -->
<%@page import="br.com.softal.pfc.Constantes"%>
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=8;FF=3;OtherUA=4" />
	<title>PeladaFC</title>

	<style type="text/css">
		.column { width: 33%; float: left; padding-bottom: 100px;  }
		.portlet { margin: 0 1em 1em 0; }
		.portlet-header { margin: 0.3em; padding-bottom: 4px; padding-left: 0.2em; }
		.portlet-header .ui-icon { float: right; }
		.portlet-content { padding: 0.4em; }
		.ui-sortable-placeholder { border: 1px dotted black; visibility: visible !important; height: 50px !important; }
		.ui-sortable-placeholder * { visibility: hidden; }
		
		/*.ui-dialog-content .ui-widget-content {
			overflow: scroll;
			height: 400px;
			max-height: 400px;
			min-height: 400px;
		}*/
		.ui-dialog .ui-dialog-content { 
			border: 0; 
			overflow: scroll; 
			zoom: 1; 
			height: 500px !important;		
		}
		
		.socio {
			padding-left: 15px;
			background-position: left;
			background-repeat: no-repeat;
			background-image: url("../imagens/icoBola.gif");
		}	
		.evento {
			padding-left: 15px;
			background-position: left;
			background-repeat: no-repeat;
			background-image: url("../imagens/icoBola.gif");
		}		
		.noticia {
			padding-left: 15px;
			background-position: left;
			background-repeat: no-repeat;
			background-image: url("../imagens/icoBola.gif");
		}			
	</style>

<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(function() {
			jQuery(".column").sortable({
				connectWith: '.column'
			});
	
			jQuery(".portlet").addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
				.find(".portlet-header")
					.addClass("ui-widget-header ui-corner-all")
					.prepend('<span class="ui-icon ui-icon-minusthick"></span>')
					.end()
				.find(".portlet-content");
	
			jQuery(".portlet-header .ui-icon").click(function() {
				jQuery(this).toggleClass("ui-icon-minusthick").toggleClass("ui-icon-plusthick");
				jQuery(this).parents(".portlet:first").find(".portlet-content").toggle();
			});
	
			jQuery(".column").disableSelection();
			
		});
		
	});

	function visualizarEvento2(cdNoticia) {
		var width = jQuery(window).width();
		var height = jQuery(window).height();
		jQuery("#dialog-evento_" + cdNoticia).dialog({
			bgiframe: true,
			height: (height - 100),
			maxHeight: (height - 100),
			width: (width - 100),
			maxWidth: (width - 100),
			modal: true,
			resizable: false,
		 	buttons: { "Fechar": function() { jQuery(this).dialog("close"); } }
		});
	}

	function visualizarNoticia2(cdNoticia) {
		var width = jQuery(window).width();
		var height = jQuery(window).height();
		jQuery("#dialog-noticia_" + cdNoticia).dialog({
			bgiframe: true,
			height: (height - 100),
			maxHeight: (height - 100),
			width: (width - 100),
			maxWidth: (width - 100),
			modal: true,
			resizable: false,
		 	buttons: { "Fechar": function() { jQuery(this).dialog("close"); } }
		});
	}

	<c:if test="${mostraPopupNoticia}">
		jQuery(function() {
			visualizarNoticia2(${nuNoticiapopup});
		});
	</c:if>	

	<c:if test="${mostraPopupEvento}">
		jQuery(function() {
			visualizarEvento2(${nuEventopopup});
		});
	</c:if>	
	
	</script>

	<!--[if IE]>
    	<style type="text/css">
			.column { 
				width: 32%; 
				float: left; 
				padding-bottom: 100px;			
			}
    	</style
	<![endif]-->
	
	<script src="${context}/js/PfcUtil.js"></script>

	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		   <tr>
		   		<td height="52px">
					<%@ include file="/jsp/templates/pfcTemplatesCabecalho.jsp" %>
				</td>
			<tr>
				<td height="89px">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="campoFundo">
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
							<td width="295" class="campoDir"  width="295" height="89" align="right">
								<%@ include file="/jsp/home/pfcHomeIncLogin.jsp" %>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="18px">			
					<table width="100%" class="menuFundo" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<%@ include file="/jsp/templates/pfcTemplatesMenuPrincipal.jsp" %>
							</td>
							<td width="263" class="menuFinal" width="263" height="18">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br/>
		
		<div >
			<div class="column">
				<!-- NOTICIAS -->
				<div class="portlet">
					<div class="portlet-header">
						Notícias
					</div>
					<div class="portlet-content">
						<logic:iterate name="<%=Constantes.SESSION_LIST_NOTICIAS %>" id="noticia" indexId="idx">
							<div class="noticia">
								<div class="${noticia.styleClass}">
									<a href="javascript:visualizarNoticia2(${noticia.noticiaPK.cdNoticia})">
										<bean:write name="noticia" property="dtNoticiastring" /> -
										<bean:write name="noticia" property="deTitulo" /></a>
								</div>
							</div>
						</logic:iterate>
					</div>
				</div>
				
				<!-- Eventos -->
				<div class="portlet">
					<div class="portlet-header">
						Eventos
					</div>
					<div class="portlet-content">
						<logic:iterate name="<%=Constantes.SESSION_LISTA_EVENTOS %>" id="evento" indexId="idx">
							<div class="evento">
								<a href="javascript:visualizarEvento2(${evento.noticiaPK.cdNoticia})">
									<div class="${evento.styleClass}">
										<bean:write name="evento" property="dtNoticiastring" /> -
										<bean:write name="evento" property="deTitulo" /></a>
									</div>
							</div>
						</logic:iterate>
					</div>
				</div>
			</div>

			<div class="column">
				<!-- Ranking -->
				<div class="portlet">
					<div class="portlet-header">
						Ranking
					</div>
					<div class="portlet-content">
						<%--
						<%@ include file="../home/incClassificacaoquadratual.jsp"%>
						--%>
						<script>
							function mostraClassificacao(quadrimestre) {			
								if (quadrimestre==4) {
									document.getElementById("tbQuadrimestreatual").style.display="none";
									document.getElementById("link_atual").className="lnNormal";
									document.getElementById("tbQuadrimestreanual").style.display="";			
									document.getElementById("link_anual").className="lnBold";
								} else {
									document.getElementById("tbQuadrimestreatual").style.display="";
									document.getElementById("link_atual").className="lnBold";	
									document.getElementById("tbQuadrimestreanual").style.display="none";
									document.getElementById("link_anual").className="lnNormal";
								}
							}
						</script>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" >
							<tr>
								<td valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="17" valign="top" class="textoRanking" align="right">
												<a id="link_atual" href="javascript:mostraClassificacao(<%=request.getSession().getAttribute("quadrimestre")%>);" class="lnBold">
													<%=request.getSession().getAttribute("quadrimestre")%>&ordm;	quadrimestre
												</a>
											</td>
											<td width="52%" height="17" valign="top" class="textoRanking" align="right">
												<a id="link_anual" href="javascript:mostraClassificacao(4);">
													Anual
												</a>
											</td>
											<td width="10px">&nbsp;</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="tbQuadrimestreatual">
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="7%">&nbsp;</td>
										<td>&nbsp;</td>
										<td width="10%" align="center">P</td>
										<td width="10%" align="center">J</td>
										<td width="10%" align="center">V</td>
										<td width="10%" align="center">E</td>
									</tr>
									<% int pos = 1; %>
									<logic:iterate name="<%=Constantes.SESSION_RANKINGATUAL %>" id="row">
										<tr>
											<td class="linhaLegenda"><%=pos%>º</td>
											<td class="linhaLegenda">
												<bean:write name="row" property="socio.nmApelido" />
											</td>
											<td align="center" class="linhaLegenda">
												<bean:write name="row" property="nuPontos" />
											</td>
											<td align="center" class="linhaLegenda">
												<bean:write name="row" property="nuJogos" />
											</td>
											<td align="center" class="linhaLegenda">
												<bean:write name="row" property="nuVitorias" />
											</td>
											<td align="center" class="linhaLegenda">
												<bean:write name="row" property="nuEmpates" />
											</td>
										</tr>
										<% pos++; %>
										</logic:iterate>
									</table>
								</td>
							</tr>
							<tr id="tbQuadrimestreanual" style="display:none;">
							<td>
								<table width="100%" border="0" cellspacing="0" ellpadding="0">
									<tr>
										<td width="7%">&nbsp;</td>
										<td>&nbsp;</td>
										<td width="10%" align="center">P</td>
										<td width="10%" align="center">J</td>
										<td width="10%" align="center">V</td>
										<td width="10%" align="center">E</td>
									</tr>
									<% pos = 1; %>
									<logic:iterate name="<%=Constantes.SESSION_RANKINGANUAL %>" id="row">
										<tr>
											<td class="linhaLegenda"><%=pos%>º</td>
											<td class="linhaLegenda">
												<bean:write name="row" property="socio.nmApelido" />
											</td>
											<td align="center" class="linhaLegenda">
												<bean:write name="row" property="nuPontos" />
											</td>
											<td align="center" class="linhaLegenda">
												<bean:write name="row" property="nuJogos" />
											</td>
											<td align="center" class="linhaLegenda">
												<bean:write name="row" property="nuVitorias" />
											</td>
											<td align="center" class="linhaLegenda">
												<bean:write name="row" property="nuEmpates" />
											</td>
										</tr>
										<% pos++; %>
										</logic:iterate>
									</table>
								</td>
							</tr>	
							<tr>
								<td height="10"></td>
							</tr>
							<tr>
								<td class="legenda">
									P:pontos
									&nbsp;&nbsp;J:jogos&nbsp;&nbsp;&nbsp;V:vit&oacute;rias&nbsp;&nbsp;&nbsp;E:empates
								</td>
							</tr>
						</table>						
						
					</div>
				</div>
				
				<!-- Ultimo Jogo -->
				<div class="portlet">
					<div class="portlet-header">
						Último jogo&nbsp;${partida.dtPartidaformatada}
					</div>
					<div class="portlet-content">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolorz="#efefef">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td colspan="3" align="center"><b>${partida.dePlacar}</b></td>
								</tr>
								<tr>
									<td width="50%" valign="top" align="right">										
										<b>Time ${nmTimeA}</b>		
										<table width="100%" border="0" cellspacing="0" cellpadding="0">																														
											<logic:iterate name="jogo" id="row">
												<logic:equal name="row" property="cdTime" value="1">																
													<tr>
														<td align="right">
								                			<logic:greaterThan name="row" property="nuGol" value="0">
								                			 (<bean:write name="row" property="nuGol" />)
								                			</logic:greaterThan>
								                			<logic:greaterThan name="row" property="nuGolcontra" value="0">
								                				<font color="red">(-<bean:write name="row" property="nuGolcontra" />)</font>
								                			</logic:greaterThan>		                			
															<bean:write name="row" property="socio.nmApelido" />
														</td>
													</tr>
												</logic:equal>
											</logic:iterate>
										</table>
									</td>
									<td width="4">&nbsp;&nbsp;</td>
									<td width="50%" valign="top">
										<b>Time ${nmTimeB}</b><br>
										<table width="100%" border="0" cellspacing="0"
											cellpadding="0">
											<logic:iterate name="jogo" id="row">
												<logic:equal name="row" property="cdTime" value="2">																
													<tr>
														<td>
															<bean:write name="row" property="socio.nmApelido" />
								                			<logic:greaterThan name="row" property="nuGol" value="0">
								                			 (<bean:write name="row" property="nuGol" />)
								                			</logic:greaterThan>
								                			<logic:greaterThan name="row" property="nuGolcontra" value="0">
								                				<font color="red">(-<bean:write name="row" property="nuGolcontra" />)</font>
								                			</logic:greaterThan>		                			
														</td>
													</tr>
												</logic:equal>
											</logic:iterate>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3" align="left" valign="middle">
										<table width="100%" cellpadding="0" cellspacing="2" border="0" >
											<tr>
												<td valign="top" width="20"><img src="../imagens/icobolacheia.gif"></td>
												<td>${partida.deBolacheia}</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td valign="top" width="20"><img src="../imagens/icobolamurcha.jpg"></td>
												<td>${partida.deBolamurcha}</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						</table>
					</div>
				</div>							
			</div>

			<div class="column">
				<!-- Patrocionio -->
				<div class="portlet">
					<div class="portlet-header">
						Patrocínio
					</div>
					<div class="portlet-content">
						<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;width: 100%;">
						<tr>
							<td>
									<%--
								<%@ include file="../home/incPatrocinio2.jsp"%>
								--%>
								<script>
									var patrocinios = eval( ${jsonpatrocionios} );
									var codigo = 0;
									function trocaImagem() {			
										var url = "/fotos/patrocinios/" + patrocinios[codigo];
										document.getElementById("idPatrocionio").src = url;
										codigo=codigo+1;
										if (codigo >= ${nupatrocionios}) {
											codigo=0;
										}
									}
							
									setInterval("trocaImagem()", 5000);	
								</script>
								<div style="text-align: center;height: 150px">	
									<img id="idPatrocionio" src="/fotos/patrocinios/<%=((Logo)request.getAttribute((Constantes.SESSION_PATROCIONIO+"0"))).getNmLogo()%>" 
										width="250"
										onclick="PfcUtil.abrirLink('<%=((Logo)request.getAttribute((Constantes.SESSION_PATROCIONIO+"0"))).getNmLink()%>')"
										class="banner">
								</div>
							</td>
						</tr>
						</table>						
					</div>
				</div>
				
				<!-- Aniversariantes -->
				<div class="portlet">
					<div class="portlet-header">
						Aniversariantes
					</div>
					<div class="portlet-content">
						<table border="0" cellpadding="1" cellspacing="0" style="table-layout: fixed;width: 100%;">
								<tr>
									<logic:iterate name="<%=Constantes.SESSION_LIST_ANIVERSARIANTES %>" id="socio" indexId="idx">
										<td width="33%" height="100" align="center">
											<img src="/abrirSocioImagemSocio.do?socioPK.cdSocio=${socio.socioPK.cdSocio}">
										</td>		
									</logic:iterate>
								</tr>
								<tr>
									<logic:iterate name="<%=Constantes.SESSION_LIST_ANIVERSARIANTES %>" id="socio" indexId="idx">
										<td valign="top" align="center">
												<bean:write name="socio" property="dtNascimentosmall" /> <br/>
												<bean:write name="socio" property="nmApelido" />
										</td>
									</logic:iterate>
								</tr>
							
						</table>	
					</div>
				</div>
			</div>

		</div>
		<!-- End demo -->
		
		<logic:iterate name="<%=Constantes.SESSION_LIST_NOTICIAS %>" id="noticia" indexId="idx">
			<div id="dialog-noticia_${noticia.noticiaPK.cdNoticia}" title="${noticia.deTitulo}" 
				style="display:none;">
				${noticia.deNoticia}
			</div>
		</logic:iterate>
		<logic:iterate name="<%=Constantes.SESSION_LISTA_EVENTOS %>" id="evento" indexId="idx">
			<div id="dialog-evento_${evento.noticiaPK.cdNoticia}" title="${evento.deTitulo}" style="display:none;">
				${evento.deNoticia}
			</div>
		</logic:iterate>		

</html>
