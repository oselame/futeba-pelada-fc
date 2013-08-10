<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>

<logic:messagesPresent message="true">
	<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-style: solid;border-width: 1px;background-color: #FFF7DF">
		<logic:messagesPresent message="true" property="msgErrorLogin">
			<tr>
				<td width="40px" valign="top">
					<img src="${pageContext.request.contextPath}/imagens/icoError.gif"></img>
				</td>
				<td>							
					<div class="error"  style="overflow: hidden">
						<b><bean:message key="error.header" /></b>
							<html:messages id="err" message="true" property="msgErrorLogin">
							<li>
								<bean:write name="err" />
							</li>
							</html:messages>
					</div>
				</td>
			</tr>
		</logic:messagesPresent>
		<logic:messagesPresent message="true" property="msgError">
			<tr>
				<td width="40px" valign="top">
					<img src="${pageContext.request.contextPath}/imagens/icoError.gif"></img>
				</td>
				<td>							
					<div class="error"  style="overflow: hidden">
						<b><bean:message key="error.header" /></b>
							<html:messages id="err" message="true" property="msgError">
							<li>
								<bean:write name="err" />
							</li>
							</html:messages>
					</div>
				</td>
			</tr>
		</logic:messagesPresent>		
		<logic:messagesPresent message="true" property="msgSucesso">
			<tr>
				<td width="40px" valign="top">
					<img src="${pageContext.request.contextPath}/imagens/icoConfirm.gif"></img>
				</td>
				<td>							
					<div class="message"  style="overflow: hidden">
						<b><bean:message key="sucesso.header" /></b>
							<html:messages id="suc" message="true" property="msgSucesso">
							<li>
								<bean:write name="suc" />
							</li>
							</html:messages>
					</div>
				</td>
			</tr>
		</logic:messagesPresent>
		<logic:messagesPresent message="true" property="info">
			<tr>
				<td width="40px" valign="top">
					<img src="${pageContext.request.contextPath}/imagens/icoInfo.gif"></img>
				</td>
				<td>							
					<div class="message"  style="overflow: hidden">
						<b><bean:message key="info.header" /></b>
							<html:messages id="inf" message="true" property="info">
							<li>
								<bean:write name="inf" />
							</li>
							</html:messages>
					</div>
				</td>
			</tr>
		</logic:messagesPresent>		
	</table>
	<br/>
</logic:messagesPresent>
