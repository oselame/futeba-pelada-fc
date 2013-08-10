<!-- /home/incPatrocinio2.jsp -->

<%@ include file="/pfcInclude.jsp"%>
<%@page import="br.com.softal.pfc.Constantes"%>
<style type="text/css">
</style>
	<script>
		var patrocinios = eval( ${jsonpatrocionios} );
		var codigo = 0;
		function trocaImagem() {			
			var url = "/fotos/patrocinios/" + patrocinios[codigo];
			document.getElementById("idPatrocionio").src = url;
			codigo=codigo+1;
			if (codigo >= ${nupatrocionios}) {
				codigo=0;
			}
		}

		setInterval("trocaImagem()", 5000);	
	</script>
	<div style="text-align: center;height: 150px">	
		<img id="idPatrocionio" src="/fotos/patrocinios/<%=request.getAttribute((Constantes.SESSION_PATROCIONIO+"0"))%>" width="200">
	</div>
