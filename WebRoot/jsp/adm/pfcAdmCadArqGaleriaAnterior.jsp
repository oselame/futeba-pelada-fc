<!-- pfcAdmCadArqGaleriaAnterior.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<p>
	<strong>Galeria Campeões Anteriores</strong>
</p>
<script type="text/javascript">
</script>
<table width="100%" border="0" cellpadding="1" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr bgcolor="#efefef">
		<td width="25">
			&nbsp;
		</td>
		<td>
			Arquivo
		</td>
	</tr>
		<%
		int i = 1;
		%>
		<tr onmouseover="toggleColor(this)" onmouseout="toggleColor(this)" class="pfcCampoTexto">
			<td width="25" align="center">
				<%=i%>
			</td>
			<td> 
				<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"   prefix="bean"%>
				<a target="_blank" href="../fotos/campeoes/${nmArqCampeosAnterior}">${nmArqCampeosAnterior}</a>
			</td>
		</tr>
		<%
		i++;
		%>
	
</table>


<logic:equal name="USUARIOLOGADO" value="true">
	<logic:equal name="TIPOUSUARIO" value="1">	
		<br>
		<html:form action="/salvarCadArquivoGaleriaAnterior.do" enctype="multipart/form-data">
			<html:file name="galeriaForm" property="arquivo" style="width:100%" /><br>
			<html:submit value="Salvar" property="bntSalvar" styleClass="bN" />
		</html:form>
	</logic:equal>		
</logic:equal>
