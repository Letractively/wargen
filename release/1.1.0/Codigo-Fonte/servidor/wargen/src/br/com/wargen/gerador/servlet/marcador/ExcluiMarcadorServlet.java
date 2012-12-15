package br.com.wargen.gerador.servlet.marcador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.wargen.gerador.bean.UsuarioBean;
import br.com.wargen.gerador.controller.MarcadorController;
import br.com.wargen.gerador.manager.SessionManager;
import br.com.wargen.gerador.security.Autenticacao;
import br.com.wargen.gerador.utils.ui.UtilitariosUI;

public class ExcluiMarcadorServlet extends HttpServlet {

	private static final long serialVersionUID = -5304585189698127789L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (Autenticacao.verificarUsuarioAutenticado(request, response)) {

				UsuarioBean usuario = SessionManager.getUsuarioAutenticado(request.getSession());
			
				MarcadorController.excluirMarcadorPorId(Integer.parseInt(request.getParameter("id")),
														getServletContext().getRealPath("ra/usuarios/" + usuario.getLogin()));
				
				UtilitariosUI.mostrarMensagem("Marcador excluído com sucesso!", request);
				response.sendRedirect(getServletContext().getContextPath() + "/arquivo/ListaArquivo");
			}
		}
		catch (Exception e) {
			UtilitariosUI.mostrarMensagem(e.getMessage(), request);
			response.sendRedirect(getServletContext().getContextPath() + "/arquivo/ListaArquivo");
			e.printStackTrace();
		}
	}
	
}
