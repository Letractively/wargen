package br.unic.wargen.gerador.servlet.associacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unic.wargen.gerador.controller.AssociacaoController;
import br.unic.wargen.gerador.security.Autenticacao;
import br.unic.wargen.gerador.utils.ui.UtilitariosUI;

public class ExcluiAssociacaoServlet extends HttpServlet {

	private static final long serialVersionUID = -5304585189698127789L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Autenticacao.verificarUsuarioAutenticado(request, response);
			
			AssociacaoController.excluirAssociacaoPorId(Integer.parseInt(request.getParameter("id")));
			UtilitariosUI.mostrarMensagem("Associação excluída com sucesso!", request);
			response.sendRedirect(getServletContext().getContextPath() + "/associacao/ListaAssociacao");
		}
		catch (Exception e) {
			e.printStackTrace();
			UtilitariosUI.mostrarMensagem(e.getMessage(), request);
			response.sendRedirect(getServletContext().getContextPath() + "/associacao/ListaAssociacao");
		}
	}
	
}
