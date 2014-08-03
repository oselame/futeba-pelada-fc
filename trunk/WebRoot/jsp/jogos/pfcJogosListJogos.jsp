<!-- pfcJogosListJogos.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<tag:Msg></tag:Msg>
<p>
	<strong>${Constantes.SESSION_TITULO}</strong>
</p>
<script>
	function showPartida(cdPartida) {
		var url = "abrirConJogosShow.do?cdPartida=" + cdPartida;
		document.location.href=url;
	}
	function reabrirPartidasate(cdPartida, dtPartida) {
		if (confirm('Você deseja reabrir todas as partidas até a data ' + dtPartida + '?')) {
			var url = "reabrirPartidasate.do?cdPartida=" + cdPartida;
			document.location.href=url;
		}
	}
	function encerrarPartidasate(cdPartida, dtPartida) {
		if (confirm('Você deseja encerrar todas as partidas até a data ' + dtPartida + '?')) {
			var url = "encerrarPartidasate.do?cdPartida=" + cdPartida;
			document.location.href=url;
		}
	}
</script>
	<table width="100%"  border="0" cellspacing="0" cellpadding="2">
		<tag:espacador />
		<tr>
			<td align="right">
				<b>Ano:</b>
			</td>
			<td>
				<bean:write name="partidaForm" property="entidade.nuAno"/>
			</td>
			<td align="right">
				<b>Quadrimestre:</b> 
			</td>
			<td>
				<bean:write name="partidaForm" property="entidade.cdQuadrimestre"/>
			</td>
		</tr>
	</table>
	<br/>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
	<tr bgcolor="#efefef">
		<td>
			Data
		</td>
		<td width="80" align="center">
			Mês
		</td>
		<td width="80" align="center">
			Ano
		</td>
		<td width="80" align="center">
			Quadrim.
		</td>
		<td width="180" align="center">
			Placar
		</td>
		<logic:equal name="USUARIOLOGADO" value="true">
			<logic:equal name="TIPOUSUARIO" value="1">
				<td width="80" align="center">
					Status
				</td>
				<td width="80" align="center">
					Reabrir
				</td>
				<td width="80" align="center">
					Encerrar
				</td>
			</logic:equal>
		</logic:equal>
	</tr>
	<logic:iterate id="row" property="rows" name="partidaForm">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td>
				<a href="javascript:showPartida(${row.partidaPK.cdPartida})">
					<bean:write name="row" property="dtPartidaformatada" />
				</a>
			</td>
			<td width="80" align="center">
				<bean:write name="row" property="deMes" />				
			</td>
			<td width="80" align="center">
				<bean:write name="row" property="nuAnodatapartida" />
			</td>
			<td width="80" align="center">
				<bean:write name="row" property="cdQuadrimestre" />
			</td>
			<td width="180" align="center">
				${nmTimeA}&nbsp;&nbsp;<b>${row.dePlacar}</b>&nbsp;&nbsp;${nmTimeB}
			</td>
			<logic:equal name="USUARIOLOGADO" value="true">
				<logic:equal name="TIPOUSUARIO" value="1">
					<td width="80" align="center">
						<logic:equal name="row" property="flConcluida" value="1">
							Encerrada
						</logic:equal>
						<logic:notEqual name="row" property="flConcluida" value="1">
							Aberta
						</logic:notEqual>					
					</td>
					<td width="80" align="center">
						<logic:equal name="row" property="flConcluida" value="1">
							<a href="javascript:reabrirPartidasate('${row.partidaPK.cdPartida}', '${row.dtPartidaformatada}')">
								Reabrir
							</a>
						</logic:equal>
						<logic:notEqual name="row" property="flConcluida" value="1">
							&nbsp;
						</logic:notEqual>
					</td>
					<td width="80" align="center">
						<logic:equal name="row" property="flConcluida" value="1">
							&nbsp;
						</logic:equal>
						<logic:notEqual name="row" property="flConcluida" value="1">
							<a href="javascript:encerrarPartidasate('${row.partidaPK.cdPartida}', '${row.dtPartidaformatada}')">
								Encerrar
							</a>
						</logic:notEqual>						
					</td>
				</logic:equal>
			</logic:equal>
		</tr>
	</logic:iterate>
</table>
<p align="right">
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">
			<html:button value="Novo" property="bntNovo" styleClass="bN" 
				onclick="location.href='abrirCadPartida.do'" />
		</logic:equal>
	</logic:equal>
</p>