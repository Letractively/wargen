<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/estilo.css" />
</head>
<body>
	<div class="panel_usuario borda_arredondada">
		<img src="/leae/images/btn_usuario_logado.gif"></img>
		
		<%
		UsuarioBean usuario = null;
	
		if (session.getAttribute("usuarioAutenticado") != null) {
			usuario = (UsuarioBean)session.getAttribute("usuarioAutenticado");
			%>
			<label style="font-size: 24px"><%= usuario.getNome() %></label>
			<a style="font-size: 24px" href="/leae/EfetuarLogout">sair</a>
			<%
		}
		else {
			%>
			<a style="font-size: 24px" href="/leae/Login.jsp">login</a>
			<%
		}
		%>
	</div>
</body>

<%@page import="br.unic.ra.gerador.core.bean.UsuarioBean"%></html>