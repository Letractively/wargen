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
				
				<div>
					<form action="FormPatrimonio" method="POST">
						<div>
							
							<div>
								<div>
									<label>Descrição:</label>
								</div>
								<div>
									<textarea name="descricao" rows="10" cols="40">${ descricao }</textarea>
								</div>
							</div>
							
							<div>
								<div>
									<label>Setor:</label>
								</div>										
								<div>
									<select name="setor" >
										<option value="0">** selecione **</option>
										<c:forEach var="setor" items="${listSetores}">
											<option value="${setor.id}">
												<c:out value="${setor.nome}" />
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div>
								<div></div>
								<div>
									<input type="image" src="../images/btn_salvar.gif" name="acao" value="salvar" alt="Salvar" />
									<a href="Patrimonio.jsp">
										<img src="../images/btn_cancelar.gif" alt="Cancelar" onclick="header(Location: Patrimonio.jsp)" />
									</a>
								</div>
							</div>
							
						</div>
					</form>
				</div>
			</div>
		</div>			
	</div>
</body>
</html>