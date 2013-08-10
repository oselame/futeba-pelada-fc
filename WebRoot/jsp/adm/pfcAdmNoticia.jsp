<%@ page import="com.peladafc.struts.form.*, java.util.*, com.peladafc.bean.*" %>
<%@ include file="/pfcInclude.jsp"%>
<%
String action = (String)request.getAttribute("action");
boolean disabledDate = false;
if(action != null && action.equals("/adm/Noticia_u.do")){
	disabledDate = true;
}
%>
<script src="../js/verificaCampos.js"></script>
<table  border="0" cellpadding="0" cellspacing="0" height="18">
	<tr align="center">
		<td width="150" class="pastaDesat"><a href="Noticia_l.do">Lista de notícias </a></td>
		<td width="5" bgcolor="#FFFFFF">&nbsp;</td>
		<td width="150" class="pastaAtiva"><strong>Nova notícia</strong></td>
	</tr>
</table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="2" bgcolor="#CCCCCC"></td>
	</tr>
</table>
<br>
<html:form action="<%=action%>" method="POST" onsubmit="return verificarCampos('noticiaForm')">
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
    	<tr bgcolor="#CCCCCC">
    		<td bgcolor="#efefef">Dados Principais </td>
   		</tr>
   	</table>
	<br>
	<html:hidden name="noticiaForm" property="noticia.id"/>
    <table width="100%"  border="0" cellspacing="0" cellpadding="2">
    	<tr>
    		<td align="right">Data : </td>
    		<td>
    			<html:text name="noticiaForm" property="noticia.data" style="width:100% " onclick="verificaData(this,1)" maxlength="10" />
   			</td>
   		</tr>
    	<tr>
    		<td width="150" align="right">Titulo : </td>
    		<td>
    			<html:text name="noticiaForm" property="noticia.titulo" style="width:100% " onclick="verificaTexto(this,1)" maxlength="50" />
   			</td>
   		</tr>
    	<tr >
    		<td align="right">Conteúdo :</td>
    		<td>
					<html:textarea name="noticiaForm" rows="10"  property="noticia.conteudo" style="width:100% " onclick="verificaTexto(this,1)"/>
			 </td>
   		</tr>
   	</table>
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">
		    <br>
		    <div align="right"><br>
		    	<input type="submit" name="Submit" value="Salvar">
		   	</div>
		</logic:equal>
	</logic:equal>
</html:form>
<br>
