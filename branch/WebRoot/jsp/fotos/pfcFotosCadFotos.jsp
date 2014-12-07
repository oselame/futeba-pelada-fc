<!-- pfcFotosCadFotos.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<link href="css/default.css" rel="stylesheet" type="text/css" />
<link href="css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/uploadif/swfobject.js"></script>
<script type="text/javascript" src="js/uploadif/jquery.uploadify.v2.1.0.min.js"></script>

<script>
	function deletar(valor) {
		var opcao = confirm("Deseja excluir o registro?");
		if (opcao) {
			var cdGaleria = document.galeriaForm["entidade.galeriaPK.cdGaleria"].value; 
			url = "/excluirFotoselecionada.do?cdGaleria=" + cdGaleria + "&cdFotogaleria=" + valor;
			document.location.href=url;
		}
	}
	
	function salvar() {
		$('#uploadify').uploadifyUpload();
		
	}

	$(document).ready(function() {
		$("#uploadify").uploadify({
			'uploader'       : '/js/uploadif/uploadify.swf',
			'script'         : 'salvarFotos.do',
			'cancelImg'      : '/imagens/cancel.png',
			'folder'         : '${galeriaForm.entidade.galeriaPK.cdGaleria}',
			'queueID'        : 'fileQueue',
			'auto'           : false,
			'multi'          : true,
			'onAllComplete': function() {
			     document.forms[0].submit();
			}
		});
		
		jQuery("#bntExcluir").click(function() {
			var opcao = confirm("Deseja excluir o registro?");
			if (opcao) {
				jQuery("#status").val("D");
				document.forms[0].submit();
			}
		});
	});	
</script>
<strong>Cadastro de Galerias de Fotos</strong>
<br>
<br>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
		<tr bgcolor="#CCCCCC">
			<td bgcolor="#efefef">
				Dados da Galeria
			</td>
		</tr>
	</table>
	<br>
	
<html:form action="/salvarCadFotos.do" focus="entidade.deGaleria" enctype="multipart/form-data">	
	<tag:Msg></tag:Msg>
	<html:hidden name="galeriaForm" property="entidade.status" styleId="status" />

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="120" align="right"><b>C&oacute;digo:</b></td>
		<td>
			<tag:Text name="galeriaForm" property="entidade.galeriaPK.cdGaleria" style="width:80px" styleClass="disabled" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td width="120" align="right"><b>Descrição:</b></td>
		<td>
			<tag:Text name="galeriaForm" property="entidade.deGaleria" style="width:100%" />
		</td>
	</tr>
	<tr>
		<td width="120" align="right"><b>Data:</b></td>
		<td>
			<tag:Date name="galeriaForm" property="entidade.dtGaleriastring" style="width:80px" />
		</td>
	</tr>
	</table>
	
	<p align="right">
					<html:submit value="Salvar" property="bntSalvar" styleClass="bH"/>
					<html:button value="Excluir" property="bntExcluir" styleId="bntExcluir" styleClass="bN" />
					<html:button value="Novo" property="bntNovo" styleClass="bN" onclick="location.href='abrirCadFotos.do'" />
			</p>
	<br>
	
	<table width="100%" border="0" cellpadding="1" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr bgcolor="#efefef">
		<td width="25">
			&nbsp;
		</td>
		<td>
			Foto
		</td>
		<logic:equal name="USUARIOLOGADO" value="true">
			<logic:equal name="TIPOUSUARIO" value="1">
				<td width="10">
					&nbsp;
				</td>
			</logic:equal>
		</logic:equal>
		
	</tr>
	<%
	int i = 1;
	%>
	<logic:iterate id="row" property="entidade.fotogaleria" name="galeriaForm">
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td width="25" align="center"><%=i%>
			</td>
			<td>
				<bean:write name="row" property="nmArqfoto" />
			</td>
			<logic:equal name="USUARIOLOGADO" value="true">
				<logic:equal name="TIPOUSUARIO" value="1">			
					<td width="10">
						<img src="../imagens/excluir.gif" alt="Excluir o Registro" 
							onclick="deletar(${row.fotogaleriaPK.cdFotogaleria});"></img>
					</td>
				</logic:equal>
			</logic:equal>			
		</tr>
		<%
		i++;
		%>
	</logic:iterate>
	</table>	
	
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">		
			<br>
			<logic:notEqual name="galeriaForm" property="entidade.status" value="I">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="120" align="right"><b>Inserir Arquivo:</b></td>
						<td>
							<div id="fileQueue"></div>
							<input type="file" name="uploadify" id="uploadify" />
							<p><a href="javascript:salvar()">Enviar arquivos</a> |
								<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">Limpar</a></p>
						</td>
				</tr>
				</table>	
			</logic:notEqual>
			
		</logic:equal>
	</logic:equal>
</html:form>

