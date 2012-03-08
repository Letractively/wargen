<%
	AutenticacaoHandler.verificarUsuarioAutenticado(request, response);
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unic.ra.gerador.security.AutenticacaoHandler"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/tables.css" />
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
				<div>
					<jsp:include page="../menu/MenuArquivo.jsp" />
				</div>
				<div>					
					<jsp:include page="../BarraMensagem.jsp" />
				</div>
				
				<br />
				
				<div>
					<table class="padrao">
						<tr>
							<td class="titulo">
								<a href="ListPatrimonio?ordem=numeracao">Numeração</a>
							</td>
							<td class="titulo">
								<a href="ListPatrimonio?ordem=descricao">Descrição</a>
							</td>
							<td class="titulo">
								<a href="ListPatrimonio?ordem=setor_nome">Setor</a>
							</td>
							<td class="titulo" />
						</tr>
						
						<c:forEach items="${ listPatrimonios }" var="patrimonio">
							<tr>
								<td>
									<label>${ patrimonio.numeracaoZeroEsquerda }</label>
								</td>
								<td>
									<label>${ patrimonio.descricao }</label>
								</td>
								<td>
									<label>${ patrimonio.setor.nome }</label>
								</td>
								<td>
									<form action="ListPatrimonio" method="POST">
										<input type="hidden" name="id" value="${ patrimonio.id }" />
										<input type="image" name="acao" value="excluir" src="../images/btn_lixeira.png"/> 
									</form>
								</td>
							</tr>
						</c:forEach>							
						
					</table>
				</div>
			</div>
		</div>			
	</div>
</body>
</html>