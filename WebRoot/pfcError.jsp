<!-- pfcError.jsp -->
<%@page import="br.com.softal.pfc.Constantes"%>
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
	</script>


	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		   <tr>
		   		<td height="52px">
					<jsp:include page="${context}/jsp/templates/pfcTemplatesCabecalho.jsp"></jsp:include>
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
								<jsp:include page="${context}/jsp/home/pfcHomeIncLogin.jsp" />
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
								<jsp:include page="${context}/jsp/templates/pfcTemplatesMenuPrincipal.jsp" />
							</td>
							<td width="263" class="menuFinal" width="263" height="18">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br/>
		
		<tag:Msg ></tag:Msg>
</html>
