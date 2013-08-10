<!-- pfcTimecamisaTimecamisa.jsp -->
<%@ include file="/pfcInclude.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<table width="100%" border="0" cellpadding="0" cellspacing="1"
	bgcolor="#CCCCCC">
	<tr bgcolor="#CCCCCC">
		<td bgcolor="#efefef">
			Cor Camisa
		</td>
	</tr>
</table>
<br>
<html:form action="/salvarCamisas" >
	<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td valign="top" align="right" width="80">Time A*:</td>
		<td>
			<tag:Text name="timecamisaForm" property="nmTimeA" 
				style="width:180"/>
		</td>
	</tr>
	<tr>
		<td valign="top" align="right" width="80">Time B*:</td>
		<td>
			<tag:Text name="timecamisaForm" property="nmTimeB" style="width:180"/>
		</td>
	</tr>
	</table>
	<logic:equal name="USUARIOLOGADO" value="true">
		<logic:equal name="TIPOUSUARIO" value="1">	
			<p align="right">
					<html:submit value="Salvar" property="bntSalvar" 
						styleClass="bH" onclick="return verificaObrigatorios();"/>
			</p>
		</logic:equal>	
	</logic:equal>	
</html:form>
<script>
	function verificaObrigatorios() {
		if (document.timecamisaForm["nmTimeA"].value == "") {
			alert("Cor camisa Time A não Informada.");
			document.timecamisaForm["nmTimeA"].focus();
			return false;
		}
		if (document.timecamisaForm["nmTimeB"].value == "") {
			alert("Cor camisa Time B não Informada.");
			document.timecamisaForm["nmTimeB"].focus();
			return false;
		}		
	}
</script>