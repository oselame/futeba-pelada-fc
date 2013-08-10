<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@attribute name="name"%>
<%@attribute name="property"%>
<logic:present name="${name}" property="${property}">
	<bean:write name="${name}" property="${property}"/>
</logic:present>