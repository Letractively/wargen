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
					<h2>Associações</h2>
				</div>
				
				<div class="conteudo borda_arredondada">				
					<div>
						<jsp:include page="MenuAssociacao.jsp" />
					</div>
					<div>					
						<jsp:include page="../BarraMensagem.jsp" />
					</div>
					
					<br />
					
					<div style="float: left; width: 100%">
						<table align="center" class="padrao">
							<tr>
								<td class="titulo">
									<a href="ListaArquivo?ordem=nome">Usuário</a>
								</td>
								<td class="titulo">
									<a href="ListaArquivo?ordem=nome">Descrição</a>
								</td>
								<td class="titulo">
									<a href="ListaArquivo?ordem=nome">Modelo</a>
								</td>
								<td class="titulo">
									<a href="ListaArquivo?ordem=extensao">Marcador</a>
								</td>
								<td class="titulo">
								</td>
								<td class="titulo">
								</td>
							</tr>
							
							<c:forEach items="${ listaAssociacoes }" var="associacao">
								<tr>
									<td>
										<label>${ associacao.usuario.nome }</label>
									</td>
									<td>
										<label>${ associacao.descricao }</label>
									</td>
									<td>
										<label>${ associacao.modelo.arquivoModelo.nome } . ${ associacao.modelo.arquivoModelo.extensao }</label>
									</td>
									<td>
										<label>${ associacao.marcador.arquivoMarcador.nome } . ${ associacao.marcador.arquivoMarcador.extensao }</label>
									</td>
									<td>
										<form action="/wargen/Aplicativo" method="POST" target="_blank">
											<input type="hidden" name="arquivoParametros" value="usuarios/${ associacao.usuario.login }/${ associacao.descricao }/parametros.xml" />
											<input type="submit" value="visualizar" />
										</form>
									</td>
									<td>
										<form action="/wargen/associacao/ExcluiAssociacao" method="POST">
											<input type="hidden" name="id" value="${ associacao.id }" />
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