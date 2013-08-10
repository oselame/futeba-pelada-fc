<!-- pfcOclubeInicio.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@page import="net.fckeditor.FCKeditor"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

<%@page import="br.com.softal.pfc.Noticia"%>
<style type="text/css">
<!--
.style1 {
	font-size: 9px;
	font-style: italic;
}
-->
</style>
<script>
		_editor_url = "${path}/HTMLArea/";
		_editor_lang = "pt-br";
		
		function salvar() {			
			var oEditor = FCKeditorAPI.GetInstance("EditorDefault");	
			document.configuracaoForm.elements['entidade.vlConfiguracao'].value = oEditor.GetXHTML(true);//oEditor.EditorDocument.body.innerHTML;
			if (document.configuracaoForm.elements['entidade.vlConfiguracao'].value == "") {
				alert("Campo 'Descrição' não informado!");
				document.configuracaoForm["entidade.vlConfiguracao"].focus();
				oEditor.Focus();
			} else {			
				document.configuracaoForm.submit();					
			}
		}

</script>

<%
	FCKeditor fckEditor = new FCKeditor(request, "EditorDefault");
%>

<html:form action="/salvarCadConfiguracao">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				${configuracaoForm.entidade.deConfiguracao}
			</td>
		</tr>
	</table>
	<br>
	<tag:Msg></tag:Msg>

	<html:hidden name="configuracaoForm" property="entidade.status"/>
	<html:hidden name="configuracaoForm" property="entidade.configuracaoPK.cdConfiguracao"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td align="right">&nbsp;</td>
		<td>
			<html:hidden name="configuracaoForm" property="entidade.vlConfiguracao" />
			<logic:equal name="USUARIOLOGADO" value="true">
				<logic:equal name="TIPOUSUARIO" value="1">
					<FCK:editor instanceName="EditorDefault" width="100%" height="500" value="${configuracaoForm.entidade.vlConfiguracao}">
				
					</FCK:editor>
				</logic:equal>
				<logic:notEqual name="TIPOUSUARIO" value="1">
					${configuracaoForm.entidade.vlConfiguracao}
				</logic:notEqual>
			</logic:equal>
			<logic:notEqual name="USUARIOLOGADO" value="true">
				${configuracaoForm.entidade.vlConfiguracao}
			</logic:notEqual>
		</td>
	</tr>
	</table>
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">
			<p align="right">
				<html:button value="Salvar" property="bntSalvar" styleClass="bH" onclick="javascript:salvar();" />
			</p>
		</logic:equal>
	</logic:equal>
</html:form>


