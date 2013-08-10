<%@ include file="/pfcInclude.jsp"%>
<html:form action="/login.do" method="post">									
	<logic:notEqual name="USUARIOLOGADO" value="true">		
		<logic:messagesPresent message="true" property="msgErrorLogin">
				<div class="error">
					<html:messages id="err" message="true" property="msgErrorLogin">
						<bean:write name="err" />&nbsp;
					</html:messages>
				</div>
			</logic:messagesPresent>
		<table width="60%" border="0" align="right" cellpadding="0" cellspacing="0">
			<tr>
				<td align="right">
					<b>Usu&aacute;rio:</b>
				</td>
				<td>
					<html:text name="loginForm" property="usuario"														
						styleClass="campo" style="width: 100%;text-transform: uppercase;" />
						
				</td>
				<td width="2px">&nbsp;</td>
			</tr>
			<tr>
				<td align="right">
					<b>Senha:</b>
				</td>
				<td>
					<html:password name="loginForm" property="senha"
						styleClass="campo" style="width: 100%" />
				</td>
				<td width="2px">&nbsp;</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td align="right">
					<input name="Submit" type="submit" class="bN" value="Entrar">
				</td>
				<td width="2px">&nbsp;</td>
			</tr>
		</table>
	</logic:notEqual>
</html:form>