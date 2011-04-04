<%
	AutenticacaoHandler.verificarUsuarioAutenticado(request, response);
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unic.ra.gerador.security.AutenticacaoHandler"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Realidade Aumentada Online</title>
</head>
<body>
	<jsp:include page="../Cabecalho.jsp" />
	
	<div class="corpo">
		<div class="panel_menu_principal borda_arredondada">
			<jsp:include page="../menu/MenuPrincipal.jsp" />
		</div>
		
		<div class="panel_conteudo borda_arredondada">			
			<div class="titulo_conteudo">
				<h2>Patrimônio</h2>
			</div>
			
			<div class="conteudo borda_arredondada">
				<jsp:include page="../menu/MenuArquivo.jsp" />
			</div>
		</div>			
	</div>
</body>
</html>