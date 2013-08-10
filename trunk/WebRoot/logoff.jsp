<%@ include file="pfcInclude.jsp"%>
<% request.getSession().invalidate();%>
<script>
	parent.document.location.href="${context}/";
</script>