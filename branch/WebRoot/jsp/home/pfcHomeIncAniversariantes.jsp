<!-- /home/incAniversariantes.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td><strong style="font-size: 12"><img src="../imagens/icoaniversario.jpg" width="19" height="22">Aniversariantes</strong></td>
</tr>
<tr>
	<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<logic:iterate name="<%=Constantes.SESSION_LIST_ANIVERSARIANTES %>" id="socio">
				<tr>
					<td width="15">
						<img src="../imagens/icoBola.gif">
					</td>
					<td>
						${socio.dtNascimentosmall}
						${socio.nmApelido}
					</td>
				</tr>
			</logic:iterate>
		</table>
	</td>
</tr>
</table>