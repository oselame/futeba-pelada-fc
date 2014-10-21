<!-- pfcSociosSocio.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<p>
	<strong>S&oacute;cio</strong>
</p>
<table width="100%" border="0" cellpadding="0" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr bgcolor="#CCCCCC">
		<td bgcolor="#efefef">
			Dados Principais
		</td>
	</tr>
</table>
<br>
<html:form action="/salvarCadSocio" enctype="multipart/form-data">
	<html:hidden name="socioForm" property="entidade.socioPK.cdSocio" />
	<html:hidden name="socioForm" property="entidade.status"/>

 	<tag:Msg></tag:Msg>	
	<logic:equal name="USUARIOLOGADO" value="true">
		<!-- Usuario nao pode alterar -->
		<logic:notEqual name="TIPOUSUARIO" value="1">		
			<table width="100%" border="0" cellspacing="0" cellpadding="2">
				<tr>
					<td valign="top" align="center" width="80">
						<logic:notEqual name="socioForm" property="entidade.status" value="I">
							<img src="abrirSocioImagemSocio.do?socioPK.cdSocio=${socioForm.entidade.socioPK.cdSocio}">
						</logic:notEqual>
						<logic:equal name="socioForm" property="entidade.status" value="I">
							<img src="/jsp/socios/fotos/fotoDefault.jpg">
						</logic:equal>			
					</td>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="2">							
						<tr>
							<td width="120" align="right">
								Nome:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.nmSocio" />
							</td>
						</tr>
						<tr>
							<td width="120" align="right">
								Apelido:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.nmApelido" />
							</td>
						</tr>
						<tr>
							<td width="120" align="right">
								Data nasc.:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.dtNascimentostring"/>
							</td>
						</tr>
						<tr>
							<td width="120" align="right">
								Time:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.nmTime" />
							</td>
						</tr>
						<tr>
							<td width="120" align="right">
								Cidade:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.nmCidade" />
							</td>
						</tr>
						<tr>
							<td width="120" align="right">
								UF:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.sgUf" />					
							</td>
						</tr>
						<tr>
							<td width="120" align="right">
								Profissão:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.nmProfissao" />
							</td>
						</tr>
						<tr>
							<td width="120" align="right">
								Empresa:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.nmEmpresa"/>
							</td>
						</tr>
				
						<tr>
							<td width="120" align="right">
								E-mail:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.deEmail" />
							</td>
						</tr>
				
						<tr>
							<td width="120" align="right">
								Celular:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.nuCelular" />
							</td>
						</tr>
						<tr>
							<td width="120" align="right"> 
								Casa:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.nuCasa" />
							</td>
						</tr>
						<tr>
							<td width="120" align="right">
								Trabalho:
							</td>
							<td>
								<tag:Write name="socioForm" property="entidade.nuTrabalho"  />
							</td>
						</tr>
						</table>
					</td>
				</tr>
			</table>
		</logic:notEqual>
		
		<!--  Usuario pode alterar -->
		<logic:equal name="TIPOUSUARIO" value="1">
		<table width="100%" border="0" cellspacing="0" cellpadding="2">
			<tr>
				<td valign="top" align="center" width="80">
					<logic:notEqual name="socioForm" property="entidade.status" value="I">
						<img src="abrirSocioImagemSocio.do?socioPK.cdSocio=${socioForm.entidade.socioPK.cdSocio}">
					</logic:notEqual>
					<logic:equal name="socioForm" property="entidade.status" value="I">
						<img src="/jsp/socios/fotos/fotoDefault.jpg">
					</logic:equal>			
				</td>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="2">							
					<tr>
						<td width="120" align="right">
							<tag:label label="Nome" obrigatorio="true" styleId="nmSocio" />
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nmSocio" styleId="nmSocio" />
						</td>
					</tr>
					<tr>
						<td width="120" align="right">
							<tag:label label="Apelido" obrigatorio="true" styleId="nmApelido" />
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nmApelido" style="width:200" 
								styleId="nmApelido"/>
						</td>
					</tr>
					<tr>
						<td width="120" align="right">
							<tag:label label="Data nasc." obrigatorio="true" styleId="dtNascimentostring" />
						</td>
						<td>
							<tag:Date name="socioForm" property="entidade.dtNascimentostring" style="width:80" 
								styleId="dtNascimentostring"/>
						</td>
					</tr>
					<tr>
						<td width="120" align="right">
							<tag:label label="Time" styleId="nmTime" />
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nmTime" style="width:180" 
								styleId="nmTime"/>
						</td>
					</tr>
					<tr>
						<td width="120" align="right">
							<tag:label label="Cidade" styleId="nmCidade" />
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nmCidade" style="width:180" 
								styleId="nmCidade"/>
						</td>
					</tr>
					<tr>
						<td width="120" align="right">
							<tag:label label="UF" styleId="sgUf" />
						</td>
						<td>
							<tag:Select name="socioForm" property="entidade.sgUf" style="width:80"
								rows="estados" rowId="sgSigla" rowLabel="sgSigla" 
								styleId="sgUf" />					
						</td>
					</tr>
					<tr>
						<td width="120" align="right">
							<tag:label label="Profissão" styleId="nmProfissao"  />
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nmProfissao" style="width:200" 
								styleId="nmProfissao"/>
						</td>
					</tr>
					<tr>
						<td width="120" align="right">
							<tag:label label="Empresa" styleId="Empresa"  />
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nmEmpresa" style="width:200" 
								styleId="Empresa"/>
						</td>
					</tr>
			
					<tr>
						<td width="120" align="right">
							<tag:label label="E-mail" styleId="deEmail"  />
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.deEmail" 
								styleId="deEmail"/>
						</td>
					</tr>
			
					<tr>
						<td width="120" align="right">
							<tag:label label="Celular" styleId="nuCelular"  />
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nuCelular" style="width:100" 
								styleId="nuCelular"/>
						</td>
					</tr>
					<tr>
						<td width="120" align="right">
							<tag:label label="Casa" styleId="nuCasa"  /> 
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nuCasa" style="width:100" 
								styleId="nuCasa"/>
						</td>
					</tr>
					<tr>
						<td width="120" align="right">
							<tag:label label="Trabalho" styleId="nuTrabalho"  /> 
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nuTrabalho" style="width:100" 
								styleId="nuTrabalho"/>
						</td>
					</tr>
					
					<tr>
						<td width="120" align="right">
							<tag:label label="Tipo usuario" obrigatorio="true" styleId="tpSocio"  />
						</td>
						<td>
							<tag:Select name="socioForm" property="entidade.tpSocio" style="width:100"
								rows="tiposUsuario" rowId="cdCodigo" rowLabel="deDescricao" onchange="mostraTableAdm(this.value);" 
									styleId="tpSocio"/>
						</td>
					</tr>	
					
					<tr id="lnSenha">
						<td width="120" align="right">
							<tag:label label="Senha" styleId="deSenha"  /> 
						</td>
						<td>
							<tag:Password  name="socioForm" property="entidade.deSenha" style="width:80"
								styleId="deSenha" />
						</td>
					</tr>	
	
					<tr id="lnAdministrador">
						<td width="120" align="right">
						</td>
						<td>
							<html:checkbox name="socioForm" property="entidade.flAdministrador" value="1" styleId="flAdministrador" />
							<tag:label label="Administrador" styleId="flAdministrador" oculta2pontos="true" align="left" /> 
						</td>
					</tr>	
					<tr>
						<td width="120" align="right">
							<tag:label label="Foto" styleId="arquivo"  /> 
						</td>
						<td>
							<html:file name="socioForm" property="arquivo" style="width:100%" styleId="arquivo"/>
						</td>
					</tr>
					<tr>
						<td width="120" align="right">&nbsp;</td>
						<td>
							<html:checkbox name="socioForm" property="entidade.flForauso" value="1" styleId="flForauso" />
							<tag:label label="Fora de Uso" styleId="flForauso" oculta2pontos="true" align="left" /> 
						</td>
					</tr>
					
					<tr id="lnOrdem">
						<td width="120" align="right">
							<tag:label label="Ordem" styleId="nuOrdem" obrigatorio="true" /> 
						</td>
						<td>
							<tag:Text name="socioForm" property="entidade.nuOrdem" style="width:100" 
								styleId="nuOrdem"/>
						</td>
					</tr>
					</table>
				</td>
			</tr>
		</table>
		
		<p align="right">
			<html:submit value="Salvar" property="bntSalvar" 
				styleClass="bH" onclick="return verificaObrigatorios();"/>
			<html:button value="Novo" property="bntNovo" 
				styleClass="bN" onclick="location.href='abrirCadSocio.do'" />
		</p>
		</logic:equal>	
	</logic:equal>
	
	<script>
		function mostraTableAdm(valor) {
			var lnOrdem = document.getElementById("lnOrdem");
			var lnAdmin = document.getElementById("lnAdministrador");
			var lnSenha = document.getElementById("lnSenha");
			var spandtNascimentostring = document.getElementById("spandtNascimentostring");
			spandtNascimentostring.style.display="none";
			lnOrdem.style.display="none";
			if (valor == 3) {
				/*if (valor == 3) {
			 		spandtNascimentostring.style.display="none";
			 	}*/
				lnAdmin.style.display="none";
				lnSenha.style.display="none";
			} else if (valor == 2) { //-- Preferencial
				lnSenha.style.display="";
				lnAdmin.style.display="";
				lnOrdem.style.display="";
			} else {
				lnSenha.style.display="";
				lnAdmin.style.display="";
			}
		}
		
		function verificaObrigatorios() {
			if (document.socioForm["entidade.flAdministrador"].checked) {
				if (document.socioForm["entidade.deSenha"].value == "") {
					alert("Senha do administrador não informado!");
					document.socioForm["entidade.deSenha"].focus();
					return false;
				}
			}
			if (document.socioForm["entidade.nmSocio"].value == "") {
				alert("Campo 'Nome' não informado!");
				document.socioForm["entidade.nmSocio"].focus();
				return false;
			}			
			if (document.socioForm["entidade.nmApelido"].value == "") {
				alert("Campo 'Apelido' não informado!");
				document.socioForm["entidade.nmApelido"].focus();
				return false;
			}	
			
			if (document.socioForm["entidade.tpSocio"].value == "") {
				alert("Campo 'Tipo Usuário' não informado!");
				document.socioForm["entidade.tpSocio"].focus();
				return false;
			} else if (document.socioForm["entidade.tpSocio"].value == "2") {
				if (document.socioForm["entidade.nuOrdem"].value == "") {
					alert("Campo 'Ordem' não informado!");
					document.socioForm["entidade.nuOrdem"].focus();
					return false;
				}
			} else if (document.socioForm["entidade.tpSocio"].value != "3") {
				/*if (document.socioForm["entidade.dtNascimentostring"].value == "") {
					alert("Campo 'Data nascimento' não informado!");
					document.socioForm["entidade.dtNascimentostring"].focus();
					return false;
				}*/
			}
		}

		mostraTableAdm(document.socioForm["entidade.tpSocio"].value);
	</script>
</html:form>