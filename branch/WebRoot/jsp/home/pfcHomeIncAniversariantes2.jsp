<!-- /home/incAniversariantes2.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<style type="text/css">
	.socio {
		padding-left: 15px;
		background-position: left;
		background-repeat: no-repeat;
		background-image: url("../imagens/icoBola.gif");
	}
</style>
<table width="100%" border="0" cellpadding="1" cellspacing="0">
	<logic:iterate name="<%=Constantes.SESSION_LIST_ANIVERSARIANTES %>" id="socio" indexId="idx">
		<tr>
			<td width="100" height="100">
				<img src="/socios/imagemSocio.do?socioPK.cdSocio=${socio.socioPK.cdSocio}">
			</td>		
			<td>
				<div class="socio ${socio.className}">
					<bean:write name="socio" property="dtNascimentosmall" /> -
					<bean:write name="socio" property="nmApelido" />
				</div>			
			</td>
		</tr>
	</logic:iterate>
</table>
