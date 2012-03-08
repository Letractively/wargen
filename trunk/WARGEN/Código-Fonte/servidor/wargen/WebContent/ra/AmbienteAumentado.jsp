<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="language" content="en" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script src="js/swfobject.js" type="text/javascript"></script>
<script type="text/javascript">
	function carregar() {
		var flashvars = {
			arquivoParametros: document.getElementById('hdnArquivoParametros').value
		};
		var params = {
			menu: "false",
			scale: "noScale",
			allowFullscreen: "true",
			allowScriptAccess: "always",
			bgcolor: "#FFFFFF"
		};
		var attributes = {
			id:"wargen"
		};
		
		swfobject.embedSWF("wargen.swf", "altContent", "100%", "100%", "10.0.0", "expressInstall.swf", flashvars, params, attributes);
	}
</script>
<style type="text/css">
	html, body { height:100%; overflow:hidden; }
	body { margin:0; }
</style>
<title></title>
</head>
<body onload="carregar()">
	<input id="hdnArquivoParametros" type="hidden" name="hdnArquivoParametros" value="${ arquivoParametros }" />
	<div id="altContent">
		<h1>wargen</h1>
		<p>Alternative content</p>
		<p><a href="http://www.adobe.com/go/getflashplayer"><img 
			src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" 
			alt="Get Adobe Flash player" /></a></p>
	</div>
</body>
</html>