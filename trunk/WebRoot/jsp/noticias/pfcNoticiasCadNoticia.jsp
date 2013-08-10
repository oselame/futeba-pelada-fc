<!-- pfcNoticiasCadNoticia.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@page import="net.fckeditor.FCKeditor"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

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
		
		function excluirCadNoticia() {
			var cdNoticia = document.noticiaForm["entidade.noticiaPK.cdNoticia"].value;
			if (confirm("Deseja excluir o registro?")) {
				location.href="excluirCadNoticia.do?entity.noticiaPK.cdNoticia=" + cdNoticia;
			}
		}
		
		function salvar() {			
			var oEditor = FCKeditorAPI.GetInstance("EditorDefault");	
			document.noticiaForm.elements['entidade.deNoticia'].value = oEditor.GetXHTML(true);//oEditor.EditorDocument.body.innerHTML;
			if (document.noticiaForm["entidade.deTitulo"].value == "") {
				alert("Campo 'Título' não informado!");
				document.noticiaForm["entidade.deTitulo"].focus();
			} else if (document.noticiaForm["entidade.dtNoticiastring"].value == "") {
				alert("Campo 'Data' não informado!");
				document.noticiaForm["entidade.dtNoticiastring"].focus();
			} else if (document.noticiaForm.elements['entidade.deNoticia'].value == "") {
				alert("Campo 'Notícia' não informado!");
				document.noticiaForm["entidade.deNoticia"].focus();
				oEditor.Focus();
			} else {			
				document.noticiaForm.submit();					
			}
		}
		
		jQuery(document).ready(function() {
			jQuery("#flPopupExtra").click(function() {
				if (jQuery(this).is(':checked')) {
					jQuery("#flPopup").val('1');
				} else {
					jQuery("#flPopup").val('0');
				}
			});
			
			jQuery(function() {
				if (jQuery("#flPopup").val() == "1") {
					jQuery("#flPopupExtra").attr('checked', true);
				}
			});
			
		});

</script>

<%
	FCKeditor fckEditor = new FCKeditor(request, "EditorDefault");
%>

<html:form action="/salvarCadNoticia">
	<tag:Msg><br></tag:Msg>
	<html:hidden name="noticiaForm" property="entidade.status"/>
	<html:hidden name="noticiaForm" property="entidade.tpNoticia"/>
	<html:hidden name="noticiaForm" property="entidade.flPopup" styleId="flPopup"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td align="right">Código:</td>
		<td><tag:Text name="noticiaForm" 
					property="entidade.noticiaPK.cdNoticia" 
					style="width:80px" 
					readonly="true" styleClass="disabled"/>
			</td>
	</tr>
	<tr>
		<td align="right"><label for="deTitulo">Título*:</label></td>
		<td>
			<tag:Text name="noticiaForm" 
					property="entidade.deTitulo"  style="width:100%" 
					styleId="deTitulo"/>
		</td>
	</tr>
	<tr>
		<td align="right"><label for="deLink">Link:</label></td>
		<td>
			<tag:Text name="noticiaForm" 
					property="entidade.deLink" style="width:100%" 
					styleId="deLink"/>
		</td>
	</tr>
	<tr>
		<td align="right"><label for="dtNoticiastring">Data*:</label></td>
		<td>
			<tag:Date name="noticiaForm" 
					property="entidade.dtNoticiastring" style="width:80px" 
					styleId="dtNoticiastring"/>
		</td>
	</tr>
	<tr>
		<td align="right">&nbsp;</td>
		<td>
			<input type="checkbox" id="flPopupExtra">
			<label for="flPopupExtra">Mostra essa notícia em janela Popup na abertura do site até a data informada.</label>
		</td>
	</tr>
	
	<tr>
		<td align="right">&nbsp;</td>
		<td>
			<html:hidden name="noticiaForm" property="entidade.deNoticia" />
			<logic:equal name="USUARIOLOGADO" value="true">
				<logic:equal name="TIPOUSUARIO" value="1">
					<FCK:editor instanceName="EditorDefault" width="100%" height="500" value="${noticiaForm.entidade.deNoticia}">
						
					</FCK:editor>
				</logic:equal>
				<logic:notEqual name="TIPOUSUARIO" value="1">
					${noticiaForm.entidade.deNoticia}
				</logic:notEqual>
			</logic:equal>
			<logic:notEqual name="USUARIOLOGADO" value="true">
				${noticiaForm.entidade.deNoticia}
			</logic:notEqual>			
		</td>
	</tr>
	</table>
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">	
			<p align="right">
				<html:button value="Salvar" property="bntSalvar" 
					styleClass="bH" onclick="javascript:salvar();" />
				<html:button value="Novo" property="bntNovo" 
					styleClass="bN" onclick="location.href='abrirCadNoticia.do'" />
					
				<html:button property="pbExcluir" value="Excluir" styleClass="bN" 
					onclick="excluirCadNoticia()" disabled="${noticiaForm.entidade.status eq 'I'}" />				
			</p>
		</logic:equal>
	</logic:equal>

</html:form>

