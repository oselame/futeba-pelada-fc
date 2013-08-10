<!-- pfcQuadrimestreCadQuadrimestre.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<p>
	<strong>Quadrimestres</strong>
</p>
<table width="100%" border="0" cellpadding="0" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr bgcolor="#CCCCCC">
		<td bgcolor="#efefef">
			Dados dos Quadrimestres
		</td>
	</tr>
</table>
<br>
<html:form action="salvarCadQuadrimestre.do">
	<tag:Msg></tag:Msg>
	<html:hidden name="quadrimestreForm" property="entidade.status"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre1.quadrimestrePK.nuAno"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre1.quadrimestrePK.cdQuadrimestre"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre1.cdSociocampeao"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre1.cdTitulo"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre1.nuJogos"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre1.flAnual"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre1.nuJogoscampeao"/> 
	<html:hidden name="quadrimestreForm" property="quadrimestre2.quadrimestrePK.nuAno"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre2.quadrimestrePK.cdQuadrimestre"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre2.cdSociocampeao"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre2.cdTitulo"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre2.nuJogos"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre2.flAnual"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre2.nuJogoscampeao"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre3.quadrimestrePK.nuAno"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre3.quadrimestrePK.cdQuadrimestre"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre3.cdSociocampeao"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre3.cdTitulo"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre3.nuJogos"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre3.flAnual"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre3.nuJogoscampeao"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre4.quadrimestrePK.nuAno"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre4.quadrimestrePK.cdQuadrimestre"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre4.cdSociocampeao"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre4.cdTitulo"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre4.nuJogos"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre4.flAnual"/>
	<html:hidden name="quadrimestreForm" property="quadrimestre4.nuJogoscampeao"/>
	<html:hidden name="quadrimestreForm" property="podeAlterar"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td valign="top" align="right" width="150">
			Ano*:
		</td>
		<td>
			<tag:Text name="quadrimestreForm" property="entidade.quadrimestrePK.nuAno" style="width:120px" styleClass="disabled" readonly="true"/>
		</td>
	</tr>	
	<tr>
		<td valign="top" align="right" width="150">
			Quadrimestre 1:
		</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<tag:Date name="quadrimestreForm" 
						property="quadrimestre1.dtIniciostring" style="width:80px" 
						onfocus="atualizaInicioQuadrimestre4();"/>
				</td>
				<td>&nbsp;até&nbsp;
				</td>
				<td>
					<tag:Date name="quadrimestreForm" property="quadrimestre1.dtFimstring" style="width:80px"
						onfocus="atualizaInicioQuadrimestre4();"/>
				</td>
			</tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td valign="top" align="right" width="150">
			Quadrimestre 2:
		</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<tag:Date name="quadrimestreForm" property="quadrimestre2.dtIniciostring" style="width:80px"
						onfocus="atualizaInicioQuadrimestre4();"/>
				</td>
				<td>&nbsp;até&nbsp;
				</td>
				<td>
					<tag:Date name="quadrimestreForm" property="quadrimestre2.dtFimstring" style="width:80px"
						onfocus="atualizaInicioQuadrimestre4();"/>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td valign="top" align="right" width="150">
			Quadrimestre 3:
		</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<tag:Date name="quadrimestreForm" property="quadrimestre3.dtIniciostring" style="width:80px"
						onfocus="atualizaInicioQuadrimestre4();"/>
				</td>
				<td>&nbsp;até&nbsp;
				</td>
				<td>
					<tag:Date name="quadrimestreForm" property="quadrimestre3.dtFimstring" style="width:80px"
						onfocus="atualizaInicioQuadrimestre4();"/>
				</td>
			</tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td valign="top" align="right" width="150">
			Quadrimestre 4:
		</td>
		<td>
			<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<tag:Date name="quadrimestreForm" property="quadrimestre4.dtIniciostring" 
						style="width:80px" readonly="true" styleClass="disabled"
						onfocus="atualizaInicioQuadrimestre4();"/>
				</td>
				<td>&nbsp;até&nbsp;
				</td>
				<td>
					<tag:Date name="quadrimestreForm" property="quadrimestre4.dtFimstring" 
						style="width:80px" 
						readonly="true" styleClass="disabled"						
						onfocus="atualizaInicioQuadrimestre4();"/>
				</td>
				<td>
					Anual
				</td>
			</tr>
			</table>
		</td>
	</tr>		
	</table>
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">	
			<p align="right">
				<logic:equal name="quadrimestreForm" property="podeAlterar" value="true">
					<html:submit value="Salvar" property="bntSalvar" 
						styleClass="bH" onclick="return verificaObrigatorios();"/>
				</logic:equal>
				<html:button value="Novo" property="bntNovo" 
					styleClass="bN" onclick="location.href='abrirCadQuadrimestre.do'" />
				<html:button value="Voltar" property="bntNovo" 
					styleClass="bN" onclick="location.href='abrirConQuadrimestre.do'" />
				
			</p>
		</logic:equal>
	</logic:equal>		
	<script>
		function atualizaInicioQuadrimestre4() {
			document.quadrimestreForm["quadrimestre4.dtIniciostring"].value = document.quadrimestreForm["quadrimestre1.dtIniciostring"].value
			document.quadrimestreForm["quadrimestre4.dtFimstring"].value = document.quadrimestreForm["quadrimestre3.dtFimstring"].value
			
		}
		
		function verificaObrigatorios() {
			if (document.quadrimestreForm["quadrimestre1.dtIniciostring"].value == "") {
				alert("Data de Início do quadrimestre 1 não informada!");
				document.quadrimestreForm["quadrimestre1.dtIniciostring"].focus();
				return false;
			}
			if (document.quadrimestreForm["quadrimestre1.dtFimstring"].value == "") {
				alert("Data de Fim do quadrimestre 1 não informada!");
				document.quadrimestreForm["quadrimestre1.dtFimstring"].focus();
				return false;
			}

			if (document.quadrimestreForm["quadrimestre2.dtIniciostring"].value == "") {
				alert("Data de Início do quadrimestre 2 não informada!");
				document.quadrimestreForm["quadrimestre2.dtIniciostring"].focus();
				return false;
			}
			if (document.quadrimestreForm["quadrimestre2.dtFimstring"].value == "") {
				alert("Data de Fim do quadrimestre 2 não informada!");
				document.quadrimestreForm["quadrimestre2.dtFimstring"].focus();
				return false;
			}

			if (document.quadrimestreForm["quadrimestre3.dtIniciostring"].value == "") {
				alert("Data de Início do quadrimestre 3 não informada!");
				document.quadrimestreForm["quadrimestre3.dtIniciostring"].focus();
				return false;
			}
			if (document.quadrimestreForm["quadrimestre3.dtFimstring"].value == "") {
				alert("Data de Fim do quadrimestre 3 não informada!");
				document.quadrimestreForm["quadrimestre3.dtFimstring"].focus();
				return false;
			}
		}
		
		function verificaPodeAlterar() {
			if (document.quadrimestreForm["podeAlterar"].value == "false") {
				document.quadrimestreForm["quadrimestre1.dtIniciostring"].setAttribute("readonly",true);
				document.quadrimestreForm["quadrimestre1.dtIniciostring"].setAttribute("class","pfcCampoTexto disabled");
				document.quadrimestreForm["quadrimestre1.dtFimstring"].setAttribute("readonly",true);
				document.quadrimestreForm["quadrimestre1.dtFimstring"].setAttribute("class","pfcCampoTexto disabled");
								
				document.quadrimestreForm["quadrimestre2.dtIniciostring"].setAttribute("readonly",true);
				document.quadrimestreForm["quadrimestre2.dtIniciostring"].setAttribute("class","pfcCampoTexto disabled");
				document.quadrimestreForm["quadrimestre2.dtFimstring"].setAttribute("readonly",true);
				document.quadrimestreForm["quadrimestre2.dtFimstring"].setAttribute("class","pfcCampoTexto disabled");				
				
				document.quadrimestreForm["quadrimestre3.dtIniciostring"].setAttribute("readonly",true);
				document.quadrimestreForm["quadrimestre3.dtIniciostring"].setAttribute("class","pfcCampoTexto disabled");
				document.quadrimestreForm["quadrimestre3.dtFimstring"].setAttribute("readonly",true);
				document.quadrimestreForm["quadrimestre3.dtFimstring"].setAttribute("class","pfcCampoTexto disabled");				
			}
		}
		
		verificaPodeAlterar();
	</script>
</html:form>