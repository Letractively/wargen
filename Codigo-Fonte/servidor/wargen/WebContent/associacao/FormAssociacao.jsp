<%@page import="br.com.wargen.gerador.security.Autenticacao"%>
<%
	Autenticacao.verificarUsuarioAutenticado(request, response);
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../css/estilo.css" />
		<link rel="stylesheet" type="text/css" href="../css/tables.css" />
		<title>Web Augmented Reality Generator Online</title>
		<script language="javascript" src="../scripts/scripts.js"></script>
	</head>
	<body>
		<div class="corpo">
			<div class="panel_conteudo_modal borda_arredondada">			
				<div class="titulo_conteudo">
					<h2>Associar</h2>
				</div>
				
				<div class="conteudo_modal borda_arredondada"">
				
					<div>					
						<jsp:include page="../BarraMensagem.jsp" />
					</div>
				
					<form method="POST" action="FormAssociacao">						
						<div>
							<input type="submit" value="Associar" />
						</div>
						
						<div>
							<label>Descri&ccedil;&atilde;o</label>
							<input type="text" name="descricao" value="${ descricao }" />
						</div>
						
						<div>
							<label>Disponibilizar</label>
							<input type="checkbox" name="isPublico" />
						</div>
										
						<div style="float: left">
							<table align="center" class="padrao">
								<tr>
									<td colspan="4">
										Marcadores
									</td>
								</tr>
								<tr>
									<td class="titulo">
										<a href="ListaArquivo?ordem=nome">Usuário</a>
									</td>
									<td class="titulo">
										<a href="ListaArquivo?ordem=nome">Nome</a>
									</td>
									<td class="titulo">
										<a href="ListaArquivo?ordem=extensao">Extensão</a>
									</td>
									<td class="titulo" />
								</tr>
								
								<c:forEach items="${ listaMarcadores }" var="marcador">
									<tr>
										<td>
											<label>${ marcador.usuario.nome }</label>
										</td>
										<td>
											<label>${ marcador.arquivoMarcador.nome }</label>
										</td>
										<td>
											<label>${ marcador.arquivoMarcador.extensao }</label>
										</td>
										<td>
											<input type="radio" name="marcador" value="${ marcador.id }"/>
										</td>
									</tr>
								</c:forEach>							
								
							</table>
						</div>
						
						<div style="float: left">
							<table align="center" class="padrao">
								<tr>
									<td colspan="4">
										Modelos
									</td>
								</tr>
								<tr>
									<td class="titulo" />
									<td class="titulo">
										<a href="ListaArquivo?ordem=nome">Usuário</a>
									</td>
									<td class="titulo">
										<a href="ListaArquivo?ordem=nome">Nome</a>
									</td>
									<td class="titulo">
										<a href="ListaArquivo?ordem=extensao">Extensão</a>
									</td>
								</tr>
								
								<c:forEach items="${ listaModelos }" var="modelo">
									<tr>
										<td>
											<input type="radio" name="modelo" value="${ modelo.id }"/>
										</td>
										<td>
											<label>${ modelo.usuario.nome }</label>
										</td>
										<td>
											<label>${ modelo.arquivoModelo.nome }</label>
										</td>
										<td>
											<label>${ modelo.arquivoModelo.extensao }</label>
										</td>
									</tr>
								</c:forEach>							
								
							</table>
						</div>
						
						<div>
							<table align="center" class="padrao">
								<tr>
									<td colspan="4">
										Interações
									</td>
								</tr>
								
								<tr>
									<td class="titulo">
										<input type="checkbox" name="isMovimento" />
										<label>Movimento</label>
									</td>
								</tr>
								<tr>
									<td class="titulo">
										<input type="checkbox" name="isRotacao" />
										<label>Rota&ccedil;&atilde;o</label>
									</td>
								</tr>
								<tr>
									<td class="titulo">
										<input type="checkbox" name="isEscala" />
										<label>Escala</label>
									</td>
								</tr>								
							</table>
						</div>
					</form>
				</div>
			</div>			
		</div>						
	</body>
</html>