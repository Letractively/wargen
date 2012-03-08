package br.unic.ra.gerador.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unic.ra.gerador.controller.UsuarioController;
import br.unic.ra.gerador.manager.SessionManager;
import br.unic.ra.gerador.utils.ui.UtilitariosUI;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 6583893480163258939L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String login = null;
		String senha = null;
		
		try {			
			validarParametros(request, response);

			login = request.getParameter("login");
			senha = request.getParameter("senha");
						
			UsuarioController.fazerLogin(login, senha);
			
			SessionManager.setUsuarioAutenticado(request.getSession(),
												 UsuarioController.carregarUsuarioPorLogin(login));
			
			response.sendRedirect(request.getContextPath());			
		}
		catch (Exception exc) {
			exc.printStackTrace();
			UtilitariosUI.mostrarMensagem(exc.getMessage(), request);
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
	}
	
	private void validarParametros(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("login") == null ||
			request.getParameter("login").equals("")) {
			
			throw new Exception("Por favor, informe um login.");
		}
		
		if (request.getParameter("senha") == null ||
			request.getParameter("senha").equals("")) {
			
			throw new Exception("Por favor, informe uma senha.");
		}
	}
	
}
