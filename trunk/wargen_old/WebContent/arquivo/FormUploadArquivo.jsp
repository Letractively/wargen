<%
	AutenticacaoHandler.verificarUsuarioAutenticado(request, response);
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unic.ra.gerador.security.AutenticacaoHandler"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Realidade Aumentada Online</title>
</head>
<body>
	<div>
		<fieldset>
		<legend>ENVIAR ARQUIVO</legend>
			<form enctype="multipart/form-data" action="FormUploadArquivo" method=POST>
				<div>
					<p class="textoNegrito" align="center"><B>Modelo/Marcador</B>
					<input name="arquivo" type="file">
					<input type="submit" value="Enviar" />
		     	</div>      
	    	 </form>
		</fieldset>
	</div>
</body>
</html>