 <!-- pfcJogosCadastroPunicoes.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<style>
<!--
	.tdLeft{
		border-left-style: solid;
		border-left-width: 1px;
	}
-->
</style>
<html:form action="/salvarCadMultaPartida.do" >

	<p>
		<a href="editarCadPartida.do?cdPartida=${punicaopartidaForm.entidade.punicaopartidaPK.cdPartida}">Cadastro de Jogos</a> 
		| <a href="abrirCadSocioPartida.do?cdPartida=${punicaopartidaForm.entidade.punicaopartidaPK.cdPartida}">Jogadores</a> 
		| <strong>Punições</strong>
	</p>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				Dados do Jogo
			</td>
		</tr>
	</table>
	<br>
	<table width="100%"  border="0" cellspacing="0" cellpadding="2">
	<tag:espacador />
	<tr>
		<td width="120" align="right"><b>Ano:</b></td>
		<td width="50%">
			<bean:write name="punicaopartidaForm" property="entidade.partida.nuAno"/>
		</td>	
		<td align="right" width="100"><b>Quadrimestre:</b> </td>
		<td>
			<bean:write name="punicaopartidaForm" property="entidade.partida.cdQuadrimestre"/>
		</td>
			
	</tr>
	<tr>
		<td width="120" align="right"><b>Partida:</b></td>
		<td width="50%">
			<bean:write name="punicaopartidaForm" property="entidade.punicaopartidaPK.cdPartida"/>
		</td>	
		<td align="right"><b>Data da Partida:</b></td>
		<td>
			<bean:write name="punicaopartidaForm" property="entidade.partida.dtPartidaformatada"/>
		</td>
		
	</tr>	
	<tr>
		<td  align="right">
			<b>Placar</b>
		</td>
		<td colspan="3">
			${nmTimeA}&nbsp;${punicaopartidaForm.entidade.partida.dePlacar}&nbsp;${nmTimeB}
		</td>
	</tr>

	</table>
	<br>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				Participantes
			</td>
		</tr>
	</table>
	<br>
	<tag:Msg></tag:Msg>
	<html:hidden name="punicaopartidaForm" property="entidade.punicaopartidaPK.cdPartida" />	
	<html:hidden name="punicaopartidaForm" property="nuJogadores" styleId="nuJogadores" />
	<style>
		.desativadoClass {
			background-color: #efefef;
		}
		.habilitadoClass {
			background-color: #FFFFFF;
		}
	</style>
	
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC" style="table-layout: fixed;">
	<tr bgcolor="#CCCCCC" >
		<td ><b>Participante</label></b></td>
		<td width="80" align="center" title="Número de pontos" class="tdLeft"><b>Punição (Pontos)</b></td>
		<td width="250" align="center" title="Porquê da punição" ><b>Porquê?</b></td>
	</tr>
	<% int i = 1; %>
	<logic:iterate name="punicaopartidaForm" property="rows" id="row" indexId="idx" >	
	<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" 
		class="pfcCampoTexto ${punicaopartidaForm.entidade.partida.flConcluida eq 1 ? 'desativadoClass' : 'habilitadoClass'}" >
		<td align="left">
			<bean:write name="row" property="socio.nmApelido"/>
			<html:hidden name="row" property="punicaopartidaPK.cdSocio" indexed="true" />
			<html:hidden name="row" property="punicaopartidaPK.cdPartida" indexed="true"/>
		</td>
		
		<td align="center" class="tdLeft">
			<html:text name="row" property="nuPontospunicao" style="width:80;text-align: right;" indexed="true" 
				readonly="${punicaopartidaForm.entidade.partida.flConcluida eq 1}"
				styleClass="${punicaopartidaForm.entidade.partida.flConcluida eq 1 ? 'desativadoClass' : 'habilitadoClass'}"				
			/>
		</td>
		
		<td align="center" class="tdLeft">
			<html:text name="row" property="dePunicao" style="width:250;text-align: left;" indexed="true"
				readonly="${punicaopartidaForm.entidade.partida.flConcluida eq 1}"
				styleClass="${punicaopartidaForm.entidade.partida.flConcluida eq 1 ? 'desativadoClass' : 'habilitadoClass'}"
			/>
		</td>

	</tr>
	<%
		i++;
	%>
	</logic:iterate>	
	</table>	
	<p align="right">
		<logic:equal name="USUARIOLOGADO" value="true">
			<logic:equal name="TIPOUSUARIO" value="1">
				<logic:notEqual name="punicaopartidaForm" property="entidade.partida.flConcluida" value="1">
					<html:submit value="Salvar" styleClass="bn" />
				</logic:notEqual>
			</logic:equal>
		</logic:equal>
	</p>
</html:form>
<br>
