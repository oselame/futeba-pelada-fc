<%@ include file="/pfcInclude.jsp"%>
<%
String action = (String)request.getAttribute("action");
boolean disabledApelido = false;
if(action != null && action.equals("/adm/updatePessoa.do")){
	disabledApelido = true;
}
%>
<script src="../js/verificaCampos.js"></script>
<table  border="0" cellpadding="0" cellspacing="0" height="18">
	<tr align="center">
		<td width="150" class="pastaDesat"><a href="Pessoa_l.do">Lista de atletas </a></td>
		<td width="5" bgcolor="#FFFFFF">&nbsp;</td>
		<td width="150" class="pastaAtiva"><strong>Novo atleta </strong></td>
	</tr>
</table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="2" bgcolor="#CCCCCC"></td>
	</tr>
</table>
<br>
<html:form action="<%=action%>" method="POST" onsubmit="return verificarCampos('pessoaForm')">
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
    	<tr bgcolor="#CCCCCC">
    		<td bgcolor="#efefef">Dados Principais </td>
   		</tr>
    	</table>
	<br>
    <table width="100%"  border="0" cellspacing="0" cellpadding="2">
    	<tr>
    		<td width="150" align="right">Nome* :</td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.nome" onclick="verificaTexto(this,1)" style="width:100%" />
    			</td>
   		</tr>
    	<tr>
    		<td align="right">Apelido* :</td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.apelido" onclick="verificaTexto(this,1)" style="width:100% " readonly="<%=disabledApelido%>" maxlength="15"  />
    			</td>
   		</tr>
    	<tr>
    		<td align="right">Data nascimento : </td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.dtNascimento" onclick="verificaData(this, 0)" style="width:100% " maxlength="10"  />
    			</td>
   		</tr>
    	<tr>
    		<td align="right">Email :</td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.email"  style="width:100% " />
    			</td>
   		</tr>
    	<tr>
    		<td align="right">Telefone :</td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.telefone"  style="width:100% " />
    			</td>
   		</tr>
    	<tr>
    		<td align="right">Tipo* :</td>
    		<td>
    			<html:select name="pessoaForm" property="pessoa.tipo" style="width:100% " onclick="verificaLista(this, 0)">
    				<option></option>
    				<html:option value="1">Patrimonial</html:option>
    				<html:option value="2">Preferencial</html:option>
    				<html:option value="3">Avulso</html:option>
    				</html:select>
    			</td>
   		</tr>
   		<tr>
    		<td align="right">Senha* :</td>
    		<td>
    			<html:password name="pessoaForm" property="pessoa.senha" onclick="verificaTexto(this,1)" style="width:100% " />
    			</td>
   		</tr>
    	<tr>
    		<td align="right">Situação* :</td>
    		<td>
    			<html:select name="pessoaForm" property="pessoa.situacao" style="width:100% " onclick="verificaLista(this, 0)">
    				<option></option>
    				<html:option value="Presente">Presente</html:option>
    				<html:option value="Ausente">Ausente</html:option>
    				</html:select>
    			</td>
   		</tr>   
    	<tr>
    		<td align="right">Papel* :</td>
    		<td>
    			<html:select name="pessoaForm" property="pessoa.papel.name" style="width:100% " onclick="verificaLista(this, 0)">
    				<option></option>
						<html:options collection="listPapeis" property="name" labelProperty="name"   />
     				</html:select>
    			</td>
   		</tr>    				
   		<tr>
   			<td align="right">Ativo :</td>
   			<td>
   				<html:checkbox name="pessoaForm" property="pessoa.active"  />
   			</td>
   		</tr>
    	</table>
    <br>
    <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
    	<tr bgcolor="#CCCCCC">
    		<td bgcolor="#efefef">Endere&ccedil;o</td>
   		</tr>
    	</table>
    <br>
    <table width="100%"  border="0" cellpadding="2" cellspacing="0">
    	<tr>
    		<td width="150" align="right">Rua :</td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.endereco.rua" style="width:100% " />
    			</td>
   		</tr>
    	<tr>
    		<td width="150" align="right">Cidade :</td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.endereco.cidade"  style="width:100% " />
    			</td>
   		</tr>
    	<tr>
    		<td align="right">Estado :</td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.endereco.estado"  style="width:100% " />
    			</td>
   		</tr>
    	<tr>
    		<td width="150" align="right">Bairro :</td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.endereco.bairro"  style="width:100% " />
    			</td>
   		</tr>
    	<tr>
    		<td align="right">Complemento :</td>
    		<td>
    			<html:text name="pessoaForm" property="pessoa.endereco.complemento"  style="width:100% " />
    			</td>
   		</tr>
    	</table>
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">
		    <br>
		    <div align="right">
		    	<input type="submit" name="Submit" value="Salvar">
		    	<br>
		   	</div>
		</logic:equal>
	</logic:equal>
</html:form>
<div align="right"></div>
