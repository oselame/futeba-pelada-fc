<!-- pfcJogosCadastro.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!-- <script src="//cdn.ckeditor.com/4.4.3/full/ckeditor.js"></script> -->
<script src="//cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script>

<html:form action="/salvarCadPartida.do" focus="entidade.dtPartidaformatada">
	<script>
		function encerrarPartida() {
			if (confirm("Deseja encerrar a partida?")) {
				document.partidaForm.action="${context}/encerrarCadPartida.do";
				document.partidaForm["entidade.flConcluida"].value = '1';
				document.partidaForm.submit();
			}
		}
		
		function reabrirPartida() {
			if (confirm("Deseja reabrir a partida atual?")) {
				document.partidaForm.action="${context}/reabrirCadPartida.do";
				document.partidaForm["entidade.flConcluida"].value = '1';
				document.partidaForm.submit();
			}
		}
		
		function enviarEmail() {
			if (confirm("Confirma o envio do e-mail?")) {
				document.partidaForm.action="${context}/enviarEmailEncerramentoPartida.do";
				document.partidaForm.submit();
			}
		}

		function excluirPartida() {
			if (confirm("Deseja excluir a partida?")) {
				document.partidaForm.action="${context}/excluirCadPartida.do";
				document.partidaForm.submit();
			}
		}
		
		function enviarEmailAtrazados() {
			if (confirm("Confirma o envio do e-mail?")) {
				document.partidaForm.action="${context}/enviarEmailAtrasadosPartida.do";
				document.partidaForm.submit();
			}
		}
	</script>
	
	<p>
		<strong>Cadastro de Jogos</strong>
		<logic:equal name="USUARIOLOGADO" value="true">	
			<logic:equal name="TIPOUSUARIO" value="1">
				<logic:notEqual name="partidaForm" property="entidade.status" value="I">
					| <a href="abrirCadSocioPartida.do?cdPartida=${partidaForm.entidade.partidaPK.cdPartida}">Jogadores</a>
					| <a href="abrirCadMultaPartida.do?cdPartida=${partidaForm.entidade.partidaPK.cdPartida}">Punições</a>
				</logic:notEqual>
			</logic:equal>
		</logic:equal>	
	</p>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				Dados do Jogo
			</td>
		</tr>
	</table>
	<br>
	<tag:Msg></tag:Msg>
	
	<html:hidden name="partidaForm" property="entidade.flEmpate" />
	<html:hidden name="partidaForm" property="entidade.cdQuadrimestre" />
	<html:hidden name="partidaForm" property="entidade.flConcluida" />
	<html:hidden name="partidaForm" property="entidade.status" />
	
	<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tag:espacador />
	<tr>
		<td width="120" align="right"><b>Ano:</b></td>
		<td width="50%">
			<tag:Text name="partidaForm" property="entidade.nuAno" style="width:80px" styleClass="disabled" readonly="true"/>
		</td>
		<td align="right" width="120"><b>Quadrimestre:</b> </td>
		<td>
			<tag:Text name="partidaForm" property="entidade.cdQuadrimestre" style="width:80px" styleClass="disabled" readonly="true"/>
		</td>	
		<td width="120" align="right"><b>Partida:</b></td>
		<td width="50%">
			<tag:Text name="partidaForm" property="entidade.partidaPK.cdPartida" style="width:80px" styleClass="disabled" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td width="120" align="right"><b>Data:</b></td>
		<td>
			<logic:equal name="partidaForm" property="entidade.flConcluida" value="0">
				<tag:Date name="partidaForm" property="entidade.dtPartidaformatada" style="width:80px"/>
			</logic:equal>
			<logic:notEqual name="partidaForm" property="entidade.flConcluida" value="0">
				<tag:Date name="partidaForm" property="entidade.dtPartidaformatada" style="width:80px" styleClass="disabled" readonly="true"/>
			</logic:notEqual>
		</td>
		<td colspan="3" align="right"><b>Participantes por time:</b></td>
		<td>
			<tag:Number name="partidaForm" formato="2" property="entidade.nuJogadorportime" style="width:80px" />
		</td>
	</tr>
	<tr>
		<td width="120" align="right"><b>Juiz:</b></td>
		<td colspan="5">
			<tag:Text name="partidaForm" property="entidade.nmJuiz"/>
		</td>
	</tr>
	</table>
	<br>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				Resultado do Jogo
			</td>
		</tr>
	</table>
	<br>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1">
	<tr>
		<td width="120" height="1px"><img src="imagens/espacador.gif" width="120" height="1px"></td>
		<td width="50%"></td>
		<td width="120" height="1px"><img src="imagens/espacador.gif" width="120" height="1px"></td>
		<td width="50%">
	</tr>
	<tr>
		<td width="120" align="right"><b>Time:</b></td>
		<td width="50%">
			<tag:Select name="partidaForm" property="entidade.cdTimevencedor" 
				rowId="timecamisaPK.cdTime" 
				rowLabel="nmTime" 
				rows="<%=Constantes.SESSION_LISTA_TIMES %>" 
				style="width:100%" styleClass="disabled" disabled="true" />
		</td>
		<td width="120" align="right"><b>Time:</b></td>
		<td width="50%">
			<tag:Select name="partidaForm" property="entidade.cdTimeperdedor" 
				rowId="timecamisaPK.cdTime" 
				rowLabel="nmTime" 
				rows="<%=Constantes.SESSION_LISTA_TIMES %>" 
				style="width:100%" styleClass="disabled" disabled="true"/>
		</td>		
	</tr>
	<tr>
		<td width="120" align="right"><b>Gols:</b></td>
		<td width="50%">
			<tag:Text name="partidaForm" property="entidade.nuGolvencedor" readonly="true" styleClass="disabled"/>
		</td>
		<td width="120" align="right"><b>Gols:</b></td>
		<td width="50%">
			<tag:Text name="partidaForm" property="entidade.nuGolperdedor" readonly="true" styleClass="disabled"/>
		</td>		
	</tr>		
	</table>
	<br>
		
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				Outras Informa&ccedil;&otilde;es
			</td>
		</tr>
	</table>
	<br>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1">
	<tr>
		<td width="120" align="right"><b>Bola Cheia:</b></td>
		<td><tag:TextArea name="partidaForm" property="entidade.deBolacheia" rows="3" styleClass="ckeditor"/></td>
	</tr>
	<tr>
		<td width="120" align="right"><b>Bola Murcha:</b></td>
		<td>
			<tag:TextArea name="partidaForm" property="entidade.deBolamurcha" rows="3" styleClass="ckeditor"/></td>
	</tr>
	<tr>
		<td width="120" align="right"><b>Observa&ccedil;&atilde;o:</b></td>
		<td><tag:TextArea name="partidaForm" property="entidade.deObservacao" rows="3" styleClass="ckeditor"/></td>
	</tr>	
	</table>
	<p align="right">
		<logic:equal name="USUARIOLOGADO" value="true">	
			<logic:equal name="TIPOUSUARIO" value="1">		
				<html:submit value="Salvar" property="bntSalvar" styleClass="bH"/>
				<html:button value="Novo" property="bntNovo" styleClass="bN" 
					onclick="location.href='abrirCadPartida.do'" title="Nova Partida"/>
				<logic:notEqual name="partidaForm" property="entidade.status" value="I">
					<logic:equal name="partidaForm" property="entidade.flConcluida" value="0">
						<html:button value="Excluir" property="btnExcluir" styleClass="bN" onclick="javascript:excluirPartida();" />
						<html:button value="Encerrar" property="bntEncerrar" styleClass="bN" onclick="javascript:encerrarPartida();" />
					</logic:equal>
					
					<logic:equal name="partidaForm" property="entidade.flConcluida" value="1">
						<html:button value="Reabrir" property="bntReabrir" styleClass="bN" onclick="javascript:reabrirPartida();" />
					</logic:equal>
					
					<logic:equal name="partidaForm" property="entidade.flConcluida" value="1">
						<html:button value="E-mail partida" property="bntEnviarEmail" styleClass="bN" onclick="javascript:enviarEmail();" />
					</logic:equal>
					
					<logic:equal name="partidaForm" property="entidade.flConcluida" value="1">
						<logic:equal name="partidaForm" value="true" property="mostraBotaoEnviarEmailAtrasados">
							<html:button value="E-mail atrasados" property="bntEnviarEmailAtrasados" 
								styleClass="bN" onclick="javascript:enviarEmailAtrazados();" />
						</logic:equal>
					</logic:equal>
				</logic:notEqual>
			</logic:equal>
		</logic:equal>
	</p>
	
</html:form>
<br>
