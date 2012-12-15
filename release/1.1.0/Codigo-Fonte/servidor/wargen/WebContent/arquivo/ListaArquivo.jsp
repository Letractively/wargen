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
		<link rel="stylesheet" type="text/css" href="../css/tables.css" />
		<title>Web Augmented Reality Generator Online</title>
		<script language="javascript" src="../scripts/scripts.js"></script>
	</head>
	<body>
	
		<jsp:include page="../Cabecalho.jsp" />
		<jsp:include page="../Modal.jsp" />
		
		<div class="corpo">
			<div class="panel_menu_principal borda_arredondada">
				<jsp:include page="../menu/MenuPrincipal.jsp" />
			</div>
			
			<div class="panel_conteudo borda_arredondada">			
				<div class="titulo_conteudo">
					<h2>Arquivos</h2>
				</div>
				
				<div class="conteudo borda_arredondada">				
					<div>
						<jsp:include page="MenuArquivo.jsp" />
					</div>
					<div>					
						<jsp:include page="../BarraMensagem.jsp" />
					</div>
					
					<br />
					
					<div>
						<table align="center" class="padrao">
							<tr>
								<td colspan="5">
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
								<td class="titulo" colspan="2"/>
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
										<form action="ListaArquivo" method="POST">
											<input type="hidden" name="caminho" value="${ marcador.arquivoMarcador.caminho }" />
											<input type="submit" value="download" /> 
										</form>
									</td>
									<td>
										<form action="/wargen/marcador/ExcluiMarcador" method="POST">
											<input type="hidden" name="id" value="${ marcador.id }" />
											<input type="submit" value="excluir" /> 
										</form>
									</td>
								</tr>
							</c:forEach>							
							
						</table>
					</div>
					
					<br />
					
					<div>
						<table align="center" class="padrao">
							<tr>
								<td colspan="5">
									Modelos
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
								<td class="titulo" colspan="2"/>
							</tr>
							
							<c:forEach items="${ listaModelos }" var="modelo">
								<tr>
									<td>
										<label>${ modelo.usuario.nome }</label>
									</td>
									<td>
										<label>${ modelo.arquivoModelo.nome }</label>
									</td>
									<td>
										<label>${ modelo.arquivoModelo.extensao }</label>
									</td>
									<td>
										<form action="ListaArquivo" method="POST">
											<input type="hidden" name="caminho" value="${ modelo.arquivoModelo.caminho }" />
											<input type="submit" value="download" /> 
										</form>
									</td>
									<td>
										<form action="/wargen/modelo/ExcluiModelo" method="POST">
											<input type="hidden" name="id" value="${ modelo.id }" />
											<input type="submit" value="excluir" /> 
										</form>
									</td>
								</tr>
							</c:forEach>							
							
						</table>
					</div>
					
					<br />
					
					<div>
						<table align="center" class="padrao">
							<tr>
								<td colspan="5">
									Arquivos
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
								<td class="titulo" colspan="2"/>
							</tr>
							
							<c:forEach items="${ listaArquivos }" var="arquivo">
								<tr>
									<td>
										<label>${ arquivo.usuario.nome }</label>
									</td>
									<td>
										<label>${ arquivo.nome }</label>
									</td>
									<td>
										<label>${ arquivo.extensao }</label>
									</td>
									<td>
										<form action="ListaArquivo" method="POST">
											<input type="hidden" name="caminho" value="${ arquivo.caminho }" />
											<input type="submit" value="download" /> 
										</form>
									</td>
									<td>
										<form action="/wargen/arquivo/ExcluiArquivo" method="POST">
											<input type="hidden" name="id" value="${ arquivo.id }" />
											<input type="submit" value="excluir" /> 
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