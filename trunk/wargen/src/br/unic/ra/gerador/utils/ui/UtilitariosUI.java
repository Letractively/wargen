package br.unic.ra.gerador.utils.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtilitariosUI {
	
	public static void mostrarMensagem(String mensagem, String pagina, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mensagem", mensagem);
		request.getRequestDispatcher(pagina).forward(request, response);
	}
}
