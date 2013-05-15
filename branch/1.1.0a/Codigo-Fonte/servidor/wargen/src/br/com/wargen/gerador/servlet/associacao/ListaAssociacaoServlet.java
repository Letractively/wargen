package br.com.wargen.gerador.servlet.associacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.wargen.gerador.controller.AssociacaoController;
import br.com.wargen.gerador.security.Autenticacao;
import br.com.wargen.gerador.utils.ui.UtilitariosUI;

public class ListaAssociacaoServlet extends HttpServlet {

	private static final long serialVersionUID = -8998837194374015469L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (Autenticacao.verificarUsuarioAutenticado(request, response)) {				
			
				request.setAttribute("listaAssociacoes", AssociacaoController.listarAssociacoes());
				
				getServletContext().getRequestDispatcher("/associacao/ListaAssociacao.jsp").forward(request, response);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			UtilitariosUI.mostrarMensagem(exc.getMessage(), request);
			getServletContext().getRequestDispatcher("/associacao/ListaAssociacao.jsp").forward(request, response);
		}
	}
}
