<%@ include file="/pfcInclude.jsp"%>
<b>Mensagem</b><br><br>
<%=request.getAttribute("mensagem")%><br><br>
<a href="javascript:history.go(-1)">Voltar</a>