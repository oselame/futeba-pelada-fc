<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"            prefix="c"%>


<%-- Importando a JSTL --%>
<%@attribute name="label"%>
<%@attribute name="obrigatorio"%>
<%@attribute name="styleId"%>
<%@attribute name="align"%>
<%@attribute name="oculta2pontos"%>

<logic:notPresent name="align" >
	<bean:define id="align" value="right" />
</logic:notPresent>
<logic:notPresent name="oculta2pontos" >
	<bean:define id="oculta2pontos" value="false" />
</logic:notPresent>

<c:if test="${not empty label}">
	<span style="font-weight: bold;text-align: ${align};">
		<label for="${styleId}">${label}<c:if test="${not empty obrigatorio && obrigatorio eq true}"><span id="span${styleId}">*</span></c:if><c:if test="${not oculta2pontos}">:</c:if></label>
	</span>
</c:if>
<c:if test="${empty label}">
	Label nao definida
</c:if>
