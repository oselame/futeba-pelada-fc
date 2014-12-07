<!-- home inicio.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<html>
	<head>
	<title>PeladaFC</title>

	<style type="text/css">
		.column { width: 33%; float: left; padding-bottom: 100px;  }
		.portlet { margin: 0 1em 1em 0; }
		.portlet-header { margin: 0.3em; padding-bottom: 4px; padding-left: 0.2em; }
		.portlet-header .ui-icon { float: right; }
		.portlet-content { padding: 0.4em; }
		.ui-sortable-placeholder { border: 1px dotted black; visibility: visible !important; height: 50px !important; }
		.ui-sortable-placeholder * { visibility: hidden; }
	</style>

<script type="text/javascript">
	$(function() {
		$(".column").sortable({
			connectWith: '.column'
		});

		$(".portlet").addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
			.find(".portlet-header")
				.addClass("ui-widget-header ui-corner-all")
				.prepend('<span class="ui-icon ui-icon-minusthick"></span>')
				.end()
			.find(".portlet-content");

		$(".portlet-header .ui-icon").click(function() {
			$(this).toggleClass("ui-icon-minusthick").toggleClass("ui-icon-plusthick");
			$(this).parents(".portlet:first").find(".portlet-content").toggle();
		});

		$(".column").disableSelection();
	});
	</script>

	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		   <tr>
		   		<td height="52px">
					<%@ include file="../templates/cabecalho.jsp"%>
				</td>
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
		</table>
		<br/>
		
		<div >

			<div class="column">
				<div class="portlet">
					<div class="portlet-header">
						Notícias
					</div>
					<div class="portlet-content">
						<%@ include file="../home/incNoticias2.jsp"%>
					</div>
				</div>

				<div class="portlet">
					<div class="portlet-header">
						Eventos
					</div>
					<div class="portlet-content">
						<%@ include file="../home/incEventos2.jsp"%>
					</div>
				</div>
			</div>

			<div class="column">
				<div class="portlet">
					<div class="portlet-header">
						Último jogo&nbsp;${partida.dtPartidaformatada}
					</div>
					<div class="portlet-content">
						<%@ include file="../home/incUltimojogo2.jsp"%>
					</div>
				</div>
			</div>

			<div class="column">
				<div class="portlet">
					<div class="portlet-header">
						Ranking
					</div>
					<div class="portlet-content">
						<%@ include file="../home/incClassificacaoquadratual.jsp"%>
					</div>
				</div>
				<div class="portlet">
					<div class="portlet-header">
						Aniversariantes
					</div>
					<div class="portlet-content">
						<%@ include file="../home/incAniversariantes2.jsp"%>
					</div>
				</div>
				<%-- 
				<div class="portlet">
					<div class="portlet-header">
						Patrocínio
					</div>
					<div class="portlet-content">
						<%@ include file="../home/incPatrocinio2.jsp"%>
					</div>
				</div>
				--%>
			</div>

		</div>
		<!-- End demo -->

</html>
