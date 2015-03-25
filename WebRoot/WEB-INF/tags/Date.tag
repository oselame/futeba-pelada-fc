<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>


<%-- Importando a JSTL --%>
<%@attribute name="name"%>
<%@attribute name="property"%>
<%@attribute name="style"%>
<%@attribute name="onfocus"%>
<%@attribute name="onblurs"%>
<%@attribute name="onchange"%>
<%@attribute name="styleClass"%>
<%@attribute name="readonly"%>
<%@attribute name="styleId"%>

<script language="javascript" type="text/JavaScript" src="../js/jsDate.js"></script>

<table border="0" cellpadding="0" cellspacing="0">
<tr><td>
<html:text name="${name}" property="${property}"
	styleClass="pfcCampoTexto ${styleClass}" style="${style}"
	onfocus="toggleColor(this); ${onfocus} "
	onblur="return CD_BLR(this); toggleColor(this); ${onblurs}; "
	onchange="${onchange}"
	maxlength="10"
	onkeypress="CD_KPS(this,event);"
	readonly="${readonly}"	
	styleId="${styleId}"
	/></td></tr></table>