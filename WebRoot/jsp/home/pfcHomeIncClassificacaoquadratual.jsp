<%@ include file="/pfcInclude.jsp"%>
<style>
	.lnBold {
		font-weight: bold;
	}
	
	.lnNormal {
		font-weight: normal;
	}
</style>
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
			<logic:iterate name="ranking" id="row">
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
			<logic:iterate name="rankinganual" id="row">
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