package br.unic.ra.gerador.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutenticacaoHandler {

	public static void verificarUsuarioAutenticado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if (request == null ||
			request.getSession() == null ||
			request.getSession().getAttribute("usuarioAutenticado") == null) {
			
			response.sendRedirect("/gerador/Login.jsp");
			return;
		}
		
	}
	
}
