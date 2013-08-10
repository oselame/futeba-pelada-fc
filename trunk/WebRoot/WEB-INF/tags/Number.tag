<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%-- Importando a JSTL --%>
<%@attribute name="name"%>
<%@attribute name="property"%>
<%@attribute name="style"%>
<%@attribute name="onfocus"%>
<%@attribute name="onblur"%>
<%@attribute name="styleClass"%>
<%@attribute name="readonly" %>
<%@attribute name="formato" required="true" %>

<html:text
	name="${name}" 
	property="${property}"
	styleClass="pfcCampoTexto ${styleClass}"
	style="${style}"	 
	onfocus="toggleColor(this); ${onfocus} "
	onblur="toggleColor(this); ${onblur} "
	onkeypress="return NumberUtil.NumberFormat(event, this, '${formato}');"
	readonly="${readonly}" />

