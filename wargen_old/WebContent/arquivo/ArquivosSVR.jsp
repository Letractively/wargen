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
	<jsp:include page="../BarraMensagem.jsp" />
	<div class="corpo">
		<div class="panel_menu_principal borda_arredondada">
			<jsp:include page="../menu/MenuPrincipal.jsp" />
		</div>
		
		<div class="panel_conteudo borda_arredondada">			
			<div class="titulo_conteudo">
				<h2>Arquivos SVR</h2>
			</div>
			
			<div class="conteudo borda_arredondada">
				<div class="panel_menu_conteudo">
					<div class="menu_conteudo borda_arredondada">
						<a href="http://www.libspark.org/wiki/saqoosha/FLARToolKit/downloadlist" shape="rect">FLARToolKit 2.5.4</a>
					</div>
					<div class="menu_conteudo borda_arredondada">
						<a href="http://www.adobe.com/cfusion/entitlement/index.cfm?e=flex4sdk" shape="rect">Flex SDK 4.1</a>
					</div>
					<div class="menu_conteudo borda_arredondada">
						<a href="http://www.flashdevelop.org/downloads/releases/FlashDevelop-3.3.1-RTM.exe" shape="rect">FlashDevelop 3.3.1</a>
					</div>
					<div class="menu_conteudo borda_arredondada">
						<a href="http://www.java.com/pt_BR/download/" shape="rect">Java Runtime 6</a>
					</div>
					<div class="menu_conteudo borda_arredondada">
						<a href="http://flash.tarotaro.org/ar/MarkerGeneratorOnline.swf" shape="rect">Marker Generator Online</a>
					</div>
				</div>
			</div>
					Acabei dormindo e nao coloquei o resto dos arquivos, nem revisei a apresentação. Precisa apenas escolher dois marcadores e colocar aqui.
		</div>			
	</div>
</body>
</html>