package br.unic.ra.gerador.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unic.ra.gerador.core.controller.UsuarioController;
import br.unic.ra.gerador.utils.ui.UtilitariosUI;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UsuarioController controller;
	private String login;
	private String senha;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			validarParametros(request, response);

			login = request.getParameter("login");
			senha = request.getParameter("senha");
			
			controller = new UsuarioController();
			controller.doLogin(login, senha);
			
			request.getSession().setAttribute("usuarioAutenticado", controller.loadByLogin(login));
			response.sendRedirect("/leae");
			
		}
		catch (Exception exc) {

			exc.printStackTrace();
			UtilitariosUI.mostrarMensagem(exc.getMessage(), "/Login.jsp", request, response);
			
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
