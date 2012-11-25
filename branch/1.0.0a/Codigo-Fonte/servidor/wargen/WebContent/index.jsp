<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/estilo.css" />
<title>Web Augmented Reality Online</title>
</head>
<body>
	<jsp:include page="Cabecalho.jsp" />
	
	<div class="corpo">
		<div class="panel_menu_principal borda_arredondada">
			<jsp:include page="menu/MenuPrincipal.jsp" />
		</div>
		
		<div class="panel_conteudo borda_arredondada">			
			<div class="titulo_conteudo">
				<h2>Home</h2>
			</div>
			
			<div class="conteudo borda_arredondada">
				<p>
					O desenvolvimento de aplicações de RA ainda exige o conhecimento de uma linguagem de programação,
					o que restringe a divulgação e o uso maciço de aplicações de RA nas diversas áreas do conhecimento,
					bem como na educação a distância. Visando facilitar o desenvolvimento de aplicações de RA na Web usando
					FLARToolKit o projeto de código aberto denominado Web Augmented Reality Generator (WARGen) foi criado.
					Este projeto tem como finalidade disponibilizar um gerador de aplicações em Realidade Aumentada para web,
					de maneira com que o usuário possa realizar algumas configurações, enviar arquivos de modelos virtuais e de marcadores,
					além de realizar associações de modelos virtuais, marcadores e interações de maneira simples e intuitiva via web.
					Diante disso, espera-se contribuir com a popularização das aplicações de RA facilitando o seu desenvolvimento e
					simplificando a maneira do como aprender.
				</p>
			</div>
		</div>			
	</div>
</body>
</html>