<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>

<%-- Importando a JSTL --%>
<%@attribute name="name"%>
<%@attribute name="property"%>
<%@attribute name="style"%>
<%@attribute name="onfocus"%>
<%@attribute name="onblur"%>
<%@attribute name="onchange"%>
<%@attribute name="styleClass"%>
<%@attribute name="styleId"%>
<%@attribute name="disabled"%>
<%@attribute name="showSelecione" description="true mostra selecione" type="java.lang.Boolean"%>
<%@attribute name="rows" required="true"%>
<%@attribute name="rowId" required="true"%>
<%@attribute name="rowLabel" required="true" %>

<logic:notPresent name="showSelecione" >
	<bean:define id="showSelecione" value="false"></bean:define>
</logic:notPresent>

<logic:equal name="disabled" value="true">
	<html:select 
		name="${name}" 
		property="${property}"
		styleClass="pfcCampoTexto ${styleClass}"
		disabled="${disabled}"
		styleId="${styleId}"
		style="${style}; color:#000000" 
		onfocus="toggleColor(this); ${onfocus} "
		onblur="toggleColor(this); ${onblur} ">
		<logic:equal name="showSelecione" value="true">
			<html:option value="">---- Selecione ----</html:option>		
		</logic:equal>
		<html:options collection="${rows}" property="${rowId}" labelProperty="${rowLabel}" />		
	</html:select>
	<html:hidden  name="${name}" property="${property}" />	
</logic:equal>

<logic:notEqual name="disabled" value="true">
	<html:select 
		name="${name}" 
		property="${property}"
		styleClass="pfcCampoTexto ${styleClass}"
		disabled="${disabled}"
		styleId="${styleId}"
		style="${style}" 
		onfocus="toggleColor(this); ${onfocus} "
		onblur="toggleColor(this); ${onblur} "
		onchange="${onchange}"> 
		<logic:equal name="showSelecione" value="true">
			<html:option value="">---- Selecione ----</html:option>		
		</logic:equal>
		<html:options collection="${rows}" property="${rowId}" labelProperty="${rowLabel}" />		
	</html:select>
</logic:notEqual>