<!-- Deve ser copiado para a pasta fotos do tomcat -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="swfobject.js"></script>
<style type="text/css">	
	/* hide from ie on mac \*/
	html { height: 100%; overflow: hidden; }
	#flashcontent {	height: 100%; }
	/* end hide */
	body { height: 100%; margin: 0; padding: 0; background-color: #000000; color:#ffffff; font-family:sans-serif; font-size:40; }
	a {	color:#cccccc; }
</style>
</head>
<body>
	<div id="flashcontent"></div>
	<% String codigo = request.getParameter("cdGaleria"); %>
	<% if (codigo != null) { %>	
	<script type="text/javascript">
		var fo = new SWFObject("TiltViewer.swf", "viewer", "100%", "100%", "9.0.28", "#000000");			
		fo.addVariable("useFlickr", "false");
		fo.addVariable("xmlURL", 'galeria_<%=codigo%>/gallery.xml');
		fo.addVariable("maxJPGSize","640");
		fo.addVariable("useReloadButton", "false");
		fo.addVariable("columns", "3");
		fo.addVariable("rows", "3");
		fo.addParam("allowFullScreen","true");
		fo.write("flashcontent");			
	</script>	
	<%} else { %>
		<br>
		<br>
		<br>
		<center>Galeria não encotrada<br><br>
			<a href="javascript:window.close();" style="color: white;">fechar</a>
		</center>
	
	<%} %>
</body>
</html>