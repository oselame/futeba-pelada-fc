<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page language="java" import="java.util.*" %>
<%@page import="br.com.softal.pfc.config.PfcConfig"%>
<%@page import="br.com.softal.pfc.Constantes"%>

<%@ include file="/includeTAGS.jsp"%>

<c:set var="context" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="urlCSS"  value="${context}/css" scope="page"/>
<c:set var="urlJS"   value="${context}/js"  scope="page"/>

<link rel="stylesheet" href="${urlCSS}/HTML.css" type="text/css" />
<link rel="stylesheet" href="${urlCSS}/datePicker.css" type="text/css" />
<link rel="stylesheet" href="${urlCSS}/ltchtml.css" type="text/css" />

<script language="javascript" type="text/JavaScript" src="${urlJS}/format.js"></script>
<script language="javascript" type="text/JavaScript" src="${urlJS}/html.js"></script>
<script language="javascript" type="text/JavaScript" src="${urlJS}/date.js"></script>
<script language="javascript" type="text/JavaScript" src="${urlJS}/calendarDateInput.js"></script>
<script language="javascript" type="text/JavaScript" src="${urlJS}/PfcUtil.js"></script>
<script language="javascript" type="text/JavaScript" src="${urlJS}/number.js"></script>
<script language="javascript" type="text/JavaScript" src="/dwr/interface/PfcDwr.js"></script>
<script language="javascript" type="text/JavaScript" src="/dwr/engine.js"></script>
<script language="javascript" type="text/JavaScript" src="/dwr/util.js"></script>

<%--
<link type="text/css" href="${urlJS}/jquery132/css/south-street/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${urlJS}/jquery132/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${urlJS}/jquery132/js/jquery-ui-1.7.2.custom.min.js"></script>
--%>
<link type="text/css" href="${urlJS}/jquery/development-bundle/themes/ui-lightness/ui.all.css" rel="stylesheet" />
<script language="javascript" type="text/JavaScript" src="${urlJS}/jquery/js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/JavaScript" src="${urlJS}/jquery/js/jquery-ui-1.8.1.custom.min.js"></script>

<script language="javascript" type="text/javascript" src="${urlJS}/jquery/js/jquery.tablesorter.js"></script> 
<script language="javascript" type="text/javascript" src="${urlJS}/jquery/js/jquery.tablesorter.pager.js"></script> 

<script type="text/javascript" src="${urlJS}/jquery/development-bundle/external/jquery.bgiframe-2.1.1.js"></script>
<script type="text/javascript" src="${urlJS}/jquery/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="${urlJS}/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${urlJS}/jquery/development-bundle/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="${urlJS}/jquery/development-bundle/ui/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="${urlJS}/jquery/development-bundle/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="${urlJS}/jquery/development-bundle/ui/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="${urlJS}/jquery/development-bundle/ui/jquery.ui.dialog.js"></script>

<c:set var="emailgroups" value="<%=PfcConfig.getProperty(Constantes.CONFIG_EMAIL_TO_GROUPS) %>" scope="application"/>
