<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>

<%-- Importando a JSTL --%>
<%@attribute name="name"%>
<%@attribute name="property"%>
<%@attribute name="style"%>
<%@attribute name="styleId"%>
<%@attribute name="onfocus"%>
<%@attribute name="onblur"%>
<%@attribute name="styleClass"%>
<%@attribute name="readonly" %>


<html:password 
	name="${name}" 
	property="${property}"
	styleClass="pfcCampoTexto ${styleClass}"
	style="${style}"	 
	styleId="${styleId}"
	onfocus="toggleColor(this); ${onfocus} "
	onblur="toggleColor(this); ${onblur} "	
	readonly="${readonly}" />