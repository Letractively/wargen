package br.com.wargen.gerador.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Autenticacao {

	public static boolean verificarUsuarioAutenticado(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request == null ||
			request.getSession() == null ||
			request.getSession().getAttribute("usuarioAutenticado") == null) {
			
			response.sendRedirect(request.getContextPath() + "/Login.jsp");
			
			return false;
		}
		
		return true;
	}
	
}
